package org.jboss.tools.sca.diagram.component;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;

public class SCADiagramAddComponentFeature extends AbstractAddShapeFeature {

	private static final IColorConstant COMPONENT_TEXT_FOREGROUND = new ColorConstant(0, 0, 0); // black
	private static final IColorConstant COMPONENT_FOREGROUND = new ColorConstant(255, 102, 0); // bright orange line around component
	private static final IColorConstant COMPONENT_BACKGROUND = new ColorConstant("c7eafb"); //light blue
	private static final IColorConstant ANCHOR_OUT_BACKGROUND = new ColorConstant("f69679"); // orange
	private static final IColorConstant ANCHOR_IN_BACKGROUND = new ColorConstant("99cc99"); // green;
	
	// the additional size of the invisible rectangle at the right border
	// (this also equals the half width of the anchor to paint there)
	public static final int INVISIBLE_RECT_RIGHT = 13;

	public SCADiagramAddComponentFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof Component) {
			ContainerShape targetContainer = context.getTargetContainer();
			// check if user wants to add to a diagram
			if (targetContainer instanceof Composite) {
				return true;
			} 
			if (getBusinessObjectForPictogramElement(targetContainer) instanceof Composite) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Component addedComponent = (Component) context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();

		IGaService gaService = Graphiti.getGaService();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape =
				peCreateService.createContainerShape(targetContainer, true);
		
		int edge = 10;
		
	    // check whether the context has a size (e.g. from a create feature)
        // otherwise define a default size for the shape
        int width = context.getWidth() <= 0 ? 100 + edge : context.getWidth();
        int height = context.getHeight() <= 0 ? 50 + edge : context.getHeight();
 
        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
        gaService.setLocationAndSize(invisibleRectangle,
                context.getX(), context.getY(), width + INVISIBLE_RECT_RIGHT,
                height);
        
		// define a default size for the shape
		RoundedRectangle roundedRectangle;
		{
			// create and set graphics algorithm
			roundedRectangle =
					gaService.createRoundedRectangle(invisibleRectangle, 5, 0);
			roundedRectangle.setForeground(manageColor(COMPONENT_FOREGROUND));
			roundedRectangle.setBackground(manageColor(COMPONENT_BACKGROUND));
			roundedRectangle.setLineWidth(1);
	        roundedRectangle.setParentGraphicsAlgorithm(invisibleRectangle);

			gaService.setLocationAndSize(roundedRectangle,
					5, 0, width, height);

			// if added Class has no resource we add it to the resource 
			// of the diagram

			// in a real scenario the business model would have its own resource
//			if (addedComponent.eResource() == null) {
//				getDiagram().eResource().getContents().add(addedComponent);
//			}

			// create link and wire it
			link(containerShape, addedComponent);
			Graphiti.getPeService().setPropertyValue(roundedRectangle, "sca-type", "component");
		}
		// SHAPE WITH TEXT
		{
			// create shape for text
//			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), roundedRectangle,
					addedComponent.getName());
			text.setForeground(manageColor(COMPONENT_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			Font font = text.getFont();
			font = gaService.manageFont(getDiagram(), font.getName(), font.getSize(), false, true);
			text.setFont(font);
//			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 5, 5, width, 20);

			// create link and wire it
//			link(shape, addedComponent);
		}

		if (addedComponent.getService().size() > 0) {
			EList<ComponentService> services = addedComponent.getService();
			for (ComponentService componentService : services) {
				// create a box relative anchor at middle-left
				final BoxRelativeAnchor boxAnchorLeft = peCreateService.createBoxRelativeAnchor(containerShape);
				boxAnchorLeft.setRelativeWidth(0.0);
				boxAnchorLeft.setRelativeHeight(0.38); // Use golden section
				// anchor references visible rectangle instead of invisible rectangle
				boxAnchorLeft.setReferencedGraphicsAlgorithm(roundedRectangle);
				// assign a graphics algorithm for the box relative anchor
				int polyxy[] = new int[] {0,0, 15,0, 20,5, 15,10, 0,10, 3,5, 0,0 }; 
		        Polygon pbox = gaService.createPolygon(boxAnchorLeft, polyxy);
		        pbox.setBackground(manageColor(ANCHOR_IN_BACKGROUND));
		        pbox.setForeground(manageColor(COMPONENT_FOREGROUND));
		        pbox.setLineVisible(false);
		        pbox.setFilled(true);
				// anchor is located on the right border of the visible rectangle
				// and touches the border of the invisible rectangle
//				final int w = INVISIBLE_RECT_RIGHT;
				gaService.setLocationAndSize(pbox, -10, 0, 25, 20); //2 * w, 2 * w);
				
				link(boxAnchorLeft, componentService);
			}
		}
		
		if (addedComponent.getReference().size() > 0) {
			
			EList<ComponentReference> references = addedComponent.getReference();
			for (ComponentReference componentReference : references) {
				
				// create a box relative anchor at middle-right
				final BoxRelativeAnchor boxAnchorRight = peCreateService.createBoxRelativeAnchor(containerShape);
				boxAnchorRight.setRelativeWidth(1.0);
				boxAnchorRight.setRelativeHeight(0.38); // Use golden section
				
				// anchor references visible rectangle instead of invisible rectangle
				boxAnchorRight.setReferencedGraphicsAlgorithm(roundedRectangle);
				
				// assign a graphics algorithm for the box relative anchor
				int polyxy[] = new int[] {0,0, 15,0, 20,5, 15,10, 0,10, 3,5, 0,0 }; 
		        Polygon pbox2 = gaService.createPolygon(boxAnchorRight, polyxy);
		        pbox2.setBackground(manageColor(ANCHOR_OUT_BACKGROUND));
		        pbox2.setForeground(manageColor(COMPONENT_FOREGROUND));
		        pbox2.setLineVisible(false);
		        pbox2.setFilled(true);
		        
				// anchor is located on the right border of the visible rectangle
				// and touches the border of the invisible rectangle
				final int w2 = INVISIBLE_RECT_RIGHT;
				gaService.setLocationAndSize(pbox2, -w2, -w2, 25, 20); //2 * w2, 2 * w2);
				
				link (boxAnchorRight, componentReference);
			}
		}

		// call the layout feature
		layoutPictogramElement(containerShape);

		return containerShape;

	}

}
