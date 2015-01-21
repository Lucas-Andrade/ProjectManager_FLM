package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetUserFromRepo;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetUserAL implements ActionListener {

	private JTextField parameter;
	private String username;

	public GetUserAL(JTextField parameter) {
		this.parameter = parameter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			username = parameter.getText();
			Command command = new GetUserFromRepo(MainFrame.getRepositories().getUsersRepo(), username);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog());
		
		} catch (IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage()).setVisible(true);
		}
		
	}

}
