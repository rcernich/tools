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
package org.jboss.tools.sca.diagram.di;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.jboss.tools.sca.core.ModelHandler;
import org.jboss.tools.switchyard.model.switchyard.DocumentRoot;
import org.jboss.tools.switchyard.model.switchyard.SwitchYardType;

public class DIImport {

	private Diagram diagram;
	private TransactionalEditingDomain domain;
	private ModelHandler modelHandler;
	private IFeatureProvider featureProvider;
	private final IPeService peService = Graphiti.getPeService();

	/**
	 * Look for model diagram interchange information and generate all shapes for the diagrams.
	 * 
	 * NB! Currently only first found diagram is generated.
	 */
	public void generateFromDI() {
		
		final List<DocumentRoot> documentRoots = modelHandler.getAll(DocumentRoot.class);
		System.out.println("-------\n " + documentRoots.toString());
		
		domain.getCommandStack().execute(new RecordingCommand(domain) {
			@Override
			protected void doExecute() {

				if (!documentRoots.isEmpty()) {
					DocumentRoot docRoot = documentRoots.get(0);
					addComposites(docRoot, diagram, featureProvider);
				}
			}

		});
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public void setDomain(TransactionalEditingDomain editingDomain) {
		this.domain = editingDomain;

	}

	public void setModelHandler(ModelHandler modelHandler) {
		this.modelHandler = modelHandler;
	}

	public void setFeatureProvider(IFeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}
	
	private ContainerShape addComposites(DocumentRoot root, Diagram diagram, IFeatureProvider featureProvider ) {
		int x = 20;
		int y = 20;

		SwitchYardType switchyardRoot = root.getSwitchyard();
		if (switchyardRoot != null && switchyardRoot.getComposite() != null) {
			Composite composite = switchyardRoot.getComposite();
	
			// Create the context information
			AddContext addContext = new AddContext();
			addContext.setNewObject(composite);
			addContext.setTargetContainer(diagram);
			addContext.setX(x);
			addContext.setY(y);
	
			IAddFeature addFeature = featureProvider.getAddFeature(addContext);
			if (addFeature.canAdd(addContext)) {
				addFeature.add(addContext);
			}
			
			ContainerShape compositeContainerShape = (ContainerShape)featureProvider.getPictogramElementForBusinessObject(composite).getGraphicsAlgorithm().eContainer();
			
			addCompositeServices(composite, compositeContainerShape, featureProvider, diagram, x, y);
			
			addComponents(composite, compositeContainerShape, featureProvider, diagram, x, y);
			
			return compositeContainerShape;
		}
		return null;
	}
	
	private void addComponents(Composite composite, ContainerShape compositeContainerShape, IFeatureProvider featureProvider, Diagram diagram, int x, int y) {
		EList<Component> components = composite.getComponent();
		for (Iterator<Component> iterator = components.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();

			AddContext addComponentContext = new AddContext();
			addComponentContext.setNewObject(component);
			addComponentContext.setTargetContainer(compositeContainerShape);
			addComponentContext.setX(x);
			addComponentContext.setY(y);

			IAddFeature addComponentFeature = featureProvider.getAddFeature(addComponentContext);
			if (addComponentFeature.canAdd(addComponentContext)) {
				addComponentFeature.add(addComponentContext);
			}
			
			ContainerShape componentContainerShape = (ContainerShape)featureProvider.getPictogramElementForBusinessObject(component).getGraphicsAlgorithm().eContainer();
			
			addComponentServices(component, componentContainerShape, featureProvider, diagram, x, y);

		}
		for (Iterator<Component> iterator = components.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			ContainerShape componentContainerShape = (ContainerShape)featureProvider.getPictogramElementForBusinessObject(component).getGraphicsAlgorithm().eContainer();
			handleComponentReferences(diagram, featureProvider, componentContainerShape);
			handleComponentServiceReferences(diagram, featureProvider, componentContainerShape);
		}
	}
	
	private void addComponentServices ( Component component, ContainerShape componentContainerShape, IFeatureProvider featureProvider, Diagram diagram, int x, int y) {
		EList<ComponentService> services = component.getService();
		for (Iterator<ComponentService> iterator1 = services.iterator(); iterator1.hasNext();) {
			ComponentService service = (ComponentService) iterator1.next();

			if (featureProvider.getPictogramElementForBusinessObject(service) == null) {
				AddContext addServiceContext = new AddContext();
				addServiceContext.setNewObject(service);
				addServiceContext.setTargetContainer(componentContainerShape);
				addServiceContext.setX(x);
				addServiceContext.setY(y);

				IAddFeature addServiceFeature = featureProvider.getAddFeature(addServiceContext);
				if (addServiceFeature != null && addServiceFeature.canAdd(addServiceContext)) {
					addServiceFeature.add(addServiceContext);
				}
			}
		}
	}

	private void addCompositeServices ( Composite composite, ContainerShape compositeContainerShape, IFeatureProvider featureProvider, Diagram diagram, int x, int y) {
		EList<Service> services = composite.getService();
		for (Iterator<Service> iterator = services.iterator(); iterator.hasNext();) {
			Service service = (Service) iterator.next();

			if (featureProvider.getPictogramElementForBusinessObject(service) == null) {
				AddContext addServiceContext = new AddContext();
				addServiceContext.setNewObject(service);
				addServiceContext.setTargetContainer(compositeContainerShape);
				addServiceContext.setX(x);
				addServiceContext.setY(y);

				IAddFeature addServiceFeature = featureProvider.getAddFeature(addServiceContext);
				if (addServiceFeature.canAdd(addServiceContext)) {
					addServiceFeature.add(addServiceContext);
				}
			}
		}
	}
	
	private ContainerShape findShapeWithName ( IFeatureProvider fp, ContainerShape root, String name) {
		if (root instanceof Diagram) {
			Collection<Shape> shapes = peService.getAllContainedShapes(root);
			for (Shape shape : shapes) {
				Object test = fp.getBusinessObjectForPictogramElement(shape);
				String testName = null;
				if (test instanceof Component) {
					Component testcomponent = (Component) test;
					testName = testcomponent.getName();
				} else if (test instanceof Service) {
					Service testservice = (Service) test;
					testName = testservice.getName();
				} else if (test instanceof ComponentService) {
					ComponentService testservice = (ComponentService) test;
					testName = testservice.getName();
				} else if (test instanceof Composite) {
					Composite testcomposite = (Composite) test;
					testName = testcomposite.getName();
				}
				if (testName != null && testName.contentEquals(name)) {
//					System.out.println(testName);
					return (ContainerShape) shape;
				}
			}
		}
		return null;
	}

	private ContainerShape[] findShapesWithName ( IFeatureProvider fp, ContainerShape root, String name) {
		ArrayList<ContainerShape> foundItems = new ArrayList<ContainerShape>();
		if (root instanceof Diagram) {
			Collection<Shape> shapes = peService.getAllContainedShapes(root);
			for (Shape shape : shapes) {
				Object test = fp.getBusinessObjectForPictogramElement(shape);
				String testName = null;
				if (test instanceof Component) {
					Component testcomponent = (Component) test;
					testName = testcomponent.getName();
				} else if (test instanceof Service) {
					Service testservice = (Service) test;
					testName = testservice.getName();
				} else if (test instanceof ComponentService) {
					ComponentService testservice = (ComponentService) test;
					testName = testservice.getName();
				} else if (test instanceof Composite) {
					Composite testcomposite = (Composite) test;
					testName = testcomposite.getName();
				}
				if (testName != null && testName.contentEquals(name) && shape instanceof ContainerShape) {
					foundItems.add((ContainerShape) shape);
				}
			}
		}
		return foundItems.toArray(new ContainerShape[foundItems.size()]);
	}

	private void handleComponentReferences ( Diagram diagram, IFeatureProvider featureProvider, ContainerShape parent ) {
		Object parentObj = featureProvider.getBusinessObjectForPictogramElement(parent);

		if (parentObj instanceof Component) {
			Component component = (Component) parentObj;
			EList<ComponentReference> references = component.getReference();
			for (ComponentReference componentReference : references) {
				String referencedShapeName = componentReference.getName();
				ContainerShape[] targetShapes = findShapesWithName(featureProvider, diagram, referencedShapeName);
				ContainerShape targetShape = null;
				Object targetObj = null;
				for (int i = 0; i < targetShapes.length; i++) {
					Object testObj = featureProvider.getBusinessObjectForPictogramElement(targetShapes[i]);
					if (testObj instanceof ComponentService) {
						targetShape = targetShapes[i];
						targetObj = testObj;
						break;
					} else if (testObj instanceof Component) {
						targetShape = targetShapes[i];
						targetObj = testObj;
						break;
					}
				}
				String targetName = null;
				if (targetObj instanceof Component) {
					Component targetComponent = (Component) targetObj;
					targetName = targetComponent.getName();
				}

				if (targetShape != null) {
					EList<Anchor> targetAnchors = targetShape.getAnchors();
					Anchor target = null;
					for (Anchor anchor : targetAnchors) {
						Object anchorObj = featureProvider.getBusinessObjectForPictogramElement(anchor);
						if (anchorObj instanceof ComponentService) {
							ComponentService cservice = (ComponentService) anchorObj;
							if (cservice.getName().contentEquals(referencedShapeName)) {
								target = anchor;
								break;
							}
						} else if (anchorObj instanceof Service) {
							Service cservice = (Service) anchorObj;
							if (cservice.getName().contentEquals(referencedShapeName)) {
								target = anchor;
								break;
							}
						}
					}
					EList<Anchor> sourceAnchors = parent.getAnchors();
					Anchor source = null;
					for (Anchor anchor : sourceAnchors) {
						Object anchorObj = featureProvider.getBusinessObjectForPictogramElement(anchor);
						if (anchorObj instanceof ComponentReference) {
							ComponentReference cref = (ComponentReference) anchorObj;
							if (cref.getName().contentEquals(referencedShapeName)) {
								source = anchor;
								break;
							}
						} else if (anchorObj instanceof ComponentService) {
							ComponentService cservice = (ComponentService) anchorObj;
							if (cservice.getName().contentEquals(referencedShapeName)) {
								source = anchor;
								break;
							}
						}
					}
					if (source != null && target != null) {
						AddConnectionContext addReferenceContext = new AddConnectionContext(target, source);
						ArrayList<String> targetRef = new ArrayList<String>();
						targetRef.add(targetName);
						addReferenceContext.setNewObject(componentReference);
						addReferenceContext.setTargetContainer((ContainerShape) parent);
		
						IAddFeature addServiceFeature = featureProvider.getAddFeature(addReferenceContext);
						if (addServiceFeature.canAdd(addReferenceContext)) {
							addServiceFeature.add(addReferenceContext);
						}
					}
				}
			}
		}
	}

	private void handleComponentServiceReferences ( Diagram diagram, IFeatureProvider featureProvider, ContainerShape parent ) {
		Object parentObj = featureProvider.getBusinessObjectForPictogramElement(parent);

		if (parentObj instanceof Component) {
			Component component = (Component) parentObj;
			EList<ComponentService> services = component.getService();
			for (ComponentService componentService : services) {
				String referencedShapeName = componentService.getName();
				ContainerShape targetShape = findShapeWithName(featureProvider, diagram, referencedShapeName);
				Object targetObj = featureProvider.getBusinessObjectForPictogramElement(targetShape);
				String sourceName = component.getName();
				String targetName = null;
				if (targetObj instanceof Component) {
					Component targetComponent = (Component) targetObj;
					targetName = targetComponent.getName();
				}

				if (sourceName != null && targetName != null && sourceName.contentEquals(targetName)) {
					return;
				}
				
				if (targetShape != null) {
					EList<Anchor> targetAnchors = targetShape.getAnchors();
					Anchor target = null;
					for (Anchor anchor : targetAnchors) {
						Object anchorObj = featureProvider.getBusinessObjectForPictogramElement(anchor);
						if (anchorObj instanceof ComponentService) {
							ComponentService cservice = (ComponentService) anchorObj;
							if (cservice.getName().contentEquals(referencedShapeName)) {
								target = anchor;
								break;
							}
						} else if (anchorObj instanceof Service) {
							Service service = (Service) anchorObj;
							if (service.getName().contentEquals(referencedShapeName)) {
								target = anchor;
								break;
							}
						}
					}
					EList<Anchor> sourceAnchors = parent.getAnchors();
					Anchor source = null;
					for (Anchor anchor : sourceAnchors) {
						Object anchorObj = featureProvider.getBusinessObjectForPictogramElement(anchor);
						if (anchorObj instanceof ComponentReference) {
							ComponentReference cref = (ComponentReference) anchorObj;
							if (cref.getName().contentEquals(referencedShapeName)) {
								source = anchor;
								break;
							}
						} else if (anchorObj instanceof ComponentService) {
							ComponentService cservice = (ComponentService) anchorObj;
							if (cservice.getName().contains(referencedShapeName)) {
								source = anchor;
								break;
							}
						}
					}
					if (source != null && target != null) {
						AddConnectionContext addReferenceContext = new AddConnectionContext(target, source);
						ArrayList<String> targetRef = new ArrayList<String>();
						targetRef.add(targetName);
						addReferenceContext.setNewObject(componentService);
						addReferenceContext.setTargetContainer((ContainerShape) parent);
		
						IAddFeature addServiceFeature = featureProvider.getAddFeature(addReferenceContext);
						if (addServiceFeature != null && addServiceFeature.canAdd(addReferenceContext)) {
							addServiceFeature.add(addReferenceContext);
						}
					}
				}
			}
		}
	}	
}