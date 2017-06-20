package visual.tableBuilders;

import javax.swing.JTable;

public class TableEditor {
	JTable table;

	public TableEditor(JTable table) {
		this.table = table;
	}
	public void setColumnWidth(int column,int minWidth,int width,int maxWidth){
		table.getColumnModel().getColumn(column).setPreferredWidth(width);
		table.getColumnModel().getColumn(column).setMinWidth(minWidth);
		table.getColumnModel().getColumn(column).setMaxWidth(maxWidth);
	}
}
