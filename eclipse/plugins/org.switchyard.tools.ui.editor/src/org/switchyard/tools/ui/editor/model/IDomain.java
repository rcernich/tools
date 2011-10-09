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

import static org.switchyard.tools.ui.editor.model.ISwitchYard.TRANSFORM_XMLNS_PREFIX;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Service;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.CustomXmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespaces;
import org.switchyard.config.model.domain.PropertiesModel;
import org.switchyard.config.model.domain.PropertyModel;
import org.switchyard.config.model.transform.TransformModel;
import org.switchyard.config.model.transform.TransformsModel;
import org.switchyard.tools.ui.editor.sapphire.ExtendedXmlListBinding;
import org.switchyard.tools.ui.editor.sapphire.PossibleExtendedTypesService;
import org.switchyard.transform.config.model.JAXBTransformModel;
import org.switchyard.transform.config.model.JSONTransformModel;
import org.switchyard.transform.config.model.Java2XmlTransformModel;
import org.switchyard.transform.config.model.JavaTransformModel;
import org.switchyard.transform.config.model.SmooksTransformModel;
import org.switchyard.transform.config.model.Xml2JavaTransformModel;
import org.switchyard.transform.config.model.XsltTransformModel;

/**
 * IDomain
 * 
 * Represents the domain element.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@XmlNamespaces(value = {@XmlNamespace(prefix = TRANSFORM_XMLNS_PREFIX, uri = TransformModel.DEFAULT_NAMESPACE) })
public interface IDomain extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(IDomain.class);

    /** The "name" attribute. */
    @XmlBinding(path = "@name")
    @Label(standard = "Name")
    @Required
    ValueProperty PROP_NAME = new ValueProperty(TYPE, "Name");

    /**
     * @return the name value.
     */
    Value<String> getName();

    /**
     * @param value the new name value.
     */
    void setName(String value);

    /** The "transforms" list. */
    @Type(base = ITransform.class, possible = {IJavaTransform.class, IJava2XmlTransform.class,
            IXml2JavaTransform.class, IJSONTransform.class, IJAXBTransform.class, IXsltTransform.class,
            ISmooksTransform.class })
    @XmlListBinding(path = TransformsModel.TRANSFORMS, mappings = {
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + JavaTransformModel.JAVA, type = IJavaTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + Java2XmlTransformModel.JAVA2XML, type = IJava2XmlTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + Xml2JavaTransformModel.XML2JAVA, type = IXml2JavaTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + JSONTransformModel.JSON, type = IJSONTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + JAXBTransformModel.JAXB, type = IJAXBTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + XsltTransformModel.XSLT, type = IXsltTransform.class),
            @XmlListBinding.Mapping(element = TRANSFORM_XMLNS_PREFIX + ':' + TransformModel.TRANSFORM + '.'
                    + SmooksTransformModel.SMOOKS, type = ISmooksTransform.class) })
    @Service(impl = PossibleExtendedTypesService.class, overrides = {"Sapphire.PossibleTypesService.Standard" })
    @CustomXmlListBinding(impl = ExtendedXmlListBinding.class)
    ListProperty PROP_TRANSFORMS = new ListProperty(TYPE, "Transforms");

    /**
     * @return the list of transform elements.
     */
    ModelElementList<ITransform> getTransforms();

    /** The "properties" list. */
    @Type(base = IProperty.class)
    @XmlListBinding(path = PropertiesModel.PROPERTIES, mappings = {@XmlListBinding.Mapping(element = PropertyModel.PROPERTY, type = IProperty.class) })
    ListProperty PROP_PROPERTIES = new ListProperty(TYPE, "Properties");

    /**
     * @return the list of property elements.
     */
    ModelElementList<IProperty> getProperties();

}
