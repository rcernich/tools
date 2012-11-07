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
package org.switchyard.tools.ui.editor.dom.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.wst.common.internal.emf.plugin.EcoreUtilitiesPlugin;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.utilities.DOMUtilities;
import org.eclipse.wst.xml.core.internal.contentmodel.util.NamespaceTable;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSEAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * EMF2DOMSSEAdapterNS
 * 
 * <p/>
 * Adapter for converting EMF objects to DOM objects and vice versa. Does not
 * support read-ahead, but does support namespaces. Translators should specify
 * QName.toString() in their paths (e.g.
 * {http://example.com/some/schema}some-element).
 * 
 * <p/>
 * For the most part, this class replaces any parent method that uses
 * DOMUtilities. Those methods are identical to the original methods with
 * DOMUtilities portions replaced with namespace aware functionality.
 */
@SuppressWarnings("restriction")
public class EMF2DOMSSEAdapterNS extends EMF2DOMSSEAdapter {

    /**
     * Create a new EMF2DOMSSEAdapterNS.
     * 
     * @param resource the resource
     * @param document the document
     * @param renderer the renderer
     * @param translator the translator
     */
    public EMF2DOMSSEAdapterNS(TranslatorResource resource, Document document, EMF2DOMRenderer renderer,
            Translator translator) {
        super(resource, document, renderer, translator);
    }

    protected EMF2DOMSSEAdapterNS(Node node, EMF2DOMRenderer fRenderer, Translator childMap) {
        super(node, fRenderer, childMap);
    }

    protected EMF2DOMSSEAdapterNS(Notifier object, Node node, EMF2DOMRenderer renderer, Translator translator) {
        super(object, node, renderer, translator);
    }

    @Override
    protected void setTargetFromNode() {
        setTarget(fTranslator.createEMFObject(getQName(fNode).toString(), extractReadAheadName()));
    }

    /**
     * Make accessible.
     * 
     * @param tagName the tag name
     * @param attributeMap true if this is for an attribute
     * 
     * @return the translator associated with the tag.
     */
    @Override
    public Translator findTranslator(String tagName, boolean attributeMap) {
        return super.findTranslator(tagName, attributeMap);
    }

    @Override
    protected Element createNewNode(EObject mofObject, Translator childMap) {
        return createNewNode(getNode(), QName.valueOf(childMap.getDOMName(mofObject)));
    }

    private Element createNewNode(Node node, QName qname) {
        final Document doc = (node instanceof Document) ? (Document) node : node.getOwnerDocument();
        if (qname.getNamespaceURI() == XMLConstants.NULL_NS_URI) {
            return doc.createElement(qname.getLocalPart());
        }
        final Element parentElement = getElementForNode(node);
        if (parentElement == null) {
            // special case for document element
            final EPackage epackage = ExtendedMetaData.INSTANCE.getPackage(qname.getNamespaceURI());
            final String prefix = epackage == null ? null : epackage.getNsPrefix();
            final Element element = doc.createElementNS(qname.getNamespaceURI(), (prefix == null ? "" : prefix + ":")
                    + qname.getLocalPart());
            element.setAttribute(XMLConstants.XMLNS_ATTRIBUTE + (prefix == null ? "" : ":" + prefix),
                    qname.getNamespaceURI());
            return element;
        }
        @SuppressWarnings("deprecation")
        final NamespaceTable namespaces = new NamespaceTable(doc);
        namespaces.addElement(parentElement);
        namespaces.addElementLineage(parentElement);
        String prefix = namespaces.getPrefixForURI(qname.getNamespaceURI());
        if (prefix == null) {
            final EPackage epackage = ExtendedMetaData.INSTANCE.getPackage(qname.getNamespaceURI());
            prefix = epackage == null ? null : epackage.getNsPrefix();
            if (prefix == null) {
                prefix = "";
            }
            // make sure the prefix is not already in use
            int suffix = 1;
            for (String testPrefix = prefix, testNamespace = namespaces.getURIForPrefix(prefix); testNamespace != null
                    && !testNamespace.equals(qname.getNamespaceURI()); testNamespace = namespaces
                    .getURIForPrefix(prefix)) {
                prefix = testPrefix + "_" + suffix++;
            }
            if (namespaces.getURIForPrefix(prefix) == null) {
                doc.getDocumentElement().setAttribute(
                        XMLConstants.XMLNS_ATTRIBUTE + (prefix.length() == 0 ? "" : ":" + prefix),
                        qname.getNamespaceURI());
            }
        }
        return doc.createElementNS(qname.getNamespaceURI(),
                (prefix.length() == 0 ? "" : prefix + ":") + qname.getLocalPart());
    }

    private Element getElementForNode(Node node) {
        while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
            node = node.getParentNode();
        }
        return (Element) node;
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
            node = createNewNode(parent, name);
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
    protected void indentEndTag(String indentString, Node node, Translator map) {
        if (!map.shouldIndentEndTag(node)) {
            return;
        }
        final String domPath = map.getDOMPath();
        final QName name = domPath == null || domPath.length() == 0 ? null : QName.valueOf(domPath);

        if ((!map.isManagedByParent() && !map.isDOMTextValue()) || (map.isManagedByParent() && name != null)
                && name.equals(getQName(node))) {
            Text newWS = node.getOwnerDocument().createTextNode(getNewlineString(node) + indentString); //$NON-NLS-1$
            DOMUtilities.insertBeforeNode(node, newWS, null);
        }
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(EObject mofObject, Translator childMap) {
        Element newNode = createNewNode(mofObject, childMap);
        return new EMF2DOMSSEAdapterNS(mofObject, newNode, fRenderer, childMap);
    }

    @Override
    protected EMF2DOMAdapter primCreateAdapter(Node node, Translator childMap) {
        return new EMF2DOMSSEAdapterNS(node, fRenderer, childMap);
    }

    private QName getQName(Node node) {
        return new QName(node.getNamespaceURI(), node.getLocalName());
    }

    private Node getNodeChild(Node node, String nodeName) {
        if (nodeName == null || nodeName.length() == 0) {
            return null;
        }
        try {
            final QName name = QName.valueOf(nodeName);
            final NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                final Node n = children.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE && name.equals(getQName(n))) {
                    return n;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            if (n.getNodeType() == Node.ELEMENT_NODE && name.equals(getQName(n))) {
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
