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
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.databinding.viewers.IViewerObservable;
import org.eclipse.jface.internal.databinding.swt.WidgetListenerUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * SWTValueUpdater
 * <p/>
 * Handles manual updating of a binding (i.e. Binding.updateTargetToModel())
 * when the UpdateValueStrategy is configured for manual updates
 * (POLICY_ON_REQUEST or POLICY_CONVERT) and the target observable is an SWT
 * control. Updates are triggered on SWT.FocusOut and SWT.DefaultSelection. In
 * addition, the updater will reset the target value when the user presses ESC
 * (i.e. Binding.updateModelToTarget()).
 */
@SuppressWarnings("restriction")
public final class SWTValueUpdater implements Listener, IDisposeListener, IChangeListener {

    /**
     * Attach a value updater to the binding.
     * 
     * @param binding the binding to attach to.
     * @return the binding, useful for chaining.
     */
    public static Binding attach(final Binding binding) {
        new SWTValueUpdater(binding);
        return binding;
    }

    private Control _control;
    private final org.eclipse.core.databinding.Binding _binding;
    private final IObservable _target;
    private boolean _dirty;

    private SWTValueUpdater(final org.eclipse.core.databinding.Binding binding) {
        _binding = binding;
        _target = _binding.getTarget();
        if (_target instanceof ISWTObservable && ((ISWTObservable) _binding.getTarget()).getWidget() instanceof Control) {
            _control = (Control) ((ISWTObservable) _binding.getTarget()).getWidget();
        } else if (_target instanceof IViewerObservable) {
            _control = ((IViewerObservable) _target).getViewer().getControl();
        } else {
            throw new IllegalArgumentException("target of binding must be an ISWTObservable whose widget is a Control.");
        }
        addListeners();
    }

    private void addListeners() {
        WidgetListenerUtil.asyncAddListener(_control, SWT.KeyUp, this);
        WidgetListenerUtil.asyncAddListener(_control, SWT.FocusOut, this);
        WidgetListenerUtil.asyncAddListener(_control, SWT.FocusIn, this);
        WidgetListenerUtil.asyncAddListener(_control, SWT.DefaultSelection, this);
        WidgetListenerUtil.asyncAddListener(_control, SWT.Dispose, this);
        _target.addDisposeListener(this);
        _target.addChangeListener(this);
    }

    @Override
    public void handleEvent(Event event) {
        // When the control loses focus..
        if (event.type == SWT.KeyUp) {
            if (event.keyCode == SWT.ESC) {
                if (_binding.isDisposed()) {
                    dispose();
                    return;
                }
                if (_dirty) {
                    _dirty = false;
                    _binding.updateModelToTarget();
                    if (_control instanceof Text) {
                        ((Text) _control).setSelection(0, ((Text) _control).getCharCount());
                    }
                }
            }
        } else if (event.type == SWT.FocusOut || event.type == SWT.DefaultSelection) {
            if (_binding.isDisposed()) {
                dispose();
                return;
            }
            if (_dirty) {
                _dirty = false;
                _binding.updateTargetToModel();
            }
        } else if (event.type == SWT.FocusIn) {
            _dirty = false;
        } else if (event.type == SWT.Dispose) {
            dispose();
        }

    }

    @Override
    public void handleChange(org.eclipse.core.databinding.observable.ChangeEvent event) {
        _dirty = true;
    }

    @Override
    public void handleDispose(DisposeEvent event) {
        dispose();
    }

    private synchronized void dispose() {
        if (_control != null) {
            WidgetListenerUtil.asyncRemoveListener(_control, SWT.KeyUp, this);
            WidgetListenerUtil.asyncRemoveListener(_control, SWT.FocusOut, this);
            WidgetListenerUtil.asyncRemoveListener(_control, SWT.FocusIn, this);
            WidgetListenerUtil.asyncRemoveListener(_control, SWT.DefaultSelection, this);
            WidgetListenerUtil.asyncRemoveListener(_control, SWT.Dispose, this);
            _control = null;
            _target.removeDisposeListener(this);
            _target.removeChangeListener(this);
        }
    }
}
