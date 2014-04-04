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
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.components.jca;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.Property;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;

/**
 * @author bfitzpat
 *
 */
public interface IJCAResourceAdapterExtension {

    /**
     * @return array of Property objects
     */
    public Property[] getPropertyList();
    
    /**
     * @return Display name
     */
    public String getDisplayName();

    /**
     * @param toolkit for creating controls
     * @return Composite with controls
     */
    public AbstractSwitchyardComposite getComposite(FormToolkit toolkit);
    
    /**
     * @return boolean true = default
     */
    public boolean isDefault();
    
    /**
     * @return String corresponding to resource adapter name
     */
    public String getResourceAdapter();
    
    /**
     * @return String corresponding to destination type
     */
    public String getDestinationType();
    
    /**
     * @param context Databinding Context to use to bind
     */
    public void bindControls(DataBindingContext context);
    
    /**
     * @param context Databinding Context to use to unbind
     */
    public void unbindControls(DataBindingContext context);
}
