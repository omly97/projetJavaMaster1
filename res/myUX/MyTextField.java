package myUX;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class MyTextField extends JTextField {

	
	public MyTextField(int length) {
		super(length);
		setOpaque(false);
		setFont(Theme.APP_FONT);
		setForeground(Theme.APP_FOREGROUND);
		setCaretColor(Theme.APP_FOREGROUND);
		setBorder(new MatteBorder(0, 0, 2, 0, Theme.APP_FOREGROUND));
	}
}
