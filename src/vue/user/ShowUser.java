package vue.user;

import java.awt.BorderLayout;

import javax.swing.JButton;

import model.entites.User;
import myUX.MyPanel;
import vue.component.InfoModel;

@SuppressWarnings("serial")
public class ShowUser extends MyPanel {
	
	private InfoModel panelNordInfo;
	private CardUser cardUser;

	public ShowUser() {
		super();
		// UI
		panelNordInfo = new InfoModel();
		cardUser = new CardUser(false);
		panelNordInfo.setPanelOnCenter(cardUser);

		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelNordInfo, BorderLayout.NORTH);
	}


	public void setUser(User user) {
		cardUser.setUser(user);
		getbEditer().setActionCommand(String.valueOf(user.getId()));
		getbEditer().setActionCommand(String.valueOf(user.getId()));
	}
	
	public void setImageIcon(String icon) {
		panelNordInfo.setIcon(icon);
	}

	public JButton getbEditer() {
		return panelNordInfo.getbEditer();
	}
	
	public JButton getbSupprimer() {
		return panelNordInfo.getbSupprimer();
	}
}
