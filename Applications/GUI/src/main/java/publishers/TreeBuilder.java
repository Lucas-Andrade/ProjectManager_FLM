package publishers;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * This class has a single public static method, which job is to parse the {@code String}
 * passed as parameter in JSON format, into a {@code JTree}.
 * 
 * Each property in the JSON object will be turned into a node of the {@code JTree}. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class TreeBuilder {
	
	/**
	 * Parses the {@code String} in a JSON format passed as parameter into a {@code JTree}.
	 * 
	 * @param elements
	 * 		- The {@code String} in JSON format
	 * @return A {@code JTree} with the information that was in the string passed as parameter
	 */
	public static JTree buildTree(String elements) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Results");
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		JsonElement element = new JsonParser().parse(elements);
		
		constructTree(root, element, "");
		return tree;
	}

	/**
	 * Adds nodes to the {@code DefaultMutableTreeNode} passed as parameter. The nodes are added according
	 * to whether the {@code JsonElement} passed as parameter is an instance of {@code JsonArray},
	 * {@code JsonObject}, {@code JsonPrimitive} or {@code JsonNull}.
	 * 
	 * The {@code String} passed as parameter is the name of the property that gave rise to this
	 * {@code JsonElement}. This will be used only in case that the {@code JsonElement} is an instance 
	 * of {@code JsonArray} or {@code JsonPrimitive}.
	 * 
	 * @param root
	 * 		- The {@code DefaultMutableTreeNode} to which nodes will be added
	 * @param element
	 * 		- The {@code JsonElement} that will be parsed into the nodes 
	 * @param nameOfProperty
	 * 		- The name of the property that gave rise to the {@code JsonElement}
	 */
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

	/**
	 * Returns a {@code DefaultMutableTreeNode} with "Empty" as it's title.
	 * @return
	 */
	private static DefaultMutableTreeNode parseNull() {
		return new DefaultMutableTreeNode("Empty");
	}

	/**
	 * Parses the {@code JsonArray} passed as parameter into {@code DefaultMutableTreeNode}s.
	 * 
	 * A new {@code DefaultMutableTreeNode} is instantiated using the {@code String} passed as 
	 * parameter and is added to the {@code DefaultMutableTreeNode} passed as parameter.
	 * Each element of the {@code JsonArray} will give rise to one
	 * {@code DefaultMutableTreeNode} which is added to the new {@code DefaultMutableTreeNode}.
	 * 
	 * @param array
	 * 		- {@code JsonArray} to be parsed into {@code DefaultMutableTreeNode}s
	 * @param root
	 * 		- {@code DefaultMutableTreeNode} to which the parsed array is to be added to
	 * @param name
	 * 		- {@code String} the name of the new {@code DefaultMutableTreeNode} that will
	 * 		represent the parsed array 		
	 */
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
	
	/**
	 * Applies the method {@code TreeBuilder#constructTree(DefaultMutableTreeNode, JsonElement, String)}
	 * to each element of the {@code Iterator} parsed as array.
	 * 
	 * @param iterator
	 * 		- {@code iterator} of {@code JsonElement}s
	 * @param node
	 * 		- the {@code DefaultMutableTreeNode} to which the parsed elements of the iterator are
	 * 		are to be added to
	 * @param name
	 * 		- the name of the property that gave rise to the {@code Iterator} passed as parameters.
	 */
	private static void iterateAlongTheArray(Iterator<JsonElement> iterator, DefaultMutableTreeNode node, String name) {
		while(iterator.hasNext()){
			JsonElement arrayElement = iterator.next();
			constructTree(node, arrayElement, name);
		}
	}

	/**
	 * Parses the {@code JsonPrimitive} passed as parameter into a {@code DefaultMutableTreeNode}. 
	 * The name of the {@code DefaultMutableTreeNode} is composed of the {@code String} passed 
	 * as parameter and the value of the primitive as a {@code String}. If the content of the 
	 * {@code JsonPrimitive} cannot be shown as a string, a {@code ClassCastException} will
	 * be thrown.
	 * 
	 * @param primitive
	 * 		- The {@code JsonPrimitive} that is to be parsed into a {@code DefaultMutableTreeNode}
	 * @param nameOfProperty
	 * 		- The name of the property that gave rise to the {@code JsonPrimitive} passed as parameter.
	 * @return A {@code DefaultMutableTreeNode} that represents the {@code JsonPrimitive} 
	 * 		passed as parameter
	 */
	private static DefaultMutableTreeNode parsePrimitive(JsonPrimitive primitive, String nameOfProperty) {
		return new DefaultMutableTreeNode(nameOfProperty + ": " + primitive.getAsString());
	}

	/**
	 * Parses the {@code JsonObject} passed as parameter into a {@code DefaultMutableTreeNode}.
	 * Instantiates a new {@code DefaultMutableTreeNode} and adds to it all the data present in
	 * the {@code JsonObject} passed as parameter, using the method 
	 * {@code TreeBuilder#constructTree(DefaultMutableTreeNode, JsonElement, String)}.
	 * 
	 * @param object
	 * 		- {@code JsonObject} to parse to {@code DefaultMutableTreeNode}
	 * @return the {@code DefaultMutableTreeNode} with the information of the {@code JsonObject}
	 */
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

	/**
	 * Returns a {@code String} with the name that the node that represents the {@code JsonElement}
	 * passed as parameter should have. The name is determined by searching certain keywords 
	 * in the properties of the {@code JsonElement}. If none is found "Item" will be returned.
	 * 
	 * @param element
	 * @return The {@code String} that will constitute the name of a node.
	 */
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
