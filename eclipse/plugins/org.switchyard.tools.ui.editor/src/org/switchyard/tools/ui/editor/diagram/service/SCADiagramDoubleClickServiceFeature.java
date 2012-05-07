package org.switchyard.tools.ui.editor.diagram.service;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * @author bfitzpat
 *
 */
public class SCADiagramDoubleClickServiceFeature extends AbstractCustomFeature {

    /**
     * @param fp Feature provider
     */
    public SCADiagramDoubleClickServiceFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public void execute(ICustomContext context) {
        MessageDialog.openInformation(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), "Information",
             "Double-click on a Service has not been implemented yet.");


    }

    @Override
    public boolean canExecute(ICustomContext context) {
        return true;
    }

}
