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
package org.switchyard.tools.ui.debug;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentationFactory;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IModelProxyFactory;
import org.eclipse.debug.ui.BreakpointTypeCategory;
import org.eclipse.debug.ui.IBreakpointTypeCategory;
import org.eclipse.jdt.internal.debug.ui.variables.JavaVariableColumnPresentationFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.switchyard.tools.ui.Activator;
import org.switchyard.tools.ui.IImageDescriptors;
import org.switchyard.tools.ui.debug.structure.SwitchYardModelProxyFactory;

/**
 * SwitchYardDebugAdapterFactory
 * <p/>
 * Adapter factory for SwitchYard debug objects.
 */
@SuppressWarnings({"rawtypes", "restriction" })
public class SwitchYardDebugAdapterFactory implements IAdapterFactory {

    private static final Class[] TYPES = new Class[] {IBreakpointTypeCategory.class, IWorkbenchAdapter.class, IModelProxyFactory.class };

    @Override
    public Class[] getAdapterList() {
        return TYPES;
    }

    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adapterType == IBreakpointTypeCategory.class) {
            return adaptBreakpointCategory(adaptableObject);
        } else if (adapterType == IWorkbenchAdapter.class) {
            return adaptWorkbenchAdapter(adaptableObject);
        } else if (adapterType == IModelProxyFactory.class) {
            return new SwitchYardModelProxyFactory();
        } else if (adapterType == IColumnPresentationFactory.class) {
            return new JavaVariableColumnPresentationFactory();
        }
        return null;
    }

    private Object adaptBreakpointCategory(Object adaptableObject) {
        if (adaptableObject instanceof DelegatingJavaBreakpoint) {
            return new BreakpointTypeCategory("SwitchYard Breakpoints", Activator.getDefault().getImageRegistry()
                    .getDescriptor(IImageDescriptors.SWITCH_YARD_SMALL));
        }
        return null;
    }

    private Object adaptWorkbenchAdapter(Object adaptableObject) {
        if (adaptableObject instanceof DelegatingJavaBreakpoint) {
            final DelegatingJavaBreakpoint breakpoint = (DelegatingJavaBreakpoint<?>) adaptableObject;
            return new IWorkbenchAdapter() {
                
                @Override
                public Object getParent(Object o) {
                    return null;
                }
                
                @Override
                public String getLabel(Object o) {
                    return DebugUIPlugin.getModelPresentation().getText(breakpoint);
                }
                
                @Override
                public ImageDescriptor getImageDescriptor(Object object) {
                    return null;
                }
                
                @Override
                public Object[] getChildren(Object o) {
                    return null;
                }
            };
        }
        return null;
    }
}
