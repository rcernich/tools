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
package org.switchyard.tools.servers.ui;

import java.text.Collator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * SwitchYardViewerSorter
 * 
 * <p/>
 * Used to sort the SwitchYard nodes appropriately.
 * 
 * @author Rob Cernich
 */
public class SwitchYardViewerSorter extends ViewerSorter {

    private static final int RUNTIME_CATEGORY = 1;
    private static final int APPLICATIONS_CATEGORY = 2;
    private static final int SERVICES_CATEGORY = 3;

    /**
     * Create a new SwitchYardViewerSorter.
     */
    public SwitchYardViewerSorter() {
    }

    /**
     * Create a new SwitchYardViewerSorter.
     * 
     * @param collator the collator
     */
    public SwitchYardViewerSorter(Collator collator) {
        super(collator);
    }

    @Override
    public int category(Object element) {
        if (element instanceof ApplicationsNode) {
            return APPLICATIONS_CATEGORY;
        } else if (element instanceof ServicesNode) {
            return SERVICES_CATEGORY;
        } else if (element instanceof RuntimeNode) {
            return RUNTIME_CATEGORY;
        }
        return super.category(element);
    }

}
