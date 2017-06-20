package visual.frames.modelResult;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

public class ResultTableModel extends DefaultTableModel {
	// TODO ���-�� ������������?
	private static final long serialVersionUID = -6131439895984876525L;
	String dat;
	JButton button = new JButton("������");

	ResultTableModel(String tname, String[][] dataValues, String[] columnNames) {
		super(dataValues, columnNames);
		dat = tname;
	}

	public boolean isCellEditable(int row, int cols) {
		if (Objects.equals(dat, "owntable")) {
			if (cols == 0) {
				return false;
			}
		}
		return true;
	}
}