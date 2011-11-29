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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.ModelProperty;
import org.eclipse.sapphire.modeling.internal.SapphireModelingExtensionSystem;
import org.eclipse.sapphire.services.PossibleTypesService;
import org.eclipse.sapphire.services.PossibleTypesServiceData;
import org.eclipse.sapphire.services.ServiceFactoryProxy;
import org.switchyard.tools.ui.editor.Activator;

/**
 * PossibleExtendedTypesService
 * 
 * Provides a list of types contributed by extensions.
 * 
 * @author Rob Cernich
 */
public class PossibleExtendedTypesService extends PossibleTypesService {

    private static final String IGNORE_DECLARED_TYPES = "ignoreDeclaredTypes";

    private PossibleTypesService _standardService;
    private Set<ModelElementType> _possible = new HashSet<ModelElementType>();

    @Override
    protected void initPossibleTypesService() {
        super.initPossibleTypesService();
        for (Class<?> specializedType : Activator.getDefault().getElementExtensionRegistry()
                .getSpecializedTypes(context(ModelProperty.class).getType().getModelElementClass())) {
            ModelElementType met = ModelElementType.getModelElementType(specializedType, false);
            if (met != null) {
                _possible.add(met);
            }
        }
        if (Boolean.parseBoolean(params().get(IGNORE_DECLARED_TYPES))) {
            return;
        }
        for (ServiceFactoryProxy factory : SapphireModelingExtensionSystem.getServiceFactories()) {
            if ("Sapphire.PossibleTypesService.Standard".equals(factory.id())) {
                _standardService = (PossibleTypesService) factory.create(context(), PossibleTypesService.class);
                _standardService.init(context(), params());
                break;
            }
        }

    }

    @Override
    public void dispose() {
        if (_standardService != null) {
            _standardService.dispose();
            _standardService = null;
        }
        super.dispose();
    }

    @Override
    protected PossibleTypesServiceData compute() {
        Set<ModelElementType> allPossible = new HashSet<ModelElementType>(_possible);
        if (_standardService != null) {
            allPossible.addAll(_standardService.types());
        }
        return new PossibleTypesServiceData(allPossible);
    }

}
