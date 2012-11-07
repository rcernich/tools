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
package org.switchyard.tools.ui.editor.dom.generic;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl;

/**
 * EMF2DOMSSETranslatorResourceImpl
 * 
 * <p/>
 * Resource that provides support for resolving reference paths (e.g.
 * #//@feature1/@feature2.2/@feature3[name=foo]).
 */
@SuppressWarnings({"restriction", "unchecked" })
public abstract class EMF2DOMSSETranslatorResourceImpl extends TranslatorResourceImpl {

    /**
     * Create a new EMF2DOMSSETranslatorResourceImpl.
     * 
     * @param uri the resource URI
     * @param renderer the xml renderer
     */
    protected EMF2DOMSSETranslatorResourceImpl(URI uri, Renderer renderer) {
        super(uri, renderer);
    }

    /**
     * This method is overridden in CompatibilityXMIResource, but for whatever
     * reason does not walk the contents like ResourceImpl does (and
     * XMIResourceImpl and XMLResourceImpl which delegate to super).
     */
    @Override
    protected EObject getEObjectByID(String id) {
        final EObject object = super.getEObjectByID(id);
        if (object == null) {
            // copied from ResourceImpl.getEObjectByID()
            Map<String, EObject> map = getIntrinsicIDToEObjectMap();
            EObject result = null;
            for (TreeIterator<EObject> i = getAllProperContents(getContents()); i.hasNext();) {
                EObject eObject = i.next();
                String eObjectId = EcoreUtil.getID(eObject);
                if (eObjectId != null) {
                    if (map != null) {
                        map.put(eObjectId, eObject);
                    }

                    if (eObjectId.equals(id)) {
                        result = eObject;
                        if (map == null) {
                            break;
                        }
                    }
                }
            }

            return result;
        }
        return object;
    }

    protected EObject getEObject(List<String> uriFragmentPath) {
        final int size = uriFragmentPath.size();
        // this should always return the one and only root object
        EObject eObject = getEObjectForURIFragmentRootSegment(size == 0 ? "" : uriFragmentPath.get(0));
        for (int i = 1; i < size && eObject != null; ++i) {
            final String segment = uriFragmentPath.get(i);
            final EObject child = ((InternalEObject) eObject).eObjectForURIFragmentSegment(segment);
            if (child == null) {
                // maybe the dom node hasn't been processed. do it now.
                final String featureName = getFeatureNameFromURISegment(segment);
                final EStructuralFeature feature = featureName == null ? null : eObject.eClass().getEStructuralFeature(
                        featureName);
                if (feature == null) {
                    throw new IllegalArgumentException("Invalid uri segment\"" + segment + "\" for class: "
                            + eObject.eClass().getName());
                }
                final EMF2DOMAdapter adapter = (EMF2DOMAdapter) EcoreUtil.getExistingAdapter(eObject,
                        EMF2DOMAdapter.ADAPTER_CLASS);
                final Translator translator = adapter instanceof EMF2DOMSSEAdapterNS ? ((EMF2DOMSSEAdapterNS) adapter)
                        .findTranslator(ExtendedMetaDataTranslator.getDomName(feature).toString(), false) : null;
                if (translator == null) {
                    continue;
                }
                final boolean notificationFlag = adapter.isNotificationEnabled();
                try {
                    adapter.setNotificationEnabled(true);
                    adapter.updateMOFFeature(translator, adapter.getNode(), eObject);
                } finally {
                    adapter.setNotificationEnabled(notificationFlag);
                }
                eObject = ((InternalEObject) eObject).eObjectForURIFragmentSegment(segment);
            } else {
                eObject = child;
            }
        }

        return eObject;
    }

    private String getFeatureNameFromURISegment(String segment) {
        if (segment.endsWith("]")) {
            final int index = segment.indexOf('[');
            if (index > 1) {
                return segment.substring(1, index);
            }
        } else {
            if (Character.isDigit(segment.charAt(segment.length() - 1))) {
                final int index = segment.lastIndexOf('.');
                if (index > 1) {
                    return segment.substring(1, index);
                }
            }

            return segment.substring(1);
        }
        return null;
    }

}
