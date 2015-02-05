package mainFrameAL;

import mainFrameAL.mainFrameAL.mainFrame.MainFrame;
import app.repositoryHolders.RepositoryHolder;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL(RepositoryHolder repositories) {
		super(repositories);
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}



}
