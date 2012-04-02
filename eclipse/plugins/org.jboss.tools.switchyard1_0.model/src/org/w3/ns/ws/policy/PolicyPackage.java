/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *     <h1>About the XML namespace</h1>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema document describes the XML namespace, in a form
 *       suitable for import by other schema documents.
 *      </p>
 *      <p>
 *       See <a href="http://www.w3.org/XML/1998/namespace.html">
 *       http://www.w3.org/XML/1998/namespace.html</a> and
 *       <a href="http://www.w3.org/TR/REC-xml">
 *       http://www.w3.org/TR/REC-xml</a> for information 
 *       about this namespace.
 *      </p>
 *      <p>
 *       Note that local names in this namespace are intended to be
 *       defined only by the World Wide Web Consortium or its subgroups.
 *       The names currently defined in this namespace are listed below.
 *       They should not be used with conflicting semantics by any Working
 *       Group, specification, or document instance.
 *      </p>
 *      <p>   
 *       See further below in this document for more information about <a href="#usage">how to refer to this schema document from your own
 *       XSD schema documents</a> and about <a href="#nsversioning">the
 *       namespace-versioning policy governing this schema document</a>.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *    
 *     <h3>Father (in any context at all)</h3> 
 * 
 *     <div class="bodytext">
 *      <p>
 *       denotes Jon Bosak, the chair of 
 *       the original XML Working Group.  This name is reserved by 
 *       the following decision of the W3C XML Plenary and 
 *       XML Coordination groups:
 *      </p>
 *      <blockquote>
 *        <p>
 * 	In appreciation for his vision, leadership and
 * 	dedication the W3C XML Plenary on this 10th day of
 * 	February, 2000, reserves for Jon Bosak in perpetuity
 * 	the XML name "xml:Father".
 *        </p>
 *      </blockquote>
 *     </div>
 *    </div>
 *   
 * 
 *    <div id="usage" xml:id="usage" xmlns="http://www.w3.org/1999/xhtml">
 *     <h2>
 *       <a name="usage">About this schema document</a>
 *     </h2>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema defines attributes and an attribute group suitable
 *       for use by schemas wishing to allow <code>xml:base</code>,
 *       <code>xml:lang</code>, <code>xml:space</code> or
 *       <code>xml:id</code> attributes on elements they define.
 *      </p>
 *      <p>
 *       To enable this, such a schema must import this schema for
 *       the XML namespace, e.g. as follows:
 *      </p>
 *      <pre>
 *           &lt;schema . . .&gt;
 *            . . .
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2001/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       or
 *      </p>
 *      <pre>
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2009/01/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       Subsequently, qualified reference to any of the attributes or the
 *       group defined below will have the desired effect, e.g.
 *      </p>
 *      <pre>
 *           &lt;type . . .&gt;
 *            . . .
 *            &lt;attributeGroup ref="xml:specialAttrs"/&gt;
 *      </pre>
 *      <p>
 *       will define a type which will schema-validate an instance element
 *       with any of those attributes.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div id="nsversioning" xml:id="nsversioning" xmlns="http://www.w3.org/1999/xhtml">
 *     <h2>
 *       <a name="nsversioning">Versioning policy for this schema document</a>
 *     </h2>
 *     <div class="bodytext">
 *      <p>
 *       In keeping with the XML Schema WG's standard versioning
 *       policy, this schema document will persist at
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd</a>.
 *      </p>
 *      <p>
 *       At the date of issue it can also be found at
 *       <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd</a>.
 *      </p>
 *      <p>
 *       The schema document at that URI may however change in the future,
 *       in order to remain compatible with the latest version of XML
 *       Schema itself, or with the XML namespace itself.  In other words,
 *       if the XML Schema or XML namespaces change, the version of this
 *       document at <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd 
 *       </a> 
 *       will change accordingly; the version at 
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd 
 *       </a> 
 *       will not change.
 *      </p>
 *      <p>
 *       Previous dated (and unchanging) versions of this schema 
 *       document are at:
 *      </p>
 *      <ul>
 *       <li>
 *           <a href="http://www.w3.org/2009/01/xml.xsd">
 * 	http://www.w3.org/2009/01/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2007/08/xml.xsd">
 * 	http://www.w3.org/2007/08/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2004/10/xml.xsd">
 * 	http://www.w3.org/2004/10/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2001/03/xml.xsd">
 * 	http://www.w3.org/2001/03/xml.xsd</a>
 *         </li>
 *      </ul>
 *     </div>
 *    </div>
 *   
 * <!-- end-model-doc -->
 * @see org.w3.ns.ws.policy.PolicyFactory
 * @model kind="package"
 * @generated
 */
public interface PolicyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "policy";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.w3.org/ns/ws-policy";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "policy";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PolicyPackage eINSTANCE = org.w3.ns.ws.policy.impl.PolicyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.AppliesToTypeImpl <em>Applies To Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.AppliesToTypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getAppliesToType()
	 * @generated
	 */
	int APPLIES_TO_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLIES_TO_TYPE__ANY = 0;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLIES_TO_TYPE__ANY_ATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>Applies To Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLIES_TO_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.DocumentRootImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>All</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ALL = 3;

	/**
	 * The feature id for the '<em><b>Applies To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLIES_TO = 4;

	/**
	 * The feature id for the '<em><b>Exactly One</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXACTLY_ONE = 5;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POLICY = 6;

	/**
	 * The feature id for the '<em><b>Policy Attachment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POLICY_ATTACHMENT = 7;

	/**
	 * The feature id for the '<em><b>Policy Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POLICY_REFERENCE = 8;

	/**
	 * The feature id for the '<em><b>URI</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__URI = 9;

	/**
	 * The feature id for the '<em><b>Ignorable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IGNORABLE = 10;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OPTIONAL = 11;

	/**
	 * The feature id for the '<em><b>Policy UR Is</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POLICY_UR_IS = 12;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.OperatorContentTypeImpl <em>Operator Content Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.OperatorContentTypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getOperatorContentType()
	 * @generated
	 */
	int OPERATOR_CONTENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__POLICY = 1;

	/**
	 * The feature id for the '<em><b>All</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__ALL = 2;

	/**
	 * The feature id for the '<em><b>Exactly One</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__EXACTLY_ONE = 3;

	/**
	 * The feature id for the '<em><b>Policy Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__POLICY_REFERENCE = 4;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE__ANY = 5;

	/**
	 * The number of structural features of the '<em>Operator Content Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_CONTENT_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.PolicyAttachmentTypeImpl <em>Attachment Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.PolicyAttachmentTypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyAttachmentType()
	 * @generated
	 */
	int POLICY_ATTACHMENT_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Applies To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__APPLIES_TO = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__POLICY = 2;

	/**
	 * The feature id for the '<em><b>Policy Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__POLICY_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__ANY = 4;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE__ANY_ATTRIBUTE = 5;

	/**
	 * The number of structural features of the '<em>Attachment Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ATTACHMENT_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.PolicyReferenceTypeImpl <em>Reference Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.PolicyReferenceTypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyReferenceType()
	 * @generated
	 */
	int POLICY_REFERENCE_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE__ANY = 0;

	/**
	 * The feature id for the '<em><b>Digest</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE__DIGEST = 1;

	/**
	 * The feature id for the '<em><b>Digest Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE__DIGEST_ALGORITHM = 2;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE__URI = 3;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE__ANY_ATTRIBUTE = 4;

	/**
	 * The number of structural features of the '<em>Reference Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_REFERENCE_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.PolicyTypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.PolicyTypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyType()
	 * @generated
	 */
	int POLICY_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__GROUP = OPERATOR_CONTENT_TYPE__GROUP;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__POLICY = OPERATOR_CONTENT_TYPE__POLICY;

	/**
	 * The feature id for the '<em><b>All</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__ALL = OPERATOR_CONTENT_TYPE__ALL;

	/**
	 * The feature id for the '<em><b>Exactly One</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__EXACTLY_ONE = OPERATOR_CONTENT_TYPE__EXACTLY_ONE;

	/**
	 * The feature id for the '<em><b>Policy Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__POLICY_REFERENCE = OPERATOR_CONTENT_TYPE__POLICY_REFERENCE;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__ANY = OPERATOR_CONTENT_TYPE__ANY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__NAME = OPERATOR_CONTENT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE__ANY_ATTRIBUTE = OPERATOR_CONTENT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_TYPE_FEATURE_COUNT = OPERATOR_CONTENT_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.w3.ns.ws.policy.impl.URITypeImpl <em>URI Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.w3.ns.ws.policy.impl.URITypeImpl
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getURIType()
	 * @generated
	 */
	int URI_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_TYPE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_TYPE__ANY_ATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>URI Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>UR Is Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyURIsType()
	 * @generated
	 */
	int POLICY_UR_IS_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.AppliesToType <em>Applies To Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Applies To Type</em>'.
	 * @see org.w3.ns.ws.policy.AppliesToType
	 * @generated
	 */
	EClass getAppliesToType();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.AppliesToType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.w3.ns.ws.policy.AppliesToType#getAny()
	 * @see #getAppliesToType()
	 * @generated
	 */
	EAttribute getAppliesToType_Any();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.AppliesToType#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.w3.ns.ws.policy.AppliesToType#getAnyAttribute()
	 * @see #getAppliesToType()
	 * @generated
	 */
	EAttribute getAppliesToType_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.w3.ns.ws.policy.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.w3.ns.ws.policy.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getAll <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>All</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getAll()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_All();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getAppliesTo <em>Applies To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Applies To</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getAppliesTo()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AppliesTo();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getExactlyOne <em>Exactly One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exactly One</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getExactlyOne()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExactlyOne();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Policy</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getPolicy()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Policy();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyAttachment <em>Policy Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Policy Attachment</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getPolicyAttachment()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PolicyAttachment();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyReference <em>Policy Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Policy Reference</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getPolicyReference()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PolicyReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.DocumentRoot#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>URI</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getURI()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_URI();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.DocumentRoot#isIgnorable <em>Ignorable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignorable</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#isIgnorable()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Ignorable();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.DocumentRoot#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#isOptional()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Optional();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.DocumentRoot#getPolicyURIs <em>Policy UR Is</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Policy UR Is</em>'.
	 * @see org.w3.ns.ws.policy.DocumentRoot#getPolicyURIs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_PolicyURIs();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.OperatorContentType <em>Operator Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operator Content Type</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType
	 * @generated
	 */
	EClass getOperatorContentType();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.OperatorContentType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getGroup()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EAttribute getOperatorContentType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.OperatorContentType#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Policy</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getPolicy()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EReference getOperatorContentType_Policy();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.OperatorContentType#getAll <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>All</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getAll()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EReference getOperatorContentType_All();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.OperatorContentType#getExactlyOne <em>Exactly One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exactly One</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getExactlyOne()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EReference getOperatorContentType_ExactlyOne();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.OperatorContentType#getPolicyReference <em>Policy Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Policy Reference</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getPolicyReference()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EReference getOperatorContentType_PolicyReference();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.OperatorContentType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.w3.ns.ws.policy.OperatorContentType#getAny()
	 * @see #getOperatorContentType()
	 * @generated
	 */
	EAttribute getOperatorContentType_Any();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.PolicyAttachmentType <em>Attachment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attachment Type</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType
	 * @generated
	 */
	EClass getPolicyAttachmentType();

	/**
	 * Returns the meta object for the containment reference '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getAppliesTo <em>Applies To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Applies To</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getAppliesTo()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EReference getPolicyAttachmentType_AppliesTo();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getGroup()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EAttribute getPolicyAttachmentType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Policy</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getPolicy()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EReference getPolicyAttachmentType_Policy();

	/**
	 * Returns the meta object for the containment reference list '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getPolicyReference <em>Policy Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Policy Reference</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getPolicyReference()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EReference getPolicyAttachmentType_PolicyReference();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getAny()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EAttribute getPolicyAttachmentType_Any();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyAttachmentType#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType#getAnyAttribute()
	 * @see #getPolicyAttachmentType()
	 * @generated
	 */
	EAttribute getPolicyAttachmentType_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.PolicyReferenceType <em>Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Type</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType
	 * @generated
	 */
	EClass getPolicyReferenceType();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyReferenceType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType#getAny()
	 * @see #getPolicyReferenceType()
	 * @generated
	 */
	EAttribute getPolicyReferenceType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.PolicyReferenceType#getDigest <em>Digest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Digest</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType#getDigest()
	 * @see #getPolicyReferenceType()
	 * @generated
	 */
	EAttribute getPolicyReferenceType_Digest();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.PolicyReferenceType#getDigestAlgorithm <em>Digest Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Digest Algorithm</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType#getDigestAlgorithm()
	 * @see #getPolicyReferenceType()
	 * @generated
	 */
	EAttribute getPolicyReferenceType_DigestAlgorithm();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.PolicyReferenceType#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType#getURI()
	 * @see #getPolicyReferenceType()
	 * @generated
	 */
	EAttribute getPolicyReferenceType_URI();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyReferenceType#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType#getAnyAttribute()
	 * @see #getPolicyReferenceType()
	 * @generated
	 */
	EAttribute getPolicyReferenceType_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.PolicyType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.w3.ns.ws.policy.PolicyType
	 * @generated
	 */
	EClass getPolicyType();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.PolicyType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.w3.ns.ws.policy.PolicyType#getName()
	 * @see #getPolicyType()
	 * @generated
	 */
	EAttribute getPolicyType_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.PolicyType#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.w3.ns.ws.policy.PolicyType#getAnyAttribute()
	 * @see #getPolicyType()
	 * @generated
	 */
	EAttribute getPolicyType_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.w3.ns.ws.policy.URIType <em>URI Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URI Type</em>'.
	 * @see org.w3.ns.ws.policy.URIType
	 * @generated
	 */
	EClass getURIType();

	/**
	 * Returns the meta object for the attribute '{@link org.w3.ns.ws.policy.URIType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.w3.ns.ws.policy.URIType#getValue()
	 * @see #getURIType()
	 * @generated
	 */
	EAttribute getURIType_Value();

	/**
	 * Returns the meta object for the attribute list '{@link org.w3.ns.ws.policy.URIType#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.w3.ns.ws.policy.URIType#getAnyAttribute()
	 * @see #getURIType()
	 * @generated
	 */
	EAttribute getURIType_AnyAttribute();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>UR Is Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>UR Is Type</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 *        extendedMetaData="name='PolicyURIs_._type' itemType='http://www.eclipse.org/emf/2003/XMLType#anyURI'"
	 * @generated
	 */
	EDataType getPolicyURIsType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PolicyFactory getPolicyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.AppliesToTypeImpl <em>Applies To Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.AppliesToTypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getAppliesToType()
		 * @generated
		 */
		EClass APPLIES_TO_TYPE = eINSTANCE.getAppliesToType();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLIES_TO_TYPE__ANY = eINSTANCE.getAppliesToType_Any();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLIES_TO_TYPE__ANY_ATTRIBUTE = eINSTANCE.getAppliesToType_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.DocumentRootImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>All</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ALL = eINSTANCE.getDocumentRoot_All();

		/**
		 * The meta object literal for the '<em><b>Applies To</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__APPLIES_TO = eINSTANCE.getDocumentRoot_AppliesTo();

		/**
		 * The meta object literal for the '<em><b>Exactly One</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXACTLY_ONE = eINSTANCE.getDocumentRoot_ExactlyOne();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POLICY = eINSTANCE.getDocumentRoot_Policy();

		/**
		 * The meta object literal for the '<em><b>Policy Attachment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POLICY_ATTACHMENT = eINSTANCE.getDocumentRoot_PolicyAttachment();

		/**
		 * The meta object literal for the '<em><b>Policy Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POLICY_REFERENCE = eINSTANCE.getDocumentRoot_PolicyReference();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__URI = eINSTANCE.getDocumentRoot_URI();

		/**
		 * The meta object literal for the '<em><b>Ignorable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__IGNORABLE = eINSTANCE.getDocumentRoot_Ignorable();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__OPTIONAL = eINSTANCE.getDocumentRoot_Optional();

		/**
		 * The meta object literal for the '<em><b>Policy UR Is</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__POLICY_UR_IS = eINSTANCE.getDocumentRoot_PolicyURIs();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.OperatorContentTypeImpl <em>Operator Content Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.OperatorContentTypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getOperatorContentType()
		 * @generated
		 */
		EClass OPERATOR_CONTENT_TYPE = eINSTANCE.getOperatorContentType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATOR_CONTENT_TYPE__GROUP = eINSTANCE.getOperatorContentType_Group();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR_CONTENT_TYPE__POLICY = eINSTANCE.getOperatorContentType_Policy();

		/**
		 * The meta object literal for the '<em><b>All</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR_CONTENT_TYPE__ALL = eINSTANCE.getOperatorContentType_All();

		/**
		 * The meta object literal for the '<em><b>Exactly One</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR_CONTENT_TYPE__EXACTLY_ONE = eINSTANCE.getOperatorContentType_ExactlyOne();

		/**
		 * The meta object literal for the '<em><b>Policy Reference</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR_CONTENT_TYPE__POLICY_REFERENCE = eINSTANCE.getOperatorContentType_PolicyReference();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATOR_CONTENT_TYPE__ANY = eINSTANCE.getOperatorContentType_Any();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.PolicyAttachmentTypeImpl <em>Attachment Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.PolicyAttachmentTypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyAttachmentType()
		 * @generated
		 */
		EClass POLICY_ATTACHMENT_TYPE = eINSTANCE.getPolicyAttachmentType();

		/**
		 * The meta object literal for the '<em><b>Applies To</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLICY_ATTACHMENT_TYPE__APPLIES_TO = eINSTANCE.getPolicyAttachmentType_AppliesTo();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_ATTACHMENT_TYPE__GROUP = eINSTANCE.getPolicyAttachmentType_Group();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLICY_ATTACHMENT_TYPE__POLICY = eINSTANCE.getPolicyAttachmentType_Policy();

		/**
		 * The meta object literal for the '<em><b>Policy Reference</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLICY_ATTACHMENT_TYPE__POLICY_REFERENCE = eINSTANCE.getPolicyAttachmentType_PolicyReference();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_ATTACHMENT_TYPE__ANY = eINSTANCE.getPolicyAttachmentType_Any();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_ATTACHMENT_TYPE__ANY_ATTRIBUTE = eINSTANCE.getPolicyAttachmentType_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.PolicyReferenceTypeImpl <em>Reference Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.PolicyReferenceTypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyReferenceType()
		 * @generated
		 */
		EClass POLICY_REFERENCE_TYPE = eINSTANCE.getPolicyReferenceType();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_REFERENCE_TYPE__ANY = eINSTANCE.getPolicyReferenceType_Any();

		/**
		 * The meta object literal for the '<em><b>Digest</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_REFERENCE_TYPE__DIGEST = eINSTANCE.getPolicyReferenceType_Digest();

		/**
		 * The meta object literal for the '<em><b>Digest Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_REFERENCE_TYPE__DIGEST_ALGORITHM = eINSTANCE.getPolicyReferenceType_DigestAlgorithm();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_REFERENCE_TYPE__URI = eINSTANCE.getPolicyReferenceType_URI();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_REFERENCE_TYPE__ANY_ATTRIBUTE = eINSTANCE.getPolicyReferenceType_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.PolicyTypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.PolicyTypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyType()
		 * @generated
		 */
		EClass POLICY_TYPE = eINSTANCE.getPolicyType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_TYPE__NAME = eINSTANCE.getPolicyType_Name();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_TYPE__ANY_ATTRIBUTE = eINSTANCE.getPolicyType_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.w3.ns.ws.policy.impl.URITypeImpl <em>URI Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.w3.ns.ws.policy.impl.URITypeImpl
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getURIType()
		 * @generated
		 */
		EClass URI_TYPE = eINSTANCE.getURIType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URI_TYPE__VALUE = eINSTANCE.getURIType_Value();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URI_TYPE__ANY_ATTRIBUTE = eINSTANCE.getURIType_AnyAttribute();

		/**
		 * The meta object literal for the '<em>UR Is Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.w3.ns.ws.policy.impl.PolicyPackageImpl#getPolicyURIsType()
		 * @generated
		 */
		EDataType POLICY_UR_IS_TYPE = eINSTANCE.getPolicyURIsType();

	}

} //PolicyPackage
