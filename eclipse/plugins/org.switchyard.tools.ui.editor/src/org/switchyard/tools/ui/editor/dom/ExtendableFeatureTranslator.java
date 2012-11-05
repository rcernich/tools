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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.wst.common.internal.emf.resource.MultiObjectTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.switchyard.tools.ui.editor.dom.ExtendedMetaDataTranslator.IExtensionsManager;
import org.w3c.dom.Node;

/**
 * ExtendableFeatureTranslator
 * 
 * <p/>
 * Translator that supports extended types (i.e. substitution groups).
 */
@SuppressWarnings("restriction")
public final class ExtendableFeatureTranslator extends MultiObjectTranslator {

    /**
     * @param containerType the type containing the feature.
     * @param feature the base feature.
     * @param style the style (e.g. DOM_ATTRIBUTE)
     * @param extensions the extensions manager.
     * @return a translator
     */
    public static Translator create(EClass containerType, EStructuralFeature feature, int style,
            IExtensionsManager extensions) {
        final Map<EClassifier, Translator> typeToTranslators = new HashMap<EClassifier, Translator>();
        final Map<String, Translator> nameToTranslators = new HashMap<String, Translator>();
        final StringBuffer names = new StringBuffer();
        if (true) {
            final String domName = ExtendedMetaDataTranslator.getDomName(feature).toString();
            Translator translator = TranslatorExtensionRegistry.instance().getTranslatorForType(feature, extensions);
            if (translator == null) {
                translator = new ExtendedMetaDataTranslator(domName, feature, style, extensions);
            }
            typeToTranslators.put(feature.getEType(), translator);
            nameToTranslators.put(domName, translator);
            names.append(domName);
        }
        for (EStructuralFeature extension : extensions.getExtensions(containerType, feature)) {
            if (ExtendedMetaData.INSTANCE.getFeatureKind(extension) == ExtendedMetaData.GROUP_FEATURE) {
                continue;
            }
            final String domName = ExtendedMetaDataTranslator.getDomName(extension).toString();
            Translator translator = TranslatorExtensionRegistry.instance().getTranslatorForType(extension, extensions);
            if (translator == null) {
                translator = new ExtendedMetaDataTranslator(domName, extension, style, extensions);
            }
            typeToTranslators.put(extension.getEType(), translator);
            nameToTranslators.put(domName, translator);
            names.append(",").append(domName);
        }
        return new ExtendableFeatureTranslator(names.toString(), feature, style, typeToTranslators, nameToTranslators);
    }

    private static final Translator[] EMPTY_TRANSLATORS = new Translator[] {};

    private final Map<EClassifier, Translator> _typeToTranslators;
    private final Map<String, Translator> _nameToTranslators;

    private ExtendableFeatureTranslator(String domNameAndPath, EStructuralFeature aFeature, int style,
            Map<EClassifier, Translator> typeToTranslators, Map<String, Translator> nameToTranslators) {
        super(domNameAndPath, aFeature);
        fStyle |= style;
        _typeToTranslators = typeToTranslators;
        _nameToTranslators = nameToTranslators;
    }

    @Override
    public Translator getDelegateFor(EObject object) {
        if (object == null) {
            return null;
        }
        return _typeToTranslators.get(object.eClass());
    }

    @Override
    public Translator getDelegateFor(String domName, String readAheadName) {
        if (domName == null) {
            return null;
        }
        return _nameToTranslators.get(domName);
    }

    @Override
    public EObject createEMFObject(String nodeName, String readAheadName) {
        final Translator translator = getDelegateFor(nodeName, readAheadName);
        return translator == null ? null : translator.createEMFObject(nodeName, readAheadName);
    }

    @Override
    public Translator[] getChildren(Object o, int version) {
        final Translator delegate = getDelegateFor(o instanceof EObject ? (EObject) o : null);
        final Translator[] children = delegate == null ? null : delegate.getChildren(o, version);
        if (children == null) {
            return EMPTY_TRANSLATORS;
        }
        return children;
    }

    @Override
    public String getDOMName(Object value) {
        final Translator delegate = getDelegateFor(value instanceof EObject ? (EObject) value : null);
        return delegate == null ? null : delegate.getDOMName(value);
    }

    @Override
    public boolean shouldIndentEndTag(Node node) {
        if (node.getNodeName().equals(getDOMPath())) {
            return super.shouldIndentEndTag(node);
        }
        final Translator delegate = getDelegateFor(new QName(node.getNamespaceURI(), node.getLocalName()).toString(),
                null);
        if (delegate != null) {
            return delegate.shouldIndentEndTag(node);
        }
        return super.shouldIndentEndTag(node);
    }

    @Override
    public boolean isManagedByParent() {
        return _nameToTranslators.size() == 1 && _nameToTranslators.values().iterator().next().isManagedByParent();
    }

    @Override
    protected void initializeDOMNameAndPath(String domNameAndPathArg) {
        if (domNameAndPathArg == null) {
            return;
        }
        this.domNameAndPath = domNameAndPathArg;

        final Matcher matcher = ExtendedMetaDataTranslator.PATH_AND_NAME_PATTERN.matcher(domNameAndPathArg);
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

}
