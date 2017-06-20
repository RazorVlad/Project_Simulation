package visual.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import visual.frames.main.Main;
import visual.save.VisualObjectsContainer;

import net.miginfocom.swing.MigLayout;

public class Param extends JDialog {

	private static final long serialVersionUID = 3962088445131697103L;
	private JPanel contentPane;

	public Param() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		setTitle("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u0440\u0430\u0441\u0447\u0435\u0442\u0430");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(new Rectangle(400, 200, 300, 150));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][]", "[][][][]"));

		JLabel startYearLabel = new JLabel(
				"\u041D\u0430\u0447\u0430\u043B\u044C\u043D\u044B\u0439 \u0433\u043E\u0434");
		startYearLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(startYearLabel, "cell 0 0,aligny center");

		final JSpinner startYear = new JSpinner();
		startYear.setPreferredSize(new Dimension(70, 25));
		startYear.setMinimumSize(new Dimension(70, 20));
		startYear.setModel(new SpinnerNumberModel(Main.Year, 2000, null,
				1));
		startYear.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(startYear, "cell 1 0,alignx right,aligny center");

		JLabel durationLabel = new JLabel(
				"\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C \u043C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F");
		durationLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(durationLabel, "cell 0 1,aligny center");

		final JSpinner duration = new JSpinner();
		duration.setPreferredSize(new Dimension(70, 25));
		duration.setMinimumSize(new Dimension(70, 20));
		duration.setModel(new SpinnerNumberModel(Main.ModelLenght, 1, 15, 1));
		duration.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(duration, "cell 1 1,alignx right,aligny center");

		JButton buttonOK = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer.parseInt(startYear.getValue().toString()));
				Main.Year = year;
				int durationValue = (Integer.parseInt(duration.getValue().toString()));
				Main.ModelLenght = durationValue;
				VisualObjectsContainer.getInstance().setStartYear(year);
				VisualObjectsContainer.getInstance().setDuration(durationValue);
				dispose();
			}
		});
		buttonOK.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(buttonOK, "cell 0 3 2 1,alignx center");

	}
}
