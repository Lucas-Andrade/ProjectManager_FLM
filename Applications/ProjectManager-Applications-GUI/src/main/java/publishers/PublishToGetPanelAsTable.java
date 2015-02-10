package publishers;

import javax.swing.JTable;

/**
 * Allows to publish information about {@code AppElement}s indirectly to the
 * {@code MainFrame}. This class presents that information as a {@code JTree},
 * and then adds it to the appropriate panel in the {@code MainGetPanel}.
 * 
 * The conversion method used to parse the {@code AppElement}s into nodes in the
 * {@code JTree} is general enough to work with all kinds of {@code AppElement}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToGetPanelAsTable extends PublishToGetPanel{

	/**
	 * Constructs a {@code JTree} using the method {@code TreeBuilder#getTree}. This
	 * {@code JTree} will be added to the {@code mainPanel} as its {@code CENTER} 
	 * component. Then, the {@code mainPanel} is added to the {@code MainFrame}, and
	 * the {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see PublishToMainFrame#publish(AppElement[])
	 */
	public void publish(String elements) {
		JTable table = TableBuilder.buildTable(elements);
		publish(table);
	}
}
