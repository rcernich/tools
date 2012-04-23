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
package org.switchyard.tools.ui.explorer;

import org.switchyard.config.model.composite.InterfaceModel;

/**
 * IComponentReference
 * 
 * <p/>
 * Represents a service referenced by a component.
 * 
 * @author Rob Cernich
 */
public interface IComponentReference extends ISwitchYardNode {

    /**
     * @return the name.
     */
    public String getName();

    /**
     * @return the interface.
     */
    public InterfaceModel getInterface();

}