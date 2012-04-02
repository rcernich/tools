/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3.ns.ws.policy.PolicyType#getName <em>Name</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.PolicyType#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3.ns.ws.policy.PolicyPackage#getPolicyType()
 * @model extendedMetaData="name='Policy_._type' kind='elementOnly'"
 * @generated
 */
public interface PolicyType extends OperatorContentType {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getPolicyType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.PolicyType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any Attribute</em>' attribute list.
	 * @see org.w3.ns.ws.policy.PolicyPackage#getPolicyType_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':2' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // PolicyType
