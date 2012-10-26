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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.switchyard.tools.ui.editor.dom.ExtendedMetaDataTranslator.IExtensionsManager;

/**
 * PackageExtensionsManager
 * 
 * <p/>
 * An extensions manager that scans the specified packages for extended types.
 */
public class PackageExtensionsManager implements IExtensionsManager {

    /**
     * The known packages to SwitchYard. Ideally, these would be contributed
     * through an extension point for better extensibility, but this suffices
     * for now.
     */
    private final Collection<EPackage> _packages;
    /**
     * Simple cache for locating type extensions in the model. For example:
     * bean:implementation.bean for sca:implementation.
     */
    private final Map<EStructuralFeature, List<EStructuralFeature>> _extensions = Collections
            .synchronizedMap(new HashMap<EStructuralFeature, List<EStructuralFeature>>());

    /**
     * Create a new PackageExtensionsManager.
     * 
     * @param packages the list of packages to scan for extended types.
     */
    public PackageExtensionsManager(Collection<EPackage> packages) {
        _packages = packages;
    }

    @Override
    public Collection<EStructuralFeature> getExtensions(EClass type, EStructuralFeature feature) {
        List<EStructuralFeature> extensions = _extensions.get(feature);
        if (extensions == null) {
            extensions = findExtensions((EClass) type, feature);
            _extensions.put(feature, extensions);
        }
        return extensions;
    }

    private List<EStructuralFeature> findExtensions(EClass type, EStructuralFeature element) {
        final List<EStructuralFeature> extensions = new ArrayList<EStructuralFeature>();
        final int featureKind = ExtendedMetaData.INSTANCE.getFeatureKind(element);
        if (featureKind == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE
                || featureKind == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE) {
            return extensions;
        }
        for (EPackage pkg : _packages) {
            final EClass root = ExtendedMetaData.INSTANCE.getDocumentRoot(pkg);
            for (EStructuralFeature extended : ExtendedMetaData.INSTANCE.getElements(root)) {
                if (ExtendedMetaData.INSTANCE.getFeatureKind(extended) == ExtendedMetaData.GROUP_FEATURE) {
                    continue;
                }
                EStructuralFeature affiliate = ExtendedMetaData.INSTANCE.getAffiliation(type, extended);
                while (affiliate != null && affiliate != extended) {
                    if (affiliate == element) {
                        if (!(new QName(ExtendedMetaData.INSTANCE.getNamespace(affiliate),
                                ExtendedMetaData.INSTANCE.getName(affiliate)).equals(new QName(
                                ExtendedMetaData.INSTANCE.getNamespace(extended), ExtendedMetaData.INSTANCE
                                        .getName(extended))))) {
                            extensions.add(extended);
                        }
                        break;
                    }
                    EStructuralFeature nextAffiliate = ExtendedMetaData.INSTANCE.getAffiliation(type, affiliate);
                    if (nextAffiliate == affiliate) {
                        break;
                    }
                    affiliate = nextAffiliate;
                }
            }
        }
        return extensions;
    }

}
