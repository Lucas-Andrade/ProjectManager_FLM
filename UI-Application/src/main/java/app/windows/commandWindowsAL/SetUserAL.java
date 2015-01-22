package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.SetUserPropertiesFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class SetUserAL implements ActionListener {

	private JTextField[] textFields;
	private String userName;
	private String oldPassword;
	private String newPassword;
	private String validateNewPass;

	public SetUserAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			userName = textFields[0].getText();
			oldPassword = textFields[1].getText();
			newPassword = textFields[2].getText();
			validateNewPass = textFields[3].getText();
			
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(userName.length() == 0 || oldPassword.length() == 0 || newPassword.length() == 0 || validateNewPass.length() == 0 ){
			new ErrorDialog("At least one required field was left blank.").setVisible(true);
			return;
		}
		
		if (!(newPassword==validateNewPass)){
			new ErrorDialog("New Passord and confirmation Password do not match").setVisible(true);
			return;
		}
		
		try{
			Command command = new SetUserPropertiesFromRepo(MainFrame.getRepositories().getUsersRepo(), userName, oldPassword, newPassword);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage()).setVisible(true);
		}
	}
}
