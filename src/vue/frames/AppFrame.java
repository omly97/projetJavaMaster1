package vue.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import myUX.Theme;
import vue.component.Menubar;
import vue.component.Sidebar;
import vue.component.Topbar;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

	private Menubar menubar;
	private Topbar topbar;
	private Sidebar sidebar;
	private JSplitPane splitPane;
	private JPanel panelLeft;
	private JPanel panelMain;
	private CardLayout cardLayout;

	public AppFrame() {
		super("Sama Stock");
		// CREATE UI COMPONENT
		menubar = new Menubar();
		topbar = new Topbar();
		sidebar = new Sidebar();
		splitPane = new JSplitPane();
		panelMain = new JPanel();
		panelLeft = new JPanel();
		cardLayout = new CardLayout();
		
		// PANEL LEFT
		sidebar.setPreferredSize(new Dimension(300, 900));
		
		//PANEL RIGHT
		panelLeft.setLayout(new BorderLayout());
		panelLeft.add(topbar, BorderLayout.NORTH);
		panelLeft.add(panelMain, BorderLayout.CENTER);

		// CARD LAYOUT
		panelMain.setLayout(cardLayout);
		panelMain.setBackground(Theme.APP_BACKGROUND);
		panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// SPLITPANE
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(sidebar);
		splitPane.setRightComponent(panelLeft);
		splitPane.setResizeWeight(0.35);
		splitPane.setDividerSize(8);

		// THIS FRAME
		add(menubar);
		setJMenuBar(menubar);
		add(splitPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 50);
		setSize(1000, 750);
		setMinimumSize(new Dimension(900, 550));
	}

	public Menubar getMenubar() {
		return menubar;
	}
	
	public Topbar getTopbar() {
		return topbar;
	}

	public Sidebar getSidebar() {
		return sidebar;
	}
	
	public JPanel getPanelMain() {
		return panelMain;
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
