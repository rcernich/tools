/******************************************************************************* 
 * Copyright (c) 2012-2014 Red Hat, Inc. 
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
package org.switchyard.tools.ui.editor.components.camel.jms;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.camel.jms.CamelJmsBindingType;
import org.switchyard.tools.models.switchyard1_0.camel.jms.JmsPackage;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.databinding.EMFUpdateValueStrategyNullForEmptyString;
import org.switchyard.tools.ui.editor.databinding.ObservablesUtil;
import org.switchyard.tools.ui.editor.databinding.SWTValueUpdater;
import org.switchyard.tools.ui.editor.databinding.StringEmptyValidator;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorComposite;

/**
 * @author bfitzpat
 * 
 */
public class CamelJmsComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelJmsBindingType _binding = null;
    private Text _nameText;
    private ComboViewer _typeCombo;
    private Text _typeNameText;
    private Text _connectionFactoryText;
    private Text _concurrentConsumersText;
    private Text _maxConcurrentConsumersText;
    private Text _replyToText;
    private Text _requestTimeOutText;
    private Text _transactionManagerText;
    private Text _selectorText;
    private Button _transactedButton;
    private OperationSelectorComposite _opSelectorComposite;
    private WritableValue _bindingValue;
    private IObservableValue _jmsValue;

    CamelJmsComposite(FormToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public String getTitle() {
        return Messages.title_jmsBindingDetails;
    }

    @Override
    public String getDescription() {
        return Messages.description_jmsBindingDetails;
    }

    @Override
    public void setBinding(Binding impl) {
        super.setBinding(impl);
        if (impl instanceof CamelJmsBindingType) {
            setTargetObject(impl.eContainer());
            _binding = (CamelJmsBindingType) impl;

            _bindingValue.setValue(_binding);

            // refresh the operation selector control
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed() && getTargetObject() != null) {
                _opSelectorComposite.setTargetObject(getTargetObject());
            }
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
                _opSelectorComposite.setBinding(_binding);
            }

        } else {
            _bindingValue.setValue(null);
        }
    }

    @Override
    public void setTargetObject(EObject target) {
        super.setTargetObject(target);
        if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
            _opSelectorComposite.setTargetObject((EObject) target);
        }
    }

    @Override
    public void createContents(Composite parent, int style, DataBindingContext context) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());

        if (getTargetObject() == null && _binding != null && this._binding.eContainer() != null) {
            setTargetObject(this._binding.eContainer());
        }
        getJmsTabControl(_panel);
        if (getTargetObject() != null && getTargetObject() instanceof Service) {
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
                _opSelectorComposite.setTargetObject((EObject) getTargetObject());
            }
        }
        
        bindControls(context);
    }

    private Control getJmsTabControl(Composite tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        composite.setLayout(gl);

        _nameText = createLabelAndText(composite, Messages.label_name);

        _typeCombo = createLabelAndComboViewer(composite, Messages.label_type, true);
        _typeCombo.setContentProvider(ArrayContentProvider.getInstance());
        _typeCombo.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof JMSType) {
                    return ((JMSType) element).getLabel();
                }
                return super.getText(element);
            }
        });
        _typeCombo.setInput(JMSType.values());

        _typeNameText = createLabelAndText(composite, Messages.label_queueTopicName);

        _connectionFactoryText = createLabelAndText(composite, Messages.label_connectionFactory);
        _connectionFactoryText.setText("#ConnectionFactory"); //$NON-NLS-1$

        _concurrentConsumersText = createLabelAndText(composite, Messages.label_concurrentConsumers);
        _concurrentConsumersText.setText("1"); //$NON-NLS-1$
        _maxConcurrentConsumersText = createLabelAndText(composite, Messages.label_maximumConcurrentConsumers);
        _maxConcurrentConsumersText.setText("1"); //$NON-NLS-1$
        _replyToText = createLabelAndText(composite, Messages.label_replyTo);
        _requestTimeOutText = null;
        if (getTargetObject() != null && getTargetObject() instanceof Reference) {
            _requestTimeOutText = createLabelAndText(composite, Messages.label_requestTimeout);
            _requestTimeOutText.setText("20000"); //$NON-NLS-1$
        }
        _selectorText = createLabelAndText(composite, Messages.label_selector);
        _transactionManagerText = createLabelAndText(composite, Messages.label_transactionManager);
        _transactedButton = createCheckbox(composite, Messages.label_transacted);

        if (getTargetObject() != null && getTargetObject() instanceof Service) {
            _opSelectorComposite = new OperationSelectorComposite(composite, SWT.NONE, this);
            _opSelectorComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
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

    protected void handleModify(final Control control) {
        // at this point, this is the only control we can't do with strict
        // databinding
        if (control.equals(_opSelectorComposite)) {
            fireChangedEvent(_opSelectorComposite);
        }
        setHasChanged(false);
        setDidSomething(true);
    }

    protected void handleUndo(Control control) {
        if (_binding != null) {
            super.handleUndo(control);
        }
    }
    
    class JMSComputedValue extends ComputedValue {
        
        private IObservableValue _jmsTypeValue = null;
        private IObservableValue _topicValue = null;
        private IObservableValue _queueValue = null;

        public JMSComputedValue(Realm realm, Object valueType, 
                IObservableValue jmsType, IObservableValue topic, IObservableValue queue) {
            super(realm, valueType);
            _jmsTypeValue = jmsType;
            _topicValue = topic;
            _queueValue = queue;
        }

        @Override
        protected Object calculate() {
            // we need to interrogate all values, or we'll miss events
            final JMSType selectedType = (JMSType) _jmsTypeValue.getValue();
            final String typeText = _typeNameText.getText();
            if (selectedType == null) {
                return null;
            }
            switch (selectedType) {
            case TOPIC:
                selectedType.updateBindingJMSType(_binding, typeText);
            case QUEUE:
                selectedType.updateBindingJMSType(_binding, typeText);
            }
            return null;
        }

        @Override
        protected void doSetValue(Object value) {
            if (value == null) {
                _topicValue.setValue(null);
                _queueValue.setValue(null);
            } else if (value instanceof CamelJmsBindingType) {
                final CamelJmsBindingType selectorType = (CamelJmsBindingType) value;
                if (selectorType.getQueue() != null) {
                    _queueValue.setValue(selectorType.getQueue());
                } else if (selectorType.getTopic() != null) {
                    _topicValue.setValue(selectorType.getTopic());
                }
            } else {
                throw new IllegalArgumentException("Unknown queue/topic type: " + value.getClass().getCanonicalName());
            }
            // update our cached value
            getValue();
        }
        
    }

    private void bindControls(final DataBindingContext context) {
        final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getTargetObject());
        final Realm realm = SWTObservables.getRealm(_nameText.getDisplay());

        _bindingValue = new WritableValue(realm, null, CamelJmsBindingType.class);

        org.eclipse.core.databinding.Binding binding = context.bindValue(
                SWTObservables.observeText(_nameText, new int[] {SWT.Modify }),
                ObservablesUtil.observeDetailValue(domain, _bindingValue,
                        ScaPackage.eINSTANCE.getBinding_Name()),
                new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT)
                        .setAfterConvertValidator(new StringEmptyValidator(
                                Messages.error_emptyName)), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        /*
         * we also want to bind the name field to the binding name. note that
         * the model to target updater is configured to NEVER update. we want
         * the camel binding name to be the definitive source for this field.
         */
        binding = context.bindValue(SWTObservables.observeText(_nameText, new int[] {SWT.Modify }), ObservablesUtil
                .observeDetailValue(domain, _bindingValue,
                        ScaPackage.eINSTANCE.getBinding_Name()),
                new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT)
                        .setAfterConvertValidator(new StringEmptyValidator(
                                Messages.error_emptyName)), new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        final IObservableValue selectorTypeValue = new WritableValue(realm, null, JMSType.class);
        final IObservableValue topicValue = new WritableValue(realm, null, String.class);
        final IObservableValue queueValue = new WritableValue(realm, null, String.class);

        /*
         * computed value. creates a JMS queue or topic based on the current
         * state of the controls.
         */
        _jmsValue = new JMSComputedValue(realm, CamelJmsBindingType.class, 
                selectorTypeValue, topicValue, queueValue);
        
        // now bind the selector into the binding
        context.bindValue(
                _jmsValue,
                ObservablesUtil.observeDetailValue(domain, _bindingValue,
                        ScaPackage.eINSTANCE.getBinding_OperationSelector()));
        binding = context
                .bindValue(
                        SWTObservables.observeText(_typeNameText, new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__QUEUE),
                        new EMFUpdateValueStrategyNullForEmptyString(null,
                                UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_connectionFactoryText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__CONNECTION_FACTORY),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_concurrentConsumersText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__CONCURRENT_CONSUMERS),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_maxConcurrentConsumersText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_replyToText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__REPLY_TO),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        if (_requestTimeOutText != null && !_requestTimeOutText.isDisposed()) {
            binding = context
                    .bindValue(
                            SWTObservables.observeText(_requestTimeOutText , new int[] {SWT.Modify }),
                            ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                    JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__REQUEST_TIMEOUT),
                            new EMFUpdateValueStrategyNullForEmptyString(
                                    "", UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);
        }

        binding = context
                .bindValue(
                        SWTObservables.observeText(_transactionManagerText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__TRANSACTION_MANAGER),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeSelection(_transactedButton),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__TRANSACTED),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                null, UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_selectorText , new int[] {SWT.Modify }),
                        ObservablesUtil.observeDetailValue(domain, _bindingValue,
                                JmsPackage.Literals.CAMEL_JMS_BINDING_TYPE__SELECTOR),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                "", UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        if (_opSelectorComposite != null) {
            _opSelectorComposite.bindControls(domain, context);
        }
    }

    private enum JMSType {
        QUEUE(Messages.label_queue) {
            @Override
            public void updateBindingJMSType(CamelJmsBindingType binding, String selectorText) {
                binding.setQueue(selectorText);
                binding.setTopic(null);
            }
        },
        TOPIC(Messages.label_topic) {
            @Override
            public void updateBindingJMSType(CamelJmsBindingType binding, String selectorText) {
                binding.setQueue(null);
                binding.setTopic(selectorText);
            }
        };

        private final String _label;

        private JMSType(String label) {
            _label = label;
        }

        /**
         * @return the display label for this JMS type.
         */
        public String getLabel() {
            return _label;
        }

        /**
         * Create a new queue or topic.
         * 
         * @param binding the binding to set the queue or topic on
         * @param queueOrTopicText the text for the queue or topic
         */
        public abstract void updateBindingJMSType(CamelJmsBindingType binding, String queueOrTopicText);

    }
}
