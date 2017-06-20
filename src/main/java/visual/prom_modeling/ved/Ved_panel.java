package visual.prom_modeling.ved;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.jfree.util.Log;

import exceptions.DataRequiredEx;
import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.manFun.ManFun;
import mathModel.manufacturing.Manufacturing;
import net.miginfocom.swing.MigLayout;
import visual.TableAndGraf;
import visual.frames.main.Main;
import visual.save.TableAndGrafSave;
import visual.save.VisualObjectsContainer;

public class Ved_panel extends JPanel {

	private static final long serialVersionUID = 6525935884990229430L;
	int manufacturingMeans = 0;// кадры/активы/средства/фонды
	int manufacturingType = 0;// рядовое/инновационное
	TableAndGraf tableAndGrafOrd;
	private String font="Calibri";

	public TableAndGraf getTableAndGrafOrd() {
		return tableAndGrafOrd;
	}

	public TableAndGraf getTableAndGrafInn() {
		return tableAndGrafInn;
	}

	TableAndGraf tableAndGrafInn;
	JPanel panelInn;
	JPanel panelOrd;

	Ved_panel_coefs coefsOrd;

	public Ved_panel_coefs getCoefsOrd() {
		return coefsOrd;
	}

	public Ved_panel_coefs getCoefsInn() {
		return coefsInn;
	}

	Ved_panel_coefs coefsInn;

	JPanel kadrOrdPanel;
	JPanel aktivInnPanel;
	JPanel sredstvaInnPanel;
	JPanel fondInnPanel;
	JPanel kadrInnPanel;
	JPanel sredstvaOrdPanel;
	JPanel fondOrdPanel;
	JSpinner numberOfDotsSpinner;
	Ved_panel_indexes ordPanel;
	Ved_panel_indexes innPanel;

	final JRadioButton radioInn;
	final JRadioButton radioOrd;

	Ved_panel_params paramInnPanel;
	Ved_panel_params paramOrdPanel;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public Statistics getFirstColumnOfTableInn() {
		return tableAndGrafInn.getFirstStatistic();
	}

	public Statistics getFirstColumnOfTableOrd() {
		return tableAndGrafOrd.getFirstStatistic();
	}

	public Ved_panel_params getInn() {
		return paramInnPanel;
	}

	public Ved_panel_params getOrd() {
		return paramOrdPanel;
	}

	public Ved_panel_indexes getOrdIndexes() {
		return ordPanel;
	}

	public Ved_panel_indexes getInnIndexes() {
		return innPanel;
	}

	public Ved_panel(int vedIndex) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try {
					tableAndGrafOrd.setBounds(Ved_panel.this.getWidth() - 300);
					tableAndGrafInn.setBounds(Ved_panel.this.getWidth() - 300);
				} catch (Exception ee) {
					Log.error(ee.getMessage(), ee);
				}
			}
		});
		setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		setBounds(new Rectangle(0, 0, 1300, 750));

		setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		topPanel.setMinimumSize(new Dimension(10, 30));
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		JLabel manufacturingTypeLabel = new JLabel(
				"   \u0422\u0438\u043F \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 :");
		manufacturingTypeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		manufacturingTypeLabel.setPreferredSize(new Dimension(170, 25));
		manufacturingTypeLabel.setMinimumSize(new Dimension(150, 30));
		topPanel.add(manufacturingTypeLabel);
		manufacturingTypeLabel.setMaximumSize(new Dimension(190, 35));
		manufacturingTypeLabel.setFont(new Font(font, Font.PLAIN, 14));

		radioOrd = new JRadioButton("\u0420\u044F\u0434\u043E\u0432\u043E\u0435");
		buttonGroup.add(radioOrd);
		radioOrd.setVerticalAlignment(SwingConstants.BOTTOM);
		radioOrd.setPreferredSize(new Dimension(120, 25));
		radioOrd.setMinimumSize(new Dimension(120, 30));
		topPanel.add(radioOrd);
		radioOrd.setHorizontalTextPosition(SwingConstants.RIGHT);
		radioOrd.setHorizontalAlignment(SwingConstants.LEFT);
		radioOrd.setMaximumSize(new Dimension(120, 35));
		radioOrd.setSelected(true);

		radioOrd.setFont(new Font(font, Font.PLAIN, 14));
		radioOrd.setAlignmentX(Component.CENTER_ALIGNMENT);

		radioInn = new JRadioButton(
				"\u0418\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u043E\u0435");
		buttonGroup.add(radioInn);
		radioInn.setVerticalAlignment(SwingConstants.BOTTOM);
		radioInn.setPreferredSize(new Dimension(120, 25));
		radioInn.setMinimumSize(new Dimension(120, 30));
		topPanel.add(radioInn);
		radioInn.setHorizontalAlignment(SwingConstants.LEFT);
		radioInn.setMaximumSize(new Dimension(120, 35));
		radioInn.setFont(new Font(font, Font.PLAIN, 14));
		radioInn.setAlignmentX(Component.CENTER_ALIGNMENT);

		radioInn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVedVisible(false);
			}
		});

		radioOrd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVedVisible(true);
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1000, 420));
		mainPanel.setMaximumSize(new Dimension(32767, 500));
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));

		paramOrdPanel = new Ved_panel_params(false);
		mainPanel.add(paramOrdPanel, "name_132880057091236");

		paramInnPanel = new Ved_panel_params(true);
		paramInnPanel.setVisible(false);
		mainPanel.add(paramInnPanel, "name_132810521006540");

		JTabbedPane parametersTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		parametersTabbedPane.setPreferredSize(new Dimension(1000, 450));
		parametersTabbedPane.setMinimumSize(new Dimension(1000, 400));
		parametersTabbedPane.setBorder(null);
		add(parametersTabbedPane, BorderLayout.SOUTH);

		JPanel funcParamPanel = new JPanel();
		funcParamPanel.setBorder(null);
		parametersTabbedPane
				.addTab("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0435\u043D\u043D\u043E\u0439 \u0444\u0443\u043D\u043A\u0446\u0438\u0438",
						null, funcParamPanel, null);
		funcParamPanel.setLayout(new BorderLayout(0, 0));

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(460, 700));
		rightPanel.setMaximumSize(new Dimension(310, 32767));
		rightPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		funcParamPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel label2 = new JLabel(
				"\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0435\u043D\u043D\u043E\u0439 \u0444\u0443\u043D\u043A\u0446\u0438\u0438");
		label2.setMaximumSize(new Dimension(310, 24));
		label2.setPreferredSize(new Dimension(300, 24));
		label2.setMinimumSize(new Dimension(300, 24));
		label2.setHorizontalTextPosition(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(label2);
		label2.setFont(new Font(font, Font.PLAIN, 14));

		JPanel coefsPanel = new JPanel();
		coefsPanel.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
		rightPanel.add(coefsPanel);
		coefsPanel.setLayout(new CardLayout(0, 0));

		coefsOrd = new Ved_panel_coefs(true);
		coefsOrd.setVisible(true);
		coefsPanel.add(coefsOrd, "name_384871124676357");

		coefsInn = new Ved_panel_coefs(false);
		coefsInn.setVisible(false);
		coefsPanel.add(coefsInn, "name_183018665092852");

		JButton getParamsCoefBtn = new JButton(
				"\u0420\u0430\u0441\u0441\u0447\u0438\u0442\u0430\u0442\u044C \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044F \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u043E\u0432");
		getParamsCoefBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		getParamsCoefBtn.setMinimumSize(new Dimension(15000, 28));
		getParamsCoefBtn.setMaximumSize(new Dimension(120000, 28));
		getParamsCoefBtn.setPreferredSize(new Dimension(8000, 28));
		rightPanel.add(getParamsCoefBtn);
		getParamsCoefBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (radioOrd.isSelected()) {
						setData(tableAndGrafOrd, true, coefsOrd);
					}
					if (radioInn.isSelected()) {
						setData(tableAndGrafInn, false, coefsInn);
					}
				} catch (DataRequiredEx e) {
					Log.error(e.getMessage(), e);
				}

			}
		});
		getParamsCoefBtn.setFont(new Font(font, Font.PLAIN, 14));

		JPanel leftPanel = new JPanel();
		funcParamPanel.add(leftPanel);
		leftPanel.setLayout(new CardLayout(0, 0));

		panelOrd = new JPanel();

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = gd.getDisplayMode().getHeight() - 475;
		int width = gd.getDisplayMode().getWidth() - 650;
		panelOrd.setLayout(new MigLayout("", "[1px]", "[1px]"));
		tableAndGrafOrd = new TableAndGraf(height - 200, width - 200, true, true);
		leftPanel.add(tableAndGrafOrd, "name_2390204300814");

		panelInn = new JPanel();
		panelInn.setVisible(false);
		panelInn.setLayout(new BorderLayout(0, 0));
		tableAndGrafInn = new TableAndGraf(height, width, true, false);
		tableAndGrafInn.setVisible(false);
		leftPanel.add(tableAndGrafInn, "name_2384067902676");

		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(null);
		parametersTabbedPane
				.addTab("\u041F\u043E\u043A\u0430\u0437\u0430\u0435\u043B\u0438 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430",
						null, paramPanel, null);
		paramPanel.setLayout(new CardLayout(0, 0));

		ordPanel = new Ved_panel_indexes(true);
		paramPanel.add(ordPanel, "name_24429903678953");

		innPanel = new Ved_panel_indexes(false);
		innPanel.setVisible(false);
		paramPanel.add(innPanel, "name_24435978678841");

		refresh(vedIndex);
	}

	public void refresh(int vedname) {
		try {
			Manufacturing vedord = ModelObjectsContainer.getInstance().getVedOrd(vedname);
			Manufacturing vedinn = ModelObjectsContainer.getInstance().getVedInn(vedname);
			TableAndGrafSave data = VisualObjectsContainer.getInstance().getTableAndGraf(vedname);

			if (vedinn != null && vedord != null) {
				setIndexes(ordPanel, vedord);
				setIndexes(innPanel, vedinn);
				setCoefs(true, coefsOrd, vedord.getMf());
				setCoefs(false, coefsInn, vedinn.getMf());
				// ord
				tableAndGrafOrd.setTableData(data.getOrd());
				paramOrdPanel.refresh(vedord);
				paramOrdPanel.refreshOrd(vedord.getpartAmort());

				// inn
				tableAndGrafInn.setTableData(data.getInn());
				paramInnPanel.refresh(vedinn);
				paramInnPanel.refreshInn(vedinn.getintangibles(0), vedinn.getworkingPart(),
						vedinn.getworkingIntangiblesPart(), vedinn.getcoefObsIntangibles());
			}
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

	void setCoefs(boolean isOrd, Ved_panel_coefs coefs, ManFun ved) {
		coefs.setA(ved.getA().getValue(0));
		coefs.setAlfa(ved.getAlfa().getValue(0));
		coefs.setBeta(ved.getBeta().getValue(0));
		coefs.setANew(ved.getA());
		coefs.setAlfaNew(ved.getAlfa());
		coefs.setBetaNew(ved.getBeta());
		coefs.setR2(ved.getR());
		coefs.setMAPE(ved.getMape());
		if (!isOrd) {
			coefs.setGamma(ved.getGamma().getValue(0));
			coefs.setGammaNew(ved.getGamma());
		}
	}

	void setIndexes(Ved_panel_indexes panel, Manufacturing ved) {
		panel.setProdPrice(ved.getProdPriceAt(0));
		panel.setRawPriceT0(ved.getrawPrice(0));
		panel.setSalaryT0(ved.getSalary(0));
		panel.setExtraCost(ved.getextraCost(0));
		panel.setCoefProdPrice(ved.getcoefProdPrice());
		panel.setCoefRawPrice(ved.getcoefRawPrice());
		panel.setCoefSalaryChange(ved.getcoefSalaryChange());
		panel.setCoefExtraCost(ved.getcoefExtraCost());
	}

	void setData(TableAndGraf table, boolean isOrd, Ved_panel_coefs ved) throws DataRequiredEx {
		if (table.isDataSet()) {
			Statistics potential = table.getFirstStatistic();
			Statistics staff = table.getSecondStatistic();
			Statistics assets = table.getThirdStatistic();

			ManFun mf = new ManFun();
			if (!isOrd) {
				Statistics intangibles = table.getFourthStatistic();
				mf.proceed(potential, staff, assets, intangibles);
			}
			mf.proceed(potential, staff, assets);

			double aDat = mf.getA().getValue(0);
			double alfaDat = mf.getAlfa().getValue(0);
			double betaDat = mf.getBeta().getValue(0);
			double gammaDat = 0;

			if (!isOrd) {
				gammaDat = mf.getGamma().getValue(0);
				ved.setGamma(gammaDat);
			}

			ved.setA(aDat);
			ved.setAlfa(alfaDat);
			ved.setBeta(betaDat);
			ved.setR2(mf.getR());
			ved.setMAPE(mf.getMape());

			table.setStatistic(mf.getY());
			table.getTableData();
			double[][] ordData = table.getTableData();

			Statistics aNewOrd = new Statistics();
			Statistics alfaNewOrd = new Statistics();
			Statistics betaNewOrd = new Statistics();
			Statistics gammaNewOrd = new Statistics();

			for (int i = 0; i < ordData.length; i++) {
				int yeardata = Main.Year - i;
				aNewOrd.add(yeardata, aDat);
				alfaNewOrd.add(yeardata, alfaDat);
				betaNewOrd.add(yeardata, betaDat);
				if (!isOrd) {
					gammaNewOrd.add(yeardata, gammaDat);
				}
			}

			ved.setANew(aNewOrd);
			ved.setAlfaNew(alfaNewOrd);
			ved.setBetaNew(betaNewOrd);
			if (!isOrd) {
				ved.setGammaNew(betaNewOrd);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Введены не все данные", "Ошибка ввода",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setVedVisible(boolean isOrd) {
		coefsOrd.setVisible(isOrd);
		coefsInn.setVisible(!isOrd);
		ordPanel.setVisible(isOrd);
		innPanel.setVisible(!isOrd);
		panelInn.setVisible(!isOrd);
		panelOrd.setVisible(isOrd);
		tableAndGrafInn.setVisible(!isOrd);
		tableAndGrafOrd.setVisible(isOrd);
		paramInnPanel.setVisible(!isOrd);
		paramOrdPanel.setVisible(isOrd);
	}
}
