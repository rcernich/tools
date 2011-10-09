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
import org.eclipse.sapphire.modeling.ListProperty;
import org.eclipse.sapphire.modeling.ModelElementHandle;
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
import org.eclipse.sapphire.modeling.xml.annotations.XmlElementBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.switchyard.config.model.composite.CompositeServiceModel;
import org.switchyard.config.model.composite.InterfaceModel;
import org.switchyard.tools.ui.editor.sapphire.ExtendedXmlListBinding;
import org.switchyard.tools.ui.editor.sapphire.PossibleExtendedTypesService;

/**
 * ICompositeService
 * 
 * Represents SCA composite service element.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
public interface ICompositeService extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(ICompositeService.class);

    /** The "name" attribute. */
    @XmlBinding(path = "@name")
    @Label(standard = "Service Name")
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

    /** The "promote" attribute. */
    @XmlBinding(path = "@" + CompositeServiceModel.PROMOTE)
    @Label(standard = "Promoted Component Service")
    @Required
    ValueProperty PROP_PROMOTE = new ValueProperty(TYPE, "Promote");

    /**
     * @return the promote value.
     */
    Value<String> getPromote();

    /**
     * @param value the new promote value.
     */
    void setPromote(String value);

    /** The "interface" element. */
    @Type(base = IInterface.class)
    @XmlElementBinding(mappings = {@XmlElementBinding.Mapping(element = InterfaceModel.INTERFACE, type = IInterface.class) })
    @Label(standard = "Interface")
    ElementProperty PROP_INTERFAZE = new ElementProperty(TYPE, "Interfaze");

    /**
     * @return the interface value.
     */
    ModelElementHandle<IInterface> getInterfaze();

    /** The list of "binding" elements. */
    @Type(base = IBinding.class)
    @XmlListBinding(mappings = {})
    @Service(impl = PossibleExtendedTypesService.class, overrides = {"Sapphire.PossibleTypesService.Standard" }, params = {@Service.Param(name = "ignoreDeclaredTypes", value = "true") })
    @CustomXmlListBinding(impl = ExtendedXmlListBinding.class)
    ListProperty PROP_BINDINGS = new ListProperty(TYPE, "Bindings");

    /**
     * @return the list of binding elements.
     */
    ModelElementList<IBinding> getBindings();

}
