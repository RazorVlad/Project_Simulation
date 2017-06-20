package visual.tableBuilders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import visual.frames.main.Main;

class CustomCell extends JFrame {
	private static final long serialVersionUID = -2191469391357420476L;
	private JPanel topPanel;
	private JTable table;
	private JScrollPane scrollPane;
	private String[] columnNames = new String[Main.ModelLenght + 2];
	private String[][] dataValues = new String[9][Main.ModelLenght + 2];
	JButton button = new JButton();

	public CustomCell() {
		columnNames[0] = "����������";
		for (int k = 1; k < Main.ModelLenght + 1; k++) {
			columnNames[k] = "" + (Main.Year + k);
		}
		columnNames[Main.ModelLenght + 1] = "������";
		setTitle("Button in JTable Cell");
		setSize(600, 300);
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		for (int i = 0; i < 9; i++) {
			for (int j = 1; j < Main.ModelLenght + 1; j++) {
				dataValues[i][j] = "";
			}
		}
		// dataValues[0][0]="����� �����������";
		// dataValues[1][0]="   ���������� 1";
		// dataValues[2][0]="   ���������� 2";
		// dataValues[3][0]="���";
		// dataValues[4][0]="   ���������� 1";
		// dataValues[5][0]="   ���������� 2";
		// dataValues[6][0]="���";
		// dataValues[7][0]="   ���������� 1";
		// dataValues[8][0]="   ���������� 2";

		TableModel model = new MyTableModel("owntable");
		table = new JTable();
		table.setModel(model);
		table.getColumn("������").setCellRenderer(new ButtonRenderer());
		table.getColumn("������").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane = new JScrollPane(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, table.getValueAt(table.getEditingRow(), 1));
			}
		});
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		private static final long serialVersionUID = -7758193145305777637L;

		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			setText((value == null) ? "������" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		private static final long serialVersionUID = 3020770138336315955L;
		private String label;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "������" : value.toString();
			button.setText("������");
			return button;
		}

		public Object getCellEditorValue() {
			return label;
		}
	}

	public class MyTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 8577642441349931157L;
		String dat;
		JButton button = new JButton("������");

		MyTableModel(String tname) {
			super(dataValues, columnNames);
			dat = tname;
		}

		public boolean isCellEditable(int row, int cols) {
			return !(dat.equals("owntable") && cols == 0);
		}
	}

	public static void main(String[] args) {
		CustomCell mainFrame = new CustomCell();
		mainFrame.setVisible(true);
	}
}
