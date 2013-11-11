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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.ICompositeOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.switchyard.tools.ui.editor.dom.generic.EMF2DOMSSERendererNS;

/**
 * SwitchYardEMF2DOMSSERenderer
 * 
 * <p/>
 * Merges the command stack declared on the editing domain contained on the
 * resource into the SSE model.
 */
@SuppressWarnings("restriction")
public class SwitchYardEMF2DOMSSERenderer extends EMF2DOMSSERendererNS {

    private CompositeOperation _compositeOperation;

    /**
     * Create a new SwitchYardEMF2DOMSSERenderer.
     */
    public SwitchYardEMF2DOMSSERenderer() {
    }

    /*
     * Hackery to ensure command stack is same as the one used by the editing
     * domain.
     */
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

    /*
     * Hackery to ensure all changes are lumped together as a single operation.
     */
    @Override
    public void modelAboutToBeChanged(IStructuredModel model) {
        final CommandStack stack = getXMLModel().getUndoManager().getCommandStack();
        if (_compositeOperation == null && stack instanceof IWorkspaceCommandStack) {
            final IOperationHistory history = ((IWorkspaceCommandStack) stack).getOperationHistory();
            try {
                _compositeOperation = new CompositeOperation();
                history.openOperation(_compositeOperation, IOperationHistory.EXECUTE);
            } catch (Exception e) {
                _compositeOperation = null;
            }
        }
        super.modelAboutToBeChanged(model);
    }

    /*
     * Hackery to ensure all changes are lumped together as a single operation.
     */
    @Override
    public void modelChanged(IStructuredModel model) {
        super.modelChanged(model);
        if (_compositeOperation != null) {
            final CommandStack stack = getXMLModel().getUndoManager().getCommandStack();
            final IOperationHistory history = ((IWorkspaceCommandStack) stack).getOperationHistory();
            try {
                history.closeOperation(true, false, IOperationHistory.EXECUTE);
            } finally {
                _compositeOperation = null;
            }
        }
    }

    private static final class CompositeOperation extends AbstractOperation implements ICompositeOperation {

        private CompositeOperation() {
            super("");
        }

        @Override
        public void add(IUndoableOperation operation) {
        }

        @Override
        public void remove(IUndoableOperation operation) {
        }

        @Override
        public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            return Status.OK_STATUS;
        }

        @Override
        public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            return Status.OK_STATUS;
        }

        @Override
        public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            return Status.OK_STATUS;
        }

    }
}
