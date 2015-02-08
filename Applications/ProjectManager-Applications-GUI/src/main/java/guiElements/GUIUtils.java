package guiElements;

import guiElements.mainFrameAL.FrameAndPanelHolder;
import guiElements.mainFrameAL.mainFrame.MainFrame;

/**
 * This class provides some utilitarian tools for running the GUI
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class GUIUtils {
	
	public static final FrameAndPanelHolder FRAMES = new FrameAndPanelHolder();
	private static ICommandCaller caller;
	private static MainFrame main = new MainFrame();
	
	/**
	 * @return caller  the entity that invokes the specified command
	 */
	public static ICommandCaller getCommandCaller() {
		return caller;
	}
	
	/**
	 * Method responsible for start and put visible windows and the main frame of
	 * the user interface.
	 * 
	 * @param commandCaller
	 */
	public static void initializeMainFrame(ICommandCaller commandCaller) {
		caller = commandCaller;
		main.setVisible(true);
	}

	/**
	 * Method responsible for update the login Button on the Main Frame
	 */
	public static void setLoginButton() {
		main.setLoginButton();
	}

	/**
	 * Method responsible for update the login Button on the Main Frame
	 */
	public static void setLogoutButton() {
		main.setLogoutButton();
	}
}
