package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.GetWorkersInProjectFrame;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

/**
 * Class responsible for calling the {@code GetWorkersInProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetWorkersInProjectAL extends MainFrameActionListener
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
	public GetWorkersInProjectAL(RepositoryHolder repositories,
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
		new GetWorkersInProjectFrame().setVisible(true);
	}

}