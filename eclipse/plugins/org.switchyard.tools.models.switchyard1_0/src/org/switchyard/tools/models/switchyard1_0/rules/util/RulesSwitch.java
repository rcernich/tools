/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_0.rules.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.soa.sca.sca1_1.model.sca.CommonExtensionBase;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.switchyard.tools.models.switchyard1_0.rules.*;

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
 * @see org.switchyard.tools.models.switchyard1_0.rules.RulesPackage
 * @generated
 */
public class RulesSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static RulesPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public RulesSwitch() {
        if (modelPackage == null) {
            modelPackage = RulesPackage.eINSTANCE;
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
            case RulesPackage.OPERATIONS_TYPE: {
                OperationsType operationsType = (OperationsType)theEObject;
                T result = caseOperationsType(operationsType);
                if (result == null) result = caseCommonExtensionBase(operationsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.RULES_OPERATION_TYPE: {
                RulesOperationType rulesOperationType = (RulesOperationType)theEObject;
                T result = caseRulesOperationType(rulesOperationType);
                if (result == null) result = caseCommonExtensionBase(rulesOperationType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.CHANNELS_TYPE: {
                ChannelsType channelsType = (ChannelsType)theEObject;
                T result = caseChannelsType(channelsType);
                if (result == null) result = caseCommonExtensionBase(channelsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.CHANNEL_TYPE: {
                ChannelType channelType = (ChannelType)theEObject;
                T result = caseChannelType(channelType);
                if (result == null) result = caseCommonExtensionBase(channelType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.CONTAINER_TYPE: {
                ContainerType containerType = (ContainerType)theEObject;
                T result = caseContainerType(containerType);
                if (result == null) result = caseCommonExtensionBase(containerType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.GLOBALS_TYPE: {
                GlobalsType globalsType = (GlobalsType)theEObject;
                T result = caseGlobalsType(globalsType);
                if (result == null) result = caseCommonExtensionBase(globalsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.INPUTS_TYPE: {
                InputsType inputsType = (InputsType)theEObject;
                T result = caseInputsType(inputsType);
                if (result == null) result = caseCommonExtensionBase(inputsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.LISTENERS_TYPE: {
                ListenersType listenersType = (ListenersType)theEObject;
                T result = caseListenersType(listenersType);
                if (result == null) result = caseCommonExtensionBase(listenersType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.LISTENER_TYPE: {
                ListenerType listenerType = (ListenerType)theEObject;
                T result = caseListenerType(listenerType);
                if (result == null) result = caseCommonExtensionBase(listenerType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.LOGGERS_TYPE: {
                LoggersType loggersType = (LoggersType)theEObject;
                T result = caseLoggersType(loggersType);
                if (result == null) result = caseCommonExtensionBase(loggersType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.LOGGER_TYPE1: {
                LoggerType1 loggerType1 = (LoggerType1)theEObject;
                T result = caseLoggerType1(loggerType1);
                if (result == null) result = caseCommonExtensionBase(loggerType1);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.MANIFEST_TYPE: {
                ManifestType manifestType = (ManifestType)theEObject;
                T result = caseManifestType(manifestType);
                if (result == null) result = caseCommonExtensionBase(manifestType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.MAPPING_TYPE: {
                MappingType mappingType = (MappingType)theEObject;
                T result = caseMappingType(mappingType);
                if (result == null) result = caseCommonExtensionBase(mappingType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.OUTPUTS_TYPE: {
                OutputsType outputsType = (OutputsType)theEObject;
                T result = caseOutputsType(outputsType);
                if (result == null) result = caseCommonExtensionBase(outputsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.PROPERTIES_TYPE: {
                PropertiesType propertiesType = (PropertiesType)theEObject;
                T result = casePropertiesType(propertiesType);
                if (result == null) result = caseCommonExtensionBase(propertiesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.PROPERTY_TYPE: {
                PropertyType propertyType = (PropertyType)theEObject;
                T result = casePropertyType(propertyType);
                if (result == null) result = caseCommonExtensionBase(propertyType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.RESOURCES_TYPE: {
                ResourcesType resourcesType = (ResourcesType)theEObject;
                T result = caseResourcesType(resourcesType);
                if (result == null) result = caseCommonExtensionBase(resourcesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.RESOURCE_TYPE: {
                ResourceType resourceType = (ResourceType)theEObject;
                T result = caseResourceType(resourceType);
                if (result == null) result = caseCommonExtensionBase(resourceType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.RULES_IMPLEMENTATION_TYPE: {
                RulesImplementationType rulesImplementationType = (RulesImplementationType)theEObject;
                T result = caseRulesImplementationType(rulesImplementationType);
                if (result == null) result = caseImplementation(rulesImplementationType);
                if (result == null) result = caseCommonExtensionBase(rulesImplementationType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RulesPackage.FAULTS_TYPE: {
                FaultsType faultsType = (FaultsType)theEObject;
                T result = caseFaultsType(faultsType);
                if (result == null) result = caseCommonExtensionBase(faultsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Operations Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operations Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationsType(OperationsType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRulesOperationType(RulesOperationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Channels Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Channels Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseChannelsType(ChannelsType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Channel Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Channel Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseChannelType(ChannelType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Container Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerType(ContainerType object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Globals Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Globals Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGlobalsType(GlobalsType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Inputs Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inputs Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInputsType(InputsType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Listeners Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Listeners Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListenersType(ListenersType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Listener Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Listener Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListenerType(ListenerType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Loggers Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Loggers Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLoggersType(LoggersType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Logger Type1</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Logger Type1</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLoggerType1(LoggerType1 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Manifest Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Manifest Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseManifestType(ManifestType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mapping Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mapping Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMappingType(MappingType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Outputs Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Outputs Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOutputsType(OutputsType object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Resources Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resources Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResourcesType(ResourcesType object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Implementation Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implementation Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRulesImplementationType(RulesImplementationType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Faults Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Faults Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFaultsType(FaultsType object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Implementation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implementation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseImplementation(Implementation object) {
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

} //RulesSwitch
