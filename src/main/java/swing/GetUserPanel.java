package swing;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

public class GetUserPanel extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel getUsersPanel = new JPanel();
	private JTextField userID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GetUserPanel dialog = new GetUserPanel();
			//definimos o título da janela
			dialog.setTitle("Get Users");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public GetUserPanel() 
	{
		
		setBounds(100, 100, 626, 387);
		getContentPane().setLayout(new BorderLayout());
		getUsersPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getUsersPanel, BorderLayout.CENTER);
		GridBagLayout gbl_getUsersPanel = new GridBagLayout();
		gbl_getUsersPanel.columnWidths = new int[]{0, 29, 100, 100, 0, 0, 48, 0};
		gbl_getUsersPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_getUsersPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_getUsersPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getUsersPanel.setLayout(gbl_getUsersPanel);
		
		
		 //Inserir imagem : user -> Path e localização no Painel
		{ 
			BufferedImage myPicture;
			try 
			{
				myPicture = ImageIO.read(new File("src\\main\\java\\swing\\imagens\\user.jpg"));
				{
//					JLabel lblGetUsers = DefaultComponentFactory.getInstance().createTitle("Get Users");
//					lblGetUsers.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
					GridBagConstraints gbc_lblGetUsers = new GridBagConstraints();
					gbc_lblGetUsers.gridwidth = 5;
					gbc_lblGetUsers.insets = new Insets(0, 0, 5, 5);
					gbc_lblGetUsers.gridx = 2;
					gbc_lblGetUsers.gridy = 0;
//					getUsersPanel.add(lblGetUsers, gbc_lblGetUsers);
				}
				JLabel userLabel = new JLabel(new ImageIcon(myPicture));
				GridBagConstraints gbc_lblUser = new GridBagConstraints();
				gbc_lblUser.gridheight = 2;
				gbc_lblUser.insets = new Insets(0, 0, 5, 5);
				gbc_lblUser.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblUser.gridx = 1;
				gbc_lblUser.gridy = 2;
				getUsersPanel.add(userLabel, gbc_lblUser);
			
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		
		//Opções de seleção
		{
			JRadioButton rdbtnAllUsers = new JRadioButton("All User");
			GridBagConstraints gbc_rdbtnAllUsers = new GridBagConstraints();
			gbc_rdbtnAllUsers.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnAllUsers.gridx = 2;
			gbc_rdbtnAllUsers.gridy = 2;
			getUsersPanel.add(rdbtnAllUsers, gbc_rdbtnAllUsers);
		}
		
		{
			JRadioButton rdbtnUserId = new JRadioButton("User ID:");
			GridBagConstraints gbc_rdbtnUserId = new GridBagConstraints();
			gbc_rdbtnUserId.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnUserId.gridx = 2;
			gbc_rdbtnUserId.gridy = 3;
			getUsersPanel.add(rdbtnUserId, gbc_rdbtnUserId);
		}
		
		{
			userID = new JTextField();
			GridBagConstraints gbc_userID = new GridBagConstraints();
			gbc_userID.insets = new Insets(0, 0, 5, 5);
			gbc_userID.fill = GridBagConstraints.HORIZONTAL;
			gbc_userID.gridx = 3;
			gbc_userID.gridy = 3;
			getUsersPanel.add(userID, gbc_userID);
			userID.setColumns(10);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.GREEN);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.gridwidth = 5;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridx = 2;
			gbc_separator.gridy = 4;
			getUsersPanel.add(separator, gbc_separator);
		}
		
		
		//Painel onde queremos que apareça a lista de Users
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 7;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 5;
			getUsersPanel.add(panel, gbc_panel);
			{
				table = new JTable();
				panel.add(table);
			}
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
