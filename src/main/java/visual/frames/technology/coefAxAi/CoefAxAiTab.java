package visual.frames.technology.coefAxAi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import visual.frames.modelResult.XLS;

public class CoefAxAiTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5057589428199238534L;
	private static Logger LOG = Logger.getLogger(CoefAxAiTab.class);
	private int defaultRowSize = 0;
	private JButton calculateButton;
	private CoefAxAi coefAx;
	private CoefAxAi coefAi;
	private final JSlider slider;
	private final ButtonGroup buttonGroupCoefAxAi = new ButtonGroup();

	public JButton getCalculateButtonAtCoefAxAiTab() {
		return calculateButton;
	}

	public CoefAxAi getCoefAx() {
		return coefAx;
	}

	public CoefAxAi getCoefAi() {
		return coefAi;
	}

	public CoefAxAiTab(String[][] data, String[] columnNames, int defaultRowSize) {
		this.defaultRowSize = defaultRowSize;
		setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		topPanel.setPreferredSize(new Dimension(3000, 28));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int sliderValue = slider.getValue();
				if (coefAx != null && coefAi != null) {
					if (coefAx.isVisible()) {
						coefAx.refreshRowSize(sliderValue);
					} else {
						coefAi.refreshRowSize(sliderValue);
					}
				}
			}
		});
		setSlider(slider);
		topPanel.add(slider, BorderLayout.WEST);

		JPanel panelCoefAxAiRadio = new JPanel();
		topPanel.add(panelCoefAxAiRadio, BorderLayout.CENTER);

		final JRadioButton radioCoefAx = new JRadioButton(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0432\u044B\u043F\u0443\u0441\u043A\u0430");
		buttonGroupCoefAxAi.add(radioCoefAx);
		radioCoefAx.setSelected(true);
		panelCoefAxAiRadio.add(radioCoefAx);

		final JRadioButton radioCoefAi = new JRadioButton(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0438\u043C\u043F\u043E\u0440\u0442\u0430");
		buttonGroupCoefAxAi.add(radioCoefAi);
		panelCoefAxAiRadio.add(radioCoefAi);

		JButton importButton = new JButton(
				"\u0418\u043C\u043F\u043E\u0440\u0442 \u0434\u0430\u043D\u043D\u044B\u0445");
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (coefAx.isVisible()) {
					coefAx.setTable(XLS.readXls());
				} else {
					coefAi.setTable(XLS.readXls());
				}
			}
		});

		topPanel.add(importButton, BorderLayout.EAST);

		JPanel panelWithCoefAxAiTables = new JPanel();
		add(panelWithCoefAxAiTables, BorderLayout.CENTER);
		panelWithCoefAxAiTables.setLayout(new CardLayout(0, 0));

		coefAx = new CoefAxAi(data.clone(), columnNames);
		coefAx.setVisible(true);

		coefAi = new CoefAxAi(data.clone(), columnNames);
		coefAi.setVisible(false);

		// final CoefAxAi notUsedPanel = new CoefAxAi(data_, column_names);

		panelWithCoefAxAiTables.add(coefAx, "name_735194277027703");
		panelWithCoefAxAiTables.add(coefAi, "name_735201723077390");

		radioCoefAx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coefAx.setVisible(true);
				coefAi.setVisible(false);
			}
		});

		radioCoefAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coefAi.setVisible(true);
				coefAx.setVisible(false);
			}
		});

		calculateButton = new JButton(
				"\u0420\u0430\u0441\u0441\u0447\u0438\u0442\u0430\u0442\u044C \u043F\u043E\u043A\u0430\u0437\u0430\u0442\u0435\u043B\u0438");
		add(calculateButton, BorderLayout.SOUTH);
		calculateButton.setMinimumSize(new Dimension(150, 23));
		calculateButton.setMaximumSize(new Dimension(150, 23));
	}

	public void setSlider(JSlider slider) {
		if (defaultRowSize > 150) {
			defaultRowSize = 150;
		}
		if (defaultRowSize < 40) {
			defaultRowSize = 40;
		}
		slider.setBorder(new EmptyBorder(0, 90, 0, 0));
		slider.setMinimum(40);
		slider.setMaximum(150);
	}
}
