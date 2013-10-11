/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.validate;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.validate.ValidatePackage
 * @generated
 */
public interface ValidateFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ValidateFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.validate.impl.ValidateFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
	DocumentRoot createDocumentRoot();

	/**
     * Returns a new object of class '<em>Java Validate Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Java Validate Type</em>'.
     * @generated
     */
	JavaValidateType createJavaValidateType();

	/**
     * Returns a new object of class '<em>Xml Validate Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Xml Validate Type</em>'.
     * @generated
     */
	XmlValidateType createXmlValidateType();

	/**
     * Returns a new object of class '<em>Schema Files Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Schema Files Type</em>'.
     * @generated
     */
    SchemaFilesType createSchemaFilesType();

    /**
     * Returns a new object of class '<em>Schema Catalogs Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Schema Catalogs Type</em>'.
     * @generated
     */
    SchemaCatalogsType createSchemaCatalogsType();

    /**
     * Returns a new object of class '<em>File Entry Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>File Entry Type</em>'.
     * @generated
     */
    FileEntryType createFileEntryType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	ValidatePackage getValidatePackage();

} //ValidateFactory
