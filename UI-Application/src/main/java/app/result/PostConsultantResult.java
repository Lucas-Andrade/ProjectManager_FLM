package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import app.AppElement;
import app.elements.Message;
import app.repositoryHolders.RepositoryHolder;

public class PostConsultantResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostConsultantResult(JSplitPane splitPane,
			RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new NewConsultantWorker(splitPane, textFields).execute();
	}

	public class NewConsultantWorker extends AppSwingWorker
	{

		String name;
		Double priceHour;
		Double bonus;

		public NewConsultantWorker(JSplitPane pane, JTextField[] textFields)
		{
			super(pane);
			try{
				this.name = textFields[0].getText();
			}catch(NullPointerException e){
				this.name=null;
			}
			this.priceHour = priceHour;
			this.bonus = bonus;
		}

		/**
		 * Creates a new Worker and adds it to the {@code WorkerRepository}.
		 * Outputs the new {@code Worker}'s CID.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Processing information...");
			if (name == null)
			{
				return new WarningMessagePanel(
						"Must specify the name of the worker.");
			}
			if (priceHour == null || priceHour < 0)
			{
				return new WarningMessagePanel(
						"Price per hour of the worker must be specified and can't be negative.");
			}

			long cid = repositories.getWorkersRepo().nextCID();
			if (bonus != null)
			{
				if (bonus < 0)
					return new WarningMessagePanel(
							"The manager's bonus can't be less than zero.");
				else
				{
					publish("Status: Creating Manager...");
					Leader manager = new Leader(name, priceHour, 0, bonus, cid);
					repositories.getWorkersRepo().addManager(manager);
				}
			} else
			{
				publish("Status: Creating Consultant...");
				Consultant consultant = new Consultant(name, priceHour, 0, cid);
				repositories.getWorkersRepo().addConsultant(consultant);
			}

			return new ResultPanel("Worker with the CID " + cid
					+ " was successfully created.");

		}

	}

}