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
package org.switchyard.tools.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.soa.sca.sca1_1.model.sca.Component;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentReference;
import org.eclipse.soa.sca.sca1_1.model.sca.ComponentService;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.eclipse.soa.sca.sca1_1.model.sca.JavaInterface;
import org.eclipse.soa.sca.sca1_1.model.sca.Reference;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.soa.sca.sca1_1.model.sca.WSDLPortType;
import org.open.oasis.docs.ns.opencsa.sca.bpel.BPELImplementation;
import org.switchyard.tools.models.switchyard1_0.bean.BeanImplementationType;
import org.switchyard.tools.models.switchyard1_0.bpm.BPMImplementationType;
import org.switchyard.tools.models.switchyard1_0.camel.CamelImplementationType;
import org.switchyard.tools.models.switchyard1_0.clojure.ClojureImplementationType;

/**
 * PlatformResourceAdapterFactory
 * 
 * <p/>
 * Adapter factory for converting SwitchYard types to IResource.
 * 
 * @author Rob Cernich
 */
@SuppressWarnings({"rawtypes", "unchecked" })
public class PlatformResourceAdapterFactory implements IAdapterFactory {

    private static final Class[] ADAPTER_LIST = new Class[] {IResource.class, IFile.class };

    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (!adapterType.isAssignableFrom(IResource.class) && !adapterType.isAssignableFrom(IFile.class)) {
            return null;
        }
        IProject project = getContainingProject(adaptableObject);
        if (project == null) {
            return null;
        }
        if (adaptableObject instanceof Component) {
            Implementation impl = ((Component) adaptableObject).getImplementation();
            if (impl instanceof BeanImplementationType) {
                return SwitchYardModelUtils.getJavaType(project, ((BeanImplementationType) impl).getClass_());
            } else if (impl instanceof BPELImplementation) {
                // TODO: figure this out
                // return SwitchYardModelUtils.getJavaResource(project,
                // ((BPELImplementation) impl).getProcess());
                return null;
            } else if (impl instanceof BPMImplementationType) {
                return SwitchYardModelUtils.getJavaResource(project,
                        ((BPMImplementationType) impl).getProcessDefinition());
            } else if (impl instanceof CamelImplementationType) {
                CamelImplementationType camelImpl = (CamelImplementationType) impl;
                if (camelImpl.getJava() != null) {
                    return SwitchYardModelUtils.getJavaType(project, camelImpl.getJava().getClass_());
                } else if (camelImpl.getXml() != null) {
                    return SwitchYardModelUtils.getJavaResource(project, camelImpl.getXml().getPath());
                }
                return null;
            } else if (impl instanceof ClojureImplementationType) {
                return SwitchYardModelUtils.getJavaResource(project, ((ClojureImplementationType) impl).getScript());
            }
            return null;
        } else if (adaptableObject instanceof Service) {
            Service service = (Service) adaptableObject;
            if (service.getInterface() == null) {
                if (service.getPromote() == null) {
                    return null;
                }
                if (service.getPromote() == null) {
                    return null;
                }
                return getAdapter(service.getPromote(), adapterType);
            }
            return getAdapter(service.getInterface(), adapterType);
        } else if (adaptableObject instanceof Reference) {
            Reference reference = (Reference) adaptableObject;
            if (reference.getInterface() == null) {
                if (reference.getPromote() == null) {
                    return null;
                }
                for (ComponentReference promoted : reference.getPromote()) {
                    if (promoted.getInterface() == null) {
                        continue;
                    }
                    return getAdapter(promoted, adapterType);
                }
                return null;
            }
            return getAdapter(reference.getInterface(), adapterType);
        } else if (adaptableObject instanceof ComponentService) {
            ComponentService service = (ComponentService) adaptableObject;
            if (service.getInterface() == null) {
                return null;
            }
            return getAdapter(service.getInterface(), adapterType);
        } else if (adaptableObject instanceof ComponentReference) {
            ComponentReference reference = (ComponentReference) adaptableObject;
            if (reference.getInterface() == null) {
                return null;
            }
            return getAdapter(reference.getInterface(), adapterType);
        } else if (adaptableObject instanceof JavaInterface) {
            return SwitchYardModelUtils.getJavaType(project, ((JavaInterface) adaptableObject).getInterface());
        } else if (adaptableObject instanceof WSDLPortType) {
            return SwitchYardModelUtils.getJavaResource(project, ((WSDLPortType) adaptableObject).getInterface());
        }
        return null;
    }

    @Override
    public Class[] getAdapterList() {
        return ADAPTER_LIST;
    }

    /**
     * I'm sure there's a better way than this...
     */
    private IProject getContainingProject(Object obj) {
        if (!(obj instanceof EObject)) {
            return null;
        }
        URI uri = ((EObject) obj).eResource().getURI();
        if (uri.isPlatformResource()) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
            if (file == null) {
                return null;
            }
            return file.getProject();
        } else if (uri.isFile()) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(uri.toFileString()));
            if (file == null) {
                return null;
            }
            return file.getProject();
        }
        return null;
    }
}
