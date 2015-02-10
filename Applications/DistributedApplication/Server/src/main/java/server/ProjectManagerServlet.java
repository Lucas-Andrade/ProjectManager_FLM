package server;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outputMethods.Result;
import parser.CommandParser;
import parserUtils.ParserResult;
import app.AppElement;

@SuppressWarnings("serial")
public class ProjectManagerServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	String path = getCommandStringFromRequest(req);
    	String method = getCommandMethodFromRequest(req);
    	System.out.println(req.getQueryString() + path + method);
//    	CommandParser cp = getCommandParser();
//    	String input = "";
//    	try {
//			Result pr = cp.getCommand(method, path).call();
//			for (AppElement elem : pr.getResults())
//				input+=elem.getJson().toString();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//startParser(parser, input);
//
//		OutputStream out = resp.getOutputStream();
//		out.write(input.getBytes());
//		
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
	 * Gets the singleton {@link CommandParser}.
	 * @return the application {@link CommandParser}
	 */
	private CommandParser getCommandParser() {
		return CommandParser.getInstance();
	}

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
		return req.getRequestURI();
	}

	private String getCommandMethodFromRequest(HttpServletRequest req) {
		return req.getMethod();
	}
	
}
