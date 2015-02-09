package guiElements;

/**
 * Class that defines the contract for the call of the available commands on the
 * userInterface.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public interface ICommandCaller {
	
	/**
	 * Method responsible for call the application domain {@code DeleteProject}
	 * Command, with the required parameters.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	public void callDeleteProject(String pidString);

	/**
	 * Method responsible for call the application domain {@code GetProject}
	 * Command, with the required parameters.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	public void callGetProject(String pidString);

	/**
	 * Method responsible for call the application domain {@code GetSubprojects}
	 * Command, with the required parameters.
	 * 
	 * @param pidString
	 *            {@code String} with the {@code Project}ID argument's name.
	 */
	public void callGetSubprojects(String pidString);

	/**
	 * Method responsible for call the application domain {@code GetUsers}
	 * Command, with the required parameters.
	 */
	public void callGetUsers();

	/**
	 * Method responsible for call the application domain {@code GetUser}
	 * Command, with the required parameters.
	 * 
	 * @param username
	 *            {@code String} with the {@code User}'s Username argument.
	 */
	public void callGetUser(String username);

	/**
	 * Method responsible for call the application domain
	 * {@code GetWorkersInProject} Command, with the required parameters.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} from which we want to
	 *            get the {@code Team}.
	 * @param workerOpt
	 *            The type of worker to be obtained. {@code "consultant"} will
	 *            get all the {@code Consultant}s working of the {@code Project}
	 *            (the {@code Project}'s {@code Team}). {@code "manager"} will
	 *            get the {@code Project}'s {@code Leader}.
	 */
	public void callGetWorkersInProject(String pid, String workerOpt);

	/**
	 * Method responsible for call the application domain
	 * {@code PatchConsultant} Command, with the required parameters.
	 * 
	 * @param consultantId
	 *            The {@code CID} of the {@code Consultant} whose properties are
	 *            to be modified.
	 * @param consultantName
	 *            The new {@code name} of the {@code Consultant}.
	 * @param priceHour
	 *            The new {@code priceHour} of the {@code Consultant}.
	 */
	public void callPatchConsultant(String consultantId, String consultantName,
			String priceHour);

	/**
	 * Method responsible for call the application domain {@code PatchProject}
	 * Command, with the required parameters.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} which properties are to
	 *            be altered
	 * @param longitudeString
	 *            The new {@code longitude}
	 * @param latitudeString
	 *            The new {@code latitude}
	 * @param priceString
	 *            The new {@code Local}'s {@code price}
	 * @param localName
	 *            The new {@code Local}'s {@code name}
	 */
	public void callPatchProject(String pidString, String longitude,
			String latitude, String price, String localName);

	/**
	 * Method responsible for call the application domain {@code PatchUser}
	 * Command, with the required parameters.
	 * 
	 * @param userName
	 *            The {@code username} of the {@code User} whose password is to
	 *            be changed
	 * @param oldPassword
	 *            The {@code User}'s current password
	 * @param newPassword
	 *            The {@code User}'s new password (must have at least 4
	 *            characters)
	 */
	public void callPatchUser(String userName, String oldPassword,
			String newPassword);

	/**
	 * Method responsible for call the application domain {@code PostConsultant}
	 * Command, with the required parameters.
	 * 
	 * @param name
	 *            The {@code name} of the new {@code AWorker}.
	 * @param priceHour
	 *            The price per hour of the new {@code AWorker}.
	 * @param bonus
	 *            The bonus of the new {@code AWorker}, if it happens to be a
	 *            {@code Leader}. If {@code null} or an empty {@code String} is
	 *            introduced in this parameter, a {@code Consultant} will be
	 *            constructed instead.
	 */
	public void callPostConsultant(String name, String priceHour, String bonus);

	/**
	 * Method responsible for call the application domain {@code PostProject}
	 * Command, with the required parameters.
	 * 
	 * @param latitude
	 *            The {@code latitude} of the new {@code Project}.
	 * @param longitude
	 *            The {@code longitude} of the new {@code Project}.
	 * @param name
	 *            The name of the new {@code Project}'s {@code Local}.
	 * @param price
	 *            The {@code price} of the {@code Project}'s {@code Local}.
	 */
	public void callPostProject(String latitude, String longitude, String name,
			String price);

	/**
	 * Method responsible for call the application domain {@code PostUser}
	 * Command, with the required parameters.
	 * 
	 * @param username
	 *            The {@code loginName} of the new {@code User}.
	 * @param password
	 *            The {@code loginPassword} of the new {@code User}. It must
	 *            have at least four characters.
	 * @param email
	 *            The {@code email} of the new {@code User}.
	 * @param fullName
	 *            The {@code fullName} of the new {@code User}. As this
	 *            parameter is optional for the {@code User}, {@code null} or an
	 *            empty {@code String}, are accepted as a way of providing no
	 *            full name for the {@code User}.
	 */
	public void callPostUser(String username, String password, String email,
			String fullname);

	/**
	 * Method responsible for call the application domain {@code PostSubproject}
	 * Command, with the required parameters.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project} to which will be added
	 *            a subproject.
	 * @param subPidString
	 *            The {@code PID} of the {@code Project} that will be added as a
	 *            subproject.
	 */
	public void callPostSubproject(String pid, String subprojectId);

	/**
	 * Method responsible for call the application domain
	 * {@code PostWorkerInProject} Command, with the required parameters.
	 * 
	 * @param pidString
	 *            The {@code PID} of the {@code Project}.
	 * @param cidString
	 *            The {@code CID} if the {@code AWorker}.
	 * @param workerType
	 *            The type of the worker to be added: {@code "consultant"} or
	 *            {@code "manager"}.
	 */
	public void callPostWorkerInProject(String pid, String cid, String worker);

	/**
	 * Method responsible for call the application domain
	 * {@code AuthenticateUser} Command, with the required parameters.
	 * 
	 * @param username
	 *            The {@code loginName} of the new {@code User}.
	 * @param password
	 *            The {@code loginPassword} of the new {@code User}. It must
	 *            have at least four characters.
	 */
	public void callAuthenticateUser(String name, String password);
}
