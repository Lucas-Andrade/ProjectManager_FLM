package outputMethods;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONObject;

import outputMethods.destination.ToConsole;
import outputMethods.destination.ToFile;
import outputMethods.destination.Writable;
import outputMethods.format.TextParser;
import outputMethods.format.ToTextPlain;
import app.AppElement;


/**
 * Class that stores the results of a Command execution and sends them to a
 * destination with the desired format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class Result {

	/**
	 * The results from a Command execution.
	 */
	private final AppElement[] results;

	/**
	 * The destination to where the results are to be sent.
	 */
	private Writable destination;

	/**
	 * The format that the results should take before being sent.
	 */
	private TextParser format;

	/**
	 * The constructor for a {@code Result}.
	 * 
	 * @param results
	 *            The results from a Command execution.
	 * @param destination
	 *            A String with the name of the destination to where the results
	 *            are to be sent.
	 * @param format
	 *            A String with the name of the format that the results should
	 *            take before being sent.
	 * @throws ClassNotFoundException
	 *             Exception thrown when the name of the format doesn't
	 *             correspond to a valid format.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 *             Exception thrown when the name of the destination doesn't
	 *             correspond to a valid destination.
	 */
	public Result(AppElement[] results, String destination, String format)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, FileNotFoundException{
		if (format == null || format == ""){
			format = ToTextPlain.class.getName();
			format = format.substring(format.lastIndexOf('.') + 3);
		}else{
			String[] formats = format.split("/");
			for (int i=0; i<formats.length; i++){
				formats[i]=formats[i].substring(0, 1).toUpperCase()+formats[i].substring(1);
			}
			format="";
			for (String fmt:formats){
				format+=fmt;
			}
		}

		String formatClassCompleteName = TextParser.class.getPackage()
				.getName() + ".To" + format;
		this.format = (TextParser) Class.forName(formatClassCompleteName)
				.newInstance();

		this.destination = (destination == null
				|| "console".equalsIgnoreCase(destination) || "".equalsIgnoreCase(destination))
				? new ToConsole()

				: new ToFile(destination);

		this.results = results;
	}
	
	/**
	 * @return the results of the {@code internalCall}
	 */
	public AppElement[] getResults(){
		return results;
	}

	/**
	 * Formats the results according to {@code format} and sends them to
	 * {@code destination}.
	 * 
	 * @throws IOException
	 */
	public void showResults() throws IOException{
		JSONObject[] toWrite = new JSONObject[results.length];
		int i = 0;
		for (AppElement result : results){
			toWrite[i++] = result.getJson();
		}
		destination.write(format.parse(toWrite));
	}
}