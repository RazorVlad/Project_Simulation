package visual.project_modeling.projectBaseTab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import mathModel.ProjectStorage;
import mathModel.project.Project;

import org.apache.log4j.Logger;

import visual.project_modeling.ProjectGroups;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ProjectBase extends JPanel {

	private static final long serialVersionUID = -4755432077853727423L;
	private static Logger LOG = Logger.getLogger(ProjectBase.class);
	ProjectBasePanel newProject;
	final ProjectBasePanel projectBase;
	JLabel projectPassportLabel;
	JLabel projectBaseLabel;
	private JPanel labelsPanel;
	private JPanel buttonsPanel;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Направления");
	DefaultTreeModel model = new DefaultTreeModel(rootNode);
	final JTree jt = new JTree(model);
	JButton okBtn;
	JButton cancelBtn;
	List<DefaultMutableTreeNode> projectPriority = new ArrayList<DefaultMutableTreeNode>();

	public void setProjectPassportLabel(String text) {
		projectPassportLabel.setText(text);
	}

	public ProjectBase() {
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		projectPriority.add(new DefaultMutableTreeNode(
				"<html>Информационные и<br>коммуникационные технологии"));
		projectPriority.add(new DefaultMutableTreeNode("Энергетика и энергоэффективность"));
		projectPriority.add(new DefaultMutableTreeNode("Рациональное природопользование"));
		projectPriority.add(new DefaultMutableTreeNode("Наука про жизнь, биотехнологии"));
		projectPriority.add(new DefaultMutableTreeNode("Новые вещества и материалы"));

		labelsPanel = new JPanel();
		add(labelsPanel, BorderLayout.NORTH);
		labelsPanel.setLayout(new BorderLayout(0, 0));

		projectBaseLabel = new JLabel(
				"\u0411\u0430\u0437\u0430 \u043F\u0440\u043E\u0435\u043A\u0442\u043E\u0432");
		projectBaseLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelsPanel.add(projectBaseLabel, BorderLayout.WEST);
		projectBaseLabel.setBorder(null);
		projectBaseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectBaseLabel.setPreferredSize(new Dimension(290, 25));

		projectPassportLabel = new JLabel(
				"\u041F\u0430\u0441\u043F\u043E\u0440\u0442 \u043F\u0440\u043E\u0435\u043A\u0442\u0430(\u043F\u0440\u043E\u043C\u044B\u0448\u043B\u0435\u043D\u043D\u043E\u0439 \u0442\u0435\u0445\u043D\u043E\u043B\u043E\u0433\u0438\u0438)");
		labelsPanel.add(projectPassportLabel, BorderLayout.CENTER);
		projectPassportLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		projectPassportLabel.setBorder(null);
		projectPassportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectPassportLabel.setHorizontalTextPosition(SwingConstants.CENTER);

		JPanel projectBaseTreePanel = new JPanel();
		projectBaseTreePanel.setBorder(null);
		projectBaseTreePanel.setPreferredSize(new Dimension(290, 10));
		projectBaseTreePanel.setMaximumSize(new Dimension(290, 32767));
		projectBaseTreePanel.setMinimumSize(new Dimension(290, 10));
		add(projectBaseTreePanel, BorderLayout.WEST);
		projectBaseTreePanel.setLayout(new CardLayout(0, 0));

		JScrollPane projectBaseScrollPane = new JScrollPane();
		projectBaseScrollPane.setMinimumSize(new Dimension(200, 25));
		projectBaseTreePanel.add(projectBaseScrollPane, "name_478370759828");
		projectBaseScrollPane.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0)));
		jt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				refresh(jt.getLastSelectedPathComponent().toString());
			}
		});
		jt.setFont(new Font("Calibri", Font.PLAIN, 14));
		jt.setEditable(true);
		jt.setSelectionRow(0);
		projectBaseScrollPane.setViewportView(jt);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jt, popupMenu);

		JMenuItem menuItem1 = new JMenuItem(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043F\u0440\u043E\u0435\u043A\u0442");
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) jt
							.getLastSelectedPathComponent();
					String name = jt.getLastSelectedPathComponent().toString();
					if (selNode == null || name.equals("Направления")) {
						return;
					}

					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Новый проект");
					if (name.equals("<html>Информационные и<br>коммуникационные технологии")
							|| name.equals("Энергетика и энергоэффективность")
							|| name.equals("Рациональное природопользование")
							|| name.equals("Наука про жизнь, биотехнологии")
							|| name.equals("Новые вещества и материалы")) {
						model.insertNodeInto(newNode, selNode, selNode.getChildCount());
					} else {
						selNode = (DefaultMutableTreeNode) selNode.getParent();
						model.insertNodeInto(newNode, selNode, selNode.getChildCount());
					}
					TreeNode[] nodes = model.getPathToRoot(newNode);
					TreePath path = new TreePath(nodes);
					jt.scrollPathToVisible(path);

					jt.setSelectionPath(path);

					jt.startEditingAtPath(path);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}

		});
		popupMenu.add(menuItem1);

		JMenuItem menuItem = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u0440\u043E\u0435\u043A\u0442");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MutableTreeNode mNode;
					DefaultTreeModel model;
					model = (DefaultTreeModel) jt.getModel();
					mNode = (MutableTreeNode) jt.getSelectionPath().getLastPathComponent();
					model.removeNodeFromParent(mNode);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		});
		popupMenu.add(menuItem);

		JPanel projectInfoPanel = new JPanel();
		add(projectInfoPanel);
		projectInfoPanel.setLayout(new CardLayout(0, 0));

		newProject = new ProjectBasePanel();
		newProject.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		projectInfoPanel.add(newProject, "name_6227924435858");
		projectBase = new ProjectBasePanel();
		projectBase.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		projectInfoPanel.add(projectBase, "name_472597211691");

		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		FormLayout flButtonsPanel = new FormLayout(new ColumnSpec[] { ColumnSpec.decode("1179px:grow(20)"),
				ColumnSpec.decode("120px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("120px"), }, new RowSpec[] { RowSpec.decode("28px"), });
		flButtonsPanel.setHonorsVisibility(false);
		buttonsPanel.setLayout(flButtonsPanel);

		okBtn = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Project pr = new Project(newProject.getK(), newProject.getFixed(), newProject
						.getProjectName(), newProject.getfield(), newProject.getProjectAim(), newProject
						.getProjectSphere(), newProject.getProjectGroupIndex(), newProject
						.getManufacturerVed(), newProject.getVedOwner(), newProject.getAssetsOutcomeMethod(),
						newProject.getProductivity(), newProject.getDeltaT(), newProject.getBeginYear(),
						newProject.getDuration(), newProject.getDurationResearch(), newProject
								.getDurationDev(), newProject.getDurationPrep(), newProject.getPower0(),
						newProject.getPowerCost(), newProject.getProjectCostResearch(), newProject
								.getProjectCostDev(), newProject.getProjectCost(), newProject.getDemand(),
						newProject.getProjectPrice(), newProject.getInvestments(), newProject.getStaff0(),
						newProject.getTotalCost(), newProject.getLoad(), newProject.getVarCost(), newProject
								.getLaborContent());

				ProjectStorage.getInstance().add(pr);

				// добавление проекта в дерево
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newProject.getProjectName());

				int sphere = newProject.getProjectSphere();
				model.insertNodeInto(newNode, projectPriority.get(sphere), projectPriority.get(sphere)
						.getChildCount());
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				jt.scrollPathToVisible(path);
				jt.setSelectionPath(path);
				jt.startEditingAtPath(path);
			}
		});
		okBtn.setMaximumSize(new Dimension(100, 28));
		okBtn.setMinimumSize(new Dimension(100, 26));
		okBtn.setPreferredSize(new Dimension(120, 28));
		buttonsPanel.add(okBtn, "2, 1, right, fill");

		cancelBtn = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cancelBtn.setMaximumSize(new Dimension(100, 28));
		cancelBtn.setMinimumSize(new Dimension(100, 26));
		cancelBtn.setPreferredSize(new Dimension(120, 28));

		buttonsPanel.add(cancelBtn, "4, 1, right, bottom");
		add(buttonsPanel, BorderLayout.SOUTH);

		jt.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				String s = arg0.getPath().getLastPathComponent().toString();
				if (s.equals("Проект 1.1")) {
				}
			}
		});

		for (Project p : ProjectStorage.getInstance().getProjects()) {
			int index = ProjectGroups.getPriorityIndex(p.getField());
			projectPriority.get(index).add(new DefaultMutableTreeNode(p.getName()));
		}

		for (int i = 0; i < 5; i++) {
			rootNode.add(projectPriority.get(i));
		}
	}

	void refresh(String name) {
		projectBase.refresh(name);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void baseVisible(boolean newProjectVisible, boolean projectBaseVisible) {
		newProject.setVisible(newProjectVisible);
		projectBase.setVisible(projectBaseVisible);
	}
}
