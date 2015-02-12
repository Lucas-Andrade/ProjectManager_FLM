package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outputMethods.Result;
import outputMethods.format.ToTextHtml;
import parser.CommandParser;
import parserCommands.exceptions.InvalidUserException;
import parserCommands.exceptions.MandatoryParameterNotPresentException;
import parserUtils.CommandParserException;
import parserUtils.DuplicateArgumentsException;
import parserUtils.InvalidCommandArgumentsException;
import parserUtils.UnknownCommandException;
import app.AppElement;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;

@SuppressWarnings("serial")
public class ProjectManagerServlet extends HttpServlet {
	

	
		/**
		 * 
		 * @param req
		 * @param resp
		 * @throws IOException
		 * @throws ServletException
		 */
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method = req.getMethod();
		if(method.equals("PATCH")) {
			doPatch(req, resp);
			return;
		}else{
			super.service(req, resp);
		}
    }
	    
	 
    /**
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String parameters = req.getQueryString();
		String path = getCommandStringFromRequest(req);
		String method = getCommandMethodFromRequest(req);
		CommandParser cp = null;
		String input = "";
		try {
			cp = getCommandParser();
			cp.getCommand("GET", "/authenticate", parameters).call();
			Result pr = cp.getCommand(method, path, parameters).call();
			for (AppElement elem : pr.getResults())
			{
				if (req.getHeader("Accept").contains("text/html"))
				{
					input += new ToTextHtml().parse(elem.getJson());
				}
				else
				{
					input += elem.getJson().toString();
				}
			}
			resp.setStatus(200);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}

		System.out.println("New Connection Received:"); //For test purposes.
		System.out.println(method + " " + path + " " + parameters); //For test purposes.
		int numBytes = Integer.parseInt(req.getHeader("Content-length")); //For test purposes.
		byte[] bytes = new byte [numBytes]; //For test purposes.
		req.getInputStream().read(bytes); //For test purposes.
		System.out.println("IO contents: " + new String(bytes)); //For test purposes.
		System.out.println(input); //For test purposes.

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
    }

	
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		InputStream inputStream = req.getInputStream();
		
		int numBytes = Integer.parseInt(req.getHeader("Content-length"));
		byte[] bytes = new byte [numBytes];
		inputStream.read(bytes);
		
		String parameters = new String(bytes) + "&accept="+ req.getHeader("Accept");
		resp.setContentType("application/json");
		
		
		String path = getCommandStringFromRequest(req);
		String method = getCommandMethodFromRequest(req);
		CommandParser cp = null;
		String input = "";
		try {
			cp = getCommandParser();
			Result pr = cp.getCommand(method, path, parameters).call();
			for (AppElement elem : pr.getResults())
			{
				if (req.getHeader("Accept").contains("text/html"))
				{
					input += new ToTextHtml().parse(elem.getJson());
				}
				else
				{
					input += elem.getJson().toString();
				}
			}
			resp.setStatus(200);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}

		System.out.println("New Connection Received:"); //For test purposes.
		System.out.println(method + " " + path + " " + parameters); //For test purposes.
		System.out.println(input); //For test purposes.

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}


/**
 * 
 * @param req
 * @param resp
 * @throws IOException
 */
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String parameters = req.getQueryString();
		String path = getCommandStringFromRequest(req);
		String method = getCommandMethodFromRequest(req);
		CommandParser cp = null;
		String input = "";
		try {
			cp = getCommandParser();
			Result pr = cp.getCommand(method, path, parameters).call();
			for (AppElement elem : pr.getResults())
			{
				if (req.getHeader("Accept").contains("text/html"))
				{
					input += new ToTextHtml().parse(elem.getJson());
				}
				else
				{
					input += elem.getJson().toString();
				}
			}
			resp.setStatus(200);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}

		System.out.println("New Connection Received:"); //For test purposes.
		System.out.println(method + " " + path + " " + parameters); //For test purposes.
		int numBytes = Integer.parseInt(req.getHeader("Content-length")); //For test purposes.
		byte[] bytes = new byte [numBytes]; //For test purposes.
		req.getInputStream().read(bytes); //For test purposes.
		System.out.println("IO contents: " + new String(bytes)); //For test purposes.
		System.out.println(input); //For test purposes.

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}

	

/**
 * 
 * @param req
 * @param resp
 * @throws IOException
 */
	public void doPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		InputStream inputStream = req.getInputStream();
		
		int numBytes = Integer.parseInt(req.getHeader("Content-length"));
		byte[] bytes = new byte [numBytes];
		inputStream.read(bytes);
		
		String parameters = new String(bytes) + "&accept="+ req.getHeader("Accept");
		resp.setContentType("application/json");
		
		
		String path = getCommandStringFromRequest(req);
		String method = getCommandMethodFromRequest(req);
		CommandParser cp = null;
		String input = "";
		try {
			cp = getCommandParser();
			Result pr = cp.getCommand(method, path, parameters).call();
			for (AppElement elem : pr.getResults())
			{
				if (req.getHeader("Accept").contains("text/html"))
				{
					input += new ToTextHtml().parse(elem.getJson());
				}
				else
				{
					input += elem.getJson().toString();
				}
			}
			resp.setStatus(200);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}

		System.out.println("New Connection Received:"); //For test purposes.
		System.out.println(method + " " + path + " " + parameters); //For test purposes.
		//int numBytes = Integer.parseInt(req.getHeader("Content-length")); //For test purposes.
		//byte[] bytes = new byte [numBytes]; //For test purposes.
		req.getInputStream().read(bytes); //For test purposes.
		System.out.println("IO contents: " + new String(bytes)); //For test purposes.
		System.out.println(input); //For test purposes.

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}


	/**
	 * Gets the singleton {@link CommandParser}.
	 * 
	 * @return the application {@link CommandParser}
	 * @throws CommandParserException
	 */
	private CommandParser getCommandParser() throws CommandParserException {
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

	/**
	 * 
	 * @param req
	 * @return
	 */
	private String getCommandMethodFromRequest(HttpServletRequest req) {
		return req.getMethod();
	}

	
	/**
	 * Method responsable for verify which Status Error Code have the response from the server and send it's message to client.
	 * @param resp provide HTTP Status Code and Error message.
	 * @param e Exception
	 * @return Error Message
	 * @throws IOException 
	 */
	private void getErrorMessage(HttpServletResponse resp, Exception e) throws IOException{
		String message;
		if (e.getMessage() == null)
			message = "";
		else
			message = e.getMessage();
		if (e.getClass().equals(UnknownCommandException.class)) {
			resp.sendError(404, message);
			System.out.println("Error " + 404 + " - " + message); //For test purposes.
		} else if (e.getClass().equals(
				InvalidCommandArgumentsException.class)
				|| e.getClass().equals(DuplicateArgumentsException.class)) {
			resp.sendError(400, message);
			System.out.println("Error " + 400 + " - " + message); //For test purposes.
		} else if (e.getClass().equals(InvalidUserException.class)
				|| e.getClass().equals(NoSuchUsernameException.class)
						|| e.getClass().equals(IncorrectPasswordException.class)) {
			resp.sendError(401, message);
			System.out.println("Error " + 401 + " - " + message); //For test purposes.
		} else if (e.getClass().equals(
				MandatoryParameterNotPresentException.class)) {
			resp.sendError(400, message);
			System.out.println("Error " + 400 + " - " + message); //For test purposes.
		} else {
			resp.sendError(500, message);
			System.out.println("Error " + 500 + " - " + message); //For test purposes.
		}
	}
	
}
