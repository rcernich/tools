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

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * EMF2DOMSSEAdapterNSTransactional
 * <p/>
 * Adapter that works within a TransactionalEditingDomain.
 */
@SuppressWarnings("restriction")
public class EMF2DOMSSEAdapterNSTransactional extends EMF2DOMSSEAdapterNS {

    /**
     * Create a new EMF2DOMSSEAdapterNSTransactional.
     * 
     * @param resource the resource
     * @param document the document
     * @param renderer the renderer
     * @param translator the translator
     */
    public EMF2DOMSSEAdapterNSTransactional(TranslatorResource resource, Document document, EMF2DOMRenderer renderer,
            Translator translator) {
        super(resource, document, renderer, translator);
    }

    protected EMF2DOMSSEAdapterNSTransactional(Node node, EMF2DOMRenderer fRenderer, Translator childMap) {
        super(node, fRenderer, childMap);
    }

    protected EMF2DOMSSEAdapterNSTransactional(Notifier object, Node node, EMF2DOMRenderer renderer,
            Translator translator) {
        super(object, node, renderer, translator);
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(EObject mofObject, Translator childMap) {
        Element newNode = createNewNode(mofObject, childMap);
        return new EMF2DOMSSEAdapterNSTransactional(mofObject, newNode, fRenderer, childMap);
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(Node node, Translator childMap) {
        return new EMF2DOMSSEAdapterNSTransactional(node, fRenderer, childMap);
    }

    @Override
    public void updateMOF() {
        if (!isNotificationEnabled()) {
            return;
        }
        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getTarget());
        if (editingDomain instanceof TransactionalEditingDomain) {
            /*
             * we're going to blow chunks if we're in a transactional domain and
             * no transaction is open. creating the command here ensures that
             * all changes occur within a single command instead of commands for
             * each update.
             */
            editingDomain.getCommandStack().execute(
                    new RecordingCommand((TransactionalEditingDomain) editingDomain, "Text Update") {
                        @Override
                        protected void doExecute() {
                            EMF2DOMSSEAdapterNSTransactional.super.updateMOF();
                        }
                    });
        } else {
            super.updateMOF();
        }
    }

    @Override
    public void updateMOFFeature(final Translator map, final Node node, final EObject mofObject) {
        if (!isNotificationEnabled()) {
            return;
        }
        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getTarget());
        if (editingDomain instanceof TransactionalEditingDomain) {
            /*
             * we're going to blow chunks if we're in a transactional domain and
             * no transaction is open. creating the command here ensures that
             * all changes occur within a single command instead of commands for
             * each update.
             */
            editingDomain.getCommandStack().execute(
                    new RecordingCommand((TransactionalEditingDomain) editingDomain, "Text Update") {
                        @Override
                        protected void doExecute() {
                            EMF2DOMSSEAdapterNSTransactional.super.updateMOFFeature(map, node, mofObject);
                        }
                    });
        } else {
            super.updateMOFFeature(map, node, mofObject);
        }
    }
}
