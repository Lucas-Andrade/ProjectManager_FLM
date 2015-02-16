package guiElements.mainFrameAL;

import guiElements.FrameAndPanelHolder;

/**
 * Class responsible for calling the {@code PatchUserFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PatchUserAL extends MainFrameActionListener {

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action() {
		FrameAndPanelHolder.setDialogVisible("PatchUser");
	}

}