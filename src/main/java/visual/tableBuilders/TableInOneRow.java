package visual.tableBuilders;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.util.Log;

public class TableInOneRow {

	public static JTable setTable(double[][] data, int startYear, int columnCount) {
		JTable table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[1][columnCount], new String[columnCount]));
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(startYear + i);
		}
		if (data != null) {
			try {
				for (int i = 0; i < table.getColumnCount(); i++) {
					table.setValueAt(data[1][i], 0, i);
				}
			} catch (Exception e) {
				Log.error(e.getMessage(), e);
			}
		}
		table.setRowHeight(25);
		table.setFillsViewportHeight(true);
		return table;
	}
}
