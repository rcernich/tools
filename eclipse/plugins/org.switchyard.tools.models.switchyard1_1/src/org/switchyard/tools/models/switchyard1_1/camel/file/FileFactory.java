/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.file;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.camel.file.FilePackage
 * @generated
 */
public interface FileFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FileFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.camel.file.impl.FileFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Additional Uri Parameters Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Additional Uri Parameters Type</em>'.
     * @generated
     */
    AdditionalUriParametersType createAdditionalUriParametersType();

    /**
     * Returns a new object of class '<em>Base Camel Binding</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Base Camel Binding</em>'.
     * @generated
     */
    BaseCamelBinding createBaseCamelBinding();

    /**
     * Returns a new object of class '<em>Camel File Binding Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Camel File Binding Type</em>'.
     * @generated
     */
    CamelFileBindingType createCamelFileBindingType();

    /**
     * Returns a new object of class '<em>Consumer Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Consumer Type</em>'.
     * @generated
     */
    FileConsumerType createFileConsumerType();

    /**
     * Returns a new object of class '<em>Producer Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Producer Type</em>'.
     * @generated
     */
    FileProducerType createFileProducerType();

    /**
     * Returns a new object of class '<em>Parameter Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Type</em>'.
     * @generated
     */
    ParameterType createParameterType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FilePackage getFilePackage();

} //FileFactory
