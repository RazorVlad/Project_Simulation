package visual.frames.technology.bMatrix;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import visual.tableBuilders.cellSpan.AttributiveCellRenderer;
import visual.tableBuilders.cellSpan.AttributiveCellTableModel;
import visual.tableBuilders.cellSpan.ColoredCell;

public class ColumnsSumMatrix extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291037659585560829L;
	JTable columnsSumTable;
	JScrollPane scrollPane;

	public ColumnsSumMatrix() {
		setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		add(scrollPane);
		setColumnsSumMatrix(null, 0);
		scrollPane.setViewportView(columnsSumTable);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	public void setColumnsSumMatrix(double[][] values, int rowSize) {
		int columnCount = 1;
		int rowCount = 1;
		if (values != null) {
			columnCount = values[0].length + 1;
			rowCount = values.length;
		}
		AttributiveCellTableModel ml = new AttributiveCellTableModel(rowCount, columnCount) {

			private static final long serialVersionUID = -9083101299087807962L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		ColoredCell cellAtt = (ColoredCell) ml.getCellAttribute();
		int[] columns = new int[columnCount];
		int[] rows = new int[1];
		for (int i = 0; i < columnCount; i++) {
			columns[i] = i;
			// rows[i] = i + 1;
		}
		rows[0] = 1;
		cellAtt.setBackground(new java.awt.Color(240, 240, 240), rows, columns);

		columnsSumTable = new JTable(ml);
		if (values != null) {
			for (byte i = 1; i < values[0].length + 1; i++) {
				for (byte j = 0; j < values.length; j++) {
					columnsSumTable.setValueAt(values[j][i - 1], j, i);
				}
			}
		}
		columnsSumTable.setValueAt("Cумма", 0, 0);
		if (rowCount > 1)
			columnsSumTable.setValueAt("Доля", 1, 0);

		columnsSumTable.setRowHeight(25);
		columnsSumTable.getColumnModel().getColumn(0).setMinWidth(380);
		columnsSumTable.getColumnModel().getColumn(0).setMaxWidth(380);
		columnsSumTable.getColumnModel().getColumn(0).setPreferredWidth(400);
		columnsSumTable.getColumnModel().getColumn(0).setHeaderValue("");

		for (int i = 1; i < columnCount; i++) {
			columnsSumTable.getColumnModel().getColumn(i).setMinWidth(40);
			columnsSumTable.getColumnModel().getColumn(i).setMaxWidth(200);
			columnsSumTable.getColumnModel().getColumn(i).setPreferredWidth(rowSize);
			columnsSumTable.getColumnModel().getColumn(i).setHeaderValue("");
		}

		columnsSumTable.setFillsViewportHeight(true);
		columnsSumTable.getTableHeader().setReorderingAllowed(false);
		columnsSumTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnsSumTable.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
		columnsSumTable.setAlignmentX(CENTER_ALIGNMENT);
		columnsSumTable.revalidate();
		columnsSumTable.repaint();
		scrollPane.setViewportView(columnsSumTable);
	}

	public void refreshRowSize(int rowSize) {
		for (byte i = 1; i < columnsSumTable.getColumnCount(); i++) {
			columnsSumTable.getColumnModel().getColumn(i).setMinWidth(40);
			columnsSumTable.getColumnModel().getColumn(i).setMaxWidth(200);
			columnsSumTable.getColumnModel().getColumn(i).setPreferredWidth(rowSize);
			columnsSumTable.revalidate();
			columnsSumTable.repaint();
		}
	}
}
