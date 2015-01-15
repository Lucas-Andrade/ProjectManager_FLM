package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.elements.IUser;
import app.framesAndPanels.AuthenticationDialog;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;

public class Authentication implements IAuthentication {
	
	private static IUser authenticatedUser;
	private static boolean isAuthenticated;
	private UserRepository uRepo;
	
	public Authentication(RepositoryHolder repoHolder){
		isAuthenticated = true;
		uRepo = repoHolder.getUsersRepo();
	}
	
	@Override
	public boolean isAuthenticated(){
		return isAuthenticated;
	}
	
	@Override
	public void authenticate(){
		new AuthenticationDialog().setVisible(true);
	}
	
	public IUser getAuthenticatedUser(){
		return authenticatedUser;
	}
	
	public static class AuthenticateActionListener implements ActionListener{
		
		IUser user;
		String password;
		
		public AuthenticateActionListener(String name, String password){
			//this.user = uRepo.getUserByUsername(name);
			this.password = password;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(user != null && user.getLoginPassword().equals(password)){
				authenticatedUser = user;
				isAuthenticated = true;
			}
		}
	}


}
