package app.framesAndPanels.mainFrameActionListener;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostUserFrame;
import app.repositoryHolders.RepositoryHolder;

/**
 * Class responsible for calling the {@code PostUserFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PostUserAL extends MainFrameActionListener
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
	public PostUserAL(RepositoryHolder repositories,
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
		new PostUserFrame().setVisible(true);
	}

}