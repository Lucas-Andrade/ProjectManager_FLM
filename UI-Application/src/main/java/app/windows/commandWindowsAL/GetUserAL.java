package app.windows.commandWindowsAL;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetUserFromRepo;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.repository.UserRepository;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetUserAL {

	private JTextField parameter;
	private String username;

	public GetUserAL(JTextField parameter) {
		this.parameter = parameter;
	}

	public GetUserAL(UserRepository usersRepo, JTextField userID) {
		try{
			username = parameter.getText();
			Command comand = new GetUserFromRepo(MainFrame.getRepositories().getUsersRepo(), username);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog());
		
		} catch (IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage()).setVisible(true);
		}
		
	}

}
