package visual.frames.technology.AxAi;

import java.awt.BorderLayout;
import java.awt.Font;
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

public class AxAi extends JPanel {

	private static final long serialVersionUID = -3625097436654544697L;
	private JTable ax;
	JScrollPane scrollPane;
	java.awt.Color color = new java.awt.Color(214, 217, 223);
	private static Logger LOG = Logger.getLogger(AxAi.class);

	public AxAi(int type) {
		setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		add(scrollPane);

		AttributiveCellTableModel ml;
		String[] columnNames = AxAiHeaders.getColumnNames(type).clone();
		String[][] data = AxAiHeaders.data.clone();
		ml = new AttributiveCellTableModel(data, columnNames) {

			private static final long serialVersionUID = -3508936232754004492L;

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};
		ColoredCell cellAtt = (ColoredCell) ml.getCellAttribute();
		int[] columns = new int[39];
		int[] rows = new int[38];
		for (int i = 0; i < 38; i++) {
			columns[i] = i;
			rows[i] = i;
		}
		columns[38] = 38;
		cellAtt.setBackground(new java.awt.Color(240, 240, 240), rows, columns);
		columns = new int[39];
		rows = new int[38];
		for (int i = 0; i < 39; i++) {
			columns[i] = i + 1;
		}
		for (int i = 0; i < 37; i = i + 2) {
			rows[i] = i + 1;
		}
		cellAtt.setBackground(color, rows, columns);
		cellAtt.setBackground(new java.awt.Color(240, 240, 240), new int[] { 0 }, columns);

		ax = new JTable(ml);
		ax.setCellSelectionEnabled(true);
		ax.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
		ax.getColumnModel().getColumn(0).setCellRenderer(new SampleTableCellRenderer());
		ax.getTableHeader().setReorderingAllowed(false);
		ax.setColumnSelectionAllowed(true);
		ax.getTableHeader().addMouseListener(new SampleTableHeaderMouseListener(ax));
		ax.setFont(new Font("Calibri", Font.PLAIN, 12));
		ax.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ax.addPropertyChangeListener(new TablePropertyChange(ax, false));

		TableEditor te = new TableEditor(ax);
		te.setColumnWidth(0, 340, 340, 340);

		ax.setRowHeight(32);
		int prefferedWidth = (this.getWidth() - 340) / 39;
		for (int i = 1; i < ax.getColumnCount(); i++) {
			te.setColumnWidth(i, 47, prefferedWidth, 200);
		}

		scrollPane.setViewportView(ax);
		JTableHeader header = ax.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer());
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int prefferedWidth = (getWidth() - 345) / 39;
				TableEditor te = new TableEditor(ax);
				for (int i = 1; i < ax.getColumnCount(); i++) {
					te.setColumnWidth(i, 47, prefferedWidth, 200);
				}
			}
		});
	}

	public boolean isDataSet() {
		for (int i = 0; i < ax.getRowCount(); i++) {
			for (int j = 1; j < ax.getColumnCount(); j++) {
				try {
					if (ax.getValueAt(i, j).toString().length() < 1) {
						return false;
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					return false;
				}
			}
		}
		return true;
	}
	
	public void refreshRowSize(int rowSize) {
		for (byte i = 1; i < ax.getColumnCount(); i++) {
			ax.getColumnModel().getColumn(i).setMinWidth(40);
			ax.getColumnModel().getColumn(i).setMaxWidth(200);
			ax.getColumnModel().getColumn(i).setPreferredWidth(rowSize);
			ax.revalidate();
			ax.repaint();
		}
	}

	public double[][] getTable(int index) {
		double[][] table = new double[ax.getRowCount()][ax.getColumnCount() - 1 - index];
		for (int i = 0; i < ax.getRowCount(); i++) {
			for (int j = 1; j < ax.getColumnCount() - index; j++) {
				try {
					table[i][j - 1] = Format.getDouble(ax.getValueAt(i, j).toString());
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					table[i][j - 1] = 0;
				}

			}
		}
		return table;
	}

	public double[] getLastColumn() {
		double[] data = new double[ax.getRowCount()];
		for (int i = 0; i < ax.getRowCount(); i++) {
			data[i] = Format.getDouble(ax.getValueAt(i, 39).toString());
		}
		return data;
	}

	public void setTable(double[][] data) {
		if (data != null) {
			for (int i = 0; i < ax.getRowCount(); i++) {
				for (int j = 1; j < ax.getColumnCount(); j++) {
					ax.getModel().setValueAt(data[i][j - 1], i, j);
				}
			}
		}
	}
}