package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.PostProjectFrame;
import app.repository.ProjectsRepository;
import app.repositoryHolders.InMemoryRepositoryHolder;
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

