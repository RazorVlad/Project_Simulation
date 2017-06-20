package visual.frames.technology;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import mathModel.ModelObjectsContainer;
import mathModel.fieldsInteraction.FieldsInteraction;

import org.apache.log4j.Logger;

import visual.frames.main.Main;
import visual.frames.technology.AxAi.AxAiTab;
import visual.frames.technology.bMatrix.BMatrixTab;
import visual.frames.technology.coefAxAi.CoefAxAiTab;
import visual.save.VisualObjectsContainer;

public class Technology extends JFrame {

	private static final long serialVersionUID = -5849583660434746213L;

	private static Logger LOG = Logger.getLogger(Technology.class);
	private String[][] data_ = new String[26][28];
	private String[] column_names = new String[26];
	private AxAiTab axAiTab;
	private CoefAxAiTab coefAxAiTab;
	java.awt.Color color = new java.awt.Color(214, 217, 223);
	private BMatrixTab bMatrixTab;
	private int defaultRowSize = 0;

	public Technology() {
		setTitle("\u0422\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u044F \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430");

		Rectangle screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int screenWidth = screenRect.width;
		int screenHeight = screenRect.height;
		// int mainWndW = (screenWidth / 8) * 6;
		// int mainWndH = (screenHeight / 8) * 6;
		// int mainWndX = screenWidth / 2 - mainWndW / 2;
		// int mainWndY = screenHeight / 2 - mainWndH / 2;
		defaultRowSize = (int) ((screenWidth * 2 - 380) / 25);
		// setLocation(mainWndX, mainWndY);
		// setSize(new Dimension(mainWndW, mainWndH));
		setLocation(0, 0);
		setSize(new Dimension(screenWidth, screenHeight));

		for (int i = 0; i < data_.length - 1; i++) {
			data_[i][0] = mathModel.VedNames.getVedName(i);
		}
		data_[6][0] = "Обработка древесины и изготовление изделий из древесины и корка";
		data_[25][0] = "Прочие виды экономической деятельности";
		for (int i = 0; i < 5; i++)
			column_names[i] = mathModel.VedNames.getVedName(i);
		column_names[5] = "<html>" + addBR(mathModel.VedNames.getVedName(5), 20);
		column_names[6] = "<html>" + addBR("Обработка и изготовление изделий из древесины и корка", 24);
		column_names[7] = "<html>" + addBR(mathModel.VedNames.getVedName(7), 19);
		column_names[8] = mathModel.VedNames.getVedName(8);
		column_names[9] = "<html>" + addBR(mathModel.VedNames.getVedName(9), 18);
		column_names[10] = "<html>" + addBR(mathModel.VedNames.getVedName(10), 22);
		column_names[11] = "<html>" + addBR(mathModel.VedNames.getVedName(11), 31);
		column_names[12] = mathModel.VedNames.getVedName(12);
		column_names[13] = "<html>" + addBR(mathModel.VedNames.getVedName(13), 22);
		column_names[14] = "<html>" + addBR(mathModel.VedNames.getVedName(14), 19);
		column_names[15] = mathModel.VedNames.getVedName(15);
		column_names[16] = "<html>" + addBR(mathModel.VedNames.getVedName(16), 20);
		column_names[17] = "<html>" + addBR(mathModel.VedNames.getVedName(17), 25);
		column_names[18] = "<html>" + addBR(mathModel.VedNames.getVedName(18), 12);
		column_names[19] = "<html>" + addBR(mathModel.VedNames.getVedName(19), 12);
		column_names[20] = "<html>" + addBR(mathModel.VedNames.getVedName(20), 29);
		column_names[21] = "<html>" + addBR(mathModel.VedNames.getVedName(21), 19);
		column_names[22] = "<html>" + addBR(mathModel.VedNames.getVedName(22), 22);
		column_names[23] = "<html>" + mathModel.VedNames.getVedName(23);
		column_names[24] = "<html>" + addBR(mathModel.VedNames.getVedName(24), 27);
		column_names[25] = "<html>Прочие виды<br>экономической деятельности";

		getContentPane().setLayout(new BorderLayout());

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		axAiTab = new AxAiTab(defaultRowSize);
		axAiTab.getCalculateButtonAtAxAiTab().addActionListener(new ButtonActionListener(tabbedPane));
		tabbedPane.addTab(
				"\u0417\u0430\u0442\u0440\u0430\u0442\u044B \u0432\u044B\u043F\u0443\u0441\u043A\u0430",
				null, axAiTab, null);

		coefAxAiTab = new CoefAxAiTab(data_, column_names, defaultRowSize);
		tabbedPane
				.addTab("\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0434\u0435\u0437\u0430\u0433\u0440\u0435\u0433\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F",
						null, coefAxAiTab, null);
		coefAxAiTab.getCalculateButtonAtCoefAxAiTab().addActionListener(new ButtonActionListener(tabbedPane));

		bMatrixTab = new BMatrixTab(data_, column_names, defaultRowSize);

		tabbedPane
				.addTab("\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u043C\u0430\u0442\u0435\u0440\u0438\u0430\u043B\u043E\u0435\u043C\u043A\u043E\u0441\u0442\u0438",
						null, bMatrixTab, null);
		tabbedPane.setEnabledAt(2, false);

		setVisible(true);
		refresh();
	}

	public static void main(String[] args) {
		new Technology();
	}

	class ButtonActionListener implements ActionListener {
		JTabbedPane tabbedPane;

		public ButtonActionListener(JTabbedPane tabbedPane) {
			this.tabbedPane = tabbedPane;
		}

		public void actionPerformed(ActionEvent arg0) {
			boolean axset = axAiTab.getAx().isDataSet();
			boolean aiset = axAiTab.getAi().isDataSet();
			boolean coefaxset = coefAxAiTab.getCoefAx().isDataSet();
			boolean coefaiset = coefAxAiTab.getCoefAi().isDataSet();
			boolean coefaxOk = coefAxAiTab.getCoefAx().isDataOk();
			boolean coefaiOk = coefAxAiTab.getCoefAi().isDataOk();
			if (axset && aiset && coefaxset && coefaiset && coefaxOk && coefaiOk) {
				
				FieldsInteraction fi = ModelObjectsContainer.getInstance().getFieldsInteraction();
				fi.setAx(axAiTab.getAx().getTable(1));
				fi.setAi(axAiTab.getAi().getTable(1));
				fi.setCoefsAx(coefAxAiTab.getCoefAx().getTable());
				fi.setCoefsAi(coefAxAiTab.getCoefAi().getTable());
				fi.setLastColumnAx(axAiTab.getAx().getLastColumn());
				fi.setLastColumnAi(axAiTab.getAi().getLastColumn());
				bMatrixTab.setBMatrix(fi.getBx(), fi.getBi(), fi.getBSum());
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setSelectedIndex(2);
				saveData();

			} else {
				String s = "";
				if (!axset) {
					s += "таблица затрат выпуска для матрицы выпуска<br>";
				}
				if (!aiset) {
					s += "таблица затрат выпуска для матрицы импорта<br>";
				}
				if (!coefaxset) {
					s += "матрица дезагрегирования для матрицы выпуска<br>";
				}
				if (!coefaiset) {
					s += "матрица дезагрегирования для матрицы импорта<br>";
				}
				if (!coefaxOk) {
					s += "в матрице дезагрегирования для выпуска не верна сумма области<br>";
				}
				if (!coefaiOk) {
					s += "в матрице дезагрегирования для импорта не верна сумма области<br>";
				}
				JOptionPane.showMessageDialog(null, "<html>Введены не все данные:<br>" + s, "Ошибка ввода",
						JOptionPane.ERROR_MESSAGE);
				LOG.error("<html>Введены не все данные:<br>" + s);
			}
		}
	}

	public void saveData() {
		visual.save.Technology_save tech = VisualObjectsContainer.getInstance().getTechnology();
		tech.setAi(axAiTab.getAi().getTable(0));
		tech.setAx(axAiTab.getAx().getTable(0));
		tech.setCoefAi(coefAxAiTab.getCoefAi().getTable());
		tech.setCoefAx(coefAxAiTab.getCoefAx().getTable());
		tech.setBi(bMatrixTab.getBi());
		tech.setBx(bMatrixTab.getBx());
		tech.setBsum(bMatrixTab.getBsum());
	}

	public void refresh() {
		visual.save.Technology_save tech = VisualObjectsContainer.getInstance().getTechnology();
		if (tech != null && !Main.path.equals("")) {
			try {
				axAiTab.getAi().setTable(tech.getAi());
				axAiTab.getAx().setTable(tech.getAx());
				coefAxAiTab.getCoefAi().setTable(tech.getCoefAi());
				coefAxAiTab.getCoefAx().setTable(tech.getCoefAx());
				bMatrixTab.setBMatrix(tech.getBx(), tech.getBi(), tech.getBsum());
			} catch (Exception e) {
				LOG.error("Can't open technology save", e);
			}
		}
	}

	public static String addBR(String name, int n) {
		name = name.substring(0, n) + "<br>" + name.substring(n);
		return name;
	}
}