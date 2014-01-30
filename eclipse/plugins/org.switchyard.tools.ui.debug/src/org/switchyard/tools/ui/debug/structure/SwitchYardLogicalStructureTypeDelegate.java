/******************************************************************************* 
 * Copyright (c) 2014 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.debug.structure;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ILogicalStructureTypeDelegate;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.jdt.debug.core.IJavaClassType;
import org.eclipse.jdt.debug.core.IJavaInterfaceType;
import org.eclipse.jdt.debug.core.IJavaObject;
import org.eclipse.jdt.debug.core.IJavaType;

/**
 * SwitchYardLogicalStructureTypeDelegate
 * <p/>
 * Provides logical structures for SwitchYard types.
 */
public class SwitchYardLogicalStructureTypeDelegate implements ILogicalStructureTypeDelegate {

    /**
     * Create a new SwitchYardLogicalStructureTypeDelegate.
     */
    public SwitchYardLogicalStructureTypeDelegate() {
    }

    @Override
    public boolean providesLogicalStructure(IValue value) {
        try {
            if (value instanceof IJavaObject) {
                final IJavaType type = ((IJavaObject) value).getJavaType();
                if (type instanceof IJavaClassType) {
                    for (IJavaInterfaceType intf : ((IJavaClassType) type).getAllInterfaces()) {
                        final String name = intf.getName();
                        if (name.equals(SwitchYardPropertyVariable.TYPE)) {
                            return true;
                        } else if (name.equals(SwitchYardServiceOperationVariable.TYPE)) {
                            return true;
                        }
                    }
                }
            }
        } catch (DebugException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public IValue getLogicalStructure(IValue value) throws CoreException {
        if (value instanceof IJavaObject) {
            final IJavaType type = ((IJavaObject) value).getJavaType();
            if (type instanceof IJavaClassType) {
                for (IJavaInterfaceType intf : ((IJavaClassType) type).getAllInterfaces()) {
                    final String name = intf.getName();
                    if (name.equals(SwitchYardPropertyVariable.TYPE)) {
                        return SwitchYardPropertyVariable.newValue((IJavaObject) value);
                    } else if (name.equals(SwitchYardServiceOperationVariable.TYPE)) {
                        return SwitchYardServiceOperationVariable.newValue((IJavaObject) value);
                    }
                }
            }
        }
        return null;
    }

}
