package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.UserRepository;

/**
 * POST /projects/{pid}/subprojects - adiciona ao projecto identificado por pid
 * um subprojecto, dados os seguintes parâmetros:
 * 
 * subproject - identificador do subprojecto a inserir 
 * Este comando retorna o sucesso ou insucesso da operação. Em caso de insucesso indica o motivo.
 */
public class PostSubproject extends BaseCommand implements Command {

	public PostSubproject(Map<String, String> parameters) {
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
