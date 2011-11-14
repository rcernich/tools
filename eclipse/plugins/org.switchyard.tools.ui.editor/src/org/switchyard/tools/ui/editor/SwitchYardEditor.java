package org.switchyard.tools.ui.editor;

import org.eclipse.sapphire.ui.SapphireEditorFormPage;
import org.eclipse.sapphire.ui.StandardFormEditorPage;
import org.eclipse.sapphire.ui.def.ISapphireFormEditorPageDef;
import org.eclipse.sapphire.ui.def.ISapphireUiDef;
import org.eclipse.sapphire.ui.def.SapphireUiDefFactory;
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

    private SapphireEditorFormPage _overviewPage;
    private SapphireEditorFormPage _servicesPage;

    /**
     * Create a new SwitchYardEditor.
     */
    public SwitchYardEditor() {
        super(Activator.PLUGIN_ID);

        setRootModelElementType(ISwitchYard.TYPE);
    }

    @Override
    protected void createFormPages() throws PartInitException {
        ISapphireUiDef uiDef = SapphireUiDefFactory.load(Activator.PLUGIN_ID, EDITOR_SDEF);
        _overviewPage = StandardFormEditorPage
                .createFormEditorPage(this, getModelElement(), (ISapphireFormEditorPageDef) uiDef.getPartDef(
                        OVERVIEW_PAGE_ID, true, ISapphireFormEditorPageDef.class));
        addPage(0, _overviewPage);
        setPageId(_overviewPage, OVERVIEW_PAGE_ID, _overviewPage.getPart());

        _servicesPage = StandardFormEditorPage
                .createFormEditorPage(this, getModelElement(), (ISapphireFormEditorPageDef) uiDef.getPartDef(
                        SERVICES_PAGE_ID, true, ISapphireFormEditorPageDef.class));
        addPage(1, _servicesPage);
        setPageId(_servicesPage, SERVICES_PAGE_ID, _servicesPage.getPart());
    }

    @Override
    protected void pageChange(int pageIndex) {
        // FIXME pageChange
        super.pageChange(pageIndex);
    }

}
