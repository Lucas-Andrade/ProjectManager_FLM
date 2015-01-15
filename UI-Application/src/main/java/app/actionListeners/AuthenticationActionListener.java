package app.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		new SwingWorker<Object, Object>(){
			@Override
			protected Object doInBackground() throws Exception {
				String loginName = fieldsToRetrieve[0].getText();
				String loginPassword = fieldsToRetrieve[1].getText();
				
				IUser user = uRepo.getUserByUsername(loginName);
				
				if(user != null && user.getLoginPassword().equals(loginPassword)){
					authentication.setAuthenticatedUser(user);
				} else {
					new ErrorDialog("Login name or password do not match any known users.");
				}
				return null;
			}
		};
	}
	
}
