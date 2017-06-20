package visual.project_modeling.projectBaseTab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import mathModel.ProjectStorage;
import mathModel.Statistics;
import mathModel.project.Project;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXLabel;


public class ProjectBasePanel extends JPanel {

	private static final long serialVersionUID = -4515601819243921413L;
	DurationTab durationPanel;
	CostsTab costsPanel;
	TechnologyTab technologyPanel;
	UsingTab usingPanel;
	JPanel projectParamPanel;
	JPanel paramGroupsPanel;
	JPanel projectParamsPanel;
	JPanel panel8;
	GeneralInfo generalInfoPanel;
	JPanel panel10;
	JPanel panel11;
	JComboBox comboBox8;
	JComboBox comboBox7;
	JComboBox comboBox6;
	JComboBox comboBox1;
	JButton durationBtn;
	JButton usingBtn;
	JButton technologyBtn;
	JButton costsBtn;
	private static Logger LOG = Logger.getLogger(ProjectBasePanel.class);

	public String getfield() {
		return generalInfoPanel.getfield();
	}

	public double getK() {
		return technologyPanel.getK();
	}

	public String getProjectGroup() {
		return generalInfoPanel.getProjectGroup();
	}

	public int getProjectGroupIndex() {
		return generalInfoPanel.getProjectGroupIndex();
	}

	public int getBeginYear() {
		return durationPanel.getBeginYear();
	}

	public int getDuration() {
		return durationPanel.getDuration();
	}

	public int getDurationResearch() {
		return durationPanel.getDurationResearch();
	}

	public int getDurationDev() {
		return durationPanel.getDurationDev();
	}

	public int getDurationPrep() {
		return durationPanel.getDurationPrep();
	}

	public int getDeltaT() {
		return durationPanel.getDeltaT();
	}

	public String getProjectName() {
		return generalInfoPanel.getProjectName();
	}

	public int getProjectSphere() {
		return generalInfoPanel.getProjectSphere();
	}

	public String getProjectAim() {
		return generalInfoPanel.getProjectAim();
	}

	public double getProjectCostResearch() {
		return costsPanel.getProjectCostResearch();
	}

	public double getProjectCostDev() {
		return costsPanel.getProjectCostResearch();
	}

	public double getProjectCost() {
		return costsPanel.getProjectCost();
	}

	public Statistics getTotalCost() {
		return costsPanel.getTotalCost();
	}

	public Statistics getPowerCost() {
		return costsPanel.getPowerCost();
	}

	public Statistics getProjectPrice() {
		return technologyPanel.getProjectPrice();
	}

	public Statistics getLaborContent() {
		return usingPanel.getLaborContent();
	}

	public double getPower0() {
		return technologyPanel.getPower0();
	}

	public double getStaff0() {
		return technologyPanel.getStaff0();
	}

	public double getProductivity() {
		return usingPanel.getProductivity();
	}

	public Statistics getInvestments() {
		return costsPanel.getInvestments();
	}

	public Statistics getDemand() {
		return technologyPanel.getDemand();
	}

	public int getManufacturerVed() {
		return generalInfoPanel.getManufacturerVed();
	}

	public int getVedOwner() {
		return generalInfoPanel.getVedOwner();
	}

	public Statistics getFixed() {
		return technologyPanel.getFixed();
	}

	public int getAssetsOutcomeMethod() {
		return technologyPanel.getAssetsOutcomeMethod();
	}

	public Statistics getLoad() {
		return usingPanel.getLoad();
	}

	public Statistics getVarCost() {
		return usingPanel.getVarCost();
	}

	public ProjectBasePanel() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(new Rectangle(0, 0, 1300, 750));

		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(new BorderLayout(0, 0));

		generalInfoPanel = new GeneralInfo();
		add(generalInfoPanel, BorderLayout.NORTH);
		projectParamPanel = new JPanel();
		add(projectParamPanel, BorderLayout.CENTER);
		projectParamPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		projectParamPanel.setLayout(new BorderLayout(0, 0));

		paramGroupsPanel = new JPanel();
		paramGroupsPanel.setPreferredSize(new Dimension(200, 10));
		paramGroupsPanel
				.setBorder(new TitledBorder(
						new LineBorder(new Color(0, 0, 0)),
						"\u0413\u0440\u0443\u043F\u043F\u0430 \u043F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u043E\u0432",
						TitledBorder.CENTER, TitledBorder.TOP, null, null));
		projectParamPanel.add(paramGroupsPanel, BorderLayout.WEST);
		paramGroupsPanel.setLayout(new BoxLayout(paramGroupsPanel, BoxLayout.Y_AXIS));

		durationBtn = new JButton("\u0414\u043B\u0438\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C");
		durationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(0);
			}
		});
		setButtonDefaults(durationBtn);
		paramGroupsPanel.add(durationBtn);

		costsBtn = new JButton("\u0417\u0430\u0442\u0440\u0430\u0442\u044B");
		costsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(1);
			}
		});
		setButtonDefaults(costsBtn);
		paramGroupsPanel.add(costsBtn);

		technologyBtn = new JButton(
				"\u0422\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u044F \u043F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0441\u0442\u0432\u0430");
		technologyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(2);
			}
		});
		setButtonDefaults(technologyBtn);
		paramGroupsPanel.add(technologyBtn);

		usingBtn = new JButton(
				"\u0418\u0441\u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u043D\u0438\u0435 \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438");
		usingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(3);
			}
		});
		setButtonDefaults(usingBtn);
		paramGroupsPanel.add(usingBtn);

		projectParamsPanel = new JPanel();
		projectParamPanel.add(projectParamsPanel, BorderLayout.CENTER);
		projectParamsPanel.setLayout(new CardLayout(0, 0));

		durationPanel = new DurationTab();
		projectParamsPanel.add(durationPanel, "name_98361862218677");

		costsPanel = new CostsTab();
		projectParamsPanel.add(costsPanel, "name_98370271993191");

		technologyPanel = new TechnologyTab();
		projectParamsPanel.add(technologyPanel, "name_98376667157372");

		usingPanel = new UsingTab();
		projectParamsPanel.add(usingPanel, "name_98382786181719");

		JXLabel projectParamsLabel = new JXLabel();
		projectParamsLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		projectParamsLabel.setBorder(null);
		projectParamsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectParamsLabel
				.setText("<html><b>\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u044B \u043F\u0440\u043E\u0435\u043A\u0442\u0430:");
		projectParamPanel.add(projectParamsLabel, BorderLayout.NORTH);

	}

	public double[] getInvestmentsData() {
		return costsPanel.getInvestmentsData();
	}

	public void refresh(String name) {
		try {
			Project pr = ProjectStorage.getInstance().getProject(name);
			generalInfoPanel.refresh(name, pr);
			durationPanel.refresh(pr);
			costsPanel.refresh(pr);
			technologyPanel.refresh(pr);
			usingPanel.refresh(pr);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	void setButtonDefaults(JButton button) {
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.setMinimumSize(new Dimension(190, 26));
		button.setMaximumSize(new Dimension(200, 28));
		button.setPreferredSize(new Dimension(190, 26));
	}

	void setVisible(int type) {
		durationPanel.setVisible(type == 0);
		costsPanel.setVisible(type == 1);
		technologyPanel.setVisible(type == 2);
		usingPanel.setVisible(type == 3);
	}
}
