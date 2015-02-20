package servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.IO;

/**
 * One of Project Manager Server's HTTP servlet. This servlet is responsible
 * for handling the requests for the favorite icon.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 15/02/2015
 */
public class HugoServlet extends HttpServlet {

	private static final long serialVersionUID = -1021895737781619740L;

	/**
	 * Method for handling the GET request for the favicon.ico.
	 * 
	 * @see super{@link #doGet(HttpServletRequest, HttpServletResponse)}
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		URL fav = this.getClass().getClassLoader().getResource("hugo.png");
		if (fav != null) {
			byte[] favicon = IO.readBytes(fav.openStream());
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("image/x-icon");
			resp.setContentLength(favicon.length);
			resp.getOutputStream().write(favicon);
		}
	}

}
