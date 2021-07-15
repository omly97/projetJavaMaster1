package myUX;

import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class MyPasswordField extends JPasswordField {

	
	public MyPasswordField(int length) {
		super(length);
		setOpaque(false);
		setFont(Theme.APP_FONT);
		setForeground(Theme.APP_FOREGROUND);
		setCaretColor(Theme.APP_FOREGROUND);
		setBorder(new MatteBorder(0, 0, 2, 0, Theme.APP_FOREGROUND));
	}
}
