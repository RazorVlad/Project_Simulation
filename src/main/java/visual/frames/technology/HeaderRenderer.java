package visual.frames.technology;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import visual.tableBuilders.VerticalLabelUI;

public class HeaderRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 7098733303959108437L;

	// метод возвращает компонент для прорисовки
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		// получаем настроенную надпись от базового класса
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		label.setBorder(BorderFactory.createLineBorder(java.awt.Color.gray));
		label.setFont(new java.awt.Font("Calibri", java.awt.Font.PLAIN, 12));
		label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setUI(new VerticalLabelUI());
//		java.awt.Color color = new java.awt.Color(233, 236, 240);
		// label.setBackground(color);
		return label;
	}
}