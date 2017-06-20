package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import mathModel.Statistics;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;
import visual.tableBuilders.TableEditor;
import visual.tableBuilders.TablePropertyChange;
import exceptions.DataRequiredEx;

public class TableAndGraf extends JPanel {

	private static final long serialVersionUID = -7921061564993050643L;
	private static Logger LOG = Logger.getLogger(TableAndGraf.class);
	private JTable table;
	JLabel label;
	JScrollPane scrollPane;
	JSpinner spinner;
	JPanel panelGraph;
	int columnCount = 4;
	private FreeChartGraf graf;
	boolean isOKRorORD;
	boolean isVED;
	JTabbedPane tabbedPane;
	int width = 0;
	int height = 0;
	private JLabel lblNewLabel;
	private JPanel panel1;
	private JLabel lblNewLabel1;
	Statistics graf2;
	boolean[] columnEditable;
	private List<String> errList;

	public void setSpinnerValue(int n) {
		spinner.setValue(n);
		setSpinner();
	}

	public int getSpinnerValue() {
		return Integer.parseInt(spinner.getValue().toString());
	}

	public TableAndGraf(int height, int width, boolean isVED, boolean isOKRorORD) {
		this.height = height;
		this.width = width;
		setBorder(null);
		this.isOKRorORD = isOKRorORD;
		this.isVED = isVED;
		if (!isOKRorORD && isVED) {
			columnCount = 5;
		}
		if (isOKRorORD && !isVED) {
			columnCount = 5;
		}

		setFocusable(false);
		setLayout(null);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setSpinner();
			}
		});
		spinner.setModel(new SpinnerNumberModel(5, 5, null, 1));
		spinner.setFont(new Font("Calibri", Font.PLAIN, 13));
		spinner.setBounds(320, -1, 60, 25);
		add(spinner);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (tabbedPane.getSelectedIndex() == 1) {
					try {
						graf();
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		});

		tabbedPane.setFocusable(false);
		tabbedPane.setFont(new Font("Calibri", Font.PLAIN, 13));

		tabbedPane.setBounds(0, -2, this.width, this.height);
		tabbedPane.setMaximumSize(new Dimension(this.width, this.height));
		tabbedPane.setMinimumSize(new Dimension(this.width, this.height));
		add(tabbedPane);

		panel1 = new JPanel();
		tabbedPane.addTab("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430", null, panel1, null);
		panel1.setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel1.add(scrollPane);

		lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setPreferredSize(new Dimension(132, 16));
		panel1.add(lblNewLabel1, BorderLayout.EAST);

		setTable(null);

		panelGraph = new JPanel();
		panelGraph.setBorder(null);
		tabbedPane.addTab("\u0413\u0440\u0430\u0444\u0438\u043A", null, panelGraph, null);
		panelGraph.setLayout(new BorderLayout(0, 0));

		graf = new FreeChartGraf(true, "0.00");
		graf.setBorder(null);
		panelGraph.add(graf);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setPreferredSize(new Dimension(130, 16));
		panelGraph.add(lblNewLabel, BorderLayout.EAST);

		label = new JLabel(
				"\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0442\u043E\u0447\u0435\u043A");
		label.setFont(new Font("Calibri", Font.PLAIN, 13));
		label.setBounds(200, 2, 120, 20);
		add(label);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		add(panel);

	}

	void createTable(int n) {
		String[] columnNames = new String[columnCount];
		String[][] data = new String[n][columnCount];
		columnEditable = new boolean[columnCount];
		columnEditable[0] = false;
		columnEditable[1] = true;
		columnEditable[2] = true;
		columnEditable[3] = true;
		if (isVED) {
			columnNames[0] = ("���");
			columnNames[1] = ("����� ������� ���������, ���.���.");
			columnNames[2] = ("�����, ���.");
			columnNames[3] = ("���. �����, ���.���.");
			if (!isOKRorORD) {
				columnNames[4] = ("����������. ������, ���.���.");
				columnEditable[4] = true;
			}
		} else {
			columnNames[0] = ("���");
			if (isOKRorORD) {
				columnNames[1] = ("����� ����������� ���, ���.���");
				columnNames[4] = ("����� ����������� ���, ���.���");
				columnEditable[4] = false;
			} else {
				columnNames[1] = ("����� ����������� ���, ���.���");
			}
			columnNames[2] = ("�����,���.");
			columnNames[3] = ("�������� �����,���.���");
		}

		for (int i = 0; i < n; i++) {
			data[i][0] = String.valueOf(Main.Year - i);
		}
		table = new JTable(n, columnCount);
		table.addPropertyChangeListener(new TablePropertyChange(table, false));
		table.setModel(new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 267185386633734970L;

			public boolean isCellEditable(int row, int column) {
				return columnEditable[column];
			}
		});
		setTableParams();
	}

	void setTableParams() {
		table.setMaximumSize(new Dimension(width, height));
		table.setPreferredSize(new Dimension(width, height));
		table.setPreferredScrollableViewportSize(new Dimension(width, height));
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setRowHeight(22);
		TableEditor te = new TableEditor(table);
		te.setColumnWidth(0, 40, 40, 40);
		te.setColumnWidth(1, 220, 220, 220);
		te.setColumnWidth(2, 60, 50, 400);
		te.setColumnWidth(3, 100, 100, 400);
		if ((isVED && !isOKRorORD) || (!isVED && isOKRorORD)) {
			te.setColumnWidth(4, 100, 100, 400);
		}
	}

	void setTable(double[][] tableData) {
		int n = 4;
		try {
			n = Integer.parseInt(spinner.getValue().toString());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		createTable(n);

		if (tableData != null) {
			try {
				int m;
				if (tableData.length < table.getRowCount()) {
					m = tableData.length;
				} else {
					m = table.getRowCount();
				}
				for (int i = 0; i < m; i++) {
					for (int j = 1; j < columnCount; j++) {
						try {
							table.setValueAt(tableData[i][j], i, j);
						} catch (Exception e) {
							table.setValueAt("", i, j);
							LOG.error(e.getMessage(), e);
						}
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		table.getTableHeader().resizeAndRepaint();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.revalidate();
		table.setFillsViewportHeight(true);
		table.repaint();
		scrollPane.setViewportView(table);
	}

	public void setFourthColumn(double[] ds) {
		if (ds != null) {
			for (int i = 0; i < table.getRowCount(); i++) {
				try {
					table.getModel().setValueAt(ds[i], i, 4);
				} catch (Exception e) {
					table.getModel().setValueAt(0, i, 4);
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	public double[] getFirstColumn() {
		double[] data = new double[table.getRowCount()];
		for (int i = 0; i < table.getRowCount(); i++) {
			try {
				data[i] = Format.getDouble(table.getValueAt(i, 1).toString());
			} catch (Exception e) {
				data[i] = 0;
				LOG.error(e.getMessage(), e);
			}
		}
		return data;
	}

	public double[][] getTableData() {
		double[][] data = new double[table.getRowCount()][table.getColumnCount()];
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				try {
					data[i][j] = Format.getDouble(table.getValueAt(i, j).toString());
				} catch (Exception e) {
					data[i][j] = 0;
					LOG.error(e.getMessage(), e);
				}
			}

		}
		return data;
	}

	public Statistics getFirstStatistic() {
		Statistics data = new Statistics();
		for (int i = 0; i < table.getRowCount(); i++) {
			Object year = table.getValueAt(i, 0);
			Object value = table.getValueAt(i, 1);
			if (year != null && value != null) {
				data.add(Integer.parseInt(year.toString()), Format.getDouble(value.toString()));
			} else {
				if (year == null) {
					data.add(0, 0);
				} else {
					data.add(Integer.parseInt(year.toString()), 0);
				}
			}
		}
		return data;
	}

	public Statistics getSecondStatistic() {
		Statistics data = new Statistics();
		for (int i = 0; i < table.getRowCount(); i++) {
			data.add(Integer.parseInt(table.getValueAt(i, 0).toString()),
					Format.getDouble(table.getValueAt(i, 2).toString()));
		}
		return data;
	}

	public Statistics getThirdStatistic() {
		Statistics data = new Statistics();
		for (int i = 0; i < table.getRowCount(); i++) {
			data.add(Integer.parseInt(table.getValueAt(i, 0).toString()),
					Format.getDouble(table.getValueAt(i, 3).toString()));
		}
		return data;
	}

	public Statistics getFourthStatistic() {
		Statistics data = new Statistics();
		for (int i = 0; i < table.getRowCount(); i++) {
			data.add(Integer.parseInt(table.getValueAt(i, 0).toString()),
					Format.getDouble(table.getValueAt(i, 4).toString()));
		}
		return data;
	}

	public void setTableData(double[][] data) {
		spinner.setValue(data.length);
		setTable(data);
	}

	public double[][] getTable() {
		return getTableData();
	}

	public boolean isDataSet() throws DataRequiredEx {
		boolean answer = true;
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				if (table.getValueAt(i, j) == null || table.getValueAt(i, j).toString().equals("")) {
					errList = new ArrayList<String>();
					errList.add(table.getColumnModel().getColumn(j).getHeaderValue().toString() + " �� "
							+ table.getValueAt(i, 0).toString() + " ���");
					throw new DataRequiredEx(errList);
				}
			}
		}
		return answer;
	}

	public void setStatistic(Statistics statistic) {
		graf2 = statistic;
	}

	void setSpinner() {
		final int n = Integer.parseInt(spinner.getValue().toString());
		if (n >= columnCount) {
			double[][] m = getTableData();
			setTable(m);

		}
	}

	void graf() {
		double[][] xy = new double[3][table.getRowCount()];
		String[] seriesNames = new String[] { "��������� ������", "��������� ������" };
		for (int i = 0; i < table.getRowCount(); i++) {
			int year = Main.Year - i;
			xy[0][i] = year;// ���������� �����
			if (table.getValueAt(i, 1) != null) {
				xy[1][i] = (Format.getDouble(table.getValueAt(i, 1).toString()));
			} else {
				seriesNames[0] = "";
			}
			if (graf2 != null) {
				xy[2][i] = graf2.getValueAt(Main.Year - i);
			}
		}
		graf.refresh(xy, seriesNames);
	}

	public void setBounds(int width) {
		tabbedPane.setBounds(0, -2, width, tabbedPane.getHeight());
	}
}
