package outputMethods.format;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that converts results to plain text format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class ToTextPlain implements TextParser
{

	/**
	 * Parses the information contained in the {@code JSONObject} into plain
	 * text.
	 * 
	 * @see TextParser#parse(jsonObject)
	 */
	@Override
	public String parse(JSONObject jsonObject)
	{
		return parse(jsonObject, "\n", 0);
	}

	/**
	 * Parses the information contained in the {@code JSONObject} passed as
	 * parameter into plain text, using the specified separation between lines
	 * and the required indentation of the line.
	 * 
	 * @param jsonObject
	 *            - the object to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line.
	 * @return the information contained in the {@code JSONObject} passed as
	 *         parameter parsed into plain text
	 */
	private static String parse(JSONObject jsonObject, String lineSeparator,
			int indentation)
	{
		Iterable<String> jsonKeys = TextParser.getOrderedKeySet(jsonObject);
		StringBuilder builder = new StringBuilder();

		for (String key : jsonKeys)
		{
			builder.append(indent(indentation)).append(key).append(": ");

			if ((jsonObject.get(key).getClass()).equals(new JSONObject()
					.getClass()))
				builder.append(parse((JSONObject) jsonObject.get(key), ", ", 0))
						.append(lineSeparator);

			else if (jsonObject.get(key) instanceof JSONObject[])
				builder.append(lineSeparator).append(
						parseArray((JSONObject[]) jsonObject.get(key),
								lineSeparator, indentation + 5));

			else if (jsonObject.get(key) instanceof JSONArray)
				builder.append(lineSeparator).append(
						parseJSONArray((JSONArray) jsonObject.get(key),
								lineSeparator, indentation));

			else
				builder.append(jsonObject.get(key)).append(lineSeparator);
		}
		return builder.toString();
	}

	/**
	 * the information in each of the elements of the {@code JSONArray} passed
	 * as parameter will be parsed into plain text. The information of each
	 * element is separated from the others using five dashes
	 * 
	 * @param jsonArray
	 *            - the object to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line.
	 * @return the information contained in the {@code JSONArray} as plain text
	 */
	private static Object parseJSONArray(JSONArray jsonArray,
			String lineSeparator, int indentation)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length(); i++)
		{
			builder.append("-----\n").append(
					parse((JSONObject) jsonArray.get(i), lineSeparator,
							indentation));
		}
		return builder.toString();
	}

	/**
	 * The integer number passed as parameter is parsed into plain text.
	 * 
	 * @param indentation
	 *            - the number of blank spaces to be left at the beginning of
	 *            the line
	 * @return a string with the desired number of spaces
	 */
	private static String indent(int indentation)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < indentation; i++)
			builder.append(" ");
		return builder.toString();
	}

	/**
	 * The information in each element of the array of {@code JSONObject} is
	 * parsed into plain text.
	 * 
	 * @param jsonArray
	 *            - the array to be parsed
	 * @param lineSeparator
	 *            - the separation to be used between the lines
	 * @param indentation
	 *            - the number of spaces needed in the beginning of the line.
	 * @return the information contained in the elements of the array passed as
	 *         parameter as plain text
	 */
	private static String parseArray(JSONObject[] jsonArray,
			String lineSeparator, int indentation)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jsonArray.length; i++)
			builder.append(parse(jsonArray[i], "\n", indentation)).append("\n");
		return builder.toString();
	}

	/**
	 * @see TextParser#parse(JSONObject[])
	 */
	@Override
	public String parse(JSONObject[] toWrite)
	{
		return TextParser.parseArray(toWrite, "----", this);
	}
}
