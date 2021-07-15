package vue.component;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Sidebar extends JPanel {

	private JButton bDashboard;
	private JButton bProduit;
	private JButton bFournisseur;
	private JButton bClient;
	private JButton bApprovision;
	private JButton bVente;
	private JButton bUser;
	
	private Vector<JButton> buttons;
	
	private final Color BACKGROUND = new Color(50, 50, 50);
	private final Color FOREGROUND = new Color(188, 186, 187);
	private final Color MENU_ACTIVE_BACKGROUNG = new Color(60, 60, 60);
	private final Color MENU_ACTIVE_BORDER = new Color(33, 150, 243);


	public Sidebar() {
		super();
		// BUTTONS
		bDashboard = createMenuButton("Dashboard");
		bProduit = createMenuButton("Produit");
		bFournisseur = createMenuButton("Fournisseur");
		bClient = createMenuButton("Client");
		bApprovision = createMenuButton("Approvision");
		bVente = createMenuButton("Vente");
		bUser = createMenuButton("User");
		
		// VECTOR
		buttons = new Vector<JButton>();
		buttons.add(bDashboard);
		buttons.add(bProduit);
		buttons.add(bFournisseur);
		buttons.add(bClient);
		buttons.add(bApprovision);
		buttons.add(bVente);
		buttons.add(bUser);
		
		// ACTIVE DASHBOARD BUTTON
		selectMenu(bDashboard);

		// ADD BUTTONS TO SIDEBAR
		setLayout(new GridLayout(15, 1));
		add(bDashboard);
		add(bProduit);
		add(bFournisseur);
		add(bClient);
		add(bApprovision);
		add(bVente);
		add(bUser);
		setBackground(BACKGROUND);
	}
	
	public JButton getbDashboard() {
		return bDashboard;
	}

	public JButton getbProduit() {
		return bProduit;
	}

	public JButton getbFournisseur() {
		return bFournisseur;
	}
	
	public JButton getbClient() {
		return bClient;
	}

	public JButton getbApprovision() {
		return bApprovision;
	}
	
	public JButton getbVente() {
		return bVente;
	}

	public JButton getbUser() {
		return bUser;
	}


	public void setActionListener(ActionListener a) {
		for (JButton b : buttons) {
			b.addActionListener(a);
		}
	}


	private JButton createMenuButton(String label) {
		JButton b = new JButton(label);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setForeground(FOREGROUND);
		b.setBackground(BACKGROUND);
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectMenu(b);
			}
		});
		return b;
	}
	
	public void selectMenu(JButton b) {
		for (JButton btn : buttons) {
			btn.setBorderPainted(false);
			btn.setBackground(BACKGROUND);
		}
		b.setBorderPainted(true);
		b.setBackground(MENU_ACTIVE_BACKGROUNG);
		b.setBorder(new MatteBorder(0, 5, 0, 0, MENU_ACTIVE_BORDER));
	}
}
