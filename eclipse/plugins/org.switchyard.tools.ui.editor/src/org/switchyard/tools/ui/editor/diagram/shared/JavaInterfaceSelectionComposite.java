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

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.actions.OpenNewInterfaceWizardAction;
import org.eclipse.jdt.ui.wizards.NewInterfaceWizardPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.soa.sca.sca1_1.model.sca.Interface;
import org.eclipse.soa.sca.sca1_1.model.sca.JavaInterface;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;

/**
 * @author bfitzpat
 * 
 */
@SuppressWarnings("restriction")
public class JavaInterfaceSelectionComposite {

    // change listeners
    private ListenerList _changeListeners;

    private Composite _panel;
    private Interface _interface = null;
    private String _errorMessage = null;
    private String _interfaceClassName = null;
    private GridData _rootGridData = null;
    private Link _newClassLink;
    private Text _mClassText;
    private Button _browseClassBtn;
    private boolean _canEdit = true;
    private boolean _inUpdate = false;

    /**
     * Constructor.
     */
    public JavaInterfaceSelectionComposite() {
        // empty
    }

    /**
     * @param parent composite parent
     * @param style any style bits
     */
    public void createContents(Composite parent, int style) {

        _panel = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout();
        gl.numColumns = 3;
        _panel.setLayout(gl);
        if (_rootGridData != null) {
            _panel.setLayoutData(_rootGridData);
        }

        _newClassLink = new Link(_panel, SWT.NONE);
        String message = "<a>Java Interface:</a>";
        _newClassLink.setText(message);
        _newClassLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (_mClassText != null && !_mClassText.isDisposed()) {
                    String classname = _mClassText.getText();
                    try {
                        IType foundClass = canFindClass(classname);
                        if (foundClass == null) {
                            String className = handleCreateJavaClass();
                            if (className != null) {
                                _mClassText.setText(className);
                                handleModify();
                                fireChangedEvent(_newClassLink);
                            }
                            return;
                        } else {
                            handleOpenJavaClass(foundClass);
                        }
                    } catch (JavaModelException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        _mClassText = new Text(_panel, SWT.BORDER);
        _mClassText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                if (!_inUpdate) {
                    handleModify();
                    fireChangedEvent(_mClassText);
                }
            }
        });
        GridData uriGD = new GridData(GridData.FILL_HORIZONTAL);
        _mClassText.setLayoutData(uriGD);

        _browseClassBtn = new Button(_panel, SWT.PUSH);
        _browseClassBtn.setText("Browse...");
        GridData btnGD = new GridData();
        _browseClassBtn.setLayoutData(btnGD);
        _browseClassBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                try {
                    IType selected = selectType(_panel.getShell(), null, null);
                    if (selected != null) {
                        _mClassText.setText(selected.getFullyQualifiedName());
                        handleModify();
                        fireChangedEvent(_browseClassBtn);
                    }
                } catch (JavaModelException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * @param shell Shell for the window
     * @param superTypeName supertype to search for
     * @param project project to look in
     * @return IType the type created
     * @throws JavaModelException exception thrown
     */
    public IType selectType(Shell shell, String superTypeName, IProject project) throws JavaModelException {
        IJavaSearchScope searchScope = null;
        if (project == null) {
            ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                    .getSelection();
            IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
            if (selection instanceof IStructuredSelection) {
                selectionToPass = (IStructuredSelection) selection;
                if (selectionToPass.getFirstElement() instanceof IFile) {
                    project = ((IFile) selectionToPass.getFirstElement()).getProject();
                }
            }
        }
        if (project != null && superTypeName != null && !superTypeName.equals("java.lang.Object")) { //$NON-NLS-1$
            IJavaProject javaProject = JavaCore.create(project);
            IType superType = javaProject.findType(superTypeName);
            if (superType != null) {
                searchScope = SearchEngine.createHierarchyScope(superType);
            }
        } else if (project != null) {
            IJavaProject javaProject = JavaCore.create(project);
            searchScope = SearchEngine.createJavaSearchScope(javaProject.getJavaModel().getChildren());
        } else {
            searchScope = SearchEngine.createWorkspaceScope();
        }
        SelectionDialog dialog = JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(shell), searchScope,
                IJavaElementSearchConstants.CONSIDER_INTERFACES, false);
        dialog.setTitle("Select entries");
        dialog.setMessage("Matching items");
        if (dialog.open() == IDialogConstants.CANCEL_ID) {
            return null;
        }
        Object[] types = dialog.getResult();
        if (types == null || types.length == 0) {
            return null;
        }
        return (IType) types[0];
    }

    private IType canFindClass(String classname) throws JavaModelException {
        IProject project = null;
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                .getSelection();
        IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
        if (selection instanceof IStructuredSelection) {
            selectionToPass = (IStructuredSelection) selection;
            if (selectionToPass.getFirstElement() instanceof IFile) {
                project = ((IFile) selectionToPass.getFirstElement()).getProject();
            }
        }
        if (selectionToPass == StructuredSelection.EMPTY) {
            project = SwitchyardSCAEditor.getActiveEditor().getModelFile().getProject();
        }
        if (project != null && classname != null) { //$NON-NLS-1$
            IJavaProject javaProject = JavaCore.create(project);
            IType superType = javaProject.findType(classname);
            if (superType != null) {
                return superType;
            }
        }
        return null;
    }

    private void handleOpenJavaClass(IType classToOpen) {
        if (classToOpen != null) {
            try {
                JavaUI.openInEditor(classToOpen);
            } catch (PartInitException e) {
                e.printStackTrace();
            } catch (JavaModelException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleCreateJavaClass() throws JavaModelException {
        IProject project = null;
        IJavaProject javaProject = null;
        OpenNewInterfaceWizardAction action = new OpenNewInterfaceWizardAction();
        IFile modelFile = SwitchyardSCAEditor.getActiveEditor().getModelFile();
        IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
        if (modelFile != null) {
            selectionToPass = new StructuredSelection(modelFile);
            project = ((IFile) selectionToPass.getFirstElement()).getProject();
        }
        String className = _mClassText.getText().trim();
        NewInterfaceWizardPage page = new NewInterfaceWizardPage();
        IPackageFragmentRoot packRoot = null;
        if (project != null) { //$NON-NLS-1$
            javaProject = JavaCore.create(project);
            if (!className.isEmpty()) {
                if (className.contains(".")) {
                    className = className.substring(className.lastIndexOf('.') + 1);
                }
                page.setTypeName(className, true);
            }
            IPackageFragment[] packages = javaProject.getPackageFragments();
            for (IPackageFragment mypackage : packages) {
                if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
                    selectionToPass = new StructuredSelection(mypackage);
                    packRoot = (IPackageFragmentRoot) mypackage.getParent();
                    break;
                }
            }
            page.setPackageFragmentRoot(packRoot, true);
        }
        action.setSelection(selectionToPass);
        if (javaProject != null) {
            IJavaElement[] roots = packRoot.getChildren();
            PackageFragment stashFrag = null;
            for (int i = 0; i < roots.length; i++) {
                PackageFragment frag = (PackageFragment) roots[i];
                if (!frag.isDefaultPackage() && !frag.hasSubpackages()) {
                    stashFrag = frag;
                    break;
                }
            }
            if (stashFrag != null) {
                page.setPackageFragment(stashFrag, true);
            }
        }
        action.setConfiguredWizardPage(page);
        action.setOpenEditorOnFinish(false);
        action.run();
        IJavaElement createdElement = action.getCreatedElement();
        if (createdElement != null && createdElement instanceof IType) {
            IType stype = (IType) createdElement;
            String name = stype.getFullyQualifiedName();
            if (name != null) {
                return name;
            }
        }
        return null;
    }

    private void handleModify() {
        _interfaceClassName = _mClassText.getText().trim();
        validate();
        if (_interface instanceof JavaInterface) {
            if (_mClassText != null && !_mClassText.isDisposed() && _mClassText.isEnabled()) {
                if (_interface.eContainer() != null) {
                    TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            ((JavaInterface) _interface).setInterface(_interfaceClassName);
                        }
                    });
                } else {
                    ((JavaInterface) _interface).setInterface(_interfaceClassName);
                }
            }
        }
    }

    private void validate() {
        _errorMessage = null;
        // test to make sure class is valid
        String className = _mClassText.getText();

        if (className == null || className.trim().length() == 0) {
            _errorMessage = "No Class specified";
        } else if (className.trim().length() < className.length()) {
            _errorMessage = "No spaces allowed in class name";
        }
    }

    /**
     * @return interface
     */
    public Interface getInterface() {
        return _interface;
    }

    /**
     * @param cInterface interface coming in
     */
    public void setInterface(Interface cInterface) {
        this._interface = cInterface;
        if (this._interface != null && this._interface instanceof JavaInterface) {
            JavaInterface javaIntfc = (JavaInterface) this._interface;
            _inUpdate = true;
            if (javaIntfc.getInterface() != null) {
                this._mClassText.setText(javaIntfc.getInterface());
            } else {
                this._mClassText.setText("org.example.IServiceInterface");
            }
            _inUpdate = false;
        }
    }

    /**
     * @return string error message
     */
    public String getErrorMessage() {
        return _errorMessage;
    }

    /**
     * If we changed, fire a changed event.
     * 
     * @param source
     */
    private void fireChangedEvent(Object source) {
        ChangeEvent e = new ChangeEvent(source);
        // inform any listeners of the resize event
        if (this._changeListeners != null && !this._changeListeners.isEmpty()) {
            Object[] listeners = this._changeListeners.getListeners();
            for (int i = 0; i < listeners.length; ++i) {
                ((ChangeListener) listeners[i]).stateChanged(e);
            }
        }
    }

    /**
     * Add a change listener.
     * 
     * @param listener new listener
     */
    public void addChangeListener(ChangeListener listener) {
        if (this._changeListeners == null) {
            this._changeListeners = new ListenerList();
        }
        this._changeListeners.add(listener);
    }

    /**
     * Remove a change listener.
     * 
     * @param listener to remove
     */
    public void removeChangeListener(ChangeListener listener) {
        this._changeListeners.remove(listener);
    }

    /**
     * @return panel
     */
    public Composite getcPanel() {
        return _panel;
    }

    /**
     * @return String for camel class
     */
    public String getInterfaceClassName() {
        return this._interfaceClassName;
    }

    /**
     * @param rootGridData the _rootGridData to set
     */
    public void setRootGridData(GridData rootGridData) {
        this._rootGridData = rootGridData;
    }

    /**
     * @return flag
     */
    public boolean canEdit() {
        return _canEdit;
    }

    /**
     * @param canEdit flag
     */
    public void setCanEdit(boolean canEdit) {
        this._canEdit = canEdit;
        if (this._mClassText != null && !this._mClassText.isDisposed()) {
            this._mClassText.setEnabled(_canEdit);
        }
        if (this._newClassLink != null && !this._newClassLink.isDisposed()) {
            this._newClassLink.setEnabled(_canEdit);
        }
        if (this._browseClassBtn != null && !this._browseClassBtn.isDisposed()) {
            this._browseClassBtn.setEnabled(_canEdit);
        }
    }
}
