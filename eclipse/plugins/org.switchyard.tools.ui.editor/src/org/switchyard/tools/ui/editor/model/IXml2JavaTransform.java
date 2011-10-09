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
package org.switchyard.tools.ui.editor.model;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.switchyard.transform.config.model.Xml2JavaTransformModel;

/**
 * IXml2JavaTransform
 * 
 * Represents transform.xml2java elements.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@XmlNamespace(prefix = ISwitchYard.TRANSFORM_XMLNS_PREFIX, uri = Xml2JavaTransformModel.DEFAULT_NAMESPACE)
public interface IXml2JavaTransform extends ITransform {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(IXml2JavaTransform.class);

    /** The "config" attribute. */
    @XmlBinding(path = "@" + Xml2JavaTransformModel.CONFIG)
    @Label(standard = "Config")
    @Required
    ValueProperty PROP_CONFIG = new ValueProperty(TYPE, "Config");

    /**
     * @return the config value.
     */
    Value<String> getConfig();

    /**
     * @param value the new config value.
     */
    void setConfig(String value);

}
