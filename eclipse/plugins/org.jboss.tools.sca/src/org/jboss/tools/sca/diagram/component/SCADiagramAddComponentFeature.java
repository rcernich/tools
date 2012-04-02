package org.jboss.tools.sca.diagram.component;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;

public class SCADiagramAddComponentFeature extends AbstractAddShapeFeature {

	private static final IColorConstant COMPONENT_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant COMPONENT_FOREGROUND = new ColorConstant(255, 102, 0);
	private static final IColorConstant COMPONENT_BACKGROUND = new ColorConstant("c7eafb");//0, 191, 255);
	private static final IColorConstant ANCHOR_OUT_BACKGROUND = new ColorConstant("f69679");
	private static final IColorConstant ANCHOR_IN_BACKGROUND = new ColorConstant("99cc99");//0, 191, 255);

	// the additional size of the invisible rectangle at the right border
	// (this also equals the half width of the anchor to paint there)
	public static final int INVISIBLE_RECT_RIGHT = 12;

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
		Component addedClass = (Component) context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape =
				peCreateService.createContainerShape(targetContainer, true);

		// define a default size for the shape
		int width = 100;
		int height = 50; 
		IGaService gaService = Graphiti.getGaService();
		RoundedRectangle roundedRectangle;
		{
			// create and set graphics algorithm
			roundedRectangle =
					gaService.createRoundedRectangle(containerShape, 20, 20);
			roundedRectangle.setForeground(manageColor(COMPONENT_FOREGROUND));
			roundedRectangle.setBackground(manageColor(COMPONENT_BACKGROUND));
			roundedRectangle.setLineWidth(1);

			gaService.setLocationAndSize(roundedRectangle,
					context.getX(), context.getY(), width, height);

			// if added Class has no resource we add it to the resource 
			// of the diagram

			// in a real scenario the business model would have its own resource
			if (addedClass.eResource() == null) {
				getDiagram().eResource().getContents().add(addedClass);
			}

			// create link and wire it
			link(containerShape, addedClass);
			Graphiti.getPeService().setPropertyValue(roundedRectangle, "sca-type", "component");
		}
		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), shape,
					addedClass.getName());
			text.setForeground(manageColor(COMPONENT_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 5, 5, width, 20);

			// create link and wire it
			link(shape, addedClass);
		}

		// add a chopbox anchor to the shape
		peCreateService.createChopboxAnchor(containerShape);

		// create an additional box relative anchor at middle-right
		final BoxRelativeAnchor boxAnchor = peCreateService.createBoxRelativeAnchor(containerShape);
		boxAnchor.setRelativeWidth(1.0);
		boxAnchor.setRelativeHeight(0.38); // Use golden section
		// anchor references visible rectangle instead of invisible rectangle
		boxAnchor.setReferencedGraphicsAlgorithm(roundedRectangle);
		// assign a graphics algorithm for the box relative anchor
		int polyxy[] = new int[] {0,0, 20,0, 25,5, 20,10, 0,10, 3,5, 0,0 }; 
        Polygon pbox = gaService.createPolygon(boxAnchor, polyxy);
        pbox.setBackground(manageColor(ANCHOR_OUT_BACKGROUND));
        pbox.setForeground(manageColor(COMPONENT_FOREGROUND));
        pbox.setLineVisible(false);
        pbox.setFilled(true);
		// anchor is located on the right border of the visible rectangle
		// and touches the border of the invisible rectangle
		final int w = INVISIBLE_RECT_RIGHT;
		gaService.setLocationAndSize(pbox, -w, -w, 2 * w, 2 * w);

		// add a chopbox anchor to the shape
		ChopboxAnchor cb2 =
				peCreateService.createChopboxAnchor(containerShape);

		// create an additional box relative anchor at middle-right
		final BoxRelativeAnchor boxAnchorLeft = peCreateService.createBoxRelativeAnchor(containerShape);
		boxAnchorLeft.setRelativeWidth(0.0);
		boxAnchorLeft.setRelativeHeight(0.38); // Use golden section
		// anchor references visible rectangle instead of invisible rectangle
		boxAnchorLeft.setReferencedGraphicsAlgorithm(roundedRectangle);
		// assign a graphics algorithm for the box relative anchor
        Polygon pbox2 = gaService.createPolygon(boxAnchorLeft, polyxy);
        pbox2.setBackground(manageColor(ANCHOR_IN_BACKGROUND));
        pbox2.setForeground(manageColor(COMPONENT_FOREGROUND));
        pbox2.setLineVisible(false);
        pbox2.setFilled(true);
		// anchor is located on the right border of the visible rectangle
		// and touches the border of the invisible rectangle
		final int w2 = INVISIBLE_RECT_RIGHT;
		gaService.setLocationAndSize(pbox2, -10, 0, 2 * w2, 2 * w2);

		
//		{
//		Shape shape2 = peCreateService.createShape(containerShape, false);
//		int xy[] = new int[] {0,0, 20,0, 25,5, 20,10, 0,10, 3,5, 0,0 }; 
//		Polygon connector2 = gaService.createPolygon(shape2, xy);
//		connector2.setBackground(manageColor(ANCHOR_IN_BACKGROUND));
//		connector2.setForeground(manageColor(COMPONENT_FOREGROUND));
//		connector2.setLineWidth(1);
//		connector2.setParentGraphicsAlgorithm(roundedRectangle);
//		shape2.setGraphicsAlgorithm(connector2);
//		gaService.setLocationAndSize(connector2, -1, 5, 25, 20);
//
//		link(shape2, addedClass);
//
//	}
		// call the layout feature
		layoutPictogramElement(containerShape);


		return containerShape;

	}

}
