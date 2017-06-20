package visual.save;

import java.io.Serializable;

import mathModel.Statistics;

public class Invest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int amountOfInvestmentTypeComboBox;
	private int investmentByYearsType;
	private int selectedType;
	private double[][][] tableData;
	private double investmentAmount;
	private Statistics tabledata;
	private int radioIndex;
	private double[][] investOnBranches;
	private boolean[] invOnBranches;
	private double[][][] investInBranch;
	private double[][] invStructValues;

	public double[][] getInvStructValues() {
		return invStructValues;
	}

	public void setInvStructValues(double[][] invStructValues) {
		this.invStructValues = invStructValues.clone();
	}

	public int getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(int setSelectedType) {
		this.selectedType = setSelectedType;
	}

	public double[][][] getTableData() {
		return tableData;
	}

	public void setTableData(double[][][] tableData) {
		this.tableData = tableData.clone();
	}

	public int getAmountOfInvestmentTypeComboBox() {
		return amountOfInvestmentTypeComboBox;
	}

	public int getInvestmentByYearsType() {
		return investmentByYearsType;
	}

	public double getinvestmentAmount() {
		return investmentAmount;
	}

	public Statistics gettabledata() {
		return tabledata;
	}

	public int getradioIndex() {
		return radioIndex;
	}

	public double[][] getinvestOnBranches() {
		return investOnBranches;
	}

	public boolean[] getInvOnBranches() {
		return invOnBranches;
	}

	public double[][][] getinvestInBranch() {
		return investInBranch;
	}

	public void setAmountOfInvestmentTypeComboBox(int index) {
		amountOfInvestmentTypeComboBox = index;
	}

	public void setInvestmentByYearsType(int index) {
		investmentByYearsType = index;
	}

	public void setinvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public void settabledata(Statistics tabledata) {
		this.tabledata = tabledata;
	}

	public void setradioIndex(int radioIndex) {
		this.radioIndex = radioIndex;
	}

	public void setinvestOnBranches(double[][] investOnBranches) {
		this.investOnBranches = investOnBranches.clone();
	}

	public void setInvOnBranches(boolean[] investOnBranches) {
		this.invOnBranches = investOnBranches.clone();
	}

	public void setinvestInBranch(double[][][] investInBranch) {
		this.investInBranch = investInBranch.clone();
	}
}
