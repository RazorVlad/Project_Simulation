package visual.frames.investment.investmentFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.investment.investmentFrame.chooseBranch.ChooseBranchesFrame;
import visual.frames.investment.investmentFrame.chooseBranch.ChooseBranchesOnYearsFrame;
import visual.frames.main.Main;
import visual.save.Invest;
import visual.save.VisualObjectsContainer;
import visual.tableBuilders.TableWithRowHeaders;

public class InvestTypePanel extends JPanel {

	private static final long serialVersionUID = 478843909821932936L;
	private JFormattedTextField amountOfInvestment;
	private JTable topTable;
	private double[] ivestOnYears;
	private static double[][] invStructValues = null;
	private static boolean[] invOnBranches;
	private final ButtonGroup buttonGroup3 = new ButtonGroup();
	private String font = "Calibri";
	static double[][] investOnBranches;// [�������][���]
	final JComboBox amountOfInvestmentTypeComboBox;
	final JButton brunchesBtn;
	final JButton structureBtn;
	final JRadioButton radioEven;
	final JRadioButton radioUneven;
	final JComboBox investmentByYearsType;
	int[][] invest = new int[10][4];
	int[][] invest1 = new int[10][4];
	boolean isGos;
	InvestmentInBranches investInBranches;
	JScrollPane topScrollPane;
	JScrollPane privateScrollPane;
	JButton btn1;
	JButton btn2;
	JRadioButton rdbtn2;
	JRadioButton rdbtn1;
	JPanel panel3;
	private static Logger LOG = Logger.getLogger(InvestTypePanel.class);

	public static void setInvestOnBranches(double[][] investBranches) {
		investOnBranches = investBranches.clone();
	}

	public static void setInvOnBranches(boolean[] investBranches) {
		invOnBranches = investBranches.clone();
	}

	public InvestmentInBranches getPanel4() {
		return investInBranches;
	}

	public InvestTypePanel(boolean isgos) {
		this.isGos = isgos;
		setBorder(null);
		setBounds(new Rectangle(0, 0, 1300, 850));

		setLayout(new MigLayout("", "[grow]", "[150.00px][88.00px][grow]"));
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"������������� ���������� �� �����", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		add(panel2, "cell 0 0,growx,aligny top");
		panel2.setLayout(new MigLayout("", "[grow][]", "[][grow][]"));

		JLabel amountOfInvestmentLabel = new JLabel("����� ���������� �� ������, ���. ���.");
		amountOfInvestmentLabel.setFont(new Font(font, Font.PLAIN, 13));
		panel2.add(amountOfInvestmentLabel, "flowx,cell 0 0,aligny center");

		final JLabel investmentByYearsTypeLabel = new JLabel("������������� �� �����");
		investmentByYearsTypeLabel.setFont(new Font(font, Font.PLAIN, 13));
		panel2.add(investmentByYearsTypeLabel, "flowx,cell 1 0,aligny center");

		investmentByYearsType = new JComboBox();
		investmentByYearsType.setMinimumSize(new Dimension(180, 26));
		investmentByYearsType.setMaximumSize(new Dimension(180, 26));
		investmentByYearsType.setPreferredSize(new Dimension(150, 26));
		investmentByYearsType.setFont(new Font(font, Font.PLAIN, 13));
		investmentByYearsType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setTopTableData();
			}
		});
		investmentByYearsType.setModel(new DefaultComboBoxModel(
				new String[] { "�����������", "�������������" }));
		panel2.add(investmentByYearsType, "cell 1 0,aligny center");

		amountOfInvestmentTypeComboBox = new JComboBox();
		amountOfInvestmentTypeComboBox.setMaximumSize(new Dimension(180, 26));
		amountOfInvestmentTypeComboBox.setPreferredSize(new Dimension(150, 26));
		amountOfInvestmentTypeComboBox.setMinimumSize(new Dimension(180, 26));
		amountOfInvestmentTypeComboBox.setFont(new Font(font, Font.PLAIN, 13));
		amountOfInvestmentTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean isInvestAmountFixed = amountOfInvestmentTypeComboBox.getSelectedIndex() == 0;
				amountOfInvestment.setVisible(!isInvestAmountFixed);
				investmentByYearsType.setVisible(!isInvestAmountFixed);
				investmentByYearsTypeLabel.setVisible(!isInvestAmountFixed);
			}
		});
		amountOfInvestmentTypeComboBox.setModel(new DefaultComboBoxModel(new String[] { "�������������",
				"������������" }));
		panel2.add(amountOfInvestmentTypeComboBox, "cell 0 0,aligny center");

		amountOfInvestment = new JFormattedTextField(Format.getNumberFormat());
		amountOfInvestment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTopTableData();
			}
		});
		amountOfInvestment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				setTopTableData();
			}
		});

		amountOfInvestment.setMinimumSize(new Dimension(100, 26));
		amountOfInvestment.setMaximumSize(new Dimension(150, 26));
		amountOfInvestment.setFont(new Font(font, Font.PLAIN, 13));

		panel2.add(amountOfInvestment, "cell 0 0,aligny center");
		amountOfInvestment.setColumns(10);

		topScrollPane = new JScrollPane();
		panel2.add(topScrollPane, "cell 0 1 2 1,grow");

		topTable(null);

		JButton btnOK = new JButton("���������");
		btnOK.setMinimumSize(new Dimension(180, 26));
		btnOK.setMaximumSize(new Dimension(180, 26));
		btnOK.setPreferredSize(new Dimension(150, 26));
		btnOK.setFont(new Font(font, Font.PLAIN, 13));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataSet();
			}
		});
		panel2.add(btnOK, "cell 0 2 2 1,alignx right");

		panel3 = new JPanel();
		panel3.setEnabled(false);
		panel3.setMinimumSize(new Dimension(100, 70));
		panel3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"������������� ���������� �� ��������", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel3.setPreferredSize(new Dimension(10, 100));
		panel3.setMaximumSize(new Dimension(32767, 88));
		add(panel3, "cell 0 1,grow");
		panel3.setLayout(new MigLayout("", "[grow][]", "[35][35]"));

		radioEven = new JRadioButton("����������� ������������� ����� ���������");
		buttonGroup3.add(radioEven);
		radioEven.setFont(new Font(font, Font.PLAIN, 13));
		panel3.add(radioEven, "cell 0 0,aligny top");
		radioEven.setSelected(true);

		radioUneven = new JRadioButton("������������� ������������� ����� ���������");
		buttonGroup3.add(radioUneven);
		radioUneven.setFont(new Font(font, Font.PLAIN, 13));

		brunchesBtn = new JButton("������� �������");
		brunchesBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					invStructValues = null;
					ChooseBranchesFrame invChoose = new ChooseBranchesFrame(ivestOnYears, invOnBranches);
					invChoose.show();
				} catch (Exception e1) {
					LOG.error(e1.getMessage(), e1);
				}
			}
		});
		brunchesBtn.setMinimumSize(new Dimension(180, 26));
		brunchesBtn.setMaximumSize(new Dimension(180, 26));
		brunchesBtn.setFont(new Font(font, Font.PLAIN, 13));

		structureBtn = new JButton("��������� �������������");
		structureBtn.setMinimumSize(new Dimension(180, 26));
		structureBtn.setPreferredSize(new Dimension(150, 26));
		structureBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (invStructValues == null) {
						invStructValues = new double[10][Main.ModelLenght];
					}
					ChooseBranchesOnYearsFrame inv = new ChooseBranchesOnYearsFrame(ivestOnYears,
							invOnBranches, invStructValues);
					inv.show();
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		});
		structureBtn.setEnabled(false);
		structureBtn.setMaximumSize(new Dimension(180, 26));
		structureBtn.setFont(new Font(font, Font.PLAIN, 13));

		radioEven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				structureBtn.setEnabled(false);
				brunchesBtn.setEnabled(true);
			}
		});

		panel3.add(brunchesBtn, "cell 1 0,alignx right,growy");
		brunchesBtn.setPreferredSize(new Dimension(150, 26));

		panel3.add(radioUneven, "cell 0 1");

		panel3.add(structureBtn, "cell 1 1,alignx right,aligny center");

		radioUneven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brunchesBtn.setEnabled(false);
				structureBtn.setEnabled(true);
			}
		});

		investInBranches = new InvestmentInBranches();
		add(investInBranches, "cell 0 2,grow");
	}

	public static void setInvStructValues(double[][] invstructValues) {
		invStructValues = invstructValues.clone();
	}

	void setTopTableData() {
		try {
			if (investmentByYearsType.getSelectedIndex() == 0) {
				double amountOfInvestmentValue = Format.getDouble(amountOfInvestment.getText());
				double money = amountOfInvestmentValue / (topTable.getColumnCount() - 1);
				for (int i = 1; i < topTable.getColumnCount(); i++) {
					topTable.getModel().setValueAt(money, 0, i);
				}
			}
			if (investmentByYearsType.getSelectedIndex() == 1) {

			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void topTable(Statistics data) {
		topTable = TableWithRowHeaders.setTable(data, Main.Year + 1, Main.ModelLenght + 1,
				new String[] { "����������, ���.���." });
		topTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		topTable.getColumnModel().getColumn(0).setMaxWidth(150);
		topTable.getColumnModel().getColumn(0).setMinWidth(150);
		topScrollPane.setViewportView(topTable);
	}

	public Statistics getTopTableData() {
		Statistics data = new Statistics();
		for (int i = 1; i < topTable.getColumnCount(); i++) {
			int year = Integer.parseInt(topTable.getColumnModel().getColumn(i).getHeaderValue().toString());
			try {
				data.add(year, Format.getDouble(topTable.getValueAt(0, i).toString()));
			} catch (Exception e) {
				data.add(year, 0);
				LOG.error(e.getMessage(), e);
			}
		}
		return data;
	}

	void savePanelData() {
		Invest inv = VisualObjectsContainer.getInstance().getInvest(isGos);
		inv.setAmountOfInvestmentTypeComboBox(amountOfInvestmentTypeComboBox.getSelectedIndex());
		inv.setInvestmentByYearsType(investmentByYearsType.getSelectedIndex());
		inv.setinvestmentAmount(Format.getDouble(amountOfInvestment.getText()));
		inv.settabledata(getTopTableData());
		int radioIndex = 0;
		if (radioEven.isSelected()) {
			radioIndex = 0;
		}
		if (radioUneven.isSelected()) {
			radioIndex = 1;
		}
		inv.setradioIndex(radioIndex);
		inv.setinvestOnBranches(investOnBranches);
		inv.setinvestInBranch(investInBranches.getInvestInBranch());
		inv.setInvOnBranches(invOnBranches);
		inv.setSelectedType(investInBranches.getSelectedType());
		inv.setTableData(investInBranches.getTableData());
		inv.setInvStructValues(invStructValues);
	}

	void dataSet() {
		ivestOnYears = new double[Main.ModelLenght];
		for (int i = 1; i < topTable.getColumnCount(); i++) {
			ivestOnYears[i - 1] = Format.getDouble((topTable.getValueAt(0, i)).toString());
		}
		investOnBranches = new double[10][Main.ModelLenght];
		investInBranches.setInvestInBranch(new double[13][Main.ModelLenght][10]);
		invOnBranches = new boolean[10];
		for (byte i = 0; i < 10; i++) {
			invOnBranches[i] = false;
		}
		setEnabled(true);
	}

	public void setEnabled(boolean enabled) {
		radioEven.setEnabled(enabled);
		radioUneven.setEnabled(enabled);
		brunchesBtn.setEnabled(enabled);
		investInBranches.setEnabled(enabled);
	}

	public void refreshPanel() {
		try {
			Invest inv = VisualObjectsContainer.getInstance().getInvest(isGos);
			if (inv.gettabledata() != null) {
				topTable(inv.gettabledata());
				amountOfInvestmentTypeComboBox.setSelectedIndex(inv.getAmountOfInvestmentTypeComboBox());
				investmentByYearsType.setSelectedIndex(inv.getInvestmentByYearsType());
				amountOfInvestment.setText(String.valueOf(inv.getinvestmentAmount()));
				investOnBranches = inv.getinvestOnBranches();
				investInBranches.setInvestInBranch(inv.getinvestInBranch());
				invOnBranches = inv.getInvOnBranches();
				invStructValues = inv.getInvStructValues();
				int radioIndex = inv.getradioIndex();
				radioEven.setSelected(radioIndex == 0);
				radioUneven.setSelected(radioIndex != 0);
				investInBranches.setSelectedType(inv.getSelectedType());
				investInBranches.setTableData(inv.getTableData());
				investInBranches.setTable(inv.getSelectedType());
			} else {
				setEnabled(false);
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
