package publishers;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TableBuilder {
	
	public static JTable buildTable(String elements){
		
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
	
	private static String[] getColumnNames(JsonElement element) {
		
		JsonObject object = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = object.entrySet();
		
		String[] columnElements = new String[set.size()];
		Iterator<Entry<String, JsonElement>> iterator = set.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			columnElements[i++] = iterator.next().getKey();
		}
		
		return columnElements;
	}
	
	private static String[] getLineData(JsonElement element, String[] columnNames) {
		
		String[] line = new String[columnNames.length];
		
		JsonObject object = element.getAsJsonObject();
		Set<Entry<String, JsonElement>> set = object.entrySet();
		Iterator<Entry<String, JsonElement>> iterator = set.iterator();
		
		int i = 0;
		while(iterator.hasNext()) {
			line[i++] = iterator.next().getValue().getAsString();
		}
		
		return line;
	}
	
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
