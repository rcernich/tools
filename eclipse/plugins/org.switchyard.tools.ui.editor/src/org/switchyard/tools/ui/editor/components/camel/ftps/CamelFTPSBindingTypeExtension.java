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
package org.switchyard.tools.ui.editor.components.camel.ftps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.tb.IImageDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.CamelFtpsBindingType;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.FtpPackage;
import org.switchyard.tools.ui.editor.IBindingTypeExtension;
import org.switchyard.tools.ui.editor.ImageProvider;
import org.switchyard.tools.ui.editor.Messages;
import org.switchyard.tools.ui.editor.diagram.binding.AdvancedCamelBindingDetailsComposite;
import org.switchyard.tools.ui.editor.diagram.binding.CreateBindingFeature;
import org.switchyard.tools.ui.editor.diagram.binding.MessageComposerComposite;
import org.switchyard.tools.ui.editor.diagram.shared.IBindingComposite;

/**
 * CamelFTPBindingTypeExtension
 * 
 * <p/>
 * Editor extension support Camel FTP bindings.
 */
public class CamelFTPSBindingTypeExtension implements IBindingTypeExtension {

    @Override
    public ICreateFeature[] newCreateFeatures(IFeatureProvider fp) {
        return new ICreateFeature[] {new CreateBindingFeature(fp, new CamelFTPSBindingFactory(), Messages.label_ftps,
                Messages.description_tool_ftps, ImageProvider.IMG_16_FTPS) };
    }

    @Override
    public IImageDecorator getImageDecorator(Binding binding) {
        return new ImageDecorator(ImageProvider.IMG_16_FTPS);
    }

    @Override
    public boolean supports(Class<? extends Binding> type) {
        return CamelFtpsBindingType.class.isAssignableFrom(type);
    }

    @Override
    public List<IBindingComposite> createComposites(FormToolkit toolkit, Binding binding) {
        return createComposites(toolkit, binding.eContainer() instanceof Service);
    }

    @Override
    public List<String> getRequiredCapabilities(Binding object) {
        return Collections.singletonList("org.switchyard.components:switchyard-component-camel-ftp"); //$NON-NLS-1$
    }

    @Override
    public String getTypeName(Binding object) {
        return Messages.label_ftps;
    }

    protected static List<IBindingComposite> createComposites(FormToolkit toolkit, boolean forConsumer) {
        final List<IBindingComposite> composites = new ArrayList<IBindingComposite>(4);
        if (forConsumer) {
            composites.add(new CamelFTPSConsumerComposite(toolkit));
            composites.add(new CamelFTPSConsumerMoveAndPollComposite(toolkit));
            composites.add(new CamelFTPSSecurityComposite(toolkit));
            composites.add(new MessageComposerComposite(toolkit,
                    FtpPackage.Literals.BASE_CAMEL_BINDING__MESSAGE_COMPOSER,
                    FtpPackage.Literals.BASE_CAMEL_BINDING__CONTEXT_MAPPER));
            composites.add(new AdvancedCamelBindingDetailsComposite(toolkit, CONSUMER_ADVANCED_PROPS,
                    FtpPackage.eINSTANCE.getBaseCamelBinding_AdditionalUriParameters(), 
                    FtpPackage.eINSTANCE.getAdditionalUriParametersType_Parameter(), 
                    FtpPackage.eINSTANCE.getParameterType()));
        } else {
            composites.add(new CamelFTPSProducerComposite(toolkit));
            composites.add(new CamelFTPSSecurityComposite(toolkit));
            composites.add(new MessageComposerComposite(toolkit,
                    FtpPackage.Literals.BASE_CAMEL_BINDING__MESSAGE_COMPOSER,
                    FtpPackage.Literals.BASE_CAMEL_BINDING__CONTEXT_MAPPER));
            composites.add(new AdvancedCamelBindingDetailsComposite(toolkit, PRODUCER_ADVANCED_PROPS,
                    FtpPackage.eINSTANCE.getBaseCamelBinding_AdditionalUriParameters(), 
                    FtpPackage.eINSTANCE.getAdditionalUriParametersType_Parameter(), 
                    FtpPackage.eINSTANCE.getParameterType()));
        }
        return composites;
    }
    private static final List<String> PRODUCER_ADVANCED_PROPS;
    
    static {
        PRODUCER_ADVANCED_PROPS = new ArrayList<String>();
        PRODUCER_ADVANCED_PROPS.add("passiveMode"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("timeout"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("soTimeout"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("siteCommand"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("connectTimeout"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("disconnect"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("maximumReconnectAttempts"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("reconnectDelay"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("separator"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("stepwise"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("throwExceptionOnConnectFailed"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("tempPrefix"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("tempFileName"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("keepLastModified"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("eagerDeleteTargetFile"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("doneFileName"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("execPbsz"); //$NON-NLS-1$
        PRODUCER_ADVANCED_PROPS.add("disableSecureDataChannelDefaults"); //$NON-NLS-1$
    }

    private static final List<String> CONSUMER_ADVANCED_PROPS;
    
    static {
        CONSUMER_ADVANCED_PROPS = new ArrayList<String>();
        CONSUMER_ADVANCED_PROPS.add("passiveMode"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("timeout"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("soTimeout"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("siteCommand"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("connectTimeout"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("disconnect"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("maximumReconnectAttempts"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("reconnectDelay"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("separator"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("stepwise"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("throwExceptionOnConnectFailed"); //$NON-NLS-1$

        CONSUMER_ADVANCED_PROPS.add("noop"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("idempotent"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("idempotentRepository"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("inProgressRepository"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("filter"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("sorter"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("sortBy"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("readLock"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("readLockTimeout"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("readLockCheckInterval"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("exclusiveReadLockStrategy"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("processStrategy"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("startingDirectoryMustExist"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("directoryMustExist"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("doneFileName"); //$NON-NLS-1$
        
        CONSUMER_ADVANCED_PROPS.add("execPbsz"); //$NON-NLS-1$
        CONSUMER_ADVANCED_PROPS.add("disableSecureDataChannelDefaults"); //$NON-NLS-1$
    }
}
