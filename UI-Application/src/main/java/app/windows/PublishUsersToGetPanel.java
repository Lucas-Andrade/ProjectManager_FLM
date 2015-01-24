package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.AppElement;
import app.elements.IUser;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Allows to publish information about {@code User}s 
 * indirectly to the {@code MainFrame}. This class presents that information as
 * a {@code JTable}, and then adds it to the appropriate panel in the
 * {@code MainGetPanel}.
 * 
 * To do so, the {@code AppElement}s are cast into {@code IUser}s, and all
 * relevant properties of the {@code IUser}s are displayed as the table's
 * columns.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishUsersToGetPanel extends PublishToGetPanel{
	
	/**
	 * Casts the array of {@code AppElement} into an array of {@code IUser}s
	 * and parses them into a {@code JTable}, using
	 * {@code TableBuilder#getTableOfUsers(IUser[])}. Each of the elements
	 * of the array will occupy a line of the {@code JTable}, and their
	 * properties are displayed in the columns. This {@code JTable} will be
	 * added to the {@code mainPanel} as its {@code CENTER} component. Then, the
	 * {@code mainPanel} is added to the {@code MainFrame}, and the
	 * {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see ResultsPublisherWithLoadingDialog#publish(AppElement[])
	 */
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
