/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.components.camel.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.switchyard.tools.models.switchyard1_1.camel.CamelFactory;
import org.switchyard.tools.models.switchyard1_1.camel.CamelImplementationType;
import org.switchyard.tools.models.switchyard1_1.camel.XMLDSLType;
import org.switchyard.tools.models.switchyard1_0.spring.DocumentRoot;
import org.switchyard.tools.models.switchyard1_0.spring.FromDefinition;
import org.switchyard.tools.models.switchyard1_0.spring.LogDefinition;
import org.switchyard.tools.models.switchyard1_0.spring.RouteDefinition;
import org.switchyard.tools.models.switchyard1_0.spring.RoutesDefinition;
import org.switchyard.tools.models.switchyard1_0.spring.SpringFactory;
import org.switchyard.tools.models.switchyard1_0.spring.SpringPackage;
import org.switchyard.tools.models.switchyard1_0.spring.util.SpringResourceFactoryImpl;
import org.switchyard.tools.ui.editor.Activator;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.shared.BaseNewServiceFileWizard;

/**
 * NewCamelXMLRouteCompenentWizard
 * 
 * <p/>
 * Creates a new Camel implementation based on an XML route definition.
 * 
 * @author bfitzpat
 * @author Rob Cernich
 */
public class NewCamelXMLRouteCompenentWizard extends BaseNewServiceFileWizard {

    /**
     * Create a new NewCamelXMLRouteCompenentWizard.
     * 
     * @param openAfterCreate true if the resource should be opened after
     *            creation.
     */
    public NewCamelXMLRouteCompenentWizard(boolean openAfterCreate) {
        super(openAfterCreate, "xml"); //$NON-NLS-1$
        setWindowTitle(Messages.title_newCamelXmlRouteFile);
    }

    @Override
    public void addPages() {
        super.addPages();

        WizardNewFileCreationPage page = getFileCreationPage();
        page.setTitle(Messages.title_routeFile);
        page.setDescription(Messages.description_routeFile);
        if (getService() == null) {
            page.setFileName("route.xml"); //$NON-NLS-1$
        } else {
            page.setFileName("" + getService().getName() + "Route.xml"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    @Override
    protected Implementation createImplementation() {
        XMLDSLType xmlType = CamelFactory.eINSTANCE.createXMLDSLType();
        xmlType.setPath(getCreatedFilePath());

        CamelImplementationType implementation = CamelFactory.eINSTANCE.createCamelImplementationType();
        implementation.setXml(xmlType);

        return implementation;
    }

    @Override
    protected List<ComponentReference> createReferences() {
        return Collections.emptyList();
    }

    @Override
    protected InputStream getInitialContents() {
        ByteArrayOutputStream baos = null;
        try {
            DocumentRoot doc = SpringFactory.eINSTANCE.createDocumentRoot();
            RoutesDefinition routes = SpringFactory.eINSTANCE.createRoutesDefinition();
            RouteDefinition route = SpringFactory.eINSTANCE.createRouteDefinition();
            FromDefinition from = SpringFactory.eINSTANCE.createFromDefinition();
            LogDefinition log = SpringFactory.eINSTANCE.createLogDefinition();
            String serviceName = getService().getName();

            from.setUri("switchyard://" + serviceName); //$NON-NLS-1$
            log.setMessage("" + serviceName + " - message received: ${body}"); //$NON-NLS-1$ //$NON-NLS-2$

            doc.setRoutes(routes);
            doc.setRoute(route);
            route.getFrom().add(from);
            route.getLog().add(log);
            routes.getRoute().add(route);

            doc.getXMLNSPrefixMap().put("", SpringPackage.eNS_URI); //$NON-NLS-1$

            Resource routeResource = new SpringResourceFactoryImpl().createResource(URI.createGenericURI("temp", //$NON-NLS-1$
                    "temp", null)); //$NON-NLS-1$
            routeResource.getContents().add(doc);

            baos = new ByteArrayOutputStream();
            routeResource.save(baos, null);
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException e2) {
            e2.printStackTrace();
            Activator.logError(e2);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.fillInStackTrace();
                }
            }
        }
        return null;
    }

}
