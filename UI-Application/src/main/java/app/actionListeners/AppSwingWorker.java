package app.actionListeners;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingWorker;

public abstract class AppSwingWorker extends SwingWorker<JPanel, String>{
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
		try
		{
			pane.setRightComponent(get());
		} catch (InterruptedException | ExecutionException e)
		{
			JPanel panel = new JPanel();
			panel.add(new JLabel("ERROR: " + e.getMessage()));
			pane.setRightComponent(panel);
		}
	}
}
