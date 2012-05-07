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
package org.switchyard.tools.ui.editor.diagram.component.wizards;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.soa.sca.sca1_1.model.sca.Implementation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.switchyard.tools.models.switchyard1_0.camel.CamelImplementationType;
import org.switchyard.tools.ui.editor.diagram.internal.wizards.BaseWizardPage;
import org.switchyard.tools.ui.editor.diagram.internal.wizards.IRefreshablePage;
import org.switchyard.tools.ui.editor.diagram.shared.CamelRouteSelectionComposite;

/**
 * @author bfitzpat
 * 
 */
public class SCADiagramAddComponentImplementationCamelPage extends BaseWizardPage implements IRefreshablePage {

    private IWizardPage _startPage = null;
    private CamelRouteSelectionComposite _camelComposite = null;

    /**
     * @param start Start page
     * @param pageName Page name
     */
    public SCADiagramAddComponentImplementationCamelPage(IWizardPage start, String pageName) {
        this(pageName);
        this._startPage = start;

    }

    protected SCADiagramAddComponentImplementationCamelPage(String pageName) {
        super(pageName);
        setTitle("Specify Camel Implementation Details");
        setDescription("Specify the details for the Camel route.");
    }

    @Override
    public void createControl(Composite parent) {
        _camelComposite = new CamelRouteSelectionComposite();
        if (getWizard() instanceof SCADiagramAddComponentWizard) {
            _camelComposite.setDiagram(((SCADiagramAddComponentWizard)getWizard()).getDiagram());
        } else if (getWizard() instanceof SCADiagramAddImplementationWizard) {
            _camelComposite.setDiagram(((SCADiagramAddImplementationWizard)getWizard()).getDiagram());
        }

        Implementation implementation = getImplementationFromStartPage();
        if (implementation instanceof CamelImplementationType) {
            _camelComposite.setImplementation((CamelImplementationType) implementation);
        }
        _camelComposite.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                setErrorMessage(_camelComposite.getErrorMessage());
                setPageComplete(_camelComposite.getErrorMessage() == null);
            }
        });
        _camelComposite.createContents(parent, SWT.NONE);

        setControl(_camelComposite.getcPanel());
    }

    /**
     * @return String for camel route
     */
    public String getCamelRouteXMLFile() {
        return _camelComposite.getCamelRouteString();
    }

    /**
     * @return String for camel route class name
     */
    public String getCamelRouteClassName() {
        return _camelComposite.getCamelRouteClass();
    }

    private Implementation getImplementationFromStartPage() {
        if (_startPage != null) {
            if (_startPage instanceof SCADiagramAddComponentStartPage) {
                SCADiagramAddComponentStartPage componentStart = (SCADiagramAddComponentStartPage) _startPage;
                return componentStart.getImplementation();
            } else if (_startPage instanceof SCADiagramAddImplementationStartPage) {
                SCADiagramAddImplementationStartPage implementationStart = (SCADiagramAddImplementationStartPage) _startPage;
                return implementationStart.getImplementation();
            }
        }
        return null;
    }

    @Override
    public boolean getSkippable() {
        if (_startPage != null) {
            Implementation impl = getImplementationFromStartPage();
            if (impl instanceof CamelImplementationType) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void refresh() {
        if (_startPage != null) {
            Implementation impl = getImplementationFromStartPage();
            if (impl instanceof CamelImplementationType) {
                CamelImplementationType camelImpl = (CamelImplementationType) impl;
                _camelComposite.setImplementation(camelImpl);
            }
        }
    }
}
