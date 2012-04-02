/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Applies To Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3.ns.ws.policy.AppliesToType#getAny <em>Any</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.AppliesToType#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3.ns.ws.policy.PolicyPackage#getAppliesToType()
 * @model extendedMetaData="name='AppliesTo_._type' kind='elementOnly'"
 * @generated
 */
public interface AppliesToType extends EObject {
	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see org.w3.ns.ws.policy.PolicyPackage#getAppliesToType_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" required="true" many="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':0' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

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
	 * @see org.w3.ns.ws.policy.PolicyPackage#getAppliesToType_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':1' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // AppliesToType
