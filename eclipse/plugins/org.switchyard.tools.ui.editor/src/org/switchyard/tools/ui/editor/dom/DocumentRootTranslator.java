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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.switchyard.tools.ui.editor.dom.ExtendedMetaDataTranslator.IExtensionsManager;
import org.w3c.dom.Node;

/**
 * DocumentRootTranslator
 * 
 * <p/>
 * Translator for root content based on an EMF "DocumentRoot" object.
 */
@SuppressWarnings("restriction")
public final class DocumentRootTranslator extends RootTranslator {

    /**
     * @param basePackage the base package from which to load the document root.
     * @param extensions the extensions manager.
     * @return the translator for the document root.
     */
    public static Translator create(EPackage basePackage, IExtensionsManager extensions) {
        final EClass root = ExtendedMetaData.INSTANCE.getDocumentRoot(basePackage);
        final Map<EClassifier, Translator> typeToTranslators = new HashMap<EClassifier, Translator>();
        final Map<String, Translator> nameToTranslators = new HashMap<String, Translator>();
        final StringBuffer names = new StringBuffer();
        for (EStructuralFeature element : ExtendedMetaData.INSTANCE.getElements(root)) {
            if (ExtendedMetaData.INSTANCE.getFeatureKind(element) == ExtendedMetaData.GROUP_FEATURE) {
                continue;
            }
            final String domName = ExtendedMetaDataTranslator.getDomName(element).toString();
            final Translator translator = new ExtendedMetaDataTranslator(domName, element, NO_STYLE, extensions);
            typeToTranslators.put(element.getEType(), translator);
            nameToTranslators.put(domName, translator);
            names.append(domName).append(",");
        }
        if (names.length() > 0) {
            names.deleteCharAt(names.length() - 1);
        }
        return new DocumentRootTranslator(names.toString(), typeToTranslators, nameToTranslators);
    }

    private static final Translator[] EMPTY_TRANSLATORS = new Translator[] {};

    private final Map<EClassifier, Translator> _typeToTranslators;
    private final Map<String, Translator> _nameToTranslators;

    private DocumentRootTranslator(String domNameAndPath, Map<EClassifier, Translator> typeToTranslators,
            Map<String, Translator> nameToTranslators) {
        super(domNameAndPath, (EClass) null);
        _typeToTranslators = typeToTranslators;
        _nameToTranslators = nameToTranslators;
    }

    private Translator getDelegateFor(EObject object) {
        if (object == null) {
            return null;
        }
        return _typeToTranslators.get(object.eClass());
    }

    private Translator getDelegateFor(String domName, String readAheadName) {
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
        return getDelegateFor((EObject) value).getDOMName(value);
    }

    @Override
    public boolean isManagedByParent() {
        return false;
    }

    @Override
    public boolean shouldIndentEndTag(Node node) {
        if (node.getNodeName().equals(getDOMPath())) {
            return super.shouldIndentEndTag(node);
        }
        final Translator delegate = getDelegateFor(node.getNodeName(), null);
        if (delegate != null) {
            return delegate.shouldIndentEndTag(node);
        }
        return super.shouldIndentEndTag(node);
    }

}
