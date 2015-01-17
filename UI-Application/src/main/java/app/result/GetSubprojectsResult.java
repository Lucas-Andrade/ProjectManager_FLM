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

public class GetSubprojectsResult implements CommandResult{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public GetSubprojectsResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new GetSubprojectsWorker(splitPane, pid).execute();
	}

	public class GetSubprojectsWorker extends AppSwingWorker
	{

		long pid;

		public GetSubprojectsWorker(JSplitPane pane, long pid)
		{
			super(pane);
			this.pid = pid;
		}

		/**
		 * Get's all the Sub{@code Project}s from a {@code Project}.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Project to delete in the repository...");
			Project project = repositories.getProjectsRepo()
					.getProjectById(pid);
			if (project == null)
			{
				return new WarningMessagePanel("Project with PID " + pid
						+ " does not exist in the repository.");
			}

			publish("Status: Searching for the Project's Subprojects...");
			int subprojectsNumber = project.getSubprojectsNumber();
			if (subprojectsNumber == 0)
			{
				return new ResultPanel("There are no Subprojects in the Project.");
			}

			publish("Status: Processing Subprojects...");
			Collection<Project> subprojects = project.getContainerProject();
			StringBuilder subprojectsString = new StringBuilder();
			for (Project subproject : subprojects)
			{
				subprojectsString.append(subproject.toString()).append("/n");
			}

			return new ResultPanel(subprojectsString.toString());

		}

	}

}
