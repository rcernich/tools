/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.tools.ui.editor.sapphire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sapphire.ui.def.ISapphirePartDef;
import org.switchyard.tools.ui.editor.Activator;

/**
 * ElementExtensionRegistry
 * 
 * Provides access to model extensions provided by various model extension
 * points.
 * 
 * @author Rob Cernich
 */
public class ElementExtensionRegistry {

    private static final String BASE_EXTENSION_POINT_ID = "modelExtension";
    private static final String MODEL_ELEMENT_EXTENSION = "modelElementExtension";
    private static final String CLASS = "class";

    private Map<Class<?>, SpecializedModelElementExtensionManager<?>> _extensionsByType = new HashMap<Class<?>, ElementExtensionRegistry.SpecializedModelElementExtensionManager<?>>();

    /**
     * @param <T> the type of the base type.
     * @param baseType the base type.
     * @return all the specialized types contributed by extensions.
     */
    public <T> List<Class<? extends T>> getSpecializedTypes(Class<T> baseType) {
        SpecializedModelElementExtensionManager<T> extensionManager = getExtensionManager(baseType);
        if (extensionManager == null) {
            return Collections.emptyList();
        }
        return extensionManager.getSpecializedTypes();
    }

    /**
     * @param <T> the type of the base type.
     * @param baseType the base type.
     * @param specializedType the specialized type.
     * @return the specialized editor part definition.
     */
    public <T> ISapphirePartDef getEditorPartDef(Class<T> baseType, Class<?> specializedType) {
        SpecializedModelElementExtensionManager<T> extensionManager = getExtensionManager(baseType);
        if (extensionManager == null) {
            return null;
        }
        return extensionManager.getEditorPartDef(specializedType);
    }

    /**
     * Clean up.
     */
    public synchronized void dispose() {
        for (SpecializedModelElementExtensionManager<?> extensionManager : _extensionsByType.values()) {
            extensionManager.dispose();
        }
        _extensionsByType.clear();
    }

    /**
     * @param <T> the base element type
     * @param baseType the base element type
     * @return the list of extensions
     */
    @SuppressWarnings("unchecked")
    private synchronized <T> SpecializedModelElementExtensionManager<T> getExtensionManager(Class<T> baseType) {
        if (baseType == null) {
            return null;
        }
        try {
            if (!_extensionsByType.containsKey(baseType)) {
                _extensionsByType.put(baseType, new SpecializedModelElementExtensionManager<T>(baseType));
            }
            return (SpecializedModelElementExtensionManager<T>) _extensionsByType.get(baseType);
        } catch (CoreException e) {
            Activator.getDefault().getLog().log(e.getStatus());
            return null;
        }
    }

    /**
     * SpecializedModelElementExtensionManager
     * 
     * Helper class to manage specialized model element extensions.
     * 
     * @author Rob Cernich
     */
    private static class SpecializedModelElementExtensionManager<T> {
        // we're using string instead of the classes to minimize leakage
        private Map<IExtension, Set<String>> _extensions = new LinkedHashMap<IExtension, Set<String>>();
        private Map<String, IModelElementExtension<T>> _modelElementExtensions = new LinkedHashMap<String, IModelElementExtension<T>>();
        private IRegistryEventListener _listener = new IRegistryEventListener() {
            @Override
            public void removed(IExtensionPoint[] extensionPoints) {
            }

            @Override
            public void removed(IExtension[] extensions) {
                for (IExtension extension : extensions) {
                    removeExtension(extension);
                }
            }

            @Override
            public void added(IExtensionPoint[] extensionPoints) {
            }

            @Override
            public void added(IExtension[] extensions) {
                for (IExtension extension : extensions) {
                    processExtension(extension);
                }
            }
        };

        /**
         * Create a new SpecializedModelElementExtensionManager.
         * 
         * @param baseType the type being specialized.
         * @throws CoreException if the corresponding extension point is not
         *             defined.
         */
        public SpecializedModelElementExtensionManager(Class<T> baseType) throws CoreException {
            String extensionPointId = BASE_EXTENSION_POINT_ID + baseType.getSimpleName();
            IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
                    extensionPointId);
            if (extensionPoint == null) {
                throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID,
                        "No extension point defined of type: " + extensionPointId));
            }
            for (IExtension extension : extensionPoint.getExtensions()) {
                processExtension(extension);
            }
            Platform.getExtensionRegistry().addListener(_listener, extensionPointId);
        }

        /**
         * @param specializedType the specialized type.
         * @return the specialized editor part definition.
         */
        public synchronized ISapphirePartDef getEditorPartDef(Class<?> specializedType) {
            IModelElementExtension<T> extension = _modelElementExtensions.get(specializedType.getName());
            return extension == null ? null : extension.getEditorPartDef();
        }

        /**
         * @return all the specialized types contributed by extensions.
         */
        public synchronized List<Class<? extends T>> getSpecializedTypes() {
            List<Class<? extends T>> retVal = new ArrayList<Class<? extends T>>(_modelElementExtensions.size());
            for (IModelElementExtension<T> modelElementExtension : _modelElementExtensions.values()) {
                retVal.add(modelElementExtension.getModelElement());
            }
            return retVal;
        }

        /**
         * Clean up.
         */
        public void dispose() {
            Platform.getExtensionRegistry().removeListener(_listener);
            _extensions.clear();
            _modelElementExtensions.clear();
        }

        private synchronized void processExtension(IExtension extension) {
            for (IConfigurationElement configElement : extension.getConfigurationElements()) {
                if (!MODEL_ELEMENT_EXTENSION.equals(configElement.getName())) {
                    Activator
                            .getDefault()
                            .getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Invalid modelExtension element: "
                                    + configElement));
                    continue;
                }
                try {
                    @SuppressWarnings("unchecked")
                    IModelElementExtension<T> modelElementExtension = (IModelElementExtension<T>) configElement
                            .createExecutableExtension(CLASS);
                    addExtension(extension, modelElementExtension);
                } catch (CoreException e) {
                    Activator.getDefault().getLog().log(e.getStatus());
                }
            }
        }

        private synchronized void addExtension(IExtension extension, IModelElementExtension<T> modelElementExtension) {
            Class<? extends T> specializedType = modelElementExtension.getModelElement();
            Set<String> specializedTypes = _extensions.get(extension);
            if (specializedTypes == null) {
                specializedTypes = new LinkedHashSet<String>();
                _extensions.put(extension, specializedTypes);
            }
            String specializedTypeName = specializedType.getName();
            if (specializedTypes.add(specializedTypeName)) {
                _modelElementExtensions.put(specializedTypeName, modelElementExtension);
            } else {
                Activator
                        .getDefault()
                        .getLog()
                        .log(new Status(Status.ERROR, Activator.PLUGIN_ID,
                                "IModelElementExtension already registered for type: " + specializedTypeName));
            }
        }

        private synchronized void removeExtension(IExtension extension) {
            Set<String> specializedTypes = _extensions.remove(extension);
            if (specializedTypes == null) {
                return;
            }
            for (String specializedType : specializedTypes) {
                _modelElementExtensions.remove(specializedType);
            }
        }
    }
}
