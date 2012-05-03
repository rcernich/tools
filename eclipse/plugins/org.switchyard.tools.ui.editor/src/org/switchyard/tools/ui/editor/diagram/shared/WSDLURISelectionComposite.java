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

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.soa.sca.sca1_1.model.sca.Interface;
import org.eclipse.soa.sca.sca1_1.model.sca.WSDLPortType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.internal.LegacyResourceSupport;
import org.eclipse.ui.internal.util.Util;
import org.eclipse.wst.wsdl.ui.internal.wizards.NewWSDLWizard;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPBindingType;
import org.switchyard.tools.models.switchyard1_0.switchyard.ContextMapperType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardFactory;

/**
 * @author bfitzpat
 * 
 */
@SuppressWarnings("restriction")
public class WSDLURISelectionComposite {

    private static final String WSDL = "wsdl";

    // change listeners
    private ListenerList _changeListeners;

    private Composite _panel;
    private Text _mWSDLInterfaceURIText;
    private String _sWSDLURI = null;
    private Interface _interface = null;
    private SOAPBindingType _binding = null;
    private String _errorMessage = null;
    private Text _mWSDLPortText;
    private Label _portLabel;
    private String _bindingPort = null;
    private GridData _rootGridData = null;

    private Button _browseBtnWorkspace;
    private Button _browseBtnFile;
    private Link _newWSDLLink;

    /**
     * Constructor.
     */
    public WSDLURISelectionComposite() {
        // empty
    }

    /**
     * @param parent composite parent
     * @param style any style bits
     */
    public void createContents(Composite parent, int style) {

        _panel = new Composite(parent, style);
        GridLayout gl = new GridLayout();
        gl.numColumns = 4;
        _panel.setLayout(gl);
        if (_rootGridData != null) {
            _panel.setLayoutData(_rootGridData);
        }

        _newWSDLLink = new Link(_panel, SWT.NONE);
        String message = "<a>WSDL URI:</a>";
        _newWSDLLink.setText(message);
        // link.setSize(400, 100);
        _newWSDLLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String result = getPathToNewWSDL(_panel.getShell());
                if (result != null) {
                    _mWSDLInterfaceURIText.setText(result);
                    handleModify();
                    fireChangedEvent(_newWSDLLink);
                }
            }
        });
        _mWSDLInterfaceURIText = new Text(_panel, SWT.BORDER);
        if (_interface != null && _interface instanceof WSDLPortType) {
            _mWSDLInterfaceURIText.setText(((WSDLPortType) _interface).getInterface());
        }
        _mWSDLInterfaceURIText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                handleModify();
                fireChangedEvent(_mWSDLInterfaceURIText);
            }
        });
        GridData uriGD = new GridData(GridData.FILL_HORIZONTAL);
        // uriGD.horizontalSpan = 2;
        _mWSDLInterfaceURIText.setLayoutData(uriGD);

        _browseBtnWorkspace = new Button(_panel, SWT.PUSH);
        _browseBtnWorkspace.setText("Workspace...");
        GridData btnGD = new GridData();
        _browseBtnWorkspace.setLayoutData(btnGD);
        _browseBtnWorkspace.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                String result = selectResourceFromWorkspace(_panel.getShell(), WSDL);
                if (result != null) {
                    _mWSDLInterfaceURIText.setText(result);
                    handleModify();
                    fireChangedEvent(_browseBtnWorkspace);
                }
            }
        });

        _browseBtnFile = new Button(_panel, SWT.PUSH);
        _browseBtnFile.setText("File System...");
        GridData btnGD2 = new GridData();
        _browseBtnFile.setLayoutData(btnGD2);
        _browseBtnFile.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                String result = openFileSelectionDialog(_panel.getShell(), WSDL);
                if (result != null) {
                    File temp = new File(result);
                    result = temp.toURI().toString();
                    _mWSDLInterfaceURIText.setText(result);
                    handleModify();
                    fireChangedEvent(_browseBtnFile);
                }
            }
        });

        _portLabel = new Label(_panel, SWT.NONE);
        _portLabel.setText("Port:");
        _mWSDLPortText = new Text(_panel, SWT.BORDER);
        _mWSDLPortText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                _bindingPort = _mWSDLPortText.getText().trim();
                handleModify();
                fireChangedEvent(_mWSDLPortText);
            }
        });

        GridData portGD = new GridData(GridData.FILL_HORIZONTAL);
        portGD.horizontalSpan = 3;
        _mWSDLPortText.setLayoutData(portGD);

        setVisibilityOfPortControls(this._binding != null);
    }

    private void setVisibilityOfPortControls(boolean flag) {
        _portLabel.setVisible(flag);
        _mWSDLPortText.setVisible(flag);
    }

    private void handleModify() {
        _sWSDLURI = _mWSDLInterfaceURIText.getText().trim();
        if (_interface != null && _interface instanceof WSDLPortType) {
            ((WSDLPortType) _interface).setInterface(_sWSDLURI);
        }
        if (_binding != null) {
            _binding.setWsdl(_sWSDLURI);
            if (_bindingPort != null && _bindingPort.trim().length() > 0) {
                try {
                    Integer.parseInt(_bindingPort);
                    _binding.setSocketAddr(_bindingPort);
                } catch (NumberFormatException nfe) {
                    _binding.setSocketAddr(null);
                }
            }
            ContextMapperType contextMapper = SwitchyardFactory.eINSTANCE.createContextMapperType();
            _binding.setContextMapper(contextMapper);
        }
        validate();
    }

    private void validate() {
        this._errorMessage = null;
        String uriString = _mWSDLInterfaceURIText.getText();

        if (uriString == null || uriString.trim().length() == 0) {
            _errorMessage = "No uri specified";
        } else if (uriString.trim().length() < uriString.length()) {
            _errorMessage = "No spaces allowed in uri";
        } else {
            try {
                URI.create(uriString);
            } catch (IllegalArgumentException e) {
                _errorMessage = "Invalid URI";
            }
        }

        String portString = _bindingPort;
        if (portString != null && portString.trim().length() > 0) {
            try {
                Integer.parseInt(_bindingPort);
            } catch (NumberFormatException nfe) {
                _errorMessage = "Port must be a valid integer";
            }
        }
    }

    /**
     * @return wsdl URI string
     */
    public String getWSDLURI() {
        return this._sWSDLURI;
    }

    /**
     * @return interface
     */
    public Interface getInterface() {
        return _interface;
    }

    /**
     * @param cInterface interface
     */
    public void setInterface(Interface cInterface) {
        this._interface = cInterface;
        if (_mWSDLInterfaceURIText != null && !_mWSDLInterfaceURIText.isDisposed()) {
            _mWSDLInterfaceURIText.setText(((WSDLPortType) this._interface).getInterface());
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
        Object[] listeners = this._changeListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            ((ChangeListener) listeners[i]).stateChanged(e);
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
     * @param switchYardBindingType binding
     */
    public void setcBinding(SOAPBindingType switchYardBindingType) {
        this._binding = switchYardBindingType;
        if (_mWSDLInterfaceURIText != null && !_mWSDLInterfaceURIText.isDisposed()) {
            _mWSDLInterfaceURIText.setText(_binding.getWsdl());
        }
        setVisibilityOfPortControls(this._binding != null);
    }

    /**
     * @return string port
     */
    public String getsBindingPort() {
        return _bindingPort;
    }

    /**
     * @param rootGridData the _rootGridData to set
     */
    public void setRootGridData(GridData rootGridData) {
        this._rootGridData = rootGridData;
    }

    private static String selectResourceFromWorkspace(Shell shell, final String extension) {

        ILabelProvider labelProvider = new LabelProvider() {

            public Image getImage(final Object element) {
                String imageType = ISharedImages.IMG_OBJ_FOLDER;
                if (((IResource) element).getType() == IResource.FILE) {
                    imageType = ISharedImages.IMG_OBJ_ELEMENT;
                }

                return PlatformUI.getWorkbench().getSharedImages().getImage(imageType);
            }

            public String getText(final Object element) {
                return ((IResource) element).getName();
            }
        };

        ITreeContentProvider contentProvider = new ITreeContentProvider() {
            public Object[] getChildren(final Object parentElement) {
                Object[] result = null;
                String cmpExt = "." + extension; //$NON-NLS-1$
                if (parentElement instanceof IContainer) {
                    try {
                        List<IResource> arrFolder = new ArrayList<IResource>();
                        List<IResource> arrFile = new ArrayList<IResource>();
                        IResource[] res = ((IContainer) parentElement).members();
                        for (int i = 0; i < res.length; ++i) {
                            if (res[i].getType() == IResource.FILE) {
                                if (res[i].getName().endsWith(cmpExt)) {
                                    arrFile.add(res[i]);
                                }
                            } else {
                                arrFolder.add(res[i]);
                            }
                        }

                        List<IResource> arr = new ArrayList<IResource>();
                        arr.addAll(arrFolder);
                        arr.addAll(arrFile);
                        result = arr.toArray();
                    } catch (CoreException e) {
                        e.fillInStackTrace();
                    }
                }
                return result;
            }

            /**
             * {@inheritDoc}
             */
            public Object getParent(Object element) {
                return ((IResource) element).getParent();
            }

            /**
             * {@inheritDoc}
             */
            public boolean hasChildren(Object element) {
                if (((IResource) element).getType() == IResource.FILE) {
                    return false;
                }
                return true;
            }

            /**
             * {@inheritDoc}
             */
            public Object[] getElements(Object inputElement) {
                return getChildren(inputElement);
            }

            /**
             * {@inheritDoc}
             */
            public void dispose() {
            }

            /**
             * {@inheritDoc}
             */
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }
        };

        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(shell, labelProvider, contentProvider);
        dialog.setTitle("Select Workspace WSDL Resource");
        dialog.setAllowMultiple(false);
        dialog.setDoubleClickSelects(true);
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        if (dialog.open() == Window.OK) {
            IResource sel = (IResource) dialog.getFirstResult();
            if (sel.getType() == IResource.FILE) {
                return sel.getFullPath().makeRelative().toPortableString();
            }
        }

        return null;
    }

    private static FileDialog getFileSelectionDialog(final Shell shell) {
        FileDialog fileDialog = new FileDialog(shell, SWT.PRIMARY_MODAL | SWT.OPEN);
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        String strDefaultProject = (projects.length > 0) ? projects[0].getFullPath().toOSString() : "";
        int index = strDefaultProject.indexOf("/"); //$NON-NLS-1$
        if (index == -1) {
            index = strDefaultProject.indexOf("\\"); //$NON-NLS-1$
        }
        if (index != -1) {
            strDefaultProject = strDefaultProject.substring(index + 1);
        }
        fileDialog.setFilterPath(strDefaultProject);
        return fileDialog;
    }

    private static String openFileSelectionDialog(Shell shell, String fileExt) {
        String extName = "*." + fileExt;
        FileDialog fileDialog = getFileSelectionDialog(shell);
        fileDialog.setFilterExtensions(new String[] {"*." + fileExt }); //$NON-NLS-1$
        fileDialog.setFilterNames(new String[] {extName });
        fileDialog.setText("Select WSDL File from File System");
        return fileDialog.open();
    }

    private static String getPathToNewWSDL(final Shell shell) {
        NewWSDLWizard newWizard = new NewWSDLWizard();
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                .getSelection();
        IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
        if (selection instanceof IStructuredSelection) {
            selectionToPass = (IStructuredSelection) selection;
        } else {
            // @issue the following is resource-specific legacy code
            // Build the selection from the IFile of the editor
            Class<?> resourceClass = LegacyResourceSupport.getResourceClass();
            if (resourceClass != null) {
                IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
                        .getActivePart();
                if (part instanceof IEditorPart) {
                    IEditorInput input = ((IEditorPart) part).getEditorInput();
                    Object resource = Util.getAdapter(input, resourceClass);
                    if (resource != null) {
                        selectionToPass = new StructuredSelection(resource);
                    }
                }
            }
        }
        newWizard.init(PlatformUI.getWorkbench(), selectionToPass);
        WizardDialog dialog = new WizardDialog(shell, newWizard);
        if (dialog.open() == Window.OK) {
            return newWizard.getNewFile().getFullPath().makeRelative().toPortableString();
        }
        return null;
    }
}
