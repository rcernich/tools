/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.switchyard.model.validate.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.jboss.tools.switchyard.model.validate.ValidatePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ValidateXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidateXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ValidatePackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ValidateResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ValidateResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ValidateResourceFactoryImpl());
		}
		return registrations;
	}

} //ValidateXMLProcessor
