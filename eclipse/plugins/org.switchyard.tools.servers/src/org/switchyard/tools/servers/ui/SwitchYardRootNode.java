/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.tools.servers.ui;

import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.OP;
import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.OP_ADDR;
import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.READ_CHILDREN_TYPES_OPERATION;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.server.core.IServer;
import org.jboss.dmr.ModelNode;
import org.jboss.ide.eclipse.as.core.server.v7.management.AS7ManagementDetails;
import org.jboss.ide.eclipse.as.management.core.IJBoss7ManagerService;
import org.jboss.ide.eclipse.as.management.core.JBoss7ManagerUtil;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContainerNode;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContentNode;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.ResourceNode;

/**
 * SwitchYardRootNode
 * 
 * <p/>
 * Root node for SwitchYard content.
 * 
 * @author Rob Cernich
 */
public class SwitchYardRootNode extends ResourceNode {

    /** The node type for the SwitchYard root node. */
    public static final String SWITCHYARD_ROOT_TYPE = "switchyard-root";

    private List<IContentNode<?>> _children;

    protected SwitchYardRootNode(IServer server) {
        super(server, SWITCHYARD_ROOT_TYPE);
    }

    @Override
    public String getAddress() {
        return "/subsystem/switchyard";
    }

    @Override
    protected List<IContentNode<? extends IContainerNode<?>>> delegateGetChildren() {
        return _children;
    }

    @Override
    protected void delegateClearChildren() {
        if (_children != null) {
            for (IContentNode<?> child : _children) {
                child.dispose();
            }
            _children.clear();
            _children = null;
        }
    }

    @Override
    protected void delegateLoad() throws Exception {
        JBoss7ManagerUtil.executeWithService(new JBoss7ManagerUtil.IServiceAware<String>() {
            public String execute(IJBoss7ManagerService service) throws Exception {
                return service.execute(new AS7ManagementDetails(getServer()), createResourceDescriptionRequest());
            }
        }, getServer());
        // if we get here, then SwitchYard is installed
        _children = new ArrayList<IContentNode<?>>(3);
        _children.add(new ApplicationsNode(this));
        _children.add(new ServicesNode(this));
        // TODO: once we populate runtime details...
        //_children.add(new RuntimeNode(this));
    }

    private String createResourceDescriptionRequest() {
        ModelNode request = new ModelNode();
        request.get(OP).set(READ_CHILDREN_TYPES_OPERATION);
        request.get(OP_ADDR).add("subsystem", "switchyard");
        return request.toJSONString(true);
    }

}
