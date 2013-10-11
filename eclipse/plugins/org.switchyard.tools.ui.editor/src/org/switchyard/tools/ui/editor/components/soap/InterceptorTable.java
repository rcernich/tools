/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
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
package org.switchyard.tools.ui.editor.components.soap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.switchyard.tools.models.switchyard1_1.soap.InterceptorType;
import org.switchyard.tools.ui.editor.Messages;

/**
 * @author bfitzpat
 * 
 */
public abstract class InterceptorTable extends Composite implements ICellModifier {

    private class InterceptorTypeTreeContentProvider implements ITreeContentProvider {
        private EList<InterceptorType> _properties;

        @Override
        public void dispose() {
        }

        @SuppressWarnings("unchecked")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            if (newInput instanceof EList<?>) {
                _properties = (EList<InterceptorType>) newInput;
            }
        }

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof EList<?>) {
                return _properties.toArray();
            }
            return null;
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof InterceptorType[]) {
                return new Object[] {_properties.toArray() };
            }
            return null;
        }

        @Override
        public Object getParent(Object element) {
            if (element instanceof InterceptorType) {
                return ((InterceptorType) element).eContainer();
            }
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            if (element instanceof EList<?>) {
                return ((EList<?>) element).size() > 0;
            }
            return false;
        }
    }

    private class InterceptorTypeTreeLabelProvider implements ITableLabelProvider {
        @Override
        public void addListener(ILabelProviderListener listener) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public boolean isLabelProperty(Object element, String property) {
            if (element instanceof InterceptorType && property.equalsIgnoreCase(NAME_COLUMN)) {
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
            if (element instanceof InterceptorType && columnIndex == 0) {
                return ((InterceptorType) element).getClass_();
            }
            return null;
        }
    }

    private TreeViewer _interceptorTreeTable;
    
    /**
     *  Name column.
     */
    public static final String NAME_COLUMN = "name"; //$NON-NLS-1$
    
    private static final String[] TREE_COLUMNS = new String[] {NAME_COLUMN };

    private Button _mAddButton;
    private Button _mRemoveButton;
    private Button _mEditButton;
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
    public InterceptorTable(Composite parent, int style) {
        this(parent, style, false);
    }

    /**
     * Constructor.
     * 
     * @param parent composite parent
     * @param style any SWT style bits
     * @param isReadOnly boolean flag
     */
    public InterceptorTable(Composite parent, int style, boolean isReadOnly) {
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

        _interceptorTreeTable = new TreeViewer(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.FULL_SELECTION
                | additionalStyles);
        this._interceptorTreeTable.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
        GridData gd11 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5);
        gd11.heightHint = 100;
        _interceptorTreeTable.getTree().setLayoutData(gd11);
        _interceptorTreeTable.getTree().setHeaderVisible(true);
        TreeColumn nameColumn = new TreeColumn(_interceptorTreeTable.getTree(), SWT.LEFT);
        nameColumn.setText(Messages.label_class);
        nameColumn.setWidth(400);

        _interceptorTreeTable.setColumnProperties(TREE_COLUMNS);

        _interceptorTreeTable.setLabelProvider(new InterceptorTypeTreeLabelProvider());

        _interceptorTreeTable.setContentProvider(new InterceptorTypeTreeContentProvider());

        _interceptorTreeTable.setCellModifier(this);
        _interceptorTreeTable.setCellEditors(new CellEditor[] {null, new TextCellEditor(_interceptorTreeTable.getTree()),
                null });

        this._mAddButton = new Button(this, SWT.NONE);
        this._mAddButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        this._mAddButton.setText(Messages.button_add);
        this._mAddButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                addInterceptorTypeToList();
                _interceptorTreeTable.refresh();
                fireChangedEvent(e.getSource());
            }
        });

        this._mAddButton.setEnabled(false);

        _interceptorTreeTable.getTree().addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                updateInterceptorTypeButtons();
            }
        });

        this._mEditButton = new Button(this, SWT.NONE);
        this._mEditButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        this._mEditButton.setText(Messages.button_edit);
        this._mEditButton.setEnabled(false);
        this._mEditButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                editInterceptorType();
                _interceptorTreeTable.refresh();
                fireChangedEvent(e.getSource());
            }
        });

        this._mRemoveButton = new Button(this, SWT.NONE);
        this._mRemoveButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        this._mRemoveButton.setText(Messages.button_remove);
        this._mRemoveButton.setEnabled(false);
        this._mRemoveButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                removeFromList();
                _interceptorTreeTable.refresh();
                fireChangedEvent(e.getSource());
            }
        });

        updateInterceptorTypeButtons();
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
    protected abstract void addInterceptorTypeToList();

    /**
     * Remove a property from the list
     */
    protected abstract void removeFromList();
    
    /**
     * Edit the selected interceptor type.
     */
    protected abstract void editInterceptorType();

    /**
     * Return the current selection.
     * 
     * @return String list
     */
    @SuppressWarnings("unchecked")
    public EList<InterceptorType> getSelection() {
        if (_interceptorTreeTable != null && _interceptorTreeTable.getInput() != null) {
            return (EList<InterceptorType>) _interceptorTreeTable.getInput();
        }
        return null;
    }
    
    /**
     * @return the currently selected PropertyType
     */
    public InterceptorType getTableSelection() {
        if (_interceptorTreeTable != null && !_interceptorTreeTable.getSelection().isEmpty()) {
            IStructuredSelection ssel = (IStructuredSelection) _interceptorTreeTable.getSelection();
            if (ssel.getFirstElement() instanceof InterceptorType) {
                return (InterceptorType) ssel.getFirstElement();
            }
        }
        return null;
    }

    /**
     * Update button state based on what's selected.
     */
    public void updateInterceptorTypeButtons() {
        if (_isReadOnly) {
            this._mAddButton.setEnabled(false);
            this._mRemoveButton.setEnabled(false);
            this._mEditButton.setEnabled(false);
        } else {
            this._mAddButton.setEnabled(true);
            if (getSelection() != null) {
                _mEditButton.setEnabled(true);
                _mRemoveButton.setEnabled(true);
            }
        }
    }

    /**
     * @param properties incoming property list
     */
    public void setSelection(EList<InterceptorType> properties) {
        _interceptorTreeTable.setInput(properties);
        updateInterceptorTypeButtons();
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
        this._targetObj = target;
    }

    protected EObject getTargetObject() {
        return this._targetObj;
    }
    
    protected void setFeatureValue(EObject eObject, String featureId, Object value) {
        EClass eClass = eObject.eClass();
        for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i) {
            EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
            if (eStructuralFeature.isChangeable()) {
                if (eStructuralFeature.getName().equalsIgnoreCase(featureId)) {
                    eObject.eSet(eStructuralFeature, value);
                    break;
                }
            }
        }
    }

    /**
     * @param element Object being modified
     * @param property PropertyType being modified
     * @return boolean flag
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return false;
    }

    /**
     * @param element Object being modified
     * @param property PropertyType being modified
     * @return value of element property
     * @see
     * org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object ,
     * java.lang.String)
     */
    public Object getValue(Object element, String property) {
        return null;
    }

    /**
     * @param element Object being modified
     * @param property PropertyType being modified
     * @param value New property value
     *
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     * java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, final Object value) {
        return;
    }

    protected TreeViewer getTreeViewer() {
        return this._interceptorTreeTable;
    }
}
