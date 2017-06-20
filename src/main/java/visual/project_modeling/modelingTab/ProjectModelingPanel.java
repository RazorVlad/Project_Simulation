package visual.project_modeling.modelingTab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import visual.treeBuilders.checkBoxNode.CheckNode;
import visual.treeBuilders.checkBoxNode.CheckRenderer;
import visual.treeBuilders.disabledNode.DisabledNode;
import visual.treeBuilders.disabledNode.DisabledRenderer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ProjectModelingPanel extends JPanel {

	private static final long serialVersionUID = -643610859378037587L;
	private JPanel treePanel;
	private JScrollPane checkBoxTreeScrollPane;
	private JScrollPane disableTreeScrollPane;
	private ProjectModeling_panel_params params;
	JPanel panelTop;
	JPanel main;
	JButton btnOK;
	JButton btnCancel;
	JLabel chooseProjectLabel;
	JLabel projectNameLabel;
	private int nodeCount;

	String[] strs = { "Направления", "<html>Информационные и<br>коммуникационные технологии",
			"Энергетика и энергоэффективность", "Рациональное природопользование",
			"Наука про жизнь, биотехнологии" };
	String[] str1 = { "проект 1", "проект 2", "проект 3" };
	String[] str2 = { "проект 5", "проект 6", "проект 7" };
	String[] str3 = { "проект 12", "проект 11", "проект 33" };
	String[] str4 = { "проект 15", "проект 21", "проект 13" };

	public ProjectModelingPanel() {
		setBounds(new Rectangle(0, 0, 1300, 750));
		setLayout(new BorderLayout(0, 0));
		int treeLenght = (strs.length + str1.length + str2.length + str3.length + str4.length + 1);
		final CheckNode[] nodes = new CheckNode[treeLenght];
		final DisabledNode[] dNodes = new DisabledNode[treeLenght];

		for (nodeCount = 0; nodeCount < strs.length; nodeCount++) {
			nodes[nodeCount] = new CheckNode(strs[nodeCount]);
			dNodes[nodeCount] = new DisabledNode(strs[nodeCount]);
		}

		for (int i = 1; i < 5; i++) {
			nodes[0].add(nodes[i]);
			dNodes[0].add(dNodes[i]);
		}

		nodeCount = addNodes(nodes, dNodes, nodeCount, str1, 1);
		nodeCount = addNodes(nodes, dNodes, nodeCount, str2, 2);
		nodeCount = addNodes(nodes, dNodes, nodeCount, str3, 3);
		nodeCount = addNodes(nodes, dNodes, nodeCount, str4, 4);

		JTree tree = new JTree(nodes[0]) {
			private static final long serialVersionUID = -1608477120959336868L;

			public boolean isPathEditable(TreePath path) {
				return false;
			}
		};
		final JTree dTree = new JTree(dNodes[0]) {
			private static final long serialVersionUID = -2184069550464281256L;

			public boolean isPathEditable(TreePath path) {
				return false;
			}
		};

		DisabledRenderer renderer = new DisabledRenderer();
		dTree.setCellRenderer(renderer);
		dTree.setEditable(true);
		dTree.addMouseListener(new SelectionListener(dTree));

		tree.setCellRenderer(new CheckRenderer());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.addMouseListener(new NodeSelectionListener(tree));

		panelTop = new JPanel();
		panelTop.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panelTop.setLayout(new BorderLayout(0, 0));
		add(panelTop, BorderLayout.NORTH);

		chooseProjectLabel = new JLabel("Выберите проекты");
		panelTop.add(chooseProjectLabel, BorderLayout.WEST);
		chooseProjectLabel.setBorder(null);
		chooseProjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chooseProjectLabel.setPreferredSize(new Dimension(290, 25));

		projectNameLabel = new JLabel("Название выбранного проекта");
		panelTop.add(projectNameLabel, BorderLayout.CENTER);
		projectNameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		projectNameLabel.setBorder(null);
		projectNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);

		treePanel = new JPanel();
		treePanel.setBorder(null);
		add(treePanel, BorderLayout.WEST);
		treePanel.setLayout(new CardLayout(0, 0));

		checkBoxTreeScrollPane = new JScrollPane();
		treePanel.add(checkBoxTreeScrollPane, "name_49569353870999");
		checkBoxTreeScrollPane.setViewportView(tree);

		disableTreeScrollPane = new JScrollPane();
		treePanel.add(disableTreeScrollPane, "name_49575076653966");
		disableTreeScrollPane.setViewportView(dTree);

		main = new JPanel();
		main.setBorder(null);
		main.setLayout(new BorderLayout());
		add(main, BorderLayout.CENTER);

		params = new ProjectModeling_panel_params();
		params.setBorder(null);
		main.add(params, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		FormLayout flButtonsPanel = new FormLayout(new ColumnSpec[] { ColumnSpec.decode("1179px:grow(20)"),
				ColumnSpec.decode("120px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("120px"), }, new RowSpec[] { RowSpec.decode("28px"), });
		flButtonsPanel.setHonorsVisibility(false);
		buttonsPanel.setLayout(flButtonsPanel);

		btnOK = new JButton("Моделировать");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] chosenProjects = TreeCheck.treeCheck(nodes[0], nodeCount);
				TreeCheck.treeCheck(dNodes[0], chosenProjects);
				// выключение невыбранных проектов
				checkBoxTreeScrollPane.setVisible(false);
				disableTreeScrollPane.setVisible(true);
				// chosenProjects - список проектов
				// TODO Sasha взять список проектов и запустить для них расчет
				// params.refresh(Effect1, Effect2, EffectSum, Efficiency,
				// Costs, Income, Discount,
				// statisticsResult);
			}
		});
		btnOK.setMinimumSize(new Dimension(120, 26));
		btnOK.setPreferredSize(new Dimension(120, 28));
		btnOK.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnOK.setMaximumSize(new Dimension(140, 26));
		buttonsPanel.add(btnOK, "2, 1, right, fill");

		btnCancel = new JButton("Сброс");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxTreeScrollPane.setVisible(true);
				disableTreeScrollPane.setVisible(false);
				nodes[0].setSelected(false);// обнуление выделенных проектов
				projectNameLabel.setText("Название выбранного проекта");
			}
		});
		btnCancel.setMinimumSize(new Dimension(120, 26));
		btnCancel.setPreferredSize(new Dimension(120, 28));
		btnCancel.setMaximumSize(new Dimension(140, 26));
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonsPanel.add(btnCancel, "4, 1, right, bottom");
	}

	int addNodes(CheckNode[] nodes, DisabledNode[] dNodes, int nodeCount, String[] str, int count) {
		for (String aStr : str) {
			nodeCount++;
			nodes[nodeCount] = new CheckNode(aStr);
			nodes[count].add(nodes[nodeCount]);
			dNodes[nodeCount] = new DisabledNode(aStr);
			dNodes[count].add(dNodes[nodeCount]);
		}
		return nodeCount;
	}

	class SelectionListener extends MouseAdapter {
		// для дерева с выключаемыми проетами
		JTree tree;

		SelectionListener(JTree tree) {
			this.tree = tree;
		}

		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int row = tree.getRowForLocation(x, y);
			TreePath path = tree.getPathForRow(row);
			if (path != null) {
				DisabledNode node = (DisabledNode) path.getLastPathComponent();
				if (node.isEnabled() && node.isLeaf()) {
					params.setPanel(path.getLastPathComponent().toString());
					projectNameLabel.setText(path.getLastPathComponent().toString());
				}
			}
		}
	}
}
