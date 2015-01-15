package app.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.repositoryHolders.RepositoryHolder;

public class PostProjectActionListener implements ActionListener{
	
	JSplitPane splitPane;
	RepositoryHolder repositories;
	JTextField[] textFields;
	
	public PostProjectActionListener(JTextField[] textFields, JSplitPane splitPane, RepositoryHolder repoHolder){
		this.splitPane = splitPane;
		this.repositories = repoHolder;
		this.textFields = textFields;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		new NewProjectWorker(splitPane).execute();
	}
	
	
	public class NewProjectWorker extends AppSwingWorker{
		
		double localLatitude;
		double localLongitude;
		String localName;
		double localPrice;
		
		public NewProjectWorker(JSplitPane pane) {
			super(pane);
		}

		/**
		 * Creates a new {@code Local} and a new {@code Project} with the created
		 * {@code Local}. Get's the new {@code Project}'s PID from the
		 * {@code ProjectRepository} and adds it to the {@code ProjectRepository}.
		 * Outputs the new {@code Project}'s PID.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground() {
			
			publish("Status: Generating Local for the new Project...");
			Local local;
			try {
				local = new Local(localLatitude, localLongitude, localName, localPrice);
			} catch (IllegalArgumentException illegalArgument) {
				JPanel panel = new JPanel();
				panel.add(new JLabel("Price, latitude or longitude out of bounds."));
				return panel;
			}
			
			publish("Status: Generating the new Project...");
			long pid = repositories.getProjectsRepo().getNextPID();
			Project project = new Project(local, pid);

			publish("Status: Adding the new Project to the repository...");
			repositories.getProjectsRepo().addProject(project);
			
			JPanel panel = new JPanel();
			panel.add(new JLabel("Project with the PID " + pid + " was successfully created."));
			return panel;
		}
	}
	
}
