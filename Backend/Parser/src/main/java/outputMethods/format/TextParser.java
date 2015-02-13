package outputMethods.format;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that converts results to the desired format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public interface TextParser{

	/**
	 * Parses the <@code JSONObject> to a string in the desired format. All the
	 * information contained in the <@code JSONObject> is parsed into a readable
	 * format.
	 * 
	 * @param jsonObject
	 * @return a string with all the information contained in the <@code
	 *         JSONObject>
	 */
	public String parse(JSONObject jsonObject);
	
	/**
	 * Parses the <@code JSONArray> to a string in the desired format. All the
	 * information contained in the <@code JSONArray> is parsed into a readable
	 * format.
	 * 
	 * @param jsonArray
	 * @return a string with all the information contained in the <@code
	 *         JSONArray>
	 */
	public String parse(JSONArray jsonArray);

	/**
	 * Parses the information in each element of the array to a string in the
	 * desired format. All the information contained in the <@code JSONObject>s
	 * is parsed into a readable format.
	 * 
	 * @param jsonObject
	 * @return a string with all the information contained in the <@code
	 *         JSONObject>
	 */
	public String parse(JSONObject[] toWrite);

	/**
	 * Returns an {@code Iterable<String>} that goes over all the keys of the
	 * {@code JSONObject} passed as parameter, in an alphabetical order
	 * 
	 * @param jsonObject
	 * @return an {@code Iterable<String>} with all the keys of the
	 *         {@code JSONObject}
	 */
	static Iterable<String> getOrderedKeySet(JSONObject jsonObject){
		Iterator<String> iterator = jsonObject.keys();
		SortedSet<String> keySet = new TreeSet<String>();
		while (iterator.hasNext()){
			keySet.add(iterator.next());
		}
		return keySet;
	}

	/**
	 * Basic method that allows to parse an array of {@code JSONObject}s using
	 * the method {@code parse(JSONObject jsonObject)}. It also allows to define
	 * a {@code String} that will be used to separate the different elements of
	 * the array.
	 * 
	 * @param toWrite
	 *            - array of {@code JSONObject}s that is to be parsed
	 * @param separator
	 *            - {@code String} that will fill the line that separates the
	 *            different elements of the array
	 * @param parser
	 *            - which parser is to be used
	 * @return a {@code String} with the information contained in all elements
	 *         of the array of {@code JSONObject}s parsed into the desired
	 *         format
	 */
	static String parseArray(JSONObject[] toWrite, String separator,
			TextParser parser){
		if (toWrite.length == 1){
			return parser.parse(toWrite[0]);
		} else {
			StringBuilder builder = new StringBuilder();
			for (JSONObject json : toWrite){
				builder.append(parser.parse(json)).append(separator)
						.append("\n");
			}
			return builder.toString();
		}
	}
}