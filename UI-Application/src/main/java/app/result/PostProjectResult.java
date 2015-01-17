package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import utils.Local;
import utils.Project;
import app.repository.ProjectsRepository;
import app.repositoryHolders.RepositoryHolder;

public class PostProjectResult implements CommandResult{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostProjectResult(JSplitPane splitPane, RepositoryHolder repoHolder){
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(String[] infoArray){
		new NewProjectWorker(splitPane, infoArray).execute();
	}

	public class NewProjectWorker extends AppSwingWorker{
		String[] infoArray;
		ProjectsRepository pRepo;

		public NewProjectWorker(JSplitPane pane, String[] infoArray){
			super(pane);
			this.infoArray = infoArray;
			pRepo = repositories.getProjectsRepo();
		}

		/**
		 * Creates a new {@code Local} and a new {@code Project} with the
		 * created {@code Local}. Get's the new {@code Project}'s PID from the
		 * {@code ProjectRepository} and adds it to the
		 * {@code ProjectRepository}. Outputs the new {@code Project}'s PID.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground(){
			publish("Status: Parsing values...");
			//Getting the text out of the fields of the array. 
			//The order in which they were placed in the array matters
			String localName = infoArray[0];
			String localPriceString = infoArray[1];
			String localLongitudeString = infoArray[2];
			String localLatitudeString = infoArray[3];
			
			if(localName.length() == 0 || localPriceString.length() == 0 || 
					localLongitudeString.length() == 0 || localLatitudeString.length() == 0)
				return new WarningMessagePanel("At least a field was left blank.");
			
			double localPrice;
			double localLongitude;
			double localLatitude;
			
			//Trying to parse the strings into doubles. If the user didn't introduce numbers 
			//in those fields a NumberFormatException will be thrown.
			try{
				localPrice = Double.parseDouble(localPriceString);
				localLongitude = Double.parseDouble(localLongitudeString);
				localLatitude =  Double.parseDouble(localLatitudeString);
			} catch (NumberFormatException e) {
				return new WarningMessagePanel("Numbers were not introduced in one of the following fields:\nPrice, Longitude of Latitude.");
			}
			
			publish("Status: Generating Local for the new Project...");
			Local local;
			try{
				local = new Local(localLatitude, localLongitude, localName, localPrice);
			} catch (IllegalArgumentException illegalArgument){
				return new WarningMessagePanel(
						"Invalid Argument. Price, latitude or longitude out of bounds.");
			}

			publish("Status: Generating the new Project...");
			long pid = pRepo.getNextPID();
			Project project = new Project(local, pid);

			publish("Status: Adding the new Project to the repository...");
			pRepo.addProject(project);

			return new ResultPanel("Project with the PID " + pid
					+ " was successfully created.");
		}
	}
}
