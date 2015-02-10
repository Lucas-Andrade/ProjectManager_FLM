package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import parser.CommandParser;
import parserUtils.CommandParserException;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class ProjectManagerServerApp {

	private static final int LISTEN_PORT = 4567;

	public static void main(String[] args) throws Exception {
		constructParser();
		
		Server server = constructServer();

		server.start();
		System.out.println("Server started.");
		System.in.read();
		server.stop();
		System.out.println("Server stopped.");
	}

	private static Server constructServer() throws Exception {
		Server server = new Server(LISTEN_PORT);
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(ProjectManagerServlet.class, "/*");
		handler.addServletWithMapping(FaviconIcoServlet.class, "/favicon.ico"); //TODO
		return server;
	}

	private static void constructParser() throws CommandParserException {
		CommandParser.register(new ServerCommandsRegister(
				new InMemoryUserRepo(), new InMemoryProjectRepo(),
				new InMemoryWorkerRepo()));
	}

}
