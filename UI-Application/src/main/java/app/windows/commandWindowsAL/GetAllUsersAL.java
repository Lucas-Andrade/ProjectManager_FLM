package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domainCommands.Command;
import app.domainCommands.GetAllUsersFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for instantiating the {@code Command}
 * {@link GetAllUsersFromRepo} and for executing it in a new
 * {@link SwingWorkerCommand}. Implements {@code ActionListener}.
 * 
 * @see GetAllUsersFromRepo
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetAllUsersAL implements ActionListener {

	/**
	 * Method responsible for instantiating the {@code Command}
	 * {@link GetAllUsersFromRepo} and for executing it in a new
	 * {@link SwingWorkerCommand}. {@see
	 * ActionListener#actionPerformed(ActionEvent)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Command command = new GetAllUsersFromRepo(MainFrame.getRepositories()
				.getUsersRepo());
		new SwingWorkerCommand(command, new PublishToMainFrame(),
				new PublishToErrorDialog()).execute();
	}

}
