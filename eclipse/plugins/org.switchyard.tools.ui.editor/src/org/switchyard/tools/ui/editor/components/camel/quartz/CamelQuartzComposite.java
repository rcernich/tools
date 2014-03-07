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
package org.switchyard.tools.ui.editor.components.camel.quartz;

import java.util.Arrays;
import java.util.TimeZone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.OperationSelectorType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.switchyard.tools.models.switchyard1_0.camel.quartz.CamelQuartzBindingType;
import org.switchyard.tools.models.switchyard1_0.camel.quartz.QuartzPackage;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardOperationSelectorType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardFactory;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.databinding.EMFUpdateValueStrategyNullForEmptyString;
import org.switchyard.tools.ui.editor.databinding.SWTValueUpdater;
import org.switchyard.tools.ui.editor.databinding.StringEmptyValidator;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorUtil;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;

/**
 * @author bfitzpat
 * 
 */
public class CamelQuartzComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelQuartzBindingType _binding = null;
    private Text _nameText;
    private boolean _defaultedName = false;
    private Text _cronText;
    private Text _startTimeText;
    private Text _endTimeText;
    private ComboViewer _timezoneViewer;
    private OperationSelectorComposite _opSelectorComposite;
    private WritableValue _bindingValue;

    @Override
    public String getTitle() {
        return Messages.title_schedulingBindingDetails;
    }

    @Override
    public String getDescription() {
        return Messages.description_schedulingBindingDetails;
    }

    @Override
    public void setBinding(Binding impl) {
        super.setBinding(impl);
        if (impl instanceof CamelQuartzBindingType) {
            _binding = (CamelQuartzBindingType) impl;
            if (_bindingValue == null) {
                bindControls();
            }
            setInUpdate(true);

            _bindingValue.setValue(_binding);

            // refresh the operation selector control
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed() && getTargetObject() != null) {
                _opSelectorComposite.setTargetObject(getTargetObject());
            }

            OperationSelectorType opSelector = OperationSelectorUtil.getFirstOperationSelector(this._binding);
            _opSelectorComposite.setBinding(this._binding);
            _opSelectorComposite.setOperation((SwitchYardOperationSelectorType) opSelector);

            setInUpdate(false);

            if (!_defaultedName
                    && this._binding != null
                    && this._binding.getCamelBindingName() == null
                    || (this._binding.getCamelBindingName() != null && this._binding.getCamelBindingName().trim()
                            .isEmpty())) {
                if (getTargetObject() != null && getTargetObject() instanceof Contract) {
                    Contract contract = (Contract) getTargetObject();
                    if (contract.eContainer() != null
                            && contract.eContainer() instanceof org.eclipse.soa.sca.sca1_1.model.sca.Composite) {
                        org.eclipse.soa.sca.sca1_1.model.sca.Composite composite = (org.eclipse.soa.sca.sca1_1.model.sca.Composite) contract
                                .eContainer();
                        _nameText.setText(composite.getName());
                    }
                }
            }
            _defaultedName = true;

            validate();
        } else {
            this._binding = null;
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
    public void createContents(Composite parent, int style) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());

        getSchedulerTabControl(_panel);
    }

    private Control getSchedulerTabControl(Composite tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        composite.setLayout(gl);

        _nameText = createLabelAndText(composite, Messages.label_nameStar);
        _cronText = createLabelAndText(composite, Messages.label_cronStar);
        _startTimeText = createLabelAndText(composite, Messages.label_startTime);
        _endTimeText = createLabelAndText(composite, Messages.label_endTime);

        new Label(composite, SWT.NONE).setText(Messages.label_timeZone);
        _timezoneViewer = new ComboViewer(composite);
        _timezoneViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        _timezoneViewer.setContentProvider(ArrayContentProvider.getInstance());
        _timezoneViewer.setLabelProvider(new LabelProvider());
        String[] timezones = TimeZone.getAvailableIDs();
        Arrays.sort(timezones);
        _timezoneViewer.setInput(timezones);

        _opSelectorComposite = new OperationSelectorComposite(composite, SWT.NONE);
        _opSelectorComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
        _opSelectorComposite.setLayout(new GridLayout(2, false));
        _opSelectorComposite.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                handleModify(_opSelectorComposite);
            }
        });

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
                setFeatureValue(_binding,
                        "operationSelector", SwitchyardFactory.eINSTANCE.createStaticOperationSelectorType()); //$NON-NLS-1$
            }
        }
    }

    protected void handleModify(final Control control) {
        // at this point, this is the only control we can't do with strict
        // databinding
        if (control.equals(_opSelectorComposite)) {
            int opType = _opSelectorComposite.getSelectedOperationSelectorType();
            updateOperationSelectorFeature(opType, _opSelectorComposite.getSelectedOperationSelectorValue());
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

    private void bindControls() {
        final EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(_binding);
        final Realm realm = SWTObservables.getRealm(_nameText.getDisplay());
        final DataBindingContext context = getDataBindingContext();

        _bindingValue = new WritableValue(realm, null, CamelQuartzBindingType.class);

        org.eclipse.core.databinding.Binding binding = context.bindValue(
                SWTObservables.observeText(_nameText, new int[] {SWT.Modify }),
                observeDetailValue(domain, _bindingValue,
                        QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__CAMEL_BINDING_NAME),
                new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT)
                        .setAfterConvertValidator(new StringEmptyValidator(
                                Messages.CamelQuartzComposite_Validation_Name_Empty)), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_cronText, new int[] {SWT.Modify }),
                        observeDetailValue(domain, _bindingValue,
                                QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__CRON),
                        new EMFUpdateValueStrategyNullForEmptyString(null, UpdateValueStrategy.POLICY_CONVERT)
                                .setAfterConvertValidator(new StringEmptyValidator(
                                        Messages.CamelQuartzComposite_Validation_CRON_Empty)), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context
                .bindValue(
                        SWTObservables.observeText(_startTimeText, new int[] {SWT.Modify }),
                        observeDetailValue(domain, _bindingValue,
                                QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME),
                        new EMFUpdateValueStrategyNullForEmptyString(
                                Messages.CamelQuartzComposite_Validation_Start_Time_Format,
                                UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context.bindValue(
                SWTObservables.observeText(_endTimeText, new int[] {SWT.Modify }),
                observeDetailValue(domain, _bindingValue,
                        QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME),
                new EMFUpdateValueStrategyNullForEmptyString(Messages.CamelQuartzComposite_Validation_End_Time_Format,
                        UpdateValueStrategy.POLICY_CONVERT), null);
        ControlDecorationSupport.create(SWTValueUpdater.attach(binding), SWT.TOP | SWT.LEFT);

        binding = context.bindValue(
                ViewersObservables.observeSingleSelection(_timezoneViewer),
                observeDetailValue(domain, _bindingValue,
                        QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_TIME_ZONE));
        ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

        getObservablesManager().addObservablesFromContext(context, true, true);
    }

    @Override
    protected void handleChange(Control control) {
        setHasChanged(true);
        if (getErrorMessage() == null) {
            handleModify(control);
        }
        fireChangedEvent(this);
    }
}
