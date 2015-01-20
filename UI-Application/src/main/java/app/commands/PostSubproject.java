package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostSubprojectResult;
import app.windows.commandWindowsAL.commandWindows.PostSubprojectFrame;

public class PostSubproject extends BaseCommand{

	public PostSubproject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new PostSubprojectFrame(new PostSubprojectResult(pane, repositories)).setVisible(true);
	}

}
