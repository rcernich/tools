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
package org.switchyard.tools.models.switchyard1_0.switchyard.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * SwitchYardResource
 * 
 * <p/>
 * Extended interface for SwitchYard resources.
 * 
 * @generated NOT
 */
public interface SwitchYardResource extends XMLResource {

    /**
     * @param generated the generated SwitchYard resource.
     */
    public void setGeneratedResource(Resource generated);

    /**
     * @return the generated SwitchYard resource associated with this source
     *         resource.
     */
    public Resource getGeneratedResource();

}
