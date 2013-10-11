/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.switchyard;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage
 * @generated
 */
public interface SwitchyardFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SwitchyardFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.switchyard.impl.SwitchyardFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Artifacts Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Artifacts Type</em>'.
     * @generated
     */
	ArtifactsType createArtifactsType();

	/**
     * Returns a new object of class '<em>Artifact Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Artifact Type</em>'.
     * @generated
     */
	ArtifactType createArtifactType();

	/**
     * Returns a new object of class '<em>Context Mapper Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Context Mapper Type</em>'.
     * @generated
     */
	ContextMapperType createContextMapperType();

	/**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
	DocumentRoot createDocumentRoot();

	/**
     * Returns a new object of class '<em>Domain Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Domain Type</em>'.
     * @generated
     */
	DomainType createDomainType();

	/**
     * Returns a new object of class '<em>Esb Interface</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Esb Interface</em>'.
     * @generated
     */
	EsbInterface createEsbInterface();

	/**
     * Returns a new object of class '<em>Message Composer Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Message Composer Type</em>'.
     * @generated
     */
	MessageComposerType createMessageComposerType();

	/**
     * Returns a new object of class '<em>Properties Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Properties Type</em>'.
     * @generated
     */
	PropertiesType createPropertiesType();

	/**
     * Returns a new object of class '<em>Property Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Property Type</em>'.
     * @generated
     */
	PropertyType createPropertyType();

	/**
     * Returns a new object of class '<em>Resource Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Type</em>'.
     * @generated
     */
	ResourceType createResourceType();

	/**
     * Returns a new object of class '<em>Switch Yard Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Switch Yard Type</em>'.
     * @generated
     */
	SwitchYardType createSwitchYardType();

	/**
     * Returns a new object of class '<em>Throttling Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Throttling Type</em>'.
     * @generated
     */
    ThrottlingType createThrottlingType();

    /**
     * Returns a new object of class '<em>Transforms Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Transforms Type</em>'.
     * @generated
     */
	TransformsType createTransformsType();

	/**
     * Returns a new object of class '<em>Validates Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Validates Type</em>'.
     * @generated
     */
	ValidatesType createValidatesType();

	/**
     * Returns a new object of class '<em>Java Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Java Operation Selector Type</em>'.
     * @generated
     */
	JavaOperationSelectorType createJavaOperationSelectorType();

	/**
     * Returns a new object of class '<em>Regex Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Regex Operation Selector Type</em>'.
     * @generated
     */
	RegexOperationSelectorType createRegexOperationSelectorType();

	/**
     * Returns a new object of class '<em>Static Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Static Operation Selector Type</em>'.
     * @generated
     */
	StaticOperationSelectorType createStaticOperationSelectorType();

	/**
     * Returns a new object of class '<em>XPath Operation Selector Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>XPath Operation Selector Type</em>'.
     * @generated
     */
	XPathOperationSelectorType createXPathOperationSelectorType();

	/**
     * Returns a new object of class '<em>Security Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Security Type</em>'.
     * @generated
     */
    SecurityType createSecurityType();

    /**
     * Returns a new object of class '<em>Securities Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Securities Type</em>'.
     * @generated
     */
    SecuritiesType createSecuritiesType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	SwitchyardPackage getSwitchyardPackage();

} //SwitchyardFactory
