package myUX;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MyAlert extends JPanel {
	
	public static final int DEFAULT = 0;
	public static final int PRIMARY = 1;
	public static final int SUCCESS = 2;
	public static final int WARNING = 3;
	public static final int DANGER = 4;
	
	private JLabel label;

	
	public MyAlert(String message) {
		super();
		label = new JLabel(message);
		add(label);
		setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	public void setType(int alertType) {
		if (alertType == DEFAULT) colorAlert(new Color(226, 227, 229), new Color(56, 61, 65));
		if (alertType == PRIMARY) colorAlert(new Color(204, 229, 255), new Color(60, 112, 169));
		if (alertType == SUCCESS) colorAlert(new Color(212, 237, 218), new Color(102, 151, 114));
		if (alertType == WARNING) colorAlert(new Color(225, 243, 205), new Color(197, 175, 109));
		if (alertType == DANGER) colorAlert(new Color(248, 215, 218), new Color(126, 44, 51));
	}

	public void setMessage(String msg) {
		label.setText(msg);
	}
	
	private void colorAlert(Color background, Color foreground) {
		setBackground(background);
		label.setForeground(foreground);
	}
}
