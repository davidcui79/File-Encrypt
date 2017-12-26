package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptActionListener implements ActionListener {
	MainFrameAdaptee adaptee;
	
	public EncryptActionListener(MainFrameAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.encryptActionPerformed();

	}

}
