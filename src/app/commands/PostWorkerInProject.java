package App.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import App.commands.exceptions.CommandException;
import App.repository.UsersRepository;

/**
 * POST /projects/{pid}/{type} - adiciona ao projecto identificado por pid um
 * consultor do tipo type (manager ou consultant), dados os seguintes
 * parâmetros:
 * 
 * consultant - identificador do consultor a inserir 
 * Este comando retorna o sucesso ou insucesso da operação. Em caso de insucesso indica o motivo.
 */
public class PostWorkerInProject extends BaseCommand implements Command {

	public PostWorkerInProject(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UsersRepository repository;
		
		public Factory(UsersRepository repository)
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
	public void execute() {
		// TODO Auto-generated method stub
		
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
