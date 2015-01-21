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
			workers = (AWorker[]) appElements;
		} catch(ClassCastException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		
		JTable table = constructTableOfWorkers(workers);
		MainGetPanel.setResults(new JScrollPane(table));
	}
	
	private JTable constructTableOfWorkers(AWorker[] workers) {
		
		String[] columnNames = {"Name", "Consultant ID", "Cost per hour", "Hours worked", "Total cost"};
		String[][] data = new String[workers.length][5];
		for(int i = 0; i < workers.length; i++) {
			AWorker worker = workers[i];
			data[i] = new String[]{worker.getName(), String.valueOf(worker.getCID()), 
					String.valueOf(worker.getCostPerHour()), String.valueOf(worker.getWorkerHours()), 
					String.valueOf(worker.getCost())};
		}
		
		return new JTable(data, columnNames);
	}
}
