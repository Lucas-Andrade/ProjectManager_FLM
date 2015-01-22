package app.windows;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;

import app.AppElement;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;

public class PublishToGetPanel extends ResultsPublisher{

	@Override
	public void publish(AppElement[] appElements) {
		
		JTree tree = TreeBuilder.getTree(appElements);
		MainGetPanel.setResults(new JScrollPane(tree));
	}

	@Override
	public void publish(List<String> chunks) {
		
	}

}
