package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import app.elements.IUser;
import app.elements.User;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;

public class PostUserResult implements CommandResult {

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostUserResult(JSplitPane splitPane, RepositoryHolder repoHolder) {
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new NewProjectWorker(splitPane, textFields).execute();
	}

	public class NewProjectWorker extends AppSwingWorker{
		
		JTextField[] textFields;
		UserRepository uRepo;
		
		public NewProjectWorker(JSplitPane pane, JTextField[] textFields){
			super(pane);
			this.textFields = textFields;
			uRepo = repositories.getUsersRepo();
		}

		/**
		 * Creates a new {@code User} and adds it to the {@code UserRepository} (if
		 * he doesn't exist already and if the Email argument is valid). Outputs
	 	 * successful message if successful.
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
			String username = textFields[0].getText();
			String password = textFields[1].getText();
			String email = textFields[2].getText();
			String fullName = textFields[3].getText();
			
			if(username.length() == 0 || password.length() == 0 || 
					email.length() == 0 || fullName.length() == 0)
				return new WarningMessagePanel("At least a field was left blank.");
			
			publish("Status: Checking information...");
			if(password.length() < User.minCharInPass){
				return new WarningMessagePanel("User's password must have at least 4 characters.");
			}
			IUser[] existingUsers = (IUser[]) repositories.getUsersRepo().getAll();
			for (IUser existingUser : existingUsers){
				if (existingUser.getLoginName().equals(username)){
					return new WarningMessagePanel("The Specified Username already exists in repository.");
				}
			}
			if (this.validEmail(email)){
				publish("Status: Generating new User...");
				uRepo.addUser(new User(username, password, email, fullName));
				return new ResultPanel("New User generated.");
			}
			return new WarningMessagePanel("The Email is not valid.");

		}

		/**
		 * Validates the Email.
		 * 
		 * @return Returns True if valid, False if not.
		 */
		private boolean validEmail(String email){
			if (!(email.contains("@"))){
				return false;
			}
			if (email.substring(email.indexOf("@") + 1, email.length()).contains("@")){
				return false;
			}
			if (email.lastIndexOf(".") < email.lastIndexOf("@")){
				return false;
			}
			return true;
		}

	}

}
