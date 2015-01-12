package app.forWindow.commandsFrames;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingWorker;

public abstract class AppWorker extends SwingWorker<String, String>
{
	Map<String, String> parameters;
	private JSplitPane pane;
	
	public AppWorker(Map<String, String> parameters, JSplitPane pane)
	{
		this.parameters = parameters;
		this.pane = pane;
	}
	
	@Override
    protected void process(List<String> chunks)
	{
		JPanel panel = new JPanel();
 		panel.add(new JLabel(chunks.get(0)));
 		pane.setRightComponent(panel);
     }
	
	@Override
	public void done()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Here is your result."));
		pane.setRightComponent(panel);
	}
}
