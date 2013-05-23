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
package org.switchyard.tools.ui.editor.components.camel.ftps;

import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Contract;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.switchyard.tools.ui.editor.diagram.binding.IBindingWizard;
import org.switchyard.tools.ui.editor.diagram.internal.wizards.LinkedWizardBase;

/**
 * CamelFileBindingWizard
 * 
 * <p/>
 * Wizard for creating new CamelFileBindingType objects.
 * 
 * @author Rob Cernich
 */
public class CamelFTPSBindingWizard extends LinkedWizardBase implements IBindingWizard {

    private boolean _showConsumer;
    private CamelFTPSBindingWizardPage _page;
    private Contract _container;

    @Override
    public void addPages() {
        _page = new CamelFTPSBindingWizardPage(CamelFTPSBindingWizardPage.class.getCanonicalName());
        _page.setShowConsumer(_showConsumer);
        addPage(_page);
    }

    @Override
    public Binding getCreatedObject() {
        return _page.getBinding();
    }

    @Override
    public void init(Contract container) {
        // FIXME init
        _showConsumer = container instanceof Service;
        _container = container;
    }

    @Override
    public boolean doFinish() {
        // not much to do
        return true;
    }

    /**
     * @return Target container
     */
    public Contract getTargetContainer() {
        return _container;
    }
}
