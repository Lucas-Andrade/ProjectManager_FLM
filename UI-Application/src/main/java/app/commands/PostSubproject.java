package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostSubprojectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostSubprojectResult;

public class PostSubproject extends BaseCommand{

	public PostSubproject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new PostSubprojectFrame(new PostSubprojectResult(pane, repositories)).setVisible(true);
	}

}
