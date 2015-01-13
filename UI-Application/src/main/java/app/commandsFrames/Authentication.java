package app.commandsFrames;

import javax.swing.JDialog;

import app.RepositoryHolders.RepositoryHolder;
import app.elements.UserInterface;
import app.repository.UserRepository;

public class Authentication {
	
	private String loginName;
	private String loginPassword;
	private boolean isAuthenticated;
	private UserRepository uRepo;
	
	public Authentication(RepositoryHolder repoHolder)
	{
		isAuthenticated = false;
		uRepo = repoHolder.getUsersRepo();
	}
	
	public boolean isAuthenticated()
	{
		return isAuthenticated;
	}
	
	public void authenticate()
	{
		new authenticationDialog().setVisible(true);
	}
	
	public void authenticateInternal(String name, String password)
	{
		UserInterface user = uRepo.getUserByUsername(loginName);
		
		if(user != null && user.getLoginPassword().equals(password))
		{
			loginName = name;
			loginPassword = password;
			isAuthenticated = true;
		}
	}
	

	public class authenticationDialog extends JDialog
	{
		
	}
}
