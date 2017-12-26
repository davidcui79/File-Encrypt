package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecryptActionListener implements ActionListener {

	MainFrameAdaptee adaptee;
	
	public DecryptActionListener(MainFrameAdaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		adaptee.decryptActionPerformed();

	}

}
