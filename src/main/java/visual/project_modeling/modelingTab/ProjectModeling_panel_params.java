package visual.project_modeling.modelingTab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import org.jfree.util.Log;

import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.graf.FreeChartGraf;

public class ProjectModeling_panel_params extends JPanel {

	private static final long serialVersionUID = 3825199829665932028L;
	private FreeChartGraf graf;
	private JFormattedTextField effect1;
	private JFormattedTextField effect2;
	private JFormattedTextField effectSum;
	private JFormattedTextField efficiency;
	private JFormattedTextField costs;
	private JFormattedTextField income;
	private JFormattedTextField discount;
	private String font = "Calibri";
	JScrollPane statisticsResultScrollPane;
	private JTable statisticsResult;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	List<JRadioButton> radio;

	public ProjectModeling_panel_params() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(new Rectangle(0, 0, 1300, 750));
		setLayout(new MigLayout("", "[grow][]", "[193px][155px][400px,grow]"));
		JPanel paramsPanel = new JPanel();
		add(paramsPanel, "cell 0 0 2 1,growx,aligny top");
		paramsPanel.setLayout(new MigLayout("", "[][120][grow][][]", "[][][][][][][]"));

		JLabel effect1Label = new JLabel(
				"Экономический эффект внедрения технологии для отрасли-производителя");
		effect1Label.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(effect1Label, "cell 0 0,alignx left");

		effect1 = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(effect1, 14, 120, 28);
		paramsPanel.add(effect1, "cell 1 0,growx");

		JLabel costsLabel = new JLabel("Совокупные затраты отрасли-производителя в плановом периоде");
		costsLabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(costsLabel, "cell 3 0,alignx left");

		costs = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(costs, 14, 120, 28);
		paramsPanel.add(costs, "cell 4 0,growx");

		JLabel effect2Label = new JLabel("Экономический эффект внедрения технологии для отрасли-заказчика");
		effect2Label.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(effect2Label, "cell 0 1,alignx left");

		effect2 = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(effect2, 14, 120, 28);
		paramsPanel.add(effect2, "cell 1 1,growx");

		JLabel incomeLabel = new JLabel("Дополнительный доход отрасли-заказчика");
		incomeLabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(incomeLabel, "cell 3 1,alignx left");

		income = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(income, 14, 120, 28);
		paramsPanel.add(income, "cell 4 1,growx");

		JLabel effectSumLabel = new JLabel("Суммарный эффект от внедрения технологии");
		effectSumLabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(effectSumLabel, "cell 0 2,alignx left");

		effectSum = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(effectSum, 14, 120, 28);
		paramsPanel.add(effectSum, "cell 1 2,growx");

		JLabel discountlabel = new JLabel("Снижение затрат на производство продукции отрасли-заказчика");
		discountlabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(discountlabel, "cell 3 2,alignx left");

		discount = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(discount, 14, 120, 28);
		paramsPanel.add(discount, "cell 4 2,growx");

		JLabel efficiencyLabel = new JLabel("Эффективность внедрения технологии");
		efficiencyLabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(efficiencyLabel, "cell 0 3,alignx left");

		efficiency = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(efficiency, 14, 120, 28);
		paramsPanel.add(efficiency, "cell 1 3,growx");

		statisticsResultScrollPane = new JScrollPane();
		add(statisticsResultScrollPane, "cell 0 1 2 1,grow");

		setTable(null);

		graf = new FreeChartGraf(true, "0.00");
		add(graf, "cell 0 2,grow");

		JPanel radioPanel = new JPanel();
		add(radioPanel, "cell 1 2,alignx left,growy");
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));

		radio = new ArrayList<JRadioButton>();

		radio.add(new JRadioButton("Реальный объм выпуска новой продукции"));
		radio.add(new JRadioButton("<html>Потребность в кадрах, необходимых<br>для удовлетворения спроса"));
		radio.add(new JRadioButton("Количество заимствованных кадров"));
		radio.add(new JRadioButton("<html>Прирост объема производства<br>отрасли-заказчика"));

		for (int i = 0; i < 4; i++) {
			radio.get(i).setFont(new Font(font, Font.PLAIN, 14));
			radio.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					graf();
				}
			});
			buttonGroup.add(radio.get(i));
			radioPanel.add(radio.get(i));
		}
	}

	void graf() {
		double[][] xy;
		xy = new double[5][statisticsResult.getColumnCount() - 1];
		for (int j = 0; j < 4; j++) {
			if (radio.get(j).isSelected()) {
				for (int i = 1; i < statisticsResult.getColumnCount(); i++) {
					xy[0][i - 1] = 2012 + i;
					xy[j + 1][i - 1] = Format.getDouble(statisticsResult.getValueAt(j, i).toString());
				}
			} else {
				for (int i = 1; i < statisticsResult.getColumnCount(); i++) {
					xy[j + 1][i - 1] = -1;
				}
			}
		}

		String[] seriesNames = new String[] { "Реальный объем выпуска новейшей продукции",
				"Потребность в кадрах, необходимых для удовлетворения спроса",
				"Количество заимствованных кадров", "Прирос объма производства отрасли-заказчика" };
		graf.refresh(xy, seriesNames);
	}

	void setPanel(String projectName) {
		Log.info("Обновляем параметры - " + projectName);

		effect1.setText("");
		effect2.setText("");
		effectSum.setText("");
		efficiency.setText("");
		costs.setText("");
		income.setText("");
		discount.setText("");
		setTable(null);
	}

	void setTable(double[][] data) {

		statisticsResult = new JTable(4, 7);
		statisticsResult.setFont(new Font(font, Font.PLAIN, 14));
		statisticsResult.getColumnModel().getColumn(0).setMinWidth(343);
		statisticsResult.getColumnModel().getColumn(0).setMaxWidth(343);
		statisticsResult.getColumnModel().getColumn(0).setPreferredWidth(343);

		statisticsResult.getColumnModel().getColumn(0)
				.setHeaderValue("\u041F\u043E\u043A\u0430\u0437\u0430\u0442\u0435\u043B\u0438");
		for (int i = 1; i < statisticsResult.getColumnCount(); i++) {
			statisticsResult.getColumnModel().getColumn(i).setHeaderValue(2012 + i);
			statisticsResult.getColumnModel().getColumn(i).setMinWidth(50);
			statisticsResult.getColumnModel().getColumn(i).setMaxWidth(400);
			statisticsResult.getColumnModel().getColumn(i).setPreferredWidth(200);
		}
		statisticsResult.setRowHeight(25);

		statisticsResult
				.setValueAt(
						"\u0420\u0435\u0430\u043B\u044C\u043D\u044B\u0439 \u043E\u0431\u044A\u043C \u0432\u044B\u043F\u0443\u0441\u043A\u0430 \u043D\u043E\u0432\u0435\u0439\u0448\u0435\u0439 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438",
						0, 0);
		statisticsResult
				.setValueAt(
						"\u041F\u043E\u0442\u0440\u0435\u0431\u043D\u043E\u0441\u0442\u044C \u0432 \u043A\u0430\u0434\u0440\u0430\u0445, \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u044B\u0445 \u0434\u043B\u044F \u0443\u0434\u043E\u0432\u043B\u0435\u0442\u0432\u043E\u0440\u0435\u043D\u0438\u044F \u0441\u043F\u0440\u043E\u0441\u0430",
						1, 0);
		statisticsResult.setValueAt("Количество заимствованных кадров", 2, 0);
		statisticsResult
				.setValueAt(
						"\u041F\u0440\u0438\u0440\u043E\u0441\u0442 \u043E\u0431\u044A\u0435\u043C\u0430 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430 \u043E\u0442\u0440\u0430\u0441\u043B\u0438-\u0437\u0430\u043A\u0430\u0437\u0447\u0438\u043A\u0430",
						3, 0);

		for (int i = 1; i < statisticsResult.getColumnCount(); i++) {
			statisticsResult.setValueAt("output", 0, i);
			statisticsResult.setValueAt("staff", 1, i);
			statisticsResult.setValueAt("staffBorrowed", 2, 1);
			statisticsResult.setValueAt("outputChange", 3, 1);
			try {
				for (int j = 0; j < 4; j++) {
					statisticsResult.setValueAt(data[j][i - 1], j, i);
				}
			} catch (Exception e) {
				Log.error(e.getMessage(), e);
			}
		}

		for (int i = 0; i < statisticsResult.getRowCount(); i++) {
			for (int j = 1; j < statisticsResult.getColumnCount(); j++) {
				statisticsResult.setValueAt("", i, j);
			}
		}
		statisticsResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		statisticsResult.getTableHeader().resizeAndRepaint();
		statisticsResult.setFillsViewportHeight(false);
		statisticsResult.revalidate();
		statisticsResult.repaint();
		statisticsResultScrollPane.setViewportView(statisticsResult);
	}

	public void refresh(double effect1Value, double effect2Value, double effectSumValue,
			double efficiencyValue, double costsValue, double incomeValue, double discountValue,
			double[][] statisticsResult) {
		effect1.setText(String.valueOf(effect1Value));
		effect2.setText(String.valueOf(effect2Value));
		effectSum.setText(String.valueOf(effectSumValue));
		efficiency.setText(String.valueOf(efficiencyValue));
		costs.setText(String.valueOf(costsValue));
		income.setText(String.valueOf(incomeValue));
		discount.setText(String.valueOf(discountValue));
		setTable(statisticsResult);
	}
}
