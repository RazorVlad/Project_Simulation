package visual.project_modeling.projectBaseTab;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import mathModel.project.Project;
import net.miginfocom.swing.MigLayout;
import visual.frames.main.Main;

public class DurationTab extends JPanel {

	private static final long serialVersionUID = -713697782621311345L;
	JSpinner beginYear;
	JSpinner duration;
	JSpinner durationResearch;
	JSpinner durationDev;
	JSpinner durationPrep;
	JSpinner deltaT;

	public int getBeginYear() {
		return Integer.parseInt(beginYear.getValue().toString());
	}

	public int getDuration() {
		return Integer.parseInt(duration.getValue().toString());
	}

	public int getDurationResearch() {
		return Integer.parseInt(durationResearch.getValue().toString());
	}

	public int getDurationDev() {
		return Integer.parseInt(durationDev.getValue().toString());
	}

	public int getDurationPrep() {
		return Integer.parseInt(durationPrep.getValue().toString());
	}

	public int getDeltaT() {
		return Integer.parseInt(deltaT.getValue().toString());
	}

	public DurationTab() {
		setLayout(new MigLayout("", "[600][grow]", "[16px][][][][][]"));

		JLabel beginYearLabel = new JLabel(
				"\u041D\u0430\u0447\u0430\u043B\u044C\u043D\u044B\u0439 \u0433\u043E\u0434 \u0440\u0435\u0430\u043B\u0438\u0437\u0430\u0446\u0438\u0438 \u043F\u0440\u043E\u0435\u043A\u0442\u0430:");
		beginYearLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(beginYearLabel, "cell 0 0,alignx left,aligny center");

		beginYear = new JSpinner();
		setSpinnerDefaults(beginYear);
		beginYear.setModel(new SpinnerNumberModel(Main.Year, Main.Year, null, 1));
		add(beginYear, "cell 1 0");

		JLabel durationlabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u0433\u043E \u043F\u0435\u0440\u0438\u043E\u0434\u0430, \u043B\u0435\u0442");
		durationlabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(durationlabel, "cell 0 1");

		duration = new JSpinner();
		setSpinnerDefaults(duration);
		duration.setModel(new SpinnerNumberModel(0, null, null, 1));
		add(duration, "cell 1 1");

		JLabel durationResearchLabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u044D\u0442\u0430\u043F\u0430 \u041D\u0418\u0420, \u043B\u0435\u0442");
		durationResearchLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(durationResearchLabel, "cell 0 2");

		durationResearch = new JSpinner();
		setSpinnerDefaults(durationResearch);
		add(durationResearch, "cell 1 2");

		JLabel durationDevLabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u044D\u0442\u0430\u043F\u0430 \u041E\u041A\u0420, \u043B\u0435\u0442");
		durationDevLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(durationDevLabel, "cell 0 3");

		durationDev = new JSpinner();
		setSpinnerDefaults(durationDev);
		add(durationDev, "cell 1 3");

		JLabel durationPrepLabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u044D\u0442\u0430\u043F\u0430 \u043F\u043E\u0434\u0433\u043E\u0442\u043E\u0432\u043A\u0438 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430, \u043B\u0435\u0442");
		durationPrepLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(durationPrepLabel, "cell 0 4");

		durationPrep = new JSpinner();
		setSpinnerDefaults(durationPrep);
		add(durationPrep, "cell 1 4");

		JLabel deltaTLabel = new JLabel(
				"\u0412\u0440\u0435\u043C\u044F \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0435 \u043D\u0430 \u043E\u0441\u0432\u043E\u0435\u043D\u0438\u0435 \u043F\u0440\u0438\u043E\u0431\u0440\u0435\u0442\u0435\u043D\u043D\u044B\u0445 \u0444\u043E\u043D\u0434\u043E\u0432, \u043B\u0435\u0442 ");
		deltaTLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(deltaTLabel, "cell 0 5");

		deltaT = new JSpinner();
		setSpinnerDefaults(deltaT);
		deltaT.setModel(new SpinnerNumberModel(0, 0, null, 1));
		add(deltaT, "cell 1 5");

	}

	void setSpinnerDefaults(JSpinner spinner) {
		spinner.setMinimumSize(new Dimension(50, 28));
		spinner.setPreferredSize(new Dimension(150, 28));
		spinner.setMaximumSize(new Dimension(200, 28));
		spinner.setFont(new Font("Calibri", Font.PLAIN, 14));
	}

	void refresh(Project pr) {
		beginYear.setValue(pr.getBeginYear());
		duration.setValue(pr.getDuration());
		durationResearch.setValue(pr.getDurationResearch());
		durationDev.setValue(pr.getDurationDev());
		durationPrep.setValue(pr.getDurationPrep());
		deltaT.setValue(pr.getDeltaT());
	}
}
