/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.editor.dom;

import java.util.Arrays;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl;
import org.switchyard.tools.models.switchyard1_0.bean.BeanPackage;
import org.switchyard.tools.models.switchyard1_0.bpm.BPMPackage;
import org.switchyard.tools.models.switchyard1_0.camel.CamelPackage;
import org.switchyard.tools.models.switchyard1_0.camel.amqp.AmqpPackage;
import org.switchyard.tools.models.switchyard1_0.camel.atom.AtomPackage;
import org.switchyard.tools.models.switchyard1_0.camel.core.CorePackage;
import org.switchyard.tools.models.switchyard1_0.camel.file.FilePackage;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.FtpPackage;
import org.switchyard.tools.models.switchyard1_0.camel.jms.JmsPackage;
import org.switchyard.tools.models.switchyard1_0.camel.jpa.JpaPackage;
import org.switchyard.tools.models.switchyard1_0.camel.mail.MailPackage;
import org.switchyard.tools.models.switchyard1_0.camel.netty.NettyPackage;
import org.switchyard.tools.models.switchyard1_0.camel.quartz.QuartzPackage;
import org.switchyard.tools.models.switchyard1_0.camel.sql.SqlPackage;
import org.switchyard.tools.models.switchyard1_0.clojure.ClojurePackage;
import org.switchyard.tools.models.switchyard1_0.http.HttpPackage;
import org.switchyard.tools.models.switchyard1_0.jca.JcaPackage;
import org.switchyard.tools.models.switchyard1_0.resteasy.ResteasyPackage;
import org.switchyard.tools.models.switchyard1_0.rules.RulesPackage;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPPackage;
import org.switchyard.tools.models.switchyard1_0.spring.SpringPackage;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardPackage;
import org.switchyard.tools.models.switchyard1_0.transform.TransformPackage;
import org.switchyard.tools.models.switchyard1_0.validate.ValidatePackage;
import org.switchyard.tools.ui.editor.dom.generic.DocumentRootTranslator;
import org.switchyard.tools.ui.editor.dom.generic.EMF2DOMSSETranslatorResourceImpl;
import org.switchyard.tools.ui.editor.dom.generic.PackageExtensionsManager;

/**
 * SwitchYardTranslatorResourceImpl
 * 
 * <p/>
 * Resource that supports integration with SSE.
 */
@SuppressWarnings({"restriction", "unchecked" })
public class SwitchYardTranslatorResourceImpl extends EMF2DOMSSETranslatorResourceImpl {

    /**
     * The known packages to SwitchYard. Ideally, these would be contributed
     * through an extension point for better extensibility, but this suffices
     * for now.
     */
    private static final EPackage[] PACKAGES = {ScaPackage.eINSTANCE, SwitchyardPackage.eINSTANCE,
            TransformPackage.eINSTANCE, ValidatePackage.eINSTANCE, BeanPackage.eINSTANCE, BPMPackage.eINSTANCE,
            CamelPackage.eINSTANCE, ClojurePackage.eINSTANCE, RulesPackage.eINSTANCE, SpringPackage.eINSTANCE,
            SOAPPackage.eINSTANCE, HttpPackage.eINSTANCE, JcaPackage.eINSTANCE, ResteasyPackage.eINSTANCE,
            CorePackage.eINSTANCE, AmqpPackage.eINSTANCE, AtomPackage.eINSTANCE, FilePackage.eINSTANCE,
            FtpPackage.eINSTANCE, JmsPackage.eINSTANCE, JpaPackage.eINSTANCE, MailPackage.eINSTANCE,
            NettyPackage.eINSTANCE, QuartzPackage.eINSTANCE, SqlPackage.eINSTANCE };

    private static final Translator ROOT_TRANSLATOR = DocumentRootTranslator.create(SwitchyardPackage.eINSTANCE,
            new PackageExtensionsManager(Arrays.asList(PACKAGES)));

    private Resource _generated;

    /**
     * Create a new SwitchYardTranslatorResourceImpl.
     * 
     * @param uri the resource URI
     */
    public SwitchYardTranslatorResourceImpl(URI uri) {
        super(uri, new SwitchYardEMF2DOMSSERenderer());
    }

    /**
     * @param generated the generated SwitchYard resource.
     */
    public void setGeneratedResource(Resource generated) {
        _generated = generated;
    }

    /**
     * @return the generated SwitchYard resource associated with this source
     *         resource.
     */
    public Resource getGeneratedResource() {
        return _generated;
    }

    @Override
    public EObject getEObject(String uriFragment) {
        EObject object = super.getEObject(uriFragment);
        if (object == null && _generated != null) {
            object = _generated.getEObject(uriFragment);
        }
        return object;
    }

    @Override
    public String getDoctype() {
        return "switchyard-v1.xsd";
    }

    @Override
    public Translator getRootTranslator() {
        return ROOT_TRANSLATOR;
    }

    @Override
    protected String getDefaultPublicId() {
        return "urn:switchyard-config:switchyard:1.0";
    }

    @Override
    protected String getDefaultSystemId() {
        return "switchyard-v1.xsd";
    }

    @Override
    protected int getDefaultVersionID() {
        return 100;
    }

}
