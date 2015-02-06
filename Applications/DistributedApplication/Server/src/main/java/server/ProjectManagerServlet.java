package server;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parser.CommandParser;
import parserUtils.ParserResult;
import app.AppElement;

@SuppressWarnings("serial")
public class ProjectManagerServlet extends HttpServlet{

	private String commandString;
	
	
	public ProjectManagerServlet(){ }
    
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	//TODO colocar aqui ifs para testar o URI inserido e se pode ser feito o substring seguinte
    
//    	CommandParser parser = new CommandParser();
    	String path = getCommandStringFromRequest(req);
    	
    	String method = path.substring(1, path.indexOf('/', 1));
    	path = path.substring(path.indexOf('/', 1));
    	CommandParser cp = getCommandParser();
    	String input = "";
    	try {
			ParserResult pr = cp.getCommand(method, path).call();
			for (AppElement elem : pr.getResults())
				input+=elem.getJson().toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//startParser(parser, input);

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
		
	}
	
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		commandString = getCommandStringFromRequest(req);
		
		CommandParser parser = new CommandParser();
		String input = getCommandStringFromRequest(req);
		StackMensage mensage = startParser(parser, input);

		resp.setContentType("application/json");
		PrintWriter out;
		out = resp.getWriter();
		out.print(mensage.pop());
		out.flush();
    }
	
	
	@Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		commandString = getCommandStringFromRequest(req);
		
		//give string to command parser and get command
    }
	
	
	@Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		commandString = getCommandStringFromRequest(req);
		
		//give string to command parser and get command
   }
	
	
	/**
	 * Gets the singleton {@link CommandParser} created and configured in the application
	 * @return the application {@link CommandParser}
	 */
	private CommandParser getCommandParser() {
		CommandParser cp = CommandParser.getInstance();
		return cp;
	}

//	
//	public StackMensage startParser(CommandParser parser, String input) {
//
//
//		getCommandParser(userRepo, parser);
//
//		System.out.println(input);
//
//		User admin = new User("Lima", "SLB", "OMAIOREMail", "Lima");
//		userRepo.add(admin);
//
//		do {
//
//
//			try {
//
//				parser.getCommand(input.split(" ")).execute(stackMensage);
//			} catch (IllegalArgumentException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			} catch (UnknownCommandException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			} catch (DuplicateArgumentsException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			} catch (InvalidCommandArgumentsException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			} catch (CommandException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			} catch (ExecutionException e) {
//				LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//
//			}
//
//			return stackMensage;
//
//		} while (true);
//	}
//

	
	/**
	 * returns a string with the command line request needed for command parser.
	 * This string is created based on {@link HttpServletRequest} data.
	 * 
	 * @param req
	 *            the {@link HttpServletRequest} corresponding to the current
	 *            HTTP request
	 * @return the string to send to {@link CommandParser}
	 */
	private String getCommandStringFromRequest(HttpServletRequest req) {
		
		String path =req.getRequestURI();
//    	String method = path.substring(1, path.indexOf('/', 1));
//    	path = path.substring(path.indexOf('/', 1));
//    	
//		StringBuilder out = new StringBuilder();
//		InputStream input = null;
//		try {
//			input = req.getInputStream();
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(input));
//			for (String line = br.readLine(); line != null; line = br.readLine())
//				out.append(line);
//			br.close();
//
//		} catch (IOException e) {
//			LOGGER.error( "FailedCreateActivityFunction Exception Occured : " ,e );
//		}

		return path;
	}

}
