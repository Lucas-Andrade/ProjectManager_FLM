package app.windows;

import javax.swing.JTable;

import utils.AWorker;
import utils.Leader;
import app.elements.IUser;

public class TableBuilder {
	
	/**
	 * As this is an utility class, a private constructor was implemented in order to hide the
	 * implicit public one.
	 */
	private TableBuilder() {
	}
	
	public static JTable getTableOfUsers(IUser[] users) {
		
		String[] columnNames = {"Username", "Email", "Full name"};
		String[][] data = new String[users.length][3];
		for(int i = 0; i < users.length; i++) {
			IUser user = users[i];
			data[i] = new String[]{user.getLoginName(), user.getEmail(), user.getFullName()};
		}
		
		return new JTable(data, columnNames);
	}
	
	
	public static JTable getTableOfWorkers(AWorker[] workers) {
		String[] columnNames = {"Name", "Consultant ID", "Cost per hour", "Hours worked", "Total cost", "Bonus"};
		String[][] data = new String[workers.length][6];
		for(int i = 0; i < workers.length; i++) {
			AWorker worker = workers[i];
			data[i] = new String[]{worker.getName(), String.valueOf(worker.getCID()), 
					String.valueOf(worker.getCostPerHour()), String.valueOf(worker.getWorkerHours()), 
					String.valueOf(worker.getCost()), checkBonus(worker)};
		}
		
		return new JTable(data, columnNames);
	}


	private static String checkBonus(AWorker worker) {
		
		if(worker instanceof Leader){
			return String.valueOf(((Leader)worker).getBonus());
		} else {
			return "N.A.";
		}
	}
}
