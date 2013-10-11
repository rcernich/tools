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
package org.switchyard.tools.ui.editor.components.rules;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.switchyard.tools.models.switchyard1_1.rules.OperationType;
import org.switchyard.tools.models.switchyard1_1.rules.RulesFactory;
import org.switchyard.tools.models.switchyard1_1.rules.RulesImplementationType;
import org.switchyard.tools.models.switchyard1_1.rules.RulesOperationType;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.shared.TableColumnLayout;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;

/**
 * @author bfitzpat
 * 
 */
public class RulesActionTable extends Composite implements ICellModifier {

    private class PropertyTreeContentProvider implements IStructuredContentProvider {
        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof RulesImplementationType) {
                final RulesImplementationType impl = (RulesImplementationType) inputElement;
                if (impl.getOperations() != null) {
                    return impl.getOperations().getOperation().toArray();
                }
            }
            return new Object[0];
        }

    }

    private class PropertyTreeLabelProvider implements ITableLabelProvider {
        @Override
        public void addListener(ILabelProviderListener listener) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public boolean isLabelProperty(Object element, String property) {
            if (element instanceof RulesOperationType && property.equalsIgnoreCase(TYPE_COLUMN)) {
                return true;
            } else if (element instanceof RulesOperationType && property.equalsIgnoreCase(OPERATION_COLUMN)) {
                return true;
            } else if (element instanceof RulesOperationType && property.equalsIgnoreCase(ENTRY_POINT_COLUMN)) {
                return true;
            }
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
            if (element instanceof RulesOperationType && columnIndex == 1) {
                RulesOperationType tp = (RulesOperationType) element;
                return (String) tp.getType().getLiteral();
            } else if (element instanceof RulesOperationType && columnIndex == 0) {
                RulesOperationType tp = (RulesOperationType) element;
                return tp.getName();
            } else if (element instanceof RulesOperationType && columnIndex == 2) {
                RulesOperationType tp = (RulesOperationType) element;
                return tp.getEventId();
            }
            return null;
        }
    }

    private TableViewer _propertyTreeTable;

    /**
     * Type column.
     */
    public static final String TYPE_COLUMN = "type"; //$NON-NLS-1$

    /**
     * Operation column.
     */
    public static final String OPERATION_COLUMN = "operation"; //$NON-NLS-1$

    /**
     * Entry Point ID column.
     */
    public static final String ENTRY_POINT_COLUMN = "entrypoint"; //$NON-NLS-1$

    private static final String[] TREE_COLUMNS = new String[] {
        OPERATION_COLUMN, TYPE_COLUMN, ENTRY_POINT_COLUMN };

    private Button _mAddButton;
    private Button _mRemoveButton;
    private boolean _isReadOnly = false;
    private EObject _targetObj = null;
    private String _mWarning = null;
    private ListenerList _changeListeners;

    /**
     * Constructor.
     * 
     * @param parent Composite parent
     * @param style any SWT style bits to pass along
     */
    public RulesActionTable(Composite parent, int style) {
        this(parent, style, false);
    }

    /**
     * Constructor.
     * 
     * @param parent composite parent
     * @param style any SWT style bits
     * @param isReadOnly boolean flag
     */
    public RulesActionTable(Composite parent, int style, boolean isReadOnly) {
        super(parent, style);
        this._isReadOnly = isReadOnly;
        this._changeListeners = new ListenerList();

        int additionalStyles = SWT.NONE;
        if (isReadOnly) {
            additionalStyles = SWT.READ_ONLY;
        }

        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        Composite tableComposite = new Composite(this, additionalStyles);
        GridData gd11 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
        gd11.heightHint = 100;
        tableComposite.setLayoutData(gd11);

        _propertyTreeTable = new TableViewer(tableComposite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.FULL_SELECTION
                | additionalStyles);
        _propertyTreeTable.getTable().setHeaderVisible(true);

        TableColumnLayout tableLayout = new TableColumnLayout();
        tableComposite.setLayout(tableLayout);

        TableColumn operationColumn = new TableColumn(_propertyTreeTable.getTable(), SWT.LEFT);
        operationColumn.setText(Messages.label_operation);
        tableLayout.setColumnData(operationColumn, new ColumnWeightData(100, 150, true));

        TableColumn typeColumn = new TableColumn(_propertyTreeTable.getTable(), SWT.LEFT);
        typeColumn.setText(Messages.label_type);
        tableLayout.setColumnData(typeColumn, new ColumnWeightData(100, 150, true));

        TableColumn entryPointIdColumn = new TableColumn(_propertyTreeTable.getTable(), SWT.LEFT);
        entryPointIdColumn.setText(Messages.label_eventId);
        tableLayout.setColumnData(entryPointIdColumn, new ColumnWeightData(100, 150, true));

        _propertyTreeTable.setColumnProperties(TREE_COLUMNS);

        _propertyTreeTable.setLabelProvider(new PropertyTreeLabelProvider());

        _propertyTreeTable.setContentProvider(new PropertyTreeContentProvider());

        _propertyTreeTable.setCellModifier(this);
        _propertyTreeTable.setCellEditors(new CellEditor[] {
                new TextCellEditor(_propertyTreeTable.getTable()), 
                new ComboBoxCellEditor(_propertyTreeTable.getTable(), new String[] {OperationType.EXECUTE.getLiteral(),
                        OperationType.INSERT.getLiteral(), OperationType.FIREALLRULES.getLiteral(),
                        OperationType.FIREUNTILHALT.getLiteral() }),
                new TextCellEditor(_propertyTreeTable.getTable()) });

        _mAddButton = new Button(this, SWT.NONE);
        _mAddButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
        _mAddButton.setText(Messages.button_add);
        _mAddButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                addPropertyToList();
                if (_propertyTreeTable.getInput() == null) {
                    RulesImplementationType impl = (RulesImplementationType) _targetObj;
                    _propertyTreeTable.setInput(impl);
                }
                _propertyTreeTable.refresh();
                fireChangedEvent(e.getSource());
            }
        });

        _mAddButton.setEnabled(false);

        _propertyTreeTable.getTable().addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                updatePropertyButtons();
            }
        });

        _mRemoveButton = new Button(this, SWT.NONE);
        _mRemoveButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
        _mRemoveButton.setText(Messages.button_remove);
        _mRemoveButton.setEnabled(false);
        _mRemoveButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                removeFromList();
                _propertyTreeTable.refresh();
                fireChangedEvent(e.getSource());
            }
        });

        updatePropertyButtons();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void checkSubclass() {
        // empty
    }

    /**
     * Add a new property to the list
     */
    protected void addPropertyToList() {
        if (getTargetObject() instanceof RulesImplementationType) {
            final RulesImplementationType impl = (RulesImplementationType) getTargetObject();
            if (impl.eContainer() != null) {
                TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                domain.getCommandStack().execute(new RecordingCommand(domain) {
                    @Override
                    protected void doExecute() {
                        RulesOperationType newAction = RulesFactory.eINSTANCE.createRulesOperationType();
                        newAction.setType(OperationType.EXECUTE);
                        newAction.setName("NewOperation"); //$NON-NLS-1$
                        if (impl.getOperations() == null) {
                            impl.setOperations(RulesFactory.eINSTANCE.createOperationsType());
                        }
                        impl.getOperations().getOperation().add(newAction);
                        getTableViewer().refresh(true);
                    }
                });
            } else {
                RulesOperationType newAction = RulesFactory.eINSTANCE.createRulesOperationType();
                newAction.setType(OperationType.EXECUTE);
                newAction.setName("NewOperation"); //$NON-NLS-1$
                if (impl.getOperations() == null) {
                    impl.setOperations(RulesFactory.eINSTANCE.createOperationsType());
                }
                impl.getOperations().getOperation().add(newAction);
                getTableViewer().refresh(true);
            }
            fireChangedEvent(this);
        }
    }

    /**
     * Remove a property from the list
     */
    protected void removeFromList() {
        if (getTargetObject() instanceof RulesImplementationType) {
            final RulesImplementationType impl = (RulesImplementationType) getTargetObject();
            final RulesOperationType actionToRemove = getTableSelection();
            if (impl.eContainer() != null) {
                TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                domain.getCommandStack().execute(new RecordingCommand(domain) {
                    @Override
                    protected void doExecute() {
                        impl.getOperations().getOperation().remove(actionToRemove);
                        if (impl.getOperations().getOperation().isEmpty()) {
                            impl.setOperations(null);
                        }
                        getTableViewer().refresh(true);
                    }
                });
            } else {
                impl.getOperations().getOperation().remove(actionToRemove);
                if (impl.getOperations().getOperation().isEmpty()) {
                    impl.setOperations(null);
                }
                getTableViewer().refresh(true);
            }
            fireChangedEvent(this);
        }
    }

    protected RulesOperationType getTableSelection() {
        if (_propertyTreeTable != null && !_propertyTreeTable.getSelection().isEmpty()) {
            IStructuredSelection ssel = (IStructuredSelection) _propertyTreeTable.getSelection();
            if (ssel.getFirstElement() instanceof RulesOperationType) {
                return (RulesOperationType) ssel.getFirstElement();
            }
        }
        return null;
    }

    /**
     * Update button state based on what's selected.
     */
    public void updatePropertyButtons() {
        if (_isReadOnly) {
            this._mAddButton.setEnabled(false);
            this._mRemoveButton.setEnabled(false);

        } else {
            this._mAddButton.setEnabled(true);
            if (getTableSelection() != null) {
                _mRemoveButton.setEnabled(true);
            }
        }
    }

    /**
     * @return warning string
     */
    public String getWarning() {
        return this._mWarning;
    }

    /**
     * If we changed, fire a changed event.
     * 
     * @param source
     */
    protected void fireChangedEvent(Object source) {
        ChangeEvent e = new ChangeEvent(source);
        // inform any listeners of the resize event
        Object[] listeners = this._changeListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            ((ChangeListener) listeners[i]).stateChanged(e);
        }
    }

    /**
     * Add a change listener.
     * 
     * @param listener new listener
     */
    public void addChangeListener(ChangeListener listener) {
        this._changeListeners.add(listener);
    }

    /**
     * Remove a change listener.
     * 
     * @param listener old listener
     */
    public void removeChangeListener(ChangeListener listener) {
        this._changeListeners.remove(listener);
    }

    /**
     * @param target Passed in what we're dropping on
     */
    public void setTargetObject(EObject target) {
        _targetObj = target;
        _propertyTreeTable.setInput(target);
        updatePropertyButtons();
    }

    protected EObject getTargetObject() {
        return this._targetObj;
    }

    /**
     * @param element Object being modified
     * @param property Property being modified
     * @return boolean flag
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return true;
    }

    /**
     * @param element Object being modified
     * @param property Property being modified
     * @return value of element property
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object ,
     *      java.lang.String)
     */
    public Object getValue(Object element, String property) {
        if (element instanceof RulesOperationType && property.equalsIgnoreCase(TYPE_COLUMN)) {
            return new Integer(((RulesOperationType) element).getType().getValue());
        } else if (element instanceof RulesOperationType && property.equalsIgnoreCase(OPERATION_COLUMN)) {
            if (((RulesOperationType) element).getName() != null) {
                return ((RulesOperationType) element).getName();
            } else {
                return ""; //$NON-NLS-1$
            }
        } else if (element instanceof RulesOperationType && property.equalsIgnoreCase(ENTRY_POINT_COLUMN)) {
            if (((RulesOperationType) element).getEventId() != null) {
                return ((RulesOperationType) element).getEventId();
            } else {
                return ""; //$NON-NLS-1$
            }
        }
        return null;
    }

    /**
     * @param element Object being modified
     * @param property Property being modified
     * @param value New property value
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, final Object value) {
        if (element instanceof TableItem && property.equalsIgnoreCase(TYPE_COLUMN)) {
            final TableItem ti = (TableItem) element;
            if (getTargetObject() instanceof RulesImplementationType) {
                final RulesImplementationType impl = (RulesImplementationType) getTargetObject();
                if (impl.eContainer() != null) {
                    TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            RulesOperationType parm = (RulesOperationType) ti.getData();
                            OperationType atype = OperationType.get(((Integer) value).intValue());
                            parm.setType(atype);
                            getTableViewer().refresh(true);
                        }
                    });
                } else {
                    RulesOperationType parm = (RulesOperationType) ti.getData();
                    OperationType atype = OperationType.get(((Integer) value).intValue());
                    parm.setType(atype);
                    getTableViewer().refresh(true);
                }
            }
            fireChangedEvent(this);
            // validate();
        } else if (element instanceof TableItem && property.equalsIgnoreCase(OPERATION_COLUMN)) {
            final TableItem ti = (TableItem) element;
            if (getTargetObject() instanceof RulesImplementationType) {
                final RulesImplementationType impl = (RulesImplementationType) getTargetObject();
                final String newValue = value == null || ((String) value).length() == 0 ? null : (String) value;
                if (impl.eContainer() != null) {
                    TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            RulesOperationType parm = (RulesOperationType) ti.getData();
                            parm.setName(newValue);
                            getTableViewer().refresh(true);
                        }
                    });
                } else {
                    RulesOperationType parm = (RulesOperationType) ti.getData();
                    parm.setName(newValue);
                    getTableViewer().refresh(true);
                }
            }
            fireChangedEvent(this);
            // validate();
        } else if (element instanceof TableItem && property.equalsIgnoreCase(ENTRY_POINT_COLUMN)) {
            final TableItem ti = (TableItem) element;
            if (getTargetObject() instanceof RulesImplementationType) {
                final RulesImplementationType impl = (RulesImplementationType) getTargetObject();
                if (impl.eContainer() != null) {
                    TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            RulesOperationType parm = (RulesOperationType) ti.getData();
                            parm.setEventId((String) value);
                            getTableViewer().refresh(true);
                        }
                    });
                } else {
                    RulesOperationType parm = (RulesOperationType) ti.getData();
                    parm.setEventId((String) value);
                    getTableViewer().refresh(true);
                }
            }
            fireChangedEvent(this);
        }
    }

    protected TableViewer getTableViewer() {
        return this._propertyTreeTable;
    }
}
