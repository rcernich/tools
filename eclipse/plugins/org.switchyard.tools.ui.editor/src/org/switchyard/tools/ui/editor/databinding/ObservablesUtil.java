/******************************************************************************* 
 * Copyright (c) 2014 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.editor.databinding;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * ObservablesUtil
 * <p/>
 * Utilities for working with Observables.
 */
public final class ObservablesUtil {

    /**
     * Creates the appropriate detail value depending on whether or not an
     * editing domain is available.
     * 
     * @param domain the editing domain, may be null.
     * @param value the value being observed
     * @param eStructuralFeature the structural feature to observe
     * @return a new observable value
     */
    public static IObservableValue observeDetailValue(EditingDomain domain, IObservableValue value,
            EStructuralFeature eStructuralFeature) {
        if (eStructuralFeature.isMany()) {
            throw new IllegalArgumentException(
                    "Multi-valued features are not supported.  Use observeDetailList(), etc.");
        }
        return domain == null ? EMFProperties.value(eStructuralFeature).observeDetail(value) : EMFEditProperties.value(
                domain, eStructuralFeature).observeDetail(value);
    }

    private ObservablesUtil() {
    }

}
