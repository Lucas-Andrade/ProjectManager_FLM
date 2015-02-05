package swingApp.app.windows;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;
import guiElements.mainFrameAL.mainFrame.MainFrame;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import utils.AWorker;
import app.AppElement;
import app.publisher.TableBuilder;

/**
 * This class allows to publish information about a {@code Project}'s
 * {@code Team} members, in the form of a {@code JTable}. To do so, the
 * {@code AppElement}s are cast into {@code AWorker}s, and all relevant
 * properties of the {@code AWorker}s are displayed as the table's columns.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishTeamToMainFrame extends PublishToMainFrame {

	/**
	 * Casts the array of {@code AppElement} into an array of {@code AWorker}s
	 * and parses them into a {@code JTable}, using
	 * {@code TableBuilder#getTableOfWorkers(AWorker[])}. Each of the elements
	 * of the array will occupy a line of the {@code JTable}, and their
	 * properties are displayed in the columns. Then, the {@code JTable} is
	 * added to the {@code mainPanel} as its {@code CENTER} component. Then, the
	 * {@code mainPanel} is added to the {@code MainFrame}, and the
	 * {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see PublishToMainFrame#publish(AppElement[])
	 */
	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();

		AWorker[] workers = null;
		try {
			workers = castToAWorker(appElements);
		} catch (ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		JTable table = TableBuilder.getTableOfWorkers(workers);

		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
		disposeLoadingDialog();
	}
}
