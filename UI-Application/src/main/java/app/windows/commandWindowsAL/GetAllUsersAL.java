package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domainCommands.Command;
import app.domainCommands.GetAllUsersFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetAllUsersAL implements ActionListener {


	@Override
	public void actionPerformed(ActionEvent e) {

		Command command = new GetAllUsersFromRepo(MainFrame.getRepositories().getUsersRepo());
		new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();


	}

}
