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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * ExtendedMetaDataTranslator
 * 
 * <p/>
 * A translator based on the extended meta-data defined on the model.
 */
@SuppressWarnings("restriction")
public class ExtendedMetaDataTranslator extends Translator {

    /**
     * Interface for retrieving extended types associated with type and feature.
     */
    public interface IExtensionsManager {
        /**
         * @param type the source type.
         * @param feature the base feature.
         * @return a collection of features which represent extensions of the
         *         base feature.
         */
        Collection<EStructuralFeature> getExtensions(EClass type, EStructuralFeature feature);
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

    private final IExtensionsManager _extensionsManager;
    private Translator[] _children;

    /**
     * Create a new ExtendedMetaDataTranslator.
     * 
     * @param name the qname for the element (QName.toString())
     * @param feature the associated feature
     * @param style the dom style
     * @param extensions the extension manager
     */
    public ExtendedMetaDataTranslator(String name, EStructuralFeature feature, int style, IExtensionsManager extensions) {
        super(name, feature, style);
        _extensionsManager = extensions;
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
                            _extensionsManager));
                }
                for (EStructuralFeature attribute : ExtendedMetaData.INSTANCE.getAllAttributes((EClass) type)) {
                    if (ignoreFeature(attribute)) {
                        continue;
                    }
                    translators.add(new ExtendedMetaDataTranslator(getDomName(attribute).toString(), attribute,
                            DOM_ATTRIBUTE, _extensionsManager));
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
            // TODO: reference handling
            System.err.println("Need to add reference handling for " + getFeature().getName() + " = " + strValue);

            // maybe something like:
            // if !foundReference then note reference details so the value can
            // be processed later
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

    // @Override
    // public boolean isMultiValued() {
    // return getAffiliate().isMany();
    // }
    //
    // @Override
    // public boolean isMapFor(Object aFeature, Object oldValue, Object
    // newValue) {
    // return super.isMapFor(aFeature, oldValue, newValue)
    // || (getAffiliate() == aFeature &&
    // (getFeature().getEType().isInstance(oldValue) || getFeature()
    // .getEType().isInstance(newValue)));
    // }
    //
    // @Override
    // public boolean featureExists(EObject emfObject) {
    // if (emfObject == null) {
    // return false;
    // }
    //
    // return ExtendedMetaData.INSTANCE.getAffiliation(emfObject.eClass(),
    // feature) != null;
    // }
    //
    // @Override
    // public Object getMOFValue(EObject mofObject) {
    // if (mofObject == null) {
    // return null;
    // }
    // return mofObject.eGet(getAffiliate());
    // }
    //
    // @Override
    // public void setMOFValue(Notifier owner, Object value, int newIndex) {
    // if ((fStyle & UNSET_IF_NULL) != 0 && value == null) {
    // ExtendedEcoreUtil.eUnsetOrRemove((EObject) owner, getAffiliate(), value);
    // } else {
    // ExtendedEcoreUtil.eSetOrAdd((EObject) owner, getAffiliate(), value,
    // newIndex);
    // }
    // }
    //
    // @Override
    // public void removeMOFValue(Notifier owner, Object value) {
    // ExtendedEcoreUtil.eUnsetOrRemove((EObject) owner, getAffiliate(), value);
    // }
    //
    // @Override
    // public boolean isSetMOFValue(EObject emfObject) {
    // boolean isSet = emfObject.eIsSet(getAffiliate());
    // if (isEmptyTag()) {
    // return isSet && ((Boolean)
    // emfObject.eGet(getAffiliate())).booleanValue();
    // }
    // return isSet;
    // }
    //
    // @Override
    // public void unSetMOFValue(EObject emfObject) {
    // emfObject.eUnset(getAffiliate());
    // }
    //
    // @Override
    // public void clearList(EObject mofObject) {
    // ((List<?>) mofObject.eGet(getAffiliate())).clear();
    // }
    //
    // protected void setFeature(EStructuralFeature aFeature) {
    // /*
    // * Do nothing as the affiliate type won't be available until after super
    // * is initialized.
    // */
    // }
    //
    // private void internalSetFeature(EStructuralFeature feature) {
    // this.feature = feature;
    //
    // final EStructuralFeature affiliate = getAffiliate();
    // if (affiliate instanceof EReference) {
    // fStyle |= OBJECT_MAP;
    // if (!((EReference) affiliate).isContainment()) {
    // fStyle |= SHARED_REFERENCE;
    // }
    // }
    //
    // if (getEcorePackage().getEBoolean() == feature.getEType()) {
    // fStyle |= BOOLEAN_FEATURE;
    // }
    // }

    private boolean ignoreFeature(EStructuralFeature feature) {
        final int featureKind = ExtendedMetaData.INSTANCE.getFeatureKind(feature);
        return featureKind == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE
                || featureKind == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE
                || featureKind == ExtendedMetaData.GROUP_FEATURE;
    }
}
