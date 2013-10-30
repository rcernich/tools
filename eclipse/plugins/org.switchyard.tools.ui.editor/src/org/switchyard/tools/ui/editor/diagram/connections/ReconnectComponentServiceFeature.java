/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.editor.diagram.connections;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;

/**
 * ReconnectComponentServiceFeature
 * <p/>
 * Supports reconnection when the source is a component service.
 */
public class ReconnectComponentServiceFeature extends DefaultReconnectionFeature implements IReconnectionFeature {

    private final ComponentService _service;

    /**
     * Create a new ReconnectComponentServiceFeature.
     * 
     * @param fp the feature provider
     * @param service the source service
     */
    public ReconnectComponentServiceFeature(IFeatureProvider fp, ComponentService service) {
        super(fp);
        _service = service;
    }

    @Override
    public boolean canReconnect(IReconnectionContext context) {
        final Object targetBo = getBusinessObjectForPictogramElement(getNewAnchor(context));
        return super.canReconnect(context) && targetBo instanceof Service && ((Service) targetBo).getPromote() == null;
    }

    @Override
    public void preReconnect(IReconnectionContext context) {
        super.preReconnect(context);
        ((Service) getBusinessObjectForPictogramElement(context.getOldAnchor())).setPromote(null);
    }

    @Override
    public void postReconnect(IReconnectionContext context) {
        ((Service) getBusinessObjectForPictogramElement(getNewAnchor(context))).setPromote(_service);
        super.postReconnect(context);
    }

    @Override
    protected Anchor getNewAnchor(IReconnectionContext context) {
        // make sure we get the fixed anchor, not the chopbox anchor
        final Anchor newAnchor = super.getNewAnchor(context);
        return newAnchor == null ? null : newAnchor.getParent().getAnchors().get(0);
    }

}
