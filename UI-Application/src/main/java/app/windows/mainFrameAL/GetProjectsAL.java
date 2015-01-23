package app.windows.mainFrameAL;

import app.repositoryHolders.RepositoryHolder;
import app.windows.commandWindowsAL.commandWindows.GetProjectsPanel;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetProjectsAL extends MainFrameActionListener {

	public GetProjectsAL(RepositoryHolder repositories) {
		super(repositories);
	}

	@Override
	void action() {
		MainFrame.getSplitPane().setRightComponent(new GetProjectsPanel());
	}



}
