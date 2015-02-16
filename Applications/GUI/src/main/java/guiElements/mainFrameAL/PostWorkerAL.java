package guiElements.mainFrameAL;

/**
 * Class responsible for calling the {@code PostWorkerFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PostWorkerAL extends MainFrameActionListener{

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		FrameAndPanelHolder.setDialogVisible("PostWorkerInProject");
	}

}