package org.jboss.tools.sca.diagram.componentservice;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;

public class SCADiagramAddComponentServiceFeature extends AbstractAddShapeFeature {

	private static final IColorConstant COMPONENT_FOREGROUND = new ColorConstant(255, 102, 0); // bright orange line around component
	private static final IColorConstant ANCHOR_IN_BACKGROUND = new ColorConstant("99cc99"); // green;
	
	// the additional size of the invisible rectangle at the right border
	// (this also equals the half width of the anchor to paint there)
	public static final int INVISIBLE_RECT_RIGHT = 13;

	public SCADiagramAddComponentServiceFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof ComponentService) {
			ContainerShape targetContainer = context.getTargetContainer();
			// check if user wants to add to a diagram
			if (getBusinessObjectForPictogramElement(targetContainer) instanceof Component) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
//		ComponentService addedComponentService = (ComponentService) context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();
		Component addedComponent = (Component) getBusinessObjectForPictogramElement(targetContainer);
		
		String scaType = Graphiti.getPeService().getPropertyValue(targetContainer, "sca-type");
		if (scaType != null && !scaType.equalsIgnoreCase("component"))
			return null;
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		if (addedComponent.getService().size() > 0) {
			
			EList<ComponentService> services = addedComponent.getService();
			for (ComponentService componentService : services) {
				
				if (!anchorComponentAlreadyExists(targetContainer, componentService)) {
					// create a box relative anchor at middle-left
					final BoxRelativeAnchor boxAnchorLeft = peCreateService.createBoxRelativeAnchor(targetContainer);
					boxAnchorLeft.setRelativeWidth(0.0);
					boxAnchorLeft.setRelativeHeight(0.38); // Use golden section
					// anchor references visible rectangle instead of invisible rectangle
//					boxAnchorLeft.setReferencedGraphicsAlgorithm(roundedRectangle);
					// assign a graphics algorithm for the box relative anchor
					int polyxy[] = new int[] {0,0, 15,0, 20,5, 15,10, 0,10, 3,5, 0,0 }; 
			        Polygon pbox = gaService.createPolygon(boxAnchorLeft, polyxy);
			        pbox.setBackground(manageColor(ANCHOR_IN_BACKGROUND));
			        pbox.setForeground(manageColor(COMPONENT_FOREGROUND));
			        pbox.setLineVisible(false);
			        pbox.setFilled(true);
					// anchor is located on the right border of the visible rectangle
					// and touches the border of the invisible rectangle
//					final int w = INVISIBLE_RECT_RIGHT;
					gaService.setLocationAndSize(pbox, -10, 0, 25, 20); //2 * w, 2 * w);
					link(boxAnchorLeft, componentService);
				}
			}
		}

		// call the layout feature
		layoutPictogramElement(targetContainer);

		return targetContainer;
	}
	
	private boolean anchorComponentAlreadyExists (ContainerShape targetContainer, ComponentService cservice) {
		
		EList<Anchor> anchors = targetContainer.getAnchors();
		for (Anchor anchor : anchors) {
			Object testObject = getBusinessObjectForPictogramElement(anchor);
			if (testObject.equals(cservice)) {
				return true;
			}
		}
		return false;
	}

}
