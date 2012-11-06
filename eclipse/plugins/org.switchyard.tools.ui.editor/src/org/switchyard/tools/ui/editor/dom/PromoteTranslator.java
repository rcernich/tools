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
package org.switchyard.tools.ui.editor.dom;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * PromoteTranslator
 * 
 * <p/>
 * Custom translator for resolving service/reference promotions.
 */
@SuppressWarnings("restriction")
public class PromoteTranslator extends ExtendedMetaDataTranslator {

    private final boolean _isService;

    /**
     * Create a new PromoteTranslator.
     * 
     * @param feature the feature
     * @param extensions the extensions manager
     * @param isService true if this is for service promotions, false for
     *            reference promotions.
     */
    public PromoteTranslator(EStructuralFeature feature, IExtensionsManager extensions, boolean isService) {
        super(getDomName(feature).toString(), feature, DOM_ATTRIBUTE, extensions);
        _isService = isService;
    }

    @Override
    public Object convertStringToValue(String strValue, EObject owner) {
        if (strValue == null || strValue.length() == 0) {
            return null;
        }
        final int separator = strValue.indexOf("/");
        if (separator < 0) {
            // No component name, so search through all the component
            // services/references
            final Composite composite = (Composite) owner.eContainer();
            loadChildren(composite, ScaPackage.eINSTANCE.getComposite_Component());
            for (Component component : composite.getComponent()) {
                loadChildren(
                        component,
                        _isService ? ScaPackage.eINSTANCE.getComponent_Service() : ScaPackage.eINSTANCE
                                .getComponent_Reference());
                final List<? extends Contract> contracts;
                if (_isService) {
                    contracts = component.getService();
                } else {
                    contracts = component.getReference();
                }
                for (Contract contract : contracts) {
                    if (strValue.equals(contract.getName())) {
                        return contract;
                    }
                }
            }
            // couldn't find it, so see if we can resolve it by id
            final Resource resource = owner.eResource();
            if (resource instanceof SwitchYardTranslatorResourceImpl) {
                // we just searched this model, so see if we can find it in the
                // associated generated resource
                final Resource generated = ((SwitchYardTranslatorResourceImpl) resource).getGeneratedResource();
                if (generated != null) {
                    return generated
                            .getEObject((_isService ? "ComponentService::" : "ComponentReference::") + strValue);
                }
            }
            return null;
        }
        return super.convertStringToValue(String.format(
                _isService ? "#//@composite/@component[name=\"%s\"]/@service[name=\"%s\"]"
                        : "#//@composite/@component[name=\"%s\"]/@reference[name=\"%s\"]", strValue.substring(0,
                        separator), strValue.substring(separator + 1)), owner);
    }

    private void loadChildren(EObject parent, EStructuralFeature feature) {
        final EMF2DOMAdapter adapter = (EMF2DOMAdapter) EcoreUtil.getExistingAdapter(parent,
                EMF2DOMAdapter.ADAPTER_CLASS);
        final Translator translator = adapter instanceof SwitchYardEMF2DOMSSEAdapter ? ((SwitchYardEMF2DOMSSEAdapter) adapter)
                .findTranslator(ExtendedMetaDataTranslator.getDomName(feature).toString(), false) : null;
        if (translator == null) {
            return;
        }
        final boolean notificationFlag = adapter.isNotificationEnabled();
        try {
            adapter.setNotificationEnabled(true);
            adapter.updateMOFFeature(translator, adapter.getNode(), parent);
        } finally {
            adapter.setNotificationEnabled(notificationFlag);
        }
    }

    @Override
    public String convertValueToString(Object value, EObject owner) {
        if (value instanceof Contract) {
            final String contractName = ((Contract) value).getName();
            final EObject component = ((Contract) value).eContainer();
            if (component instanceof Component) {
                return "" + ((Component) component).getName() + "/" + contractName;
            }
            return contractName;
        }
        return null;
    }

}
