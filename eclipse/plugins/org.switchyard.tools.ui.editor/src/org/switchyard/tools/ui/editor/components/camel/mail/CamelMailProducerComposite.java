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
package org.switchyard.tools.ui.editor.components.camel.mail;

import java.util.ArrayList;

import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.switchyard.tools.models.switchyard1_1.camel.mail.CamelMailBindingType;
import org.switchyard.tools.models.switchyard1_1.camel.mail.MailFactory;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.util.PropTypeUtil;

/**
 * @author bfitzpat
 * 
 */
public class CamelMailProducerComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelMailBindingType _binding = null;
    private Text _nameText;
    private Text _hostText;
    private Text _portText;
    private Text _usernameText;
    private Text _passwordText;
    private Text _subjectText;
    private Text _fromText;
    private Text _toText;
    private Text _ccText;
    private Text _bccText;
    private Text _replyToText;
    private Button _securedCheckbox;

    @Override
    public String getTitle() {
        return Messages.title_mailBindingDetails;
    }

    @Override
    public String getDescription() {
        return Messages.description_mailBindingDetails;
    }

    @Override
    public void setBinding(Binding impl) {
        super.setBinding(impl);
        if (impl instanceof CamelMailBindingType) {
            this._binding = (CamelMailBindingType) impl;
            setInUpdate(true);
            if (this._binding.getProduce() != null) {
                if (this._binding.getProduce().getSubject() != null) {
                    _subjectText.setText(this._binding.getProduce().getSubject());
                } else {
                    _subjectText.setText(""); //$NON-NLS-1$
                }
                if (this._binding.getProduce().getFrom() != null) {
                    _fromText.setText(this._binding.getProduce().getFrom());
                } else {
                    _fromText.setText(""); //$NON-NLS-1$
                }
                if (this._binding.getProduce().getTo() != null) {
                    _toText.setText(this._binding.getProduce().getTo());
                } else {
                    _toText.setText(""); //$NON-NLS-1$
                }
                if (this._binding.getProduce().getCC() != null) {
                    _ccText.setText(this._binding.getProduce().getCC());
                } else {
                    _ccText.setText(""); //$NON-NLS-1$
                }
                if (this._binding.getProduce().getBCC() != null) {
                    _bccText.setText(this._binding.getProduce().getBCC());
                } else {
                    _bccText.setText(""); //$NON-NLS-1$
                }
                if (this._binding.getProduce().getReplyTo() != null) {
                    _replyToText.setText(this._binding.getProduce().getReplyTo());
                } else {
                    _replyToText.setText(""); //$NON-NLS-1$
                }
            }
            if (this._binding.getHost() != null) {
                _hostText.setText(this._binding.getHost());
            } else {
                _hostText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.isSetPort()) {
                setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
//                _portText.setText(Integer.toString(this._binding.getPort()));
            } else {
                _portText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getUsername() != null) {
                _usernameText.setText(this._binding.getUsername());
            } else {
                _usernameText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getPassword() != null) {
                _passwordText.setText(this._binding.getPassword());
            } else {
                _passwordText.setText(""); //$NON-NLS-1$
            }
            if (_binding.getName() == null) {
                _nameText.setText(""); //$NON-NLS-1$
            } else {
                _nameText.setText(_binding.getName());
            }
            _securedCheckbox.setSelection(this._binding.isSecure());
            setInUpdate(false);
            validate();
        } else {
            this._binding = null;
        }
        addObservableListeners();
    }

    @Override
    protected boolean validate() {
        setErrorMessage(null);
        if (getBinding() != null) {
            if (_hostText.getText().trim().isEmpty()) {
                setErrorMessage(Messages.error_emptyHost);
//            } else if (!_portText.getText().trim().isEmpty()) {
//                try {
//                    new Integer(_portText.getText().trim());
//                } catch (NumberFormatException nfe) {
//                    setErrorMessage("Port value must be a valid number.");
//                }
            }
        }
        return (getErrorMessage() == null);
    }

    @Override
    public void createContents(Composite parent, int style) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());

        getProducerTabControl(_panel);
    }

    private Control getProducerTabControl(Composite tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        composite.setLayout(gl);

        _nameText = createLabelAndText(composite, Messages.label_name);

        _hostText = createLabelAndText(composite, Messages.label_hostStar);
        _portText = createLabelAndText(composite, Messages.label_port);
        _usernameText = createLabelAndText(composite, Messages.label_userName);
        _passwordText = createLabelAndText(composite, Messages.label_password);
        _passwordText.setEchoChar('*');
        _securedCheckbox = createCheckbox(composite, Messages.label_secured);

        Group producerGroup = new Group(composite, SWT.NONE);
        producerGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
        producerGroup.setLayout(new GridLayout(2, false));
        producerGroup.setText(Messages.label_producerOptions);
        
        _subjectText = createLabelAndText(producerGroup, Messages.label_subject);
        _fromText = createLabelAndText(producerGroup, Messages.label_from);
        _toText = createLabelAndText(producerGroup, Messages.label_to);
        _ccText = createLabelAndText(producerGroup, Messages.label_cc);
        _bccText = createLabelAndText(producerGroup, Messages.label_bcc);
        _replyToText = createLabelAndText(producerGroup, Messages.label_replyTo);

        return composite;
    }

    @Override
    public Composite getPanel() {
        return this._panel;
    }

    class ProduceOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            if (_binding != null && _binding.getProduce() == null) {
                setFeatureValue(_binding, "produce", MailFactory.eINSTANCE.createCamelMailProducerType()); //$NON-NLS-1$
            }
        }
    }

    protected void updateProduceFeature(String featureId, Object value) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new ProduceOp());
        ops.add(new BasicOperation("produce", featureId, value)); //$NON-NLS-1$
        wrapOperation(ops);
    }

    protected void handleModify(final Control control) {
        if (control.equals(_hostText)) {
            updateFeature(_binding, "host", _hostText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_portText)) {
            try {
                Integer port = new Integer(_portText.getText().trim());
                updateFeature(_binding, "port", port.intValue()); //$NON-NLS-1$
            } catch (NumberFormatException nfe) {
                updateFeature(_binding, "port", _portText.getText().trim()); //$NON-NLS-1$
            }
            updateFeature(_binding, "port", _portText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_usernameText)) {
            updateFeature(_binding, "username", _usernameText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_passwordText)) {
            updateFeature(_binding, "password", _passwordText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_subjectText)) {
            updateProduceFeature("subject", _subjectText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_fromText)) {
            updateProduceFeature("from", _fromText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_toText)) {
            updateProduceFeature("to", _toText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_ccText)) {
            updateProduceFeature("cc", _ccText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_bccText)) {
            updateProduceFeature("bcc", _bccText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_replyToText)) {
            updateProduceFeature("replyTo", _replyToText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_securedCheckbox)) {
            updateFeature(_binding, "secure", _securedCheckbox.getSelection()); //$NON-NLS-1$
        } else if (control.equals(_nameText)) {
            super.updateFeature(_binding, "name", _nameText.getText().trim()); //$NON-NLS-1$
        } else {
            super.handleModify(control);
        }
        validate();
        setHasChanged(false);
        setDidSomething(true);
    }

    protected void handleUndo(Control control) {
        if (_binding != null) {
            if (control.equals(_hostText)) {
                _hostText.setText(this._binding.getHost());
            } else if (control.equals(_portText)) {
                setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
//                _portText.setText(Integer.toString(this._binding.getPort()));
            } else if (control.equals(_usernameText)) {
                _usernameText.setText(this._binding.getUsername());
            } else if (control.equals(_passwordText)) {
                _passwordText.setText(this._binding.getPassword());
            } else if (control.equals(_subjectText)) {
                _subjectText.setText(this._binding.getPassword());
            } else if (control.equals(_fromText)) {
                _fromText.setText(this._binding.getProduce().getFrom());
            } else if (control.equals(_toText)) {
                _toText.setText(this._binding.getProduce().getTo());
            } else if (control.equals(_ccText)) {
                _ccText.setText(this._binding.getProduce().getCC());
            } else if (control.equals(_bccText)) {
                _bccText.setText(this._binding.getProduce().getBCC());
            } else if (control.equals(_replyToText)) {
                _replyToText.setText(this._binding.getProduce().getReplyTo());
            } else if (control.equals(_securedCheckbox)) {
                _securedCheckbox.setSelection(this._binding.isSecure());
            } else if (control.equals(_nameText)) {
                _nameText.setText(_binding.getName() == null ? "" : _binding.getName()); //$NON-NLS-1$
            } else {
                super.handleUndo(control);
            }
        }
        setHasChanged(false);
    }

}
