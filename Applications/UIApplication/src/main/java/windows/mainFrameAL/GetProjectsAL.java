package windows.mainFrameAL;

import repositoryHolders.RepositoryHolder;
import windows.mainFrameAL.mainFrame.MainFrame;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL(RepositoryHolder repositories) {
		super(repositories);
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(FrameAndPanelHolder.getPanel("GetProjects"));
	}



}
