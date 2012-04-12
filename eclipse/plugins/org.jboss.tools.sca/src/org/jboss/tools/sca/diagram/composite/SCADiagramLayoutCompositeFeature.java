package org.jboss.tools.sca.diagram.composite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;

public class SCADiagramLayoutCompositeFeature extends AbstractLayoutFeature {

	private static final int MIN_HEIGHT = 150;
	private static final int MIN_WIDTH = 150;
	
	private static final int COMPONENT_MIN_HEIGHT = 50;
	private static final int COMPONENT_MIN_WIDTH = 100;

	public SCADiagramLayoutCompositeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to an EClass
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.size() == 1 
				&& businessObjects.get(0) instanceof Composite;
	}

	@Override
	public boolean layout(ILayoutContext context) {
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

		int containerWidth = containerGa.getWidth();
		for (Shape shape : containerShape.getChildren()){
			Object bo = getBusinessObjectForPictogramElement(shape);

			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = 
					gaService.calculateSize(graphicsAlgorithm);
			System.out.println("Layout: " + bo.toString() + "\n" + "   Algorithm: " + graphicsAlgorithm.toString());
			if (containerWidth != size.getWidth()) {
				if (graphicsAlgorithm instanceof Polygon) {
					// ignore
				} else if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint =
							gaService.createPoint(containerWidth, secondPoint.getY()); 
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else if (graphicsAlgorithm instanceof RoundedRectangle) {
					if (bo instanceof Component) {
						RoundedRectangle rrect = (RoundedRectangle) graphicsAlgorithm;
						if (rrect.getWidth() < COMPONENT_MIN_WIDTH) {
							rrect.setWidth(COMPONENT_MIN_WIDTH);
							anythingChanged = true;
						}
						if (rrect.getHeight() < COMPONENT_MIN_HEIGHT) {
							rrect.setHeight(COMPONENT_MIN_HEIGHT);
							anythingChanged = true;
						}
					}
				} else if (graphicsAlgorithm instanceof Polygon) {
					// ignore
				} else if (graphicsAlgorithm instanceof Text) {
					// ignore
				}
			}
		}
		return anythingChanged;
	}

}
