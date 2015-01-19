package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PatchUserFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.PatchUserResult;

public class PatchUser extends BaseCommand{

	public PatchUser(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PatchUserFrame(new PatchUserResult(pane, repositories)).setVisible(true);
	}

}