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
 * @author Ivar Meikas
 ******************************************************************************/
package org.jboss.tools.sca.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.jboss.tools.sca.Activator;
import org.jboss.tools.switchyard.model.bean.BeanPackage;
import org.jboss.tools.switchyard.model.bpm.BPMPackage;
import org.jboss.tools.switchyard.model.clojure.ClojurePackage;
import org.jboss.tools.switchyard.model.commonrules.CommonRulesPackage;
import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;
import org.jboss.tools.switchyard.model.rules.RulesPackage;
import org.jboss.tools.switchyard.model.soap.SOAPPackage;
import org.jboss.tools.switchyard.model.switchyard.DocumentRoot;
import org.jboss.tools.switchyard.model.switchyard.SwitchYardType;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardFactory;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceFactoryImpl;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceImpl;
import org.jboss.tools.switchyard.model.transform.TransformPackage;
import org.jboss.tools.switchyard.model.validate.ValidatePackage;
import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELPackage;

public class ModelHandler {

	SwitchyardResourceImpl resource;

	ModelHandler() {
	}

	void createDefinitionsIfMissing() {
		EList<EObject> contents = resource.getContents();

		if (contents.isEmpty() || !(contents.get(0) instanceof DocumentRoot)) {
			
			System.out.println("********EMPTY FILE MEANS DIDN'T READ RIGHT*************");
//			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
//
//			if (domain != null) {
//
//				domain.getCommandStack().execute(new RecordingCommand(domain) {
//					@Override
//					protected void doExecute() {
//						DocumentRoot docRoot = createSY(DocumentRoot.class);
//						SwitchYardType switchyardRoot = SwitchyardFactory.eINSTANCE.createSwitchYardType();
//				        Composite compositeRoot = ScaFactory.eINSTANCE.createComposite();
//				        compositeRoot.setName("composite");
//						switchyardRoot.setComposite(compositeRoot);
//						docRoot.setSwitchyard(switchyardRoot);
//						resource.getContents().add(docRoot);
//					}
//				});
//				return;
//			}
		}
	}

	public static ModelHandler getInstance(EObject object) throws IOException {
		return ModelHandlerLocator.getModelHandler(object.eResource());
	}

	public SwitchyardResourceImpl getResource() {
		return resource;
	}

	public void save() {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					saveResource();
				}
			});
		} else {
			saveResource();
		}
	}

	private void saveResource() {
		// disabling save for now, as it's not working and is corrupting things
		try {
//			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
//			Map<String, Object> m = reg.getExtensionToFactoryMap();
//			m.put("website", new XMIResourceFactoryImpl());
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			Activator.logError(e);
		}
	}

	void loadResource() {
		URI fileuri = resource.getURI();

		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resource);
		ResourceSet resourceSet = editingDomain.getResourceSet();

		ExtendedMetaData extendedMetaData = 
				new BasicExtendedMetaData(resourceSet.getPackageRegistry());
		editingDomain.getResourceSet().getLoadOptions().put
			(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
					new SwitchyardResourceFactoryImpl());

		// Register the package to make it available during loading.
		resourceSet.getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200912", ScaPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put ("urn:switchyard-config:switchyard:1.0", SwitchyardPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-config:transform:1.0", TransformPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-bean:config:1.0", BeanPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-config:validate:1.0", ValidatePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-soap:config:1.0", SOAPPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-rules:config:1.0", RulesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-hornetq:config:1.0", HornetQPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-common-rules:config:1.0", CommonRulesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-clojure:config:1.0", ClojurePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("urn:switchyard-component-bpm:config:1.0", BPMPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200903", BPELPackage.eINSTANCE);

		try {
			resource = (SwitchyardResourceImpl) resourceSet.getResource(fileuri, true);
		} catch (WrappedException we) {
			resource = (SwitchyardResourceImpl) resourceSet.getResource(fileuri, true);
		} catch (Exception e) {
			resource = (SwitchyardResourceImpl) resourceSet.getResource(fileuri, true);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(final Class<T> class1) {
		ArrayList<T> l = new ArrayList<T>();
		TreeIterator<EObject> contents = resource.getAllContents();
		for (; contents.hasNext();) {
			Object t = contents.next();
			if (class1.isInstance(t)) {
				l.add((T) t);
			}
		}
		return l;
	}

	/**
	 * General-purpose factory method that sets appropriate default values for new objects.
	 */
	public EObject create(EClass eClass) {
		EObject newObject = null;
		EPackage pkg = eClass.getEPackage();
		EFactory factory = pkg.getEFactoryInstance();
		newObject = factory.create(eClass);
		initialize(newObject);
		return newObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EObject> T createSY(Class<T> clazz) {
		EObject newObject = null;
		EClassifier eClassifier = SwitchyardPackage.eINSTANCE.getEClassifier(clazz.getSimpleName());
		if (eClassifier instanceof EClass) {
			EClass eClass = (EClass)eClassifier;
			newObject = SwitchyardFactory.eINSTANCE.create(eClass);
		}
		
		if (newObject!=null) {
			initialize(newObject);
		}

		return (T)newObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EObject> T createSCA(Class<T> clazz) {
		EObject newObject = null;
		EClassifier eClassifier = ScaPackage.eINSTANCE.getEClassifier(clazz.getSimpleName());
		if (eClassifier instanceof EClass) {
			EClass eClass = (EClass)eClassifier;
			newObject = ScaFactory.eINSTANCE.create(eClass);
		}
		
		if (newObject!=null) {
			initialize(newObject);
		}

		return (T)newObject;
	}

	public void initialize(EObject newObject) {
		if (newObject!=null) {
//			if (newObject.eClass().getEPackage() == Bpmn2Package.eINSTANCE) {
//				// Set appropriate default values for the object features here
//				switch (newObject.eClass().getClassifierID()) {
//				case Bpmn2Package.ITEM_DEFINITION:
//					((ItemDefinition)newObject).setItemKind(ItemKind.INFORMATION);
//				}
//			}
			
			// if the object has an "id", assign it now.
//			String id = ModelUtil.setID(newObject,resource);
			// also set a default name
//			EStructuralFeature feature = newObject.eClass().getEStructuralFeature("name");
//			if (feature!=null) {
//				if (id!=null)
//					newObject.eSet(feature, ModelUtil.toDisplayName(id));
//				else
//					newObject.eSet(feature, "New "+ModelUtil.toDisplayName(newObject.eClass().getName()));
//			}
		}
	}
}