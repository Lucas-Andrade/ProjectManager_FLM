package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.commands.exceptions.IllegalEmailException;
import app.commands.exceptions.PasswordLengthOutOfBoundsException;
import app.commands.exceptions.RepeatedUsernameException;
import app.domainCommands.AddUserToRepo;
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
		}
		
		try{
			new SwingWorkerCommand(new AddUserToRepo(MainFrame.repositories.getUsersRepo(), username, password, email, fullname));
			
			//TODO
		}catch(PasswordLengthOutOfBoundsException nfe){
			new ErrorDialog("User's password must have at least 4 characters.").setVisible(true);
		
		}catch(IllegalEmailException nfe){
			new ErrorDialog("The Email is not valid.").setVisible(true);
		 
		}catch(RepeatedUsernameException nfe){
			new ErrorDialog("The Specified Username is already being used.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
		
	}
	
	
}
