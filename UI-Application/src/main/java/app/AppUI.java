package app;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.MainFrame;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;

public class AppUI {

	private static JSplitPane splitPane = new JSplitPane();
	private static RepositoryHolder repositories = new InMemoryRepositoryHolder();
	private static Authentication authentication = new Authentication();
	
	public static void main(String[] args) {
		new MainFrame(splitPane, repositories, authentication).setVisible(true);
	}
}
