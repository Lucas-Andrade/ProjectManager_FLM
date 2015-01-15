package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import utils.AWorker;
import utils.Local;
import utils.Project;
import app.AppElement;
import app.actionListeners.AppSwingWorker;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.PatchConsultantFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

public class PatchConsultant extends BaseCommand{
	
	private PatchConsultantFrame frame;
	
	public PatchConsultant(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		frame = new PatchConsultantFrame(new OkActionListener());
		frame.setVisible(true);
	}
	
	public class PatchConsultantWorker extends AppSwingWorker {

		long cid;
		String name;
		Double priceHour;
		
		public PatchConsultantWorker(JSplitPane pane, long cid, String name, Double priceHour)
		{
			super(pane);
			this.cid=cid;
			if(name==null)
				this.name=null;
			if(priceHour==null)
				this.priceHour=null;
		}

		/**
		 * Modifies an {@code AWorker}. Get's the {@code AWorker}'s from the
		 * {@code WorkerRepository} and modifies it.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground() {
			
			JPanel panel = new JPanel();
			
			publish("Status: Searching for the Worker in the repository...");
			AWorker worker = repositories.getWorkersRepo()
					.getAWorkerByID(cid);
			if (worker == null){
				panel.add(new JLabel("Worker with CID " + cid + " does not exist in the repository."));
				return panel;
			}
			publish("Status: Making modifications in Worker...");
			if (name!=null){
				worker.setName(name);
			}
			if (priceHour!=null &&
						!worker.setCostPerHour(priceHour)){
				panel.add(new JLabel("Price per hour cannot be negative."));
				return panel;
			}
			
			panel.add(new JLabel("Worker modified successfully."));
			return panel;
			
		}

	public class OkActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			new DeleteProjectWorker(pane, pid).execute();
			frame.dispose();
		}
	}
}