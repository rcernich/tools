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
    }

    /**
     * Values for when a service/reference breakpoint should be triggered.
     */
    public enum TriggerType {
        /** On entry. */
        ENTRY,
        /** On exit. */
        EXIT,
        /** On fault. */
        FAULT;

        /**
         * @param value a space separated list of TriggerType strings
         * @return a set of trigger types
         */
        public static Set<TriggerType> fromString(String value) {
            final StringTokenizer tokens = value == null ? null : new StringTokenizer(value);
            if (tokens == null || !tokens.hasMoreTokens()) {
                return Collections.emptySet();
            }
            final Set<TriggerType> triggers = new HashSet<TriggerType>();
            for (; tokens.hasMoreTokens();) {
                final TriggerType trigger = TriggerType.valueOf(tokens.nextToken());
                if (trigger != null) {
                    triggers.add(trigger);
                }
            }
            return EnumSet.copyOf(triggers);
        }

        /**
         * @param triggers a list of triggers
         * @return a string with a space separated list of values.
         */
        public static String toString(TriggerType... triggers) {
            final StringBuffer buffer = new StringBuffer();
            for (TriggerType trigger : triggers) {
                buffer.append(trigger.toString()).append(' ');
            }
            if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
            return buffer.toString();
        }
    }

    /**
     * Create a new ServiceBreakpoint.
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
    public static ServiceBreakpoint createServiceBreakpoint(IResource resource, QName service, String uri,
            ServiceType type, Set<TriggerType> triggers) throws CoreException {
        return new ServiceBreakpoint(resource, service, uri, type, triggers);
    }

    private SwitchYardDebugUtil() {
    }
}
