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
package org.switchyard.tools.soap.ui.model;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlRootBinding;
import org.switchyard.component.soap.config.model.SOAPBindingModel;
import org.switchyard.config.model.composite.BindingModel;
import org.switchyard.tools.ui.editor.model.sca.IBinding;

/**
 * ISoapBinding
 * 
 * SOAP binding UI model.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@Label(standard = "SOAP Binding")
@XmlRootBinding(elementName = BindingModel.BINDING + '.' + SOAPBindingModel.SOAP, namespace = SOAPBindingModel.DEFAULT_NAMESPACE, defaultPrefix = "sySoap")
public interface ISoapBinding extends IBinding {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(ISoapBinding.class);

    /** The "wsdl" element. */
    @XmlBinding(path = "wsdl")
    @Label(standard = "WSDL File Location")
    @Required
    ValueProperty PROP_WSDL = new ValueProperty(TYPE, "Wsdl");

    /**
     * @return the wsdl value.
     */
    Value<String> getWsdl();

    /**
     * @param value the new wsdl value;
     */
    void setWsdl(String value);

    /** The "wsdlPort" element. */
    @XmlBinding(path = "wsdlPort")
    @Label(standard = "WSDL Port")
    ValueProperty PROP_WSDL_PORT = new ValueProperty(TYPE, "WsdlPort");

    /**
     * @return the wsdlPort value.
     */
    Value<String> getWsdlPort();

    /**
     * @param value the new wsdlPort value;
     */
    void setWsdlPort(String value);

    /** The "serverHost" element. */
    @XmlBinding(path = "serverHost")
    @Label(standard = "Server Host")
    ValueProperty PROP_SERVER_HOST = new ValueProperty(TYPE, "ServerHost");

    /**
     * @return the serverHost value.
     */
    Value<String> getServerHost();

    /**
     * @param value the new serverHost value;
     */
    void setServerHost(String value);

    /** The "serverPort" element. */
    @Type(base = Integer.class)
    @XmlBinding(path = "serverPort")
    @Label(standard = "Server Port")
    @DefaultValue(text = "8080")
    ValueProperty PROP_SERVER_PORT = new ValueProperty(TYPE, "ServerPort");

    /**
     * @return the serverPort value.
     */
    Value<Integer> getServerPort();

    /**
     * @param value the new serverPort value;
     */
    void setServerPort(Integer value);

    /**
     * @param value the new serverPort value;
     */
    void setServerPort(String value);

    /** The "contextPath" element. */
    @XmlBinding(path = "contextPath")
    @Label(standard = "Context Path")
    ValueProperty PROP_CONTEXT_PATH = new ValueProperty(TYPE, "ContextPath");

    /**
     * @return the contextPath value.
     */
    Value<String> getContextPath();

    /**
     * @param value the new contextPath value;
     */
    void setContextPath(String value);

}
