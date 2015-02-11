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
	
	private static final String HOST = "localhost";
	private static final int PORT = 8081;
	
	
	private static int getPort() {
		return PORT;
	}

	private static String getHost() {
		return HOST;
	}
	
	
	
	
	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) {
		
		AppCommandCaller caller = new AppCommandCaller(getHost(), getPort());
		GUIUtils.initializeMainFrame(caller);
	}

	
}