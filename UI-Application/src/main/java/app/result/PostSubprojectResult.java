package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.elements.Message;
import app.repositoryHolders.RepositoryHolder;

public class PostSubprojectResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostSubprojectResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new PostSubprojectWorker(splitPane, pid, subPid).execute();
	}

	public class PostSubprojectWorker extends AppSwingWorker
	{

		long pid;
		long subPid;

		public PostSubprojectWorker(JSplitPane pane, long pid, long subPid)
		{
			super(pane);
			this.pid = pid;
			this.subPid = subPid;
		}

		/**
		 * Adds a {@code Project} as a subproject of another {@code Project} if both
	 	 * {@code Project}s have different IDs (if they are not the same), if the
	 	 * {@code Project} to be added as subproject isn't a subproject and if both
	 	 * {@code Project}s exist. Outputs a successful message if successful and
	 	 * vice-versa.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Analyzing information...");
			if (pid == subPid)
			{
				return new WarningMessagePanel(
						"Specified projects identifications are equal!");
			}
			Project project = repositories.getProjectsRepo().getProjectById(pid);
			if (project == null)
			{
				return new WarningMessagePanel(
						"Specified project does not exist.");
			}
			Project subProject = repositories.getProjectsRepo().getProjectById(subPid);
			if (subProject == null)
			{
				return new WarningMessagePanel(
						"Specified subproject does not exist.");
			}

			publish("Status: Adding Subproject to Project...");
			if (project.addProject(subProject))
			{
				return new ResultPanel("Added Subproject to Project.");
			}
			return new ResultPanel(
					"Could not add subproject to project, because subproject already is a subproject (of this or another project).");

		}

	}

}
