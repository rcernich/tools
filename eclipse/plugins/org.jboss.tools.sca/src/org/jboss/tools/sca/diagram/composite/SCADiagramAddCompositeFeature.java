package org.jboss.tools.sca.diagram.composite;

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
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;

public class SCADiagramAddCompositeFeature extends AbstractAddShapeFeature {

	private static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant CLASS_FOREGROUND = new ColorConstant(255, 102, 0);
	private static final IColorConstant CLASS_BACKGROUND = new ColorConstant("6699ff");//0, 191, 255);
	private static final IColorConstant COMPONENT_FOREGROUND = new ColorConstant(255, 102, 0); // bright orange line around component
	private static final IColorConstant ANCHOR_OUT_BACKGROUND = new ColorConstant("f69679"); // orange

	// the additional size of the invisible rectangle at the right border
	// (this also equals the half width of the anchor to paint there)
	public static final int INVISIBLE_RECT_RIGHT = 40;

	public SCADiagramAddCompositeFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof Composite) {
			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Composite addedClass = (Composite) context.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape =
				peCreateService.createContainerShape(targetDiagram, true);
		Graphiti.getPeService().setPropertyValue(containerShape, "sca-type", "composite");

		// define a default size for the shape
//		int width = 500;
//		int height = 300; 
		IGaService gaService = Graphiti.getGaService();
		int edge = 10;
		
	    // check whether the context has a size (e.g. from a create feature)
        // otherwise define a default size for the shape
        int width = context.getWidth() <= 0 ? 500 + edge : context.getWidth();
        int height = context.getHeight() <= 0 ? 300 + edge : context.getHeight();
 
        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
        gaService.setLocationAndSize(invisibleRectangle,
                context.getX(), context.getY(), width + INVISIBLE_RECT_RIGHT,
                height + INVISIBLE_RECT_RIGHT);

        RoundedRectangle roundedRectangle = null;        
        {
			// create and set graphics algorithm
			roundedRectangle =
					gaService.createRoundedRectangle(invisibleRectangle, 20, 20);
			roundedRectangle.setForeground(manageColor(CLASS_FOREGROUND));
			roundedRectangle.setBackground(manageColor(CLASS_BACKGROUND));
			roundedRectangle.setLineWidth(2);

			gaService.setLocationAndSize(roundedRectangle,
					INVISIBLE_RECT_RIGHT/2, INVISIBLE_RECT_RIGHT/2, width - edge, height - edge);

			// if added Class has no resource we add it to the resource 
			// of the diagram

			// in a real scenario the business model would have its own resource
//			if (addedClass.eResource() == null) {
//				getDiagram().eResource().getContents().add(addedClass);
//			}

			Graphiti.getPeService().setPropertyValue(roundedRectangle, "sca-type", "composite");

			// create link and wire it
			link(containerShape, addedClass);
		}

		// SHAPE WITH TEXT
		{
//			// create shape for text
//			Shape shape = peCreateService.createShape(invisibleRectangle, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), roundedRectangle,
					addedClass.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			Font font = text.getFont();
			font = gaService.manageFont(getDiagram(), font.getName(), font.getSize(), false, true);
//			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, edge + 2, edge + 2, width, font.getSize() * 2);

//			// create link and wire it
//			link(shape, addedClass);
		}

		if (addedClass.getReference().size() > 0) {
			
			EList<Reference> references = addedClass.getReference();
			for (Reference compositetReference : references) {
				
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
				gaService.setLocationAndSize(pbox2, -w2 + INVISIBLE_RECT_RIGHT - 10, -w2, 20, 10); //2 * w2, 2 * w2);
				
				link (boxAnchorRight, compositetReference);
			}
		}

		// call the layout feature
		layoutPictogramElement(containerShape);

 
		return containerShape;

	}

}
