package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.Consultant;
import utils.Leader;
import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * POST /consultants - cria um novo consultor, dados os seguintes parâmetros:
 * name - nome do consultor
 * priceHour - preço/hora do consultor
 */
public class PostConsultant extends BasePostCommand {

	private final WorkerRepository repository;
	
	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final WorkerRepository repository;
		private final UserRepository uRepository;
		
		public Factory(WorkerRepository repository, UserRepository uRepository)
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new PostConsultant(repository, uRepository, parameters);
		}
	}

	public PostConsultant(WorkerRepository repository, UserRepository uRepository, Map<String, String> parameters) {
		super(uRepository, parameters);
		this.repository = repository;
	}

	@Override
	protected String[] getDemandingParametres() {
		return new String[]{"name", "priceHour"};
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out) throws CommandException, IOException 
	{
		String name = getParameterAsString("name");
		double priceHour = getParameterAsDouble("priceHour");
		
		if(priceHour < 0)
		{
			out.giveResults("Specified price per hour of the worker is less than zero.");
			return;
		}
		
		long cid = repository.nextCID();
		
		try{
			double bonus = getParameterAsDouble("bonus");
			
			Leader manager = new Leader(name, priceHour, 0, bonus, cid);
			repository.addManager(manager);
		}
		catch (NullPointerException e)
		{
			Consultant consultant = new Consultant(name, priceHour, 0, cid);
			repository.addConsultant(consultant);
		}
		
		out.giveResults(cid);
	}

}
