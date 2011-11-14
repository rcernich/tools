/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
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
package org.switchyard.tools.ui.editor.sapphire;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.Status;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.xml.StandardXmlListBindingImpl;
import org.eclipse.sapphire.modeling.xml.XmlNamespaceResolver;
import org.eclipse.sapphire.modeling.xml.XmlUtil;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.switchyard.tools.ui.editor.Activator;

/**
 * ExtendedXmlListBinding
 * 
 * This class provides a custom list binding by accommodating elements that may
 * be contributed through an extension point.
 * 
 * @author Rob Cernich
 */
public class ExtendedXmlListBinding extends StandardXmlListBindingImpl {

    @Override
    public void dispose() {
        super.dispose();
        // try prevent leakage
        this.xmlElementNames = null;
        this.modelElementTypes = null;
    }

    @Override
    protected QName createDefaultElementName(ModelElementType type, XmlNamespaceResolver xmlNamespaceResolver) {
        XmlBinding xmlBinding = type.getAnnotation(XmlBinding.class);
        XmlNamespace xmlNamespace = type.getAnnotation(XmlNamespace.class);
        if (xmlBinding == null) {
            Activator
                    .getDefault()
                    .getLog()
                    .log(new Status(Status.WARNING, Activator.PLUGIN_ID,
                            "Using default element name mapping, @XmlBinding not specified on specialized type: "
                                    + type.getModelElementClass().getName()));
            return super.createDefaultElementName(type, xmlNamespaceResolver);
        }
        if (xmlNamespace == null) {
            return XmlUtil.createQualifiedName(xmlBinding.path(), xmlNamespaceResolver);
        }
        return new QName(xmlNamespace.uri(), xmlBinding.path(), xmlNamespace.prefix());
    }

}
