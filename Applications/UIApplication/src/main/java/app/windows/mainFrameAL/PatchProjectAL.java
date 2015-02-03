package app.windows.mainFrameAL;

import repositoryHolders.RepositoryHolder;

/**
 * Class responsible for calling the {@code PatchProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PatchProjectAL extends MainFrameActionListener {

	/**
	 * Call to the constructor of the {@code super} class.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public PatchProjectAL(RepositoryHolder repositories) {
		super(repositories);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action() {
		FrameAndPanelHolder.setDialogVisible("PatchProject");
	}

}