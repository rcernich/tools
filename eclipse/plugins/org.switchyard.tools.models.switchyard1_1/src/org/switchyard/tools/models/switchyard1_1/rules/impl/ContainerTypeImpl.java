/**
 */
package org.switchyard.tools.models.switchyard1_1.rules.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.soa.sca.sca1_1.model.sca.impl.CommonExtensionBaseImpl;

import org.switchyard.tools.models.switchyard1_1.rules.ContainerType;
import org.switchyard.tools.models.switchyard1_1.rules.RulesPackage;
import org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardFactory;
import org.switchyard.tools.models.switchyard1_1.switchyard.SwitchyardPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.rules.impl.ContainerTypeImpl#getBaseName <em>Base Name</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.rules.impl.ContainerTypeImpl#getReleaseId <em>Release Id</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.rules.impl.ContainerTypeImpl#isScan <em>Scan</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.rules.impl.ContainerTypeImpl#getScanInterval <em>Scan Interval</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.rules.impl.ContainerTypeImpl#getSessionName <em>Session Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerTypeImpl extends CommonExtensionBaseImpl implements ContainerType {
    /**
     * The default value of the '{@link #getBaseName() <em>Base Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBaseName()
     * @generated
     * @ordered
     */
    protected static final String BASE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBaseName() <em>Base Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBaseName()
     * @generated
     * @ordered
     */
    protected String baseName = BASE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getReleaseId() <em>Release Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReleaseId()
     * @generated
     * @ordered
     */
    protected static final String RELEASE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReleaseId() <em>Release Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReleaseId()
     * @generated
     * @ordered
     */
    protected String releaseId = RELEASE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #isScan() <em>Scan</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isScan()
     * @generated
     * @ordered
     */
    protected static final boolean SCAN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isScan() <em>Scan</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isScan()
     * @generated
     * @ordered
     */
    protected boolean scan = SCAN_EDEFAULT;

    /**
     * This is true if the Scan attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean scanESet;

    /**
     * The default value of the '{@link #getScanInterval() <em>Scan Interval</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScanInterval()
     * @generated
     * @ordered
     */
    protected static final Object SCAN_INTERVAL_EDEFAULT = SwitchyardFactory.eINSTANCE.createFromString(SwitchyardPackage.eINSTANCE.getPropLong(), "60000");

    /**
     * The cached value of the '{@link #getScanInterval() <em>Scan Interval</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScanInterval()
     * @generated
     * @ordered
     */
    protected Object scanInterval = SCAN_INTERVAL_EDEFAULT;

    /**
     * This is true if the Scan Interval attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean scanIntervalESet;

    /**
     * The default value of the '{@link #getSessionName() <em>Session Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSessionName()
     * @generated
     * @ordered
     */
    protected static final String SESSION_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSessionName() <em>Session Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSessionName()
     * @generated
     * @ordered
     */
    protected String sessionName = SESSION_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ContainerTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RulesPackage.Literals.CONTAINER_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBaseName(String newBaseName) {
        String oldBaseName = baseName;
        baseName = newBaseName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.CONTAINER_TYPE__BASE_NAME, oldBaseName, baseName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReleaseId() {
        return releaseId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReleaseId(String newReleaseId) {
        String oldReleaseId = releaseId;
        releaseId = newReleaseId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.CONTAINER_TYPE__RELEASE_ID, oldReleaseId, releaseId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isScan() {
        return scan;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScan(boolean newScan) {
        boolean oldScan = scan;
        scan = newScan;
        boolean oldScanESet = scanESet;
        scanESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.CONTAINER_TYPE__SCAN, oldScan, scan, !oldScanESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetScan() {
        boolean oldScan = scan;
        boolean oldScanESet = scanESet;
        scan = SCAN_EDEFAULT;
        scanESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, RulesPackage.CONTAINER_TYPE__SCAN, oldScan, SCAN_EDEFAULT, oldScanESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetScan() {
        return scanESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getScanInterval() {
        return scanInterval;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScanInterval(Object newScanInterval) {
        Object oldScanInterval = scanInterval;
        scanInterval = newScanInterval;
        boolean oldScanIntervalESet = scanIntervalESet;
        scanIntervalESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL, oldScanInterval, scanInterval, !oldScanIntervalESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetScanInterval() {
        Object oldScanInterval = scanInterval;
        boolean oldScanIntervalESet = scanIntervalESet;
        scanInterval = SCAN_INTERVAL_EDEFAULT;
        scanIntervalESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL, oldScanInterval, SCAN_INTERVAL_EDEFAULT, oldScanIntervalESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetScanInterval() {
        return scanIntervalESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSessionName(String newSessionName) {
        String oldSessionName = sessionName;
        sessionName = newSessionName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.CONTAINER_TYPE__SESSION_NAME, oldSessionName, sessionName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RulesPackage.CONTAINER_TYPE__BASE_NAME:
                return getBaseName();
            case RulesPackage.CONTAINER_TYPE__RELEASE_ID:
                return getReleaseId();
            case RulesPackage.CONTAINER_TYPE__SCAN:
                return isScan();
            case RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL:
                return getScanInterval();
            case RulesPackage.CONTAINER_TYPE__SESSION_NAME:
                return getSessionName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RulesPackage.CONTAINER_TYPE__BASE_NAME:
                setBaseName((String)newValue);
                return;
            case RulesPackage.CONTAINER_TYPE__RELEASE_ID:
                setReleaseId((String)newValue);
                return;
            case RulesPackage.CONTAINER_TYPE__SCAN:
                setScan((Boolean)newValue);
                return;
            case RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL:
                setScanInterval(newValue);
                return;
            case RulesPackage.CONTAINER_TYPE__SESSION_NAME:
                setSessionName((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case RulesPackage.CONTAINER_TYPE__BASE_NAME:
                setBaseName(BASE_NAME_EDEFAULT);
                return;
            case RulesPackage.CONTAINER_TYPE__RELEASE_ID:
                setReleaseId(RELEASE_ID_EDEFAULT);
                return;
            case RulesPackage.CONTAINER_TYPE__SCAN:
                unsetScan();
                return;
            case RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL:
                unsetScanInterval();
                return;
            case RulesPackage.CONTAINER_TYPE__SESSION_NAME:
                setSessionName(SESSION_NAME_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RulesPackage.CONTAINER_TYPE__BASE_NAME:
                return BASE_NAME_EDEFAULT == null ? baseName != null : !BASE_NAME_EDEFAULT.equals(baseName);
            case RulesPackage.CONTAINER_TYPE__RELEASE_ID:
                return RELEASE_ID_EDEFAULT == null ? releaseId != null : !RELEASE_ID_EDEFAULT.equals(releaseId);
            case RulesPackage.CONTAINER_TYPE__SCAN:
                return isSetScan();
            case RulesPackage.CONTAINER_TYPE__SCAN_INTERVAL:
                return isSetScanInterval();
            case RulesPackage.CONTAINER_TYPE__SESSION_NAME:
                return SESSION_NAME_EDEFAULT == null ? sessionName != null : !SESSION_NAME_EDEFAULT.equals(sessionName);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (baseName: ");
        result.append(baseName);
        result.append(", releaseId: ");
        result.append(releaseId);
        result.append(", scan: ");
        if (scanESet) result.append(scan); else result.append("<unset>");
        result.append(", scanInterval: ");
        if (scanIntervalESet) result.append(scanInterval); else result.append("<unset>");
        result.append(", sessionName: ");
        result.append(sessionName);
        result.append(')');
        return result.toString();
    }

} //ContainerTypeImpl
