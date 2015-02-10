package publishers;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TreeBuilder {
	
	public static JTree buildTree(String elements) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		
		JsonElement element = new JsonParser().parse(elements);
		
		root.add(parseElement(element));
		return tree;
	}

	private static MutableTreeNode parseElement(JsonElement element) {
		
		
		
		return null;
	}
}
