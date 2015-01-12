package app.forWindow.commandsFrames;

import javax.swing.JDialog;

import app.elements.Message;

public class MessageDialog extends JDialog {
	
	public MessageDialog(Message messageToRead)
	{
		String message = messageToRead.read();
		
	}
}
