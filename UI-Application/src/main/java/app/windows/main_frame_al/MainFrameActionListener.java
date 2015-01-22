package app.windows.main_frame_al;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.repository_holders.RepositoryHolder;
import app.windows.command_windows_al.command_windows.AuthenticationDialog;

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