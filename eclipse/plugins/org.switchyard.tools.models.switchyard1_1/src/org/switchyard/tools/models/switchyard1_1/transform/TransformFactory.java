/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.transform;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.transform.TransformPackage
 * @generated
 */
public interface TransformFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	TransformFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.transform.impl.TransformFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
	DocumentRoot createDocumentRoot();

	/**
     * Returns a new object of class '<em>Java Transform Type1</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Java Transform Type1</em>'.
     * @generated
     */
	JavaTransformType1 createJavaTransformType1();

	/**
     * Returns a new object of class '<em>JAXB Transform Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>JAXB Transform Type</em>'.
     * @generated
     */
	JAXBTransformType createJAXBTransformType();

	/**
     * Returns a new object of class '<em>Json Transform Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Json Transform Type</em>'.
     * @generated
     */
	JsonTransformType createJsonTransformType();

	/**
     * Returns a new object of class '<em>Smooks Transform Type1</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Smooks Transform Type1</em>'.
     * @generated
     */
	SmooksTransformType1 createSmooksTransformType1();

	/**
     * Returns a new object of class '<em>Xslt Transform Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Xslt Transform Type</em>'.
     * @generated
     */
	XsltTransformType createXsltTransformType();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	TransformPackage getTransformPackage();

} //TransformFactory
