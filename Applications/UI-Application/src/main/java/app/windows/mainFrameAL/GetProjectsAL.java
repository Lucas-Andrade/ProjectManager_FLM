package app.windows.mainFrameAL;

import app.repositoryHolders.RepositoryHolder;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL(RepositoryHolder repositories) {
		super(repositories);
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}



}
