package visual.prom_modeling.niokr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import mathModel.RnD;
import mathModel.Statistics;
import mathModel.manFun.ManFun;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.TableAndGraf;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;
import exceptions.DataRequiredEx;

public class NIOKR_panel_param extends JPanel {

	private static final long serialVersionUID = 2989028121403292500L;
	private static Logger LOG = Logger.getLogger(NIOKR_panel_param.class);
	String workType = "НИР";
	private JFormattedTextField alfa;
	private JFormattedTextField beta;
	private JFormattedTextField gamma;
	private JFormattedTextField a;
	private JFormattedTextField r2;
	private JFormattedTextField mape;
	private String font = "Calibri";
	double[][] tableData;
	TableAndGraf tableAndGraf;
	NIOKR_panel_param_top topPanel;

	Statistics aNew;
	Statistics alfaNew;
	Statistics betaNew;
	Statistics gammaNew;
	boolean isokr;

	public NIOKR_panel_param_top getTopPanel() {
		return topPanel;
	}

	public double[][] getTableData() {
		tableData = tableAndGraf.getTableData();
		return tableData;
	}

	public TableAndGraf getTable() {
		return tableAndGraf;
	}

	public void setTableData(double[][] tableData) {
		tableAndGraf.setTableData(tableData);

	}

	public void setTable(double[][] table) {
		tableAndGraf.setTableData(table);
	}

	public Statistics getAlfa() {
		return alfaNew;
	}

	public void setAlfa(double value) {
		alfa.setText(Format.formatValue(value));
	}

	public void setAlfa(Statistics value) {
		alfaNew = value;
	}

	public Statistics getBeta() {
		return betaNew;
	}

	public void setBeta(double value) {
		beta.setText(Format.formatValue(value));
	}

	public void setBeta(Statistics value) {
		betaNew = value;
	}

	public Statistics getGamma() {
		return gammaNew;
	}

	public void setGamma(double value) {
		gamma.setText(Format.formatValue(value));
	}

	public void setGamma(Statistics value) {
		gammaNew = value;
	}

	public Statistics getA() {
		return aNew;
	}

	public void setA(double value) {
		a.setText(Format.formatValue(value));
	}

	public void setA(Statistics value) {
		aNew = value;
	}

	public Double getR2() {
		return Format.getDouble(r2.getText());
	}

	public void setR2(double value) {
		r2.setText(Format.formatValue(value));
	}

	public Double getMape() {
		return Format.getDouble(mape.getText());
	}

	public void setMape(double value) {
		mape.setText(Format.formatValue(value));
	}

	public NIOKR_panel_param(boolean isOkr) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try {
					tableAndGraf.setBounds(NIOKR_panel_param.this.getWidth() - 300);
				} catch (Exception ee) {
					LOG.error(ee.getMessage(), ee);
				}
			}
		});
		this.isokr = isOkr;
		if (isokr) {
			workType = "ОКР";
		} else {
			workType = "НИР";
		}

		setLayout(new BorderLayout(0, 0));

		topPanel = new NIOKR_panel_param_top(workType);
		add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(null);
		add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel tableAndGrafPanel = new JPanel();
		centerPanel.add(tableAndGrafPanel);
		tableAndGrafPanel.setBorder(null);
		tableAndGrafPanel.setLayout(new BorderLayout(0, 0));

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = gd.getDisplayMode().getHeight() - 634;
		int width = gd.getDisplayMode().getWidth() - 611;
		tableAndGraf = new TableAndGraf(height, width, false, isokr);
		tableAndGrafPanel.add(tableAndGraf);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(450, 200));
		centerPanel.add(panel, BorderLayout.EAST);
		panel.setMaximumSize(new Dimension(370, 32767));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel paramsLabel = new JLabel(
				"\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0435\u043D\u043D\u043E\u0439 \u0444\u0443\u043D\u043A\u0446\u0438\u0438:");
		paramsLabel.setPreferredSize(new Dimension(230, 24));
		paramsLabel.setMinimumSize(new Dimension(230, 24));
		paramsLabel.setMaximumSize(new Dimension(230, 24));
		panel.add(paramsLabel);
		paramsLabel.setFont(new Font(font, Font.PLAIN, 13));

		JPanel coefsPanel = new JPanel();
		panel.add(coefsPanel);
		coefsPanel.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		coefsPanel.setMaximumSize(new Dimension(450, 120));
		coefsPanel.setLayout(new MigLayout("", "[206][grow][80:80:80]", "[][][][]"));

		JLabel aLabel = new JLabel(
				"\u0422\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u043A\u043E\u044D\u0444\u0438\u0446\u0438\u0435\u043D\u0442 \u0410");
		coefsPanel.add(aLabel, "cell 0 0,alignx right");
		aLabel.setFont(new Font(font, Font.PLAIN, 13));

		a = new JFormattedTextField(Format.getNumberFormat());
		a.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				aNew = setStatistics(Format.getDouble(a.getText()));
			}
		});
		a.setMinimumSize(new Dimension(40, 26));
		coefsPanel.add(a, "flowx,cell 1 0,growx");
		a.setFont(new Font(font, Font.PLAIN, 13));
		a.setMaximumSize(new Dimension(200, 26));
		a.setColumns(10);

		JLabel alfaLabel = new JLabel(
				"\u042D\u043B\u0430\u0441\u0442\u0438\u0447\u043D\u043E\u0441\u0442\u044C \u0432\u044B\u043F\u0443\u0441\u043A\u0430 \u043F\u043E \u0442\u0440\u0443\u0434\u0443 \u03B1");
		coefsPanel.add(alfaLabel, "cell 0 1,alignx right");
		alfaLabel.setFont(new Font(font, Font.PLAIN, 13));

		alfa = new JFormattedTextField(Format.getNumberFormat());
		alfa.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				aNew = setStatistics(Format.getDouble(alfa.getText()));
			}
		});
		alfa.setMinimumSize(new Dimension(40, 26));
		coefsPanel.add(alfa, "cell 1 1,growx");
		alfa.setFont(new Font(font, Font.PLAIN, 13));
		alfa.setMaximumSize(new Dimension(200, 26));
		alfa.setColumns(10);

		JButton btnAlfa = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		btnAlfa.addActionListener(new ButtonActionListener("показатель \u03B1", 1));
		coefsPanel.add(btnAlfa, "cell 2 1");

		JLabel betaLabel = new JLabel(
				"\u042D\u043B\u0430\u0441\u0442\u0438\u0447\u043D\u043E\u0441\u0442\u044C \u0432\u044B\u043F\u0443\u0441\u043A\u0430 \u043F\u043E \u0444\u043E\u043D\u0434\u0430\u043C \u03B2");
		coefsPanel.add(betaLabel, "cell 0 2,alignx right");
		betaLabel.setFont(new Font(font, Font.PLAIN, 13));

		beta = new JFormattedTextField(Format.getNumberFormat());
		beta.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				aNew = setStatistics(Format.getDouble(beta.getText()));
			}
		});
		beta.setMinimumSize(new Dimension(40, 26));
		coefsPanel.add(beta, "cell 1 2,growx");
		beta.setFont(new Font(font, Font.PLAIN, 13));
		beta.setMaximumSize(new Dimension(200, 26));
		beta.setColumns(10);

		JButton btnBeta = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		btnBeta.addActionListener(new ButtonActionListener("показатель \u03B2", 2));
		coefsPanel.add(btnBeta, "cell 2 2");

		JLabel label = new JLabel("\u03B3");
		coefsPanel.add(label, "cell 0 3,alignx trailing");
		label.setVisible(isokr);

		gamma = new JFormattedTextField(Format.getNumberFormat());
		gamma.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				aNew = setStatistics(Format.getDouble(gamma.getText()));
			}
		});
		gamma.setMinimumSize(new Dimension(40, 26));
		gamma.setMaximumSize(new Dimension(200, 26));
		coefsPanel.add(gamma, "cell 1 3,growx");
		gamma.setColumns(10);

		JButton btnA = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		btnA.addActionListener(new ButtonActionListener("показатель A", 0));
		coefsPanel.add(btnA, "cell 2 0");

		JButton btnGamma = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		btnGamma.addActionListener(new ButtonActionListener("показатель \u03B3", 3));
		coefsPanel.add(btnGamma, "cell 2 3");

		JPanel panel2 = new JPanel();
		panel2.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(panel2);
		panel2.setLayout(new MigLayout("", "[206][grow]", "[][][]"));

		JLabel determCoefLabel = new JLabel(
				"         \u041A\u043E\u044D\u0444\u0438\u0446\u0438\u0435\u043D\u0442 \u0434\u0435\u0442\u0435\u0440\u043C\u0438\u043D\u0430\u0446\u0438\u0438");
		panel2.add(determCoefLabel, "flowx,cell 0 0,alignx right");
		determCoefLabel.setFont(new Font(font, Font.PLAIN, 13));

		r2 = new JFormattedTextField(Format.getNumberFormat());
		r2.setPreferredSize(new Dimension(180, 28));
		r2.setMinimumSize(new Dimension(142, 26));
		r2.setMaximumSize(new Dimension(180, 26));
		panel2.add(r2, "cell 1 0,alignx left");
		r2.setColumns(10);

		JLabel mapeLabel = new JLabel("MAPE");
		panel2.add(mapeLabel, "cell 0 1,alignx right");
		mapeLabel.setFont(new Font(font, Font.PLAIN, 13));

		mape = new JFormattedTextField(Format.getNumberFormat());
		mape.setPreferredSize(new Dimension(180, 28));
		mape.setMinimumSize(new Dimension(142, 26));
		mape.setMaximumSize(new Dimension(180, 26));
		panel2.add(mape, "cell 1 1,alignx left");
		mape.setColumns(10);

		JButton calculateBtn = new JButton(
				"\u0420\u0430\u0441\u0441\u0447\u0438\u0442\u0430\u0442\u044C \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044F \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u043E\u0432");
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean answer = false;
				try {
					answer = tableAndGraf.isDataSet();
				} catch (DataRequiredEx e) {
					LOG.error(e.getMessage(), e);
				}
				if (answer) {

					double[][] data = tableAndGraf.getTableData();

					ManFun mf = new ManFun();

					Statistics poss = tableAndGraf.getFirstStatistic();
					Statistics staff = tableAndGraf.getSecondStatistic();
					Statistics assets = tableAndGraf.getThirdStatistic();
					double gammaDat = 0;
					if (isokr) {
						Statistics real = tableAndGraf.getFourthStatistic();
						mf.proceed(poss, staff, assets, real);
						gammaDat = mf.getGamma().getValue(0);
						gamma.setText(Format.formatValue(gammaDat));
					} else {
						mf.proceed(poss, staff, assets);
					}

					double aDat = mf.getA().getValue(0);
					double alfaDat = mf.getAlfa().getValue(0);
					double betaDat = mf.getBeta().getValue(0);

					a.setText(Format.formatValue(aDat));
					alfa.setText(Format.formatValue(alfaDat));
					beta.setText(Format.formatValue(betaDat));
					r2.setText(Format.formatValue(mf.getR()));
					mape.setText(Format.formatValue(mf.getMape()));

					tableAndGraf.setStatistic(mf.getY());

					aNew = new Statistics();
					alfaNew = new Statistics();
					betaNew = new Statistics();

					if (isokr) {
						gammaNew = new Statistics();
					}

					for (int i = 0; i < data.length; i++) {
						int year = Main.Year - i;
						aNew.add(year, aDat);
						alfaNew.add(year, alfaDat);
						betaNew.add(year, betaDat);
						if (isokr) {
							gammaNew.add(year, gammaDat);
						}
					}
				}

			}
		});
		panel2.add(calculateBtn, "cell 0 2 2 1,growx,aligny bottom");
		calculateBtn.setPreferredSize(new Dimension(380, 25));
		calculateBtn.setFont(new Font(font, Font.PLAIN, 13));

		JLabel r2Label = new JLabel("R^2");
		panel2.add(r2Label, "cell 0 0");
		r2Label.setFont(new Font(font, Font.PLAIN, 13));
		gamma.setVisible(isokr);
		btnGamma.setVisible(isokr);

	}

	Statistics setStatistics(double xy) {
		Statistics value = new Statistics();
		for (int i = 0; i < Main.ModelLenght; i++) {
			try {
				value.add((int) Main.Year + i, xy);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return value;
	}

	public void refresh(RnD data, double[][] table) {
		topPanel.refresh(data);
		setTable(table);
	}

	public class ButtonActionListener implements ActionListener {
		String name;
		int index;

		ButtonActionListener(String name, int index) {
			this.name = name;
			this.index = index;
		}

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) {
			try {
				StatisticParam frame = new StatisticParam(name, index);
				frame.show();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	public class StatisticParam extends JFrame {

		private static final long serialVersionUID = -6748576458004160515L;
		private JPanel contentPane;
		private JTable table;
		private JButton btnOk;
		double[] statisticParamArray;
		private JPanel panel;
		private JButton btnCancel;
		private JLabel label;
		private FreeChartGraf graf;

		public StatisticParam(String tableName, final int index) throws ClassNotFoundException,
				InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			setBounds(new Rectangle(400, 100, 720, 480));
			setTitle("Таблица значений параметра");

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
			// ////////////////////////////////////////////
			table = new JTable(2, Main.ModelLenght);

			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setMaxWidth(300);
				table.getColumnModel().getColumn(i).setPreferredWidth(100);
				table.getColumnModel().getColumn(i).setMinWidth(50);
			}

			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setHeaderValue(Main.Year - i);
			}

			table.setPreferredScrollableViewportSize(new Dimension(450, 17));
			table.setFont(new Font(font, Font.PLAIN, 13));

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
						break;
					case 1:
						alfaNew = new Statistics();
						break;
					case 2:
						betaNew = new Statistics();
						break;
					case 3:
						gammaNew = new Statistics();
						break;
					}

					for (int i = 0; i < table.getColumnCount(); i++) {
						try {
							xy[0][i] = Main.Year + i;// координаты иксов
							xy[1][i] = Format.getDouble(table.getValueAt(0, i));

							switch (index) {
							case 0:
								aNew.add((int) xy[0][i], xy[1][i]);
								break;
							case 1:
								alfaNew.add((int) xy[0][i], xy[1][i]);
								break;
							case 2:
								betaNew.add((int) xy[0][i], xy[1][i]);
								break;
							case 3:
								gammaNew.add((int) xy[0][i], xy[1][i]);
								break;
							}
						} catch (Exception e) {
							LOG.error(e.getMessage(), e);
						}
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
			label.setFont(new Font(font, Font.PLAIN, 13));
			label.setText(tableName);
			contentPane.add(label, BorderLayout.NORTH);

		}

		public double[] getStatisticParamArray() {
			return statisticParamArray;
		}

		public void setStatisticParamArray(double[] statisticParamArray) {
			this.statisticParamArray = statisticParamArray.clone();
		}
	}

}
