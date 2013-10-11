/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.http;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.http.HttpPackage
 * @generated
 */
public interface HttpFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    HttpFactory eINSTANCE = org.switchyard.tools.models.switchyard1_1.http.impl.HttpFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>HTTP Binding Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>HTTP Binding Type</em>'.
     * @generated
     */
    HTTPBindingType createHTTPBindingType();

    /**
     * Returns a new object of class '<em>Basic Authentication Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Basic Authentication Type</em>'.
     * @generated
     */
    BasicAuthenticationType createBasicAuthenticationType();

    /**
     * Returns a new object of class '<em>NTLM Authentication Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>NTLM Authentication Type</em>'.
     * @generated
     */
    NTLMAuthenticationType createNTLMAuthenticationType();

    /**
     * Returns a new object of class '<em>Proxy Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Proxy Type</em>'.
     * @generated
     */
    ProxyType createProxyType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    HttpPackage getHttpPackage();

} //HttpFactory
