package app.resultsAndOutputMethods.outputFormat;

import java.util.Iterator;
import java.util.TreeSet;
import org.json.JSONObject;

public interface TextParser {
	
	/**
	 * Parses the <@code JSONObject> to a string in the desired format.
	 * All the information contained in the <@code JSONObject> is parsed into a readable format.
	 * 
	 * @param jsonObject
	 * @return a string with all the information contained in the <@code JSONObject>
	 */
	public String parse(JSONObject jsonObject);
	
	/**
	 * Returns an {@code Iterable<String>} that goes over all the keys of the {@code JSONObject}
	 * passed as parameter, in an alphabetical order
	 * @param jsonObject
	 * @return an {@code Iterable<String>} with all the keys of the {@code JSONObject}
	 */
	public static Iterable<String> getOrderedKeySet(JSONObject jsonObject)
	{
		Iterator<String> iterator = jsonObject.keys();
		TreeSet<String> keySet = new TreeSet<String>();
		while (iterator.hasNext())
			keySet.add(iterator.next());
		return keySet;
	}
}