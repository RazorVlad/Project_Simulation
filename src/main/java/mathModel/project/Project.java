package mathModel.project;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Project {

	ModelObjectsContainer model = ModelObjectsContainer.getInstance();
	private static Logger LOG = Logger.getLogger(Project.class);
	// calculated
	private Statistics power = new Statistics();
	private Statistics staff = new Statistics();
	private Statistics output = new Statistics();
	private Statistics staffBorrowed = new Statistics();
	private Statistics outcomeDeficit = new Statistics();
	private Statistics labor = new Statistics();
	private double effect1;
	private double effect2;
	private double effectSum;
	private double efficiency;
	private Statistics fullProductivity = new Statistics();
	private Statistics assetsOutcome = new Statistics();
	private double discount;
	private Statistics outputChange = new Statistics();
	private double totalCosts;
	private double newAssetsCost;
	private double income;

	// inside
	private Statistics amount = new Statistics();
	private Statistics staffNew = new Statistics();
	private Statistics amountOld = new Statistics();
	private Statistics potentialOutcome = new Statistics();
	private Statistics amountNaturNew = new Statistics();
	private int tau1;
	private int tau2;
	private int tau3;

	// input
	private String name;
	private String field;
	private String aim;
	private int sphere;
	private int techGroup;
	private int vedProducer;
	private int vedOwner;

	private Statistics laborContent;

	private int deltaT;
	private double productivity;
	private int assetsOutcomeMethod;

	private Statistics loadCoef;
	private Statistics varCostOld;
	private Statistics varCostNew;

	private int beginYear;
	private int duration;
	private int durationResearch;
	private int durationDev;
	private int durationPrep;

	private double costResearch;
	private double costDev;
	private double cost;

	private Statistics demand;
	private Statistics price;
	private Statistics investments;

	private Statistics totalCost;

	private double power0;
	private double staff0;
	private Statistics powerCost;

	private Statistics fixed;
	private double k;

	public Project(double k, Statistics fixed, String name, String field, String aim, int sphere,
			int techGroup, int vedProducer, int vedOwner, int assetsOutcomeMethod, double productivity,
			int deltaT, int beginYear, int duration, int durationResearch, int durationDev, int durationPrep,
			double power0, Statistics powerCost, double costResearch, double costDev, double cost,
			Statistics demand, Statistics price, Statistics investments, double staff0, Statistics totalCost,
			Statistics loadCoef, Statistics varCostNew, Statistics laborContent) {
		this.name = name;
		this.k = k;
		this.fixed = fixed;
		this.field = field;
		this.aim = aim;
		this.sphere = sphere;
		this.techGroup = techGroup;
		this.vedProducer = vedProducer;
		this.vedOwner = vedOwner;
		this.beginYear = beginYear;
		this.duration = beginYear + duration;
		this.durationResearch = durationResearch;
		this.durationDev = durationDev;
		this.durationPrep = durationPrep;
		this.power0 = power0;
		this.powerCost = powerCost;
		this.cost = cost;
		this.costDev = costDev;
		this.costResearch = costResearch;
		this.demand = demand;
		this.price = price;
		this.investments = investments;
		this.staff0 = staff0;
		this.totalCost = totalCost;
		this.loadCoef = loadCoef;
		this.deltaT = deltaT;
		this.productivity = productivity;
		this.assetsOutcomeMethod = assetsOutcomeMethod;
		this.varCostNew = varCostNew;
		this.laborContent = laborContent;
		tau1 = beginYear + durationResearch;
		tau2 = tau1 + durationDev;
		tau3 = tau2 + durationPrep;
		// tau3 = beginYear + durationResearch + durationDev + durationPrep;
	}

	public int getTechGroup() {
		return techGroup;
	}

	public String getName() {
		return name;
	}

	public double getK() {
		return k;
	}

	public Statistics getFixed() {
		return fixed;
	}

	public String getField() {
		return field;
	}

	public String getAim() {
		return aim;
	}

	public int getVedProducer() {
		return vedProducer;
	}

	public int getVedOwner() {
		return vedOwner;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public int getDuration() {
		return duration;
	}

	public int getDurationResearch() {
		return durationResearch;
	}

	public int getDurationDev() {
		return durationDev;
	}

	public int getDurationPrep() {
		return durationPrep;
	}

	public double getPower0() {
		return power0;
	}

	public Statistics getPowerCost() {
		return powerCost;
	}

	public double getCost() {
		return cost;
	}

	public double getCostDev() {
		return costDev;
	}

	public double getCostResearch() {
		return costResearch;
	}

	public Statistics getDemand() {
		return demand;
	}

	public Statistics getPrice() {
		return price;
	}

	public Statistics getInvestments() {
		return investments;
	}

	public double getStaff0() {
		return staff0;
	}

	public Statistics getTotalCost() {
		return totalCost;
	}

	public Statistics getLoadCoef() {
		return loadCoef;
	}

	public int getDeltaT() {
		return deltaT;
	}

	public double getProductivity() {
		return productivity;
	}

	public int getAssetsOutcomeMethod() {
		return assetsOutcomeMethod;
	}

	public Statistics getVarCostOld() {
		return varCostOld;
	}

	public Statistics getVarCostNew() {
		return varCostNew;
	}

	public Statistics getLaborContent() {
		return laborContent;
	}

	public void proceed() {
		varCostOld = model.getVedInn(vedOwner).getVarCostOld();
		power();
		OutputAndStaff.outputAndStaff(tau3, duration, power, demand, output, staff, staff0, vedProducer);
		StaffBorrowed.staffBorrowed(tau3, duration, vedProducer, staff, staffBorrowed);
		labor();
		outcomeDeficit();
		Effect.effect1(tau3, duration, price, output, effect1, costResearch, vedProducer, investments,
				costDev, cost, outcomeDeficit, totalCost);
		fullProductivity();
		AssetsOutcome.assetsOutcome(assetsOutcomeMethod, tau3, duration, k, deltaT, vedOwner, fixed,
				assetsOutcome, fullProductivity);
		Amount.amount(tau3, deltaT, duration, loadCoef, fullProductivity, amount);
		Discount.discount(tau3, deltaT, duration, varCostOld, varCostNew, amount, discount);
		OutputChange.outputChange(vedOwner, tau3, deltaT, duration, amountOld, staffNew, staff,
				assetsOutcome, potentialOutcome, amountNaturNew, outputChange, laborContent, amount);
		newAssetsCost();
		income();
		TotalCosts.totalCosts(totalCosts, tau3, duration, totalCost, output, investments, cost, costDev,
				costResearch);
		Effect.effect2(effect2, discount, income, newAssetsCost, tau3, deltaT, duration, assetsOutcome);
		Effect.effectSum(effectSum, effect1, effect2);
		efficiency();
	}

	private void power() {
		LOG.debug("power = power0 + (investments(k) / powerCost(k))");
		for (int i = tau3 + 2; i < duration; i++) {
			double powerValue = power0;
			LOG.debug("power(" + i + ") = " + power0);
			for (int k = tau3 + 1; k < i - 1; k++) {
				LOG.debug("power(" + i + ") += " + (investments.getValueAt(k) / powerCost.getValueAt(k)));
				powerValue += investments.getValueAt(k) / powerCost.getValueAt(k);
			}
			LOG.debug("power(" + i + ") = " + powerValue);
			power.add(i, powerValue);
		}
	}

	private void labor() {
		double laborValue;
		double PotentialNatur;
		LOG.debug("labor(i) = staff(i) / PotentialNatur(i)");
		for (int i = tau3 + 2; i < duration; i++) {
			PotentialNatur = model.getVedInn(vedProducer).getPotentialNatur(i);
			laborValue = staff.getValueAt(i) / PotentialNatur;
			LOG.debug("labor(" + i + ") = " + staff.getValueAt(i) + " / " + PotentialNatur + " = "
					+ laborValue);
			labor.add(i, laborValue);
		}
	}

	private void outcomeDeficit() {
		double outcomeDeficitValue;
		LOG.debug("outcomeDeficit(i) = staffBorrowed(i) / labor(i)");
		for (int i = tau3 + 2; i < duration; i++) {
			outcomeDeficitValue = staffBorrowed.getValueAt(i) / labor.getValueAt(i);
			LOG.debug("outcomeDeficit(" + i + ") = " + staffBorrowed.getValueAt(i) + " / "
					+ labor.getValueAt(i) + " = " + outcomeDeficitValue);
			outcomeDeficit.add(i, outcomeDeficitValue);
		}
	}

	private void fullProductivity() {
		double fullProductivityValue;
		LOG.debug("fullProductivityValue(i) = productivity * output(i)");
		for (int i = tau3 + deltaT + 1; i < duration; i++) {
			fullProductivityValue = productivity * output.getValueAt(i);
			LOG.debug("fullProductivity(" + i + ") = " + productivity + " * " + output.getValueAt(i) + " = "
					+ fullProductivityValue);
			fullProductivity.add(i, fullProductivityValue);
		}
	}

	private void income() {
		income = 0.;
		LOG.debug("income += ProdPriceAt(i) * outputChange(i)");
		for (int i = tau3 + 1; i < duration; i++) {
			income += model.getVedInn(vedOwner).getProdPriceAt(i) * outputChange.getValueAt(i);
			LOG.debug("income += " + model.getVedInn(vedOwner).getProdPriceAt(i) + " * "
					+ outputChange.getValueAt(i));
		}
		LOG.debug("income = " + income);
	}

	private void newAssetsCost() {
		newAssetsCost = 0.;
		LOG.debug("newAssetsCost += price(i) * output(i)");
		for (int i = tau3 + 1; i < duration; i++) {
			newAssetsCost += price.getValueAt(i) * output.getValueAt(i);
			LOG.debug("newAssetsCost += " + price.getValueAt(i) + " * " + output.getValueAt(i));
		}
		LOG.debug("newAssetsCost = " + newAssetsCost);
	}

	private void efficiency() {
		LOG.debug("efficiency = effectSum / (totalCosts + newAssetsCost)");
		efficiency = effectSum / (totalCosts + newAssetsCost);
		LOG.debug("efficiency = " + effectSum + " / (" + totalCosts + " + " + newAssetsCost + ") = "
				+ efficiency);
	}

	public double getAssetsOutcomeAt(int year) {
		return assetsOutcome.getValueAt(year);
	}

	public double getDiscountAt(int year) {
		if (year < tau3 + deltaT + 1) {
			return 0.;
		}
		return (varCostOld.getValueAt(year) - varCostNew.getValueAt(year)) * amount.getValueAt(year);
	}

	public double getLumpSumCosts(int year) {
		return LumpSumCosts.getLumpSumCosts(year, beginYear, tau1, tau2, tau3, costResearch, costDev, cost,
				output, totalCost);
	}

	public double getOutputChangeAt(int year) {
		return outputChange.getValueAt(year);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Project) {
			return ((Project) obj).getName().equals(name);
		}
		return false;
	}
}
