package visual.project_modeling.projectBaseTab;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import mathModel.Statistics;
import mathModel.project.Project;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;
import visual.frames.main.Main;
import visual.tableBuilders.TableInOneRow;

import javax.swing.ScrollPaneConstants;

public class UsingTab extends JPanel {

	private static final long serialVersionUID = 8104390766046862317L;

	private JFormattedTextField productivity;
	private NumberOrButton laborContent;
	JScrollPane varCostScrollPane;
	JScrollPane loadScrollPane;
	private JTable load;
	private JTable varCost;
	private String font = "Calibri";

	public Statistics getLaborContent() {
		return laborContent.getStatistics();
	}

	public double getProductivity() {
		return Format.getDouble(productivity.getText());
	}

	public Statistics getLoad() {
		Statistics loadValue = new Statistics();
		for (int i = 0; i < load.getColumnCount(); i++) {
			loadValue.add(Integer.parseInt(load.getColumnModel().getColumn(i).getHeaderValue().toString()),
					Format.getDouble(load.getValueAt(0, i).toString()));
		}
		return loadValue;
	}

	public Statistics getVarCost() {
		Statistics varCostValue = new Statistics();
		for (int i = 0; i < varCost.getColumnCount(); i++) {
			varCostValue.add(
					Integer.parseInt(varCost.getColumnModel().getColumn(i).getHeaderValue().toString()),
					Format.getDouble(varCost.getValueAt(0, i).toString()));
		}
		return varCostValue;
	}

	public UsingTab() {
		setLayout(new MigLayout("", "[600][grow]", "[][][50][][50][]"));

		JLabel productivityLabel = new JLabel("Производительность новых фондов, усл.ед.продукции");
		productivityLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(productivityLabel, "cell 0 0,alignx left");

		productivity = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(productivity, 14, 150, 28);
		add(productivity, "cell 1 0,alignx left");

		JLabel loadLabel = new JLabel("Степень загруженности новых фондов");
		loadLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(loadLabel, "cell 0 1");

		loadScrollPane = new JScrollPane();
		loadScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(loadScrollPane, "cell 0 2 2 1,grow");

		JLabel varCostLabel = new JLabel(
				"Переменные затраты на производство единицы продукции с использованием новых фондов, ден.ед.");
		add(varCostLabel, "cell 0 3");

		varCostScrollPane = new JScrollPane();
		varCostScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(varCostScrollPane, "cell 0 4 2 1,grow");

		JLabel laborContentLabel = new JLabel(
				"Трудоемкость производства единицы продукции с использованием новых фондов, чел/ед.пр.");
		laborContentLabel.setFont(new Font(font, Font.PLAIN, 14));
		laborContentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(laborContentLabel, "cell 0 5,alignx left");

		laborContent = new NumberOrButton(
				"Трудоемкость производства единицы продукции с использованием новых фондов, чел/ед.пр.",
				false);
		add(laborContent, "flowx,cell 1 5,alignx left");

		setLoad(null);
		setVarCost(null);
	}

	void refresh(Project pr) {
		productivity.setText(String.valueOf(pr.getProductivity()));
		setLoad(pr.getLoadCoef().getStatisticsData());
		setVarCost(pr.getVarCostNew().getStatisticsData());
		laborContent.setValue(pr.getLaborContent().getStatisticsData());
	}

	public void setVarCost(double[][] data) {
		varCost = TableInOneRow.setTable(data, Main.Year + 1, Main.ModelLenght);
		varCostScrollPane.setViewportView(varCost);
	}

	public void setLoad(double[][] data) {
		load = TableInOneRow.setTable(data, Main.Year + 1, Main.ModelLenght);
		loadScrollPane.setViewportView(load);
	}
}
