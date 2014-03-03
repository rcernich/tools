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
package org.switchyard.tools.ui.editor.diagram.shared.validators;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;

/**
 * @author bfitzpat
 *
 */
public class AbstractBindingValidator implements IValidator {

    private String _message = null;
    private ControlDecoration _controlDecoration = null;
    private AbstractSYBindingComposite _composite = null;

    /**
     * Constructor.
     */
    public AbstractBindingValidator() {
        // empty
    }

    /**
     * Constructor.
     * @param message Validation message
     */
    public AbstractBindingValidator(String message) {
        this._message = message;
    }

    /**
     * Constructor.
     * @param message Validation message
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     */
    public AbstractBindingValidator(String message,
            ControlDecoration controlDecoration, AbstractSYBindingComposite composite) {
        this._controlDecoration = controlDecoration;
        this._composite = composite;
        this._message = message;
    }

    /**
     * Constructor.
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     */
    public AbstractBindingValidator(ControlDecoration controlDecoration, 
            AbstractSYBindingComposite composite) {
        if (controlDecoration != null && controlDecoration.getDescriptionText() != null) {
            this._message = controlDecoration.getDescriptionText();
        }
        this._controlDecoration = controlDecoration;
        this._composite = composite;
    }

    protected ControlDecoration getDecoration() {
        return this._controlDecoration;
    }
    
    protected String getMessage() {
        if (_message == null && _controlDecoration != null 
                && _controlDecoration.getDescriptionText() != null) {
            this._message = _controlDecoration.getDescriptionText();
        }
        return this._message;
    }
    
    protected void setMessage(String msg) {
        this._message = msg;
    }
    
    protected AbstractSYBindingComposite getComposite() {
        return this._composite;
    }
    
    @Override
    public IStatus validate(Object value) {
        return ValidationStatus.ok();
    };
    
    protected IStatus clear() {
        if (getDecoration() != null && getComposite() != null) {
            getDecoration().hide();
            getComposite().setErrorMessage(null);
        }
        return ValidationStatus.ok();
    }

    protected IStatus error() {
        return error(getMessage());
    }
    
    protected IStatus error(String message) {
        if (getDecoration() != null && getComposite() != null) {
            getDecoration().show();
            getDecoration().setDescriptionText(message);
            getComposite().setErrorMessage(message);
        }
        return ValidationStatus.error(message);
    }
}

