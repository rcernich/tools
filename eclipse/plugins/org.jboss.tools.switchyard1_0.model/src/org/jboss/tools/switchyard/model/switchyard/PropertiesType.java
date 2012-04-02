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
 * A representation of the model object '<em><b>Properties Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jboss.tools.switchyard.model.switchyard.PropertiesType#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage#getPropertiesType()
 * @model extendedMetaData="name='PropertiesType' kind='elementOnly'"
 * @generated
 */
public interface PropertiesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.jboss.tools.switchyard.model.switchyard.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage#getPropertiesType_Property()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PropertyType> getProperty();

} // PropertiesType
