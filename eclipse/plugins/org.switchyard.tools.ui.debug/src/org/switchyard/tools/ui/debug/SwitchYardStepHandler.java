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
package org.switchyard.tools.ui.debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.jdt.debug.core.IJavaBreakpoint;
import org.eclipse.jdt.debug.core.IJavaThread;
import org.eclipse.jdt.debug.core.JDIDebugModel;

/**
 * SwitchYardStepHandler
 * <p/>
 * Implements behavior for stepping through SwitchYard service bus processors
 * (intercept->addressing->transaction->security...).
 */
public class SwitchYardStepHandler implements IDebugEventSetListener {

    /** Marker used to identify a step breakpoint. */
    public static final String STEP_MARKER = SwitchYardStepHandler.class.getCanonicalName();

    private final IJavaThread _thread;
    private final List<IJavaBreakpoint> _breakpoints;

    /**
     * Create a new SwitchYardStepHandler.
     * 
     * @param thread the thread to be stepped.
     */
    public SwitchYardStepHandler(IJavaThread thread) {
        _thread = thread;
        _breakpoints = new ArrayList<IJavaBreakpoint>(20);
    }

    /**
     * Register appropriate break events corresponding to entry points for each
     * step in the bus and resume the thread. (Breakpoint locations are derived
     * from the route declared in CamelExchangeBusRoute.)
     * 
     * @throws CoreException if something goes awry.
     */
    public void step() throws CoreException {
        final IDebugTarget debugTarget = _thread.getDebugTarget();
        // InterceptProcessor.process() (CONSUMER/PROVIDER intercepts)
        _breakpoints.add(createBreakpoint("org.switchyard.bus.camel.processors.InterceptProcessor", "process",
                "(Lorg/apache/camel/Exchange;)V"));
        /*
         * AddressingHandler.handleMessage() condition: exchange.getPhase() ==
         * ExchangePhase.IN && exchange.getProvider() == null
         */
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.AddressingHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        /*
         * TransactionHandler.handleMessage()/handleFault() condition:
         * this._transactionManager != null
         */
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.TransactionHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.TransactionHandler", "handleFault",
                "(Lorg/switchyard/Exchange;)V"));
        /*
         * SecurityHandler.handleMessage()/handleFault() condition:
         * this.getServiceSecurity(exchange) != null
         */
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.SecurityHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.SecurityHandler", "handleFault",
                "(Lorg/switchyard/Exchange;)V"));
        // PolicyHandler.handleMessage()
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.PolicyHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        /*
         * ValidateHandler.handleMessage()/handleFault() condition:
         * this.get(exchange) != null
         */
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.ValidateHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.ValidateHandler", "handleFault",
                "(Lorg/switchyard/Exchange;)V"));
        // TransformHandler.handleMessage()/handleFault()
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.TransformHandler", "handleMessage",
                "(Lorg/switchyard/Exchange;)V"));
        _breakpoints.add(createBreakpoint("org.switchyard.handlers.TransformHandler", "handleFault",
                "(Lorg/switchyard/Exchange;)V"));
        // ProviderProcessor().process() (provider call)
        _breakpoints.add(createBreakpoint("org.switchyard.bus.camel.processors.ProviderProcessor", "process",
                "(Lorg/apache/camel/Exchange;)V"));
        // ErrorHandlingProcessor().process()
        _breakpoints.add(createBreakpoint("org.switchyard.bus.camel.processors.ErrorHandlingProcessor", "process",
                "(Lorg/apache/camel/Exchange;)V"));
        /*
         * ConsumerCallbackProcessor().process() condition: reply handler !=
         * null
         */
        _breakpoints.add(createBreakpoint("org.switchyard.bus.camel.processors.ConsumerCallbackProcessor", "process",
                "(Lorg/apache/camel/Exchange;)V"));

        // add the breakpoints
        for (IJavaBreakpoint breakpoint : _breakpoints) {
            breakpoint.setThreadFilter(_thread);
            debugTarget.breakpointAdded(breakpoint);
        }
        DebugPlugin.getDefault().addDebugEventListener(this);
        // resume
        try {
            _thread.resume();
        } catch (Exception e) {
            cleanup();
        }
    }

    @Override
    public void handleDebugEvents(DebugEvent[] events) {
        for (DebugEvent event : events) {
            final Object source = event.getSource();
            if (source != _thread && source != _thread.getDebugTarget()) {
                continue;
            }
            if (event.getKind() == DebugEvent.SUSPEND || event.getKind() == DebugEvent.TERMINATE) {
                // nuke the breakpoints
                cleanup();
            }
        }
    }

    private IJavaBreakpoint createBreakpoint(String type, String method, String signature) throws CoreException {
        final IJavaBreakpoint breakpoint = JDIDebugModel.createMethodEntryBreakpoint(ResourcesPlugin.getWorkspace()
                .getRoot(), type, method, signature, -1, -1, -1, 0, false, null);
        breakpoint.setPersisted(false);
        breakpoint.setThreadFilter(_thread);
        breakpoint.getMarker().setAttribute(STEP_MARKER, true);
        return breakpoint;
    }

    private void cleanup() {
        DebugPlugin.getDefault().removeDebugEventListener(this);
        final IDebugTarget target = _thread.getDebugTarget();
        for (IJavaBreakpoint breakpoint : _breakpoints) {
            target.breakpointRemoved(breakpoint, null);
        }
        Job cleanupJob = new Job("Cleanup SwitchYard Bus Step Breakpoints") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                for (IJavaBreakpoint breakpoint : _breakpoints) {
                    try {
                        breakpoint.delete();
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                }
                _breakpoints.clear();
                return Status.OK_STATUS;
            }
        };
        cleanupJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
        cleanupJob.schedule();
    }
}
