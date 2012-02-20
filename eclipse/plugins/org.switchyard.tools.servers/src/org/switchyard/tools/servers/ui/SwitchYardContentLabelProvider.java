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

import javax.xml.namespace.QName;

import org.eclipse.swt.graphics.Image;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.IContentNode;
import org.jboss.ide.eclipse.as.ui.views.as7.management.content.ServerContentLabelProvider;
import org.switchyard.tools.servers.Activator;
import org.switchyard.tools.servers.ImageRegistryKeys;

/**
 * SwitchYardContentLabelProvider
 * 
 * <p/>
 * Label provider for SwitchYard content nodes.
 * 
 * @author Rob Cernich
 */
public class SwitchYardContentLabelProvider extends ServerContentLabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof ApplicationNode) {
            return textForQName(QName.valueOf(((ApplicationNode) element).getName()));
        } else if (element instanceof ServiceNode) {
            return textForQName(QName.valueOf(((ServiceNode) element).getName()));
        } else if (element instanceof IContentNode<?>) {
            String typeName = ((IContentNode<?>) element).getName();
            if (SwitchYardRootNode.SWITCHYARD_ROOT_TYPE.equals(typeName)) {
                return "SwitchYard";
            } else if (ApplicationsNode.SWITCHYARD_APPLICATIONS_TYPE.equals(typeName)) {
                return "Applications";
            } else if (ServicesNode.SWITCHYARD_SERVICES_TYPE.equals(typeName)) {
                return "Services";
            } else if (RuntimeNode.SWITCHYARD_RUNTIME_TYPE.equals(typeName)) {
                return "Runtime";
            }
        }
        return super.getText(element);
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof SwitchYardRootNode) {
            return Activator.getDefault().getImageRegistry().get(ImageRegistryKeys.SWITCHYARD_16PX);
        }
        return super.getImage(element);
    }

    private String textForQName(QName qname) {
        if (qname == null || qname.getLocalPart() == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer(qname.getLocalPart());
        if (qname.getNamespaceURI() != null) {
            sb.append(" : ").append(qname.getNamespaceURI());
        }
        return sb.toString();
    }
}
