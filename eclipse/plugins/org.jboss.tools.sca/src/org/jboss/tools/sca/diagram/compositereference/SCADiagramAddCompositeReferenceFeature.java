/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
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
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.jboss.tools.sca.diagram.StyleUtil;

public class SCADiagramAddCompositeReferenceFeature extends AbstractAddShapeFeature {

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
		ContainerShape targetContainer = context.getTargetContainer();
		Composite addedComposite = (Composite) getBusinessObjectForPictogramElement(targetContainer);
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		if (addedComposite.getReference().size() > 0) {
			
			EList<Reference> references = addedComposite.getReference();
			for (Reference compositetReference : references) {
				
				if (!anchorComponentAlreadyExists(targetContainer, compositetReference)) {
					// create a box relative anchor at middle-right
					final BoxRelativeAnchor boxAnchorRight = 
							peCreateService.createBoxRelativeAnchor(targetContainer);
					boxAnchorRight.setRelativeWidth(1.0);
					boxAnchorRight.setRelativeHeight(0.38); // Use golden section
					
					// assign a graphics algorithm for the box relative anchor
			        Polygon pbox2 = gaService.createPolygon(boxAnchorRight, StyleUtil.LARGE_RIGHT_ARROW);
			        pbox2.setBackground(manageColor(StyleUtil.ORANGE));
			        pbox2.setForeground(manageColor(StyleUtil.GREEN));
			        pbox2.setLineVisible(true);
			        pbox2.setFilled(true);
			        
					// anchor is located on the right border of the visible rectangle
					// and touches the border of the invisible rectangle
					final int w2 = StyleUtil.COMPONENT_INVISIBLE_RECT_RIGHT;
					gaService.setLocationAndSize(pbox2, -w2 - (StyleUtil.LARGE_RIGHT_ARROW_WIDTH/2), 
							-w2, 
							StyleUtil.LARGE_RIGHT_ARROW_WIDTH, StyleUtil.LARGE_RIGHT_ARROW_HEIGHT);
					
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
