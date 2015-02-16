package publishers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class has a single public static method, which job is to parse the {@code String}
 * passed as parameter in JSON format, into a {@code JTable}.
 * 
 * The name of the properties of the JSON string will be the names of the columns, and the content
 * of each property will be in the lines of the table.
 * 
 * This is useful only if the {@code String} contains a simple JSON object (where all elements are
 * just strings), or an array of JSON object, where all of them have the same simple properties. 
 * If a complex JSON object in introduced (for example, one that as an array as one of it's 
 * properties, a {@code TableBuilderException} is thrown.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class TableBuilder {
	
	/**
	 * A map which keys are the title of the columns of the {@code Jtable}, and the 
	 * elements are the index of the column that possesses the name.
	 */
	private static Map<String, Integer> columnsContent = new HashMap<String, Integer>();
	
	/**
	 * Parses the {@code String} passed as parameter into a {@code JTable}.
	 * If the {@code String} could not be parsed a {@code TableBuilderException} is
	 * thrown.
	 * 
	 * @param elements
	 * @return a {@code JTable} with the information contained in the {@code String}.
	 * @throws TableBuilderException 
	 */
	public static JTable buildTable(String elements) throws TableBuilderException{
		
		JsonElement element = new JsonParser().parse(elements);
		String[][] data;
		String[] columnNames;
		
		if(element.isJsonArray()) {
			JsonArray array = element.getAsJsonArray();
			columnNames = getColumnNames(array.get(0));
			data = getMultiLineData(array, columnNames);
		} else if(element.isJsonObject()) {
			JsonObject object = element.getAsJsonObject();
			Set<Entry<String, JsonElement>> set = object.entrySet();
			
			if(set.size() == 1) {
				return buildTable(set.iterator().next().getValue().toString());
			}
						
			columnNames = getColumnNames(element);
			data = new String[1][columnNames.length];
			data[0] = getLineData(element, columnNames);
			
		} else {
			throw new IllegalStateException();
		}
		
		return new JTable(data, columnNames);
	}
	
	/**
	 * Probes the {@code JsonElement} for the name of the properties
	 * that might be contained in it. With that information an array
	 * of {@code String}s is constructed and will be returned. Also,
	 * the field {@code columnElements} is updated to mirror the 
	 * information in the string.
	 * 
	 * @param element
	 * @return an array of {@code String}s with the names of the columns.
	 * @throws TableBuilderException 
	 */
	private static String[] getColumnNames(JsonElement element) throws TableBuilderException {
		
		JsonObject object = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = object.entrySet();
		
		String[] columnElements = new String[set.size()];
		Iterator<Entry<String, JsonElement>> iterator = set.iterator();
		int i = 0;
		
		try{
			while(iterator.hasNext()) {
				String columnName = iterator.next().getKey();
				columnsContent.put(columnName, i);
				columnElements[i++] = columnName;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TableBuilderException();
		}
		
		return columnElements;
	}
	
	/**
	 * Probes all properties of the {@code JsonElement} passed as parameter, 
	 * and constructs an array of {@code String}s, based on the elements present
	 * in the {@code JsonElement}.
	 * 
	 * @param element
	 * @param columnNames
	 * @return An array of {@code String}s containing the content of a line 
	 * 		that will be part of the {@code JTable}.
	 */
	private static String[] getLineData(JsonElement element, String[] columnNames) {
		
		String[] line = new String[columnNames.length];
		
		JsonObject object = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = object.entrySet();
		Iterator<Entry<String, JsonElement>> iterator = set.iterator();
		
		while(iterator.hasNext()) {
			Entry<String, JsonElement> entry = iterator.next();
			String cellContent = entry.getValue().getAsString();
			String columnTitle = entry.getKey();
			int column = columnsContent.get(columnTitle);
			line[column] = cellContent;
		}
		
		return line;
	}
	
	/**
	 * Creates one line for each of the elements of the {@code JsonArray} passed as parameter.
	 * 
	 * @param array
	 * @param columnNames
	 * @return A bi-dimensional array of {@code String}s containing the contents of the {@code JTable}.
	 */
	private static String[][] getMultiLineData(JsonArray array, String[] columnNames){
		String[][] data = new String[array.size()][columnNames.length];
		Iterator<JsonElement> iterator = array.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			data[i++] = getLineData(iterator.next(), columnNames);
		}
		return data;
	}
}
