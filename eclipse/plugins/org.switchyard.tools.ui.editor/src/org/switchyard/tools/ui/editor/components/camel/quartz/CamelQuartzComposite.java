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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TimeZone;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.internal.databinding.swt.SWTDelayedObservableValueDecorator;
import org.eclipse.jface.internal.databinding.swt.SWTVetoableValueDecorator;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorUtil;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.diagram.shared.validators.SimpleDateFormatValidator;
import org.switchyard.tools.ui.editor.diagram.shared.validators.StringEmptyValidator;
import org.switchyard.tools.ui.editor.util.ErrorUtils;

/**
 * @author bfitzpat
 * 
 */
@SuppressWarnings("restriction")
public class CamelQuartzComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelQuartzBindingType _binding = null;
    private Text _nameText;
    private ControlDecoration _nameDecorator;
    private boolean _defaultedName = false;
    private Text _cronText;
    private ControlDecoration _cronDecorator;
    private Text _startTimeText;
    private ControlDecoration _startTimeDecorator;
    private Text _endTimeText;
    private ControlDecoration _endTimeDecorator;
    private ComboViewer _timezoneViewer;
    private OperationSelectorComposite _opSelectorComposite;

    private TextValueChangeListener _textValueChangeListener = null;
    private ArrayList<org.eclipse.core.databinding.Binding> _bindings = null;
    private ArrayList<IObservable> _observables = null;
    

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
            this._binding = (CamelQuartzBindingType) impl;
            setInUpdate(true);
            if (this._binding.getCamelBindingName() != null) {
                _nameText.setText(this._binding.getCamelBindingName());
            } else {
                _nameText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getCron() != null) {
                _cronText.setText(this._binding.getCron());
            } else {
                _cronText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getTriggerStartTime() != null) {
                _startTimeText.setText(this._binding.getTriggerStartTime().toString());
            } else {
                _startTimeText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getTriggerEndTime() != null) {
                _endTimeText.setText(this._binding.getTriggerEndTime().toString());
            } else {
                _endTimeText.setText(""); //$NON-NLS-1$
            }
            if (this._binding.getTriggerTimeZone() != null) {
                _timezoneViewer.setSelection(new StructuredSelection(this._binding.getTriggerTimeZone().toString()));
            } else {
                _timezoneViewer.setSelection(null);
            }

            // refresh the operation selector control
            if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed() && getTargetObject() != null) {
                _opSelectorComposite.setTargetObject(getTargetObject());
            }

            OperationSelectorType opSelector = OperationSelectorUtil.getFirstOperationSelector(this._binding);
            _opSelectorComposite.setBinding(this._binding);
            _opSelectorComposite.setOperation((SwitchYardOperationSelectorType) opSelector);

            setInUpdate(false);
            addObservableListeners();

            if (!_defaultedName && this._binding != null 
                    && this._binding.getCamelBindingName() == null 
                    || (this._binding.getCamelBindingName() != null 
                    && this._binding.getCamelBindingName().trim().isEmpty())) {
                if (getTargetObject() != null && getTargetObject() instanceof Contract) {
                    Contract contract = (Contract) getTargetObject();
                    if (contract.eContainer() != null && contract.eContainer() instanceof org.eclipse.soa.sca.sca1_1.model.sca.Composite) {
                        org.eclipse.soa.sca.sca1_1.model.sca.Composite composite = (org.eclipse.soa.sca.sca1_1.model.sca.Composite) contract.eContainer();
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
        _nameDecorator = createDecorator(_nameText);
        _cronText = createLabelAndText(composite, Messages.label_cronStar);
        _cronDecorator = createDecorator(_cronText);
        _startTimeText = createLabelAndText(composite, Messages.label_startTime);
        _startTimeDecorator = createDecorator(_startTimeText);
        _endTimeText = createLabelAndText(composite, Messages.label_endTime);
        _endTimeDecorator = createDecorator(_endTimeText);
        new Label(composite,  SWT.NONE).setText(Messages.label_timeZone);
        _timezoneViewer = new ComboViewer(composite);
        _timezoneViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        _timezoneViewer.setContentProvider(new ArrayContentProvider());
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
                setFeatureValue(_binding, "operationSelector", SwitchyardFactory.eINSTANCE.createStaticOperationSelectorType()); //$NON-NLS-1$
            }
        }
    }
    
    protected void handleModify(final Control control) {
        // at this point, this is the only control we can't do with strict databinding
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
            if (control.equals(_nameText)) {
                _nameText.setText(this._binding.getCamelBindingName());
            } else if (control.equals(_cronText)) {
                _cronText.setText(this._binding.getCron());
            } else if (control.equals(_endTimeText)) {
                _endTimeText.setText(this._binding.getTriggerEndTime().toString());
            } else if (control.equals(_startTimeText)) {
                _startTimeText.setText(this._binding.getTriggerStartTime().toString());
//            } else if (control.equals(_operationSelectionCombo)) {
//                if (this._binding.getOperationSelector() != null && this._binding.getOperationSelector() instanceof StaticOperationSelectorType) {
//                    populateOperationCombo();
//                    String opName = OperationSelectorUtil.getOperationNameForStaticOperationSelector(this._binding);
//                    setTextValue(_operationSelectionCombo, opName);
//                }
            } else {
                super.handleUndo(control);
            }
        }
        setHasChanged(false);
    }

    private void addObservableListeners(Object t, ControlDecoration d, EObject object, EAttribute attr, 
            IValidator v) {
        if (_textValueChangeListener == null) {
            _textValueChangeListener = new TextValueChangeListener();
        }
        if (_bindings == null) {
            _bindings = new ArrayList<org.eclipse.core.databinding.Binding>();
        }
        if (_observables == null) {
            _observables = new ArrayList<IObservable>();
        }
        IObservableValue observableWidget = null;
        IObservableValue observableValue = null;
        
        if (t instanceof Text) {
            IWidgetValueProperty textProp = WidgetProperties.text(SWT.FocusOut | SWT.Modify);
            observableWidget = textProp.observeDelayed(400, (Text)t);
            observableValue = EMFObservables.observeValue(object, attr);
        } else if (t instanceof ComboViewer) {
            observableWidget = EMFObservables.observeValue(object, attr);
            observableValue = ViewersObservables.observeSingleSelection((ComboViewer)t);
        }
        _observables.add(observableWidget);
        _observables.add(observableValue);
        
        UpdateValueStrategy uvStrategy = null;
        if (v != null) {
            uvStrategy = new UpdateValueStrategy();
            uvStrategy.setBeforeSetValidator(v);
        }
        
        org.eclipse.core.databinding.Binding dataBinding = 
                getDataBindingContext().bindValue(observableWidget, 
                        observableValue, uvStrategy, null);
        _bindings.add(dataBinding);
        
        if (d != null) {
            addDecorator(d);
        }
        
        observableWidget.addValueChangeListener(_textValueChangeListener);        
    }

    @Override
    protected void addObservableListeners() {
        if (_bindings != null) {
            Iterator<org.eclipse.core.databinding.Binding> iter = _bindings.iterator();
            while (iter.hasNext()) {
                org.eclipse.core.databinding.Binding dataBinding = iter.next();
                getDataBindingContext().removeBinding(dataBinding);
            }
        }
        if (_observables != null && _observables.size() > 0) {
            for (int i = 0; i < _observables.size(); i++) {
                _observables.get(i).dispose();
            }
            _observables.clear();
        }

        // name cannot be empty
        addObservableListeners(_nameText, _nameDecorator, 
                _binding, QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__CAMEL_BINDING_NAME,
                new StringEmptyValidator(
                        Messages.CamelQuartzComposite_Validation_Name_Empty, 
                        _nameDecorator, this));

        // cron value cannot be empty
        addObservableListeners(_cronText, _cronDecorator, 
                _binding, QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__CRON,
                new StringEmptyValidator(
                        Messages.CamelQuartzComposite_Validation_CRON_Empty, 
                        _cronDecorator, this));

        // if start time has a value, it must have correct format
        addObservableListeners(_startTimeText, _startTimeDecorator, 
                _binding, QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_START_TIME,
                new SimpleDateFormatValidator(
                        Messages.CamelQuartzComposite_Validation_Start_Time_Format, 
                        _startTimeDecorator, this));

        // if end time has a value, it must have correct format
        addObservableListeners(_endTimeText, _endTimeDecorator, 
                _binding, QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_END_TIME,
                new SimpleDateFormatValidator(
                        Messages.CamelQuartzComposite_Validation_End_Time_Format, 
                        _endTimeDecorator, this));

        // handle the time zone without a validator
        addObservableListeners(_timezoneViewer, 
                null, _binding, 
                QuartzPackage.Literals.CAMEL_QUARTZ_BINDING_TYPE__TRIGGER_TIME_ZONE, null);
    
        // can't handle the Operation selector yet using the data binding method
    }
    
    @Override
    protected void handleChange(Control control) {
        setHasChanged(true);
        if (getErrorMessage() == null) {
            handleModify(control);
        }
        fireChangedEvent(this);
    }

    class TextValueChangeListener implements IValueChangeListener {
        @Override
        public void handleValueChange(final ValueChangeEvent e) {
            if (!inUpdate() && e.diff != null) {
                if  ((e.diff.getOldValue() == null && e.diff.getNewValue() != null)
                        || (e.diff.getOldValue() != null && e.diff.getNewValue() == null)
                        || !e.diff.getOldValue().equals(e.diff.getNewValue())) {
                    System.out.println("CamelQuartzComposite:TextValueChanged: " + e.diff); //$NON-NLS-1$
                    Control ctrl = null;
                    if (e.getSource() instanceof SWTDelayedObservableValueDecorator) {
                        SWTDelayedObservableValueDecorator decorator = (SWTDelayedObservableValueDecorator) e.getSource();
                        ctrl = (Control) decorator.getWidget();
                    } else if (e.getSource() instanceof SWTVetoableValueDecorator) {
                        SWTVetoableValueDecorator decorator = (SWTVetoableValueDecorator) e.getSource();
                        ctrl = (Control) decorator.getWidget();
                    }
                    if (ctrl != null && !ctrl.isDisposed()) {
                        handleChange(ctrl);
                        ErrorUtils.showErrorMessage(null);
                    }
                }
            }
        }
    }

}
