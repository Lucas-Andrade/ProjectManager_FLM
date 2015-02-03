package server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProjectManagerServlet extends HttpServlet{

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String commandString = getCommandStringFromRequest(req);
		
		//give string to command parser and get command
    }
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
		String commandString = getCommandStringFromRequest(req);
		
		
	}

	private String getCommandStringFromRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
}
