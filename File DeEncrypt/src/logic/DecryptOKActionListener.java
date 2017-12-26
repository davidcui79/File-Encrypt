package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecryptOKActionListener implements ActionListener {
	
	DecryptDialogAdaptee adaptee;
	
	public DecryptOKActionListener(DecryptDialogAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.okActionPerformed();

	}

}
