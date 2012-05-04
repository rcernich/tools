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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.switchyard.tools.ui.editor.Activator;

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
        WizardNewFileCreationPage filePage = (WizardNewFileCreationPage) getPage("newFilePage1");
        if (filePage != null) {
            IFile file = filePage.createNewFile();
            if (file == null) {
                return false;
            }

            _createdFilePath = file.getProjectRelativePath().toPortableString();
            
            try {
                InputStream inputStream = FileLocator.openStream(
                        Activator.getDefault().getBundle(), new Path("resources/RouteXMLTemplate.xml"), false);
                file.setContents(inputStream, true, true, null);
            } catch (CoreException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }

//            String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//                    + "<route xmlns=\"http://camel.apache.org/schema/spring\">\n"
//                    + "    <from uri=\"switchyard://${service.name}\"/>\n"
//                    + "    <log message=\"${service.name} - message received: ${body}\"/>\n" + "</route>";
//            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
//            try {
//                file.setContents(bais, true, true, null);
//            } catch (CoreException e1) {
//                e1.printStackTrace();
//            }

            selectAndReveal(file);

            if (_openFileAfterCreate) {
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
        }
        return super.performFinish();
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
        super.addPages();
        WizardNewFileCreationPage filePage = (WizardNewFileCreationPage) getPage("newFilePage1");
        if (filePage != null) {
            filePage.setTitle("Route File");
            filePage.setDescription("Create a new Camel Route file resource.");
            filePage.setFileName("route.xml");
            if (this._createdFilePath != null) {
                filePage.setFileName(_createdFilePath);
            }
        }
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

}
