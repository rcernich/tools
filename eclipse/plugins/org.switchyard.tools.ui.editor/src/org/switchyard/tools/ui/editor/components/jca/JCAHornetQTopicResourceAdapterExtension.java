/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.ActivationSpec;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JcaFactory;
import org.switchyard.tools.models.switchyard1_0.jca.JcaPackage;
import org.switchyard.tools.models.switchyard1_0.jca.Property;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.databinding.EMFUpdateValueStrategyNullForEmptyString;
import org.switchyard.tools.ui.editor.databinding.ObservablesUtil;
import org.switchyard.tools.ui.editor.databinding.SWTValueUpdater;
import org.switchyard.tools.ui.editor.databinding.StringEmptyValidator;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;


/**
 * @author bfitzpat
 *
 */
public class JCAHornetQTopicResourceAdapterExtension extends AbstractResourceAdapterExtension {

    private JCAHornetQTopicResourceAdapterComposite _composite;

    /**
     * Constructor.
     */
    public JCAHornetQTopicResourceAdapterExtension() {
    }

    @Override
    public Property[] getPropertyList() {
        ArrayList<Property> list = new ArrayList<Property>();
        list.add(createNewProperty("destinationType", "javax.jms.Topic")); //$NON-NLS-1$ //$NON-NLS-2$
        list.add(createNewProperty("subscriptionDurability", "NonDurable")); //$NON-NLS-1$ //$NON-NLS-2$
        list.add(createNewProperty("destination", "topic/YourTopicName")); //$NON-NLS-1$ //$NON-NLS-2$
        return list.toArray(new Property[list.size()]);
    }

    @Override
    public String getDisplayName() {
        return Messages.label_hornetQTopicResourceAdapter;
    }

    @Override
    public AbstractSwitchyardComposite getComposite(FormToolkit toolkit) {
        if (_composite != null) {
            _composite.dispose();
        }
//        if (_composite == null) {
            _composite = new JCAHornetQTopicResourceAdapterComposite(toolkit);
//        }
        return _composite;
    }

    private final class JCAHornetQTopicResourceAdapterComposite extends AbstractJCABindingComposite {

        final static String DESTINATION_PROP = "destination"; //$NON-NLS-1$
        final static String MESSAGE_SELECTOR_PROP = "messageSelector"; //$NON-NLS-1$
        final static String ACKNOWLEDGE_MODE_PROP = "acknowledgeMode"; //$NON-NLS-1$
        final static String CLIENTID_PROP = "clientId"; //$NON-NLS-1$
        final static String SUBSCRIPTION_NAME_PROP = "subscriptionName"; //$NON-NLS-1$
        final static String SUBSCRIPTION_DURABILITY_PROP = "subscriptionDurability"; //$NON-NLS-1$

        private Text _messageSelectorText;
        private JCABinding _binding;
        private Composite _panel;
        private ComboViewer _acknowledgeModeCombo;
        private Button _subscriptionDurabilityCheckbox;
        private Text _clientIdText;
        private Text _subscriptionNameText;
        private Text _destinationText;
        private WritableValue _bindingValue;
        private IObservableValue _destinationValue;
        private IObservableValue _messageSelectorValue;
        private IObservableValue _acknowledgeModeValue;
        private IObservableValue _clientIdValue;
        private IObservableValue _subscriptionNameValue;
        private IObservableValue _subscriptionDurabilityValue;
        private org.eclipse.core.databinding.Binding _destinationTextBinding;
        private org.eclipse.core.databinding.Binding _subscriptionNameTextBinding;
        private org.eclipse.core.databinding.Binding _clientIdTextBinding;
        private org.eclipse.core.databinding.Binding _subscriptionDurabilityCheckboxBinding;
        private org.eclipse.core.databinding.Binding _messageSelectorTextBinding;
        private org.eclipse.core.databinding.Binding _acknowledgeModeCombobBinding;
        private org.eclipse.core.databinding.Binding _dataBinding;
        private DataBindingContext _context;

        private JCAHornetQTopicResourceAdapterComposite(FormToolkit toolkit) {
            super(toolkit);
        }

        @Override
        public String getTitle() {
            return getDisplayName();
        }

        @Override
        public String getDescription() {
            return getTitle();
        }

//        @Override
//        protected boolean validate() {
//            if (!_destinationText.isDisposed() && _destinationText.getText().trim().isEmpty()) {
//                setErrorMessage(Messages.error_emptyTopic);
//                return false;
//            } else if (_subscriptionDurabilityCheckbox.getSelection() && _clientIdText.getText().trim().isEmpty() && _subscriptionNameText.getText().trim().isEmpty()) {
//                setErrorMessage(Messages.error_emptyClientIdAndSubscriptionName);
//                return false;
//            }
//            return true;
//        }

        @Override
        public void createContents(Composite parent, int style, DataBindingContext context) {
            _context = context;
            _panel = getToolkit().createComposite(parent, style);
            _panel.setLayout(new GridLayout(2, false));
            _destinationText = createLabelAndText(_panel, Messages.label_destinationTopic);
            _messageSelectorText = createLabelAndText(_panel, Messages.label_messageSelector);
            _acknowledgeModeCombo = createLabelAndComboViewer(_panel, Messages.label_acknowledgeMode, true);
            _acknowledgeModeCombo.setContentProvider(ArrayContentProvider.getInstance());
            _acknowledgeModeCombo.setLabelProvider(new LabelProvider());
            String[] methods = new String[] {"Auto-acknowledge", "Dups-ok-acknowledge"};
            _acknowledgeModeCombo.setInput(methods);
            _acknowledgeModeCombo.getCombo().setText("Auto-acknowledge"); //$NON-NLS-1$
            _subscriptionDurabilityCheckbox = createCheckbox(_panel, Messages.label_subscriptionDurability);
            _clientIdText = createLabelAndText(_panel, Messages.label_clientId);
            _subscriptionNameText = createLabelAndText(_panel, Messages.label_subscriptionName);
            
//            bindControls(context);
            
//            _subscriptionDurabilityCheckbox.addSelectionListener(new SelectionListener(){
//
//                @Override
//                public void widgetSelected(SelectionEvent e) {
//                    _clientIdText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
//                    _subscriptionNameText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
//                    if (_subscriptionDurabilityCheckbox.getSelection()) {
//                        _clientIdText.setText(UUID.randomUUID().toString());
//                        updateInboundActivationProperty("clientId", _clientIdText.getText().trim()); //$NON-NLS-1$
//                        _subscriptionNameText.setText(UUID.randomUUID().toString());
//                        updateInboundActivationProperty("subscriptionName", _subscriptionNameText.getText().trim()); //$NON-NLS-1$
//                    } else {
//                        _clientIdText.setText(""); //$NON-NLS-1$
//                        _subscriptionNameText.setText(""); //$NON-NLS-1$
//                        updateInboundActivationProperty("clientId", null); //$NON-NLS-1$
//                        updateInboundActivationProperty("subscriptionName", null); //$NON-NLS-1$
//                    }
//                }
//
//                @Override
//                public void widgetDefaultSelected(SelectionEvent e) {
//                    widgetSelected(e);
//                }
//             });
//            
//            _clientIdText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
//            _subscriptionNameText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
        }

        @Override
        protected void handleModify(Control control) {
            setHasChanged(false);
            setDidSomething(true);
//            if (control.equals(_destinationText)) {
//                updateInboundActivationProperty("destination", _destinationText.getText().trim()); //$NON-NLS-1$
//            } else if (control.equals(_messageSelectorText)) {
//                updateInboundActivationProperty("messageSelector", _messageSelectorText.getText().trim()); //$NON-NLS-1$
//            } else if (control.equals(_clientIdText)) {
//                updateInboundActivationProperty("clientId", _clientIdText.getText().trim()); //$NON-NLS-1$
//            } else if (control.equals(_subscriptionNameText)) {
//                updateInboundActivationProperty("subscriptionName", _subscriptionNameText.getText().trim()); //$NON-NLS-1$
//            } else if (control.equals(_subscriptionDurabilityCheckbox)) {
//                if (_subscriptionDurabilityCheckbox.getSelection()) {
//                    updateInboundActivationProperty("subscriptionDurability", "Durable"); //$NON-NLS-1$ //$NON-NLS-2$
//                } else {
//                    updateInboundActivationProperty("subscriptionDurability", "NonDurable"); //$NON-NLS-1$ //$NON-NLS-2$
//                }
//            } else if (control.equals(_acknowledgeModeCombo)) {
//                updateInboundActivationProperty("acknowledgeMode", _acknowledgeModeCombo.getText().trim()); //$NON-NLS-1$
//            } else {
//                super.handleModify(control);
//            }
        }

        @Override
        public Composite getPanel() {
            return _panel;
        }
        
        @Override
        public void setBinding(Binding impl) {
            super.setBinding(impl);
            this._binding = (JCABinding) impl;
//            _bindingValue.setValue(_binding);
//            JCAInboundConnection inbound = this._binding.getInboundConnection();
//            if (inbound.getResourceAdapter() != null) {
//                getActivationPropertyForControl(inbound.getActivationSpec(), "destination", this._destinationText); //$NON-NLS-1$
//                getActivationPropertyForControl(inbound.getActivationSpec(), "messageSelector", this._messageSelectorText); //$NON-NLS-1$
//                getActivationPropertyForControl(inbound.getActivationSpec(), "clientId", this._clientIdText); //$NON-NLS-1$
//                getActivationPropertyForControl(inbound.getActivationSpec(), "subscriptionName", this._subscriptionNameText); //$NON-NLS-1$
//                getActivationPropertyForControl(inbound.getActivationSpec(), "acknowledgeMode", this._acknowledgeModeCombo); //$NON-NLS-1$
//
//                String subscriptionDurability =
//                        getResourceAdapterPropertyValue(inbound.getActivationSpec(), "subscriptionDurability"); //$NON-NLS-1$
//                if (subscriptionDurability != null && !this._subscriptionDurabilityCheckbox.isDisposed()) {
//                    _subscriptionDurabilityCheckbox.setSelection(subscriptionDurability.equals("Durable")); //$NON-NLS-1$
//                    _clientIdText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
//                    _subscriptionNameText.setEnabled(_subscriptionDurabilityCheckbox.getSelection());
//                }
//
//            }
//            validate();
//            addObservableListeners(true);
        }

        protected void bindControls(final DataBindingContext context) {
            final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getTargetObject());
            final Realm realm = SWTObservables.getRealm(_destinationText.getDisplay());
            
            _bindingValue = new WritableValue(realm, null, JCABinding.class);
            _destinationValue = new WritableValue(realm, null, String.class);
            _messageSelectorValue = new WritableValue(realm, null, String.class);
            _acknowledgeModeValue = new WritableValue(realm, null, String.class);
            _clientIdValue = new WritableValue(realm, null, String.class);
            _subscriptionNameValue = new WritableValue(realm, null, String.class);
            _subscriptionDurabilityValue = new WritableValue(realm, null, String.class);

            _destinationTextBinding = 
                    context.bindValue(SWTObservables.observeText(_destinationText, SWT.Modify), _destinationValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT).setAfterConvertValidator(new StringEmptyValidator(
                                            "Destination cannot be empty")), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_destinationTextBinding), SWT.TOP | SWT.LEFT);
            
            _messageSelectorTextBinding = 
                    context.bindValue(SWTObservables.observeText(_messageSelectorText, SWT.Modify), _messageSelectorValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_messageSelectorTextBinding), SWT.TOP | SWT.LEFT);

            _acknowledgeModeCombobBinding = 
                    context.bindValue(ViewersObservables.observeSingleSelection(_acknowledgeModeCombo), _acknowledgeModeValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_acknowledgeModeCombobBinding), SWT.TOP | SWT.LEFT);

            _clientIdTextBinding = 
                    context.bindValue(SWTObservables.observeText(_clientIdText, SWT.Modify), _clientIdValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_clientIdTextBinding), SWT.TOP | SWT.LEFT);

            _subscriptionNameTextBinding = 
                    context.bindValue(SWTObservables.observeText(_subscriptionNameText, SWT.Modify), _subscriptionNameValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_subscriptionNameTextBinding), SWT.TOP | SWT.LEFT);

            _subscriptionDurabilityCheckboxBinding = 
                    context.bindValue(SWTObservables.observeSelection(_subscriptionDurabilityCheckbox), _subscriptionDurabilityValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_subscriptionDurabilityCheckboxBinding), SWT.TOP | SWT.LEFT);

            ComputedValue computedResourceAdapter = new ComputedValue() {
                
                private Property findProperty(ActivationSpec activationSpec, String propName) {
                    EList<Property> inboundProps = activationSpec.getProperty();
                    Iterator<Property> propIter = inboundProps.iterator();
                    while (propIter.hasNext()) {
                        Property next = propIter.next();
                        if (next.getName().equals(propName)) {
                            return next;
                        }
                    }
                    return null;
                }
                
                private void removeProperty(ActivationSpec activationSpec, Property existingProp) {
                    EList<Property> inboundProps = activationSpec.getProperty();
                    inboundProps.remove(existingProp);
                }

                private void addProperty(ActivationSpec activationSpec, Property newProp) {
                    EList<Property> inboundProps = activationSpec.getProperty();
                    inboundProps.add(newProp);
                }

                private void setPropertyValue(ActivationSpec activationSpec, String propName, String propValue) {
                    Property existingProp = findProperty(activationSpec, propName);
                    if (existingProp != null) {
                        if (propValue == null || propValue.trim().isEmpty()) {
                            removeProperty(activationSpec, existingProp);
                        } else {
                           existingProp.setValue(propValue);
                        }
                    } else {
                        Property newProp = JcaFactory.eINSTANCE.createProperty();
                        newProp.setName(propName);
                        newProp.setValue(propValue);
                        addProperty(activationSpec, newProp);
                    }
                }
                
                private String getPropertyValue(ActivationSpec activationSpec, String propName) {
                    Property existingProp = findProperty(activationSpec, propName);
                    if (existingProp != null) {
                        return (String) existingProp.getValue();
                    }
                    return null;
                }
                
                @Override
                protected Object calculate() {
                    final String destination = (String) _destinationValue.getValue();
                    final String messageSelector = (String) _messageSelectorValue.getValue();
                    final String acknowledgeMode = (String) _acknowledgeModeValue.getValue();
                    final String clientId = (String) _clientIdValue.getValue();
                    final String subscriptionName = (String) _subscriptionNameValue.getValue();
                    final String subscriptionDurability = (String) _subscriptionDurabilityValue.getValue();
                    
                    if (destination != null || messageSelector != null || acknowledgeMode != null 
                            || clientId != null || subscriptionName != null || subscriptionDurability != null) {
                        final JCAInboundConnection inboundConnection = JcaFactory.eINSTANCE
                                    .createJCAInboundConnection();
                        final ActivationSpec activationSpec = JcaFactory.eINSTANCE
                                .createActivationSpec();
                        inboundConnection.setActivationSpec(activationSpec);
                        setPropertyValue(activationSpec, DESTINATION_PROP, destination);
                        setPropertyValue(activationSpec, MESSAGE_SELECTOR_PROP, messageSelector);
                        setPropertyValue(activationSpec, ACKNOWLEDGE_MODE_PROP, acknowledgeMode);
                        setPropertyValue(activationSpec, CLIENTID_PROP, clientId);
                        setPropertyValue(activationSpec, SUBSCRIPTION_NAME_PROP, subscriptionName);
                        
                        if (subscriptionDurability != null) {
                            boolean isDurable = Boolean.getBoolean(subscriptionDurability);
                            if (isDurable) {
                                setPropertyValue(activationSpec, SUBSCRIPTION_DURABILITY_PROP, "Durable");
                            } else {
                                setPropertyValue(activationSpec, SUBSCRIPTION_DURABILITY_PROP, "NonDurable");
                            }
                        } else {
                            setPropertyValue(activationSpec, SUBSCRIPTION_DURABILITY_PROP, null);
                        }
                        return inboundConnection;
                    }
                    return null;
                }

                protected void doSetValue(Object value) {
                    if (value instanceof JCAInboundConnection) {
                        final JCAInboundConnection inboundConnection = (JCAInboundConnection) value;
                        final ActivationSpec activationSpec = inboundConnection.getActivationSpec();
                        _destinationValue.setValue(getPropertyValue(activationSpec, DESTINATION_PROP));
                        _messageSelectorValue.setValue(getPropertyValue(activationSpec, MESSAGE_SELECTOR_PROP));
                        _acknowledgeModeValue.setValue(getPropertyValue(activationSpec, ACKNOWLEDGE_MODE_PROP));
                        _clientIdValue.setValue(getPropertyValue(activationSpec, CLIENTID_PROP));
                        _subscriptionNameValue.setValue(getPropertyValue(activationSpec, SUBSCRIPTION_NAME_PROP));
                        
                        boolean flag = false;
                        String durableValue = getPropertyValue(activationSpec, SUBSCRIPTION_DURABILITY_PROP);
                        if (durableValue != null) {
                            if (durableValue.equalsIgnoreCase("Durable")) {
                                flag = true;
                            }
                        }
                        _subscriptionDurabilityValue.setValue(Boolean.toString(flag));
                        _clientIdText.setEnabled(flag);
                        _subscriptionNameText.setEnabled(flag);
                    } else {
                        _destinationValue.setValue(null);
                        _messageSelectorValue.setValue(null);
                        _acknowledgeModeValue.setValue(null);
                        _clientIdValue.setValue(null);
                        _subscriptionNameValue.setValue(null);
                        _subscriptionDurabilityValue.setValue(null);
                        _clientIdText.setEnabled(false);
                        _subscriptionNameText.setEnabled(false);
                    }
                    getValue();
                }
            };
            
            if (getTargetObject() instanceof Service) {
                // now bind the proxy into the binding
                _dataBinding = context.bindValue(
                        computedResourceAdapter,
                        ObservablesUtil.observeDetailValue(domain, _bindingValue, 
                                JcaPackage.Literals.JCA_BINDING__INBOUND_CONNECTION));
                
            } else if (getTargetObject() instanceof Reference) {
                // now bind the proxy into the binding
                _dataBinding = context.bindValue(
                        computedResourceAdapter,
                        ObservablesUtil.observeDetailValue(domain, _bindingValue, 
                                JcaPackage.Literals.JCA_BINDING__OUTBOUND_CONNECTION));
            }
            
            if (_binding != null) {
                _bindingValue.setValue(_binding);
            }
        }

        protected void unbindControls(final DataBindingContext context) {
            context.removeBinding(_acknowledgeModeCombobBinding);
            context.removeBinding(_dataBinding);
            context.removeBinding(_clientIdTextBinding);
            context.removeBinding(_destinationTextBinding);
            context.removeBinding(_messageSelectorTextBinding);
            context.removeBinding(_subscriptionDurabilityCheckboxBinding);
            context.removeBinding(_subscriptionNameTextBinding);
        }   
        
        /* (non-Javadoc)
         * @see org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite#dispose()
         */
        @Override
        public void dispose() {
            if (_context != null) {
                unbindControls(_context);
            }
            super.dispose();
        }
    }

    @Override
    public String getResourceAdapter() {
        return "hornetq-ra.rar"; //$NON-NLS-1$
    }

    @Override
    public String getDestinationType() {
        return "javax.jms.Topic"; //$NON-NLS-1$
    }

    @Override
    public void bindControls(DataBindingContext context) {
        _composite.bindControls(context);
    }

    @Override
    public void unbindControls(DataBindingContext context) {
        _composite.unbindControls(context);
    }
}
