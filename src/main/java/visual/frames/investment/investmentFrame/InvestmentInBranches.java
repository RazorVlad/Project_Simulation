package visual.frames.investment.investmentFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.investment.FormatNames;
import visual.frames.main.Main;
import visual.tableBuilders.TablePropertyChange;

public class InvestmentInBranches extends JPanel {

	private static final long serialVersionUID = -873509826332013938L;
	private static Logger LOG = Logger.getLogger(InvestmentInBranches.class);
	private JTable table;
	private String font = "Calibri";
	List<JRadioButton> radio = new ArrayList<JRadioButton>();
	final JComboBox comboBox;
	double[][][] investInBranch;
	final JScrollPane scrollPane;
	int typeOfPercentageInTable = 1;
	int selectedBranch = 0;
	int selectedRadio = 0;
	double[][][] tableData = new double[13][Main.ModelLenght][10];

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public double[][][] getInvestInBranch() {
		return investInBranch;
	}

	public void setInvestInBranch(double[][][] investInBranch) {
		this.investInBranch = investInBranch.clone();
	}

	public double[][][] getTableData() {
		return tableData;
	}

	public void setTableData(double[][][] tableData) {
		this.tableData = tableData.clone();
	}

	public InvestmentInBranches() {
		setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0)),
				"\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439 \u0432\u043D\u0443\u0442\u0440\u0438 \u043E\u0442\u0440\u0430\u0441\u043B\u0438",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel radioPanel = new JPanel();
		radioPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		radioPanel.setMaximumSize(new Dimension(200, 32767));
		add(radioPanel);
		radioPanel.setLayout(new MigLayout("", "[]", "[][][][][][][][][][][]"));

		JLabel branchesLabel = new JLabel(
				"\u041E\u0442\u0440\u0430\u0441\u043B\u0438 \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		branchesLabel.setFont(new Font(font, Font.PLAIN, 13));
		radioPanel.add(branchesLabel, "cell 0 0");

		for (int i = 0; i < 10; i++) {
			radio.add(new JRadioButton(mathModel.VedNames.getOtraslName(i)));
			radio.get(i).setFont(new Font(font, Font.PLAIN, 13));
			radioPanel.add(radio.get(i), "cell 0 " + (i + 1));
			buttonGroup.add(radio.get(i));
			final int k = i;
			radio.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (testTable()) {
						setInvest(k);
						selectedRadio = k;
					} else {
						radio.get(k).setSelected(false);
						radio.get(selectedRadio).setSelected(true);
					}
				}
			});
		}
		radio.get(0).setSelected(true);

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
		tablePanel.setPreferredSize(new Dimension(550, 200));
		tablePanel.setBounds(new Rectangle(100, 100, 200, 200));
		add(tablePanel);
		tablePanel.setLayout(new MigLayout("", "[][grow]", "[][]"));

		JLabel bySphereLabel = new JLabel(
				"\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u043F\u043E \u0441\u0444\u0435\u0440\u0430\u043C:");
		bySphereLabel.setFont(new Font(font, Font.PLAIN, 13));
		tablePanel.add(bySphereLabel, "cell 0 0,alignx trailing,aligny center");

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				investInBranch = new double[13][Main.ModelLenght][10];
				setTable(selectedBranch);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u0424\u0438\u043A\u0441\u0438\u0440\u043E\u0432\u0430\u043D\u043D\u043E\u0435",
				"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u043B\u044C\u043D\u043E\u0435" }));
		comboBox.setMinimumSize(new Dimension(180, 26));
		comboBox.setMaximumSize(new Dimension(180, 26));
		comboBox.setFont(new Font(font, Font.PLAIN, 13));
		tablePanel.add(comboBox, "cell 1 0,alignx right");

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1000, 600));

		tablePanel.add(scrollPane, "cell 0 1 2 1,grow");
		setTable(0);
	}

	public int getSelectedType() {
		return selectedBranch;
	}

	public void setSelectedType(int selectedType) {
		this.selectedBranch = selectedType;
	}

	void setTable(int type) {
		int columns = 2;

		if (comboBox.getSelectedIndex() == 1) {
			columns = Main.ModelLenght + 1;
		}
		table = new JTable();
		int rowCount = mathModel.VedNames.getVeds(type).length * 2 + 3;
		table.setModel(new DefaultTableModel(new String[rowCount][columns], new String[columns]) {

			private static final long serialVersionUID = 1355893200387081105L;

			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});
		String[] names = mathModel.VedNames.getVedNames(type);
		names = FormatNames.FormatVedNames(type, names);

		int index = 0;

		for (int i = 0; i < rowCount - 3; i++) {
			if (i % 2 == 0) {
				table.setValueAt(names[index], i + 3, 0);
				index++;
			} else {
				table.setValueAt("<html>Из них на инновации </html>", i + 3, 0);
			}
		}

		table.setValueAt("Образование", 0, 0);
		table.setValueAt("НИР", 1, 0);
		table.setValueAt("ОКР", 2, 0);

		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setInvestInBranch(selectedBranch);
			}
		});
		table.setPreferredScrollableViewportSize(new Dimension(600, 600));
		table.setFillsViewportHeight(true);
		table.setFont(new Font(font, Font.PLAIN, 13));
		if (table.getColumnCount() > 2) {
			table.getColumnModel().getColumn(0).setHeaderValue("Сферы\\Года");
		} else {
			table.getColumnModel().getColumn(0).setHeaderValue("Сферы");
		}

		table.getColumnModel().getColumn(0).setMaxWidth(270);
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		table.getColumnModel().getColumn(0).setMinWidth(250);

		table.addPropertyChangeListener(new TablePropertyChange(table, true));

		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setMaxWidth(800);
			table.getColumnModel().getColumn(i).setPreferredWidth(80);
			table.getColumnModel().getColumn(i).setMinWidth(40);
			if (table.getColumnCount() > 2) {
				table.getColumnModel().getColumn(i).setHeaderValue(Main.Year + i);
			} else {
				table.getColumnModel().getColumn(i).setHeaderValue("Доля расходов, %");
			}
		}

		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 1; j < table.getColumnCount(); j++) {
				try {
					table.setValueAt(tableData[i][j - 1][type], i, j);
					if (i == 0) {
						LOG.debug("setTable() return " + tableData[i][j - 1][type]);
					}
				} catch (Exception e) {
					table.setValueAt(0, i, j);
					LOG.error(e.getMessage(), e);
				}
			}
		}
		table.setRowHeight(40);
		table.getTableHeader().resizeAndRepaint();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.revalidate();
		table.repaint();
		scrollPane.setViewportView(table);
	}

	void setInvest(int type) {
		getTable();
		countInvestInBranch(selectedBranch);
		testTable();
		setTable(type);
		selectedBranch = type;
	}

	void setInvestInBranch(int branchNumber) {
		// double investInBranch = [Сфера][Год][Отрасль промышленности];
		// double[][] data=tableData[][][branchNumber];

		double[][] data = getTable();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				try {
					double ivest = InvestTypePanel.investOnBranches[branchNumber][j];
					if (i < 3) {
						double value = ivest * (data[i][j] / typeOfPercentageInTable);
						investInBranch[i][j][branchNumber] = value;
					} else {
						if (i % 2 != 0) {
							investInBranch[i][j][branchNumber] = (typeOfPercentageInTable - data[i + 1][j])
									* ((ivest * (data[i][j] / typeOfPercentageInTable)) / typeOfPercentageInTable);
						} else {
							investInBranch[i][j][branchNumber] = (data[i][j])
									* ((ivest * (data[i - 1][j] / typeOfPercentageInTable)) / typeOfPercentageInTable);
						}
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		testTable();
	}

	void countInvestInBranch(int branchNumber) {
		// double investInBranch = [Сфера][Год][Отрасль промышленности];

		int f = (mathModel.VedNames.getVeds(branchNumber)).length;
		f = f * 2 + 3;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < Main.ModelLenght; j++) {
				try {
					double ivest = InvestTypePanel.investOnBranches[branchNumber][j];
					if (i < 3) {
						double value = ivest * (tableData[i][j][branchNumber] / typeOfPercentageInTable);
						investInBranch[i][j][branchNumber] = value;
					} else {
						if (i % 2 != 0) {
							investInBranch[i][j][branchNumber] = (typeOfPercentageInTable - tableData[i + 1][j][branchNumber])
									* ((ivest * (tableData[i][j][branchNumber] / typeOfPercentageInTable)) / typeOfPercentageInTable);
						} else {
							investInBranch[i][j][branchNumber] = (tableData[i][j][branchNumber])
									* ((ivest * (tableData[i - 1][j][branchNumber] / typeOfPercentageInTable)) / typeOfPercentageInTable);
						}
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	double[][] getTable() {
		double[][] data = new double[table.getRowCount()][Main.ModelLenght];
		for (int i = 0; i < table.getRowCount(); i++) {// сфера
			for (int j = 0; j < Main.ModelLenght; j++) {// год
				double value;
				if (comboBox.getSelectedIndex() == 0) {
					value = Format.getDouble(table.getValueAt(i, 1).toString());
				} else {
					value = Format.getDouble(table.getValueAt(i, j + 1).toString());
				}
				data[i][j] = value;
				if (value > 1) {
					typeOfPercentageInTable = 100;
				}
			}
		}
		for (int i = 0; i < table.getRowCount(); i++) {// сфера
			for (int j = 0; j < Main.ModelLenght; j++) {// год
				try {
					tableData[i][j][selectedBranch] = data[i][j];
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return data;
	}

	boolean testTable() {
		Boolean isOk = true;
		String answer;
		for (int i = 1; i < table.getColumnCount(); i++) {
			double sum = 0;
			for (int j = 0; j < table.getRowCount(); j++) {
				sum += Format.getDouble(table.getValueAt(j, i).toString());
				if (sum > typeOfPercentageInTable) {
					answer = "Суммарная доля превышает 100%";
					if (table.getColumnCount() > 2) {
						answer = answer.substring(0, 11) + "за " + table.getColumnName(i) + " "
								+ answer.substring(11, answer.length());
					}
					JOptionPane.showMessageDialog(null, answer, "Ошибка ввода данных",
							JOptionPane.ERROR_MESSAGE);
					isOk = false;
					return isOk;
				}
				if (j > 2) {
					j++;
				}
			}

		}
		return isOk;
	}

	public void setEnabled(boolean enabled) {
		table.setEnabled(enabled);
		scrollPane.setEnabled(enabled);
		comboBox.setEnabled(enabled);
		for (int i = 0; i < 10; i++) {
			radio.get(i).setEnabled(enabled);
		}
	}

}
