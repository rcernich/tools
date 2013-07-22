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

import static org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled;

import java.io.InputStreamReader;
import java.io.Reader;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.ui.internal.contextbuttons.ContextButton;
import org.eclipse.graphiti.ui.internal.contextbuttons.ContextButtonPad;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;

/**
 * SwitchYardBotTestCase
 * <p/>
 * Base test case for SwitchYard bot tests. Creates a clean SwitchYard project
 * for running the test.
 */
@SuppressWarnings("restriction")
public abstract class SwitchYardBotTestCase extends SWTBotGefTestCase {

    protected static final String TEST_PROJECT = "switchyard-bot-test";
    protected static final String TEST_GROUP = "org.switchyard.ui.tests.bot";

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        try {
            bot.viewByTitle("Welcome").close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        UIThreadRunnable.syncExec(SWTUtils.display(), new VoidResult() {
            @Override
            public void run() {
                final Shell workbenchShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                if (!workbenchShell.getMaximized()) {
                    workbenchShell.setMaximized(true);
                }
            }
        });
        createProject();
    }

    @After
    protected void tearDown() throws Exception {
        UIThreadRunnable.syncExec(SWTUtils.display(), new CloseDialogs());
        closeAllEditors();
        deleteTestProject();
        super.tearDown();
    }

    /**
     * Closes all open editors.
     */
    protected void closeAllEditors() {
        try {
            for (SWTBotEditor editor : bot.editors()) {
                try {
                    editor.close();
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        } catch (WidgetNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    protected void deleteTestProject() {
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);
        if (project != null && project.exists()) {
            try {
                project.delete(true, true, new NullProgressMonitor());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    /**
     * Creates a new project using the wizard.
     * 
     * @throws Exception if something goes wrong or fails.
     */
    protected void createProject() throws Exception {
        bot.menu("File").menu("New").menu("Project...").click();
        bot.tree().getTreeItem("SwitchYard").expand();
        bot.tree().getTreeItem("SwitchYard").getNode("SwitchYard Project").select();
        bot.button("Next >").click();
        bot.text().setText(TEST_PROJECT);
        bot.button("Next >").click();
        bot.text(1).setText(TEST_GROUP);
        bot.text(3).setText(TEST_GROUP);
        finishWizard(bot, 60000);
        bot.waitUntil(Conditions.waitForEditor(WidgetMatcherFactory
                .<IEditorReference> withPartId("org.switchyard.tools.ui.editor.switchyard.editor.multi")));
    }

    /**
     * Invokes a "Finish" on the active bot, then waits for the dialog to close.
     * 
     * @param bot the bot
     * @param timeout the timeout
     */
    protected void finishWizard(final SWTWorkbenchBot bot, final long timeout) {
        final SWTBotShell wizard = bot.activeShell();
        bot.button("Finish").click();
        bot.waitUntil(Conditions.shellCloses(wizard), timeout);
    }

    /**
     * Invokes a "Finish" on the active bot, then waits for the dialog to close.
     * equivalent to finishWizard(bot, SWTBotPreferences.TIMEOUT).
     * 
     * @param bot the bot
     */
    protected void finishWizard(final SWTWorkbenchBot bot) {
        finishWizard(bot, SWTBotPreferences.TIMEOUT);
    }

    /**
     * Wait for the save to finish on switchyard.xml
     * 
     * @throws Exception if something goes wrong
     */
    protected void waitForSave() throws Exception {
        final long timeout = SWTBotPreferences.TIMEOUT;
        final Job myJob = new Job("File Exists") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                return Status.OK_STATUS;
            }
        };
        myJob.setRule(getSwitchYardFile());
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.wait(timeout);
                    }
                } catch (InterruptedException e) {
                    e.fillInStackTrace();
                }
                myJob.cancel();
            }
        }).run();
        myJob.schedule();
        myJob.join();
        synchronized (lock) {
            lock.notify();
        }
        if (!myJob.getResult().isOK()) {
            throw new TimeoutException("Timeout after: " + timeout + " ms.: waiting for file save."); 
        }
    }

    /**
     * Waits for build jobs to complete.
     * 
     * @throws Exception if something goes wrong.
     */
    protected void waitForBuildJobs() throws Exception {
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, new NullProgressMonitor());
    }

    protected IFile getSwitchYardFile() {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT)
                .getFile("src/main/resources/META-INF/switchyard.xml");
    }

    protected void radioClick(final SWTBotRadio radio) {
        new FixedSWTBotRadio(radio).click();
    }

    /**
     * Validates XML file contents.
     * 
     * @param label for the assertion.
     * @param testFile the file to test.
     * @param expectedFileName the name of the existing file, relative to the
     *            executing class.
     * @throws Exception if it fails.
     */
    protected void assertXMLFilesMatch(String label, IFile testFile, String expectedFileName) throws Exception {
        Reader testReader = null;
        Reader expectedReader = null;
        try {
            testReader = new InputStreamReader(testFile.getContents());
            expectedReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(
                    getClass().getPackage().getName().replace('.', '/') + "/" + expectedFileName));
            XMLUnit.setIgnoreWhitespace(true);
            XMLUnit.setIgnoreAttributeOrder(true);
            Diff diff = XMLUnit.compareXML(expectedReader, testReader);
            assertTrue(label + ": " + diff.toString(), diff.identical());
        } finally {
            if (testReader != null) {
                try {
                    testReader.close();
                } catch (Exception e) {
                    // for codestyle check
                    e.fillInStackTrace();
                }
                testReader = null;
            }
            if (expectedReader != null) {
                try {
                    expectedReader.close();
                } catch (Exception e) {
                    // for codestyle check
                    e.fillInStackTrace();
                }
                expectedReader = null;
            }
        }
    }

    /**
     * Initiate an action using a tool from the palette.
     * 
     * @param editor the editor
     * @param action the name of the tool to invoke
     */
    protected void initiateContextPadAction(SWTBotGefEditor editor, String action) {
        FreeformGraphicalRootEditPart rootEditPart = (FreeformGraphicalRootEditPart) editor.getSWTBotGefViewer()
                .rootEditPart().part();
        IFigure feedbackLayer = rootEditPart.getLayer(LayerConstants.HANDLE_LAYER);
        for (Object child : feedbackLayer.getChildren()) {
            if (child instanceof ContextButtonPad) {
                for (Object button : ((ContextButtonPad) child).getChildren()) {
                    ContextButton contextButton = (ContextButton) button;
                    IContextButtonEntry entry = contextButton.getEntry();
                    if (action.equals(entry.getText())) {
                        editor.click(contextButton.getBounds().x, contextButton.getBounds().y);
                    }
                }
            }
        }
    }

    private final class CloseDialogs implements VoidResult {
        private Shell _workbenchShell;

        @Override
        public void run() {
            _workbenchShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            closeChildren(_workbenchShell);
        }

        private void closeChildren(final Shell parent) {
            if (parent.isDisposed()) {
                return;
            }
            for (Shell shell : parent.getShells()) {
                closeChildren(shell);
            }
            if (parent != _workbenchShell && parent.isVisible()) {
                parent.close();
            }
        }
    }

    private final class FixedSWTBotRadio extends SWTBotRadio {
        private FixedSWTBotRadio(final SWTBotRadio radio) {
            this(radio.widget);
        }

        private FixedSWTBotRadio(Button button) {
            super(button);
        }

        public SWTBotRadio click() {
            if (this.isSelected()) {
                return this;
            }
            bot.waitUntil(widgetIsEnabled(this));

            final FixedSWTBotRadio otherSelectedButton = otherSelectedButton();

            if (otherSelectedButton != null) {
                otherSelectedButton.notify(SWT.Deactivate);
                asyncExec(new VoidResult() {
                    public void run() {
                        otherSelectedButton.widget.setSelection(false);
                    }
                });
                otherSelectedButton.notify(SWT.Selection);
            }

            notify(SWT.Activate);
            notify(SWT.MouseDown, createMouseEvent(0, 0, 1, 0, 1));
            notify(SWT.MouseUp, createMouseEvent(0, 0, 1, SWT.BUTTON1, 1));
            asyncExec(new VoidResult() {
                public void run() {
                    widget.setSelection(true);
                }
            });
            notify(SWT.Selection);
            log.debug(MessageFormat.format("Clicked on {0}", this)); //$NON-NLS-1$
            return this;
        }

        private FixedSWTBotRadio otherSelectedButton() {
            Button button = syncExec(new WidgetResult<Button>() {
                public Button run() {
                    if (hasStyle(widget.getParent(), SWT.NO_RADIO_GROUP)) {
                        return null;
                    }
                    Widget[] siblings = SWTUtils.siblings(widget);
                    for (Widget widget : siblings) {
                        if ((widget instanceof Button) && hasStyle(widget, SWT.RADIO)) {
                            if (((Button) widget).getSelection()) {
                                return (Button) widget;
                            }
                        }
                    }
                    return null;
                }
            });

            if (button != null) {
                return new FixedSWTBotRadio(button);
            }
            return null;
        }

    }
}
