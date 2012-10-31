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
package org.switchyard.tools.ui.editor.dom;

import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.extension.ModelQueryExtension;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * SwitchYardModelQueryExtension
 * 
 * <p/>
 * Adds content for specialized types (e.g. implementation.bean) and filters out
 * unsupported types (e.g. implementation.cpp).
 */
@SuppressWarnings("restriction")
public class SwitchYardModelQueryExtension extends ModelQueryExtension {

    /**
     * Create a new SwitchYardModelQueryExtension.
     */
    public SwitchYardModelQueryExtension() {
    }

    @Override
    public boolean isApplicableChildElement(Node parentNode, String namespace, String name) {
        // TODO: filter out unsupported types (e.g. implementation.cpp)
        return super.isApplicableChildElement(parentNode, namespace, name);
    }

    @Override
    public CMNode[] getAvailableElementContent(Element parentElement, String namespace, int includeOptions) {
        // TODO: add extended types (e.g. implementation.bean, binding.soap)
        return super.getAvailableElementContent(parentElement, namespace, includeOptions);
    }

}
