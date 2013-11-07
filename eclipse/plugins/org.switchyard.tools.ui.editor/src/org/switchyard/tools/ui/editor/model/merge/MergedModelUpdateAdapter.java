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
package org.switchyard.tools.ui.editor.model.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * MergedModelUpdateAdapter
 * 
 * <p/>
 * Keeps the generated model synchronized with changes to the source.
 */
public class MergedModelUpdateAdapter extends EContentAdapter implements Adapter {

    private MergedModelAdapterFactory _factory;

    /**
     * Create a new MergedModelUpdateAdapter.
     * 
     * @param factory the factory that manages the differences.
     */
    protected MergedModelUpdateAdapter(MergedModelAdapterFactory factory) {
        _factory = factory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyChanged(Notification notification) {
        if (notification.isTouch() || !(notification.getNotifier() instanceof EObject)) {
            return;
        }
        final EObject source = (EObject) notification.getNotifier();
        final EObject generated = getGenerated(source);
        if (generated == null || _factory.isCopiedSource(source, generated)) {
            // we're not tracking changes if we copied the generated object into
            // source
            return;
        }
        final boolean oldDeliver = generated.eDeliver();
        generated.eSetDeliver(false);
        try {
            // make sure the generated model is kept up-to-date
            final EStructuralFeature feature = ExtendedMetaData.INSTANCE.getAffiliation(generated.eClass(),
                    (EStructuralFeature) notification.getFeature());
            if (FeatureMap.Entry.class.isAssignableFrom(feature.getEType().getInstanceClass())) {
                /*
                 * don't process notifications on feature maps (i.e. the groups
                 * that actually hold the contained objects).
                 */
                return;
            } else {
                if (feature.isMany()) {
                    /*
                     * TODO: this code does not support the addition/removal of
                     * objects that are copied from source, i.e. if the object
                     * being removed corresponds with generated content, we
                     * remove it anyway. The diagram should self-correct upon
                     * synchronization after a save.
                     * 
                     * See handleContainmentSet() for an example of what we
                     * could do _if_ we could determine that the item being
                     * removed corresponds to generated content. (Of course, we
                     * could use the same process as "set" except that it is far
                     * from foolproof. We also don't support editing of
                     * generated content. Set represents a special case for
                     * container elements (e.g. transforms, artifacts, etc.).)
                     */
                    /*
                     * XXX: all positional code assumes the lists are identical
                     * between source and destination.
                     */
                    final Object newValue = notification.getNewValue();
                    switch (notification.getEventType()) {
                    case Notification.ADD:
                        if (feature instanceof EReference
                                && (((EReference) feature).isContainment() || ((EReference) feature).isContainer())) {
                            ((EList<Object>) generated.eGet(feature)).add(copy(newValue));
                        } else {
                            ((EList<Object>) generated.eGet(feature)).add(getGeneratedReference(newValue));
                        }
                        break;
                    case Notification.ADD_MANY:
                        if (feature instanceof EReference
                                && (((EReference) feature).isContainment() || ((EReference) feature).isContainer())) {
                            ((EList<Object>) generated.eGet(feature)).addAll((Collection<?>) copy(newValue));
                        } else {
                            ((EList<Object>) generated.eGet(feature))
                                    .addAll((Collection<?>) getGeneratedReference(newValue));
                        }
                        break;
                    case Notification.REMOVE:
                        do {
                            final Object removed = notification.getOldValue();
                            ((EList<Object>) generated.eGet(feature)).remove(removed instanceof EObject ? _factory
                                    .getGenerated((EObject) removed) : removed);
                            if (feature instanceof EReference
                                    && (((EReference) feature).isContainment() || ((EReference) feature).isContainer())) {
                                _factory.removeMatchFromSource(removed);
                            }
                        } while (false);
                        break;
                    case Notification.REMOVE_MANY:
                        do {
                            final boolean isContainment = feature instanceof EReference
                                    && (((EReference) feature).isContainment() || ((EReference) feature).isContainer());
                            final EList<Object> sourceList = (EList<Object>) generated.eGet(feature);
                            for (Object removed : (Collection<Object>) notification.getOldValue()) {
                                sourceList.remove(removed instanceof EObject ? _factory.getGenerated((EObject) removed)
                                        : removed);
                                if (isContainment) {
                                    if (removed instanceof EObject) {
                                        _factory.removeMatchFromSource(removed);
                                    }
                                }
                            }
                        } while (false);
                        break;
                    case Notification.MOVE:
                        // TODO: move is messed up. ignore for now
                        // ((EList<Object>)
                        // generated.eGet(feature)).move(notification.getPosition(),
                        // notification.getOldIntValue());
                        break;
                    }
                } else {
                    if (feature instanceof EReference
                            && (((EReference) feature).isContainment() || ((EReference) feature).isContainer())) {
                        handleContainmentSet(notification, generated, feature);
                    } else {
                        generated.eSet(feature, getGeneratedReference(notification.getNewValue()));
                    }
                }
            }
        } finally {
            generated.eSetDeliver(oldDeliver);
            super.notifyChanged(notification);
        }
    }

    private void handleContainmentSet(final Notification notification, final EObject generated,
            final EStructuralFeature feature) {
        if (generated.eIsSet(feature)) {
            final Object existingGenerated = generated.eGet(feature);
            // see what type of feature we're dealing with
            if (existingGenerated instanceof EObject) {
                final EObject existingSource = _factory.getSource((EObject) existingGenerated);
                final EObject newSource = (EObject) notification.getNewValue();
                if (_factory.isCopiedSource(existingSource, (EObject) existingGenerated)
                        || _factory.getDifferencesFor(existingSource).size() > 0) {
                    /*
                     * There are differences between the source and the
                     * generated value, act accordingly.
                     */
                    if (existingSource == existingGenerated || existingSource == null) {
                        /*
                         * There is no source. Recalucate the differences so
                         * everything works correctly.
                         */
                        _factory.calculateDifferences();
                    } else if (newSource != existingSource) {
                        /*
                         * The source is changing. We'll try to remove any items
                         * that match between source and generated. This may not
                         * be correct as it's possible generated content could
                         * be fully defined in the source. Presumably,
                         * everything will get worked out after a save.
                         */
                        final List<EObject> generatedContents = new ArrayList<EObject>(
                                ((EObject) existingGenerated).eContents());
                        for (Iterator<EObject> it = generatedContents.iterator(); it.hasNext();) {
                            final EObject generatedContent = it.next();
                            final EObject sourceContent = _factory.getSource(generatedContent);
                            if (sourceContent != generatedContent) {
                                Object container = ((EObject) existingGenerated).eGet(generatedContent.eContainingFeature());
                                if (container instanceof FeatureMap) {
                                    /* we should only get here for "any" features */
                                    if (ExtendedMetaData.INSTANCE.getFeatureKind(generatedContent.eContainingFeature()) == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE) {
                                        EClass docRoot = ExtendedMetaData.INSTANCE.getDocumentRoot(generatedContent
                                                .eClass().getEPackage());
                                        for (EStructuralFeature testFeature : ExtendedMetaData.INSTANCE
                                                .getElements(docRoot)) {
                                            if (testFeature.getEType() == generatedContent.eClass()) {
                                                Object data = ((FeatureMap) container).get(testFeature, false);
                                                if (data == generatedContent) {
                                                    ((FeatureMap) container).unset(testFeature);
                                                } else if (data instanceof Collection<?>) {
                                                    ((Collection<?>) data).remove(generatedContent);
                                                    if (((Collection<?>) data).isEmpty()) {
                                                        ((FeatureMap) container).unset(testFeature);
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    ((Collection<?>)container).remove(generatedContent);
                                }
                                _factory.removeMatchFromSource(sourceContent);
                            }
                        }
                        _factory.calculateDifferences();
                    }
                } else {
                    /*
                     * safe to copy over (i.e. generated == source)
                     */
                    if (notification.isReset()) {
                        generated.eUnset(feature);
                    } else {
                        generated.eSet(feature, copy(notification.getNewValue()));
                    }
                }
            } else {
                // not an EObject or null
                if (notification.isReset()) {
                    generated.eUnset(feature);
                } else {
                    generated.eSet(feature, copy(notification.getNewValue()));
                }
            }
        } else {
            if (notification.isReset()) {
                generated.eUnset(feature);
            } else {
                generated.eSet(feature, copy(notification.getNewValue()));
            }
        }
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return type instanceof Class && ((Class<?>) type).isAssignableFrom(getClass());
    }

    private EObject getGenerated(EObject changed) {
        if (changed instanceof EObject) {
            return _factory.getGenerated((EObject) changed);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Object copy(Object object) {
        if (object instanceof EObject) {
            Copier copier = new NoReferenceCopier();
            EObject copy = copier.copy((EObject) object);
            copier.copyReferences();
            return copy;
        } else if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (list.size() > 0) {
                if (list.get(0) instanceof EObject) {
                    return new NoReferenceCopier().copyAll((List<? extends EObject>) list);
                }
            }
        }
        /*
         * just return the object. theoretically, this shouldn't cause any
         * problems. these should be primitive types or collections of primitive
         * types.
         */
        return object;
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    private Object getGeneratedReference(Object object) {
        if (object instanceof EObject) {
            EObject generated = _factory.getGenerated((EObject) object);
            if (generated == null) {
                // XXX: this should never be null
                generated = (EObject) copy(object);
                if (generated != null) {
                    EObject source = (EObject) object;
                    EObject generatedContainer = _factory.getGenerated(source.eContainer());
                    if (generatedContainer != null) {
                        EStructuralFeature containingFeature = source.eContainingFeature();
                        ((List) generatedContainer.eGet(containingFeature)).add(
                                ((List) source.eContainer().eGet(containingFeature)).indexOf(source), generated);
                    }
                    _factory.setMatchedFromSource((EObject) object, generated);
                }
            }
            return generated;
        } else if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (list.size() > 0) {
                if (list.get(0) instanceof EObject) {
                    List<EObject> generatedList = new ArrayList<EObject>(list.size());
                    for (EObject source : (List<EObject>) list) {
                        EObject generated = (EObject) getGeneratedReference(source);
                        if (generated != null) {
                            // XXX: this should never be null
                            generatedList.add(generated);
                        }
                    }
                    return generatedList;
                }
            }
        }
        /*
         * just return the object. theoretically, this shouldn't cause any
         * problems. these should be primitive types or collections of primitive
         * types.
         */
        return object;
    }

    private final class NoReferenceCopier extends Copier {
        private static final long serialVersionUID = 1L;

        public NoReferenceCopier() {
            super(true, false);
        }

        @Override
        public EObject get(Object key) {
            EObject copy = super.get(key);
            if (copy == null) {
                copy = _factory.getGenerated((EObject) key);
                if (copy == null) {
                    copy = (EObject) key;
                }
            }
            return copy;
        }

        @Override
        protected EObject createCopy(EObject eObject) {
            final EObject copy = super.createCopy(eObject);
            // make sure the copy is matched up
            _factory.setMatchedFromSource(eObject, copy);
            return copy;
        }
    }
}
