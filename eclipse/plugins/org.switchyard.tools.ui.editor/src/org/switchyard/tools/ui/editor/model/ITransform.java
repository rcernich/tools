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

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.Derived;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Service;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.switchyard.config.model.transform.TransformModel;
import org.switchyard.tools.ui.editor.sapphire.TypeNameValueService;

/**
 * ITransform
 * 
 * Base type for transform elements.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@XmlNamespace(prefix = ISwitchYard.TRANSFORM_XMLNS_PREFIX, uri = TransformModel.DEFAULT_NAMESPACE)
public interface ITransform extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(ITransform.class);

    /** The type of transform. */
    @Label(standard = "Transform Type")
    @Derived
    @Service(impl = TypeNameValueService.class)
    ValueProperty PROP_TRANSFORM_TYPE = new ValueProperty(TYPE, "TransformType");

    /**
     * @return the type value.
     */
    Value<String> getTransformType();

    /** The "from" attribute. */
    @XmlBinding(path = "@" + TransformModel.FROM)
    @Label(standard = "From")
    @Required
    ValueProperty PROP_FROM = new ValueProperty(TYPE, "From");

    /**
     * @return the from value.
     */
    Value<String> getFrom();

    /**
     * @param value the new from value.
     */
    void setFrom(String value);

    /** The "to" attribute. */
    @XmlBinding(path = "@" + TransformModel.TO)
    @Label(standard = "To")
    @Required
    ValueProperty PROP_TO = new ValueProperty(TYPE, "To");

    /**
     * @return the to value.
     */
    Value<String> getTo();

    /**
     * @param value the new to value.
     */
    void setTo(String value);

}
