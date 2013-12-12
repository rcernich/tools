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
package org.switchyard.tools.ui.common;

import static org.switchyard.tools.ui.M2EUtils.resolveSwitchYardVersionRange;
import static org.switchyard.tools.ui.facets.ISwitchYardFacetConstants.FSW_RUNTIME_ID;
import static org.switchyard.tools.ui.facets.ISwitchYardFacetConstants.SWITCHYARD_FACET;
import static org.switchyard.tools.ui.facets.ISwitchYardFacetConstants.SWITCHYARD_RUNTIME_ID;
import static org.switchyard.tools.ui.facets.ISwitchYardFacetConstants.SWITCHYARD_RUNTIME_VERSION_KEY;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponent;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;
import org.eclipse.wst.common.project.facet.ui.IRuntimeComponentLabelProvider;
import org.sonatype.aether.util.version.GenericVersionScheme;
import org.sonatype.aether.version.InvalidVersionSpecificationException;
import org.sonatype.aether.version.Version;
import org.switchyard.tools.ui.Activator;
import org.switchyard.tools.ui.common.ISwitchYardComponentExtension.Category;
import org.switchyard.tools.ui.facets.ISwitchYardFacetConstants;
import org.switchyard.tools.ui.wizards.NewSwitchYardProjectWizard;

/**
 * SwitchYardSettingsGroup
 * 
 * <p/>
 * Controls for editing/viewing for SwitchYard settings.
 * 
 * @author Rob Cernich
 */
public class SwitchYardSettingsGroup {

    /** The "None" runtime. */
    public static final Object NULL_RUNTIME = new Object();

    private IRunnableContext _context;
    private ComboViewer _runtimesList;
    private ComboViewer _runtimeVersionsList;
    private ComboViewer _configVersionsList;
    private Button _runtimeProvidedCheckbox;
    private CheckboxTreeViewer _componentsTable;
    private Text _descriptionText;
    private IRuntimeComponent _initialComponent;
    private List<Object> _compatibleRuntimes;
    private Set<Version> _availableVersions;
    private IFacetedProjectWorkingCopy _project;
    private IFacetedProjectListener _projectListener = new IFacetedProjectListener() {
        @Override
        public void handleEvent(IFacetedProjectEvent event) {
            switch (event.getType()) {
            case PROJECT_FACETS_CHANGED:
                _configVersionsList.setSelection(
                        new StructuredSelection(_project
                                .getProjectFacetVersion(ISwitchYardFacetConstants.SWITCHYARD_FACET)), true);
                // filter out any invalid runtimes
            case TARGETABLE_RUNTIMES_CHANGED:
                repopulateRuntimesList();
                break;
            default:
                break;
            }
        }
    };
    private ViewerFilter _runtimesFilter = new ViewerFilter() {
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            if (element == NULL_RUNTIME) {
                return true;
            }
            if (_project == null) {
                final IRuntime runtime = ((IRuntimeComponent) element).getRuntime();
                return runtime.supports(getSelectedConfigurationVersion());
            } else {
                final IRuntime runtime = ((IRuntimeComponent) element).getRuntime();
                for (IProjectFacetVersion ipfv : _project.getProjectFacets()) {
                    if (!runtime.supports(ipfv)) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

    /**
     * Create a new SwitchYardSettingsGroup.
     * 
     * @param parent the parent composite
     * @param layoutUtilities utilities to help with layout.
     * @param context a runnable context to use for long running operations.
     */
    public SwitchYardSettingsGroup(Composite parent, ILayoutUtilities layoutUtilities, IRunnableContext context) {
        super();
        _context = context;

        Composite content = new Composite(parent, SWT.NONE);
        content.setLayout(new GridLayout());
        content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createRuntimeControls(content);

        createComponentControls(content);

        initControls();
    }

    private void createRuntimeControls(Composite content) {
        Group runtimeControls = new Group(content, SWT.NONE);
        runtimeControls.setLayout(new GridLayout(3, false));
        runtimeControls.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        runtimeControls.setText("SwitchYard Version Details");

        Label label = new Label(runtimeControls, SWT.NONE);
        label.setText("Configuration Version:");

        _configVersionsList = new ComboViewer(runtimeControls);
        _configVersionsList.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        _configVersionsList.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                return ((IProjectFacetVersion) element).getVersionString();
            }
        });
        _configVersionsList.setContentProvider(ArrayContentProvider.getInstance());
        _configVersionsList.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                handleConfigVersionSelected();
            }
        });
        _configVersionsList.setInput(ISwitchYardFacetConstants.SWITCHYARD_FACET.getVersions());
        
        // spacer
        new Label(runtimeControls, SWT.NONE);

        label = new Label(runtimeControls, SWT.NONE);
        label.setText("Target Runtime:");

        _runtimesList = new ComboViewer(runtimeControls);
        _runtimesList.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _runtimesList.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IRuntimeComponent) {
                    final IRuntimeComponent component = (IRuntimeComponent) element;
                    final StringBuffer text = new StringBuffer(IRuntimeComponentLabelProvider.class.cast(
                            component.getAdapter(IRuntimeComponentLabelProvider.class)).getLabel());
                    text.append(" [").append(component.getRuntime().getName()).append("]");
                    return text.toString();
                }
                return "<None>";
            }
        });
        _runtimesList.setContentProvider(ArrayContentProvider.getInstance());
        _runtimesList.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                handleRuntimeSelected();
            }
        });
        _runtimesList.setSorter(new RuntimesListSorter());
        _runtimesList.addFilter(_runtimesFilter);
        _runtimesList.getControl().addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent event) {
                if (_project != null) {
                    _project.removeListener(_projectListener);
                }
            }
        });

        final Link newRuntimeLink = new Link(runtimeControls, SWT.NONE);
        newRuntimeLink.setText("<a>Configure runtimes...</a>");
        newRuntimeLink.setEnabled(true);
        newRuntimeLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                String id = "org.eclipse.wst.server.ui.preferencePage";
                String id2 = "org.eclipse.wst.server.ui.runtime.preferencePage";
                String id3 = "org.jboss.tools.runtime.preferences.RuntimePreferencePage";
                final PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(newRuntimeLink.getShell(),
                        id2, new String[] {id, id2, id3 }, null);
                if (dialog.open() == PreferenceDialog.OK) {
                    repopulateRuntimesList();
                }
            }
        });

        label = new Label(runtimeControls, SWT.NONE);
        label.setText("Libary Version:");

        _runtimeVersionsList = new VersionComboViewer(runtimeControls);
        _runtimeVersionsList.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        _runtimeVersionsList.setLabelProvider(new LabelProvider());
        _runtimeVersionsList.setContentProvider(ArrayContentProvider.getInstance());

        // spacer
        new Label(runtimeControls, SWT.NONE);
    }

    private void createComponentControls(Composite content) {
        Group componentControls = new Group(content, SWT.NONE);
        componentControls.setLayout(new GridLayout());
        componentControls.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        componentControls.setText("SwitchYard Components");

        _componentsTable = new CheckboxTreeViewer(componentControls, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL
                | SWT.V_SCROLL);
        _componentsTable.setLabelProvider(new ComponentsLabelProvider());
        _componentsTable.setContentProvider(new ComponentsContentProvider());
        _componentsTable.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        _componentsTable.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = _componentsTable.getSelection();
                if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
                    Object selected = ((IStructuredSelection) selection).getFirstElement();
                    if (selected instanceof ISwitchYardComponentExtension) {
                        String description = ((ISwitchYardComponentExtension) selected).getDescription();
                        if (description == null || description.length() == 0) {
                            description = "unavailable";
                        }
                        _descriptionText.setText(description);
                    } else {
                        _descriptionText.setText("");
                    }
                } else {
                    _descriptionText.setText("");
                }
            }
        });
        _componentsTable.addFilter(new ViewerFilter() {
            private final Object _runtimeComponent = SwitchYardComponentExtensionManager.instance()
                    .getRuntimeComponentExtension();
            private final boolean _hasUnknownComponents = SwitchYardComponentExtensionManager.instance()
                    .getComponentExtensions(Category.UNKNOWN).size() > 1;

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element != _runtimeComponent && (element != Category.UNKNOWN || _hasUnknownComponents);
            }
        });
        _componentsTable.setSorter(new ComponentsSorter());
        _componentsTable.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                updateCheckState(event.getElement(), event.getChecked());
            }
        });

        Group detailsGroup = new Group(componentControls, SWT.NONE);
        detailsGroup.setText("Description");
        detailsGroup.setLayout(new GridLayout());
        detailsGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        _descriptionText = new Text(detailsGroup, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL | SWT.V_SCROLL);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = _descriptionText.getLineHeight() * 3;
        _descriptionText.setLayoutData(gd);
    }

    /**
     * @param project the project associated with these controls.
     */
    public void setProject(IFacetedProjectWorkingCopy project) {
        if (_project != null) {
            _project.removeListener(_projectListener);
        }
        _project = project;
        _runtimesList.refresh();
        if (_project != null) {
            _configVersionsList
                    .setSelection(
                            new StructuredSelection(_project
                                    .getProjectFacetVersion(ISwitchYardFacetConstants.SWITCHYARD_FACET)), true);
            _project.addListener(_projectListener, IFacetedProjectEvent.Type.TARGETABLE_RUNTIMES_CHANGED,
                    IFacetedProjectEvent.Type.PROJECT_FACETS_CHANGED);
        }
    }

    /**
     * @return the selected target runtime component.
     */
    public IRuntimeComponent getSelectedTargetRuntime() {
        final IStructuredSelection selection = (IStructuredSelection) _runtimesList.getSelection();
        if (selection == null || selection.isEmpty() || selection.getFirstElement() == NULL_RUNTIME) {
            return null;
        }
        return (IRuntimeComponent) selection.getFirstElement();
    }

    /**
     * @return the selected configuration version.
     */
    public IProjectFacetVersion getSelectedConfigurationVersion() {
        final IStructuredSelection selection = (IStructuredSelection) _configVersionsList.getSelection();
        if (selection == null || selection.isEmpty() || selection.getFirstElement() == NULL_RUNTIME) {
            return null;
        }
        return (IProjectFacetVersion) selection.getFirstElement();
    }

    /**
     * @return the available SwitchYard runtime versions.
     */
    public Set<Version> getAvailableVersions() {
        return _availableVersions;
    }

    /**
     * @return the components viewer.
     */
    public CheckboxTreeViewer getComponentsTable() {
        return _componentsTable;
    }

    /**
     * @return the runtime versions viewer.
     */
    public StructuredViewer getRuntimeVersionsList() {
        return _runtimeVersionsList;
    }

    /**
     * @return the target runtimes viewer.
     */
    public StructuredViewer getTargetRuntimesList() {
        return _runtimesList;
    }

    /**
     * @return the runtime provided checkbox.
     */
    public Button getRuntimeProvidedCheckbox() {
        return _runtimeProvidedCheckbox;
    }

    /**
     * @return components added by the user.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    public Set<ISwitchYardComponentExtension> getSelectedComponents() {
        Set selectedComponents = new LinkedHashSet((List) Arrays.asList(_componentsTable.getCheckedElements()));
        selectedComponents.add(SwitchYardComponentExtensionManager.instance().getRuntimeComponentExtension());
        selectedComponents.removeAll(Arrays.asList(Category.values()));
        return selectedComponents;
    }

    /**
     * Set the check state for the set of components.
     * 
     * @param components the components to check/uncheck.
     * @param checked true, checked; false, unchecked.
     */
    public void setCheckedComponents(Set<ISwitchYardComponentExtension> components, boolean checked) {
        if (components == null || components.isEmpty() || _componentsTable == null
                || _componentsTable.getTree().isDisposed()) {
            return;
        }
        for (ISwitchYardComponentExtension component : components) {
            _componentsTable.setChecked(component, checked);
            updateCheckState(component, checked);
        }
    }

    private void handleConfigVersionSelected() {
        IStructuredSelection selection = (IStructuredSelection) _configVersionsList.getSelection();
        if (selection == null || selection.isEmpty()) {
            return;
        }
        if (_project != null) {
            _project.changeProjectFacetVersion((IProjectFacetVersion) selection.getFirstElement());
        }
        // update in the event the selected runtime is no longer compatible
        repopulateRuntimesList();
    }

    private void handleRuntimeSelected() {
        IStructuredSelection selection = (IStructuredSelection) _runtimesList.getSelection();
        if (selection == null || selection.isEmpty() || selection.getFirstElement() == NULL_RUNTIME) {
            _runtimeVersionsList.getCombo().setEnabled(true);
        } else {
            final Version version = getRuntimeVersion((IRuntimeComponent) selection.getFirstElement());
            if (version != null) {
                _runtimeVersionsList.setSelection(new StructuredSelection(version));
            }
            _runtimeVersionsList.getCombo().setEnabled(false);
        }
    }

    private Version getRuntimeVersion(IRuntimeComponent component) {
        return parseVersion(component.getProperty(SWITCHYARD_RUNTIME_VERSION_KEY));
    }

    private void initControls() {
        if (_project == null) {
            _configVersionsList.setSelection(
                    new StructuredSelection(ISwitchYardFacetConstants.SWITCHYARD_FACET.getDefaultVersion()), true);
        } else {
            _configVersionsList
                    .setSelection(
                            new StructuredSelection(_project
                                    .getProjectFacetVersion(ISwitchYardFacetConstants.SWITCHYARD_FACET)), true);
        }
        try {
            _context.run(false, true, new IRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask("Loading available SwitchYard capabilities.", 100);
                    try {
                        populateComponentsTable();
                        monitor.worked(25);
                        populateRuntimesList();
                        monitor.worked(25);
                        populateRuntimeVersionsList(new SubProgressMonitor(monitor, 50));
                        if (_initialComponent == null) {
                            _runtimesList.setSelection(new StructuredSelection(NULL_RUNTIME), true);
                            _runtimeVersionsList.setSelection(new StructuredSelection(_availableVersions.iterator().next()));
                        } else {
                            // TODO: use preferences
                            _runtimesList.setSelection(new StructuredSelection(_initialComponent), true);
                        }
                    } finally {
                        monitor.done();
                    }
                }

            });
        } catch (Exception e) {
            if (e.getCause() instanceof CoreException) {
                Activator
                        .getDefault()
                        .getLog()
                        .log(new MultiStatus(Activator.PLUGIN_ID, -1, new IStatus[] {((CoreException) e.getCause())
                                .getStatus() }, "Error loading available SwitchYard capabilities.", e));
            } else {
                Activator
                        .getDefault()
                        .getLog()
                        .log(new Status(Status.ERROR, Activator.PLUGIN_ID,
                                "Error loading available SwitchYard capabilities.", e));
            }
        }
    }

    private void populateRuntimesList() {
        final Set<IRuntimeComponent> compatibleRuntimes = new LinkedHashSet<IRuntimeComponent>();
        for (IProjectFacetVersion ipfv : SWITCHYARD_FACET.getVersions()) {
            final Set<IRuntime> runtimes = RuntimeManager.getRuntimes(Collections.singleton(ipfv));
            final ViewerSorter sorter = new RuntimesListSorter();
            for (IRuntime runtime : runtimes) {
                for (IRuntimeComponent component : runtime.getRuntimeComponents()) {
                    if (SWITCHYARD_RUNTIME_ID.equals(component.getRuntimeComponentType().getId())
                            || FSW_RUNTIME_ID.equals(component.getRuntimeComponentType().getId())) {
                        compatibleRuntimes.add(component);
                        if (_runtimesFilter.select(null, null, component)) {
                            if (_initialComponent == null) {
                                _initialComponent = component;
                            } else {
                                if (sorter.compare(null, component, _initialComponent) < 0) {
                                    _initialComponent = component;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        _compatibleRuntimes = new ArrayList<Object>(compatibleRuntimes.size() + 1);
        _compatibleRuntimes.add(NULL_RUNTIME);
        _compatibleRuntimes.addAll(compatibleRuntimes);
        _runtimesList.setInput(_compatibleRuntimes);
    }

    @SuppressWarnings("unchecked")
    private void populateRuntimeVersionsList(IProgressMonitor monitor) {
        try {
            _availableVersions = filterSwitchYardVersions(resolveSwitchYardVersionRange(monitor).getVersions());
        } catch (Exception e) {
            _availableVersions = new LinkedHashSet<Version>();
        }
        // add default version
        final Version defaultVersion = parseVersion(NewSwitchYardProjectWizard.DEFAULT_RUNTIME_VERSION);
        _availableVersions.add(defaultVersion);

        // add known runtime versions
        for (Object obj : (List<Object>) _runtimesList.getInput()) {
            if (obj == NULL_RUNTIME) {
                continue;
            }
            Version version = getRuntimeVersion((IRuntimeComponent) obj);
            if (version != null && !_availableVersions.contains(version)) {
                _availableVersions.add(version);
            }
        }
        _runtimeVersionsList.setInput(_availableVersions);
    }

    private Set<Version> filterSwitchYardVersions(List<Version> versions) {
        final String onePointZero = "1.0";
        final Set<Version> filtered = new LinkedHashSet<Version>();
        String previousMajorMinor = null;
        boolean previousWasSnapshot = false;
        /* assumes list is sorted lowest to highest. */
        for (ListIterator<Version> it = versions.listIterator(versions.size()); it.hasPrevious();) {
            Version next = it.previous();
            String version = next.toString();
            String majorMinor = getMajorMinor(version);
            try {
                if (previousMajorMinor == null) {
                    filtered.add(next);
                    previousWasSnapshot = version.endsWith("-SNAPSHOT");
                } else if (version.endsWith("-SNAPSHOT")) {
                    // skip snapshots
                    continue;
                } else if (previousMajorMinor.equals(majorMinor) && !previousWasSnapshot) {
                    // we already have this version
                    continue;
                } else {
                    // don't have this one yet
                    filtered.add(next);
                    previousWasSnapshot = false;
                    if (onePointZero.equals(majorMinor)) {
                        // nothing before 1.0
                        break;
                    }
                }
            } finally {
                previousMajorMinor = majorMinor;
            }
        }
        return filtered;
    }

    private String getMajorMinor(final String version) {
        final int firstDot = version.indexOf('.');
        final int secondDot = firstDot < 0 ? -1 : version.indexOf('.', firstDot + 1);
        if (secondDot < 0) {
            return version;
        }
        return version.substring(0, secondDot);
    }

    private void populateComponentsTable() {
        _componentsTable.setInput(SwitchYardComponentExtensionManager.instance());
        _componentsTable.setExpandedElements(EnumSet.of(Category.IMPLEMENTATION).toArray());
    }

    private void updateCheckState(Object element, boolean checked) {
        final ITreeContentProvider contentProvider = (ITreeContentProvider) _componentsTable.getContentProvider();
        // ensure children are checked
        _componentsTable.setSubtreeChecked(element, checked);
        _componentsTable.setGrayed(element, false);
        for (Object parent = contentProvider.getParent(element), input = _componentsTable.getInput(); parent != null
                && parent != input; parent = contentProvider.getParent(parent)) {
            final Object[] siblings = contentProvider.getChildren(parent);
            int count = 0;
            for (int i = 0; i < siblings.length; i++) {
                if (_componentsTable.getChecked(siblings[i])) {
                    count++;
                }
            }
            if (count == 0) {
                _componentsTable.setGrayChecked(parent, false);
            } else if (count == siblings.length) {
                _componentsTable.setChecked(parent, true);
                _componentsTable.setGrayed(parent, false);
            } else {
                _componentsTable.setGrayChecked(parent, true);
            }
        }
    }

    private Version parseVersion(final String text) {
        if (text == null || text.length() == 0) {
            return null;
        }
        try {
            return new GenericVersionScheme().parseVersion(text);
        } catch (InvalidVersionSpecificationException e) {
            return null;
        }
    }

    private void repopulateRuntimesList() {
        _runtimesList.getControl().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IStructuredSelection selection = (IStructuredSelection) _runtimesList.getSelection();
                _initialComponent = null;
                populateRuntimesList();
                if (selection.isEmpty() || !_runtimesFilter.select(null, null, selection.getFirstElement())) {
                    if (_initialComponent == null) {
                        _runtimesList.setSelection(new StructuredSelection(NULL_RUNTIME), true);
                    } else {
                        _runtimesList.setSelection(new StructuredSelection(_initialComponent), true);
                    }
                } else {
                    _runtimesList.setSelection(selection, true);
                }
            };
        });
    }

    private final class VersionComboViewer extends ComboViewer {
        private VersionComboViewer(Composite parent) {
            super(parent, SWT.DROP_DOWN);
            getCombo().addModifyListener(new ModifyListener() {
                @Override
                public void modifyText(ModifyEvent e) {
                    SelectionChangedEvent event = new SelectionChangedEvent(VersionComboViewer.this, getSelection());
                    fireSelectionChanged(event);
                }
            });
        }

        @Override
        protected List<?> getSelectionFromWidget() {
            if (getCombo().getSelectionIndex() < 0) {
                List<Version> selected = new ArrayList<Version>();
                Version version = parseVersion(getCombo().getText());
                if (version != null) {
                    selected.add(version);
                }
                return selected;
            }
            return super.getSelectionFromWidget();
        }

        @Override
        protected void setSelectionToWidget(ISelection selection, boolean reveal) {
            if (!selection.isEmpty() && _availableVersions != null) {
                Object obj = ((IStructuredSelection) selection).getFirstElement();
                if (!_availableVersions.contains(obj)) {
                    getCombo().setText(obj.toString());
                    return;
                }
            }
            super.setSelectionToWidget(selection, reveal);
        }
    }

    private final static class ComponentsLabelProvider extends LabelProvider {
        public String getText(Object element) {
            if (element instanceof Category) {
                switch ((Category) element) {
                case UNKNOWN:
                    return "Unknown";
                case IMPLEMENTATION:
                    return "Implementation Support";
                case GATEWAY:
                    return "Gateway Bindings";
                case TEST:
                    return "Test Mixins";
                }
            } else if (element instanceof ISwitchYardComponentExtension) {
                return ((ISwitchYardComponentExtension) element).getName();
            }
            return super.getText(element);
        }
    }

    private final static class ComponentsContentProvider implements ITreeContentProvider {
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public boolean hasChildren(Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public Object getParent(Object element) {
            if (element instanceof Category) {
                return SwitchYardComponentExtensionManager.instance();
            } else if (element instanceof ISwitchYardComponentExtension) {
                return ((ISwitchYardComponentExtension) element).getCategory();
            }
            return null;
        }

        @Override
        public Object[] getElements(Object inputElement) {
            return getChildren(inputElement);
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof SwitchYardComponentExtensionManager) {
                return Category.values();
            } else if (parentElement instanceof Category) {
                return SwitchYardComponentExtensionManager.instance().getComponentExtensions((Category) parentElement)
                        .toArray();
            }
            return new Object[0];
        }
    }

    private final static class ComponentsSorter extends ViewerSorter {
        public int category(Object element) {
            final Category category;
            if (element instanceof Category) {
                category = (Category) element;
            } else if (element instanceof ISwitchYardComponentExtension) {
                category = ((ISwitchYardComponentExtension) element).getCategory();
            } else {
                category = Category.UNKNOWN;
            }
            switch (category) {
            case IMPLEMENTATION:
                return 0;
            case GATEWAY:
                return 1;
            case TEST:
                return 2;
            case UNKNOWN:
            default:
                return 3;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private final static class RuntimesListSorter extends ViewerSorter {
        private Comparator _comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                return -((String) o1).compareTo((String) o2);
            }
        };

        @Override
        public int category(Object element) {
            if (element == NULL_RUNTIME) {
                return 0;
            } else if (FSW_RUNTIME_ID.equals(((IRuntimeComponent) element).getRuntimeComponentType().getId())) {
                return 1;
            }
            return 2;
        }

        @Override
        protected Comparator getComparator() {
            return _comparator;
        }
    }
}
