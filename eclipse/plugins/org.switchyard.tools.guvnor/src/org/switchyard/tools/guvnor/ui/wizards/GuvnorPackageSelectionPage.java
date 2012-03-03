/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.tools.guvnor.ui.wizards;

import java.util.EnumSet;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.progress.PendingUpdateAdapter;
import org.guvnor.tools.views.RepositoryContentProvider;
import org.guvnor.tools.views.RepositoryLabelProvider;
import org.guvnor.tools.views.model.TreeObject;
import org.guvnor.tools.views.model.TreeObject.Type;

/**
 * GuvnorPackageSelectionPage
 * 
 * <p/>
 * Presents the user with a list of packages and package snapshots within
 * Guvnor.
 * 
 * @author Rob Cernich
 */
public class GuvnorPackageSelectionPage extends WizardPage {

    // TODO: add support for snapshots once binary downloads are supported
    // through Guvnor REST API
    private static final EnumSet<Type> FILTER = EnumSet.of(Type.NONE, Type.REPOSITORY, Type.PACKAGES, Type.PACKAGE);
    private static final EnumSet<Type> VALID_SELECTION_TYPE = EnumSet.of(Type.PACKAGE);

    private TreeViewer _viewer;

    /**
     * Create a new GuvnorPackageSelectionPage.
     */
    public GuvnorPackageSelectionPage() {
        super(GuvnorPackageSelectionPage.class.getName());
        setTitle("Select Package or Snapshot");
    }

    @Override
    public void createControl(Composite parent) {
        Composite content = new Composite(parent, SWT.NONE);
        content.setLayout(new GridLayout());

        Label label = new Label(content, SWT.NONE);
        label.setText("Select package or snapshot to reference:");

        _viewer = new TreeViewer(content);
        _viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
        _viewer.setContentProvider(new RepositoryContentProvider() {
            @Override
            public boolean hasChildren(Object parent) {
                if (parent instanceof TreeObject && ((TreeObject) parent).getNodeType() == Type.PACKAGE) {
                    // TODO: don't show snapshot children either, once snapshots
                    // are supported
                    // don't show package children
                    return false;
                }
                return super.hasChildren(parent);
            }
        });
        _viewer.setLabelProvider(new RepositoryLabelProvider());
        _viewer.addFilter(new ViewerFilter() {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element instanceof PendingUpdateAdapter || FILTER.contains(((TreeObject) element).getNodeType());
            }
        });
        _viewer.setInput(_viewer);
        _viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                validate();
            }
        });

        validate();
        setErrorMessage(null);

        setControl(content);
    }

    /**
     * @return the currently selected Guvnor object.
     */
    public TreeObject getSelection() {
        IStructuredSelection selection = (IStructuredSelection) _viewer.getSelection();
        if (selection.isEmpty()) {
            return null;
        }
        return (TreeObject) selection.getFirstElement();
    }

    private void validate() {
        TreeObject selection = getSelection();
        if (selection == null || !VALID_SELECTION_TYPE.contains(selection.getNodeType())) {
            setErrorMessage("Please select a package or snapshot.");
        } else {
            setErrorMessage(null);
        }
        setPageComplete(getErrorMessage() == null);
    }
}
