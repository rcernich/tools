/**
 */
package org.switchyard.tools.models.switchyard1_1.camel.ftp;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Camel Sftp Binding Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getDirectory <em>Directory</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isAutoCreate <em>Auto Create</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getBufferSize <em>Buffer Size</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isFlatten <em>Flatten</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getCharset <em>Charset</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getHost <em>Host</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPort <em>Port</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getUsername <em>Username</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPassword <em>Password</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isBinary <em>Binary</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConnectTimeout <em>Connect Timeout</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isDisconnect <em>Disconnect</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getMaximumReconnectAttempts <em>Maximum Reconnect Attempts</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getReconnectDelay <em>Reconnect Delay</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getSeparator <em>Separator</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isStepwise <em>Stepwise</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isThrowExceptionOnConnectFailed <em>Throw Exception On Connect Failed</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getKnownHostsFile <em>Known Hosts File</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPrivateKeyFile <em>Private Key File</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPrivateKeyFilePassphrase <em>Private Key File Passphrase</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConsume <em>Consume</em>}</li>
 *   <li>{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getProduce <em>Produce</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType()
 * @model extendedMetaData="name='CamelSftpBindingType' kind='elementOnly'"
 * @generated
 */
public interface CamelSftpBindingType extends BaseCamelBinding {
    /**
     * Returns the value of the '<em><b>Directory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Directory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Directory</em>' attribute.
     * @see #setDirectory(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Directory()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='directory' namespace='##targetNamespace'"
     * @generated
     */
    String getDirectory();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getDirectory <em>Directory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Directory</em>' attribute.
     * @see #getDirectory()
     * @generated
     */
    void setDirectory(String value);

    /**
     * Returns the value of the '<em><b>Auto Create</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Auto Create</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Auto Create</em>' attribute.
     * @see #isSetAutoCreate()
     * @see #unsetAutoCreate()
     * @see #setAutoCreate(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_AutoCreate()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='autoCreate' namespace='##targetNamespace'"
     * @generated
     */
    boolean isAutoCreate();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isAutoCreate <em>Auto Create</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Auto Create</em>' attribute.
     * @see #isSetAutoCreate()
     * @see #unsetAutoCreate()
     * @see #isAutoCreate()
     * @generated
     */
    void setAutoCreate(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isAutoCreate <em>Auto Create</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetAutoCreate()
     * @see #isAutoCreate()
     * @see #setAutoCreate(boolean)
     * @generated
     */
    void unsetAutoCreate();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isAutoCreate <em>Auto Create</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Auto Create</em>' attribute is set.
     * @see #unsetAutoCreate()
     * @see #isAutoCreate()
     * @see #setAutoCreate(boolean)
     * @generated
     */
    boolean isSetAutoCreate();

    /**
     * Returns the value of the '<em><b>Buffer Size</b></em>' attribute.
     * The default value is <code>"131072"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Buffer Size</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Buffer Size</em>' attribute.
     * @see #isSetBufferSize()
     * @see #unsetBufferSize()
     * @see #setBufferSize(Object)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_BufferSize()
     * @model default="131072" unsettable="true" dataType="org.switchyard.tools.models.switchyard1_1.switchyard.PropInteger"
     *        extendedMetaData="kind='element' name='bufferSize' namespace='##targetNamespace'"
     * @generated
     */
    Object getBufferSize();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getBufferSize <em>Buffer Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Buffer Size</em>' attribute.
     * @see #isSetBufferSize()
     * @see #unsetBufferSize()
     * @see #getBufferSize()
     * @generated
     */
    void setBufferSize(Object value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getBufferSize <em>Buffer Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetBufferSize()
     * @see #getBufferSize()
     * @see #setBufferSize(Object)
     * @generated
     */
    void unsetBufferSize();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getBufferSize <em>Buffer Size</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Buffer Size</em>' attribute is set.
     * @see #unsetBufferSize()
     * @see #getBufferSize()
     * @see #setBufferSize(Object)
     * @generated
     */
    boolean isSetBufferSize();

    /**
     * Returns the value of the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>File Name</em>' attribute.
     * @see #setFileName(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_FileName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='fileName' namespace='##targetNamespace'"
     * @generated
     */
    String getFileName();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getFileName <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Name</em>' attribute.
     * @see #getFileName()
     * @generated
     */
    void setFileName(String value);

    /**
     * Returns the value of the '<em><b>Flatten</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flatten</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flatten</em>' attribute.
     * @see #isSetFlatten()
     * @see #unsetFlatten()
     * @see #setFlatten(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Flatten()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='flatten' namespace='##targetNamespace'"
     * @generated
     */
    boolean isFlatten();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isFlatten <em>Flatten</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Flatten</em>' attribute.
     * @see #isSetFlatten()
     * @see #unsetFlatten()
     * @see #isFlatten()
     * @generated
     */
    void setFlatten(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isFlatten <em>Flatten</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetFlatten()
     * @see #isFlatten()
     * @see #setFlatten(boolean)
     * @generated
     */
    void unsetFlatten();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isFlatten <em>Flatten</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Flatten</em>' attribute is set.
     * @see #unsetFlatten()
     * @see #isFlatten()
     * @see #setFlatten(boolean)
     * @generated
     */
    boolean isSetFlatten();

    /**
     * Returns the value of the '<em><b>Charset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Charset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Charset</em>' attribute.
     * @see #setCharset(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Charset()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='charset' namespace='##targetNamespace'"
     * @generated
     */
    String getCharset();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getCharset <em>Charset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Charset</em>' attribute.
     * @see #getCharset()
     * @generated
     */
    void setCharset(String value);

    /**
     * Returns the value of the '<em><b>Host</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Host</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Host</em>' attribute.
     * @see #setHost(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Host()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='host' namespace='##targetNamespace'"
     * @generated
     */
    String getHost();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getHost <em>Host</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Host</em>' attribute.
     * @see #getHost()
     * @generated
     */
    void setHost(String value);

    /**
     * Returns the value of the '<em><b>Port</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Port</em>' attribute.
     * @see #isSetPort()
     * @see #unsetPort()
     * @see #setPort(Object)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Port()
     * @model unsettable="true" dataType="org.switchyard.tools.models.switchyard1_1.switchyard.PropInteger"
     *        extendedMetaData="kind='element' name='port' namespace='##targetNamespace'"
     * @generated
     */
    Object getPort();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPort <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Port</em>' attribute.
     * @see #isSetPort()
     * @see #unsetPort()
     * @see #getPort()
     * @generated
     */
    void setPort(Object value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPort <em>Port</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPort()
     * @see #getPort()
     * @see #setPort(Object)
     * @generated
     */
    void unsetPort();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPort <em>Port</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Port</em>' attribute is set.
     * @see #unsetPort()
     * @see #getPort()
     * @see #setPort(Object)
     * @generated
     */
    boolean isSetPort();

    /**
     * Returns the value of the '<em><b>Username</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Username</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Username</em>' attribute.
     * @see #setUsername(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Username()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='username' namespace='##targetNamespace'"
     * @generated
     */
    String getUsername();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getUsername <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Username</em>' attribute.
     * @see #getUsername()
     * @generated
     */
    void setUsername(String value);

    /**
     * Returns the value of the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Password</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Password</em>' attribute.
     * @see #setPassword(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Password()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='password' namespace='##targetNamespace'"
     * @generated
     */
    String getPassword();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPassword <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Password</em>' attribute.
     * @see #getPassword()
     * @generated
     */
    void setPassword(String value);

    /**
     * Returns the value of the '<em><b>Binary</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binary</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Binary</em>' attribute.
     * @see #isSetBinary()
     * @see #unsetBinary()
     * @see #setBinary(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Binary()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='binary' namespace='##targetNamespace'"
     * @generated
     */
    boolean isBinary();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isBinary <em>Binary</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Binary</em>' attribute.
     * @see #isSetBinary()
     * @see #unsetBinary()
     * @see #isBinary()
     * @generated
     */
    void setBinary(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isBinary <em>Binary</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetBinary()
     * @see #isBinary()
     * @see #setBinary(boolean)
     * @generated
     */
    void unsetBinary();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isBinary <em>Binary</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Binary</em>' attribute is set.
     * @see #unsetBinary()
     * @see #isBinary()
     * @see #setBinary(boolean)
     * @generated
     */
    boolean isSetBinary();

    /**
     * Returns the value of the '<em><b>Connect Timeout</b></em>' attribute.
     * The default value is <code>"10000"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connect Timeout</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connect Timeout</em>' attribute.
     * @see #isSetConnectTimeout()
     * @see #unsetConnectTimeout()
     * @see #setConnectTimeout(Object)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_ConnectTimeout()
     * @model default="10000" unsettable="true" dataType="org.switchyard.tools.models.switchyard1_1.switchyard.PropInteger"
     *        extendedMetaData="kind='element' name='connectTimeout' namespace='##targetNamespace'"
     * @generated
     */
    Object getConnectTimeout();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConnectTimeout <em>Connect Timeout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Connect Timeout</em>' attribute.
     * @see #isSetConnectTimeout()
     * @see #unsetConnectTimeout()
     * @see #getConnectTimeout()
     * @generated
     */
    void setConnectTimeout(Object value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConnectTimeout <em>Connect Timeout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetConnectTimeout()
     * @see #getConnectTimeout()
     * @see #setConnectTimeout(Object)
     * @generated
     */
    void unsetConnectTimeout();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConnectTimeout <em>Connect Timeout</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Connect Timeout</em>' attribute is set.
     * @see #unsetConnectTimeout()
     * @see #getConnectTimeout()
     * @see #setConnectTimeout(Object)
     * @generated
     */
    boolean isSetConnectTimeout();

    /**
     * Returns the value of the '<em><b>Disconnect</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Disconnect</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Disconnect</em>' attribute.
     * @see #isSetDisconnect()
     * @see #unsetDisconnect()
     * @see #setDisconnect(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Disconnect()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='disconnect' namespace='##targetNamespace'"
     * @generated
     */
    boolean isDisconnect();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isDisconnect <em>Disconnect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Disconnect</em>' attribute.
     * @see #isSetDisconnect()
     * @see #unsetDisconnect()
     * @see #isDisconnect()
     * @generated
     */
    void setDisconnect(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isDisconnect <em>Disconnect</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDisconnect()
     * @see #isDisconnect()
     * @see #setDisconnect(boolean)
     * @generated
     */
    void unsetDisconnect();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isDisconnect <em>Disconnect</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Disconnect</em>' attribute is set.
     * @see #unsetDisconnect()
     * @see #isDisconnect()
     * @see #setDisconnect(boolean)
     * @generated
     */
    boolean isSetDisconnect();

    /**
     * Returns the value of the '<em><b>Maximum Reconnect Attempts</b></em>' attribute.
     * The default value is <code>"3"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Maximum Reconnect Attempts</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum Reconnect Attempts</em>' attribute.
     * @see #isSetMaximumReconnectAttempts()
     * @see #unsetMaximumReconnectAttempts()
     * @see #setMaximumReconnectAttempts(Object)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_MaximumReconnectAttempts()
     * @model default="3" unsettable="true" dataType="org.switchyard.tools.models.switchyard1_1.switchyard.PropInteger"
     *        extendedMetaData="kind='element' name='maximumReconnectAttempts' namespace='##targetNamespace'"
     * @generated
     */
    Object getMaximumReconnectAttempts();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getMaximumReconnectAttempts <em>Maximum Reconnect Attempts</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum Reconnect Attempts</em>' attribute.
     * @see #isSetMaximumReconnectAttempts()
     * @see #unsetMaximumReconnectAttempts()
     * @see #getMaximumReconnectAttempts()
     * @generated
     */
    void setMaximumReconnectAttempts(Object value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getMaximumReconnectAttempts <em>Maximum Reconnect Attempts</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMaximumReconnectAttempts()
     * @see #getMaximumReconnectAttempts()
     * @see #setMaximumReconnectAttempts(Object)
     * @generated
     */
    void unsetMaximumReconnectAttempts();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getMaximumReconnectAttempts <em>Maximum Reconnect Attempts</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Maximum Reconnect Attempts</em>' attribute is set.
     * @see #unsetMaximumReconnectAttempts()
     * @see #getMaximumReconnectAttempts()
     * @see #setMaximumReconnectAttempts(Object)
     * @generated
     */
    boolean isSetMaximumReconnectAttempts();

    /**
     * Returns the value of the '<em><b>Reconnect Delay</b></em>' attribute.
     * The default value is <code>"1000"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reconnect Delay</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reconnect Delay</em>' attribute.
     * @see #isSetReconnectDelay()
     * @see #unsetReconnectDelay()
     * @see #setReconnectDelay(Object)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_ReconnectDelay()
     * @model default="1000" unsettable="true" dataType="org.switchyard.tools.models.switchyard1_1.switchyard.PropInteger"
     *        extendedMetaData="kind='element' name='reconnectDelay' namespace='##targetNamespace'"
     * @generated
     */
    Object getReconnectDelay();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getReconnectDelay <em>Reconnect Delay</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reconnect Delay</em>' attribute.
     * @see #isSetReconnectDelay()
     * @see #unsetReconnectDelay()
     * @see #getReconnectDelay()
     * @generated
     */
    void setReconnectDelay(Object value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getReconnectDelay <em>Reconnect Delay</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetReconnectDelay()
     * @see #getReconnectDelay()
     * @see #setReconnectDelay(Object)
     * @generated
     */
    void unsetReconnectDelay();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getReconnectDelay <em>Reconnect Delay</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Reconnect Delay</em>' attribute is set.
     * @see #unsetReconnectDelay()
     * @see #getReconnectDelay()
     * @see #setReconnectDelay(Object)
     * @generated
     */
    boolean isSetReconnectDelay();

    /**
     * Returns the value of the '<em><b>Separator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Separator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Separator</em>' attribute.
     * @see #setSeparator(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Separator()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='separator' namespace='##targetNamespace'"
     * @generated
     */
    String getSeparator();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getSeparator <em>Separator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Separator</em>' attribute.
     * @see #getSeparator()
     * @generated
     */
    void setSeparator(String value);

    /**
     * Returns the value of the '<em><b>Stepwise</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stepwise</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stepwise</em>' attribute.
     * @see #isSetStepwise()
     * @see #unsetStepwise()
     * @see #setStepwise(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Stepwise()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='stepwise' namespace='##targetNamespace'"
     * @generated
     */
    boolean isStepwise();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isStepwise <em>Stepwise</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Stepwise</em>' attribute.
     * @see #isSetStepwise()
     * @see #unsetStepwise()
     * @see #isStepwise()
     * @generated
     */
    void setStepwise(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isStepwise <em>Stepwise</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetStepwise()
     * @see #isStepwise()
     * @see #setStepwise(boolean)
     * @generated
     */
    void unsetStepwise();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isStepwise <em>Stepwise</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Stepwise</em>' attribute is set.
     * @see #unsetStepwise()
     * @see #isStepwise()
     * @see #setStepwise(boolean)
     * @generated
     */
    boolean isSetStepwise();

    /**
     * Returns the value of the '<em><b>Throw Exception On Connect Failed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Throw Exception On Connect Failed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Throw Exception On Connect Failed</em>' attribute.
     * @see #isSetThrowExceptionOnConnectFailed()
     * @see #unsetThrowExceptionOnConnectFailed()
     * @see #setThrowExceptionOnConnectFailed(boolean)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_ThrowExceptionOnConnectFailed()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='throwExceptionOnConnectFailed' namespace='##targetNamespace'"
     * @generated
     */
    boolean isThrowExceptionOnConnectFailed();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isThrowExceptionOnConnectFailed <em>Throw Exception On Connect Failed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Throw Exception On Connect Failed</em>' attribute.
     * @see #isSetThrowExceptionOnConnectFailed()
     * @see #unsetThrowExceptionOnConnectFailed()
     * @see #isThrowExceptionOnConnectFailed()
     * @generated
     */
    void setThrowExceptionOnConnectFailed(boolean value);

    /**
     * Unsets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isThrowExceptionOnConnectFailed <em>Throw Exception On Connect Failed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetThrowExceptionOnConnectFailed()
     * @see #isThrowExceptionOnConnectFailed()
     * @see #setThrowExceptionOnConnectFailed(boolean)
     * @generated
     */
    void unsetThrowExceptionOnConnectFailed();

    /**
     * Returns whether the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#isThrowExceptionOnConnectFailed <em>Throw Exception On Connect Failed</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Throw Exception On Connect Failed</em>' attribute is set.
     * @see #unsetThrowExceptionOnConnectFailed()
     * @see #isThrowExceptionOnConnectFailed()
     * @see #setThrowExceptionOnConnectFailed(boolean)
     * @generated
     */
    boolean isSetThrowExceptionOnConnectFailed();

    /**
     * Returns the value of the '<em><b>Known Hosts File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Known Hosts File</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Known Hosts File</em>' attribute.
     * @see #setKnownHostsFile(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_KnownHostsFile()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='knownHostsFile' namespace='##targetNamespace'"
     * @generated
     */
    String getKnownHostsFile();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getKnownHostsFile <em>Known Hosts File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Known Hosts File</em>' attribute.
     * @see #getKnownHostsFile()
     * @generated
     */
    void setKnownHostsFile(String value);

    /**
     * Returns the value of the '<em><b>Private Key File</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Private Key File</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Private Key File</em>' attribute.
     * @see #setPrivateKeyFile(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_PrivateKeyFile()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='privateKeyFile' namespace='##targetNamespace'"
     * @generated
     */
    String getPrivateKeyFile();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPrivateKeyFile <em>Private Key File</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Private Key File</em>' attribute.
     * @see #getPrivateKeyFile()
     * @generated
     */
    void setPrivateKeyFile(String value);

    /**
     * Returns the value of the '<em><b>Private Key File Passphrase</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Private Key File Passphrase</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Private Key File Passphrase</em>' attribute.
     * @see #setPrivateKeyFilePassphrase(String)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_PrivateKeyFilePassphrase()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='privateKeyFilePassphrase' namespace='##targetNamespace'"
     * @generated
     */
    String getPrivateKeyFilePassphrase();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getPrivateKeyFilePassphrase <em>Private Key File Passphrase</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Private Key File Passphrase</em>' attribute.
     * @see #getPrivateKeyFilePassphrase()
     * @generated
     */
    void setPrivateKeyFilePassphrase(String value);

    /**
     * Returns the value of the '<em><b>Consume</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Consume</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Consume</em>' containment reference.
     * @see #setConsume(RemoteFileConsumerType)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Consume()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='consume' namespace='##targetNamespace'"
     * @generated
     */
    RemoteFileConsumerType getConsume();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getConsume <em>Consume</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Consume</em>' containment reference.
     * @see #getConsume()
     * @generated
     */
    void setConsume(RemoteFileConsumerType value);

    /**
     * Returns the value of the '<em><b>Produce</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Produce</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Produce</em>' containment reference.
     * @see #setProduce(RemoteFileProducerType)
     * @see org.switchyard.tools.models.switchyard1_1.camel.ftp.FtpPackage#getCamelSftpBindingType_Produce()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='produce' namespace='##targetNamespace'"
     * @generated
     */
    RemoteFileProducerType getProduce();

    /**
     * Sets the value of the '{@link org.switchyard.tools.models.switchyard1_1.camel.ftp.CamelSftpBindingType#getProduce <em>Produce</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Produce</em>' containment reference.
     * @see #getProduce()
     * @generated
     */
    void setProduce(RemoteFileProducerType value);

} // CamelSftpBindingType
