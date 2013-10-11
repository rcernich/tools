/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.netty;

import org.switchyard.tools.models.switchyard1_1.switchyard.ContextMapperType;
import org.switchyard.tools.models.switchyard1_1.switchyard.MessageComposerType;
import org.switchyard.tools.models.switchyard1_1.switchyard.SwitchYardBindingType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Camel Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getContextMapper <em>Context Mapper</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getMessageComposer <em>Message Composer</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getAdditionalUriParameters <em>Additional Uri Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.camel.netty.NettyPackage#getBaseCamelBinding()
 * @model extendedMetaData="name='BaseCamelBinding' kind='elementOnly'"
 * @generated
 */
public interface BaseCamelBinding extends SwitchYardBindingType {

    /**
     * Returns the value of the '<em><b>Context Mapper</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context Mapper</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Context Mapper</em>' containment reference.
     * @see #setContextMapper(ContextMapperType)
     * @see org.switchyard.tools.models.switchyard1_1.camel.netty.NettyPackage#getBaseCamelBinding_ContextMapper()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='contextMapper' namespace='##targetNamespace'"
     * @generated
     */
    ContextMapperType getContextMapper();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getContextMapper <em>Context Mapper</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context Mapper</em>' containment reference.
     * @see #getContextMapper()
     * @generated
     */
    void setContextMapper(ContextMapperType value);

    /**
     * Returns the value of the '<em><b>Message Composer</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Composer</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Composer</em>' containment reference.
     * @see #setMessageComposer(MessageComposerType)
     * @see org.switchyard.tools.models.switchyard1_1.camel.netty.NettyPackage#getBaseCamelBinding_MessageComposer()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='messageComposer' namespace='##targetNamespace'"
     * @generated
     */
    MessageComposerType getMessageComposer();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getMessageComposer <em>Message Composer</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Composer</em>' containment reference.
     * @see #getMessageComposer()
     * @generated
     */
    void setMessageComposer(MessageComposerType value);

    /**
     * Returns the value of the '<em><b>Additional Uri Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Additional Uri Parameters</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Additional Uri Parameters</em>' containment reference.
     * @see #setAdditionalUriParameters(AdditionalUriParametersType)
     * @see org.switchyard.tools.models.switchyard1_1.camel.netty.NettyPackage#getBaseCamelBinding_AdditionalUriParameters()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='additionalUriParameters' namespace='##targetNamespace'"
     * @generated
     */
    AdditionalUriParametersType getAdditionalUriParameters();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.netty.BaseCamelBinding#getAdditionalUriParameters <em>Additional Uri Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Additional Uri Parameters</em>' containment reference.
     * @see #getAdditionalUriParameters()
     * @generated
     */
    void setAdditionalUriParameters(AdditionalUriParametersType value);
} // BaseCamelBinding
