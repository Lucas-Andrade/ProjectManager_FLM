package app.framesAndPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelActionListener implements ActionListener{
	private JDialog frame;
	
	public CancelActionListener(JDialog frame){
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
	}
}
