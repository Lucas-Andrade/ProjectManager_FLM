package app.result;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.repositoryHolders.RepositoryHolder;

public class DeleteProjectResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public DeleteProjectResult(JSplitPane splitPane, RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new DeleteProjectWorker(splitPane, pid).execute();
	}

	public class DeleteProjectWorker extends AppSwingWorker
	{

		long pid;

		public DeleteProjectWorker(JSplitPane pane, long pid)
		{
			super(pane);
			this.pid = pid;
		}

		/**
		 * Deletes a {@code Project} and all it's Subprojects, and their own
		 * Subprojects and so on.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Project to delete in the repository...");
			Project parent = repositories.getProjectsRepo().getProjectById(pid);
			if (parent == null)
			{
				return new WarningMessagePanel("Project with PID " + pid
						+ " does not exist in the repository.");
			}

			publish("Status: Searching for all the Project's Subprojects in the repository...");
			Collection<Project> projectsToRemove = getAllProjectsToRemove(parent);

			publish("Status: Deleting all the Projects from the repository...");
			for (Project project : projectsToRemove)
			{
				repositories.getProjectsRepo().removeProject(project);
			}

			return new ResultPanel("Project with the PID " + pid
					+ " and all it's Subprojects were successfully deleted.");

		}

		/**
		 * Constructs a {@code Collection<Project>} with all the Subprojects of
		 * the parent {@code Project}, including all the Subprojects of the
		 * Subprojects and so on. The parent itself will be included in the
		 * {@code Collection}.
		 * 
		 * @param parent
		 *            The Project to be deleted.
		 * @return A {@code Collection} with all the Subprojects of a parent
		 *         {@code Project}, and all of their Subprojects and so on.
		 */
		private Collection<Project> getAllProjectsToRemove(Project parent)
		{
			Collection<Project> toRemove = new ArrayList<Project>();
			toRemove.add(parent);

			Collection<Project> subprojects = parent.getContainerProject();
			for (Project project : subprojects)
			{
				toRemove.addAll(getAllProjectsToRemove(project));
			}

			parent.removeAllSubprojects();

			return toRemove;
		}

	}

}
