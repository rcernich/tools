/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.editor.dom;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.switchyard.tools.ui.editor.dom.ExtendedMetaDataTranslator.IExtensionsManager;

/**
 * TranslatorFactory
 * 
 * <p/>
 * Translator factory that creates custom translators for select SCA model
 * features, specifically Service.promote and Reference.promote.
 */
@SuppressWarnings("restriction")
public class TranslatorFactory implements TranslatorExtensionRegistry.ITranslatorFactory {

    /**
     * Create a new ITranslatorFactory.
     */
    public TranslatorFactory() {
    }

    @Override
    public Translator create(EStructuralFeature feature, IExtensionsManager extensions) {
        if (ScaPackage.eINSTANCE.getService_Promote() == feature) {
            return new PromoteTranslator(feature, extensions, true);
        } else if (ScaPackage.eINSTANCE.getReference_Promote() == feature) {
            return new PromoteTranslator(feature, extensions, false);
        }
        return null;
    }

}
