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

/**
 * IServiceGateway
 * 
 * <p/>
 * Base type representing a service gateway in the project explorer.
 * 
 * @author Rob Cernich
 */
public interface IServiceGateway extends ISwitchYardNode {

    /**
     * @return the gateway type.
     */
    public String getType();
}
