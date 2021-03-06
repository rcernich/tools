/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_0.spring.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.switchyard.tools.models.switchyard1_0.spring.AbstractKeyManagersParametersFactoryBean;
import org.switchyard.tools.models.switchyard1_0.spring.SpringPackage;

/**
 * This is the item provider adapter for a {@link org.switchyard.tools.models.switchyard1_0.spring.AbstractKeyManagersParametersFactoryBean} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractKeyManagersParametersFactoryBeanItemProvider
    extends AbstractJsseUtilFactoryBeanItemProvider
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractKeyManagersParametersFactoryBeanItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addAlgorithmPropertyDescriptor(object);
            addKeyPasswordPropertyDescriptor(object);
            addProviderPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Algorithm feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addAlgorithmPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKeyManagersParametersFactoryBean_algorithm_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKeyManagersParametersFactoryBean_algorithm_feature", "_UI_AbstractKeyManagersParametersFactoryBean_type"),
                 SpringPackage.eINSTANCE.getAbstractKeyManagersParametersFactoryBean_Algorithm(),
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Key Password feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addKeyPasswordPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKeyManagersParametersFactoryBean_keyPassword_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKeyManagersParametersFactoryBean_keyPassword_feature", "_UI_AbstractKeyManagersParametersFactoryBean_type"),
                 SpringPackage.eINSTANCE.getAbstractKeyManagersParametersFactoryBean_KeyPassword(),
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Provider feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addProviderPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKeyManagersParametersFactoryBean_provider_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKeyManagersParametersFactoryBean_provider_feature", "_UI_AbstractKeyManagersParametersFactoryBean_type"),
                 SpringPackage.eINSTANCE.getAbstractKeyManagersParametersFactoryBean_Provider(),
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((AbstractKeyManagersParametersFactoryBean)object).getId();
        return label == null || label.length() == 0 ?
            getString("_UI_AbstractKeyManagersParametersFactoryBean_type") :
            getString("_UI_AbstractKeyManagersParametersFactoryBean_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(AbstractKeyManagersParametersFactoryBean.class)) {
            case SpringPackage.ABSTRACT_KEY_MANAGERS_PARAMETERS_FACTORY_BEAN__ALGORITHM:
            case SpringPackage.ABSTRACT_KEY_MANAGERS_PARAMETERS_FACTORY_BEAN__KEY_PASSWORD:
            case SpringPackage.ABSTRACT_KEY_MANAGERS_PARAMETERS_FACTORY_BEAN__PROVIDER:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
