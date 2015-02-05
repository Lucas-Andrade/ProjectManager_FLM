package guiElements.mainFrameAL;

import guiElements.mainFrameAL.mainFrame.MainFrame;

/**
 * Class responsible for calling the {@code GetWorkersInProjectFrame}. Extends
 * {@link MainFrameActionListener}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class GetWorkersInProjectAL extends MainFrameActionListener {

	/**
	 * @see super{@link #action()}
	 */
	@Override
	void action(){
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetWorkersInProject"));
	}

}