package app.windows.mainFrameAL;

import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.PostProjectFrame;

/**
 * Class responsible for calling the {@code PostProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PostProjectAL extends MainFrameActionListener{

	/**
	 * Call to the constructor of the {@code super} class.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public PostProjectAL(RepositoryHolder repositories){
		super(repositories);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		new PostProjectFrame().setVisible(true);
	}

}