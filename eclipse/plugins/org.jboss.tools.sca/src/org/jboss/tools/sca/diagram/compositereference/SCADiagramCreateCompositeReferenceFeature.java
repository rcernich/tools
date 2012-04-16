package org.jboss.tools.sca.diagram.compositereference;

import java.io.IOException;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.jboss.tools.sca.Activator;
import org.jboss.tools.sca.core.ModelHandler;
import org.jboss.tools.sca.core.ModelHandlerLocator;

public class SCADiagramCreateCompositeReferenceFeature extends AbstractCreateFeature {

	private static final String TITLE = "Create composite reference";
    private static final String USER_QUESTION = "Enter new composite reference name";

    public SCADiagramCreateCompositeReferenceFeature(IFeatureProvider fp) {
    	super (fp, "Composite Reference", "Create composite reference");
    }
    
	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		// check if user wants to add to a diagram
		if (targetContainer instanceof Composite) {
			return true;
		} 
		if (getBusinessObjectForPictogramElement(targetContainer) instanceof Composite) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		
        // ask user for EClass name
        String newClassName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newClassName == null || newClassName.trim().length() == 0) {
            return EMPTY;
        }

        Reference newClass = null;

		try {
			ModelHandler mh = ModelHandlerLocator.getModelHandler(getDiagram().eResource());
			Object o = getBusinessObjectForPictogramElement(context.getTargetContainer());
			newClass = mh.createCompositeReference((Composite)o);
			newClass.setName(newClassName);
		} catch (IOException e) {
			Activator.logError(e);
		}
        // do the add
        addGraphicalRepresentation(context, newClass);

		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);

		// return newly created business object(s)
        return new Object[] { newClass };
	}

}
