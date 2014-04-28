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
package org.switchyard.tools.ui.editor.property.adapters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.soa.sca.sca1_1.model.sca.JavaInterface;
import org.eclipse.soa.sca.sca1_1.model.sca.WSDLPortType;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.switchyard.EsbInterface;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;
import org.switchyard.tools.ui.editor.diagram.shared.ESBInterfaceSelectionComposite;
import org.switchyard.tools.ui.editor.diagram.shared.JavaInterfaceSelectionComposite;
import org.switchyard.tools.ui.editor.diagram.shared.WSDLURISelectionComposite;

/**
 * @author bfitzpat
 * 
 */
public final class InterfaceCompositeAdapter {

    private InterfaceCompositeAdapter() {
        // empty constructor
    }
    
    /**
     * @param toolkit the toolkit to be used for creating controls
     * @param object incoming model object
     * @return outgoing composite instance or null
     */
    public static AbstractSwitchyardComposite adaptModelToComposite(FormToolkit toolkit, EObject object) {
        AbstractSwitchyardComposite composite = null;
        if (object instanceof JavaInterface) {
            composite = new JavaInterfaceSelectionComposite(toolkit);
        } else if (object instanceof WSDLPortType) {
            composite = new WSDLURISelectionComposite(toolkit);
        } else if (object instanceof EsbInterface) {
            composite = new ESBInterfaceSelectionComposite(toolkit);
        }
        if (composite != null) {
            return composite;
        }
        return null;
    }

}
