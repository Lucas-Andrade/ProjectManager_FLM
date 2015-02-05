package app;

import guiElements.GUIUtils;

/**
 * This is the main class.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/02/2015
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
		AppCommandCaller caller = new AppCommandCaller();
		GUIUtils.initializeMainFrame(caller);
	}
}