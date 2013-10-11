/**
 */
package org.switchyard.tools.models.switchyard1_1.soap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.soa.sca.sca1_1.model.sca.CommonExtensionBase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interceptors Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.soap.InterceptorsType#getInterceptor <em>Interceptor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.soap.SOAPPackage#getInterceptorsType()
 * @model extendedMetaData="name='InterceptorsType' kind='elementOnly'"
 * @generated
 */
public interface InterceptorsType extends CommonExtensionBase {
    /**
     * Returns the value of the '<em><b>Interceptor</b></em>' containment reference list.
     * The list contents are of type {@link org.switchyard.tools.models.switchyard1_1.soap.InterceptorType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interceptor</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interceptor</em>' containment reference list.
     * @see org.switchyard.tools.models.switchyard1_1.soap.SOAPPackage#getInterceptorsType_Interceptor()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='interceptor' namespace='##targetNamespace'"
     * @generated
     */
    EList<InterceptorType> getInterceptor();

} // InterceptorsType
