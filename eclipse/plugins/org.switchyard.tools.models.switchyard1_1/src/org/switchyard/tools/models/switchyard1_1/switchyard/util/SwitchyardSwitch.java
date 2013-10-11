/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.switchyard.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.CommonExtensionBase;
import org.eclipse.soa.sca.sca1_1.model.sca.Interface;
import org.eclipse.soa.sca.sca1_1.model.sca.OperationSelectorType;
import org.switchyard.tools.models.switchyard1_1.switchyard.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage
 * @generated
 */
public class SwitchyardSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SwitchyardPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SwitchyardSwitch() {
        if (modelPackage == null) {
            modelPackage = SwitchyardPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SwitchyardPackage.ARTIFACTS_TYPE: {
                ArtifactsType artifactsType = (ArtifactsType)theEObject;
                T result = caseArtifactsType(artifactsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.ARTIFACT_TYPE: {
                ArtifactType artifactType = (ArtifactType)theEObject;
                T result = caseArtifactType(artifactType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.CONTEXT_MAPPER_TYPE: {
                ContextMapperType contextMapperType = (ContextMapperType)theEObject;
                T result = caseContextMapperType(contextMapperType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.DOMAIN_TYPE: {
                DomainType domainType = (DomainType)theEObject;
                T result = caseDomainType(domainType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.ESB_INTERFACE: {
                EsbInterface esbInterface = (EsbInterface)theEObject;
                T result = caseEsbInterface(esbInterface);
                if (result == null) result = caseInterface(esbInterface);
                if (result == null) result = caseCommonExtensionBase(esbInterface);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.JAVA_OPERATION_SELECTOR_TYPE: {
                JavaOperationSelectorType javaOperationSelectorType = (JavaOperationSelectorType)theEObject;
                T result = caseJavaOperationSelectorType(javaOperationSelectorType);
                if (result == null) result = caseSwitchYardOperationSelectorType(javaOperationSelectorType);
                if (result == null) result = caseOperationSelectorType(javaOperationSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.MESSAGE_COMPOSER_TYPE: {
                MessageComposerType messageComposerType = (MessageComposerType)theEObject;
                T result = caseMessageComposerType(messageComposerType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.PROPERTIES_TYPE: {
                PropertiesType propertiesType = (PropertiesType)theEObject;
                T result = casePropertiesType(propertiesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.PROPERTY_TYPE: {
                PropertyType propertyType = (PropertyType)theEObject;
                T result = casePropertyType(propertyType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.REGEX_OPERATION_SELECTOR_TYPE: {
                RegexOperationSelectorType regexOperationSelectorType = (RegexOperationSelectorType)theEObject;
                T result = caseRegexOperationSelectorType(regexOperationSelectorType);
                if (result == null) result = caseSwitchYardOperationSelectorType(regexOperationSelectorType);
                if (result == null) result = caseOperationSelectorType(regexOperationSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.RESOURCE_TYPE: {
                ResourceType resourceType = (ResourceType)theEObject;
                T result = caseResourceType(resourceType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.SECURITIES_TYPE: {
                SecuritiesType securitiesType = (SecuritiesType)theEObject;
                T result = caseSecuritiesType(securitiesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.SECURITY_TYPE: {
                SecurityType securityType = (SecurityType)theEObject;
                T result = caseSecurityType(securityType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.STATIC_OPERATION_SELECTOR_TYPE: {
                StaticOperationSelectorType staticOperationSelectorType = (StaticOperationSelectorType)theEObject;
                T result = caseStaticOperationSelectorType(staticOperationSelectorType);
                if (result == null) result = caseSwitchYardOperationSelectorType(staticOperationSelectorType);
                if (result == null) result = caseOperationSelectorType(staticOperationSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.SWITCH_YARD_BINDING_TYPE: {
                SwitchYardBindingType switchYardBindingType = (SwitchYardBindingType)theEObject;
                T result = caseSwitchYardBindingType(switchYardBindingType);
                if (result == null) result = caseBinding(switchYardBindingType);
                if (result == null) result = caseCommonExtensionBase(switchYardBindingType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.SWITCH_YARD_OPERATION_SELECTOR_TYPE: {
                SwitchYardOperationSelectorType switchYardOperationSelectorType = (SwitchYardOperationSelectorType)theEObject;
                T result = caseSwitchYardOperationSelectorType(switchYardOperationSelectorType);
                if (result == null) result = caseOperationSelectorType(switchYardOperationSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.SWITCH_YARD_TYPE: {
                SwitchYardType switchYardType = (SwitchYardType)theEObject;
                T result = caseSwitchYardType(switchYardType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.THROTTLING_TYPE: {
                ThrottlingType throttlingType = (ThrottlingType)theEObject;
                T result = caseThrottlingType(throttlingType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.TRANSFORMS_TYPE: {
                TransformsType transformsType = (TransformsType)theEObject;
                T result = caseTransformsType(transformsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.TRANSFORM_TYPE: {
                TransformType transformType = (TransformType)theEObject;
                T result = caseTransformType(transformType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.VALIDATES_TYPE: {
                ValidatesType validatesType = (ValidatesType)theEObject;
                T result = caseValidatesType(validatesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.VALIDATE_TYPE: {
                ValidateType validateType = (ValidateType)theEObject;
                T result = caseValidateType(validateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SwitchyardPackage.XPATH_OPERATION_SELECTOR_TYPE: {
                XPathOperationSelectorType xPathOperationSelectorType = (XPathOperationSelectorType)theEObject;
                T result = caseXPathOperationSelectorType(xPathOperationSelectorType);
                if (result == null) result = caseSwitchYardOperationSelectorType(xPathOperationSelectorType);
                if (result == null) result = caseOperationSelectorType(xPathOperationSelectorType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Artifacts Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Artifacts Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseArtifactsType(ArtifactsType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Artifact Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Artifact Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseArtifactType(ArtifactType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Context Mapper Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Context Mapper Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContextMapperType(ContextMapperType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Domain Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Domain Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDomainType(DomainType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Esb Interface</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Esb Interface</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEsbInterface(EsbInterface object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Message Composer Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Composer Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMessageComposerType(MessageComposerType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Properties Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Properties Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePropertiesType(PropertiesType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Property Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePropertyType(PropertyType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Resource Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseResourceType(ResourceType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Switch Yard Binding Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Switch Yard Binding Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSwitchYardBindingType(SwitchYardBindingType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Switch Yard Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Switch Yard Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSwitchYardType(SwitchYardType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Throttling Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Throttling Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseThrottlingType(ThrottlingType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Transforms Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Transforms Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTransformsType(TransformsType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Transform Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Transform Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTransformType(TransformType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Validates Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Validates Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseValidatesType(ValidatesType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Validate Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Validate Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseValidateType(ValidateType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Java Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Java Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseJavaOperationSelectorType(JavaOperationSelectorType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Regex Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Regex Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRegexOperationSelectorType(RegexOperationSelectorType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Static Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Static Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStaticOperationSelectorType(StaticOperationSelectorType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XPath Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XPath Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseXPathOperationSelectorType(XPathOperationSelectorType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Security Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Security Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSecurityType(SecurityType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Securities Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Securities Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSecuritiesType(SecuritiesType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Switch Yard Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Switch Yard Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSwitchYardOperationSelectorType(SwitchYardOperationSelectorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Common Extension Base</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Common Extension Base</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCommonExtensionBase(CommonExtensionBase object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interface</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interface</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterface(Interface object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Binding</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Binding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBinding(Binding object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Selector Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationSelectorType(OperationSelectorType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //SwitchyardSwitch
