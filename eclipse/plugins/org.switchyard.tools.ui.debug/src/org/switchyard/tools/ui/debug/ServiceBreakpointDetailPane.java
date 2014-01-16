/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 ******************************************************************************/
package org.switchyard.tools.ui.debug;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.internal.debug.ui.breakpoints.AbstractDetailPane;
import org.eclipse.jdt.internal.debug.ui.breakpoints.AbstractJavaBreakpointEditor;
import org.eclipse.jdt.internal.debug.ui.breakpoints.CompositeBreakpointEditor;
import org.eclipse.jdt.internal.debug.ui.breakpoints.StandardJavaBreakpointEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.switchyard.tools.ui.debug.IInteractionConfiguration.TriggerType;

/**
 * ServiceBreakpointDetailPane
 * <p/>
 * Detail pane for SwitchYard service breakpoints.
 */
@SuppressWarnings("restriction")
public class ServiceBreakpointDetailPane extends AbstractDetailPane {

    /** The ID for this detail pane. */
    public static final String ID = ServiceBreakpointDetailPane.class.getCanonicalName();

    /**
     * Create a new ServiceBreakpointDetailPane.
     */
    public ServiceBreakpointDetailPane() {
        super("SwitchYard Service Breakpoint", "SwitchYard service breakpoint properties.", ID);
    }

    @Override
    protected AbstractJavaBreakpointEditor createEditor(Composite parent) {
        return new CompositeBreakpointEditor(new AbstractJavaBreakpointEditor[] {new ServiceBreakpointEditor(),
                new StandardJavaBreakpointEditor() });
    }

    private static final class ServiceBreakpointEditor extends AbstractJavaBreakpointEditor {

        private ServiceInterceptBreakpoint _breakpoint;
        private Button _entryButton;
        private Button _exitButton;
        private Button _faultButton;

        @Override
        public Control createControl(Composite parent) {
            final Group settings = new Group(parent, SWT.NONE);
            settings.setLayout(new GridLayout(3, false));
            settings.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
            settings.setText("Trigger on");

            _entryButton = new Button(settings, SWT.CHECK);
            _entryButton.setText("ENTRY");
            _entryButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    setDirty(true);
                }
            });

            _exitButton = new Button(settings, SWT.CHECK);
            _exitButton.setText("EXIT");
            _exitButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    _faultButton.setEnabled(_breakpoint != null && !_exitButton.getSelection());
                    setDirty(true);
                }
            });

            _faultButton = new Button(settings, SWT.CHECK);
            _faultButton.setText("FAULT");
            _faultButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    setDirty(true);
                }
            });

            return settings;
        }

        @Override
        public void setFocus() {
        }

        @Override
        public IStatus getStatus() {
            if (_entryButton.getSelection() || _exitButton.getSelection() || _faultButton.getSelection()) {
                return Status.OK_STATUS;
            }
            return new Status(Status.ERROR, Activator.PLUGIN_ID,
                    "Must select at least one trigger (ENTRY, EXIT or FAULT).");
        }

        @Override
        public Object getInput() {
            return _breakpoint;
        }

        @Override
        public void setInput(Object breakpoint) throws CoreException {
            if (breakpoint instanceof ServiceInterceptBreakpoint) {
                _breakpoint = (ServiceInterceptBreakpoint) breakpoint;
            } else {
                breakpoint = null;
            }
            initialize();
        }

        @Override
        public void doSave() throws CoreException {
            if (_breakpoint != null) {
                final Set<TriggerType> triggers = new HashSet<TriggerType>();
                if (_entryButton.getSelection()) {
                    triggers.add(TriggerType.ENTRY);
                }
                if (_exitButton.getSelection()) {
                    triggers.add(TriggerType.EXIT);
                }
                if (_faultButton.getSelection()) {
                    triggers.add(TriggerType.FAULT);
                }
                InteractionConfigurationBuilder builder = InteractionConfigurationBuilder.createFrom(_breakpoint
                        .getInteractionConfiguration());
                builder.triggers(triggers);
                _breakpoint.setInteractionConfiguration(builder.build());
            }
            setDirty(false);
        }

        private void initialize() {
            if (_breakpoint == null) {
                _entryButton.setEnabled(false);
                _entryButton.setSelection(false);

                _exitButton.setEnabled(false);
                _exitButton.setSelection(false);

                _faultButton.setEnabled(false);
                _faultButton.setSelection(false);
            } else {
                final IInteractionConfiguration config = _breakpoint.getInteractionConfiguration();
                final Set<TriggerType> triggers = config == null ? null : config.getTriggers();

                _entryButton.setEnabled(true);
                _entryButton.setSelection(triggers == null ? true : triggers.contains(TriggerType.ENTRY));

                _exitButton.setEnabled(true);
                _exitButton.setSelection(triggers == null ? true : triggers.contains(TriggerType.EXIT));

                _faultButton.setEnabled(!_exitButton.getSelection());
                _faultButton.setSelection(triggers == null ? true : triggers.contains(TriggerType.FAULT));
            }
            setDirty(false);
        }
    }

}
