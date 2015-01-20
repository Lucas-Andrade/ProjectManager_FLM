package app.windows.mainFrameAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.AuthenticationDialog;
import app.windows.commandWindowsAL.commandWindows.PatchUserFrame;
import app.windows.commandWindowsAL.commandWindows.PostProjectFrame;

/**
 * Class responsible for calling the {@code PatchUserFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PatchUserAL extends MainFrameActionListener
{

	/**
	 * Call to the constructor of the {@code super} class.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public PatchUserAL(RepositoryHolder repositories,
			Authentication authentication)
	{
		super(repositories, authentication);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action()
	{
		new PatchUserFrame().setVisible(true);
	}

}