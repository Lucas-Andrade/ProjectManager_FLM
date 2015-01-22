package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import utils.AWorker;
import app.AppElement;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

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
