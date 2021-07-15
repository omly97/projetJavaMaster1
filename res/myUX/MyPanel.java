package myUX;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	
	
	public MyPanel() {
		setOpaque(false);
	}
	
	public MyPanel(boolean isOpaque) {
		super();
		if (isOpaque) setBackground(Theme.UI_BACKGROUND);
		else setOpaque(false);
	}
}
