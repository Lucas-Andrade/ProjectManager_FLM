package app.result;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class responsible for showing the results in a JPanel.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 16/01/2014
 */
public class ResultPanel extends JPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for a JPanel that shows the results.
	 * 
	 * @param results
	 *            The results for showing in the JPanel.
	 */
	public ResultPanel(String results)
	{
		this.add(new JLabel(results));
	}

}