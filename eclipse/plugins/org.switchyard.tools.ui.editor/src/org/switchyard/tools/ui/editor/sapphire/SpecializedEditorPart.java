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
package org.switchyard.tools.ui.editor.sapphire;

import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.Status;
import org.eclipse.sapphire.ui.SapphirePart;
import org.eclipse.sapphire.ui.SapphirePartEvent;
import org.eclipse.sapphire.ui.SapphirePartListener;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.def.ISapphirePartDef;
import org.switchyard.tools.ui.editor.Activator;

/**
 * SpecializedEditorPart
 * 
 * Provides editor parts for extended element types.
 * 
 * @author Rob Cernich
 */
public class SpecializedEditorPart extends SapphirePart {

    private SapphirePart _delegate;
    private SapphirePartListener _listener = new SapphirePartListener() {

        @Override
        public void handleEvent(SapphirePartEvent event) {
            notifyListeners(event);
        }

        @Override
        public void handleValidateStateChange(Status oldValidateState, Status newValidationState) {
            updateValidationState();
        }

        @Override
        public void handleStructureChangedEvent(SapphirePartEvent event) {
            notifyStructureChangedEventListeners(event);
        }

        @Override
        public void handleFocusReceivedEvent(SapphirePartEvent event) {
            notifyFocusRecievedEventListeners();
        }
    };

    /**
     * Create a new SpecializedEditorPart.
     * 
     */
    public SpecializedEditorPart() {
    }

    @Override
    protected void init() {
        super.init();
        try {
            IModelElement modelElement = getModelElement();
            if (modelElement == null) {
                return;
            }
            String baseTypeName = getParams().get("baseType");
            if (baseTypeName == null) {
                return;
            }
            Class<?> baseType = modelElement.getClass().getClassLoader().loadClass(baseTypeName);
            ISapphirePartDef delegateDef = Activator.getDefault().getElementExtensionRegistry()
                    .getEditorPartDef(baseType, modelElement.getModelElementType().getModelElementClass());
            if (delegateDef == null) {
                return;
            }
            _delegate = SapphirePart.create(this, modelElement, delegateDef, params);
            _delegate.addListener(_listener);
        } catch (Exception e) {
            Activator
                    .getDefault()
                    .getLog()
                    .log(new org.eclipse.core.runtime.Status(org.eclipse.core.runtime.Status.ERROR,
                            Activator.PLUGIN_ID, "Error occurred initializing control.", e));
        } finally {
            updateValidationState();
        }
    }

    @Override
    protected Status computeValidationState() {
        if (_delegate == null) {
            return Status.createOkStatus();
        }
        return _delegate.getValidationState();
    }

    @Override
    public void render(SapphireRenderingContext context) {
        if (_delegate == null) {
            return;
        }
        context = new SapphireRenderingContext(this, context, context.getComposite());
        _delegate.render(context);
    }

    @Override
    public void dispose() {
        if (_delegate != null) {
            _delegate.dispose();
            _delegate = null;
        }
        super.dispose();
    }

}
