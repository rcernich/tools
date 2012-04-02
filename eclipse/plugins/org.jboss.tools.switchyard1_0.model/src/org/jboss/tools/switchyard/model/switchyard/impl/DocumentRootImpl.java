/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.switchyard.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.jboss.tools.switchyard.model.switchyard.ArtifactType;
import org.jboss.tools.switchyard.model.switchyard.ArtifactsType;
import org.jboss.tools.switchyard.model.switchyard.ContextMapperType;
import org.jboss.tools.switchyard.model.switchyard.DocumentRoot;
import org.jboss.tools.switchyard.model.switchyard.DomainType;
import org.jboss.tools.switchyard.model.switchyard.EsbInterface;
import org.jboss.tools.switchyard.model.switchyard.HandlerType;
import org.jboss.tools.switchyard.model.switchyard.HandlersType;
import org.jboss.tools.switchyard.model.switchyard.MessageComposerType;
import org.jboss.tools.switchyard.model.switchyard.PropertiesType;
import org.jboss.tools.switchyard.model.switchyard.PropertyType;
import org.jboss.tools.switchyard.model.switchyard.ResourceType;
import org.jboss.tools.switchyard.model.switchyard.SwitchYardBindingType;
import org.jboss.tools.switchyard.model.switchyard.SwitchYardType;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.TransformType;
import org.jboss.tools.switchyard.model.switchyard.TransformsType;
import org.jboss.tools.switchyard.model.switchyard.ValidateType;
import org.jboss.tools.switchyard.model.switchyard.ValidatesType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getBindingSwitchyard <em>Binding Switchyard</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getResource1 <em>Resource1</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getBindingSwitchyard1 <em>Binding Switchyard1</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getBindingSwitchyard2 <em>Binding Switchyard2</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getContextMapper <em>Context Mapper</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getHandler <em>Handler</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getInterfaceEsb <em>Interface Esb</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getMessageComposer <em>Message Composer</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getResource2 <em>Resource2</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getSwitchyard <em>Switchyard</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform <em>Transform</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransforms <em>Transforms</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getValidate <em>Validate</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getValidates <em>Validates</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform1 <em>Transform1</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform2 <em>Transform2</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform3 <em>Transform3</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform4 <em>Transform4</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getTransform5 <em>Transform5</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getValidate1 <em>Validate1</em>}</li>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.impl.DocumentRootImpl#getValidate2 <em>Validate2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected static final Object RESOURCE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBindingSwitchyard() <em>Binding Switchyard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindingSwitchyard()
	 * @generated
	 * @ordered
	 */
	protected static final Object BINDING_SWITCHYARD_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getResource1() <em>Resource1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource1()
	 * @generated
	 * @ordered
	 */
	protected static final Object RESOURCE1_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBindingSwitchyard1() <em>Binding Switchyard1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindingSwitchyard1()
	 * @generated
	 * @ordered
	 */
	protected static final Object BINDING_SWITCHYARD1_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform1() <em>Transform1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform1()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRANSFORM1_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform2() <em>Transform2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform2()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRANSFORM2_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform3() <em>Transform3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform3()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRANSFORM3_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform4() <em>Transform4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform4()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRANSFORM4_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform5() <em>Transform5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform5()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRANSFORM5_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getValidate1() <em>Validate1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidate1()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALIDATE1_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getValidate2() <em>Validate2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidate2()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALIDATE2_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SwitchyardPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, SwitchyardPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getResource() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(Object newResource) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getBindingSwitchyard() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBindingSwitchyard(Object newBindingSwitchyard) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD, newBindingSwitchyard);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getResource1() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE1, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource1(Object newResource1) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE1, newResource1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getBindingSwitchyard1() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD1, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBindingSwitchyard1(Object newBindingSwitchyard1) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD1, newBindingSwitchyard1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactType getArtifact() {
		return (ArtifactType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifact(ArtifactType newArtifact, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACT, newArtifact, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifact(ArtifactType newArtifact) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACT, newArtifact);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactsType getArtifacts() {
		return (ArtifactsType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifacts(ArtifactsType newArtifacts, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACTS, newArtifacts, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifacts(ArtifactsType newArtifacts) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__ARTIFACTS, newArtifacts);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchYardBindingType getBindingSwitchyard2() {
		return (SwitchYardBindingType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD2, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBindingSwitchyard2(SwitchYardBindingType newBindingSwitchyard2, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__BINDING_SWITCHYARD2, newBindingSwitchyard2, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextMapperType getContextMapper() {
		return (ContextMapperType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__CONTEXT_MAPPER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContextMapper(ContextMapperType newContextMapper, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__CONTEXT_MAPPER, newContextMapper, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextMapper(ContextMapperType newContextMapper) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__CONTEXT_MAPPER, newContextMapper);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainType getDomain() {
		return (DomainType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__DOMAIN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomain(DomainType newDomain, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__DOMAIN, newDomain, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomain(DomainType newDomain) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__DOMAIN, newDomain);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HandlerType getHandler() {
		return (HandlerType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHandler(HandlerType newHandler, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLER, newHandler, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandler(HandlerType newHandler) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLER, newHandler);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HandlersType getHandlers() {
		return (HandlersType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHandlers(HandlersType newHandlers, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLERS, newHandlers, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandlers(HandlersType newHandlers) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__HANDLERS, newHandlers);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EsbInterface getInterfaceEsb() {
		return (EsbInterface)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__INTERFACE_ESB, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterfaceEsb(EsbInterface newInterfaceEsb, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__INTERFACE_ESB, newInterfaceEsb, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterfaceEsb(EsbInterface newInterfaceEsb) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__INTERFACE_ESB, newInterfaceEsb);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageComposerType getMessageComposer() {
		return (MessageComposerType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__MESSAGE_COMPOSER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageComposer(MessageComposerType newMessageComposer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__MESSAGE_COMPOSER, newMessageComposer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageComposer(MessageComposerType newMessageComposer) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__MESSAGE_COMPOSER, newMessageComposer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesType getProperties() {
		return (PropertiesType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTIES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperties(PropertiesType newProperties, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTIES, newProperties, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperties(PropertiesType newProperties) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTIES, newProperties);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyType getProperty() {
		return (PropertyType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperty(PropertyType newProperty, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTY, newProperty, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(PropertyType newProperty) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__PROPERTY, newProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType getResource2() {
		return (ResourceType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE2, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource2(ResourceType newResource2, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE2, newResource2, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource2(ResourceType newResource2) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__RESOURCE2, newResource2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchYardType getSwitchyard() {
		return (SwitchYardType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__SWITCHYARD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwitchyard(SwitchYardType newSwitchyard, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__SWITCHYARD, newSwitchyard, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchyard(SwitchYardType newSwitchyard) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__SWITCHYARD, newSwitchyard);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformType getTransform() {
		return (TransformType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransform(TransformType newTransform, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM, newTransform, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformsType getTransforms() {
		return (TransformsType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORMS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransforms(TransformsType newTransforms, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORMS, newTransforms, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransforms(TransformsType newTransforms) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORMS, newTransforms);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidateType getValidate() {
		return (ValidateType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValidate(ValidateType newValidate, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE, newValidate, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatesType getValidates() {
		return (ValidatesType)getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValidates(ValidatesType newValidates, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATES, newValidates, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidates(ValidatesType newValidates) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATES, newValidates);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTransform1() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM1, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransform1(Object newTransform1) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM1, newTransform1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTransform2() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM2, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransform2(Object newTransform2) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM2, newTransform2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTransform3() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM3, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransform3(Object newTransform3) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM3, newTransform3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTransform4() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM4, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransform4(Object newTransform4) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM4, newTransform4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTransform5() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM5, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransform5(Object newTransform5) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__TRANSFORM5, newTransform5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getValidate1() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE1, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidate1(Object newValidate1) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE1, newValidate1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getValidate2() {
		return getMixed().get(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE2, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidate2(Object newValidate2) {
		((FeatureMap.Internal)getMixed()).set(SwitchyardPackage.Literals.DOCUMENT_ROOT__VALIDATE2, newValidate2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SwitchyardPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACT:
				return basicSetArtifact(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACTS:
				return basicSetArtifacts(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD2:
				return basicSetBindingSwitchyard2(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__CONTEXT_MAPPER:
				return basicSetContextMapper(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__DOMAIN:
				return basicSetDomain(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLER:
				return basicSetHandler(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLERS:
				return basicSetHandlers(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__INTERFACE_ESB:
				return basicSetInterfaceEsb(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__MESSAGE_COMPOSER:
				return basicSetMessageComposer(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTIES:
				return basicSetProperties(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTY:
				return basicSetProperty(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE2:
				return basicSetResource2(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__SWITCHYARD:
				return basicSetSwitchyard(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM:
				return basicSetTransform(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORMS:
				return basicSetTransforms(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE:
				return basicSetValidate(null, msgs);
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATES:
				return basicSetValidates(null, msgs);
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
			case SwitchyardPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE:
				return getResource();
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD:
				return getBindingSwitchyard();
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE1:
				return getResource1();
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD1:
				return getBindingSwitchyard1();
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACT:
				return getArtifact();
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACTS:
				return getArtifacts();
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD2:
				return getBindingSwitchyard2();
			case SwitchyardPackage.DOCUMENT_ROOT__CONTEXT_MAPPER:
				return getContextMapper();
			case SwitchyardPackage.DOCUMENT_ROOT__DOMAIN:
				return getDomain();
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLER:
				return getHandler();
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLERS:
				return getHandlers();
			case SwitchyardPackage.DOCUMENT_ROOT__INTERFACE_ESB:
				return getInterfaceEsb();
			case SwitchyardPackage.DOCUMENT_ROOT__MESSAGE_COMPOSER:
				return getMessageComposer();
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTIES:
				return getProperties();
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty();
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE2:
				return getResource2();
			case SwitchyardPackage.DOCUMENT_ROOT__SWITCHYARD:
				return getSwitchyard();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM:
				return getTransform();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORMS:
				return getTransforms();
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE:
				return getValidate();
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATES:
				return getValidates();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM1:
				return getTransform1();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM2:
				return getTransform2();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM3:
				return getTransform3();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM4:
				return getTransform4();
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM5:
				return getTransform5();
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE1:
				return getValidate1();
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE2:
				return getValidate2();
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
			case SwitchyardPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE:
				setResource(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD:
				setBindingSwitchyard(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE1:
				setResource1(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD1:
				setBindingSwitchyard1(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACT:
				setArtifact((ArtifactType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACTS:
				setArtifacts((ArtifactsType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__CONTEXT_MAPPER:
				setContextMapper((ContextMapperType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__DOMAIN:
				setDomain((DomainType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLER:
				setHandler((HandlerType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLERS:
				setHandlers((HandlersType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__INTERFACE_ESB:
				setInterfaceEsb((EsbInterface)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__MESSAGE_COMPOSER:
				setMessageComposer((MessageComposerType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTIES:
				setProperties((PropertiesType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((PropertyType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE2:
				setResource2((ResourceType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__SWITCHYARD:
				setSwitchyard((SwitchYardType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORMS:
				setTransforms((TransformsType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATES:
				setValidates((ValidatesType)newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM1:
				setTransform1(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM2:
				setTransform2(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM3:
				setTransform3(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM4:
				setTransform4(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM5:
				setTransform5(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE1:
				setValidate1(newValue);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE2:
				setValidate2(newValue);
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
			case SwitchyardPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE:
				setResource(RESOURCE_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD:
				setBindingSwitchyard(BINDING_SWITCHYARD_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE1:
				setResource1(RESOURCE1_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD1:
				setBindingSwitchyard1(BINDING_SWITCHYARD1_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACT:
				setArtifact((ArtifactType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACTS:
				setArtifacts((ArtifactsType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__CONTEXT_MAPPER:
				setContextMapper((ContextMapperType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__DOMAIN:
				setDomain((DomainType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLER:
				setHandler((HandlerType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLERS:
				setHandlers((HandlersType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__INTERFACE_ESB:
				setInterfaceEsb((EsbInterface)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__MESSAGE_COMPOSER:
				setMessageComposer((MessageComposerType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTIES:
				setProperties((PropertiesType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((PropertyType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE2:
				setResource2((ResourceType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__SWITCHYARD:
				setSwitchyard((SwitchYardType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORMS:
				setTransforms((TransformsType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATES:
				setValidates((ValidatesType)null);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM1:
				setTransform1(TRANSFORM1_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM2:
				setTransform2(TRANSFORM2_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM3:
				setTransform3(TRANSFORM3_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM4:
				setTransform4(TRANSFORM4_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM5:
				setTransform5(TRANSFORM5_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE1:
				setValidate1(VALIDATE1_EDEFAULT);
				return;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE2:
				setValidate2(VALIDATE2_EDEFAULT);
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
			case SwitchyardPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case SwitchyardPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case SwitchyardPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE:
				return RESOURCE_EDEFAULT == null ? getResource() != null : !RESOURCE_EDEFAULT.equals(getResource());
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD:
				return BINDING_SWITCHYARD_EDEFAULT == null ? getBindingSwitchyard() != null : !BINDING_SWITCHYARD_EDEFAULT.equals(getBindingSwitchyard());
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE1:
				return RESOURCE1_EDEFAULT == null ? getResource1() != null : !RESOURCE1_EDEFAULT.equals(getResource1());
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD1:
				return BINDING_SWITCHYARD1_EDEFAULT == null ? getBindingSwitchyard1() != null : !BINDING_SWITCHYARD1_EDEFAULT.equals(getBindingSwitchyard1());
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACT:
				return getArtifact() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__ARTIFACTS:
				return getArtifacts() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__BINDING_SWITCHYARD2:
				return getBindingSwitchyard2() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__CONTEXT_MAPPER:
				return getContextMapper() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__DOMAIN:
				return getDomain() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLER:
				return getHandler() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__HANDLERS:
				return getHandlers() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__INTERFACE_ESB:
				return getInterfaceEsb() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__MESSAGE_COMPOSER:
				return getMessageComposer() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTIES:
				return getProperties() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__RESOURCE2:
				return getResource2() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__SWITCHYARD:
				return getSwitchyard() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM:
				return getTransform() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORMS:
				return getTransforms() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE:
				return getValidate() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATES:
				return getValidates() != null;
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM1:
				return TRANSFORM1_EDEFAULT == null ? getTransform1() != null : !TRANSFORM1_EDEFAULT.equals(getTransform1());
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM2:
				return TRANSFORM2_EDEFAULT == null ? getTransform2() != null : !TRANSFORM2_EDEFAULT.equals(getTransform2());
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM3:
				return TRANSFORM3_EDEFAULT == null ? getTransform3() != null : !TRANSFORM3_EDEFAULT.equals(getTransform3());
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM4:
				return TRANSFORM4_EDEFAULT == null ? getTransform4() != null : !TRANSFORM4_EDEFAULT.equals(getTransform4());
			case SwitchyardPackage.DOCUMENT_ROOT__TRANSFORM5:
				return TRANSFORM5_EDEFAULT == null ? getTransform5() != null : !TRANSFORM5_EDEFAULT.equals(getTransform5());
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE1:
				return VALIDATE1_EDEFAULT == null ? getValidate1() != null : !VALIDATE1_EDEFAULT.equals(getValidate1());
			case SwitchyardPackage.DOCUMENT_ROOT__VALIDATE2:
				return VALIDATE2_EDEFAULT == null ? getValidate2() != null : !VALIDATE2_EDEFAULT.equals(getValidate2());
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
