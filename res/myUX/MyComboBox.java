package myUX;

import java.awt.Graphics;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class MyComboBox extends JComboBox<String> {

	public MyComboBox() {
		super();
		setFont(Theme.APP_FONT);
		setBackground(Theme.UI_BACKGROUND);
		setForeground(Theme.APP_FOREGROUND);
		setBorder(new MatteBorder(0, 0, 2, 0, Theme.APP_FOREGROUND));
		setRenderer(new DefaultListCellRenderer() {
			@Override
			public void paint(Graphics g) {
				setBackground(Theme.UI_BACKGROUND);
				super.paint(g);
			}
		});
	}
}
