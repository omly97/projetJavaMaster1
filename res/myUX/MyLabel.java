package myUX;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MyLabel extends JLabel {
	
	public static float H6 = 12.0f;
	public static float H5 = 15.0f;
	public static float H4 = 20.0f;
	public static float H3 = 25.0f;
	public static float H2 = 30.0f;
	public static float H1 = 35.0f;

	public MyLabel() {
		super();
		setFont(Theme.APP_FONT);
		setForeground(Theme.APP_FOREGROUND);
	}
	
	public MyLabel(String text) {
		this();
		setText(text);
	}
	
	public MyLabel(float size) {
		this();
		setFont(getFont().deriveFont(size));
		setBorder(new EmptyBorder(5, 0, 5, 0));
	}
	
	public MyLabel(String text, float size) {
		this(size);
		setText(text);
	}
}
