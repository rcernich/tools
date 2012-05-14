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
package org.switchyard.tools.ui.editor.diagram.binding.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPBindingType;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPFactory;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardBindingType;

/**
 * @author bfitzpat
 * 
 */
public class SCADiagramAddBindingStartPage extends WizardPage {

    private ListViewer _listViewer;
    private SwitchYardBindingType _binding = null;

    /**
     * List width in characters.
     */
    private final static int LIST_WIDTH = 60;

    /**
     * List height in characters.
     */
    private final static int LIST_HEIGHT = 10;

    protected SCADiagramAddBindingStartPage(String pageName) {
        super(pageName);
        setTitle("Create a New Binding");
        setDescription("Specify binding type and details.");
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        composite.setLayout(gl);

        Label listLabel = new Label(composite, SWT.NONE);
        listLabel.setText("Binding Type:");
        GridData labelGD = new GridData(GridData.FILL_HORIZONTAL);
        labelGD.horizontalSpan = 2;
        listLabel.setLayoutData(labelGD);

        _listViewer = new ListViewer(composite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        data.heightHint = convertHeightInCharsToPixels(LIST_HEIGHT);
        data.widthHint = convertWidthInCharsToPixels(LIST_WIDTH);
        _listViewer.getList().setLayoutData(data);
        _listViewer.getList().setFont(parent.getFont());
        ArrayList<SwitchYardBindingType> typeList = new ArrayList<SwitchYardBindingType>();
        getBindingTypes(typeList);
        // Set the label provider
        _listViewer.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                SwitchYardBindingType interfaceType = (SwitchYardBindingType) element;
                if (interfaceType instanceof SOAPBindingType) {
                    return "SOAP";
                } else {
                    return "";
                }
            }
        });
        _listViewer.setContentProvider(new IStructuredContentProvider() {
            public Object[] getElements(Object inputElement) {
                if (inputElement instanceof List<?>) {
                    List<?> v = (List<?>) inputElement;
                    return v.toArray();
                }
                return new Object[0];
            }

            public void dispose() {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }
        });
        _listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection ssel = (IStructuredSelection) event.getSelection();
                if (!ssel.isEmpty() && ssel.getFirstElement() instanceof SwitchYardBindingType) {
                    _binding = (SwitchYardBindingType) ssel.getFirstElement();
                    handleModify();
                }

            }
        });
        _listViewer.setInput(typeList);

        setControl(composite);

        validate();
        setErrorMessage(null);
    }

    /**
     * @return updated binding
     */
    public SwitchYardBindingType getBinding() {
        return this._binding;
    }

    private void handleModify() {
        validate();
    }

    private void validate() {
        String errorMessage = null;
        if (_binding == null) {
            errorMessage = "Please select a binding type.";
        // String cpName = mBindingName.getText();
        //
        // if (cpName == null || cpName.trim().length() == 0) {
        // errorMessage = "No name specified";
        // }
        // else if (cpName.trim().length() < cpName.length() ) {
        // errorMessage = "No spaces allowed in name";
        }
        setErrorMessage(errorMessage);
        setPageComplete(errorMessage == null);
    }

    private void getBindingTypes(List<SwitchYardBindingType> types) {
        SwitchYardBindingType soapBindingType = SOAPFactory.eINSTANCE.createSOAPBindingType();
        ((SOAPBindingType) soapBindingType).setWsdl("MyService.wsdl");
        ((SOAPBindingType) soapBindingType).setSocketAddr("18001");
        types.add(soapBindingType);
    }

    // private void getImplementationTypes ( List<Implementation> types ) {
    //
    // Implementation beanImplementation =
    // BeanFactory.eINSTANCE.createBeanImplementationType();
    // types.add(beanImplementation);
    //
    // Implementation soapImplementation =
    // SOAPFactory.eINSTANCE.createSOAPBindingType().eClass();
    // types.add(soapImplementation);
    //
    // EClass implementationTypeEClass = null;
    // EList<EClass> superTypes =
    // BeanFactory.eINSTANCE.createBeanImplementationType().eClass().getEAllSuperTypes();
    // for (EClass eClass : superTypes) {
    // if (eClass.getName().contentEquals(Implementation.class.getSimpleName()))
    // {
    // implementationTypeEClass = eClass;
    // break;
    // }
    // }
    // for (EClassifier eclassifier :
    // implementationTypeEClass.getEPackage().getEClassifiers() ) {
    // if (eclassifier instanceof EClass) {
    // EClass eclass = (EClass)eclassifier;
    // if (eclass.getESuperTypes().contains(implementationTypeEClass)) {
    // types.add(eclass);
    // }
    // }
    // }
    // }

}
