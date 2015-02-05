package mainFrameAL;

import mainFrameAL.mainFrameAL.mainFrame.MainFrame;
import app.repositoryHolders.RepositoryHolder;

/**
 * Class responsible for calling the {@code GetWorkersInProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetWorkersInProjectAL extends MainFrameActionListener {

	/**
	 * Call to the constructor of the {@code super} class.
	 * 
	 * @param repositories
	 *            The {@code RepositoryHolder} with the {@code UserRepository}.
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public GetWorkersInProjectAL(RepositoryHolder repositories){
		super(repositories);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetWorkersInProject"));
	}

}