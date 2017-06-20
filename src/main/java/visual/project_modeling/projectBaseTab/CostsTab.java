package visual.project_modeling.projectBaseTab;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mathModel.Statistics;
import mathModel.project.Project;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;
import visual.frames.main.Main;
import visual.tableBuilders.TableInOneRow;

public class CostsTab extends JPanel {

	private static final long serialVersionUID = 5773916234852210859L;
	private JFormattedTextField costResearch;
	private JFormattedTextField costDev;
	private JFormattedTextField cost;
	private NumberOrButton totalCost;
	private NumberOrButton powerCost;
	private String font = "Calibri";
	JScrollPane investmentsScrollPane;
	private JTable investments;

	public double getProjectCostResearch() {
		return Format.getDouble(costResearch.getText());
	}

	public double getProjectCostDev() {
		return Format.getDouble(costDev.getText());
	}

	public double getProjectCost() {
		return Format.getDouble(cost.getText());
	}

	public Statistics getTotalCost() {
		return totalCost.getStatistics();
	}

	public Statistics getPowerCost() {
		return powerCost.getStatistics();
	}

	public Statistics getInvestments() {
		double[] data = getInvestmentsData();
		Statistics dat = new Statistics();
		for (int i = 0; i < data.length; i++) {
			dat.add(Main.Year + i, data[i]);
		}
		return dat;
	}

	public CostsTab() {
		setLayout(new MigLayout("", "[600,grow][240]", "[][][][][][][][grow]"));

		JLabel costResearchLabel = new JLabel("Величина затрат на НИР, ден.ед.");
		costResearchLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(costResearchLabel, "cell 0 0,alignx left");

		costResearch = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(costResearch, 14, 200, 28);
		add(costResearch, "cell 1 0,alignx right");

		JLabel costDevLabel = new JLabel("Величина затрат на ОКР, ден.ед.");
		costDevLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(costDevLabel, "cell 0 1,alignx left");

		costDev = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(costDev, 14, 200, 28);
		add(costDev, "cell 1 1,alignx right");

		JLabel costLabel = new JLabel("Единовременные затраты на создание производства, ден.ед.");
		costLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(costLabel, "cell 0 2,alignx left");

		cost = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(cost, 14, 200, 28);
		add(cost, "cell 1 2,alignx right");

		JLabel totalCostLabel = new JLabel(
				"Величина полных затрат для производства единицы продукции, ден.ед.");
		totalCostLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(totalCostLabel, "cell 0 4,alignx left");

		totalCost = new NumberOrButton("Величина полных затрат для производства единицы продукции, ден.ед.",
				false);
		totalCost.setMinimumSize(new Dimension(200, 28));
		totalCost.setMaximumSize(new Dimension(200, 28));
		totalCost.setPreferredSize(new Dimension(200, 28));
		add(totalCost, "flowx,cell 1 4,alignx right");

		JLabel powerCostLabel = new JLabel(
				"Объем денежных средств, необходимых для увеличения производственной мощности на одну единицу");
		powerCostLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(powerCostLabel, "cell 0 5,alignx left");

		powerCost = new NumberOrButton(
				"Объем денежных средств, необходимых для увеличения производственной мощности на одну единицу",
				false);
		powerCost.setMinimumSize(new Dimension(200, 28));
		powerCost.setMaximumSize(new Dimension(200, 28));
		powerCost.setPreferredSize(new Dimension(200, 28));
		add(powerCost, "flowx,cell 1 5,alignx right");

		JLabel investmentsLabel = new JLabel("Планируемые инвестиции в производственные мощности, ден.ед.");
		investmentsLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(investmentsLabel, "cell 0 6");

		investmentsScrollPane = new JScrollPane();
		investmentsScrollPane.setMaximumSize(new Dimension(32767, 47));
		investmentsScrollPane.setFont(new Font(font, Font.PLAIN, 14));
		investmentsScrollPane.setBorder(null);
		add(investmentsScrollPane, "cell 0 7 2 1,grow");

		setInvestments(null);
	}

	public double[] getInvestmentsData() {
		double[] data = new double[investments.getColumnCount()];
		for (int j = 0; j < investments.getColumnCount(); j++) {
			data[j] = Format.getDouble(investments.getValueAt(0, j).toString());
		}
		return data;
	}

	public void setInvestments(double[][] data) {
		investments = TableInOneRow.setTable(data, Main.Year + 1, Main.ModelLenght);
		investmentsScrollPane.setViewportView(investments);
	}

	void refresh(Project pr) {
		costResearch.setText(String.valueOf(pr.getCostResearch()));
		costDev.setText(String.valueOf(pr.getCostDev()));
		cost.setText(String.valueOf(pr.getCost()));
		totalCost.setValue(pr.getTotalCost().getStatisticsData());
		powerCost.setValue(pr.getPowerCost().getStatisticsData());
		setInvestments(pr.getInvestments().getStatisticsData());
	}
}
