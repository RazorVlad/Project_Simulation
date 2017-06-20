package mathModel.manufacturing;

import java.io.Serializable;

import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.RnD;
import mathModel.Statistics;
import mathModel.fieldsInteraction.FieldsInteraction;
import mathModel.manFun.ManFun;
import mathModel.manufacturing.capital.CapitalIntensity;
import mathModel.manufacturing.capital.CapitalLaborRatio;
import mathModel.manufacturing.costs.ConstCost;
import mathModel.manufacturing.costs.Costs;
import mathModel.manufacturing.costs.ExtraCost;
import mathModel.manufacturing.costs.TotalCost;
import mathModel.manufacturing.costs.VarCost;
import mathModel.manufacturing.intangibles.Intangibles;
import mathModel.manufacturing.intangibles.IntangiblesIncome;
import mathModel.manufacturing.labor.Labor;
import mathModel.manufacturing.labor.LaborContent;
import mathModel.manufacturing.staff.Staff;
import mathModel.manufacturing.staff.StaffDeficit;
import mathModel.manufacturing.staff.StaffDemand;
import mathModel.manufacturing.staff.StaffEscess;
import mathModel.manufacturing.staff.StaffIncome;
import mathModel.manufacturing.staff.StaffOutcome;

import org.apache.log4j.Logger;

public class Manufacturing implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(Manufacturing.class);
	private boolean innovational;// input
	private int indexInFieldsInteraction;
	private int fieldIndex;

	private Statistics staff;
	private Statistics staffIncome;
	private Statistics staffOutcome;
	private Statistics coefOutcome;// input
	private Statistics eduAbility;
	private Statistics staffDemand;
	private Statistics prodDemand;

	private Statistics fieldPart;// input
	private Statistics qPart;// input
	private Statistics labor;

	private Statistics staffDeficit;
	private Statistics staffExcess;

	private Statistics assets;
	private Statistics assetsIncome;
	private Statistics assetsOutcome;

	private Statistics coefAmort1;// input
	private Statistics coefAmort2;// input
	private Statistics qGovInv;
	private Statistics qPrivInv;
	private Statistics partForFunds;// input
	private Statistics partAmort;// input
	private Statistics coefObsFunds;// input

	private Statistics wc;
	private Statistics wcIncome;
	private Statistics wcOutcome;

	private Statistics netProfit;
	private Statistics loss;

	private Statistics intangibles;
	private Statistics inangiblesIncome;
	private Statistics intangiblesOutcome;

	private Statistics workingPart;// input
	private Statistics workingIntangiblesPart;// input
	private Statistics coefObsIntangibles;// input

	private Statistics potential;
	private Statistics prodPrice;
	private Statistics coefProdPrice;// input

	private Statistics amountDem;
	private Statistics rawPrice;// input
	private Statistics coefRawPrice;// input

	private Statistics amountWC;
	private Statistics coefWC;// input
	private Statistics constCost;

	private Statistics amountReal;
	private Statistics amountNatur;

	private Statistics salary;
	private Statistics coefSalaryChange;// input
	private double coefSalaryOut;// input
	private Statistics extraCost;// input0
	private Statistics coefExtraCost;// input
	private Statistics varCost;
	private Statistics totalCost;
	private Statistics coefTax; // input

	private Statistics profit = new Statistics();
	private Statistics returnOnSales = new Statistics();
	private Statistics returnOnMargin = new Statistics();
	private Statistics costs = new Statistics();
	private Statistics breakEvenPoint = new Statistics();
	private Statistics breakEvenPointMoney = new Statistics();
	private Statistics finSafetyMargin = new Statistics();
	private Statistics finSafetyMarginRe = new Statistics();
	private Statistics workforceProdFin = new Statistics();
	private Statistics workforceProdNat = new Statistics();
	private Statistics laborContentFin = new Statistics();
	private Statistics laborContentNat = new Statistics();
	private Statistics turnoverFin = new Statistics();
	private Statistics turnoverNat = new Statistics();
	private Statistics capitalIntensityFin = new Statistics();
	private Statistics capitalIntensityNat = new Statistics();
	private Statistics capitalLaborRatio = new Statistics();
	Statistics varCostOld;

	private Education edu;
	private RnD rnd;
	private ManFun mf;
	private Manufacturing ord;

	private FieldsInteraction fi = ModelObjectsContainer.getInstance().getFieldsInteraction();

	public Manufacturing(boolean innovational, int indexInFieldsInteraction, int fieldIndex, ManFun mf,
			Statistics coefOutcome, Statistics fieldPart, Statistics qPart, Statistics coefAmort1,
			Statistics coefAmort2, Statistics workingPart, Statistics workingIntangiblesPart,
			Statistics coefObsIntangibles, Statistics coefProdPrice, Statistics rawPrice0,
			Statistics coefRawPrice, Statistics coefWC, Statistics prodPrice0, double intangibles0,
			Statistics potential0, double salary0, Statistics coefSalaryChange, double coefSalaryOut,
			Statistics coefExtraCost, Statistics extraCost0, double staff0, double assest0,
			Statistics coefObsFunds, Statistics partForFunds, Statistics partAmort, double wc0) {
		this.innovational = innovational;
		this.fieldIndex = fieldIndex;
		this.indexInFieldsInteraction = indexInFieldsInteraction;

		this.mf = mf;
		this.coefObsFunds = coefObsFunds;
		this.partAmort = partAmort;
		this.coefOutcome = coefOutcome;
		this.fieldPart = fieldPart;
		this.partForFunds = partForFunds;
		this.qPart = qPart;
		this.coefAmort1 = coefAmort1;
		this.coefAmort2 = coefAmort2;
		this.workingPart = workingPart;
		this.workingIntangiblesPart = workingIntangiblesPart;
		this.coefObsIntangibles = coefObsIntangibles;
		this.coefProdPrice = coefProdPrice;
		this.rawPrice = rawPrice0;
		this.coefRawPrice = coefRawPrice;
		this.coefWC = coefWC;
		this.staff = new Statistics();
		staff.add(coefOutcome.getYear(0), staff0);
		this.staffIncome = new Statistics();
		this.staffOutcome = new Statistics();
		this.eduAbility = new Statistics();
		this.staffDemand = new Statistics();
		this.labor = new Statistics();
		this.staffDeficit = new Statistics();
		this.staffExcess = new Statistics();
		// this.assets = assest0; изменено из Statistics в double
		this.assets = new Statistics();
		assets.add(coefOutcome.getYear(0), assest0);
		this.assetsIncome = new Statistics();
		this.assetsOutcome = new Statistics();
		this.qGovInv = new Statistics();
		this.qPrivInv = new Statistics();
		// this.wc = wc;
		this.wc = new Statistics();
		wc.add(coefOutcome.getYear(0), wc0);
		this.wcIncome = new Statistics();
		this.wcOutcome = new Statistics();
		this.netProfit = new Statistics();
		netProfit.add(coefOutcome.getYear(0), 0.0);
		this.loss = new Statistics();
		loss.add(coefOutcome.getYear(0), 0.0);
		// this.intangibles = intangibles0;
		this.intangibles = new Statistics();
		intangibles.add(coefOutcome.getYear(0), intangibles0);
		this.inangiblesIncome = new Statistics();
		this.intangiblesOutcome = new Statistics();
		this.potential = potential0;
		this.prodPrice = prodPrice0;
		this.amountDem = new Statistics();
		this.amountWC = new Statistics();
		this.constCost = new Statistics();
		this.amountReal = new Statistics();
		this.salary = new Statistics();
		salary.add(coefOutcome.getYear(0), salary0);
		this.coefSalaryChange = coefSalaryChange;
		this.coefSalaryOut = coefSalaryOut;
		this.extraCost = extraCost0;
		this.coefExtraCost = coefExtraCost;
		this.varCost = new Statistics();
		this.totalCost = new Statistics();
	}

	public ManFun getMf() {
		return mf;
	}

	public double[][] getcoefOutcome() {
		return coefOutcome.getStatisticsData();
	}

	public double[][] getfieldPart() {
		return fieldPart.getStatisticsData();
	}

	public double[][] getqPart() {
		return qPart.getStatisticsData();
	}

	public double[][] getcoefAmort1() {
		return coefAmort1.getStatisticsData();
	}

	public double[][] getcoefAmort2() {
		return coefAmort2.getStatisticsData();
	}

	public double[][] getpartAmort() {
		return partAmort.getStatisticsData();
	}

	public double[][] getcoefObsFunds() {
		return coefObsFunds.getStatisticsData();
	}

	public double[][] getworkingPart() {
		return workingPart.getStatisticsData();
	}

	public double[][] getworkingIntangiblesPart() {
		return workingIntangiblesPart.getStatisticsData();
	}

	public double[][] getcoefObsIntangibles() {
		return coefObsIntangibles.getStatisticsData();
	}

	public double[][] getcoefProdPrice() {
		return coefProdPrice.getStatisticsData();
	}

	public double getrawPrice(int j) {
		return rawPrice.getValue(j);
	}

	public double[][] getcoefRawPrice() {
		return coefRawPrice.getStatisticsData();
	}

	public double[][] getcoefWC() {
		return coefWC.getStatisticsData();
	}

	public double[][] getcoefSalaryChange() {
		return coefSalaryChange.getStatisticsData();
	}

	public double getcoefSalaryOut() {
		return coefSalaryOut;
	}

	public double getextraCost(int j) {
		return extraCost.getValue(j);
	}

	public double[][] getcoefExtraCost() {
		return coefExtraCost.getStatisticsData();
	}

	public double[][] getpartForFunds() {
		return partForFunds.getStatisticsData();
	}

	public double[][] getwc() {
		return wc.getStatisticsData();
	}

	public double getwc(int i) {
		return wc.getValue(i);
	}

	public double[][] getintangibles() {
		return intangibles.getStatisticsData();
	}

	public double getintangibles(int i) {
		return intangibles.getValue(i);
	}

	public double[][] getAssets() {
		return assets.getStatisticsData();
	}

	public void proceedBeforeFI() {
		LOG.debug("proceedBeforeFI");
		this.edu = ModelObjectsContainer.getInstance().getEducation(fieldIndex);
		this.rnd = ModelObjectsContainer.getInstance().getRndD(indexInFieldsInteraction);
		this.prodDemand = fi.getProdDemand(indexInFieldsInteraction, innovational);
		if (innovational) {
			this.coefTax = ModelObjectsContainer.getInstance().getTaxesInn(indexInFieldsInteraction)
					.getCoefTax();
		} else {
			this.coefTax = ModelObjectsContainer.getInstance().getTaxesOrd(indexInFieldsInteraction)
					.getCoefTax();
		}
		EduAbility.eduAbility(innovational, coefOutcome, edu, fieldPart, qPart, eduAbility);
		StaffOutcome.staffOutcome(coefOutcome, staff, staffOutcome);
		StaffDemand.staffDemand(coefOutcome, prodDemand, labor, staff, staffDemand);
		StaffIncome.staffIncome(coefOutcome, eduAbility, staffDemand, staffIncome);
		Staff.staff(coefOutcome, staff, staffIncome, staffOutcome);
		StaffDeficit.staffDeficit(coefOutcome, staffDemand, eduAbility, staffDeficit);
		Assets.assets(innovational, coefOutcome, coefAmort1, coefAmort2, qGovInv, qPrivInv, partForFunds,
				partAmort, assets, assetsIncome, coefObsFunds, assetsOutcome, ord);
		IntangiblesIncome.intangiblesIncome(coefOutcome, rnd, workingPart, workingIntangiblesPart,
				inangiblesIncome);
		Intangibles.intangibles(coefOutcome, intangibles, coefObsIntangibles, inangiblesIncome,
				intangiblesOutcome);
		Prices.prices(coefOutcome, coefProdPrice, coefRawPrice, prodPrice, rawPrice, wc);
		Salary.salary(coefSalaryChange, coefOutcome, salary);
		ExtraCost.extraCost(coefOutcome, extraCost, coefExtraCost);
		ConstCost.constCost(coefOutcome, assets, coefAmort1, staff, salary, coefSalaryOut, extraCost,
				constCost);
	}

	public void proceedAfterFI() {
		LOG.debug("proceedAfterFI");
		amountReal = fi.getAmountReal(indexInFieldsInteraction, innovational);
		VarCost.varCost(coefOutcome, amountReal, fi.getColumnSum(indexInFieldsInteraction), varCost);
		TotalCost.totalCost(coefOutcome, varCost, constCost, totalCost);
		Profit.profit(coefOutcome, amountReal, totalCost, profit);
		Profit.netProfit(coefOutcome, coefTax, profit, netProfit);
		Loss.loss(coefOutcome, loss, profit);
		WC.wc(innovational, coefOutcome, assets, ord, coefAmort1, coefAmort2, partAmort, qGovInv, qPrivInv,
				partForFunds, netProfit, wcIncome, wcOutcome, loss, wc);
		AmountWC.amountWC(coefOutcome, wc, coefWC, constCost, prodPrice, rawPrice, amountWC);
		Potential.potential(innovational, coefOutcome, mf, staff, assets, intangibles, potential);
		AmountDem.amountDem(coefOutcome, potential, prodPrice, amountDem, prodDemand);
		Returns.returnOnSales(coefOutcome, netProfit, profit, loss, amountReal, returnOnSales);
		Returns.returnOnMargin(coefOutcome, netProfit, totalCost, returnOnMargin);
		Costs.costs(coefOutcome, totalCost, amountReal, costs);
		BreakEvenPoint.breakEvenPoint(indexInFieldsInteraction, coefOutcome, constCost, breakEvenPoint,
				prodPrice, fi.getColumnSum(indexInFieldsInteraction));
		BreakEvenPoint.breakEvenPointMoney(coefOutcome, breakEvenPoint, prodPrice, breakEvenPointMoney);
		FinSafetyMargin.finSafetyMargin(coefOutcome, amountReal, breakEvenPointMoney, finSafetyMargin);
		FinSafetyMargin.finSafetyMarginRe(coefOutcome, finSafetyMargin, amountReal);
		WorkforceProd.workforceProdFin(coefOutcome, amountReal, staff, workforceProdFin);
		WorkforceProd.workforceProdNat(coefOutcome, amountReal, prodPrice, workforceProdNat, staff);
		LaborContent.laborContentFin(coefOutcome, laborContentFin, workforceProdFin);
		LaborContent.laborContentNat(coefOutcome, laborContentNat, workforceProdNat);
		Labor.labor(staff, potential, prodPrice, labor, coefOutcome, laborContentNat);
		StaffEscess.staffEscess(coefOutcome, staff, prodDemand, staffExcess, labor);
		Turnover.turnoverFin(turnoverFin, amountReal, assets, coefOutcome);
		Turnover.turnoverNat(turnoverNat, amountReal, assets, coefOutcome, prodPrice);
		CapitalIntensity.capitalIntensityFin(coefOutcome, turnoverFin, capitalIntensityFin);
		CapitalIntensity.capitalIntensityNat(coefOutcome, turnoverNat, capitalIntensityNat);
		CapitalLaborRatio.capitalLaborRatio(coefOutcome, assets, staff, capitalLaborRatio);
	}

	double getCoefAmort1(int i) {
		return coefAmort1.getValue(i);
	}

	public double getAssets(int i) {
		return assets.getValue(i);
	}

	public double getAssetsAt(int year) {
		return assets.getValueAt(year);
	}

	public double getStaff(int j) {
		return staff.getValue(j);
	}

	public double getStaffIncome(int j) {
		return staffIncome.getValue(j);
	}

	public double getStaffOutcome(int j) {
		return staffOutcome.getValue(j);
	}

	public double getEduAbility(int j) {
		return eduAbility.getValue(j);
	}

	public double getStaffDemand(int j) {
		return staffDemand.getValue(j);
	}

	public double getStaffDeficit(int j) {
		return staffDeficit.getValue(j);
	}

	public double getStaffDeficitAt(int year) {
		return staffDeficit.getValueAt(year);
	}

	public double getStaffExcess(int j) {
		return staffExcess.getValue(j);
	}

	public double getAssetsIncome(int j) {
		return assetsIncome.getValue(j);
	}

	public double getAssetsOutcome(int j) {
		return assetsOutcome.getValue(j);
	}

	public double getWc(int j) {
		return wc.getValue(j);
	}

	public double getWcIncome(int j) {
		return wcIncome.getValue(j);
	}

	public double getWcOutcome(int j) {
		return wcOutcome.getValue(j);
	}

	public double getIntangibles(int j) {
		return intangibles.getValue(j);
	}

	public double getIntangiblesAt(int year) {
		return intangibles.getValueAt(year);
	}

	public double getIntangiblesIncome(int j) {
		return inangiblesIncome.getValue(j);
	}

	public double getIntangiblesOutcome(int j) {
		return intangiblesOutcome.getValue(j);
	}

	public double getPotential(int j) {
		return potential.getValue(j);
	}

	public double getProdPriceAt(int j) {
		return prodPrice.getValue(j);
	}

	public double getTotalCost(int j) {
		return totalCost.getValue(j);
	}

	public double getVarCost(int j) {
		return varCost.getValue(j);
	}

	public double getConstCost(int j) {
		return constCost.getValue(j);
	}

	public double getSalary(int j) {
		return salary.getValue(j);
	}

	public double getProfit(int j) {
		return profit.getValue(j);
	}

	public double getNetProfit(int j) {
		return netProfit.getValue(j);
	}

	public double getLoss(int j) {
		return loss.getValue(j);
	}

	public double getReturnOnSales(int j) {
		return returnOnSales.getValue(j);
	}

	public double getReturnOnMargin(int j) {
		return returnOnMargin.getValue(j);
	}

	public double getCosts(int j) {
		return costs.getValue(j);
	}

	public double getBreakEvenPoint(int j) {
		return breakEvenPoint.getValue(j);
	}

	public double getBreakEvenPointMoney(int j) {
		return breakEvenPointMoney.getValue(j);
	}

	public double getFinSafetyMargin(int j) {
		return finSafetyMargin.getValue(j);
	}

	public double getFinSafetyMarginRe(int j) {
		return finSafetyMarginRe.getValue(j);
	}

	public double getWorkforceProdFin(int j) {
		return workforceProdFin.getValue(j);
	}

	public double getLaborContentFin(int j) {
		return laborContentFin.getValue(j);
	}

	public double getTurnoverFin(int j) {
		return turnoverFin.getValue(j);
	}

	public Statistics getTurnoverFin() {
		return turnoverFin;
	}

	public double getCapitalIntensityFin(int j) {
		return capitalIntensityFin.getValue(j);
	}

	public double getCapitalLaborRatio(int j) {
		return capitalLaborRatio.getValue(j);
	}

	public int getIndexInFieldsInteraction() {
		return indexInFieldsInteraction;
	}

	public void setIndexInFieldsInteraction(int indexInFieldsInteraction) {
		this.indexInFieldsInteraction = indexInFieldsInteraction;
	}

	public Statistics getPotential() {
		return this.potential;
	}

	public double getPotentialNatur(int year) {
		return this.potential.getValueAt(year) / prodPrice.getValueAt(year);
	}

	public double getSalary2(int j) {
		return staff.getValue(j) * salary.getValue(j) * (1 + coefSalaryOut) * 12;
	}

	public Statistics getA() {
		return mf.getA();
	}

	public Statistics getAlfa() {
		return mf.getAlfa();
	}

	public Statistics getBeta() {
		return mf.getBeta();
	}

	public Statistics getGamma() {
		return mf.getGamma();
	}

	public Statistics getProdDemand() {
		return prodDemand;
	}

	public Statistics getAmountReal() {
		return amountReal;
	}

	public Statistics getAmountNatur() {
		if (amountNatur == null) {
			amountNatur = new Statistics();
			for (int i = 0; i < amountReal.size(); i++) {
				amountNatur.add(amountReal.getYear(i), amountReal.getValue(i) / prodPrice.getValue(i));
			}
		}
		return amountNatur;
	}

	public Statistics getVarCostOld() {
		if (varCostOld == null) {
			varCostOld = new Statistics();
			for (int i = 0; i < varCost.size(); i++) {
				varCostOld.add(varCost.getYear(i), varCost.getValue(i) / amountReal.getValue(i));
			}
		}
		return varCostOld;
	}
}
