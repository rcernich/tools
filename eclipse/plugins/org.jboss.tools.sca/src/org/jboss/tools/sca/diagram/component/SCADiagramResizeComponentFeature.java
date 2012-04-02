package org.jboss.tools.sca.diagram.component;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;

public class SCADiagramResizeComponentFeature extends DefaultResizeShapeFeature {

	public SCADiagramResizeComponentFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		boolean canResize = super.canResizeShape(context);
		// perform further check only if move allowed by default feature
		if (canResize) {
			// don't allow resize if the class name has the length of 1
			Shape shape = context.getShape();
			Object bo = getBusinessObjectForPictogramElement(shape);
			if (bo instanceof Component) {
				Component c = (Component) bo;
				if (c.getName() != null && c.getName().length() == 1) {
					canResize = false;
				}
			}
		}
		return canResize;
	}

}
