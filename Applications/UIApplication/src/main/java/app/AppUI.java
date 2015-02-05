package app;

import mainFrameAL.mainFrame.MainFrame;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.repositoryHolders.RepositoryHolder;

/**
 * This is the main class.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class AppUI {
	
	/**
	 * As this is an utility class, a private constructor was implemented in order to hide the
	 * implicit public one.
	 */
	private AppUI() {
	}
	
	private static RepositoryHolder repositories = new InMemoryRepositoryHolder();
	private AppCommandFactory factory = new AppCommandFactory(repositories);
	
	public static RepositoryHolder getRepositories() {
		return repositories;
	}
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}