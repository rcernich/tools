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

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Composite;
import org.eclipse.soa.sca.sca1_1.model.sca.ScaFactory;
import org.eclipse.ui.PlatformUI;
import org.switchyard.tools.models.switchyard1_1.bean.BeanFactory;
import org.switchyard.tools.models.switchyard1_1.bean.BeanImplementationType;
import org.switchyard.tools.ui.JavaTypeScanner;
import org.switchyard.tools.ui.JavaUtil;
import org.switchyard.tools.ui.PlatformResourceAdapterFactory;
import org.switchyard.tools.ui.editor.diagram.component.IComponentWizard;
import org.switchyard.tools.ui.wizards.NewBeanServiceWizard;

/**
 * NewBeanComponentWizard
 * 
 * <p/>
 * Creates a new component based on a Bean service.
 * 
 * @author Rob Cernich
 */
public class NewBeanComponentWizard extends NewBeanServiceWizard implements IComponentWizard {

    private Component _component;

    /**
     * Create a new NewBeanComponentWizard.
     * 
     * @param openAfterCreate true if the new file should be opened in an
     *            editor.
     */
    public NewBeanComponentWizard(boolean openAfterCreate) {
        super(false, openAfterCreate);
    }

    @Override
    public Component getCreatedObject() {
        return _component;
    }

    @Override
    public void init(Composite container) {
        if (container == null || !(getSelection() == null || getSelection().isEmpty())) {
            return;
        }
        IProject project = PlatformResourceAdapterFactory.getContainingProject(container);
        if (project == null) {
            return;
        }
        IJavaElement element = JavaUtil.getInitialPackageForProject(JavaCore.create(project));
        StructuredSelection selection = element == null ? StructuredSelection.EMPTY : new StructuredSelection(element);
        init(getWorkbench() == null ? PlatformUI.getWorkbench() : getWorkbench(), selection);
    }

    @Override
    public boolean performFinish() {
        if (super.performFinish()) {
            IType type = getNewClass();

            BeanImplementationType implementation = BeanFactory.eINSTANCE.createBeanImplementationType();
            implementation.setClass(type.getFullyQualifiedName());

            _component = ScaFactory.eINSTANCE.createComponent();
            _component.setName(type.getElementName());
            _component.setImplementation(implementation);

            // see if there are any services on the class
            final JavaTypeScanner scanner = new JavaTypeScanner(type);
            List<ComponentService> services = scanner.getServices();
            if (services != null && services.size() > 0) {
                _component.getService().add(services.get(0));
            } else if (getServiceContract() instanceof ComponentService) {
                // fallback to service contract
                _component.getService().add((ComponentService) getServiceContract());
            }
            List<ComponentReference> references = scanner.getReferences();
            if (references != null) {
                references.addAll(references);
            }

            return true;
        }
        return false;
    }

}
