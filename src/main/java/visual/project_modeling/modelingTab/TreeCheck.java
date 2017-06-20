package visual.project_modeling.modelingTab;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import visual.treeBuilders.checkBoxNode.CheckNode;
import visual.treeBuilders.disabledNode.DisabledNode;

public class TreeCheck {

	static void treeCheck(DisabledNode root, String[] asd) {
		// выключает не выбранные проекты
		Enumeration enumeration = root.breadthFirstEnumeration();
		while (enumeration.hasMoreElements()) {
			DisabledNode node = (DisabledNode) enumeration.nextElement();
			node.setEnabled(false);
			TreeNode[] nodes = node.getPath();
			for (String anAsd : asd) {
				if (nodes[nodes.length - 1].toString().equals(anAsd)) {
					for (int i = 1; i < nodes.length; i++) {
						System.out.println("/" + nodes[i].toString());
					}
					node.setEnabled(true);
				}
			}
		}
	}
	
	static String[] treeCheck(CheckNode root, int z) {
		// создает список выбранных проектов
		Enumeration enumeration = root.breadthFirstEnumeration();
		int k = 0;
		String[] checkList = new String[z];
		while (enumeration.hasMoreElements()) {
			CheckNode node = (CheckNode) enumeration.nextElement();
			if (node.isSelected()) {
				TreeNode[] nodes = node.getPath();
				checkList[k] = nodes[nodes.length - 1].toString();
				k++;
				for (int i = 1; i < nodes.length; i++) {
				}
			}
		}
		return checkList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
