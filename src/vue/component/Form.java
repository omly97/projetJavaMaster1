package vue.component;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class Form extends MyPanel {
	
	protected JPanel panelNord;
	protected JPanel panelNordCentre;
	protected JPanel panelNordSud;
	protected JPanel panelCentre;
	protected JLabel labelImage;
	protected JLabel labelTitre;
	protected JButton bSoumettre;

	public Form() {
		super(true);
		// NORTH
		panelNord = new MyPanel();
		panelNordCentre = new MyPanel();
		panelNordSud = new MyPanel();
		panelNord.setLayout(new BorderLayout());
		panelNord.add(panelNordCentre, BorderLayout.CENTER);
		panelNord.add(panelNordSud, BorderLayout.SOUTH);
		
		// NORTH CENTER
		labelImage = new JLabel();
		panelNordCentre.add(labelImage);
		
		// NORTH SOUTH
		labelTitre = new MyLabel(MyLabel.H2);
		panelNordSud.add(labelTitre);
		
		// CENTER
		panelCentre = new MyPanel();

		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelNord, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
	}
	
	public void setIcon(ImageIcon image) {
		labelImage.setIcon(image);
	}
	
	public void setTitle(String text) {
		labelTitre.setText(text);
	}

	public JButton getbSoumettre() {
		return bSoumettre;
	}
}
