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
package org.switchyard.tools.ui.editor.components.camel.netty;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.OperationSelectorType;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.switchyard.tools.models.switchyard1_0.camel.netty.CamelNettyUdpBindingType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardOperationSelectorType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardFactory;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorUtil;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.util.PropTypeUtil;

/**
 * @author bfitzpat
 * 
 */
public class CamelNettyUDPComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelNettyUdpBindingType _binding = null;
    private Text _hostText;
    private Text _portText;
    private Button _broadcastCheckbox;
    private OperationSelectorComposite _opSelectorComposite;

    @Override
    public String getTitle() {
        return "UDP Binding Details";
    }

    @Override
    public String getDescription() {
        return "Specify pertinent details for your Netty UDP Binding.";
    }

    @Override
    public void setBinding(Binding impl) {
        super.setBinding(impl);
        if (impl instanceof CamelNettyUdpBindingType) {
            this._binding = (CamelNettyUdpBindingType) impl;
            setInUpdate(true);
            if (this._binding.getHost() != null) {
                _hostText.setText(this._binding.getHost());
            } else {
                _hostText.setText("");
            }
            setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
            _broadcastCheckbox.setSelection(this._binding.isBroadcast());

            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
                OperationSelectorType opSelector = OperationSelectorUtil.getFirstOperationSelector(this._binding);
                _opSelectorComposite.setBinding(this._binding);
                _opSelectorComposite.setOperation((SwitchYardOperationSelectorType) opSelector);
            }

            setInUpdate(false);
            validate();
        } else {
            this._binding = null;
        }
        addObservableListeners();
    }

    @Override
    public void setTargetObject(EObject target) {
        super.setTargetObject(target);
        if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
            _opSelectorComposite.setTargetObject((EObject) target);
        }
    }

    @Override
    protected boolean validate() {
        setErrorMessage(null);
        if (getBinding() != null) {
            if (_hostText.getText().trim().isEmpty()) {
                setErrorMessage("Host may not be empty.");
            }
            if (_portText.getText().trim().isEmpty()) {
                setErrorMessage("Port may not be empty.");
//            } else {
//                try {
//                    Integer.valueOf(_portText.getText().trim());
//                } catch (NumberFormatException nfe) {
//                    setErrorMessage("Port must be a valid number.");
//                }
            }
        }
        return (getErrorMessage() == null);
    }

    @Override
    public void createContents(Composite parent, int style) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());

        getNettyUDPTabControl(_panel);

        if (getTargetObject() != null && getTargetObject() instanceof Service) {
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
                _opSelectorComposite.setTargetObject((EObject) getTargetObject());
            }
        }
    }

    private Control getNettyUDPTabControl(Composite tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);

        Group udpGroup = new Group(composite, SWT.NONE);
        udpGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        udpGroup.setLayout(new GridLayout(2, false));
        udpGroup.setText("UDP Options");

        _hostText = createLabelAndText(udpGroup, "Host*");
        _portText = createLabelAndText(udpGroup, "Port*");
        _broadcastCheckbox = createCheckbox(udpGroup, "Broadcast");

        if (getTargetObject() instanceof Service) {
            _opSelectorComposite = new OperationSelectorComposite(composite, SWT.NONE);
            _opSelectorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            _opSelectorComposite.setLayout(new GridLayout(2, false));
            _opSelectorComposite.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    handleModify(_opSelectorComposite);
                }
             });
        }

        return composite;
    }

    @Override
    public Composite getPanel() {
        return this._panel;
    }

    class CamelOperationSelectorOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            if (_binding.getOperationSelector() == null) {
                setFeatureValue(_binding, "operationSelector", SwitchyardFactory.eINSTANCE.createStaticOperationSelectorType());
            }
        }
    }

    protected void handleModify(final Control control) {
        if (control.equals(_hostText)) {
            updateFeature(_binding, "host", _hostText.getText().trim());
        } else if (control.equals(_portText)) {
            updateFeature(_binding, "port", _portText.getText().trim());
        } else if (control.equals(_broadcastCheckbox)) {
            boolean value = _broadcastCheckbox.getSelection();
            updateFeature(_binding, "broadcast", value);
        } else if (control.equals(_opSelectorComposite)) {
            int opType = _opSelectorComposite.getSelectedOperationSelectorType();
            updateOperationSelectorFeature(opType, _opSelectorComposite.getSelectedOperationSelectorValue());
        }
        super.handleModify(control);
        setHasChanged(false);
        setDidSomething(true);
    }

    protected void handleUndo(Control control) {
        if (_binding != null) {
            if (control.equals(_hostText)) {
                _hostText.setText(this._binding.getHost());
            } else if (control.equals(_portText)) {
                setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
            } else if (control.equals(_broadcastCheckbox)) {
                _broadcastCheckbox.setSelection(this._binding.isBroadcast());
            } else {
                super.handleUndo(control);
            }
        }
        setHasChanged(false);
    }

}
