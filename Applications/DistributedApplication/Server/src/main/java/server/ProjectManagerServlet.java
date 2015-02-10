package server;

import java.io.IOException;
import java.io.OutputStream;

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

@SuppressWarnings("serial")
public class ProjectManagerServlet extends HttpServlet {

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
					input += new ToTextHtml().parse(pr.getResults()[0].getJson());
				}
				else
				{
					input += elem.getJson().toString();
				}
			}
			resp.setStatus(200);
		} catch (Exception e) {
			String message;
			if (e.getMessage() == null)
				message = "";
			else
				message = e.getMessage();
			if (e.getClass().equals(UnknownCommandException.class)) {
				resp.sendError(404, message);
			} else if (e.getClass().equals(
					InvalidCommandArgumentsException.class)
					|| e.getClass().equals(DuplicateArgumentsException.class)) {
				resp.sendError(400, message);
			} else if (e.getClass().equals(InvalidUserException.class)) {
				resp.sendError(401, message);
			} else if (e.getClass().equals(
					MandatoryParameterNotPresentException.class)) {
				resp.sendError(400, message);
			} else {
				resp.sendError(500, message);
			}
		}

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
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
				input += elem.getJson().toString();
			resp.setStatus(200);
		} catch (Exception e) {
			String message;
			if (e.getMessage() == null)
				message = "";
			else
				message = e.getMessage();
			if (e.getClass().equals(UnknownCommandException.class)) {
				resp.sendError(404, message);
			} else if (e.getClass().equals(
					InvalidCommandArgumentsException.class)
					|| e.getClass().equals(DuplicateArgumentsException.class)) {
				resp.sendError(400, message);
			} else {
				resp.sendError(500, message);
			}
		}

		OutputStream out = resp.getOutputStream();
		out.write(input.getBytes());
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// give string to command parser and get command
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// give string to command parser and get command
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

	private String getCommandMethodFromRequest(HttpServletRequest req) {
		return req.getMethod();
	}

}
