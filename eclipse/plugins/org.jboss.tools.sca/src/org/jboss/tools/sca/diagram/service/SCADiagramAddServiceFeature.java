package org.jboss.tools.sca.diagram.service;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;

public class SCADiagramAddServiceFeature extends AbstractAddShapeFeature {

	private static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant CLASS_FOREGROUND = new ColorConstant(255, 102, 0);
	private static final IColorConstant CLASS_BACKGROUND = new ColorConstant("99cc99");//0, 191, 255);

	public static final int INVISIBLE_RECT_RIGHT = 5;
	public static final int POLYGON_HEIGHT = 50;
	public static final int POLYGON_WIDTH = 100;

	public SCADiagramAddServiceFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof Service ) {
			
			if (getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof Composite) {
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
		ContainerShape targetContainerShape = null;
		if (context.getTargetContainer() instanceof ContainerShape) 
			targetContainerShape = (ContainerShape) context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		
		ContainerShape containerShape = peCreateService.createContainerShape(targetContainerShape, true);

		int edge = 0;
		
	    // check whether the context has a size (e.g. from a create feature)
        // otherwise define a default size for the shape
        int width = context.getWidth() <= 0 ? POLYGON_WIDTH + edge : context.getWidth();
        int height = context.getHeight() <= 0 ? POLYGON_HEIGHT + edge : context.getHeight();
 
        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
        gaService.setLocationAndSize(invisibleRectangle,
                context.getX() , context.getY(), width + INVISIBLE_RECT_RIGHT, height);

        Polygon p = null;
        // create service
		{
			// triangle through points: top-middle, bottom-right, bottom-left
			int xy[] = new int[] {0,0, 75,0, 100,25, 75,50, 0,50, 15,25, 0,0 };
			p = gaService.createPolygon(invisibleRectangle, xy);
			p.setForeground(manageColor(CLASS_FOREGROUND));
			p.setBackground(manageColor(CLASS_BACKGROUND));
			p.setLineWidth(2);
	        p.setParentGraphicsAlgorithm(invisibleRectangle);

			gaService.setLocationAndSize(p,
					0, 0, width, height);

			Graphiti.getPeService().setPropertyValue(p, "sca-type", "service");
			// if added Class has no resource we add it to the resource 
			// of the diagram

//			// in a real scenario the business model would have its own resource
//			if (addedClass != null && addedClass.eResource() == null) {
//				getDiagram().eResource().getContents().add(addedClass);
//			}

			// create link and wire it
			link(containerShape, addedClass);

			ChopboxAnchor anchor = 
					peCreateService.createChopboxAnchor(containerShape);
			anchor.setActive(true);
			link(anchor, addedClass);
		}
		// SHAPE WITH TEXT
		{
			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), p,
					addedClass.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			Font font = text.getFont();
			font = gaService.manageFont(getDiagram(), font.getName(), font.getSize(), false, true);
//			text.getFont().setBold(true);
//			gaService.setLocation(text, 0, 0);
			gaService.setLocationAndSize(text, 5, 0, width - 15, height);

//			// create link and wire it
//			link(text, addedClass);
		}

		// call the layout feature
//		layoutPictogramElement(containerShape);


		return containerShape;

	}

}
