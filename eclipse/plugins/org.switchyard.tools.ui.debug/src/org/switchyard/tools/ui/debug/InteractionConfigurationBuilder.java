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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.switchyard.tools.ui.debug.IInteractionConfiguration.TriggerType;

/**
 * InteractionConfigurationBuilder
 * <p/>
 * Utility for creating/updating breakpoint service interaction configurations.
 */
public class InteractionConfigurationBuilder {

    /** Attributes key for trigger type. */
    public static final String TRIGGER_KEY = "switchYardServiceBreakpoint.trigger";
    /** Attributes key for consumer name. */
    public static final String CONSUMER_NAME_KEY = "switchYardServiceBreakpoint.consumer.name";
    /** Attributes key for consumer model URI. */
    public static final String CONSUMER_URI_KEY = "switchYardServiceBreakpoint.consumer.uri";
    /** Attributes key for provider name. */
    public static final String PROVIDER_NAME_KEY = "switchYardServiceBreakpoint.provider.name";
    /** Attributes key for provider model URI. */
    public static final String PROVIDER_URI_KEY = "switchYardServiceBreakpoint.provider.uri";

    private BasicBreakpointConfiguration _config;

    private InteractionConfigurationBuilder() {
        this(new BasicBreakpointConfiguration());
    }

    protected InteractionConfigurationBuilder(BasicBreakpointConfiguration config) {
        _config = config;
    }

    /**
     * @param triggers the trigger set to configure
     * @return this
     */
    public InteractionConfigurationBuilder triggers(Set<TriggerType> triggers) {
        _config._triggers = triggers;
        return this;
    }

    /**
     * @param name the name of the consumer
     * @param uri the model uri for the consumer
     * @return this
     */
    public InteractionConfigurationBuilder consumer(QName name, String uri) {
        _config._consumerName = name;
        _config._consumerUri = uri;
        return this;
    }

    /**
     * @param name the name of the provider
     * @param uri the model uri for the provider
     * @return this
     */
    public InteractionConfigurationBuilder provider(QName name, String uri) {
        _config._providerName = name;
        _config._providerUri = uri;
        return this;
    }

    /**
     * Updates fields in the configuration using details from the attributes.
     * 
     * @param attributes attributes representing the configuration
     * @return this
     */
    public InteractionConfigurationBuilder updateFrom(Map<String, Object> attributes) {
        return triggers(TriggerType.fromString(getString(attributes, TRIGGER_KEY, null))).consumer(
                getQName(attributes, CONSUMER_NAME_KEY, null), getString(attributes, CONSUMER_URI_KEY, null)).provider(
                getQName(attributes, PROVIDER_NAME_KEY, null), getString(attributes, PROVIDER_URI_KEY, null));
    }

    /**
     * @return the configuration
     */
    public IInteractionConfiguration build() {
        return _config;
    }

    /**
     * @return a new builder
     */
    public static InteractionConfigurationBuilder create() {
        return new InteractionConfigurationBuilder();
    }

    /**
     * @param config an existing configuration
     * @return a new builder initialized using the specified configuration
     */
    public static InteractionConfigurationBuilder createFrom(IInteractionConfiguration config) {
        if (config == null) {
            return create();
        }
        return create().updateFrom(config);
    }

    /**
     * Update this builder using the specified configuration.
     * 
     * @param config an existing configuration
     * @return the updated builder
     */
    protected InteractionConfigurationBuilder updateFrom(IInteractionConfiguration config) {
        return create().triggers(config.getTriggers()).consumer(config.getConsumerName(), config.getConsumerUri())
                .provider(config.getProviderName(), config.getProviderUri());
    }

    /**
     * @param attributes the attributes map
     * @param key the key
     * @param defaultValue the default value
     * @return the value
     */
    protected QName getQName(Map<String, Object> attributes, String key, QName defaultValue) {
        if (attributes.containsKey(key)) {
            Object value = attributes.get(key);
            if (value instanceof String) {
                return QName.valueOf((String) value);
            }
        }
        return defaultValue;
    }

    /**
     * @param attributes the attributes map
     * @param key the key
     * @param defaultValue the default value
     * @return the value
     */
    protected String getString(Map<String, Object> attributes, String key, String defaultValue) {
        if (attributes.containsKey(key)) {
            Object value = attributes.get(key);
            if (value instanceof String) {
                return (String) value;
            }
        }
        return defaultValue;
    }

    /**
     * @param attributes the attributes map
     * @param key the key
     * @param defaultValue the default value
     * @return the value
     */
    protected boolean getBoolean(Map<String, Object> attributes, String key, boolean defaultValue) {
        if (attributes.containsKey(key)) {
            Object value = attributes.get(key);
            if (value instanceof Boolean) {
                return ((Boolean) value).booleanValue();
            }
        }
        return defaultValue;
    }

    protected static class BasicBreakpointConfiguration implements IInteractionConfiguration {
        private Set<TriggerType> _triggers;
        private QName _providerName;
        private String _providerUri;
        private QName _consumerName;
        private String _consumerUri;

        /**
         * Get the triggers.
         * 
         * @return the triggers.
         */
        public Set<TriggerType> getTriggers() {
            return _triggers;
        }

        /**
         * Get the providerName.
         * 
         * @return the providerName.
         */
        public QName getProviderName() {
            return _providerName;
        }

        /**
         * Get the providerUri.
         * 
         * @return the providerUri.
         */
        public String getProviderUri() {
            return _providerUri;
        }

        /**
         * Get the consumerName.
         * 
         * @return the consumerName.
         */
        public QName getConsumerName() {
            return _consumerName;
        }

        /**
         * Get the consumerUri.
         * 
         * @return the consumerUri.
         */
        public String getConsumerUri() {
            return _consumerUri;
        }

        @Override
        public Map<String, Object> toAttributesMap() {
            final Map<String, Object> attributes = new HashMap<String, Object>();
            if (_consumerName != null) {
                attributes.put(CONSUMER_NAME_KEY, _consumerName.toString());
            }
            if (_consumerUri != null) {
                attributes.put(CONSUMER_URI_KEY, _consumerUri);
            }
            if (_providerName != null) {
                attributes.put(PROVIDER_NAME_KEY, _providerName.toString());
            }
            if (_providerUri != null) {
                attributes.put(PROVIDER_URI_KEY, _providerUri);
            }
            if (_triggers != null && _triggers.size() > 0) {
                attributes.put(TRIGGER_KEY, TriggerType.toString(_triggers.toArray(new TriggerType[_triggers.size()])));
            }
            return attributes;
        }

    }
}
