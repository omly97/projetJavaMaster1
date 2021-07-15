package myUX;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton {
	
	public static final int SUCCESS = 1;
	public static final int PRIMARY = 2;
	public static final int DANGER = 3;
	
	private final Color FOREGROUND = new Color(255, 255, 255);
	private final Color BACKGROUND_DEFAULT = new Color(237, 240, 244);
	private final Color BACKGROUND_SUCCESS = new Color(33, 136, 56);
	private final Color BACKGROUND_PRIMARY = new Color(0, 98, 204);
	private final Color BACKGROUND_DANGER = new Color(219, 28, 44);

	public MyButton(String title) {
		super(title);
		setPreferredSize(new Dimension(150, 30));
		setBackground(BACKGROUND_DEFAULT);
	}
	
	public MyButton(String title, int btnType) {
		super(title);
		setPreferredSize(new Dimension(150, 30));
		setForeground(FOREGROUND);
		if (btnType == SUCCESS) setBackground(BACKGROUND_SUCCESS);
		if (btnType == PRIMARY) setBackground(BACKGROUND_PRIMARY);
		if (btnType == DANGER) setBackground(BACKGROUND_DANGER);
	}
}
