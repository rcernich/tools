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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.ActivationSpec;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JCAOutboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JCAOutboundInteraction;
import org.switchyard.tools.models.switchyard1_0.jca.JcaPackage;
import org.switchyard.tools.models.switchyard1_0.jca.Processor;
import org.switchyard.tools.models.switchyard1_0.jca.ResourceAdapter;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.databinding.EMFUpdateValueStrategyNullForEmptyString;
import org.switchyard.tools.ui.editor.databinding.SWTValueUpdater;
import org.switchyard.tools.ui.editor.databinding.StringEmptyValidator;


/**
 * @author bfitzpat
 *
 */
public class JCAHornetQTopicResourceAdapterExtension extends AbstractResourceAdapterExtension {

    private static final String DESTINATION_TYPE_PROP = "destinationType"; //$NON-NLS-1$
    private static final String DESTINATION_PROP = "destination"; //$NON-NLS-1$
    private static final String MESSAGE_SELECTOR_PROP = "messageSelector"; //$NON-NLS-1$
    private static final String ACKNOWLEDGE_MODE_PROP = "acknowledgeMode"; //$NON-NLS-1$
    private static final String CLIENTID_PROP = "clientId"; //$NON-NLS-1$
    private static final String SUBSCRIPTION_NAME_PROP = "subscriptionName"; //$NON-NLS-1$
    private static final String SUBSCRIPTION_DURABILITY_PROP = "subscriptionDurability"; //$NON-NLS-1$

    private static final String DESTINATION_TYPE_DEFAULT = "javax.jms.Topic";  //$NON-NLS-1$
    private static final String DESTINATION_DEFAULT = "topic/YourTopicName";  //$NON-NLS-1$
    private static final String SUBSCRIPTION_DURABILITY_DEFAULT = "NonDurable"; //$NON-NLS-1$
    private static final String ACKNOWLEDGE_MODE_DEFAULT = "Auto-acknowledge"; //$NON-NLS-1$
    private static final String RESOURCE_ADAPTER_NAME = "hornetq-ra.rar"; //$NON-NLS-1$

    private static final IInboundConnectionSettings INBOUND_CONNECTION_SETTINGS = new IInboundConnectionSettings() {
        @SuppressWarnings("serial")
        private final Map<String, String> _activationProperties = new LinkedHashMap<String, String>() {
            {
                put(DESTINATION_TYPE_PROP, DESTINATION_TYPE_DEFAULT);
                put(DESTINATION_PROP, DESTINATION_DEFAULT);
                put(SUBSCRIPTION_NAME_PROP, null);
                put(SUBSCRIPTION_DURABILITY_PROP, SUBSCRIPTION_DURABILITY_DEFAULT);
                put(MESSAGE_SELECTOR_PROP, null);
                put(ACKNOWLEDGE_MODE_PROP, ACKNOWLEDGE_MODE_DEFAULT);
                put(CLIENTID_PROP, null);
            }
        };

        @Override
        public Map<String, String> getResourceAdapterProperties() {
            return null;
        }
        
        @Override
        public String getResourceAdapterName() {
            return RESOURCE_ADAPTER_NAME;
        }
        
        @Override
        public Map<String, String> getActivationSpecProperties() {
            return _activationProperties;
        }
    };

    private static final String LISTENER_TYPE_DEFAULT = "javax.jms.MessageListener"; //$NON-NLS-1$
    private static final String ENDPOINT_TYPE_DEFAULT = "org.switchyard.component.jca.endpoint.JMSEndpoint"; //$NON-NLS-1$
    
    private static final IInboundInteractionSettings INBOUND_INTERACTION_SETTINGS = new IInboundInteractionSettings() {
        
        @Override
        public Boolean isTransacted() {
            return true;
        }
        
        @Override
        public String getListenerType() {
            return LISTENER_TYPE_DEFAULT;
        }
        
        @Override
        public String getEndpointType() {
            return ENDPOINT_TYPE_DEFAULT;
        }
        
        @Override
        public Map<String, String> getEndpointProperties() {
            return null;
        }
    };

    private static final IOutboundConnectionSettings OUTBOUND_CONNECTION_SETTINGS = new IOutboundConnectionSettings() {
        
        @Override
        public Map<String, String> getResourceAdapterProperties() {
            return null;
        }
        
        @Override
        public String getResourceAdapterName() {
            return RESOURCE_ADAPTER_NAME;
        }
        
        @Override
        public Map<String, String> getConnectionProperties() {
            return null;
        }
        
        @Override
        public String getConnectionJndiName() {
            return null;
        }
    };

    private static final String PROCESSOR_TYPE_DEFAULT = "org.switchyard.component.jca.processor.JMSProcessor"; //$NON-NLS-1$

    private static final IOutboundInteractionSettings OUTBOUND_INTERACTION_SETTINGS = new IOutboundInteractionSettings() {
        
        @SuppressWarnings("serial")
        private final Map<String, String> _processorProperties = new LinkedHashMap<String, String>() {
            {
                put(DESTINATION_PROP, DESTINATION_DEFAULT);
            }
        };
        @Override
        public String getProcessorType() {
            return PROCESSOR_TYPE_DEFAULT;
        }
        
        @Override
        public Map<String, String> getProcessorProperties() {
            return _processorProperties;
        }
        
        @Override
        public Map<String, String> getInteractionSpecProperties() {
            return null;
        }
        
        @Override
        public Map<String, String> getConnectionSpecProperties() {
            return null;
        }
    };

    /**
     * Constructor.
     */
    public JCAHornetQTopicResourceAdapterExtension() {
    }

    @Override
    public IInboundInteractionSettings getInboundInteractionSettings() {
        return INBOUND_INTERACTION_SETTINGS;
    }

    @Override
    public IInboundConnectionSettings getInboundConnectionSettings() {
        return INBOUND_CONNECTION_SETTINGS;
    }

    @Override
    public IOutboundInteractionSettings getOutboundInteractionSettings() {
        return OUTBOUND_INTERACTION_SETTINGS;
    }

    @Override
    public IOutboundConnectionSettings getOutboundConnectionSettings() {
        return OUTBOUND_CONNECTION_SETTINGS;
    }

    @Override
    public String getDisplayName() {
        return Messages.label_hornetQTopicResourceAdapter;
    }

    @Override
    public AbstractJCABindingComposite createComposite(FormToolkit toolkit) {
        return new JCAHornetQTopicResourceAdapterComposite(toolkit);
    }

    @Override
    public int score(JCABinding binding) {
        if (binding == null) {
            return 0;
        } else if (binding.getInboundConnection() != null) {
            // process inbound
            return scoreInboundProperties(binding);
        } else if (binding.getOutboundConnection() != null) {
            // process outbound
            return scoreOutboundProperties(binding);
        }
        return 0;
    }
    
    private int scoreInboundProperties(JCABinding binding) {
        int score = -1;
        final JCAInboundConnection connection = binding.getInboundConnection();
        if (connection == null) {
            return score;
        }
        final IInboundConnectionSettings settings = getInboundConnectionSettings();
        if (settings == null) {
            return score;
        }
        
        final ResourceAdapter ra = connection.getResourceAdapter();
        if (ra != null && settings.getResourceAdapterName() != null) {
            if (settings.getResourceAdapterName().equals(ra.getName())) {
                ++score;
            }
        }
        
        final ActivationSpec activationSpec = connection.getActivationSpec();
        if (activationSpec != null && settings.getActivationSpecProperties() != null) {
            score = score + scoreProperty(activationSpec.getProperty(), DESTINATION_TYPE_PROP, DESTINATION_TYPE_DEFAULT);
        }
        
        return score;
    }

    private int scoreOutboundProperties(JCABinding binding) {
        int score = -1;
        final JCAOutboundConnection connection = binding.getOutboundConnection();
        if (connection == null) {
            return score;
        }
        final IOutboundConnectionSettings settings = getOutboundConnectionSettings();
        if (settings == null) {
            return score;
        }
        final ResourceAdapter ra = connection.getResourceAdapter();
        if (ra != null) {
            if (settings.getResourceAdapterName() != null) {
                if (settings.getResourceAdapterName().equals(ra.getName())) {
                    ++score;
                }
            }
        }
        final JCAOutboundInteraction interaction = binding.getOutboundInteraction();
        if (interaction != null && OUTBOUND_INTERACTION_SETTINGS.getProcessorType() != null) {
            final Processor processor = interaction.getProcessor();
            if (processor != null) {
                if (OUTBOUND_INTERACTION_SETTINGS.getProcessorType().equals(processor.getType())) {
                    ++score;
                }
            }
        }
        
        return score;
    }

    private final class JCAHornetQTopicResourceAdapterComposite extends AbstractJCABindingComposite {

        private Text _messageSelectorText;
        private JCABinding _binding;
        private Composite _panel;
        private ComboViewer _acknowledgeModeCombo;
        private ComboViewer _subscriptionDurabilityCombo;
        private Text _clientIdText;
        private Text _subscriptionNameText;
        private Text _destinationText;
        private Set<org.eclipse.core.databinding.Binding> _validators = new HashSet<org.eclipse.core.databinding.Binding>();
        private WritableValue _bindingValue;

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

        @Override
        public void createContents(Composite parent, int style, DataBindingContext context) {
            _panel = getToolkit().createComposite(parent, style);
            _panel.setLayout(new GridLayout(2, false));

            _destinationText = createLabelAndText(_panel, Messages.label_destinationTopic);
            _messageSelectorText = createLabelAndText(_panel, Messages.label_messageSelector);

            _acknowledgeModeCombo = createLabelAndComboViewer(_panel, Messages.label_acknowledgeMode, true);
            _acknowledgeModeCombo.setContentProvider(ArrayContentProvider.getInstance());
            _acknowledgeModeCombo.setLabelProvider(new LabelProvider());
            _acknowledgeModeCombo.setInput(new String[] {"Auto-acknowledge", "Dups-ok-acknowledge"});
            _acknowledgeModeCombo.getCombo().setText("Auto-acknowledge"); //$NON-NLS-1$

            _subscriptionDurabilityCombo = createLabelAndComboViewer(_panel, Messages.label_subscriptionDurability, true);
            _subscriptionDurabilityCombo.setContentProvider(ArrayContentProvider.getInstance());
            _subscriptionDurabilityCombo.setLabelProvider(new LabelProvider());
            _subscriptionDurabilityCombo.setInput(new String[] {"Durable", "NonDurable"});
            _subscriptionDurabilityCombo.getCombo().setText(SUBSCRIPTION_DURABILITY_DEFAULT);

            _clientIdText = createLabelAndText(_panel, Messages.label_clientId);
            _subscriptionNameText = createLabelAndText(_panel, Messages.label_subscriptionName);
            
            bindControls(context);
            
        }

        @Override
        public Composite getPanel() {
            return _panel;
        }
        
        @Override
        public void setBinding(Binding impl) {
            super.setBinding(impl);
            _binding = (JCABinding) impl;
            if (_bindingValue != null) {
                _bindingValue.setValue(_binding);
                if (_binding == null) {
                    for (org.eclipse.core.databinding.Binding binding : _validators) {
                        binding.getValidationStatus().setValue(Status.OK_STATUS);
                    }
                } else {
                    for (org.eclipse.core.databinding.Binding binding : _validators) {
                        binding.validateTargetToModel();
                    }
                }
            }
        }

        protected void bindControls(final DataBindingContext context) {
            final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getTargetObject());
            final Realm realm = SWTObservables.getRealm(_destinationText.getDisplay());
            
            _bindingValue = new WritableValue(realm, null, JCABinding.class);
            
            final FeaturePath propertiesFeaturePath;
            if (getTargetObject() instanceof Service) {
                propertiesFeaturePath = FeaturePath.fromList(JcaPackage.Literals.JCA_BINDING__INBOUND_CONNECTION,
                        JcaPackage.Literals.JCA_INBOUND_CONNECTION__ACTIVATION_SPEC,
                        JcaPackage.Literals.ACTIVATION_SPEC__PROPERTY);
            } else {
                propertiesFeaturePath = FeaturePath.fromList(JcaPackage.Literals.JCA_BINDING__OUTBOUND_INTERACTION,
                        JcaPackage.Literals.JCA_OUTBOUND_INTERACTION__PROCESSOR,
                        JcaPackage.Literals.PROCESSOR__PROPERTY);
            }
            final IObservableList propertiesList = (domain == null ? EMFProperties.list(propertiesFeaturePath)
                    : EMFEditProperties.list(domain, propertiesFeaturePath)).observeDetail(_bindingValue);

            org.eclipse.core.databinding.Binding binding = context.bindValue(SWTObservables.observeText(
                    _destinationText, SWT.Modify), new JCANamedPropertyObservableValue(realm, propertiesList,
                    DESTINATION_PROP), new EMFUpdateValueStrategyNullForEmptyString(null,
                    UpdateValueStrategy.POLICY_CONVERT).setAfterConvertValidator(new StringEmptyValidator(
                    "Destination cannot be empty")), null);
            _validators.add(binding);
            ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT, _panel);

            binding = context.bindValue(SWTObservables.observeText(_messageSelectorText, SWT.Modify),
                    new JCANamedPropertyObservableValue(realm, propertiesList, MESSAGE_SELECTOR_PROP),
                    new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT, _panel);

            binding = context.bindValue(ViewersObservables.observeSingleSelection(_acknowledgeModeCombo),
                    new JCANamedPropertyObservableValue(realm, propertiesList, ACKNOWLEDGE_MODE_PROP),
                    new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_UPDATE), null);
            ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT, _panel);

            binding = context.bindValue(SWTObservables.observeText(_clientIdText, SWT.Modify),
                    new JCANamedPropertyObservableValue(realm, propertiesList, CLIENTID_PROP),
                    new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT, _panel);

            binding = context.bindValue(SWTObservables.observeText(_subscriptionNameText, SWT.Modify),
                    new JCANamedPropertyObservableValue(realm, propertiesList, SUBSCRIPTION_NAME_PROP),
                    new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT, _panel);

            binding = context.bindValue(ViewersObservables.observeSingleSelection(_subscriptionDurabilityCombo),
                    new JCANamedPropertyObservableValue(realm, propertiesList, SUBSCRIPTION_DURABILITY_PROP),
                    new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_UPDATE), null);
            ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT, _panel);
            
            if (_binding != null) {
                _bindingValue.setValue(_binding);
            }
        }

    }

}
