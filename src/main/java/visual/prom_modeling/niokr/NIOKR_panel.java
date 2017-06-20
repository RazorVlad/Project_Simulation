package visual.prom_modeling.niokr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mathModel.ModelObjectsContainer;
import mathModel.RnD;
import mathModel.Statistics;
import mathModel.manFun.ManFun;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.NumberOrButton;
import visual.save.TableAndGrafSave;
import visual.save.VisualObjectsContainer;

public class NIOKR_panel extends JPanel {

	private static final long serialVersionUID = 4733968958587670294L;
	private static Logger LOG = Logger.getLogger(NIOKR_panel.class);
	private NIOKR_panel_param nir;
	private NIOKR_panel_param okr;
	private JFormattedTextField finExt;
	private JFormattedTextField finInt;
	final NumberOrButton partIncomeStaff;
	final NumberOrButton coefFin;
	final NumberOrButton coefFinInt;
	final NumberOrButton coefFinExt;
	JTabbedPane tabbedPane1;

	public double getfinInt() {
		return Format.getDouble(finInt.getText());
	}

	public double getfinExt() {
		return Format.getDouble(finExt.getText());
	}

	public Statistics getpartIncomeStaff() {
		return partIncomeStaff.getStatistics();
	}

	public Statistics getcoefFinPart() {
		return coefFin.getStatistics();
	}

	public Statistics getcoefFinInt() {
		return coefFinInt.getStatistics();
	}

	public Statistics getcoefFinExt() {
		return coefFinExt.getStatistics();
	}

	public NIOKR_panel_param getNIR() {
		return nir;
	}

	public NIOKR_panel_param getOKR() {
		return okr;
	}

	public NIOKR_panel(int otrasl) {

		setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		setBounds(new Rectangle(0, 0, 1300, 750));
		setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(3000, 183));
		topPanel.setMaximumSize(new Dimension(32767, 310));
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new MigLayout("", "[899.00,grow][105][120px:120:120px]",
				"[25:25:25][25:25:25][26:26:26][26:26:26][26:26:26][26:26:26]"));

		JLabel finIntLabel = new JLabel(
				"Объем финансирования НИР за счет отечественных заказчиков в начальном году, тыс.грн.");
		finIntLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(finIntLabel, "cell 0 0,alignx left");

		finInt = new JFormattedTextField(Format.getNumberFormat());
		finInt.setPreferredSize(new Dimension(120, 25));
		finInt.setFont(new Font("Calibri", Font.PLAIN, 13));
		finInt.setMinimumSize(new Dimension(120, 25));
		finInt.setMaximumSize(new Dimension(120, 25));
		topPanel.add(finInt, "cell 2 0,growx");
		finInt.setColumns(10);

		JLabel finExtLabel = new JLabel(
				"Объем финансирования НИР за счет иностранных заказчиков в начальном году, тыс.грн.");
		finExtLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(finExtLabel, "cell 0 1,alignx left");

		finExt = new JFormattedTextField(Format.getNumberFormat());
		finExt.setPreferredSize(new Dimension(120, 25));
		finExt.setFont(new Font("Calibri", Font.PLAIN, 13));
		finExt.setMinimumSize(new Dimension(120, 25));
		finExt.setMaximumSize(new Dimension(120, 25));
		topPanel.add(finExt, "cell 2 1,growx");
		finExt.setColumns(10);

		partIncomeStaff = new NumberOrButton("Доля кадров, поступивших на работу в сферу НИР", true);
		partIncomeStaff.setMaxValue(1);
		topPanel.add(partIncomeStaff, "cell 1 2 2 1,growx");

		JLabel coefFinIntLabel = new JLabel(
				"Изменение объема финансирования НИОКР за счет отечественных заказчиков");
		coefFinIntLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(coefFinIntLabel, "cell 0 3,alignx left");

		coefFinInt = new NumberOrButton(
				"Изменение объема финансирования НИОКР за счет отечественных заказчиков", true);
		topPanel.add(coefFinInt, "cell 1 3 2 1,growx");

		JLabel coefFinExtLabel = new JLabel(
				"Изменение объема финансирования НИОКР за счет иностранных заказчиков");
		coefFinExtLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(coefFinExtLabel, "cell 0 4,alignx left");

		coefFinExt = new NumberOrButton(
				"Изменение объема финансирования НИОКР за счет иностранных заказчиков", true);
		topPanel.add(coefFinExt, "cell 1 4 2 1,growx");

		JLabel coefFinPartLabel = new JLabel("Доля финансирования НИР в общем объеме финансирования НИОКР");
		coefFinPartLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(coefFinPartLabel, "cell 0 5,alignx left");

		coefFin = new NumberOrButton("Доля финансирования НИР в общем объеме финансирования НИОКР", true);
		coefFin.setMaxValue(1);
		topPanel.add(coefFin, "cell 1 5 2 1,growx");

		JLabel partIncomeStaffLabel = new JLabel("Доля кадров, поступивших на работу в сферу НИР");
		partIncomeStaffLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(partIncomeStaffLabel, "cell 0 2,alignx left");

		tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (okr != null && nir != null) {
					if (tabbedPane1.getSelectedIndex() == 1) {
						okr.getTable().setSpinnerValue(nir.getTable().getSpinnerValue());
						okr.getTable().setFourthColumn(nir.getTable().getFirstColumn());
					} else {
						nir.getTable().setSpinnerValue(okr.getTable().getSpinnerValue());
					}
				}
			}
		});
		tabbedPane1.setFont(new Font("Calibri", Font.PLAIN, 13));
		add(tabbedPane1, BorderLayout.CENTER);

		JPanel panelNIR = new JPanel();
		panelNIR.setBorder(null);
		tabbedPane1.addTab("\u0421\u0444\u0435\u0440\u0430 \u041D\u0418\u0420", null, panelNIR, null);
		panelNIR.setLayout(new BorderLayout(0, 0));

		nir = new NIOKR_panel_param(false);
		panelNIR.add(nir);

		final JPanel panelOKR = new JPanel();
		panelOKR.setBorder(null);
		tabbedPane1.addTab("\u0421\u0444\u0435\u0440\u0430 \u041E\u041A\u0420", null, panelOKR, null);
		panelOKR.setLayout(new BorderLayout(0, 0));

		okr = new NIOKR_panel_param(true);
		panelOKR.add(okr);
		refresh(otrasl);
	}

	public void refresh(int vedID) {
		try {
			RnD nirdata = ModelObjectsContainer.getInstance().getRndR(vedID);
			RnD okrdata = ModelObjectsContainer.getInstance().getRndD(vedID);
			if (nirdata != null && okrdata != null) {

				finInt.setText(String.valueOf(nirdata.getfinInt()));
				finExt.setText(String.valueOf(nirdata.getfinExt()));
				partIncomeStaff.setValue(nirdata.getpartIncomeStaff());
				coefFinInt.setValue(nirdata.getcoefFinInt());
				coefFinExt.setValue(nirdata.getcoefFinExt());
				coefFin.setValue(nirdata.getcoefFin());
				TableAndGrafSave data = VisualObjectsContainer.getInstance().getTableAndGraf(vedID);
//				data.getNir();
//				data.getOkr();
				nir.refresh(nirdata, data.getNir());
				okr.refresh(okrdata, data.getOkr());

				setCoefs(false, nir, nirdata.getMf());
				setCoefs(true, okr, okrdata.getMf());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	void setCoefs(boolean isOKR, NIOKR_panel_param coefs, ManFun mf) {
		coefs.setA(mf.getA().getValue(0));
		coefs.setAlfa(mf.getAlfa().getValue(0));
		coefs.setBeta(mf.getBeta().getValue(0));
		coefs.setA(mf.getA());
		coefs.setAlfa(mf.getAlfa());
		coefs.setBeta(mf.getBeta());
		coefs.setR2(mf.getR());
		coefs.setMape(mf.getMape());
		if (isOKR) {
			coefs.setGamma(mf.getGamma().getValue(0));
			coefs.setGamma(mf.getGamma());
		}
	}
}
