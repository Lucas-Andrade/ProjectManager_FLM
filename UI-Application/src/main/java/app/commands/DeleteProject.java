package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.DeleteProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.DeleteProjectResult;

public class DeleteProject extends BaseCommand{

	public DeleteProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new DeleteProjectFrame(new DeleteProjectResult(pane, repositories)).setVisible(true);
	}

}