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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;

/**
 * @author bfitzpat
 *
 */
public class StringEmptyValidator extends AbstractBindingValidator {

    /**
     * Constructor.
     * @param message Validation message
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     */
    public StringEmptyValidator(String message,
            ControlDecoration controlDecoration, AbstractSYBindingComposite composite) {
        super(message, controlDecoration, composite);
    }
    
    /**
     * Constructor.
     * @param message Validation message
     */
    public StringEmptyValidator(String message) {
        super(message);
    }

    /**
     * Constructor.
     */
    public StringEmptyValidator() {
        super();
    }

    @Override
    public IStatus validate(Object value) {
        if (value instanceof String) {
            String s = (String) value;
            if (!s.trim().isEmpty()) {
                return clear();
            } else {
                return error();
            }
        } else {
            throw new RuntimeException(
                    Messages.StringEmptyValidator_Only_Call_for_String_Data);
        }
    }
}

