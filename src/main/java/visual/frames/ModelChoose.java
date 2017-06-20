package visual.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.RnD;
import mathModel.manufacturing.Manufacturing;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

public class ModelChoose extends JFrame {

	private static final long serialVersionUID = 8168725713889662568L;

	private JPanel contentPane;
	private String font="Calibri";
	private static Logger LOG = Logger.getLogger(ModelChoose.class);

	List<JCheckBox> eduList = new ArrayList<JCheckBox>();
	List<JCheckBox> niokrList = new ArrayList<JCheckBox>();
	List<JCheckBox> innList = new ArrayList<JCheckBox>();
	List<JCheckBox> ordList = new ArrayList<JCheckBox>();
	List<JCheckBox> finalList = new ArrayList<JCheckBox>();
	List<JPanel> panels = new ArrayList<JPanel>();
	List<JLabel> oraslLabels = new ArrayList<JLabel>();

	private final JLabel inn2Label = new JLabel("производство");
	private final JLabel ord2Label = new JLabel("производство");

	public ModelChoose() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		setBounds(new Rectangle(50, 50, 900, 600));
		setTitle("Выбор моделируемых отраслей промышленности");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new MigLayout("", "[][][][][][]", "[][][][][][][][][][][][][][][]"));

		for (int i = 0; i < 10; i++) {
			oraslLabels.add(new JLabel(mathModel.VedNames.getOtraslName(i)));
			eduList.add(new JCheckBox(""));
			niokrList.add(new JCheckBox(""));
			innList.add(new JCheckBox(""));
			ordList.add(new JCheckBox(""));
			finalList.add(new JCheckBox(""));
			panels.add(new JPanel());

			setPanel(panels.get(i));
			setLabel(oraslLabels.get(i), panels.get(i));
			setCheckBox(eduList.get(i));
			setCheckBox(niokrList.get(i));
			setCheckBox(innList.get(i));
			setCheckBox(ordList.get(i));
			setCheckBox(finalList.get(i));

			panel1.add(panels.get(i), "cell 0 " + (4 + i) + " 6 1,growx,aligny center");
			panels.get(i).add(eduList.get(i), "cell 1 0");
			panels.get(i).add(niokrList.get(i), "cell 2 0,aligny top");
			panels.get(i).add(innList.get(i), "cell 3 0,aligny top");
			panels.get(i).add(ordList.get(i), "cell 4 0,aligny top");
			panels.get(i).add(finalList.get(i), "cell 5 0");
		}

		JLabel topLabel = new JLabel(
				"\u041D\u0430\u043B\u0438\u0447\u0438\u0435 \u0438\u0441\u0445\u043E\u0434\u043D\u043E\u0439 \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u0438");
		topLabel.setFont(new Font(font, Font.PLAIN, 14));
		panel1.add(topLabel, "cell 1 0 4 1,alignx center");
		JLabel branchesLabel = new JLabel("  \u041E\u0442\u0440\u0430\u0441\u043B\u0438");
		branchesLabel.setMaximumSize(new Dimension(292, 30));
		branchesLabel.setMinimumSize(new Dimension(300, 0));
		branchesLabel.setFont(new Font(font, Font.PLAIN, 14));
		panel1.add(branchesLabel, "cell 0 1 1 2,alignx center,aligny center");

		JLabel educationLabel = new JLabel("Образование");
		setHeaderLabel(educationLabel);
		panel1.add(educationLabel, "cell 1 1 1 2,alignx center");

		JLabel niokrLabel = new JLabel("НИОКР");
		setHeaderLabel(niokrLabel);
		panel1.add(niokrLabel, "cell 2 1 1 2,alignx center");

		JLabel ord1Label = new JLabel("Рядовое");
		setHeaderLabel(ord1Label);
		panel1.add(ord1Label, "flowx,cell 3 1,alignx center");

		JLabel inn1Label = new JLabel("Инновационное");
		setHeaderLabel(inn1Label);
		panel1.add(inn1Label, "flowy,cell 4 1,alignx center");

		JLabel modelingLabel = new JLabel("Моделировать");
		setHeaderLabel(modelingLabel);
		panel1.add(modelingLabel, "cell 5 1 1 2,alignx center,aligny center");

		setHeaderLabel(ord2Label);
		panel1.add(ord2Label, "cell 3 2,alignx right");

		setHeaderLabel(inn2Label);
		panel1.add(inn2Label, "cell 4 2,alignx center");

		JButton btnOK = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isAnyChosen = false;
				List<Integer> chosenIndusties = new ArrayList<Integer>();
				for (int i = 0; i < 10; i++) {
					if (finalList.get(i).isSelected()) {
						chosenIndusties.add(i);
						isAnyChosen = true;
					}
				}
				if (isAnyChosen) {
					ModelObjectsContainer.getInstance().proceed();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Не выбрано ни одной отрасли", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setPreferredSize(new Dimension(100, 23));
		panel1.add(btnOK, "flowx,cell 4 14 2 1,alignx right");

		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setPreferredSize(new Dimension(100, 23));
		panel1.add(btnCancel, "cell 4 14 2 1,alignx right");

		Checked();

	}

	void Checked() {
		boolean[] niokr = checkNIOKR();
		boolean[][] ved = checkVed();
		boolean[] edu = checkEducation();
		checkEducation();

		for (int i = 0; i < 10; i++) {
			eduList.get(i).setSelected(edu[i]);
			niokrList.get(i).setSelected(niokr[i]);
			innList.get(i).setSelected(ved[0][i]);
			ordList.get(i).setSelected(ved[1][i]);
			if (edu[i] && niokr[i] && ved[0][i] && ved[1][i]) {
				finalList.get(i).setEnabled(true);
				panels.get(i).setBackground(SystemColor.menu);
			} else {
				finalList.get(i).setEnabled(false);
				panels.get(i).setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	boolean[] checkNIOKR() {
		boolean[] result = new boolean[10];
		for (int i = 0; i < 10; i++) {
			boolean isSet = true;
			RnD[] niokr = new RnD[] { ModelObjectsContainer.getInstance().getRndR(i),
					ModelObjectsContainer.getInstance().getRndD(i) };
			for (int j = 0; j < 2; j++) {
				try {
					niokr[0].getfinInt();
					niokr[0].getfinExt();
					niokr[0].getpartIncomeStaff();
					niokr[0].getcoefFinInt();
					niokr[0].getcoefFinExt();

					niokr[j].getStaff(0);
					niokr[j].getAssets(0);
					niokr[j].getcoefInvest();
					niokr[j].getcoefFin();
					niokr[j].getcoefProd();
					niokr[j].getcoefObs();
					niokr[j].getpartOutcomeStaff();
					niokr[j].getcoefNew();

				} catch (Exception e) {
					isSet = false;
					LOG.error(e.getMessage(), e);
				}
				result[i] = isSet;
			}
		}
		return result;
	}

	boolean[][] checkVed() {
		boolean[][] result = new boolean[2][10];
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 10; i++) {
				for (int j : mathModel.VedNames.getVeds(i)) {
					boolean isSet = true;
					Manufacturing ved;
					if (k == 0) {
						ved = ModelObjectsContainer.getInstance().getVedInn(j);
					} else {
						ved = ModelObjectsContainer.getInstance().getVedOrd(j);
					}
					try {
						ved.getProdPriceAt(0);
						ved.getextraCost(0);
						ved.getrawPrice(0);
						ved.getProdPriceAt(0);
						ved.getcoefProdPrice();
						ved.getcoefRawPrice();
						ved.getcoefSalaryChange();
						ved.getcoefExtraCost();
						ved.getAlfa();
						ved.getBeta();
						if (k == 1) {
							ved.getGamma();
							ved.getpartAmort();
							ved.getintangibles();
							ved.getworkingPart();
							ved.getworkingIntangiblesPart();
							ved.getcoefObsIntangibles();
						}
						ved.getA();
						ved.getStaff(0);
						ved.getcoefOutcome();
						ved.getfieldPart();
						ved.getAssets();
						ved.getcoefAmort1();
						ved.getcoefAmort2();
						ved.getpartForFunds();
						ved.getcoefObsFunds();
						ved.getwc();
						ved.getcoefWC();

					} catch (Exception e) {
						isSet = false;
						LOG.error(e.getMessage(), e);
					}
					result[k][i] = isSet;
				}
			}
		}
		return result;
	}

	boolean[] checkEducation() {
		boolean[] result = new boolean[10];
		for (int i = 0; i < 10; i++) {
			boolean isSet = true;
			Education edu = ModelObjectsContainer.getInstance().getEducation(i);
			try {
				edu.getcollegeStud(0);
				edu.getfieldCollegeAppl9();
				edu.getfieldCollegeAppl12();
				edu.getsuccCollegeGrad();
				edu.getuniverStud(0);
				edu.getuniverApplAmongCollegeGrad();
				edu.getfieldUniverAppl12();
				edu.getsuccUniverGrad();
				edu.getcollegeGradWork();
				edu.getuniverGradWork();
				edu.getuniverGradEdu();
				edu.getuniverGradRnD();
			} catch (Exception e) {
				isSet = false;
				if (i == 0) {
					LOG.error("i==0");
				}
				LOG.error(e.getMessage(), e);
			}
			result[i] = isSet;
		}
		return result;
	}

	void setCheckBox(JCheckBox checkBox) {
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setHorizontalTextPosition(SwingConstants.CENTER);
		checkBox.setMinimumSize(new Dimension(100, 20));
		checkBox.setMaximumSize(new Dimension(100, 20));
		checkBox.setFocusable(false);
		checkBox.setDisabledIcon(new ImageIcon(ModelChoose.class
				.getResource("/org/jdesktop/swingx/plaf/basic/resources/clear_rollover.gif")));
		checkBox.setDisabledSelectedIcon(new ImageIcon(ModelChoose.class
				.getResource("/junit/swingui/icons/ok.gif")));

		checkBox.setEnabled(false);
	}

	void setHeaderLabel(JLabel label) {
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font(font, Font.PLAIN, 13));
		label.setMaximumSize(new Dimension(100, 110));
		label.setMinimumSize(new Dimension(100, 10));
	}

	void setPanel(JPanel panel) {
		panel.setMaximumSize(new Dimension(10000, 40));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(new MigLayout("", "[][][][][][]", "[][]"));
	}

	void setLabel(JLabel label, JPanel panel) {
		label.setFont(new Font(font, Font.PLAIN, 13));
		label.setMinimumSize(new Dimension(285, 0));
		panel.add(label, "cell 0 0,aligny center");
	}

}
