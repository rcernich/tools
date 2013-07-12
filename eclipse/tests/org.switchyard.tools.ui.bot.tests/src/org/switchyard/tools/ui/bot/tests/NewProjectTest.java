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

import java.io.InputStreamReader;
import java.io.Reader;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IEditorReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests new project creation.
 */
public class NewProjectTest extends SWTBotEclipseTestCase {

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
        closeAllEditors();
    }

    @After
    protected void tearDown() throws Exception {
        try {
            ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT)
                    .delete(true, true, new NullProgressMonitor());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        super.tearDown();
    }

    /**
     * Closes all open editors.
     */
    @SuppressWarnings("deprecation")
    protected void closeAllEditors() {
        try {
            for (SWTBotEclipseEditor editor : bot.editors()) {
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

    /**
     * Creates a new project using the wizard.
     * 
     * @throws Exception if something goes wrong or fails.
     */
    protected void createProject() throws Exception {
        bot.menu("File").menu("New").menu("Project...").click();
        final SWTBotShell wizard = bot.activeShell();
        bot.tree().getTreeItem("SwitchYard").expand();
        bot.tree().getTreeItem("SwitchYard").getNode("SwitchYard Project").select();
        bot.button("Next >").click();
        bot.text().setText(TEST_PROJECT);
        bot.button("Next >").click();
        bot.text(1).setText(TEST_GROUP);
        bot.text(3).setText(TEST_GROUP);
        bot.button("Finish").click();
        bot.waitUntil(Conditions.shellCloses(wizard), 30000);
        bot.waitUntil(Conditions.waitForEditor(WidgetMatcherFactory
                .<IEditorReference> withPartId("org.switchyard.tools.ui.editor.switchyard.editor.multi")));
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

    protected void assertXMLFilesMatch(String label, IFile testFile, String expectedFileName) throws Exception {
        Reader testReader = null;
        Reader expectedReader = null;
        try {
            testReader = new InputStreamReader(testFile.getContents());
            expectedReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(
                    getClass().getPackage().getName().replace('.', '/') + "/" + expectedFileName));
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
     * Creates a new project using the wizard, then validates that the
     * switchyard.xml file is open for editing and that the project contains no
     * errors/warnings.
     * 
     * @throws Exception if something goes wrong or fails.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testNewProjectWizard() throws Exception {
        createProject();
        // Validate...
        final IEditorReference editorReference = bot.activeEditor().getReference();
        final IProject switchYardProject = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);

        // editor open
        assertEquals(editorReference.getId(), "org.switchyard.tools.ui.editor.switchyard.editor.multi");

        // editing correct file
        final IFile switchYardFile = (IFile) editorReference.getEditorInput().getAdapter(IFile.class);
        assertEquals(switchYardFile.getFullPath().toString(),
                switchYardProject.getFile("src/main/resources/META-INF/switchyard.xml").getFullPath().toString());

        // pom details
        assertXMLFilesMatch("pom.xml file does not match expected file", switchYardProject.getFile("pom.xml"),
                getClass().getSimpleName() + ".pom.xml");

        // switchyard.xml details
        assertXMLFilesMatch("switchyard.xml file does not match expected file", switchYardFile, getClass().getSimpleName()
                + ".switchyard.xml");

        // no errors/warnings
        waitForBuildJobs();
        assertTrue(switchYardProject.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE) <= IMarker.SEVERITY_INFO);
    }
}
