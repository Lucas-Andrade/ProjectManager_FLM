package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import utils.Local;
import utils.Project;
import app.actionListeners.AppSwingWorker;
import app.authentication.Authentication;
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
		public NewProjectWorker(Project project) {
			super(pane);
		}

		/**
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected String doInBackground() {
			
			publish("Status: Accessing database");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			publish("Status: Computing changes.");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Thread.sleep(5000);
			
			return "";
		}
	}
	
	public class OkActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			//TODO ir buscar os parâmetros aos campos da janela, construir o
			//projecto
			
			Project project = new Project(new Local(1, 1, "coisa", 1), 1);
			
			new NewProjectWorker(project).execute();
			frame.dispose();
		}
	}
}

