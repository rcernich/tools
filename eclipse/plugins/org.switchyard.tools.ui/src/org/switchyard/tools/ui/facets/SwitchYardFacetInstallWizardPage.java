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
package org.switchyard.tools.ui.facets;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;
import org.eclipse.wst.common.project.facet.ui.IFacetWizardPage;
import org.sonatype.aether.version.Version;
import org.switchyard.tools.ui.common.ILayoutUtilities;
import org.switchyard.tools.ui.common.ISwitchYardComponentExtension;
import org.switchyard.tools.ui.common.SwitchYardComponentExtensionManager;
import org.switchyard.tools.ui.common.SwitchYardSettingsGroup;
import org.switchyard.tools.ui.i18n.Messages;

/**
 * SwitchYardFacetInstallWizardPage
 * 
 * Wizard page for configuring SwitchYard facet.
 * 
 * @author Rob Cernich
 */
public class SwitchYardFacetInstallWizardPage extends AbstractFacetWizardPage implements IFacetWizardPage,
        ISwitchYardFacetConstants, ILayoutUtilities {

    private boolean _isInitialized;
    private SwitchYardSettingsGroup _settingsGroup;
    private IDataModel _config;

    /**
     * Create a new SwitchYardFacetInstallWizardPage.
     */
    public SwitchYardFacetInstallWizardPage() {
        super(Messages.SwitchYardFacetInstallWizardPage_pageName_switchYard);
        setTitle(Messages.SwitchYardFacetInstallWizardPage_pageTitle_switchYard);
        setDescription(Messages.SwitchYardFacetInstallWizardPage_pageDescription_switchYard);
    }

    @Override
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);

        Composite content = new Composite(parent, SWT.NONE);
        content.setLayout(new GridLayout());
        content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        _settingsGroup = new SwitchYardSettingsGroup(content, this, getWizard().getContainer());

        _settingsGroup.getRuntimeVersionsList().addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()) {
                    _config.setProperty(RUNTIME_VERSION, null);
                } else {
                    _config.setProperty(RUNTIME_VERSION,
                            (Version) ((IStructuredSelection) event.getSelection()).getFirstElement());
                }
                validate();
            }
        });
        _settingsGroup.getTargetRuntimesList().addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()
                        || ((IStructuredSelection) event.getSelection()).getFirstElement() == SwitchYardSettingsGroup.NULL_RUNTIME) {
                    _config.setProperty(RUNTIME_TARGET, null);
                } else {
                    _config.setProperty(RUNTIME_TARGET, ((IStructuredSelection) event.getSelection()).getFirstElement());
                }
                validate();
            }
        });

        setControl(content);
    }

    @Override
    public void transferStateToConfig() {
        super.transferStateToConfig();
        _config.setProperty(RUNTIME_COMPONENTS, _settingsGroup.getSelectedComponents());
    }

    @Override
    public void setConfig(Object config) {
        _config = (IDataModel) config;
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible && !_isInitialized) {
            initControls();
            // clear out any error the first time we are displayed
            setErrorMessage(null);
            _isInitialized = true;
        }
        super.setVisible(visible);
    }

    @SuppressWarnings("unchecked")
    private void initControls() {
        if (_settingsGroup == null) {
            return;
        }
        // if (_config.isPropertySet(RUNTIME_PROVIDED)) {
        // _settingsGroup.getRuntimeProvidedCheckbox().setSelection(_config.getBooleanProperty(RUNTIME_PROVIDED));
        // } else {
        // _settingsGroup.getRuntimeProvidedCheckbox().setSelection(
        // (Boolean) _config.getDefaultProperty(RUNTIME_PROVIDED));
        // }
        if (_config.isPropertySet(RUNTIME_TARGET)) {
            _settingsGroup.getTargetRuntimesList().setSelection(
                    new StructuredSelection(_config.getProperty(RUNTIME_TARGET)), true);
            if (_config.isPropertySet(RUNTIME_VERSION)) {
                // override the default runtime version if it's already specified in the config
                _settingsGroup.getRuntimeVersionsList().setSelection(
                        new StructuredSelection(_config.getProperty(RUNTIME_VERSION)), true);
            }
        } else {
            if (_config.isPropertySet(RUNTIME_VERSION)) {
                _settingsGroup.getRuntimeVersionsList().setSelection(
                        new StructuredSelection(_config.getProperty(RUNTIME_VERSION)), true);
                _settingsGroup.getTargetRuntimesList().setSelection(
                        new StructuredSelection(SwitchYardSettingsGroup.NULL_RUNTIME), true);
            } else if (_settingsGroup.getSelectedTargetRuntime() != null) {
                /*
                 * no target or version set so use default selections to
                 * initialize the config.
                 */
                _config.setProperty(RUNTIME_TARGET, _settingsGroup.getSelectedTargetRuntime());
                _config.setProperty(RUNTIME_VERSION, ((IStructuredSelection) _settingsGroup.getRuntimeVersionsList()
                        .getSelection()).getFirstElement());
            } else if (_settingsGroup.getRuntimeVersionsList().getSelection().isEmpty()) {
                /* no target or version selected by default, so we'll specify one now. */
                Version defaultVersion = (Version) _config.getDefaultProperty(RUNTIME_VERSION);
                if (defaultVersion != null) {
                    _settingsGroup.getRuntimeVersionsList().setSelection(new StructuredSelection(defaultVersion), true);
                }
            } else {
                // update the config to use the default selected version
                _config.setProperty(RUNTIME_VERSION, ((IStructuredSelection) _settingsGroup.getRuntimeVersionsList()
                        .getSelection()).getFirstElement());
            }
        }
        if (_config.isPropertySet(RUNTIME_COMPONENTS)) {
            _settingsGroup.setCheckedComponents(
                    (Set<ISwitchYardComponentExtension>) _config.getProperty(RUNTIME_COMPONENTS), true);
        } else {
            _settingsGroup.setCheckedComponents(
                    (Set<ISwitchYardComponentExtension>) _config.getDefaultProperty(RUNTIME_COMPONENTS), true);
        }
        _settingsGroup.setCheckedComponents(
                Collections.singleton(SwitchYardComponentExtensionManager.instance().getRuntimeComponentExtension()),
                true);
    }

    private void validate() {
        IStatus status = _config.validate();
        switch (status.getSeverity()) {
        case IStatus.OK:
            setErrorMessage(null);
            break;
        case IStatus.INFO:
            setMessage(status.getMessage(), INFORMATION);
            break;
        case IStatus.WARNING:
            setMessage(status.getMessage(), WARNING);
            break;
        case IStatus.ERROR:
        default:
            setErrorMessage(status.getMessage());
            break;
        }
        setPageComplete(getErrorMessage() == null);
    }

    @Override
    public GridData setButtonLayoutData(Button button) {
        return super.setButtonLayoutData(button);
    }

}
