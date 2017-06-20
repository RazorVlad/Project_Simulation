package visual.frames.technology.AxAi;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import visual.frames.modelResult.XLS;

public class AxAiTab extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3828325824613504257L;
	private int defaultRowSize = 0;
	private final JSlider slider;
	private AxAi Ax;

	public AxAi getAx() {
		return Ax;
	}

	public AxAi getAi() {
		return Ai;
	}

	private AxAi Ai;
	JButton calculateButton;
	private final ButtonGroup buttonGroupAxAi = new ButtonGroup();

	public AxAiTab(int defaultRowSize) {
		this.defaultRowSize = defaultRowSize;
		setLayout(new BorderLayout(0, 0));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int sliderValue = slider.getValue();
				if (Ax != null && Ai != null) {
					if (Ax.isVisible()) {
						Ax.refreshRowSize(sliderValue);
					} else {
						Ai.refreshRowSize(sliderValue);
					}
				}
			}
		});
		setSlider(slider);

		JPanel panelWithAxAiTables = new JPanel();
		add(panelWithAxAiTables, BorderLayout.CENTER);
		panelWithAxAiTables.setLayout(new CardLayout(0, 0));

		Ax = new AxAi(0);
		panelWithAxAiTables.add(Ax, "name_64258037328551");
		Ai = new AxAi(1);
		panelWithAxAiTables.add(Ai, "name_64258037328551");

		calculateButton = new JButton(
				"\u0420\u0430\u0441\u0441\u0447\u0438\u0442\u0430\u0442\u044C \u043F\u043E\u043A\u0430\u0437\u0430\u0442\u0435\u043B\u0438");
		calculateButton.setMinimumSize(new Dimension(150, 23));
		calculateButton.setMaximumSize(new Dimension(150, 23));
//		calculateButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(calculateButton, BorderLayout.SOUTH);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0, 0));
		add(topPanel, BorderLayout.NORTH);

		topPanel.add(slider, BorderLayout.WEST);

		JPanel panelAxAiRadio = new JPanel();
		topPanel.add(panelAxAiRadio, BorderLayout.CENTER);

		final JRadioButton radioAx = new JRadioButton(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0432\u044B\u043F\u0443\u0441\u043A\u0430");
		buttonGroupAxAi.add(radioAx);
		panelAxAiRadio.add(radioAx);
		radioAx.setSelected(true);

		final JRadioButton radioAi = new JRadioButton(
				"\u041C\u0430\u0442\u0440\u0438\u0446\u0430 \u0438\u043C\u043F\u043E\u0440\u0442\u0430");
		buttonGroupAxAi.add(radioAi);
		panelAxAiRadio.add(radioAi);

		JButton importAxAiButton = new JButton(
				"\u0418\u043C\u043F\u043E\u0440\u0442 \u0434\u0430\u043D\u043D\u044B\u0445");
		importAxAiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Ax.isVisible()) {
					Ax.setTable(XLS.readXls());
				} else {
					Ai.setTable(XLS.readXls());
				}
			}
		});
		importAxAiButton.setHorizontalAlignment(SwingConstants.RIGHT);
		topPanel.add(importAxAiButton,BorderLayout.EAST);

		radioAx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ax.setVisible(true);
				Ai.setVisible(false);
			}
		});
		radioAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ax.setVisible(false);
				Ai.setVisible(true);
			}
		});
	}

	public void setSlider(JSlider slider) {
		if (defaultRowSize > 150) {
			defaultRowSize = 150;
		}
		if (defaultRowSize < 30) {
			defaultRowSize = 30;
		}
		slider.setBorder(new EmptyBorder(0, 90, 0, 0));
		slider.setMinimum(30);
		slider.setMaximum(150);
	}

	public JButton getCalculateButtonAtAxAiTab() {
		return calculateButton;
	}
}
