package app.windows.mainFrameAL;

import app.windows.mainFrameAL.mainFrame.MainFrame;
import repositoryHolders.RepositoryHolder;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL(RepositoryHolder repositories) {
		super(repositories);
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}



}
