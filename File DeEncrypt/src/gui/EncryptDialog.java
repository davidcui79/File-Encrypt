package gui;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;
import java.io.File;

import logic.EncryptCancelActionListener;
import logic.EncryptDialogAdaptee;
import logic.EncryptOKActionListener;
import logic.FileBroker;

public class EncryptDialog extends JDialog implements EncryptDialogAdaptee{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel labelPwd = null;

	private JPasswordField pwdField = null;

	private JLabel labelCnfPwd = null;

	private JPasswordField cnfPwdField = null;

	private JButton btnOK = null;

	private JButton btnCancel = null;
	
	private File srcFile = null;
	
	private File tgtFile = null;

	private JLabel labelLenReq = null;

	/**
	 * @param owner
	 */
	public EncryptDialog(File src, File tgt, Frame owner) {
		//set modal=true in super()
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
		this.setTitle("Encrypt Password");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints11.gridy = 0;
			labelLenReq = new JLabel();
			labelLenReq.setText("(Password must be 8 - 16 character)");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints5.insets = new Insets(20, 30, 0, 0);
			gridBagConstraints5.gridy = 5;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints4.gridy = 5;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints2.gridy = 2;
			labelCnfPwd = new JLabel();
			labelCnfPwd.setText("Confirm Password:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridy = 1;
			labelPwd = new JLabel();
			labelPwd.setText("Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(labelPwd, gridBagConstraints);
			jContentPane.add(getPwdField(), gridBagConstraints1);
			jContentPane.add(labelCnfPwd, gridBagConstraints2);
			jContentPane.add(getCnfPwdField(), gridBagConstraints3);
			jContentPane.add(getBtnOK(), gridBagConstraints4);
			jContentPane.add(getBtnCancel(), gridBagConstraints5);
			jContentPane.add(labelLenReq, gridBagConstraints11);
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
	 * This method initializes cnfPwdField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getCnfPwdField() {
		if (cnfPwdField == null) {
			cnfPwdField = new JPasswordField();
		}
		return cnfPwdField;
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
			btnOK.addActionListener(new EncryptOKActionListener(this));
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
			btnCancel.addActionListener(new EncryptCancelActionListener(this));
		}
		return btnCancel;
	}

	public void cancelActionPerformed() {
		//nothing happened
		dispose();
	}

	public void okActionPerformed() {
		String pwd = new String(getPwdField().getPassword());
		String cnfPwd = new String(getCnfPwdField().getPassword());
		
		if(pwd.length() < 8 || pwd.length() > 16){
			JOptionPane.showMessageDialog(null, "Passwords must be 8 - 16 character!","Error", JOptionPane.ERROR_MESSAGE);
			getPwdField().setText("");
			getCnfPwdField().setText("");
			return;
		}
		
		if(pwd.equals(cnfPwd)){
			/*
			 * test if the target file can be written
			 */
			/*
			 * JAVA BUG, canWrite() will return false on user folder
			 */
			/*
			if(!tgtFile.canWrite()){
				JOptionPane.showMessageDialog(null, "Target file cannot be written!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			*/
			
			//ok to go
			FileBroker fb = new FileBroker(srcFile, tgtFile);
			fb.encrypt(pwd);
			dispose();
		}
		else{
			//passwords don't match
			JOptionPane.showMessageDialog(null, "Entered passwords not identical!","Error", JOptionPane.ERROR_MESSAGE);
			getPwdField().setText("");
			getCnfPwdField().setText("");
		}
		
	}
	
	

}
