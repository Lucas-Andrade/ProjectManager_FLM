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
			System.out.println("Descobrimos um array");
			JsonArray array = element.getAsJsonArray();
			parseArray(array, root);
			
		} else if(element.isJsonObject()) {
			System.out.println("Descobrimos um object");
			System.out.println(element.toString());
			root.add(parseObject(element.getAsJsonObject()));
			
		} else if(element.isJsonPrimitive()){
			System.out.println("Descobrimos uma primitiva");
			root.add(parsePrimitive(element.getAsJsonPrimitive(), nameOfProperty));
			
		} else if(element.isJsonNull()) {
			root.add(parseNull());
		} else {
			System.out.println("unknown type of json element. this should never happen");
		}
		
	}

	private static MutableTreeNode parseNull() {
		return new DefaultMutableTreeNode("Empty");
	}

	private static void parseArray(JsonArray array, DefaultMutableTreeNode root) {
		Iterator<JsonElement> iterator = array.iterator();
		while(iterator.hasNext()){
			JsonElement arrayElement = iterator.next();
			constructTree(root, arrayElement, "");
		}
	}

	private static DefaultMutableTreeNode parsePrimitive(JsonPrimitive primitive, String nameOfProperty) {
		System.out.println("valor da primitiva: " + primitive.getAsString());
		return new DefaultMutableTreeNode(nameOfProperty + ": " + primitive.getAsString());
	}

	private static DefaultMutableTreeNode parseObject(JsonObject object) {
		
		System.out.println("vamos parsear o objecto");
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

//	private static DefaultMutableTreeNode parseSubprojects(JsonElement element) {
//		
//		System.out.println("ENTROU NO PARSE SUBPROJECT");
//		DefaultMutableTreeNode subprojectsNode = new DefaultMutableTreeNode("Subprojects");
//		
//		if(element.isJsonNull()) {
//			System.out.println("É NULL");
//			return new DefaultMutableTreeNode("Subprojects: None.");
////		} else if(element.isJsonArray()) {
////			System.out.println("É ARRAY");
////			DefaultMutableTreeNode subprojectsNode = new DefaultMutableTreeNode("Subprojects");
////			
////			JsonArray array = element.getAsJsonArray();
////			parseArray(array, subprojectsNode);
////			
////			return subprojectsNode;
//		} else {
//			if(element.isJsonArray()) {
//				System.out.println("É ARRAY");
//			} else if(element.isJsonObject()) {
//				System.out.println("É OBJECTO");
//			} else if(element.isJsonPrimitive()) {
//				System.out.println("É PRIMITIVA");
//			}
//			constructTree(subprojectsNode, element);
//			return subprojectsNode;
//		}
//	}

	private static String getName(JsonElement element) {

		if(element.isJsonObject()) {
			System.out.println("vamos tirar o nome do object");
			JsonObject object = element.getAsJsonObject();
			
			Set<Entry<String, JsonElement>> set = object.entrySet();
			Iterator<Entry<String, JsonElement>> iterator = object.entrySet().iterator();
			
			boolean consultantFlag = false;
			
			while(iterator.hasNext()) {
				String key = iterator.next().getKey();
				
				if("Project ID".equals(key)){
					return "Project";
				} 
	
				if("Username".equals(key)){
					return "User";
				} 
	
				if("Bonus".equals(key)){
					return "Manager";
				}
				
				if("Consultant ID".equals(key)){
					consultantFlag = true;
				} 
	
				if("Longitude".equals(key)){
					return "Local";
				}
				
				if(set.size() == 1) {
					return key;
				}
			}
			
			if (consultantFlag) {
				return "Worker";
			} else {
				return "Item";
			}
			
		}else if(element.isJsonArray()) {
			System.out.println("no arrays allowed");
		} else {
			System.out.println("no other things allowed.");
		}
		return "Item";
	}

	
}
