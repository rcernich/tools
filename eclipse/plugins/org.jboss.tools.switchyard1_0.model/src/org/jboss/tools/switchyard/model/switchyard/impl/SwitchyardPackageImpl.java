/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.switchyard.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.soa.sca.core.model.addressing.AddressingPackage;
import org.eclipse.soa.sca.core.model.instance.InstancePackage;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;

import org.jboss.tools.switchyard.model.bean.BeanPackage;

import org.jboss.tools.switchyard.model.bean.impl.BeanPackageImpl;

import org.jboss.tools.switchyard.model.bpm.BPMPackage;

import org.jboss.tools.switchyard.model.bpm.impl.BPMPackageImpl;

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
import org.jboss.tools.switchyard.model.switchyard.SwitchyardFactory;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.TransformType;
import org.jboss.tools.switchyard.model.switchyard.TransformsType;
import org.jboss.tools.switchyard.model.switchyard.ValidateType;
import org.jboss.tools.switchyard.model.switchyard.ValidatesType;

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
public class SwitchyardPackageImpl extends EPackageImpl implements SwitchyardPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextMapperTypeEClass = null;

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
	private EClass domainTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass esbInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageComposerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertiesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyTypeEClass = null;

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
	private EClass switchYardBindingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchYardTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validatesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validateTypeEClass = null;

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
	 * @see org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SwitchyardPackageImpl() {
		super(eNS_URI, SwitchyardFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SwitchyardPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SwitchyardPackage init() {
		if (isInited) return (SwitchyardPackage)EPackage.Registry.INSTANCE.getEPackage(SwitchyardPackage.eNS_URI);

		// Obtain or create and register package
		SwitchyardPackageImpl theSwitchyardPackage = (SwitchyardPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SwitchyardPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SwitchyardPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ScaPackage.eINSTANCE.eClass();
		InstancePackage.eINSTANCE.eClass();
		AddressingPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		PolicyPackageImpl thePolicyPackage = (PolicyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PolicyPackage.eNS_URI) instanceof PolicyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PolicyPackage.eNS_URI) : PolicyPackage.eINSTANCE);
		BeanPackageImpl theBeanPackage = (BeanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BeanPackage.eNS_URI) instanceof BeanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BeanPackage.eNS_URI) : BeanPackage.eINSTANCE);
		BPELPackageImpl theBPELPackage = (BPELPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BPELPackage.eNS_URI) instanceof BPELPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BPELPackage.eNS_URI) : BPELPackage.eINSTANCE);
		BPMPackageImpl theBPMPackage = (BPMPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BPMPackage.eNS_URI) instanceof BPMPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BPMPackage.eNS_URI) : BPMPackage.eINSTANCE);
		CommonRulesPackageImpl theCommonRulesPackage = (CommonRulesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonRulesPackage.eNS_URI) instanceof CommonRulesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonRulesPackage.eNS_URI) : CommonRulesPackage.eINSTANCE);
		ClojurePackageImpl theClojurePackage = (ClojurePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClojurePackage.eNS_URI) instanceof ClojurePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClojurePackage.eNS_URI) : ClojurePackage.eINSTANCE);
		HornetQPackageImpl theHornetQPackage = (HornetQPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HornetQPackage.eNS_URI) instanceof HornetQPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HornetQPackage.eNS_URI) : HornetQPackage.eINSTANCE);
		RulesPackageImpl theRulesPackage = (RulesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RulesPackage.eNS_URI) instanceof RulesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RulesPackage.eNS_URI) : RulesPackage.eINSTANCE);
		SOAPPackageImpl theSOAPPackage = (SOAPPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SOAPPackage.eNS_URI) instanceof SOAPPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SOAPPackage.eNS_URI) : SOAPPackage.eINSTANCE);
		TransformPackageImpl theTransformPackage = (TransformPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TransformPackage.eNS_URI) instanceof TransformPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TransformPackage.eNS_URI) : TransformPackage.eINSTANCE);
		ValidatePackageImpl theValidatePackage = (ValidatePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ValidatePackage.eNS_URI) instanceof ValidatePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ValidatePackage.eNS_URI) : ValidatePackage.eINSTANCE);

		// Create package meta-data objects
		theSwitchyardPackage.createPackageContents();
		thePolicyPackage.createPackageContents();
		theBeanPackage.createPackageContents();
		theBPELPackage.createPackageContents();
		theBPMPackage.createPackageContents();
		theCommonRulesPackage.createPackageContents();
		theClojurePackage.createPackageContents();
		theHornetQPackage.createPackageContents();
		theRulesPackage.createPackageContents();
		theSOAPPackage.createPackageContents();
		theTransformPackage.createPackageContents();
		theValidatePackage.createPackageContents();

		// Initialize created meta-data
		theSwitchyardPackage.initializePackageContents();
		thePolicyPackage.initializePackageContents();
		theBeanPackage.initializePackageContents();
		theBPELPackage.initializePackageContents();
		theBPMPackage.initializePackageContents();
		theCommonRulesPackage.initializePackageContents();
		theClojurePackage.initializePackageContents();
		theHornetQPackage.initializePackageContents();
		theRulesPackage.initializePackageContents();
		theSOAPPackage.initializePackageContents();
		theTransformPackage.initializePackageContents();
		theValidatePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSwitchyardPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SwitchyardPackage.eNS_URI, theSwitchyardPackage);
		return theSwitchyardPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifactsType() {
		return artifactsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifactsType_Artifact() {
		return (EReference)artifactsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifactType() {
		return artifactTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactType_Name() {
		return (EAttribute)artifactTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifactType_Url() {
		return (EAttribute)artifactTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextMapperType() {
		return contextMapperTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextMapperType_Class() {
		return (EAttribute)contextMapperTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextMapperType_ExcludeNamespaces() {
		return (EAttribute)contextMapperTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextMapperType_Excludes() {
		return (EAttribute)contextMapperTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextMapperType_IncludeNamespaces() {
		return (EAttribute)contextMapperTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContextMapperType_Includes() {
		return (EAttribute)contextMapperTypeEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getDocumentRoot_Resource() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_BindingSwitchyard() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Resource1() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_BindingSwitchyard1() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Artifact() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Artifacts() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BindingSwitchyard2() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ContextMapper() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Domain() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Handler() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Handlers() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_InterfaceEsb() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MessageComposer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Properties() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Property() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Resource2() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Switchyard() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transform() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transforms() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Validate() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Validates() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Transform1() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Transform2() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Transform3() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Transform4() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Transform5() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Validate1() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Validate2() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainType() {
		return domainTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainType_Transforms() {
		return (EReference)domainTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainType_Validates() {
		return (EReference)domainTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainType_Properties() {
		return (EReference)domainTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainType_Handlers() {
		return (EReference)domainTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainType_Name() {
		return (EAttribute)domainTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEsbInterface() {
		return esbInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlersType() {
		return handlersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHandlersType_Handler() {
		return (EReference)handlersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlerType() {
		return handlerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerType_Class() {
		return (EAttribute)handlerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerType_Name() {
		return (EAttribute)handlerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageComposerType() {
		return messageComposerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageComposerType_Class() {
		return (EAttribute)messageComposerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertiesType() {
		return propertiesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertiesType_Property() {
		return (EReference)propertiesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyType() {
		return propertyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyType_Name() {
		return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyType_Value() {
		return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getResourceType_Location() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Type() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchYardBindingType() {
		return switchYardBindingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardBindingType_ContextMapper() {
		return (EReference)switchYardBindingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardBindingType_MessageComposer() {
		return (EReference)switchYardBindingTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchYardType() {
		return switchYardTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardType_Composite() {
		return (EReference)switchYardTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardType_Transforms() {
		return (EReference)switchYardTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardType_Validates() {
		return (EReference)switchYardTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardType_Domain() {
		return (EReference)switchYardTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchYardType_Artifacts() {
		return (EReference)switchYardTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchYardType_Name() {
		return (EAttribute)switchYardTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchYardType_TargetNamespace() {
		return (EAttribute)switchYardTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformsType() {
		return transformsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformsType_TransformGroup() {
		return (EAttribute)transformsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformsType_Transform() {
		return (EReference)transformsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformType() {
		return transformTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformType_From() {
		return (EAttribute)transformTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformType_To() {
		return (EAttribute)transformTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidatesType() {
		return validatesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatesType_ValidateGroup() {
		return (EAttribute)validatesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValidatesType_Validate() {
		return (EReference)validatesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidateType() {
		return validateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidateType_Name() {
		return (EAttribute)validateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchyardFactory getSwitchyardFactory() {
		return (SwitchyardFactory)getEFactoryInstance();
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
		artifactsTypeEClass = createEClass(ARTIFACTS_TYPE);
		createEReference(artifactsTypeEClass, ARTIFACTS_TYPE__ARTIFACT);

		artifactTypeEClass = createEClass(ARTIFACT_TYPE);
		createEAttribute(artifactTypeEClass, ARTIFACT_TYPE__NAME);
		createEAttribute(artifactTypeEClass, ARTIFACT_TYPE__URL);

		contextMapperTypeEClass = createEClass(CONTEXT_MAPPER_TYPE);
		createEAttribute(contextMapperTypeEClass, CONTEXT_MAPPER_TYPE__CLASS);
		createEAttribute(contextMapperTypeEClass, CONTEXT_MAPPER_TYPE__EXCLUDE_NAMESPACES);
		createEAttribute(contextMapperTypeEClass, CONTEXT_MAPPER_TYPE__EXCLUDES);
		createEAttribute(contextMapperTypeEClass, CONTEXT_MAPPER_TYPE__INCLUDE_NAMESPACES);
		createEAttribute(contextMapperTypeEClass, CONTEXT_MAPPER_TYPE__INCLUDES);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__RESOURCE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__BINDING_SWITCHYARD);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__RESOURCE1);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__BINDING_SWITCHYARD1);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ARTIFACT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ARTIFACTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__BINDING_SWITCHYARD2);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONTEXT_MAPPER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DOMAIN);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HANDLER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HANDLERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__INTERFACE_ESB);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MESSAGE_COMPOSER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTIES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCE2);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SWITCHYARD);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSFORM);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSFORMS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VALIDATE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VALIDATES);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TRANSFORM1);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TRANSFORM2);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TRANSFORM3);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TRANSFORM4);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TRANSFORM5);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VALIDATE1);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VALIDATE2);

		domainTypeEClass = createEClass(DOMAIN_TYPE);
		createEReference(domainTypeEClass, DOMAIN_TYPE__TRANSFORMS);
		createEReference(domainTypeEClass, DOMAIN_TYPE__VALIDATES);
		createEReference(domainTypeEClass, DOMAIN_TYPE__PROPERTIES);
		createEReference(domainTypeEClass, DOMAIN_TYPE__HANDLERS);
		createEAttribute(domainTypeEClass, DOMAIN_TYPE__NAME);

		esbInterfaceEClass = createEClass(ESB_INTERFACE);

		handlersTypeEClass = createEClass(HANDLERS_TYPE);
		createEReference(handlersTypeEClass, HANDLERS_TYPE__HANDLER);

		handlerTypeEClass = createEClass(HANDLER_TYPE);
		createEAttribute(handlerTypeEClass, HANDLER_TYPE__CLASS);
		createEAttribute(handlerTypeEClass, HANDLER_TYPE__NAME);

		messageComposerTypeEClass = createEClass(MESSAGE_COMPOSER_TYPE);
		createEAttribute(messageComposerTypeEClass, MESSAGE_COMPOSER_TYPE__CLASS);

		propertiesTypeEClass = createEClass(PROPERTIES_TYPE);
		createEReference(propertiesTypeEClass, PROPERTIES_TYPE__PROPERTY);

		propertyTypeEClass = createEClass(PROPERTY_TYPE);
		createEAttribute(propertyTypeEClass, PROPERTY_TYPE__NAME);
		createEAttribute(propertyTypeEClass, PROPERTY_TYPE__VALUE);

		resourceTypeEClass = createEClass(RESOURCE_TYPE);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__LOCATION);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__TYPE);

		switchYardBindingTypeEClass = createEClass(SWITCH_YARD_BINDING_TYPE);
		createEReference(switchYardBindingTypeEClass, SWITCH_YARD_BINDING_TYPE__CONTEXT_MAPPER);
		createEReference(switchYardBindingTypeEClass, SWITCH_YARD_BINDING_TYPE__MESSAGE_COMPOSER);

		switchYardTypeEClass = createEClass(SWITCH_YARD_TYPE);
		createEReference(switchYardTypeEClass, SWITCH_YARD_TYPE__TRANSFORMS);
		createEReference(switchYardTypeEClass, SWITCH_YARD_TYPE__VALIDATES);
		createEReference(switchYardTypeEClass, SWITCH_YARD_TYPE__DOMAIN);
		createEReference(switchYardTypeEClass, SWITCH_YARD_TYPE__ARTIFACTS);
		createEAttribute(switchYardTypeEClass, SWITCH_YARD_TYPE__NAME);
		createEAttribute(switchYardTypeEClass, SWITCH_YARD_TYPE__TARGET_NAMESPACE);
		createEReference(switchYardTypeEClass, SWITCH_YARD_TYPE__COMPOSITE);

		transformsTypeEClass = createEClass(TRANSFORMS_TYPE);
		createEAttribute(transformsTypeEClass, TRANSFORMS_TYPE__TRANSFORM_GROUP);
		createEReference(transformsTypeEClass, TRANSFORMS_TYPE__TRANSFORM);

		transformTypeEClass = createEClass(TRANSFORM_TYPE);
		createEAttribute(transformTypeEClass, TRANSFORM_TYPE__FROM);
		createEAttribute(transformTypeEClass, TRANSFORM_TYPE__TO);

		validatesTypeEClass = createEClass(VALIDATES_TYPE);
		createEAttribute(validatesTypeEClass, VALIDATES_TYPE__VALIDATE_GROUP);
		createEReference(validatesTypeEClass, VALIDATES_TYPE__VALIDATE);

		validateTypeEClass = createEClass(VALIDATE_TYPE);
		createEAttribute(validateTypeEClass, VALIDATE_TYPE__NAME);
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

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		ScaPackage theScaPackage = (ScaPackage)EPackage.Registry.INSTANCE.getEPackage(ScaPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(artifactsTypeEClass, ArtifactsType.class, "ArtifactsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArtifactsType_Artifact(), this.getArtifactType(), null, "artifact", null, 0, -1, ArtifactsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactTypeEClass, ArtifactType.class, "ArtifactType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ArtifactType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactType_Url(), theXMLTypePackage.getString(), "url", null, 1, 1, ArtifactType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contextMapperTypeEClass, ContextMapperType.class, "ContextMapperType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContextMapperType_Class(), theXMLTypePackage.getString(), "class", null, 0, 1, ContextMapperType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContextMapperType_ExcludeNamespaces(), theXMLTypePackage.getString(), "excludeNamespaces", null, 0, 1, ContextMapperType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContextMapperType_Excludes(), theXMLTypePackage.getString(), "excludes", null, 0, 1, ContextMapperType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContextMapperType_IncludeNamespaces(), theXMLTypePackage.getString(), "includeNamespaces", null, 0, 1, ContextMapperType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContextMapperType_Includes(), theXMLTypePackage.getString(), "includes", null, 0, 1, ContextMapperType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Resource(), theXMLTypePackage.getAnySimpleType(), "resource", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_BindingSwitchyard(), theXMLTypePackage.getAnySimpleType(), "bindingSwitchyard", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Resource1(), theXMLTypePackage.getAnySimpleType(), "resource1", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_BindingSwitchyard1(), theXMLTypePackage.getAnySimpleType(), "bindingSwitchyard1", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Artifact(), this.getArtifactType(), null, "artifact", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Artifacts(), this.getArtifactsType(), null, "artifacts", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_BindingSwitchyard2(), this.getSwitchYardBindingType(), null, "bindingSwitchyard2", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ContextMapper(), this.getContextMapperType(), null, "contextMapper", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Domain(), this.getDomainType(), null, "domain", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Handler(), this.getHandlerType(), null, "handler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Handlers(), this.getHandlersType(), null, "handlers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_InterfaceEsb(), this.getEsbInterface(), null, "interfaceEsb", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MessageComposer(), this.getMessageComposerType(), null, "messageComposer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Properties(), this.getPropertiesType(), null, "properties", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Property(), this.getPropertyType(), null, "property", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Resource2(), this.getResourceType(), null, "resource2", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Switchyard(), this.getSwitchYardType(), null, "switchyard", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Transform(), this.getTransformType(), null, "transform", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Transforms(), this.getTransformsType(), null, "transforms", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Validate(), this.getValidateType(), null, "validate", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Validates(), this.getValidatesType(), null, "validates", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Transform1(), theXMLTypePackage.getAnySimpleType(), "transform1", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Transform2(), theXMLTypePackage.getAnySimpleType(), "transform2", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Transform3(), theXMLTypePackage.getAnySimpleType(), "transform3", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Transform4(), theXMLTypePackage.getAnySimpleType(), "transform4", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Transform5(), theXMLTypePackage.getAnySimpleType(), "transform5", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Validate1(), theXMLTypePackage.getAnySimpleType(), "validate1", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Validate2(), theXMLTypePackage.getAnySimpleType(), "validate2", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(domainTypeEClass, DomainType.class, "DomainType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainType_Transforms(), this.getTransformsType(), null, "transforms", null, 0, 1, DomainType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainType_Validates(), this.getValidatesType(), null, "validates", null, 0, 1, DomainType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainType_Properties(), this.getPropertiesType(), null, "properties", null, 0, 1, DomainType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainType_Handlers(), this.getHandlersType(), null, "handlers", null, 0, 1, DomainType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, DomainType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(esbInterfaceEClass, EsbInterface.class, "EsbInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(handlersTypeEClass, HandlersType.class, "HandlersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHandlersType_Handler(), this.getHandlerType(), null, "handler", null, 0, -1, HandlersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(handlerTypeEClass, HandlerType.class, "HandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHandlerType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, HandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHandlerType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, HandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageComposerTypeEClass, MessageComposerType.class, "MessageComposerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMessageComposerType_Class(), theXMLTypePackage.getString(), "class", null, 0, 1, MessageComposerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertiesTypeEClass, PropertiesType.class, "PropertiesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertiesType_Property(), this.getPropertyType(), null, "property", null, 0, -1, PropertiesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyTypeEClass, PropertyType.class, "PropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyType_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceTypeEClass, ResourceType.class, "ResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceType_Location(), theXMLTypePackage.getString(), "location", null, 1, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Type(), theXMLTypePackage.getNCName(), "type", null, 1, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(switchYardBindingTypeEClass, SwitchYardBindingType.class, "SwitchYardBindingType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwitchYardBindingType_ContextMapper(), this.getContextMapperType(), null, "contextMapper", null, 0, 1, SwitchYardBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchYardBindingType_MessageComposer(), this.getMessageComposerType(), null, "messageComposer", null, 0, 1, SwitchYardBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(switchYardTypeEClass, SwitchYardType.class, "SwitchYardType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwitchYardType_Transforms(), this.getTransformsType(), null, "transforms", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchYardType_Validates(), this.getValidatesType(), null, "validates", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchYardType_Domain(), this.getDomainType(), null, "domain", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchYardType_Artifacts(), this.getArtifactsType(), null, "artifacts", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwitchYardType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwitchYardType_TargetNamespace(), theXMLTypePackage.getString(), "targetNamespace", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchYardType_Composite(), theScaPackage.getComposite(), null, "composite", null, 0, 1, SwitchYardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(transformsTypeEClass, TransformsType.class, "TransformsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformsType_TransformGroup(), ecorePackage.getEFeatureMapEntry(), "transformGroup", null, 0, -1, TransformsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformsType_Transform(), this.getTransformType(), null, "transform", null, 0, -1, TransformsType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(transformTypeEClass, TransformType.class, "TransformType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformType_From(), theXMLTypePackage.getString(), "from", null, 1, 1, TransformType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformType_To(), theXMLTypePackage.getString(), "to", null, 1, 1, TransformType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(validatesTypeEClass, ValidatesType.class, "ValidatesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidatesType_ValidateGroup(), ecorePackage.getEFeatureMapEntry(), "validateGroup", null, 0, -1, ValidatesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValidatesType_Validate(), this.getValidateType(), null, "validate", null, 0, -1, ValidatesType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(validateTypeEClass, ValidateType.class, "ValidateType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidateType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ValidateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (artifactsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ArtifactsType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getArtifactsType_Artifact(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "artifact",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (artifactTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ArtifactType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getArtifactType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getArtifactType_Url(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "url"
		   });		
		addAnnotation
		  (contextMapperTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ContextMapperType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getContextMapperType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });		
		addAnnotation
		  (getContextMapperType_ExcludeNamespaces(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "excludeNamespaces"
		   });		
		addAnnotation
		  (getContextMapperType_Excludes(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "excludes"
		   });		
		addAnnotation
		  (getContextMapperType_IncludeNamespaces(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "includeNamespaces"
		   });		
		addAnnotation
		  (getContextMapperType_Includes(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "includes"
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
		  (getDocumentRoot_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BindingSwitchyard(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "binding.switchyard",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Resource1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BindingSwitchyard1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "binding.switchyard",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Artifact(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "artifact",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Artifacts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "artifacts",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BindingSwitchyard2(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "binding.switchyard",
			 "namespace", "##targetNamespace",
			 "affiliation", "http://docs.oasis-open.org/ns/opencsa/sca/200912#binding"
		   });		
		addAnnotation
		  (getDocumentRoot_ContextMapper(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "contextMapper",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Domain(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "domain",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Handler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handlers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_InterfaceEsb(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "interface.esb",
			 "namespace", "##targetNamespace",
			 "affiliation", "http://docs.oasis-open.org/ns/opencsa/sca/200912#interface"
		   });		
		addAnnotation
		  (getDocumentRoot_MessageComposer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "messageComposer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Properties(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "properties",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Resource2(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Switchyard(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "switchyard",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transforms(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transforms",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Validate(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validate",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Validates(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validates",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform2(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform3(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform4(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transform5(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Validate1(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validate",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Validate2(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validate",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (domainTypeEClass, 
		   source, 
		   new String[] {
			 "name", "DomainType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDomainType_Transforms(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transforms",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDomainType_Validates(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validates",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDomainType_Properties(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "properties",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDomainType_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handlers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDomainType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (esbInterfaceEClass, 
		   source, 
		   new String[] {
			 "name", "EsbInterface",
			 "kind", "empty"
		   });		
		addAnnotation
		  (handlersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "HandlersType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getHandlersType_Handler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (handlerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "HandlerType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getHandlerType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });		
		addAnnotation
		  (getHandlerType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (messageComposerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MessageComposerType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getMessageComposerType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });		
		addAnnotation
		  (propertiesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "PropertiesType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getPropertiesType_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (propertyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "PropertyType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getPropertyType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getPropertyType_Value(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "value"
		   });		
		addAnnotation
		  (resourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ResourceType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getResourceType_Location(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "location"
		   });		
		addAnnotation
		  (getResourceType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (switchYardBindingTypeEClass, 
		   source, 
		   new String[] {
			 "name", "SwitchYardBindingType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSwitchYardBindingType_ContextMapper(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "contextMapper",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwitchYardBindingType_MessageComposer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "messageComposer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (switchYardTypeEClass, 
		   source, 
		   new String[] {
			 "name", "SwitchYardType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSwitchYardType_Transforms(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transforms",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwitchYardType_Validates(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validates",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwitchYardType_Domain(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "domain",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwitchYardType_Artifacts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "artifacts",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwitchYardType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getSwitchYardType_TargetNamespace(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "targetNamespace"
		   });		
		addAnnotation
		  (transformsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransformsType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransformsType_TransformGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "transform:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTransformsType_Transform(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transform",
			 "namespace", "##targetNamespace",
			 "group", "transform:group"
		   });		
		addAnnotation
		  (transformTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransformType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTransformType_From(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "from"
		   });		
		addAnnotation
		  (getTransformType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (validatesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ValidatesType",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getValidatesType_ValidateGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "validate:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getValidatesType_Validate(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "validate",
			 "namespace", "##targetNamespace",
			 "group", "validate:group"
		   });		
		addAnnotation
		  (validateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ValidateType",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getValidateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });
	}

} //SwitchyardPackageImpl