package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.AppElement;
import app.elements.IUser;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

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
		disposeLoadingDialog();
	}
}
