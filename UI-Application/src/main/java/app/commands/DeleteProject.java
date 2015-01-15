package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.actionListeners.AppSwingWorker;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

public class DeleteProject extends BaseCommand{
	
	private DeleteProjectFrame frame;
	
	public DeleteProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		frame = new DeleteProjectFrame(new OkActionListener());
		frame.setVisible(true);
	}
	
	public class NewDeleteWorker extends AppSwingWorker {

		long pid;
		
		public NewDeleteWorker(JSplitPane pane, long pid)
		{
			super(pane);
			this.pid=pid;
		}

		/**
		 * Deletes a {@code Project} and all it's Subprojects, and their own
		 * Subprojects and so on.
		 * 
		 * @return A JPanle with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground() {
			
			publish("Status: Searching for the Project to delete in the repository...");
			Project parent = repositories.getProjectsRepo().getProjectById(pid);
			if (parent == null){
				JPanel panel = new JPanel();
				panel.add(new JLabel("Project with PID " + pid + " does not exist in the repository."));
				return panel;
			}
			
			publish("Status: Searching for all the Project's Subprojects in the repository...");
			Collection<Project> projectsToRemove = getAllProjectsToRemove(parent);

			publish("Status: Deleting all the Projects from the repository...");
			for (Project project : projectsToRemove){
				repositories.getProjectsRepo().removeProject(project);
			}
			
			JPanel panel = new JPanel();
			panel.add(new JLabel("Project with the PID " + pid + " and all it's Subprojects were successfully deleted."));
			return panel;
			
		}
		
		/**
		 * Constructs a {@code Collection<Project>} with all the Subprojects of the
		 * parent {@code Project}, including all the Subprojects of the Subprojects
		 * and so on. The parent itself will be included in the {@code Collection}.
		 * 
		 * @param parent The Project to be deleted.
		 * @return A {@code Collection} with all the Subprojects of a parent
		 *         {@code Project}, and all of their Subprojects and so on.
		 */
		private Collection<Project> getAllProjectsToRemove(Project parent){
			Collection<Project> toRemove = new ArrayList<Project>();
			toRemove.add(parent);

			Collection<Project> subprojects = parent.getContainerProject();
			for (Project project : subprojects){
				toRemove.addAll(getAllProjectsToRemove(project));
			}
			
			parent.removeAllSubprojects();
			
			return toRemove;
		}

	}
	
	public class OkActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			new NewDeleteWorker(pane, pid).execute();
			frame.dispose();
		}
	}
}