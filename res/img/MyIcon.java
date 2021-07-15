package img;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MyIcon {


	// CREATE IMAGE
	public static ImageIcon createImageIcon(int width, int height, String filename) {
		ImageIcon img = new ImageIcon("res/icons/" + filename);
		Image imgResized = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(imgResized);
	}
}
