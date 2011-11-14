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
package org.switchyard.tools.ui.editor.model;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.switchyard.config.model.transform.TransformModel;
import org.switchyard.transform.config.model.SmooksTransformModel;

/**
 * ISmooksTransform
 * 
 * Represents transform.smooks elements.
 * 
 * @author Rob Cernich
 */
@GenerateImpl
@XmlBinding(path = TransformModel.TRANSFORM + '.' + SmooksTransformModel.SMOOKS)
@XmlNamespace(prefix = ISwitchYard.TRANSFORM_XMLNS_PREFIX, uri = SmooksTransformModel.DEFAULT_NAMESPACE)
public interface ISmooksTransform extends ITransform {

    /** The type of this model element. */
    ModelElementType TYPE = new ModelElementType(ISmooksTransform.class);

    /** The "config" attribute. */
    @XmlBinding(path = "@" + SmooksTransformModel.CONFIG)
    @Label(standard = "Config")
    @Required
    ValueProperty PROP_CONFIG = new ValueProperty(TYPE, "Config");

    /**
     * @return the config value.
     */
    Value<String> getConfig();

    /**
     * @param value the new config value.
     */
    void setConfig(String value);

    /** The "type" attribute. */
    @XmlBinding(path = "@" + SmooksTransformModel.TYPE)
    @Label(standard = "Type")
    @Required
    ValueProperty PROP_TYPE = new ValueProperty(TYPE, "Type");

    /**
     * @return the type value.
     */
    Value<String> getType();

    /**
     * @param value the new type value.
     */
    void setType(String value);

    /** The "reportPath" attribute. */
    @XmlBinding(path = "@" + SmooksTransformModel.REPORT_PATH)
    @Label(standard = "Report Path")
    @Required
    ValueProperty PROP_REPORT_PATH = new ValueProperty(TYPE, "ReportPath");

    /**
     * @return the reportPath value.
     */
    Value<String> getReportPath();

    /**
     * @param value the new reportPath value.
     */
    void setReportPath(String value);

}
