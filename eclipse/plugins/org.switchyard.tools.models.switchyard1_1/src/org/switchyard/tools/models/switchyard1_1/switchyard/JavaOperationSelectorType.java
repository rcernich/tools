/**
 */
package org.switchyard.tools.models.switchyard1_1.switchyard;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Operation Selector Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 Java Operation Selector Configuration.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.switchyard.JavaOperationSelectorType#getClass_ <em>Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage#getJavaOperationSelectorType()
 * @model extendedMetaData="name='JavaOperationSelectorType' kind='empty'"
 * @generated
 */
public interface JavaOperationSelectorType extends SwitchYardOperationSelectorType {
	/**
     * Returns the value of the '<em><b>Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *                             operation selector implementation class name.
     *                         
     * <!-- end-model-doc -->
     * @return the value of the '<em>Class</em>' attribute.
     * @see #setClass(String)
     * @see org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage#getJavaOperationSelectorType_Class()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='class'"
     * @generated
     */
	String getClass_();

	/**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.switchyard.JavaOperationSelectorType#getClass_ <em>Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class</em>' attribute.
     * @see #getClass_()
     * @generated
     */
	void setClass(String value);

} // JavaOperationSelectorType
