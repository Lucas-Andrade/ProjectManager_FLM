package outputMethods.format;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that converts results to HTML format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class ToTextHtml implements TextParser{

	/**
	 * Parses the information contained in the {@code JSONObject} into the HTML
	 * format.
	 * 
	 * @see TextParser#parse(jsonObject)
	 */
	@Override
	public String parse(JSONObject jsonObject){
		StringBuilder builder = new StringBuilder();
		return builder.append("<html>\n")
				.append("<head><meta http-equiv=\"Content-Type\"content=\"text/html;charset=UTF-8\"></head>")
				.append(parse(jsonObject, "</p>\n", 0)).append("</html>")
				.toString();
	}

	/**
	 * Parses the information contained in the {@code JSONObject} passed as
	 * parameter into the HTML format, using the specified separation between
	 * lines (in HTML format) and the required indentation of the line.
	 * 
	 * @param jsonObject
	 *            - the object to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line. If
	 *            a negative number is introduced, the information will not be
	 *            added in a new line (and will continue writing in the same
	 *            line). If zero is introduced, there will not be any blank
	 *            space at the beginning of the line, but the information will
	 *            be written in a new line.
	 * @return the information contained in the {@code JSONObject} passed as
	 *         parameter in HTML format
	 */
	private static String parse(JSONObject jsonObject, String lineSeparator,
			int indentation){
		Iterable<String> jsonKeys = TextParser.getOrderedKeySet(jsonObject);
		StringBuilder builder = new StringBuilder();

		for (String key : jsonKeys){
			builder.append(indent(indentation)).append("<b>").append(key)
					.append("</b>: ");

			if ((jsonObject.get(key).getClass()).equals(new JSONObject()
					.getClass())){
				builder.append(
						parse((JSONObject) jsonObject.get(key), ", ", -1))
						.append(lineSeparator);
			}else if (jsonObject.get(key) instanceof JSONObject[]){
				builder.append(lineSeparator).append(
						parseArray((JSONObject[]) jsonObject.get(key),
								lineSeparator, indentation + 5));
			}else if (jsonObject.get(key) instanceof JSONArray){
				builder.append(parseJSONArray((JSONArray) jsonObject.get(key),
						lineSeparator, indentation));
			}else {
				builder.append(jsonObject.get(key)).append(lineSeparator);
			}
		}
		return builder.toString();
	}

	/**
	 * the information in each of the elements of the {@code JSONArray} passed
	 * as parameter will be parsed into HTML format. The information of each
	 * element is separated from the others using an horizontal bar
	 * 
	 * @param jsonArray
	 *            - the object to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line. If
	 *            a negative number is introduced, the information will not be
	 *            added in a new line (and will continue writing in the same
	 *            line). If zero is introduced, there will not be any blank
	 *            space at the beginning of the line, but the information will
	 *            be written in a new line.
	 * @return the information contained in the {@code JSONArray} parsed into
	 *         HTML format
	 */
	private static Object parseJSONArray(JSONArray jsonArray,
			String lineSeparator, int indentation){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length(); i++){
			builder.append("<hr>\n").append(
					parse((JSONObject) jsonArray.get(i), lineSeparator,
							indentation));
		}
		return builder.toString();
	}

	/**
	 * The integer number passed as parameter is parsed into HTML format.
	 * 
	 * If a negative number is introduced, the information will not be added in
	 * a new line (and will continue writing in the same line). If zero is
	 * introduced, there will not be any blank space at the beginning of the
	 * line, but the information will be written in a new line.
	 * 
	 * @param indentation
	 *            - the number of blank spaces to be left at the beginning of
	 *            the line
	 * @return the correct HTML code to begin a new line with the desired
	 *         indentation
	 */
	private static String indent(int indentation){
		return indentation >= 0 ? "<p style=\"text-indent: " + indentation
				+ "em;\">" : "";
	}

	/**
	 * The information in each element of the array of {@code JSONObject} is
	 * parsed into HTML format.
	 * 
	 * @param jsonArray
	 *            - the array to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line. If
	 *            a negative number is introduced, the information will not be
	 *            added in a new line (and will continue writing in the same
	 *            line). If zero is introduced, there will not be any blank
	 *            space at the beginning of the line, but the information will
	 *            be written in a new line.
	 * @return the information contained in the elements of the array passed as
	 *         parameter in the HTML format
	 */
	private static String parseArray(JSONObject[] jsonArray,
			String lineSeparator, int indentation){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length; i++){
			builder.append(parse(jsonArray[i], lineSeparator, indentation))
					.append(lineSeparator);
		}
		return builder.toString();
	}

	/**
	 * @see TextParser#parse(JSONObject[])
	 */
	@Override
	public String parse(JSONObject[] toWrite){
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");

		for (JSONObject json : toWrite){
			builder.append(parse(json, "</p>\n", 0)).append("<hr>\n");
		}
		return builder.append("</html>").toString();
	}
}
