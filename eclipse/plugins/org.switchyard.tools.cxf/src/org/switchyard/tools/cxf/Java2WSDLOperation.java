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
package org.switchyard.tools.cxf;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.wsdl.Definition;
import javax.xml.namespace.QName;

import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.common.WSDLConstants;
import org.apache.cxf.databinding.AbstractDataBinding;
import org.apache.cxf.frontend.AbstractServiceFactory;
import org.apache.cxf.jaxws.support.JaxWsServiceConfiguration;
import org.apache.cxf.service.factory.AbstractServiceConfiguration;
import org.apache.cxf.service.factory.DefaultServiceConfiguration;
import org.apache.cxf.service.factory.ReflectionServiceFactoryBean;
import org.apache.cxf.service.model.OperationInfo;
import org.apache.cxf.service.model.SchemaInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.tools.common.ToolConstants;
import org.apache.cxf.tools.java2wsdl.processor.internal.ServiceBuilderFactory;
import org.apache.cxf.wsdl11.ServiceWSDLBuilder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.w3c.dom.Element;

/**
 * Java2WSDLOperation
 * 
 * <p/>
 * Generates WSDL from a Java class or interface.
 * 
 * @author Rob Cernich
 */
public class Java2WSDLOperation implements IRunnableWithProgress {

    private Java2WSDLOptions _options;
    private Definition _definition;
    private Map<String, Element> _imports = new HashMap<String, Element>();

    /**
     * Create a new Java2WSDLOperation.
     * 
     * @param options the options to use when generating the WSDL.
     */
    public Java2WSDLOperation(Java2WSDLOptions options) {
        _options = options;
    }

    /**
     * @return the generated WSDL file.
     */
    public Definition getGeneratedWSDL() {
        return _definition;
    }

    /**
     * @return any generated schema (imported by the generated WSDL), file name
     *         : schema element.
     */
    public Map<String, Element> getGeneratedSchema() {
        return _imports;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader loader = getProjectBuildClassLoader(_options.getServiceInterface());
            Thread.currentThread().setContextClassLoader(loader);
            ServiceBuilderFactory builderFactory = ServiceBuilderFactory.getInstance(null,
                    ToolConstants.JAXB_DATABINDING);
            builderFactory.setServiceClass(loader.loadClass(_options.getServiceInterface().getFullyQualifiedName()));
            builderFactory.setDatabindingName(ToolConstants.JAXB_DATABINDING);

            AbstractServiceFactory builder = (AbstractServiceFactory) builderFactory.newBuilder();
            builder.setServiceName(new QName(_options.getTargetNamespace(), _options.getServiceName()));
            builder.setAddress(_options.getLocationURI());
            // TODO: should this be configurable?
            builder.setTransportId(WSDLConstants.NS_SOAP11);
            builder.setBindingId(WSDLConstants.NS_SOAP11);
            if (builder.getBindingConfig() == null) {
                builder.setBindingConfig(new SoapBindingConfiguration() {
                    @Override
                    public String getStyle() {
                        return _options.getStyle();
                    }

                    @Override
                    public String getUse() {
                        return _options.getUse();
                    }
                });
            }

            ReflectionServiceFactoryBean serviceFactory = builder.getServiceFactory();
            addCustomConfigurations(serviceFactory);

            AbstractDataBinding dataBinding = (AbstractDataBinding) builder.getDataBinding();
            dataBinding.setNamespaceMap(createNamepsaceMap());

            ServiceInfo service = builder.createService();
            service.setTargetNamespace(_options.getTargetNamespace());
            service.setName(new QName(_options.getTargetNamespace(), _options.getServiceName()));

            ServiceWSDLBuilder wsdlBuilder = new ServiceWSDLBuilder(builder.getBus(), service);
            wsdlBuilder.setUseSchemaImports(_options.isUseImportedSchema());
            wsdlBuilder.setBaseFileName(_options.getServiceName());
            Map<String, SchemaInfo> imports = new HashMap<String, SchemaInfo>();
            _definition = wsdlBuilder.build(imports);

            for (Entry<String, SchemaInfo> entry : imports.entrySet()) {
                _imports.put(entry.getKey(), entry.getValue().getElement());
                // TODO: consider adding schemaLocation a la
                // WSDL11Generator.updateImports()
            }

            // if (def.getImports().size() > 0 || (_options.isUseSchemaImports()
            // && _imports.size() > 0)) {
            // // TODO: spit out an error.
            // }
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(oldLoader);
            BusFactory.setDefaultBus(null);
            BusFactory.setThreadDefaultBus(null);
        }
    }

    private void addCustomConfigurations(ReflectionServiceFactoryBean serviceFactory) {
        for (AbstractServiceConfiguration asc : serviceFactory.getConfigurations()) {
            if (asc instanceof JaxWsServiceConfiguration || asc instanceof DefaultServiceConfiguration) {
                // override default param name generation
                serviceFactory.getServiceConfigurations().add(0, new CustomParamNameServiceConfiguration(asc));
                break;
            }
        }
        serviceFactory.getConfigurations().add(new CustomSOAPActionServiceConfiguration());
    }

    private ClassLoader getProjectBuildClassLoader(IType interfaceType) throws Exception {
        IJavaProject javaProject = interfaceType.getJavaProject();
        IProject project = javaProject.getProject();
        IWorkspaceRoot root = project.getWorkspace().getRoot();
        List<URL> urls = new ArrayList<URL>();
        urls.add(new File(project.getLocation() + "/" + javaProject.getOutputLocation().removeFirstSegments(1) + "/")
                .toURI().toURL());
        for (IClasspathEntry classpathEntry : javaProject.getResolvedClasspath(true)) {
            if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
                IPath projectPath = classpathEntry.getPath();
                IProject otherProject = root.getProject(projectPath.segment(0));
                IJavaProject otherJavaProject = JavaCore.create(otherProject);
                urls.add(new File(otherProject.getLocation() + "/"
                        + otherJavaProject.getOutputLocation().removeFirstSegments(1) + "/").toURI().toURL());
            } else if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
                urls.add(new File(classpathEntry.getPath().toOSString()).toURI().toURL());
            }
        }
        return new URLClassLoader(urls.toArray(new URL[urls.size()]), getClass().getClassLoader());
    }

    private Map<String, String> createNamepsaceMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(_options.getTargetNamespace(), _options.getTargetNamespacePrefix());
        return map;
    }

    private static final class CustomParamNameServiceConfiguration extends AbstractServiceConfiguration {
        private AbstractServiceConfiguration _default;

        private CustomParamNameServiceConfiguration(AbstractServiceConfiguration orig) {
            _default = orig;
        }

        @Override
        public QName getInParameterName(OperationInfo op, Method method, int paramNumber) {
            QName name = _default.getInParameterName(op, method, paramNumber);
            if (name == null || !name.getLocalPart().matches("arg\\d*")) {
                return name;
            }
            return replaceDefaultWithTypeName(method.getParameterTypes()[paramNumber], name);
        }

        @Override
        public QName getOutParameterName(OperationInfo op, Method method, int paramNumber) {
            QName name = _default.getOutParameterName(op, method, paramNumber);
            if (name == null || !name.getLocalPart().matches("return\\d*")) {
                return name;
            }
            return replaceDefaultWithTypeName(method.getReturnType(), name);
        }

        private QName replaceDefaultWithTypeName(Class<?> type, QName name) {
            if (type.isPrimitive()) {
                return name;
            }
            String local = type.getSimpleName();
            if (type.isArray()) {
                local = local.substring(0, local.length() - 2);
            }
            if (local.length() == 0) {
                return name;
            }
            return new QName(name.getNamespaceURI(), local.substring(0, 1).toLowerCase() + local.substring(1));
        }

    }

    private static final class CustomSOAPActionServiceConfiguration extends AbstractServiceConfiguration {
        @Override
        public String getAction(OperationInfo op, Method method) {
            String action = super.getAction(op, method);
            if (action == null || action.length() == 0) {
                return op.getName().getLocalPart();
            }
            return action;
        }
    }

}
