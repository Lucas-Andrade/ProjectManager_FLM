package app.windows;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import app.AppElement;
import app.elements.IUser;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishUsersToMainFrame extends PublishToMainFrame{
	
	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();
		
		IUser[] users = null;
		try{
			users = castToIUser(appElements);
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		JTable table = TableBuilder.getTableOfUsers(users);
		
		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		statusLabel.setText("Status: Ready");
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
	}
}
