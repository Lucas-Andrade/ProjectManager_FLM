package app.windows;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.json.JSONObject;

import utils.Project;
import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishToMainFrame implements ResultsPublisher{

//	public static void main(String[] args){
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(500,700);
//		JSplitPane splitPane = new JSplitPane();
//		
//		Project project = RepositoryConstructor.constructProject(1);
//		
//		Project otherProject = RepositoryConstructor.constructProject(2);
//		project.addProject(RepositoryConstructor.constructProject(3));
//		project.addProject(RepositoryConstructor.constructProject(4));
//		otherProject.addProject(RepositoryConstructor.constructProject(5));
//		project.addProject(otherProject);
//		
//		project.addWorker(RepositoryConstructor.constructConsultant(1));
//		project.addWorker(RepositoryConstructor.constructConsultant(2));
//		project.addWorker(RepositoryConstructor.constructConsultant(3));
//		project.addWorker(RepositoryConstructor.constructConsultant(4));
//		
//		project.setManager(RepositoryConstructor.constructLeader(5));
//		
//		frame.getContentPane().add(splitPane);
//		new PublishToMainFrame().publish(new AppElement[]{project, otherProject}, splitPane );
//		frame.setVisible(true);
//	}
	
	
	JSplitPane splitPane = MainFrame.getSplitPane();
	JPanel mainPanel = new JPanel();
	JPanel miniPanel = new JPanel();
	
	public PublishToMainFrame() {
		mainPanel.add(miniPanel, BorderLayout.SOUTH);
	}
	
	
	@Override
	public void publish(List<String> chunks) {
		
		miniPanel.add(new JLabel(chunks.get(0)));
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
	}
	
	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		
		for(AppElement element : appElements){
			root.add(getBranch(element));
		}
				
		tree.setShowsRootHandles(true);
	//	tree.setRootVisible(false);
		
		mainPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
		miniPanel.add(new JLabel("Status: Ready"));
		splitPane.setRightComponent(mainPanel);
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
				node.add(newNode);
				addArrayOfNodes(newNode, (Object[])object);
			} else {
				node.add(new DefaultMutableTreeNode(key + ": " + object.toString()));
			}
		}
		return node;
	}

	private void addArrayOfNodes(DefaultMutableTreeNode node, Object[] arrayToAdd) {

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
	
	private String getBranchName(JSONObject json) {
		
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
	
	private String getTheProperty(JSONObject json, String key) {
		return key + ": " + json.get(key).toString();
	}
}
