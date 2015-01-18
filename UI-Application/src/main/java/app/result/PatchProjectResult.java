package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Project;
import app.repositoryHolders.RepositoryHolder;

public class PatchProjectResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PatchProjectResult(JSplitPane splitPane, RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new PatchProjectWorker(splitPane, textFields).execute();
	}

	public class PatchProjectWorker extends AppSwingWorker
	{

		long pid;
		Double localLatitude;
		Double localLongitude;
		String localName;
		Double localPrice;

		public PatchProjectWorker(JSplitPane pane, JTextField[] textFields)
		{
			super(pane);
			this.pid=Long.parseLong(textFields[0].toString());
			this.localLatitude=Double.parseDouble(textFields[1].toString());
			this.localLongitude=Double.parseDouble(textFields[2].toString());
			this.localName=textFields[3].toString();
			this.localPrice=Double.parseDouble(textFields[4].toString());
		}

		/**
		 * Modifies a {@code Project} with the supplied information by the User.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Project in the repository...");
			Project project = repositories.getProjectsRepo().getProjectById(pid);

			if (project == null){
				return new WarningMessagePanel("Project with PID " + pid
						+ " does not exist in the repository.");
			}

			publish("Status: Making modifications in Project...");
			if (localName!=null){
				project.updateLocalName(localName);
			}
			if (localLongitude!=null && 
					!project.updateLongitude(localLongitude)){
				return new WarningMessagePanel(
						"Invalid Argument. Longitude out of bounds.");
			}
			if (localLatitude!=null && 
						!project.updateLatitude(localLatitude)){
				return new WarningMessagePanel(
						"Invalid Argument. Latitude out of bounds.");
			}
			if (localPrice!=null &&
						!project.updateLocalPrice(localPrice)){
				return new WarningMessagePanel(
						"Invalid Argument. Price per hour cannot be negative.");
			}
			
			return new ResultPanel("Project modified successfully.");

		}

	}

}
