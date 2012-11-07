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

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.switchyard.tools.ui.editor.dom.generic.EMF2DOMSSEAdapterNS;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * SwitchYardEMF2DOMSSEAdapter
 * 
 * <p/>
 * If the resource is contained within a TransactionalEditingDomain, we need to
 * ensure that any EMF changes initiated in by text changes are made in a
 * transaction.
 */
@SuppressWarnings("restriction")
public class SwitchYardEMF2DOMSSEAdapter extends EMF2DOMSSEAdapterNS {

    /**
     * Create a new SwitchYardEMF2DOMSSEAdapter.
     * 
     * @param resource the resource
     * @param document the document
     * @param renderer the renderer
     * @param translator the translator
     */
    public SwitchYardEMF2DOMSSEAdapter(TranslatorResource resource, Document document, EMF2DOMRenderer renderer,
            Translator translator) {
        super(resource, document, renderer, translator);
    }

    protected SwitchYardEMF2DOMSSEAdapter(Node node, EMF2DOMRenderer fRenderer, Translator childMap) {
        super(node, fRenderer, childMap);
    }

    protected SwitchYardEMF2DOMSSEAdapter(Notifier object, Node node, EMF2DOMRenderer renderer, Translator translator) {
        super(object, node, renderer, translator);
    }

    @Override
    public void notifyChanged(final INodeNotifier notifier, final int eventType, final Object changedFeature,
            final Object oldValue, final Object newValue, final int pos) {
        if (!isNotificationEnabled()) {
            return;
        }

        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getTarget());
        if (editingDomain instanceof TransactionalEditingDomain) {
            final SwitchyardSCAEditor editor = SwitchyardSCAEditor.getEditor(editingDomain.getResourceSet());
            editingDomain.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) editingDomain) {
                @Override
                protected void doExecute() {
                    SwitchYardEMF2DOMSSEAdapter.super.notifyChanged(notifier, eventType, changedFeature, oldValue,
                            newValue, pos);
                    if (editor != null) {
                        final IUpdateContext context = new UpdateContext(editor.getDiagramTypeProvider().getDiagram());
                        editor.getDiagramTypeProvider().getFeatureProvider().updateIfPossibleAndNeeded(context);
                        editor.refresh();
                    }
                }
            });
        } else {
            super.notifyChanged(notifier, eventType, changedFeature, oldValue, newValue, pos);
        }
    }

}
