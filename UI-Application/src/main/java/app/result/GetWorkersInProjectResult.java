package app.result;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;
import app.repositoryHolders.RepositoryHolder;

public class GetWorkersInProjectResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public GetWorkersInProjectResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new GetWorkersInProjectWorker(splitPane, pid, typeOfWorker).execute();
	}

	public class GetWorkersInProjectWorker extends AppSwingWorker
	{

		String typeOfWorker;
		long pid;

		public GetWorkersInProjectWorker(JSplitPane pane, long pid,
				String typeOfWorker)
		{
			super(pane);
			this.pid = pid;
			this.typeOfWorker = typeOfWorker;
		}

		/**
		 * Get's the {@code AWorker}s from a {@code Project}. If type of Worker
		 * is a Manager, then this {@code Command} get's the Manager (
		 * {@link Leader}) from the {@code Project} ({@link Project#manager}),
		 * if is Consultant then get's all the {@link Consultant}s in the
		 * {@link Team} of the {@code Project} ({@link Project#team}).
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Project with the Worker in the repository...");
			Project project = repositories.getProjectsRepo()
					.getProjectById(pid);
			if (project == null)
			{
				return new WarningMessagePanel("Project with PID " + pid
						+ " does not exist in the repository.");
			}

			publish("Status: Searching for the Worker(s) in the Project...");
			if (typeOfWorker.equalsIgnoreCase("Manager"))
			{
				return getManager(project);
			} else if (typeOfWorker.equalsIgnoreCase("Consultant"))
			{
				return getWorkers(project);
			} else
			{
				return new WarningMessagePanel("Unrecognized type of Worker.");
			}

		}

		/**
		 * Gets the {@code Consultant}s working on the {@code Project}, if any
		 * have been assigned.
		 * 
		 * @param project
		 *            The Project where the Workers should be searched.
		 * @return A JPanel with the results of the operation.
		 */
		private JPanel getWorkers(Project project)
		{
			Collection<AWorker> workers = project.getTeam();
			if (workers.isEmpty())
			{
				return new ResultPanel("There are no Consultants in the Project.");
			}
			StringBuilder workersString = new StringBuilder();
			for (AWorker worker : workers)
			{
				workersString.append(worker.toString()).append("/n");
			}
			return new ResultPanel(workersString.toString());
		}

		/**
		 * Returns the {@code Manager} of the {@code Project}, if one has been
		 * assigned.
		 * 
		 * @param project
		 *            The Project where the Workers should be searched.
		 * @return A JPanel with the results of the operation.
		 */
		private JPanel getManager(Project project)
		{
			Leader manager = project.getManager();
			if (manager == null)
			{
				return new ResultPanel("There is no Manager in the Project.");
			}
			return new ResultPanel(manager.toString());
		}

	}

}
