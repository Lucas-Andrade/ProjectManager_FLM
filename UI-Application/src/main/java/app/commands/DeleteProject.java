package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.DeleteProjectResult;
import app.windows.commandWindowsAL.commandWindows.DeleteProjectFrame;

public class DeleteProject extends BaseCommand{

	public DeleteProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new DeleteProjectFrame(new DeleteProjectResult(pane, repositories)).setVisible(true);
	}

}