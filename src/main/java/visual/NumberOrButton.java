package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;

import javax.swing.SpinnerNumberModel;

import org.apache.log4j.Logger;

public class NumberOrButton extends JPanel {

	private static final long serialVersionUID = 1669803068735561490L;

	private static Logger LOG = Logger.getLogger(NumberOrButton.class);
	private JFormattedTextField textField;

	JComboBox comboBox;
	String indexName = "";
	JButton button;
	boolean useSpinner = false;
	private JSpinner spinner;
	double maxValue = Double.MAX_VALUE;
	double minValue = 0;
	int startYear = Main.Year + 1;
	int length = Main.ModelLenght;
	double[][] values = new double[3][length];
	double coefX = 1;

	public void setCoefX(double coefX) {
		this.coefX = coefX;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
		if (useSpinner) {
			spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(minValue),
					new Double(maxValue), new Double(0.1)));
		}
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
		if (useSpinner) {
			spinner.setModel(new SpinnerNumberModel(new Double(0), new Double(minValue),
					new Double(maxValue), new Double(1)));
		}
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public void setLength(int length) {
		this.length = length;
		values = new double[2][length];
	}

	public NumberOrButton(String IndexName, boolean UseSpinner) {
		setPreferredSize(new Dimension(230, 28));
		setMaximumSize(new Dimension(230, 28));
		this.indexName = IndexName;
		this.useSpinner = UseSpinner;
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(120, 28));
		panel.setMaximumSize(new Dimension(120, 28));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		textField = new JFormattedTextField(Format.getNumberFormat());
		textField.setPreferredSize(new Dimension(120, 28));
		textField.setMaximumSize(new Dimension(120, 28));
		textField.setText("");
		textField.setColumns(10);
		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				double val = 0;
				try {
					val = Format.getDouble(textField.getText());
					if (val > maxValue && val < minValue) {
						val = 0;
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								textField.setValue(0);
							}
						});
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				for (int i = 0; i < values[0].length; i++) {
					values[0][i] = startYear + i;
					values[1][i] = val * coefX;
				}
			}
		});
		panel.add(textField, "name_358509420360131");

		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(120, 28));
		spinner.setMaximumSize(new Dimension(120, 28));
		spinner.setModel(new SpinnerNumberModel(0d, minValue, null, 1d));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double val = -1;
				try {
					val = Format.getDouble(spinner.getValue().toString());
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				for (int i = 0; i < values[0].length; i++) {
					values[0][i] = startYear + i;
					values[1][i] = val * coefX;
				}
			}
		});
		panel.add(spinner, "name_12840913744372");

		if (useSpinner) {
			textField.setVisible(false);
			spinner.setVisible(true);
		} else {
			textField.setVisible(true);
			spinner.setVisible(false);
		}

		button = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					StatisticParam frame = new StatisticParam(indexName);
					frame.show();
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}

			}
		});

		panel.add(button, "name_358343362014033");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u041F\u043E\u0441\u0442\u043E\u044F\u043D\u043D\u044B\u0439",
				"\u041F\u0435\u0440\u0435\u043C\u0435\u043D\u043D\u044B\u0439" }));
		add(comboBox, BorderLayout.WEST);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedIndex() == 0) {
					button.setVisible(false);
					spinner.setVisible(useSpinner);
					textField.setVisible(!useSpinner);
				}
				if (comboBox.getSelectedIndex() == 1) {
					button.setVisible(true);
					textField.setVisible(false);
					spinner.setVisible(false);
				}
			}
		});
	}

	public double[][] getValues() {
		double[][] finalValues = new double[2][];
		finalValues[0] = values[0];
		if (comboBox.getSelectedIndex() == 0) {
			finalValues[1] = values[1];
		} else {
			finalValues[1] = values[2];
		}
		return finalValues;
	}

	public Statistics getStatistics() {
		Statistics data = new Statistics();
		int selectedRow = 2;
		if (comboBox.getSelectedIndex() == 0) {
			selectedRow = 1;
		}
		for (int i = 0; i < values[0].length; i++) {
			data.add((int) values[0][i], values[selectedRow][i]);
		}
		return data;
	}

	public class StatisticParam extends JFrame {

		private static final long serialVersionUID = 6898391740068299577L;
		private JPanel contentPane;
		private JTable table;
		private JButton btnOk;
		double[] statisticParamArray;
		private JPanel panel;
		private JButton btnCancel;
		private JLabel label;
		private FreeChartGraf graf;

		/**
		 * @throws IllegalAccessException
		 * @throws InstantiationException
		 * @throws ClassNotFoundException
		 */

		public StatisticParam(String tableName) throws ClassNotFoundException, InstantiationException,
				IllegalAccessException, UnsupportedLookAndFeelException {
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			setBounds(new Rectangle(400, 100, 720, 480));
			setTitle("������� �������� ���������");

			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));

			graf = new FreeChartGraf(false, "0.00");
			graf.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPane.add(graf, BorderLayout.CENTER);

			panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new MigLayout("", "[621px][]", "[44px][23px]"));

			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, "cell 0 0 2 1,grow");

			table = new JTable(1, length);

			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setMaxWidth(300);
				table.getColumnModel().getColumn(i).setPreferredWidth(100);
				table.getColumnModel().getColumn(i).setMinWidth(50);
			}

			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setHeaderValue(startYear + i);
				try {
					table.setValueAt(values[2][i], 0, i);
				} catch (Exception e) {
					table.setValueAt(0, 0, i);
					LOG.error(e.getMessage(), e);
				}
			}

			table.setPreferredScrollableViewportSize(new Dimension(450, 17));
			table.setFont(new Font("Calibri", Font.PLAIN, 13));
			table.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("tableCellEditor")) {
						try {
							int row = table.getSelectedRow();
							int column = table.getSelectedColumn();
							Object object = table.getValueAt(row, column);
							if (object != null) {
								String s = object.toString();
								double value;
								if (!s.isEmpty()) {
									value = Format.getDouble(s);
									if (value > maxValue || value < minValue) {
										value = 0.0;
									}
									table.setValueAt(Format.formatValue(value), table.getSelectedRow(),
											table.getSelectedColumn());
								}
							}
							refreshGraf();
						} catch (Exception e) {
							LOG.error(e.getMessage(), e);
						}
					}
				}
			});

			table.getTableHeader().resizeAndRepaint();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.revalidate();
			table.repaint();

			scrollPane.setViewportView(table);

			btnOk = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
			btnOk.setMinimumSize(new Dimension(120, 28));
			btnOk.setMaximumSize(new Dimension(120, 28));
			btnOk.setPreferredSize(new Dimension(120, 28));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					double[][] tableValues = new double[2][table.getColumnCount()];
					for (int i = 0; i < table.getColumnCount(); i++) {
						tableValues[0][i] = Format.getDouble(table.getColumnModel().getColumn(i).getHeaderValue()
								.toString());
						tableValues[1][i] = Format.getDouble(table.getValueAt(0, i)) * coefX;
					}
					values[0] = tableValues[0];
					values[2] = tableValues[1];
					refreshGraf();
					// dispose();
				}
			});

			panel.add(btnOk, "cell 0 1,alignx right,aligny center");

			btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setMinimumSize(new Dimension(120, 28));
			btnCancel.setMaximumSize(new Dimension(120, 28));
			btnCancel.setPreferredSize(new Dimension(120, 28));
			panel.add(btnCancel, "cell 1 1");

			label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Calibri", Font.PLAIN, 13));
			label.setText(tableName);
			contentPane.add(label, BorderLayout.NORTH);

			refreshGraf();
		}

		public void refreshGraf() {
			try {
				double[][] xy = new double[2][table.getColumnCount()];
				for (int i = 0; i < table.getColumnCount(); i++) {
					xy[0][i] = Double.parseDouble(table.getColumnModel().getColumn(i).getHeaderValue()
							.toString());
					if (table.getValueAt(0, i) == null) {
						LOG.debug("null value at numberOrButton(" + indexName + ")");
						xy[1][i] = 0;
					} else {
						xy[1][i] = Format.getDouble(table.getValueAt(0, i).toString());
					}
				}
				String[] seriesNames = new String[] { indexName };
				graf.refresh(xy, seriesNames);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		public double[] getStatisticParamArray() {
			return statisticParamArray;
		}

		public void setStatisticParamArray(double[] statisticParamArray) {
			this.statisticParamArray = statisticParamArray.clone();
		}
	}

	public void setValue(double[][] Values) {
		values = new double[3][Values[0].length];
		System.arraycopy(Values, 0, values, 0, Values.length);

		if (useSpinner) {
			spinner.setValue((int) values[1][0]);
		} else {
			textField.setText(Format.formatValue(values[1][0]));
		}
	}

	public void setValue(Statistics Values) {

		values = new double[3][Values.size()];
		for (int i = 0; i < Values.size(); i++) {
			values[0][i] = Values.getYear(i);
			values[1][i] = Values.getValue(i);
			values[2][i] = Values.getValue(i);
		}
		if (useSpinner) {
			spinner.setValue((double) values[1][0]);
		} else {
			textField.setText(Format.formatValue(values[1][0]));
		}
	}

}
