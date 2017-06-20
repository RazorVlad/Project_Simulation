package visual.frames.technology.coefAxAi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.technology.HeaderRenderer;
import visual.frames.technology.SampleTableCellRenderer;
import visual.frames.technology.SampleTableHeaderMouseListener;
import visual.tableBuilders.TableEditor;
import visual.tableBuilders.TablePropertyChange;
import visual.tableBuilders.cellSpan.AttributiveCellRenderer;
import visual.tableBuilders.cellSpan.AttributiveCellTableModel;
import visual.tableBuilders.cellSpan.ColoredCell;

public class CoefAxAi extends JPanel {

	private static final long serialVersionUID = -6033526916710329563L;
	private static Logger LOG = Logger.getLogger(CoefAxAi.class);
	private JTable coefAxAi;
	JScrollPane scrollPane;
	ColoredCell cellAtt;
	TableEditor te;
	Color color = new Color(214, 217, 223);
	int[][] columns1 = { { 1, 2, 3 }, { 7, 8, 9 }, { 11 }, { 15 }, { 18, 19, 20, 21, 22 }, { 24 }, { 26 } };
	int[][] rows1 = { { 0, 1, 2 }, { 6, 7, 8 }, { 10 }, { 14 }, { 17, 18, 19, 20, 21 }, { 23 }, { 25 } };
	int[][] columns2 = { { 4, 5, 6 }, { 10 }, { 12, 13, 14 }, { 16, 17 }, { 23 }, { 25 } };
	int[][] rows2 = { { 3, 4, 5 }, { 9 }, { 11, 12, 13 }, { 15, 16 }, { 22 }, { 24 } };

	public CoefAxAi(String[][] data, String[] columnNames) {
		setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		add(scrollPane);

		AttributiveCellTableModel ml = new AttributiveCellTableModel(26, 27);

		coefAxAi = new JTable(ml);
		coefAxAi.setModel(new AttributiveCellTableModel(26, 27) {

			private static final long serialVersionUID = -956535446675010606L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					if (column == 10 || column == 11 || column == 15 || (column >= 23 && column <= 26)) {
						if (row == 9 || row == 10 || row == 14 || (column >= 22 && column <= 25)) {
							return false;
						} else {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		});
		coefAxAi.addPropertyChangeListener(new TablePropertyChange(coefAxAi, false));
		cellAtt = (ColoredCell) ((AttributiveCellTableModel) coefAxAi.getModel()).getCellAttribute();
		coefAxAi.setShowHorizontalLines(true);
		coefAxAi.setShowVerticalLines(true);

		setColor();

		for (byte i = 0; i < 26; i++) {
			coefAxAi.setValueAt(data[i][0], i, 0);
		}
		for (byte i = 1; i < 27; i++) {
			coefAxAi.getColumnModel().getColumn(i).setHeaderValue(columnNames[i - 1]);
		}
		coefAxAi.getColumnModel().getColumn(0)
				.setHeaderValue("                                                                  ");
		coefAxAi.setRowHeight(25);
		te = new TableEditor(coefAxAi);
		te.setColumnWidth(0, 385, 400, 385);

		int prefferedWidth = (this.getWidth() - 385) / 26;
		for (int i = 1; i < coefAxAi.getColumnCount(); i++) {
			te.setColumnWidth(i, 40, prefferedWidth, 400);
		}
		int[] cells = { 9, 10, 14, 22, 23, 24, 25 };
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				coefAxAi.setValueAt(1, cells[i], (cells[j]) + 1);
			}
		}
		coefAxAi.setFillsViewportHeight(true);
		coefAxAi.getTableHeader().setReorderingAllowed(false);
		coefAxAi.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
		coefAxAi.getColumnModel().getColumn(0).setCellRenderer(new SampleTableCellRenderer());
		coefAxAi.getTableHeader().addMouseListener(new SampleTableHeaderMouseListener(coefAxAi));
		coefAxAi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header1 = coefAxAi.getTableHeader();
		header1.setDefaultRenderer(new HeaderRenderer());
		coefAxAi.setAlignmentX(CENTER_ALIGNMENT);
		coefAxAi.revalidate();
		coefAxAi.repaint();
		scrollPane.setViewportView(coefAxAi);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				TableEditor tableEditor = new TableEditor(coefAxAi);
				int prefferedWidth = (getWidth() - 380) / 26;
				for (int i = 1; i < coefAxAi.getColumnCount(); i++) {
					tableEditor.setColumnWidth(i, 40, prefferedWidth, 300);
				}
			}
		});
	}

	public void refreshRowSize(int rowSize) {
		for (int i = 1; i < coefAxAi.getColumnCount(); i++) {
			te.setColumnWidth(i, 40, rowSize, 400);
		}
		coefAxAi.revalidate();
		coefAxAi.repaint();
	}

	public boolean isDataSet() {
		for (int i = 0; i < coefAxAi.getRowCount(); i++) {
			for (int j = 1; j < coefAxAi.getColumnCount(); j++) {
				try {
					if (coefAxAi.getValueAt(i, j).toString().length() < 1) {
						return false;
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(),e);
					return false;
				}
			}
		}
		return true;
	}

	public double[][] getTable() {
		double[][] table = new double[coefAxAi.getRowCount()][coefAxAi.getColumnCount()];
		for (int i = 0; i < coefAxAi.getRowCount(); i++) {
			for (int j = 1; j < coefAxAi.getColumnCount(); j++) {
				try {
					table[i][j - 1] = Format.getDouble(coefAxAi.getValueAt(i, j).toString());
				} catch (Exception e) {
					LOG.error(e.getMessage(),e);
					table[i][j - 1] = 0;
				}
			}
		}
		return table;
	}

	public void setTable(double[][] data) {
		if (data != null) {
			for (int i = 0; i < coefAxAi.getRowCount(); i++) {
				for (int j = 1; j < coefAxAi.getColumnCount(); j++) {
					coefAxAi.getModel().setValueAt(data[i][j - 1], i, j);
				}
			}
		}
	}

	public boolean isDataOk() {
		return (testData(columns1, rows1) && testData(columns2, rows2) && testData(columns1, rows2) && testData(
				columns2, rows1));
	}

	public boolean testData(int[][] columns, int[][] rows) {
		setColor();
		boolean answer = true;
		for (int[] column1 : columns) {
			for (int t = 0; t < rows.length; t++) {
				double sum = 0;
				int[] row = new int[rows[t].length * column1.length];
				int[] column = new int[rows[t].length * column1.length];
				int index = 0;
				for (int j = 0; j < column1.length; j++) {
					for (int k = 0; k < rows[t].length; k++) {
						column[index] = column1[j];
						row[index] = rows[t][k];
						index++;
						try {
							double value = Format.getDouble(coefAxAi.getValueAt(rows[t][k], column1[j])
									.toString());
							sum += value;
						} catch (Exception e) {
							LOG.error(e.getMessage(), e);
							answer = false;
							break;
						}
					}
				}
				if (sum < 0.99999999 || sum > 1.0000001) {
					answer = false;
					cellAtt.setBackground(Color.red, row, column);
					break;
				}
			}
		}
		return answer;
	}

	void setColor() {
		int[] columns = new int[27];
		int[] rows = new int[26];
		for (int i = 0; i < 26; i++) {
			columns[i] = i;
			rows[i] = i;
		}
		columns[26] = 26;
		cellAtt.setBackground(new Color(240, 240, 240), rows, columns);

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				cellAtt.setBackground(color, rows1[i], columns1[j]);
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				cellAtt.setBackground(color, rows2[i], columns2[j]);
			}
		}
	}

}