package app.authentication;

import javax.swing.JTextField;

import app.elements.IUser;
import app.repositoryHolders.RepositoryHolder;

public interface IAuthentication
{

	public boolean isAuthenticated();

	public void authenticate(JTextField[] fieldsToRetrieve,
			Authentication authentication, RepositoryHolder repoHolder);

	public IUser getAuthenticatedUser();

}
