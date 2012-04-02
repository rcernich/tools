package org.jboss.tools.sca.diagram.service;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.jboss.tools.sca.handlers.AddAllSCACommand;

public class SCADiagramAddServiceFeature extends AbstractAddShapeFeature {

	private static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant CLASS_FOREGROUND = new ColorConstant(255, 102, 0);
	private static final IColorConstant CLASS_BACKGROUND = new ColorConstant("99cc99");//0, 191, 255);

	public SCADiagramAddServiceFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof Service ) {
			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
			if (context.getTargetContainer() instanceof ContainerShape) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Service addedClass = null;
		if (context.getNewObject() instanceof Service) {
			addedClass = (Service) context.getNewObject();
		}
		Diagram targetDiagram = null;
		ContainerShape targetContainerShape = null;
		if (context.getTargetContainer() instanceof Diagram)
			targetDiagram = (Diagram) context.getTargetContainer();
		if (context.getTargetContainer() instanceof ContainerShape) 
			targetContainerShape = (ContainerShape) context.getTargetContainer();
		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		
		ContainerShape containerShape = null;
		if (targetDiagram != null)
				containerShape = peCreateService.createContainerShape(targetDiagram, true);
		if (targetContainerShape != null) {
			containerShape = peCreateService.createContainerShape(targetContainerShape, true);
		}

		// define a default size for the shape
		int width = 100;
		int height = 50; 
		IGaService gaService = Graphiti.getGaService();
		// create service
		{
			// triangle through points: top-middle, bottom-right, bottom-left
			int xy[] = new int[] {0,15, 75,15, 100,50, 75,85, 0,85, 15,50, 0,15 };
			Polygon p = gaService.createPolygon(containerShape, xy);
			p.setForeground(manageColor(CLASS_FOREGROUND));
			p.setBackground(manageColor(CLASS_BACKGROUND));
			p.setLineWidth(2);

			gaService.setLocationAndSize(p,
					context.getX(), context.getY(), width, height);

			// if added Class has no resource we add it to the resource 
			// of the diagram

			// in a real scenario the business model would have its own resource
			if (addedClass != null && addedClass.eResource() == null) {
				getDiagram().eResource().getContents().add(addedClass);
			}


			// create link and wire it
			if (addedClass != null)
				link(containerShape, addedClass);

			ChopboxAnchor anchor = 
					peCreateService.createChopboxAnchor(containerShape);
			anchor.setActive(true);
		}
		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), shape,
					addedClass.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 3, 25, width, 20);

			// create link and wire it
			link(shape, addedClass);
		}

//		// call the layout feature
//		layoutPictogramElement(containerShape);


		return containerShape;

	}

}
