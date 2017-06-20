package visual.frames.technology;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class SampleTableHeaderMouseListener implements MouseListener {

	private JTable table;

	public SampleTableHeaderMouseListener(JTable table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent e) {
		if (table.getRowCount() == 0 || table.getColumnCount() == 0) {
			return;
		}

		int columnIndex = table.getColumnModel().getColumnIndexAtX(e.getX());
		if (columnIndex == 0) {
			table.setRowSelectionInterval(0, table.getRowCount() - 1);
			table.setColumnSelectionInterval(1, table.getColumnCount() - 1);

			return;
		}

		if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
			table.addRowSelectionInterval(0, table.getRowCount() - 1);
			table.addColumnSelectionInterval(columnIndex, columnIndex);
		} else {
			table.setRowSelectionInterval(0, table.getRowCount() - 1);
			table.setColumnSelectionInterval(columnIndex, columnIndex);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}