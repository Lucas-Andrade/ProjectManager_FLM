package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import commandAndResult.CommandParserConstructor;
import parserUtils.CommandParserException;
import servlets.FaviconIcoServlet;
import servlets.ProjectManagerServlet;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

/**
 * This is the Project Manager Server main class, which bridges the Clients and
 * the Project Manager Domain.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 15/02/2015
 */
public class ProjectManagerServerApp {

	/**
	 * The server's port.
	 */
	private static final int LISTEN_PORT = 8081;

	/**
	 * Main method, starts the application.
	 */
	public static void main(String[] args) throws Exception {
		instantiateBackendEntities();

		Server server = constructServer();

		server.start();

		System.in.read();
		server.stop();
		System.out.println("Server stopped.");
	}

	/**
	 * Instantiates the {@code Server} for the application ProjectManagerServer
	 * with a {@code ServletHandler} and the required {@code HttpServlet}s.
	 * 
	 * @return The instantiated {@code Server} with a {@code ServletHandler} and
	 *         the required {@code HttpServlet}s.
	 * @throws Exception
	 */
	private static Server constructServer() throws Exception {
		Server server = new Server(LISTEN_PORT);
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(ProjectManagerServlet.class, "/*");
		handler.addServletWithMapping(FaviconIcoServlet.class, "/favicon.ico");
		return server;
	}

	/**
	 * Instantiates the {@code CommandParser} and the {@code Repository}s.
	 * 
	 * @throws CommandParserException
	 */
	private static void instantiateBackendEntities()
			throws CommandParserException {
		new CommandParserConstructor().constructCommandParser(
				new InMemoryUserRepo(), new InMemoryProjectRepo(),
				new InMemoryWorkerRepo());
	}

}
