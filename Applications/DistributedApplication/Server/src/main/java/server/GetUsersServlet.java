package server;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outputMethods.Result;
import parser.CommandParser;
import app.AppElement;

@SuppressWarnings("serial")
public class GetUsersServlet extends HttpServlet{

	private String commandString;
	
	
	public GetUsersServlet(){ }
    
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	//commandString = getCommandStringFromRequest(req);
    	//CommandParser parser = new CommandParser();
    	CommandParser cp = CommandParser.getInstance();
    	String input="";
    	try {
			Result pr = cp.getCommand("GET", "/users").call();
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
	
	
	
//	@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		commandString = getCommandStringFromRequest(req);
//		
//		CommandParser parser = new CommandParser();
//		String input = getCommandStringFromRequest(req);
//		StackMensage mensage = startParser(parser, input);
//
//		resp.setContentType("application/json");
//		PrintWriter out;
//		out = resp.getWriter();
//		out.print(mensage.pop());
//		out.flush();
//    }
//	
//	
//	@Override
//    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		commandString = getCommandStringFromRequest(req);
//		
//		//give string to command parser and get command
//    }
//	
//	
//	@Override
//    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		commandString = getCommandStringFromRequest(req);
//		
//		//give string to command parser and get command
//    }
//	
//	
//	/**
//	 * Gets the singleton {@link CommandParser} created and configured in the application
//	 * @return the application {@link CommandParser}
//	 */
//	private CommandParser getCommandParser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
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
//	/**
//	 * Gets the singleton {@link CommandParser} created and configured in the
//	 * application
//	 * 
//	 * @return the application {@link CommandParser}
//	 */
//	private void getCommandParser(UserRepository userRepo, CommandParser parser) {
//		try {
//			parser.registerCommand("POST",
//					new StringBuilder("/users/").toString(),
//					new PostUser.Factory(userRepo));
//
//			parser.registerCommand("GET",
//					new StringBuilder("/users").toString(),
//					new GetUsers.Factory(userRepo));
//
//			parser.registerCommand(
//					"GET",
//					new StringBuilder("/users/{").append(GetUser.USERNAME)
//							.append("}").toString(), new GetUser.Factory(userRepo));
//			
//		} catch (InvalidRegisterException e) {
//			LOGGER.error( "Invalid Register Command!: " ,e );
//			
//		}
//
//	}
//
//	/**
//	 * returns a string with the command line request needed for command parser.
//	 * This string is created based on {@link HttpServletRequest} data.
//	 * 
//	 * @param req
//	 *            the {@link HttpServletRequest} corresponding to the current
//	 *            HTTP request
//	 * @return the string to send to {@link CommandParser}
//	 */
//	private String getCommandStringFromRequest(HttpServletRequest req) {
//		req.getRequestURI();
//		
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
//
//		return out.toString();
//	}

}
