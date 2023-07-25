import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListeDepartements extends JFrame {

	private JPanel contentPane;
	private JTable TableD;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField rechercheField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeDepartements frame = new ListeDepartements();
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
	public ListeDepartements() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 251, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = DBConnection.getConnection();
		
		JLabel refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				rechercheField.setText("");
				ListeD();
			}
		});
		refresh.setHorizontalAlignment(SwingConstants.CENTER);
		refresh.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\refresh.png"));
		refresh.setBounds(158, 133, 46, 30);
		contentPane.add(refresh);
		
		JButton recherchebtn = new JButton("");
		recherchebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechercheD();
			}
		});
		recherchebtn.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\search.png"));
		recherchebtn.setBounds(927, 104, 47, 36);
		contentPane.add(recherchebtn);
		
		rechercheField = new JTextField();
		rechercheField.setBounds(734, 104, 183, 36);
		contentPane.add(rechercheField);
		rechercheField.setColumns(10);
	   
		
		JLabel lblNewLabel = new JLabel("Liste Departements");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(376, 117, 231, 36);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(158, 164, 652, 286);
		contentPane.add(scrollPane);
		
		TableD = new JTable();
		TableD.setEnabled(false);
		scrollPane.setViewportView(TableD);
		ListeD();
		
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 MenuUtilisation obj = new MenuUtilisation ();
				 obj.setVisible(true);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\return.png"));
		btnNewButton.setBounds(39, 32, 53, 30);
		contentPane.add(btnNewButton);
		
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
	
	public void RechercheD() {
		
		
		String nom = rechercheField.getText().toString();
		String sql = "SELECT * FROM departement WHERE nom = ?";
		try {
			if (!nom.equals("")) {
				
			prepared = cnx.prepareStatement(sql);
			prepared.setString(1, nom);
			rs = prepared.executeQuery();
				TableD.setModel(DbUtils.resultSetToTableModel(rs));
			}else {
				
				JOptionPane.showMessageDialog(null, "S'il Vous Plaît Remplir Le Champ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
