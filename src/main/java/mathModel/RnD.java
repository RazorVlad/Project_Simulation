package mathModel;

import java.io.Serializable;

import mathModel.manFun.ManFun;

public class RnD implements Serializable {

	private static final long serialVersionUID = 1L;
	private Statistics staff;// out? ex 0
	private Statistics partIncomeStaff;// input
	private Statistics partOutcomeStaff;// input

	private Statistics assets;// out? ex 0
	private Statistics coefInvest;// input
	private double finInt;// input
	private Statistics coefFinInt;// in
	private double finExt;// in
	private Statistics coefFinExt;// in
	private Statistics coefNew;// in

	private Statistics coefObs;// in

	private Statistics poss;// out

	private Statistics real;// out
	private Statistics coefFin;// in
	private Statistics coefProd;// in

	private Statistics incomeStaff;// out
	private Statistics outcomeStaff;// out
	private Statistics assetsIncome;// out
	private Statistics assetsOutcome;// out

	private ManFun mf;

	private boolean okr;
	private int field;

	private ModelObjectsContainer model = ModelObjectsContainer.getInstance();

	private double finCoef; // inside

	public double getReal(int j) {
		return real.getValue(j);
	}

	public RnD(int field, ManFun mf, Statistics staff0, Statistics partIncomeStaff,
			Statistics partOutcomeStaff, Statistics assets0, Statistics coefInvest, double finInt,
			Statistics coefFinInt, double finExt, Statistics coefFinExt, Statistics coefFinPart,
			Statistics coefNew, Statistics coefObs, Statistics coefFin, Statistics coefProd, boolean okr) {
		this.field = field;
		this.staff = staff0;
		this.partIncomeStaff = partIncomeStaff;
		this.partOutcomeStaff = partOutcomeStaff;
		this.assets = assets0;
		this.coefInvest = coefInvest;
		this.finInt = finInt;
		this.coefFinInt = coefFinInt;
		this.finExt = finExt;
		this.coefFinExt = coefFinExt;
		this.coefNew = coefNew;
		this.coefObs = coefObs;
		this.poss = new Statistics();
		this.mf = mf;
		this.real = new Statistics();
		this.coefFin = coefFin;
		this.coefProd = coefProd;
		this.okr = okr;
		incomeStaff = new Statistics();
		outcomeStaff = new Statistics();
		assetsIncome = new Statistics();
		assetsOutcome = new Statistics();
	}

	public void proceed() {
		for (int i = 0; i < partIncomeStaff.size(); i++) {
			if (i > 0) {
				incomeStaff.add(partIncomeStaff.getYear(i),
						model.getEducation(field).getStaffRnD().getValue(i) * getRightPartIncomeStaff(i));

				outcomeStaff.add(partIncomeStaff.getYear(i),
						staff.getValue(i - 1) * partOutcomeStaff.getValue(i));
				staff.add(incomeStaff.getYear(i), staff.getValue(i - 1) + incomeStaff.getValue(i)
						+ outcomeStaff.getValue(i));
			}
			calcFinCoef(i);
			assetsIncome.add(incomeStaff.getYear(i),
					model.getInvestmentsRnD(field).getPrivInv(okr).getValue(i) * coefInvest.getValue(i)
							+ finCoef * coefNew.getValue(i));

			assetsOutcome.add(incomeStaff.getYear(i), assets.getValue(i) * coefObs.getValue(i));
			assets.add(assetsIncome.getYear(i),
					assets.getValue(i) + assetsIncome.getValue(i) - assetsOutcome.getValue(i));
			// poss
			// mf.proceed();

			real.add(assets.getYear(i), getRealToCheck(i));
		}
	}

	public ManFun getMf() {
		return mf;
	}

	public double[][] getpartIncomeStaff() {
		return partIncomeStaff.getStatisticsData();
	}

	public double[][] getpartOutcomeStaff() {
		return partOutcomeStaff.getStatisticsData();
	}

	public double[][] getcoefInvest() {
		return coefInvest.getStatisticsData();
	}

	public double getfinInt() {
		return finInt;
	}

	public double[][] getcoefFinInt() {
		return coefFinInt.getStatisticsData();
	}

	public double getfinExt() {
		return finExt;
	}

	public double[][] getcoefFinExt() {
		return coefFinExt.getStatisticsData();
	}

	public double[][] getcoefNew() {
		return coefNew.getStatisticsData();
	}

	public double[][] getcoefObs() {
		return coefObs.getStatisticsData();
	}

	public double[][] getcoefFin() {
		return coefFin.getStatisticsData();
	}

	public double[][] getcoefProd() {
		return coefProd.getStatisticsData();
	}

	private double getRealToCheck(int i) {
		double fin = finCoef * coefFin.getValue(i) * coefProd.getValue(i);
		if (poss.getValue(i) > fin) {
			return poss.getValue(i);
		} else {
			return fin;
		}
	}

	private double getRightCoefFin(int i) {
		if (okr) {
			return 1. - coefFin.getValue(i);
		} else {
			return coefFin.getValue(i);
		}
	}

	private double getRightPartIncomeStaff(int i) {
		if (okr) {
			return 1.0 - partIncomeStaff.getValue(i);
		} else {
			return partIncomeStaff.getValue(i);
		}
	}

	private void calcFinCoef(int i) {
		finCoef = model.getInvestmentsRnD(field).getGovInv(okr).getValue(i)
				+ (finInt * coefFinInt.getValue(i) + finExt * coefFinExt.getValue(i)) * getRightCoefFin(i);
	}

	public double getStaff(int j) {
		return staff.getValue(j);
	}

	public double getIncomeStaff(int j) {
		return incomeStaff.getValue(j);
	}

	public double getOutcomeStaff(int j) {
		return outcomeStaff.getValue(j);
	}

	public double getAssets(int j) {
		return assets.getValue(j);
	}

	public double getAssetsIncome(int j) {
		return assetsIncome.getValue(j);
	}

	public double getAssetsOutcome(int j) {
		return assetsOutcome.getValue(j);
	}
}
