/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.jca.Endpoint;
import org.switchyard.tools.models.switchyard1_0.jca.JCABinding;
import org.switchyard.tools.models.switchyard1_0.jca.JCAInboundInteraction;
import org.switchyard.tools.models.switchyard1_0.jca.JcaFactory;
import org.switchyard.tools.models.switchyard1_0.jca.Property;
import org.switchyard.tools.ui.editor.impl.SwitchyardSCAEditor;

/**
 * @author bfitzpat
 *
 */
public class JCAInteractionPropertyTable extends JCAPropertyTable {

    /**
     * @param parent Composite
     * @param style any additional style bits
     * @param toolkit Form toolkit to use when creating controls
     */
    public JCAInteractionPropertyTable(Composite parent, int style, FormToolkit toolkit) {
        super(parent, style, false, toolkit);
    }
    
    /**
     * @param parent Composite
     * @param style any additional style bits
     * @param isReadOnly flag
     * @param toolkit Form toolkit to use when creating controls
     */
    public JCAInteractionPropertyTable(Composite parent, int style, boolean isReadOnly, FormToolkit toolkit) {
        super(parent, style, isReadOnly, toolkit);
    }

    protected void removeFromList() {
        final Property toRemove = getTableSelection();
        if (toRemove != null) {
            if (getTargetObject() instanceof JCABinding) {
                final JCABinding binding = (JCABinding) getTargetObject();
                if (binding.getInboundInteraction() != null) {
                    final Endpoint endpoint = binding.getInboundInteraction().getEndpoint();
                    if (endpoint != null) {
                        if (endpoint.eContainer() != null) {
                            TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                            domain.getCommandStack().execute(new RecordingCommand(domain) {
                                @Override
                                protected void doExecute() {
                                    boolean flag = endpoint.getProperty().remove(toRemove);
                                    System.out.println("Property " + toRemove.getName() + " was removed: " + flag); //$NON-NLS-1$ //$NON-NLS-2$
                                }
                            });
                        } else {
                            boolean flag = endpoint.getProperty().remove(toRemove);
                            System.out.println("Property " + toRemove.getName() + " was removed: " + flag); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                    }
                }
            }
        }
    }

    /**
     * Add a new property to the list
     */
    protected void addPropertyToList() {
        final JCAPropertyInputDialog dialog = new JCAPropertyInputDialog(Display.getCurrent().getActiveShell());
        int rtn_value = dialog.open();
        if (rtn_value == JCAPropertyInputDialog.OK) {
            if (getTargetObject() instanceof JCABinding) {
                final JCABinding binding = (JCABinding) getTargetObject();
                if (getTargetObject().eContainer() != null) {
                    TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            Property newProperty = JcaFactory.eINSTANCE.createProperty();
                            newProperty.setName(dialog.getPropertyName());
                            newProperty.setValue(dialog.getPropertyValue());
                            
                            if (binding.getInboundInteraction() == null) {
                                JCAInboundInteraction inBound = JcaFactory.eINSTANCE.createJCAInboundInteraction();
                                setFeatureValue(binding, "outboundInteraction", inBound); //$NON-NLS-1$
                            }
                            if (binding.getInboundInteraction().getEndpoint() == null) {
                                Endpoint endpoint = JcaFactory.eINSTANCE.createEndpoint();
                                setFeatureValue(binding.getInboundInteraction(), "endpoint", endpoint); //$NON-NLS-1$
                            }
                            Endpoint endpoint = binding.getInboundInteraction().getEndpoint();
                            endpoint.getProperty().add(newProperty);
                        }
                    });
                } else {
                    Property newProperty = JcaFactory.eINSTANCE.createProperty();
                    newProperty.setName(dialog.getPropertyName());
                    newProperty.setValue(dialog.getPropertyValue());
                    
                    if (binding.getInboundInteraction() == null) {
                        JCAInboundInteraction inBound = JcaFactory.eINSTANCE.createJCAInboundInteraction();
                        setFeatureValue(binding, "outboundInteraction", inBound); //$NON-NLS-1$
                    }
                    if (binding.getInboundInteraction().getEndpoint() == null) {
                        Endpoint endpoint = JcaFactory.eINSTANCE.createEndpoint();
                        setFeatureValue(binding.getInboundInteraction(), "endpoint", endpoint); //$NON-NLS-1$
                    }
                    Endpoint endpoint = binding.getInboundInteraction().getEndpoint();
                    endpoint.getProperty().add(newProperty);
                }
                fireChangedEvent(this);
            }
        }
    }

    protected void clearList() {
        if (getTargetObject() instanceof JCABinding) {
            final JCABinding binding = (JCABinding) getTargetObject();
            final Endpoint endpoint = binding.getInboundInteraction().getEndpoint();
            if (endpoint.eContainer() != null) {
                TransactionalEditingDomain domain = SwitchyardSCAEditor.getActiveEditor().getEditingDomain();
                domain.getCommandStack().execute(new RecordingCommand(domain) {
                    @Override
                    protected void doExecute() {
                        endpoint.getProperty().clear();
                        System.out.println("All properties were removed"); //$NON-NLS-1$
                    }
                });
            } else {
                endpoint.getProperty().clear();
                System.out.println("All properties were removed"); //$NON-NLS-1$
            }
        }
    }

}
