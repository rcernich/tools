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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;

/**
 * ExtendedMetaDataTranslator
 * 
 * <p/>
 * A translator based on the extended meta-data defined on the EMF model.
 */
@SuppressWarnings("restriction")
public class ExtendedMetaDataTranslator extends Translator {

    /**
     * Interface for retrieving extended types associated with type and feature.
     */
    public interface ISpecializedTypesProvider {
        /**
         * @param type the source type.
         * @param feature the base feature.
         * @return a collection of features which represent specializations of
         *         the base feature.
         */
        Collection<EStructuralFeature> getSpecializations(EClass type, EStructuralFeature feature);
    }

    /**
     * TODO: wildcard matching probably doesn't work.
     * 
     * @param feature the feature
     * @return a QName based on the feature name and package namespace
     */
    public static QName getDomName(EStructuralFeature feature) {
        switch (ExtendedMetaData.INSTANCE.getFeatureKind(feature)) {
        case ExtendedMetaData.ATTRIBUTE_FEATURE:
        case ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE:
        case ExtendedMetaData.ELEMENT_FEATURE:
        case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
            return new QName(ExtendedMetaData.INSTANCE.getNamespace(feature),
                    ExtendedMetaData.INSTANCE.getName(feature));
        }
        return null;
    }

    /** Pattern for matching path segments containing QName strings. */
    public static final Pattern PATH_AND_NAME_PATTERN = Pattern.compile("\\G(([{][^}]*[}][^/,]*)|([^{}/,]*))[/]");

    private final ISpecializedTypesProvider _specializedTypesProvider;
    private Translator[] _children;

    /**
     * Create a new ExtendedMetaDataTranslator.
     * 
     * @param name the qname for the element (QName.toString())
     * @param feature the associated feature
     * @param style the dom style
     * @param specializations the specialized type provider.
     */
    public ExtendedMetaDataTranslator(String name, EStructuralFeature feature, int style,
            ISpecializedTypesProvider specializations) {
        super(name, feature, style);
        _specializedTypesProvider = specializations;
        // internalSetFeature(feature);
    }

    @Override
    protected void initializeDOMNameAndPath(String domNameAndPathArg) {
        if (domNameAndPathArg == null) {
            return;
        }
        this.domNameAndPath = domNameAndPathArg;

        final Matcher matcher = PATH_AND_NAME_PATTERN.matcher(domNameAndPathArg);
        if (matcher.find()) {
            int end = matcher.end();
            while (matcher.find()) {
                end = matcher.end();
            }
            fDOMNames = parseDOMNames(domNameAndPathArg.substring(end));
            fDOMPath = domNameAndPathArg.substring(0, end - 1);
        } else {
            fDOMNames = parseDOMNames(domNameAndPathArg);
            fDOMPath = "";
        }
    }

    @Override
    protected Translator[] getChildren() {
        if (_children == null) {
            final EClassifier type = getFeature().getEType();
            if (type instanceof EClass) {
                final List<Translator> translators = new ArrayList<Translator>();
                for (EStructuralFeature element : ExtendedMetaData.INSTANCE.getAllElements((EClass) type)) {
                    if (ignoreFeature(element)) {
                        continue;
                    }
                    translators.add(ExtendableFeatureTranslator.create((EClass) type, element, NO_STYLE,
                            _specializedTypesProvider));
                }
                for (EStructuralFeature attribute : ExtendedMetaData.INSTANCE.getAllAttributes((EClass) type)) {
                    if (ignoreFeature(attribute)) {
                        continue;
                    }
                    final String domName = ExtendedMetaDataTranslator.getDomName(attribute).toString();
                    Translator translator = FeatureTranslatorExtensionRegistry.instance().getTranslatorForType(
                            attribute, _specializedTypesProvider);
                    if (translator == null) {
                        translator = new ExtendedMetaDataTranslator(domName, attribute, DOM_ATTRIBUTE,
                                _specializedTypesProvider);
                    }
                    translators.add(translator);
                }
                _children = translators.toArray(new Translator[translators.size()]);
            } else {
                _children = new Translator[0];
            }
        }
        return _children;
    }

    @Override
    public Object convertStringToValue(String strValue, EObject owner) {
        if (getFeature().getEType() instanceof EDataType) {
            return EcoreUtil.createFromString(((EDataType) getFeature().getEType()), strValue);
        } else if (isShared()) {
            final URI uri = URI.createURI(strValue);
            final TranslatorResource resource = (TranslatorResource) owner.eResource();
            if (uri.isCurrentDocumentReference()) {
                return resource.getEObject(uri.fragment());
            } else if (resource.getResourceSet() != null) {
                return resource.getResourceSet().getEObject(uri, false);
            }
            return null;
        }
        return super.convertStringToValue(strValue, owner);
    }

    @Override
    public String convertValueToString(Object value, EObject owner) {
        if (getFeature().getEType() instanceof EDataType) {
            return EcoreUtil.convertToString(((EDataType) getFeature().getEType()), value);
        }
        return super.convertValueToString(value, owner);
    }

    @Override
    public boolean isManagedByParent() {
        final Translator[] children = getChildren(null, -1);
        return children == null || children.length == 0;
    }

    private boolean ignoreFeature(EStructuralFeature feature) {
        final int featureKind = ExtendedMetaData.INSTANCE.getFeatureKind(feature);
        return featureKind == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE
                || featureKind == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE
                || featureKind == ExtendedMetaData.GROUP_FEATURE;
    }
}
