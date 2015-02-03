package swingApp.app.windows.mainFrameAL;

import swingApp.app.windows.mainFrameAL.FrameAndPanelHolder;
import swingApp.app.windows.mainFrameAL.MainFrameActionListener;

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
	 * @param authentication
	 *            The {@code Authentication} to check if any {@code User} is
	 *            authenticated.
	 */
	public PostProjectAL(){
		super();
	}

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		FrameAndPanelHolder.setDialogVisible("PostProject");
	}

}