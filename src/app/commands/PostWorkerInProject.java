package app.commands;

import java.io.IOException;
import java.util.Map;
import utils.Consultant;
import utils.Leader;
import utils.Project;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * POST /projects/{pid}/{type} - adiciona ao projecto identificado por pid um
 * consultor do tipo type (manager ou consultant), dados os seguintes
 * parâmetros:
 * 
 * consultant - identificador do consultor a inserir 
 * Este comando retorna o sucesso ou insucesso da operação. Em caso de insucesso indica o motivo.
 */
public class PostWorkerInProject extends BasePostCommand {

	public static final String PID = "pid";
	
	public static final String WTYPE = "type";
	
	public static final String CID = "cid";
	
		
	
	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final ProjectRepository pRepository;
		private final WorkerRepository wRepository;
		private final UserRepository uRepository;
		
		public Factory(UserRepository uRepository, ProjectRepository pRepository, WorkerRepository wRepository)
		{
			this.pRepository = pRepository;
			this.wRepository = wRepository;
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostWorkerInProject(uRepository, pRepository, wRepository, parameters);
		}
	}
	
	private final ProjectRepository projectRepository;
	
	private final WorkerRepository workerRepository;

	private static final String[] DEMANDING_PARAMETERS = {PID, WTYPE, CID };
	
	private long projectId;

	private String typeWorker;

	private long workerId;
	
	/*construtor*/
	public PostWorkerInProject(UserRepository uRepository, ProjectRepository pRepository, WorkerRepository wRepository, Map<String, String> parameters) 
	{
		super(uRepository, parameters);
		this.projectRepository = pRepository;
		this.workerRepository = wRepository;
	}
	
	
	//devolve o array com os nomes dos parãmetros obrigatórios
	@Override
	protected String[] getMandatoryParametres() 
	{		
		return DEMANDING_PARAMETERS;
	}


	@Override
	protected void internalPostExecute(ResultOutputMethod out) throws CommandException, IOException 
	{
		this.projectId = getParameterAsLong(PID);
		this.typeWorker = getParameterAsString(WTYPE);
		this.workerId = getParameterAsLong (CID);
		
		Project project = projectRepository.getProjectById(projectId);
		if (project == null)
		{
			out.giveResults("The Specified Project do not exists in repository.");
			return;
		}
		
		if (typeWorker.equalsIgnoreCase("manager"))
		{
			out.giveResults(addManager(out, projectId, workerId));
			return;
		}
		else if(typeWorker.equalsIgnoreCase("consultant")) 
		{
			out.giveResults(addConsultant(out,projectId, workerId));
			
			return;
		}
		else
			out.giveResults("Unrecognised type of worker.");
		
		
		
//		AWorker worker = null;
//		
//		String methodName = "add" + typeWorker;
//		
//		Class<? extends PostWorkerInProject> c = this.getClass();
//		Method creatorMethod;
//		try {
//			creatorMethod = c.getMethod(methodName, (Class<?>[])null);
//			worker = (AWorker) creatorMethod.invoke(this, name, descr, price);
//		} catch (Exception e) {
//			throw new CommandException("Error finding method to create a " + typeWorker, e);
//		}
//		
//		productsRepository.insert(p);
		
		
	}
	private Boolean addConsultant(ResultOutputMethod out, long projectId, long workerId) throws IOException
	{
		Consultant consultant = workerRepository.getConsultantByID(workerId);
		
		if (consultant!=null)
		{
			projectRepository.getProjectById(projectId).addWorker(consultant);
			return true;
		}
		out.giveResults("The Specified Consultant do not exists in repository.");
		return false;
	}

	private Boolean addManager(ResultOutputMethod out, long projectId, long workerId) throws IOException 
	{
		Leader manager = workerRepository.getManagerByID(workerId);
		if (manager!=null)
		{
			projectRepository.getProjectById(projectId).setManager(manager);
			return true;
		}
		out.giveResults("The Specified Manager do not exists in repository.");
		return false;
	}
}
