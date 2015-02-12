package publishers;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class TreeBuilder {
	
	public static JTree buildTree(String elements) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Results");
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		JsonElement element = new JsonParser().parse(elements);
		
		constructTree(root, element, "");
		return tree;
	}

	private static void constructTree(DefaultMutableTreeNode root, JsonElement element, String nameOfProperty) {
		
		if(element.isJsonArray()) {
			JsonArray array = element.getAsJsonArray();
			parseArray(array, root, nameOfProperty);
			
		} else if(element.isJsonObject()) {
			root.add(parseObject(element.getAsJsonObject()));
			
		} else if(element.isJsonPrimitive()){
			root.add(parsePrimitive(element.getAsJsonPrimitive(), nameOfProperty));
			
		} else if(element.isJsonNull()) {
			root.add(parseNull());
		}
		
	}

	private static MutableTreeNode parseNull() {
		return new DefaultMutableTreeNode("Empty");
	}

	private static void parseArray(JsonArray array, DefaultMutableTreeNode root, String name) {
		Iterator<JsonElement> iterator = array.iterator();
		
		if(!"".equals(name)){
			DefaultMutableTreeNode arrayNode = new DefaultMutableTreeNode(name);
			root.add(arrayNode);
			iterateAlongTheArray(iterator, arrayNode, name);
		} else {
			iterateAlongTheArray(iterator, root, name);
		}
		
	}
	
	private static void iterateAlongTheArray(Iterator<JsonElement> iterator, DefaultMutableTreeNode node, String name) {
		while(iterator.hasNext()){
			JsonElement arrayElement = iterator.next();
			constructTree(node, arrayElement, name);
		}
	}

	private static DefaultMutableTreeNode parsePrimitive(JsonPrimitive primitive, String nameOfProperty) {
		return new DefaultMutableTreeNode(nameOfProperty + ": " + primitive.getAsString());
	}

	private static DefaultMutableTreeNode parseObject(JsonObject object) {
		
		DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(getName(object));
		Iterator<Entry<String, JsonElement>> iterator = object.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Entry<String, JsonElement> entry = iterator.next();
			JsonElement element = entry.getValue();
			String key = entry.getKey();
			
			constructTree(objectNode, element, key);
		}
		
		return objectNode;
	}

	private static String getName(JsonElement element) {

		if(element.isJsonObject()) {
			JsonObject object = element.getAsJsonObject();
			
			Set<Entry<String, JsonElement>> set = object.entrySet();
			Iterator<Entry<String, JsonElement>> iterator = object.entrySet().iterator();
			
			boolean consultantFlag = false;
			
			while(iterator.hasNext()) {
				String key = iterator.next().getKey();
				
				if("Project ID".equals(key)){
					return key + ": " + object.get(key).getAsString();
				} else if("Username".equals(key)) {
					return "User";
				} else if("Bonus".equals(key)) {
					return "Manager";
				} else if("Consultant ID".equals(key)){
					consultantFlag = true;
				} else if("Longitude".equals(key)) {		
					return "Local";
				}
				
				if(set.size() == 1) {
					return key;
				}
			}
			
			if (consultantFlag) {
				return "Worker";
			}
		}
		return "Item";
	}

	
}
