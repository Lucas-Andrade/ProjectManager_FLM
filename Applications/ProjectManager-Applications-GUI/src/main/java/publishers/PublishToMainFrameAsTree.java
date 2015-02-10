package publishers;

import javax.swing.JTree;

/**
 * Allows to publish information about {@code AppElement}s into the right panel
 * of the {@code MainFrame}. This class presents that information as a {@code JTree}.
 * 
 * The conversion method used to parse the {@code AppElement}s into nodes in the
 * {@code JTree} is general enough to work with all kinds of {@code AppElement}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToMainFrameAsTree extends PublishToMainFrame{
	
	/**
	 * Constructs a {@code JTree} using the method {@code TreeBuilder#getTree}. This
	 * {@code JTree} will be added to the {@code mainPanel} as its {@code CENTER} 
	 * component. Then, the {@code mainPanel} is added to the {@code MainFrame}, and
	 * the {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see PublishToMainFrame#publish(AppElement[])
	 */
	@Override
	public void publish(String elements) {
		JTree tree = TreeBuilder.buildTree(elements);
		publish(tree);
	}


}
