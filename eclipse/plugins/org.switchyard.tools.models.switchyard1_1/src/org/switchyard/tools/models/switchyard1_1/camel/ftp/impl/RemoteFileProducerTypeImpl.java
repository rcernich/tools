/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.ftp.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage;
import org.switchyard.tools.models.switchyard1_1.camel.ftp.RemoteFileProducerType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Remote File Producer Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#getFileExist <em>File Exist</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#getTempPrefix <em>Temp Prefix</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#getTempFileName <em>Temp File Name</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#isKeepLastModified <em>Keep Last Modified</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#isEagerDeleteTargetFile <em>Eager Delete Target File</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.impl.RemoteFileProducerTypeImpl#getDoneFileName <em>Done File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RemoteFileProducerTypeImpl extends EObjectImpl implements RemoteFileProducerType {
    /**
     * The default value of the '{@link #getFileExist() <em>File Exist</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileExist()
     * @generated
     * @ordered
     */
    protected static final String FILE_EXIST_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getFileExist() <em>File Exist</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFileExist()
     * @generated
     * @ordered
     */
    protected String fileExist = FILE_EXIST_EDEFAULT;
    /**
     * The default value of the '{@link #getTempPrefix() <em>Temp Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTempPrefix()
     * @generated
     * @ordered
     */
    protected static final String TEMP_PREFIX_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getTempPrefix() <em>Temp Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTempPrefix()
     * @generated
     * @ordered
     */
    protected String tempPrefix = TEMP_PREFIX_EDEFAULT;
    /**
     * The default value of the '{@link #getTempFileName() <em>Temp File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTempFileName()
     * @generated
     * @ordered
     */
    protected static final String TEMP_FILE_NAME_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getTempFileName() <em>Temp File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTempFileName()
     * @generated
     * @ordered
     */
    protected String tempFileName = TEMP_FILE_NAME_EDEFAULT;
    /**
     * The default value of the '{@link #isKeepLastModified() <em>Keep Last Modified</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isKeepLastModified()
     * @generated
     * @ordered
     */
    protected static final boolean KEEP_LAST_MODIFIED_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isKeepLastModified() <em>Keep Last Modified</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isKeepLastModified()
     * @generated
     * @ordered
     */
    protected boolean keepLastModified = KEEP_LAST_MODIFIED_EDEFAULT;
    /**
     * This is true if the Keep Last Modified attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean keepLastModifiedESet;
    /**
     * The default value of the '{@link #isEagerDeleteTargetFile() <em>Eager Delete Target File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEagerDeleteTargetFile()
     * @generated
     * @ordered
     */
    protected static final boolean EAGER_DELETE_TARGET_FILE_EDEFAULT = true;
    /**
     * The cached value of the '{@link #isEagerDeleteTargetFile() <em>Eager Delete Target File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEagerDeleteTargetFile()
     * @generated
     * @ordered
     */
    protected boolean eagerDeleteTargetFile = EAGER_DELETE_TARGET_FILE_EDEFAULT;
    /**
     * This is true if the Eager Delete Target File attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean eagerDeleteTargetFileESet;
    /**
     * The default value of the '{@link #getDoneFileName() <em>Done File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoneFileName()
     * @generated
     * @ordered
     */
    protected static final String DONE_FILE_NAME_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getDoneFileName() <em>Done File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoneFileName()
     * @generated
     * @ordered
     */
    protected String doneFileName = DONE_FILE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RemoteFileProducerTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FtpPackage.Literals.REMOTE_FILE_PRODUCER_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFileExist() {
        return fileExist;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFileExist(String newFileExist) {
        String oldFileExist = fileExist;
        fileExist = newFileExist;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__FILE_EXIST, oldFileExist, fileExist));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTempPrefix() {
        return tempPrefix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTempPrefix(String newTempPrefix) {
        String oldTempPrefix = tempPrefix;
        tempPrefix = newTempPrefix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_PREFIX, oldTempPrefix, tempPrefix));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTempFileName() {
        return tempFileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTempFileName(String newTempFileName) {
        String oldTempFileName = tempFileName;
        tempFileName = newTempFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_FILE_NAME, oldTempFileName, tempFileName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isKeepLastModified() {
        return keepLastModified;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKeepLastModified(boolean newKeepLastModified) {
        boolean oldKeepLastModified = keepLastModified;
        keepLastModified = newKeepLastModified;
        boolean oldKeepLastModifiedESet = keepLastModifiedESet;
        keepLastModifiedESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED, oldKeepLastModified, keepLastModified, !oldKeepLastModifiedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetKeepLastModified() {
        boolean oldKeepLastModified = keepLastModified;
        boolean oldKeepLastModifiedESet = keepLastModifiedESet;
        keepLastModified = KEEP_LAST_MODIFIED_EDEFAULT;
        keepLastModifiedESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED, oldKeepLastModified, KEEP_LAST_MODIFIED_EDEFAULT, oldKeepLastModifiedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetKeepLastModified() {
        return keepLastModifiedESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEagerDeleteTargetFile() {
        return eagerDeleteTargetFile;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEagerDeleteTargetFile(boolean newEagerDeleteTargetFile) {
        boolean oldEagerDeleteTargetFile = eagerDeleteTargetFile;
        eagerDeleteTargetFile = newEagerDeleteTargetFile;
        boolean oldEagerDeleteTargetFileESet = eagerDeleteTargetFileESet;
        eagerDeleteTargetFileESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE, oldEagerDeleteTargetFile, eagerDeleteTargetFile, !oldEagerDeleteTargetFileESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetEagerDeleteTargetFile() {
        boolean oldEagerDeleteTargetFile = eagerDeleteTargetFile;
        boolean oldEagerDeleteTargetFileESet = eagerDeleteTargetFileESet;
        eagerDeleteTargetFile = EAGER_DELETE_TARGET_FILE_EDEFAULT;
        eagerDeleteTargetFileESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE, oldEagerDeleteTargetFile, EAGER_DELETE_TARGET_FILE_EDEFAULT, oldEagerDeleteTargetFileESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetEagerDeleteTargetFile() {
        return eagerDeleteTargetFileESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDoneFileName() {
        return doneFileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDoneFileName(String newDoneFileName) {
        String oldDoneFileName = doneFileName;
        doneFileName = newDoneFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FtpPackage.REMOTE_FILE_PRODUCER_TYPE__DONE_FILE_NAME, oldDoneFileName, doneFileName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__FILE_EXIST:
                return getFileExist();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_PREFIX:
                return getTempPrefix();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_FILE_NAME:
                return getTempFileName();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED:
                return isKeepLastModified();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE:
                return isEagerDeleteTargetFile();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__DONE_FILE_NAME:
                return getDoneFileName();
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
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__FILE_EXIST:
                setFileExist((String)newValue);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_PREFIX:
                setTempPrefix((String)newValue);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_FILE_NAME:
                setTempFileName((String)newValue);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED:
                setKeepLastModified((Boolean)newValue);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE:
                setEagerDeleteTargetFile((Boolean)newValue);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__DONE_FILE_NAME:
                setDoneFileName((String)newValue);
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
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__FILE_EXIST:
                setFileExist(FILE_EXIST_EDEFAULT);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_PREFIX:
                setTempPrefix(TEMP_PREFIX_EDEFAULT);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_FILE_NAME:
                setTempFileName(TEMP_FILE_NAME_EDEFAULT);
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED:
                unsetKeepLastModified();
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE:
                unsetEagerDeleteTargetFile();
                return;
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__DONE_FILE_NAME:
                setDoneFileName(DONE_FILE_NAME_EDEFAULT);
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
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__FILE_EXIST:
                return FILE_EXIST_EDEFAULT == null ? fileExist != null : !FILE_EXIST_EDEFAULT.equals(fileExist);
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_PREFIX:
                return TEMP_PREFIX_EDEFAULT == null ? tempPrefix != null : !TEMP_PREFIX_EDEFAULT.equals(tempPrefix);
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__TEMP_FILE_NAME:
                return TEMP_FILE_NAME_EDEFAULT == null ? tempFileName != null : !TEMP_FILE_NAME_EDEFAULT.equals(tempFileName);
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__KEEP_LAST_MODIFIED:
                return isSetKeepLastModified();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__EAGER_DELETE_TARGET_FILE:
                return isSetEagerDeleteTargetFile();
            case FtpPackage.REMOTE_FILE_PRODUCER_TYPE__DONE_FILE_NAME:
                return DONE_FILE_NAME_EDEFAULT == null ? doneFileName != null : !DONE_FILE_NAME_EDEFAULT.equals(doneFileName);
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
        result.append(" (fileExist: ");
        result.append(fileExist);
        result.append(", tempPrefix: ");
        result.append(tempPrefix);
        result.append(", tempFileName: ");
        result.append(tempFileName);
        result.append(", keepLastModified: ");
        if (keepLastModifiedESet) result.append(keepLastModified); else result.append("<unset>");
        result.append(", eagerDeleteTargetFile: ");
        if (eagerDeleteTargetFileESet) result.append(eagerDeleteTargetFile); else result.append("<unset>");
        result.append(", doneFileName: ");
        result.append(doneFileName);
        result.append(')');
        return result.toString();
    }

} //RemoteFileProducerTypeImpl
