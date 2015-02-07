package app;

import guiElements.GUIUtils;

/**
 * This is the main class.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/02/2015
 */
public class DistributedAppUI {
	
	/**
	 * As this is an utility class, a private constructor was implemented in order to hide the
	 * implicit public one.
	 */
	private DistributedAppUI() {
	}
	
	private static String HOST = "localhost";
	private final static int PORT = 9999;
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		String requestURL = "http://" + HOST + ":" + PORT;
		AppCommandCaller caller = new AppCommandCaller(requestURL);
		GUIUtils.initializeMainFrame(caller);
	}
}