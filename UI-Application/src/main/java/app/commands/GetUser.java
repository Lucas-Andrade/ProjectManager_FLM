package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.GetUserPanel;
import app.repositoryHolders.RepositoryHolder;
import app.result.GetUserResult;

public class GetUser extends BaseCommand{

	public GetUser(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new GetUserPanel(new GetUserResult(pane, repositories)).setVisible(true);
	}

}