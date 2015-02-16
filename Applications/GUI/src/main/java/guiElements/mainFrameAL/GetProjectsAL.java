package guiElements.mainFrameAL;

import guiElements.mainFrameAL.mainFrame.MainFrame;

public class GetProjectsAL extends MainFrameActionListener {

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}

}
