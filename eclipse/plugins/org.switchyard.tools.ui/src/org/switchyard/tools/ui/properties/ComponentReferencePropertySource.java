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
package org.switchyard.tools.ui.properties;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.switchyard.tools.ui.explorer.impl.ComponentReference;

/**
 * ComponentReferencePropertySource
 * 
 * <p/>
 * Provides properties for ComponentReference objects.
 * 
 * @author Rob Cernich
 */
public class ComponentReferencePropertySource implements IPropertySource {

    private static final String PROP_MODEL = "model";
    private static final PropertyDescriptor[] DESCRIPTORS;

    private ComponentReference _node;

    /* package */ComponentReferencePropertySource(ComponentReference node) {
        _node = node;
    }

    @Override
    public Object getEditableValue() {
        return null;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public Object getPropertyValue(Object id) {
        if (PROP_MODEL.equals(id)) {
            return _node.getModel();
        }
        return "";
    }

    @Override
    public boolean isPropertySet(Object id) {
        return false;
    }

    @Override
    public void resetPropertyValue(Object id) {
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
    }

    static {
        DESCRIPTORS = new PropertyDescriptor[] {new PropertyDescriptor(PROP_MODEL, "SwitchYard Component Reference") };
        DESCRIPTORS[0].setDescription("SwitchYard component reference details.");
    }
}
