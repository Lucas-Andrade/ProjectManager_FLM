package app.commands;

import javax.swing.JSplitPane;
import app.authentication.Authentication;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

public class PostProject extends BaseCommand{
	
	public PostProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PostProjectFrame(pane, repositories).setVisible(true);
	}
}

