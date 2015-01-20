package app.windows;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class teste{

	public static void main(String[] args) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
 
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        //create the tree by passing in the root node
        JTree tree = new JTree(root);
        panel.add(tree, BorderLayout.CENTER);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JTree Example");       
        frame.pack();
        frame.setVisible(true);
	}

}
