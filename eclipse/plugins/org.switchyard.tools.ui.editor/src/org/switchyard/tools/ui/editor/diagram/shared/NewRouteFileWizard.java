/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram.shared;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.switchyard.tools.ui.editor.Activator;

/**
 * @author bfitzpat
 * 
 */
class NewRouteFileWizard extends BasicNewFileResourceWizard {

    private boolean _openFileAfterCreate = false;
    private String _createdFilePath = null;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard#performFinish
     * ()
     */
    @Override
    public boolean performFinish() {
        RouteFileCreationPage filePage = (RouteFileCreationPage) getPage("newFilePage1");
        if (filePage != null) {
            IFile file = filePage.createNewFile();
            if (file == null) {
                return false;
            }

            _createdFilePath = file.getProjectRelativePath().toPortableString();

            try {
                InputStream inputStream = FileLocator.openStream(Activator.getDefault().getBundle(), new Path(
                        "resources/RouteXMLTemplate.xml"), false);
                file.setContents(inputStream, true, true, null);
            } catch (CoreException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            if (_openFileAfterCreate) {
                selectAndReveal(file);

                // Open editor on new file.
                IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();
                try {
                    if (dw != null) {
                        IWorkbenchPage page = dw.getActivePage();
                        if (page != null) {
                            IDE.openEditor(page, file, true);
                        }
                    }
                } catch (PartInitException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getLocalizedMessage()));
                }
            }
            return true;
        }
        return false;
    }

    public String getCreatedFilePath() {
        return _createdFilePath;
    }

    public void setCreatedFilePath(String inPath) {
        this._createdFilePath = inPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard#addPages()
     */
    @Override
    public void addPages() {
        RouteFileCreationPage filePage = new RouteFileCreationPage("newFilePage1", getSelection());
        // WizardNewFileCreationPage filePage = (WizardNewFileCreationPage)
        // getPage("newFilePage1");
        if (filePage != null) {
            filePage.setTitle("Route File");
            filePage.setDescription("Create a new Camel Route file resource.");
            filePage.setFileName("route.xml");
            if (this._createdFilePath != null) {
                filePage.setFileName(_createdFilePath);
            }
        }
        // super.addPages();
        addPage(filePage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard#init(org
     * .eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        super.init(workbench, currentSelection);
        setWindowTitle("New Route File");
    }

    private class RouteFileCreationPage extends WizardNewFileCreationPage {

        public RouteFileCreationPage(String pageName, IStructuredSelection selection) {
            super(pageName, selection);
        }

        @Override
        protected void createAdvancedControls(Composite parent) {
            // empty - no controls
        }

        @Override
        protected IStatus validateLinkedResource() {
            return Status.OK_STATUS;
        }

        @Override
        protected void createLinkTarget() {
            // empty
        }

    }

}
