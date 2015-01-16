package app.authentication;

import java.util.concurrent.ExecutionException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import app.elements.IUser;
import app.framesAndPanels.ErrorDialog;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;

public class Authentication implements IAuthentication {
	
	private static IUser authenticatedUser;
	private static boolean isAuthenticated;
	
	public Authentication(){
		isAuthenticated = true; //TODO trocar para false
	}
	
	@Override
	public boolean isAuthenticated(){
		return isAuthenticated;
	}
	
	@Override
	public void authenticate(){
		isAuthenticated = true;
	}
	
	public IUser getAuthenticatedUser(){
		return authenticatedUser;
	}
	
	public void setAuthenticatedUser(IUser user) {
		authenticatedUser = user;
		authenticate();
	}
	
	public static void authenticate(JTextField[] fieldsToRetrieve, Authentication authentication, RepositoryHolder repoHolder){
		
		System.out.println("a seguir é o swing worker");
		new SwingWorker<IUser, Object>(){
			
			UserRepository uRepo = repoHolder.getUsersRepo();
			
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
				System.out.println("loginName: " + loginName);
				System.out.println("password: " +loginPassword);
				System.out.println("o swing worker acabou");
				return (user != null) && user.getLoginPassword().equals(loginPassword) ? user : null;
			}
			
			@Override
			protected void done(){
				IUser user = null;
				
				try {
					user = get();
					System.out.println("user: " + user.toString());
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
