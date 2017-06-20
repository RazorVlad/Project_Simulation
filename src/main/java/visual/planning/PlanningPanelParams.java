package visual.planning;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

import mathModel.ProjectStorage;
import mathModel.Statistics;
import mathModel.project.Project;
import mathModel.variantsResolver.Variant;
import mathModel.variantsResolver.VariantsResolver;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

public class PlanningPanelParams extends JPanel {

	private static final long serialVersionUID = 6176515938815294595L;
	private static Logger LOG = Logger.getLogger(PlanningPanelParams.class);
	private JComboBox branchComboBox;
	private JCheckBox criteriaIncome;
	private JCheckBox criteriaOutcome;
	private JCheckBox criteriaInnov;
	private ModelParamsPanel modelParams;
	private JTabbedPane tabbedPane;
	private JPanel interrelation;
	private ResultPanel resultPanel;
	private JLabel deltaLabel;
	private JSpinner delta;
	private JButton setDeltaBtn;
	private JRadioButton radioBefore;
	private JRadioButton radioNotAfter;
	private JPanel panel;
	private Before_notAfter_table tableBefore;
	private Before_notAfter_table tableNotAfter;
	private JTable before;
	private JTable notAfter;
	private int m;
	int max = 1;// количество выбранных проектов
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> chosenProjects;
	List<Variant> vars;

	private VariantsResolver vr;

	public VariantsResolver getVR() {
		return vr;
	}

	public void setMax(int max) {
		// this.max = max;
		delta.setModel(new SpinnerNumberModel(1, 1, max, 1));
	}

	public void setChosenProjects(List<String> projects) {
		this.chosenProjects = projects;
	}

	public PlanningPanelParams() {
		setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		setBounds(new Rectangle(0, 0, 1300, 750));

		setLayout(new BorderLayout(0, 0));
		JPanel paramsPanel = new JPanel();
		add(paramsPanel, BorderLayout.NORTH);
		paramsPanel.setLayout(new MigLayout("", "[227.00][120,grow]", "[][][][]"));

		JLabel label_1 = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u043E\u043F\u0442\u0438\u043C\u0438\u0437\u0438\u0440\u0443\u0435\u043C\u0443\u044E \u043E\u0442\u0440\u0430\u0441\u043B\u044C");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		paramsPanel.add(label_1, "cell 0 0,alignx left");

		modelParams = new ModelParamsPanel();

		branchComboBox = new JComboBox();
		branchComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int k = branchComboBox.getSelectedIndex();
				modelParams.setVedComboBox(k);
				modelParams.setGovorder(new Statistics[mathModel.VedNames.getVeds(k).length]);
				modelParams.setLastVedIndex(0);
			}
		});
		branchComboBox.setMinimumSize(new Dimension(33, 28));
		branchComboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		branchComboBox.setPreferredSize(new Dimension(33, 28));
		branchComboBox
				.setModel(new DefaultComboBoxModel(
						new String[] {
								"\u041F\u0438\u0449\u0435\u0432\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u041B\u0435\u0433\u043A\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u0414\u0435\u0440\u0435\u0432\u043E\u043E\u0431\u0440\u0430\u0431\u0430\u0442\u044B\u0432\u0430\u044E\u0449\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u0426\u0435\u043B\u043B\u044E\u043B\u043E\u0437\u043D\u043E-\u0431\u0443\u043C\u0430\u0436\u043D\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u041D\u0435\u0444\u0442\u0435\u043F\u0435\u0440\u0435\u0440\u0430\u0431\u0430\u0442\u044B\u0432\u0430\u044E\u0449\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u0425\u0438\u043C\u0438\u0447\u0435\u0441\u043A\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C",
								"\u041F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C \u0441\u0442\u0440\u043E\u0439\u043C\u0430\u0442\u0435\u0440\u0438\u0430\u043B\u043E\u0432",
								"\u041C\u0435\u0442\u0430\u043B\u043B\u0443\u0440\u0433\u0438\u044F",
								"\u041C\u0430\u0448\u0438\u043D\u043E\u0441\u0442\u0440\u043E\u0435\u043D\u0438\u0435",
								"\u042D\u043B\u0435\u043A\u0442\u0440\u043E\u044D\u043D\u0435\u0440\u0433\u0435\u0442\u0438\u043A\u0430" }));
		branchComboBox.setMaximumSize(new Dimension(300, 28));
		paramsPanel.add(branchComboBox, "cell 1 0,growx");

		JLabel label2 = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u043A\u0440\u0438\u0442\u0435\u0440\u0438\u0439 \u043E\u043F\u0442\u0438\u043C\u0438\u0437\u0430\u0446\u0438\u0438");
		label2.setFont(new Font("Calibri", Font.PLAIN, 14));
		paramsPanel.add(label2, "cell 0 1,alignx left");

		criteriaIncome = new JCheckBox(
				"\u0421\u043E\u0432\u043E\u043A\u0443\u043F\u043D\u0430\u044F \u043F\u0440\u0438\u0431\u044B\u043B\u044C \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u0438 \u043E\u0442 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 \u0438 \u0440\u0435\u0430\u043B\u0438\u0437\u0430\u0446\u0438\u0438 \u0440\u044F\u0434\u043E\u0432\u043E\u0439 \u0438 \u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u043E\u0439 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		criteriaIncome.setFont(new Font("Calibri", Font.PLAIN, 14));
		paramsPanel.add(criteriaIncome, "cell 1 1");

		criteriaOutcome = new JCheckBox(
				"\u041E\u0431\u0449\u0438\u0435 \u0437\u0430\u0442\u0440\u0430\u0442\u044B \u043D\u0430 \u0444\u0446\u043D\u043A\u0446\u0438\u043E\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u0438 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u0435 \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		criteriaOutcome.setFont(new Font("Calibri", Font.PLAIN, 14));
		paramsPanel.add(criteriaOutcome, "cell 1 2");

		criteriaInnov = new JCheckBox(
				"\u0421\u043E\u0432\u043E\u043A\u0443\u0430\u043D\u044B\u0439 \u043E\u0431\u044A\u0435\u043C \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 \u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u043E\u0439 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438 \u043E\u0442\u0440\u0430\u0441\u043B\u044C\u044E \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		criteriaInnov.setFont(new Font("Calibri", Font.PLAIN, 14));
		paramsPanel.add(criteriaInnov, "cell 1 3");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		tabbedPane
				.addTab("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u043C\u043E\u0434\u0435\u043B\u0438",
						null, modelParams, null);

		interrelation = new JPanel();
		interrelation.setMinimumSize(new Dimension(1000, 300));
		tabbedPane
				.addTab("\u0412\u0437\u0430\u0438\u043C\u043E\u0441\u0432\u044F\u0437\u044C \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u043E\u0432",
						null, interrelation, null);
		interrelation.setLayout(new MigLayout("", "[][][grow]", "[][][grow]"));

		deltaLabel = new JLabel(
				"\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0432\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043F\u0440\u043E\u0435\u043A\u0442\u043E\u0432, \u0438\u0437 \u043A\u043E\u0442\u043E\u0440\u044B\u0445 \u043C\u043E\u0436\u0435\u0442 \u0444\u043E\u0440\u043C\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F \u0432\u0430\u0440\u0438\u0430\u043D\u0442 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F");
		interrelation.add(deltaLabel, "cell 0 0");

		delta = new JSpinner();
		delta.setFont(new Font("Calibri", Font.PLAIN, 14));
		delta.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		interrelation.add(delta, "cell 1 0");

		setDeltaBtn = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		setDeltaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Project> projects = new ArrayList<Project>();

				for (String chosenProject : chosenProjects) {
					projects.add(ProjectStorage.getInstance().getProject(chosenProject));
				}
				// TODO Vlad вытащить значения нужного типа из элементов
				// визуалки
				double f0 = modelParams.getF0();
				// F0-Величина фонда развития отрасли в начале планового
				// периода, ден.ед.?
				Statistics f = modelParams.getTable3();
				// F-средства выделяемые на развитие отрасли в плановом периоде,
				// ден.ед.?
				int lastT = modelParams.getLastT();
				int delt = Integer.parseInt(delta.getValue().toString());
				int maxDur = modelParams.getMaxDuration();
				int dur = modelParams.getDuration();
				int aimYearr = modelParams.getAimYear();
				double costBeforre = modelParams.getCostBefore();
				double amountBeforre = modelParams.getAmountBefore();
				double varCostBeforre = modelParams.getVarCostBefore();
				int otrasl = branchComboBox.getSelectedIndex();
				int[] vedIndex = mathModel.VedNames.getVeds(otrasl);
				List<Integer> vedIndexes = new ArrayList<Integer>();
				for (int index : vedIndex) {
					vedIndexes.add(index);
				}

				vr = new VariantsResolver(delt, maxDur, dur, lastT, aimYearr, modelParams.getEn(), projects,
						costBeforre, amountBeforre, varCostBeforre, f0, f, vedIndexes);

				vr.generateVariants();
				vars = vr.getVariants();
				int m = vr.m(); // - количество вариантов
				tableBefore.setTable(5);
				tableNotAfter.setTable(5);
			}
		});
		setDeltaBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
		setDeltaBtn.setPreferredSize(new Dimension(120, 28));
		interrelation.add(setDeltaBtn, "cell 2 0,alignx left");

		radioBefore = new JRadioButton(
				"\u041C\u043D\u043E\u0436\u0435\u0441\u0442\u0432\u043E \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u043E\u0432 \u0440\u0430\u0437\u0438\u0442\u0438\u044F, \u043A\u043E\u0442\u043E\u0440\u044B\u0435 \u0434\u043E\u043B\u0436\u043D\u044B \u0431\u044B\u0442\u044C \u0440\u0435\u0430\u043B\u0438\u0437\u043E\u0432\u0430\u043D\u044B \u0434\u043E \u0432\u043D\u0435\u0434\u0440\u0435\u043D\u0438\u044F \u044D\u0442\u043E\u0433\u043E \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u0430 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F");
		radioBefore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableBefore.setVisible(true);
				tableNotAfter.setVisible(false);
			}
		});
		radioBefore.setSelected(true);
		buttonGroup.add(radioBefore);
		interrelation.add(radioBefore, "flowx,cell 0 1 3 1");

		radioNotAfter = new JRadioButton(
				"\u041C\u043D\u043E\u0436\u0435\u0441\u0442\u0432\u043E \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u043E\u0432 \u0440\u0430\u0437\u0438\u0442\u0438\u044F, \u043F\u043E\u0441\u043B\u0435 \u043A\u043E\u0442\u043E\u0440\u044B\u0445 \u0434\u0430\u043D\u043D\u044B\u0439 \u0432\u0430\u0440\u0438\u0430\u043D\u0442 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F \u043D\u0435 \u043C\u043E\u0436\u0435\u0442 \u0431\u044B\u0442\u044C \u0440\u0435\u0430\u043B\u0438\u0437\u043E\u0432\u0430\u043D");
		radioNotAfter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableBefore.setVisible(false);
				tableNotAfter.setVisible(true);
			}
		});
		buttonGroup.add(radioNotAfter);
		interrelation.add(radioNotAfter, "cell 0 1 3 1");

		panel = new JPanel();
		interrelation.add(panel, "cell 0 2 3 1,grow");
		panel.setLayout(new CardLayout(0, 0));

		tableBefore = new Before_notAfter_table(vars);
		panel.add(tableBefore, "name_374695525389920");

		tableNotAfter = new Before_notAfter_table(vars);
		panel.add(tableNotAfter, "name_374698959245829");

		resultPanel = new ResultPanel();
		add(resultPanel, BorderLayout.SOUTH);
	}

	void setPanel(String projectName) {
		System.out.println("Обновляем параметры - " + projectName);
		tableNotAfter.setTable(0);
		tableBefore.setTable(0);
	}

	Statistics getGovOrder() {
		return modelParams.getGovOrder();
	}

	void setResult() {
		double[] result = { 7, 8, 9 };
		int modelYear = modelParams.getAimYear();
		int columnCount = modelParams.getDuration() + 1;
		double criteriaIncome = 0;
		double criteriaOutcome = 0;
		double criteriaInnov = 0;
		resultPanel.SetResult(result, modelYear, columnCount, criteriaIncome, criteriaOutcome, criteriaInnov);
	}

	List<Integer> getBeforeVariants(int index) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < before.getRowCount(); i++) {
			if (Boolean.parseBoolean(before.getValueAt(i, index).toString())) {
				data.add(i);
			}
		}
		return data;
	}

	List<Integer> getNotAfterVariants(int index) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < notAfter.getRowCount(); i++) {
			if (Boolean.parseBoolean(notAfter.getValueAt(i, index).toString())) {
				data.add(i);
			}
		}
		return data;
	}

	void showProjectsInVariant(JTable table) {
		StringBuilder projects = new StringBuilder("<html>");
		String selected = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
		try {
			Variant selectedVariant = vars.get(table.getSelectedRow());
			List<Project> projectsInVariant = selectedVariant.getProjects();
			for (Project i : projectsInVariant) {
				projects.append(i.getName()).append("<br>");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		JOptionPane.showMessageDialog(null, projects.toString(), selected, JOptionPane.PLAIN_MESSAGE);
	}
}
