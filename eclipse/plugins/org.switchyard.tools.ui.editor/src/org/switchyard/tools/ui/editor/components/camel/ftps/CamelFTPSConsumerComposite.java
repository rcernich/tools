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
package org.switchyard.tools.ui.editor.components.camel.ftps;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.soa.sca.sca1_1.model.sca.Binding;
import org.eclipse.soa.sca.sca1_1.model.sca.OperationSelectorType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.CamelFtpsBindingType;
import org.switchyard.tools.models.switchyard1_0.camel.ftp.FtpFactory;
import org.switchyard.tools.models.switchyard1_0.switchyard.ContextMapperType;
import org.switchyard.tools.models.switchyard1_0.switchyard.MessageComposerType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchYardOperationSelectorType;
import org.switchyard.tools.models.switchyard1_0.switchyard.SwitchyardFactory;
import org.switchyard.tools.ui.editor.diagram.binding.AbstractSYBindingComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorComposite;
import org.switchyard.tools.ui.editor.diagram.binding.OperationSelectorUtil;
import org.switchyard.tools.ui.editor.diagram.shared.ModelOperation;
import org.switchyard.tools.ui.editor.util.PropTypeUtil;

/**
 * @author bfitzpat
 * 
 */
public class CamelFTPSConsumerComposite extends AbstractSYBindingComposite {

    private Composite _panel;
    private CamelFtpsBindingType _binding = null;
    private Text _hostText;
    private Text _portText;
    private Text _usernameText;
    private Text _pwdText;
    private Button _binaryButton;
    private Text _directoryText;
    private Button _autoCreateButton;
    private Text _fileNameText;
    private Button _deleteButton;
    private Button _recursiveButton;
    private Text _preMoveText;
    private Text _moveText;
    private Text _moveFailedText;
    private Text _includeText;
    private Text _excludeText;
    private Text _delayText;
    private TabFolder _tabFolder;
    private List<String> _advancedPropsFilterList;
    private OperationSelectorComposite _opSelectorComposite;
    private Button _isImplicitCheckbox;
    private Combo _execProtCombo;
    private Combo _securityProtocolCombo;

    @Override
    public Binding getBinding() {
        return this._binding;
    }

    @Override
    public void setBinding(Binding impl) {
        if (impl instanceof CamelFtpsBindingType) {
            this._binding = (CamelFtpsBindingType) impl;
            setInUpdate(true);
            if (this._binding.getConsume() != null) {
                if (this._binding.getConsume().isSetDelay()) {
                    setTextValue(_delayText, PropTypeUtil.getPropValueString(this._binding.getConsume().getDelay()));
                } else {
                    _delayText.setText("");
                }
                if (this._binding.getConsume().getExclude() != null) {
                    _excludeText.setText(this._binding.getConsume().getExclude());
                } else {
                    _excludeText.setText("");
                }
                if (this._binding.getConsume().getInclude() != null) {
                    _includeText.setText(this._binding.getConsume().getInclude());
                } else {
                    _includeText.setText("");
                }
                if (this._binding.getConsume().getMoveFailed() != null) {
                    _moveFailedText.setText(this._binding.getConsume().getMoveFailed());
                } else {
                    _moveFailedText.setText("");
                }
                if (this._binding.getConsume().getMove() != null) {
                    _moveText.setText(this._binding.getConsume().getMove());
                } else {
                    _moveText.setText("");
                }
                if (this._binding.getConsume().getPreMove() != null) {
                    _preMoveText.setText(this._binding.getConsume().getPreMove());
                } else {
                    _preMoveText.setText("");
                }
                _deleteButton.setSelection(this._binding.getConsume().isDelete());
                _recursiveButton.setSelection(this._binding.getConsume().isRecursive());
            }
            if (this._binding.getDirectory() != null) {
                _directoryText.setText(this._binding.getDirectory());
            } else {
                _directoryText.setText("");
            }
            if (this._binding.getFileName() != null) {
                _fileNameText.setText(this._binding.getFileName());
            } else {
                _fileNameText.setText("");
            }
            _autoCreateButton.setSelection(this._binding.isAutoCreate());
            if (this._binding.getHost() != null) {
                _hostText.setText(this._binding.getHost());
            } else {
                _hostText.setText("");
            }
            if (this._binding.isSetPort()) {
                setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
            } else {
                _portText.setText("");
            }
            if (this._binding.getUsername() != null) {
                _usernameText.setText(this._binding.getUsername());
            } else {
                _usernameText.setText("");
            }
            if (this._binding.getPassword() != null) {
                _pwdText.setText(this._binding.getPassword());
            } else {
                _pwdText.setText("");
            }
            _binaryButton.setSelection(this._binding.isBinary());
            _isImplicitCheckbox.setSelection(this._binding.isIsImplicit());
            if (this._binding.getSecurityProtocol() != null) {
                _securityProtocolCombo.setText(this._binding.getSecurityProtocol());
            } else {
                _securityProtocolCombo.setText("");
            }
            if (this._binding.getExecProt() != null) {
                _execProtCombo.setText(this._binding.getExecProt());
            } else {
                _execProtCombo.setText("");
            }
            OperationSelectorType opSelector = OperationSelectorUtil.getFirstOperationSelector(this._binding);
            _opSelectorComposite.setBinding(this._binding);
            _opSelectorComposite.setOperation((SwitchYardOperationSelectorType) opSelector);
            
            super.setTabsBinding(_binding);
            setInUpdate(false);
            validate();
        } else {
            this._binding = null;
        }
        addObservableListeners();
    }

    @Override
    public void setTargetObject(Object target) {
        super.setTargetObject(target);
        if (_opSelectorComposite != null && !_opSelectorComposite.isDisposed()) {
            _opSelectorComposite.setTargetObject((EObject) target);
        }
    }

    @Override
    protected boolean validate() {
        setErrorMessage(null);
        if (getBinding() != null) {
            if (_directoryText.getText().trim().isEmpty()) {
                setErrorMessage("Directory may not be empty.");
                return false;
            }
        }
        super.validateTabs();
        return (getErrorMessage() == null);
    }

    @Override
    public void createContents(Composite parent, int style) {
        _panel = new Composite(parent, style);
        _panel.setLayout(new FillLayout());
        if (getRootGridData() != null) {
            _panel.setLayoutData(getRootGridData());
        }

        _tabFolder = new TabFolder(_panel, SWT.NONE);

        TabItem one = new TabItem(_tabFolder, SWT.NONE);
        one.setText("Consumer");
        one.setControl(getConsumerTabControl(_tabFolder));

        TabItem two = new TabItem(_tabFolder, SWT.NONE);
        two.setText("Secure");
        two.setControl(getFTPSTabControl(_tabFolder));

        addTabs(_tabFolder);
    }
    
    private Control getFTPSTabControl(TabFolder tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);
        
        Group ftpsGroup = new Group(composite, SWT.NONE);
        ftpsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ftpsGroup.setLayout(new GridLayout(2, false));
        ftpsGroup.setText("FTPS Options");
        
        _securityProtocolCombo = createLabelAndCombo(ftpsGroup, "Security Protocol", true);
        _securityProtocolCombo.add("TLS");
        _securityProtocolCombo.add("SSL");
        
        _isImplicitCheckbox = createCheckbox(ftpsGroup, "Implicit");
        
        _execProtCombo = createLabelAndCombo(ftpsGroup, "Execution Protocol", true);
        _execProtCombo.add("C");
        _execProtCombo.add("S");
        _execProtCombo.add("E");
        _execProtCombo.add("P");
        
        return composite;
    }

    private Control getConsumerTabControl(TabFolder tabFolder) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);

        Group ftpGroup = new Group(composite, SWT.NONE);
        ftpGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ftpGroup.setLayout(new GridLayout(2, false));
        ftpGroup.setText("FTP Options");

        _hostText = createLabelAndText(ftpGroup, "Host");
        _portText = createLabelAndText(ftpGroup, "Port (Default 21)");
        _usernameText = createLabelAndText(ftpGroup, "User Name");
        _pwdText = createLabelAndText(ftpGroup, "Password");
        _pwdText.setEchoChar('*');
        _binaryButton = createCheckbox(ftpGroup, "Use Binary Transfer Mode");

        Group fileGroup = new Group(composite, SWT.NONE);
        fileGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        fileGroup.setLayout(new GridLayout(2, false));
        fileGroup.setText("File and Directory Options");

        _directoryText = createLabelAndText(fileGroup, "Directory*");
        _fileNameText = createLabelAndText(fileGroup, "File Name");
        _autoCreateButton = createCheckbox(fileGroup, "Auto Create Missing Directories in File Path");
        _includeText = createLabelAndText(fileGroup, "Include");
        _excludeText = createLabelAndText(fileGroup, "Exclude");
        _deleteButton = createCheckbox(fileGroup, "Delete Files Once Processed");
        _recursiveButton = createCheckbox(fileGroup, "Process Sub-Directories Recursively");

        _opSelectorComposite = new OperationSelectorComposite(composite, SWT.NONE);
        _opSelectorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        _opSelectorComposite.setLayout(new GridLayout(2, false));
        _opSelectorComposite.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                handleModify(_opSelectorComposite);
            }
         });

        Group moveGroup = new Group(composite, SWT.NONE);
        moveGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        moveGroup.setLayout(new GridLayout(2, false));
        moveGroup.setText("Move Options");

        _preMoveText = createLabelAndText(moveGroup, "Pre-Move");
        _moveText = createLabelAndText(moveGroup, "Move (Default .camel)");
        _moveFailedText = createLabelAndText(moveGroup, "Move Failed");

        Group pollGroup = new Group(composite, SWT.NONE);
        pollGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        pollGroup.setLayout(new GridLayout(2, false));
        pollGroup.setText("Poll Options");

        _delayText = createLabelAndText(pollGroup, "Delay Between Polls (MS) (Default 500)");

        return composite;
    }

    @Override
    public Composite getPanel() {
        return this._panel;
    }

    class ConsumeOp extends ModelOperation {
        @Override
        public void run() throws Exception {
            if (_binding != null && _binding.getConsume() == null) {
                setFeatureValue(_binding, "consume", FtpFactory.eINSTANCE.createRemoteFileConsumerType());
            }
        }
    }

    protected void updateConsumeFeature(String featureId, Object value) {
        ArrayList<ModelOperation> ops = new ArrayList<ModelOperation>();
        ops.add(new ConsumeOp());
        ops.add(new BasicOperation("consume", featureId, value));
        wrapOperation(ops);
    }

    private void handleConsumer(Control control) {
        if (control.equals(_delayText)) {
            try {
                updateConsumeFeature("delay", new BigInteger(_delayText.getText().trim()));
            } catch (NumberFormatException nfe) {
                updateConsumeFeature("delay", _delayText.getText().trim());
            }
        } else if (control.equals(_deleteButton)) {
            updateConsumeFeature("delete", new Boolean(_deleteButton.getSelection()));
        } else if (control.equals(_recursiveButton)) {
            updateConsumeFeature("recursive", new Boolean(_recursiveButton.getSelection()));
        } else if (control.equals(_excludeText)) {
            updateConsumeFeature("exclude", _excludeText.getText().trim());
        } else if (control.equals(_includeText)) {
            updateConsumeFeature("include", _includeText.getText().trim());
        } else if (control.equals(_moveFailedText)) {
            updateConsumeFeature("moveFailed", _moveFailedText.getText().trim());
        } else if (control.equals(_moveText)) {
            updateConsumeFeature("move", _moveText.getText().trim());
        } else if (control.equals(_preMoveText)) {
            updateConsumeFeature("preMove", _preMoveText.getText().trim());
        } else if (control.equals(_opSelectorComposite)) {
            int opType = _opSelectorComposite.getSelectedOperationSelectorType();
            updateOperationSelectorFeature(opType, _opSelectorComposite.getSelectedOperationSelectorValue());
        }
    }

    protected void handleModify(Control control) {
        if (control.equals(_directoryText)) {
            updateFeature(_binding, "directory", _directoryText.getText().trim());
        } else if (control.equals(_fileNameText)) {
            updateFeature(_binding, "fileName", _fileNameText.getText().trim());
        } else if (control.equals(_autoCreateButton)) {
            updateFeature(_binding, "autoCreate", new Boolean(_autoCreateButton.getSelection()));
        } else if (control.equals(_hostText)) {
            updateFeature(_binding, "host", _hostText.getText().trim());
        } else if (control.equals(_usernameText)) {
            updateFeature(_binding, "username", _usernameText.getText().trim());
        } else if (control.equals(_pwdText)) {
            updateFeature(_binding, "password", _pwdText.getText().trim());
        } else if (control.equals(_binaryButton)) {
            updateFeature(_binding, "binary", new Boolean(_binaryButton.getSelection()));
        } else if (control.equals(_isImplicitCheckbox)) {
            updateFeature(_binding, "isImplicit", new Boolean(_isImplicitCheckbox.getSelection()));
        } else if (control.equals(_securityProtocolCombo)) {
            updateFeature(_binding, "securityProtocol", _securityProtocolCombo.getText().trim());
        } else if (control.equals(_execProtCombo)) {
            updateFeature(_binding, "execProt", _execProtCombo.getText().trim());
        } else if (control.equals(_portText)) {
            try {
                int port = Integer.parseInt(_portText.getText().trim());
                updateFeature(_binding, "port", port);
            } catch (NumberFormatException nfe) {
                updateFeature(_binding, "port", _portText.getText().trim());
            }
        } else {
            handleConsumer(control);
        }
        super.handleModify(control);
        setHasChanged(false);
        setDidSomething(true);
    }

    protected void handleUndo(Control control) {
        if (_binding != null) {
            if (control.equals(_directoryText)) {
                _directoryText.setText(this._binding.getDirectory());
            } else if (control.equals(_fileNameText)) {
                _fileNameText.setText(this._binding.getFileName());
            } else if (control.equals(_autoCreateButton)) {
                _autoCreateButton.setSelection(this._binding.isAutoCreate());
            } else if (control.equals(_hostText)) {
                _hostText.setText(this._binding.getHost());
            } else if (control.equals(_portText)) {
                setTextValue(_portText, PropTypeUtil.getPropValueString(this._binding.getPort()));
            } else if (control.equals(_usernameText)) {
                _usernameText.setText(this._binding.getUsername());
            } else if (control.equals(_pwdText)) {
                _pwdText.setText(this._binding.getPassword());
            } else if (control.equals(_binaryButton)) {
                _binaryButton.setSelection(this._binding.isBinary());
            } else if (control.equals(_isImplicitCheckbox)) {
                _isImplicitCheckbox.setSelection(this._binding.isIsImplicit());
            } else if (control.equals(_securityProtocolCombo)) {
                _securityProtocolCombo.setText(this._binding.getSecurityProtocol());
            } else if (control.equals(_execProtCombo)) {
                _execProtCombo.setText(this._binding.getExecProt());
            } else if (this._binding.getConsume() != null) {
                if (control.equals(_delayText)) {
                    setTextValue(_delayText, PropTypeUtil.getPropValueString(this._binding.getConsume().getDelay()));
                } else if (control.equals(_excludeText)) {
                    _excludeText.setText(this._binding.getConsume().getExclude());
                } else if (control.equals(_includeText)) {
                    _includeText.setText(this._binding.getConsume().getInclude());
                } else if (control.equals(_moveFailedText)) {
                    _moveFailedText.setText(this._binding.getConsume().getMoveFailed());
                } else if (control.equals(_moveText)) {
                    _moveText.setText(this._binding.getConsume().getMove());
                } else if (control.equals(_preMoveText)) {
                    _preMoveText.setText(this._binding.getConsume().getPreMove());
                } else if (control.equals(_deleteButton)) {
                    _deleteButton.setSelection(this._binding.getConsume().isDelete());
                } else if (control.equals(_recursiveButton)) {
                    _recursiveButton.setSelection(this._binding.getConsume().isRecursive());
                }
            } else {
                super.handleUndo(control);
            }
        }
        setHasChanged(false);
    }

    @Override
    protected List<String> getAdvancedPropertiesFilterList() {
        if (_advancedPropsFilterList == null) {
            _advancedPropsFilterList = new ArrayList<String>();
            _advancedPropsFilterList.add("passiveMode");
            _advancedPropsFilterList.add("timeout");
            _advancedPropsFilterList.add("soTimeout");
            _advancedPropsFilterList.add("siteCommand");
            _advancedPropsFilterList.add("connectTimeout");
            _advancedPropsFilterList.add("disconnect");
            _advancedPropsFilterList.add("maximumReconnectAttempts");
            _advancedPropsFilterList.add("reconnectDelay");
            _advancedPropsFilterList.add("separator");
            _advancedPropsFilterList.add("stepwise");
            _advancedPropsFilterList.add("throwExceptionOnConnectFailed");

            _advancedPropsFilterList.add("noop");
            _advancedPropsFilterList.add("idempotent");
            _advancedPropsFilterList.add("idempotentRepository");
            _advancedPropsFilterList.add("inProgressRepository");
            _advancedPropsFilterList.add("filter");
            _advancedPropsFilterList.add("sorter");
            _advancedPropsFilterList.add("sortBy");
            _advancedPropsFilterList.add("readLock");
            _advancedPropsFilterList.add("readLockTimeout");
            _advancedPropsFilterList.add("readLockCheckInterval");
            _advancedPropsFilterList.add("exclusiveReadLockStrategy");
            _advancedPropsFilterList.add("processStrategy");
            _advancedPropsFilterList.add("startingDirectoryMustExist");
            _advancedPropsFilterList.add("directoryMustExist");
            _advancedPropsFilterList.add("doneFileName");
            
            _advancedPropsFilterList.add("execPbsz");
            _advancedPropsFilterList.add("disableSecureDataChannelDefaults");
        }
        return _advancedPropsFilterList;
    }

    @Override
    protected ContextMapperType createContextMapper() {
        return SwitchyardFactory.eINSTANCE.createContextMapperType();
    }

    @Override
    protected MessageComposerType createMessageComposer() {
        return SwitchyardFactory.eINSTANCE.createMessageComposerType();
    }
    
}
