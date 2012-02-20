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

import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.NAME;
import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.OP;
import static org.jboss.ide.eclipse.as.management.core.ModelDescriptionConstants.OP_ADDR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.dmr.ModelNode;
import org.jboss.ide.eclipse.as.core.server.v7.management.AS7ManagementDetails;
import org.jboss.ide.eclipse.as.management.core.IJBoss7ManagerService;
import org.jboss.ide.eclipse.as.management.core.JBoss7ManagerUtil;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.ContainerNode;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContentNode;

/**
 * ServicesNode
 * 
 * <p/>
 * Servers view node containing SwitchYard services.
 * 
 * @author Rob Cernich
 */
public class ServicesNode extends ContainerNode<SwitchYardRootNode> {

    /** The node type for the SwitchYard services node. */
    public static final String SWITCHYARD_SERVICES_TYPE = "sy-services";

    private List<ServiceNode> _children;

    /**
     * Create a new ServicesNode.
     * 
     * @param container the parent container.
     */
    public ServicesNode(SwitchYardRootNode container) {
        super(container, SWITCHYARD_SERVICES_TYPE);
    }

    @Override
    protected List<? extends IContentNode<?>> delegateGetChildren() {
        return _children;
    }

    @Override
    protected void delegateClearChildren() {
        if (_children != null) {
            for (ServiceNode child : _children) {
                child.dispose();
            }
            _children.clear();
            _children = null;
        }
    }

    @Override
    protected void delegateLoad() throws Exception {
        String resultJSON = JBoss7ManagerUtil.executeWithService(new JBoss7ManagerUtil.IServiceAware<String>() {
            public String execute(IJBoss7ManagerService service) throws Exception {
                return service.execute(new AS7ManagementDetails(getServer()), createListServicesRequest());
            }
        }, getServer());
        ModelNode result = ModelNode.fromJSONString(resultJSON);
        if (!result.isDefined()) {
            _children = Collections.emptyList();
            return;
        }
        List<ModelNode> nodes = result.asList();
        _children = new ArrayList<ServiceNode>(nodes.size());
        for (ModelNode node : nodes) {
            ModelNode nameNode = node.get(NAME);
            _children.add(new ServiceNode(this, nameNode == null || !nameNode.isDefined() ? "unknown" : nameNode
                    .asString()));
        }
    }

    private String createListServicesRequest() {
        ModelNode request = new ModelNode();
        request.get(OP).set("list-services");
        request.get(OP_ADDR).add("subsystem", "switchyard");
        return request.toJSONString(true);
    }
}
