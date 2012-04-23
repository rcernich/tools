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

import org.switchyard.config.model.composite.ComponentReferenceModel;
import org.switchyard.config.model.composite.InterfaceModel;
import org.switchyard.tools.ui.explorer.IComponentNode;
import org.switchyard.tools.ui.explorer.IComponentReference;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ComponentReference
 * 
 * <p/>
 * Implementation of IComponentReference. Wraps ComponentReferenceModel.
 * 
 * @author Rob Cernich
 */
public class ComponentReference implements IComponentReference {

    private IComponentNode _parent;
    private ComponentReferenceModel _reference;

    /**
     * Create a new ComponentReference.
     * 
     * @param parent the parent.
     * @param reference the ComponentReferenceModel.
     */
    public ComponentReference(IComponentNode parent, ComponentReferenceModel reference) {
        _parent = parent;
        _reference = reference;
    }

    @Override
    public ISwitchYardNode getParent() {
        return _parent;
    }

    @Override
    public String getName() {
        return _reference.getName();
    }

    @Override
    public InterfaceModel getInterface() {
        return _reference.getInterface();
    }

    @Override
    public int hashCode() {
        return _reference.getQName() == null ? super.hashCode() : _reference.getQName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComponentReference) {
            ComponentReference other = (ComponentReference) obj;
            return other._parent.equals(_parent) && other._reference.getName() != null && _reference.getName() != null
                    && other._reference.getName().equals(_reference.getName());
        }
        return false;
    }

}
