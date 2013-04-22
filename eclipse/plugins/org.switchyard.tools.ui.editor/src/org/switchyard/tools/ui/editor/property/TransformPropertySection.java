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
package org.switchyard.tools.ui.editor.property;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardFactory;
import org.switchyard.tools.models.switchyard1_0.switchyard.TransformType;
import org.switchyard.tools.models.switchyard1_0.switchyard.TransformsType;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;
import org.switchyard.tools.ui.editor.property.adapters.LabelAdapter;
import org.switchyard.tools.ui.editor.transform.wizards.AddTransformWizard;
import org.switchyard.tools.ui.editor.util.TransformTypesUtil;
import org.switchyard.tools.ui.model.merge.MergedModelUtil;
import org.switchyard.tools.ui.model.merge.SwitchYardMergedModelAdapter;

/**
 * @author bfitzpat
 * 
 */
public class TransformPropertySection extends GFPropertySection implements ITabbedPropertyConstants,
        ResourceSetListener {

    private TransformType _transformer = null;
    private TableViewer _tableViewer;
    private FormToolkit _toolkit = null;
    private Section _tableSection;
    private Composite _tableComposite;
    private Button _addButton;
    private Button _removeButton;
    private TransactionalEditingDomain _domain = null;

    /**
     * Constructor.
     */
    public TransformPropertySection() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls
     * (org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        parent.setLayout(new GridLayout(2, false));
        _toolkit = this.getWidgetFactory();

        _tableSection = _toolkit.createSection(parent, ExpandableComposite.TITLE_BAR);
//        _tableSection.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
        _tableSection.setLayout(new FillLayout());
        _tableSection.setText("Transforms List");
        _tableSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));

        _tableComposite = _toolkit.createComposite(_tableSection, SWT.NONE);
//        _tableComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
        _tableSection.setClient(_tableComposite);
        _tableComposite.setLayout(new GridLayout(3, false));
        createTableAndButtons(_tableComposite, SWT.NONE);

        addDomainListener();
    }

    private void handleSelectListItem() {
        if (_removeButton != null && !_removeButton.isDisposed()) {
            URI _modelUri = URI.createPlatformResourceURI(SwitchyardSCAEditor.getActiveEditor().getModelFile().getFullPath().toString(), true);
            if (_transformer.eResource().getURI().equals(_modelUri)) {
                _removeButton.setEnabled(_transformer != null);
            } else {
                _removeButton.setEnabled(false);
            }
        }
    }

    private SwitchYardType getSwitchYardRoot() {
        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
            return MergedModelUtil.getSwitchYard(Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(
                    pe));
        }
        return null;
    }

    @Override
    public void refresh() {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                final SwitchYardType switchYardRoot = getSwitchYardRoot();
                if (_tableViewer.getTable().isDisposed()) {
                    return;
                }
                if (switchYardRoot == null) {
                    _tableViewer.setInput(null);
                } else {
                    _tableViewer.setInput(MergedModelUtil
                            .getAdapter(switchYardRoot, SwitchYardMergedModelAdapter.class).getTransforms());
                }
            }
        });
    }
    
    private void createTableAndButtons(Composite parent, int style) {

        GridData gridData;

        Composite tableAndButtonsComposite = _toolkit.createComposite(parent, SWT.NONE);
//        tableAndButtonsComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
        gridData.verticalIndent = -5;
        tableAndButtonsComposite.setLayoutData(gridData);
        tableAndButtonsComposite.setLayout(new GridLayout(3, false));

        Composite buttonsComposite = _toolkit.createComposite(tableAndButtonsComposite);
//        buttonsComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA));
        buttonsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
        
        Composite tableComposite = _toolkit.createComposite(tableAndButtonsComposite);
//        tableComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        gridData.widthHint = 100;
        gridData.heightHint = 100;
        tableComposite.setLayoutData(gridData);
        TableColumnLayout tableLayout = new TableColumnLayout();
        tableComposite.setLayout(tableLayout);

        _tableViewer = new TableViewer(tableComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER
                | SWT.FULL_SELECTION);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        gridData.widthHint = 100;
        gridData.heightHint = 100;
        _tableViewer.getTable().setLayoutData(gridData);

        Table table = _tableViewer.getTable();

        // Add the from column
        TableColumn tc2 = new TableColumn(table, SWT.LEFT);
        tc2.setText("From");
        tableLayout.setColumnData(tc2,  new ColumnWeightData(45));
        tc2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                ((ColumnViewerSorter) _tableViewer.getSorter()).doSort(ColumnConst.COLUMN_FROM);
                _tableViewer.refresh();
            }
        });

        // Add the to column
        TableColumn tc1 = new TableColumn(table, SWT.LEFT);
        tc1.setText("To");
        tableLayout.setColumnData(tc1,  new ColumnWeightData(45));
        tc1.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                ((ColumnViewerSorter) _tableViewer.getSorter()).doSort(ColumnConst.COLUMN_TO);
                _tableViewer.refresh();
            }
        });

        // Add the type column
        TableColumn tc3 = new TableColumn(table, SWT.LEFT);
        tc3.setText("Type");
        tableLayout.setColumnData(tc3,  new ColumnWeightData(10));
        tc3.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                ((ColumnViewerSorter) _tableViewer.getSorter()).doSort(ColumnConst.COLUMN_TYPE);
                _tableViewer.refresh();
            }
        });

        _tableViewer.setSorter(new ColumnViewerSorter());

        // Turn on the header and the lines
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        _tableViewer.setLabelProvider(new TransformTableLabelProvider());
        _tableViewer.setContentProvider(new TransformTableContentProvider());

        _tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection ssel = (IStructuredSelection) event.getSelection();
                if (!ssel.isEmpty() && ssel.getFirstElement() instanceof TransformType) {
                    _transformer = (TransformType) ssel.getFirstElement();
                    handleSelectListItem();
                }
            }
        });

        Label legend = new Label(tableAndButtonsComposite, SWT.NONE);
        legend.setText("* = Generated Transform");
        gridData = new GridData(SWT.RIGHT, SWT.TOP, true, false, 2, 1);
        legend.setLayoutData(gridData);

        _addButton = _toolkit.createButton(buttonsComposite, "Add", SWT.PUSH);
        _addButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addTransform();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });
        _removeButton = _toolkit.createButton(buttonsComposite, "Remove", SWT.PUSH);
        _removeButton.setEnabled(false);
        _removeButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection ssel = (IStructuredSelection) _tableViewer.getSelection();
                removeTransform((TransformType) ssel.getFirstElement());
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });
    }

    private Object removeListItem(EObject object, EStructuralFeature feature, int index) {
        @SuppressWarnings("unchecked")
        EList<EObject> list = (EList<EObject>) object.eGet(feature);
        return list.remove(index);
    }

    private TransformType addTransform() {
        TransformType newTransform = null;
        AddTransformWizard wizard = new AddTransformWizard(getSwitchYardRoot());
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        WizardDialog wizDialog = new WizardDialog(shell, wizard);
        int rtn_code = wizDialog.open();
        if (rtn_code == Window.OK) {
            newTransform = wizard.getTransform();
            if (_domain != null) {
                final TransformType transform = newTransform;
                _domain.getCommandStack().execute(new RecordingCommand(_domain) {
                    @Override
                    protected void doExecute() {
                        SwitchYardType switchYardRoot = getSwitchYardRoot();
                        TransformsType transforms = switchYardRoot.getTransforms();
                        if (transforms == null) {
                            switchYardRoot.setTransforms(SwitchyardFactory.eINSTANCE.createTransformsType());
                            transforms = switchYardRoot.getTransforms();
                        }
                        transforms.getTransform().add(transform);
                    }
                });
                refresh();
            }
        }
        return newTransform;
    }

    private void removeTransform(final TransformType selected) {
        if (selected != null && _domain != null) {
            _domain.getCommandStack().execute(new RecordingCommand(_domain) {
                @Override
                protected void doExecute() {
                    SwitchYardType switchYardRoot = getSwitchYardRoot();
                    TransformsType transforms = switchYardRoot.getTransforms();
                    int index = transforms.getTransform().indexOf(selected);
                    EStructuralFeature feature = transforms.eClass().getEStructuralFeature("transform");
                    removeListItem(transforms, feature, index);
                }
            });
            refresh();
        }
    }

    private class TransformTableContentProvider implements IStructuredContentProvider {
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof EList<?>) {
                EList<?> v = (EList<?>) inputElement;
                return v.toArray();
            } else if (inputElement instanceof List<?>) {
                List<?> v = (List<?>) inputElement;
                return v.toArray();
            }

            return new Object[0];
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

    private class TransformTableLabelProvider implements ITableLabelProvider {
        @Override
        public void addListener(ILabelProviderListener listener) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        @Override
        public void removeListener(ILabelProviderListener listener) {
        }

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        @Override
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof TransformType) {
                TransformType transform = (TransformType) element;
                if (columnIndex == ColumnConst.COLUMN_TO) {
                    return TransformTypesUtil.getLabelForType(transform.getTo());
                } else if (columnIndex == ColumnConst.COLUMN_FROM) {
                    return TransformTypesUtil.getLabelForType(transform.getFrom());
                } else if (columnIndex == ColumnConst.COLUMN_TYPE) {
                    return LabelAdapter.getLabel(transform);
                }
            }
            return null;
        }
    }

    /**
     * This class implements the sorting for the Player Table
     */

    class ColumnViewerSorter extends ViewerSorter {
        private static final int ASCENDING = 0;

        private static final int DESCENDING = 1;

        private int _column;

        private int _direction;

        /**
         * Does the sort. If it's a different column from the previous sort, do
         * an ascending sort. If it's the same column as the last sort, toggle
         * the sort direction.
         * 
         * @param column
         */
        public void doSort(int column) {
            if (column == this._column) {
                // Same column as last sort; toggle the direction
                _direction = 1 - _direction;
            } else {
                // New column; do an ascending sort
                this._column = column;
                _direction = ASCENDING;
            }
        }

        /**
         * Compares the object for sorting
         */
        @SuppressWarnings("unchecked")
        public int compare(Viewer viewer, Object e1, Object e2) {
            int rc = 0;
            TransformType p1 = (TransformType) e1;
            TransformType p2 = (TransformType) e2;

            // Determine which column and do the appropriate sort
            switch (_column) {
            case ColumnConst.COLUMN_TO:
                rc = getComparator().compare(p1.getTo(), p2.getTo());
                break;
            case ColumnConst.COLUMN_FROM:
                rc = getComparator().compare(p1.getFrom(), p2.getFrom());
                break;
            case ColumnConst.COLUMN_TYPE:
                String type1 = LabelAdapter.getLabel(p1);
                String type2 = LabelAdapter.getLabel(p2);
                rc = getComparator().compare(type1, type2);
                break;
            }

            // If descending order, flip the direction
            if (_direction == DESCENDING) {
                rc = -rc;
            }

            return rc;
        }
    }

    /**
     * This class contains constants for the table
     */

    final class ColumnConst {
        // Column constants
        public static final int COLUMN_TO = 1;

        public static final int COLUMN_FROM = 0;
        
        public static final int COLUMN_TYPE = 2;

        private ColumnConst() {
            // hidden constructor
        }
    }

    @Override
    public NotificationFilter getFilter() {
        return null;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return false;
    }

    @Override
    public boolean isPostcommitOnly() {
        return false;
    }

    @Override
    public boolean isPrecommitOnly() {
        return false;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent arg0) {
        refresh();
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent arg0) throws RollbackException {
        return null;
    }

    private void addDomainListener() {
        if (_domain == null) {
            _domain = (TransactionalEditingDomainImpl) SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
            _domain.addResourceSetListener(this);
        }
    }

    private void removeDomainListener() {
        if (_domain != null) {
            _domain.removeResourceSetListener(this);
        }
    }

    @Override
    public void dispose() {
        removeDomainListener();
        super.dispose();
    }
}
