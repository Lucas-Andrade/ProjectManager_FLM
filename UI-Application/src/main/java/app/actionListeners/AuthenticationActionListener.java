package app.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import app.authentication.Authentication;
import app.elements.IUser;
import app.framesAndPanels.ErrorDialog;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;

public class AuthenticationActionListener implements ActionListener{
	
	Authentication authentication;
	JTextField[] fieldsToRetrieve;
	UserRepository uRepo;
	
	public AuthenticationActionListener(Authentication authentication, JTextField[] fieldsToRetrieve, 
			RepositoryHolder repoHolder) {
		this.authentication = authentication;
		this.fieldsToRetrieve = fieldsToRetrieve;
		this.uRepo = repoHolder.getUsersRepo();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new SwingWorker<IUser, Object>(){
			@Override
			protected IUser doInBackground() throws Exception {
				
				String loginName = fieldsToRetrieve[0].getText();
				char[] loginPasswordChars = ((JPasswordField)fieldsToRetrieve[1]).getPassword();
				StringBuilder builder = new StringBuilder();
				
				for(char passChar : loginPasswordChars){
					builder.append(passChar);
				}
					
				String loginPassword = builder.toString();
				IUser user = uRepo.getUserByUsername(loginName);
				
				return (user != null) && user.getLoginPassword().equals(loginPassword) ? user : null;
			}
			
			@Override
			protected void done(){
				IUser user = null;
				
				try {
					user = get();
				} catch (InterruptedException e) {
					new ErrorDialog("Was interrupted before reaching database.").setVisible(true);
				} catch (ExecutionException e) {
					new ErrorDialog("Could not verify if the login name and password were correct.").setVisible(true);
				}
				
				if(user != null){
					new ErrorDialog("merdas.").setVisible(true);
					authentication.setAuthenticatedUser(user);
				} else {
					new ErrorDialog("Login name or password do not match any known users.").setVisible(true);
				}
			}
		};
	}
	
}
