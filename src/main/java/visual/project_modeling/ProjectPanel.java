package visual.project_modeling;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import visual.project_modeling.modelingTab.ProjectModelingPanel;
import visual.project_modeling.projectBaseTab.ProjectBase;

public class ProjectPanel extends JPanel {

	private static final long serialVersionUID = 7068901883430943001L;
	double[][] xy;
	JPanel modelingPanel;
	JPanel projectPanel;
	final JButton newProjectBtn;
	final JButton projectBaseBtn;
	final JButton projectModelingBtn;
	final JButton portfolioModelingBtn;
	Color background;
	final ProjectModelingPanel projectModeling = new ProjectModelingPanel();
	private JPanel mainPanel;
	private ProjectBase projectBasePanel;
	private String font = "Calibri";

	public ProjectPanel() {
		setBorder(null);

		setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		add(topPanel, BorderLayout.NORTH);

		newProjectBtn = new JButton("\u041D\u043E\u0432\u044B\u0439 \u043F\u0440\u043E\u0435\u043A\u0442");
		background = newProjectBtn.getBackground();
		newProjectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectBasePanel
						.setProjectPassportLabel("\u041F\u0430\u0441\u043F\u043E\u0440\u0442 \u043F\u0440\u043E\u0435\u043A\u0442\u0430(\u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0439 \u0442\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u0438)");
				projectModeling.setVisible(false);
				projectBasePanel.baseVisible(true, false);
				projectBasePanel.setVisible(true);
				projectBaseBtn.setBackground(background);
				newProjectBtn.setBackground(new Color(100, 149, 237));
				projectModelingBtn.setBackground(background);
				portfolioModelingBtn.setBackground(background);
			}
		});
		newProjectBtn.setFont(new Font(font, Font.PLAIN, 14));
		newProjectBtn.setMaximumSize(new Dimension(210, 26));
		newProjectBtn.setMinimumSize(new Dimension(210, 26));
		newProjectBtn.setPreferredSize(new Dimension(210, 28));

		projectBaseBtn = new JButton(
				"\u0411\u0430\u0437\u0430 \u043F\u0440\u043E\u0435\u043A\u0442\u043E\u0432");
		projectBaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectBasePanel
						.setProjectPassportLabel("\u041F\u0430\u0441\u043F\u043E\u0440\u0442 \u043F\u0440\u043E\u0435\u043A\u0442\u0430(\u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0439 \u0442\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u0438)");
				projectModeling.setVisible(false);
				projectBasePanel.setVisible(true);
				projectBasePanel.baseVisible(false, true);
				newProjectBtn.setBackground(background);
				projectBaseBtn.setBackground(new Color(100, 149, 237));
				projectModelingBtn.setBackground(background);
				portfolioModelingBtn.setBackground(background);
			}
		});
		projectBaseBtn.setBackground(new Color(100, 149, 237));
		projectBaseBtn.setFont(new Font(font, Font.PLAIN, 14));
		projectBaseBtn.setMaximumSize(new Dimension(210, 26));
		projectBaseBtn.setMinimumSize(new Dimension(210, 26));
		projectBaseBtn.setPreferredSize(new Dimension(210, 28));
		projectBaseBtn.getModel().setPressed(true);

		projectModelingBtn = new JButton(
				"\u041C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435");
		projectModelingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				projectModeling.setVisible(true);
				projectBasePanel.baseVisible(false, false);
				projectBasePanel.setVisible(false);
				newProjectBtn.setBackground(background);
				projectModelingBtn.setBackground(new Color(100, 149, 237));
				projectBaseBtn.setBackground(background);
				portfolioModelingBtn.setBackground(background);
			}
		});
		projectModelingBtn.setFont(new Font(font, Font.PLAIN, 14));
		projectModelingBtn.setMaximumSize(new Dimension(210, 26));
		projectModelingBtn.setMinimumSize(new Dimension(210, 26));
		projectModelingBtn.setPreferredSize(new Dimension(210, 28));

		portfolioModelingBtn = new JButton(
				"\u041F\u043E\u0440\u0442\u0444\u0435\u043B\u044C\u043D\u043E\u0435 \u043C\u043E\u0434\u0435\u043B\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435");
		portfolioModelingBtn.setEnabled(false);
		portfolioModelingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newProjectBtn.setBackground(background);
				portfolioModelingBtn.setBackground(new Color(100, 149, 237));
				projectModelingBtn.setBackground(background);
				projectBaseBtn.setBackground(background);
			}
		});
		portfolioModelingBtn.setFont(new Font(font, Font.PLAIN, 14));
		portfolioModelingBtn.setMaximumSize(new Dimension(210, 26));
		portfolioModelingBtn.setMinimumSize(new Dimension(210, 26));
		portfolioModelingBtn.setPreferredSize(new Dimension(210, 28));
		GroupLayout glTopPanel = new GroupLayout(topPanel);
		glTopPanel.setHorizontalGroup(glTopPanel.createParallelGroup(Alignment.LEADING).addGroup(
				glTopPanel
						.createSequentialGroup()
						.addComponent(newProjectBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(projectBaseBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(projectModelingBtn, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(portfolioModelingBtn, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		glTopPanel.setVerticalGroup(glTopPanel
				.createParallelGroup(Alignment.LEADING)
				.addComponent(newProjectBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addComponent(projectBaseBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addComponent(projectModelingBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addComponent(portfolioModelingBtn, GroupLayout.PREFERRED_SIZE, 26,
						GroupLayout.PREFERRED_SIZE));
		topPanel.setLayout(glTopPanel);

		mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));

		projectBasePanel = new ProjectBase();
		mainPanel.add(projectBasePanel, "name_49132812912596");

		mainPanel.add(projectModeling, "name_49137981410515");
	}
}
