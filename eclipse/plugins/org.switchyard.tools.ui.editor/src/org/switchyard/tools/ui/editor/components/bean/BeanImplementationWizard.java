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
package org.switchyard.tools.ui.editor.components.bean;

import java.util.List;

import org.eclipse.jdt.core.IType;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.switchyard.tools.models.switchyard1_1.bean.BeanFactory;
import org.switchyard.tools.models.switchyard1_1.bean.BeanImplementationType;
import org.switchyard.tools.ui.JavaTypeScanner;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.implementation.IImplementationWizard;
import org.switchyard.tools.ui.editor.diagram.internal.wizards.LinkedWizardBase;

/**
 * BeanImplementationWizard
 * 
 * <p/>
 * Wizard providing support for creating/configuring Bean implementation types.
 * 
 * @author Rob Cernich
 */
public class BeanImplementationWizard extends LinkedWizardBase implements IImplementationWizard {

    private BeanImplementationType _implementation;
    private BeanImplementationWizardPage _page;
    private Component _component;
    private ComponentService _service;
    private List<ComponentReference> _references;

    @Override
    public void addPages() {
        setWindowTitle(Messages.title_beanImplementation);
        _page = new BeanImplementationWizardPage(BeanImplementationWizardPage.class.getCanonicalName());
        _page.setComponent(_component);
        addPage(_page);
    }

    @Override
    public void init(Component container) {
        _component = container;
    }

    @Override
    public Implementation getCreatedObject() {
        return _implementation;
    }

    @Override
    public ComponentService getImplementedService() {
        return _service;
    }

    @Override
    public List<ComponentReference> getImplementationReferences() {
        return _references;
    }

    @Override
    public boolean doFinish() {
        IType type = _page.getBeanClass();
        if (type == null) {
            return false;
        }
        _implementation = BeanFactory.eINSTANCE.createBeanImplementationType();
        _implementation.setClass(type.getFullyQualifiedName());

        // see if there are any services on the class
        final JavaTypeScanner scanner = new JavaTypeScanner(type);
        List<ComponentService> services = scanner.getServices();
        if (services != null && services.size() > 0) {
            _service = services.get(0);
        }
        _references = scanner.getReferences();

        return true;
    }

}
