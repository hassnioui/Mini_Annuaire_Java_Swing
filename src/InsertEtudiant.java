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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class InsertEtudiant extends JFrame {

	private JPanel contentPane;
	JComboBox filiereField;
	JComboBox departementField;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField CNEField;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField telephoneField;
	private JTable TableE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEtudiant frame = new InsertEtudiant();
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
	public InsertEtudiant() {
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
				
				int ligne = TableE.getSelectedRow();
				if (ligne != -1) {
					String CNE = TableE.getModel().getValueAt(ligne, 0).toString();
					String sql = "DELETE FROM etudiant WHERE CNE = ? ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, CNE);	
						prepared.execute();
						ListeE();
						JOptionPane.showMessageDialog(null, "Étudiant a été supprimée avec succès !");
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}else{
					
					JOptionPane.showMessageDialog(null, "Aucune Étudiant sélectionnée !");
				}
			}
		});
		btnDelete.setBounds(673, 404, 162, 34);
		contentPane.add(btnDelete);
		
		JLabel line3_1 = new JLabel("");
		line3_1.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line-2.png"));
		line3_1.setBounds(593, 375, 303, 30);
		contentPane.add(line3_1);
		
		JLabel lblNewLabel = new JLabel("Liste Étudiants :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(539, 108, 116, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(539, 133, 398, 231);
		contentPane.add(scrollPane);
		
		TableE = new JTable();
		scrollPane.setViewportView(TableE);
		ListeE ();
		
		JLabel line3 = new JLabel("");
		line3.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line-2.png"));
		line3.setBounds(87, 375, 303, 30);
		contentPane.add(line3);
		
		JButton ajouterbtn = new JButton("Ajouter");
		ajouterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String CNE = CNEField.getText().toString();
				String nom = nomField.getText().toString();
				String prenom = prenomField.getText().toString();
				String filiere = filiereField.getSelectedItem().toString();
				String departement = departementField.getSelectedItem().toString();
				String telephone = telephoneField.getText().toString();
				
				String sql = "INSERT INTO etudiant (CNE, nom, prenom, filiere, departement, telephone) VALUES (? , ? , ? , ? , ? , ?)";
				
				try {
					if (!CNE.equals("") && !nom.equals("") && !prenom.equals("") && !filiere.equals("----- Sélectionner une option -----") && !departement.equals("----- Sélectionner une option -----") && !telephone.equals("")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, CNE);
						prepared.setString(2, nom);
						prepared.setString(3, prenom);
						prepared.setString(4, filiere);
						prepared.setString(5, departement);
						prepared.setString(6, telephone);
						prepared.execute();
						ListeE ();
						
						
						CNEField.setText("");
						nomField.setText("");
						prenomField.setText("");
						filiereField.setSelectedIndex(0);
						departementField.setSelectedIndex(0);
						telephoneField.setText("");
						
						JOptionPane.showMessageDialog(null, "Étudiant ajouté avec succès !");
						
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
		
		JLabel departementLabel = new JLabel("Départements :");
		departementLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		departementLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		departementLabel.setBounds(75, 300, 110, 14);
		contentPane.add(departementLabel);
		
		JLabel filiereLabel = new JLabel("Filières :");
		filiereLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		filiereLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		filiereLabel.setBounds(125, 341, 60, 14);
		contentPane.add(filiereLabel);
		
		JLabel telephoneLabel = new JLabel("Téléphone :");
		telephoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		telephoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		telephoneLabel.setBounds(103, 259, 82, 14);
		contentPane.add(telephoneLabel);
		
		JLabel nomLabel = new JLabel("Nom :");
		nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nomLabel.setBounds(125, 177, 60, 14);
		contentPane.add(nomLabel);
		
		JLabel prenomLabel = new JLabel("Prénom :");
		prenomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prenomLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prenomLabel.setBounds(125, 218, 60, 14);
		contentPane.add(prenomLabel);
		
		JLabel CNELabel = new JLabel("CNE :");
		CNELabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CNELabel.setHorizontalAlignment(SwingConstants.CENTER);
		CNELabel.setBounds(139, 140, 46, 14);
		contentPane.add(CNELabel);
		
		JLabel ligne2 = new JLabel("");
		ligne2.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line.jpg"));
		ligne2.setBounds(482, 90, 4, 371);
		contentPane.add(ligne2);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(195, 252, 169, 30);
		contentPane.add(telephoneField);
		telephoneField.setColumns(10);
		
		departementField = new JComboBox();
		departementField.setToolTipText("");
		departementField.setBounds(195, 293, 169, 30);
		contentPane.add(departementField);
		departementField.addItem("----- Sélectionner une option -----");
		rempComboboxD();
		
		filiereField = new JComboBox();
		filiereField.setBounds(195, 334, 169, 30);
		contentPane.add(filiereField);
		filiereField.addItem("----- Sélectionner une option -----");
		rempComboboxF();
		
		prenomField = new JTextField();
		prenomField.setBounds(195, 211, 169, 30);
		contentPane.add(prenomField);
		prenomField.setColumns(10);
		
		nomField = new JTextField();
		nomField.setBounds(195, 170, 169, 30);
		contentPane.add(nomField);
		nomField.setColumns(10);
		
		CNEField = new JTextField();
		CNEField.setBounds(195, 133, 169, 30);
		contentPane.add(CNEField);
		CNEField.setColumns(10);
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
	
	public void rempComboboxF() {
		
		String sql = "SELECT * FROM filiere";
		try {
			prepared = cnx.prepareStatement(sql);
			rs = prepared.executeQuery();
			while(rs.next()) {
				
				String nom = rs.getString("nom").toString();
				filiereField.addItem(nom);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
public void rempComboboxD() {
		
		String sql = "SELECT * FROM departement";
		try {
			prepared = cnx.prepareStatement(sql);
			rs = prepared.executeQuery();
			while(rs.next()) {
				
				String nom = rs.getString("nom").toString();
				departementField.addItem(nom);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

public void ListeE () {
	
	String sql = "SELECT * FROM etudiant";
	try {
		prepared = cnx.prepareStatement(sql);
		rs = prepared.executeQuery();
		TableE.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
}
}
