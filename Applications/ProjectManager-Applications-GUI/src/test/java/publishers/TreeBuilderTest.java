package publishers;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class TreeBuilderTest {

	public static void main(String[] args) {
		//JTree tree = TreeBuilder.buildTree("{\"All users\":[{\"Email\":\"user1@email.com\",\"Username\":\"user1\",\"Full name\":\"user1\"},{\"Email\":\"user2@email.com\",\"Username\":\"user2\",\"Full name\":\"user2\"},{\"Email\":\"admin_admin@administration.com\",\"Username\":\"admin\",\"Full name\":\"Administator\"},{\"Email\":\"user5@email.com\",\"Username\":\"user5\",\"Full name\":\"user5\"},{\"Email\":\"user3@email.com\",\"Username\":\"user3\",\"Full name\":\"user3\"},{\"Email\":\"user4@email.com\",\"Username\":\"user4\",\"Full name\":\"user4\"}]}");
		JTree tree = TreeBuilder.buildTree("[{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":1,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":3,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":2,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":1,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":3,\"Subprojects\":[{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":1,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":4,\"Subprojects\":[{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":1,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":3,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":2,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":5,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}}],\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"},\"Manager\":{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":5,\"Bonus\":0,\"Name\":\"worker0\"}}],\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":1,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":4,\"Subprojects\":[{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":1,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":3,\"Name\":\"worker0\"}],\"Cost (Euros)\":\"0\",\"Project ID\":2,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}},{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":5,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}}],\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"},\"Manager\":{\"Payment per hour (Euros)\":\"0\",\"Cost (Euros)\":\"0\",\"Consultant ID\":5,\"Bonus\":0,\"Name\":\"worker0\"}},{\"Project team\":[],\"Cost (Euros)\":\"0\",\"Project ID\":5,\"Subprojects\":\"None.\",\"Local\":{\"Cost (Euros)\":\"0\",\"Latitude\":0,\"Longitude\":0,\"Name\":\"local0\"}}]");
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel results = new JPanel();
		results.setLayout(new BorderLayout(0, 0));
		results.add(new JScrollPane(tree));
		
		frame.getContentPane().add(results, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}