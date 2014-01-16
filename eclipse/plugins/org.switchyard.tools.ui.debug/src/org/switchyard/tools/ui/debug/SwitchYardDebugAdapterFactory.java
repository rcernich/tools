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
import org.eclipse.debug.ui.BreakpointTypeCategory;
import org.eclipse.debug.ui.IBreakpointTypeCategory;
import org.switchyard.tools.ui.Activator;
import org.switchyard.tools.ui.IImageDescriptors;

/**
 * SwitchYardDebugAdapterFactory
 * <p/>
 * Adapter factory for SwitchYard debug objects.
 */
@SuppressWarnings("rawtypes")
public class SwitchYardDebugAdapterFactory implements IAdapterFactory {

    private static final Class[] TYPES = new Class[] {IBreakpointTypeCategory.class };

    @Override
    public Class[] getAdapterList() {
        return TYPES;
    }

    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adapterType == IBreakpointTypeCategory.class) {
            return adaptBreakpointCategory(adaptableObject);
        }
        return null;
    }

    private Object adaptBreakpointCategory(Object adaptableObject) {
        if (adaptableObject instanceof ServiceInterceptBreakpoint) {
            return new BreakpointTypeCategory("SwitchYard Breakpoints", Activator.getDefault().getImageRegistry()
                    .getDescriptor(IImageDescriptors.SWITCH_YARD_SMALL));
        }
        return null;
    }

}
