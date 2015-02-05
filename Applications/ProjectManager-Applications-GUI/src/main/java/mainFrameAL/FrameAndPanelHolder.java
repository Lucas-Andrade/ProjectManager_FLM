package mainFrameAL;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import commandWindowsAL.commandWindows.DeleteProjectFrame;
import commandWindowsAL.commandWindows.GetProjectsPanel;
import commandWindowsAL.commandWindows.GetSubprojectsPanel;
import commandWindowsAL.commandWindows.GetUserPanel;
import commandWindowsAL.commandWindows.GetWorkersInProjectPanel;
import commandWindowsAL.commandWindows.MainDialogFrame;
import commandWindowsAL.commandWindows.MainGetPanel;
import commandWindowsAL.commandWindows.PatchConsultantFrame;
import commandWindowsAL.commandWindows.PatchProjectFrame;
import commandWindowsAL.commandWindows.PatchUserFrame;
import commandWindowsAL.commandWindows.PostConsultantFrame;
import commandWindowsAL.commandWindows.PostProjectFrame;
import commandWindowsAL.commandWindows.PostSubprojectFrame;
import commandWindowsAL.commandWindows.PostUserFrame;
import commandWindowsAL.commandWindows.PostWorkerInProjectFrame;

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
		setNotVisibleAndReset();
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
		setNotVisibleAndReset();
		MainGetPanel panel = PANELS.get(panelName);
		panel.setFrameUser();
		lastPanel = panel;
		return panel;
	}
	
	public static MainGetPanel getLastPanel() {
		return lastPanel;
	}

	/**
	 * Sets the last used {@code MainDialogFrame} and {@code MainGetPanel}, to
	 * not visible, and resets all their text fields. 
	 */
	private static void setNotVisibleAndReset() {
		lastDialog.setVisible(false);
		lastDialog.resetAllFields();
		lastPanel.resetAllFields();
	}
	
	
}
