package visual.frames.spros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;
import visual.save.Spros_save;
import visual.save.VisualObjectsContainer;
import visual.tableBuilders.TableWithRowHeaders;

public class SprosPanel extends JPanel {

	private static final long serialVersionUID = 516778797777360254L;
	private static Logger LOG = Logger.getLogger(SprosPanel.class);
	private FreeChartGraf graf;
	private String font="Calibri";
	JPanel panel1;
	private JTable resultTable;
	private JTable sprosTable;
	double[][] xy;
	JScrollPane sprosScrollPane;
	int vedIndex;
	JScrollPane resultScrollPane;
	List<JCheckBox> checkBoxes;
	Box verticalBox;

	double[] yiord;
	double[] yiinn;
	double[] yx3;
	double[] export;
	double[] yxInn;
	double[] yxOrd;
	double[] sovSpros;

	public double[] getYxOrd() {
		return yxOrd;
	}

	public double[] getYxInn() {
		return yxInn;
	}

	public double[] getExport() {
		return export;
	}

	public double[] getYx3() {
		return yx3;
	}

	public double[] getYiord() {
		return yiord;
	}

	public double[] getYiinn() {
		return yiinn;
	}

	public SprosPanel(int vedIndex) {

		this.vedIndex = vedIndex;
		setLayout(new BorderLayout(0, 0));

		checkBoxes = new ArrayList<JCheckBox>();
		checkBoxes.add(new JCheckBox("\u0421\u043E\u0432\u043E\u043A\u0443\u043F\u043D\u044B\u0439"));
		checkBoxes.add(new JCheckBox("\u0420\u044F\u0434\u043E\u0432\u0430\u044F"));
		checkBoxes.add(new JCheckBox(
				"\u0418\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u0430\u044F"));
		checkBoxes.add(new JCheckBox(
				"<html>\u0418\u043C\u043F\u043E\u0440\u0442<br>\u0440\u044F\u0434\u043E\u0432\u043E\u0439"));
		checkBoxes
				.add(new JCheckBox(
						"<html>\u0418\u043C\u043F\u043E\u0440\u0442<br>\u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u044B\u0439"));

		panel1 = new JPanel();
		add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout(0, 0));

		resultScrollPane = new JScrollPane();
		resultScrollPane.setPreferredSize(new Dimension(500, 80));
		resultScrollPane.setMaximumSize(new Dimension(2000, 35));
		panel1.add(resultScrollPane, BorderLayout.SOUTH);

		verticalBox = Box.createVerticalBox();
		verticalBox.setMinimumSize(new Dimension(100, 200));
		panel1.add(verticalBox, BorderLayout.EAST);

		for (int i = 0; i < 5; i++) {
			setCheckBox(checkBoxes.get(i));
		}

		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(10, 251));
		panel3.setMinimumSize(new Dimension(10, 100));
		panel1.add(panel3, BorderLayout.NORTH);
		panel3.setLayout(new MigLayout("", "[grow]", "[14px][215]"));

		JLabel lblNewLabel = new JLabel("Задайте величину конечного спроса на продукцию данного ВЭДа");
		lblNewLabel.setFont(new Font(font, Font.PLAIN, 14));
		panel3.add(lblNewLabel, "cell 0 0,alignx center,aligny top");

		sprosScrollPane = new JScrollPane();
		sprosScrollPane.setMinimumSize(new Dimension(23, 90));
		panel3.add(sprosScrollPane, "cell 0 1,grow");

		graf = new FreeChartGraf(true, "0.00");
		graf.setMinimumSize(new Dimension(10, 200));
		panel1.add(graf, BorderLayout.CENTER);

		setSprosTable();
		setResultTable();
		refresh();
	}

	void sprosTableProcessing() {
		int percentType = getPercentType();
		int yearCount = sprosTable.getColumnCount() - 1;
		export = new double[yearCount];
		yiord = new double[yearCount];
		yiinn = new double[yearCount];
		yx3 = new double[yearCount];
		sovSpros = new double[yearCount];
		yxInn = new double[yearCount];
		yxOrd = new double[yearCount];
		for (int i = 1; i <= yearCount; i++) {
			try {
				double exportValue = Format.getDouble(sprosTable.getValueAt(3, i).toString());
				export[i - 1] = exportValue;
				double yiordValue = Format.getDouble(sprosTable.getValueAt(4, i).toString());
				yiord[i - 1] = yiordValue;
				double yiinnValue = Format.getDouble(sprosTable.getValueAt(5, i).toString());
				yiinn[i - 1] = yiinnValue;

				double spros = Format.getDouble(sprosTable.getValueAt(0, i).toString())
						+ Format.getDouble(sprosTable.getValueAt(1, i).toString())
						+ Format.getDouble(sprosTable.getValueAt(2, i).toString());

				double sovSprosValue = spros + exportValue;
				sovSpros[i - 1] = sovSprosValue;

				double value = Format.getDouble(sprosTable.getValueAt(6, i).toString());

				yx3[i - 1] = (spros * (1 - value / percentType));

				double yxOrdValue = sovSprosValue * (1 - value / percentType);
				double yxInnValue = sovSprosValue * (value / percentType);

				resultTable.setValueAt(yxOrdValue, 0, i);
				resultTable.setValueAt(yxInnValue, 1, i);

				yxOrd[i - 1] = yxOrdValue;
				yxInn[i - 1] = yxInnValue;

			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				resultTable.setValueAt(0, 0, i);
				resultTable.setValueAt(0, 1, i);
			}
		}
	}

	void graf() {

		sprosTableProcessing();
		xy = new double[6][sprosTable.getColumnCount() - 1];
		for (int i = 1; i < sprosTable.getColumnCount(); i++) {
			try {
				xy[0][i - 1] = Main.Year + i;// координаты иксов

				if (checkBoxes.get(0).isSelected()) {
					xy[1][i - 1] = (sovSpros[i - 1]);// совокупный спрос
				} else {
					xy[1][i - 1] = -1;
				}
				if (checkBoxes.get(1).isSelected()) {
					xy[2][i - 1] = yxOrd[i - 1];// Спрос на рядовую продукцию
				} else {
					xy[2][i - 1] = -1;
				}

				if (checkBoxes.get(2).isSelected()) {
					xy[3][i - 1] = yxInn[i - 1];// Спрос на инн продукцию
				} else {
					xy[3][i - 1] = -1;
				}

				if (checkBoxes.get(3).isSelected()) {
					xy[4][i - 1] = yiord[i - 1];// Спрос на рядовой импорт
				} else {
					xy[4][i - 1] = -1;
				}

				if (checkBoxes.get(4).isSelected()) {
					xy[5][i - 1] = yiinn[i - 1];// Спрос на инновационный импорт
				} else {
					xy[5][i - 1] = -1;
				}

			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		String[] seriesNames = new String[] { "совокупный спрос", "Спрос на рядовую продукцию",
				"Спрос на инновационную продукцию", "Спрос на рядовой импорт",
				"Спрос на инновационный импорт" };
		graf.refresh(xy, seriesNames);
	}

	void save() {
		Spros_save save = VisualObjectsContainer.getInstance().getSprosMap(vedIndex);
		save.setSpros(getTableData());
	}

	void setTableData(double[][] data) {
		if (data != null) {
			for (int j = 0; j < data.length; j++) {
				for (int i = 0; i < data[0].length; i++) {
					try {
						sprosTable.getModel().setValueAt(data[j][i], j, i + 1);
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
						sprosTable.getModel().setValueAt("", j, i + 1);
					}
				}
			}
			sprosTableProcessing();
		}
	}

	double[][] getTableData() {
		double[][] data = new double[sprosTable.getRowCount()][sprosTable.getColumnCount() - 1];
		for (int j = 0; j < sprosTable.getRowCount(); j++) {
			for (int i = 1; i < sprosTable.getColumnCount(); i++) {
				try {
					data[j][i - 1] = Format.getDouble(sprosTable.getValueAt(j, i).toString());
				} catch (Exception e) {
					data[j][i - 1] = 0;
					LOG.error(e.getMessage(), e);
				}
			}
		}

		return data;
	}

	void refresh() {
		Spros_save save = VisualObjectsContainer.getInstance().getSprosMap(vedIndex);
		setTableData(save.getSpros());
	}

	void setResultTable() {
		String[] seriesNames = new String[] { "Совокупный спрос на рядовую продукцию, тыс.грн.",
				"Совокупный спрос на инновационную продукцию, тыс.грн." };
		resultTable = TableWithRowHeaders.setTable(null, Main.Year + 1, Main.ModelLenght + 1, seriesNames);
		resultTable.getColumnModel().getColumn(0).setHeaderValue("Показатели\\Года");
		resultScrollPane.setViewportView(resultTable);
	}

	void setCheckBox(JCheckBox checkBox) {
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				graf();
			}
		});
		checkBox.setBorder(null);
		checkBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		verticalBox.add(checkBox);
	}

	void setSprosTable() {
		String[] seriesNames = new String[] { "Потребление продукции домашними хозяйствами, тыс.грн.",
				"Потребление продукции органами государственной власти, тыс.грн.",
				"Валовое накопление капитала, усл.ед.", "Экспорт продукции, тыс.грн.",
				"Спрос на импортную рядовую продукцию, тыс.грн.",
				"Спрос на импортную инновационную продукцию, тыс.грн.",
				"Доля спроса на инновационную продукцию" };
		sprosTable = TableWithRowHeaders.setTable(null, Main.Year + 1, Main.ModelLenght + 1, seriesNames);
		sprosTable.getColumnModel().getColumn(0).setHeaderValue("Показатели\\Года");
		sprosScrollPane.setViewportView(sprosTable);
	}

	int getPercentType() {
		int percentType = 1;
		for (int i = 1; i < resultTable.getColumnCount(); i++) {
			try {
				double value = Format.getDouble(sprosTable.getValueAt(6, i).toString());
				if ((value) > 1) {
					percentType = 100;
				}
				if (value > 100) {
					sprosTable.setValueAt("100", 1, i);
					LOG.debug("Доля > 100%");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return percentType;
	}

}
