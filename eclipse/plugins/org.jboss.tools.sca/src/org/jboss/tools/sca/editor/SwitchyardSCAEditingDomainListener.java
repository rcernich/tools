/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.jboss.tools.sca.editor;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.transaction.ExceptionHandler;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListenerImpl;

public class SwitchyardSCAEditingDomainListener extends TransactionalEditingDomainListenerImpl implements ExceptionHandler {
	
	protected SwitchyardSCAEditor switchyardSCAEditor;
	protected BasicDiagnostic diagnostics;

	public SwitchyardSCAEditingDomainListener(SwitchyardSCAEditor switchyardSCAEditor) {
		super();
		this.switchyardSCAEditor = switchyardSCAEditor;
		TransactionalEditingDomain editingDomain = switchyardSCAEditor.getEditingDomain();
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.setExceptionHandler(this);
	}

	@Override
	public void transactionStarting(TransactionalEditingDomainEvent event) {
		diagnostics = null;
	}

	@Override
	public void handleException(Exception e) {
		String source = null;
		int code = 0;
		String message = e.getMessage();
		Object[] data = null;
		StackTraceElement trace[] = e.getStackTrace();
		if (trace!=null && trace.length>0) {
			source = trace[0].getMethodName();
		}
		if (diagnostics==null) {
			diagnostics = new BasicDiagnostic(source,code,message,data);
		}
		else
			diagnostics.add(new BasicDiagnostic(source,code,message,data));
	}

	public BasicDiagnostic getDiagnostics() {
		return diagnostics;
	}
}
