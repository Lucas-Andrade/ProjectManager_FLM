package app.windows;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishToMainFrame extends ResultsPublisher{
	
	JSplitPane splitPane = MainFrame.getSplitPane();
	JPanel mainPanel = new JPanel();
	JLabel statusLabel = new JLabel();
	
	public PublishToMainFrame() {
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(statusLabel, BorderLayout.SOUTH);
	}
	
	
	@Override
	public void publish(List<String> chunks) {
		statusLabel.setText(chunks.get(0));
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
	}
	
	@Override
	public void publish(AppElement[] appElements) {
		JSplitPane splitPane = MainFrame.getSplitPane();
		JTree tree = TreeBuilder.getTree(appElements);
	//	tree.setRootVisible(false);
		
		mainPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
		statusLabel.setText("Status: Ready");
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
	}
}
