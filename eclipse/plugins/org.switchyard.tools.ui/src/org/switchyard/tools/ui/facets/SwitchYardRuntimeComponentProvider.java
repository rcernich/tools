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
package org.switchyard.tools.ui.facets;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponent;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.internal.facets.RuntimeFacetComponentProviderDelegate;

/**
 * SwitchYardRuntimeComponentProvider
 * <p/>
 * Identifies SwitchYard runtime components that may be installed on servers.
 */
@SuppressWarnings("restriction")
public class SwitchYardRuntimeComponentProvider extends RuntimeFacetComponentProviderDelegate {

    @Override
    public List<IRuntimeComponent> getRuntimeComponents(IRuntime runtime) {
        IJBossServerRuntime jbossRuntime = (IJBossServerRuntime)runtime.loadAdapter(IJBossServerRuntime.class, new NullProgressMonitor());
        if (jbossRuntime != null) {
            
        }
        return null;
    }

}
