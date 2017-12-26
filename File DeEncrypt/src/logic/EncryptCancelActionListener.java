package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptCancelActionListener implements ActionListener {

	EncryptDialogAdaptee adaptee;
	
	public EncryptCancelActionListener(EncryptDialogAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.cancelActionPerformed();

	}

}
