package publishers;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;

import javax.swing.JTable;

import com.google.gson.JsonParseException;

/**
 * Allows to publish information into the right panel
 * of the {@code MainFrame}. This class presents that information as a {@code JTable}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToMainFrameAsTable extends PublishToMainFrame{
	
	/**
	 * Tries to publish the information in the {@code String} passed as parameter as a {@code JTable}.
	 * If unable, the information will be published as a {@code JTree} instead.
	 * @see PublishToMainFrame#publish(String)
	 */
	@Override
	public void publish(String elements) {
		JTable table = new JTable();
		try{
			table = TableBuilder.buildTable(elements);
		} catch(JsonParseException | IllegalStateException | ClassCastException | UnsupportedOperationException e) {
			new ErrorDialog("Could not present the results.").setVisible(true);
			return;
		} catch(TableBuilderException e) {
			new PublishToMainFrameAsTree().publish(elements);
			return;
		}
		publish(table);
	}
}
