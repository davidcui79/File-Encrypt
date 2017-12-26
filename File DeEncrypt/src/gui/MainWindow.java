package gui;

import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import logic.AboutActionListener;
import logic.DecryptActionListener;
import logic.EncryptActionListener;
import logic.FileExitActionListener;
import logic.FileOpenActionListener;
import logic.MainFrameAdaptee;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SaharaSkin;

public class MainWindow extends JFrame implements MainFrameAdaptee {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar mainMenu = null;

	private JMenu fileMenu = null;

	private JMenuItem exitMenuItem = null;

	private JLabel labelFile = null;

	private JTextField txtFieldSrcFile = null;

	private JButton btnBrowseSrcFile = null;

	private JButton btnEncrypt = null;

	private JButton btnDecrypt = null;

	File srcFile = null;

	File tgtFile = null;

	private JLabel labelDstFile = null;

	private JTextField txtFileTargetFile = null;

	private JButton btnBrowseTgtFile = null;

	private JMenu helpMenu = null;

	private JMenuItem aboutMenuItem = null;

	/**
	 * This is the default constructor
	 */
	public MainWindow() {
		super();
		initialize();
	}
	
	public static void initGlobalFontSetting(Font fnt){
		FontUIResource fontRes = new FontUIResource(fnt);
		for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
		Object key = keys.nextElement();
		Object value = UIManager.get(key);
		if(value instanceof FontUIResource)
		UIManager.put(key, fontRes);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(480, 200);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resource/lock32.png")));
		this.setJMenuBar(getMainMenu());
		this.setContentPane(getJContentPane());
		this.setTitle("File DeEncryption");
		getBtnEncrypt().setEnabled(false);
		getBtnDecrypt().setEnabled(false);
		addWindowListener(new MainWindowAdapter(this));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 2;
			gridBagConstraints31.gridy = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 1;
			labelDstFile = new JLabel();
			labelDstFile.setText("Target File:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints3.gridy = 3;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			labelFile = new JLabel();
			labelFile.setText("Source File");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(labelFile, gridBagConstraints);
			jContentPane.add(getTxtFieldSrcFile(), gridBagConstraints1);
			jContentPane.add(getBtnBrowseSrcFile(), gridBagConstraints11);
			jContentPane.add(labelDstFile, gridBagConstraints12);
			jContentPane.add(getBtnEncrypt(), gridBagConstraints2);
			jContentPane.add(getBtnDecrypt(), gridBagConstraints3);
			jContentPane.add(getTxtFieldTargetFile(), gridBagConstraints21);
			jContentPane.add(getBtnBrowseTgtFile(), gridBagConstraints31);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenu
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new JMenuBar();
			mainMenu.add(getFileMenu());
			mainMenu.add(getHelpMenu());
		}
		return mainMenu;
	}

	/**
	 * This method initializes fileMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes exitMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.setActionCommand("Exit");
			exitMenuItem.addActionListener(new FileExitActionListener(this));

		}
		return exitMenuItem;
	}

	/**
	 * This method initializes txtFieldFile
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFieldSrcFile() {
		if (txtFieldSrcFile == null) {
			txtFieldSrcFile = new JTextField();
			txtFieldSrcFile.setEditable(false);
		}
		return txtFieldSrcFile;
	}

	/**
	 * This method initializes btnBrowse
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnBrowseSrcFile() {
		if (btnBrowseSrcFile == null) {
			btnBrowseSrcFile = new JButton();
			btnBrowseSrcFile.setText("Browse");
			btnBrowseSrcFile
					.addActionListener(new FileOpenActionListener(this));
		}
		return btnBrowseSrcFile;
	}

	/**
	 * This method initializes btnEncrypt
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEncrypt() {
		if (btnEncrypt == null) {
			btnEncrypt = new JButton();
			btnEncrypt.setText("Encrypt");
			btnEncrypt.addActionListener(new EncryptActionListener(this));
		}
		return btnEncrypt;
	}

	/**
	 * This method initializes btnDecrypt
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDecrypt() {
		if (btnDecrypt == null) {
			btnDecrypt = new JButton();
			btnDecrypt.setText("Decrypt");
			btnDecrypt.addActionListener(new DecryptActionListener(this));
		}
		return btnDecrypt;
	}

	/**
	 * This method initializes txtFileTargetFile
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFieldTargetFile() {
		if (txtFileTargetFile == null) {
			txtFileTargetFile = new JTextField();
			txtFileTargetFile.setEditable(false);
		}
		return txtFileTargetFile;
	}

	/**
	 * This method initializes btnBrowseTgtFile
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnBrowseTgtFile() {
		if (btnBrowseTgtFile == null) {
			btnBrowseTgtFile = new JButton();
			btnBrowseTgtFile.setText("Browse");
			btnBrowseTgtFile
					.addActionListener(new FileOpenActionListener(this));
		}
		return btnBrowseTgtFile;
	}

	/**
	 * This method initializes helpMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes aboutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new AboutActionListener(this));
		}
		return aboutMenuItem;
	}

	public static void main(String[] args){
		/*
		 * necessary way to correctly display Chinese char
		 */
		initGlobalFontSetting(new Font("Dialog",Font.PLAIN,14)); 
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		/*
		 * necessary way to run substance stuff...
		 */
		SwingUtilities.invokeLater(new Runnable() 
		{
          public void run() {
			  SubstanceLookAndFeel.setSkin(new SaharaSkin());
			  MainWindow app = new MainWindow();
			  app.setSize(480, 280);
			  app.setVisible(true);
			  Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
			  app.setLocation(screenSize.width/2 - app.getWidth()/2, screenSize.height/2 - app.getHeight()/2);
          }
		});
	}

	public void fileOpenActionPerformed(ActionEvent event) {
		/*
		 * open source file
		 */
		if (event.getSource() == getBtnBrowseSrcFile()) {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				srcFile = chooser.getSelectedFile();
				getTxtFieldSrcFile().setText(
						srcFile.getAbsoluteFile().toString());
			}
		} else if (event.getSource() == getBtnBrowseTgtFile()) {
			/*
			 * save to a target file
			 */
			JFileChooser chooser;
			/*
			 * set the initial directory to the source file to be more user friendly
			 */
			if(srcFile != null){
				File currDir = srcFile.getAbsoluteFile();
				chooser = new JFileChooser(currDir);
			}
			else{
				chooser = new JFileChooser();
			}
			 
			/*
			 * realized in the decrypt case don't want to specify the ext
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Encrypted /ile", "ecf");
			chooser.setFileFilter(filter);
			*/
			int returnVal = chooser.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				tgtFile = chooser.getSelectedFile();
				
				/*
				 * target file cannot be the same to source file
				 */
				if(tgtFile.equals(srcFile)){
					JOptionPane.showMessageDialog(null, "Target file cannot be the source file!","Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				getTxtFieldTargetFile().setText(
						tgtFile.getAbsoluteFile().toString());
			}
		}
		if (srcFile != null && tgtFile != null) {
			getBtnEncrypt().setEnabled(true);
			getBtnDecrypt().setEnabled(true);
		}
	}

	public void encryptActionPerformed() {
		/*
		 * Object[] possibleValues = { "First", "Second", "Third" }; Object
		 * selectedValue = JOptionPane.showInputDialog(null, "Choose one",
		 * "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
		 * possibleValues[0]);
		 */
		EncryptDialog dialog = new EncryptDialog(srcFile, tgtFile, this);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	public void decryptActionPerformed() {
		DecryptDialog dialog = new DecryptDialog(srcFile, tgtFile, this);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

	}

	public void windowClosing() {
		System.exit(0);
		
	}

	public void aboutActionPerformed() {
		AboutDialog about = new AboutDialog(this);
		about.setLocationRelativeTo(this);
		about.setVisible(true);
	}

}
