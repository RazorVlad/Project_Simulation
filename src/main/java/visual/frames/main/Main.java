package visual.frames.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import mathModel.ModelObjectsContainer;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTaskPane;

import visual.Format;
import visual.frames.modelResult.ModelResult;
import visual.planning.Planning_panel;
import visual.project_modeling.ProjectPanel;
import visual.prom_modeling.niokr.NIOKR_panel;
import visual.prom_modeling.stud.Stud_panel;
import visual.prom_modeling.ved.Ved_panel;
import visual.topMenu.FileOperations;
import visual.topMenu.TopMenu;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Main extends JFrame {

	private static final long serialVersionUID = -8013202239453523973L;

	static ModelObjectsContainer model = ModelObjectsContainer.getInstance();

	private int monitorWidth = 800;
	private int monitorHeight = 600;
	private String font = "Calibri";
	public static int Year = 2012;
	public static int ModelLenght = 3;
	static JXLabel lblName;
	static String name;
	static String otrasl_name;
	static String ved_name;
	static byte vedIndex = 0;
	static byte otraslIndex = 0;
	static JPanel NIOKRpanel;
	static JPanel VEDpanel;
	static JPanel STUDpanel;
	private Box branchList;
	public static String path = "";
	static Map<Byte, NIOKR_panel> NiokrMap = new HashMap<Byte, NIOKR_panel>();
	static Map<Byte, Ved_panel> VedMap = new HashMap<Byte, Ved_panel>(25);
	static Map<Byte, Stud_panel> StudMap = new HashMap<Byte, Stud_panel>();
	private Planning_panel panelPlaning;
	static ModelResult panel_3;
	static JPanel panel2;
	JTabbedPane tabbedPane;
	final ProjectPanel panelModelProject;
	private List<JXTaskPane> taskList = new ArrayList<JXTaskPane>();
	private List<JLabel> studLabels = new ArrayList<JLabel>();
	private List<JLabel> niokrLabels = new ArrayList<JLabel>();
	private List<JLabel> vedLabels = new ArrayList<JLabel>();
	private JButton okBtn;
	private JButton cancelBtn;
	static JLabel choosenLabel;
	private static Logger LOG = Logger.getLogger(Main.class);
	private static String logProperties = "/log4j.properties";
	private String title = "Industry Simulation";
	private TopMenu topMenu;

	public static String getLabelName() {
		return name;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PropertyConfigurator.configure(getClass().getResource(logProperties));
				LOG.info("Entering application.");
				Main frame;
				try {
					frame = new Main();
					frame.setResizable(true);
					frame.setUndecorated(false);
					frame.setVisible(true);
				} catch (Exception e) {
					LOG.error("Error on create frame", e);
				}
			}
		});
	}

	public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(0, 0, monitorWidth, monitorHeight);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			LOG.error("Error on set LookAndFeel", e);
		}

		monitorWidth = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getWidth();
		monitorHeight = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
				.getHeight();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setFont(new Font(font, Font.PLAIN, 14));

		setContentPane(tabbedPane);

		Panel promModelPanel = new Panel();
		promModelPanel.setFont(new Font("Cambria", Font.PLAIN, 12));
		promModelPanel.setPreferredSize(new Dimension(10, 600));
		tabbedPane.addTab("Моделирование промышленности", null, promModelPanel, null);
		tabbedPane.setEnabledAt(0, true);
		promModelPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel1 = new JPanel();
		promModelPanel.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new CardLayout(0, 0));

		panel2 = new JPanel();
		panel1.add(panel2, "name_746706824836980");
		panel2.setLayout(new BorderLayout(0, 0));

		lblName = new JXLabel();
		lblName.setBackground(SystemColor.inactiveCaption);

		JPanel inputPanel = new JPanel();

		inputPanel.setPreferredSize(new Dimension(1300, 700));
		inputPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane inputScroll = new JScrollPane(inputPanel);
		inputScroll.setBorder(null);

		panel2.add(inputScroll, BorderLayout.CENTER);

		inputPanel.add(lblName, BorderLayout.NORTH);
		lblName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setText("\u041D\u0418\u041E\u041A\u0420 [\u041F\u0438\u0449\u0435\u0432\u0430\u044F \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C]");
		name = lblName.getText();
		lblName.setFont(new Font(font, Font.PLAIN, 14));
		lblName.setPreferredSize(new Dimension(300, 25));
		lblName.setMaximumSize(new Dimension(300, 30));
		lblName.setMinimumSize(new Dimension(300, 30));

		JPanel panel = new JPanel();
		inputPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		NIOKRpanel = new JPanel();
		panel.add(NIOKRpanel, "name_123700124921848");
		NIOKRpanel.setLayout(new CardLayout(0, 0));

		STUDpanel = new JPanel();
		panel.add(STUDpanel, "name_47157715158603");
		STUDpanel.setLayout(new CardLayout(0, 0));

		VEDpanel = new JPanel();
		panel.add(VEDpanel, "name_338431018794978");
		VEDpanel.setLayout(new CardLayout(0, 0));

		JPanel branchesPanel = new JPanel();
		panel2.add(branchesPanel, BorderLayout.WEST);
		branchesPanel.setMaximumSize(new Dimension(200, 32767));
		branchesPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		branchesPanel.setPreferredSize(new Dimension(286, 150));
		branchesPanel.setLayout(new BorderLayout(0, 0));

		branchList = Box.createVerticalBox();
		branchList.setMaximumSize(new Dimension(200, 2000));
		branchesPanel.add(branchList);

		setVedMap();

		setTaskPanePanel();

		JXHeader header = new JXHeader();
		header.setFont(new Font(font, Font.PLAIN, 14));
		header.setMaximumSize(new Dimension(250, 2147483647));
		header.setPreferredSize(new Dimension(300, 45));
		header.setBorder(new LineBorder(Color.GRAY, 1, true));
		header.setTitle("\u041E\u0442\u0440\u0430\u0441\u043B\u0438 \u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		header.setTitleFont(new Font("Cambria", Font.BOLD, 14));
		branchesPanel.add(header, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel();
		panel2.add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		FormLayout flPanelButtons = new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow(15)"),
				ColumnSpec.decode("120px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("120px"), }, new RowSpec[] { RowSpec.decode("28px"), });
		flPanelButtons.setHonorsVisibility(false);
		buttonsPanel.setLayout(flPanelButtons);

		okBtn = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModelObjectsContainer modelOC = ModelObjectsContainer.getInstance();
				try {
					if (NIOKRpanel.isVisible()) {
						NIOKR_panel niokr = NiokrMap.get(otraslIndex);
						NIOKR.set(niokr, otraslIndex, modelOC);
					}
					if (VEDpanel.isVisible()) {
						Ved_panel ved = VedMap.get(vedIndex);
						Ved.set(vedIndex, otraslIndex, ved, modelOC);
					}
					if (STUDpanel.isVisible()) {
						Stud_panel stud = StudMap.get(otraslIndex);
						Stud.set(stud, otraslIndex, modelOC);
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		});
		okBtn.setMaximumSize(new Dimension(100, 28));
		okBtn.setMinimumSize(new Dimension(100, 26));
		okBtn.setPreferredSize(new Dimension(120, 28));
		buttonsPanel.add(okBtn, "2, 1, right, fill");

		cancelBtn = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NIOKRpanel.isVisible()) {
					NiokrMap.get(otraslIndex).refresh(otraslIndex);
				}
				if (VEDpanel.isVisible()) {
					VedMap.get(vedIndex).refresh(vedIndex);
				}
				if (STUDpanel.isVisible()) {
					StudMap.get(otraslIndex).refresh(otraslIndex);
				}
			}
		});
		cancelBtn.setMaximumSize(new Dimension(100, 28));
		cancelBtn.setMinimumSize(new Dimension(100, 26));
		cancelBtn.setPreferredSize(new Dimension(120, 28));
		buttonsPanel.add(cancelBtn, "4, 1, right, bottom");

		VEDpanel.setVisible(false);
		STUDpanel.setVisible(false);
		panel_3 = new ModelResult();
		panel1.add(panel_3, "name_746714609168326");

		topMenu = new TopMenu(monitorWidth);
		topMenu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		promModelPanel.add(topMenu, BorderLayout.NORTH);

		topMenu.getOpen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (FileOperations.open()) {
					setTitle(title + " [" + path + "]");
				}
			}
		});

		topMenu.getSave_as().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (FileOperations.saveAs()) {
					setTitle(title + " [" + path + "]");
				}
			}
		});

		Panel projectModelPanel = new Panel();
		projectModelPanel.setFont(new Font(font, Font.PLAIN, 14));
		tabbedPane.addTab("Моделирование проектов", null, projectModelPanel, null);
		tabbedPane.setEnabledAt(1, true);
		projectModelPanel.setLayout(new BorderLayout(0, 0));

		panelModelProject = new ProjectPanel();
		panelModelProject.setPreferredSize(new Dimension(1600, 700));

		JScrollPane projectModelScroll = new JScrollPane(panelModelProject);
		projectModelScroll.setBorder(null);

		projectModelPanel.add(projectModelScroll);

		JPanel planing = new JPanel();
		planing.setFont(new Font(font, Font.PLAIN, 14));
		planing.setBorder(null);
		tabbedPane.addTab("Планирование развития", null, planing, null);
		tabbedPane.setEnabledAt(2, true);
		planing.setLayout(new BorderLayout(0, 0));

		panelPlaning = new Planning_panel();
		panelPlaning.setPreferredSize(new Dimension(1590, 700));
		panelPlaning.setBorder(null);

		JScrollPane planingScroll = new JScrollPane(panelPlaning);
		projectModelScroll.setBorder(null);
		planing.add(planingScroll);

		Panel helpPanel = new Panel();
		helpPanel.setFont(new Font(font, Font.PLAIN, 14));
		tabbedPane.addTab("\u0421\u043F\u0440\u0430\u0432\u043A\u0430", null, helpPanel, null);
		tabbedPane.setEnabledAt(3, true);
		helpPanel.setLayout(new BorderLayout(0, 0));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(title);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\\u0411\u0435\u043B\u043E\u0447\u043A\u0430\\Desktop\\522254.jpg"));

	}

	private void setTaskPanePanel() {
		byte vedLabelsIndex = 0;
		boolean hide;
		for (byte i = 0; i < 10; i++) {
			NiokrMap.put(i, new NIOKR_panel(i));
			NIOKRpanel.add(NiokrMap.get(i), "ниокр" + i);

			StudMap.put(i, new Stud_panel(i));
			STUDpanel.add(StudMap.get(i), "образование" + i);

			taskList.add(new JXTaskPane());
			branchList.add(taskList.get(i));

			hide = (i != 0);
			setTaskPane(hide, i);
			NiokrMap.get(i).setVisible(!hide);
			StudMap.get(i).setVisible(!hide);

			studLabels.add(new JLabel("Образование"));
			studLabels.get(i).setFont(new Font(font, Font.PLAIN, 12));
			studLabels.get(i).addMouseListener(
					new TaskPaneMouseListener("Образование", mathModel.VedNames.getOtraslName(i), 0,
							studLabels.get(i)));
			niokrLabels.add(new JLabel("НИОКР"));
			niokrLabels.get(i).setFont(new Font(font, Font.PLAIN, 12));
			niokrLabels.get(i).addMouseListener(
					new TaskPaneMouseListener("НИОКР", mathModel.VedNames.getOtraslName(i), 1, niokrLabels
							.get(i)));
			StringBuilder rows = new StringBuilder("[][]");
			for (int j = 0; j < mathModel.VedNames.getVeds(i).length; j++) {
				rows.append("[19px]");
			}
			taskList.get(i).getContentPane().setLayout(new MigLayout("", "[273px]", rows.toString()));

			taskList.get(i).getContentPane().add(studLabels.get(i), "cell 0 0,alignx left,aligny top");

			taskList.get(i).getContentPane().add(niokrLabels.get(i), "cell 0 1,alignx left,aligny top");

			for (int j = 0; j < mathModel.VedNames.getVeds(i).length; j++) {
				taskList.get(i).getContentPane().add(vedLabels.get(vedLabelsIndex), "cell 0 " + (2 + j));
				vedLabelsIndex++;
			}
		}
		choosenLabel = niokrLabels.get(0);
	}

	private void setVedMap() {
		for (byte i = 0; i < 25; i++) {
			String vedName = mathModel.VedNames.getVedName(i);
			String newName = vedName;
			int brIndex = 0;
			switch (i) {
			case 24:
				brIndex = 28;
				break;
			case 6:
				brIndex = 35;
				break;
			case 11:
				brIndex = 32;
				break;
			case 13:
				brIndex = 25;
				break;
			case 14:
				brIndex = 36;
				break;
			case 17:
				brIndex = 26;
				break;
			case 20:
				brIndex = 39;
				break;
			case 22:
				brIndex = 23;
				break;
			}
			if (brIndex != 0) {
				newName = Format.addBR(vedName, brIndex);
			}

			vedLabels.add(new JLabel(newName));
			vedLabels.get(i).setFont(new Font(font, Font.PLAIN, 12));
			vedLabels.get(i).addMouseListener(
					new TaskPaneMouseListener(vedName, mathModel.VedNames.getOtraslName(mathModel.VedNames
							.getOtraslByVed(i)), 2, vedLabels.get(i)));

			VedMap.put(i, new Ved_panel(i));
			VEDpanel.add(VedMap.get(i), "qwec" + i);
			VedMap.get(i).setVisible(i == 0);
		}
	}

	void chooseTaskPane(byte q) {
		for (byte i = 0; i < 10; i++) {
			if (q != i) {
				taskList.get(i).setCollapsed(true);
			}
		}
	}

	void setTaskPane(boolean collapsed, final byte index) {
		JXTaskPane taskPane = taskList.get(index);
		if (collapsed) {
			taskPane.setCollapsed(collapsed);
		}
		branchList.add(taskPane);
		taskPane.setPreferredSize(new Dimension(299, 0));
		taskPane.setMinimumSize(new Dimension(31, 25));
		taskPane.setFont(new Font(font, Font.PLAIN, 13));
		taskPane.setTitle(mathModel.VedNames.getOtraslName(index));
		taskPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseTaskPane(index);
			}
		});
	}

	public static void setVisibleInputPanel() {
		panel_3.setVisible(false);
		panel2.setVisible(true);
	}

	public static void setVisibleResultPanel() {
		panel_3.setVisible(true);
		panel2.setVisible(false);
	}

	public static void refresh() {
		LOG.debug("Refresh form.");

		for (byte i = 0; i < 25; i++) {
			try {
				if (i < 10) {
					NiokrMap.get(i).refresh(i);
					StudMap.get(i).refresh(i);
				}
				VedMap.get(i).refresh(i);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

}
