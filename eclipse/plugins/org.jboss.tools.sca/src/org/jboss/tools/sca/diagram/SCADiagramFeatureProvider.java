package org.jboss.tools.sca.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.jboss.tools.sca.diagram.binding.SCADiagramAddReferenceFeature;
import org.jboss.tools.sca.diagram.binding.SCADiagramCreateReferenceFeature;
import org.jboss.tools.sca.diagram.component.SCADiagramAddComponentFeature;
import org.jboss.tools.sca.diagram.component.SCADiagramCreateComponentFeature;
import org.jboss.tools.sca.diagram.component.SCADiagramDirectEditComponentFeature;
import org.jboss.tools.sca.diagram.component.SCADiagramLayoutComponentFeature;
import org.jboss.tools.sca.diagram.component.SCADiagramResizeComponentFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramAddCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramCreateCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramDirectEditCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramLayoutCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramMoveCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramResizeCompositeFeature;
import org.jboss.tools.sca.diagram.composite.SCADiagramUpdateCompositeFeature;
import org.jboss.tools.sca.diagram.service.SCADiagramAddComponentServiceFeature;
import org.jboss.tools.sca.diagram.service.SCADiagramAddServiceFeature;
import org.jboss.tools.sca.diagram.service.SCADiagramCreateComponentServiceFeature;
import org.jboss.tools.sca.diagram.service.SCADiagramCreateServiceFeature;
import org.jboss.tools.sca.diagram.service.SCADiagramDirectEditServiceFeature;

public class SCADiagramFeatureProvider extends DefaultFeatureProvider {

	public SCADiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override

	public IAddFeature getAddFeature(IAddContext context) {
		// is object for add request a EClass?
		if (context.getNewObject() instanceof Composite) {
			return new SCADiagramAddCompositeFeature(this);
		}
		if (context.getNewObject() instanceof Component) {
			return new SCADiagramAddComponentFeature(this);
		}
		if (context.getNewObject() instanceof Service) {
			return new SCADiagramAddServiceFeature(this);
		}
		if (context.getNewObject() instanceof ComponentService) {
			return new SCADiagramAddComponentServiceFeature(this);
		}
		if (context.getNewObject() instanceof Reference) {
			return new SCADiagramAddReferenceFeature(this);
		}
		if (context.getNewObject() instanceof ComponentReference) {
			return new SCADiagramAddReferenceFeature(this);
		}
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new SCADiagramCreateCompositeFeature(this),
				new SCADiagramCreateComponentFeature(this),
				new SCADiagramCreateServiceFeature(this),
				new SCADiagramCreateComponentServiceFeature(this) };
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { 
				new SCADiagramCreateReferenceFeature(this) };
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof Composite) {
				return new SCADiagramUpdateCompositeFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override

	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
		if (bo instanceof Composite) {
			return new SCADiagramMoveCompositeFeature(this);
		}
		return super.getMoveShapeFeature(context);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(
			IResizeShapeContext context) {
		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
//		if (bo instanceof Composite) {
//			return new SCADiagramResizeCompositeFeature(this);
//		}
		if (bo instanceof Component) {
			return new SCADiagramResizeComponentFeature(this);
		}
		return super.getResizeShapeFeature(context);
	}

	@Override

	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
				return getCreateConnectionFeatures();

	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
    	PictogramElement pictogramElement = context.getPictogramElement();
    	Object bo = getBusinessObjectForPictogramElement(pictogramElement);
    	if (bo instanceof Component) {
    		return new SCADiagramLayoutComponentFeature(this);
    	}
    	if (bo instanceof Composite) {
    		return new SCADiagramLayoutCompositeFeature(this);
    	}
		return super.getLayoutFeature(context);
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Composite) {
			return new SCADiagramDirectEditCompositeFeature(this);
		}
		if (bo instanceof Component) {
			return new SCADiagramDirectEditComponentFeature(this);
		}
		if (bo instanceof Service) {
			return new SCADiagramDirectEditServiceFeature(this);
		}
		return super.getDirectEditingFeature(context);
	}
}
