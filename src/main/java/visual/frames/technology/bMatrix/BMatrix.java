package visual.frames.technology.bMatrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.technology.HeaderRenderer;
import visual.frames.technology.SampleTableCellRenderer;
import visual.frames.technology.SampleTableHeaderMouseListener;
import visual.tableBuilders.TablePropertyChange;
import visual.tableBuilders.cellSpan.AttributiveCellRenderer;
import visual.tableBuilders.cellSpan.AttributiveCellTableModel;
import visual.tableBuilders.cellSpan.CellFont;
import visual.tableBuilders.cellSpan.ColoredCell;

public class BMatrix extends JPanel {

	private static final long serialVersionUID = 7627941140486466836L;
	private static Logger LOG = Logger.getLogger(BMatrix.class);
	private JTable bMatrix;
	String[][] data;
	String[] columnNames;
	JScrollPane scrollPane;
	Boolean productivityCondition = true;

	public BMatrix(String[][] data, String[] columnNames) {
		this.data = data.clone();
		this.columnNames = columnNames.clone();
		setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		add(scrollPane);
		setBMatrix(null, null, 0);
		scrollPane.setViewportView(bMatrix);
	}

	public void setBMatrix(double[][] values, double[][] sumValues, int rowSize) {

		int sumValuesRows = 0;
		if (sumValues != null) {
			sumValuesRows = sumValues.length;
		}
		int rowCount = 26 + sumValuesRows;

		int columnCount = 27;

		if (rowSize == 0) {
			rowSize = (int) ((getWidth() * 2 - 380) / 25);
		}

		AttributiveCellTableModel ml = new AttributiveCellTableModel(rowCount, columnCount) {

			private static final long serialVersionUID = -9083101299087807962L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if (sumValuesRows > 0) {
			ColoredCell cellAtt = (ColoredCell) ml.getCellAttribute();
			CellFont cf = (CellFont) ml.getCellAttribute();

			int[] columns = new int[columnCount];
			int[] blueRows = new int[1];
			int[] blackRows = new int[sumValuesRows];

			for (int i = 0; i < columnCount; i++) {
				columns[i] = i;
			}
			for (byte i = 0; i < sumValuesRows; i++) {
				blackRows[i] = rowCount - (i + 1);
			}
			for (byte i = 0; i < sumValuesRows - 1; i++) {
				blueRows[i] = rowCount - (i + 1);
			}

			// cellAtt.setBackground(Color.green, rows, columns);
			cellAtt.setForeground(Color.BLACK, blackRows, columns);
			if (sumValuesRows > 1) {
				cellAtt.setForeground(Color.BLUE, blueRows, columns);
			}
			Font font = new Font("Calibri", Font.BOLD, 14);
			cf.setFont(font, blackRows, columns);
		}
		bMatrix = new JTable(ml);
		for (int i = 0; i < data.length; i++) {
			bMatrix.setValueAt(data[i][0], i, 0);
		}
		if (sumValuesRows > 0) {
			bMatrix.setValueAt("Cумма", rowCount - sumValuesRows, 0);
			if (sumValuesRows > 1) {
				bMatrix.setValueAt("Доля", rowCount - 1, 0);
			}
		}

		for (int i = 1; i < columnCount; i++) {
			bMatrix.getColumnModel().getColumn(i).setHeaderValue(columnNames[i - 1]);
		}
		bMatrix.getColumnModel().getColumn(0)
				.setHeaderValue("                                                                  ");
		bMatrix.setRowHeight(25);
		bMatrix.addPropertyChangeListener(new TablePropertyChange(bMatrix, false));
		bMatrix.getColumnModel().getColumn(0).setMinWidth(380);
		bMatrix.getColumnModel().getColumn(0).setMaxWidth(380);
		bMatrix.getColumnModel().getColumn(0).setPreferredWidth(400);

		for (int i = 1; i < columnCount; i++) {
			bMatrix.getColumnModel().getColumn(i).setMinWidth(40);
			bMatrix.getColumnModel().getColumn(i).setMaxWidth(200);
			bMatrix.getColumnModel().getColumn(i).setPreferredWidth(rowSize);
		}
		if (values != null) {
			double value = 0;
			for (int i = 0; i < rowCount; i++) {
				for (int j = 1; j < columnCount; j++) {
					try {
						if (i < values.length - 1) {
							value = values[i][j - 1];
						} else {
							if (i == values.length) {
								value = sumValues[0][j - 1];
							}
							if (i == values.length + 1) {
								value = sumValues[1][j - 1];
							}
						}
						bMatrix.setValueAt(Format.formatValue(value), i, j);

					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
		if (values != null) {
			productivityCondition = testProductivityCondition(values);
			if (productivityCondition) {
				LOG.info("Условия продуктивности выполняются");
			} else {
				LOG.error("Условия продуктивности не выполняются");
			}
		}
		productivityCondition = false;
		bMatrix.setFillsViewportHeight(true);
		bMatrix.getTableHeader().setReorderingAllowed(false);
		bMatrix.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
		bMatrix.getColumnModel().getColumn(0).setCellRenderer(new SampleTableCellRenderer());
		bMatrix.getTableHeader().addMouseListener(new SampleTableHeaderMouseListener(bMatrix));
		bMatrix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header3 = bMatrix.getTableHeader();
		header3.setDefaultRenderer(new HeaderRenderer());

		bMatrix.setAlignmentX(CENTER_ALIGNMENT);
		bMatrix.revalidate();
		bMatrix.repaint();
		scrollPane.setViewportView(bMatrix);
	}

	public void refreshRowSize(int rowSize) {
		for (byte i = 1; i < bMatrix.getColumnCount(); i++) {
			bMatrix.getColumnModel().getColumn(i).setMinWidth(40);
			bMatrix.getColumnModel().getColumn(i).setMaxWidth(200);
			bMatrix.getColumnModel().getColumn(i).setPreferredWidth(rowSize);
			bMatrix.revalidate();
			bMatrix.repaint();
		}
	}

	boolean testProductivityCondition(double[][] values) {
		boolean answer = true;
		double[] sum = new double[values.length];
		double Sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum[i] = 0;
			for (int j = 0; j < values[0].length; j++) {
				sum[i] += (values[i][j]);
				if (values[i][j] < 0) {
					LOG.debug("values[" + i + "][" + j + "] < 0 ==> answer=false");
					answer = false;
					break;
				}
			}
			Sum += sum[i];
		}
		for (int i = 0; i < sum.length; i++) {
			if (sum[i] / Sum > 1) {
				LOG.debug("sum[" + i + "] / Sum > 1 ==> answer=false");
				answer = false;
				break;
			}
		}
		return answer;
	}
}
