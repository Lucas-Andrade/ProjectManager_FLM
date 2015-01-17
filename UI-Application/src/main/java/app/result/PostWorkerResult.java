package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import commands.PostWorkerInProject;
import commands.ResultOutputMethodToStream;
import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;
import app.AppElement;
import app.elements.Message;
import app.repositoryHolders.RepositoryHolder;

public class PostWorkerResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostWorkerResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new NewConsultantWorker(splitPane, name, priceHour, bonus).execute();
	}

	public class NewConsultantWorker extends AppSwingWorker
	{

		String typeWorker;
		Long projectId;
		Long workerId;

		public NewConsultantWorker(JSplitPane pane, String name,
				double priceHour, double bonus)
		{
			super(pane);
			this.name = name;
			this.priceHour = priceHour;
			this.bonus = bonus;
		}

		/**
		 * Adds a Worker to a Project.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Processing information...");
			Project project = repositories.getProjectsRepo().getProjectById(projectId);
			if (project == null){
				return new WarningMessagePanel("The Specified Project does not exist in repository.");
			}

			if (typeWorker.equalsIgnoreCase("manager")){
				return addManager(project, workerId);
			} else if (typeWorker.equalsIgnoreCase("consultant")) {
				return addConsultant(project, workerId);
			}
			return new WarningMessagePanel("Unrecognised type of worker.");

		}

		/**
		 * Adds a Consultant to a Project.
		 * 
		 * @param project The Project to receive the Worker.
		 * @param workerId The Worker's ID.
		 * @return A JPanel with the results of the operation.
		 */
		private JPanel addConsultant(Project project, long workerId){

			Consultant consultant = repositories.getWorkersRepo().getConsultantByID(workerId);
			if (consultant != null){
				publish("Status: Adding Consultant to Project...");
				project.addWorker(consultant);
				return new ResultPanel("Consultant added to Project.");
			}
			return new WarningMessagePanel("The Specified Consultant does not exist in repository.");

		}

		/**
		 * Adds a Manager to a Project.
		 * 
		 * @param project The Project to receive the Worker.
		 * @param workerId The Worker's ID.
		 * @return A JPanel with the results of the operation.
		 */
		private JPanel addManager(Project project, long workerId){

			Leader manager = repositories.getWorkersRepo().getManagerByID(workerId);
			if (manager != null){
				publish("Status: Adding Manager to Project...");
				project.setManager(manager);
				return new ResultPanel("Manager added to Project.");
			}
			return new WarningMessagePanel("The Specified Manager does not exist in repository.");

		}

	}

}