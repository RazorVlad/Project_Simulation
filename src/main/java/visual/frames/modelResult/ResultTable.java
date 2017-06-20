package visual.frames.modelResult;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.RnD;
import mathModel.manufacturing.Manufacturing;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf_frame;

public class ResultTable extends JScrollPane {

	private static final long serialVersionUID = 834959369260309127L;
	JButton button = new JButton();
	private JTable table;
	private String[] columnNames = new String[Main.ModelLenght + 2];
	private String[][] dataValues;// =new String[9][Main.ModelLenght+2] ;
	int[] mainHeaders;
	private static Logger LOG = Logger.getLogger(ResultTable.class);

	public int[] getMainHeaders() {
		return mainHeaders;
	}

	public int[] getHeaders() {
		return headers;
	}

	public JTable getTable() {
		return table;
	}

	int[] headers;
	private ModelObjectsContainer model = ModelObjectsContainer.getInstance();

	public ResultTable() {

		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event) {

				double[][] xy = new double[2][Main.ModelLenght];
				int k = 0;
				for (int i = 0; i < table.getColumnCount() - 2; i++) {
					try {
						String s = (String) (table.getValueAt(table.getEditingRow(), i + 2));
						xy[1][i] = Format.getDouble(s);
					} catch (Exception e) {
						k = 1;// xy[0][i]=5;
						LOG.error(e.getMessage(), e);
					}
					xy[0][i] = Main.Year + i + 1;
				}
				if (k == 0) {
					FreeChartGraf_frame g = new FreeChartGraf_frame(table
							.getValueAt(table.getEditingRow(), 0).toString(), xy, new String[] { table
							.getValueAt(table.getEditingRow(), 0).toString() });

					g.setDomainAxis("����");
					g.show();
				}
			}
		});
	}

	void setTable(String chooseVED, String chooseSphere, int manufacturingType) {
		if (model.modelIsReady()) {
			columnNames[0] = "����������";
			for (int k = 2; k < Main.ModelLenght + 2; k++) {
				columnNames[k] = "" + (Main.Year + k - 1);
			}
			columnNames[1] = "������";
			int vedID = mathModel.VedNames.getID(chooseSphere, chooseVED);
			if (manufacturingType == 0) {
				int rows = ResultTableIndicators.inn.length;
				int columns = Main.ModelLenght + 2;
				dataValues = new String[rows][columns];
				for (int i = 0; i < 53; i++)
					dataValues[i][0] = ResultTableIndicators.inn[i];
				setVed(model.getVedInn(vedID), 0);
				headers = new int[] { 0, 1, 9, 13, 17, 21, 40 };
				mainHeaders = new int[] { 0 };
			}
			if (manufacturingType == 1) {
				dataValues = new String[ResultTableIndicators.ord.length][Main.ModelLenght + 2];
				for (int i = 0; i < 49; i++) {
					dataValues[i][0] = ResultTableIndicators.ord[i];
				}
				setVed(model.getVedOrd(vedID), 0);
				headers = new int[] { 0, 1, 9, 13, 17, 36 };
				mainHeaders = new int[] { 0 };
			}
			if (chooseVED.equals("��� ����") && manufacturingType == 0) {
				dataValues = new String[(ResultTableIndicators.stud.length
						+ ResultTableIndicators.niokr.length + ResultTableIndicators.inn.length)][Main.ModelLenght + 2];
				for (int i = 0; i < 12; i++) {
					dataValues[i][0] = ResultTableIndicators.stud[i];
				}
				for (int i = 12; i < 29; i++) {
					dataValues[i][0] = ResultTableIndicators.niokr[i - 12];
				}
				setStud(vedID);
				for (int i = 29; i < 82; i++) {
					dataValues[i][0] = ResultTableIndicators.inn[i - 29];
				}
				setVed(model.getVedInn(vedID), 29);
				headers = new int[] { 0, 4, 8, 12, 13, 21, 29, 30, 38, 42, 46, 50, 69 };
				mainHeaders = new int[] { 0, 12, 29 };
			}
			if (chooseVED.equals("��� ����") && manufacturingType == 1) {
				dataValues = new String[(ResultTableIndicators.stud.length
						+ ResultTableIndicators.niokr.length + ResultTableIndicators.ord.length)][Main.ModelLenght + 2];
				for (int i = 0; i < 12; i++) {
					dataValues[i][0] = ResultTableIndicators.stud[i];
				}
				for (int i = 12; i < 29; i++) {
					dataValues[i][0] = ResultTableIndicators.niokr[i - 12];
				}
				setStud(vedID);
				for (int i = 29; i < 78; i++) {
					dataValues[i][0] = ResultTableIndicators.ord[i - 29];
				}
				setVed(model.getVedOrd(vedID), 29);
				headers = new int[] { 0, 4, 8, 12, 13, 21, 29, 30, 38, 42, 46, 65 };
				mainHeaders = new int[] { 0, 12, 29 };
			}
			createTable();

		} else {
			JOptionPane.showMessageDialog(null, "������ ��� �� ���������", "��� ������",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void createTable() {
		table = new JTable();
		table.setModel(new DefaultTableModel(dataValues, columnNames) {

			private static final long serialVersionUID = -7409809560311114828L;

			public boolean isCellEditable(int row, int column) {
				return (column != 0);
			}
		});
		table.setRowHeight(25);
		table.getColumn("������").setCellRenderer(new ButtonRenderer());
		table.getColumn("������").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumnModel().getColumn(0).setPreferredWidth(400);

		setViewportView(table);
	}

	void setStud(int vedID) {
		// ����� �����������
		Education edu = model.getEducation(vedID);
		dataValues = ResultTableData.setStud(vedID, edu, dataValues);

		// ����� �����
		// ������-������. ������
		RnD rndR = model.getRndR(vedID);
		dataValues = ResultTableData.setRnD_R(rndR, dataValues);

		// ������-������. ������
		RnD rndD = model.getRndD(vedID);
		dataValues = ResultTableData.setRnD_D(rndD, dataValues);
	}

	void setVed(Manufacturing ved, int startIndex) {

		dataValues = ResultTableData.setVed(ved, startIndex, dataValues);
	}

	Boolean test(int row) {
		for (int header : headers) {
			if (row == header) {
				return true;
			}
		}
		return false;
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		private static final long serialVersionUID = 2750748826266366266L;

		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {

			if (!test(row)) {
				setText((value == null) ? "������" : value.toString());
			} else {
				setText("");
			}
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		private static final long serialVersionUID = 5048708429079021035L;
		private String label;
		int k;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			if (!test(row)) {
				label = (value == null) ? "������" : value.toString();
				button.setText("������");
				return button;
			} else {
				return null;
			}
		}

		public Object getCellEditorValue() {
			return label;
		}
	}
}
