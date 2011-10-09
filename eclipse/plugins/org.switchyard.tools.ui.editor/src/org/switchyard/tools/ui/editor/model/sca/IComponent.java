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
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlElementBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.switchyard.config.model.composite.ComponentImplementationModel;
import org.switchyard.config.model.composite.ComponentReferenceModel;
import org.switchyard.config.model.composite.ComponentServiceModel;

/**
 * IComponent
 * 
 * Represents SCA component elements.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
public interface IComponent extends IModelElement {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(IComponent.class);

    /** The "implementation" element. */
    @Type(base = IImplementation.class)
    @XmlElementBinding(mappings = {@XmlElementBinding.Mapping(element = ComponentImplementationModel.IMPLEMENTATION, type = IImplementation.class) })
    @Label(standard = "Implementation")
    @Required
    ElementProperty PROP_IMPLEMENTATION = new ElementProperty(TYPE, "Implementation");

    /**
     * @return the implementation value.
     */
    ModelElementHandle<IImplementation> getImplementation();

    /** The list of "service" elements. */
    @Type(base = IComponentService.class)
    @XmlListBinding(mappings = {@XmlListBinding.Mapping(element = ComponentServiceModel.SERVICE, type = IComponentService.class) })
    ListProperty PROP_SERVICES = new ListProperty(TYPE, "Services");

    /**
     * @return the list of service elements.
     */
    ModelElementList<IComponentService> getServices();

    /** The list of "reference" elements. */
    @Type(base = IComponentReference.class)
    @XmlListBinding(mappings = {@XmlListBinding.Mapping(element = ComponentReferenceModel.REFERENCE, type = IComponentReference.class) })
    ListProperty PROP_REFERENCES = new ListProperty(TYPE, "References");

    /**
     * @return the list of reference elements.
     */
    ModelElementList<IComponentReference> getReferences();

}
