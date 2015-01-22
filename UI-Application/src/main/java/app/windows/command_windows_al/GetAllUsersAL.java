package app.windows.command_windows_al;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domain_commands.Command;
import app.domain_commands.GetAllUsersFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.main_frame_al.main_frame.MainFrame;

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
