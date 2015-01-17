package app.result;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.elements.Message;
import app.elements.User;
import app.repositoryHolders.RepositoryHolder;

public class GetUserResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public GetUserResult(JSplitPane splitPane, RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new GetUserWorker(splitPane, username).execute();
	}

	public class GetUserWorker extends AppSwingWorker
	{

		String username;

		public GetUserWorker(JSplitPane pane, String username)
		{
			super(pane);
			this.username = username;
		}

		/**
		 * Gets a {@code User} from the repository by searching it's username.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the User in the repository...");
			User user = repositories.getUsersRepo().getUserByUsername(username);

			if(user == null){
				return new WarningMessagePanel("User with username " + username
						+ " does not exist in the repository.");
			}

			return new ResultPanel(user.toString());

		}

	}

}
