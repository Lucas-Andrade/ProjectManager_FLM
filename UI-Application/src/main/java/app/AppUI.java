package app;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * This is the main class.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class AppUI {
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		new MainFrame(new InMemoryRepositoryHolder()).setVisible(true);
	}
}