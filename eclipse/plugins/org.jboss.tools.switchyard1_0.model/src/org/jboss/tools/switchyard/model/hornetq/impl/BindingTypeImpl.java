/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.hornetq.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.jboss.tools.switchyard.model.hornetq.BindingType;
import org.jboss.tools.switchyard.model.hornetq.ConfigType;
import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;
import org.jboss.tools.switchyard.model.hornetq.OperationSelectorType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jboss.tools.switchyard.model.hornetq.impl.BindingTypeImpl#getOperationSelector <em>Operation Selector</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.hornetq.impl.BindingTypeImpl#getConfig <em>Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BindingTypeImpl extends EObjectImpl implements BindingType {
	/**
	 * The cached value of the '{@link #getOperationSelector() <em>Operation Selector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationSelector()
	 * @generated
	 * @ordered
	 */
	protected OperationSelectorType operationSelector;

	/**
	 * The cached value of the '{@link #getConfig() <em>Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfig()
	 * @generated
	 * @ordered
	 */
	protected ConfigType config;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HornetQPackage.Literals.BINDING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationSelectorType getOperationSelector() {
		return operationSelector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperationSelector(OperationSelectorType newOperationSelector, NotificationChain msgs) {
		OperationSelectorType oldOperationSelector = operationSelector;
		operationSelector = newOperationSelector;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR, oldOperationSelector, newOperationSelector);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationSelector(OperationSelectorType newOperationSelector) {
		if (newOperationSelector != operationSelector) {
			NotificationChain msgs = null;
			if (operationSelector != null)
				msgs = ((InternalEObject)operationSelector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR, null, msgs);
			if (newOperationSelector != null)
				msgs = ((InternalEObject)newOperationSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR, null, msgs);
			msgs = basicSetOperationSelector(newOperationSelector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR, newOperationSelector, newOperationSelector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigType getConfig() {
		return config;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfig(ConfigType newConfig, NotificationChain msgs) {
		ConfigType oldConfig = config;
		config = newConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HornetQPackage.BINDING_TYPE__CONFIG, oldConfig, newConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfig(ConfigType newConfig) {
		if (newConfig != config) {
			NotificationChain msgs = null;
			if (config != null)
				msgs = ((InternalEObject)config).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HornetQPackage.BINDING_TYPE__CONFIG, null, msgs);
			if (newConfig != null)
				msgs = ((InternalEObject)newConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HornetQPackage.BINDING_TYPE__CONFIG, null, msgs);
			msgs = basicSetConfig(newConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HornetQPackage.BINDING_TYPE__CONFIG, newConfig, newConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR:
				return basicSetOperationSelector(null, msgs);
			case HornetQPackage.BINDING_TYPE__CONFIG:
				return basicSetConfig(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR:
				return getOperationSelector();
			case HornetQPackage.BINDING_TYPE__CONFIG:
				return getConfig();
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
			case HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR:
				setOperationSelector((OperationSelectorType)newValue);
				return;
			case HornetQPackage.BINDING_TYPE__CONFIG:
				setConfig((ConfigType)newValue);
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
			case HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR:
				setOperationSelector((OperationSelectorType)null);
				return;
			case HornetQPackage.BINDING_TYPE__CONFIG:
				setConfig((ConfigType)null);
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
			case HornetQPackage.BINDING_TYPE__OPERATION_SELECTOR:
				return operationSelector != null;
			case HornetQPackage.BINDING_TYPE__CONFIG:
				return config != null;
		}
		return super.eIsSet(featureID);
	}

} //BindingTypeImpl
