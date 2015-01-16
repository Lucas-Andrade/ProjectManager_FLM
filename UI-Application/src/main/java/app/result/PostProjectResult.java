package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.repositoryHolders.RepositoryHolder;

public class PostProjectResult implements CommandResult{
	
	JSplitPane splitPane;
	RepositoryHolder repositories;
	
	public PostProjectResult(JSplitPane splitPane, RepositoryHolder repoHolder){
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}
	
	@Override
	public void executeResult(JTextField[] textFields) {
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
				return new ErrorPanel("Price, latitude or longitude out of bounds.");
			}
			
			publish("Status: Generating the new Project...");
			long pid = repositories.getProjectsRepo().getNextPID();
			Project project = new Project(local, pid);

			publish("Status: Adding the new Project to the repository...");
			repositories.getProjectsRepo().addProject(project);
			
			return new ResultPanel("Project with the PID " + pid + " was successfully created.");
		}
	}
}
