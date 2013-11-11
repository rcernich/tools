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
package org.switchyard.tools.ui.editor.dom.generic;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSERenderer;

/**
 * EMF2DOMSSERendererNS
 * 
 * <p/>
 * Overrides basic renderer by creating EMF2DOMSSEAdapterNS instances. This
 * class basically integrates namespaces into the DOM path names.
 */
@SuppressWarnings("restriction")
public class EMF2DOMSSERendererNS extends EMF2DOMSSERenderer {

    /**
     * Create a new EMF2DOMSSERendererNS.
     */
    public EMF2DOMSSERendererNS() {
    }

    @Override
    protected EMF2DOMAdapter createRootDOMAdapter() {
        final IEditingDomainProvider domainProvider = (IEditingDomainProvider) EcoreUtil.getAdapter(
                getResourceSet() == null ? getResource().eAdapters() : getResourceSet().eAdapters(),
                IEditingDomainProvider.class);
        if (domainProvider != null && domainProvider.getEditingDomain() instanceof TransactionalEditingDomain) {
            return new EMF2DOMSSEAdapterNSTransactional(getResource(), document, this, getResource()
                    .getRootTranslator());
        }
        return new EMF2DOMSSEAdapterNS(getResource(), document, this, getResource().getRootTranslator());
    }
}
