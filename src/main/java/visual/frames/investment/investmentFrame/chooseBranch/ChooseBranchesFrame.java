package visual.frames.investment.investmentFrame.chooseBranch;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import visual.frames.investment.investmentFrame.InvestTypePanel;
import visual.frames.main.Main;
import net.miginfocom.swing.MigLayout;

public class ChooseBranchesFrame extends JFrame {

	private static final long serialVersionUID = -4078204493102789218L;
	private JPanel contentPane;
	double[] ivestOnYears;
	private List<JCheckBox> checkBoxes;
	boolean[] invOnBranches;

	public ChooseBranchesFrame(double[] ivestYears, boolean[] invBranches) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		setBounds(new Rectangle(50, 50, 373, 322));
		this.ivestOnYears = ivestYears.clone();
		this.invOnBranches = invBranches.clone();
		setTitle("\u0420\u0430\u0441\u043F\u0440\u0435\u0434\u0435\u043B\u0435\u043D\u0438\u0435 \u0438\u043D\u0432\u0435\u0441\u0442\u0438\u0446\u0438\u0439 \u043F\u043E \u043E\u0442\u0440\u0430\u0441\u043B\u044F\u043C");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][]"));

		JLabel lblChooseBranch = new JLabel("Выберите отрасли:");
		lblChooseBranch.setFont(new Font("Calibri", Font.PLAIN, 13));
		contentPane.add(lblChooseBranch, "cell 0 0");

		checkBoxes = new ArrayList<JCheckBox>();

		for (int i = 0; i < 10; i++) {
			checkBoxes.add(new JCheckBox(mathModel.VedNames.getOtraslName(i)));
			checkBoxes.get(i).setFont(new Font("Calibri", Font.PLAIN, 13));
			contentPane.add(checkBoxes.get(i), "cell 0 " + (i + 1));
			checkBoxes.get(i).setSelected(invOnBranches[i]);
		}

		JButton btnCancel = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InvestTypePanel.setInvestOnBranches(SetInvestOnBranches(Count()));
				dispose();
			}
		});
		btnCancel.setMinimumSize(new Dimension(105, 26));
		btnCancel.setMaximumSize(new Dimension(105, 26));
		contentPane.add(btnCancel, "flowx,cell 0 11,alignx right");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 13));

		JButton button = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setMinimumSize(new Dimension(105, 26));
		button.setMaximumSize(new Dimension(105, 26));
		contentPane.add(button, "cell 0 11,alignx right");
		button.setFont(new Font("Calibri", Font.PLAIN, 13));
	}

	Integer Count() {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			if (checkBoxes.get(i).isSelected()) {
				count++;
			}
		}
		return count;
	}

	double[][] SetInvestOnBranches(int count) {
		double[][] investOnBranches = new double[10][ivestOnYears.length];
		for (int i = 0; i < Main.ModelLenght; i++) {
			for (int j = 0; j < 10; j++) {
				if (checkBoxes.get(j).isSelected()) {
					investOnBranches[j][i] = ivestOnYears[i] / count;
				} else {
					investOnBranches[j][i] = 0;
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			invOnBranches[i] = checkBoxes.get(i).isSelected();
		}
		InvestTypePanel.setInvOnBranches(invOnBranches);
		InvestTypePanel.setInvestOnBranches(investOnBranches);
		return investOnBranches;
	}
}
