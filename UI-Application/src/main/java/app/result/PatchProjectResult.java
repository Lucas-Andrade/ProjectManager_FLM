package app.result;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.elements.Message;
import app.elements.User;
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
		new PatchProjectWorker(splitPane, pid, localLatitude, localLongitude, localName, localPrice).execute();
	}

	public class PatchProjectWorker extends AppSwingWorker
	{

		long pid;
		Double localLatitude;
		Double localLongitude;
		String localName;
		Double localPrice;

		public PatchProjectWorker(JSplitPane pane, long pid, double localLatitude,
				double localLongitude, String localName, double localPrice)
		{
			super(pane);
			this.pid=pid;
			this.localLatitude=localLatitude;
			this.localLongitude=localLongitude;
			this.localName=localName;
			this.localPrice=localPrice;
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
