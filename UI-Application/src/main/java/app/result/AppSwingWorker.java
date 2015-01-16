package app.result;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
		pane.setRightComponent(new ResultPanel(chunks.get(0)));
     }
	
	@Override
	public void done(){
		try
		{
			pane.setRightComponent(get());
		} catch (InterruptedException | ExecutionException e)
		{
			pane.setRightComponent(new WarningMessagePanel("ERROR: " + e.getMessage()));
		}
	}
}
