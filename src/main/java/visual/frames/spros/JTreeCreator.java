package visual.frames.spros;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class JTreeCreator {
	DefaultMutableTreeNode level;
	DefaultMutableTreeNode level00;

	public DefaultMutableTreeNode createMainNode() {
		level = new DefaultMutableTreeNode("�������");
		DefaultMutableTreeNode level0;// ������� ��������������
		DefaultMutableTreeNode level1;// ������ ��������������
		DefaultMutableTreeNode level2;// �������������������� ��������������
		DefaultMutableTreeNode level3;// ����������-�������� ��������������
		DefaultMutableTreeNode level4;// ��������������������� ��������������
		DefaultMutableTreeNode level5;// ���������� ��������������
		DefaultMutableTreeNode level6;// �������������� ���������������
		DefaultMutableTreeNode level7;// �����������
		DefaultMutableTreeNode level8;// ��������������
		DefaultMutableTreeNode level9;// �����������������
		// //////////////////////////////////////////////////////////////////////////////////////////
		level0 = new DefaultMutableTreeNode("������� ��������������");
		level00 = new DefaultMutableTreeNode("������������ ������� ���������");
		level0.add(level00);
		DefaultMutableTreeNode level01 = new DefaultMutableTreeNode("������������ ��������");
		level0.add(level01);
		DefaultMutableTreeNode level02 = new DefaultMutableTreeNode("������������ �������� �������");
		level0.add(level02);
		level.add(level0);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level1 = new DefaultMutableTreeNode("������ ��������������");
		DefaultMutableTreeNode level10 = new DefaultMutableTreeNode("����������� ������������");
		level1.add(level10);
		DefaultMutableTreeNode level11 = new DefaultMutableTreeNode("������������ ������");
		level1.add(level11);
		DefaultMutableTreeNode level12 = new DefaultMutableTreeNode("������������ ������� �� ����");
		level1.add(level12);
		level.add(level1);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level2 = new DefaultMutableTreeNode("�������������������� ��������������");
		DefaultMutableTreeNode level20 = new DefaultMutableTreeNode(
				"<HTML>��������� ��������� � ������������<br>������� �� ��������� � �����, ����� ������</HTML>");
		level2.add(level20);
		level.add(level2);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level3 = new DefaultMutableTreeNode("����������-�������� ��������������");
		DefaultMutableTreeNode level30 = new DefaultMutableTreeNode("������������ ������ � �������� �������");
		level3.add(level30);
		DefaultMutableTreeNode level31 = new DefaultMutableTreeNode("��������������� ������������");
		level3.add(level31);
		level.add(level3);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level4 = new DefaultMutableTreeNode("��������������������� ��������������");
		DefaultMutableTreeNode level40 = new DefaultMutableTreeNode("������������ ����� � ��������������");
		level4.add(level40);
		DefaultMutableTreeNode level41 = new DefaultMutableTreeNode(
				"������������ ��������� ����������������");
		level4.add(level41);
		level.add(level4);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level5 = new DefaultMutableTreeNode("���������� ��������������");
		DefaultMutableTreeNode level50 = new DefaultMutableTreeNode(
				"<html>������������ ���������� �������<br>� ���������� ���������");
		level5.add(level50);
		DefaultMutableTreeNode level51 = new DefaultMutableTreeNode("���������������� ������������");
		level5.add(level51);
		DefaultMutableTreeNode level52 = new DefaultMutableTreeNode(
				"������������ ��������� � ������������� �������");
		level5.add(level52);
		level.add(level5);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level6 = new DefaultMutableTreeNode("�������������� ���������������");
		DefaultMutableTreeNode level60 = new DefaultMutableTreeNode(
				"<html>������������ ������ ���������������<br>����������� ���������");
		level6.add(level60);
		level.add(level6);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level7 = new DefaultMutableTreeNode("�����������");
		DefaultMutableTreeNode level70 = new DefaultMutableTreeNode("���������������� ������������");
		level7.add(level70);
		DefaultMutableTreeNode level71 = new DefaultMutableTreeNode(
				"������������ ������� ������������� �������");
		level7.add(level71);
		level.add(level7);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level8 = new DefaultMutableTreeNode("��������������");
		DefaultMutableTreeNode level80 = new DefaultMutableTreeNode(
				"������������ �����������, ����������� � ���������� ���������");
		level8.add(level80);
		DefaultMutableTreeNode level81 = new DefaultMutableTreeNode(
				"������������ �������������� ������������");
		level8.add(level81);
		DefaultMutableTreeNode level82 = new DefaultMutableTreeNode("������������ ����� � ������������");
		level8.add(level82);
		DefaultMutableTreeNode level83 = new DefaultMutableTreeNode(
				"������������ ���������������� ������� � ��������");
		level8.add(level83);
		DefaultMutableTreeNode level84 = new DefaultMutableTreeNode(
				"������������ ������ ������������ �������");
		level8.add(level84);
		level.add(level8);
		// //////////////////////////////////////////////////////////////////////////////////////////
		level9 = new DefaultMutableTreeNode("�����������������");
		DefaultMutableTreeNode level90 = new DefaultMutableTreeNode(
				"������������, �������� � ������������� �����������������");
		level9.add(level90);
		DefaultMutableTreeNode level91 = new DefaultMutableTreeNode("������������ ����");
		level9.add(level91);
		DefaultMutableTreeNode level92 = new DefaultMutableTreeNode(
				"�������� ����, ������� ���� � ������������������� �������");
		level9.add(level92);
		level.add(level9);
		return level;
	}

	public TreeNode[] getSelectionPath() {
		return level00.getPath();
	}
}
