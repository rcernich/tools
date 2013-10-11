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
package org.switchyard.tools.ui.editor.components.camel.xml;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.switchyard.tools.models.switchyard1_1.camel.CamelImplementationType;
import org.switchyard.tools.ui.editor.diagram.component.BaseComponentFactory;
import org.switchyard.tools.ui.editor.diagram.component.IComponentWizard;

/**
 * CamelXMLComponentFactory
 * 
 * <p/>
 * Create a new component with a Camel XML based route implementation.
 * 
 * @author Rob Cernich
 */
public class CamelXMLComponentFactory extends BaseComponentFactory {

    @Override
    protected IComponentWizard createTypeWizard() {
        return new NewCamelXMLRouteCompenentWizard(false);
    }

    @Override
    protected String getComponentName(Implementation implementation, Composite container) {
        IPath path = new Path(((CamelImplementationType) implementation).getXml().getPath());
        String fileName = path.removeFileExtension().lastSegment().toString();
        if (fileName.length() > 0) {
            fileName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
        } else {
            fileName = "CamelXMLRoute"; //$NON-NLS-1$
        }
        return makeUniqueComponentName(fileName, container);
    }

}
