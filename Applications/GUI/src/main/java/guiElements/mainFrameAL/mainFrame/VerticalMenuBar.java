package guiElements.mainFrameAL.mainFrame;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JMenuBar;

public class VerticalMenuBar extends JMenuBar {
	private static final long serialVersionUID = -651471821217764757L;
	
	private static final LayoutManager grid = new GridLayout(8,1);
	
	public VerticalMenuBar() {
		setLayout(grid);
	}
}
