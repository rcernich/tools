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
package org.switchyard.tools.ui.editor.model.sca;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.switchyard.config.model.composite.ComponentModel;
import org.switchyard.config.model.composite.CompositeModel;
import org.switchyard.config.model.composite.CompositeReferenceModel;
import org.switchyard.config.model.composite.CompositeServiceModel;
import org.switchyard.tools.ui.editor.model.ISwitchYard;

/**
 * IComposite
 * 
 * Represents SCA composite element within switchyard.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@XmlNamespace(prefix = ISwitchYard.SCA_XMLNS_PREFIX, uri = CompositeModel.DEFAULT_NAMESPACE)
public interface IComposite extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(IComposite.class);

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

    /** The "targetNamespace" attribute. */
    @XmlBinding(path = "@targetNamespace")
    @Label(standard = "Target Namespace")
    @Required
    ValueProperty PROP_TARGET_NAMESPACE = new ValueProperty(TYPE, "TargetNamespace");

    /**
     * @return the targetNamespace value.
     */
    Value<String> getTargetNamespace();

    /**
     * @param value the new targetNamespace value.
     */
    void setTargetNamespace(String value);

    /** The list of "service" elements. */
    @Type(base = ICompositeService.class)
    @XmlListBinding(mappings = {@XmlListBinding.Mapping(element = CompositeServiceModel.SERVICE, type = ICompositeService.class) })
    ListProperty PROP_SERVICES = new ListProperty(TYPE, "Services");

    /**
     * @return the list of service elements.
     */
    ModelElementList<ICompositeService> getServices();

    /** The list of "reference" elements. */
    @Type(base = ICompositeReference.class)
    @XmlListBinding(mappings = {@XmlListBinding.Mapping(element = CompositeReferenceModel.REFERENCE, type = ICompositeReference.class) })
    ListProperty PROP_REFERENCES = new ListProperty(TYPE, "References");

    /**
     * @return the list of reference elements.
     */
    ModelElementList<ICompositeReference> getReferences();

    /** The list of "component" elements. */
    @Type(base = IComponent.class)
    @XmlListBinding(mappings = {@XmlListBinding.Mapping(element = ComponentModel.COMPONENT, type = IComponent.class) })
    ListProperty PROP_COMPONENTS = new ListProperty(TYPE, "Components");

    /**
     * @return the list of component elements.
     */
    ModelElementList<IComponent> getComponents();

}
