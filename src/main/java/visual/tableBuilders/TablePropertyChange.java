package visual.tableBuilders;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;

import org.jfree.util.Log;

import visual.Format;

public class TablePropertyChange implements PropertyChangeListener {
	private JTable table;
	private boolean isPercent;

	public TablePropertyChange(JTable table, boolean isPercent) {
		this.table = table;
		this.isPercent = isPercent;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("tableCellEditor")) {
			try {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				Object object = table.getValueAt(row, column);
				if (object != null) {
					String s = object.toString();
					double value;
					if (!s.isEmpty() && !s.equals("true") && !s.equals("false")) {
						value = Format.getDouble(s);
						if (isPercent) {
							if (value > 100 || value < 0) {
								value = 0.0;
							}
						}
						table.setValueAt(Format.formatValue(value), table.getSelectedRow(),
								table.getSelectedColumn());
					}
				}
			} catch (Exception e) {
				Log.error(e.getMessage(), e);
			}
		}
	}
}
