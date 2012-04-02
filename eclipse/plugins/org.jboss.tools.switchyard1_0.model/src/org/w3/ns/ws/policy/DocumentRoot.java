/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy;

import java.util.List;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getAll <em>All</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getAppliesTo <em>Applies To</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getExactlyOne <em>Exactly One</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyAttachment <em>Policy Attachment</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyReference <em>Policy Reference</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getURI <em>URI</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#isIgnorable <em>Ignorable</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyURIs <em>Policy UR Is</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>All</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All</em>' containment reference.
	 * @see #setAll(OperatorContentType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_All()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='All' namespace='##targetNamespace'"
	 * @generated
	 */
	OperatorContentType getAll();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getAll <em>All</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All</em>' containment reference.
	 * @see #getAll()
	 * @generated
	 */
	void setAll(OperatorContentType value);

	/**
	 * Returns the value of the '<em><b>Applies To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applies To</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applies To</em>' containment reference.
	 * @see #setAppliesTo(AppliesToType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_AppliesTo()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='AppliesTo' namespace='##targetNamespace'"
	 * @generated
	 */
	AppliesToType getAppliesTo();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getAppliesTo <em>Applies To</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applies To</em>' containment reference.
	 * @see #getAppliesTo()
	 * @generated
	 */
	void setAppliesTo(AppliesToType value);

	/**
	 * Returns the value of the '<em><b>Exactly One</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exactly One</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exactly One</em>' containment reference.
	 * @see #setExactlyOne(OperatorContentType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_ExactlyOne()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExactlyOne' namespace='##targetNamespace'"
	 * @generated
	 */
	OperatorContentType getExactlyOne();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getExactlyOne <em>Exactly One</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exactly One</em>' containment reference.
	 * @see #getExactlyOne()
	 * @generated
	 */
	void setExactlyOne(OperatorContentType value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' containment reference.
	 * @see #setPolicy(PolicyType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_Policy()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Policy' namespace='##targetNamespace'"
	 * @generated
	 */
	PolicyType getPolicy();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicy <em>Policy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' containment reference.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(PolicyType value);

	/**
	 * Returns the value of the '<em><b>Policy Attachment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy Attachment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy Attachment</em>' containment reference.
	 * @see #setPolicyAttachment(PolicyAttachmentType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_PolicyAttachment()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PolicyAttachment' namespace='##targetNamespace'"
	 * @generated
	 */
	PolicyAttachmentType getPolicyAttachment();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyAttachment <em>Policy Attachment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy Attachment</em>' containment reference.
	 * @see #getPolicyAttachment()
	 * @generated
	 */
	void setPolicyAttachment(PolicyAttachmentType value);

	/**
	 * Returns the value of the '<em><b>Policy Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy Reference</em>' containment reference.
	 * @see #setPolicyReference(PolicyReferenceType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_PolicyReference()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PolicyReference' namespace='##targetNamespace'"
	 * @generated
	 */
	PolicyReferenceType getPolicyReference();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyReference <em>Policy Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy Reference</em>' containment reference.
	 * @see #getPolicyReference()
	 * @generated
	 */
	void setPolicyReference(PolicyReferenceType value);

	/**
	 * Returns the value of the '<em><b>URI</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>URI</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>URI</em>' containment reference.
	 * @see #setURI(URIType)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_URI()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='URI' namespace='##targetNamespace'"
	 * @generated
	 */
	URIType getURI();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getURI <em>URI</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>URI</em>' containment reference.
	 * @see #getURI()
	 * @generated
	 */
	void setURI(URIType value);

	/**
	 * Returns the value of the '<em><b>Ignorable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignorable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignorable</em>' attribute.
	 * @see #isSetIgnorable()
	 * @see #unsetIgnorable()
	 * @see #setIgnorable(boolean)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_Ignorable()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='Ignorable' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIgnorable();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isIgnorable <em>Ignorable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignorable</em>' attribute.
	 * @see #isSetIgnorable()
	 * @see #unsetIgnorable()
	 * @see #isIgnorable()
	 * @generated
	 */
	void setIgnorable(boolean value);

	/**
	 * Unsets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isIgnorable <em>Ignorable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIgnorable()
	 * @see #isIgnorable()
	 * @see #setIgnorable(boolean)
	 * @generated
	 */
	void unsetIgnorable();

	/**
	 * Returns whether the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isIgnorable <em>Ignorable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ignorable</em>' attribute is set.
	 * @see #unsetIgnorable()
	 * @see #isIgnorable()
	 * @see #setIgnorable(boolean)
	 * @generated
	 */
	boolean isSetIgnorable();

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #isSetOptional()
	 * @see #unsetOptional()
	 * @see #setOptional(boolean)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_Optional()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='Optional' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isSetOptional()
	 * @see #unsetOptional()
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * Unsets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOptional()
	 * @see #isOptional()
	 * @see #setOptional(boolean)
	 * @generated
	 */
	void unsetOptional();

	/**
	 * Returns whether the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#isOptional <em>Optional</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Optional</em>' attribute is set.
	 * @see #unsetOptional()
	 * @see #isOptional()
	 * @see #setOptional(boolean)
	 * @generated
	 */
	boolean isSetOptional();

	/**
	 * Returns the value of the '<em><b>Policy UR Is</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy UR Is</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy UR Is</em>' attribute.
	 * @see #setPolicyURIs(List)
	 * @see org.w3.ns.ws.policy.PolicyPackage#getDocumentRoot_PolicyURIs()
	 * @model dataType="org.w3.ns.ws.policy.PolicyURIsType" many="false"
	 *        extendedMetaData="kind='attribute' name='PolicyURIs' namespace='##targetNamespace'"
	 * @generated
	 */
	List<String> getPolicyURIs();

	/**
	 * Sets the value of the '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyURIs <em>Policy UR Is</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy UR Is</em>' attribute.
	 * @see #getPolicyURIs()
	 * @generated
	 */
	void setPolicyURIs(List<String> value);

} // DocumentRoot
