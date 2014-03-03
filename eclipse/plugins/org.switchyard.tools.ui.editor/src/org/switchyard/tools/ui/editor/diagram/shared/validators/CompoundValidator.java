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
 * Based on code from Tillmann Seidel's post at EclipseSource.
 * http://eclipsesource.com/blogs/2012/08/22/improving-reuse-of-jface-data-binding-validators/
 *
 */
public class CompoundValidator extends AbstractBindingValidator {
    private final IValidator[] _validators;
    
    /**
     * Constructor.
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     * @param validators list of validators
     */
    public CompoundValidator(ControlDecoration controlDecoration, 
            AbstractSYBindingComposite composite, final IValidator... validators) {
        super(controlDecoration, composite);
        this._validators = validators;
    }
 
    /**
     * @param value Value to be validated
     * @return IStatus of highest severity
     */
    public IStatus validate(final Object value) {
        IStatus result = ValidationStatus.ok();
        for (IValidator validator : _validators) {
            IStatus status = validator.validate(value);
 
            if (status.getSeverity() > result.getSeverity()) {
                result = status;
            }
        }
        if (result == ValidationStatus.ok()) {
            getDecoration().hide();
            getComposite().setErrorMessage(null);
            return ValidationStatus.ok();
        }
        getDecoration().show();
        getDecoration().setDescriptionText(result.getMessage());
        getComposite().setErrorMessage(result.getMessage());
        return result;
    }
}
