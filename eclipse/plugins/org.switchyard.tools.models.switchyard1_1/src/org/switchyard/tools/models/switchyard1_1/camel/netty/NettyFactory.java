/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.netty;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.camel.netty.NettyPackage
 * @generated
 */
public interface NettyFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NettyFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.camel.netty.impl.NettyFactoryImpl.init();

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
     * Returns a new object of class '<em>Camel Netty Tcp Binding Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Camel Netty Tcp Binding Type</em>'.
     * @generated
     */
    CamelNettyTcpBindingType createCamelNettyTcpBindingType();

    /**
     * Returns a new object of class '<em>Camel Netty Udp Binding Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Camel Netty Udp Binding Type</em>'.
     * @generated
     */
    CamelNettyUdpBindingType createCamelNettyUdpBindingType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Parameter Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Type</em>'.
     * @generated
     */
    ParameterType createParameterType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    NettyPackage getNettyPackage();

} //NettyFactory
