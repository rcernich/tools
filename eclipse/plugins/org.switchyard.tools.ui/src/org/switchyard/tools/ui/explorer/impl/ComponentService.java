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
package org.switchyard.tools.ui.explorer.impl;

import org.switchyard.config.model.composite.ComponentServiceModel;
import org.switchyard.config.model.composite.InterfaceModel;
import org.switchyard.tools.ui.explorer.IComponentNode;
import org.switchyard.tools.ui.explorer.IComponentService;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ComponentService
 * 
 * <p/>
 * Implementation of IComponentService. Wraps ComponentServiceModel.
 * 
 * @author Rob Cernich
 */
public class ComponentService implements IComponentService {

    private IComponentNode _parent;
    private ComponentServiceModel _service;

    /**
     * Create a new ComponentService.
     * 
     * @param parent the parent.
     * @param service the ComponentServiceModel.
     */
    public ComponentService(IComponentNode parent, ComponentServiceModel service) {
        _parent = parent;
        _service = service;
    }

    @Override
    public ISwitchYardNode getParent() {
        return _parent;
    }

    @Override
    public String getName() {
        return _service.getName();
    }

    @Override
    public InterfaceModel getInterface() {
        return _service.getInterface();
    }

    @Override
    public int hashCode() {
        return _service.getQName() == null ? super.hashCode() : _service.getQName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComponentService) {
            ComponentService other = (ComponentService) obj;
            return other._parent.equals(_parent) && other._service.getName() != null && _service.getName() != null
                    && other._service.getName().equals(_service.getName());
        }
        return false;
    }

}
