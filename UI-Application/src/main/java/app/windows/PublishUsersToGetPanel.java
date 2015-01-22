package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.AppElement;
import app.elements.User;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

public class PublishUsersToGetPanel extends PublishToGetPanel{
	
	@Override
	public void publish(AppElement[] appElements) {
		
		User[] users = null;
		try{
			users = (User[]) appElements;
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		
		JTable table = TableBuilder.getTableOfUsers(users);
		MainGetPanel.setResults(new JScrollPane(table));
	}
}
