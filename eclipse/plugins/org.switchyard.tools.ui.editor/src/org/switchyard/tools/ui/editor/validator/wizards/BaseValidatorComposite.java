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
package org.switchyard.tools.ui.editor.validator.wizards;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.switchyard.tools.models.switchyard1_1.switchyard.ValidateType;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.util.ValidatorTypesUtil;

/**
 * @author bfitzpat
 * 
 */
public class BaseValidatorComposite extends AbstractSwitchyardComposite {

    private Composite _panel;
    private ValidateType _validator;
    private Combo _nameText;
    private static ValidatorTypesUtil _typesUtil = null;
    private String _warningMessage = null;
    private WizardPage _wizPage = null;
    
    /**
     * Basic constructor.
     */
    public BaseValidatorComposite() {
        super();
        if (_typesUtil == null) {
            _typesUtil = new ValidatorTypesUtil();
        }
    }

    @Override
    protected boolean validate() {
        setErrorMessage(null);
        setWarningMessage(null);
        if (_nameText != null && !_nameText.isDisposed()) {
            String text = _nameText.getText().trim();
            if (text.isEmpty()) {
                setErrorMessage(Messages.error_emptyName);
            } else {
                try {
                    QName.valueOf(text);
                } catch (IllegalArgumentException e) {
                    setErrorMessage(Messages.error_invalidQname);
                }
            }
            if (!text.isEmpty()) {
                boolean inEdit = false;
                if (_wizPage != null && _wizPage.getWizard() instanceof AddValidatorWizard) {
                    if (((AddValidatorWizard)_wizPage.getWizard()).getValidator() != null) {
                        inEdit = true;
                    }
                }
                if (_typesUtil.validatorExists(text) && !inEdit) {
                    setWarningMessage(Messages.error_validatorNameExists);
                }
            }
        }
        return (getErrorMessage() == null);
    }

    @Override
    public void createContents(Composite parent, int style) {
        _panel = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        _panel.setLayout(gl);
        if (getRootGridData() != null) {
            _panel.setLayoutData(getRootGridData());
        }
        _nameText = createLabelAndCombo(_panel, Messages.label_name);
    }

    @Override
    public Composite getPanel() {
        return _panel;
    }

    /**
     * @param transform incoming transform type
     */
    public void setValidator(ValidateType transform) {
        setInUpdate(true);
        this._validator = transform;
        try {
            String[] types = _typesUtil.getTypesAsStringsForConfig();
            updateList(_nameText, types);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (transform != null) {
            setTextValue(_nameText, transform.getName());
        }
        setInUpdate(false);
    }

    /**
     * @return outgoing transform type
     */
    public ValidateType getValidator() {
        return this._validator;
    }

    protected void handleModify(final Control control) {
        if (control.equals(_nameText)) {
            updateFeature(_validator, "name", _nameText.getText().trim()); //$NON-NLS-1$
        }
        validate();
    }

    protected void handleUndo(Control control) {
        setInUpdate(true);
        if (_validator != null) {
            if (control.equals(_nameText)) {
                _nameText.setText(this._validator.getName());
            }
        }
        setInUpdate(false);
    }

    protected void wrapOperation(final List<ModelOperation> ops) {
        wrapOperation(this._validator, ops);
    }

    protected void updateFeature(EObject eObject, String featureId, Object value) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new BasicEObjectOperation(eObject, featureId, value));
        wrapOperation(ops);
    }

    private void updateList(Combo list, String[] types) {
        if (list != null) {
            list.removeAll();
            for (int i = 0; i < types.length; i++) {
                list.add(types[i]);
            }
        }
        // because we cleared and reloaded the list of items, we must reset the
        // listeners
        addObservableListeners(true);
    }

    protected void setWarningMessage(String warningMessage) {
        this._warningMessage = warningMessage;
    }

    protected String getWarningMessage() {
        return _warningMessage;
    }
    
    /**
     * @param page Need reference to parent wizard page
     */
    public void setWizardPage(WizardPage page) {
        this._wizPage = page;
    }
}
