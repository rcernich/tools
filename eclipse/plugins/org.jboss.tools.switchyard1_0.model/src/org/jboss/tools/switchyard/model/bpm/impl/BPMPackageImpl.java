/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.bpm.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.soa.sca.core.model.addressing.AddressingPackage;
import org.eclipse.soa.sca.core.model.instance.InstancePackage;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;

import org.jboss.tools.switchyard.model.bean.BeanPackage;

import org.jboss.tools.switchyard.model.bean.impl.BeanPackageImpl;

import org.jboss.tools.switchyard.model.bpm.ActionType;
import org.jboss.tools.switchyard.model.bpm.ActionType1;
import org.jboss.tools.switchyard.model.bpm.AuditType;
import org.jboss.tools.switchyard.model.bpm.BPMFactory;
import org.jboss.tools.switchyard.model.bpm.BPMImplementationType;
import org.jboss.tools.switchyard.model.bpm.BPMPackage;
import org.jboss.tools.switchyard.model.bpm.DocumentRoot;
import org.jboss.tools.switchyard.model.bpm.ResourceType;
import org.jboss.tools.switchyard.model.bpm.TaskHandlerType;

import org.jboss.tools.switchyard.model.clojure.ClojurePackage;

import org.jboss.tools.switchyard.model.clojure.impl.ClojurePackageImpl;

import org.jboss.tools.switchyard.model.commonrules.CommonRulesPackage;

import org.jboss.tools.switchyard.model.commonrules.impl.CommonRulesPackageImpl;

import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;

import org.jboss.tools.switchyard.model.hornetq.impl.HornetQPackageImpl;

import org.jboss.tools.switchyard.model.rules.RulesPackage;

import org.jboss.tools.switchyard.model.rules.impl.RulesPackageImpl;

import org.jboss.tools.switchyard.model.soap.SOAPPackage;

import org.jboss.tools.switchyard.model.soap.impl.SOAPPackageImpl;

import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;

import org.jboss.tools.switchyard.model.switchyard.impl.SwitchyardPackageImpl;

import org.jboss.tools.switchyard.model.transform.TransformPackage;

import org.jboss.tools.switchyard.model.transform.impl.TransformPackageImpl;

import org.jboss.tools.switchyard.model.validate.ValidatePackage;

import org.jboss.tools.switchyard.model.validate.impl.ValidatePackageImpl;

import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELPackage;

import org.open.oasis.docs.ns.opencsa.sca.bpel.impl.BPELPackageImpl;

import org.w3.ns.ws.policy.PolicyPackage;

import org.w3.ns.ws.policy.impl.PolicyPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BPMPackageImpl extends EPackageImpl implements BPMPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionType1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass auditTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bpmImplementationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskHandlerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum actionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType actionTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.jboss.tools.switchyard.model.bpm.BPMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BPMPackageImpl() {
		super(eNS_URI, BPMFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link BPMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BPMPackage init() {
		if (isInited) return (BPMPackage)EPackage.Registry.INSTANCE.getEPackage(BPMPackage.eNS_URI);

		// Obtain or create and register package
		BPMPackageImpl theBPMPackage = (BPMPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BPMPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BPMPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ScaPackage.eINSTANCE.eClass();
		InstancePackage.eINSTANCE.eClass();
		AddressingPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		PolicyPackageImpl thePolicyPackage = (PolicyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PolicyPackage.eNS_URI) instanceof PolicyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PolicyPackage.eNS_URI) : PolicyPackage.eINSTANCE);
		BeanPackageImpl theBeanPackage = (BeanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BeanPackage.eNS_URI) instanceof BeanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BeanPackage.eNS_URI) : BeanPackage.eINSTANCE);
		BPELPackageImpl theBPELPackage = (BPELPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BPELPackage.eNS_URI) instanceof BPELPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BPELPackage.eNS_URI) : BPELPackage.eINSTANCE);
		CommonRulesPackageImpl theCommonRulesPackage = (CommonRulesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonRulesPackage.eNS_URI) instanceof CommonRulesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonRulesPackage.eNS_URI) : CommonRulesPackage.eINSTANCE);
		SwitchyardPackageImpl theSwitchyardPackage = (SwitchyardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SwitchyardPackage.eNS_URI) instanceof SwitchyardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SwitchyardPackage.eNS_URI) : SwitchyardPackage.eINSTANCE);
		ClojurePackageImpl theClojurePackage = (ClojurePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClojurePackage.eNS_URI) instanceof ClojurePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClojurePackage.eNS_URI) : ClojurePackage.eINSTANCE);
		HornetQPackageImpl theHornetQPackage = (HornetQPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HornetQPackage.eNS_URI) instanceof HornetQPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HornetQPackage.eNS_URI) : HornetQPackage.eINSTANCE);
		RulesPackageImpl theRulesPackage = (RulesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RulesPackage.eNS_URI) instanceof RulesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RulesPackage.eNS_URI) : RulesPackage.eINSTANCE);
		SOAPPackageImpl theSOAPPackage = (SOAPPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SOAPPackage.eNS_URI) instanceof SOAPPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SOAPPackage.eNS_URI) : SOAPPackage.eINSTANCE);
		TransformPackageImpl theTransformPackage = (TransformPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TransformPackage.eNS_URI) instanceof TransformPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TransformPackage.eNS_URI) : TransformPackage.eINSTANCE);
		ValidatePackageImpl theValidatePackage = (ValidatePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ValidatePackage.eNS_URI) instanceof ValidatePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ValidatePackage.eNS_URI) : ValidatePackage.eINSTANCE);

		// Create package meta-data objects
		theBPMPackage.createPackageContents();
		thePolicyPackage.createPackageContents();
		theBeanPackage.createPackageContents();
		theBPELPackage.createPackageContents();
		theCommonRulesPackage.createPackageContents();
		theSwitchyardPackage.createPackageContents();
		theClojurePackage.createPackageContents();
		theHornetQPackage.createPackageContents();
		theRulesPackage.createPackageContents();
		theSOAPPackage.createPackageContents();
		theTransformPackage.createPackageContents();
		theValidatePackage.createPackageContents();

		// Initialize created meta-data
		theBPMPackage.initializePackageContents();
		thePolicyPackage.initializePackageContents();
		theBeanPackage.initializePackageContents();
		theBPELPackage.initializePackageContents();
		theCommonRulesPackage.initializePackageContents();
		theSwitchyardPackage.initializePackageContents();
		theClojurePackage.initializePackageContents();
		theHornetQPackage.initializePackageContents();
		theRulesPackage.initializePackageContents();
		theSOAPPackage.initializePackageContents();
		theTransformPackage.initializePackageContents();
		theValidatePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBPMPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BPMPackage.eNS_URI, theBPMPackage);
		return theBPMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionType1() {
		return actionType1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAuditType() {
		return auditTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBPMImplementationType() {
		return bpmImplementationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBPMImplementationType_Action() {
		return (EReference)bpmImplementationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBPMImplementationType_Audit() {
		return (EReference)bpmImplementationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBPMImplementationType_TaskHandler() {
		return (EReference)bpmImplementationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBPMImplementationType_Resource() {
		return (EReference)bpmImplementationTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Action() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Audit() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ImplementationBpm() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Resource() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TaskHandler() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceType() {
		return resourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskHandlerType() {
		return taskHandlerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getActionType() {
		return actionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActionTypeObject() {
		return actionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BPMFactory getBPMFactory() {
		return (BPMFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		actionType1EClass = createEClass(ACTION_TYPE1);

		auditTypeEClass = createEClass(AUDIT_TYPE);

		bpmImplementationTypeEClass = createEClass(BPM_IMPLEMENTATION_TYPE);
		createEReference(bpmImplementationTypeEClass, BPM_IMPLEMENTATION_TYPE__ACTION);
		createEReference(bpmImplementationTypeEClass, BPM_IMPLEMENTATION_TYPE__AUDIT);
		createEReference(bpmImplementationTypeEClass, BPM_IMPLEMENTATION_TYPE__TASK_HANDLER);
		createEReference(bpmImplementationTypeEClass, BPM_IMPLEMENTATION_TYPE__RESOURCE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__AUDIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IMPLEMENTATION_BPM);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TASK_HANDLER);

		resourceTypeEClass = createEClass(RESOURCE_TYPE);

		taskHandlerTypeEClass = createEClass(TASK_HANDLER_TYPE);

		// Create enums
		actionTypeEEnum = createEEnum(ACTION_TYPE);

		// Create data types
		actionTypeObjectEDataType = createEDataType(ACTION_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(actionType1EClass, ActionType1.class, "ActionType1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(auditTypeEClass, AuditType.class, "AuditType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bpmImplementationTypeEClass, BPMImplementationType.class, "BPMImplementationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBPMImplementationType_Action(), this.getActionType1(), null, "action", null, 0, -1, BPMImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBPMImplementationType_Audit(), this.getAuditType(), null, "audit", null, 0, 1, BPMImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBPMImplementationType_TaskHandler(), this.getTaskHandlerType(), null, "taskHandler", null, 0, -1, BPMImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBPMImplementationType_Resource(), this.getResourceType(), null, "resource", null, 0, -1, BPMImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Action(), this.getActionType1(), null, "action", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Audit(), this.getAuditType(), null, "audit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ImplementationBpm(), this.getBPMImplementationType(), null, "implementationBpm", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Resource(), this.getResourceType(), null, "resource", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TaskHandler(), this.getTaskHandlerType(), null, "taskHandler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(resourceTypeEClass, ResourceType.class, "ResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(taskHandlerTypeEClass, TaskHandlerType.class, "TaskHandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(actionTypeEEnum, ActionType.class, "ActionType");
		addEEnumLiteral(actionTypeEEnum, ActionType.STARTPROCESS);
		addEEnumLiteral(actionTypeEEnum, ActionType.SIGNALEVENT);
		addEEnumLiteral(actionTypeEEnum, ActionType.ABORTPROCESSINSTANCE);

		// Initialize data types
		initEDataType(actionTypeObjectEDataType, ActionType.class, "ActionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (actionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "actionType"
		   });		
		addAnnotation
		  (actionType1EClass, 
		   source, 
		   new String[] {
			 "name", "ActionType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (actionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "actionType:Object",
			 "baseType", "actionType"
		   });		
		addAnnotation
		  (auditTypeEClass, 
		   source, 
		   new String[] {
			 "name", "AuditType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (bpmImplementationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "BPMImplementationType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getBPMImplementationType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getBPMImplementationType_Audit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "audit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getBPMImplementationType_TaskHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "taskHandler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getBPMImplementationType_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Audit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "audit",
			 "namespace", "##targetNamespace",
			 "affiliation", "urn:switchyard-component-common-rules:config:1.0#audit"
		   });		
		addAnnotation
		  (getDocumentRoot_ImplementationBpm(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "implementation.bpm",
			 "namespace", "##targetNamespace",
			 "affiliation", "urn:switchyard-component-common-rules:config:1.0#implementation.common.rules"
		   });		
		addAnnotation
		  (getDocumentRoot_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace",
			 "affiliation", "urn:switchyard-config:switchyard:1.0#resource"
		   });		
		addAnnotation
		  (getDocumentRoot_TaskHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "taskHandler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (resourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ResourceType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (taskHandlerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TaskHandlerType",
			 "kind", "empty"
		   });
	}

} //BPMPackageImpl