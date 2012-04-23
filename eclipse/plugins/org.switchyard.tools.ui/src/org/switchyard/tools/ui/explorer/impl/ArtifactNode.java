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

import org.switchyard.config.model.switchyard.ArtifactModel;
import org.switchyard.tools.ui.explorer.IArtifactNode;
import org.switchyard.tools.ui.explorer.IArtifactsNode;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ArtifactNode
 * 
 * <p/>
 * Implementation of IArtifactNode. Wraps ArtifactModel.
 * 
 * @author Rob Cernich
 */
public class ArtifactNode implements IArtifactNode {

    private IArtifactsNode _parent;
    private ArtifactModel _artifact;

    /**
     * Create a new ArtifactNode.
     * 
     * @param parent the parent.
     * @param artifact the configuration.
     */
    public ArtifactNode(IArtifactsNode parent, ArtifactModel artifact) {
        _parent = parent;
        _artifact = artifact;
    }

    @Override
    public ISwitchYardNode getParent() {
        return _parent;
    }

    @Override
    public String getName() {
        return _artifact.getName();
    }

    @Override
    public String getURL() {
        return _artifact.getURL();
    }

    @Override
    public int hashCode() {
        return _artifact.getName() == null ? super.hashCode() : _artifact.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArtifactNode) {
            ArtifactNode other = (ArtifactNode) obj;
            return other._parent.equals(_parent) && other._artifact.getName() != null && _artifact.getName() != null
                    && other._artifact.getName().equals(_artifact.getName());
        }
        return false;
    }

}
