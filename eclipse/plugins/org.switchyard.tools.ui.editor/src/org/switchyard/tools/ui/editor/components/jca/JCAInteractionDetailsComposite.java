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
package org.switchyard.tools.ui.editor.components.jca;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.emf.common.util.EList;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.switchyard.tools.models.switchyard1_0.jca.BatchCommit;
import org.switchyard.tools.models.switchyard1_0.jca.Endpoint;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundInteraction;
import org.switchyard.tools.models.switchyard1_0.jca.JcaFactory;
import org.switchyard.tools.models.switchyard1_0.jca.Property;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.util.PropTypeUtil;

/**
 * @author bfitzpat
 * 
 */
public class JCAInteractionDetailsComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private JCABinding _binding = null;
    private Combo _endpointMappingTypeCombo;
    private Button _transactedButton;
    private Group _batchGroup;
    private Button _batchEnabledCheckbox;
    private Text _batchSizeText;
    private Text _batchTimeoutText;
    private AbstractJCABindingComposite _endpointPropsComposite;
    private Composite _stackComposite;
    private StackLayout _stackLayout;
    private DataBindingContext _context;

    private enum ENDPOINT_MAPPING_TYPE {
        JMSENDPOINT, CCIENDPOINT
    }

    JCAInteractionDetailsComposite(FormToolkit toolkit) {
        super(toolkit);
    }

    @Override
    public String getTitle() {
        return Messages.title_interactionDetails;
    }

    @Override
    public String getDescription() {
        return Messages.description_interactionDetails;
    }

    private void processEndpointComboSelection() {
        Combo combo = _endpointMappingTypeCombo;
        int index = combo.getSelectionIndex();
        String value = ""; //$NON-NLS-1$
        if (index == ENDPOINT_MAPPING_TYPE.CCIENDPOINT.ordinal()) {
            value = "cciendpoint"; //$NON-NLS-1$
        } else if (index == ENDPOINT_MAPPING_TYPE.JMSENDPOINT.ordinal()) {
            value = "jmsendpoint"; //$NON-NLS-1$
        }
        swapExtensionComposites(value, true);
    }

    private void swapExtensionComposites(String endpointType, boolean updateValues) {
        AbstractJCABindingComposite syComposite = null;
        IJCAEndpointPropertiesExtension extension = null;
        if (endpointType.equalsIgnoreCase("cciendpoint")) { //$NON-NLS-1$
            extension = new JCACCIEndpointPropertiesExtension();
            syComposite = (AbstractJCABindingComposite) extension.getComposite(_stackComposite, getToolkit());
            syComposite.createContents(_stackComposite, SWT.NONE, _context);
        } else if (endpointType.equalsIgnoreCase("jmsendpoint")) { //$NON-NLS-1$
            extension = new JCAJMSEndpointPropertiesExtension();
            syComposite = (AbstractJCABindingComposite) extension.getComposite(_stackComposite, getToolkit());
            syComposite.createContents(_stackComposite, SWT.NONE, _context);
        }
        _endpointPropsComposite = syComposite;
        if (syComposite != null) {
            _stackLayout.topControl = syComposite.getPanel();
            syComposite.setBinding(getBinding());
        }
        _stackComposite.layout();
        if (_stackComposite.getParent() != null) {
            _stackComposite.getParent().layout();
        }
        syComposite.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (_endpointPropsComposite != null && !_endpointPropsComposite.validate()) {
                    setErrorMessage(_endpointPropsComposite.getErrorMessage());
                } else {
                    validate();
                }
                fireChangedEvent(_endpointPropsComposite);
            }
        });
        validate();
    }
 
    @Override
    public void setBinding(Binding impl) {
        super.setBinding(impl);
        if (impl instanceof JCABinding) {
            this._binding = (JCABinding) impl;
            setInUpdate(true);
            JCAInboundInteraction interaction = _binding.getInboundInteraction();
            if (interaction != null 
                    && _binding.getInboundInteraction().getEndpoint() == null) {
                _endpointMappingTypeCombo.select(ENDPOINT_MAPPING_TYPE.JMSENDPOINT.ordinal());
                updateEndpoint();
                interaction = _binding.getInboundInteraction();
                String className = interaction.getEndpoint().getType();
                className = className.substring(className.lastIndexOf('.') + 1);
                swapExtensionComposites(className, true);
            } else if (interaction != null && interaction.getEndpoint() != null) {
                String className = interaction.getEndpoint().getType();
                className = className.substring(className.lastIndexOf('.') + 1);
                swapExtensionComposites(className, true);
                if (className.equalsIgnoreCase("jmsendpoint")) { //$NON-NLS-1$
                    _endpointMappingTypeCombo.select(ENDPOINT_MAPPING_TYPE.JMSENDPOINT.ordinal());
                } else if (className.equalsIgnoreCase("cciendpoint")) { //$NON-NLS-1$
                    _endpointMappingTypeCombo.select(ENDPOINT_MAPPING_TYPE.CCIENDPOINT.ordinal());
                }
            }
            if (interaction.getBatchCommit() != null) {
                _batchEnabledCheckbox.setSelection(true);
                setTextValue(_batchSizeText, PropTypeUtil.getPropValueString(this._binding.getInboundInteraction().getBatchCommit().getBatchSize()));
                setTextValue(_batchTimeoutText, PropTypeUtil.getPropValueString(this._binding.getInboundInteraction().getBatchCommit().getBatchTimeout()));
            } else {
                _batchEnabledCheckbox.setSelection(false);
            }
            if (interaction.isTransacted()) {
                _transactedButton.setSelection(interaction.isTransacted());
            }
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
            if (!_endpointMappingTypeCombo.isDisposed() && _endpointMappingTypeCombo.getSelectionIndex() == -1) {
                setErrorMessage(Messages.error_emptyMappingType);
            }
        }
        return (getErrorMessage() == null);
    }

    @Override
    public void createContents(Composite parent, int style, DataBindingContext context) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());
        _context = context;

        getJCAInteractionDetailsTabControl(_panel);
    }
    
    class ClearEndpointPropertiesOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            if (_binding != null && _binding.getInboundInteraction() != null
                    && _binding.getInboundInteraction().getEndpoint() != null 
                    && _binding.getInboundInteraction().getEndpoint().getProperty() != null) {
                EList<Property> props = _binding.getInboundInteraction().getEndpoint().getProperty();
                props.clear();
            }
        }
    }

    class EndpointPropertyOp extends ModelOperation {
        private Property _property;
        public EndpointPropertyOp(Property prop) {
            _property = prop;
        }
        @Override
        public void run() throws Exception {
            if (_binding != null && _binding.getInboundInteraction() != null
                    && _binding.getInboundInteraction().getEndpoint() != null 
                    && _binding.getInboundInteraction().getEndpoint().getProperty() != null) {
                EList<Property> props = _binding.getInboundInteraction().getEndpoint().getProperty();
                props.add(_property);
            }
        }
    }

    protected void updatePropertiesFromPropertyArray(Property[] propList) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new EndpointOp());
        ops.add(new ClearEndpointPropertiesOp());
        if (propList != null && propList.length > 0) {
            for (int i = 0; i < propList.length; i++) {
                Property property = propList[i];
                ops.add(new EndpointPropertyOp(property));
            }
        }
        wrapOperation(ops);
    }

    private Control getJCAInteractionDetailsTabControl(Composite tabFolder) {
        Composite composite = getToolkit().createComposite(tabFolder, SWT.NONE);
//        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);
        
        _endpointMappingTypeCombo = createLabelAndCombo(composite, Messages.label_endpointMappingType, true);
        _endpointMappingTypeCombo.add(Messages.label_jmsEndpoint, ENDPOINT_MAPPING_TYPE.JMSENDPOINT.ordinal());
        _endpointMappingTypeCombo.setData(Messages.label_jmsEndpoint, ENDPOINT_MAPPING_TYPE.JMSENDPOINT);
        _endpointMappingTypeCombo.add(Messages.label_cciEndpoint, ENDPOINT_MAPPING_TYPE.CCIENDPOINT.ordinal());
        _endpointMappingTypeCombo.setData(Messages.label_cciEndpoint, ENDPOINT_MAPPING_TYPE.CCIENDPOINT);
        
        _endpointMappingTypeCombo.addSelectionListener(new SelectionListener(){
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                processEndpointComboSelection();
            }

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                processEndpointComboSelection();
            }
        });

        _transactedButton = createCheckbox(composite, Messages.label_transacted);
        
        _batchGroup = new Group(composite, SWT.NONE);
        GridData bgGridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        _batchGroup.setLayoutData(bgGridData);
        _batchGroup.setLayout(new GridLayout(2, false));
        _batchGroup.setText(Messages.label_batchCommitOptions);
        getToolkit().adapt(_batchGroup);
        
        _batchEnabledCheckbox = createCheckbox(_batchGroup, Messages.label_enableBatchCommit);
        _batchSizeText = createLabelAndText(_batchGroup, Messages.label_batchSize);
        _batchTimeoutText = createLabelAndText(_batchGroup, Messages.label_batchTimeout);
        _batchEnabledCheckbox.addSelectionListener(new SelectionListener(){

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
            }

            @Override
            public void widgetSelected(SelectionEvent e) {
                _batchSizeText.setEnabled(_batchEnabledCheckbox.getSelection());
                _batchTimeoutText.setEnabled(_batchEnabledCheckbox.getSelection());
                handleModify(_batchEnabledCheckbox);
            }
        });

        _stackComposite = getToolkit().createComposite(composite, SWT.NONE);
        GridData gd = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1);
        _stackComposite.setLayoutData(gd);
        _stackLayout = new StackLayout();
        _stackComposite.setLayout(_stackLayout);
        Composite dummy = getToolkit().createComposite(_stackComposite, SWT.NONE);
        _stackLayout.topControl = dummy;
        TabbedPropertySheetWidgetFactory factory = new TabbedPropertySheetWidgetFactory();
        factory.adapt(_stackComposite, false, false);

        return composite;
    }

    @Override
    public Composite getPanel() {
        return this._panel;
    }

    class InboundInteractionOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            if (_binding != null && _binding.getInboundInteraction() == null) {
                JCAInboundInteraction interaction = JcaFactory.eINSTANCE.createJCAInboundInteraction();
                setFeatureValue(_binding, "inboundInteraction", interaction); //$NON-NLS-1$
            }
        }
    }
    
    class BatchCommitOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            JCAInboundInteraction interaction = _binding.getInboundInteraction();
            if (interaction != null) {
                BatchCommit batchCommit = interaction.getBatchCommit();
                if (batchCommit == null) {
                    batchCommit = JcaFactory.eINSTANCE.createBatchCommit();
                    setFeatureValue(_binding.getInboundInteraction(), "batchCommit", batchCommit); //$NON-NLS-1$
                }
            }
        }
    }

    class RemoveBatchCommitOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            JCAInboundInteraction interaction = _binding.getInboundInteraction();
            if (interaction != null) {
                BatchCommit batchCommit = interaction.getBatchCommit();
                if (batchCommit != null) {
                    setFeatureValue(_binding.getInboundInteraction(), "batchCommit", null); //$NON-NLS-1$
                }
            }
        }
    }

    class EndpointOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            JCAInboundInteraction interaction = _binding.getInboundInteraction();
            ENDPOINT_MAPPING_TYPE type = (ENDPOINT_MAPPING_TYPE) _endpointMappingTypeCombo
                    .getData(_endpointMappingTypeCombo.getText());
            String listener = null;
            // String inboundOpName = null;
            String endpointClass = null;
            boolean foundEndpoint = true;
            switch (type) {
            case JMSENDPOINT:
                listener = "javax.jms.MessageListener"; //$NON-NLS-1$
                // inboundOpName = "onMessage";
                endpointClass = "org.switchyard.component.jca.endpoint.JMSEndpoint"; //$NON-NLS-1$
                break;
            case CCIENDPOINT:
                listener = "javax.resource.cci.MessageListener"; //$NON-NLS-1$
                // inboundOpName = "onMessage";
                endpointClass = "org.switchyard.component.jca.endpoint.CCIEndpoint"; //$NON-NLS-1$
                break;
            default:
                foundEndpoint = false;
                break;
            }

            if (foundEndpoint) {
                interaction.setListener(listener);
                if (interaction.getEndpoint() == null) {
                    Endpoint endpoint = JcaFactory.eINSTANCE.createEndpoint();
                    endpoint.setType(endpointClass);
                    setFeatureValue(interaction, "endpoint", endpoint); //$NON-NLS-1$
                } else {
                    interaction.getEndpoint().setType(endpointClass);
                    interaction.getEndpoint().getProperty().clear();
                }
            }
        }
    }

    protected void updateInboundInteractionFeature(String featureId, Object value) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new InboundInteractionOp());
        ops.add(new BasicOperation("inboundInteraction", featureId, value)); //$NON-NLS-1$
        wrapOperation(ops);
    }

    protected void updateInboundInteractionBatchCommitFeature(String featureId, Object value) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new InboundInteractionOp());
        ops.add(new BatchCommitOp());
        if (featureId != null) {
            ops.add(new BasicOperation("inboundInteraction/batchCommit", featureId, value)); //$NON-NLS-1$
        }
        wrapOperation(ops);
    }

    protected void removeInboundInteractionBatchCommitFeature() {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new InboundInteractionOp());
        ops.add(new RemoveBatchCommitOp());
        wrapOperation(ops);
    }

    protected void updateEndpoint() {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new InboundInteractionOp());
        ops.add(new EndpointOp());
        wrapOperation(ops);
    }

    protected void handleModify(final Control control) {
        if (control.equals(_endpointMappingTypeCombo)) {
            updateEndpoint();
        } else if (control.equals(_transactedButton)) {
            updateInboundInteractionFeature("transacted", Boolean.valueOf(_transactedButton.getSelection())); //$NON-NLS-1$
        } else if (control.equals(_batchEnabledCheckbox)) {
            if (!_batchEnabledCheckbox.getSelection()) {
                removeInboundInteractionBatchCommitFeature();
            } else {
                updateInboundInteractionBatchCommitFeature(null,  null);
            }
        } else if (control.equals(_batchSizeText)) {
            updateInboundInteractionBatchCommitFeature("batchSize", _batchSizeText.getText().trim()); //$NON-NLS-1$
        } else if (control.equals(_batchTimeoutText)) {
            updateInboundInteractionBatchCommitFeature("batchTimeout", _batchTimeoutText.getText().trim()); //$NON-NLS-1$
        } else {
            super.handleModify(control);
        }
        validate();
        setHasChanged(false);
        setDidSomething(true);
    }

    protected void handleUndo(Control control) {
        if (_binding != null) {
            if (control.equals(_batchSizeText)) {
                setTextValue(_batchSizeText, PropTypeUtil.getPropValueString(this._binding.getInboundInteraction().getBatchCommit().getBatchSize()));
            } else if (control.equals(_batchTimeoutText)) {
                setTextValue(_batchTimeoutText, PropTypeUtil.getPropValueString(this._binding.getInboundInteraction().getBatchCommit().getBatchTimeout()));
            } else {
                super.handleUndo(control);
            }
        }
    }

}
