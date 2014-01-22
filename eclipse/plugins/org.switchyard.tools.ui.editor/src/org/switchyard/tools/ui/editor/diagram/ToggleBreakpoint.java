/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardType;
import org.switchyard.tools.ui.debug.IInteractionConfiguration;
import org.switchyard.tools.ui.debug.ServiceInteractionBreakpoint;
import org.switchyard.tools.ui.debug.SwitchYardDebugUtil;
import org.switchyard.tools.ui.debug.SwitchYardDebugUtil.ServiceType;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;

/**
 * ToggleBreakpoint
 * <p/>
 * Toggles a breakpoint on a selected figure.
 */
public class ToggleBreakpoint extends AbstractCustomFeature implements ICustomFeature {

    /**
     * Create a new ToggleBreakpoint.
     * 
     * @param fp the feature provider
     */
    public ToggleBreakpoint(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public void execute(ICustomContext context) {
        final PictogramElement pe = context.getPictogramElements()[0];
        final Object bo = getBusinessObjectForPictogramElement(pe);
        final IProject project = getProject();
        if (bo instanceof Contract) {
            final IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager()
                    .getBreakpoints(SwitchYardDebugUtil.MODEL_IDENTIFIER);
            IBreakpoint toDelete = null;
            for (IBreakpoint breakpoint : breakpoints) {
                final IMarker marker = breakpoint.getMarker();
                String markerType = null;
                try {
                    markerType = marker.getType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (marker == null || !project.equals(marker.getResource())
                        || !SwitchYardDebugUtil.SERVICE_INTERACTION_BREAKPOINT_MARKER_ID.equals(markerType)
                        || !breakpointMatchesSelection((ServiceInteractionBreakpoint) breakpoint, (Contract) bo)) {
                    continue;
                }
                toDelete = breakpoint;
                break;
            }
            if (toDelete != null) {
                try {
                    toDelete.delete();
                } catch (CoreException e) {
                    final IDiagramContainer container = getDiagramBehavior().getDiagramContainer();
                    final Shell shell;
                    if (container instanceof SwitchyardSCAEditor) {
                        shell = ((SwitchyardSCAEditor) container).getEditorSite().getShell();
                    } else {
                        shell = Display.getCurrent().getActiveShell();
                    }
                    MessageDialog.openError(shell, "Error Removing Breakpoint", e.getStatus().getMessage());
                    return;
                }
            } else {
                final URI uri = URI.createGenericURI("switchyard", "generated", EcoreUtil.getURI((EObject) bo)
                        .fragment());
                try {
                    SwitchYardDebugUtil.createServiceBreakpoint(project, getServiceName((Contract) bo), uri.toString(),
                            ServiceType.fromContract((Contract) bo));
                } catch (CoreException e) {
                    final IDiagramContainer container = getDiagramBehavior().getDiagramContainer();
                    final Shell shell;
                    if (container instanceof SwitchyardSCAEditor) {
                        shell = ((SwitchyardSCAEditor) container).getEditorSite().getShell();
                    } else {
                        shell = Display.getCurrent().getActiveShell();
                    }
                    MessageDialog.openError(shell, "Error Adding Breakpoint", e.getStatus().getMessage());
                    return;
                }
            }
        }
        getDiagramBehavior().refreshRenderingDecorators(pe);
    }

    private boolean breakpointMatchesSelection(final ServiceInteractionBreakpoint breakpoint, final Contract contract) {
        final IInteractionConfiguration config = breakpoint.getInteractionConfiguration();
        if (config == null) {
            return false;
        }
        final QName name = getServiceName(contract);
        if (contract instanceof Service || contract instanceof ComponentReference) {
            return config.getProviderName() == null && name.equals(config.getConsumerName());
        } else {
            return config.getConsumerName() == null && name.equals(config.getProviderName());
        }
    }

    @Override
    public String getDescription() {
        return "Set or clear a breakpoint on the selected object.";
    }

    @Override
    public boolean canExecute(ICustomContext context) {
        return isAvailable(context);
    }

    @Override
    public boolean isAvailable(IContext context) {
        if (!(context instanceof ICustomContext) || getProject() == null) {
            return false;
        }
        final PictogramElement[] elements = ((ICustomContext) context).getPictogramElements();
        final Object bo = elements == null || elements.length != 1 ? null
                : getBusinessObjectForPictogramElement(elements[0]);
        return bo instanceof Contract && getServiceName((Contract) bo).getLocalPart() != null;
    }

    @Override
    public boolean hasDoneChanges() {
        // we don't change anything
        return false;
    }

    @Override
    public String getName() {
        return "Toggle Breakpoint";
    }

    private QName getServiceName(Contract contract) {
        String targetNamespace = null;
        for (EObject container = contract.eContainer(); container != null && targetNamespace == null; container = container
                .eContainer()) {
            if (container instanceof Composite) {
                targetNamespace = ((Composite) container).getTargetNamespace();
            } else if (container instanceof SwitchYardType) {
                targetNamespace = ((SwitchYardType) container).getTargetNamespace();
            }
        }
        if (contract instanceof Service) {
            return new QName(targetNamespace, contract.getName());
        } else if (contract instanceof ComponentReference) {
            final String componentName = ((Component) contract.eContainer()).getName();
            return new QName(targetNamespace, componentName == null ? contract.getName() : componentName + "/"
                    + contract.getName());
        } else {
            return new QName(targetNamespace, contract.getName());
        }
    }

    private IProject getProject() {
        final IDiagramContainer container = getDiagramBehavior().getDiagramContainer();
        if (container instanceof SwitchyardSCAEditor) {
            return ((SwitchyardSCAEditor) container).getModelFile().getProject();
        }
        return null;
    }
}
