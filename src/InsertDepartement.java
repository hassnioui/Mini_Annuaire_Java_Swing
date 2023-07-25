import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class InsertDepartement extends JFrame {

	private JPanel contentPane;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField nomField;
	private JTable TableD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertDepartement frame = new InsertDepartement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertDepartement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 251, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = DBConnection.getConnection();
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 MenuAdministration obj = new MenuAdministration ();
				 obj.setVisible(true);
				
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = TableD.getSelectedRow();
				if (ligne != -1) {
					String id = TableD.getModel().getValueAt(ligne, 0).toString();
					String sql = "DELETE FROM departement WHERE id = ? ";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, id);
						prepared.execute();
						ListeD();
						JOptionPane.showMessageDialog(null, "Département a été supprimée avec succès !");
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					
					JOptionPane.showMessageDialog(null, "Aucune Département sélectionnée !");
				}
				
			}
		});
		btnDelete.setBounds(673, 404, 162, 34);
		contentPane.add(btnDelete);
		
		JLabel line3_1 = new JLabel("");
		line3_1.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line-2.png"));
		line3_1.setBounds(593, 375, 303, 30);
		contentPane.add(line3_1);
		
		JLabel lblNewLabel = new JLabel("Liste Départements :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(539, 108, 147, 14);
		contentPane.add(lblNewLabel);
		
		nomField = new JTextField();
		nomField.setBounds(148, 251, 205, 30);
		contentPane.add(nomField);
		nomField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(539, 133, 398, 231);
		contentPane.add(scrollPane);
		
		TableD = new JTable();
		scrollPane.setViewportView(TableD);
		ListeD();
		
		
		
		JLabel line3 = new JLabel("");
		line3.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line-2.png"));
		line3.setBounds(87, 375, 303, 30);
		contentPane.add(line3);
		
		JButton ajouterbtn = new JButton("Ajouter");
		ajouterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				String nom = nomField.getText().toString();
				
				String sql = "INSERT INTO departement (nom) VALUES (?)";
				
				try {
					if (!nom.equals("")) {
					
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, nom);
						prepared.execute();
						ListeD();
						
						nomField.setText("");
					
						JOptionPane.showMessageDialog(null, "Département ajouté avec succès !");
						
					}else {
					
					JOptionPane.showMessageDialog(null, "S'il Vous Plaît Remplir Tous Les Champs");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		ajouterbtn.setBounds(160, 404, 162, 34);
		contentPane.add(ajouterbtn);
		
		JLabel nomLabel = new JLabel("Nom :");
		nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nomLabel.setBounds(78, 258, 60, 14);
		contentPane.add(nomLabel);
		
		JLabel ligne2 = new JLabel("");
		ligne2.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line.jpg"));
		ligne2.setBounds(482, 90, 4, 371);
		contentPane.add(ligne2);
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\return.png"));
		btnNewButton.setBounds(39, 32, 53, 30);
		contentPane.add(btnNewButton);
		
		JLabel Title1 = new JLabel("Mini Annuaire");
		Title1.setHorizontalAlignment(SwingConstants.CENTER);
		Title1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 44));
		Title1.setBounds(348, -13, 314, 92);
		contentPane.add(Title1);
		
		JLabel Title2 = new JLabel("ENS Tetouan");
		Title2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Title2.setHorizontalAlignment(SwingConstants.CENTER);
		Title2.setBounds(427, 52, 153, 38);
		contentPane.add(Title2);
		
		
		JLabel Ligne = new JLabel("");
		Ligne.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line.jpg"));
		Ligne.setBounds(0, 90, 984, 3);
		contentPane.add(Ligne);
		
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line - Copy.jpg"));
		Background.setBounds(0, 90, 984, 371);
		contentPane.add(Background);
	}
	
public void ListeD() {
		
		String sql = "SELECT * FROM departement";
		try {
			prepared = cnx.prepareStatement(sql);
			rs = prepared.executeQuery();
			TableD.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
