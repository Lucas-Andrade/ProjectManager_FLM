package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddUserToRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class NewUserAL implements ActionListener{

	private JTextField[] textfields;
	private String username;
	private String password;
	private String email;
	private String fullname;
	
	public NewUserAL(JTextField[] fields){
		this.textfields = fields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			username = textfields[0].getText();
			password = textfields[1].getText();
			email = textfields[2].getText();
			fullname = textfields[3].getText();
		
		}catch (NullPointerException npe){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(username.length() == 0 || password.length() == 0 || email.length() == 0){
			new ErrorDialog("At least one required field was left blank.").setVisible(true);
			return;
		}
		
		try{
			Command command = new AddUserToRepo(MainFrame.getRepositories().getUsersRepo(), username, password, email, fullname);
			new SwingWorkerCommand(command , new PublishToMainFrame(), new PublishToErrorDialog());
			
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}		
}
