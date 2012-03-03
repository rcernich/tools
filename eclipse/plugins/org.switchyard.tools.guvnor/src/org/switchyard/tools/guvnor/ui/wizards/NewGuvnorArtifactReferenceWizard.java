/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.tools.guvnor.ui.wizards;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.undo.CreateFileOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.guvnor.tools.utils.webdav.IWebDavClient;
import org.guvnor.tools.utils.webdav.WebDavServerCache;
import org.guvnor.tools.views.model.TreeObject;
import org.guvnor.tools.views.model.TreeObject.Type;
import org.switchyard.tools.guvnor.Activator;
import org.switchyard.tools.ui.wizards.ArtifactDetailsWizardPage;
import org.switchyard.tools.ui.wizards.NewArtifactReferenceWizard;

/**
 * NewGuvnorArtifactReferenceWizard
 * 
 * <p/>
 * Allows the user to add an artifact reference to a package in a Guvnor
 * repository.
 * 
 * @author Rob Cernich
 */
public class NewGuvnorArtifactReferenceWizard extends NewArtifactReferenceWizard implements IWorkbenchWizard {

    private GuvnorPackageSelectionPage _guvnorSelectionPage;
    private WizardNewFileCreationPage _fileCreationPage;

    /**
     * Create a new NewGuvnorArtifactReferenceWizard.
     */
    public NewGuvnorArtifactReferenceWizard() {
        super(true);
    }

    @Override
    public void addPages() {
        _guvnorSelectionPage = new GuvnorPackageSelectionPage();
        addPage(_guvnorSelectionPage);

        addPage(getArtifactDetailsPage());

        _fileCreationPage = new WizardNewFileCreationPage(WizardNewFileCreationPage.class.getName(),
                new StructuredSelection(getProject())) {
            @Override
            protected boolean validatePage() {
                if (getProject().getFullPath().isPrefixOf(getContainerFullPath())) {
                    return super.validatePage();
                }
                setErrorMessage("Please select a folder within project, " + getProject().getName());
                return false;
            }

            @Override
            protected void createAdvancedControls(Composite parent) {
                // No linking, we're downloading
            }

            @Override
            protected IStatus validateLinkedResource() {
                return Status.OK_STATUS;
            }
        };
        _fileCreationPage.setTitle("Download File Name");
        _fileCreationPage.setDescription("Specify name and location for downloaded package.");
        _fileCreationPage.setFileExtension("jar");
        addPage(_fileCreationPage);
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        IWizardPage nextPage = super.getNextPage(page);
        if (nextPage == _fileCreationPage) {
            if (getArtifactDetailsPage().isDownloadArtifact()) {
                _fileCreationPage.setFileName(getArtifactDetailsPage().getArtifactName());
            } else {
                nextPage = null;
            }
        }
        return nextPage;
    }

    @Override
    public boolean canFinish() {
        ArtifactDetailsWizardPage artifactDetailsPage = getArtifactDetailsPage();
        IWizardPage currentPage = getContainer().getCurrentPage();
        return _guvnorSelectionPage.isPageComplete()
                && artifactDetailsPage.isPageComplete()
                && ((!artifactDetailsPage.isDownloadArtifact() && currentPage == artifactDetailsPage) || (_fileCreationPage
                        .isPageComplete() && currentPage == _fileCreationPage));
    }

    @Override
    protected IWorkspaceRunnable createDownloadOperation() {
        final TreeObject selectedPackage = _guvnorSelectionPage.getSelection();
        final IFile newFile = getProject().getWorkspace().getRoot()
                .getFile(_fileCreationPage.getContainerFullPath().append(_fileCreationPage.getFileName()));
        final IAdaptable uiInfo = WorkspaceUndoUtil.getUIInfoAdapter(getShell());
        return new IWorkspaceRunnable() {
            @Override
            public void run(IProgressMonitor monitor) throws CoreException {
                InputStream is = null;
                try {
                    is = openPackageStream(selectedPackage);
                    new CreateFileOperation(newFile, null, is, "Downloading repository package.").execute(monitor,
                            uiInfo);
                } catch (Exception e) {
                    throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID,
                            "Error downloading Guvnor package.", e));
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.fillInStackTrace();
                        }
                    }
                }
            }
        };
    }

    private InputStream openPackageStream(TreeObject obj) {
        try {
            IWebDavClient client = WebDavServerCache.getWebDavClient(obj.getGuvnorRepository().getLocation());
            return client.getResourceInputStream(getPackageDownloadURL(obj)).getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("Error opening package resource stream.", e);
        }
    }

    @Override
    protected String getProviderURL() {
        TreeObject selection = _guvnorSelectionPage.getSelection();
        return selection == null ? null : getPackageDownloadURL(selection);
    }

    @Override
    protected String getProviderName() {
        TreeObject selection = _guvnorSelectionPage.getSelection();
        return selection == null ? null : sanitizeName(selection.getName());
    }

    /**
     * @return the download URL for the package
     */
    private String getPackageDownloadURL(TreeObject selection) {
        try {
            URL location = new URL(selection.getFullPath());
            String path = location.getPath();
            while (path.charAt(0) == '/') {
                path = path.substring(1, path.length());
            }
            String context = "/" + path.substring(0, path.indexOf("/"));
            if (selection.getNodeType() == Type.PACKAGE) {
                path = context + "/rest/packages/" + selection.getName();
            } else if (selection.getNodeType() == Type.SNAPSHOT) {
                path = context + "/rest/packages/" + selection.getParent().getName();
                if (path.endsWith("/")) {
                    path = path + "snapshots/" + selection.getName();
                } else {
                    path = path + "/snapshots/" + selection.getName();
                }
            } else {
                return null;
            }
            if (path.endsWith("/")) {
                path = path + "binary";
            } else {
                path = path + "/binary";
            }
            return new URL(location.getProtocol(), location.getHost(), location.getPort(), path).toString();
        } catch (MalformedURLException e) {
            // we should never get here (i.e. if the selection's path is bad,
            // we've got bigger problems).
            return null;
        }
    }

    private String sanitizeName(String name) {
        return name.endsWith("/") ? name.substring(0, name.length() - 1) : name;
    }
}
