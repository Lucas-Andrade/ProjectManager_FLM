package swingApp.app.windows.mainFrameAL;

import swingApp.app.windows.mainFrameAL.mainFrame.MainFrame;
import swingApp.app.windows.mainFrameAL.FrameAndPanelHolder;
import swingApp.app.windows.mainFrameAL.MainFrameActionListener;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL() {
		super();
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}



}
