package vue.user;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import model.entites.User;
import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class CardUser extends MyPanel {

	private JLabel lTitreNom;
	private JLabel lTitreRole;
	private JLabel lTitreLogin;
	private JLabel lNom;
	private JLabel lRole;
	private JLabel lLogin;
	
	public CardUser(boolean showTitle) {
		super();
		if (showTitle) {
			lTitreNom = new MyLabel("Nom", MyLabel.H6);
			lTitreRole = new MyLabel("Role", MyLabel.H6);
			lTitreLogin = new MyLabel("Login", MyLabel.H6);
			lNom = new MyLabel(MyLabel.H5);
			lRole = new MyLabel(MyLabel.H5);
			lLogin = new MyLabel(MyLabel.H5);
			setLayout(new GridLayout(3, 2, 20, 10));
			add(lTitreNom);
			add(lNom);
			add(lTitreRole);
			add(lRole);
			add(lTitreLogin);
			add(lLogin);
		}
		else {
			lNom = new MyLabel(MyLabel.H4);
			lRole = new MyLabel(MyLabel.H5);
			lLogin = new MyLabel(MyLabel.H5);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(lNom);
			add(lRole);
			add(lLogin);
		}
	}

	public void setUser(User user) {
		lNom.setText(user.getNom());
		lRole.setText(user.getRole());
		lLogin.setText(user.getLogin());
	}
}
