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
package org.jboss.tools.sca.diagram;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;

public class SCADiagramToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public SCADiagramToolBehaviorProvider(
			IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Service) {
			Service service = (Service) bo;
			if (!service.getBinding().isEmpty()) {
				EList<Binding> bindings = service.getBinding();
				ArrayList<IDecorator> decorators = new ArrayList<IDecorator>();
				for (Binding binding : bindings) {
					IDecorator imageRenderingDecorator =
							new ImageDecorator(
									IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
					imageRenderingDecorator
						.setMessage(binding.getName());
					decorators.add(imageRenderingDecorator);
				}
				return (IDecorator[]) decorators.toArray();
			}
		}
		return super.getDecorators(pe);
	}

}
