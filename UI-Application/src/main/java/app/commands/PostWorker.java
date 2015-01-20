package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostWorkerResult;
import app.windows.commandWindowsAL.commandWindows.PostWorkerFrame;

public class PostWorker extends BaseCommand{
	
	public PostWorker(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PostWorkerFrame(new PostWorkerResult(pane, repositories)).setVisible(true);
	}
}

