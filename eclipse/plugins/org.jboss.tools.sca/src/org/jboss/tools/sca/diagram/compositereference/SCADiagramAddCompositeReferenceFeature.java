package org.jboss.tools.sca.diagram.compositereference;

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
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;

public class SCADiagramAddCompositeReferenceFeature extends AbstractAddShapeFeature {

	private static final IColorConstant COMPONENT_FOREGROUND = new ColorConstant(255, 102, 0); // bright orange line around component
	private static final IColorConstant ANCHOR_OUT_BACKGROUND = new ColorConstant("f69679"); // orange
	
	// the additional size of the invisible rectangle at the right border
	// (this also equals the half width of the anchor to paint there)
	public static final int INVISIBLE_RECT_RIGHT = 13;

	public SCADiagramAddCompositeReferenceFeature( IFeatureProvider fp ) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
		if (context.getNewObject() instanceof Reference) {
			ContainerShape targetContainer = context.getTargetContainer();
			// check if user wants to add to a diagram
			if (getBusinessObjectForPictogramElement(targetContainer) instanceof Composite) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
//		ComponentService addedComponentService = (ComponentService) context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();
		Composite addedComposite = (Composite) getBusinessObjectForPictogramElement(targetContainer);
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		if (addedComposite.getReference().size() > 0) {
			
			EList<Reference> references = addedComposite.getReference();
			for (Reference compositetReference : references) {
				
				if (!anchorComponentAlreadyExists(targetContainer, compositetReference)) {
					// create a box relative anchor at middle-right
					final BoxRelativeAnchor boxAnchorRight = peCreateService.createBoxRelativeAnchor(targetContainer);
					boxAnchorRight.setRelativeWidth(1.0);
					boxAnchorRight.setRelativeHeight(0.38); // Use golden section
					
					// anchor references visible rectangle instead of invisible rectangle
	//				boxAnchorRight.setReferencedGraphicsAlgorithm(roundedRectangle);
					
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
//					gaService.setLocationAndSize(pbox2, -w2, -w2, 25, 20); //2 * w2, 2 * w2);
					gaService.setLocationAndSize(pbox2, -w2 - 15, -w2, 20, 10); //2 * w2, 2 * w2);
					
					link (boxAnchorRight, compositetReference);
				}
			}
		}

		// call the layout feature
		layoutPictogramElement(targetContainer);

		return targetContainer;
	}
	
	private boolean anchorComponentAlreadyExists (ContainerShape targetContainer, Reference reference) {
		
		EList<Anchor> anchors = targetContainer.getAnchors();
		for (Anchor anchor : anchors) {
			Object testObject = getBusinessObjectForPictogramElement(anchor);
			if (testObject.equals(reference)) {
				return true;
			}
		}
		return false;
	}

}
