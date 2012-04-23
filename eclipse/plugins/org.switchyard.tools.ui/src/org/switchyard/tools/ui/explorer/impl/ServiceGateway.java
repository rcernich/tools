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

import org.switchyard.config.model.composite.BindingModel;
import org.switchyard.tools.ui.explorer.IServiceGateway;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ServiceGateway
 * 
 * <p/>
 * Implementation of IServiceGateway. Wraps BindingModel.
 * 
 * @author Rob Cernich
 */
public class ServiceGateway implements IServiceGateway {

    private ISwitchYardNode _parent;
    private BindingModel _binding;

    /**
     * Create a new ServiceGateway.
     * 
     * @param parent the parent.
     * @param binding the binding.
     */
    public ServiceGateway(ISwitchYardNode parent, BindingModel binding) {
        _parent = parent;
        _binding = binding;
    }

    @Override
    public ISwitchYardNode getParent() {
        return _parent;
    }

    @Override
    public String getType() {
        return _binding.getType();
    }

}
