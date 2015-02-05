package swingApp.app.windows;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import org.PublishToMainFrame;
import org.TableBuilder;

import swingApp.app.windows.mainFrameAL.mainFrame.ErrorDialog;
import swingApp.app.windows.mainFrameAL.mainFrame.MainFrame;
import app.AppElement;
import app.elements.IUser;

/**
 * This class allows to publish information about {@code User}s in the form of
 * a {@code JTable}. To do so, the {@code AppElement}s are cast into
 * {@code IUser}s, and all relevant properties of the {@code IUser}s are
 * displayed as the table's columns.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishUsersToMainFrame extends PublishToMainFrame {

	/**
	 * Casts the array of {@code AppElement} into an array of {@code IUser}s and
	 * parses them into a {@code JTable}, using
	 * {@code TableBuilder#getTableOfUsers(IUser[])}. Each of the elements of
	 * the array will occupy a line of the {@code JTable}, and their properties
	 * are displayed in the columns. Then, the {@code JTable} is added to the
	 * {@code mainPanel} as its {@code CENTER} component. Then, the
	 * {@code mainPanel} is added to the {@code MainFrame}, and the
	 * {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see PublishToMainFrame#publish(AppElement[])
	 */
	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();

		IUser[] users = null;
		try {
			users = castToIUser(appElements);
		} catch (ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		JTable table = TableBuilder.getTableOfUsers(users);

		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
		disposeLoadingDialog();
	}
}
