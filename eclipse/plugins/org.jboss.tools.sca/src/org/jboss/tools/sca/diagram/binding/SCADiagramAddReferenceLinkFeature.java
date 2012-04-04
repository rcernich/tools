package org.jboss.tools.sca.diagram.binding;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;

public class SCADiagramAddReferenceLinkFeature extends AbstractAddFeature {

	public SCADiagramAddReferenceLinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// return true if both anchors belong to an EClass
		// and those EClasses are not identical
		if (context instanceof IAddConnectionContext) {
			if (context.getNewObject() instanceof Reference) {
				System.out.println("Adding Reference");
				return true;
			}
			if (context.getNewObject() instanceof ComponentReference) {
				System.out.println("Adding Component Reference");
				return true;
			}
		}
		System.out.println("Not adding Reference");
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConnContext = (IAddConnectionContext) context;
		ComponentReference addedComponentReference = null;
		Reference addedReference = null;
		if (context.getNewObject() instanceof ComponentReference) 
			addedComponentReference = (ComponentReference) context.getNewObject();
		if (context.getNewObject() instanceof Reference)
			addedReference = (Reference) context.getNewObject();

		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		
	       // CONNECTION WITH POLYLINE
        Connection connection = peCreateService
            .createFreeFormConnection(getDiagram());
        connection.setStart(addConnContext.getSourceAnchor());
        connection.setEnd(addConnContext.getTargetAnchor());
 
        IGaService gaService = Graphiti.getGaService();
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setLineWidth(2);
        polyline.setForeground(manageColor(IColorConstant.BLACK));
 
        // create link and wire it
        if (addedReference != null)
        	link(connection, addedReference);
        if (addedComponentReference != null) {
        	AnchorContainer container = connection.getEnd().getParent();
        	Object startObject = getFeatureProvider().getBusinessObjectForPictogramElement(container);
        	link(connection, startObject);
        }
 
//		Graphiti.getPeService().setPropertyValue(connection, "sca-type", "reference");

		return connection;
	}

}
