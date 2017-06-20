package visual.tableBuilders;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mathModel.Statistics;

import org.apache.log4j.Logger;

import visual.frames.main.Main;

public class TableWithRowHeaders {
	private static Logger LOG = Logger.getLogger(Main.class);

	public static JTable setTable(Statistics data, int startYear, int columnCount, String[] name) {
		JTable table = new JTable(name.length, columnCount);
		table.setModel(new DefaultTableModel(new Object[name.length][columnCount], new String[columnCount]) {
			private static final long serialVersionUID = 8900778925186327460L;

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});
		table.addPropertyChangeListener(new TablePropertyChange(table, false));
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setBounds(0, 0, 500, 80);
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(80);
			table.getColumnModel().getColumn(i).setHeaderValue(startYear + i - 1);
		}
		table.getColumnModel().getColumn(0).setHeaderValue("Показатели\\Года");
		for (int i = 0; i < name.length; i++) {
			table.setValueAt(name[i], i, 0);
		}
		if (data != null) {
			for (int i = 1; i < columnCount; i++) {
				try {
					table.setValueAt(data.getValue(i - 1), 0, i);
					table.getColumnModel().getColumn(i).setHeaderValue(data.getYear(i - 1));
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		table.getTableHeader().resizeAndRepaint();
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.revalidate();
		table.repaint();
		table.setFillsViewportHeight(true);
		return table;
	}
}
