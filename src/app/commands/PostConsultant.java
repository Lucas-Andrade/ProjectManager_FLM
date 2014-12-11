package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import app.commands.exceptions.CommandException;
import app.repository.UserRepository;

/**
 * POST /consultants - cria um novo consultor, dados os seguintes parâmetros:
 * name - nome do consultor
 * priceHour - preço/hora do consultor
 */
public class PostConsultant extends BaseCommand implements Command {

	public PostConsultant(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UserRepository repository;
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			// TODO
			return null;
		}
	}

	@Override
	public void execute(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getDemandingParametres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void internalExecute() throws CommandException {
		// TODO Auto-generated method stub
		
	}

}
