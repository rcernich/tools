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
package org.switchyard.tools.xsd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import org.eclipse.soa.sca.sca1_1.model.sca.ScaPackage;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDataType;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNodeList;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.extension.ModelQueryExtension;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xml.core.internal.ssemodelquery.ModelQueryAdapter;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl.XSDBaseAdapter;
import org.eclipse.wst.xsd.contentmodel.internal.XSDImpl.XSDSchemaAdapter;
import org.eclipse.wst.xsd.contentmodel.internal.XSDTypeUtil;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.switchyard.tools.models.switchyard1_0.bean.BeanPackage;
import org.switchyard.tools.models.switchyard1_0.bpm.BPMPackage;
import org.switchyard.tools.models.switchyard1_0.camel.CamelPackage;
import org.switchyard.tools.models.switchyard1_0.camel.amqp.AmqpPackage;
import org.switchyard.tools.models.switchyard1_0.camel.atom.AtomPackage;
import org.switchyard.tools.models.switchyard1_0.camel.core.CorePackage;
import org.switchyard.tools.models.switchyard1_0.camel.file.FilePackage;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.FtpPackage;
import org.switchyard.tools.models.switchyard1_0.camel.jms.JmsPackage;
import org.switchyard.tools.models.switchyard1_0.camel.jpa.JpaPackage;
import org.switchyard.tools.models.switchyard1_0.camel.mail.MailPackage;
import org.switchyard.tools.models.switchyard1_0.camel.netty.NettyPackage;
import org.switchyard.tools.models.switchyard1_0.camel.quartz.QuartzPackage;
import org.switchyard.tools.models.switchyard1_0.camel.sql.SqlPackage;
import org.switchyard.tools.models.switchyard1_0.http.HttpPackage;
import org.switchyard.tools.models.switchyard1_0.jca.JcaPackage;
import org.switchyard.tools.models.switchyard1_0.resteasy.ResteasyPackage;
import org.switchyard.tools.models.switchyard1_0.rules.RulesPackage;
import org.switchyard.tools.models.switchyard1_0.soap.SOAPPackage;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardPackage;
import org.switchyard.tools.models.switchyard1_0.transform.TransformPackage;
import org.switchyard.tools.models.switchyard1_0.validate.ValidatePackage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * SwitchYardModelQueryExtension
 * <p/>
 * Model query helper for switchyard.xml files.
 */
@SuppressWarnings("restriction")
public class SwitchYardModelQueryExtension extends ModelQueryExtension {

    private static final Set<String> SWITCHYARD_1_0_CORE;
    private static final Set<String> SWITCHYARD_1_0_TRANSFORMS;
    private static final Set<String> SWITCHYARD_1_0_VALIDATES;
    private static final Set<String> SWITCHYARD_1_0_INTERFACES;
    private static final Set<String> SWITCHYARD_1_0_IMPLEMENTATION;
    private static final Set<String> SWITCHYARD_1_0_BINDING;
    private static final Set<QName> FILTERED_TYPES;
    private static final Set<QName> COMPOSITE_TYPES;

    private static final QName TRANSFORMS_QNAME = new QName(SwitchyardPackage.eNS_URI, "transforms");
    private static final QName VALIDATES_QNAME = new QName(SwitchyardPackage.eNS_URI, "validates");
    private static final QName COMPOSITE_QNAME = new QName(ScaPackage.eNS_URI, "composite");
    private static final QName COMPONENT_QNAME = new QName(ScaPackage.eNS_URI, "component");
    private static final QName SERVICE_QNAME = new QName(ScaPackage.eNS_URI, "service");
    private static final QName REFERENCE_QNAME = new QName(ScaPackage.eNS_URI, "reference");
    private static final QName IMPLEMENTATION_QNAME = new QName(ScaPackage.eNS_URI, "implementation");
    private static final QName SCA_BINDING_QNAME = new QName(ScaPackage.eNS_URI, "binding.sca");

    private static final Set<String> IMPLEMENTATION_REQUIRES_1_0;
    private static final Set<String> INTERACTION_REQUIRES_1_0;
    private static final Set<String> LOAD_BALANCE_1_0;

    static {
        // core
        SWITCHYARD_1_0_CORE = new LinkedHashSet<String>();
        SWITCHYARD_1_0_CORE.add(SwitchyardPackage.eNS_URI);

        SWITCHYARD_1_0_TRANSFORMS = new LinkedHashSet<String>();
        SWITCHYARD_1_0_TRANSFORMS.add(TransformPackage.eNS_URI);

        SWITCHYARD_1_0_VALIDATES = new LinkedHashSet<String>();
        SWITCHYARD_1_0_VALIDATES.add(ValidatePackage.eNS_URI);

        // interfaces
        SWITCHYARD_1_0_INTERFACES = new LinkedHashSet<String>();
        SWITCHYARD_1_0_INTERFACES.add(SwitchyardPackage.eNS_URI);

        // implementation
        SWITCHYARD_1_0_IMPLEMENTATION = new LinkedHashSet<String>();
        SWITCHYARD_1_0_IMPLEMENTATION.add(BeanPackage.eNS_URI);
        SWITCHYARD_1_0_IMPLEMENTATION.add(BPMPackage.eNS_URI);
        SWITCHYARD_1_0_IMPLEMENTATION.add(CamelPackage.eNS_URI);
        SWITCHYARD_1_0_IMPLEMENTATION.add(RulesPackage.eNS_URI);

        // binding
        SWITCHYARD_1_0_BINDING = new LinkedHashSet<String>();
        SWITCHYARD_1_0_BINDING.add(AmqpPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(AtomPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(CorePackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(FilePackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(FtpPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(HttpPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(JcaPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(JmsPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(JpaPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(MailPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(NettyPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(QuartzPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(ResteasyPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(SOAPPackage.eNS_URI);
        SWITCHYARD_1_0_BINDING.add(SqlPackage.eNS_URI);

        IMPLEMENTATION_REQUIRES_1_0 = new TreeSet<String>();
        IMPLEMENTATION_REQUIRES_1_0.add("managedTransaction.Global"); //$NON-NLS-1$
        IMPLEMENTATION_REQUIRES_1_0.add("managedTransaction.Local"); //$NON-NLS-1$
        IMPLEMENTATION_REQUIRES_1_0.add("noManagedTransaction"); //$NON-NLS-1$

        INTERACTION_REQUIRES_1_0 = new TreeSet<String>();
        INTERACTION_REQUIRES_1_0.add("confidentiality"); //$NON-NLS-1$
        INTERACTION_REQUIRES_1_0.add("authentication"); //$NON-NLS-1$
        INTERACTION_REQUIRES_1_0.add("propagatesTransaction"); //$NON-NLS-1$
        INTERACTION_REQUIRES_1_0.add("suspendsTransaction"); //$NON-NLS-1$

        LOAD_BALANCE_1_0 = new TreeSet<String>();
        LOAD_BALANCE_1_0.add("RandomStrategy"); //$NON-NLS-1$
        LOAD_BALANCE_1_0.add("RoundRobinStrategy"); //$NON-NLS-1$

        FILTERED_TYPES = new HashSet<QName>();
        // interfaces
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "interface.c"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "interface.cpp"));
        // implementations
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "implementation.java"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "implementation.composite"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "implementation.cpp"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "implementation.c"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "implementation.spring"));
        // bindings
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "binding.ws"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "binding.jms"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "binding.jca"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "binding.ejb"));
        // others
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "callback"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "definitions"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "extensions"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "include"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "intent"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "policySet"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "policySetAttachment"));
        FILTERED_TYPES.add(new QName(ScaPackage.eNS_URI, "requires"));
        FILTERED_TYPES.add(new QName(null, "policySets"));
        FILTERED_TYPES.add(new QName(null, "target"));
        FILTERED_TYPES.add(new QName(null, "wiredByImpl"));
        
        // types supported within composite
        COMPOSITE_TYPES = new HashSet<QName>();
        COMPOSITE_TYPES.add(COMPONENT_QNAME);
        COMPOSITE_TYPES.add(SERVICE_QNAME);
        COMPOSITE_TYPES.add(REFERENCE_QNAME);
        COMPOSITE_TYPES.add(new QName(ScaPackage.eNS_URI, "property"));
    };

    @Override
    public boolean isApplicableChildElement(Node parentNode, String namespace, String name) {
        /*
         * TODO: filter out unused SCA elements, e.g. implementation.c, binding
         * on component contracts, etc.
         * 
         * XXX: this doesn't appear to filter out unused attributes.
         */
        if (FILTERED_TYPES.contains(new QName(namespace, name))) {
            return false;
        }
        if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
            final Element element = (Element) parentNode;
            final QName elementName = new QName(element.getNamespaceURI(), element.getLocalName());
            if (SERVICE_QNAME.equals(elementName) || REFERENCE_QNAME.equals(elementName)) {
                final Node grandparent = element.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && COMPONENT_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    // no bindings for component services/references
                    return !name.startsWith("binding.");
                }
            } else if (COMPOSITE_QNAME.equals(elementName)) {
                return COMPOSITE_TYPES.contains(new QName(namespace, name));
            }
        }
        return true;
    }

    @Override
    public CMNode[] getAvailableElementContent(Element parentElement, String namespace, int includeOptions) {
        /*
         * TODO: add SwitchYard specific extensions, e.g. interface.esb,
         * transform.java, implementation.camel, binding.ftp, etc.
         */
        final ModelQueryAdapter adapter = ModelQueryUtil.getModelQueryAdapter(parentElement.getOwnerDocument());
        final QName elementName = new QName(parentElement.getNamespaceURI(), parentElement.getLocalName());
        final Collection<CMNode> nodes = new ArrayList<CMNode>();
        if ((includeOptions & ModelQuery.INCLUDE_CHILD_NODES) != 0) {
            if (TRANSFORMS_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_TRANSFORMS, "transform.", nodes, true);
            } else if (VALIDATES_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_VALIDATES, "validate.", nodes, true);
            } else if (COMPONENT_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_IMPLEMENTATION, "implementation.", nodes, true);
            } else if (SERVICE_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_INTERFACES, "interface.", nodes, true);
                final Node grandparent = parentElement.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && COMPOSITE_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    addNodes(adapter, SWITCHYARD_1_0_BINDING, "binding.", nodes, true);
                }
            } else if (REFERENCE_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_INTERFACES, "interface.", nodes, true);
                final Node grandparent = parentElement.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && COMPOSITE_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    addNodes(adapter, SWITCHYARD_1_0_BINDING, "binding.", nodes, true);
                }
            }
        }

        if ((includeOptions & ModelQuery.INCLUDE_ATTRIBUTES) != 0) {
            if (SERVICE_QNAME.equals(elementName)) {
                final Node grandparent = parentElement.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && COMPONENT_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    addNodes(adapter, SWITCHYARD_1_0_CORE, "security", nodes, false);
                }
            } else if (REFERENCE_QNAME.equals(elementName)) {
                final Node grandparent = parentElement.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && COMPONENT_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    addNodes(adapter, SWITCHYARD_1_0_CORE, "security", nodes, false);
                }
            } else if (SCA_BINDING_QNAME.equals(elementName)) {
                addNodes(adapter, SWITCHYARD_1_0_CORE, "clustered", nodes, false);
                final Node grandparent = parentElement.getParentNode();
                if (grandparent.getNodeType() == Node.ELEMENT_NODE
                        && REFERENCE_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                                ((Element) grandparent).getLocalName()))) {
                    addNodes(adapter, SWITCHYARD_1_0_CORE, "loadBalance", nodes, false);
                    addNodes(adapter, SWITCHYARD_1_0_CORE, "target", nodes, false);
                    addNodes(adapter, SWITCHYARD_1_0_CORE, "targetNamespace", nodes, false);
                }
            }
        }

        return nodes.toArray(new CMNode[nodes.size()]);
    }

    @Override
    public String[] getAttributeValues(Element ownerElement, String namespace, String name) {
        /*
         * TODO: attribute content. we should handle things like from/to on
         * transforms, type on validators, bean, promote, policy requires, etc.
         * derived from project contents.
         */
        final QName elementName = new QName(ownerElement.getNamespaceURI(), ownerElement.getLocalName());
        if (IMPLEMENTATION_QNAME.equals(elementName)) {
            if (ScaPackage.eNS_URI.equals(namespace) && "requires".equals(name)) {
                return getContentListProposals(ownerElement.getAttribute("requires"), IMPLEMENTATION_REQUIRES_1_0);
            }
        } else if (SERVICE_QNAME.equals(elementName)) {
            final Node grandparent = ownerElement.getParentNode();
            if (grandparent.getNodeType() == Node.ELEMENT_NODE
                    && COMPONENT_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                            ((Element) grandparent).getLocalName()))) {
                if (ScaPackage.eNS_URI.equals(namespace) && "requires".equals(name)) {
                    return getContentListProposals(ownerElement.getAttribute("requires"), INTERACTION_REQUIRES_1_0);
                }
            }
        } else if (REFERENCE_QNAME.equals(elementName)) {
            final Node grandparent = ownerElement.getParentNode();
            if (grandparent.getNodeType() == Node.ELEMENT_NODE
                    && COMPONENT_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                            ((Element) grandparent).getLocalName()))) {
                if (ScaPackage.eNS_URI.equals(namespace) && "requires".equals(name)) {
                    return getContentListProposals(ownerElement.getAttribute("requires"), INTERACTION_REQUIRES_1_0);
                }
            }
        } else if (SCA_BINDING_QNAME.equals(elementName)) {
            final Node grandparent = ownerElement.getParentNode();
            if (grandparent.getNodeType() == Node.ELEMENT_NODE
                    && REFERENCE_QNAME.equals(new QName(((Element) grandparent).getNamespaceURI(),
                            ((Element) grandparent).getLocalName()))) {
                if (SwitchyardPackage.eNS_URI.equals(namespace) && "loadBalance".equals(name)) {
                    return LOAD_BALANCE_1_0.toArray(new String[LOAD_BALANCE_1_0.size()]);
                }
            }
        }
        return EMPTY_STRING_ARRAY;
    }

    @Override
    public String[] getElementValues(Node parentNode, String namespace, String name) {
        /*
         * TODO: default values for element types with text content. i don't
         * think there's anything really applicable here.
         */
        return super.getElementValues(parentNode, namespace, name);
    }

    private String[] getContentListProposals(String value, Set<String> availableValues) {
        availableValues = new LinkedHashSet<String>(availableValues);
        final StringTokenizer tokenizer = new StringTokenizer(value);
        final StringBuffer buffer = new StringBuffer();
        final boolean endsWithSpace = value.endsWith(" ");
        for (int index = 0, count = endsWithSpace ? tokenizer.countTokens() : tokenizer.countTokens() - 1; index < count; ++index) {
            final String nextToken = tokenizer.nextToken();
            buffer.append(nextToken).append(' ');
            availableValues.remove(nextToken);
        }
        if (!endsWithSpace && tokenizer.hasMoreTokens()) {
            final String nextToken = tokenizer.nextToken();
            if (availableValues.remove(nextToken)) {
                // special case where the value ends with a complete available
                // value
                buffer.append(nextToken).append(' ');
            }
        }
        final String baseValue = buffer.toString();
        final List<String> proposals = new ArrayList<String>(availableValues.size());
        for (final String proposal : availableValues) {
            proposals.add(baseValue + proposal);
        }
        return proposals.toArray(new String[proposals.size()]);
    }

    private void addNodes(final ModelQueryAdapter adapter, final Collection<String> namespaces,
            final String localNamePrefix, final Collection<CMNode> nodes, final boolean elements) {
        for (String namespaceUri : namespaces) {
            final CMDocument cmDoc = adapter.getModelQuery().getCMDocumentManager().getCMDocument(namespaceUri);
            if (cmDoc != null) {
                if (elements) {
                    for (@SuppressWarnings("unchecked")
                    Iterator<CMNode> it = cmDoc.getElements().iterator(); it.hasNext();) {
                        CMNode node = it.next();
                        if (node.getNodeName().startsWith(localNamePrefix)) {
                            nodes.add(node);
                        }
                    }
                } else {
                    if (cmDoc instanceof XSDSchemaAdapter) {
                        XSDSchema schema = (XSDSchema) ((XSDSchemaAdapter) cmDoc).getKey();
                        for (XSDAttributeDeclaration attrDecl : schema.getAttributeDeclarations()) {
                            if (namespaceUri.equals(attrDecl.getTargetNamespace()) && attrDecl.getName() != null
                                    && attrDecl.getName().startsWith(localNamePrefix)) {
                                nodes.add(new XSDAttributeDeclarationAdapter(attrDecl));
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * Adapted from
     * org.eclipse.wst.xsd.contentmodel.internal.XSDImpl.XSDAttributeUseAdapter.
     */
    private static final class XSDAttributeDeclarationAdapter extends XSDBaseAdapter implements CMAttributeDeclaration {

        private XSDAttributeDeclaration _xsdAttributeDeclaration;
        private CMDataType _dataType = new DataTypeImpl();

        private XSDAttributeDeclarationAdapter(XSDAttributeDeclaration xsdAttributeDeclaration) {
            _xsdAttributeDeclaration = xsdAttributeDeclaration;
        }

        @Override
        public Object getKey() {
            return _xsdAttributeDeclaration;
        }

        @Override
        public String getSpec() {
            return "//@" + getAttrName();
        }

        @Override
        public int getNodeType() {
            return ATTRIBUTE_DECLARATION;
        }

        @Override
        public String getNodeName() {
            return getAttrName();
        }

        @SuppressWarnings({"rawtypes", "unchecked" })
        @Override
        public Enumeration getEnumAttr() {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }

        @Override
        public String getAttrName() {
            return _xsdAttributeDeclaration.getName();
        }

        @Override
        public CMDataType getAttrType() {
            return _dataType;
        }

        @Override
        public String getDefaultValue() {
            return _dataType.getImpliedValue();
        }

        @Override
        public int getUsage() {
            return OPTIONAL;
        }

        @Override
        public Object getNSPrefixQualification() {
            return "qualified";
        }

        @Override
        protected CMNodeList getDocumentation() {
            return XSDImpl.getDocumentations(_xsdAttributeDeclaration.getAnnotation());
        }

        @Override
        public CMDocument getCMDocument() {
            return (CMDocument) XSDImpl.getAdapter(_xsdAttributeDeclaration.getSchema());
        }

        private final class DataTypeImpl implements CMDataType {
            @Override
            public int getNodeType() {
                return CMNode.DATA_TYPE;
            }

            @Override
            public String getNodeName() {
                return getDataTypeName();
            }

            @Override
            public boolean supports(String propertyName) {
                return (XSDImpl.PROPERTY_WHITESPACE_FACET.equals(propertyName));
            }

            @Override
            public Object getProperty(String propertyName) {
                if (XSDImpl.PROPERTY_WHITESPACE_FACET.equals(propertyName)) {
                    return getWhiteSpaceFacetValue();
                }
                return null;
            }

            @Override
            public String getDataTypeName() {
                XSDSimpleTypeDefinition sc = _xsdAttributeDeclaration.getTypeDefinition();
                String typeName = sc.getName();
                return typeName != null ? typeName : "string"; //$NON-NLS-1$
            }

            @Override
            public int getImpliedValueKind() {
                int result = IMPLIED_VALUE_NONE;
                if (_xsdAttributeDeclaration.isSetConstraint()) {
                    if (_xsdAttributeDeclaration.getConstraint().getValue() == XSDConstraint.DEFAULT) {
                        result = IMPLIED_VALUE_DEFAULT;
                    } else if (_xsdAttributeDeclaration.getConstraint().getValue() == XSDConstraint.FIXED) {
                        result = IMPLIED_VALUE_FIXED;
                    }
                }
                return result;
            }

            @Override
            public String getImpliedValue() {
                String result = null;
                if (_xsdAttributeDeclaration.isSetConstraint()) {
                    result = _xsdAttributeDeclaration.getLexicalValue();
                }
                return result;
            }

            @Override
            public String[] getEnumeratedValues() {
                return XSDImpl.getEnumeratedValuesForType(getXSDType());
            }

            @Override
            public String generateInstanceValue() {
                return XSDTypeUtil.getInstanceValue(_xsdAttributeDeclaration.getResolvedAttributeDeclaration()
                        .getTypeDefinition());
            }

            private XSDTypeDefinition getXSDType() {
                return _xsdAttributeDeclaration.getResolvedAttributeDeclaration().getTypeDefinition();
            }

            private String getWhiteSpaceFacetValue() {
                XSDSimpleTypeDefinition def = getXSDType().getSimpleType();
                return (def != null && def.getEffectiveWhiteSpaceFacet() != null) ? def.getEffectiveWhiteSpaceFacet()
                        .getLexicalValue() : null;
            }
        }
    }
}
