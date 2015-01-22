package app.windows.mainFrameAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.AuthenticationDialog;

/**
 * Class responsible for implementing the {@link ActionListener}s for the
 * {@link MainFrame}. Checks if there is an authenticated {@code User} in
 * {@code Authentication}, if yes calls the method {@code this#action()}, if not
 * calls the {@code AuthenticationDialog}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class MainFrameActionListener implements ActionListener
{

	/**
	 * The {@code RepositoryHolder} with the {@code UserRepository}.
	 */
	protected RepositoryHolder repositories;

	/**
	 * The constructor for an {@code MainFramActionListener}.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 */
	public MainFrameActionListener(RepositoryHolder repositories)
	{
		this.repositories = repositories;
	}

	/**
	 * Checks if there is an authenticated {@code User} in
	 * {@code Authentication}, if yes calls the method {@code this#action()}, if
	 * not calls the {@code AuthenticationDialog}.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Authentication.isAuthenticated())
		{
			this.action();
		} else
		{
			new AuthenticationDialog(repositories).setVisible(true);
		}
	}

	/**
	 * The action to perform if an {@code User} is authenticated.
	 */
	abstract void action();

}