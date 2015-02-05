package guiElements;

import guiElements.mainFrameAL.FrameAndPanelHolder;
import guiElements.mainFrameAL.mainFrame.MainFrame;

public class GUIUtils {
	
	public static final FrameAndPanelHolder FRAMES = new FrameAndPanelHolder();
	private static ICommandCaller caller;
	private static MainFrame main = new MainFrame();
	
	public static ICommandCaller getCommandCaller() {
		return caller;
	}
	
	public static void initializeMainFrame(ICommandCaller commandCaller) {
		caller = commandCaller;
		main.setVisible(true);
	}
	
	public static void setLoginButton() {
		main.setLoginButton();
	}
	
	public static void setLogoutButton() {
		main.setLogoutButton();
	}
}
