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
package org.switchyard.tools.ui.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;

/**
 * ComponentTypeExtensionManager
 * 
 * <p/>
 * Manages the set of contributed component type extensions, i.e. loads the
 * extensions and provides access to them.
 */
public final class ComponentTypeExtensionManager {

    private static final ComponentTypeExtensionManager INSTANCE = new ComponentTypeExtensionManager();

    /**
     * @return the sole instance of the component type extension manager.
     */
    public static ComponentTypeExtensionManager instance() {
        return INSTANCE;
    }

    private Map<Class<? extends Implementation>, IComponentTypeExtension> _cache = new HashMap<Class<? extends Implementation>, IComponentTypeExtension>();
    private List<IComponentTypeExtension> _extensions = new ArrayList<IComponentTypeExtension>();
    private IComponentTypeExtension _defaultExtension = new DefaultComponentTypeExtension();

    /**
     * Create a new ComponentTypeExtensionManager.
     */
    private ComponentTypeExtensionManager() {
        loadExtensions();
    }

    /**
     * @param type the implementation type
     * @return the extension supporting this type.
     */
    public IComponentTypeExtension getExtensionFor(Class<? extends Implementation> type) {
        if (_cache.containsKey(type)) {
            return _cache.get(type);
        }
        for (IComponentTypeExtension extension : _extensions) {
            if (extension.supports(type)) {
                _cache.put(type, extension);
                return extension;
            }
        }
        _cache.put(type, _defaultExtension);
        return _defaultExtension;
    }

    /**
     * @return all the registered extensions.
     */
    public Collection<IComponentTypeExtension> getExtensions() {
        return Collections.unmodifiableCollection(_extensions);
    }

    private void loadExtensions() {
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
                "editorExtension");
        for (IExtension pluginExtension : extensionPoint.getExtensions()) {
            for (IConfigurationElement element : pluginExtension.getConfigurationElements()) {
                if (!"componentExtension".equals(element.getName())) {
                    continue;
                }
                try {
                    _extensions.add((IComponentTypeExtension) element.createExecutableExtension("class"));
                } catch (CoreException e) {
                    Activator.getDefault().getLog().log(e.getStatus());
                }
            }
        }
    }

    private static final class DefaultComponentTypeExtension implements IComponentTypeExtension {

        @Override
        public ICreateFeature[] newCreateFeatures(IFeatureProvider fp) {
            return new ICreateFeature[0];
        }

        @Override
        public ImageDecorator getImageDecorator(Implementation implementation) {
            return new ImageDecorator(ImageProvider.IMG_16_UNKNOWN_IMPL);
        }

        @Override
        public boolean supports(Class<? extends Implementation> type) {
            return false;
        }

    }
}
