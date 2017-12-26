package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecryptCancelActionListener implements ActionListener {

	DecryptDialogAdaptee adaptee;
	
	public DecryptCancelActionListener(DecryptDialogAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.cancelActionPerformed();

	}

}
