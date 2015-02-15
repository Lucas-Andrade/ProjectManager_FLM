package commandAndResult;

import org.json.JSONArray;

import outputMethods.format.ToTextHtml;
import app.AppElement;

/**
 * This class has static methods for formating command results (arrays of
 * {@code AppElement}) into a String in application/json or text/html.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public class ResultFormater {

	/**
	 * The constructor for this class. This constructor is private.
	 */
	private ResultFormater() {
	};

	/**
	 * Turns an array of {@code AppElement}s into a string in the JSON format.
	 * 
	 * @param result
	 *            The result from a command computation.
	 * @return a string with the information contained in the array of
	 *         {@code AppElement}s
	 */
	public static String toJSONString(AppElement[] result) {

		if (result.length == 1) {
			return result[0].getJson().toString();
		} else {
			JSONArray array = new JSONArray();
			for (int i = 0; i < result.length; i++) {
				array.put(result[i].getJson());
			}
			return array.toString();
		}
	}

	/**
	 * Turns an array of {@code AppElement}s into a string in the HTML format.
	 * 
	 * @param result
	 *            The result from a command computation.
	 * @return a string with the information contained in the array of
	 *         {@code AppElement}s
	 */
	public static String toHTMLString(AppElement[] result) {

		if (result.length == 1) {
			return new ToTextHtml().parse(result[0].getJson());
		} else {
			JSONArray array = new JSONArray();
			for (int i = 0; i < result.length; i++) {
				array.put(result[i].getJson());
			}
			return new ToTextHtml().parse(array);
		}
	}

}
