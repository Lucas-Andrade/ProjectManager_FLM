package app.windows;

import java.util.Iterator;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONObject;

import app.AppElement;

public class TreeBuilder {
	
	public static JTree getTree(AppElement[] appElements){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		for(AppElement element : appElements){
			root.add(getBranch(element));
		}
		
		return tree;
	}
	
	private static DefaultMutableTreeNode getBranch(AppElement element) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(getBranchName(element));
		
		JSONObject json = element.getJson();
		return constructBranch(node, json);
	}

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
	
	private static String getBranchName(JSONObject json) {
		
		Iterator<String> iterator = json.keys();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			
			if(key.equals("Project ID")){
				return getTheProperty(json, key);
			} 

			if(key.equals("Username")){
				return getTheProperty(json, key);
			} 

			if(key.equals("Consultant ID")){
				return getTheProperty(json, key);
			} 

			if(key.equals("Name")){
				return getTheProperty(json, key);
			}
		}
		return "Item";
	}
	
	private static String getTheProperty(JSONObject json, String key) {
		return key + ": " + json.get(key).toString();
	}
}
