package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.Project;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * POST /projects/{pid}/subprojects - adiciona ao projecto identificado por pid
 * um subprojecto, dados os seguintes parâmetros:
 * 
 * subproject - identificador do subprojecto a inserir 
 * Este comando retorna o sucesso ou insucesso da operação. Em caso de insucesso indica o motivo.
 */
public class PostSubproject extends BasePostCommand {

	private final ProjectRepository repository;
	
	public static final String pathholderParameter = "pid";
	
	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final ProjectRepository repository;
		private final UserRepository uRepository;
		
		public Factory(ProjectRepository repository, UserRepository uRepository)
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostSubproject(repository, uRepository, parameters);
		}
	}
	
	public PostSubproject(ProjectRepository repository, UserRepository uRepository, Map<String, String> parameters) {
		super(uRepository, parameters);
		this.repository = repository;
	}
	
	@Override
	protected String[] getDemandingParametres() {
		return new String[]{"pid", "subPid"};
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out) throws CommandException, IOException 
	{
		long pid = getParameterAsLong("pid");
		long subPid = getParameterAsLong("subPid");
		
		if(pid == subPid)
		{
			out.giveResults("Specified project identifications are equal!");
			return;
		}
		
		Project project = repository.getProjectById(pid);
		if(project == null)
		{
			out.giveResults("Specified project does not exist.");
			return;
		}
		
		Project subProject = repository.getProjectById(subPid);
		if(subProject == null)
		{
			out.giveResults("Specified subproject does not exist.");
			return;
		}
		
		project.addProject(subProject);
		out.giveResults("Success.");
	}
	

}
