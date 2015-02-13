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
	
	/**
	 * The host of the server.
	 */
	private static final String HOST = "localhost";
	
	/**
	 * The port where the server is expected to be waiting for requests.
	 */
	private static final String PORT = "8081";
	
	/**
	 * @return a {@code String} with the port where the server is expected to be waiting for requests.
	 */
	private static String getPort() {
		return PORT;
	}

	/**
	 * @return a {@code String} with the host where the server is expected to be waiting for requests.
	 */
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