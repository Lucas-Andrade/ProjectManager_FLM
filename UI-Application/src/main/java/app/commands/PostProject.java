package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import utils.Local;
import utils.Project;
import app.elements.AppElement;
import app.elements.Message;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

public class PostProject extends BaseCommand{
	
	private PostProjectFrame frame;
	
	public PostProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		frame = new PostProjectFrame(new OkActionListener());
		frame.setVisible(true);
	}
	
	public class NewProjectWorker extends AppSwingWorker{
		
		double localLatitude;
		double localLongitude;
		String localName;
		double localPrice;
		
		public NewProjectWorker(JSplitPane pane, double localLatitude, double localLongitude,
				String localName, double localPrice) {
			super(pane);
			this.localLatitude=localLatitude;
			this.localLongitude=localLongitude;
			this.localName=localName;
			this.localPrice=localPrice;
		}

		/**
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground() {
			
			publish("Status: Generating Local for the new Project...");
			Local local;
			try {
				local = new Local(localLatitude, localLongitude, localName, localPrice);
			} catch (IllegalArgumentException illegalArgument) {
				publish("Price, latitude or longitude out of bounds.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					JPanel panel = new JPanel();
					panel.add(new JLabel("ERROR: " + e.getMessage()));
					return panel;
				}
				return new JPanel();
			}
			
			publish("Status: Generating the new Project...");
			long pid = repositories.getProjectsRepo().getNextPID();
			Project project = new Project(local, pid);

			publish("Status: Adding the new Project to the repository...");
			repositories.getProjectsRepo().addProject(project);
			
			JPanel panel = new JPanel();
			panel.add(new JLabel("Project with the PID " + pid + " was successfully created."));
			return panel;
		}
	}
	
	public class OkActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			new NewProjectWorker(pane, localLatitude, localLongitude,
					localName, localPrice).execute();
			frame.dispose();
		}
	}
}

