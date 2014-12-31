package app.resultsAndOutputMethods.outputFormat;

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
}