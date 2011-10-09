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
package org.switchyard.tools.soap.ui;

import org.eclipse.sapphire.ui.def.ISapphirePartDef;
import org.eclipse.sapphire.ui.def.SapphireUiDefFactory;
import org.switchyard.tools.soap.ui.model.ISoapBinding;
import org.switchyard.tools.ui.editor.model.sca.IBinding;
import org.switchyard.tools.ui.editor.sapphire.IModelElementExtension;

/**
 * SOAPBindingModelElementExtension
 * 
 * Model element extension for SOAP binding.
 * 
 * @author Rob Cernich
 */
public class SOAPBindingModelElementExtension implements IModelElementExtension<IBinding> {
    private static final String EDITOR_SDEF = SOAPBindingModelElementExtension.class.getResource("soapbinding.sdef")
            .getPath();
    private static final String SOAP_BINDING_EDITOR_PART_ID = "switchyard.soapbinding.editor";

    @Override
    public Class<? extends IBinding> getModelElement() {
        return ISoapBinding.class;
    }

    @Override
    public ISapphirePartDef getEditorPartDef() {
        return SapphireUiDefFactory.getCompositeDef(Activator.PLUGIN_ID, EDITOR_SDEF, SOAP_BINDING_EDITOR_PART_ID);
    }

}
