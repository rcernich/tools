package org.jboss.tools.sca.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

public class SCADiagramTypeProvider extends AbstractDiagramTypeProvider {
	
	public SCADiagramTypeProvider() {
		super();
		setFeatureProvider(new SCADiagramFeatureProvider(this));
	}

}
