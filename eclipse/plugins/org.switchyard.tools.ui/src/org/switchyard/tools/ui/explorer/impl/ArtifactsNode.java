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
import java.util.Collections;
import java.util.List;

import org.switchyard.config.model.switchyard.ArtifactModel;
import org.switchyard.config.model.switchyard.ArtifactsModel;
import org.switchyard.config.model.switchyard.SwitchYardModel;
import org.switchyard.tools.ui.explorer.IArtifactNode;
import org.switchyard.tools.ui.explorer.IArtifactsNode;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ArtifactsNode
 * 
 * <p/>
 * Implementation of IArtifactsNode.
 * 
 * @author Rob Cernich
 */
public class ArtifactsNode implements IArtifactsNode {

    private SwitchYardRootNode _root;
    private List<IArtifactNode> _artifacts;

    /**
     * Create a new ArtifactsNode.
     * 
     * @param root the parent.
     * @param configuration the configuration.
     */
    public ArtifactsNode(SwitchYardRootNode root, SwitchYardModel configuration) {
        _root = root;
        if (configuration == null) {
            _artifacts = Collections.emptyList();
            return;
        }
        ArtifactsModel artifactsModel = configuration.getArtifacts();
        if (artifactsModel == null) {
            _artifacts = Collections.emptyList();
            return;
        }
        List<ArtifactModel> artifacts = artifactsModel.getArtifacts();
        _artifacts = new ArrayList<IArtifactNode>(artifacts.size());
        for (ArtifactModel artifact : artifacts) {
            _artifacts.add(new ArtifactNode(this, artifact));
        }
    }

    @Override
    public ISwitchYardNode getParent() {
        return _root;
    }

    @Override
    public List<IArtifactNode> getArtifacts() {
        return _artifacts;
    }

    @Override
    public int hashCode() {
        return _root.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArtifactsNode) {
            return ((ArtifactsNode) obj)._root == _root;
        }
        return false;
    }

}
