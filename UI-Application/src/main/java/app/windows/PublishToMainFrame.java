package app.windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.json.JSONObject;

import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishToMainFrame {//implements ResultsPublisher{

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
	//	JPanel panel = new JPanel();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		JTree tree = new JTree(root);
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.add(new JScrollPane(tree));
		
		for(AppElement element : appElements){
			root.add(getBranch(element));
		}
		
		root.add(new DefaultMutableTreeNode("coisa"));
	//	panel.add(tree, BorderLayout.CENTER);
		splitPane.setRightComponent(new JScrollPane(tree));
		splitPane.setLeftComponent(new JPanel());
	}

	private MutableTreeNode getBranch(AppElement element) {
		MutableTreeNode toReturn = new DefaultMutableTreeNode(getBranchName(element));
		return toReturn;
	}

	private String getBranchName(AppElement element) {
		
		String elementString = element.toString();
		StringBuilder builder = new StringBuilder();
		
		for(int i = 1; i < elementString.length(); i++){
			if(elementString.charAt(i-1) == '\\' &&
					elementString.charAt(i) == 'n') {
				return builder.toString();
			} else {
				builder.append(elementString.charAt(i-1));
			}
		}
		return null;
	}



}
