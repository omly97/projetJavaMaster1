package vue.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import img.MyIcon;
import myUX.MyLabel;
import myUX.MyPanel;
import myUX.Theme;

@SuppressWarnings("serial")
public class Topbar extends MyPanel {
	
	private MyPanel panelEst;
	private MyPanel panelOuest;
	private MyLabel lTitre;
	private JButton bNotification;
	private JButton bUser;
	private JButton bLogout;

	public Topbar() {
		super(true);
		// PANEL EST
		lTitre = new MyLabel(MyLabel.H5);
		panelOuest = new MyPanel();
		panelOuest.add(lTitre);
		
		// PANEL OUEST
		bNotification = createMenuButton("0", "img09-notification.png");
		bUser = createMenuButton("Anonymous", "img06-user.png");
		bLogout = createMenuButton("Deconnexion", "img08-logout.png");
		panelEst = new MyPanel();
		panelEst.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelEst.add(bNotification);
		panelEst.add(bUser);
		panelEst.add(bLogout);

		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelEst, BorderLayout.EAST);
		add(panelOuest, BorderLayout.WEST);
		setBorder(new EmptyBorder(3, 15, 3, 15));
	}

	public void setTitre(String titre) {
		lTitre.setText(titre);
	}
	
	public void setAlert(int nombre) {
		bNotification.setText(String.valueOf(nombre));
	}

	public void setUser(String username) {
		bUser.setText(username);
	}
	
	public JButton getbNotification() {
		return bNotification;
	}
	
	public JButton getbUser() {
		return bUser;
	}
	
	public JButton getbLogout() {
		return bLogout;
	}
	
	public void setActionListener(ActionListener a) {
		bNotification.addActionListener(a);
		bUser.addActionListener(a);
		bLogout.addActionListener(a);
	}


	private JButton createMenuButton(String label, String ionName) {
		JButton b = new JButton(label);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setFont(Theme.APP_FONT);
		b.setBackground(Theme.UI_BACKGROUND);
		b.setIcon(MyIcon.createImageIcon(20, 20, ionName));
		return b;
	}
}
