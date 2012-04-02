/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.w3.ns.ws.policy.PolicyPackage
 * @generated
 */
public interface PolicyFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PolicyFactory eINSTANCE = org.w3.ns.ws.policy.impl.PolicyFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Applies To Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Applies To Type</em>'.
	 * @generated
	 */
	AppliesToType createAppliesToType();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Operator Content Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operator Content Type</em>'.
	 * @generated
	 */
	OperatorContentType createOperatorContentType();

	/**
	 * Returns a new object of class '<em>Attachment Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attachment Type</em>'.
	 * @generated
	 */
	PolicyAttachmentType createPolicyAttachmentType();

	/**
	 * Returns a new object of class '<em>Reference Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Type</em>'.
	 * @generated
	 */
	PolicyReferenceType createPolicyReferenceType();

	/**
	 * Returns a new object of class '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type</em>'.
	 * @generated
	 */
	PolicyType createPolicyType();

	/**
	 * Returns a new object of class '<em>URI Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>URI Type</em>'.
	 * @generated
	 */
	URIType createURIType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PolicyPackage getPolicyPackage();

} //PolicyFactory
