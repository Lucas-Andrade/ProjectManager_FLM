package app.windows.mainFrameAL;

import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.DeleteProjectFrame;

/**
 * Class responsible for calling the {@code DeleteProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class DeleteProjectAL extends MainFrameActionListener
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
	public DeleteProjectAL(RepositoryHolder repositories)
	{
		super(repositories);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action()
	{
		new DeleteProjectFrame().setVisible(true);
	}

}