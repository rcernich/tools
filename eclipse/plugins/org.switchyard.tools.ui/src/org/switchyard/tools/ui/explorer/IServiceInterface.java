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
 * IServiceInterface
 * 
 * <p/>
 * Represents an interface provided by a service or reference.
 * 
 * @author Rob Cernich
 */
public interface IServiceInterface {

    /**
     * @return the type of interface (e.g. Java, WSDL, etc.).
     */
    public String getType();

    /**
     * @return the interface (e.g. class name, port type, etc.).
     */
    public String getInterface();

}
