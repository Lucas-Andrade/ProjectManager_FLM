package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import commands.GetSubproject;
import utils.Local;
import utils.Project;
import app.AppElement;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.GetSubprojectsFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.AppSwingWorker;

public class GetSubprojects extends BaseCommand{
	
	private GetSubprojectsFrame frame;
	
	public GetSubprojects(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new GetSubprojectsFrame(repositories).setVisible(true);
	}
	
	public class GetSubprojectsWorker extends AppSwingWorker {

		long pid;
		
		public GetSubprojectsWorker(JSplitPane pane, long pid)
		{
			super(pane);
			this.pid=pid;
		}

		/**
		 * Get's all the Sub{@code Project}s from a {@code Project}.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground() {
			
			publish("Status: Searching for the Project to delete in the repository...");
			Project project = repositories.getProjectsRepo().getProjectById(pid);
			if (project == null){
				JPanel panel = new JPanel();
				panel.add(new JLabel("Project with PID " + pid + " does not exist in the repository."));
				return panel;
			}
			
			publish("Status: Searching for the Project's Subprojects...");
			int subprojectsNumber = project.getSubprojectsNumber();
			if(subprojectsNumber == 0){
				JPanel panel = new JPanel();
				panel.add(new JLabel("Project with PID " + pid + " does not contain Subprojects."));
				return panel;
			}
			
			publish("Status: Processing Subprojects...");
			Collection<Project> subprojects = project.getContainerProject();
			StringBuilder subprojectsString = new StringBuilder();
			for (Project subproject : subprojects){
				subprojectsString.append(subproject.toString()).append("/n");
			}
			
			JPanel panel = new JPanel();
			panel.add(new JLabel(subprojectsString.toString()));
			return panel;
			
		}

	}
	
	public class OkActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			new GetSubprojectsWorker(pane, pid).execute();
			frame.dispose();
		}
	}
}