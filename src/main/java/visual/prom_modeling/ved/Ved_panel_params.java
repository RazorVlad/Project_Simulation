package visual.prom_modeling.ved;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

import mathModel.Statistics;
import mathModel.manufacturing.Manufacturing;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;

public class Ved_panel_params extends JPanel {

	private static final long serialVersionUID = -6712391274558339480L;
	private static Logger LOG = Logger.getLogger(Ved_panel_params.class);
	int manufacturingMeans = 0;// кадры/активы/средства/фонды
	JPanel kadrPanel;
	JPanel aktivPanel;
	JPanel sredstvaPanel;
	JPanel fondPanel;
	private String font = "Calibri";
	private JFormattedTextField staffTo;
	private JFormattedTextField assetsT0;
	private NumberOrButton coefAmort1;
	private NumberOrButton coefAmort2;
	private NumberOrButton partForFunds;
	private NumberOrButton partAmort;
	private NumberOrButton coefObsFunds;
	private NumberOrButton coefOutcome;
	private NumberOrButton fieldPart;
	private JFormattedTextField wc;
	private NumberOrButton coefWC;
	private JFormattedTextField intangibles;
	private NumberOrButton workingPart;
	private NumberOrButton workingIntengiblesPart;
	private NumberOrButton coefObsIntangibles;
	JButton aktivBtn;
	JButton kadrBtn;
	JButton fondBtn;
	JButton sredstvaBtn;
	Color background;
	String manufacturingType = "инновационн";
	boolean isInnovation;
	JButton btn;

	public double getStaffTo() {
		return Format.getDouble(staffTo.getText());
	}

	public double getAssetsT0() {
		return Format.getDouble(assetsT0.getText());
	}

	public Statistics getCoefAmort1() {
		return coefAmort1.getStatistics();
	}

	public Statistics getCoefAmort2() {
		return coefAmort2.getStatistics();
	}

	public Statistics getPartForFunds() {
		return partForFunds.getStatistics();
	}

	public Statistics getPartAmort() {
		return partAmort.getStatistics();
	}

	public Statistics getCoefObsFunds() {
		return coefObsFunds.getStatistics();
	}

	public Statistics getCoefOutcome() {
		return coefOutcome.getStatistics();
	}

	public Statistics getqPart() {
		return fieldPart.getStatistics();
	}

	public double getWc() {
		return Format.getDouble(wc.getText());
	}

	public Statistics getCoefWC() {
		return coefWC.getStatistics();
	}

	public double getIntangibles() {
		return Format.getDouble(intangibles.getText());
	}

	public Statistics getWorkingPart() {
		return workingPart.getStatistics();
	}

	public Statistics getWorkingIntengiblesPart() {
		return workingIntengiblesPart.getStatistics();
	}

	public Statistics getCoefObsIntangibles() {
		return coefObsIntangibles.getStatistics();
	}

	public Ved_panel_params(boolean isInnovation) {
		this.isInnovation = isInnovation;
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		if (isInnovation) {
			manufacturingType = "инновационн";
		} else {
			manufacturingType = "рядов";
		}

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[170px]", "[40][40][40][40]"));

		kadrBtn = new JButton("Кадры");
		background = kadrBtn.getBackground();
		panel.add(kadrBtn, "cell 0 0,growx,aligny center");
		kadrBtn.setBackground(new Color(100, 149, 237));
		kadrBtn.setPreferredSize(new Dimension(120, 30));
		kadrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose(0);
			}
		});
		kadrBtn.setFont(new Font(font, Font.PLAIN, 14));
		kadrBtn.setHorizontalAlignment(SwingConstants.LEFT);

		fondBtn = new JButton("Основные фонды");
		panel.add(fondBtn, "cell 0 1,growx,aligny center");
		fondBtn.setPreferredSize(new Dimension(120, 30));
		fondBtn.setFont(new Font(font, Font.PLAIN, 14));
		fondBtn.setHorizontalAlignment(SwingConstants.LEFT);
		fondBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose(1);
			}
		});

		sredstvaBtn = new JButton("Оборотные средства");
		panel.add(sredstvaBtn, "cell 0 2,growx,aligny center");
		sredstvaBtn.setPreferredSize(new Dimension(120, 30));
		sredstvaBtn.setFont(new Font(font, Font.PLAIN, 14));
		sredstvaBtn.setHorizontalAlignment(SwingConstants.LEFT);
		sredstvaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose(2);
			}
		});

		if (isInnovation) {
			aktivBtn = new JButton("Немтериальные активы");
			panel.add(aktivBtn, "cell 0 3,growx,aligny center");
			aktivBtn.setVisible(true);
			aktivBtn.setPreferredSize(new Dimension(120, 30));
			aktivBtn.setFont(new Font(font, Font.PLAIN, 14));
			aktivBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose(3);
				}
			});
		}
		btn = kadrBtn;

		JPanel paramsPanel = new JPanel();
		paramsPanel.setBorder(new MatteBorder(1, 0, 1, 1, (Color) new Color(0, 0, 0)));
		paramsPanel.setMaximumSize(new Dimension(32767, 220));
		add(paramsPanel);
		paramsPanel.setLayout(new CardLayout(0, 0));

		kadrPanel = new JPanel();
		kadrPanel.setBorder(null);
		kadrPanel.setPreferredSize(new Dimension(1000, 500));
		kadrPanel.setMinimumSize(new Dimension(10, 500));
		paramsPanel.add(kadrPanel, "name_380940060458460");
		kadrPanel.setLayout(new MigLayout("", "[550.00][105][135]", "[][][]"));

		JLabel staffToLabel = new JLabel("Количество кадров в сфере " + manufacturingType
				+ "ого производства ВЭДа в базовом году, чел.");
		staffToLabel.setFont(new Font(font, Font.PLAIN, 14));
		kadrPanel.add(staffToLabel, "cell 0 0,alignx left,aligny center");

		staffTo = new JFormattedTextField(Format.getNumberFormat());
		staffTo.setMaximumSize(new Dimension(120, 28));
		staffTo.setMinimumSize(new Dimension(12, 25));
		staffTo.setPreferredSize(new Dimension(120, 28));
		staffTo.setFont(new Font(font, Font.PLAIN, 14));
		kadrPanel.add(staffTo, "cell 2 0,grow");
		staffTo.setColumns(10);

		JLabel coefOutcomeLabel = new JLabel("Нормальный темп уменьшения кадров в " + manufacturingType
				+ "ом производстве");
		coefOutcomeLabel.setFont(new Font(font, Font.PLAIN, 14));
		kadrPanel.add(coefOutcomeLabel, "cell 0 1,alignx left");

		coefOutcome = new NumberOrButton("Нормальный темп уменьшения кадров в " + manufacturingType
				+ "ом производстве", false);
		kadrPanel.add(coefOutcome, "cell 1 1 2 1,grow");

		JLabel qPartLabel;
		if (isInnovation) {
			qPartLabel = new JLabel("Доля специалистов, поступающих в " + manufacturingType
					+ "ое производство");
		} else {
			qPartLabel = new JLabel("Доля кадров, поступающих на роботу в данный ВЭД");
		}
		qPartLabel.setBorder(null);
		qPartLabel.setFont(new Font(font, Font.PLAIN, 14));
		kadrPanel.add(qPartLabel, "cell 0 2,alignx left");

		fieldPart = new NumberOrButton(qPartLabel.getText(), false);
		fieldPart.setMaxValue(1);

		kadrPanel.add(fieldPart, "cell 1 2 2 1,grow");

		fondPanel = new JPanel();
		fondPanel.setPreferredSize(new Dimension(900, 500));
		fondPanel.setMinimumSize(new Dimension(900, 500));
		paramsPanel.add(fondPanel, "name_380940106185422");
		fondPanel.setLayout(new MigLayout("", "[550.00][105][120]", "[][][][][][][]"));

		JLabel assetsT0RLabel = new JLabel("Основные фонды в сфере " + manufacturingType
				+ "ого производства в базовом году, тыс. грн.");
		assetsT0RLabel.setFont(new Font(font, Font.PLAIN, 14));
		fondPanel.add(assetsT0RLabel, "cell 0 0,alignx left");

		// assetsT0 = new NumberOrButton("Основные фонды в сфере " +
		// tipProizvodsta
		// + "ого производства, тыс. грн.", false);
		assetsT0 = new JFormattedTextField(Format.getNumberFormat());
		assetsT0.setPreferredSize(new Dimension(120, 28));
		assetsT0.setMaximumSize(new Dimension(120, 28));
		fondPanel.add(assetsT0, "cell 2 0,grow");

		JLabel coefAmort1RLabel = new JLabel("Коэффициент амортизации основных фондов в " + manufacturingType
				+ "ом производстве");
		coefAmort1RLabel.setFont(new Font(font, Font.PLAIN, 14));
		fondPanel.add(coefAmort1RLabel, "cell 0 1,alignx left");

		coefAmort1 = new NumberOrButton("Коэффициент амортизации основных фондов в " + manufacturingType
				+ "ом производстве", false);
		fondPanel.add(coefAmort1, "cell 1 1 2 1,grow");

		JLabel coefAmort2Label = new JLabel("Коэффициент пополнения основных фондов из амортизации в "
				+ manufacturingType + "ом производстве");
		coefAmort2Label.setFont(new Font(font, Font.PLAIN, 14));
		fondPanel.add(coefAmort2Label, "cell 0 2,alignx left");

		coefAmort2 = new NumberOrButton("Коэффициент пополнения основных фондов из амортизации в "
				+ manufacturingType + "ом производстве", false);
		fondPanel.add(coefAmort2, "cell 1 2 2 1,grow");

		JLabel partForFundsLabel = new JLabel("Доля расходов на пополнение основных фондов в "
				+ manufacturingType + "ом производстве");
		partForFundsLabel.setFont(new Font(font, Font.PLAIN, 14));
		fondPanel.add(partForFundsLabel, "cell 0 3,alignx left");

		partForFunds = new NumberOrButton("Доля расходов на пополнение основных фондов в "
				+ manufacturingType + "ом производстве", false);
		partForFunds.setMaxValue(1);
		fondPanel.add(partForFunds, "cell 1 3 2 1,grow");
		JLabel coefObsFundsLabel = new JLabel("Нормальный темп устаревания основных фондов из амортизации в "
				+ manufacturingType + "ом производстве");
		coefObsFundsLabel.setFont(new Font(font, Font.PLAIN, 14));
		fondPanel.add(coefObsFundsLabel, "cell 0 4,alignx left");

		coefObsFunds = new NumberOrButton("Нормальный темп устаревания основных фондов из амортизации в "
				+ manufacturingType + "ом производстве", false);
		fondPanel.add(coefObsFunds, "cell 1 4 2 1,grow");

		if (!isInnovation) {
			JLabel partAmortLabel = new JLabel(
					"Доля амортизационных отчислений, использующихся в рядовом производстве");
			partAmortLabel.setFont(new Font(font, Font.PLAIN, 14));
			fondPanel.add(partAmortLabel, "cell 0 5,alignx left");

			partAmort = new NumberOrButton(
					"Доля амортизационных отчислений, использующихся в рядовом производстве", false);
			partAmort.setMaxValue(1);
			fondPanel.add(partAmort, "cell 1 5 2 1,grow");

		}

		sredstvaPanel = new JPanel();
		paramsPanel.add(sredstvaPanel, "name_380940181523167");
		sredstvaPanel.setLayout(new MigLayout("", "[550.00][105][120]", "[][]"));

		JLabel wcRLabel = new JLabel("Оборотные средства в сфере " + manufacturingType
				+ "ого производства в базовом году, тыс грн");
		wcRLabel.setFont(new Font(font, Font.PLAIN, 14));
		sredstvaPanel.add(wcRLabel, "cell 0 0,alignx left");

		wc = new JFormattedTextField(Format.getNumberFormat());
		wc.setPreferredSize(new Dimension(120, 28));
		wc.setMaximumSize(new Dimension(120, 28));
		sredstvaPanel.add(wc, "cell 2 0,growx");

		JLabel coefWCLabel = new JLabel("Коэффициент оборачиваемости оборотных средств в "
				+ manufacturingType + "ом производстве");
		coefWCLabel.setFont(new Font(font, Font.PLAIN, 14));
		sredstvaPanel.add(coefWCLabel, "cell 0 1,alignx left");

		coefWC = new NumberOrButton("Коэффициент оборачиваемости оборотных средств в " + manufacturingType
				+ "ом производстве", false);
		sredstvaPanel.add(coefWC, "cell 1 1 2 1,growx");

		if (isInnovation) {
			aktivPanel = new JPanel();
			paramsPanel.add(aktivPanel, "name_380940483264550");
			aktivPanel.setLayout(new MigLayout("", "[550][105][120]", "[][][][]"));

			JLabel intangiblesLabel = new JLabel(
					"Нематериальные активы в сфере инновационного производства в базовом году, тыс грн.");
			aktivPanel.add(intangiblesLabel, "cell 0 0,alignx left");

			intangibles = new JFormattedTextField(Format.getNumberFormat());
			aktivPanel.add(intangibles, "cell 2 0,growx");

			JLabel workingPartLabel = new JLabel("Доля ОКР нашедших применение в производстве");
			aktivPanel.add(workingPartLabel, "cell 0 1,alignx left");

			workingPart = new NumberOrButton("Доля ОКР нашедших применение в производстве", false);
			workingPart.setMaxValue(1);
			aktivPanel.add(workingPart, "cell 1 1 2 1,grow");

			JLabel workingIntengiblesPartLabel = new JLabel(
					"Доля нематериальных активов, поступающих в данный ВЭД");
			aktivPanel.add(workingIntengiblesPartLabel, "cell 0 2,alignx left");

			workingIntengiblesPart = new NumberOrButton(
					"Доля нематериальных активов, поступающих в данный ВЭД", false);
			workingIntengiblesPart.setMaxValue(1);
			aktivPanel.add(workingIntengiblesPart, "cell 1 2 2 1,grow");

			JLabel coefObsIntangiblesLabel = new JLabel("Нормальный темп устаревания нематериальных активов");
			aktivPanel.add(coefObsIntangiblesLabel, "cell 0 3,alignx left");

			coefObsIntangibles = new NumberOrButton("Нормальный темп устаревания нематериальных активов",
					false);
			aktivPanel.add(coefObsIntangibles, "cell 1 3 2 1,grow");
		}
	}

	void choose(int manufacturingMeans) {
		kadrPanel.setVisible(false);
		if (isInnovation) {
			aktivPanel.setVisible(false);
		}
		sredstvaPanel.setVisible(false);
		fondPanel.setVisible(false);
		btn.setBackground(background);

		switch (manufacturingMeans) {
		case 0:
			kadrPanel.setVisible(true);
			kadrBtn.setBackground(new Color(100, 149, 237));
			btn = kadrBtn;
			break;
		case 1:
			fondPanel.setVisible(true);
			fondBtn.setBackground(new Color(100, 149, 237));
			btn = fondBtn;
			break;
		case 2:
			sredstvaPanel.setVisible(true);
			sredstvaBtn.setBackground(new Color(100, 149, 237));
			btn = sredstvaBtn;
			break;
		case 3:
			aktivPanel.setVisible(true);
			aktivBtn.setBackground(new Color(100, 149, 237));
			btn = aktivBtn;
			break;
		}
	}

	public void refresh(Manufacturing ved) {
		staffTo.setText(String.valueOf(ved.getStaff(0)));
		coefOutcome.setValue(ved.getcoefOutcome());
		fieldPart.setValue(ved.getfieldPart());

		assetsT0.setValue(ved.getAssets(0));
		coefAmort1.setValue(ved.getcoefAmort1());
		coefAmort2.setValue(ved.getcoefAmort2());
		partForFunds.setValue(ved.getpartForFunds());
		coefObsFunds.setValue(ved.getcoefObsFunds());

		wc.setValue(ved.getwc(0));
		coefWC.setValue(ved.getcoefWC());
	}

	public void refreshInn(double intangiblesValue, double[][] workingPartValue,
			double[][] workingIntengiblesPartValue, double[][] coefObsIntangiblesValue) {
		intangibles.setValue(intangiblesValue);
		workingPart.setValue(workingPartValue);
		workingIntengiblesPart.setValue(workingIntengiblesPartValue);
		coefObsIntangibles.setValue(coefObsIntangiblesValue);
	}

	public void refreshOrd(double[][] partAmortValue) {
		partAmort.setValue(partAmortValue);
	}
}
