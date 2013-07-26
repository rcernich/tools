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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Test;

/**
 * BasicElementsTest
 * <p/>
 * Bot tests for basic SwitchYard elements: components, services and references.
 */
public class BindingsTest extends SwitchYardBotTestCase {

    /**
     * Tests the creation of bindings.
     * 
     * @throws Exception if test fails.
     */
    @Test
    public void testBindings() throws Exception {
        final SWTBotGefEditor editor = new SWTBotGefEditor(bot.activeEditor().getReference(), bot);
        final SWTBotGefEditPart compositeEditPart = editor.getEditPart("switchyard-bot-test");
        final Point compositeCenter = ((GraphicalEditPart) compositeEditPart.part()).getFigure().getBounds()
                .getCenter();

        SWTBotShell firstWizard = null;

        // new Java service, tool
        editor.activateTool("Service");
        compositeEditPart.click(compositeCenter);
        firstWizard = bot.activeShell();
        bot.link().click();
        bot.text(3).setText("TestService");
        finishWizard(bot, 10000);
        firstWizard.activate();
        finishWizard(bot);

        // new wsdl reference, context pad
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
        bot.text(1).setText("TestService2");
        finishWizard(bot);

        // TODO: add bindings
        // save
        bot.menu("File").menu("Save").click();
        waitForSave();

        assertXMLFilesMatch("switchyard.xml file does not match expected.", getSwitchYardFile(), getClass()
                .getSimpleName() + ".switchyard.xml");
    }

}
