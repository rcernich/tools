package org.jboss.tools.sca.handlers;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.soa.sca.sca1_1.model.sca.impl.ScaPackageImpl;
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
import org.w3c.dom.Document;

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

	private void getWSDLFiles ( ArrayList<IResource> list, IResource[] members ) throws CoreException {
		for (int i = 0; i < members.length; i++) {
			IResource resource = members[i];
			if (resource instanceof IFile) {
				String fileExtention = resource.getFileExtension();
				if (fileExtention.equalsIgnoreCase("wsdl")) {
					IFile wsdl = (IFile) members[i];
					list.add(wsdl);
				}
			} else if (resource instanceof IFolder) {
				IFolder folder = (IFolder) members[i];
				IResource[] newmembers = folder.members();
				getWSDLFiles(list, newmembers);
			}
		}
	}
	
	private IResource[] getWSDLFiles() {
		ArrayList<IResource> list = new ArrayList<IResource>();
		if (project != null) {
			try {
				IResource[] members = project.members();
				getWSDLFiles(list, members);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return (IResource[]) list.toArray(new IResource[list.size()]);
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

//		Map<String, Object> opts = new HashMap<String, Object>();
//		opts.put(XMLResource.OPTION_EXTENDED_META_DATA, new Boolean(true));//or you could specify an ExtendedMetaData instance
//		Resource resource = ...
//		InputStream is = ...
//		resource.load(is, opts);

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
//		editingDomain.getResourceSet().getPackageRegistry().put(null, SwitchyardPackage.eINSTANCE);

		// Register the appropriate resource factory
		// to handle all file extensions.
//		editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
//			("xml", new SwitchyardResourceFactoryImpl());
//		editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put
//			("xml", new ScaResourceFactoryImpl());

//		IResource[] wsdls = getWSDLFiles();
//		if (wsdls != null && wsdls.length > 0) {
//			for (int i = 0; i < wsdls.length; i++) {
//				IResource wsdlFile = wsdls[i];
//				URI fileURI = URI.createPlatformResourceURI(wsdlFile.getFullPath().toPortableString());
//				java.net.URI wsdlURI = wsdlFile.getLocationURI();
//				editingDomain.getResourceSet().createResource(fileURI);
//			}
//		}
		
//		EcoreUtil.resolveAll(editingDomain.getResourceSet());
		
//		editingDomain.getResourceSet().createResource(uri)
		
		
		Resource resource = null;
		
		try {
			resource = editingDomain.getResourceSet().getResource(fileuri, true);
		} catch (WrappedException we) {
			if (we.getCause() instanceof UnresolvedReferenceException) {
				// try again
				resource = editingDomain.getResourceSet().getResource(fileuri, true);
			}
		}

		try {
			
//			demonstrateEMFDOMLoadAndTraverse(fileuri);
//			EObject documentRoot = (EObject)resource.getContents().get(0);
//			AnyType rootTreeNode = (AnyType)documentRoot.eContents().get(0);		  

			// Demand load the resource into the resource set.
//			Resource resource = editingDomain.getResourceSet().getResource(fileuri, true);
			// Extract the root object from the resource.
			if (resource == null || resource.getContents().size() == 0) 
				return;
			
			DocumentRoot rootNode = (DocumentRoot)resource.getContents().get(0);
			SwitchYardType switchyardRoot = rootNode.getSwitchyard();
			Composite composite = switchyardRoot.getComposite();
			
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
	
			// Add all classes to diagram
			int x = 20;
			int y = 20;
	
			// Create the context information
			AddContext addContext = new AddContext();
			addContext.setNewObject(composite);
			addContext.setTargetContainer(diagram);
			addContext.setX(x);
			addContext.setY(y);
			x = x + 20;
			y = y + 20;
	
			IAddFeature addFeature = featureProvider.getAddFeature(addContext);
			if (addFeature.canAdd(addContext)) {
				addFeature.add(addContext);
			}
			
			ContainerShape compositeContainerShape = (ContainerShape)featureProvider.getPictogramElementForBusinessObject(composite).getGraphicsAlgorithm().eContainer();
			
			EList<Component> components = composite.getComponent();
			for (Iterator<Component> iterator = components.iterator(); iterator.hasNext();) {
				Component component = (Component) iterator.next();

				AddContext addComponentContext = new AddContext();
				addComponentContext.setNewObject(component);
				addComponentContext.setTargetContainer(compositeContainerShape);
				addComponentContext.setX(x);
				addComponentContext.setY(y);
				x = x + 20;
				y = y + 20;

				IAddFeature addComponentFeature = featureProvider.getAddFeature(addComponentContext);
				if (addComponentFeature.canAdd(addComponentContext)) {
					addComponentFeature.add(addComponentContext);
				}
				
				ContainerShape componentContainerShape = (ContainerShape)featureProvider.getPictogramElementForBusinessObject(component).getGraphicsAlgorithm().eContainer();

				EList<ComponentService> services = component.getService();
				for (Iterator<ComponentService> iterator1 = services.iterator(); iterator1.hasNext();) {
					ComponentService service = (ComponentService) iterator1.next();

					AddContext addServiceContext = new AddContext();
					addServiceContext.setNewObject(service);
					addServiceContext.setTargetContainer(componentContainerShape);
					addServiceContext.setX(x);
					addServiceContext.setY(y);
					x = x + 20;
					y = y + 20;

					IAddFeature addServiceFeature = featureProvider.getAddFeature(addServiceContext);
					if (addServiceFeature != null && addServiceFeature.canAdd(addServiceContext)) {
						addServiceFeature.add(addServiceContext);
					}
				}
				
				handleReferences(diagram, featureProvider, diagram);
			}

			EList<Service> services = composite.getService();
			for (Iterator<Service> iterator = services.iterator(); iterator.hasNext();) {
				Service service = (Service) iterator.next();

				AddContext addServiceContext = new AddContext();
				addServiceContext.setNewObject(service);
				addServiceContext.setTargetContainer(compositeContainerShape);
				addServiceContext.setX(x);
				addServiceContext.setY(y);
				x = x + 20;
				y = y + 20;

				IAddFeature addServiceFeature = featureProvider.getAddFeature(addServiceContext);
				if (addServiceFeature.canAdd(addServiceContext)) {
					addServiceFeature.add(addServiceContext);
				}
			}
			
			handleReferences(diagram, featureProvider, diagram);
			
		} catch (WrappedException we) {
			we.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ContainerShape findShapeWithName ( IFeatureProvider fp, ContainerShape root, String name) {
		EList<Shape> shapes = root.getChildren();
		ContainerShape targetShape = null;
		for (Shape shape : shapes) {
			Object test = fp.getBusinessObjectForPictogramElement(shape);
			if (test instanceof Component) {
				Component testcomponent = (Component) test;
				if (testcomponent.getName().contentEquals(name)) {
					targetShape = (ContainerShape) shape;
					return targetShape;
				}
			}
			if (shape instanceof ContainerShape) {
				ContainerShape testShape = (ContainerShape) shape;
				return findShapeWithName(fp, testShape, name);
			}
		}
		return null;
	}

	private void handleReferences(Diagram diagram, IFeatureProvider featureProvider, ContainerShape parent) {
		
		Object parentObj = featureProvider.getBusinessObjectForPictogramElement(parent);
		if (parentObj instanceof Composite) {
			Composite composite = (Composite) parentObj;
			EList<Reference> references = composite.getReference();
			for (Reference reference : references) {
				System.out.println(reference.toString());

				AddContext addReferenceContext = new AddContext();
				addReferenceContext.setNewObject(reference);
				addReferenceContext.setTargetContainer((ContainerShape) parent);

				IAddFeature addServiceFeature = featureProvider.getAddFeature(addReferenceContext);
				if (addServiceFeature.canAdd(addReferenceContext)) {
					addServiceFeature.add(addReferenceContext);
				}
			}
		} else if (parentObj instanceof Component) {
			Component component = (Component) parentObj;
			EList<ComponentReference> references = component.getReference();
			for (ComponentReference componentReference : references) {
				System.out.println(componentReference.toString());

				ContainerShape targetShape = findShapeWithName(featureProvider, diagram, componentReference.getName());
				Object targetObj = featureProvider.getBusinessObjectForPictogramElement(targetShape);
				String sourceName = component.getName();
				String targetName = null;
				if (targetObj instanceof Component) {
					Component targetComponent = (Component) targetObj;
					targetName = targetComponent.getName();
				}

//				if (targetShape != null) {
//					AddConnectionContext addReferenceContext = new AddConnectionContext(targetShape.getAnchors().get(1), parent.getAnchors().get(0));
//					Reference eReference = ScaFactory.eINSTANCE.createReference();
//					eReference.setName(sourceName);
//					ArrayList<String> targetRef = new ArrayList<String>();
//					targetRef.add(targetName);
//					eReference.setTarget(targetRef);
//					eReference.eResource().s
//					addReferenceContext.setNewObject(eReference);
//					addReferenceContext.setTargetContainer((ContainerShape) parent);
//	
//					IAddFeature addServiceFeature = featureProvider.getAddFeature(addReferenceContext);
//					if (addServiceFeature.canAdd(addReferenceContext)) {
//						addServiceFeature.add(addReferenceContext);
//					}
//				}
			}
		}
		EList<Shape> shapes = parent.getChildren();
		for (Shape shape : shapes) {
			if (shape instanceof ContainerShape) {
				handleReferences(diagram, featureProvider, (ContainerShape) shape);
			}
		}
	}
	
	/**
	 * @return the createdResource
	 */
	public Resource getCreatedResource() {
		return createdResource;
	}

	 public static void demonstrateEMFDOMLoadAndTraverse(URI fileURI) throws Exception
	  {
	    System.out.println("EMF DOM Load And Traverse");
	    
	    // Set up the same context as was used during save.
	    //
	    ResourceSet resourceSet = new ResourceSetImpl();
	    final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(resourceSet.getPackageRegistry());
	    resourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
	    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
	      (Resource.Factory.Registry.DEFAULT_EXTENSION, 
	       new GenericXMLResourceFactoryImpl());
	       
	    // Demand load the resource.
	    //
	    Resource resource = resourceSet.getResource(fileURI, true);
	    
	    // Display what was loaded.
	    //
	    resource.save(System.out, null);
	    System.out.println();
	    
	    System.out.println("Traversing EMF AnyType");
	    
	    // Get the document root instance from the resource
	    // and get the root object from that.
	    // We'll assume that it's an instance of AnyType (which isn't a good general assumption).
	    //
	    EObject documentRoot = (EObject)resource.getContents().get(0);
	    AnyType rootTreeNode = (AnyType)documentRoot.eContents().get(0);
	    
	    // Cache the label feature we will use during the traversal.
	    //
	    final EStructuralFeature labelAttribute = extendedMetaData.demandFeature(null, "label", false);
	    
	    // Traverse the root tree node.
	    //
	    new Object()
	    {
	      public void traverse(String indent, AnyType anyType)
	      {
	        // Print out the element namespace and name.
	        //
	        System.out.println(indent + "{" + extendedMetaData.getNamespace(anyType.eContainmentFeature()) + "}" + 
	                                          extendedMetaData.getName(anyType.eContainmentFeature()));
	        // Print out the label.
	        //
	        System.out.println(indent + "  label=" + anyType.eGet(labelAttribute));
	        
	        // Visit the mixed content.
	        //
	        FeatureMap featureMap = anyType.getMixed();
	        for (int i = 0, size = featureMap.size(); i < size; ++i)
	        {
	          // Consider each type of feature (in this example).
	          //
	          EStructuralFeature feature = featureMap.getEStructuralFeature(i);
	          if (FeatureMapUtil.isText(feature))
	          {
	            // Print out the text.
	            //
	            System.out.println(indent + "  '" + featureMap.getValue(i).toString().replaceAll("\n", "\\\\n") + "'");
	          }
	          else if (FeatureMapUtil.isComment(feature))
	          {
	            // Print out the comment.
	            //
	            System.out.println(indent + "  <!--" +  featureMap.getValue(i) + "-->");
	          }
	          else if (FeatureMapUtil.isCDATA(feature))
	          {
	            // Print out the CDATA.
	            //
	            System.out.println(indent + "  <![CDATA[" + featureMap.getValue(i) + "]]>");
	          }
	          else if (feature instanceof EReference)
	          {
	            // Visit the child.
	            //
	            traverse(indent + "  ", (AnyType)featureMap.getValue(i));
	          }
	        }
	      }
	    }.traverse("", rootTreeNode);
	    
	    System.out.println("Result of EMF to DOM conversion");
	    
	    // An XML resource can be converted directly to a DOM document,
	    // and it's possible to record a map between the two representations.
	    // We'll do that and display the result of DOM as before
	    //
	    Document document = ((XMLResource)resource).save(null, null, null);
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.transform(new DOMSource(document), new StreamResult(System.out));
	    System.out.println();
	  }
	  }
