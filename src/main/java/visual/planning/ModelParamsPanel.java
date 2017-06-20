package visual.planning;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.main.Main;
import visual.tableBuilders.TableWithRowHeaders;

public class ModelParamsPanel extends JPanel {

	private static final long serialVersionUID = -1077774018420222302L;
	private JLabel durationLabel;
	private JLabel maxDurationLabel;
	private JLabel lastTimeLabel;
	private JLabel label9;
	private JLabel labelF0;
	private JLabel costBeforeLabel;
	private JLabel amountBeforeLabel;
	private JLabel varCostBeforeLabel;
	private JLabel labelEn;
	private JLabel aimYearLabel;
	private JPanel panel3;
	private JTable table3;
	private JButton button;
	private JSpinner duration;
	private JFormattedTextField f0;
	private JFormattedTextField costBefore;
	private JFormattedTextField amountBefore;
	private JFormattedTextField varCostBefore;
	private JSpinner maxDuration;
	private JSpinner lastTime;
	private JSpinner aimYear;
	private JComboBox vedComboBox;
	private JFormattedTextField en;
	private JScrollPane govOrderScrollPane;
	private JScrollPane scrollPane3;
	private JTable govOrder;
	Statistics[] govorder;
	int lastVedIndex = 0;
	private static Logger LOG = Logger.getLogger(ModelParamsPanel.class);

	public double getEn() {
		return Format.getDouble(en.getText());
	}

	public double getCostBefore() {
		return Format.getDouble(costBefore.getText());
	}

	public double getAmountBefore() {
		return Format.getDouble(amountBefore.getText());
	}

	public double getVarCostBefore() {
		return Format.getDouble(varCostBefore.getText());
	}

	public int getLastT() {
		return Integer.parseInt(lastTime.getValue().toString());
	}

	public int getDuration() {
		return Integer.parseInt(duration.getValue().toString());
	}

	public double getF0() {
		return Format.getDouble(f0.getText());
	}

	public int getMaxDuration() {
		return Integer.parseInt(maxDuration.getValue().toString());
	}

	public int getAimYear() {
		return Integer.parseInt(aimYear.getValue().toString());
	}

	public void setLastVedIndex(int lastVedIndex) {
		this.lastVedIndex = lastVedIndex;
	}

	public void setGovorder(Statistics[] govorder) {
		this.govorder = govorder.clone();
	}

	public void setVedComboBox(int k) {
		this.vedComboBox.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(k)));
	}

	public ModelParamsPanel() {
		setLayout(new MigLayout("", "[grow][120.00][][120]", "[][][][][][][77][70]"));

		labelF0 = new JLabel(
				"\u0412\u0435\u043B\u0438\u0447\u0438\u043D\u0430 \u0444\u043E\u043D\u0434\u0430 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u0432 \u043D\u0430\u0447\u0430\u043B\u0435 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u0433\u043E \u043F\u0435\u0440\u0438\u043E\u0434\u0430, \u0434\u0435\u043D.\u0435\u0434.");
		add(labelF0, "cell 0 0,alignx left");

		f0 = new JFormattedTextField(Format.getNumberFormat());
		add(f0, "cell 1 0,growx");
		f0.setColumns(10);

		durationLabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u0433\u043E \u043F\u0435\u0440\u0438\u043E\u0434\u0430 \u0444\u0443\u043D\u043A\u0446\u0438\u043E\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F \u043E\u0442\u0440\u0430\u0441\u043B\u0438 \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u0438, \u043B\u0435\u0442");
		add(durationLabel, "cell 2 0,alignx right");

		duration = new JSpinner();
		duration.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setTableGovOrder(null);
				SetTable3();
			}
		});
		duration.setModel(new SpinnerNumberModel(1, 1, Main.ModelLenght, 1));
		add(duration, "cell 3 0,growx");

		costBeforeLabel = new JLabel(
				"<html>\u0415\u0434\u0438\u043D\u043E\u0432\u0440\u0435\u043C\u0435\u043D\u043D\u044B\u0435 \u0437\u0430\u0442\u0440\u0430\u0442\u044B, \u043A\u043E\u0442\u043E\u0440\u044B\u0435 \u0434\u043E\u043B\u0436\u043D\u044B \u0431\u044B\u0442\u044C \u043F\u043E\u043D\u0435\u0441\u0435\u043D\u044B \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435<br>(\u0432 \u0441\u0432\u044F\u0437\u0438 \u0441 \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u0430\u043C\u0438 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F, \u043F\u0440\u0438\u043D\u044F\u0442\u044B\u043C\u0438 \u043D\u0430 \u043F\u0440\u0435\u0434\u044B\u0441\u0442\u043E\u0440\u0438\u0438)");
		add(costBeforeLabel, "cell 0 1,alignx left");

		costBefore = new JFormattedTextField(Format.getNumberFormat());
		add(costBefore, "cell 1 1,growx");
		costBefore.setColumns(10);

		maxDurationLabel = new JLabel(
				"<html>\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u043B\u0435\u0442,<br>\u0432 \u0442\u0435\u0447\u0435\u043D\u0438\u0438 \u043A\u043E\u0442\u043E\u0440\u044B\u0445 \u043C\u043E\u0436\u0435\u0442 \u043E\u0441\u0443\u0449\u0435\u0441\u0442\u0432\u043B\u044F\u0442\u044C\u0441\u044F \u0432\u0430\u0440\u0438\u0430\u043D\u0442 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F, \u043B\u0435\u0442");
		maxDurationLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		add(maxDurationLabel, "cell 2 1,alignx left");

		maxDuration = new JSpinner();
		maxDuration.setModel(new SpinnerNumberModel(1, 1, Main.ModelLenght, 1));
		add(maxDuration, "cell 3 1,growx");

		amountBeforeLabel = new JLabel(
				"\u041E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u0430\u044F \u0441\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C \u043E\u0441\u043D\u043E\u0432\u043D\u044B\u0445 \u0444\u043E\u043D\u0434\u043E\u0432, \u043A\u043E\u0442\u043E\u0440\u044B\u0435 \u0432\u044B\u0431\u044B\u0432\u0430\u044E\u0442 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435");
		add(amountBeforeLabel, "cell 0 2,alignx left");

		amountBefore = new JFormattedTextField(Format.getNumberFormat());
		add(amountBefore, "cell 1 2,growx");
		amountBefore.setColumns(10);

		lastTimeLabel = new JLabel(
				"\u0412\u0440\u0435\u043C\u044F \u043D\u0430\u0447\u0430\u043B\u0430 \u0440\u0435\u0430\u043B\u0438\u0437\u0432\u0430\u0446\u0438\u0438 \u043F\u043E\u0441\u043B\u0435\u0434\u043D\u0435\u0433\u043E \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u0430 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F");
		add(lastTimeLabel, "cell 2 2,alignx left");

		lastTime = new JSpinner();
		lastTime.setModel(new SpinnerNumberModel(Main.Year, Main.Year, Main.Year + Main.ModelLenght, 1));
		add(lastTime, "cell 3 2,growx");

		varCostBeforeLabel = new JLabel(
				"<html>\u0418\u0437\u043C\u0435\u043D\u0435\u043D\u0435\u043D\u0438\u0435 \u0442\u0435\u043A\u0443\u0449\u0438\u0445 \u0437\u0430\u0442\u0440\u0430\u0442 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435<br>\u0432 \u0441\u0432\u044F\u0437\u0438 \u0441 \u043D\u0430\u0447\u0430\u0442\u044B\u043C\u0438 \u0432\u0430\u0440\u0438\u0430\u043D\u0442\u0430\u043C\u0438 \u0440\u0430\u0437\u0432\u0438\u0442\u0438\u044F \u043D\u0430 \u043F\u0440\u0435\u0434\u044B\u0441\u0442\u043E\u0440\u0438\u0438");
		add(varCostBeforeLabel, "cell 0 3,alignx left");

		varCostBefore = new JFormattedTextField(Format.getNumberFormat());
		add(varCostBefore, "cell 1 3,growx");
		varCostBefore.setColumns(10);

		aimYearLabel = new JLabel("\u0420\u0430\u0441\u0447\u0435\u0442\u043D\u044B\u0439 \u0433\u043E\u0434");
		add(aimYearLabel, "cell 2 3,alignx left");

		aimYear = new JSpinner();
		aimYear.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					setTableGovOrder(null);
					SetTable3();
				} catch (Exception e1) {
					LOG.error(e1.getMessage(), e1);
				}
			}
		});
		aimYear.setModel(new SpinnerNumberModel(Main.Year, Main.Year, Main.Year + Main.ModelLenght, 1));
		add(aimYear, "cell 3 3,growx");

		labelEn = new JLabel(
				"\u041D\u043E\u0440\u043C\u0430\u0442\u0438\u0432 \u043F\u0440\u0438\u0432\u0435\u0434\u0435\u043D\u0438\u044F \u0440\u0430\u0437\u043D\u044B\u0445 \u043F\u043E \u0432\u0440\u0435\u043C\u0435\u043D\u0438 \u0437\u0430\u0442\u0440\u0430\u0442 \u0438 \u0440\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u043E\u0432");
		add(labelEn, "cell 0 4");

		panel3 = new JPanel();
		add(panel3, "cell 1 4,grow");
		panel3.setLayout(new CardLayout(0, 0));

		en = new JFormattedTextField(Format.getNumberFormat());
		panel3.add(en, "name_694922187272957");
		en.setColumns(10);

		button = new JButton("\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u044F");
		panel3.add(button, "name_694910790450284");

		label9 = new JLabel(
				"\u0413\u043E\u0441\u0443\u0434\u0430\u0440\u0441\u0442\u0432\u0435\u043D\u043D\u044B\u0439 \u0437\u0430\u043A\u0430\u0437 \u043D\u0430 \u0440\u044F\u0434\u043E\u0432\u0443\u044E(\u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u0443\u044E \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u044E) \u0412\u042D\u0414\u0430 \u0432 \u043F\u043B\u0430\u043D\u043E\u0432\u043E\u043C \u043F\u0435\u0440\u0438\u043E\u0434\u0435, \u0434\u0435\u043D.\u0435\u0434");
		add(label9, "cell 0 5 2 1,alignx left");

		vedComboBox = new JComboBox();
		vedComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				govorder[lastVedIndex] = getGovOrder();
				lastVedIndex = vedComboBox.getSelectedIndex();
				setTableGovOrder(govorder[lastVedIndex]);
			}
		});
		vedComboBox.setModel(new DefaultComboBoxModel(mathModel.VedNames.getVedNames(0)));
		add(vedComboBox, "cell 2 5,growx");

		govOrderScrollPane = new JScrollPane();
		govOrderScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(govOrderScrollPane, "cell 0 6 4 1,grow");

		setTableGovOrder(null);
		govOrderScrollPane.setViewportView(govOrder);

		scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane3, "cell 0 7 4 1,grow");

		SetTable3();
		scrollPane3.setViewportView(table3);
	}

	void setTableGovOrder(Statistics datas) {
		int modelYear = Integer.parseInt(aimYear.getValue().toString());
		int columnCount = Integer.parseInt(duration.getValue().toString()) + 1;
		govOrder = TableWithRowHeaders.setTable(datas, modelYear, columnCount, new String[] {
				"\u0440\u044F\u0434\u043E\u0432\u0430\u044F",
				"\u0438\u043D\u043D\u043E\u0432\u0430\u0446\u0438\u043E\u043D\u043D\u0430\u044F" });
		govOrder.getColumnModel().getColumn(0).setPreferredWidth(150);
		govOrder.getColumnModel().getColumn(0).setMinWidth(150);
		govOrderScrollPane.setViewportView(govOrder);
	}

	void SetTable3() {
		int modelYear = Integer.parseInt(aimYear.getValue().toString());
		int columnCount = Integer.parseInt(duration.getValue().toString()) + 1;
		table3 = TableWithRowHeaders
				.setTable(
						null,
						modelYear,
						columnCount,
						new String[] { "<html>Средства выделяемые на развитие отрасли<br>промышленности в плановом периоде, ден.ед." });
		table3.getColumnModel().getColumn(0).setPreferredWidth(150);
		table3.getColumnModel().getColumn(0).setMinWidth(150);
		table3.setRowHeight(42);
		scrollPane3.setViewportView(table3);
	}

	Statistics getGovOrder() {
		Statistics data = new Statistics();
		for (int i = 0; i < govOrder.getColumnCount(); i++) {
			data.add(Integer.parseInt(govOrder.getColumnModel().getColumn(i).toString()),
					Format.getDouble(govOrder.getValueAt(0, i).toString()));
		}
		return data;
	}

	Statistics getTable3() {
		Statistics data = new Statistics();
		for (int i = 0; i < table3.getColumnCount(); i++) {
			data.add(Integer.parseInt(table3.getColumnModel().getColumn(i).toString()),
					Format.getDouble(table3.getValueAt(0, i).toString()));
		}
		return data;
	}
}
