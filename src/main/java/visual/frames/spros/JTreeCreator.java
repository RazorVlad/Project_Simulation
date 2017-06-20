package visual.frames.spros;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class JTreeCreator {
	DefaultMutableTreeNode level;
	DefaultMutableTreeNode level00;

	public DefaultMutableTreeNode createMainNode() {
		level = new DefaultMutableTreeNode("Отрасли");
		DefaultMutableTreeNode level0;// Пищевая промышленность
		DefaultMutableTreeNode level1;// Легкая промышленность
		DefaultMutableTreeNode level2;// Деревообрабатывающая промышленность
		DefaultMutableTreeNode level3;// Целлюлозно-бумажная промышленность
		DefaultMutableTreeNode level4;// Нефтеперерабатывающая промышленность
		DefaultMutableTreeNode level5;// Химическая промышленность
		DefaultMutableTreeNode level6;// Промышленность стройматериалов
		DefaultMutableTreeNode level7;// Металлургия
		DefaultMutableTreeNode level8;// Машиностроение
		DefaultMutableTreeNode level9;// Электроэнергетика
		// //////////////////////////////////////////////////////////////////////////////////////////
		level0 = new DefaultMutableTreeNode("Пищевая промышленность");
		level00 = new DefaultMutableTreeNode("Производство пищевых продуктов");
		level0.add(level00);
		DefaultMutableTreeNode level01 = new DefaultMutableTreeNode("Производство напитков");
		level0.add(level01);
		DefaultMutableTreeNode level02 = new DefaultMutableTreeNode("Производство табачных изделий");
		level0.add(level02);
		level.add(level0);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level1 = new DefaultMutableTreeNode("Легкая промышленность");
		DefaultMutableTreeNode level10 = new DefaultMutableTreeNode("Текстильное производство");
		level1.add(level10);
		DefaultMutableTreeNode level11 = new DefaultMutableTreeNode("Производство одежды");
		level1.add(level11);
		DefaultMutableTreeNode level12 = new DefaultMutableTreeNode("Изготовление изделий из кожи");
		level1.add(level12);
		level.add(level1);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level2 = new DefaultMutableTreeNode("Деревообрабатывающая промышленность");
		DefaultMutableTreeNode level20 = new DefaultMutableTreeNode(
				"<HTML>Обработка древесины и изготовление<br>изделий из древесины и корка, кроме мебели</HTML>");
		level2.add(level20);
		level.add(level2);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level3 = new DefaultMutableTreeNode("Целлюлозно-бумажная промышленность");
		DefaultMutableTreeNode level30 = new DefaultMutableTreeNode("Производство бумаги и бумажных изделий");
		level3.add(level30);
		DefaultMutableTreeNode level31 = new DefaultMutableTreeNode("Полиграфическая деятельность");
		level3.add(level31);
		level.add(level3);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level4 = new DefaultMutableTreeNode("Нефтеперерабатывающая промышленность");
		DefaultMutableTreeNode level40 = new DefaultMutableTreeNode("Производство кокса и коксопродуктов");
		level4.add(level40);
		DefaultMutableTreeNode level41 = new DefaultMutableTreeNode(
				"Производство продуктов нефтепереработки");
		level4.add(level41);
		level.add(level4);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level5 = new DefaultMutableTreeNode("Химическая промышленность");
		DefaultMutableTreeNode level50 = new DefaultMutableTreeNode(
				"<html>Производство химических веществ<br>и химической продукции");
		level5.add(level50);
		DefaultMutableTreeNode level51 = new DefaultMutableTreeNode("Фармацевтическое производство");
		level5.add(level51);
		DefaultMutableTreeNode level52 = new DefaultMutableTreeNode(
				"Производство резиновых и пластмассовых изделий");
		level5.add(level52);
		level.add(level5);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level6 = new DefaultMutableTreeNode("Промышленность стройматериалов");
		DefaultMutableTreeNode level60 = new DefaultMutableTreeNode(
				"<html>Производство прочей неметаллической<br>минеральной продукции");
		level6.add(level60);
		level.add(level6);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level7 = new DefaultMutableTreeNode("Металлургия");
		DefaultMutableTreeNode level70 = new DefaultMutableTreeNode("Металлургическое производство");
		level7.add(level70);
		DefaultMutableTreeNode level71 = new DefaultMutableTreeNode(
				"Производство готовых металлических изделий");
		level7.add(level71);
		level.add(level7);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level8 = new DefaultMutableTreeNode("Машиностроение");
		DefaultMutableTreeNode level80 = new DefaultMutableTreeNode(
				"Производство компьютеров, электронной и оптической продукции");
		level8.add(level80);
		DefaultMutableTreeNode level81 = new DefaultMutableTreeNode(
				"Производство электрического оборудования");
		level8.add(level81);
		DefaultMutableTreeNode level82 = new DefaultMutableTreeNode("Производство машин и оборудования");
		level8.add(level82);
		DefaultMutableTreeNode level83 = new DefaultMutableTreeNode(
				"Производство автотранспортных средств и прицепов");
		level8.add(level83);
		DefaultMutableTreeNode level84 = new DefaultMutableTreeNode(
				"Производство прочих транспортных средств");
		level8.add(level84);
		level.add(level8);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level9 = new DefaultMutableTreeNode("Электроэнергетика");
		DefaultMutableTreeNode level90 = new DefaultMutableTreeNode(
				"Производство, передача и распределение электроэнергетики");
		level9.add(level90);
		DefaultMutableTreeNode level91 = new DefaultMutableTreeNode("Производство газа");
		level9.add(level91);
		DefaultMutableTreeNode level92 = new DefaultMutableTreeNode(
				"Поставка пара, горячей воды и кондиционированного воздуха");
		level9.add(level92);
		level.add(level9);
		return level;
	}

	public TreeNode[] getSelectionPath() {
		return level00.getPath();
	}
}
