package org.switchyard.tools.ui.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.sapphire.ui.FormEditorPage;
import org.eclipse.sapphire.ui.swt.xml.editor.SapphireEditorForXml;
import org.eclipse.ui.PartInitException;
import org.switchyard.tools.ui.editor.model.ISwitchYard;

/**
 * SwitchYardEditor
 * 
 * Editor implementation for switchyard.xml files.
 * 
 * @author Rob Cernich
 */
public final class SwitchYardEditor extends SapphireEditorForXml {

    private static final String EDITOR_SDEF = SwitchYardEditor.class.getResource("switchyard.sdef").getPath();
    private static final String OVERVIEW_PAGE_ID = "switchyard.overview.page";
    private static final String SERVICES_PAGE_ID = "switchyard.services.page";

    private FormEditorPage _overviewPage;
    private FormEditorPage _servicesPage;

    /**
     * Create a new SwitchYardEditor.
     */
    public SwitchYardEditor() {
        super(Activator.PLUGIN_ID);

        setRootModelElementType(ISwitchYard.TYPE);
    }

    @Override
    protected void createFormPages() throws PartInitException {
        _overviewPage = new FormEditorPage(this, getModelElement(),
                createFormEditorPageDefinitionPath(OVERVIEW_PAGE_ID));
        addPage(0, _overviewPage);
        setPageId(_overviewPage, OVERVIEW_PAGE_ID, _overviewPage.getPart());

        _servicesPage = new FormEditorPage(this, getModelElement(),
                createFormEditorPageDefinitionPath(SERVICES_PAGE_ID));
        addPage(1, _servicesPage);
        setPageId(_servicesPage, SERVICES_PAGE_ID, _servicesPage.getPart());
    }

    @Override
    protected void pageChange(int pageIndex) {
        // FIXME pageChange
        super.pageChange(pageIndex);
    }

    private IPath createFormEditorPageDefinitionPath(String pageDefinition) {
        return new Path(Activator.PLUGIN_ID).append(EDITOR_SDEF).append(pageDefinition);
    }

}
