package app.windows.commandWindowsAL;

import javax.swing.JTextField;

import app.domainCommands.GetUserFromRepo;
import app.repository.UserRepository;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetUserAL {

	private JTextField parameter;
	private String username;

	public GetUserAL(JTextField parameter) {
		this.parameter = parameter;
	}

	public GetUserAL(UserRepository usersRepo, JTextField userID) {
		username = parameter.getText();
		new SwingWorkerCommand(new GetUserFromRepo(MainFrame.repositories.getUsersRepo(), username), new PublishToMainFrame());
	}

}
