/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
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

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.switchyard.tools.ui.debug.IInteractionConfiguration.TriggerType;

/**
 * SwitchYardDebugUtil
 * <p/>
 * Utility methods for SwitchYard debug support.
 */
public final class SwitchYardDebugUtil {

    /** The model ID for SwitchYard breakpoints. */
    public static final String MODEL_IDENTIFIER = "org.switchyard.tools.ui.debug";

    /** The id for the base SwitchYard breakpoint markers. */
    public static final String BASE_BREAKPOINT_MARKER_ID = "org.switchyard.tools.ui.debug.switchYardBreakpointMarker";
    /** The id for service (consumer and provider) breakpoint markers. */
    public static final String SERVICE_BREAKPIONT_MARKER_ID = "org.switchyard.tools.ui.debug.switchYardServiceBreakpointMarker";

    /**
     * The type of service breakpoint.
     */
    public enum ServiceType {
        /** Provider breakpoint. */
        PROVIDER,
        /** Consumer breakpoint. */
        CONSUMER;

        /**
         * The service type based on the contract type.
         * 
         * @param contract the contract
         * @return the service type, may be null.
         */
        public static ServiceType fromContract(Contract contract) {
            if (contract instanceof Service || contract instanceof ComponentReference) {
                return ServiceType.CONSUMER;
            } else if (contract instanceof Reference || contract instanceof ComponentService) {
                return ServiceType.PROVIDER;
            }
            return null;
        }
    }

    /**
     * Values for when a service/reference breakpoint should be triggered.
     */
    public enum AspectType {
        /**
         * Break on consumer invocations (InterceptProcessor._target==consumer).
         */
        CONSUMER_INVOCATION,
        /**
         * Break on provider invocations (InterceptProcessor._target==provider).
         */
        PRODUCER_INVOCATION,
        /**
         * Break on transformer invocations
         * (TransformerSequence.applySequence()).
         */
        TRANSFORMATION,
        /**
         * Break on validator invocations
         * (ValidationHandler.handleMessage()/handleFault()).
         */
        VALIDATION,
        /**
         * Break on security context setup/cleanup
         * (SecurityHandler.handleMessage()/handleFault()).
         */
        SECURITY,
        /**
         * Break on transaction setup/cleanup
         * (TransactionHandler.handleMessage()/handleFault()).
         */
        TRANSACTION;

        /**
         * @param value a space separated list of AspectType strings
         * @return a set of aspect types
         */
        public static Set<AspectType> fromString(String value) {
            final StringTokenizer tokens = value == null ? null : new StringTokenizer(value);
            if (tokens == null || !tokens.hasMoreTokens()) {
                return Collections.emptySet();
            }
            final Set<AspectType> aspects = new HashSet<AspectType>();
            for (; tokens.hasMoreTokens();) {
                final AspectType aspect = AspectType.valueOf(tokens.nextToken());
                if (aspect != null) {
                    aspects.add(aspect);
                }
            }
            return EnumSet.copyOf(aspects);
        }

        /**
         * @param aspects a list of aspects
         * @return a string with a space separated list of values.
         */
        public static String toString(AspectType... aspects) {
            final StringBuffer buffer = new StringBuffer();
            for (AspectType aspect : aspects) {
                buffer.append(aspect.toString()).append(' ');
            }
            if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
            return buffer.toString();
        }
    }

    /**
     * Create a breakpoint corresponding to a specific service invocation.
     * 
     * @param resource the switchyard.xml file
     * @param service the service name
     * @param uri the emf object uri (e.g.
     *            generated:/switchyard[0]/composite[0]/ServiceName)
     * @param type the service type (provider/consumer)
     * @param triggers when to trigger the breakpoint
     * @return a new service breakpoint
     * @throws CoreException if something goes awry.
     */
    public static ServiceInterceptBreakpoint createServiceBreakpoint(IResource resource, QName service, String uri,
            ServiceType type, Set<TriggerType> triggers) throws CoreException {
        final InteractionConfigurationBuilder builder = InteractionConfigurationBuilder.create();
        builder.triggers(triggers);
        if (type == ServiceType.CONSUMER) {
            builder.consumer(service, uri);
        } else {
            builder.provider(service, uri);
        }
        return new ServiceInterceptBreakpoint(resource, builder.build(), true);
    }

    private SwitchYardDebugUtil() {
    }
}
