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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorReference;
import org.junit.Test;

/**
 * Tests new project creation.
 */
public class NewProjectTest extends SwitchYardBotTestCase {

    /**
     * Creates a new project using the wizard, then validates that the
     * switchyard.xml file is open for editing and that the project contains no
     * errors/warnings.
     * 
     * @throws Exception if something goes wrong or fails.
     */
    @Test
    public void testNewProjectWizard() throws Exception {
        // Validate...
        final IEditorReference editorReference = bot.activeEditor().getReference();
        final IProject switchYardProject = ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT);

        // editor open
        assertEquals(editorReference.getId(), "org.switchyard.tools.ui.editor.switchyard.editor.multi");

        // editing correct file
        final IFile switchYardFile = (IFile) editorReference.getEditorInput().getAdapter(IFile.class);
        assertEquals(switchYardFile.getFullPath().toString(), getSwitchYardFile().getFullPath().toString());

        // pom details
        assertXMLFilesMatch("pom.xml file does not match expected file", switchYardProject.getFile("pom.xml"),
                getClass().getSimpleName() + ".pom.xml");

        // switchyard.xml details
        assertXMLFilesMatch("switchyard.xml file does not match expected file", switchYardFile, getClass()
                .getSimpleName() + ".switchyard.xml");

        // no errors/warnings
        waitForBuildJobs();
        assertTrue(switchYardProject.findMaxProblemSeverity(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE) <= IMarker.SEVERITY_INFO);
    }
}
