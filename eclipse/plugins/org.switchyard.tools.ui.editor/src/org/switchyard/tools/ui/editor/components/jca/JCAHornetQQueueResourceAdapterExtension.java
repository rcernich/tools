/******************************************************************************* 
 * Copyright (c) 2013-2014 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
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
public class JCAHornetQQueueResourceAdapterExtension extends AbstractResourceAdapterExtension {
    
    @Override
    public boolean isDefault() {
        return true;
    }

    private JCAHornetQQueueResourceAdapterComposite _composite;

    /**
     * Constructor.
     */
    public JCAHornetQQueueResourceAdapterExtension() {
    }

    @Override
    public Property[] getPropertyList() {
        ArrayList<Property> list = new ArrayList<Property>();
        list.add(createNewProperty("destination", "queue/YourQueueName")); //$NON-NLS-1$ //$NON-NLS-2$
        list.add(createNewProperty("destinationType", "javax.jms.Queue")); //$NON-NLS-1$ //$NON-NLS-2$
        return list.toArray(new Property[list.size()]);
    }

    @Override
    public String getDisplayName() {
        return Messages.label_hornetQResourceAdapter;
    }

    @Override
    public AbstractSwitchyardComposite getComposite(FormToolkit toolkit) {
        if (_composite != null) {
            _composite.dispose();
        }
        _composite = new JCAHornetQQueueResourceAdapterComposite(toolkit);
        return _composite;
    }

    private final class JCAHornetQQueueResourceAdapterComposite extends AbstractJCABindingComposite {

        final static String DESTINATION_PROP = "destination"; //$NON-NLS-1$
        final static String MESSAGE_SELECTOR_PROP = "messageSelector"; //$NON-NLS-1$
        final static String ACKNOWLEDGE_MODE_PROP = "acknowledgeMode"; //$NON-NLS-1$

        private Text _messageSelectorText;
        private JCABinding _binding;
        private Composite _panel;
        private Text _destinationText;
        private ComboViewer _acknowledgeModeCombo;
        private WritableValue _bindingValue;
        private IObservableValue _destinationValue;
        private IObservableValue _messageSelectorValue;
        private IObservableValue _acknowledgeModeValue;

        private org.eclipse.core.databinding.Binding _destinationTextBinding;
        private org.eclipse.core.databinding.Binding _messageSelectorTextBinding;
        private org.eclipse.core.databinding.Binding _acknowledgeModeComboBinding;
        private org.eclipse.core.databinding.Binding _dataBinding;
        private DataBindingContext _context;
        
        private JCAHornetQQueueResourceAdapterComposite(FormToolkit toolkit) {
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
            _panel = new Composite(parent, style);
            _panel.setLayout(new GridLayout(2, false));
            _destinationText = createLabelAndText(_panel, Messages.label_destinationQueue);
            _messageSelectorText = createLabelAndText(_panel, Messages.label_messageSelector);
            _acknowledgeModeCombo = createLabelAndComboViewer(_panel, Messages.label_acknowledgeMode, true);
            _acknowledgeModeCombo.setContentProvider(ArrayContentProvider.getInstance());
            _acknowledgeModeCombo.setLabelProvider(new LabelProvider());
            String[] methods = new String[] {"Auto-acknowledge", "Dups-ok-acknowledge"};
            _acknowledgeModeCombo.setInput(methods);
            _acknowledgeModeCombo.getCombo().setText("Auto-acknowledge"); //$NON-NLS-1$
        }

        @Override
        protected void handleModify(Control control) {
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
        }

        protected void bindControls(final DataBindingContext context) {
            final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getTargetObject());
            final Realm realm = SWTObservables.getRealm(_destinationText.getDisplay());
            
            _bindingValue = new WritableValue(realm, null, JCABinding.class);
            _destinationValue = new WritableValue(realm, null, String.class);
            _messageSelectorValue = new WritableValue(realm, null, String.class);
            _acknowledgeModeValue = new WritableValue(realm, null, String.class);

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

            _acknowledgeModeComboBinding = 
                    context.bindValue(ViewersObservables.observeSingleSelection(_acknowledgeModeCombo), _acknowledgeModeValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_acknowledgeModeComboBinding), SWT.TOP | SWT.LEFT);

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
                    
                    if (destination != null || messageSelector != null || acknowledgeMode != null) {
                        final JCAInboundConnection resourceAdapter = JcaFactory.eINSTANCE
                                    .createJCAInboundConnection();
                        final ActivationSpec activationSpec = JcaFactory.eINSTANCE
                                .createActivationSpec();
                        resourceAdapter.setActivationSpec(activationSpec);
                        setPropertyValue(activationSpec, DESTINATION_PROP, destination);
                        setPropertyValue(activationSpec, MESSAGE_SELECTOR_PROP, messageSelector);
                        setPropertyValue(activationSpec, ACKNOWLEDGE_MODE_PROP, acknowledgeMode);
                        return resourceAdapter;
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
                    } else {
                        _destinationValue.setValue(null);
                        _messageSelectorValue.setValue(null);
                        _acknowledgeModeValue.setValue(null);
                    }
                    getValue();
                }
            };
            
            // now bind the proxy into the binding
            _dataBinding = context.bindValue(
                    computedResourceAdapter,
                    ObservablesUtil.observeDetailValue(domain, _bindingValue, JcaPackage.Literals.JCA_BINDING__INBOUND_CONNECTION));
            
            if (_binding != null) {
                _bindingValue.setValue(_binding);
            }
            
        }

        protected void unbindControls(final DataBindingContext context) {
            context.removeBinding(_acknowledgeModeComboBinding);
            context.removeBinding(_dataBinding);
            context.removeBinding(_destinationTextBinding);
            context.removeBinding(_messageSelectorTextBinding);
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
        return "javax.jms.Queue"; //$NON-NLS-1$
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
