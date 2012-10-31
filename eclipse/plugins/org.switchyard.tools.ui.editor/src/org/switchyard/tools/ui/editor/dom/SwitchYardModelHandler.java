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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolver;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapterFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.CMDocumentManager;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.contentmodel.modelqueryimpl.ModelQueryImpl;
import org.eclipse.wst.xml.core.internal.contentmodel.modelqueryimpl.XMLAssociationProvider;
import org.eclipse.wst.xml.core.internal.contentmodel.util.CMDocumentCache;
import org.eclipse.wst.xml.core.internal.contentmodel.util.NamespaceInfo;
import org.eclipse.wst.xml.core.internal.contentmodel.util.NamespaceTable;
import org.eclipse.wst.xml.core.internal.modelhandler.ModelHandlerForXML;
import org.eclipse.wst.xml.core.internal.modelhandler.XMLModelLoader;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryAdapterFactoryForXML;
import org.eclipse.wst.xml.core.internal.modelquery.XMLModelQueryAssociationProvider;
import org.eclipse.wst.xml.core.internal.ssemodelquery.ModelQueryAdapter;
import org.eclipse.wst.xml.core.internal.ssemodelquery.ModelQueryAdapterImpl;
import org.switchyard.tools.models.switchyard1_0.switchyard.util.SwitchyardResourceFactoryImpl;
import org.w3c.dom.Element;

/**
 * SwitchYardModelHandler
 * 
 * <p/>
 * Model handler for switchyard.xml files.
 * 
 * XXX: all this crap may be unnecessary if we're using ModelQueryExtension
 * (i.e. default may be good enough).
 */
@SuppressWarnings({"restriction" })
public class SwitchYardModelHandler extends ModelHandlerForXML {

    /**
     * Create a new SwitchYardModelHandler.
     */
    public SwitchYardModelHandler() {
        super();
        setAssociatedContentTypeId(SwitchyardResourceFactoryImpl.CONTENT_TYPE);
    }

    @Override
    public IModelLoader getModelLoader() {
        return new SwitchYardModelLoader();
    }

    private static final class SwitchYardModelLoader extends XMLModelLoader {

        @SuppressWarnings("rawtypes")
        @Override
        public List getAdapterFactories() {
            List<INodeAdapterFactory> result = new ArrayList<INodeAdapterFactory>();
            INodeAdapterFactory factory = new SwitchYardModelQueryAdapterFactory();
            result.add(factory);
            return result;
        }

    }

    private static final class SwitchYardModelQueryAdapterFactory extends ModelQueryAdapterFactoryForXML {

        @Override
        protected INodeAdapter createAdapter(INodeNotifier target) {
            if (modelQueryAdapterImpl == null) {
                final ModelQueryAdapter adapter = (ModelQueryAdapter) super.createAdapter(target);
                final ModelQuery query = new SwitchYardModelQueryImpl(adapter.getCMDocumentCache(),
                        adapter.getIdResolver());
                if (query.getCMDocumentManager() != null) {
                    // initialize all known related schema (e.g. bean)
                    final CMDocumentManager documentManager = query.getCMDocumentManager();
                    configureDocumentManager(documentManager);
                    final boolean oldAsyncLoad = documentManager
                            .getPropertyEnabled(CMDocumentManager.PROPERTY_ASYNC_LOAD);
                    documentManager.setPropertyEnabled(CMDocumentManager.PROPERTY_ASYNC_LOAD, true);
                    try {
                        for (EPackage pkg : SwitchYardTranslatorResourceImpl.PACKAGES) {
                            final String uri = pkg.getNsURI();
                            if (uri == null) {
                                continue;
                            }
                            documentManager.addCMDocumentReference(uri, "", "XSD");
                        }
                    } finally {
                        documentManager.setPropertyEnabled(CMDocumentManager.PROPERTY_ASYNC_LOAD, oldAsyncLoad);
                    }
                }
                modelQueryAdapterImpl = new ModelQueryAdapterImpl(adapter.getCMDocumentCache(), query,
                        adapter.getIdResolver());
            }
            return modelQueryAdapterImpl;
        }
    }

    private static final class SwitchYardModelQueryImpl extends ModelQueryImpl {
        public SwitchYardModelQueryImpl(CMDocumentCache cache, URIResolver idResolver) {
            super(new XMLModelQueryAssociationProvider(cache, idResolver));
        }

        /**
         * Include all known extension xsds (e.g. bean-v1.xsd).
         */
        @SuppressWarnings("rawtypes")
        @Override
        public List getCMDocumentList(Element element, CMElementDeclaration ed, String uri) {
            final List<CMDocument> result = new ArrayList<CMDocument>();
            final XMLAssociationProvider xmlAssociationProvider = (XMLAssociationProvider) modelQueryAssociationProvider;
            @SuppressWarnings("deprecation")
            final NamespaceTable namespaces = new NamespaceTable(element.getOwnerDocument());
            namespaces.addElementLineage(element);

            // deviate from super: add known extensions
            addKnownExtensions(namespaces);
            // end deviation

            @SuppressWarnings("unchecked")
            final List<NamespaceInfo> infoList = namespaces.getNamespaceInfoList();
            for (NamespaceInfo info : infoList) {
                if (info.uri != null) {
                    CMDocument document = xmlAssociationProvider.getCMDocument(info.uri, info.locationHint, "XSD");
                    if (document != null) {
                        result.add(document);
                    }
                }
            }
            return result;
        }

        private void addKnownExtensions(NamespaceTable namespaces) {
            for (EPackage pkg : SwitchYardTranslatorResourceImpl.PACKAGES) {
                final String uri = pkg.getNsURI();
                if (uri == null || namespaces.getNamespaceInfoForURI(uri) != null) {
                    continue;
                }
                String prefix = pkg.getNsPrefix();
                if (prefix == null) {
                    prefix = "";
                }
                // make sure the prefix is not already in use
                int suffix = 1;
                for (String testPrefix = prefix, testNamespace = namespaces.getURIForPrefix(prefix); testNamespace != null
                        && !testNamespace.equals(uri); testNamespace = namespaces.getURIForPrefix(prefix)) {
                    prefix = testPrefix + "_" + suffix++;
                }
                namespaces.addNamespaceInfo(prefix, uri, "");
            }
        }
    }

}
