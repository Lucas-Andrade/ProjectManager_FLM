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
import app.authentication.Authentication;
import app.elements.IUser;
import app.elements.Message;
import app.elements.User;
import app.repositoryHolders.RepositoryHolder;

public class PatchUserResult implements CommandResult
{

	JSplitPane splitPane;
	RepositoryHolder repositories;

	public PatchUserResult(JSplitPane splitPane, RepositoryHolder repoHolder)
	{
		this.splitPane = splitPane;
		this.repositories = repoHolder;
	}

	@Override
	public void executeResult(JTextField[] textFields)
	{
		new PatchUserWorker(splitPane, authentication, newPassword).execute();
	}

	public class PatchUserWorker extends AppSwingWorker
	{

		long pid;
		Authentication authentication;
		String newPassword;

		public PatchUserWorker(JSplitPane pane, Authentication authentication, String newPassword)
		{
			super(pane);
			this.authentication=authentication;
			this.newPassword=newPassword;
		}

		/**
		 * Modifies a {@code User} with the supplied information.
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			IUser user = authentication.getAuthenticatedUser();

			publish("Status: Changing password for " + user.getLoginName() + "...");
			if (newPassword != null && !user.setNewPassword(newPassword))
				return new WarningMessagePanel("New password must at least have 4 characters.");
		
			return new ResultPanel("User's password modified successfully.");

		}

	}

}
