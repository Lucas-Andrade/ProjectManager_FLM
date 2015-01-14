package app.commands;

import javax.swing.JDialog;

import app.elements.UserInterface;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;

public class Authentication implements IAuthentication {
	
	private UserInterface authenticatedUser;
	private boolean isAuthenticated;
	private UserRepository uRepo;
	
	public Authentication(RepositoryHolder repoHolder){
		isAuthenticated = false;
		uRepo = repoHolder.getUsersRepo();
	}
	
	@Override
	public boolean isAuthenticated(){
		return isAuthenticated;
	}
	
	@Override
	public void authenticate(){
		new authenticationDialog().setVisible(true);
	}
	
	public UserInterface getAuthenticatedUser(){
		return authenticatedUser;
	}
	
	public void authenticateInternal(String name, String password){
		UserInterface user = uRepo.getUserByUsername(name);
		
		if(user != null && user.getLoginPassword().equals(password)){
			authenticatedUser = user;
			isAuthenticated = true;
		}
	}

	public class authenticationDialog extends JDialog{

		private static final long serialVersionUID = 9190969392304934338L;
		
		public authenticationDialog() {
			
		}
		
	}
}
