package mainFrameAL;

import mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for calling the {@code GetUserPanel}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetUserAL extends MainFrameActionListener{
	
	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetUser"));
	}

}