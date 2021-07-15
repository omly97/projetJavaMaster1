package vue.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import img.MyIcon;
import myUX.MyButton;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class InfoModel extends MyPanel {
	
	private MyPanel panelGauche;
	private MyPanel panelCentre;
	private MyPanel panelSud;
	private JLabel imageIcon;
	private MyButton bEditer;
	private MyButton bSupprimer;

	
	public InfoModel() {
		super(true);
		// THIS PANEL CONFIG
		panelGauche = new MyPanel();
		panelCentre = new MyPanel();
		panelSud = new MyPanel();
		setLayout(new BorderLayout());
		add(panelGauche, BorderLayout.WEST);
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud, BorderLayout.SOUTH);
		
		// PANEL GAUCHE
		imageIcon = new JLabel();
		panelGauche.add(imageIcon);
		panelGauche.setBorder(new EmptyBorder(10, 50, 0, 0));
		
		// PANEL SUD
		bEditer = new MyButton("Editer", MyButton.PRIMARY);
		bSupprimer = new MyButton("Supprimer", MyButton.DANGER);
		panelSud.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelSud.add(bEditer);
		panelSud.add(bSupprimer);
	}

	public void setIcon(String icon) {
		imageIcon.setIcon(MyIcon.createImageIcon(150, 150, icon));
	}

	public void setPanelOnCenter(JPanel panel) {
		panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
		panelCentre.add(panel);
		panelCentre.setBorder(new EmptyBorder(50, 50, 0, 50));
	}
	
	public JButton getbEditer() {
		return bEditer;
	}
	
	public JButton getbSupprimer() {
		return bSupprimer;
	}
}
