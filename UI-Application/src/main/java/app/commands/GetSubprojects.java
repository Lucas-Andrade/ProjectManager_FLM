package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.GetSubprojectsFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.GetSubprojectsResult;

public class GetSubprojects extends BaseCommand{

	public GetSubprojects(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new GetSubprojectsFrame(new GetSubprojectsResult(pane, repositories)).setVisible(true);
	}

}