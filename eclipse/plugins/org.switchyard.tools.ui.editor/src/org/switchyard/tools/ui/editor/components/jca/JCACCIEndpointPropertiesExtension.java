/******************************************************************************* 
 * Copyright (c) 2014 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author bfitzpat
 ******************************************************************************/
package org.switchyard.tools.ui.editor.components.jca;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundConnection;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.shared.AbstractSwitchyardComposite;

/**
 * @author bfitzpat
 *
 */
public class JCACCIEndpointPropertiesExtension implements
        IJCAEndpointPropertiesExtension {

    private JCACCIEndpointPropertiesComposite _composite;

    @Override
    public AbstractSwitchyardComposite getComposite(Composite parent) {
        if (_composite == null) {
            _composite = new JCACCIEndpointPropertiesComposite();
        }
        return _composite;
    }

    /**
     * @author bfitzpat
     *
     */
    public class JCACCIEndpointPropertiesComposite extends AbstractJCABindingComposite {

        private JCABinding _binding;
        private Composite _panel;
        private JCAInteractionPropertyTable _propsList;

        @Override
        public String getTitle() {
            return Messages.JCACCIEndpointPropertiesExtension_Title;
        }

        @Override
        public String getDescription() {
            return getTitle();
        }

        @Override
        protected boolean validate() {
            return true;
        }

        @Override
        public void createContents(Composite parent, int style) {
            _panel = new Composite(parent, style);
            _panel.setLayout(new GridLayout(2, false));
            TabbedPropertySheetWidgetFactory factory = new TabbedPropertySheetWidgetFactory();
            factory.adapt(_panel, false, false);

            Group endpointPropsGroup = new Group(_panel, SWT.NONE);
            endpointPropsGroup.setText(getTitle());
            endpointPropsGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true, 3, 1));
            endpointPropsGroup.setLayout(new GridLayout(1, false));
            factory.adapt(endpointPropsGroup, false, false);

            _propsList = new JCAInteractionPropertyTable(endpointPropsGroup, SWT.NONE);
            _propsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4));
            _propsList.addChangeListener(new ChangeListener(){
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!inUpdate() && hasChanged()) {
                        validate();
                        handleModify(_propsList);
                        fireChangedEvent(_propsList);
                    }
                }
            });
            factory.adapt(_propsList, false, false);
        }

        @Override
        protected void handleModify(Control control) {
            super.handleModify(control);
            validate();
            setHasChanged(false);
            setDidSomething(true);
        }

        @Override
        public Composite getPanel() {
            return _panel;
        }

        @Override
        public void setBinding(Binding impl) {
            super.setBinding(impl);
            this._binding = (JCABinding) impl;
            _propsList.setTargetObject(this._binding);
            JCAInboundConnection inbound = this._binding.getInboundConnection();
            if (inbound != null) {
                if (_binding.getInboundInteraction() != null 
                        && _binding.getInboundInteraction().getEndpoint() != null) {
                    _propsList.setSelection(_binding.getInboundInteraction().getEndpoint().getProperty());
                }
            }
            setInUpdate(false);
            validate();
            addObservableListeners(true);
        }

        /**
         * @param binding incoming binding
         */
        public void setJCABinding(JCABinding binding) {
        }
    }
}
