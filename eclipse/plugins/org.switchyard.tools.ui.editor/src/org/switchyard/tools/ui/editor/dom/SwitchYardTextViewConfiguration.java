/*************************************************************************************
 * Copyright (c) 2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.switchyard.tools.ui.editor.dom;

import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

/**
 * SwitchYardTextViewConfiguration
 * 
 * <p/>
 * Basic specialization for switchyard.xml files.
 */
public class SwitchYardTextViewConfiguration extends StructuredTextViewerConfigurationXML {

    /**
     * Create a new SwitchYardTextViewConfiguration.
     */
    public SwitchYardTextViewConfiguration() {
    }

    @Override
    public IContentAssistProcessor[] getContentAssistProcessors(ISourceViewer sourceViewer, String partitionType) {
        return super.getContentAssistProcessors(sourceViewer, partitionType);
    }

    @Override
    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
        return super.getTextHover(sourceViewer, contentType, stateMask);
    }

    @Override
    public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
        return super.getHyperlinkDetectors(sourceViewer);
    }

    @Override
    public IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer) {
        return super.getQuickAssistAssistant(sourceViewer);
    }
}
