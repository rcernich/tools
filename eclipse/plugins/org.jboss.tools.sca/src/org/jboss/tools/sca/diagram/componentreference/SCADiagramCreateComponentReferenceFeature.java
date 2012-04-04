package org.jboss.tools.sca.diagram.componentreference;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;

public class SCADiagramCreateComponentReferenceFeature extends AbstractCreateFeature {

	private static final String TITLE = "Create component reference";
    private static final String USER_QUESTION = "Enter new component reference name";

    public SCADiagramCreateComponentReferenceFeature(IFeatureProvider fp) {
    	super (fp, "Component Reference", "Create component reference");
    }
    
	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		// check if user wants to add to a diagram
		if (targetContainer instanceof Component) {
			return true;
		} 
		if (getBusinessObjectForPictogramElement(targetContainer) instanceof Component) {
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

        ContainerShape containerShape = context.getTargetContainer();
        Component component = (Component) getBusinessObjectForPictogramElement(containerShape);
        
        // create EClass
        ComponentReference newClass = ScaFactory.eINSTANCE.createComponentReference();

        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
        component.getReference().add(newClass);
//        getDiagram().eResource().getContents().add(newClass);
        newClass.setName(newClassName);

        // do the add
        addGraphicalRepresentation(context, newClass);

		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);

		// return newly created business object(s)
        return new Object[] { newClass };
	}

}
