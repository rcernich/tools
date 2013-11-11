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
package org.switchyard.tools.ui.editor.dom.generic;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jem.util.emf.workbench.ISynchronizerExtender;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.ResourceHandler;
import org.eclipse.jem.util.emf.workbench.ResourceSetWorkbenchSynchronizer;
import org.eclipse.wst.common.internal.emfworkbench.integration.ResourceSetWorkbenchEditSynchronizer;

/**
 * ProjectResourceSetImpl
 * <p/>
 * Simple implementation of a ProjectResourceSet that behaves like a regular
 * ResourceSet.
 */
@SuppressWarnings("restriction")
public class ProjectResourceSetImpl extends ResourceSetImpl implements ProjectResourceSet {

    private final ResourceSetWorkbenchSynchronizer _synchronizer;

    /**
     * Create a new ProjectResourceSetImpl.
     */
    public ProjectResourceSetImpl() {
        _synchronizer = new DummyResourceSetWorkbenchSynchronizer();
    }

    @Override
    public IProject getProject() {
        return null;
    }

    @Override
    public void release() {
    }

    @Override
    public boolean add(ResourceHandler resourceHandler) {
        return true;
    }

    @Override
    public void addFirst(ResourceHandler resourceHandler) {
    }

    @Override
    public boolean remove(ResourceHandler resourceHandler) {
        return true;
    }

    @Override
    public ResourceSetWorkbenchSynchronizer getSynchronizer() {
        return _synchronizer;
    }

    @Override
    public void setSynchronizer(ResourceSetWorkbenchSynchronizer aSynchronizer) {
    }

    @Override
    public void resetNormalizedURICache() {
    }

    /**
     * Dummy implementation that does nothing.
     */
    private final class DummyResourceSetWorkbenchSynchronizer extends ResourceSetWorkbenchEditSynchronizer {

        public DummyResourceSetWorkbenchSynchronizer() {
            super(ProjectResourceSetImpl.this, null);
        }

        @Override
        public void resourceChanged(IResourceChangeEvent event) {
        }

        @Override
        public boolean visit(IResourceDelta delta) {
            return false;
        }

        @Override
        public void preSave(IFile aFile) {
        }

        @Override
        public void preSave(IFile aFile, Resource res) {
        }

        @Override
        public void removeFromRecentlySavedList(IFile aFile) {
        }

        @Override
        public void postSave(IFile aFile) {
        }

        @Override
        public void enableAutoload(URI uri) {
        }

        @Override
        public void disableAutoload(URI uri) {
        }

        @Override
        public void enableAutoload(String extension) {
        }

        @Override
        public void disableAutoload(String extension) {
        }

        @Override
        protected void initialize() {
        }

        @Override
        public void dispose() {
        }

        @Override
        public void addExtender(ISynchronizerExtender extender) {
        }

        @Override
        public void removeExtender(ISynchronizerExtender extender) {
        }

    }
}
