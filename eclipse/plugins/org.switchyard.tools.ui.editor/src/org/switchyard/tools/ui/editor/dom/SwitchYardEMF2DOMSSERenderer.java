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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSERenderer;

/**
 * SwitchYardEMF2DOMSSERenderer
 * 
 * <p/>
 * Overrides basic renderer by creating SwitchYardEMF2DOMSSEAdapter instances.
 */
@SuppressWarnings("restriction")
public class SwitchYardEMF2DOMSSERenderer extends EMF2DOMSSERenderer {

    /**
     * Create a new SwitchYardEMF2DOMSSERenderer.
     */
    public SwitchYardEMF2DOMSSERenderer() {
    }

    @Override
    protected EMF2DOMAdapter createRootDOMAdapter() {
        return new SwitchYardEMF2DOMSSEAdapter(getResource(), document, this, getResource().getRootTranslator());
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void loadDocument(InputStream in, Map options) throws IOException {
        super.loadDocument(in, options);
        final EditingDomain domain;
        final IEditingDomainProvider domainProvider = (IEditingDomainProvider) EcoreUtil.getAdapter(
                getResourceSet() == null ? getResource().eAdapters() : getResourceSet().eAdapters(),
                IEditingDomainProvider.class);
        if (domainProvider == null) {
            domain = null;
        } else {
            domain = domainProvider.getEditingDomain();
        }
        if (domain != null) {
            // share the command stack
            getXMLModel().getUndoManager().setCommandStack(domain.getCommandStack());
        }
    }

}
