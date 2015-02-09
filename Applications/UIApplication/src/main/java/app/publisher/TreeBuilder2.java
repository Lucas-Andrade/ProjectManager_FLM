package app.publisher;

import java.util.Iterator;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONObject;

import app.AppElement;

/**
 * This class has a single public method that accepts as parameter an array of {@code AppElement},
 * and returns a {@code JTree}, which nodes represent each element of the array. Each node has
 * several children-nodes that represent each property of the element. So, in essence, this class
 * is for parsing {@code AppElement} into {@code JTree}.
 * 
 * To do so, the JSON representation of the {@code AppElement} is used. Given that the {@code getJSON()}
 * method is implemented for all subclasses of {@code AppElement}. Each key of the {@code JSONObject}
 * gives rise to a new child-node, below the node belonging to the {@code AppElement} in question.
 * 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 *
 */
public class TreeBuilder2 {
	
	/**
	 * As this is an utility class, a private constructor was implemented in order to hide the
	 * implicit public one.
	 */
	private TreeBuilder2() {
	}
	
	/**
	 * Parses an array of {@code AppElement} into a {@code JTree} format, where all properties
	 * of the {@code AppElement} are stored as nodes.
	 * @param appElements
	 * @return a {@code JTree} representation of all the {@code AppElement}s in the array
	 */
	public static JTree getTree(AppElement[] appElements){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		for(AppElement element : appElements){
			root.add(getBranch(element));
		}
		return tree;
	}
	
	/**
	 * Constructs a branch of the {@code JTree}, based on a single {@code AppElement}. All the properties
	 * of that {@code AppElement} are represented as children-nodes of the node returned by this method.
	 * The node is represented by a {@code DefaultMutableTreeNode} object. 
	 * 
	 * @param element
	 * @return A {@code DefaultMutableTreeNode} with the representation of the {@code AppElement} passed
	 * as parameter.
	 */
	private static DefaultMutableTreeNode getBranch(AppElement element) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(getBranchName(element));
		
		JSONObject json = element.getJson();
		return constructBranch(node, json);
	}

	/**
	 * Attaches new children-nodes to the node passed as parameter, and returns it. The new nodes will 
	 * be built using all the keys stored in the {@code JSONObject} passed as parameter. So, each
	 * key of the {@code JSONObject} gives rise to a new child-node. 
	 * 
	 * If the object belonging to a certain key is a {@code JSONObject} itself (say, a secondary 
	 * {@code JSONObject}), its keys will become children-nodes of the key that pointed to the 
	 * secondary {@code JSONObject}.
	 * 
	 * If the object belonging to a certain key is an array, each object in the array will be 
	 * represented as a child node, of the node of the key. Each of those children-nodes, will have 
	 * children nodes of their own, if the array happens to be an array of {@code JSONObject}s.
	 * 
	 * @param node
	 * @param json
	 * @return The {@code DefaultMutableTreeNode} that was passed as parameter with the new branches.
	 */
	private static DefaultMutableTreeNode constructBranch(DefaultMutableTreeNode node, JSONObject json) {
		
		Iterator<String> iterator = json.keys();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object object = json.get(key);
			
			if(object instanceof JSONObject){
				node.add(constructBranch(new DefaultMutableTreeNode(key), (JSONObject)object));
			} else if(object.getClass().isArray()) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(key);
				node.add(newNode);
				addArrayOfNodes(newNode, (Object[])object);
			} else {
				node.add(new DefaultMutableTreeNode(key + ": " + object.toString()));
			}
		}
		return node;
	}

	/**
	 * Adds a node to the node passed as parameter for each of the {@code Object} in the array.
	 * If the array is an array of {@code JSONObject}s, each element is parsed into node format
	 * using the {@code constructBranch()} method.
	 * @param node
	 * @param arrayToAdd
	 */
	private static void addArrayOfNodes(DefaultMutableTreeNode node, Object[] arrayToAdd) {

		if(arrayToAdd.length == 0){
			node.add(new DefaultMutableTreeNode("empty"));
		}
		
		for(Object obj : arrayToAdd){
			if (obj instanceof JSONObject) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(getBranchName((JSONObject) obj));
				node.add(newNode);
				constructBranch(newNode, (JSONObject)obj);
			} else {
				node.add(new DefaultMutableTreeNode(obj.toString()));
			}
		}
	}

	/**
	 * Returns the name that will be given to the node that will represent the {@code AppElement}
	 * passed as parameter.
	 * 
	 * Returns the first line of the {@code String} returned by the {@code toString()} of the
	 * {@code AppElement}. This first line is usually the most important property of the {@code AppElement},
	 * and so it is a good candidate to name the node.
	 * 
	 * @param element
	 * @return A {@code String} with the name that should be given to a branch that represents
	 * and {@code AppElement}
	 */
	private static String getBranchName(AppElement element) {

		String elementString = element.toString();
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < elementString.length(); i++){
			if( elementString.charAt(i) == '\n'){
				return builder.toString();
			} else {
				builder.append(elementString.charAt(i));
			}
		}
		return "";
	}
	
	/**
	 * Returns the name that will be given to the node that will represent the {@code JSONObject}
	 * passed as parameter.
	 * 
	 * Certain keys are looked for, such as {@code "Project ID"}, {@code "Username"}, {@code "Consultant ID"},
	 * or {@code "Name"}, which would be a good way of charaterizing the object represented by this 
	 * {@code JSONObject}. If none of those are found, the branch is given a generic name, such as {@code "Item"}.
	 * 
	 * 
	 * @param json
	 * @return A {@code String} with the name that should be given to a branch that represents
	 * and {@code JSONObject}
	 */
	private static String getBranchName(JSONObject json) {
		
		Iterator<String> iterator = json.keys();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			
			if("Project ID".equals(key)){
				return getTheProperty(json, key);
			} 

			if("Username".equals(key)){
				return getTheProperty(json, key);
			} 

			if("Consultant ID".equals(key)){
				return getTheProperty(json, key);
			} 

			if("Name".equals(key)){
				return getTheProperty(json, key);
			}
		}
		return "Item";
	}
	
	/**
	 * Gets the {@code Object} mapped by the key passed as parameter. Then returns a string 
	 * consisting of the key appended to {@code ": "}, and to the {@code toString()} of the object.
	 * @param json
	 * @param key
	 * @return A {@code String} consisting of the key, and {@code toString()} of the object
	 * mapped by this key.
	 */
	private static String getTheProperty(JSONObject json, String key) {
		return key + ": " + json.get(key).toString();
	}
}
