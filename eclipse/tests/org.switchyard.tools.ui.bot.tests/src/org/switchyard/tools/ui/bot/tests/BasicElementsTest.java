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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
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
        waitForSave();

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
        initiateContextPadAction(editor, "Component", "Component");

        // save
        bot.menu("File").menu("Save").click();
        waitForSave();

        assertXMLFilesMatch("switchyard.xml file does not match expected.", getSwitchYardFile(), getClass()
                .getSimpleName() + ".testComponent.switchyard.xml");
    }

}
