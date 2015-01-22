package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetUserFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishUsersToGetPanel;
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
			if(username.length() == 0){
				new ErrorDialog("The username field was left blank.").setVisible(true);
				return;
			}
			Command command = new GetUserFromRepo(MainFrame.getRepositories().getUsersRepo(), username);
			new SwingWorkerCommand(command, new PublishUsersToGetPanel(), new PublishToErrorDialog()).execute();
		
		} catch (IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage()).setVisible(true);
		}
		
	}

}
