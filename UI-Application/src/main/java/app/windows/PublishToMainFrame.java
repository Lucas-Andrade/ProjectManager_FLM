package app.windows;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import org.json.JSONObject;

import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishToMainFrame implements ResultsPublisher{

	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();
		JPanel panel = new JPanel();
		JList list = new JList();
		
		for(AppElement element : appElements){
			list.add(treeifyTheElement(element));
		}
		
		panel.add(list, BorderLayout.CENTER);
		splitPane.setRightComponent(panel);
	}

	private static JTree treeifyTheElement(AppElement element) {

		JSONObject json = element.getJson();
		JTree tree = new JTree();
		
		json.get
		
		
		return null;
	}

}
