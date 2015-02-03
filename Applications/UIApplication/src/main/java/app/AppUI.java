package app;

import app.repositoryHolders.InMemoryRepositoryHolder;
import windows.mainFrameAL.mainFrame.MainFrame;

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
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		new MainFrame(new InMemoryRepositoryHolder()).setVisible(true);
	}
}