package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.AWorker;
import app.AppElement;
import app.windows.command_windows_al.command_windows.MainGetPanel;
import app.windows.main_frame_al.main_frame.ErrorDialog;

public class PublishTeamToGetPanel extends PublishToGetPanel{

	@Override
	public void publish(AppElement[] appElements) {
		
		AWorker[] workers = null;
		try{
			workers = castToAWorker(appElements);
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		
		JTable table = TableBuilder.getTableOfWorkers(workers);
		MainGetPanel.setResults(new JScrollPane(table));
	}
}
