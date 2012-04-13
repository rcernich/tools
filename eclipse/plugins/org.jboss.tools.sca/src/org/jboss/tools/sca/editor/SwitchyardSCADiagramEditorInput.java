/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.jboss.tools.sca.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.ui.part.FileEditorInput;
import org.jboss.tools.switchyard.model.bean.BeanPackage;
import org.jboss.tools.switchyard.model.bpm.BPMPackage;
import org.jboss.tools.switchyard.model.clojure.ClojurePackage;
import org.jboss.tools.switchyard.model.commonrules.CommonRulesPackage;
import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;
import org.jboss.tools.switchyard.model.rules.RulesPackage;
import org.jboss.tools.switchyard.model.soap.SOAPPackage;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceFactoryImpl;
import org.jboss.tools.switchyard.model.transform.TransformPackage;
import org.jboss.tools.switchyard.model.validate.ValidatePackage;
import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELPackage;

public final class SwitchyardSCADiagramEditorInput extends DiagramEditorInput {

	private final TransactionalEditingDomain domain;

	SwitchyardSCADiagramEditorInput(URI diagramUri, TransactionalEditingDomain domain, String providerId) {
		super(diagramUri, domain, providerId);
		this.domain = domain;
	}

	@Override
	public boolean equals(Object obj) {
		boolean superEquals = super.equals(obj);
		if (superEquals) {
			return true;
		}

		ExtendedMetaData extendedMetaData = 
				new BasicExtendedMetaData(editingDomain.getResourceSet().getPackageRegistry());
		domain.getResourceSet().getLoadOptions().put
			(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		domain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
					new SwitchyardResourceFactoryImpl());

		// Register the package to make it available during loading.
		domain.getResourceSet().getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200912", ScaPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put ("urn:switchyard-config:switchyard:1.0", SwitchyardPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-config:transform:1.0", TransformPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-bean:config:1.0", BeanPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-config:validate:1.0", ValidatePackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-soap:config:1.0", SOAPPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-rules:config:1.0", RulesPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-hornetq:config:1.0", HornetQPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-common-rules:config:1.0", CommonRulesPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-clojure:config:1.0", ClojurePackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-bpm:config:1.0", BPMPackage.eINSTANCE);
		domain.getResourceSet().getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200903", BPELPackage.eINSTANCE);

		// Eclipse makes FileEditorInputs for files to be opened. Here we check if the file is actually the same
		// as the DiagramEditorInput uses. This is for preventing opening new editors for the same file.
		if (obj instanceof FileEditorInput) {

			String path = ((FileEditorInput) obj).getFile().getFullPath().toString();
			URI platformUri = URI.createPlatformResourceURI(path, true);

			Resource resource = null;
			
			try {
				resource = editingDomain.getResourceSet().getResource(platformUri, true);
			} catch (WrappedException we) {
				resource = editingDomain.getResourceSet().getResource(platformUri, true);
			} catch (Exception e) {
				resource = editingDomain.getResourceSet().getResource(platformUri, true);
			}
			if (resource != null) return true;

		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
//		if (adapter.equals(TransactionalEditingDomain.class)) {
//			return new Bpmn2TransactionalEditingDomain();
//		}
		return super.getAdapter(adapter);
	}

//	public class Bpmn2TransactionalEditingDomain implements TransactionalEditingDomain {
//		
//	}
}