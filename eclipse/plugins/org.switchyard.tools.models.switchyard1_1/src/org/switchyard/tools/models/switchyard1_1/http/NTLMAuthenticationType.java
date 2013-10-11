/**
 */
package org.switchyard.tools.models.switchyard1_1.http;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NTLM Authentication Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.http.NTLMAuthenticationType#getDomain <em>Domain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.http.HttpPackage#getNTLMAuthenticationType()
 * @model extendedMetaData="name='NTLMAuthenticationType' kind='elementOnly'"
 * @generated
 */
public interface NTLMAuthenticationType extends BasicAuthenticationType {
    /**
     * Returns the value of the '<em><b>Domain</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The NTLM Domain.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Domain</em>' attribute.
     * @see #setDomain(String)
     * @see org.switchyard.tools.models.switchyard1_1.http.HttpPackage#getNTLMAuthenticationType_Domain()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='domain' namespace='##targetNamespace'"
     * @generated
     */
    String getDomain();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.http.NTLMAuthenticationType#getDomain <em>Domain</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Domain</em>' attribute.
     * @see #getDomain()
     * @generated
     */
    void setDomain(String value);

} // NTLMAuthenticationType
