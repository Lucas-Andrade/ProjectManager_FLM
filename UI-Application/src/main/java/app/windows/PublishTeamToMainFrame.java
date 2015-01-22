package app.windows;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import utils.AWorker;
import app.AppElement;
import app.windows.main_frame_al.main_frame.ErrorDialog;
import app.windows.main_frame_al.main_frame.MainFrame;

public class PublishTeamToMainFrame extends PublishToMainFrame {

	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();
		
		AWorker[] workers = null;
		try{
			workers = castToAWorker(appElements);
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		JTable table = TableBuilder.getTableOfWorkers(workers);
		
		mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		statusLabel.setText("Status: Ready");
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
	}
}
