package visual.planning;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//import disabledNode.DisabledNode;
//import disabledNode.DisabledRenderer;
import java.util.Enumeration;
import java.util.List;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

import visual.treeBuilders.checkBoxNode.CheckNode;
import visual.treeBuilders.checkBoxNode.CheckRenderer;
import mathModel.variantsResolver.VariantsResolver;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Planning_panel extends JPanel {

	private static final long serialVersionUID = -6407265669053284328L;
	private static Logger LOG = Logger.getLogger(Planning_panel.class);
	private List<String> chosenProjects;
	private JScrollPane checkBoxTreeScrollPane;
	private PlanningPanelParams params;
	private int nodeCount;
	CheckNode[] nodes;
	int checkNodeCount = 0;
	JButton okBtn;
	JButton cancelBtn;

	String[] strs = { "Направления", "<html>Информационные и<br>коммуникационные технологии",
			"Энергетика и энергоэффективность", "Рациональное природопользование",
			"Наука про жизнь, биотехнологии" };
	String[] str1 = { "проект 1", "проект 2", "проект 3" };
	String[] str2 = { "проект 5", "проект 6", "проект 7" };
	String[] str3 = { "проект 12", "проект 11", "проект 33" };
	String[] str4 = { "проект 15", "проект 21", "проект 13" };

	public Planning_panel() {
		int treeLenght = (strs.length + str1.length + str2.length + str3.length + str4.length + 1);
		nodes = new CheckNode[treeLenght];
		setBorder(null);
		for (nodeCount = 0; nodeCount < strs.length; nodeCount++) {
			nodes[nodeCount] = new CheckNode(strs[nodeCount]);
		}
		nodes[0].add(nodes[1]);
		nodes[0].add(nodes[2]);
		nodes[0].add(nodes[3]);
		nodes[0].add(nodes[4]);
		//
		for (String aStr1 : str1) {
			nodeCount++;
			nodes[nodeCount] = new CheckNode(aStr1);
			nodes[1].add(nodes[nodeCount]);
		}

		for (String aStr2 : str2) {
			nodeCount++;
			nodes[nodeCount] = new CheckNode(aStr2);
			nodes[2].add(nodes[nodeCount]);
		}
		for (String aStr3 : str3) {
			nodeCount++;
			nodes[nodeCount] = new CheckNode(aStr3);
			nodes[3].add(nodes[nodeCount]);
		}

		for (String aStr4 : str4) {
			nodeCount++;
			nodes[nodeCount] = new CheckNode(aStr4);
			nodes[4].add(nodes[nodeCount]);
		}
		// TODO ломает визуальный редактор
		JTree tree = new JTree(nodes[0]) {
			private static final long serialVersionUID = -4651649730501997216L;

			public boolean isPathEditable(TreePath path) {
				return false;
			}
		};

		tree.setCellRenderer(new CheckRenderer());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.putClientProperty("JTree.lineStyle", "Angled");
		setLayout(new BorderLayout(0, 0));
		tree.addMouseListener(new NodeSelectionListener(tree));

		checkBoxTreeScrollPane = new JScrollPane();
		checkBoxTreeScrollPane.setPreferredSize(new Dimension(300, 700));
		checkBoxTreeScrollPane.setMinimumSize(new Dimension(300, 700));
		checkBoxTreeScrollPane.setBorder(null);
		add(checkBoxTreeScrollPane, BorderLayout.WEST);
		checkBoxTreeScrollPane.setViewportView(tree);
		params = new PlanningPanelParams();
		add(params, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		FormLayout flButtonspanel = new FormLayout(new ColumnSpec[] { ColumnSpec.decode("1179px:grow(20)"),
				ColumnSpec.decode("120px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("120px"), }, new RowSpec[] { RowSpec.decode("28px"), });
		flButtonspanel.setHonorsVisibility(false);
		buttonsPanel.setLayout(flButtonspanel);

		okBtn = new JButton("\u041F\u0440\u0438\u043C\u0435\u043D\u0438\u0442\u044C");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VariantsResolver vr = params.getVR();
				vr.proceed();
				params.setResult();
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

	}

	class NodeSelectionListener extends MouseAdapter {// для дерева с чекбоксами
		JTree tree;

		NodeSelectionListener(JTree tree) {
			this.tree = tree;
		}

		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int row = tree.getRowForLocation(x, y);
			TreePath path = tree.getPathForRow(row);
			if (path != null) {
				CheckNode node = (CheckNode) path.getLastPathComponent();
				boolean isSelected = !(node.isSelected());
				node.setSelected(isSelected);
				if (node.getSelectionMode() == CheckNode.DIG_IN_SELECTION) {
					if (isSelected) {
						tree.expandPath(path);
					} else {
						tree.collapsePath(path);
					}
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
				if (row == 0) {
					tree.revalidate();
					tree.repaint();
				}
			}
			treeCheck(nodes[0], nodeCount);
			params.setMax(checkNodeCount);
			params.setChosenProjects(chosenProjects);
		}
	}

	String[] treeCheck(CheckNode root, int z) {
		// создает список выбранных проектов
		Enumeration enumeration = root.breadthFirstEnumeration();
		int k = 0;
		checkNodeCount = 0;
		chosenProjects = new ArrayList<String>();
		String[] checkList = new String[z];
		while (enumeration.hasMoreElements()) {
			CheckNode node = (CheckNode) enumeration.nextElement();
			if (node.isSelected()) {
				TreeNode[] nodes = node.getPath();
				checkList[k] = nodes[nodes.length - 1].toString();
				k++;
				if (node.isLeaf()) {
					checkNodeCount++;
					chosenProjects.add(node.toString());
					LOG.info("chosenProject=" + chosenProjects.get(checkNodeCount - 1));
				}
				for (int i = 1; i < nodes.length; i++) {
				}
			}
		}
		return checkList;
	}
}
