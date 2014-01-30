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
package org.switchyard.tools.ui.debug.structure;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaObject;
import org.eclipse.jdt.debug.core.IJavaValue;

/**
 * SwitchYardExchangeVariable
 * <p/>
 * A variable representing a SwitchYard exchange.
 */
public class SwitchYardExchangeVariable extends JavaInterfaceVariable {

    protected static final String TYPE = "org.switchyard.Exchange";

    /**
     * Create a new SwitchYardExchangeVariable.
     * 
     * @param underlyingObject the underlying Exchange object.
     */
    public SwitchYardExchangeVariable(IJavaObject underlyingObject) {
        super(underlyingObject, "Exchange", null, null, TYPE);
    }

    @Override
    protected IValue wrapJavaValue(IJavaValue actualValue) {
        return new SwitchYardExchangeValue((IJavaObject) actualValue);
    }

    private static final class SwitchYardExchangeValue extends JavaInterfaceValue {

        private IVariable[] _variables;

        private SwitchYardExchangeValue(IJavaObject delegate) {
            super(delegate);
        }

        @Override
        public IVariable[] getVariables() throws DebugException {
            if (_variables == null) {
                final List<IVariable> variables = new ArrayList<IVariable>();
                variables.add(new SwitchYardMessageVariable((IJavaObject) getDelegate()));
                variables.add(new SimpleInterfaceVariable((IJavaObject) getDelegate(), "State",
                        "org.switchyard.ExchangeState", true));
                variables.add(new SimpleInterfaceVariable((IJavaObject) getDelegate(), "Phase",
                        "org.switchyard.ExchangePhase", true));
                variables.add(new SwitchYardExchangeInteractionGroup((IJavaObject) getDelegate()));
                variables.add(new SwitchYardTransactionSettingsGroup((IJavaObject) getDelegate()));
                variables.add(new SwitchYardSecurityContextVariable((IJavaObject) getDelegate()));
                variables.add(new SimpleInterfaceVariable((IJavaObject) getDelegate(), "Properties",
                        "getContext().getProperties(org.switchyard.Scope.EXCHANGE)", null,
                        "java.util.Set<org.switchyard.Property>"));
                _variables = variables.toArray(new IVariable[variables.size()]);
            }
            return _variables;
        }
    }
}
