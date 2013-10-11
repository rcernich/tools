/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.jca;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JCA Inbound Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.jca.JCAInboundConnection#getResourceAdapter <em>Resource Adapter</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.jca.JCAInboundConnection#getActivationSpec <em>Activation Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.jca.JcaPackage#getJCAInboundConnection()
 * @model extendedMetaData="name='JCAInboundConnection' kind='elementOnly'"
 * @generated
 */
public interface JCAInboundConnection extends EObject {
    /**
     * Returns the value of the '<em><b>Resource Adapter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Adapter</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Adapter</em>' containment reference.
     * @see #setResourceAdapter(ResourceAdapter)
     * @see org.switchyard.tools.models.switchyard1_1.jca.JcaPackage#getJCAInboundConnection_ResourceAdapter()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='resourceAdapter' namespace='##targetNamespace'"
     * @generated
     */
    ResourceAdapter getResourceAdapter();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.jca.JCAInboundConnection#getResourceAdapter <em>Resource Adapter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Adapter</em>' containment reference.
     * @see #getResourceAdapter()
     * @generated
     */
    void setResourceAdapter(ResourceAdapter value);

    /**
     * Returns the value of the '<em><b>Activation Spec</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activation Spec</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activation Spec</em>' containment reference.
     * @see #setActivationSpec(ActivationSpec)
     * @see org.switchyard.tools.models.switchyard1_1.jca.JcaPackage#getJCAInboundConnection_ActivationSpec()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='activationSpec' namespace='##targetNamespace'"
     * @generated
     */
    ActivationSpec getActivationSpec();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.jca.JCAInboundConnection#getActivationSpec <em>Activation Spec</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activation Spec</em>' containment reference.
     * @see #getActivationSpec()
     * @generated
     */
    void setActivationSpec(ActivationSpec value);

} // JCAInboundConnection
