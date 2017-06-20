package visual.frames.investment;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import visual.Format;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;

public class InvestmentResultFrame extends JFrame {

	private static final long serialVersionUID = 7257918111915237396L;
	private static Logger LOG = Logger.getLogger(InvestmentResultFrame.class);
	String[] seriesNames = new String[] { "Образование", "НИР", "ОКР", "Производство пищевых продуктов(ряд)",
			"Производство пищевых продуктов(инн)", "Производство напитков(ряд)",
			"Производство напитков(инн)", "Производство табачных изделий(ряд)",
			"Производство табачных изделий(инн)" };
	private FreeChartGraf graf;
	private JPanel contentPane;
	private JTable table;
	JScrollPane scrollPane;
	int lastBranch = 0;
	int otrasl = 0;
	int investType = 0;// инвестиции_0-государственные_1-частные
	final JComboBox comboBox;
	private JPanel panel2;
	double[][][] investInBranchGos;
	double[][][] investInBranchChastn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	List<JRadioButton> radioButtons = new ArrayList<JRadioButton>();

	public InvestmentResultFrame(double[][][] investInBranchGos, double[][][] investInBranchPrivate)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				graf();
			}
		});
		this.investInBranchChastn = investInBranchPrivate.clone();
		this.investInBranchGos = investInBranchGos.clone();
		setBounds(new Rectangle(50, 50, 900, 600));
		setTitle("\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439 \u043F\u043E \u043E\u0442\u0440\u0430\u0441\u043B\u044F\u043C");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[270:270.00][220.00px:n,grow][]",
				"[24][grow][::250px,grow][]"));

		JLabel label = new JLabel("\u041E\u0442\u0440\u0430\u0441\u043B\u0438");
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(label, "cell 0 0,growy");

		final JLabel branchNameLabel = new JLabel(
				"\u041F\u0438\u0449\u0435\u0432\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C");
		branchNameLabel.setMinimumSize(new Dimension(220, 16));
		branchNameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(branchNameLabel, "flowx,cell 1 0,alignx center,growy");

		JLabel label2 = new JLabel("\u0418\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0438");
		label2.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(label2, "flowx,cell 2 0,alignx right,growy");

		JScrollPane scrollPane1 = new JScrollPane();
		contentPane.add(scrollPane1, "cell 0 1,grow");

		JPanel panel = new JPanel();
		scrollPane1.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		for (int i = 0; i < 10; i++) {
			radioButtons.add(new JRadioButton(mathModel.VedNames.getOtraslName(i)));
			radioButtons.get(i).setSelected(i == 0);
			final int k = i;
			radioButtons.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					select(k);
					setTable(mathModel.VedNames.getVeds(k).length * 2 + 3);
					branchNameLabel.setText(mathModel.VedNames.getOtraslName(k));
				}
			});
			radioButtons.get(i).setFont(new Font("Calibri", Font.PLAIN, 12));
			panel.add(radioButtons.get(i));
			buttonGroup.add(radioButtons.get(i));
		}

		graf = new FreeChartGraf(true, "0.00");
		contentPane.add(graf, "cell 1 1,grow");

		panel2 = new JPanel();
		contentPane.add(panel2, "cell 2 1,grow");
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		checkBoxes.add(new JCheckBox("Образование"));
		checkBoxes.add(new JCheckBox("НИР"));
		checkBoxes.add(new JCheckBox("ОКР"));
		checkBoxes.add(new JCheckBox("Производство пищевых продуктов(ряд)"));
		checkBoxes.add(new JCheckBox("Производство пищевых проуктов(инн)"));
		checkBoxes.add(new JCheckBox("Производство напитков(ряд)"));
		checkBoxes.add(new JCheckBox("Производство напитков(инн)"));
		checkBoxes.add(new JCheckBox("Производство табачных изделий(ряд)"));
		checkBoxes.add(new JCheckBox("Производство табачных изделий(инн)"));

		for (int i = 0; i < 4; i++) {
			checkBoxes.add(new JCheckBox("New check box"));
		}

		for (int i = 0; i < 13; i++) {
			checkBoxes.get(i).addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					graf();
				}
			});
			checkBoxes.get(i).setBorder(null);
			if (i > 8) {
				checkBoxes.get(i).setVisible(false);
			}
			panel2.add(checkBoxes.get(i));
		}

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 2 3 1,grow");

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox.setMaximumSize(new Dimension(140, 32767));
		comboBox.setPreferredSize(new Dimension(130, 25));
		comboBox.setMinimumSize(new Dimension(140, 25));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				investType = comboBox.getSelectedIndex();
				setTable(table.getRowCount());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u0433\u043E\u0441\u0443\u0434\u0430\u0440\u0441\u0442\u0432\u0435\u043D\u043D\u044B\u0435",
				"\u0447\u0430\u0441\u0442\u043D\u044B\u0435" }));
		contentPane.add(comboBox, "cell 2 0,alignx right,growy");
		setTable(9);
	}

	void select(int otr) {
		otrasl = otr;
	}

	void setTable(int linesCount) {
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[linesCount][Main.ModelLenght + 1],
				new String[Main.ModelLenght + 1]) {

			private static final long serialVersionUID = -4543059656235765023L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		table.setFont(new Font("Calibri", Font.PLAIN, 12));

		table.getColumnModel().getColumn(0).setPreferredWidth(450);
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(90);
			table.getColumnModel().getColumn(i).setHeaderValue(Main.Year + i);
		}
		table.getColumnModel().getColumn(0).setHeaderValue("Сферы/Года");

		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 1; j < table.getColumnCount(); j++) {
				try {
					if (comboBox.getSelectedIndex() == 0) {
						table.setValueAt(investInBranchGos[i][j - 1][otrasl], i, j);
					} else {
						table.setValueAt(investInBranchChastn[i][j - 1][otrasl], i, j);
					}
				} catch (Exception e) {
					table.setValueAt(0, i, j + 1);
					LOG.error(e.getMessage(), e);
				}
			}
		}

		table.setValueAt("Образование", 0, 0);
		table.setValueAt("НИР", 1, 0);
		table.setValueAt("ОКР", 2, 0);
		switch (otrasl) {
		case 0:
			table.setValueAt("производство пищевых продуктов(рядовое)", 3, 0);
			table.setValueAt("производство пищевых продуктов(инновационное)", 4, 0);
			table.setValueAt("производство напитков(рядовое)", 5, 0);
			table.setValueAt("производство напитков(инновационное)", 6, 0);
			table.setValueAt("производство табачных изделий(рядовое)", 7, 0);
			table.setValueAt("производство табачных изделий(инновационное)", 8, 0);
			break;
		case 1:
			table.setValueAt("текстильное производство(рядовое)", 3, 0);
			table.setValueAt("текстильное производство(инновационное)", 4, 0);
			table.setValueAt("производство одежды(рядовое)", 5, 0);
			table.setValueAt("производство одежды(инновационное)", 6, 0);
			table.setValueAt("производство изделий из кожи(рядовое)", 7, 0);
			table.setValueAt("производство изделий из кожи(инновационное)", 8, 0);
			break;
		case 2:
			table.setValueAt("изготовление изделий из древесины(рядовое)", 3, 0);
			table.setValueAt("изготовление изделий из древесины(инновационное)", 4, 0);
			break;
		case 3:
			table.setValueAt("производство бумажных изделий(рядовое)", 3, 0);
			table.setValueAt("производство бумажных изделий(инновационное)", 4, 0);
			table.setValueAt("полиграфическая деятельность(рядовое)", 5, 0);
			table.setValueAt("полиграфическая деятельность(инновационное)", 6, 0);
			break;
		case 4:
			table.setValueAt("производство кокса(рядовое)", 3, 0);
			table.setValueAt("производство кокса(инновационное)", 4, 0);
			table.setValueAt("производство продуктов нефтепереработки(рядовое)", 5, 0);
			table.setValueAt("производство продуктов нефтепереработки(инновационное)", 6, 0);
			break;
		case 5:
			table.setValueAt("производство химической продукции(рядовое)", 3, 0);
			table.setValueAt("производство химической продукции(инновационное)", 4, 0);
			table.setValueAt("фармацевтическое производство(рядовое)", 5, 0);
			table.setValueAt("фармацевтическое производство(инновационное)", 6, 0);
			table.setValueAt("производство резиновых и плстмассовых изделий(рядовое)", 7, 0);
			table.setValueAt("производство резиновых и плстмассовых изделий(инновационное)", 8, 0);
			break;
		case 6:
			table.setValueAt("производство неметаллической минеральной продукции(рядовое)", 3, 0);
			table.setValueAt("производство неметаллической минеральной продукции(инновационное)", 4, 0);
			break;
		case 7:
			table.setValueAt("металлургическое производство(рядовое)", 3, 0);
			table.setValueAt("металлургическое производство(инновационное)", 4, 0);
			table.setValueAt("производство готовых металлических изделий(рядовое)", 5, 0);
			table.setValueAt("производство готовых металлических изделий(инновационное)", 6, 0);
			break;
		case 8:
			table.setValueAt("производство электронной и оптической продукции(рядовое)", 3, 0);
			table.setValueAt("производство электронной и оптической продукции(инновационное)", 4, 0);
			table.setValueAt("производство электрического оборудования(рядовое)", 5, 0);
			table.setValueAt("производство электрического оборудования(инновационное)", 6, 0);
			table.setValueAt("производство машин и оборудования(рядовое)", 7, 0);
			table.setValueAt("производство машин и оборудования(инновационное)", 8, 0);
			table.setValueAt("производство автотранспортных средств(рядовое)", 9, 0);
			table.setValueAt("производство автотранспортных средств(инновационное)", 10, 0);
			table.setValueAt("производство прочих транспортных средств(рядовое)", 11, 0);
			table.setValueAt("производство прочих транспортных средств(инновационное)", 12, 0);
			break;
		case 9:
			table.setValueAt("производство электроэнергии(рядовое)", 3, 0);
			table.setValueAt("производство электроэнергии(инновационное)", 4, 0);
			table.setValueAt("производство газа(рядовое)", 5, 0);
			table.setValueAt("производство газа(инновационное)", 6, 0);
			table.setValueAt("поставка пара и горячей воды(рядовое)", 7, 0);
			table.setValueAt("поставка пара и горячей воды(инновационное)", 8, 0);
			break;
		case 10:
			table.getTableHeader().resizeAndRepaint();
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.revalidate();
			table.repaint();
			break;
		}
		scrollPane.setViewportView(table);
		setChkBox();
	}

	void setChkBox() {
		seriesNames = new String[table.getRowCount()];
		seriesNames[0] = "Образование";
		seriesNames[1] = "НИР";
		seriesNames[2] = "ОКР";
		for (int i = 3; i < 13; i += 2) {
			if (table.getRowCount() > i) {
				String row1 = table.getValueAt(i, 0).toString()
						.substring(0, table.getValueAt(i, 0).toString().length() - 5)
						+ ")";
				String row2 = table.getValueAt(i + 1, 0).toString()
						.substring(0, table.getValueAt(i + 1, 0).toString().length() - 11)
						+ ")";
				seriesNames[i] = row1;
				seriesNames[i + 1] = row2;
				checkBoxes.get(i).setText(row1);
				checkBoxes.get(i + 1).setText(row2);
				checkBoxes.get(i).setVisible(true);
				checkBoxes.get(i + 1).setVisible(true);
			} else {
				checkBoxes.get(i).setVisible(false);
				checkBoxes.get(i + 1).setVisible(false);
			}
		}
	}

	void graf() {
		try {
			double[][] xy = new double[14][table.getColumnCount() - 1];
			for (int i = 1; i < table.getColumnCount(); i++) {
				xy[0][i - 1] = Main.Year + i;
				for (int j = 0; j < 13; j++) {
					try {
						if (checkBoxes.get(j).isSelected()) {
							xy[j + 1][i - 1] = Format.getDouble(table.getValueAt(j, i).toString());
						} else {
							xy[j + 1][i - 1] = -1;
						}
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
			graf.refresh(xy, seriesNames);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
