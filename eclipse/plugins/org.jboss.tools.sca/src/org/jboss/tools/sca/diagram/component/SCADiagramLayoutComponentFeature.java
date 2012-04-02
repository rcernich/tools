package org.jboss.tools.sca.diagram.component;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class SCADiagramLayoutComponentFeature extends AbstractLayoutFeature {

	private static final int MIN_HEIGHT = 30;
	private static final int MIN_WIDTH = 30;
	
	public SCADiagramLayoutComponentFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to an EClass
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		return true;
//		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
//		return businessObjects.size() == 1 
//				&& businessObjects.get(0) instanceof Composite;
	}

	public boolean layout(ILayoutContext context){
		boolean anythingChanged = false;

		ContainerShape containerShape =
				(ContainerShape) context.getPictogramElement();

		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();

		// height
		if (containerGa.getHeight() < MIN_HEIGHT) {
			containerGa.setHeight(MIN_HEIGHT);
			anythingChanged = true;
		}

		// width
		if (containerGa.getWidth() < MIN_WIDTH) {
			containerGa.setWidth(MIN_WIDTH);
			anythingChanged = true;
		}

		// width of visible rectangle (smaller than invisible rectangle)
		int rectangleWidth = containerGa.getWidth() - SCADiagramAddComponentFeature.INVISIBLE_RECT_RIGHT;
		if (containerGa.getWidth() != rectangleWidth) {
			containerGa.setWidth(rectangleWidth);
			anythingChanged = true;
		}

		int containerWidth = containerGa.getWidth();
		for (Shape shape : containerShape.getChildren()){
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = 
					gaService.calculateSize(graphicsAlgorithm);
			if (containerWidth != size.getWidth()) {
				if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint =
							gaService.createPoint(rectangleWidth, secondPoint.getY()); 
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else {
					gaService.setWidth(graphicsAlgorithm,
							rectangleWidth);
					anythingChanged = true;
				}
			}
		}
		return anythingChanged;
	}
}
