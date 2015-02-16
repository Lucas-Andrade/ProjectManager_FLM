package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commandAndResult.ResultFormater;

import outputMethods.Result;
import parser.CommandParser;
import parserCommands.exceptions.InvalidUserException;
import parserCommands.exceptions.MandatoryParameterNotPresentException;
import parserUtils.CommandParserException;
import parserUtils.DuplicateArgumentsException;
import parserUtils.InvalidCommandArgumentsException;
import parserUtils.UnknownCommandException;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;

/**
 * Project Manager Server's HTTP servlet which serves as an intermediary for the
 * Clients and the Project Manager Domain. This servlet translates the client's
 * request, sends the translated request to the entity responsible for computing
 * it (calls the parser's commands), receives the computation results and
 * "translates" them, finally sends the response to the client.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 15/02/2015
 */
public class ProjectManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 3890240570465772949L;

	/**
	 * Adds support for the "PATCH" method.
	 * 
	 * @see super
	 *      {@link #service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)}
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
	 * If user authentication is successful, processes the response, else sends
	 * an error to the client.
	 * 
	 * @see super{@link #doGet(HttpServletRequest, HttpServletResponse)}
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String parameters = req.getQueryString();

		try {
			callAuthenticateCommand(parameters);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}

		processResponse(req, resp, parameters);
	}

	/**
	 * @see super{@link #doDelete(HttpServletRequest, HttpServletResponse)}
	 */
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String parameters = req.getQueryString();
		processResponse(req, resp, parameters);
	}

	/**
	 * @see super{@link #doPost(HttpServletRequest, HttpServletResponse)}
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPostOrPatch(req, resp);
	}

	/**
	 * Method for handling the PATCH requests.
	 * 
	 * @param req
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * @param resp
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * @exception IOException
	 *                if an input or output error is detected when the servlet
	 *                handles the request
	 */
	public void doPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPostOrPatch(req, resp);
	}

	/**
	 * Method for handling POST and PATCH requests.
	 * 
	 * @param req
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * @param resp
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * @exception IOException
	 *                if an input or output error is detected when the servlet
	 *                handles the request
	 */
	private void doPostOrPatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String parameters = getInputStreamContents(req) + "&accept="
				+ req.getHeader("Accept");
		processResponse(req, resp, parameters);
	}

	/**
	 * Method that calls the parser's command responsible for user
	 * authentication. All client's requests must be made by an authenticated
	 * user, yet the parser's GET commands doesn't require an authenticated
	 * user, so this method is called for all the client's requests with GET
	 * method.
	 * 
	 * @param parameters
	 *            The {@code Command} arguments.
	 * @throws Exception
	 */
	private void callAuthenticateCommand(String parameters) throws Exception {
		getCommandParser().getCommand("GET", "/authenticate", parameters)
				.call();
	}

	/**
	 * Processes the response to the client, according to it's request. If a bad
	 * request was made sends an error to the client.
	 * 
	 * @param req
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * @param resp
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * @param parameters
	 *            The {@code Command} arguments.
	 * @throws IOException
	 */
	private void processResponse(HttpServletRequest req,
			HttpServletResponse resp, String parameters) throws IOException {
		String method = req.getMethod();
		String path = req.getRequestURI();

		try {
			Result results = getResult(method, path, parameters);
			String commandResult = resultFormat(results, req, resp);
			writeResponse(commandResult, resp);
		} catch (Exception e) {
			getErrorMessage(resp, e);
		}
	}

	/**
	 * Calls the parser's command with the method, path and parameters supplied
	 * by the client, returns the {@code Result}s.
	 * 
	 * @param method
	 *            The request method (i.e. GET, POST)
	 * @param path
	 *            The resource path
	 * @param parameters
	 *            The {@code Command} arguments.
	 * @return The {@code Result}s.
	 * @throws Exception
	 */
	private Result getResult(String method, String path, String parameters)
			throws Exception {
		return getCommandParser().getCommand(method, path, parameters).call();
	}

	/**
	 * Formats the {@code Result}s into a text String in application/json if the
	 * client didn't mention text/html in the header field Accept, or in
	 * text/html if the client did mention it.
	 * 
	 * @param results
	 *            The command's {@code Result}s.
	 * @param req
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * @param resp
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * @return A String with the command's result.
	 */
	private String resultFormat(Result results, HttpServletRequest req,
			HttpServletResponse resp) {
		if (req.getHeader("Accept").contains("text/html")) {
			resp.setContentType("text/html");
			return ResultFormater.toHTMLString(results.getResults());
		} else {
			resp.setContentType("application/json");
			return ResultFormater.toJSONString(results.getResults());
		}
	}

	/**
	 * Writes a successful response to the client.
	 * 
	 * @param commandResult
	 *            A String with the command's result.
	 * @param resp
	 *            an {@link HttpServletResponse} object that contains the
	 *            response the servlet sends to the client
	 * @throws IOException
	 */
	private void writeResponse(String commandResult, HttpServletResponse resp)
			throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);

		resp.setCharacterEncoding("UTF-8");
		OutputStream out = resp.getOutputStream();
		out.write(commandResult.getBytes("UTF-8"));
	}

	/**
	 * Method responsible for verifying which Status Error Code and error
	 * message should be sent to the client.
	 * 
	 * @param resp
	 *            The response for the client.
	 * @param e
	 *            The exception caught while analyzing the client's request and
	 *            processing the response.
	 * @return Error Message
	 * @throws IOException
	 */
	private void getErrorMessage(HttpServletResponse resp, Exception e)
			throws IOException {
		String message;
		if (e.getMessage() == null)
			message = "";
		else {
			message = e.getMessage();
		}
		if (e.getClass().equals(UnknownCommandException.class)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, message);
		} else if (e.getClass().equals(InvalidCommandArgumentsException.class)
				|| e.getClass().equals(DuplicateArgumentsException.class)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
		} else if (e.getClass().equals(InvalidUserException.class)
				|| e.getClass().equals(NoSuchUsernameException.class)
				|| e.getClass().equals(IncorrectPasswordException.class)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
		} else if (e.getClass().equals(
				MandatoryParameterNotPresentException.class)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
		} else {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
		}
	}

	/**
	 * Get's from the client's POST or PATCH request the {@code Command}
	 * arguments necessary for computing a result.
	 * 
	 * @param req
	 *            an {@link HttpServletRequest} object that contains the request
	 *            the client has made of the servlet
	 * @return A String with the {@code Command} arguments
	 * @throws IOException
	 */
	private String getInputStreamContents(HttpServletRequest req)
			throws IOException {
		InputStream inputStream = req.getInputStream();
		int numBytes = Integer.parseInt(req.getHeader("Content-length"));
		byte[] bytes = new byte[numBytes];
		inputStream.read(bytes);
		return new String(bytes, "UTF-8");
	}

	/**
	 * Gets the singleton {@link CommandParser}.
	 * 
	 * @return The application {@link CommandParser}.
	 * @throws CommandParserException
	 */
	private CommandParser getCommandParser() throws CommandParserException {
		return CommandParser.getInstance();
	}

}
