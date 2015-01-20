package app.windowsAndActionListeners.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.windowsAndActionListeners.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.windowsAndActionListeners.commandWindowsActionListener.commandWindows.GetSubprojectsFrame;
import app.windowsAndActionListeners.commandWindowsActionListener.commandWindows.PostProjectFrame;

/**
 * Class responsible for calling the {@code GetSubprojectsFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetSubprojectsAL extends MainFrameActionListener
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
	public GetSubprojectsAL(RepositoryHolder repositories,
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
		new GetSubprojectsFrame().setVisible(true);
	}

}