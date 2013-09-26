/*************************************************************************************
 * Copyright (c) 2011 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.operations;

import static org.switchyard.tools.ui.SwitchYardModelUtils.createSwitchYardModel;
import static org.switchyard.tools.ui.SwitchYardModelUtils.createTargetnamespace;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.m2e.core.embedder.ArtifactKey;
import org.eclipse.m2e.core.project.MavenProjectUtils;
import org.eclipse.ui.ide.undo.CreateFileOperation;
import org.switchyard.config.OutputKey;
import org.switchyard.config.model.ModelPuller;
import org.switchyard.config.model.composite.CompositeModel;
import org.switchyard.config.model.composite.v1.V1CompositeModel;
import org.switchyard.config.model.switchyard.SwitchYardModel;
import org.switchyard.tools.ui.Activator;
import org.switchyard.tools.ui.common.ISwitchYardComponentExtension;
import org.switchyard.tools.ui.common.ISwitchYardProjectWorkingCopy;
import org.switchyard.tools.ui.common.impl.SwitchYardProjectManager;
import org.switchyard.tools.ui.i18n.Messages;

/**
 * AbstractSwitchYardProjectOperation
 * 
 * Executes extension operation, then updates project if necessary.
 * 
 * @author Rob Cernich
 */
public abstract class AbstractSwitchYardProjectOperation implements IWorkspaceRunnable {

    private final static String BEANS_XML_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //$NON-NLS-1$
            + "<beans xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" //$NON-NLS-1$
            + "    xsi:schemaLocation=\"\n" //$NON-NLS-1$
            + "      http://java.sun.com/xml/ns/javaee \n" //$NON-NLS-1$
            + "      http://java.sun.com/xml/ns/javaee/beans_1_0.xsd\">\n" //$NON-NLS-1$
            + "</beans>\n"; //$NON-NLS-1$
    private final static byte[] BEANS_XML_BYTES;
    private ISwitchYardProjectWorkingCopy _workingCopy;
    private String _switchYardVersion;
    private Collection<ISwitchYardComponentExtension> _components;
    private String _label;
    private boolean _addingServices;
    private IAdaptable _uiInfo;
    private boolean _cleanupWorkingCopy;

    /**
     * Create a new CreateBeanServiceOperation. If the extended operation is
     * adding service, the switchyard.xml validation will ensure that a
     * composite element exists.
     * 
     * @param workingCopy the working copy containing any settings changes.
     * @param addingServices true if this operation is adding a service to the
     *            project.
     * @param label monitor task label.
     * @param uiInfo adaptable for UI Shell, may be null.
     */
    public AbstractSwitchYardProjectOperation(ISwitchYardProjectWorkingCopy workingCopy, boolean addingServices,
            String label, IAdaptable uiInfo) {
        _workingCopy = workingCopy;
        _addingServices = addingServices;
        _label = label;
        _uiInfo = uiInfo;
    }

    /**
     * Create a new AbstractSwitchYardProjectOperation.
     * 
     * @param switchYardVersion the version of the SwitchYard dependencies
     * @param components the required components.
     * @param addingServices true if this operation is adding a service to the
     *            project.
     * @param label monitor task label.
     * @param uiInfo adaptable for UI Shell, may be null.
     */
    public AbstractSwitchYardProjectOperation(String switchYardVersion,
            Collection<ISwitchYardComponentExtension> components, boolean addingServices, String label,
            IAdaptable uiInfo) {
        _switchYardVersion = switchYardVersion;
        _components = components;
        _addingServices = addingServices;
        _label = label;
        _uiInfo = uiInfo;
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        MultiStatus status = new MultiStatus(Activator.PLUGIN_ID, 15,
                Messages.AbstractSwitchYardProjectOperation_statusLabel_errorsUpdatingProjectConfig, null);

        monitor.beginTask(_label, 600);
        try {
            IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 100,
                    SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
            try {
                execute(subMonitor);
            } catch (CoreException e) {
                if ((e.getStatus().getSeverity() & (Status.ERROR | Status.CANCEL)) != 0) {
                    throw e;
                } else {
                    status.merge(e.getStatus());
                }
            } finally {
                subMonitor.done();
                subMonitor.setTaskName(""); //$NON-NLS-1$
            }

            // make sure the working copy is setup
            if (_workingCopy == null) {
                _cleanupWorkingCopy = true;
                _workingCopy = SwitchYardProjectManager.instance().getSwitchYardProject(getProject()).createWorkingCopy();
                if (_components != null) {
                    _workingCopy.addComponents(_components);
                }
                _workingCopy.setRuntimeVersion(_switchYardVersion);
            }

            monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_readingPOM);
            subMonitor = new SubProgressMonitor(monitor, 100, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
            if (_workingCopy.needsLoading()) {
                _workingCopy.load(subMonitor);
            }
            subMonitor.done();

            // make sure bean.xml exists
            try {
                monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_creatingBeansXMLFiles);
                // create source beans.xml
                subMonitor = new SubProgressMonitor(monitor, 100, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
                createBeansXMLFile(MavenProjectUtils.getResourceLocations(_workingCopy.getProject(), _workingCopy
                        .getMavenProject().getResources()), subMonitor);
                subMonitor.done();
                subMonitor.setTaskName(""); //$NON-NLS-1$

                // create test beans.xml
                subMonitor = new SubProgressMonitor(monitor, 100, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
                createBeansXMLFile(MavenProjectUtils.getResourceLocations(_workingCopy.getProject(), _workingCopy
                        .getMavenProject().getTestResources()), subMonitor);
            } catch (ExecutionException e) {
                status.merge(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.AbstractSwitchYardProjectOperation_statusMessage_unableToCreateBeansXML, e));
            } finally {
                subMonitor.done();
                subMonitor.setTaskName(""); //$NON-NLS-1$
            }

            // make sure composite exists in switchyard.xml
            try {
                monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_updatingSYXMLFile);
                subMonitor = new SubProgressMonitor(monitor, 100, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
                updateSwitchYardFile(subMonitor);
            } catch (CoreException e) {
                status.merge(e.getStatus());
            } finally {
                subMonitor.done();
                subMonitor.setTaskName(""); //$NON-NLS-1$
            }

            // update the project's pom
            try {
                monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_updatingPOMFile);
                UpdateProjectPomOperation op = new UpdateProjectPomOperation(_workingCopy);
                subMonitor = new SubProgressMonitor(monitor, 100, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
                op.run(subMonitor);
            } catch (CoreException e) {
                status.merge(e.getStatus());
            } finally {
                subMonitor.done();
                subMonitor.setTaskName(""); //$NON-NLS-1$
            }

            if (!status.isOK()) {
                throw new CoreException(status);
            }
        } finally {
            if (_cleanupWorkingCopy) {
                _workingCopy.dispose();
            }
            monitor.subTask(""); //$NON-NLS-1$
            monitor.done();
        }
    }

    /**
     * Perform operation (e.g. create a bean service class). If an exception is
     * thrown from this method, the operation will exit without updating any
     * project files.
     * 
     * @param monitor the progress monitor.
     * @throws CoreException if an error occurs.
     */
    protected abstract void execute(IProgressMonitor monitor) throws CoreException;

    /**
     * @return the project upon which the operation is acting.
     */
    protected abstract IProject getProject();

    private void createBeansXMLFile(IPath[] resourceLocations, IProgressMonitor monitor) throws ExecutionException {
        IFile beansFile = null;
        IProject project = _workingCopy.getProject();
        for (IPath resourceLocation : resourceLocations) {
            IFile temp = project.getFolder(resourceLocation).getFile("META-INF/beans.xml"); //$NON-NLS-1$
            if (temp.exists()) {
                beansFile = temp;
                break;
            }
        }
        if (beansFile == null && resourceLocations.length > 0) {
            // TODO: find the first resource root that would include a beans.xml
            // file
            IFile beansIFile = project.getFolder(resourceLocations[0]).getFile("META-INF/beans.xml"); //$NON-NLS-1$
            CreateFileOperation op = new CreateFileOperation(beansIFile, null, new ByteArrayInputStream(BEANS_XML_BYTES), Messages.AbstractSwitchYardProjectOperation_operationLabel_creatingBeansXMLFile);
            op.execute(monitor, _uiInfo);
        }
    }

    private void updateSwitchYardFile(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_updatingSYXML, 100);
        try {
            monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_readingSYXML);
            IFile switchYardFile = _workingCopy.getSwitchYardConfigurationFile();

            boolean modelUpdated = false;
            SwitchYardModel switchYardModel;
            if (!switchYardFile.exists()) {
                ArtifactKey key = new ArtifactKey(_workingCopy.getMavenProject().getArtifact());
                switchYardModel = createSwitchYardModel(key.getArtifactId(),
                        createTargetnamespace(key.getGroupId(), key.getArtifactId(), key.getVersion()));
                modelUpdated = true;
            } else {
                InputStream is = switchYardFile.getContents(true);
                try {
                    switchYardModel = new ModelPuller<SwitchYardModel>().pull(is);
                } finally {
                    is.close();
                }
            }
            monitor.worked(50);

            if (_addingServices) {
                CompositeModel composite = switchYardModel.getComposite();
                if (composite == null) {
                    composite = new V1CompositeModel();
                    composite.setName(switchYardModel.getName());
                    composite.setTargetNamespace(switchYardModel.getTargetNamespace());
                    switchYardModel.setComposite(composite);
                    modelUpdated = true;
                } else {
                    if (composite.getName() == null || composite.getName().length() == 0) {
                        composite.setName(switchYardModel.getName());
                        modelUpdated = true;
                    } else if (composite.getTargetNamespace() == null || composite.getTargetNamespace().length() == 0) {
                        composite.setTargetNamespace(switchYardModel.getTargetNamespace());
                        modelUpdated = true;
                    }
                }
            }

            if (!modelUpdated) {
                return;
            }

            monitor.subTask(Messages.AbstractSwitchYardProjectOperation_taskLabel_writingUpdatedSYXML);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            switchYardModel.getModelConfiguration().write(baos, new OutputKey[0]);
            if (switchYardFile.exists()) {
                switchYardFile.setContents(new ByteArrayInputStream(baos.toByteArray()), true, true,
                        new SubProgressMonitor(monitor, 50));
            } else {
                try {
                    new CreateFileOperation(switchYardFile, null, new ByteArrayInputStream(baos.toByteArray()),
                            Messages.AbstractSwitchYardProjectOperation_operationLabel_creatingSYXMLFile).execute(monitor, _uiInfo);
                } catch (ExecutionException e) {
                    if (e.getCause() instanceof CoreException) {
                        throw (CoreException) e.getCause();
                    }
                    throw e;
                }
            }
        } catch (Exception e) {
            throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.AbstractSwitchYardProjectOperation_exceptionMessage_errorUpdatingSYXML, e));
        } finally {
            monitor.done();
        }
    }

    static {
        byte[] bytes = new byte[0];
        try {
            bytes = BEANS_XML_CONTENT.getBytes("UTF-8"); //$NON-NLS-1$
        } catch (Exception e) {
            bytes = BEANS_XML_CONTENT.getBytes();
        }
        BEANS_XML_BYTES = bytes;
    }
}
