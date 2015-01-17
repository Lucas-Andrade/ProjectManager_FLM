package app.result;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.elements.IUser;
import app.elements.Message;
import app.elements.User;
import app.repositoryHolders.RepositoryHolder;

public class PostUserResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PostUserResult(JSplitPane splitPane, RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new NewProjectWorker(splitPane, username, password, email, fullname).execute();
	}

	public class NewProjectWorker extends AppSwingWorker
	{

		String username;
		String password;
		String email;
		String fullname;

		public NewProjectWorker(JSplitPane pane, String username, String password, String email, String fullname)
		{
			super(pane);
			this.username=username;
			this.password=password;
			this.email=email;
			this.fullname=fullname;
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
		protected JPanel doInBackground()
		{

			publish("Status: Checking information...");
			if(password.length() < User.minCharInPass){
				return new WarningMessagePanel("User's password must have at least 4 characters.");
			}
			IUser[] existingUsers = (IUser[]) repositories.getUsersRepo().getAll();
			for (IUser existingUser : existingUsers){
				if (existingUser.getLoginName().equals(this.username)){
					return new WarningMessagePanel("The Specified Username already exists in repository.");
				}
			}
			if (this.validEmail()){
				publish("Status: Generating new User...");
				repositories.getUsersRepo().addUser(new User(this.username, this.password,
						this.email, this.fullname));
				return new ResultPanel("New User generated.");
			}
			return new WarningMessagePanel("The Email is not valid.");

		}

		/**
		 * Validates the Email.
		 * 
		 * @return Returns True if valid, False if not.
		 */
		private boolean validEmail(){
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
