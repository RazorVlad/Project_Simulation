package visual.frames.technology;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SampleTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 8694273341714305868L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		JLabel label = new JLabel(value.toString());
		label.setFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN, 12));
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
//		java.awt.Color color = new java.awt.Color(233, 236, 240);
		// label.setBackground(color);

		if (isSelected) {
			label.setOpaque(true);
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());

		}

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label);

		return panel;
	}

}