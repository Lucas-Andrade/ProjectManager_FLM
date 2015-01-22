package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.AppElement;
import app.elements.IUser;
import app.windows.command_windows_al.command_windows.MainGetPanel;
import app.windows.main_frame_al.main_frame.ErrorDialog;

public class PublishUsersToGetPanel extends PublishToGetPanel{
	
	@Override
	public void publish(AppElement[] appElements) {
		
		IUser[] users = null;
		try{
			users = castToIUser(appElements);
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		
		JTable table = TableBuilder.getTableOfUsers(users);
		MainGetPanel.setResults(new JScrollPane(table));
	}
}
