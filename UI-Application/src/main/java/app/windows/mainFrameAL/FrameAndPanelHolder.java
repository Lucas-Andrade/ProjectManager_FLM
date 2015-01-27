package app.windows.mainFrameAL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;

import app.windows.commandWindowsAL.commandWindows.DeleteProjectFrame;
import app.windows.commandWindowsAL.commandWindows.GetProjectsPanel;
import app.windows.commandWindowsAL.commandWindows.GetSubprojectsPanel;
import app.windows.commandWindowsAL.commandWindows.GetUserPanel;
import app.windows.commandWindowsAL.commandWindows.GetWorkersInProjectPanel;
import app.windows.commandWindowsAL.commandWindows.MainDialogFrame;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;
import app.windows.commandWindowsAL.commandWindows.PatchConsultantFrame;
import app.windows.commandWindowsAL.commandWindows.PatchProjectFrame;
import app.windows.commandWindowsAL.commandWindows.PatchUserFrame;
import app.windows.commandWindowsAL.commandWindows.PostConsultantFrame;
import app.windows.commandWindowsAL.commandWindows.PostProjectFrame;
import app.windows.commandWindowsAL.commandWindows.PostSubprojectFrame;
import app.windows.commandWindowsAL.commandWindows.PostUserFrame;
import app.windows.commandWindowsAL.commandWindows.PostWorkerInProjectFrame;

public class FrameAndPanelHolder {
	
	private static final Map<String, MainDialogFrame> DIALOGS = new HashMap<String, MainDialogFrame>();
	private static final Map<String, MainGetPanel> PANELS = new HashMap<String, MainGetPanel>();
	private static Set<Entry<String, MainDialogFrame>> dialogsSet; 
	private static Set<Entry<String, MainGetPanel>> panelsSet; 
	
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
		dialogsSet = DIALOGS.entrySet();
		
		PANELS.put("GetWorkersInProject", new GetWorkersInProjectPanel());
		PANELS.put("GetUser", new GetUserPanel());
		PANELS.put("GetSubprojects", new GetSubprojectsPanel());
		PANELS.put("GetProjects", new GetProjectsPanel());
		
		panelsSet = PANELS.entrySet();
	}
	
	public static void setDialogVisible(String dialogName) {
		setAllNotVisible();
		MainDialogFrame dialog = DIALOGS.get(dialogName);
		dialog.setFrameUser();
		dialog.pack();
		dialog.setVisible(true);
	}

	public static JPanel getPanel(String panelName) {
		setAllNotVisible();
		MainGetPanel panel = PANELS.get(panelName);
		panel.setFrameUser();
		return panel;
	}
	
	private static void setAllNotVisible() {
		Iterator<Entry<String, MainDialogFrame>> dialogsIterator = dialogsSet.iterator();
		while(dialogsIterator.hasNext() ){
			MainDialogFrame frame = dialogsIterator.next().getValue();
			if(frame.isVisible()) {
				frame.setVisible(false);
				frame.resetAllFields();
			}
		}
		
		Iterator<Entry<String, MainGetPanel>> panelsIterator = panelsSet.iterator();
		while(panelsIterator.hasNext()) {
			panelsIterator.next().getValue().resetAllFields();
		}
	}
}
