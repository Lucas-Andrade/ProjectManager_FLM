package publishers;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class TableBuilderTester  {

	public static void main(String[] args) {
		JTable table = TableBuilder.buildTable("{\"All users\":[{\"Email\":\"user1@email.com\",\"Username\":\"user1\",\"Full name\":\"user1\"},{\"Email\":\"user2@email.com\",\"Username\":\"user2\",\"Full name\":\"user2\"},{\"Email\":\"admin_admin@administration.com\",\"Username\":\"admin\",\"Full name\":\"Administator\"},{\"Email\":\"user5@email.com\",\"Username\":\"user5\",\"Full name\":\"user5\"},{\"Email\":\"user3@email.com\",\"Username\":\"user3\",\"Full name\":\"user3\"},{\"Email\":\"user4@email.com\",\"Username\":\"user4\",\"Full name\":\"user4\"}]}");
		JFrame frame = new JFrame();


		JPanel results = new JPanel();
		results.setLayout(new BorderLayout(0, 0));
		results.add(new JScrollPane(table));
			
			
		frame.add(results, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
