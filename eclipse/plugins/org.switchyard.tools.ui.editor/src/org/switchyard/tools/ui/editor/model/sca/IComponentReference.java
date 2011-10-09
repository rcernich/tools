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

import org.eclipse.sapphire.modeling.ElementProperty;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementHandle;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlElementBinding;
import org.switchyard.config.model.composite.ComponentReferenceInterfaceModel;
import org.switchyard.config.model.composite.ComponentReferenceModel;

/**
 * IComponentReference
 * 
 * Represents SCA component reference element.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
public interface IComponentReference extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(IComponentReference.class);

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

    /** The "multiplicity" attribute. */
    @XmlBinding(path = "@" + ComponentReferenceModel.MULTIPLICITY)
    @Label(standard = "Multiplicity")
    @Required
    ValueProperty PROP_MULTIPLICITY = new ValueProperty(TYPE, "Multiplicity");

    /**
     * @return the multiplicity value.
     */
    Value<String> getMultiplicity();

    /**
     * @param value the new multiplicity value.
     */
    void setMultiplicity(String value);

    /** The "interface" element. */
    @Type(base = IComponentReferenceInterface.class)
    @XmlElementBinding(mappings = {@XmlElementBinding.Mapping(element = ComponentReferenceInterfaceModel.INTERFACE, type = IComponentReferenceInterface.class) })
    @Label(standard = "Interface")
    @Required
    ElementProperty PROP_INTERFAZE = new ElementProperty(TYPE, "Interfaze");

    /**
     * @return the interface value.
     */
    ModelElementHandle<IComponentReferenceInterface> getInterfaze();

}
