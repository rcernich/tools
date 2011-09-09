/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.tools.m2e;

import java.io.File;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.Scanner;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.sonatype.plexus.build.incremental.BuildContext;

/**
 * SwitchYardBuildParticipant
 * 
 * Manages SwitchYard mojo execution during Eclipse builds.
 * 
 * @author Rob Cernich
 */
public class SwitchYardBuildParticipant extends MojoExecutionBuildParticipant {

    private static final String SWITCHYARD_DEFAULT_OUTPUT_FILE_PATH = "META-INF/switchyard.xml";

    /**
     * Create a new SwitchYardBuildParticipant.
     * 
     * @param execution the execution wrapped by this build participant.
     */
    public SwitchYardBuildParticipant(MojoExecution execution) {
        super(execution, true);
    }

    @Override
    public Set<IProject> build(int kind, IProgressMonitor monitor) throws Exception {
        File outputDirectory = getOutputDirectory();
        File outputFile = getOutputFile(outputDirectory);

        if (IncrementalProjectBuilder.FULL_BUILD == kind) {
            // skip delta scanning and just perform the build.
            return performBuild(outputFile, kind, monitor);
        }

        // scan for changes
        // scan for changes to META-INF/switchyard.xml source file
        BuildContext buildContext = getBuildContext();
        String[] compileClasspathElements = getCompileClasspathElements();
        for (String compileClasspath : compileClasspathElements) {
            if (buildContext.hasDelta(new File(compileClasspath, SWITCHYARD_DEFAULT_OUTPUT_FILE_PATH)
                    .getCanonicalFile())) {
                return performBuild(outputFile, kind, monitor);
            }
        }

        // scan for changes in "scanned" output folders
        File[] scanDirectories = getScanDirectories(outputDirectory);
        String outputFilePath = outputFile.getCanonicalPath();
        for (File scanDirectory : scanDirectories) {
            Scanner scanner = buildContext.newScanner(scanDirectory);
            String scanPath = scanDirectory.getCanonicalPath();
            if (outputFilePath.startsWith(scanPath)) {
                // we'll be changing this file and we don't want any infinite
                // build loops
                scanner.setExcludes(new String[] {outputFilePath.substring(scanPath.length()) });
            }
            scanner.scan();
            String[] includedFiles = scanner.getIncludedFiles();
            if (includedFiles != null && includedFiles.length > 0) {
                return performBuild(outputFile, kind, monitor);
            }
        }
        return null;
    }

    @Override
    public void clean(IProgressMonitor monitor) throws CoreException {
        File outputDirectory = getOutputDirectory();
        File outputFile = getOutputFile(outputDirectory);
        
        if (outputFile != null && outputFile.exists()) {
            if (outputFile.delete()) {
                getBuildContext().refresh(outputFile);
            }
        }

        super.clean(monitor);
    }

    private Set<IProject> performBuild(File outputFile, int kind, IProgressMonitor monitor) throws Exception {
        Set<IProject> result = super.build(kind, monitor);
        getBuildContext().refresh(outputFile);
        return result;
    }

    private File getOutputDirectory() throws CoreException {
        return MavenPlugin.getMaven().getMojoParameterValue(getSession(), getMojoExecution(), "outputDirectory",
                File.class);
    }

    private File getOutputFile(File outputDirectory) throws CoreException {
        File outputFile = MavenPlugin.getMaven().getMojoParameterValue(getSession(), getMojoExecution(), "outputFile",
                File.class);
        if (outputFile == null) {
            outputFile = new File(outputDirectory, SWITCHYARD_DEFAULT_OUTPUT_FILE_PATH);
        }
        return outputFile;
    }

    private String[] getCompileClasspathElements() throws CoreException {
        String[] compileClasspathElements = MavenPlugin.getMaven().getMojoParameterValue(getSession(),
                getMojoExecution(), "compileClasspathElements", String[].class);
        if (compileClasspathElements == null) {
            return new String[0];
        }
        return compileClasspathElements;
    }

    private File[] getScanDirectories(File outputDirectory) throws CoreException {
        File[] scanDirectories = MavenPlugin.getMaven().getMojoParameterValue(getSession(), getMojoExecution(),
                "scanDirectories", File[].class);
        if (scanDirectories == null || scanDirectories.length == 0) {
            return new File[] {outputDirectory };
        }
        return scanDirectories;
    }

}
