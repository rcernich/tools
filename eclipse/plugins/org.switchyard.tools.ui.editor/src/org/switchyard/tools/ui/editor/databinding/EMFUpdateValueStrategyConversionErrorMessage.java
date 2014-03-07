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
 ******************************************************************************/
package org.switchyard.tools.ui.editor.databinding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;

/**
 * We provide a more usable error message should conversion fail.
 */
public class EMFUpdateValueStrategyConversionErrorMessage extends EMFUpdateValueStrategy {
    private final String _message;

    /**
     * Create a new EMFUpdateValueStrategyConversionErrorMessage.
     * 
     * @param message message to display for conversion errors
     * @param updatePolicy update policy
     */
    public EMFUpdateValueStrategyConversionErrorMessage(final String message, int updatePolicy) {
        super(updatePolicy);
        _message = message;
    }

    /**
     * Create a new EMFUpdateValueStrategyNullForEmptyString.
     * 
     * @param message message to display for conversion errors
     */
    public EMFUpdateValueStrategyConversionErrorMessage(final String message) {
        _message = message;
    }

    /**
     * Create a new EMFUpdateValueStrategyNullForEmptyString.
     */
    public EMFUpdateValueStrategyConversionErrorMessage() {
        this(null);
    }

    @Override
    protected IConverter createConverter(Object fromType, Object toType) {
        return new ConverterWithErrorMessage(super.createConverter(fromType, toType));
    }

    private final class ConverterWithErrorMessage implements IConverter {
        private final IConverter _delegate;

        private ConverterWithErrorMessage(final IConverter delegate) {
            _delegate = delegate;
        }

        @Override
        public Object convert(Object fromObject) {
            try {
                return _delegate.convert(fromObject);
            } catch (RuntimeException e) {
                if (_message != null) {
                    throw new RuntimeException(_message, e);
                }
                throw e;
            }
        }

        @Override
        public Object getFromType() {
            return _delegate.getFromType();
        }

        @Override
        public Object getToType() {
            return _delegate.getToType();
        }
    }
}
