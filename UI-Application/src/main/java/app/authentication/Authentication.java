package app.authentication;

import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import app.elements.IUser;
import app.repository.UserRepository;
import app.repositoryHolders.RepositoryHolder;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Class that logs in and logs out an {@code IUser}. It's able to tell if there
 * is any {@code IUser} logged in or not and it's able to return the logged in
 * {@code IUser} (or {@code null} if there is none).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class Authentication
{

	/**
	 * The authenticated {@code IUser}.
	 */
	private static IUser authenticatedUser;

	/**
	 * A flag with {@code true} if there is an {@code IUser} logged in,
	 * {@code false} if not.
	 */
	private static boolean isAuthenticated = false;

	/**
	 * A {@code List} containing all the Observers of {@code this} object.
	 */
	private static final List<AuthenticationActionListener> actionListeners = new ArrayList<AuthenticationActionListener>();

	/**
	 * Method that tells if there is an {@code IUser} logged in.
	 * 
	 * @return {@code this#isAuthenticated}
	 */
	public static boolean isAuthenticated()
	{
		return isAuthenticated;
	}

	/**
	 * Method that tells the {@code IUser} logged in (or {@code null} if none).
	 * 
	 * @return {@code this#authenticatedUser}
	 */
	public static IUser getAuthenticatedUser()
	{
		return authenticatedUser;
	}

	/**
	 * Logs in an {@code IUser}: turns the flag to {@code true} and saves a
	 * reference to the logged in {@code IUser}.
	 * 
	 * @param user
	 *            The {@code IUser} to be logged in.
	 */
	private static void setAuthenticatedUser(IUser user)
	{
		authenticatedUser = user;
		isAuthenticated = true;
		fire(true, user);
	}

	/**
	 * Logs in an {@code IUser} if the supplied username and password match with
	 * an {@code IUser}.
	 * 
	 * @param fieldsToRetrieve
	 *            The username and password of the {@code IUser} attempting to
	 *            login.
	 * @param repoHolder
	 *            The {@code UserRepository} with the {@code IUser}s.
	 */
	public static void authenticate(JTextField[] fieldsToRetrieve,
			RepositoryHolder repoHolder)
	{
		new AuthenticationSwingWorker(fieldsToRetrieve, repoHolder).execute();
	}

	/**
	 * Logs out any logged in {@code IUser}: turns the flag to {@code false} and
	 * any reference to an {@code IUser} becomes {@code null}.
	 */
	public static void unauthenticate()
	{
		authenticatedUser = null;
		isAuthenticated = false;
		fire(false, null);
	}

	private static class AuthenticationSwingWorker extends
			SwingWorker<IUser, Object>
	{

		UserRepository uRepo;
		JTextField[] fieldsToRetrieve;

		public AuthenticationSwingWorker(JTextField[] fieldsToRetrieve,
				RepositoryHolder repoHolder)
		{

			this.uRepo = repoHolder.getUsersRepo();
			this.fieldsToRetrieve = fieldsToRetrieve;
		}

		@Override
		protected IUser doInBackground() throws Exception
		{

			String loginName = fieldsToRetrieve[0].getText();
			char[] loginPasswordChars = ((JPasswordField) fieldsToRetrieve[1])
					.getPassword();
			StringBuilder builder = new StringBuilder();

			for (char passChar : loginPasswordChars)
			{
				builder.append(passChar);
			}

			String loginPassword = builder.toString();
			IUser user = uRepo.getUserByUsername(loginName);
			return (user != null)
					&& user.getLoginPassword().equals(loginPassword) ? user
					: null;
		}

		@Override
		protected void done()
		{
			IUser user = null;

			try
			{
				user = get();
			} catch (InterruptedException e)
			{
				new ErrorDialog("Was interrupted before reaching database.")
						.setVisible(true);
				return;
			} catch (ExecutionException e)
			{
				new ErrorDialog(
						"Could not verify if the login name and password were correct.")
						.setVisible(true);
				return;
			}

			if (user != null)
			{
				setAuthenticatedUser(user);
				return;
			}
			new ErrorDialog(
					"Login name or password do not match any known users.")
					.setVisible(true);
		}
	}

	/**
	 * The interface to be implemented by all the Observers of {@code this}
	 * object.
	 * 
	 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
	 * @since 22/01/2015
	 */
	public static interface AuthenticationActionListener
	{
		/**
		 * The method containing the action to be performed when the Observer is
		 * notified by the {@code Authentication}.
		 * 
		 * @param isAuthenticated
		 *            {@code true} if there is an {@code IUser} authenticated,
		 *            {@code false} if not.
		 * @param authenticatedUser
		 *            The authenticated {@code IUser}, or null if there isn't
		 *            one.
		 */
		public void actionPerformed(boolean isAuthenticated,
				IUser authenticatedUser);
	}

	/**
	 * Method that adds an Observer to {@code this#actionListeners}.
	 * 
	 * @param actionListener
	 *            The Observer to be added.
	 */
	public static void addActionListener(
			AuthenticationActionListener actionListener)
	{
		actionListeners.add(actionListener);
	}

	/**
	 * Method that removes an Observer to {@code this#actionListeners}.
	 * 
	 * @param actionListener
	 *            The Observer to be removed.
	 */
	public static void removeActionListener(
			AuthenticationActionListener actionListener)
	{
		actionListeners.remove(actionListener);
	}

	/**
	 * Method that notifies all the Observers when changes are made in
	 * {@code this} object.
	 * 
	 * @param isAuthenticated
	 *            {@code true} if there is an {@code IUser} authenticated,
	 *            {@code false} if not.
	 * @param authenticatedUser
	 *            The authenticated {@code IUser}, or null if there isn't one.
	 */
	public static void fire(boolean isAuthenticated, IUser authenticatedUser)
	{
		for (AuthenticationActionListener actionListener : actionListeners)
			actionListener.actionPerformed(isAuthenticated, authenticatedUser);
	}

}