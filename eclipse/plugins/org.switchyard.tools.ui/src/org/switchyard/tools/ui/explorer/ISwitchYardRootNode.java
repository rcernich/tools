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
 * ISwitchYardRootNode
 * 
 * <p/>
 * Root node for SwitchYard content in project explorer.
 * 
 * @author Rob Cernich
 */
public interface ISwitchYardRootNode extends ISwitchYardNode {

    /**
     * @return the node containing application services.
     */
    public IServicesNode getServicesNode();

    /**
     * @return the node containing application references.
     */
    public IReferencesNode getReferencesNode();

    /**
     * @return the node containing application components.
     */
    public IComponentsNode getComponentsNode();

    /**
     * @return the node containing artifacts referenced by the application.
     */
    public IArtifactsNode getArtifactsNode();

}
