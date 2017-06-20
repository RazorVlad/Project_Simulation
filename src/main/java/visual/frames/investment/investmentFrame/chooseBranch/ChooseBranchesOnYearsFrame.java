package visual.frames.investment.investmentFrame.chooseBranch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.investment.investmentFrame.InvestTypePanel;
import visual.frames.main.Main;
import visual.tableBuilders.TablePropertyChange;

public class ChooseBranchesOnYearsFrame extends JFrame {

	private static final long serialVersionUID = -1916740245020884782L;
	private JPanel contentPane;
	private JTable table;
	private String font = "Calibri";
	double[][] investOnBranches;
	double[] ivestOnYears;
	int percentFormat = 1;
	JScrollPane scrollPane;
	String[] columnNames;
	String[] rowNames;
	boolean[] invOnBranches;
	double[][] invStructValues;
	private static Logger LOG = Logger.getLogger(ChooseBranchesOnYearsFrame.class);

	public ChooseBranchesOnYearsFrame(double[] ivestYears, boolean[] invBranches, double[][] invstructValues)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		this.ivestOnYears = ivestYears.clone();
		this.invOnBranches = invBranches.clone();
		this.invStructValues = invstructValues.clone();
		setBounds(new Rectangle(50, 50, 700, 320));
		setTitle("\u0421\u0442\u0440\u0443\u043A\u0442\u0443\u0440\u0430 \u0440\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u044F \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		columnNames = new String[ivestOnYears.length + 2];
		columnNames[0] = "<html>Участие в<br>распределении";
		columnNames[1] = "Отрасли\\Года";
		for (int i = 2; i < ivestOnYears.length + 2; i++) {
			columnNames[i] = "" + (Main.Year + i - 1);
		}

		rowNames = new String[10];
		for (int i = 0; i < 10; i++) {
			rowNames[i] = mathModel.VedNames.getOtraslName(i);
		}

		JLabel titleLabel = new JLabel(
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0434\u043E\u043B\u044E \u0441\u0440\u0435\u0434\u0441\u0442\u0432 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0440\u0443\u0435\u043C\u044B\u0445 \u0432 \u043A\u0430\u0436\u0434\u0443\u044E \u043E\u0442\u0440\u0430\u0441\u043B\u044C");
		titleLabel.setFont(new Font(font, Font.PLAIN, 14));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		setTable(invStructValues, invOnBranches);

		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new MigLayout("", "[grow][50px][75px]", "[25px]"));

		JButton btnOk = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setPercentFormat();
				String answer = testTable();
				if (answer.isEmpty()) {
					investOnBranches = new double[table.getRowCount()][table.getColumnCount() - 2];
					for (int i = 0; i < table.getRowCount(); i++) {
						boolean isCheck = Boolean.parseBoolean(table.getValueAt(i, 0).toString());
						for (int j = 2; j < table.getColumnCount(); j++) {
							double value = Format.getDouble(table.getValueAt(i, j));
							if (isCheck) {
								investOnBranches[i][j - 2] = (ivestOnYears[j - 2] / percentFormat * (value));
							} else {
								investOnBranches[i][j - 2] = 0;
							}
							invStructValues[i][j - 2] = value;
						}
						invOnBranches[i] = isCheck;
					}
					InvestTypePanel.setInvStructValues(invStructValues);
					InvestTypePanel.setInvOnBranches(invOnBranches);
					InvestTypePanel.setInvestOnBranches(investOnBranches);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Суммарная доля за " + answer + " год превышает 100%", "Ошибка ввода данных",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setMinimumSize(new Dimension(105, 26));
		btnOk.setPreferredSize(new Dimension(105, 26));
		btnOk.setFont(new Font(font, Font.PLAIN, 14));
		buttonsPanel.add(btnOk, "cell 1 0,alignx left,growy");

		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setMinimumSize(new Dimension(105, 26));
		btnCancel.setPreferredSize(new Dimension(105, 26));
		btnCancel.setFont(new Font(font, Font.PLAIN, 14));
		buttonsPanel.add(btnCancel, "cell 2 0,alignx right,growy");
	}

	void setTable(double[][] data, boolean[] invOnBranches) {
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[10][ivestOnYears.length + 2], columnNames) {

			private static final long serialVersionUID = 8061481766355426052L;

			public Class getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class;
				} else {
					return Object.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column != 1;
			}
		});
		table.addPropertyChangeListener(new TablePropertyChange(table, true));

		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt(rowNames[i], i, 1);

			table.setValueAt(invOnBranches[i], i, 0);
			if (data != null) {
				for (int j = 2; j < table.getColumnCount(); j++) {
					try {
						table.setValueAt(data[i][j - 2], i, j);
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(1).setMinWidth(260);
		table.getColumnModel().getColumn(1).setMaxWidth(260);
		table.setPreferredScrollableViewportSize(new Dimension(450, 300));
		table.setFont(new Font(font, Font.PLAIN, 13));
		for (int i = 2; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setMaxWidth(600);
			table.getColumnModel().getColumn(i).setPreferredWidth(70);
			table.getColumnModel().getColumn(i).setMinWidth(50);
		}
		table.getTableHeader().resizeAndRepaint();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.revalidate();
		table.repaint();
		scrollPane.setViewportView(table);
	}

	void setPercentFormat() {
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 2; j < table.getColumnCount(); j++) {
				if ((Format.getDouble(table.getValueAt(i, j).toString())) > 1) {
					percentFormat = 100;
					return;
				}
			}
		}
	}

	String testTable() {
		String answer = "";
		for (int i = 2; i < table.getColumnCount(); i++) {
			double sum = 0;
			for (int j = 0; j < 10; j++) {
				sum += Format.getDouble(table.getValueAt(j, i).toString());
				if (sum > percentFormat) {
					answer = table.getColumnName(i);
					LOG.debug("sum(" + sum + ") > percentFormat(" + percentFormat + ")");
					return answer;
				}
			}
		}
		return answer;
	}
}
