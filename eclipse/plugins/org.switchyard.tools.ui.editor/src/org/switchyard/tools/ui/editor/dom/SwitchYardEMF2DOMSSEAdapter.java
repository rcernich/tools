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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emf.plugin.EcoreUtilitiesPlugin;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.utilities.DOMUtilities;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSEAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SwitchYardEMF2DOMSSEAdapter
 * 
 * <p/>
 * Adapter for converting EMF objects to DOM objects and vice versa. Does not
 * support read-ahead, but does support namespaces. Translators should specify
 * QName.toString() for their paths.
 */
@SuppressWarnings("restriction")
public class SwitchYardEMF2DOMSSEAdapter extends EMF2DOMSSEAdapter {

    /**
     * Create a new SwitchYardEMF2DOMSSEAdapter.
     * 
     * @param resource the resource
     * @param document the document
     * @param renderer the renderer
     * @param translator the translator
     */
    public SwitchYardEMF2DOMSSEAdapter(TranslatorResource resource, Document document, EMF2DOMRenderer renderer,
            Translator translator) {
        super(resource, document, renderer, translator);
    }

    private SwitchYardEMF2DOMSSEAdapter(Node node, EMF2DOMRenderer fRenderer, Translator childMap) {
        super(node, fRenderer, childMap);
    }

    private SwitchYardEMF2DOMSSEAdapter(Notifier object, Node node, EMF2DOMRenderer renderer, Translator translator) {
        super(object, node, renderer, translator);
    }

    @Override
    protected void setTargetFromNode() {
        setTarget(fTranslator.createEMFObject(new QName(fNode.getNamespaceURI(), fNode.getLocalName()).toString(),
                extractReadAheadName()));
    }

    @Override
    public void primUpdateMOF() {
        super.primUpdateMOF();
    }

    @Override
    protected Element createNewNode(EObject mofObject, Translator childMap) {
        final Node node = getNode();
        final Document doc = (node instanceof Document) ? (Document) node : node.getOwnerDocument();
        final QName qname = QName.valueOf(childMap.getDOMName(mofObject));
        return doc.createElementNS(qname.getNamespaceURI(), qname.getLocalPart());
    }

    @Override
    protected Node findDOMPath(Node parent, Translator map, boolean addAdapters) {
        final String path = map.getDOMPath();
        Node curNode = parent;
        Iterator<String> iter = createPathIterator(path);

        while (curNode != null && iter.hasNext()) {
            final String nodeName = iter.next();
            curNode = getNodeChild(curNode, nodeName);
            if (addAdapters && curNode != null) {
                addDOMAdapter(curNode);
            }
        }
        return curNode;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected List getDOMChildren(Node node, Translator map) {
        final Node parent = findDOMPath(node, map, true);
        if (parent != null) {
            return getNodeChildren(parent, map.getDOMNames());
        }
        return new ArrayList();
    }

    @Override
    protected Node createDOMPath(Node node, Translator map) {
        Iterator<String> i = createPathIterator(map.getDOMPath());
        Node curNode = node;
        while (i.hasNext()) {
            String nodeName = i.next();
            curNode = findOrCreateNode(node, map, nodeName);
        }
        return curNode;
    }

    @Override
    protected Element findOrCreateNode(Node parent, Translator map, String segment) {
        final QName name = QName.valueOf(segment);
        Node node = getNodeChild(parent, segment);
        if (node == null) {
            // The node did not already exist, create it.
            final Document doc = parent.getOwnerDocument();
            node = doc.createElementNS(name.getNamespaceURI(), name.getLocalPart());
            final Node insertBeforeNode = findInitialInsertBeforeNode(parent, map);
            DOMUtilities.insertBeforeNodeAndWhitespace(parent, node, insertBeforeNode);
            indent(node, map);
            addDOMAdapter(node); // Hook up listeners
        }
        return (Element) node;
    }

    @Override
    protected Node findDOMNode(Node parent, Translator map, boolean addAdapters) {
        // First, trace down the path
        Node curNode = findDOMPath(parent, map, addAdapters);
        if (map.isDOMTextValue() || map.isDOMAttribute() || curNode == null) {
            return curNode;
        }

        // Now look for the first DOM name we can find
        Node node = null;
        for (final String nodeName : map.getDOMNames()) {
            List<Node> nodes = getNodeChildren(curNode, nodeName);
            if (nodes != null && !nodes.isEmpty()) {
                if (nodes.size() > 1) {
                    handleInvalidMultiNodes(nodeName);
                }
                node = nodes.get(0);
                if (node != null) {
                    if (addAdapters) {
                        addDOMAdapter(curNode);
                    }
                    break;
                }
            }
        }
        return node;
    }

    @Override
    protected void addDOMAdapter() {
        primAddDOMAdapter(fNode, this);
        if (fDebug) {
            EcoreUtilitiesPlugin.logError("Adding DOM adapter: " + this); //$NON-NLS-1$
            EcoreUtilitiesPlugin.logError("\tto: " + fNode); //$NON-NLS-1$
        }

        // Go through the maps. All of the DOM nodes that are not listened
        // to by another DOM Node adapter, must be listened to by this adapter.
        final NodeList childNodes = fNode.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            final Node childNode = childNodes.item(j);
            final int nodeType = childNode.getNodeType();
            if (!DOMUtilities.isTextNode(childNode) && nodeType != Node.COMMENT_NODE) {
                try {
                    Translator map = findTranslator(getQName(childNode).toString(), false);
                    if (map != null && map.isManagedByParent()) {
                        addDOMAdapter(childNode);
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        }
    }

    @Override
    protected Node findInitialInsertBeforeNode(Node parentNode, Translator mapNode) {
        final Translator[] maps = getChildTranslators();

        // First, skip past all the maps in the ordered collection
        // of maps. We want to begin the search with this node.
        int i = 0;
        while (i < maps.length && maps[i] != mapNode) {
            ++i;
        }

        // Now search go through each map node until a child node matching
        // its DOM name is found.
        for (int j = i; j < maps.length; j++) {
            final Translator nodeToFindMap = maps[j];
            final Node node = getNodeChild(parentNode, nodeToFindMap.getDOMName(null));
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(EObject mofObject, Translator childMap) {
        Element newNode = createNewNode(mofObject, childMap);
        return new SwitchYardEMF2DOMSSEAdapter(mofObject, newNode, fRenderer, childMap);
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(Node node, Translator childMap) {
        return new SwitchYardEMF2DOMSSEAdapter(node, fRenderer, childMap);
    }

    private QName getQName(Node node) {
        return new QName(node.getNamespaceURI(), node.getLocalName());
    }

    private Node getNodeChild(Node node, String nodeName) {
        final QName name = QName.valueOf(nodeName);
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getLocalName().equals(name.getLocalPart())
                    && n.getNamespaceURI().equals(name.getNamespaceURI())) {
                return n;
            }
        }
        return null;
    }

    private List<Node> getNodeChildren(Node node, String[] nodeNames) {
        final List<Node> results = new ArrayList<Node>();
        for (final String nodeName : nodeNames) {
            results.addAll(getNodeChildren(node, nodeName));
        }
        return results;
    }

    private List<Node> getNodeChildren(Node node, String nodeName) {
        final QName name = QName.valueOf(nodeName);
        final NodeList childNodes = node.getChildNodes();
        final List<Node> results = new ArrayList<Node>();

        for (int i = 0; i < childNodes.getLength(); i++) {
            final Node n = childNodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getLocalName().equals(name.getLocalPart())
                    && n.getNamespaceURI().equals(name.getNamespaceURI())) {
                results.add(n);
            }
        }
        return results;
    }

    private Iterator<String> createPathIterator(String path) {
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        final Matcher matcher;
        if (path.length() == 0) {
            matcher = null;
        } else {
            matcher = Pattern.compile("\\G(([{][^}]*[}][^/]*)|([^{}/]*))[/]?").matcher(path);
        }

        return new Iterator<String>() {
            private boolean _hasNext = matcher == null ? false : matcher.find();

            public boolean hasNext() {
                return _hasNext;
            }

            public String next() {
                final String next = matcher.group(1);
                _hasNext = matcher.find();
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
