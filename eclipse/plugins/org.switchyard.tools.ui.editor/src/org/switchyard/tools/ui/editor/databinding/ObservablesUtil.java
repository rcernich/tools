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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
        return observeDetailValue(domain, value, FeaturePath.fromList(eStructuralFeature));
    }

    /**
     * Creates the appropriate detail value depending on whether or not an
     * editing domain is available.
     * 
     * @param domain the editing domain, may be null.
     * @param value the value being observed
     * @param featurePath the path to the feature to observe
     * @return a new observable value
     */
    public static IObservableValue observeDetailValue(EditingDomain domain, IObservableValue value,
            FeaturePath featurePath) {
        return domain == null ? EMFProperties.value(featurePath).observeDetail(value) : EMFEditProperties.value(domain,
                featurePath).observeDetail(value);
    }

    /**
     * Wraps the observable, redirecting getValue() to null if the actual value
     * is empty and setValue() to a newly created object if the value being set
     * is null. This should be used when the observed value should be removed
     * from the model if its contents are empty. Likewise, it provides an object
     * for child bindings to work with when the source is null.
     * 
     * @param value the value to be decorated.
     * @param type the type to be created when the source is null.
     * @return the decorated value.
     */
    public static IObservableValue observeNullForEmptyValue(final IObservableValue value, final EClass type) {
        return new DecoratingObservableValue(value, true) {
            @Override
            public Object getValue() {
                final Object decoratedValue = super.getValue();
                if (decoratedValue == null) {
                    return type.getEPackage().getEFactoryInstance().create(type);
                }
                return decoratedValue;
            }

            @Override
            public void setValue(Object value) {
                if (value == null || isEmpty((EObject) value)) {
                    super.setValue(null);
                } else {
                    super.setValue(value);
                }
            }
        };
    }

    /**
     * Updates the container binding when one of its contained values changes.
     * This should be used with containers wrapped with null-for-empty-value
     * observers.
     * 
     * XXX: unfortunately, this comes with the side effect of having the update
     * occur as a separate command, which means any time the container is added
     * or removed from the model, there will be two commands on the undo stack
     * for the change: one to add/remove the child and one to add/remove the
     * container.
     * 
     * @param containedValue the child field that changes the contents of the
     *            target in the container binding.
     * @param containerBinding the binding between the container and its parent.
     * @return the contained value (useful for chaining)
     */
    public static IObservableValue updateContainerBinding(final IObservableValue containedValue,
            final Binding containerBinding) {
        containedValue.addChangeListener(new IChangeListener() {
            @Override
            public void handleChange(ChangeEvent event) {
                final EObject value = (EObject) ((IObservableValue) containerBinding.getTarget()).getValue();
                final boolean isEmpty = isEmpty(value);
                final boolean isContained = value.eContainer() != null;
                if ((isEmpty && isContained) || (!isEmpty && !isContained)) {
                    containerBinding.updateTargetToModel();
                }
            }
        });
        return containedValue;
    }

    private static boolean isEmpty(EObject object) {
        final boolean hasContents = EcoreUtil.getAllContents(object, false).hasNext();
        if (hasContents) {
            return false;
        }
        for (EAttribute attribute : object.eClass().getEAllAttributes()) {
            if (object.eIsSet(attribute) || object.eGet(attribute) != null) {
                return false;
            }
        }
        return true;
    }

    private ObservablesUtil() {
    }

}
