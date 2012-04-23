/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.explorer;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.switchyard.tools.ui.Activator;
import org.switchyard.tools.ui.IImageDescriptors;

/**
 * SwitchYardExplorerLabelProvider
 * 
 * <p/>
 * Label provider for SwitchYard project explorer content.
 * 
 * @author Rob Cernich
 */
public class SwitchYardExplorerLabelProvider extends LabelProvider implements ILabelProvider {

    @Override
    public Image getImage(Object element) {
        if (element instanceof ISwitchYardRootNode) {
            return Activator.getDefault().getImageRegistry().get(IImageDescriptors.SWITCH_YARD_SMALL);
        } else if (element instanceof IServicesNode || element instanceof IReferencesNode
                || element instanceof IComponentsNode) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        } else if (element instanceof IServiceNode || element instanceof IComponentService) {
            return Activator.getDefault().getImageRegistry().get(IImageDescriptors.SERVICE_ICON);
        } else if (element instanceof IReferenceNode || element instanceof IComponentReference) {
            return Activator.getDefault().getImageRegistry().get(IImageDescriptors.REFERENCE_ICON);
        } else if (element instanceof IComponentNode) {
            return Activator.getDefault().getImageRegistry().get(IImageDescriptors.COMPONENT_ICON);
        } else if (element instanceof IArtifactsNode) {
            return Activator.getDefault().getImageRegistry().get(IImageDescriptors.ARTIFACT_ICON);
        } else if (element instanceof IServiceGateway || element instanceof IArtifactNode) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        }
        return super.getImage(element);
    }

    @Override
    public String getText(Object element) {
        if (element instanceof ISwitchYardRootNode) {
            return "SwitchYard";
        } else if (element instanceof IServicesNode) {
            return "Services";
        } else if (element instanceof IReferencesNode) {
            return "References";
        } else if (element instanceof IComponentsNode) {
            return "Components";
        } else if (element instanceof IArtifactsNode) {
            return "Artifacts";
        } else if (element instanceof IServiceNode) {
            return named(((IServiceNode) element).getName());
        } else if (element instanceof IReferenceNode) {
            return named(((IReferenceNode) element).getName());
        } else if (element instanceof IComponentNode) {
            return named(((IComponentNode) element).getName());
        } else if (element instanceof IComponentService) {
            return named(((IComponentService) element).getName());
        } else if (element instanceof IComponentReference) {
            return named(((IComponentReference) element).getName());
        } else if (element instanceof IArtifactNode) {
            return named(((IArtifactNode) element).getName());
        } else if (element instanceof IServiceGateway) {
            return ((IServiceGateway) element).getType();
        }
        return super.getText(element);
    }

    private String named(String name) {
        if (name == null || name.length() == 0) {
            return "<unnamed>";
        }
        return name;
    }

}
