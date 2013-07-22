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
 ******************************************************************************/
package org.switchyard.tools.ui.bot.tests;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withItem;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.EventContextMenuFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Description;
import org.junit.Test;

/**
 * BasicElementsTest
 * <p/>
 * Bot tests for basic SwitchYard elements: components, services and references.
 */
public class BasicElementsTest extends SwitchYardBotTestCase {

    /**
     * Tests the creation of composite services.
     * 
     * <ol>
     * <li>Using service tool, new Java interface.</li>
     * <li>Using composite context pad, specifying Java interface created in
     * step 1.</li>
     * <li>Using context menu, new WSDL interface.</li>
     * </ol>
     * 
     * @throws Exception if test fails.
     */
    @Test
    public void testCompositeService() throws Exception {
        final SWTBotGefEditor editor = new SWTBotGefEditor(bot.activeEditor().getReference(), bot);
        final SWTBotGefEditPart compositeEditPart = editor.getEditPart("switchyard-bot-test");
        final Point compositeCenter = ((GraphicalEditPart) compositeEditPart.part()).getFigure().getBounds()
                .getCenter();

        SWTBotShell firstWizard = null;

        // new Java, tool
        editor.activateTool("Service");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        bot.link().click();
        bot.text(3).setText("TestService");
        finishWizard(bot, 10000);
        firstWizard.activate();
        finishWizard(bot);

        // existing Java, context pad
        editor.activateDefaultTool();
        compositeEditPart.click(compositeCenter);
        bot.sleep(1000); // ensure context pad is activated
        initiateContextPadAction(editor, "Service");
        firstWizard = bot.activeShell();
        bot.button("Browse...").click();
        bot.text().setText("testser");
        // XXX: select type
        bot.waitUntil(Conditions.waitForWidget(withItem(new AbstractMatcher<Item>() {
            @Override
            protected boolean doMatch(Object item) {
                return ((Item) item).getText().startsWith("TestService ");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item text 'TestService'");
            }
        }), bot.table().widget), 30000);
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService2");
        finishWizard(bot);

        // new wsdl, context pad
        editor.activateDefaultTool();
        compositeEditPart.click(compositeCenter);
        initiateContextPadAction(editor, "Service");
        firstWizard = bot.activeShell();
        radioClick(bot.radio("WSDL"));
        bot.sleep(1000);
        bot.link().click();
        bot.text(1).setText("TestService.wsdl");
        bot.button("Next >").click();
        finishWizard(bot);
        firstWizard.activate();
        bot.text(1).setText("TestService3");
        finishWizard(bot);

        // existing wsdl, tool
        editor.activateTool("Service");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        radioClick(bot.radio("WSDL"));
        bot.button("Browse...").click();
        // XXX: select type
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService4");
        finishWizard(bot);

        // esb, tool
        editor.activateTool("Service");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        radioClick(bot.radio("ESB"));
        bot.link().click();
        bot.text().setText("{tns}input");
        bot.text(1).setText("{tns}output");
        bot.text(2).setText("{tns}fault");
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService5");
        finishWizard(bot);

        // save
        bot.menu("File").menu("Save").click();

        assertXMLFilesMatch("switchyard.xml file does not match expected.", getSwitchYardFile(), getClass()
                .getSimpleName() + ".testCompositeService.switchyard.xml");
    }

    /**
     * Tests the creation of composite references.
     * 
     * <ol>
     * <li>Using service tool, new Java interface.</li>
     * <li>Using composite context pad, specifying Java interface created in
     * step 1.</li>
     * <li>Using context menu, new WSDL interface.</li>
     * </ol>
     * 
     * @throws Exception if test fails.
     */
    @Test
    public void testCompositeReference() throws Exception {
        final SWTBotGefEditor editor = new SWTBotGefEditor(bot.activeEditor().getReference(), bot);
        final SWTBotGefEditPart compositeEditPart = editor.getEditPart("switchyard-bot-test");
        final Point compositeCenter = ((GraphicalEditPart) compositeEditPart.part()).getFigure().getBounds()
                .getCenter();

        SWTBotShell firstWizard = null;

        // new Java, tool
        editor.activateTool("Reference");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        bot.link().click();
        bot.text(3).setText("TestService");
        finishWizard(bot, 10000);
        firstWizard.activate();
        finishWizard(bot);

        // existing Java, context pad
        editor.activateDefaultTool();
        compositeEditPart.click(compositeCenter);
        initiateContextPadAction(editor, "Reference");
        firstWizard = bot.activeShell();
        bot.button("Browse...").click();
        bot.text().setText("testser");
        // XXX: select type
        bot.waitUntil(Conditions.waitForWidget(withItem(new AbstractMatcher<Item>() {
            @Override
            protected boolean doMatch(Object item) {
                return ((Item) item).getText().startsWith("TestService ");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item text 'TestService'");
            }
        }), bot.table().widget), 30000);
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService2");
        finishWizard(bot);

        // new wsdl, context pad
        editor.activateDefaultTool();
        compositeEditPart.click(compositeCenter);
        initiateContextPadAction(editor, "Reference");
        firstWizard = bot.activeShell();
        radioClick(bot.radio("WSDL"));
        bot.sleep(1000);
        bot.link().click();
        bot.text(1).setText("TestService.wsdl");
        bot.button("Next >").click();
        finishWizard(bot);
        firstWizard.activate();
        bot.text(1).setText("TestService3");
        finishWizard(bot);

        // existing wsdl, tool
        editor.activateTool("Reference");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        radioClick(bot.radio("WSDL"));
        bot.button("Browse...").click();
        // XXX: select type
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService4");
        finishWizard(bot);

        // esb, tool
        editor.activateTool("Reference");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        radioClick(bot.radio("ESB"));
        bot.link().click();
        bot.text().setText("{tns}input");
        bot.text(1).setText("{tns}output");
        bot.text(2).setText("{tns}fault");
        bot.button("OK").click();
        firstWizard.activate();
        bot.text(1).setText("TestService5");
        finishWizard(bot);

        // save
        bot.menu("File").menu("Save").click();
        // make sure the file is written
        waitForSave();

        assertXMLFilesMatch("switchyard.xml file does not match expected.", getSwitchYardFile(), getClass()
                .getSimpleName() + ".testCompositeReference.switchyard.xml");
    }

    /**
     * Tests the creation of composite services.
     * 
     * <ol>
     * <li>Using service tool, new Java interface.</li>
     * <li>Using composite context pad, specifying Java interface created in
     * step 1.</li>
     * <li>Using context menu, new WSDL interface.</li>
     * </ol>
     * 
     * @throws Exception if test fails.
     */
    @Test
    public void testComponent() throws Exception {
        final SWTBotGefEditor editor = new SWTBotGefEditor(bot.activeEditor().getReference(), bot);
        final SWTBotGefEditPart compositeEditPart = editor.getEditPart("switchyard-bot-test");
        final Point compositeCenter = ((GraphicalEditPart) compositeEditPart.part()).getFigure().getBounds()
                .getCenter();

        // tool
        editor.activateTool("Component");
        compositeEditPart.click(new Point(100, 100));

        // context pad
        editor.activateDefaultTool();
        compositeEditPart.click(compositeCenter);
        bot.sleep(1000); // wait for context pad
        EventContextMenuFinder contextMenuFinder = new EventContextMenuFinder();
        try {
            contextMenuFinder.register();
            initiateContextPadAction(editor, "Component");
            bot.sleep(1000);
            new SWTBotMenu(contextMenuFinder.findMenus(WidgetMatcherFactory.<MenuItem> withText("Component")).get(
                    0)).click();
            bot.sleep(1000);
            // context pad popup puts the display to sleep()
            SWTUtils.display().wake();
        } finally {
            contextMenuFinder.unregister();
        }

        // save
        bot.menu("File").menu("Save").click();
        waitForSave();

        assertXMLFilesMatch("switchyard.xml file does not match expected.", getSwitchYardFile(), getClass()
                .getSimpleName() + ".testComponent.switchyard.xml");
    }

    private static final class FixedSWTBotGefEditor extends SWTBotGefEditor {

        private SWTBotGefFigureCanvas _canvas;

        private FixedSWTBotGefEditor(IEditorReference reference, SWTWorkbenchBot bot) throws WidgetNotFoundException {
            super(reference, bot);
            _canvas = UIThreadRunnable.syncExec(new Result<SWTBotGefFigureCanvas>() {
                public SWTBotGefFigureCanvas run() {
                    final IEditorPart editor = partReference.getEditor(true);
                    GraphicalViewer graphicalViewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
                    final Control control = graphicalViewer.getControl();
                    if (control instanceof FigureCanvas) {
                        return new FixedSWTBotGefFigureCanvas((FigureCanvas) control);
                    } else if (control instanceof Canvas) {
                        if (control instanceof IAdaptable) {
                            IAdaptable adaptable = (IAdaptable) control;
                            Object adapter = adaptable.getAdapter(LightweightSystem.class);
                            if (adapter instanceof LightweightSystem) {
                                return new FixedSWTBotGefFigureCanvas((Canvas) control, (LightweightSystem) adapter);
                            }
                        }
                    }
                    throw new WidgetNotFoundException("Could not find canvas for editor.");
                }
            });
        }

        @Override
        public void click(int xPosition, int yPosition) {
            _canvas.mouseMoveLeftClick(xPosition, yPosition);
        }

    }

    private static final class FixedSWTBotGefFigureCanvas extends SWTBotGefFigureCanvas {

        private FixedSWTBotGefFigureCanvas(Canvas canvas, LightweightSystem lightweightSystem)
                throws WidgetNotFoundException {
            super(canvas, lightweightSystem);
        }

        private FixedSWTBotGefFigureCanvas(FigureCanvas canvas) throws WidgetNotFoundException {
            super(canvas);
        }

        @Override
        public void mouseMoveLeftClick(final int xPosition, final int yPosition) {
            UIThreadRunnable.asyncExec(new VoidResult() {
                public void run() {
                    eventDispatcher.dispatchMouseMoved(new MouseEvent(createMouseEvent(xPosition, yPosition, 0, 0, 0)));
                }
            });
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                }
            });
            UIThreadRunnable.asyncExec(new VoidResult() {
                public void run() {
                    eventDispatcher.dispatchMousePressed(new MouseEvent(createMouseEvent(xPosition, yPosition, 1, SWT.BUTTON1, 1)));
                }
            });
//            UIThreadRunnable.syncExec(new VoidResult() {
//                @Override
//                public void run() {
//                }
//            });
            UIThreadRunnable.asyncExec(new VoidResult() {
                public void run() {
                    eventDispatcher.dispatchMouseReleased(new MouseEvent(createMouseEvent(xPosition, yPosition, 1, SWT.BUTTON1, 1)));
                }
            });
//            UIThreadRunnable.syncExec(new VoidResult() {
//                @Override
//                public void run() {
//                }
//            });
        }
    }
    private static final class SWTBotPopupMenu extends SWTBotMenu {

        private SWTBotPopupMenu(MenuItem w) throws WidgetNotFoundException {
            super(w);
        }

        @Override
        public SWTBotMenu click() {
            final Menu menu = syncExec(new Result<Menu>() {
                public Menu run() {
                    return widget.getParent();
                }
            });
            super.click();
            syncExec(new VoidResult() {
                @Override
                public void run() {
                    menu.notifyListeners(SWT.Hide, null);
                }
            });
            return this;
        };

    }
}
