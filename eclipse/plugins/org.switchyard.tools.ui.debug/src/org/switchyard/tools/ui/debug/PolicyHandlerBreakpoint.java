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
package org.switchyard.tools.ui.debug;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.debug.core.IJavaMethodBreakpoint;

/**
 * PolicyHandlerBreakpoint
 * <p/>
 * Breakpoint for SwitchYard GENERIC_POLICY.
 */
public class PolicyHandlerBreakpoint extends ServiceHandlerBreakpoint {

    private static final String MARKER_ID = SwitchYardDebugUtil.BASE_BREAKPOINT_MARKER_ID;
    private static final String TYPE = "org.switchyard.handlers.PolicyHandler";
    private static final String EXCHANGE_VARIABLE = "exchange";

    /**
     * Create a new PolicyHandlerBreakpoint.
     */
    public PolicyHandlerBreakpoint() {
        super(TYPE, EXCHANGE_VARIABLE);
    }

    /**
     * Create a new PolicyHandlerBreakpoint.
     * 
     * @param resource the associated resource
     * @param configuration the configuration for this breakpoint
     * @param register true to register this breakpoint with the debug
     *            subsystem.
     * @throws CoreException if something goes awry
     */
    public PolicyHandlerBreakpoint(final IResource resource, final IInteractionConfiguration configuration,
            final boolean register) throws CoreException {
        super(resource, configuration, register, TYPE, EXCHANGE_VARIABLE, MARKER_ID);
    }

    @Override
    protected String createMessageCondition() {
        final IInteractionConfiguration config = getInteractionConfiguration();
        if (config == null) {
            return null;
        }
        final IConditionBuilder builder = new SwitchYardExchangeConditionBuilder(EXCHANGE_VARIABLE);
        final String providerCondition = builder.provider(config.getProviderName());
        final String consumerCondition = builder.consumer(config.getConsumerName());
        final StringBuffer buffer = new StringBuffer();
        if (providerCondition != null) {
            buffer.append(providerCondition);
        }
        if (consumerCondition != null) {
            if (buffer.length() > 0) {
                buffer.append("\n        && ");
            }
            buffer.append(consumerCondition);
        }
        if (buffer.length() == 0) {
            return null;
        }
        buffer.insert(0, '(');
        buffer.append(")");
        return buffer.toString();
    }

    @Override
    protected String createFaultCondition() {
        return null;
    }

    @Override
    protected IJavaMethodBreakpoint createHandleFaultDelegate(Map<String, Object> attributes) throws CoreException {
        return null;
    }

}
