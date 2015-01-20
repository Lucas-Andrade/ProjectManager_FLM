package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostProjectResult;
import app.windowsAndActionListeners.commandWindowsActionListener.commandWindows.PostProjectFrame;

public class PostProject extends BaseCommand{
	
	public PostProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PostProjectFrame(new PostProjectResult(pane, repositories)).setVisible(true);
	}
}

