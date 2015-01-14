package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.Local;
import utils.Project;
import app.AppMainFrame;
import app.RepositoryHolders.RepositoryHolder;
import app.commands.utils.AppSwingWorker;
import app.commands.utils.CancelActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;


public class NewProject extends BaseCommand{
	
	public NewProject(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new NewProjectFrame().setVisible(true);
	}
	
	public class NewProjectWorker extends AppSwingWorker{
		public NewProjectWorker(Project project) {
			super(pane);
		}

		/**
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected String doInBackground() {
			
			publish("Status: Accessing database");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			publish("Status: Computing changes.");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Thread.sleep(5000);
			
			return "";
		}
	}
	
	public class OkActionListener implements ActionListener{
		private JFrame frame;
		
		public OkActionListener(JFrame frame){
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {	
			//TODO ir buscar os parâmetros aos campos da janela, construir o
			//projecto
			
			Project project = new Project(new Local(1, 1, "coisa", 1), 1);
			
			new NewProjectWorker(project).execute();
			frame.dispose();
		}
	}
	
	
	public class NewProjectFrame extends JFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = -1029585138292762256L;
		
		private final JPanel postProjectPanel = new JPanel();
		private JTextField LatitudeField;
		private JTextField longitudeField;
		private JTextField nameField;
		private JTextField priceField;
	
		/**
		 * Create the frame.
		 */
		public NewProjectFrame() {
			
			this.setTitle("Post project");
			
			//Definição da Caixa de Diálogo
			setBounds(100, 100, 636, 387);
			getContentPane().setLayout(new BorderLayout());
			postProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(postProjectPanel, BorderLayout.CENTER);
			
			// Definição do Painel interno
			GridBagLayout gbl_postProjectPanel = new GridBagLayout();  //Layout Manager
			gbl_postProjectPanel.columnWidths = new int[]{20, 0, 100, 100, 0, 60, 100, 0, 0};
			gbl_postProjectPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 10, 0, 0, 0, 0};
			gbl_postProjectPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_postProjectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			postProjectPanel.setLayout(gbl_postProjectPanel);
			
		    //Inserir imagem :
			//myPicture = ImageIO.read(new File("src\\main\\java\\swing\\imagens\\Project.jpg"));
			ClassLoader cl = AppMainFrame.class.getClassLoader();
			Image myPicture = new ImageIcon(cl.getResource("images/Project.jpg")).getImage();
				
			JLabel lblLocation = new JLabel("Location:");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 2;
			gbc_lblLocation.gridy = 2;
			postProjectPanel.add(lblLocation, gbc_lblLocation);
			
			JLabel userLabel = new JLabel(new ImageIcon(myPicture));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.gridheight = 3;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
			gbc_lblUser.gridx = 1;
			gbc_lblUser.gridy = 3;
			postProjectPanel.add(userLabel, gbc_lblUser);
			
			
			// Informação sobre o user que está a usar o programa
			{
				JLabel lblWellcome = new JLabel("Bem-vindo");
				GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
				gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
				gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
				gbc_lblWellcome.gridx = 6;
				gbc_lblWellcome.gridy = 1;
				postProjectPanel.add(lblWellcome, gbc_lblWellcome);
			}
			{
				JLabel lblRegistryuser = new JLabel("RegistryUser");
				GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
				gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 0);
				gbc_lblRegistryuser.gridx = 7;
				gbc_lblRegistryuser.gridy = 1;
				postProjectPanel.add(lblRegistryuser, gbc_lblRegistryuser);
			}
			
			// Título do Comando : Post User
			{
		//		JLabel lblPostUser = DefaultComponentFactory.getInstance().createTitle("Post Project");
		//		lblPostUser.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
				GridBagConstraints gbc_lblPostUser = new GridBagConstraints();
				gbc_lblPostUser.insets = new Insets(0, 0, 5, 5);
				gbc_lblPostUser.gridx = 3;
				gbc_lblPostUser.gridy = 0;
		//		postProjectPanel.add(lblPostUser, gbc_lblPostUser);
			}
			
			    // Labels e campos a ser preenchidos
			{
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.gridx = 2;
				gbc_lblName.gridy = 4;
				postProjectPanel.add(lblName, gbc_lblName);
			}
			{
				nameField = new JTextField();
				nameField.setColumns(10);
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.insets = new Insets(0, 0, 5, 5);
				gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameField.gridx = 3;
				gbc_nameField.gridy = 4;
				postProjectPanel.add(nameField, gbc_nameField);
			}
			
			{
				JLabel lblPrice = new JLabel("Price:");
				GridBagConstraints gbc_lblPrice = new GridBagConstraints();
				gbc_lblPrice.anchor = GridBagConstraints.EAST;
				gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
				gbc_lblPrice.gridx = 5;
				gbc_lblPrice.gridy = 4;
				postProjectPanel.add(lblPrice, gbc_lblPrice);
				lblPrice.setToolTipText("Euros");
	
			}
			{
				priceField = new JTextField();
				priceField.setColumns(10);
				GridBagConstraints gbc_priceField = new GridBagConstraints();
				gbc_priceField.insets = new Insets(0, 0, 5, 5);
				gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_priceField.gridx = 6;
				gbc_priceField.gridy = 4;
				postProjectPanel.add(priceField, gbc_priceField);
			}
			
			{
				JLabel lblLatitude = new JLabel("Latitude:");
				GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
				gbc_lblLatitude.anchor = GridBagConstraints.EAST;
				gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
				gbc_lblLatitude.gridx = 2;
				gbc_lblLatitude.gridy = 6;
				postProjectPanel.add(lblLatitude, gbc_lblLatitude);
			}
			
			{
				LatitudeField = new JTextField();
				GridBagConstraints gbc_latitudeField = new GridBagConstraints();
				gbc_latitudeField.fill = GridBagConstraints.HORIZONTAL;
				gbc_latitudeField.insets = new Insets(0, 0, 5, 5);
				gbc_latitudeField.gridx = 3;
				gbc_latitudeField.gridy = 6;
				postProjectPanel.add(LatitudeField, gbc_latitudeField);
				LatitudeField.setColumns(10);
			}
			{
				JLabel lblLongitude = new JLabel("Longitude:");
				GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
				gbc_lblLongitude.anchor = GridBagConstraints.EAST;
				gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
				gbc_lblLongitude.gridx = 2;
				gbc_lblLongitude.gridy = 7;
				postProjectPanel.add(lblLongitude, gbc_lblLongitude);
			}
			{
				longitudeField = new JTextField();
				GridBagConstraints gbc_longitudeField = new GridBagConstraints();
				gbc_longitudeField.fill = GridBagConstraints.HORIZONTAL;
				gbc_longitudeField.insets = new Insets(0, 0, 5, 5);
				gbc_longitudeField.gridx = 3;
				gbc_longitudeField.gridy = 7;
				postProjectPanel.add(longitudeField, gbc_longitudeField);
				longitudeField.setColumns(10);
			}
			
					
			
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
					okButton.addActionListener(new OkActionListener(this));
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
					cancelButton.addActionListener(new CancelActionListener(this));
				}
			}
			
		}
	}
}
