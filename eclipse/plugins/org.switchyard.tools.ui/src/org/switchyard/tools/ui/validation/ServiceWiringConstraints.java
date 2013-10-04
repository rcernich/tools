/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.switchyard.ExchangePattern;
import org.switchyard.metadata.ServiceInterface;
import org.switchyard.metadata.ServiceOperation;

/**
 * ServiceWiringConstraints
 * 
 * <p/>
 * Verifies the wiring of services. This encompasses the following:
 * <ul>
 * <li>Unique names for services, references and component services.</li>
 * <li>Matching names for references and their promoted component references.</li>
 * <li>Exactly one wire per component reference.</li>
 * <li>Interface compatibility between services on a wire.</li>
 * <li>Transformations for type conversion between different interfaces on a
 * wire.</li>
 * </ul>
 */
public class ServiceWiringConstraints extends AbstractModelConstraint {

    private WiringValidationContext _wiring;

    @Override
    public IStatus validate(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        EMFEventType eType = ctx.getEventType();

        // In the case of batch mode.
        if (eType == EMFEventType.NULL) {
            if (eObj instanceof Contract) {
                IStatus contextProblems = initialize(ctx);
                if (contextProblems == null) {
                    return validate(ctx, (Contract) eObj);
                }
                IStatus validationProblems = validate(ctx, (Contract) eObj);
                if (validationProblems == null) {
                    return contextProblems;
                }
                return ConstraintStatus.createMultiStatus(ctx,
                        Arrays.asList(new IStatus[] {contextProblems, validationProblems }));
            }
            // } else { // In the case of live mode.
        }

        return ctx.createSuccessStatus();
    }

    private IStatus initialize(IValidationContext ctx) {
        _wiring = (WiringValidationContext) ctx.getCurrentConstraintData();
        if (_wiring == null) {
            _wiring = new WiringValidationContext(ctx, SwitchYardProjectValidator.getSwitchYard(ctx.getTarget()
                    .eResource()));
            ctx.putCurrentConstraintData(_wiring);
            return _wiring.getContextStatus();
        }
        return null;
    }

    private IStatus validate(IValidationContext ctx, Contract contract) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        IStatus status = validateUniqueName(ctx, contract);
        if (status != null) {
            statuses.add(status);
        }
        status = validateInterfaceResolution(ctx, contract);
        if (status != null) {
            statuses.add(status);
        }
        status = validateInterfaceCompatibility(ctx, contract);
        if (status != null) {
            statuses.add(status);
        }
        if (statuses.isEmpty()) {
            return ctx.createSuccessStatus();
        }
        return ConstraintStatus.createMultiStatus(ctx, statuses);
    }

    private IStatus validateInterfaceCompatibility(IValidationContext ctx, Contract contract) {
        if (contract instanceof Reference) {
            return validateInterfaceCompatibility(ctx, (Reference) contract);
        } else if (contract instanceof Service) {
            return validateInterfaceCompatibility(ctx, (Service) contract);
        } else if (contract instanceof ComponentReference) {
            return validateInterfaceCompatibility(ctx, (ComponentReference) contract);
        }
        return null;
    }

    private IStatus validateInterfaceCompatibility(IValidationContext ctx, Reference reference) {
        Set<Contract> promotions = _wiring.getWires().get(reference);
        if (promotions == null || promotions.isEmpty()) {
            final ValidationProblem problem = ValidationProblem.UnusedReference;
            return ConstraintStatus.createStatus(ctx, reference, null, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage(), reference.getName());
        }
        return null;
    }

    private IStatus validateInterfaceCompatibility(IValidationContext ctx, Service service) {
        Set<Contract> services = _wiring.getWires().get(service);
        if (services == null || services.isEmpty()) {
            final ValidationProblem problem = ValidationProblem.UnboundService;
            return ConstraintStatus.createStatus(ctx, service, null, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage(), service.getName());
        }
        return validateInterfaces(ctx, service, services.iterator().next());
    }

    private IStatus validateInterfaceResolution(IValidationContext ctx, Contract contract) {
        if (_wiring.getServiceInterfaces().get(contract) == WiringValidationContext.URESOLVABLE_SERVICE_INTERFACE) {
            final ValidationProblem problem = ValidationProblem.UnresolvableServiceInterface;
            return ConstraintStatus.createStatus(ctx, contract, null, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage());
        }
        return null;
    }

    private IStatus validateInterfaces(IValidationContext ctx, Contract source, Contract target) {
        final Map<Contract, ServiceInterface> interfaces = _wiring.getServiceInterfaces();
        if (!interfaces.containsKey(source) || !interfaces.containsKey(target)) {
            // can't validate if there aren't any interfaces to validate
            /*
             * in the case where one interface exists and the other doesn't,
             * there should be an error indicating that an interface needs to be
             * defined. once that is fixed, we can move along here.
             */
            return null;
        }
        final ServiceInterface sourceInterface = interfaces.get(source);
        final ServiceInterface targetInterface = interfaces.get(target);
        if (sourceInterface == WiringValidationContext.URESOLVABLE_SERVICE_INTERFACE) {
            // if we can't get the source interface, we can't validate
            return null;
        } else if (sourceInterface.getOperations().size() == 1 && targetInterface.getOperations().size() == 1) {
            // special case for single op interfaces
            return validateOperation(ctx, source, target, sourceInterface.getOperations().iterator().next(),
                    targetInterface.getOperations().iterator().next());
        } else {
            final List<IStatus> problems = new ArrayList<IStatus>();
            for (ServiceOperation operation : sourceInterface.getOperations()) {
                IStatus status = validateOperation(ctx, source, target, operation,
                        targetInterface.getOperation(operation.getName()));
                if (status != null) {
                    problems.add(status);
                }
            }
            if (problems.size() > 0) {
                return ConstraintStatus.createMultiStatus(ctx, problems);
            }
        }
        return null;
    }

    private IStatus validateOperation(IValidationContext ctx, Contract source, Contract target,
            ServiceOperation sourceOperation, ServiceOperation targetOperation) {
        if (targetOperation == null) {
            final ValidationProblem problem = ValidationProblem.UnboundOperation;
            return ConstraintStatus.createStatus(ctx, source, Arrays.asList(new Contract[] {source, target }),
                    problem.getSeverity(), problem.ordinal(), problem.getMessage(), sourceOperation.getName());
        }
        // validate MEP
        final List<IStatus> problems = new ArrayList<IStatus>();
        IStatus status = validateTransformation(ctx, source, target, sourceOperation.getInputType(),
                targetOperation.getInputType());
        if (status != null) {
            problems.add(status);
        }
        if (sourceOperation.getExchangePattern() == ExchangePattern.IN_ONLY) {
            if (targetOperation.getExchangePattern() == ExchangePattern.IN_OUT) {
                final ValidationProblem problem = ValidationProblem.UnusedReturnValue;
                problems.add(ConstraintStatus.createStatus(ctx, source,
                        Arrays.asList(new Contract[] {source, target }), problem.getSeverity(), problem.ordinal(),
                        problem.getMessage(), sourceOperation.getName()));
            }
        } else if (targetOperation.getExchangePattern() == ExchangePattern.IN_ONLY) {
            final ValidationProblem problem = ValidationProblem.IncomptaibleMEP;
            problems.add(ConstraintStatus.createStatus(ctx, source, Arrays.asList(new Contract[] {source, target }),
                    problem.getSeverity(), problem.ordinal(), problem.getMessage(), sourceOperation.getName()));
        } else {
            status = validateTransformation(ctx, source, target, targetOperation.getOutputType(),
                    sourceOperation.getOutputType());
            if (status != null) {
                problems.add(status);
            }
            if (targetOperation.getFaultType() != null) {
                if (sourceOperation.getFaultType() == null) {
                    final ValidationProblem problem = ValidationProblem.UnhandledFault;
                    problems.add(ConstraintStatus.createStatus(ctx, source,
                            Arrays.asList(new Contract[] {source, target }), problem.getSeverity(), problem.ordinal(),
                            problem.getMessage(), sourceOperation.getName()));
                } else {
                    status = validateTransformation(ctx, source, target, targetOperation.getFaultType(),
                            sourceOperation.getFaultType());
                    if (status != null) {
                        problems.add(status);
                    }
                }
            }
        }
        if (problems.size() > 0) {
            return ConstraintStatus.createMultiStatus(ctx, problems);
        }
        return null;
    }

    private IStatus validateTransformation(IValidationContext ctx, Contract source, Contract target, QName fromType,
            QName toType) {
        if (fromType == null || toType == null || fromType.equals(toType) || _wiring.getTranformers() == null) {
            // there should be a validation error on the interface. regardless,
            // we can't validate
            return null;
        }
        final Set<QName> tos = _wiring.getTranformers().get(fromType);
        if (tos == null || !tos.contains(toType)) {
            final ValidationProblem problem = ValidationProblem.MissingTransformation;
            return ConstraintStatus.createStatus(ctx, source, Arrays.asList(new Contract[] {source, target }),
                    problem.getSeverity(), problem.ordinal(), problem.getMessage(), fromType, toType);
        }
        return null;
    }

    private IStatus validateInterfaceCompatibility(IValidationContext ctx, ComponentReference reference) {
        Set<Contract> targets = _wiring.getWires().get(reference);
        if (targets == null || targets.isEmpty()) {
            final ValidationProblem problem = ValidationProblem.UnresolvedReference;
            return ConstraintStatus.createStatus(ctx, reference, targets, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage(), reference.getName());
        } else if (targets.size() > 1) {
            final ValidationProblem problem = ValidationProblem.AmbiguousReference;
            return ConstraintStatus.createStatus(ctx, reference, targets, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage(), reference.getName());
        }
        return validateInterfaces(ctx, reference, targets.iterator().next());
    }

    private IStatus validateUniqueName(IValidationContext ctx, Contract contract) {
        final String name = contract.getName();
        if (!(contract instanceof Reference || contract instanceof ComponentService)
                || name == null) {
            // only need to verify types that provide services
            return null;
        }
        final Map<String, Set<Contract>> names = _wiring.getNames();
        if (!names.containsKey(name)) {
            return null;
        }
        final Set<Contract> contracts = names.get(name);
        boolean dupd = false;
        for (Contract other : contracts) {
            if (other == contract || other instanceof Service) {
                // not a duplicate if it's us or if it's a service.
                continue;
            }
            dupd = true;
            break;
        }
        if (dupd) {
            final ValidationProblem problem = ValidationProblem.DuplicateName;
            return ConstraintStatus.createStatus(ctx, contract, contracts, problem.getSeverity(), problem.ordinal(),
                    problem.getMessage(), name);
        }
        return null;
    }
}
