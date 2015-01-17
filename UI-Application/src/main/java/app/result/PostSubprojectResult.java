package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Project;
import app.repository.ProjectsRepository;
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
		new PostSubprojectWorker(splitPane, textFields).execute();
	}

	public class PostSubprojectWorker extends AppSwingWorker
	{
		JTextField[] textFields;
		ProjectsRepository pRepo;

		public PostSubprojectWorker(JSplitPane pane, JTextField[] textFields)
		{
			super(pane);
			pRepo = repositories.getProjectsRepo();
			this.textFields = textFields;
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
		protected JPanel doInBackground(){
			
			String pidString = textFields[0].getText();
			String subPidString = textFields[1].getText();
			
			//TODO verificar se os campos nao estavam vazios
			
			long pid;
			long subPid;
			try{
				pid = Long.parseLong(pidString);
				subPid = Long.parseLong(subPidString);
			} catch (NumberFormatException e) {
				return new WarningMessagePanel("Numbers were not introduced in one of the following fields: Project ID, or Subproject ID.");
			}
			
			publish("Status: Analyzing information...");
			if (pid == subPid){
				return new WarningMessagePanel(
						"Specified projects identifications are equal!");
			}
			
			Project project = pRepo.getProjectById(pid);
			if (project == null){
				return new WarningMessagePanel(
						"Specified project does not exist.");
			}
			Project subProject = pRepo.getProjectById(subPid);
			if (subProject == null){
				return new WarningMessagePanel(
						"Specified subproject does not exist.");
			}

			publish("Status: Adding Subproject to Project...");
			if (project.addProject(subProject)){
				return new ResultPanel("Added Subproject to Project.");
			}
			return new ResultPanel(
					"Could not add subproject to project, because subproject already is a subproject (of this or another project).");
		}

	}

}
