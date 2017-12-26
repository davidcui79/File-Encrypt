package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptOKActionListener implements ActionListener {

	EncryptDialogAdaptee adaptee;
	
	public EncryptOKActionListener(EncryptDialogAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.okActionPerformed();

	}

}
