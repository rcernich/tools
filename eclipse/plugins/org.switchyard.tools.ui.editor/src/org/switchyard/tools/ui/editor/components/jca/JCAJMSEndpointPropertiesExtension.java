/******************************************************************************* 
 * Copyright (c) 2014 Red Hat, Inc. 
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
package org.switchyard.tools.ui.editor.components.jca;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.Endpoint;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundInteraction;
import org.switchyard.tools.models.switchyard1_0.jca.JcaFactory;
import org.switchyard.tools.models.switchyard1_0.jca.Property;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;

/**
 * @author bfitzpat
 *
 */
public class JCAJMSEndpointPropertiesExtension implements
        IJCAEndpointPropertiesExtension {

    private JCAJMSEndpointPropertiesComposite _composite;

    @Override
    public AbstractSwitchyardComposite getComposite(Composite parent, FormToolkit toolkit) {
        if (_composite == null) {
            _composite = new JCAJMSEndpointPropertiesComposite(toolkit);
        }
        return _composite;
    }

    /**
     * @author bfitzpat
     *
     */
    public class JCAJMSEndpointPropertiesComposite extends AbstractJCABindingComposite {

        /**
         * @param toolkit Form toolkit to use during control creation
         */
        public JCAJMSEndpointPropertiesComposite(FormToolkit toolkit) {
            super(toolkit);
        }

        private JCABinding _binding;
        private Composite _panel;
        private JCAInteractionPropertyTable _propsList;
        private Text _jndiPropsFileNameText;
        private Text _destinationJndiPropertiesFileNameText;
        private Text _connectionFactoryJNDINameText;
        private Text _replyToText;
        private Text _faultToText;
        private Combo _messageTypeCombo;
        private Text _usernameText;
        private Text _passwordText;
        private ArrayList<Control> _controlsList;

        @Override
        public String getTitle() {
            return Messages.JCAJMSEndpointPropertiesExtension_title;
        }

        @Override
        public String getDescription() {
            return getTitle();
        }

        @Override
        protected boolean validate() {
            // everything is optional
            return true;
        }

        @Override
        public void createContents(Composite parent, int style, DataBindingContext dataBindingContext) {
            _panel = getToolkit().createComposite(parent, style);
//            _panel = new Composite(parent, style);
            _panel.setLayout(new GridLayout(2, false));
//            TabbedPropertySheetWidgetFactory factory = new TabbedPropertySheetWidgetFactory();
//            factory.adapt(_panel, false, false);
            _controlsList = new ArrayList<Control>();
            
            Group endpointPropsGroup = new Group(_panel, SWT.NONE);
            endpointPropsGroup.setText(getTitle());
            endpointPropsGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true, 3, 1));
            endpointPropsGroup.setLayout(new GridLayout(2, false));
            getToolkit().adapt(endpointPropsGroup);
//            factory.adapt(endpointPropsGroup, false, false);
            
            _jndiPropsFileNameText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_JNDIPropsFileName_Label);
            _jndiPropsFileNameText.setData("jndiPropertiesFileName"); //$NON-NLS-1$
            _controlsList.add(_jndiPropsFileNameText);
            _destinationJndiPropertiesFileNameText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_DestinationJNDIPropertiesFileName_label);
            _destinationJndiPropertiesFileNameText.setData("destinationJndiPropertiesFileName"); //$NON-NLS-1$
            _controlsList.add(_destinationJndiPropertiesFileNameText);
            _connectionFactoryJNDINameText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_ConnectionFactoryJNDIName_label);
            _connectionFactoryJNDINameText.setData("connectionFactoryJNDIName"); //$NON-NLS-1$
            _controlsList.add(_connectionFactoryJNDINameText);
            _replyToText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_ReplyTo_Label);
            _replyToText.setData("replyTo"); //$NON-NLS-1$
            _controlsList.add(_replyToText);
            _faultToText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_FaultTo_Label);
            _faultToText.setData("faultTo"); //$NON-NLS-1$
            _controlsList.add(_faultToText);
            _messageTypeCombo = createLabelAndCombo(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_MessageType_label, true);
            _messageTypeCombo.setData("messageType"); //$NON-NLS-1$
            _messageTypeCombo.add("");
            _messageTypeCombo.add("Stream"); //$NON-NLS-1$
            _messageTypeCombo.add("Map"); //$NON-NLS-1$
            _messageTypeCombo.add("Text"); //$NON-NLS-1$
            _messageTypeCombo.add("Object"); //$NON-NLS-1$
            _messageTypeCombo.add("Bytes"); //$NON-NLS-1$
            _messageTypeCombo.add("Plain"); //$NON-NLS-1$
            _controlsList.add(_messageTypeCombo);
            _usernameText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_UserName_label);
            _usernameText.setData("username"); //$NON-NLS-1$
            _controlsList.add(_usernameText);
            _passwordText = createLabelAndText(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_Password_label);
            _passwordText.setData("password"); //$NON-NLS-1$
            _controlsList.add(_passwordText);

            getToolkit().createLabel(endpointPropsGroup, Messages.JCAJMSEndpointPropertiesExtension_PropertyList_label); //new Label(endpointPropsGroup, SWT.NONE);
//            tempLabel.setText(Messages.JCAJMSEndpointPropertiesExtension_PropertyList_label);
//            factory.adapt(tempLabel, false, false);
            getToolkit().createLabel(endpointPropsGroup, ""); // spacer
//            new Label(endpointPropsGroup, SWT.NONE); // spacer

            _propsList = new JCAInteractionPropertyTable(endpointPropsGroup, SWT.NONE, getToolkit());
            _propsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 4));
            _propsList.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!inUpdate() && hasChanged()) {
                        validate();
                        handleModify(_propsList);
                        fireChangedEvent(_propsList);
                    }
                }
            });
//            factory.adapt(_propsList, false, false);
        }

        private void handlePropertyField(Control control) {
            String value = null;
            if (control instanceof Text) {
                Text textControl = (Text) control;
                value = textControl.getText().trim();
            } else if (control instanceof Combo) {
                Combo comboControl = (Combo) control;
                value = comboControl.getText();
            }
            if (value != null && value.isEmpty()) {
                value = null;
            }
            String name = (String) control.getData();
            if (name != null) {
                updateInboundInteractionEndpointPropertyFeature(name, value);
                if (_binding.getInboundInteraction().getEndpoint() != null) {
                    _propsList.setSelection(_binding.getInboundInteraction().getEndpoint().getProperty());
                }
            }
        }
        
        @Override
        protected void handleModify(Control control) {
            if (_controlsList.contains(control)) {
                handlePropertyField(control);
            } else {
                super.handleModify(control);
            }
            validate();
            setHasChanged(false);
            setDidSomething(true);
        }

        @Override
        public Composite getPanel() {
            return _panel;
        }

        @Override
        public void setBinding(Binding impl) {
            super.setBinding(impl);
            this._binding = (JCABinding) impl;
            JCAInboundConnection inbound = this._binding.getInboundConnection();
            if (inbound != null) {
                if (_binding.getInboundInteraction() != null && _propsList != null) {
                    _propsList.setTargetObject(this._binding);
                    if (_binding.getInboundInteraction().getEndpoint() != null) {
                        setInUpdate(true);
                        Iterator<Control> controlIter = _controlsList.iterator();
                        while (controlIter.hasNext()) {
                            Control control = controlIter.next();
                            String featureName = (String) control.getData();
                            Property findProp = findExistingProperty(featureName);
                            if (findProp != null) {
                                if (control instanceof Text) {
                                    ((Text)control).setText((String) findProp.getValue());
                                } else if (control instanceof Combo) {
                                    ((Combo)control).setText((String) findProp.getValue());
                                }
                            }
                        }
                        setInUpdate(false);
                        
                        _propsList.setSelection(_binding.getInboundInteraction().getEndpoint().getProperty());
                    }
                }
            }
            setInUpdate(false);
            validate();
            addObservableListeners(true);
        }

        /**
         * @param binding incoming binding
         */
        public void setJCABinding(JCABinding binding) {
        }

        class EndpointOp extends ModelOperation {
            @Override
            public void run() throws Exception {
                JCAInboundInteraction interaction = _binding.getInboundInteraction();
                // default to a JMS endpoint if one is missing
                String endpointClass  = "org.switchyard.component.jca.endpoint.JMSEndpoint"; //$NON-NLS-1$
                if (interaction.getEndpoint() == null) {
                    Endpoint endpoint = JcaFactory.eINSTANCE.createEndpoint();
                    endpoint.setType(endpointClass);
                    setFeatureValue(interaction, "endpoint", endpoint); //$NON-NLS-1$
                }
            }
        }

        class EndpointPropertyOp extends ModelOperation {
            private Property _property;
            private String _name;
            private String _value;
            
            public EndpointPropertyOp(Property prop) {
                _property = prop;
            }
            
            public EndpointPropertyOp(String name, String value) {
                _name = name;
                _value = value;
            }
            
            @Override
            public void run() throws Exception {
                if (_binding != null && _binding.getInboundInteraction() != null
                        && _binding.getInboundInteraction().getEndpoint() != null 
                        && _binding.getInboundInteraction().getEndpoint().getProperty() != null) {
                    EList<Property> endpointProps = _binding.getInboundInteraction().getEndpoint().getProperty();
                    Iterator<Property> propIter = endpointProps.iterator();
                    while (propIter.hasNext()) {
                        Property next = propIter.next();
                        if (next.getName().equals(_name)) {
                            if (_value != null && !_value.trim().isEmpty()) {
                                next.setValue(_value);
                            } else {
                                endpointProps.remove(next);
                            }
                            return;
                        }
                    }
                    // if we got this far, we need to add a new property
                    Property newProp = JcaFactory.eINSTANCE.createProperty();
                    newProp.setName(_name);
                    newProp.setValue(_value);
                    endpointProps.add(newProp);
                }
            }
        }
        
        private Property findExistingProperty(String name) {
            if (_binding != null && _binding.getInboundInteraction() != null
                    && _binding.getInboundInteraction().getEndpoint() != null 
                    && _binding.getInboundInteraction().getEndpoint().getProperty() != null) {
                EList<Property> props = _binding.getInboundInteraction().getEndpoint().getProperty();
                Iterator<Property> iter = props.iterator();
                while (iter.hasNext()) {
                    Property test = iter.next();
                    if (test.getName().equalsIgnoreCase(name)) {
                        return test;
                    }
                }
            }
            return null;
        }

        protected void updateInboundInteractionEndpointPropertyFeature(String name, String value) {
            ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
            ops.add(new InboundInteractionOp());
            ops.add(new EndpointOp());
            if (name != null) {
                ops.add(new EndpointPropertyOp(name, value));
            }
            wrapOperation(this._binding, ops);
        }

    }
}
