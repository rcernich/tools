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

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;

/**
 * Configuration for SwitchYard breakpoints.
 */
public interface IInteractionConfiguration {

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
            final Set<TriggerType> triggers = new HashSet<TriggerType>();
            final StringTokenizer tokens = value == null ? null : new StringTokenizer(value);
            if (tokens == null || !tokens.hasMoreTokens()) {
                return EnumSet.copyOf(triggers);
            }
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
     * @return the name of the provider service.
     */
    public QName getProviderName();

    /**
     * @return the model URI for the provider service.
     */
    public String getProviderUri();

    /**
     * @return the name of the consumer service.
     */
    public QName getConsumerName();

    /**
     * @return the model URI for the consumer service.
     */
    public String getConsumerUri();

    /**
     * @return the triggers
     */
    public Set<TriggerType> getTriggers();

    /**
     * @return an attributes map corresponding to this configurations settings.
     */
    public Map<String, Object> toAttributesMap();
}
