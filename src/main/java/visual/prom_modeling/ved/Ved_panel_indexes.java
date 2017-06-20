package visual.prom_modeling.ved;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mathModel.Statistics;
import net.miginfocom.swing.MigLayout;
import visual.Format;
import visual.NumberOrButton;

public class Ved_panel_indexes extends JPanel {

	private static final long serialVersionUID = 6815924248273247514L;
	private JFormattedTextField prodPrice;
	private NumberOrButton coefProdPrice;
	private JFormattedTextField rawPriceT0;
	private NumberOrButton coefRawPrice;
	private JFormattedTextField salaryT0;
	private NumberOrButton coefSalaryChange;
	private JFormattedTextField extraCost;
	private NumberOrButton coefExtraCost;

	String text;

	public double getProdPrice() {
		return Format.getDouble(prodPrice.getText());
	}

	public Statistics getCoefProdPrice() {
		return coefProdPrice.getStatistics();
	}

	public double getRawPriceT0() {
		return Format.getDouble(rawPriceT0.getText());
	}

	public Statistics getCoefRawPrice() {
		return coefRawPrice.getStatistics();
	}

	public double getSalaryT0() {
		return Format.getDouble(salaryT0.getText());
	}

	public Statistics getCoefSalaryChange() {
		return coefSalaryChange.getStatistics();
	}

	public double getExtraCost() {
		return Format.getDouble(extraCost.getText());
	}

	public Statistics getCoefExtraCost() {
		return coefExtraCost.getStatistics();
	}

	public Ved_panel_indexes(boolean isOrd) {

		if (isOrd) {
			text = "�����������";
		} else {
			text = "�����";
		}
		setLayout(new MigLayout("", "[730][105][138]", "[][][][][][][][]"));

		JLabel prodPriceRLabel = new JLabel("���� �� " + text + "�� ��������� � ������� ����, ��� ���");
		prodPriceRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(prodPriceRLabel, "flowx,cell 0 0,alignx left");

		prodPrice = new JFormattedTextField(Format.getNumberFormat());
		prodPrice.setPreferredSize(new Dimension(120, 28));
		prodPrice.setMaximumSize(new Dimension(120, 28));
		add(prodPrice, "flowx,cell 2 0,growx");
		prodPrice.setColumns(10);

		JLabel rawPriceT0RLabel = new JLabel("��������� ����� � ������� ����");
		rawPriceT0RLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(rawPriceT0RLabel, "cell 0 1,alignx left");

		rawPriceT0 = new JFormattedTextField(Format.getNumberFormat());
		rawPriceT0.setPreferredSize(new Dimension(120, 28));
		rawPriceT0.setMaximumSize(new Dimension(120, 28));
		rawPriceT0.setColumns(10);
		add(rawPriceT0, "cell 2 1,growx");

		salaryT0 = new JFormattedTextField(Format.getNumberFormat());
		salaryT0.setPreferredSize(new Dimension(120, 28));
		salaryT0.setMaximumSize(new Dimension(120, 28));
		salaryT0.setColumns(10);
		add(salaryT0, "cell 2 2,growx");

		extraCost = new JFormattedTextField(Format.getNumberFormat());
		extraCost.setPreferredSize(new Dimension(120, 28));
		extraCost.setMaximumSize(new Dimension(120, 28));
		extraCost.setColumns(10);
		add(extraCost, "cell 2 3,growx");

		JLabel coefProdPriceRLabel = new JLabel("����������� ��������� ���� �� " + text + "�� ���������");
		coefProdPriceRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(coefProdPriceRLabel, "cell 0 4,alignx left");

		coefProdPrice = new NumberOrButton("����������� ��������� ���� �� ������� ���������", false);
		add(coefProdPrice, "cell 1 4 2 1,grow");

		JLabel coefRawPriceRLabel = new JLabel(
				"����������� ��������� ��������� ����� ��� ������������ ������� " + text + "�� ���������");
		coefRawPriceRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(coefRawPriceRLabel, "cell 0 5,alignx left");

		coefRawPrice = new NumberOrButton("����������� ��������� ��������� ����� ��� ������������ ������� "
				+ text + "�� ���������", false);
		add(coefRawPrice, "cell 1 5 2 1,grow");

		JLabel coefSalaryChangeRLabel = new JLabel("����������� ��������� �/� � " + text + "�� ������������");
		coefSalaryChangeRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(coefSalaryChangeRLabel, "cell 0 6,alignx left");

		coefSalaryChange = new NumberOrButton("����������� ��������� �/� � " + text + "�� ������������",
				false);
		add(coefSalaryChange, "cell 1 6 2 1,grow");

		JLabel coefExtraCostRLabel = new JLabel("����������� ��������� ������ ���������� ������");
		coefExtraCostRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(coefExtraCostRLabel, "cell 0 7,alignx left");

		coefExtraCost = new NumberOrButton("����������� ��������� ������ ���������� ������", false);
		add(coefExtraCost, "cell 1 7 2 1,grow");

		JLabel extraCostRLabel = new JLabel("������ ���������� ������� � ������� ����, ��� ���");
		extraCostRLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(extraCostRLabel, "cell 0 3,alignx left");

		JLabel salaryT0RLabel = new JLabel("�������������� �/� � " + text + "�� ������������ � ������� ����");
		salaryT0RLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(salaryT0RLabel, "cell 0 2,alignx left");
	}

	public void setProdPrice(double value) {
		prodPrice.setText(String.valueOf(value));
	}

	public void setRawPriceT0(double value) {
		rawPriceT0.setText(String.valueOf(value));
	}

	public void setSalaryT0(double value) {
		salaryT0.setText(String.valueOf(value));
	}

	public void setExtraCost(double value) {
		extraCost.setText(String.valueOf(value));
	}

	public void setCoefProdPrice(double[][] value) {
		coefProdPrice.setValue(value);
	}

	public void setCoefRawPrice(double[][] value) {
		coefRawPrice.setValue(value);
	}

	public void setCoefSalaryChange(double[][] value) {
		coefSalaryChange.setValue(value);
	}

	public void setCoefExtraCost(double[][] value) {
		coefExtraCost.setValue(value);
	}
}
