package vue.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import img.MyIcon;
import myUX.MyPanel;
import vue.auth.FormLogin;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JSplitPane splitPane;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JLabel imageLabel;
	private FormLogin form;
	
	private final Color BACKGROUNG_LEFT = new Color(33, 150, 243);


	public LoginFrame() {
		super("Connexion");
		// CREATE COMPONENTS
		splitPane = new JSplitPane();
		panelGauche = new MyPanel(true);
		panelDroite = new MyPanel(true);
		imageLabel = new JLabel();
		form = new FormLogin();
		
		// LEFT
		panelGauche.setPreferredSize(new Dimension(300, 700));
		panelGauche.setBackground(BACKGROUNG_LEFT);
		panelGauche.setLayout(new FlowLayout());
		panelGauche.add(imageLabel);
		
		// RIGHT
		panelDroite.setBorder(new EmptyBorder(40, 100, 120, 100));
		panelDroite.setLayout(new GridLayout(1, 1));
		panelDroite.add(form);
		
		// FORM TITLE AND IMAGE
		form.setTitle("Connexion");
		form.setIcon(MyIcon.createImageIcon(200, 200, "img07-login.png"));
		
		// LABEL IMAGE
		imageLabel.setIcon(MyIcon.createImageIcon(200, 200, "img10-stock.png"));

		// split pane
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(panelGauche);
		splitPane.setRightComponent(panelDroite);
		splitPane.setResizeWeight(0.50);
		splitPane.setDividerSize(0);

		// THIS FRAME
		add(splitPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 50);
		setSize(900, 700);
		setResizable(false);
	}
	
	public FormLogin getForm() {
		return form;
	}
}
