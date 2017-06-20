package visual.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import mathModel.VedNames;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTaskPane;

import visual.NumberOrButton;

public class Taxes extends JFrame {

	private static final long serialVersionUID = -1270197112866424800L;
	private JPanel contentPane;
	List<JXTaskPane> taskPanes = new ArrayList<JXTaskPane>();
	Box verticalBox;
	JCheckBox checkBoxAll;

	private List<JCheckBox> VedMap = new ArrayList<JCheckBox>();

	NumberOrButton incomeTaxInn;
	NumberOrButton salaryTaxInn;
	NumberOrButton valueAddedTaxRateInn;
	NumberOrButton incomeTaxOrd;
	NumberOrButton salaryTaxOrd;
	NumberOrButton valueAddedTaxRateOrd;

	public Statistics getIncomeTaxInn() {
		return incomeTaxInn.getStatistics();
	}

	public Statistics getSalaryTaxInn() {
		return salaryTaxInn.getStatistics();
	}

	public Statistics getValueAddedTaxRateInn() {
		return valueAddedTaxRateInn.getStatistics();
	}

	public Statistics getIncomeTaxOrd() {
		return incomeTaxOrd.getStatistics();
	}

	public Statistics getSalaryTaxOrd() {
		return salaryTaxOrd.getStatistics();
	}

	public Statistics getValueAddedTaxRateOrd() {
		return valueAddedTaxRateOrd.getStatistics();
	}

	public Taxes() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		setBounds(new Rectangle(50, 50, 900, 550));
		setTitle("\u0411\u044E\u0434\u0436\u0435\u0442\u043D\u043E-\u043D\u0430\u043B\u043E\u0433\u043E\u0432\u0430\u044F \u043F\u043E\u043B\u0438\u0442\u0438\u043A\u0430");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[360][grow]", "[][][][grow][]"));

		JLabel chooseVedLabel = new JLabel(
				"\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0412\u042D\u0414");
		contentPane.add(chooseVedLabel, "cell 0 0");

		JPanel checkBoxesPanel = new JPanel();
		checkBoxesPanel.setMinimumSize(new Dimension(325, 10));
		checkBoxesPanel.setMaximumSize(new Dimension(375, 32767));
		contentPane.add(checkBoxesPanel, "cell 0 1 1 3,alignx left,growy");

		verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(null);
		verticalBox.setPreferredSize(new Dimension(370, 400));
		checkBoxesPanel.add(verticalBox);

		for (int i = 0; i < 10; i++) {
			taskPanes.add(new JXTaskPane());
			setTaskPane(taskPanes.get(i), (i != 0), i, VedNames.getOtraslName(i));
		}

		for (int i = 0; i < 25; i++) {
			String name = VedNames.getVedName(i);
			switch (i) {
			case 6:
				name = addBR(name, 43);
				break;
			case 11:
				name = addBR(name, 32);
				break;
			case 14:
				name = addBR(name, 36);
				break;
			case 17:
				name = addBR(name, 26);
				break;
			case 20:
				name = addBR(name, 39);
				break;
			case 22:
				name = addBR(name, 23);
				break;
			case 24:
				name = addBR(name, 28);
				break;
			}

			VedMap.add(new JCheckBox(name));
			final int ved = i;
			VedMap.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refresh(ved);
				}
			});
		}

		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < VedNames.getVeds(i).length; j++) {
				taskPanes.get(i).getContentPane().add(VedMap.get(count), "cell 0 " + j + ",alignx left");
				VedMap.get(count).addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						checkBoxAll.setSelected(checkVeds());
					}
				});
				count++;
			}
		}

		JPanel innPanel = new JPanel();
		innPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "������������� ������������",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(innPanel, "cell 1 1,growx");
		innPanel.setLayout(new MigLayout("", "[173px][55px,grow]", "[16px][][]"));

		JLabel incomeTaxInnLabel = new JLabel("������ ������ �� �������, %");
		innPanel.add(incomeTaxInnLabel, "cell 0 0,alignx left");

		incomeTaxInn = new NumberOrButton("������ ������ �� �������", false);
		incomeTaxInn.setMaxValue(1);
		innPanel.add(incomeTaxInn, "cell 1 0,alignx right");

		JLabel salaryTaxInnLabel = new JLabel("������ ����������� ������ � ��������, %");
		innPanel.add(salaryTaxInnLabel, "cell 0 1,alignx left,aligny center");

		salaryTaxInn = new NumberOrButton("������ ����������� ������ � ��������, %", false);
		salaryTaxInn.setMaxValue(1);
		innPanel.add(salaryTaxInn, "cell 1 1,alignx right");

		JLabel valueAddedTaxRateInnLabel = new JLabel("������ ������ �� ����������� ���������, %");
		innPanel.add(valueAddedTaxRateInnLabel, "cell 0 2,alignx left");

		valueAddedTaxRateInn = new NumberOrButton("������ ������ �� ����������� ���������, %", false);
		valueAddedTaxRateInn.setMaxValue(1);
		innPanel.add(valueAddedTaxRateInn, "cell 1 2,alignx right");

		JPanel ordPanel = new JPanel();
		ordPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "������� ������������",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(ordPanel, "flowx,cell 1 2,growx");
		ordPanel.setLayout(new MigLayout("", "[55px][122px,grow]", "[][][]"));

		JLabel incomeTaxOrdLabel = new JLabel("������ ������ �� �������, %");
		ordPanel.add(incomeTaxOrdLabel, "cell 0 0,alignx left,aligny center");

		incomeTaxOrd = new NumberOrButton("������ ������ �� �������, %", false);
		incomeTaxOrd.setMaxValue(1);
		ordPanel.add(incomeTaxOrd, "cell 1 0,alignx right,aligny top");

		JLabel salaryTaxOrdLabel = new JLabel("������ ����������� ������ � ��������, %");
		ordPanel.add(salaryTaxOrdLabel, "cell 0 1,alignx left");

		salaryTaxOrd = new NumberOrButton("������ ����������� ������ � ��������, %", false);
		salaryTaxOrd.setMaxValue(1);
		ordPanel.add(salaryTaxOrd, "cell 1 1,alignx right");

		JLabel valueAddedTaxRateOrdLabel = new JLabel("������ ������ �� ����������� ���������, %");
		ordPanel.add(valueAddedTaxRateOrdLabel, "cell 0 2,alignx left");

		valueAddedTaxRateOrd = new NumberOrButton("������ ������ �� ����������� ���������, %", false);
		valueAddedTaxRateOrd.setMaxValue(1);
		ordPanel.add(valueAddedTaxRateOrd, "cell 1 2,alignx right");

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(buttonsPanel, "cell 0 4 2 1,growx,aligny bottom");
		buttonsPanel.setLayout(new MigLayout("", "[grow][105px][105px]", "[26px]"));

		checkBoxAll = new JCheckBox(
				"\u0412\u044B\u0431\u0440\u0430\u0442\u044C \u0432\u0441\u0435 \u0412\u042D\u0414\u044B");
		buttonsPanel.add(checkBoxAll, "cell 0 0");
		checkBoxAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = checkBoxAll.isSelected();
				for (int i = 0; i < 25; i++) {
					VedMap.get(i).setSelected(selected);
				}
			}
		});

		JButton okBtn = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO read taxes data
				ModelObjectsContainer model = ModelObjectsContainer.getInstance();
				for (Integer ved : VedNames.VED) {
					mathModel.Taxes inn = new mathModel.Taxes(getIncomeTax(true, ved),
							getSalaryTax(true, ved), getValueAddedTaxRate(true, ved));
					mathModel.Taxes ord = new mathModel.Taxes(getIncomeTax(false, ved), getSalaryTax(false,
							ved), getValueAddedTaxRate(false, ved));
					model.setTaxesInn(ved, inn);
					model.setTaxesOrd(ved, ord);
				}
				// getIncomeTax(boolean isInnovation,int vedIndex);
				// getSalaryTax(boolean isInnovation,int vedIndex);
				// getValueAddedTaxRateInn(boolean isInnovation,int vedIndex);
			}
		});
		okBtn.setPreferredSize(new Dimension(105, 26));
		okBtn.setMinimumSize(new Dimension(105, 26));
		okBtn.setMaximumSize(new Dimension(105, 30));
		buttonsPanel.add(okBtn, "cell 1 0,alignx right,aligny top");

		JButton cancelBtn = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setPreferredSize(new Dimension(105, 26));
		cancelBtn.setMinimumSize(new Dimension(105, 26));
		cancelBtn.setMaximumSize(new Dimension(105, 30));
		buttonsPanel.add(cancelBtn, "cell 2 0,alignx right,aligny top");
	}

	void refresh(int ved) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		mathModel.Taxes inn = model.getTaxesInn(ved);
		mathModel.Taxes ord = model.getTaxesOrd(ved);
		if (inn != null && ord != null) {
			incomeTaxInn.setValue(inn.getCoefTax());
			salaryTaxInn.setValue(inn.getSalaryTax());
			valueAddedTaxRateInn.setValue(inn.getAddedValueTax());
			incomeTaxOrd.setValue(ord.getCoefTax());
			salaryTaxOrd.setValue(ord.getSalaryTax());
			valueAddedTaxRateOrd.setValue(ord.getAddedValueTax());
		}
	}

	public Statistics getIncomeTax(boolean isInnovation, int vedIndex) {
		if (VedMap.get(vedIndex).isSelected()) {
			if (isInnovation) {
				return incomeTaxInn.getStatistics();
			} else {
				return incomeTaxOrd.getStatistics();
			}
		} else {
			return null;
		}
	}

	public Statistics getSalaryTax(boolean isInnovation, int vedIndex) {
		if (VedMap.get(vedIndex).isSelected()) {
			if (isInnovation) {
				return salaryTaxInn.getStatistics();
			} else {
				return salaryTaxOrd.getStatistics();
			}
		} else {
			return null;
		}
	}

	public Statistics getValueAddedTaxRate(boolean isInnovation, int vedIndex) {
		if (VedMap.get(vedIndex).isSelected()) {
			if (isInnovation) {
				return valueAddedTaxRateInn.getStatistics();
			} else {
				return valueAddedTaxRateOrd.getStatistics();
			}
		} else {
			return null;
		}
	}

	void setTaskPane(JXTaskPane taskPane, boolean iscollapsed, final int index, String title) {
		taskPane.setFont(new Font("Calibri", Font.BOLD, 12));
		if (iscollapsed) {
			taskPane.setCollapsed(iscollapsed);
		}
		taskPane.setTitle(title);
		taskPane.setPreferredSize(new Dimension(368, 0));
		taskPane.setMinimumSize(new Dimension(368, 25));
		StringBuilder j = new StringBuilder();
		for (int h = 0; h < VedNames.getVeds(index).length; h++) {
			j.append("[]");
		}
		taskPane.getContentPane().setLayout(new MigLayout("", "[318px]", j.toString()));
		taskPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				collaps(index);
			}
		});
		verticalBox.add(taskPane);
	}

	void collaps(int k) {
		for (int i = 0; i < 10; i++) {
			if (k != i) {
				taskPanes.get(i).setCollapsed(true);
			}
		}
	}

	boolean checkVeds() {
		boolean answer = true;
		for (int i = 0; i < 25; i++) {
			if (!VedMap.get(i).isSelected()) {
				answer = false;
			}
		}
		return answer;
	}

	public static String addBR(String name, int n) {
		name = "<html>" + name.substring(0, n) + "<br>" + name.substring(n);
		return name;
	}
}
