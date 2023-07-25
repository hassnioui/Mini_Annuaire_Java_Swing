import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class index {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index window = new index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(251, 251, 251));
		frame.getContentPane().setLayout(null);
		
		JLabel MenuUtilisation_Label = new JLabel("Menu Utilisation");
		MenuUtilisation_Label.setHorizontalAlignment(SwingConstants.CENTER);
		MenuUtilisation_Label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		MenuUtilisation_Label.setBounds(600, 354, 128, 31);
		frame.getContentPane().add(MenuUtilisation_Label);
		
		JLabel MenuAdministration_Label = new JLabel("Menu Administration");
		MenuAdministration_Label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		MenuAdministration_Label.setHorizontalAlignment(SwingConstants.CENTER);
		MenuAdministration_Label.setBounds(241, 354, 128, 31);
		frame.getContentPane().add(MenuAdministration_Label);
		
		JButton MenuUtilisation_Btn = new JButton("");
		MenuUtilisation_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MenuUtilisation obj = new MenuUtilisation();
				obj.setVisible(true);
			}
		});
		MenuUtilisation_Btn.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\user.png"));
		MenuUtilisation_Btn.setBounds(600, 244, 128, 105);
		frame.getContentPane().add(MenuUtilisation_Btn);
		
		JButton MenuAdministration_Btn = new JButton("");
		MenuAdministration_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MenuAdministration obj = new MenuAdministration();
				obj.setVisible(true);
			}
		});
		MenuAdministration_Btn.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\Ad.png"));
		MenuAdministration_Btn.setBounds(241, 244, 128, 105);
		frame.getContentPane().add(MenuAdministration_Btn);
		
		JLabel Title1 = new JLabel("Mini Annuaire");
		Title1.setHorizontalAlignment(SwingConstants.CENTER);
		Title1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 44));
		Title1.setBounds(348, -13, 314, 92);
		frame.getContentPane().add(Title1);
		
		JLabel Title2 = new JLabel("ENS Tetouan");
		Title2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Title2.setHorizontalAlignment(SwingConstants.CENTER);
		Title2.setBounds(427, 52, 153, 38);
		frame.getContentPane().add(Title2);
		
		JLabel Ligne = new JLabel("");
		Ligne.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line.jpg"));
		Ligne.setBounds(0, 90, 984, 3);
		frame.getContentPane().add(Ligne);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("C:\\Users\\Amine\\eclipse-workspace\\Mini_Annuaire_Java\\src\\images\\line - Copy.jpg"));
		Background.setBounds(0, 90, 984, 371);
		frame.getContentPane().add(Background);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
