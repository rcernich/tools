/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.amqp.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.switchyard.tools.models.switchyard1_1.camel.amqp.AmqpPackage;
import org.switchyard.tools.models.switchyard1_1.camel.amqp.CamelAmqpBindingType;
import org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardFactory;
import org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Camel Amqp Binding Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getQueue <em>Queue</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getTopic <em>Topic</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getConnectionFactory <em>Connection Factory</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getClientId <em>Client Id</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getDurableSubscriptionName <em>Durable Subscription Name</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getConcurrentConsumers <em>Concurrent Consumers</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getMaxConcurrentConsumers <em>Max Concurrent Consumers</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#isDisableReplyTo <em>Disable Reply To</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#isPreserveMessageQos <em>Preserve Message Qos</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#isDeliveryPersistent <em>Delivery Persistent</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#isExplicitQosEnabled <em>Explicit Qos Enabled</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getReplyTo <em>Reply To</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getReplyToType <em>Reply To Type</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getRequestTimeout <em>Request Timeout</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getSelector <em>Selector</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getTimeToLive <em>Time To Live</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#isTransacted <em>Transacted</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getTransactionManager <em>Transaction Manager</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getAcknowledgementModeName <em>Acknowledgement Mode Name</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.amqp.impl.CamelAmqpBindingTypeImpl#getAcknowledgementMode <em>Acknowledgement Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CamelAmqpBindingTypeImpl extends BaseCamelBindingImpl implements CamelAmqpBindingType {
    /**
     * The default value of the '{@link #getQueue() <em>Queue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueue()
     * @generated
     * @ordered
     */
    protected static final String QUEUE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getQueue() <em>Queue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueue()
     * @generated
     * @ordered
     */
    protected String queue = QUEUE_EDEFAULT;
    /**
     * The default value of the '{@link #getTopic() <em>Topic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTopic()
     * @generated
     * @ordered
     */
    protected static final String TOPIC_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getTopic() <em>Topic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTopic()
     * @generated
     * @ordered
     */
    protected String topic = TOPIC_EDEFAULT;
    /**
     * The default value of the '{@link #getConnectionFactory() <em>Connection Factory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectionFactory()
     * @generated
     * @ordered
     */
    protected static final String CONNECTION_FACTORY_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getConnectionFactory() <em>Connection Factory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectionFactory()
     * @generated
     * @ordered
     */
    protected String connectionFactory = CONNECTION_FACTORY_EDEFAULT;
    /**
     * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
    protected static final String USERNAME_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
    protected String username = USERNAME_EDEFAULT;
    /**
     * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
    protected static final String PASSWORD_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
    protected String password = PASSWORD_EDEFAULT;
    /**
     * The default value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClientId()
     * @generated
     * @ordered
     */
    protected static final String CLIENT_ID_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClientId()
     * @generated
     * @ordered
     */
    protected String clientId = CLIENT_ID_EDEFAULT;
    /**
     * The default value of the '{@link #getDurableSubscriptionName() <em>Durable Subscription Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDurableSubscriptionName()
     * @generated
     * @ordered
     */
    protected static final String DURABLE_SUBSCRIPTION_NAME_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getDurableSubscriptionName() <em>Durable Subscription Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDurableSubscriptionName()
     * @generated
     * @ordered
     */
    protected String durableSubscriptionName = DURABLE_SUBSCRIPTION_NAME_EDEFAULT;
    /**
     * The default value of the '{@link #getConcurrentConsumers() <em>Concurrent Consumers</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConcurrentConsumers()
     * @generated
     * @ordered
     */
    protected static final Object CONCURRENT_CONSUMERS_EDEFAULT = SwitchyardFactory.eINSTANCE.createFromString(SwitchyardPackage.eINSTANCE.getPropInteger(), "1");
    /**
     * The cached value of the '{@link #getConcurrentConsumers() <em>Concurrent Consumers</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConcurrentConsumers()
     * @generated
     * @ordered
     */
    protected Object concurrentConsumers = CONCURRENT_CONSUMERS_EDEFAULT;
    /**
     * This is true if the Concurrent Consumers attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean concurrentConsumersESet;
    /**
     * The default value of the '{@link #getMaxConcurrentConsumers() <em>Max Concurrent Consumers</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxConcurrentConsumers()
     * @generated
     * @ordered
     */
    protected static final Object MAX_CONCURRENT_CONSUMERS_EDEFAULT = SwitchyardFactory.eINSTANCE.createFromString(SwitchyardPackage.eINSTANCE.getPropInteger(), "1");
    /**
     * The cached value of the '{@link #getMaxConcurrentConsumers() <em>Max Concurrent Consumers</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxConcurrentConsumers()
     * @generated
     * @ordered
     */
    protected Object maxConcurrentConsumers = MAX_CONCURRENT_CONSUMERS_EDEFAULT;
    /**
     * This is true if the Max Concurrent Consumers attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean maxConcurrentConsumersESet;
    /**
     * The default value of the '{@link #isDisableReplyTo() <em>Disable Reply To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDisableReplyTo()
     * @generated
     * @ordered
     */
    protected static final boolean DISABLE_REPLY_TO_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isDisableReplyTo() <em>Disable Reply To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDisableReplyTo()
     * @generated
     * @ordered
     */
    protected boolean disableReplyTo = DISABLE_REPLY_TO_EDEFAULT;
    /**
     * This is true if the Disable Reply To attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean disableReplyToESet;
    /**
     * The default value of the '{@link #isPreserveMessageQos() <em>Preserve Message Qos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPreserveMessageQos()
     * @generated
     * @ordered
     */
    protected static final boolean PRESERVE_MESSAGE_QOS_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isPreserveMessageQos() <em>Preserve Message Qos</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPreserveMessageQos()
     * @generated
     * @ordered
     */
    protected boolean preserveMessageQos = PRESERVE_MESSAGE_QOS_EDEFAULT;
    /**
     * This is true if the Preserve Message Qos attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean preserveMessageQosESet;
    /**
     * The default value of the '{@link #isDeliveryPersistent() <em>Delivery Persistent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDeliveryPersistent()
     * @generated
     * @ordered
     */
    protected static final boolean DELIVERY_PERSISTENT_EDEFAULT = true;
    /**
     * The cached value of the '{@link #isDeliveryPersistent() <em>Delivery Persistent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDeliveryPersistent()
     * @generated
     * @ordered
     */
    protected boolean deliveryPersistent = DELIVERY_PERSISTENT_EDEFAULT;
    /**
     * This is true if the Delivery Persistent attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean deliveryPersistentESet;
    /**
     * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected static final Object PRIORITY_EDEFAULT = SwitchyardFactory.eINSTANCE.createFromString(SwitchyardPackage.eINSTANCE.getPropInteger(), "4");
    /**
     * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected Object priority = PRIORITY_EDEFAULT;
    /**
     * This is true if the Priority attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean priorityESet;
    /**
     * The default value of the '{@link #isExplicitQosEnabled() <em>Explicit Qos Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExplicitQosEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean EXPLICIT_QOS_ENABLED_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isExplicitQosEnabled() <em>Explicit Qos Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isExplicitQosEnabled()
     * @generated
     * @ordered
     */
    protected boolean explicitQosEnabled = EXPLICIT_QOS_ENABLED_EDEFAULT;
    /**
     * This is true if the Explicit Qos Enabled attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean explicitQosEnabledESet;
    /**
     * The default value of the '{@link #getReplyTo() <em>Reply To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplyTo()
     * @generated
     * @ordered
     */
    protected static final String REPLY_TO_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getReplyTo() <em>Reply To</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplyTo()
     * @generated
     * @ordered
     */
    protected String replyTo = REPLY_TO_EDEFAULT;
    /**
     * The default value of the '{@link #getReplyToType() <em>Reply To Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplyToType()
     * @generated
     * @ordered
     */
    protected static final String REPLY_TO_TYPE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getReplyToType() <em>Reply To Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplyToType()
     * @generated
     * @ordered
     */
    protected String replyToType = REPLY_TO_TYPE_EDEFAULT;
    /**
     * The default value of the '{@link #getRequestTimeout() <em>Request Timeout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequestTimeout()
     * @generated
     * @ordered
     */
    protected static final Object REQUEST_TIMEOUT_EDEFAULT = SwitchyardFactory.eINSTANCE.createFromString(SwitchyardPackage.eINSTANCE.getPropInteger(), "20000");
    /**
     * The cached value of the '{@link #getRequestTimeout() <em>Request Timeout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequestTimeout()
     * @generated
     * @ordered
     */
    protected Object requestTimeout = REQUEST_TIMEOUT_EDEFAULT;
    /**
     * This is true if the Request Timeout attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean requestTimeoutESet;
    /**
     * The default value of the '{@link #getSelector() <em>Selector</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSelector()
     * @generated
     * @ordered
     */
    protected static final String SELECTOR_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getSelector() <em>Selector</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSelector()
     * @generated
     * @ordered
     */
    protected String selector = SELECTOR_EDEFAULT;
    /**
     * The default value of the '{@link #getTimeToLive() <em>Time To Live</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeToLive()
     * @generated
     * @ordered
     */
    protected static final Object TIME_TO_LIVE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getTimeToLive() <em>Time To Live</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeToLive()
     * @generated
     * @ordered
     */
    protected Object timeToLive = TIME_TO_LIVE_EDEFAULT;
    /**
     * The default value of the '{@link #isTransacted() <em>Transacted</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTransacted()
     * @generated
     * @ordered
     */
    protected static final boolean TRANSACTED_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isTransacted() <em>Transacted</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTransacted()
     * @generated
     * @ordered
     */
    protected boolean transacted = TRANSACTED_EDEFAULT;
    /**
     * This is true if the Transacted attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean transactedESet;
    /**
     * The default value of the '{@link #getTransactionManager() <em>Transaction Manager</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransactionManager()
     * @generated
     * @ordered
     */
    protected static final String TRANSACTION_MANAGER_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getTransactionManager() <em>Transaction Manager</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransactionManager()
     * @generated
     * @ordered
     */
    protected String transactionManager = TRANSACTION_MANAGER_EDEFAULT;

    /**
     * The default value of the '{@link #getAcknowledgementModeName() <em>Acknowledgement Mode Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAcknowledgementModeName()
     * @generated
     * @ordered
     */
    protected static final String ACKNOWLEDGEMENT_MODE_NAME_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getAcknowledgementModeName() <em>Acknowledgement Mode Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAcknowledgementModeName()
     * @generated
     * @ordered
     */
    protected String acknowledgementModeName = ACKNOWLEDGEMENT_MODE_NAME_EDEFAULT;
    /**
     * The default value of the '{@link #getAcknowledgementMode() <em>Acknowledgement Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAcknowledgementMode()
     * @generated
     * @ordered
     */
    protected static final Object ACKNOWLEDGEMENT_MODE_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getAcknowledgementMode() <em>Acknowledgement Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAcknowledgementMode()
     * @generated
     * @ordered
     */
    protected Object acknowledgementMode = ACKNOWLEDGEMENT_MODE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CamelAmqpBindingTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AmqpPackage.Literals.CAMEL_AMQP_BINDING_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getQueue() {
        return queue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueue(String newQueue) {
        String oldQueue = queue;
        queue = newQueue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__QUEUE, oldQueue, queue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTopic() {
        return topic;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTopic(String newTopic) {
        String oldTopic = topic;
        topic = newTopic;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TOPIC, oldTopic, topic));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnectionFactory(String newConnectionFactory) {
        String oldConnectionFactory = connectionFactory;
        connectionFactory = newConnectionFactory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONNECTION_FACTORY, oldConnectionFactory, connectionFactory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUsername(String newUsername) {
        String oldUsername = username;
        username = newUsername;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__USERNAME, oldUsername, username));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPassword(String newPassword) {
        String oldPassword = password;
        password = newPassword;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PASSWORD, oldPassword, password));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClientId(String newClientId) {
        String oldClientId = clientId;
        clientId = newClientId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CLIENT_ID, oldClientId, clientId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDurableSubscriptionName() {
        return durableSubscriptionName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDurableSubscriptionName(String newDurableSubscriptionName) {
        String oldDurableSubscriptionName = durableSubscriptionName;
        durableSubscriptionName = newDurableSubscriptionName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DURABLE_SUBSCRIPTION_NAME, oldDurableSubscriptionName, durableSubscriptionName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getConcurrentConsumers() {
        return concurrentConsumers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConcurrentConsumers(Object newConcurrentConsumers) {
        Object oldConcurrentConsumers = concurrentConsumers;
        concurrentConsumers = newConcurrentConsumers;
        boolean oldConcurrentConsumersESet = concurrentConsumersESet;
        concurrentConsumersESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS, oldConcurrentConsumers, concurrentConsumers, !oldConcurrentConsumersESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetConcurrentConsumers() {
        Object oldConcurrentConsumers = concurrentConsumers;
        boolean oldConcurrentConsumersESet = concurrentConsumersESet;
        concurrentConsumers = CONCURRENT_CONSUMERS_EDEFAULT;
        concurrentConsumersESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS, oldConcurrentConsumers, CONCURRENT_CONSUMERS_EDEFAULT, oldConcurrentConsumersESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetConcurrentConsumers() {
        return concurrentConsumersESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMaxConcurrentConsumers(Object newMaxConcurrentConsumers) {
        Object oldMaxConcurrentConsumers = maxConcurrentConsumers;
        maxConcurrentConsumers = newMaxConcurrentConsumers;
        boolean oldMaxConcurrentConsumersESet = maxConcurrentConsumersESet;
        maxConcurrentConsumersESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS, oldMaxConcurrentConsumers, maxConcurrentConsumers, !oldMaxConcurrentConsumersESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetMaxConcurrentConsumers() {
        Object oldMaxConcurrentConsumers = maxConcurrentConsumers;
        boolean oldMaxConcurrentConsumersESet = maxConcurrentConsumersESet;
        maxConcurrentConsumers = MAX_CONCURRENT_CONSUMERS_EDEFAULT;
        maxConcurrentConsumersESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS, oldMaxConcurrentConsumers, MAX_CONCURRENT_CONSUMERS_EDEFAULT, oldMaxConcurrentConsumersESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetMaxConcurrentConsumers() {
        return maxConcurrentConsumersESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDisableReplyTo() {
        return disableReplyTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisableReplyTo(boolean newDisableReplyTo) {
        boolean oldDisableReplyTo = disableReplyTo;
        disableReplyTo = newDisableReplyTo;
        boolean oldDisableReplyToESet = disableReplyToESet;
        disableReplyToESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO, oldDisableReplyTo, disableReplyTo, !oldDisableReplyToESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDisableReplyTo() {
        boolean oldDisableReplyTo = disableReplyTo;
        boolean oldDisableReplyToESet = disableReplyToESet;
        disableReplyTo = DISABLE_REPLY_TO_EDEFAULT;
        disableReplyToESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO, oldDisableReplyTo, DISABLE_REPLY_TO_EDEFAULT, oldDisableReplyToESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDisableReplyTo() {
        return disableReplyToESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isPreserveMessageQos() {
        return preserveMessageQos;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPreserveMessageQos(boolean newPreserveMessageQos) {
        boolean oldPreserveMessageQos = preserveMessageQos;
        preserveMessageQos = newPreserveMessageQos;
        boolean oldPreserveMessageQosESet = preserveMessageQosESet;
        preserveMessageQosESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS, oldPreserveMessageQos, preserveMessageQos, !oldPreserveMessageQosESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPreserveMessageQos() {
        boolean oldPreserveMessageQos = preserveMessageQos;
        boolean oldPreserveMessageQosESet = preserveMessageQosESet;
        preserveMessageQos = PRESERVE_MESSAGE_QOS_EDEFAULT;
        preserveMessageQosESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS, oldPreserveMessageQos, PRESERVE_MESSAGE_QOS_EDEFAULT, oldPreserveMessageQosESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPreserveMessageQos() {
        return preserveMessageQosESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDeliveryPersistent() {
        return deliveryPersistent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeliveryPersistent(boolean newDeliveryPersistent) {
        boolean oldDeliveryPersistent = deliveryPersistent;
        deliveryPersistent = newDeliveryPersistent;
        boolean oldDeliveryPersistentESet = deliveryPersistentESet;
        deliveryPersistentESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT, oldDeliveryPersistent, deliveryPersistent, !oldDeliveryPersistentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDeliveryPersistent() {
        boolean oldDeliveryPersistent = deliveryPersistent;
        boolean oldDeliveryPersistentESet = deliveryPersistentESet;
        deliveryPersistent = DELIVERY_PERSISTENT_EDEFAULT;
        deliveryPersistentESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT, oldDeliveryPersistent, DELIVERY_PERSISTENT_EDEFAULT, oldDeliveryPersistentESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDeliveryPersistent() {
        return deliveryPersistentESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getPriority() {
        return priority;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPriority(Object newPriority) {
        Object oldPriority = priority;
        priority = newPriority;
        boolean oldPriorityESet = priorityESet;
        priorityESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY, oldPriority, priority, !oldPriorityESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetPriority() {
        Object oldPriority = priority;
        boolean oldPriorityESet = priorityESet;
        priority = PRIORITY_EDEFAULT;
        priorityESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY, oldPriority, PRIORITY_EDEFAULT, oldPriorityESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetPriority() {
        return priorityESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isExplicitQosEnabled() {
        return explicitQosEnabled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExplicitQosEnabled(boolean newExplicitQosEnabled) {
        boolean oldExplicitQosEnabled = explicitQosEnabled;
        explicitQosEnabled = newExplicitQosEnabled;
        boolean oldExplicitQosEnabledESet = explicitQosEnabledESet;
        explicitQosEnabledESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED, oldExplicitQosEnabled, explicitQosEnabled, !oldExplicitQosEnabledESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetExplicitQosEnabled() {
        boolean oldExplicitQosEnabled = explicitQosEnabled;
        boolean oldExplicitQosEnabledESet = explicitQosEnabledESet;
        explicitQosEnabled = EXPLICIT_QOS_ENABLED_EDEFAULT;
        explicitQosEnabledESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED, oldExplicitQosEnabled, EXPLICIT_QOS_ENABLED_EDEFAULT, oldExplicitQosEnabledESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetExplicitQosEnabled() {
        return explicitQosEnabledESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReplyTo(String newReplyTo) {
        String oldReplyTo = replyTo;
        replyTo = newReplyTo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO, oldReplyTo, replyTo));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReplyToType() {
        return replyToType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReplyToType(String newReplyToType) {
        String oldReplyToType = replyToType;
        replyToType = newReplyToType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO_TYPE, oldReplyToType, replyToType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getRequestTimeout() {
        return requestTimeout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRequestTimeout(Object newRequestTimeout) {
        Object oldRequestTimeout = requestTimeout;
        requestTimeout = newRequestTimeout;
        boolean oldRequestTimeoutESet = requestTimeoutESet;
        requestTimeoutESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT, oldRequestTimeout, requestTimeout, !oldRequestTimeoutESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetRequestTimeout() {
        Object oldRequestTimeout = requestTimeout;
        boolean oldRequestTimeoutESet = requestTimeoutESet;
        requestTimeout = REQUEST_TIMEOUT_EDEFAULT;
        requestTimeoutESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT, oldRequestTimeout, REQUEST_TIMEOUT_EDEFAULT, oldRequestTimeoutESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetRequestTimeout() {
        return requestTimeoutESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSelector() {
        return selector;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelector(String newSelector) {
        String oldSelector = selector;
        selector = newSelector;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__SELECTOR, oldSelector, selector));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getTimeToLive() {
        return timeToLive;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeToLive(Object newTimeToLive) {
        Object oldTimeToLive = timeToLive;
        timeToLive = newTimeToLive;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TIME_TO_LIVE, oldTimeToLive, timeToLive));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTransacted() {
        return transacted;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransacted(boolean newTransacted) {
        boolean oldTransacted = transacted;
        transacted = newTransacted;
        boolean oldTransactedESet = transactedESet;
        transactedESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED, oldTransacted, transacted, !oldTransactedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTransacted() {
        boolean oldTransacted = transacted;
        boolean oldTransactedESet = transactedESet;
        transacted = TRANSACTED_EDEFAULT;
        transactedESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED, oldTransacted, TRANSACTED_EDEFAULT, oldTransactedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTransacted() {
        return transactedESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTransactionManager() {
        return transactionManager;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransactionManager(String newTransactionManager) {
        String oldTransactionManager = transactionManager;
        transactionManager = newTransactionManager;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTION_MANAGER, oldTransactionManager, transactionManager));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAcknowledgementModeName() {
        return acknowledgementModeName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAcknowledgementModeName(String newAcknowledgementModeName) {
        String oldAcknowledgementModeName = acknowledgementModeName;
        acknowledgementModeName = newAcknowledgementModeName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE_NAME, oldAcknowledgementModeName, acknowledgementModeName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getAcknowledgementMode() {
        return acknowledgementMode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAcknowledgementMode(Object newAcknowledgementMode) {
        Object oldAcknowledgementMode = acknowledgementMode;
        acknowledgementMode = newAcknowledgementMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE, oldAcknowledgementMode, acknowledgementMode));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__QUEUE:
                return getQueue();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TOPIC:
                return getTopic();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONNECTION_FACTORY:
                return getConnectionFactory();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__USERNAME:
                return getUsername();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PASSWORD:
                return getPassword();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CLIENT_ID:
                return getClientId();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DURABLE_SUBSCRIPTION_NAME:
                return getDurableSubscriptionName();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS:
                return getConcurrentConsumers();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS:
                return getMaxConcurrentConsumers();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO:
                return isDisableReplyTo();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS:
                return isPreserveMessageQos();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT:
                return isDeliveryPersistent();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY:
                return getPriority();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED:
                return isExplicitQosEnabled();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO:
                return getReplyTo();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO_TYPE:
                return getReplyToType();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT:
                return getRequestTimeout();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__SELECTOR:
                return getSelector();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TIME_TO_LIVE:
                return getTimeToLive();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED:
                return isTransacted();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTION_MANAGER:
                return getTransactionManager();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE_NAME:
                return getAcknowledgementModeName();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE:
                return getAcknowledgementMode();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__QUEUE:
                setQueue((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TOPIC:
                setTopic((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONNECTION_FACTORY:
                setConnectionFactory((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__USERNAME:
                setUsername((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PASSWORD:
                setPassword((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CLIENT_ID:
                setClientId((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DURABLE_SUBSCRIPTION_NAME:
                setDurableSubscriptionName((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS:
                setConcurrentConsumers(newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS:
                setMaxConcurrentConsumers(newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO:
                setDisableReplyTo((Boolean)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS:
                setPreserveMessageQos((Boolean)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT:
                setDeliveryPersistent((Boolean)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY:
                setPriority(newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED:
                setExplicitQosEnabled((Boolean)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO:
                setReplyTo((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO_TYPE:
                setReplyToType((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT:
                setRequestTimeout(newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__SELECTOR:
                setSelector((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TIME_TO_LIVE:
                setTimeToLive(newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED:
                setTransacted((Boolean)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTION_MANAGER:
                setTransactionManager((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE_NAME:
                setAcknowledgementModeName((String)newValue);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE:
                setAcknowledgementMode(newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__QUEUE:
                setQueue(QUEUE_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TOPIC:
                setTopic(TOPIC_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONNECTION_FACTORY:
                setConnectionFactory(CONNECTION_FACTORY_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__USERNAME:
                setUsername(USERNAME_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PASSWORD:
                setPassword(PASSWORD_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CLIENT_ID:
                setClientId(CLIENT_ID_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DURABLE_SUBSCRIPTION_NAME:
                setDurableSubscriptionName(DURABLE_SUBSCRIPTION_NAME_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS:
                unsetConcurrentConsumers();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS:
                unsetMaxConcurrentConsumers();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO:
                unsetDisableReplyTo();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS:
                unsetPreserveMessageQos();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT:
                unsetDeliveryPersistent();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY:
                unsetPriority();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED:
                unsetExplicitQosEnabled();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO:
                setReplyTo(REPLY_TO_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO_TYPE:
                setReplyToType(REPLY_TO_TYPE_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT:
                unsetRequestTimeout();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__SELECTOR:
                setSelector(SELECTOR_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TIME_TO_LIVE:
                setTimeToLive(TIME_TO_LIVE_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED:
                unsetTransacted();
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTION_MANAGER:
                setTransactionManager(TRANSACTION_MANAGER_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE_NAME:
                setAcknowledgementModeName(ACKNOWLEDGEMENT_MODE_NAME_EDEFAULT);
                return;
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE:
                setAcknowledgementMode(ACKNOWLEDGEMENT_MODE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__QUEUE:
                return QUEUE_EDEFAULT == null ? queue != null : !QUEUE_EDEFAULT.equals(queue);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TOPIC:
                return TOPIC_EDEFAULT == null ? topic != null : !TOPIC_EDEFAULT.equals(topic);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONNECTION_FACTORY:
                return CONNECTION_FACTORY_EDEFAULT == null ? connectionFactory != null : !CONNECTION_FACTORY_EDEFAULT.equals(connectionFactory);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__USERNAME:
                return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PASSWORD:
                return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CLIENT_ID:
                return CLIENT_ID_EDEFAULT == null ? clientId != null : !CLIENT_ID_EDEFAULT.equals(clientId);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DURABLE_SUBSCRIPTION_NAME:
                return DURABLE_SUBSCRIPTION_NAME_EDEFAULT == null ? durableSubscriptionName != null : !DURABLE_SUBSCRIPTION_NAME_EDEFAULT.equals(durableSubscriptionName);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__CONCURRENT_CONSUMERS:
                return isSetConcurrentConsumers();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__MAX_CONCURRENT_CONSUMERS:
                return isSetMaxConcurrentConsumers();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DISABLE_REPLY_TO:
                return isSetDisableReplyTo();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRESERVE_MESSAGE_QOS:
                return isSetPreserveMessageQos();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__DELIVERY_PERSISTENT:
                return isSetDeliveryPersistent();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__PRIORITY:
                return isSetPriority();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__EXPLICIT_QOS_ENABLED:
                return isSetExplicitQosEnabled();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO:
                return REPLY_TO_EDEFAULT == null ? replyTo != null : !REPLY_TO_EDEFAULT.equals(replyTo);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REPLY_TO_TYPE:
                return REPLY_TO_TYPE_EDEFAULT == null ? replyToType != null : !REPLY_TO_TYPE_EDEFAULT.equals(replyToType);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__REQUEST_TIMEOUT:
                return isSetRequestTimeout();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__SELECTOR:
                return SELECTOR_EDEFAULT == null ? selector != null : !SELECTOR_EDEFAULT.equals(selector);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TIME_TO_LIVE:
                return TIME_TO_LIVE_EDEFAULT == null ? timeToLive != null : !TIME_TO_LIVE_EDEFAULT.equals(timeToLive);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTED:
                return isSetTransacted();
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__TRANSACTION_MANAGER:
                return TRANSACTION_MANAGER_EDEFAULT == null ? transactionManager != null : !TRANSACTION_MANAGER_EDEFAULT.equals(transactionManager);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE_NAME:
                return ACKNOWLEDGEMENT_MODE_NAME_EDEFAULT == null ? acknowledgementModeName != null : !ACKNOWLEDGEMENT_MODE_NAME_EDEFAULT.equals(acknowledgementModeName);
            case AmqpPackage.CAMEL_AMQP_BINDING_TYPE__ACKNOWLEDGEMENT_MODE:
                return ACKNOWLEDGEMENT_MODE_EDEFAULT == null ? acknowledgementMode != null : !ACKNOWLEDGEMENT_MODE_EDEFAULT.equals(acknowledgementMode);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (queue: ");
        result.append(queue);
        result.append(", topic: ");
        result.append(topic);
        result.append(", connectionFactory: ");
        result.append(connectionFactory);
        result.append(", username: ");
        result.append(username);
        result.append(", password: ");
        result.append(password);
        result.append(", clientId: ");
        result.append(clientId);
        result.append(", durableSubscriptionName: ");
        result.append(durableSubscriptionName);
        result.append(", concurrentConsumers: ");
        if (concurrentConsumersESet) result.append(concurrentConsumers); else result.append("<unset>");
        result.append(", maxConcurrentConsumers: ");
        if (maxConcurrentConsumersESet) result.append(maxConcurrentConsumers); else result.append("<unset>");
        result.append(", disableReplyTo: ");
        if (disableReplyToESet) result.append(disableReplyTo); else result.append("<unset>");
        result.append(", preserveMessageQos: ");
        if (preserveMessageQosESet) result.append(preserveMessageQos); else result.append("<unset>");
        result.append(", deliveryPersistent: ");
        if (deliveryPersistentESet) result.append(deliveryPersistent); else result.append("<unset>");
        result.append(", priority: ");
        if (priorityESet) result.append(priority); else result.append("<unset>");
        result.append(", explicitQosEnabled: ");
        if (explicitQosEnabledESet) result.append(explicitQosEnabled); else result.append("<unset>");
        result.append(", replyTo: ");
        result.append(replyTo);
        result.append(", replyToType: ");
        result.append(replyToType);
        result.append(", requestTimeout: ");
        if (requestTimeoutESet) result.append(requestTimeout); else result.append("<unset>");
        result.append(", selector: ");
        result.append(selector);
        result.append(", timeToLive: ");
        result.append(timeToLive);
        result.append(", transacted: ");
        if (transactedESet) result.append(transacted); else result.append("<unset>");
        result.append(", transactionManager: ");
        result.append(transactionManager);
        result.append(", acknowledgementModeName: ");
        result.append(acknowledgementModeName);
        result.append(", acknowledgementMode: ");
        result.append(acknowledgementMode);
        result.append(')');
        return result.toString();
    }

} //CamelAmqpBindingTypeImpl
