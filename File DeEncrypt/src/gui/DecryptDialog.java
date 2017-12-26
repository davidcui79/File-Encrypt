package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JDialog;

import logic.DESDecrypt;
import logic.DecryptCancelActionListener;
import logic.DecryptDialogAdaptee;
import logic.DecryptOKActionListener;
import logic.FileBroker;

public class DecryptDialog extends JDialog implements DecryptDialogAdaptee{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel labelPwd = null;

	private JPasswordField pwdField = null;

	private JButton btnOK = null;

	private JButton btnCancel = null;
	
	File srcFile;
	
	File tgtFile;

	/**
	 * @param owner
	 */
	public DecryptDialog(File src, File tgt, Frame owner) {
		//set modal to true
		super(owner, true);
		srcFile = src;
		tgtFile = tgt;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(360, 200);
		this.setTitle("Decrypt Password");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(10, 10, 0, 0);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridx = 2;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.insets = new Insets(10, 50, 0, 0);
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.gridx = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.gridy = 0;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridwidth = 2;
			gridBagConstraints21.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.gridx = 0;
			labelPwd = new JLabel();
			labelPwd.setText("Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(labelPwd, gridBagConstraints11);
			jContentPane.add(getPwdField(), gridBagConstraints21);
			jContentPane.add(getBtnOK(), gridBagConstraints31);
			jContentPane.add(getBtnCancel(), gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pwdField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPwdField() {
		if (pwdField == null) {
			pwdField = new JPasswordField();
		}
		return pwdField;
	}

	/**
	 * This method initializes btnOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton();
			btnOK.setText("OK");
			btnOK.addActionListener(new DecryptOKActionListener(this));
		}
		return btnOK;
	}

	/**
	 * This method initializes btnCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(new DecryptCancelActionListener(this));
		}
		return btnCancel;
	}

	public void cancelActionPerformed() {
		dispose();
		
	}

	public void okActionPerformed() {
		DESDecrypt decrypt = new DESDecrypt();
		String pwd = new String(getPwdField().getPassword());
		decrypt.initialize(pwd);
		FileBroker fb = new FileBroker(srcFile, tgtFile);
		fb.decrypt(pwd);
		dispose();
	}

}
