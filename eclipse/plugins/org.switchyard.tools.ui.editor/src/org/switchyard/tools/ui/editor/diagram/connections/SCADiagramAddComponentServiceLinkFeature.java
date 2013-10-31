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
package org.switchyard.tools.ui.editor.diagram.connections;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.switchyard.tools.ui.editor.diagram.StyleUtil;

/**
 * @author bfitzpat
 *
 */
public class SCADiagramAddComponentServiceLinkFeature extends AbstractAddFeature {

    /**
     * @param fp the feature provider
     */
    public SCADiagramAddComponentServiceLinkFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public boolean canAdd(IAddContext context) {
        if (context instanceof IAddConnectionContext) {
            IAddConnectionContext addConnContext = (IAddConnectionContext) context;
            if (addConnContext.getSourceAnchor() != null && addConnContext.getTargetAnchor() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PictogramElement add(IAddContext context) {
        IAddConnectionContext addConnContext = (IAddConnectionContext) context;
        IPeCreateService peCreateService = Graphiti.getPeCreateService();

        // CONNECTION WITH POLYLINE
        Connection connection = peCreateService.createFreeFormConnection(getDiagram());
        // get the actual anchors we will be connecting
        connection.setStart(addConnContext.getSourceAnchor().getParent().getAnchors().get(0));
        connection.setEnd(addConnContext.getTargetAnchor().getParent().getAnchors().get(0));

        IGaService gaService = Graphiti.getGaService();
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setLineWidth(3);
        polyline.setLineStyle(LineStyle.SOLID);
        polyline.setForeground(manageColor(new ColorConstant(StyleUtil.LINE_COLOR)));

        return connection;
    }

}
