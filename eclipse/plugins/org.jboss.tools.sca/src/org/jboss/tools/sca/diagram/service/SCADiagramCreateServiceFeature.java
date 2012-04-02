package org.jboss.tools.sca.diagram.service;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;

public class SCADiagramCreateServiceFeature extends AbstractCreateFeature {

	private static final String TITLE = "Create service";
    private static final String USER_QUESTION = "Enter new service name";

    public SCADiagramCreateServiceFeature(IFeatureProvider fp) {
    	super (fp, "Service", "Create service");
    }
    
	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
        // ask user for EClass name
        String newClassName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newClassName == null || newClassName.trim().length() == 0) {
            return EMPTY;
        }

        // create EClass
        Service newClass = ScaFactory.eINSTANCE.createService();

        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
        getDiagram().eResource().getContents().add(newClass);
        newClass.setName(newClassName);

        // do the add
        addGraphicalRepresentation(context, newClass);

        // return newly created business object(s)
        return new Object[] { newClass };
	}

}
