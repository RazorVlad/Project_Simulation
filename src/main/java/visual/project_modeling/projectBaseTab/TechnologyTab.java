package visual.project_modeling.projectBaseTab;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import mathModel.Statistics;
import mathModel.project.Project;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;
import visual.frames.main.Main;
import visual.tableBuilders.TableInOneRow;

public class TechnologyTab extends JPanel {

	private static final long serialVersionUID = -5181051800017032950L;

	private JFormattedTextField power0;
	private JFormattedTextField staff;
	private NumberOrButton price;
	JScrollPane demandScrollPane;
	JComboBox switcher1;
	JComboBox switcher2;
	JLabel label30;
	private JTable fixed;
	private JTable demand;
	private JFormattedTextField k;
	private String font = "Calibri";
	JScrollPane fixedScrollPane;

	public double getPower0() {
		return Format.getDouble(power0.getText());
	}

	public double getStaff0() {
		return Format.getDouble(staff.getText());
	}

	public Statistics getProjectPrice() {
		return price.getStatistics();
	}

	public double getK() {
		return Format.getDouble(k.getText());
	}

	public Statistics getFixed() {
		Statistics Fixed = new Statistics();
		for (int i = 0; i < fixed.getColumnCount(); i++) {
			Fixed.add(Integer.parseInt(fixed.getColumnModel().getColumn(i).getHeaderValue().toString()),
					Format.getDouble(fixed.getValueAt(0, i).toString()));
		}
		return Fixed;
	}

	public Statistics getDemand() {
		double[] data = getDemandData();
		Statistics dat = new Statistics();
		for (int i = 0; i < data.length; i++) {
			dat.add(Main.Year + i, data[i]);
		}
		return dat;
	}

	public TechnologyTab() {
		setLayout(new MigLayout("", "[705.00,grow][]", "[][][][][50][][][50]"));

		JLabel power0Label = new JLabel(
				"����������� �������� ������������ ������������� ������������, ��.���������");
		power0Label.setFont(new Font(font, Font.PLAIN, 14));
		add(power0Label, "cell 0 0,alignx left");

		power0 = new JFormattedTextField();
		Format.setFieldDefaults(power0, 14, 200, 28);
		add(power0, "cell 1 0,alignx right");

		JLabel staffLabel = new JLabel("���������� ������, ����������� ��� ������������ ������� ���������");
		staffLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(staffLabel, "cell 0 1,alignx left");

		staff = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(staff, 14, 200, 28);
		add(staff, "cell 1 1,alignx right");

		JLabel priceLabel = new JLabel("��������� ������� ���������, ���.��.");
		priceLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(priceLabel, "cell 0 2,alignx left");

		price = new NumberOrButton("��������� ������� ���������, ���.��.", false);
		price.setMinimumSize(new Dimension(200, 28));
		price.setMaximumSize(new Dimension(200, 28));
		add(price, "flowx,cell 1 2,alignx right");

		JLabel demandLabel = new JLabel("����� �� ������������ ��������� , ���.��");
		demandLabel.setFont(new Font(font, Font.PLAIN, 14));
		add(demandLabel, "cell 0 3");

		demandScrollPane = new JScrollPane();
		demandScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(demandScrollPane, "cell 0 4 2 1,grow");

		JLabel lblNewLabel2 = new JLabel("��������� ����� ���������������� ������ �������� � �������:");
		lblNewLabel2.setFont(new Font(font, Font.PLAIN, 14));
		add(lblNewLabel2, "cell 0 5,alignx left");

		switcher1 = new JComboBox();
		switcher1.setFont(new Font(font, Font.PLAIN, 14));
		switcher1.setMaximumSize(new Dimension(400, 28));
		switcher1.setPreferredSize(new Dimension(33, 28));
		switcher1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				boolean switcher1SI2 = (switcher1.getSelectedIndex() == 2);
				switcher2.setEnabled(switcher1SI2);
				label30.setEnabled(switcher1SI2);
				k.setEnabled(switcher1SI2);
			}
		});
		switcher1
				.setModel(new DefaultComboBoxModel(
						new String[] {
								"\u042D\u043A\u0432\u0438\u0432\u0430\u043B\u0435\u043D\u0442\u043D\u043E\u0433\u043E \u043F\u043E \u0441\u043E\u0432\u043E\u043A\u0443\u043F\u043D\u043E\u0439 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u0438 \u043E\u0431\u044A\u0435\u043C\u0430",
								"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0435\u043D\u043D\u044B\u0435 \u0444\u043E\u043D\u0434\u044B \u043D\u0435 \u0432\u044B\u0431\u044B\u0432\u0430\u044E\u0442",
								"\u041C\u0435\u043D\u044C\u0448\u0435\u0433\u043E \u043F\u043E \u0441\u043E\u0432\u043E\u043A\u0443\u0430\u043D\u043E\u0439 \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u0438 \u043E\u0431\u044A\u0435\u043C\u0430" }));
		add(switcher1, "flowx,cell 1 5,alignx right");

		label30 = new JLabel("��������� ������������������ ���������� � ���������� ������");
		label30.setFont(new Font(font, Font.PLAIN, 14));
		label30.setEnabled(false);
		add(label30, "cell 0 6,alignx left");

		switcher2 = new JComboBox();
		switcher2.setFont(new Font(font, Font.PLAIN, 14));
		switcher2.setMinimumSize(new Dimension(150, 26));
		switcher2.setMaximumSize(new Dimension(260, 28));
		switcher2.setPreferredSize(new Dimension(255, 0));
		switcher2.setEnabled(false);
		switcher2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (switcher2.getSelectedIndex() == 2) {
					k.setVisible(false);
					fixedScrollPane.setVisible(true);
				} else {
					k.setVisible(true);
					fixedScrollPane.setVisible(false);
				}

			}
		});
		switcher2
				.setModel(new DefaultComboBoxModel(
						new String[] {
								"\u041F\u0440\u043E\u043F\u043E\u0440\u0446\u0438\u043E\u043D\u0430\u043B\u044C\u043D\u043E\u0435",
								"\u0424\u0438\u043A\u0441\u0438\u0440\u043E\u0432\u0430\u043D\u043D\u043E\u0435",
								"\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u043B\u044C\u043D\u043E\u0435" }));
		add(switcher2, "flowx,cell 1 6,alignx right");

		fixedScrollPane = new JScrollPane();
		fixedScrollPane.setVisible(false);
		add(fixedScrollPane, "cell 0 7 2 1,grow");

		k = new JFormattedTextField(Format.getNumberFormat());
		Format.setFieldDefaults(k, 14, 150, 28);
		k.setEnabled(false);
		add(k, "cell 1 6,alignx right");

		setFixed(null);
		setDemand(null);

	}

	public void setFixed(double[][] data) {
		fixed = TableInOneRow.setTable(data, Main.Year + 1, Main.ModelLenght);
		fixedScrollPane.setViewportView(fixed);
	}

	public void setDemand(double[][] data) {
		demand = TableInOneRow.setTable(data, Main.Year + 1, Main.ModelLenght);
		demandScrollPane.setViewportView(demand);
	}

	public double[] getDemandData() {
		double[] data = new double[demand.getColumnCount()];
		for (int j = 0; j < demand.getColumnCount(); j++) {
			data[j] = Format.getDouble(demand.getValueAt(0, j).toString());
		}
		return data;
	}

	public int getAssetsOutcomeMethod() {
		int index = 0;
		int k = switcher1.getSelectedIndex();
		switch (k) {
		case 0:
			index = 1;
			break;
		case 1:
			index = 2;
			break;
		case 2:
			switch (switcher2.getSelectedIndex()) {
			case 0:
				index = 3;
				break;
			case 1:
				index = 4;
				break;
			case 2:
				index = 4;
				break;
			}
			break;
		}
		return index;
	}

	void refresh(Project pr) {
		power0.setText(String.valueOf(pr.getPower0()));
		staff.setText(String.valueOf(pr.getStaff0()));
		price.setValue(pr.getPrice().getStatisticsData());
		setDemand(pr.getDemand().getStatisticsData());
		int index = pr.getTechGroup();
		switch (index) {
		case 1:
			switcher1.setSelectedIndex(0);
			switcher2.setEnabled(false);
			break;
		case 2:
			switcher1.setSelectedIndex(1);
			switcher2.setEnabled(false);
			break;
		case 3:
			switcher1.setSelectedIndex(2);
			switcher2.setSelectedIndex(0);
			switcher2.setEnabled(true);
			break;
		case 4:
			switcher1.setSelectedIndex(2);
			switcher2.setSelectedIndex(1);
			switcher2.setEnabled(true);
			break;
		}
		setFixed(pr.getFixed().getStatisticsData());
	}
}
