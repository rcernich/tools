package org.jboss.tools.sca.diagram.component;

import java.io.IOException;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.jboss.tools.sca.Activator;
import org.jboss.tools.sca.core.ModelHandler;
import org.jboss.tools.sca.core.ModelHandlerLocator;

public class SCADiagramCreateComponentFeature extends AbstractCreateFeature {

	private static final String TITLE = "Create component";
    private static final String USER_QUESTION = "Enter new component name";

    public SCADiagramCreateComponentFeature(IFeatureProvider fp) {
    	super (fp, "Component", "Create component");
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
		
		Component newClass = null;
		
        // ask user for EClass name
        String newClassName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newClassName == null || newClassName.trim().length() == 0) {
            return EMPTY;
        }

		try {
			ModelHandler mh = ModelHandlerLocator.getModelHandler(getDiagram().eResource());
			Object o = getBusinessObjectForPictogramElement(context.getTargetContainer());
			newClass = mh.createComponent((Composite)o);
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
