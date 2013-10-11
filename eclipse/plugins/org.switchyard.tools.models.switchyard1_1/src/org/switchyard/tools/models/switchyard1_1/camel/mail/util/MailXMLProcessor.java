/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.mail.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.switchyard.tools.models.switchyard1_1.camel.mail.MailPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MailXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MailXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        MailPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the MailResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new MailResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new MailResourceFactoryImpl());
        }
        return registrations;
    }

} //MailXMLProcessor
