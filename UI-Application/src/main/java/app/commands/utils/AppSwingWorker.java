package app.commands.utils;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingWorker;

public abstract class AppSwingWorker extends SwingWorker<String, String>{
	private JSplitPane pane;
	
	public AppSwingWorker(JSplitPane pane){
		this.pane = pane;
	}
	
	@Override
    protected void process(List<String> chunks){
		JPanel panel = new JPanel();
 		panel.add(new JLabel(chunks.get(0)));
 		pane.setRightComponent(panel);
     }
	
	@Override
	public void done(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Here is your result."));
		pane.setRightComponent(panel);
	}
}
