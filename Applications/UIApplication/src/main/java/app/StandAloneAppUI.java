package app;

import guiElements.ICommandCaller;
import guiElements.GUIUtils;

/**
 * This is the main class.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class StandAloneAppUI {
	
	/**
	 * As this is an utility class, a private constructor was implemented in order to hide the
	 * implicit public one.
	 */
	private StandAloneAppUI() {
	}
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		ICommandCaller caller = new AppCommandCaller();
		GUIUtils.initializeMainFrame(caller);
	}
	
}