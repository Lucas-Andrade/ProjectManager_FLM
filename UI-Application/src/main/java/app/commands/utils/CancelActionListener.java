package app.commands.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class CancelActionListener implements ActionListener
{
	private JFrame frame;
	
	public CancelActionListener(JFrame frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
	}
}
