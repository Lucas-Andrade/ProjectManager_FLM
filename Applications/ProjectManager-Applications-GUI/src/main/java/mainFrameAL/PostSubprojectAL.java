package mainFrameAL;

/**
 * Class responsible for calling the {@code PostSubprojectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class PostSubprojectAL extends MainFrameActionListener{

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		FrameAndPanelHolder.setDialogVisible("PostSubproject");
	}

}