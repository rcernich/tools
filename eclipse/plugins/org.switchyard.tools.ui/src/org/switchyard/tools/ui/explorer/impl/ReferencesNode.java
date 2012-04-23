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

import org.switchyard.config.model.composite.CompositeModel;
import org.switchyard.config.model.composite.CompositeReferenceModel;
import org.switchyard.config.model.switchyard.SwitchYardModel;
import org.switchyard.tools.ui.explorer.IReferenceNode;
import org.switchyard.tools.ui.explorer.IReferencesNode;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;

/**
 * ReferencesNode
 * 
 * <p/>
 * Implementation of IReferencesNode.
 * 
 * @author Rob Cernich
 */
public class ReferencesNode implements IReferencesNode {

    private SwitchYardRootNode _root;
    private List<IReferenceNode> _references;

    /**
     * Create a new ReferencesNode.
     * 
     * @param root the parent.
     * @param configuration the configuration.
     */
    public ReferencesNode(SwitchYardRootNode root, SwitchYardModel configuration) {
        _root = root;
        if (configuration == null) {
            _references = Collections.emptyList();
            return;
        }
        CompositeModel composite = configuration.getComposite();
        if (composite == null) {
            _references = Collections.emptyList();
            return;
        }
        List<CompositeReferenceModel> references = composite.getReferences();
        _references = new ArrayList<IReferenceNode>(references.size());
        for (CompositeReferenceModel reference : references) {
            _references.add(new ReferenceNode(this, reference));
        }
    }

    @Override
    public ISwitchYardNode getParent() {
        return _root;
    }

    @Override
    public List<IReferenceNode> getReferences() {
        return _references;
    }

    @Override
    public int hashCode() {
        return _root.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReferencesNode) {
            return ((ReferencesNode) obj)._root == _root;
        }
        return false;
    }

}
