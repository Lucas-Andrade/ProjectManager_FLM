package app.authentication;

import java.util.concurrent.ExecutionException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import app.elements.IUser;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

public abstract class Authentication {

	private static IUser authenticatedUser;
	private static boolean isAuthenticated = false;

	public static boolean isAuthenticated(){
		return isAuthenticated;
	}

	public static IUser getAuthenticatedUser(){
		return authenticatedUser;
	}

	private static void setAuthenticatedUser(IUser user) {
		authenticatedUser = user;
		isAuthenticated = true;
	}

	public static void authenticate(JTextField[] fieldsToRetrieve, RepositoryHolder repoHolder){
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
				return (user != null) && user.getLoginPassword().equals(loginPassword) ? user : null;
			}
			
			@Override
			protected void done(){
				IUser user = null;
				
				try {
					user = get();
				} catch (InterruptedException e) {
					new ErrorDialog("Was interrupted before reaching database.").setVisible(true);
					return;
				} catch (ExecutionException e) {
					new ErrorDialog("Could not verify if the login name and password were correct.").setVisible(true);
					return;
				}
				
				if(user != null){
					setAuthenticatedUser(user);
					return;
				}
				new ErrorDialog("Login name or password do not match any known users.").setVisible(true);
			}
		}.execute();
	}

}
