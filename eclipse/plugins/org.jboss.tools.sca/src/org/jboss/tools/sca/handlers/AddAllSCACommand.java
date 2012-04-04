package org.jboss.tools.sca.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
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
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.jboss.tools.switchyard.model.bean.BeanPackage;
import org.jboss.tools.switchyard.model.bpm.BPMPackage;
import org.jboss.tools.switchyard.model.clojure.ClojurePackage;
import org.jboss.tools.switchyard.model.commonrules.CommonRulesPackage;
import org.jboss.tools.switchyard.model.hornetq.HornetQPackage;
import org.jboss.tools.switchyard.model.rules.RulesPackage;
import org.jboss.tools.switchyard.model.soap.SOAPPackage;
import org.jboss.tools.switchyard.model.switchyard.DocumentRoot;
import org.jboss.tools.switchyard.model.switchyard.SwitchYardType;
import org.jboss.tools.switchyard.model.switchyard.SwitchyardPackage;
import org.jboss.tools.switchyard.model.switchyard.util.SwitchyardResourceFactoryImpl;
import org.jboss.tools.switchyard.model.transform.TransformPackage;
import org.jboss.tools.switchyard.model.validate.ValidatePackage;
import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELPackage;

public class AddAllSCACommand extends RecordingCommand {

	private IProject project;
	private TransactionalEditingDomain editingDomain;
	private String diagramName;
	private Resource createdResource;
	private IFile file;
	
	public AddAllSCACommand(IProject project, TransactionalEditingDomain editingDomain, String diagramName, IFile file) {
		super(editingDomain);
		this.project = project;
		this.editingDomain = editingDomain;
		this.diagramName = diagramName;
		this.file = file;
	}

	@Override
	protected void doExecute() {
		// Get all EClasses
		URI fileuri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);

		ExtendedMetaData extendedMetaData = 
				new BasicExtendedMetaData(editingDomain.getResourceSet().getPackageRegistry());
		editingDomain.getResourceSet().getLoadOptions().put
			(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
					new SwitchyardResourceFactoryImpl());

		// Register the package to make it available during loading.
		editingDomain.getResourceSet().getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200912", ScaPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put ("urn:switchyard-config:switchyard:1.0", SwitchyardPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-config:transform:1.0", TransformPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-bean:config:1.0", BeanPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-config:validate:1.0", ValidatePackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-soap:config:1.0", SOAPPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-rules:config:1.0", RulesPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-hornetq:config:1.0", HornetQPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-common-rules:config:1.0", CommonRulesPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-clojure:config:1.0", ClojurePackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("urn:switchyard-component-bpm:config:1.0", BPMPackage.eINSTANCE);
		editingDomain.getResourceSet().getPackageRegistry().put("http://docs.oasis-open.org/ns/opencsa/sca/200903", BPELPackage.eINSTANCE);

		Resource resource = null;
		
		try {
			resource = editingDomain.getResourceSet().getResource(fileuri, true);
		} catch (WrappedException we) {
			resource = editingDomain.getResourceSet().getResource(fileuri, true);
		} catch (Exception e) {
			resource = editingDomain.getResourceSet().getResource(fileuri, true);
		}

		try {
			// Extract the root object from the resource.
			if (resource == null || resource.getContents().size() == 0) 
				return;
			
			DocumentRoot rootNode = (DocumentRoot)resource.getContents().get(0);
			
			// Create the diagram and its file
			Diagram diagram = Graphiti.getPeCreateService().createDiagram("org.jboss.tools.sca.diagram", diagramName, true); //$NON-NLS-1$
			IFolder diagramFolder = project.getFolder("src/diagrams/"); //$NON-NLS-1$
			IFile diagramFile = diagramFolder.getFile(diagramName + ".diagram"); //$NON-NLS-1$
			URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
			createdResource = editingDomain.getResourceSet().createResource(uri);
			createdResource.getContents().add(diagram);
	
			IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(diagram,
					"org.jboss.tools.sca.diagram.SCADiagramTypeProvider"); //$NON-NLS-1$
			IFeatureProvider featureProvider = dtp.getFeatureProvider();
	
			addComposites(rootNode, diagram, featureProvider); 
			
			
		} catch (WrappedException we) {
			we.printStackTrace();
		}
	}
	
	private ContainerShape addComposites(DocumentRoot root, Diagram diagram, IFeatureProvider featureProvider ) {
		int x = 20;
		int y = 20;

		SwitchYardType switchyardRoot = root.getSwitchyard();
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
		
//		handleServiceReferences(diagram, featureProvider, compositeContainerShape);
		
		return compositeContainerShape;
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

//			handleComponentReferences(diagram, featureProvider, componentContainerShape);
//			handleComponentServiceReferences(diagram, featureProvider, componentContainerShape);
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
			IPeService peService = Graphiti.getPeService();
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
			IPeService peService = Graphiti.getPeService();
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


	/**
	 * @return the createdResource
	 */
	public Resource getCreatedResource() {
		return createdResource;
	}

  }
