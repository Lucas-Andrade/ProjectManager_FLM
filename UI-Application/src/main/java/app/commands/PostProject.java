package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostProjectResult;

public class PostProject extends BaseCommand{
	
	public PostProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PostProjectFrame(new PostProjectResult(pane, repositories)).setVisible(true);
	}
}

