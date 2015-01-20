package app.windows;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONObject;

import app.AppElement;

public class PublishToMainFrame implements ResultsPublisher{

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		JSplitPane splitPane = new JSplitPane();
		
		frame.getContentPane().add(splitPane);
		new PublishToMainFrame().publish(new AppElement[]{RepositoryConstructor.constructProject(2)}, splitPane );
		frame.setVisible(true);
	}
	
//	@Override
	public void publish(AppElement[] appElements, JSplitPane splitPane) {
	//	JSplitPane splitPane = MainFrame.getSplitPane();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		
		for(AppElement element : appElements){
			root.add(getBranch(element));
		}
				
		tree.setShowsRootHandles(true);
	//	tree.setRootVisible(false);
		splitPane.setRightComponent(new JScrollPane(tree));
		splitPane.setLeftComponent(new JPanel());
		splitPane.updateUI();
	}

	private DefaultMutableTreeNode getBranch(AppElement element) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(getBranchName(element));
		
		JSONObject json = element.getJson();
		return constructBranch(node, json);
	}

	private DefaultMutableTreeNode constructBranch(DefaultMutableTreeNode node, JSONObject json) {
		
		Iterator<String> iterator = json.keys();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object object = json.get(key);
			
			if(object instanceof JSONObject){
				node.add(constructBranch(new DefaultMutableTreeNode(key), (JSONObject)object));
			} else if(object.getClass().isArray()) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(key);
				addArrayOfNodes(newNode, (Object[])object);
			} else {
				node.add(new DefaultMutableTreeNode(key + ": " + object.toString()));
			}
		}
		return node;
	}

	private void addArrayOfNodes(DefaultMutableTreeNode node, Object[] arrayToAdd) {

		for(Object obj : arrayToAdd){
			if (obj instanceof JSONObject) {
				constructBranch(node, (JSONObject)obj);
			} else {
				node.add(new DefaultMutableTreeNode(obj.toString()));
			}
		}
	}

	private String getBranchName(AppElement element) {

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

	@Override
	public void publish(AppElement[] appElements) {
		// TODO Auto-generated method stub
		
	}

}
