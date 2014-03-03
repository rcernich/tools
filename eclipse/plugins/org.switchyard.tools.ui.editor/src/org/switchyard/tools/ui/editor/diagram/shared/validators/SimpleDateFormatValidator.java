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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;

/**
 * @author bfitzpat
 *
 */
public class SimpleDateFormatValidator extends AbstractBindingValidator {

    /**
     * Constructor.
     * @param message Validation message
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     */
    public SimpleDateFormatValidator(String message, ControlDecoration controlDecoration, 
            AbstractSYBindingComposite composite) {
        super(message, controlDecoration, composite);
    }

    /**
     * Constructor.
     * @param controlDecoration Decorator to update
     * @param composite Binding composite to keep validated
     */
    public SimpleDateFormatValidator(ControlDecoration controlDecoration, 
            AbstractSYBindingComposite composite) {
        super(controlDecoration, composite);
    }

    /**
     * Constructor.
     * @param message Validation message
     */
    public SimpleDateFormatValidator(String message) {
        super(message);
    }

    /**
     * Constructor.
     */
    public SimpleDateFormatValidator() {
        super();
    }

    @Override
    public IStatus validate(Object value) {
        if (value instanceof String) {
            String s = (String) value;
            if (s.trim().isEmpty()) {
                // we don't have to worry about an empty string
                return clear();
            }
            // also need to test for regex
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$
            try {
                sdf.parse(s.trim());
                return clear();
            } catch (ParseException pe) {
                return error();
            }
        } else {
            throw new RuntimeException(
                    Messages.SimpleDateFormatValidator_Only_Call_for_String_Data);
        }
    }
}

