package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domainCommands.GetAllUsersFromRepo;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetAllUsersAL implements ActionListener {


	@Override
	public void actionPerformed(ActionEvent e) {
		new SwingWorkerCommand(new GetAllUsersFromRepo(MainFrame.getRepositories().getUsersRepo()), new PublishToMainFrame());

	}

}
