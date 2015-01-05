package app.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import app.elements.AppElement;
import app.elements.Message;
import app.resultsAndOutputMethods.Result;

/**
 * Class whose instances are commands that return the Path and a description of all available commands
 * Caller {@code String}: OPTION/
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/01/2015
 */
public class Option extends BaseCommandResultsOutputMethod{

	/**
	 * Class that implements the {@code Option} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{
		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new Option( parameters);
		}
	}
	
	/**
	 * The constructor for {@code Option}.
	 * 
	 * @param parameters    The command arguments.
	 */
	public Option(Map<String, String> parameters) {
		super(parameters);
	}

	
	/**
	 * Gets the {@code Project} with the argument
	 * PID stored in {@link GetProjects#parameters} 
	 * 
	 * @return An array of {@code DatabaseElement} with one element carrying the 
	 * {@code Project}
	 */
	@Override
	protected AppElement[] internalCall() throws Exception {
		
		Message[] options = availableCommands(); 		
		return options;
	}

	
	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters() 
	{
		return null;
	}

	/**
	 * This method create an array with an description of all available commands in this
	 * application
	 */
	private Message[] availableCommands()
	{
		List<Message> options = new ArrayList<Message>();
		options.add(new Message("POST COMMANDS:", "POST COMMANDS:"));
		options.add(new Message(
				"PostUsers",
				"\nPOST /users {parameter list: loginName, loginPassord, username, password, email, fullname(optional)}  "
						+ "\n	  Add a user to the User Repository. "));
		options.add(new Message(
				"PostProjects",
				"\n  	POST /projects  {parameter list: loginName, loginPassord, Latitiude, longitude, name, price} "
						+ "\n	  Add a Project to the Project repository."));
		options.add(new Message(
				"PostConsultants",
				"\n  	POST /consultants {parameter list: loginName, loginPassord, name, priceHour, bonus (optional)}"
						+ "\n	  Add a consultant to the Worker Repository"));
		options.add(new Message(
				"PostWorkerInProject",
				"\n  	POST /projects/{pid}/{type} {parameter list: loginName, loginPassord, WorkerId}"
						+ "\n	  Add a consultant or Manager to a project/subproject"));
		options.add(new Message(
				"PostSubprojects",
				"\n  	POST /projects/{pid}/subproject {parameter list: loginName, loginPassord, ProjectId}"
						+ "\n	  Add a subproject to a project/subproject."));
		options.add(new Message("GET COMMANDS",
				"\n\n  GET COMMANDS: {parameter list: accept, output-file(optional)}"));
		options.add(new Message("GetUsers",
				"\n  	GET /users : Return the information of all users in the User Repository"));
		options.add(new Message(
				"GetUser",
				"\n  	GET /users/{username}  : Return the information of the user with the  specify {@code username} of the User Repository"));
		options.add(new Message(
				"GetProjects",
				"\n  	GET /projects/{pid}: Return the information of the project with the  specify {@code ProjectId}")); // extra ao enunciado
		options.add(new Message(
				"GetProjectWorkers",
				"\n  	GET /projects/{pid}/{type} : Return the information of all consultants or of the Manager of a project with the  specify {@code ProjectId}"));
		options.add(new Message(
				"GetSubproject",
				"\n  	GET /projects/{pid}/subproject : Return the information of all subprojects of a project with the  specify {@code ProjectId}"));
		options.add(new Message("PATCH COMMANDS", "\n\n  PATCH COMMANDS:"));
		options.add(new Message(
				"PatchUser",
				"\n  	PATCH /users/{username} {parameter list: loginName, loginPassord, oldpassword, newPassword} "
						+ "\n	  Updates the password of the user identified by the  specify {@code username}. "));
		options.add(new Message(
				"PatchProject",
				"\n  	PATCH /projects/{pid} {parameter list: loginName, loginPassord, Latitiude, longitude, name, price (the last four optional)} "
						+ "\n	  Update the information of the project identified by the  specify {@code ProjectId}."));
		options.add(new Message(
				"PatchConsultant",
				"\n  	PATCH /consultants/{cid} {parameter list: loginName, loginPassord, name, priceHour (the last two optional)} "
						+ "\n	  Updates the information of the consultant with the  specify {@code WorkerId}. "));
		options.add(new Message("DELETE COMMANDS", "\n\n  DELETE COMMANDS:"));
		options.add(new Message(
				"DeleteProjects",
				"\n  	DELETE /projects/{pid} {parameter list: loginName, loginPassord} "
						+ "\n	  Deletes the project with the  specify {@code ProjectId} and all its subprojects"));

		return (Message[]) options.toArray();
		
	}
}
