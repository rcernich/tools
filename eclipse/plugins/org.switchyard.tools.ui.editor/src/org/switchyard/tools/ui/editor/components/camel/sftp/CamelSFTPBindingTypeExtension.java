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
package org.switchyard.tools.ui.editor.components.camel.sftp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.tb.IImageDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.Service;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.CamelSftpBindingType;
import org.switchyard.tools.ui.editor.IBindingTypeExtension;
import org.switchyard.tools.ui.editor.ImageProvider;
import org.switchyard.tools.ui.editor.diagram.binding.AdvancedBindingDetailsComposite;
import org.switchyard.tools.ui.editor.diagram.binding.CreateBindingFeature;
import org.switchyard.tools.ui.editor.diagram.binding.MessageComposerComposite;
import org.switchyard.tools.ui.editor.diagram.shared.IBindingComposite;

/**
 * CamelSFTPBindingTypeExtension
 * 
 * <p/>
 * Editor extension support Camel FTP bindings.
 */
public class CamelSFTPBindingTypeExtension implements IBindingTypeExtension {

    @Override
    public ICreateFeature[] newCreateFeatures(IFeatureProvider fp) {
        return new ICreateFeature[] {new CreateBindingFeature(fp, new CamelSFTPBindingFactory(), "SFTP",
                "A Camel SFTP based endpoint.", ImageProvider.IMG_16_SFTP) };
    }

    @Override
    public IImageDecorator getImageDecorator(Binding binding) {
        return new ImageDecorator(ImageProvider.IMG_16_SFTP);
    }

    @Override
    public boolean supports(Class<? extends Binding> type) {
        return CamelSftpBindingType.class.isAssignableFrom(type);
    }

    @Override
    public List<IBindingComposite> createComposites(Binding binding) {
        return createComposites(binding.eContainer() instanceof Service);
    }

    @Override
    public List<String> getRequiredCapabilities(Binding object) {
        return Collections.singletonList("org.switchyard.components:switchyard-component-camel-ftp");
    }

    @Override
    public String getTypeName(Binding object) {
        return "SFTP";
    }

    protected static List<IBindingComposite> createComposites(boolean forConsumer) {
        final List<IBindingComposite> composites = new ArrayList<IBindingComposite>(3);
        if (forConsumer) {
            composites.add(new CamelSFTPConsumerComposite());
            composites.add(new MessageComposerComposite());
            composites.add(new CamelSFTPSecurityComposite());
            composites.add(new AdvancedBindingDetailsComposite(CONSUMER_ADVANCED_PROPS));
        } else {
            composites.add(new CamelSFTPProducerComposite());
            composites.add(new MessageComposerComposite());
            composites.add(new CamelSFTPSecurityComposite());
            composites.add(new AdvancedBindingDetailsComposite(PRODUCER_ADVANCED_PROPS));
        }
        return composites;
    }

    private static final List<String> PRODUCER_ADVANCED_PROPS;
    
    static {
        PRODUCER_ADVANCED_PROPS = new ArrayList<String>();
        PRODUCER_ADVANCED_PROPS.add("bufferSize");
        PRODUCER_ADVANCED_PROPS.add("flatten");
        PRODUCER_ADVANCED_PROPS.add("charset");
        PRODUCER_ADVANCED_PROPS.add("connectTimeout");
        PRODUCER_ADVANCED_PROPS.add("disconnect");
        PRODUCER_ADVANCED_PROPS.add("maximumReconnectAttempts");
        PRODUCER_ADVANCED_PROPS.add("reconnectDelay");
        PRODUCER_ADVANCED_PROPS.add("separator");
        PRODUCER_ADVANCED_PROPS.add("stepwise");
        PRODUCER_ADVANCED_PROPS.add("throwExceptionOnConnectFailed");

        PRODUCER_ADVANCED_PROPS.add("tempPrefix");
        PRODUCER_ADVANCED_PROPS.add("tempFileName");
        PRODUCER_ADVANCED_PROPS.add("keepLastModified");
        PRODUCER_ADVANCED_PROPS.add("eagerDeleteTargetFile");
        PRODUCER_ADVANCED_PROPS.add("doneFileName");

        PRODUCER_ADVANCED_PROPS.add("execPbsz");
        PRODUCER_ADVANCED_PROPS.add("disableSecureDataChannelDefaults");
    }

    private static final List<String> CONSUMER_ADVANCED_PROPS;
    
    static {
        CONSUMER_ADVANCED_PROPS = new ArrayList<String>();
        CONSUMER_ADVANCED_PROPS.add("bufferSize");
        CONSUMER_ADVANCED_PROPS.add("flatten");
        CONSUMER_ADVANCED_PROPS.add("charset");
        CONSUMER_ADVANCED_PROPS.add("connectTimeout");
        CONSUMER_ADVANCED_PROPS.add("disconnect");
        CONSUMER_ADVANCED_PROPS.add("maximumReconnectAttempts");
        CONSUMER_ADVANCED_PROPS.add("reconnectDelay");
        CONSUMER_ADVANCED_PROPS.add("separator");
        CONSUMER_ADVANCED_PROPS.add("stepwise");
        CONSUMER_ADVANCED_PROPS.add("throwExceptionOnConnectFailed");

        CONSUMER_ADVANCED_PROPS.add("noop");
        CONSUMER_ADVANCED_PROPS.add("idempotent");
        CONSUMER_ADVANCED_PROPS.add("idempotentRepository");
        CONSUMER_ADVANCED_PROPS.add("inProgressRepository");
        CONSUMER_ADVANCED_PROPS.add("filter");
        CONSUMER_ADVANCED_PROPS.add("sorter");
        CONSUMER_ADVANCED_PROPS.add("sortBy");
        CONSUMER_ADVANCED_PROPS.add("readLock");
        CONSUMER_ADVANCED_PROPS.add("readLockTimeout");
        CONSUMER_ADVANCED_PROPS.add("readLockCheckInterval");
        CONSUMER_ADVANCED_PROPS.add("exclusiveReadLockStrategy");
        CONSUMER_ADVANCED_PROPS.add("processStrategy");
        CONSUMER_ADVANCED_PROPS.add("startingDirectoryMustExist");
        CONSUMER_ADVANCED_PROPS.add("directoryMustExist");
        CONSUMER_ADVANCED_PROPS.add("doneFileName");
        
        CONSUMER_ADVANCED_PROPS.add("knownHostsFile");
    }

}
