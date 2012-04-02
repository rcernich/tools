package org.jboss.tools.sca.diagram.composite;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;

public class SCADiagramAddCompositeFeature extends AbstractAddShapeFeature {

	private static final IColorConstant CLASS_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	private static final IColorConstant CLASS_FOREGROUND = new ColorConstant(255, 102, 0);
	private static final IColorConstant CLASS_BACKGROUND = new ColorConstant("6699ff");//0, 191, 255);

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

		// define a default size for the shape
		int width = 500;
		int height = 300; 
		IGaService gaService = Graphiti.getGaService();
		{
			// create and set graphics algorithm
			RoundedRectangle roundedRectangle =
					gaService.createRoundedRectangle(containerShape, 20, 20);
			roundedRectangle.setForeground(manageColor(CLASS_FOREGROUND));
			roundedRectangle.setBackground(manageColor(CLASS_BACKGROUND));
			roundedRectangle.setLineWidth(2);

			gaService.setLocationAndSize(roundedRectangle,
					context.getX(), context.getY(), width, height);

			// if added Class has no resource we add it to the resource 
			// of the diagram

			// in a real scenario the business model would have its own resource
			if (addedClass.eResource() == null) {
				getDiagram().eResource().getContents().add(addedClass);
			}

			Graphiti.getPeService().setPropertyValue(roundedRectangle, "sca-type", "composite");

			// create link and wire it
			link(containerShape, addedClass);
		}

		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createDefaultText(getDiagram(), shape,
					addedClass.getName());
			text.setForeground(manageColor(CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.getFont().setBold(true);
			gaService.setLocationAndSize(text, 5, 5, width, 20);

			// create link and wire it
			link(shape, addedClass);
		}

//		// call the layout feature
//		layoutPictogramElement(containerShape);

 
		return containerShape;

	}

}
