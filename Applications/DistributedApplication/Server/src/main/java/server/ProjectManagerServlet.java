package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

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
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String method = req.getMethod();
		if (method.equals("PATCH")) {
			doPatch(req, resp);
			return;
		} else {
			super.service(req, resp);
		}
	}

	/**
	 * Turns the array of {@code AppElement}s into a string in the JSON format.
	 * 
	 * @param result
	 * @return a string with the information contained in the array of
	 *         {@code AppElement}s
	 */
	private static String toString(AppElement[] result) {
		return toJSONObject(result).toString();
	}

	/**
	 * Turns the array of {@code AppElement}s into a {@code JSONObject}.
	 * 
	 * @param result
	 * @return a a {@code JSONObject} with the information contained in the
	 *         array of {@code AppElement}s
	 */
	private static JSONObject toJSONObject(AppElement[] result) {
		JSONArray array;
		if (result.length == 1) {
			return result[0].getJson();
		} else {
			array = new JSONArray();
			for (int i = 0; i < result.length; i++) {
				array.put(result[i].getJson());
			}
		}
		return array.toJSONObject(array);
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
		doGetAndDelete(req, resp);
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
		doPostAndPatch(req, resp);
	}

	private String getInputStreamContents(HttpServletRequest req) throws IOException{
		InputStream inputStream = req.getInputStream();
		int numBytes = Integer.parseInt(req.getHeader("Content-length"));
		byte[] bytes = new byte[numBytes];
		inputStream.read(bytes);
		return new String(bytes);
	}
	
	private void callAuthenticateCommand(String parameters, CommandParser cp) throws Exception{
		cp.getCommand("GET", "/authenticate", parameters).call();
	}
	
	private String callRequestedCommand(String method, String path, String parameters, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		try {
			CommandParser cp = getCommandParser();
			if(method.equals("GET"))
				callAuthenticateCommand(parameters, cp);
			Result pr = cp.getCommand(method, path, parameters).call();
			if (req.getHeader("Accept").contains("text/html")) {
				return new ToTextHtml().parse(toJSONObject(pr.getResults()));
			} else {
				return toString(pr.getResults());
			}
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}
		return null;
	}
	
	private void doPostAndPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println(); // For test purposes.
		System.out.println("New Connection Received:"); // For test purposes.

		String method = getCommandMethodFromRequest(req);
		String path = getCommandStringFromRequest(req);
		
		String parameters = getInputStreamContents(req) + "&accept="
				+ req.getHeader("Accept");
		resp.setContentType("application/json");

		System.out.println(method + " - " + path + " - " + parameters); // For
																		// test
																		// purposes.

		String input = callRequestedCommand(method, path, parameters, req, resp);
		resp.setStatus(200);

		System.out.println(method + " - " + path + " - IO constents: "
				+ parameters); // For test purposes.
		System.out.println(input); // For test purposes.

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}

	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	System.out.println("sucesso");}

/**
 * 
 * @param req
 * @param resp
 * @throws IOException
 */
//	public void doPatch(HttpServletRequest req, HttpServletResponse resp)
	private void doGetAndDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println(); // For test purposes.
		System.out.println("New Connection Received:"); // For test purposes.

		String method = getCommandMethodFromRequest(req);
		String path = getCommandStringFromRequest(req);

		String parameters = req.getQueryString();
		
		System.out.println(method + " - " + path + " - " + parameters); // For
																		// test
																		// purposes.

		String input = callRequestedCommand(method, path, parameters, req, resp);
		resp.setStatus(200);

		try // For test purposes.
		{ // For test purposes.
			int numBytes = Integer.parseInt(req.getHeader("Content-length")); // For
																				// test
																				// purposes.
			byte[] bytes = new byte[numBytes]; // For test purposes.
			req.getInputStream().read(bytes); // For test purposes.
			System.out.println("IO contents: " + new String(bytes)); // For test
																		// purposes.
		} // For test purposes.
		catch (NumberFormatException e) { // For test purposes.
		} // For test purposes.
		System.out.println(input); // For test purposes.

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
		doGetAndDelete(req, resp);
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void doPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPostAndPatch(req, resp);
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
	 * Method responsable for verify which Status Error Code have the response
	 * from the server and send it's message to client.
	 * 
	 * @param resp
	 *            provide HTTP Status Code and Error message.
	 * @param e
	 *            Exception
	 * @return Error Message
	 * @throws IOException
	 */
	private void getErrorMessage(HttpServletResponse resp, Exception e)
			throws IOException {
		String message;
		if (e.getMessage() == null)
			message = "";
		else
			message = e.getMessage();
		if (e.getClass().equals(UnknownCommandException.class)) {
			resp.sendError(404, message);
			System.out.println("Error " + 404 + " - " + message); // For test
																	// purposes.
		} else if (e.getClass().equals(InvalidCommandArgumentsException.class)
				|| e.getClass().equals(DuplicateArgumentsException.class)) {
			resp.sendError(400, message);
			System.out.println("Error " + 400 + " - " + message); // For test
																	// purposes.
		} else if (e.getClass().equals(InvalidUserException.class)
				|| e.getClass().equals(NoSuchUsernameException.class)
				|| e.getClass().equals(IncorrectPasswordException.class)) {
			resp.sendError(401, message);
			System.out.println("Error " + 401 + " - " + message); // For test
																	// purposes.
		} else if (e.getClass().equals(
				MandatoryParameterNotPresentException.class)) {
			resp.sendError(400, message);
			System.out.println("Error " + 400 + " - " + message); // For test
																	// purposes.
		} else {
			resp.sendError(500, message);
			System.out.println("Error " + 500 + " - " + message); // For test
																	// purposes.
		}
	}

}
