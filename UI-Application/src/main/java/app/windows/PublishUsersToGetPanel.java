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
		
		JTable table = constructTableOfUsers(users);
		MainGetPanel.setResults(new JScrollPane(table));
	}
	
	private JTable constructTableOfUsers(User[] users) {
		
		String[] columnNames = {"Username", "Email", "Full name"};
		String[][] data = new String[users.length][3];
		for(int i = 0; i < users.length; i++) {
			User user = users[i];
			data[i] = new String[]{user.getLoginName(), user.getEmail(), user.getFullName()};
		}
		
		return new JTable(data, columnNames);
	}
}
