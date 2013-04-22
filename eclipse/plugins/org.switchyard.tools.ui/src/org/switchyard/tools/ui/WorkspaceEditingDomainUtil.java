/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.graphiti.ui.internal.editor.GFWorkspaceCommandStackImpl;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardType;

/**
 * WorkspaceEditingDomainUtil
 * 
 * <p/>
 * Utility class for managing shared editing domains within the workspace (e.g.
 * when a switchyard.xml file is opened in an editor and the navigator).
 */
@SuppressWarnings("restriction")
public final class WorkspaceEditingDomainUtil {

    /**
     * @return the sole instance of the editing domain util.
     */
    public static WorkspaceEditingDomainUtil instance() {
        return INSTANCE;
    }

    private static final WorkspaceEditingDomainUtil INSTANCE = new WorkspaceEditingDomainUtil();

    private static final Lock LOCK = new ReentrantLock();
    private static final String ID_PREFIX = "switchyard::";

    /**
     * Returns the shared editing domain to be used for a specific
     * switchyard.xml file. This allows the model to be used by views outside
     * the editor.
     * 
     * @param switchYardFile the file representing the switchyard.xml file.
     * @return the editing domain.
     */
    public TransactionalEditingDomain getOrCreateEditingDomain(IFile switchYardFile) {
        final String domainID = ID_PREFIX + switchYardFile.getFullPath().toString();
        LOCK.lock();
        try {
            TransactionalEditingDomain domain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(domainID);
            if (domain == null) {
                domain = createResourceSetAndEditingDomain(domainID);
            } else {
                ((ReferenceCountTransactionalEditingDomain) domain).incrementReferenceCount();
            }
            return domain;
        } finally {
            LOCK.unlock();
        }
    }

    private ReferenceCountTransactionalEditingDomain createResourceSetAndEditingDomain(String domainID) {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final IWorkspaceCommandStack workspaceCommandStack = new GFWorkspaceCommandStackImpl(
                new DefaultOperationHistory());

        final ReferenceCountTransactionalEditingDomain editingDomain = new ReferenceCountTransactionalEditingDomain(
                domainID, new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
                workspaceCommandStack, resourceSet);
        WorkspaceEditingDomainFactory.INSTANCE.mapResourceSet(editingDomain);
        return editingDomain;
    }

    private WorkspaceEditingDomainUtil() {
    }

    private final static class ReferenceCountTransactionalEditingDomain extends TransactionalEditingDomainImpl {

        private final String _domainID;
        private int _referenceCount = 1;
        private final EditingDomainSaveable _saveable;

        public ReferenceCountTransactionalEditingDomain(String domainID, AdapterFactory adapterFactory,
                TransactionalCommandStack stack, ResourceSet resourceSet) {
            super(adapterFactory, stack, resourceSet);
            _domainID = domainID;
            _saveable = new EditingDomainSaveable(this);
            TransactionalEditingDomain.Registry.INSTANCE.add(domainID, this);
        }

        private void incrementReferenceCount() {
            LOCK.lock();
            ++_referenceCount;
            LOCK.unlock();
        }

        @Override
        public <T> T getAdapter(Class<? extends T> adapterType) {
            if (adapterType.isAssignableFrom(Saveable.class)
                    || adapterType.isAssignableFrom(EditingDomainSaveable.class)) {
                return adapterType.cast(_saveable);
            }
            return super.getAdapter(adapterType);
        }

        @Override
        public void dispose() {
            LOCK.lock();
            if (--_referenceCount == 0) {
                try {
                    TransactionalEditingDomain.Registry.INSTANCE.remove(_domainID);
                } catch (Throwable t) {
                    t.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
                super.dispose();
            } else {
                LOCK.unlock();
            }
        }

        private static class EditingDomainSaveable extends Saveable {

            private final ReferenceCountTransactionalEditingDomain _domain;

            public EditingDomainSaveable(ReferenceCountTransactionalEditingDomain domain) {
                super();
                _domain = domain;
            }

            @Override
            public String getName() {
                return getPath().lastSegment();
            }

            @Override
            public String getToolTipText() {
                return _domain.getID().substring(ID_PREFIX.length());
            }

            @Override
            public ImageDescriptor getImageDescriptor() {
                return Activator.getDefault().getImageRegistry().getDescriptor(IImageDescriptors.SWITCH_YARD_SMALL);
            }

            @Override
            public void doSave(IProgressMonitor monitor) throws CoreException {
                // FIXME doSave

            }

            @Override
            public boolean isDirty() {
                return ((BasicCommandStack) _domain.getCommandStack()).isSaveNeeded();
            }

            @Override
            public boolean show(IWorkbenchPage page) {
                if (page == null || !(page.getActivePart() instanceof ISetSelectionTarget)) {
                    return super.show(page);
                }
                Resource resource = _domain.getResourceSet().getResource(
                        URI.createPlatformResourceURI(getPath().toString(), true), false);
                if (resource == null) {
                    return super.show(page);
                }
                SwitchYardType switchYard = SwitchYardModelUtils.getSwitchYard(resource);
                if (switchYard == null) {
                    return super.show(page);
                }
                ((ISetSelectionTarget) page.getActivePart()).selectReveal(new StructuredSelection(switchYard));
                return true;
            }

            @Override
            public boolean equals(Object object) {
                return object == this;
            }

            @Override
            public int hashCode() {
                return _domain.hashCode();
            }

            @Override
            @SuppressWarnings({"rawtypes", "unchecked" })
            public Object getAdapter(Class adapter) {
                if (adapter.isAssignableFrom(IResource.class) || adapter.isAssignableFrom(IFile.class)) {
                    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(getPath());
                    return file == null || !file.exists() || !file.isAccessible() ? null : file;
                }
                return super.getAdapter(adapter);
            }

            private IPath getPath() {
                return new Path(_domain.getID().substring(ID_PREFIX.length()));
            }
        }
    }
}
