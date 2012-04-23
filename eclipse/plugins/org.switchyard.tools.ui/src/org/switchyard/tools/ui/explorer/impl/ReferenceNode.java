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

import java.util.ArrayList;
import java.util.List;

import org.switchyard.config.model.composite.BindingModel;
import org.switchyard.config.model.composite.CompositeReferenceModel;
import org.switchyard.tools.ui.explorer.IReferenceNode;
import org.switchyard.tools.ui.explorer.IReferencesNode;
import org.switchyard.tools.ui.explorer.IServiceGateway;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ReferenceNode
 * 
 * <p/>
 * Implementation of IReferenceNode. Wraps CompositeReferenceModel.
 * 
 * @author Rob Cernich
 */
public class ReferenceNode implements IReferenceNode {

    private IReferencesNode _parent;
    private CompositeReferenceModel _reference;
    private List<IServiceGateway> _gateways;

    /**
     * Create a new ReferenceNode.
     * 
     * @param parent the parent.
     * @param reference the configuration.
     */
    public ReferenceNode(IReferencesNode parent, CompositeReferenceModel reference) {
        _parent = parent;
        _reference = reference;
        List<BindingModel> bindings = reference.getBindings();
        _gateways = new ArrayList<IServiceGateway>(bindings.size());
        for (BindingModel binding : bindings) {
            _gateways.add(new ServiceGateway(this, binding));
        }
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
    public List<IServiceGateway> getGateways() {
        return _gateways;
    }

    @Override
    public int hashCode() {
        return _reference.getQName() == null ? super.hashCode() : _reference.getQName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReferenceNode) {
            ReferenceNode other = (ReferenceNode) obj;
            return other._parent.equals(_parent) && other._reference.getQName() != null
                    && _reference.getQName() != null && other._reference.getQName().equals(_reference.getQName());
        }
        return false;
    }

}
