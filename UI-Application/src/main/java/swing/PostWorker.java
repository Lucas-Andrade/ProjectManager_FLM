package swing;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class PostWorker extends JDialog {

	private final JPanel PostWorkerPanel = new JPanel();
	private JTextField previewField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PostWorker dialog = new PostWorker();
			dialog.setTitle("Post Worker In Project");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PostWorker() 
	{

			//Definição da Caixa de Diálogo
			setBounds(100, 100, 636, 387);
			getContentPane().setLayout(new BorderLayout());
			PostWorkerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(PostWorkerPanel, BorderLayout.CENTER);
			
			// Definição do Painel interno
			GridBagLayout gbl_postWorkerPanel = new GridBagLayout();  //Layout Manager
			gbl_postWorkerPanel.columnWidths = new int[]{20, 100, 100, 100, 100, 60, 100, 100, 10, 0};
			gbl_postWorkerPanel.rowHeights = new int[]{0, 0, 50, 0, 10, 0, 0, 0, 0, 0};
			gbl_postWorkerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_postWorkerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			PostWorkerPanel.setLayout(gbl_postWorkerPanel);
			
			
			
		     //Inserir imagem : user -> Path e localização no Painel
			//alterar imagem
		{ 
			BufferedImage myPicture;
			try {
				myPicture = ImageIO.read(new File("src\\main\\java\\swing\\imagens\\Project.jpg"));
				{
					JLabel label = new JLabel("Post Worker In Project");
					label.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
					GridBagConstraints gbc_label = new GridBagConstraints();
					gbc_label.gridwidth = 7;
					gbc_label.insets = new Insets(0, 0, 5, 5);
					gbc_label.gridx = 1;
					gbc_label.gridy = 0;
					PostWorkerPanel.add(label, gbc_label);
				}
				
				JLabel userLabel = new JLabel(new ImageIcon(myPicture));
				GridBagConstraints gbc_lblUser = new GridBagConstraints();
				gbc_lblUser.gridheight = 3;
				gbc_lblUser.insets = new Insets(0, 0, 5, 5);
				gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblUser.gridx = 1;
				gbc_lblUser.gridy = 3;
				PostWorkerPanel.add(userLabel, gbc_lblUser);
			
			} catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
			
			// Informação sobre o user que está a usar o programa
			{
				JLabel lblWellcome = new JLabel("Bem-vindo");
				GridBagConstraints gbc_lblWellcome = new GridBagConstraints();
				gbc_lblWellcome.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
				gbc_lblWellcome.insets = new Insets(0, 0, 5, 5);
				gbc_lblWellcome.gridx = 6;
				gbc_lblWellcome.gridy = 1;
				PostWorkerPanel.add(lblWellcome, gbc_lblWellcome);
			}
			
			{
				JLabel lblRegistryuser = new JLabel("RegistryUser");
				GridBagConstraints gbc_lblRegistryuser = new GridBagConstraints();
				gbc_lblRegistryuser.insets = new Insets(0, 0, 5, 5);
				gbc_lblRegistryuser.gridx = 7;
				gbc_lblRegistryuser.gridy = 1;
				PostWorkerPanel.add(lblRegistryuser, gbc_lblRegistryuser);
			}
			
			    // Labels e campos a ser preenchidos
			{
				JLabel lblProjectID = new JLabel("Project ID:");
				GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
				gbc_lblProjectID.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
				gbc_lblProjectID.gridx = 2;
				gbc_lblProjectID.gridy = 3;
				PostWorkerPanel.add(lblProjectID, gbc_lblProjectID);
			}
			
			//terá a lista dos projectos no repositório 
			{
				//elementos da lista -> alterar
				String[] projects = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
				
				JComboBox projectComboBox = new JComboBox(projects);
				GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
				gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_projectComboBox.gridx = 3;
				gbc_projectComboBox.gridy = 3;
				PostWorkerPanel.add(projectComboBox, gbc_projectComboBox);
				projectComboBox.setEditable(true);
				//projectComboBox.addActionListener(this);
			}
						
			{
				JLabel lblAddWorker = new JLabel("Add Workers:");
				GridBagConstraints gbc_lblAddWorker = new GridBagConstraints();
				gbc_lblAddWorker.anchor = GridBagConstraints.EAST;
				gbc_lblAddWorker.insets = new Insets(0, 0, 5, 5);
				gbc_lblAddWorker.gridx = 2;
				gbc_lblAddWorker.gridy = 5;
				PostWorkerPanel.add(lblAddWorker, gbc_lblAddWorker);
			}
			
			//terá a lista dos projectos no repositório (tentar que permita a seleção de vários projectos)
			{				
				JCheckBox chckbxManager = new JCheckBox("Manager");
				GridBagConstraints gbc_chckbxManager = new GridBagConstraints();
				gbc_chckbxManager.fill = GridBagConstraints.HORIZONTAL;
				gbc_chckbxManager.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxManager.gridx = 3;
				gbc_chckbxManager.gridy = 6;
				PostWorkerPanel.add(chckbxManager, gbc_chckbxManager);
			}
			
			//elementos da lista -> alterar
			{
				String[] managers = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
				JComboBox subprojectComboBox = new JComboBox(managers);
				GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
				gbc_projectComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_projectComboBox.gridx = 4;
				gbc_projectComboBox.gridy = 6;
				PostWorkerPanel.add(subprojectComboBox, gbc_projectComboBox);
				subprojectComboBox.setEditable(true);
			}
			
			{
				JCheckBox chckbxConsultants = new JCheckBox("Consultants");
				GridBagConstraints gbc_chckbxConsultants = new GridBagConstraints();
				gbc_chckbxConsultants.fill = GridBagConstraints.HORIZONTAL;
				gbc_chckbxConsultants.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxConsultants.gridx = 3;
				gbc_chckbxConsultants.gridy = 7;
				PostWorkerPanel.add(chckbxConsultants, gbc_chckbxConsultants);
			}
			
			//elementos da lista -> alterar
			{
				String[] consultants = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
				JComboBox consultantsComboBox = new JComboBox(consultants);
				consultantsComboBox.setEditable(true);
				GridBagConstraints gbc_consultantsComboBox = new GridBagConstraints();
				gbc_consultantsComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_consultantsComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_consultantsComboBox.gridx = 4;
				gbc_consultantsComboBox.gridy = 7;
				PostWorkerPanel.add(consultantsComboBox, gbc_consultantsComboBox);
			}
			{
				previewField = new JTextField();
				GridBagConstraints gbc_previewField = new GridBagConstraints();
				gbc_previewField.gridwidth = 7;
				gbc_previewField.insets = new Insets(0, 0, 0, 5);
				gbc_previewField.fill = GridBagConstraints.BOTH;
				gbc_previewField.gridx = 1;
				gbc_previewField.gridy = 8;
				PostWorkerPanel.add(previewField, gbc_previewField);
				previewField.setColumns(10);
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
