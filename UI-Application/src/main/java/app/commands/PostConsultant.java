package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.PostConsultantFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.PostConsultantResult;

public class PostConsultant extends BaseCommand{
	
	public PostConsultant(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PostConsultantFrame(new PostConsultantResult(pane, repositories)).setVisible(true);
	}
}

