package visual.prom_modeling.niokr;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mathModel.RnD;
import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;

public class NIOKR_panel_param_top extends JPanel {

	private static final long serialVersionUID = -5066774641763578931L;
	NumberOrButton coefInvest;
	NumberOrButton coefFin;
	NumberOrButton coefProd;
	NumberOrButton coefObs;
	NumberOrButton partOutcomeStaff;
	NumberOrButton coefNew;
	private JFormattedTextField AssetsT0;
	private JFormattedTextField staff;
	private String font = "Calibri";

	public Double getStaff() {
		return Format.getDouble(staff.getText());
	}

	public Double getAssetsT0() {
		return Format.getDouble(AssetsT0.getText());
	}

	public Statistics getCoefInvest() {
		return coefInvest.getStatistics();
	}

	public Statistics getCoefFin() {
		return coefFin.getStatistics();
	}

	public Statistics getCoefProd() {
		return coefProd.getStatistics();
	}

	public Statistics getCoefObs() {
		return coefObs.getStatistics();
	}

	public Statistics getPartOutcomeStaff() {
		return partOutcomeStaff.getStatistics();
	}

	public Statistics getCoefNew() {
		return coefNew.getStatistics();
	}

	public NIOKR_panel_param_top(String workType) {
		setPreferredSize(new Dimension(2000, 215));
		setLayout(new MigLayout("", "[][grow][][]",
				"[][26px:26px:26px][26px:26px:26px][26px:26px:26px][26px:26px:26px][26px:26px:26px][26px:26px:26px]"));
		JLabel label13 = new JLabel("Кадры в сфере " + workType + " в начальном году, чел.");

		add(label13, "flowx,cell 0 0");
		label13.setFont(new Font(font, Font.PLAIN, 13));

		JLabel label14 = new JLabel(
				"\u041E\u0441\u043D\u043E\u0432\u043D\u044B\u0435 \u0444\u043E\u043D\u0434\u044B \u0432 \u0441\u0444\u0435\u0440\u0435 "
						+ workType
						+ " \u0432 \u043D\u0430\u0447\u0430\u043B\u044C\u043D\u043E\u043C \u0433\u043E\u0434\u0443, \u0442\u044B\u0441.\u0433\u0440\u043D.");
		add(label14, "cell 1 0 2 1,alignx right");
		label14.setFont(new Font(font, Font.PLAIN, 13));

		AssetsT0 = new JFormattedTextField(Format.getNumberFormat());
		add(AssetsT0, "cell 3 0,alignx right");
		AssetsT0.setPreferredSize(new Dimension(123, 25));
		AssetsT0.setMinimumSize(new Dimension(123, 25));
		AssetsT0.setMaximumSize(new Dimension(123, 25));
		AssetsT0.setFont(new Font(font, Font.PLAIN, 13));
		AssetsT0.setColumns(10);

		JLabel coefInvestLabel = new JLabel("Доля инвестиций в основные фонды в сфере " + workType);
		add(coefInvestLabel, "cell 0 1");
		coefInvestLabel.setFont(new Font(font, Font.PLAIN, 13));

		coefInvest = new NumberOrButton("Доля инвестиций в основные фонды в сфере " + workType, true);
		coefInvest.setMaxValue(1);
		add(coefInvest, "cell 2 1 2 1,growy");

		JLabel coefFinLabel = new JLabel("Отношение затрат на " + workType + " к объему финансирования");
		add(coefFinLabel, "cell 0 2");
		coefFinLabel.setFont(new Font(font, Font.PLAIN, 13));

		coefFin = new NumberOrButton("Отношение затрат на " + workType + " к объему финансирования", true);
		add(coefFin, "cell 2 2 2 1,growy");

		JLabel coefProdLabel = new JLabel("Коэффициент продуктивности научных организаций");
		add(coefProdLabel, "cell 0 3");
		coefProdLabel.setFont(new Font(font, Font.PLAIN, 13));

		coefProd = new NumberOrButton("Коэффициент продуктивности научных организаций", true);
		add(coefProd, "cell 2 3 2 1,growy");

		JLabel coefObsLabel = new JLabel("Нормальный темп устаревания основных фондов в сфере " + workType);
		add(coefObsLabel, "cell 0 4");
		coefObsLabel.setFont(new Font(font, Font.PLAIN, 13));

		coefObs = new NumberOrButton("Нормальный темп устаревания основных фондов в сфере " + workType, true);
		add(coefObs, "cell 2 4 2 1,growy");

		JLabel partOutcomeStaffLabel = new JLabel("Нормальный темп уменьшения кадров в сфере " + workType);
		add(partOutcomeStaffLabel, "cell 0 5");
		partOutcomeStaffLabel.setFont(new Font(font, Font.PLAIN, 13));

		partOutcomeStaff = new NumberOrButton("Нормальный темп уменьшения кадров в сфере " + workType, true);
		add(partOutcomeStaff, "cell 2 5 2 1,growy");
		JLabel coefNewLabel = new JLabel("Доля затрат на приобретение новых фондов от объема финансирования "
				+ workType);
		add(coefNewLabel, "cell 0 6");
		coefNewLabel.setFont(new Font(font, Font.PLAIN, 13));

		coefNew = new NumberOrButton("Доля затрат на приобретение новых фондов от объема финансирования "
				+ workType, true);
		coefNew.setMaxValue(1);
		add(coefNew, "cell 2 6 2 1,growy");

		staff = new JFormattedTextField(Format.getNumberFormat());
		add(staff, "cell 0 0");
		staff.setPreferredSize(new Dimension(105, 25));
		staff.setMinimumSize(new Dimension(105, 25));
		staff.setMaximumSize(new Dimension(105, 25));
		staff.setFont(new Font(font, Font.PLAIN, 13));
		staff.setColumns(10);
	}

	public void refresh(RnD data) {
		staff.setText(String.valueOf(data.getStaff(0)));
		AssetsT0.setText(String.valueOf(data.getAssets(0)));
		coefInvest.setValue(data.getcoefInvest());
		coefFin.setValue(data.getcoefFin());
		coefProd.setValue(data.getcoefProd());
		coefObs.setValue(data.getcoefObs());
		partOutcomeStaff.setValue(data.getpartOutcomeStaff());
		coefNew.setValue(data.getcoefNew());
	}
}
