/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3.ns.ws.policy.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.w3.ns.ws.policy.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.w3.ns.ws.policy.PolicyPackage
 * @generated
 */
public class PolicyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PolicyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolicyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PolicyPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolicySwitch<Adapter> modelSwitch =
		new PolicySwitch<Adapter>() {
			@Override
			public Adapter caseAppliesToType(AppliesToType object) {
				return createAppliesToTypeAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseOperatorContentType(OperatorContentType object) {
				return createOperatorContentTypeAdapter();
			}
			@Override
			public Adapter casePolicyAttachmentType(PolicyAttachmentType object) {
				return createPolicyAttachmentTypeAdapter();
			}
			@Override
			public Adapter casePolicyReferenceType(PolicyReferenceType object) {
				return createPolicyReferenceTypeAdapter();
			}
			@Override
			public Adapter casePolicyType(PolicyType object) {
				return createPolicyTypeAdapter();
			}
			@Override
			public Adapter caseURIType(URIType object) {
				return createURITypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.AppliesToType <em>Applies To Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.AppliesToType
	 * @generated
	 */
	public Adapter createAppliesToTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.OperatorContentType <em>Operator Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.OperatorContentType
	 * @generated
	 */
	public Adapter createOperatorContentTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.PolicyAttachmentType <em>Attachment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.PolicyAttachmentType
	 * @generated
	 */
	public Adapter createPolicyAttachmentTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.PolicyReferenceType <em>Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.PolicyReferenceType
	 * @generated
	 */
	public Adapter createPolicyReferenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.PolicyType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.PolicyType
	 * @generated
	 */
	public Adapter createPolicyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.w3.ns.ws.policy.URIType <em>URI Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.w3.ns.ws.policy.URIType
	 * @generated
	 */
	public Adapter createURITypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PolicyAdapterFactory
