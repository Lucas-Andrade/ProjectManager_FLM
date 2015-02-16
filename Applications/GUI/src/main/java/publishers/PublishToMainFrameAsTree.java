package publishers;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import javax.swing.JTree;

import com.google.gson.JsonParseException;

/**
 * Allows to publish information into the right panel
 * of the {@code MainFrame}. This class presents that information as a {@code JTree}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToMainFrameAsTree extends PublishToMainFrame{
	
	/**
	 * @see PublishToMainFrame#publish(String)
	 */
	@Override
	public void publish(String elements) {
		JTree tree = new JTree();
		try{
			tree = TreeBuilder.buildTree(elements);
		} catch(JsonParseException | IllegalStateException | ClassCastException | UnsupportedOperationException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		}
		publish(tree);
	}


}
