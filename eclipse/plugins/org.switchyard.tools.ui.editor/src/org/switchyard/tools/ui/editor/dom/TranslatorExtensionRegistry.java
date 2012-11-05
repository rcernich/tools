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

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.switchyard.tools.ui.editor.Activator;
import org.switchyard.tools.ui.editor.dom.ExtendedMetaDataTranslator.IExtensionsManager;

/**
 * TranslatorExtensionRegistry
 * 
 * <p/>
 * Manages registered extensions for specific type/feature pairs.
 */
@SuppressWarnings("restriction")
public final class TranslatorExtensionRegistry {

    /**
     * Interface to be implemented by extension providers.
     */
    public interface ITranslatorFactory {
        /**
         * Create a new Translator instance for the specified feature.
         * 
         * @param feature the feature on which the translator should act.
         * @param extensions the extensions for any specialized child types.
         * 
         * @return a new Translator
         */
        public Translator create(EStructuralFeature feature, IExtensionsManager extensions);
    }

    /**
     * @return the translator extensions registry.
     */
    public static TranslatorExtensionRegistry instance() {
        return INSTANCE;
    }

    private static final TranslatorExtensionRegistry INSTANCE = new TranslatorExtensionRegistry();

    private static final String EXTENSION_ID = "translatorExtension";
    private static final String FACTORY_ATTRIBUTE = "factory";
    private static final String TRANSLATOR_ELEMENT = "translator";

    private Map<QName, TranslatorExtension> _extensions = new HashMap<QName, TranslatorExtension>();

    /**
     * Create a new TranslatorExtensionRegistry.
     */
    private TranslatorExtensionRegistry() {
        final IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
                EXTENSION_ID);
        for (final IExtension extension : extensionPoint.getExtensions()) {
            for (final IConfigurationElement element : extension.getConfigurationElements()) {
                final String factory = element.getAttribute(FACTORY_ATTRIBUTE);
                if (factory == null || factory.length() == 0) {
                    Activator.logStatus(new Status(Status.ERROR, Activator.PLUGIN_ID, "Invalid " + EXTENSION_ID + ": "
                            + element));
                    continue;
                }
                for (final IConfigurationElement translator : element.getChildren(TRANSLATOR_ELEMENT)) {
                    final TranslatorExtension translatorExtension = new TranslatorExtension(translator);
                    final QName key = translatorExtension.getKey();
                    if (_extensions.containsKey(key)) {
                        final TranslatorExtension existing = _extensions.get(key);
                        Activator.logStatus(new Status(Status.ERROR, Activator.PLUGIN_ID,
                                "Duplicate translator extension: " + key + ", decalred in: "
                                        + translator.getContributor().getName() + ", already declared by: "
                                        + existing.getConfig().getContributor().getName()));
                        continue;
                    }
                    _extensions.put(key, translatorExtension);
                }
            }
        }
    }

    /**
     * @param feature the feature needing specialization.
     * @param extensions the extensions manager for resolving specialized child
     *            types.
     * @return the specialized translator; null if no specialized translator has
     *         been registered for the feature.
     */
    public Translator getTranslatorForType(EStructuralFeature feature, IExtensionsManager extensions) {
        final QName key = createQName(feature);
        if (_extensions.containsKey(key)) {
            final TranslatorExtension extension = _extensions.get(key);
            try {
                return extension.createTranslator(feature, extensions);
            } catch (CoreException e) {
                Activator.logStatus(e.getStatus());
                _extensions.remove(key);
            }
        }
        return null;
    }

    private QName createQName(EStructuralFeature feature) {
        final EClass type = feature.getEContainingClass();
        return createQName(type.getEPackage().getNsURI(), type.getName(), feature.getName());
    }

    private static QName createQName(String namespace, String type, String feature) {
        return new QName(namespace, type + "::" + feature);
    }

    private static final class TranslatorExtension {

        private static final String NAMESPACE = "namespace";
        private static final String TYPE = "type";
        private static final String FEATURE = "feature";

        private final IConfigurationElement _config;
        private final QName _translatorType;

        private TranslatorExtension(IConfigurationElement config) {
            _config = config;
            _translatorType = createQNameFromConfig(config);
        }

        public QName getKey() {
            return _translatorType;
        }

        public IConfigurationElement getConfig() {
            return _config;
        }

        public Translator createTranslator(EStructuralFeature feature, IExtensionsManager extensions)
                throws CoreException {
            final ITranslatorFactory factory = (ITranslatorFactory) ((IConfigurationElement) _config.getParent())
                    .createExecutableExtension(FACTORY_ATTRIBUTE);
            return factory.create(feature, extensions);
        }

        private static QName createQNameFromConfig(IConfigurationElement config) {
            return createQName(config.getAttribute(NAMESPACE), config.getAttribute(TYPE), config.getAttribute(FEATURE));
        }

    }
}
