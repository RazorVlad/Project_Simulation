package visual.prom_modeling.ved;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.util.Log;

import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;
import visual.tableBuilders.TablePropertyChange;

public class Ved_panel_coefs extends JPanel {

	private static final long serialVersionUID = -1665811527043328084L;
	public Statistics aNew;

	public Statistics getANew() {
		return aNew;
	}

	public void setANew(Statistics aNew) {
		this.aNew = aNew;
	}

	public Statistics getAlfaNew() {
		return alfaNew;
	}

	public void setAlfaNew(Statistics alfaNew) {
		this.alfaNew = alfaNew;
	}

	public Statistics getBetaNew() {
		return betaNew;
	}

	public void setBetaNew(Statistics betaNew) {
		this.betaNew = betaNew;
	}

	public Statistics getGammaNew() {
		return gammaNew;
	}

	public void setGammaNew(Statistics gammaNew) {
		this.gammaNew = gammaNew;
	}

	public double getA() {
		return getValue(a);
	}

	public void setA(double value) {
		a.setText(Format.formatValue(value));
	}

	public double getAlfa() {
		return getValue(alfa);
	}

	public void setAlfa(double value) {
		alfa.setText(Format.formatValue(value));
	}

	public double getBeta() {
		return getValue(beta);
	}

	public void setBeta(double value) {
		beta.setText(Format.formatValue(value));
	}

	public double getGamma() {
		return getValue(gamma);
	}

	public void setGamma(double value) {
		gamma.setText(Format.formatValue(value));
	}

	public double getR2() {
		return getValue(r2);
	}

	public void setR2(double value) {
		r2.setText(Format.formatValue(value));
	}

	public double getMAPE() {
		return getValue(mape);
	}

	public void setMAPE(double value) {
		mape.setText(Format.formatValue(value));
	}

	public Statistics alfaNew;
	public Statistics betaNew;
	public Statistics gammaNew;

	private JFormattedTextField a;
	private JFormattedTextField alfa;
	private JFormattedTextField beta;
	private JFormattedTextField gamma;
	private JFormattedTextField r2;
	private JFormattedTextField mape;

	JPanel panelInn;
	JPanel coefsInn;
	JPanel panel;

	boolean isOrd;

	public Ved_panel_coefs(boolean isOrd) {
		this.isOrd = isOrd;
		setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));

		coefsInn = new JPanel();
		coefsInn.setMaximumSize(new Dimension(32767, 200));
		coefsInn.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		add(coefsInn, BorderLayout.NORTH);
		coefsInn.setLayout(new MigLayout("", "[210:210:210][grow][]", "[35][35][35][35]"));

		JLabel label = new JLabel(
				"\u0422\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u043A\u043E\u044D\u0444\u0444\u0438\u0446\u0438\u0435\u043D\u0442 \u0410");
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		coefsInn.add(label, "cell 0 0,alignx trailing");

		a = new JFormattedTextField(Format.getNumberFormat());
		a.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				aNew = setStatistics(Format.getDouble(a.getText()));
			}
		});
		a.setPreferredSize(new Dimension(40, 28));
		a.setMinimumSize(new Dimension(12, 23));
		a.setMaximumSize(new Dimension(1000, 26));
		a.setFont(new Font("Calibri", Font.PLAIN, 14));
		a.setColumns(10);
		coefsInn.add(a, "cell 1 0,growx");

		JButton aInnBtn = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		aInnBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
		setButton(aInnBtn, "���������� A", false, 0);
		coefsInn.add(aInnBtn, "cell 2 0");

		JLabel label1 = new JLabel(
				"\u042D\u043B\u0430\u0441\u0442\u0438\u0447\u043D\u043E\u0441\u0442\u044C \u0432\u044B\u043F\u0443\u0441\u043A\u0430 \u043F\u043E \u0442\u0440\u0443\u0434\u0443 \u03B1");
		label1.setFont(new Font("Calibri", Font.PLAIN, 14));
		coefsInn.add(label1, "cell 0 1,alignx trailing");

		alfa = new JFormattedTextField(Format.getNumberFormat());
		alfa.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				alfaNew = setStatistics(Format.getDouble(alfa.getText()));
			}
		});
		alfa.setPreferredSize(new Dimension(40, 28));
		alfa.setMinimumSize(new Dimension(12, 23));
		alfa.setMaximumSize(new Dimension(1000, 26));
		alfa.setFont(new Font("Calibri", Font.PLAIN, 14));
		alfa.setColumns(10);
		coefsInn.add(alfa, "cell 1 1,growx");

		JButton alfaInnBtn = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		alfaInnBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
		setButton(alfaInnBtn, "���������� \u03B1", false, 1);
		coefsInn.add(alfaInnBtn, "cell 2 1");

		JLabel label3 = new JLabel(
				"\u042D\u043B\u0430\u0441\u0442\u0438\u0447\u043D\u043E\u0441\u0442\u044C \u0432\u044B\u043F\u0443\u0441\u043A\u0430 \u043F\u043E \u0444\u043E\u043D\u0434\u0430\u043C \u03B2");
		label3.setFont(new Font("Calibri", Font.PLAIN, 14));
		coefsInn.add(label3, "cell 0 2,alignx trailing");

		beta = new JFormattedTextField(Format.getNumberFormat());
		beta.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				betaNew = setStatistics(Format.getDouble(beta.getText()));
			}
		});
		beta.setPreferredSize(new Dimension(40, 28));
		beta.setMinimumSize(new Dimension(12, 23));
		beta.setMaximumSize(new Dimension(1000, 26));
		beta.setFont(new Font("Calibri", Font.PLAIN, 14));
		beta.setColumns(10);
		coefsInn.add(beta, "cell 1 2,growx");

		JButton betaInnBtn = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		betaInnBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
		setButton(betaInnBtn, "���������� \u03B2", false, 2);
		coefsInn.add(betaInnBtn, "cell 2 2");

		if (!this.isOrd) {
			JLabel label4 = new JLabel(
					"\u043F\u043E \u043D\u0435\u043C\u0430\u0442\u0435\u0440\u0438\u0430\u043B\u044C\u043D\u044B\u043C \u0430\u043A\u0442\u0438\u0432\u0430\u043C \u03B3");
			label4.setFont(new Font("Calibri", Font.PLAIN, 14));
			coefsInn.add(label4, "cell 0 3,alignx trailing");

			gamma = new JFormattedTextField(Format.getNumberFormat());
			gamma.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent arg0) {
					gammaNew = setStatistics(Format.getDouble(gamma.getText()));
				}
			});
			gamma.setPreferredSize(new Dimension(40, 28));
			gamma.setMinimumSize(new Dimension(12, 23));
			gamma.setMaximumSize(new Dimension(160, 26));
			gamma.setFont(new Font("Calibri", Font.PLAIN, 14));
			gamma.setColumns(10);
			coefsInn.add(gamma, "cell 1 3,growx");

			JButton gammaInnBtn = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
			gammaInnBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
			setButton(gammaInnBtn, "���������� \u03B3", false, 3);
			coefsInn.add(gammaInnBtn, "cell 2 3");
		}

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[210:210:210][grow]", "[][]"));

		JLabel r1Label = new JLabel(
				"\u041A\u043E\u044D\u0444\u0444\u0438\u0446\u0438\u0435\u043D\u0442 \u0434\u0435\u0442\u0435\u0440\u043C\u0438\u043D\u0430\u0446\u0438\u0438 R^2");
		panel.add(r1Label, "cell 0 0,alignx trailing");

		r2 = new JFormattedTextField(Format.getNumberFormat());
		r2.setPreferredSize(new Dimension(130, 26));
		r2.setMinimumSize(new Dimension(130, 26));
		r2.setMaximumSize(new Dimension(1000, 26));
		panel.add(r2, "cell 1 0,growx");
		r2.setColumns(10);

		JLabel mape1Label = new JLabel("\u041F\u043E\u043A\u0430\u0437\u0430\u0442\u0435\u043B\u044C MAPE");
		panel.add(mape1Label, "cell 0 1,alignx trailing");

		mape = new JFormattedTextField(Format.getNumberFormat());
		mape.setPreferredSize(new Dimension(130, 26));
		mape.setMinimumSize(new Dimension(130, 26));
		mape.setMaximumSize(new Dimension(1000, 26));
		panel.add(mape, "cell 1 1,growx");
		mape.setColumns(10);
	}

	Statistics setStatistics(double xy) {
		Statistics value = new Statistics();
		for (int i = 0; i < Main.ModelLenght; i++) {
			try {
				value.add((int) Main.Year + i, xy);
			} catch (Exception e) {
				Log.error(e.getMessage(), e);
			}
		}
		return value;
	}

	public class StatParam extends JFrame {

		private static final long serialVersionUID = 4180593226450311353L;
		private JPanel contentPane;
		private JTable table;
		private JButton btnOk;
		double[] statisticParamArray;
		private JPanel panel;
		private JButton btnCancel;
		private JLabel label;
		private FreeChartGraf graf;

		public StatParam(String tableName, final int index) throws ClassNotFoundException,
				InstantiationException, IllegalAccessException {
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

			table = new JTable(1, Main.ModelLenght);
			table.setModel(new DefaultTableModel(new String[1][Main.ModelLenght],
					new String[Main.ModelLenght]));
			table.addPropertyChangeListener(new TablePropertyChange(table, false));

			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setMaxWidth(300);
				table.getColumnModel().getColumn(i).setPreferredWidth(100);
				table.getColumnModel().getColumn(i).setMinWidth(50);
				table.getColumnModel().getColumn(i).setHeaderValue(Main.Year - i);
			}

			table.setPreferredScrollableViewportSize(new Dimension(450, 17));
			table.setFont(new Font("Calibri", Font.PLAIN, 13));

			table.getTableHeader().resizeAndRepaint();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.revalidate();
			table.repaint();

			scrollPane.setViewportView(table);

			btnOk = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
			btnOk.setMinimumSize(new Dimension(100, 26));
			btnOk.setMaximumSize(new Dimension(100, 26));
			btnOk.setPreferredSize(new Dimension(80, 26));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					double[][] xy;
					xy = new double[2][table.getColumnCount()];

					switch (index) {
					case 0:
						aNew = new Statistics();
						setData(xy, aNew);
						break;
					case 1:
						alfaNew = new Statistics();
						setData(xy, alfaNew);
						break;
					case 2:
						betaNew = new Statistics();
						setData(xy, betaNew);
						break;
					case 3:
						gammaNew = new Statistics();
						setData(xy, gammaNew);
						break;
					}

					String[] seriesNames = new String[] { "" };
					graf.refresh(xy, seriesNames);
				}
			});
			panel.add(btnOk, "cell 0 1,alignx right,aligny center");

			btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setMinimumSize(new Dimension(100, 26));
			btnCancel.setMaximumSize(new Dimension(100, 26));
			btnCancel.setPreferredSize(new Dimension(80, 26));
			panel.add(btnCancel, "cell 1 1");

			label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Calibri", Font.PLAIN, 13));
			label.setText(tableName);
			contentPane.add(label, BorderLayout.NORTH);
		}

		public double[] getStatisticParamArray() {
			return statisticParamArray;
		}

		public void setStatisticParamArray(double[] statisticParamArray) {
			this.statisticParamArray = statisticParamArray.clone();
		}

		void setData(double[][] xy, Statistics value) {
			for (int i = 0; i < table.getColumnCount(); i++) {
				try {
					xy[0][i] = Main.Year + i;// ���������� �����
					xy[1][i] = Format.getDouble(table.getValueAt(0, i).toString());
					value.add((int) xy[0][i], xy[1][i]);
				} catch (Exception e) {
					Log.error(e.getMessage(), e);
				}
			}
		}
	}

	void setButton(JButton button, final String title, final boolean isOrd, final int index) {
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					StatParam frame = new StatParam(title, index);
					frame.show();
				} catch (Exception e) {
					Log.error(e.getMessage(), e);
				}
			}
		});
	}

	double getValue(JFormattedTextField textField) {
		return Format.getDouble(textField.getText());
	}

}
