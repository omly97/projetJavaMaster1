package myUX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MyTable extends MyPanel {

	protected DefaultTableModel model;
	protected JTable table;
	protected JScrollPane scPane;
	
	private final Color SELECTION_BACKGROUNG = new Color(188, 186, 187);


	public MyTable() {
		super(true);
		model = new DefaultTableModel();
		
		table = new JTable(model);
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setSelectionBackground(SELECTION_BACKGROUNG);
		table.setBackground(Theme.UI_BACKGROUND);
		table.setForeground(Theme.APP_FOREGROUND);
		table.setFont(Theme.APP_FONT);

		table.getTableHeader().setBackground(Theme.UI_BACKGROUND);
		table.getTableHeader().setForeground(Theme.APP_FOREGROUND);
		table.getTableHeader().setFont(Theme.APP_FONT);
		table.getTableHeader().setPreferredSize(new Dimension(10, 30));
		
		scPane = new JScrollPane(table);
		scPane.setBorder(new LineBorder(Theme.UI_BACKGROUND));
		scPane.getViewport().setBackground(Theme.UI_BACKGROUND);

		setLayout(new GridLayout(0, 1));
		add(scPane);
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}

	public ListSelectionModel getSelectionModel() {
		return table.getSelectionModel();
	}
}
