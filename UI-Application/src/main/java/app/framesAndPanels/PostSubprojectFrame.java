package app.framesAndPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JList;

public class PostSubprojectFrame extends JDialog {

	private final JPanel postSubprojectPanel = new JPanel();
	private JTextField textField;
	private JTextField subprojects;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostSubprojectFrame dialog = new PostSubprojectFrame();
			dialog.setTitle("Post Subproject");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostSubprojectFrame() 
	{

			//Definição da Caixa de Diálogo
			setBounds(100, 100, 636, 387);
			getContentPane().setLayout(new BorderLayout());
			postSubprojectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(postSubprojectPanel, BorderLayout.CENTER);
			
			// Definição do Painel interno
			GridBagLayout gbl_postProjectPanel = new GridBagLayout();  //Layout Manager
			gbl_postProjectPanel.columnWidths = new int[]{20, 128, 100, 100, 0, 60, 100, 0, 10, 0};
			gbl_postProjectPanel.rowHeights = new int[]{0, 0, 50, 0, 30, 0, 0, 0, 0};
			gbl_postProjectPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_postProjectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			postSubprojectPanel.setLayout(gbl_postProjectPanel);
			
			
			{	// Título do Comando : Post Subproject
				JLabel lblPostSubproject = new JLabel("Post Subproject");
				lblPostSubproject.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
				GridBagConstraints gbc_lblPostSubproject = new GridBagConstraints();
				gbc_lblPostSubproject.gridwidth = 10;
				gbc_lblPostSubproject.insets = new Insets(0, 0, 5, 0);
				gbc_lblPostSubproject.gridx = 0;
				gbc_lblPostSubproject.gridy = 0;
				postSubprojectPanel.add(lblPostSubproject, gbc_lblPostSubproject);
			}
			     
			{ //Inserir imagem : subproject -> Path e localização no Painel
									
				JLabel subprojectLabel = new JLabel("");
				subprojectLabel.setIcon(new ImageIcon(PostSubprojectFrame.class.getClassLoader().getResource("images/subproject.jpg")));
				GridBagConstraints gbc_lblSubproject = new GridBagConstraints();
				gbc_lblSubproject.gridheight = 3;
				gbc_lblSubproject.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubproject.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblSubproject.gridx = 1;
				gbc_lblSubproject.gridy = 3;
				postSubprojectPanel.add(subprojectLabel, gbc_lblSubproject);	
			}
			
			// Informação sobre o user que está a usar o programa
			{
				JLabel lblWellcome = new JLabel("Bem-vindo");
				GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
				gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
				gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
				gbc_lblWellcome.gridx = 6;
				gbc_lblWellcome.gridy = 1;
				postSubprojectPanel.add(lblWellcome, gbc_lblWellcome);
			}
			{
				JLabel lblRegistryuser = new JLabel("RegistryUser");
				GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
				gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 5);
				gbc_lblRegistryuser.gridx = 7;
				gbc_lblRegistryuser.gridy = 1;
				postSubprojectPanel.add(lblRegistryuser, gbc_lblRegistryuser);
			}
			
			    // Labels e campos a ser preenchidos
			{
				JLabel lblProjectID = new JLabel("Project ID:");
				GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
				gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
				gbc_lblProjectID.gridx = 2;
				gbc_lblProjectID.gridy = 3;
				postSubprojectPanel.add(lblProjectID, gbc_lblProjectID);
			}
			
			//terá a lista dos projectos no repositório 
			{
				//elementos da lista
				String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
				
				//Create the combo box, select item at index 4.
				//Indices start at 0, so 4 specifies the pig.
				JComboBox projectComboBox = new JComboBox(petStrings);
				GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
				gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_projectComboBox.gridx = 3;
				gbc_projectComboBox.gridy = 3;
				postSubprojectPanel.add(projectComboBox, gbc_projectComboBox);
				projectComboBox.setEditable(true);
				//projectComboBox.addActionListener(this);
			}
						
			{
				JLabel lblSubprojects = new JLabel("Add Subprojects ID:");
				GridBagConstraints gbc_lblSubprojects = new GridBagConstraints();
				gbc_lblSubprojects.anchor = GridBagConstraints.EAST;
				gbc_lblSubprojects.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubprojects.gridx = 2;
				gbc_lblSubprojects.gridy = 5;
				postSubprojectPanel.add(lblSubprojects, gbc_lblSubprojects);
			}
			
			//terá a lista dos projectos no repositório (tentar que permita a seleção de vários projectos)
			{
				//elementos da lista
				String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

				//Create the combo box, select item at index 4.
				//Indices start at 0, so 4 specifies the pig.
				JComboBox subprojectComboBox = new JComboBox(petStrings);
				GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
				gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_projectComboBox.gridx = 3;
				gbc_projectComboBox.gridy = 5;
				postSubprojectPanel.add(subprojectComboBox, gbc_projectComboBox);
				subprojectComboBox.setEditable(true);
				//projectComboBox.addActionListener(this);
			}
			
			//caixa de texto onde será inserida a informação do Get Subproject
			{
				subprojects = new JTextField();
				GridBagConstraints gbc_subprojects = new GridBagConstraints();
				gbc_subprojects.gridwidth = 7;
				gbc_subprojects.insets = new Insets(0, 0, 0, 5);
				gbc_subprojects.fill = GridBagConstraints.BOTH;
				gbc_subprojects.gridx = 1;
				gbc_subprojects.gridy = 7;
				postSubprojectPanel.add(subprojects, gbc_subprojects);
				subprojects.setColumns(10);
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
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}

	}
