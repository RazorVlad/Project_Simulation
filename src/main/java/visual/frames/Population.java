package visual.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;
import visual.frames.main.Main;
import visual.graf.FreeChartGraf;
import visual.tableBuilders.TableInOneRow;
import visual.tableBuilders.TableWithRowHeaders;
import exceptions.DataRequiredEx;

public class Population extends JFrame {

	private static final long serialVersionUID = -2539804429599477323L;
	private static Logger LOG = Logger.getLogger(Population.class);
	private JPanel contentPane;
	private JFormattedTextField deathCoef;
	private JTable newborns;
	private NumberOrButton collegeAppl9const;
	private NumberOrButton collegeApplAfter12;
	private NumberOrButton univerApplAfter12;
	JScrollPane newbornsScrollPane;
	private JTable resultTable;
	private FreeChartGraf graf;
	private String font = "Calibri";
	Box verticalBox;
	private ArrayList<String> errList;
	String[] seriesNames = new String[] { "Количество школьников, окончивших 9 классов ",
			"Количество школьников, окончивших 12 классов",
			"Количество выпускников  9 классов, поступающих в ВУЗы 1-2 уровня аккредетации",
			"Количество выпускников 12 классов, поступающих в ВУЗы 1-2 уровня аккредетации",
			"Количество выпускников 12 классов, поступающих в ВУЗы 3-4 уровня аккредетации" };
	List<JCheckBox> check = new ArrayList<JCheckBox>();

	public Population() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		setBounds(new Rectangle(50, 50, 900, 800));
		setTitle("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430 \u043D\u0430\u0441\u0435\u043B\u0435\u043D\u0438\u044F");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow]", "[][35px:n,grow][22px][grow]"));

		JLabel statisticLabel = new JLabel("Статистика новорожденных");
		statisticLabel.setFont(new Font(font, Font.PLAIN, 14));
		panel.add(statisticLabel, "cell 0 0");

		newbornsScrollPane = new JScrollPane();
		newbornsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		newbornsScrollPane.setMaximumSize(new Dimension(32767, 68));
		newbornsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(newbornsScrollPane, "cell 0 1,growx,aligny center");
		setTableNewborns(null);

		JLabel deathCoefLabel = new JLabel("Коэффициент смертности:");
		deathCoefLabel.setPreferredSize(new Dimension(135, 20));
		deathCoefLabel.setFont(new Font(font, Font.PLAIN, 14));
		panel.add(deathCoefLabel, "flowx,cell 0 2,alignx center,growy");

		deathCoef = new JFormattedTextField(Format.getNumberFormat());
		deathCoef.setFont(new Font(font, Font.PLAIN, 14));
		deathCoef.setMinimumSize(new Dimension(200, 27));
		deathCoef.setPreferredSize(new Dimension(100, 27));
		panel.add(deathCoef, "cell 0 2,alignx center,growy");
		deathCoef.setColumns(10);

		JPanel paramsPanel = new JPanel();
		panel.add(paramsPanel, "cell 0 3,grow");
		paramsPanel.setLayout(new MigLayout("", "[grow][200]", "[23px][][]"));

		JLabel collegeAppl9constLabel = new JLabel(
				"Доля школьников, поступающих в ВУЗы 1-2 уровня аккредетации после 9 классов");
		collegeAppl9constLabel.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(collegeAppl9constLabel, "cell 0 0,alignx left,aligny center");

		collegeAppl9const = new NumberOrButton(
				"Доля школьников, поступающих в ВУЗы 1-2 уровня аккредетации после 9 классов", false);
		collegeAppl9const.setMaxValue(1);
		paramsPanel.add(collegeAppl9const, "cell 1 0,alignx center,aligny center");
		collegeAppl9const.setStartYear(Main.Year - 2);
		collegeAppl9const.setLength(Main.ModelLenght + 3);

		JLabel collegeApplAfter12Label = new JLabel(
				"Доля школьников, поступающих в ВУЗы 1-2 уровня аккредетации после 12 классов");
		collegeApplAfter12Label.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(collegeApplAfter12Label, "cell 0 1,alignx left,aligny center");

		collegeApplAfter12 = new NumberOrButton(
				"Доля школьников, поступающих в ВУЗы 1-2 уровня аккредетации после 12 классов", false);
		collegeApplAfter12.setMaxValue(1);
		paramsPanel.add(collegeApplAfter12, "cell 1 1,alignx center,aligny center");

		JLabel univerApplAfter12Label = new JLabel(
				"Доля школьников, поступающих в ВУЗы 3-4 уровня аккредетации после 12 классов");
		univerApplAfter12Label.setFont(new Font(font, Font.PLAIN, 14));
		paramsPanel.add(univerApplAfter12Label, "cell 0 2,alignx left,aligny center");

		univerApplAfter12 = new NumberOrButton(
				"Доля школьников, поступающих в ВУЗы 3-4 уровня аккредетации после 12 классов", false);
		univerApplAfter12.setMaxValue(1);
		paramsPanel.add(univerApplAfter12, "cell 1 2,alignx center,aligny center");

		JPanel tableAndGrafPanel = new JPanel();
		contentPane.add(tableAndGrafPanel, BorderLayout.CENTER);
		tableAndGrafPanel.setLayout(new BorderLayout(0, 0));

		graf = new FreeChartGraf(true, "0");
		graf.setMinimumSize(new Dimension(400, 400));
		graf.setPreferredSize(new Dimension(400, 1400));
		tableAndGrafPanel.add(graf, BorderLayout.CENTER);

		verticalBox = Box.createVerticalBox();
		verticalBox.setMinimumSize(new Dimension(200, 400));
		tableAndGrafPanel.add(verticalBox, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 170));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setMaximumSize(new Dimension(2000, 100));
		tableAndGrafPanel.add(scrollPane, BorderLayout.SOUTH);

		resultTable = TableWithRowHeaders.setTable(null, Main.Year + 1, Main.ModelLenght + 1, seriesNames);
		scrollPane.setViewportView(resultTable);

		JPanel buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new MigLayout("", "[250px][grow][200px]", "[30px]"));

		for (int i = 0; i < 5; i++) {
			String name = seriesNames[i];
			if (i > 1) {
				name = Format.addBR(name, 34);
			}
			check.add(new JCheckBox(name));
			setCheckBox(check.get(i));
		}

		JButton btnCount = new JButton("\u0412\u044B\u0447\u0438\u0441\u043B\u0438\u0442\u044C");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (testData()) {
					try {
						Statistics newborns = getNewborns();
						double deathCoeff = Format.getDouble(deathCoef.getText());
						Statistics collegeAppl91 = collegeAppl9const.getStatistics();
						Statistics collegeApplAfter121 = collegeApplAfter12.getStatistics();
						Statistics univerApplAfter121 = univerApplAfter12.getStatistics();

						mathModel.Population pop = new mathModel.Population(newborns, deathCoeff,
								collegeAppl91, collegeApplAfter121, univerApplAfter121);
						ModelObjectsContainer.getInstance().setPopulation(pop);

						pop.proceed();
					} catch (DataRequiredEx e) {
						LOG.error(e.getMessage(), e);
					}
					setResultTableData();
				}
			}
		});
		btnCount.setMinimumSize(new Dimension(120, 28));
		btnCount.setPreferredSize(new Dimension(120, 28));
		btnCount.setMaximumSize(new Dimension(120, 28));
		btnCount.setFont(new Font(font, Font.PLAIN, 14));
		buttonsPanel.add(btnCount, "cell 0 0,alignx left,growy");

		JButton btnOK = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose();
			}
		});
		btnOK.setMinimumSize(new Dimension(120, 28));
		btnOK.setPreferredSize(new Dimension(120, 28));
		btnOK.setFont(new Font(font, Font.PLAIN, 14));
		btnOK.setMaximumSize(new Dimension(120, 28));
		buttonsPanel.add(btnOK, "flowx,cell 2 0,alignx right,growy");

		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setMinimumSize(new Dimension(120, 28));
		btnCancel.setPreferredSize(new Dimension(120, 28));
		btnCancel.setMaximumSize(new Dimension(120, 28));
		btnCancel.setFont(new Font(font, Font.PLAIN, 14));
		buttonsPanel.add(btnCancel, "cell 2 0,alignx right,growy");

		refresh();
	}

	void setTableNewborns(double[][] newbornsData) {
		int columnCount = 18;
		newborns = TableInOneRow.setTable(newbornsData, Main.Year - 17, columnCount);
		newbornsScrollPane.setViewportView(newborns);
	}

	Statistics getNewborns() throws DataRequiredEx {
		Statistics statistics = new Statistics();
		for (int i = 0; i < newborns.getColumnCount(); i++) {
			if (newborns.getValueAt(0, i) == null) {
				errList = new ArrayList<String>();
				errList.add("Статистика новорожденных за 18 лет");
				throw new DataRequiredEx(errList);
			} else {
				statistics.add(
						Integer.parseInt(newborns.getColumnModel().getColumn(i).getHeaderValue().toString()),
						Format.getDouble(newborns.getValueAt(0, i).toString()));
			}
		}

		return statistics;
	}

	void setResultTableData() {
		mathModel.Population population = ModelObjectsContainer.getInstance().getPopulation();
		for (int i = 0; i < resultTable.getColumnCount() - 1; i++) {
			resultTable.getModel().setValueAt(roundValue(population.getSchoolGrad9().getValue(i)), 0, i + 1);
			resultTable.getModel().setValueAt(roundValue(population.getSchoolGrad12().getValue(i)), 1, i + 1);
			resultTable.getModel().setValueAt(roundValue(population.getCollegeApplAmongGrad9().getValue(i)),
					2, i + 1);
			resultTable.getModel().setValueAt(roundValue(population.getCollegeApplAmongGrad12().getValue(i)),
					3, i + 1);
			resultTable.getModel().setValueAt(roundValue(population.getUniverApplAmongGrad12().getValue(i)),
					4, i + 1);
		}
	}

	public int roundValue(double val) {
		BigDecimal value = new BigDecimal(val);
		value = value.setScale(0, BigDecimal.ROUND_HALF_UP);
		return value.intValue();
	}

	public void refresh() {
		mathModel.Population population = ModelObjectsContainer.getInstance().getPopulation();
		if (population != null) {
			try {
				deathCoef.setText(String.valueOf(population.getdeathCoef()));
				collegeAppl9const.setValue(population.getcollegeAppl9());
				collegeApplAfter12.setValue(population.getcollegeApplAfter12());
				univerApplAfter12.setValue(population.getuniverApplAfter12());
				setTableNewborns(population.getnewborns());
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	void graf() {
		double[][] xy = new double[6][resultTable.getColumnCount() - 1];
		for (int i = 1; i < resultTable.getColumnCount(); i++) {
			try {
				xy[0][i - 1] = Main.Year + i;// координаты иксов
				for (int j = 0; j < 5; j++) {
					if (check.get(j).isSelected()) {
						try {
							xy[j + 1][i - 1] = Double.parseDouble(resultTable.getValueAt(j, i).toString());
						} catch (Exception e) {
							xy[j + 1][i - 1] = 0;
							LOG.error(e.getMessage(), e);
						}
					} else {
						xy[j + 1][0] = -1;
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		graf.refresh(xy, seriesNames);
	}

	boolean testData() {
		boolean answer = true;
		Statistics college = collegeApplAfter12.getStatistics();
		Statistics univer = univerApplAfter12.getStatistics();
		for (int i = 0; i < college.size(); i++) {
			if ((college.getValue(i) + univer.getValue(i)) > 1) {
				answer = false;
				JOptionPane.showMessageDialog(null,
						"Количество выпускников 11 классов не может привышать 100%", "Ошибка ввода данных",
						JOptionPane.ERROR_MESSAGE);
				return answer;
			}
		}
		return answer;
	}

	void setCheckBox(JCheckBox checkBox) {
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				graf();
			}
		});
		checkBox.setFont(new Font(font, Font.PLAIN, 13));
		verticalBox.add(checkBox);
	}
}
