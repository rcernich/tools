package org.jboss.tools.sca.diagram.service;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.jboss.tools.sca.diagram.composite.SCADiagramAddCompositeFeature;

public class SCADiagramMoveServiceFeature extends DefaultMoveShapeFeature {

	// these values offer a bit of a buffer between the title of the composite and the
	// bottom of the composite
	private static int MIN_Y_PAD = 20;
	private static int MAX_Y_PAD = 30;
	
	public SCADiagramMoveServiceFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void internalMove(IMoveShapeContext context) {
		super.internalMove(context);
		
		Shape shapeToMove = context.getShape();
		ContainerShape newContainerShape = context.getTargetContainer();
		Object containerbo = getBusinessObjectForPictogramElement(newContainerShape);
		if (containerbo instanceof Composite) {
			GraphicsAlgorithm ga = shapeToMove.getGraphicsAlgorithm();
			int x = ga.getX();
			GraphicsAlgorithm containerGa = newContainerShape.getGraphicsAlgorithm();
			int containerMinX = 0;
			int containerMaxX = containerGa.getWidth();
			if (x < containerMinX) {
				ga.setX(containerMinX);
			} else if ((x + ga.getWidth()) > containerMaxX) {
				ga.setX(containerMaxX - ga.getWidth());
			}
			int y = context.getY();
			int containerMinY = SCADiagramAddCompositeFeature.INVISIBLE_RECT_RIGHT + MIN_Y_PAD;
			int containerMaxY = containerGa.getHeight() - MAX_Y_PAD;
			if (y < containerMinY) {
				ga.setY(containerMinY);
			} else if ((y + ga.getHeight()) > containerMaxY) {
				ga.setY(containerMaxY - ga.getHeight());
			}
		}

	}

}
