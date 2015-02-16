package guiElements;

import guiElements.commandWindowsAL.commandWindows.DeleteProjectFrame;
import guiElements.commandWindowsAL.commandWindows.GetProjectsPanel;
import guiElements.commandWindowsAL.commandWindows.GetSubprojectsPanel;
import guiElements.commandWindowsAL.commandWindows.GetUserPanel;
import guiElements.commandWindowsAL.commandWindows.GetWorkersInProjectPanel;
import guiElements.commandWindowsAL.commandWindows.MainDialogFrame;
import guiElements.commandWindowsAL.commandWindows.MainGetPanel;
import guiElements.commandWindowsAL.commandWindows.PatchConsultantFrame;
import guiElements.commandWindowsAL.commandWindows.PatchProjectFrame;
import guiElements.commandWindowsAL.commandWindows.PatchUserFrame;
import guiElements.commandWindowsAL.commandWindows.PostConsultantFrame;
import guiElements.commandWindowsAL.commandWindows.PostProjectFrame;
import guiElements.commandWindowsAL.commandWindows.PostSubprojectFrame;
import guiElements.commandWindowsAL.commandWindows.PostUserFrame;
import guiElements.commandWindowsAL.commandWindows.PostWorkerInProjectFrame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * This class instantiates all {@code MainDialogFrame} and {@code MainGetPanel} that the {@code MainFrame}
 * may need during its lifetime. This class also has the responsibility to hide and reset any 
 * {@code MainDialogFrame} when another {@code MainDialogFrame} or {@code MainGetPanel} is requested by 
 * any of the {@code MainFrame}'s {@code ActionListener}s. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 27/01/2015
 *
 */
public class FrameAndPanelHolder {
	
	private static final Map<String, MainDialogFrame> DIALOGS = new HashMap<String, MainDialogFrame>();
	private static final Map<String, MainGetPanel> PANELS = new HashMap<String, MainGetPanel>();
	
	private static MainDialogFrame lastDialog;
	private static MainGetPanel lastPanel;
	
	/**
	 * Constructs a new {@code FrameAndPanelHolder}, instantiates all the {@code MainDialogFrame}s 
	 * and {@code MainGetPanel}s, and puts them in the respective {@code Map}s.
	 */
	public FrameAndPanelHolder() {
		
		DIALOGS.put("PostWorkerInProject", new PostWorkerInProjectFrame());
		DIALOGS.put("DeleteProject", new DeleteProjectFrame());
		DIALOGS.put("PatchConsultant", new PatchConsultantFrame());
		DIALOGS.put("PatchProject", new PatchProjectFrame());
		DIALOGS.put("PatchUser", new PatchUserFrame());
		DIALOGS.put("PostConsultant", new PostConsultantFrame());
		DIALOGS.put("PostProject", new PostProjectFrame());
		DIALOGS.put("PostSubproject", new PostSubprojectFrame());
		DIALOGS.put("PostUser", new PostUserFrame());
		
		PANELS.put("GetWorkersInProject", new GetWorkersInProjectPanel());
		PANELS.put("GetUser", new GetUserPanel());
		PANELS.put("GetSubprojects", new GetSubprojectsPanel());
		PANELS.put("GetProjects", new GetProjectsPanel());
		
		lastDialog = new PatchUserFrame(); //to avoid using ifs or null pointer exceptions
		lastPanel = new GetUserPanel();
	}
	
	/**
	 * Sets the {@code MainDialogFrame} visible. They are searched for by name:
	 * 
	 * "PostWorkerInProject" corresponds to a {@code PostWorkerInProjectFrame}
	 * "DeleteProject" corresponds to a {@code DeleteProjectFrame}
	 * "PatchConsultant" corresponds to a {@code PatchConsultantFrame}
	 * "PatchProject" corresponds to a {@code PatchProjectFrame}
	 * "PatchUser" corresponds to a {@code PatchUserFrame}
	 * "PostConsultant" corresponds to a {@code PostConsultantFrame}
	 * "PostProject" corresponds to a {@code PostProjectFrame}
	 * "PostSubproject" corresponds to a {@code PostSubprojectFrame}
	 * "PostUser" corresponds to a {@code PostUserFrame}
	 * 
	 * @param dialogName 
	 * 				The name of the dialog to be set visible.
	 */
	public static void setDialogVisible(String dialogName) {
		setNotVisible();
		resetFields();
		MainDialogFrame dialog = DIALOGS.get(dialogName);
		dialog.setFrameUser();
		dialog.pack();
		dialog.setVisible(true);
		lastDialog = dialog;
	}

	/**
	 * Gets a {@code MainGetPanel}. They are searched for by name:
	 * 
	 * "GetWorkersInProject" corresponds to a {@code GetWorkersInProjectPanel}
	 * "GetUser" corresponds to a {@code GetUserPanel}
	 * "GetSubprojects" corresponds to a {@code GetSubprojectsPanel}
	 * "GetProjects" corresponds to a {@code GetProjectsPanel}
	 * 
	 * @param panelName
	 * 			The name of the panel.
	 * @return The requested {@code JPanel}
	 */
	public static JPanel getPanel(String panelName) {
		setNotVisible();
		resetFields();
		MainGetPanel panel = PANELS.get(panelName);
		panel.setFrameUser();
		lastPanel = panel;
		return panel;
	}
	
	public static MainGetPanel getLastPanel() {
		return lastPanel;
	}

	/**
	 * Sets the last used {@code MainDialogFrame} to not visible
	 */
	private static void setNotVisible() {
		lastDialog.setVisible(false);
	}
	
	/**
	 * Resets all the fields of the last used {@code MainDialogFrame} and {@code MainGetPanel}, to
	 * not visible, and resets all their text fields. 
	 */
	public static void resetFields() {
		lastDialog.resetAllFields();
		lastPanel.resetAllFields();
		lastPanel.clearResults();
	}
	
	
}
