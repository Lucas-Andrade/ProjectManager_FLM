package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.repositoryHolders.RepositoryHolder;

/**
 * Class responsible for implementing the {@link ActionListener}s for the
 * {@link MainFrame}. Checks if there is an authenticated {@code User}, if yes
 * calls the method {@code this#action()}, if not calls the
 * {@code AuthenticationDialog}.
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
	 * The {@code Authentication} to check if any {@code User} is authenticated.
	 */
	protected Authentication authentication;

	/**
	 * The constructor for an {@code MainFramActionListener}.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public MainFrameActionListener(RepositoryHolder repositories,
			Authentication authentication)
	{
		this.repositories = repositories;
		this.authentication = authentication;
	}

	/**
	 * Checks if there is an authenticated {@code User}, if yes calls the method
	 * {@code this#action()}, if not calls the {@code AuthenticationDialog}.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (authentication.isAuthenticated())
		{
			this.action();
		} else
		{
			new AuthenticationDialog(authentication, repositories)
					.setVisible(true);
		}
	}

	/**
	 * The action to perform if an {@code User} is authenticated.
	 */
	abstract void action();

}