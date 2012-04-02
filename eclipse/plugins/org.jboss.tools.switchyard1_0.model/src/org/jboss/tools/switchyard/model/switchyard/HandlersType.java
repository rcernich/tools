/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.switchyard;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Handlers Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.HandlersType#getHandler <em>Handler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage#getHandlersType()
 * @model extendedMetaData="name='HandlersType' kind='elementOnly'"
 * @generated
 */
public interface HandlersType extends EObject {
	/**
	 * Returns the value of the '<em><b>Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.jboss.tools.switchyard.model.switchyard.HandlerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler</em>' containment reference list.
	 * @see org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage#getHandlersType_Handler()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='handler' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<HandlerType> getHandler();

} // HandlersType
