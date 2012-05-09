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
package org.switchyard.tools.ui.editor.property;

import java.beans.PropertyChangeEvent;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPBindingType;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;

/**
 * @author bfitzpat
 *
 */
public class SwitchyardSCAPropertiesBindings extends GFPropertySection implements ITabbedPropertyConstants {

    private Text _wsdlText;
    private Binding _binding;

    /**
     * Constructor. 
     */
    public SwitchyardSCAPropertiesBindings() {
        super();
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        FormData data;

        _wsdlText = factory.createText(composite, ""); //$NON-NLS-1$
        _wsdlText.setEditable(true);
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, VSPACE);
        _wsdlText.setLayoutData(data);
        _wsdlText.addModifyListener(new ModifyListener() {
            @SuppressWarnings("restriction")
            @Override
            public void modifyText(ModifyEvent e) {
                if (_binding != null && _binding instanceof SOAPBindingType) {
                    final SOAPBindingType soapBinding = (SOAPBindingType) _binding;
                    if (!soapBinding.getWsdl().contentEquals(_wsdlText.getText().trim())) {
                        TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                        domain.getCommandStack().execute(new RecordingCommand(domain) {
                            @Override
                            protected void doExecute() {
                                soapBinding.setWsdl(_wsdlText.getText().trim());
                            }
                        });
                    }
                }
            }
         });

        CLabel valueLabel = factory.createCLabel(composite, "Bindings:"); //$NON-NLS-1$
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(_wsdlText, -HSPACE);
        data.top = new FormAttachment(_wsdlText, 0, SWT.CENTER);
        valueLabel.setLayoutData(data);
    }

    @Override
    public void refresh() {
        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
            Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
            // the filter assured, that it is a EClass
            if (bo == null) {
                return;
            }
            if (bo instanceof Service) {
                Service service = (Service) bo; 
                EList<Binding> bindings = service.getBinding();
                for (Binding binding : bindings) {
                    if (binding instanceof SOAPBindingType) {
                        SOAPBindingType soapBinding = (SOAPBindingType) binding;
                        _binding = soapBinding;
                        String wsdlFile = soapBinding.getWsdl();
                        _wsdlText.setText(wsdlFile == null ? "" : wsdlFile); //$NON-NLS-1$
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.toString());
    }
}
