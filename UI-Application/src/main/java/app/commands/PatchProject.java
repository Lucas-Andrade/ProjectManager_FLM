package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PatchProjectResult;
import app.windowsAndActionListeners.commandWindowsActionListener.commandWindows.PatchProjectFrame;

public class PatchProject extends BaseCommand{

	public PatchProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PatchProjectFrame(new PatchProjectResult(pane, repositories)).setVisible(true);
	}

}