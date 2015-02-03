package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import parser.CommandParser;

public class ProjectManagerServerApp{

	private static final int LISTEN_PORT = 9999;
	public static final CommandParser PARSER = constructParser();
	
	public static void main(String[] args) throws Exception {
		constructServer();
	}

	private static void constructServer() throws Exception {
		Server server = new Server(LISTEN_PORT);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        
        handler.addServletWithMapping(ProjectManagerServlet.class, "/*");
        
        server.start();
        System.out.println("Server is started");
        
        System.in.read();
        server.stop();
        System.out.println("Server is stopped, it was an honor serving you.");
	}

	private static CommandParser constructParser() {
		
		// TODO Auto-generated method stub
		return null;
	}
	

}
