package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostUserResult;
import app.windows.commandWindowsAL.commandWindows.PostUserFrame;

public class PostUser extends BaseCommand{

	public PostUser(JSplitPane pane, RepositoryHolder repositories,
			Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new PostUserFrame(new PostUserResult(pane, repositories)).setVisible(true);
	}

}
