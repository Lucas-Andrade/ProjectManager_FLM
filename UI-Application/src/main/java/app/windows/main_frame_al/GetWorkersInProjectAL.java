package app.windows.main_frame_al;

import app.repository_holders.RepositoryHolder;
import app.windows.command_windows_al.command_windows.GetWorkersInProjectPanel;
import app.windows.main_frame_al.main_frame.MainFrame;

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
	public GetWorkersInProjectAL(RepositoryHolder repositories){
		super(repositories);
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		MainFrame.getSplitPane().setRightComponent(new GetWorkersInProjectPanel());
	}

}