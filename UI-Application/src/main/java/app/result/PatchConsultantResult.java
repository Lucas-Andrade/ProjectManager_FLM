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

public class PatchConsultantResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PatchConsultantResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new PatchConsultantWorker(splitPane, cid, name, priceHour).execute();
	}

	public class PatchConsultantWorker extends AppSwingWorker
	{

		long cid;
		String name;
		Double priceHour;

		public PatchConsultantWorker(JSplitPane pane, long cid, String name,
				Double priceHour)
		{
			super(pane);
			this.cid = cid;
			if (name == null)
				this.name = null;
			if (priceHour == null)
				this.priceHour = null;
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
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Worker in the repository...");
			AWorker worker = repositories.getWorkersRepo().getAWorkerByID(cid);
			if (worker == null)
			{
				return new WarningMessagePanel("Worker with CID " + cid
						+ " does not exist in the repository.");
			}
			publish("Status: Making modifications in Worker...");
			if (name != null)
			{
				worker.setName(name);
			}
			if (priceHour != null && !worker.setCostPerHour(priceHour))
			{
				return new WarningMessagePanel("Price per hour cannot be negative.");
			}

			return new ResultPanel("Worker modified successfully.");

		}

	}

}
