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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JCAOutboundConnection;
import org.switchyard.tools.models.switchyard1_0.jca.JcaFactory;
import org.switchyard.tools.models.switchyard1_0.jca.JcaPackage;
import org.switchyard.tools.models.switchyard1_0.jca.ResourceAdapter;
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
public class JCAGenericResourceAdapterExtension extends AbstractResourceAdapterExtension {

    private JCACustomResourceAdapterComposite _composite;

    @Override
    public AbstractSwitchyardComposite getComposite(FormToolkit toolkit) {
        if (_composite != null) {
            _composite.dispose();
        }
        _composite = new JCACustomResourceAdapterComposite(toolkit);
        return _composite;
    }

    /**
     * Constructor.
     */
    public JCAGenericResourceAdapterExtension() {
    }

    @Override
    public String getDisplayName() {
        return Messages.label_genericResourceAdapter;
    }

    private final class JCACustomResourceAdapterComposite extends AbstractJCABindingComposite {

        private Text _resourceAdapterText;
        private JCABinding _binding;
        private Composite _panel;
        private WritableValue _bindingValue;
        private IObservableValue _resourceAdapterNameValue;
        private org.eclipse.core.databinding.Binding _resourceAdapterTextBinding;
        private org.eclipse.core.databinding.Binding _dataBinding;
        private DataBindingContext _context;

        private JCACustomResourceAdapterComposite(FormToolkit toolkit) {
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
            _context = context;
            _panel = getToolkit().createComposite(parent, style);
            _panel.setLayout(new GridLayout(2, false));
            _resourceAdapterText = createLabelAndText(_panel, Messages.label_resourceAdapterArchive);
//            bindControls(context);
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
//            _bindingValue.setValue(_binding);
        }

        protected void bindControls(final DataBindingContext context) {
            final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getTargetObject());
            final Realm realm = SWTObservables.getRealm(_resourceAdapterText.getDisplay());

            _bindingValue = new WritableValue(realm, null, JCABinding.class);
            _resourceAdapterNameValue = new WritableValue(realm, null, String.class);

            _resourceAdapterTextBinding = 
                    context.bindValue(SWTObservables.observeText(_resourceAdapterText, SWT.Modify), _resourceAdapterNameValue,
                            new EMFUpdateValueStrategyNullForEmptyString(null,
                                    UpdateValueStrategy.POLICY_CONVERT).setAfterConvertValidator(new StringEmptyValidator(
                                            "Resource Adapter name cannot be empty")), null);
            ControlDecorationSupport.create(SWTValueUpdater.attach(_resourceAdapterTextBinding), SWT.TOP | SWT.LEFT);

            ComputedValue computedResourceAdapter = new ComputedValue() {
                @Override
                protected Object calculate() {
                    final String name = (String) _resourceAdapterNameValue.getValue();
                    if (name != null) {
                        if (getTargetObject() instanceof Service) {
                            final JCAInboundConnection inboundConnection = JcaFactory.eINSTANCE
                                    .createJCAInboundConnection();
                            final ResourceAdapter resourceAdapter = JcaFactory.eINSTANCE
                                    .createResourceAdapter();
                            resourceAdapter.setName(name);
                            inboundConnection.setResourceAdapter(resourceAdapter);
                            return inboundConnection;
                        } else if (getTargetObject() instanceof Reference) {
                            final JCAOutboundConnection outboundConnection = JcaFactory.eINSTANCE
                                    .createJCAOutboundConnection();
                            final ResourceAdapter resourceAdapter = JcaFactory.eINSTANCE
                                    .createResourceAdapter();
                            resourceAdapter.setName(name);
                            outboundConnection.setResourceAdapter(resourceAdapter);
                            return outboundConnection;
                        }

                    }
                    return null;
                }

                protected void doSetValue(Object value) {
                    String resourceAdapterName = null;
                    if (value instanceof JCAInboundConnection) {
                        final JCAInboundConnection inboundConnection = (JCAInboundConnection) value;
                        if (inboundConnection != null && inboundConnection.getResourceAdapter() != null) {
                            final ResourceAdapter resourceAdapter = inboundConnection.getResourceAdapter();
                            resourceAdapterName = resourceAdapter.getName();
                        }
                    } else if (value instanceof JCAOutboundConnection) {
                        final JCAOutboundConnection outboundConnection = (JCAOutboundConnection) value;
                        if (outboundConnection != null && outboundConnection.getResourceAdapter() != null) {
                            final ResourceAdapter resourceAdapter = outboundConnection.getResourceAdapter();
                            resourceAdapterName = resourceAdapter.getName();
                        }
                    }
                    _resourceAdapterNameValue.setValue(resourceAdapterName);
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
            context.removeBinding(_dataBinding);
            context.removeBinding(_resourceAdapterTextBinding);
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
    public void bindControls(DataBindingContext context) {
        _composite.bindControls(context);
    }

    @Override
    public void unbindControls(DataBindingContext context) {
        _composite.unbindControls(context);
    }

}
