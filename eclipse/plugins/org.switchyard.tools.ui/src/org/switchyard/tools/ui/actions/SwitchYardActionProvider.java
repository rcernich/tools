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
package org.switchyard.tools.ui.actions;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.OpenFileAction;
import org.eclipse.ui.actions.RefreshAction;
import org.eclipse.ui.internal.navigator.resources.plugin.WorkbenchNavigatorMessages;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;
import org.switchyard.tools.ui.SwitchYardModelUtils;
import org.switchyard.tools.ui.explorer.ISwitchYardNode;
import org.switchyard.tools.ui.explorer.ISwitchYardRootNode;
import org.switchyard.tools.ui.explorer.impl.ComponentNode;
import org.switchyard.tools.ui.explorer.impl.ComponentReference;
import org.switchyard.tools.ui.explorer.impl.ComponentService;
import org.switchyard.tools.ui.explorer.impl.ReferenceNode;
import org.switchyard.tools.ui.explorer.impl.ServiceNode;

/**
 * SwitchYardActionProvider
 * 
 * <p/>
 * Provides SwitchYard related actions to the project explorer.
 * 
 * @author Rob Cernich
 */
@SuppressWarnings("restriction")
public class SwitchYardActionProvider extends CommonActionProvider {

    private static final String SWITCHYARD_MENU = "org.switchyard.tools.ui.switchyard";
    private static final String NEW_MENU = "new.menu";

    private WizardActionGroup _newWizardActionGroup;
    private RefreshAction _refreshAction;
    private OpenFileAction _openFileAction;
    private boolean _contribute;

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);
        if (aSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) aSite.getViewSite();
            _newWizardActionGroup = new WizardActionGroup(workbenchSite.getWorkbenchWindow(), PlatformUI.getWorkbench()
                    .getNewWizardRegistry(), WizardActionGroup.TYPE_NEW, aSite.getContentService());
            _openFileAction = new OpenFileAction(workbenchSite.getPage());
            _contribute = true;
        }
        _refreshAction = new RefreshAction(new IShellProvider() {
            public Shell getShell() {
                return getActionSite().getViewSite().getShell();
            }
        }) {
            @Override
            public void run() {
                ISelection selection = getContext().getSelection();
                if (selection == null || selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
                    return;
                }
                getActionSite().getStructuredViewer().refresh(((IStructuredSelection) selection).getFirstElement());
            }
        };
        _refreshAction.setActionDefinitionId(IWorkbenchCommandConstants.FILE_REFRESH);
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, new Separator(SWITCHYARD_MENU));
        if (_contribute) {
            IMenuManager submenu = new MenuManager(WorkbenchNavigatorMessages.NewActionProvider_NewMenu_label, NEW_MENU);

            _newWizardActionGroup.setContext(getContext());
            _newWizardActionGroup.fillContextMenu(submenu);

            menu.insertAfter(ICommonMenuConstants.GROUP_NEW, submenu);

            _openFileAction.selectionChanged(getOpenFileSelection());
            if (_openFileAction.isEnabled()) {
                menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, _openFileAction);
            }
        }
        _refreshAction.setEnabled(true);
        menu.appendToGroup(ICommonMenuConstants.GROUP_BUILD, _refreshAction);
    }

    /**
     * Converts various ISwitchYardNode objects in the selection to their
     * related IResource.
     * 
     * @return the converted selection.
     */
    @SuppressWarnings("rawtypes")
    private IStructuredSelection getOpenFileSelection() {
        if (!(getContext().getSelection() instanceof IStructuredSelection)) {
            return StructuredSelection.EMPTY;
        }
        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        if (selection.isEmpty()) {
            return selection;
        }
        Set<Object> converted = new LinkedHashSet<Object>(selection.size());
        for (Iterator it = selection.iterator(); it.hasNext();) {
            Object obj = it.next();
            if (obj instanceof ComponentService) {
                ComponentService service = (ComponentService) obj;
                IResource related = SwitchYardModelUtils.getAssociatedResource(service.getRoot().getProject(), service
                        .getModel().getInterface());
                if (related == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(related);
            } else if (obj instanceof ComponentReference) {
                ComponentReference reference = (ComponentReference) obj;
                IResource related = SwitchYardModelUtils.getAssociatedResource(reference.getRoot().getProject(),
                        reference.getModel().getInterface());
                if (related == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(related);
            } else if (obj instanceof ComponentNode) {
                ComponentNode component = (ComponentNode) obj;
                IResource related = SwitchYardModelUtils.getAssociatedResource(component.getRoot().getProject(),
                        component.getModel().getImplementation());
                if (related == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(related);
            } else if (obj instanceof ServiceNode) {
                ServiceNode service = (ServiceNode) obj;
                IResource related = SwitchYardModelUtils.getAssociatedResource(service.getRoot().getProject(), service
                        .getModel().getInterface());
                if (related == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(related);
            } else if (obj instanceof ReferenceNode) {
                ReferenceNode reference = (ReferenceNode) obj;
                IResource related = SwitchYardModelUtils.getAssociatedResource(reference.getRoot().getProject(),
                        reference.getModel().getInterface());
                if (related == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(related);
            } else if (obj instanceof ISwitchYardRootNode) {
                IResource switchYardConfigFile = ((ISwitchYardNode) obj).getRoot().getSwitchYardProject()
                        .getSwitchYardConfigurationFile();
                if (switchYardConfigFile == null) {
                    return StructuredSelection.EMPTY;
                }
                converted.add(switchYardConfigFile);
            } else {
                converted.add(obj);
            }
        }
        return new StructuredSelection(converted.toArray());
    }

}
