package mathModel.variantsResolver;

import java.util.List;

import mathModel.ModelObjectsContainer;

import org.apache.log4j.Logger;

public class Income {
	private static Logger LOG = Logger.getLogger(Income.class);

	static double income(List<Integer> vedIndexes, int currentYear, double amountBefore, double costBefore,
			double varCostBefore) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		int tn;
		LOG.debug("calculate income for currentYear = " + currentYear);
		LOG.debug("lastTime = " + vrContainer.getLastTime());
		if (currentYear > vrContainer.getLastTime()) {
			LOG.debug("currentYear > lastTime ==> tn=lastTime");
			tn = vrContainer.getLastTime();
		} else {
			LOG.debug("currentYear < lastTime ==> tn=currentYear");
			tn = currentYear;
		}
		double income = 0.;
		income += amountBefore;
		LOG.debug("amountBefore = " + amountBefore);
		LOG.debug("income += amountBefore = " + income);
		income -= costBefore;
		LOG.debug("costBefore = " + costBefore);
		LOG.debug("income -= costBefore = " + income);
		income -= varCostBefore;
		LOG.debug("varCostBefore = " + varCostBefore);
		LOG.debug("income -= varCostBefore = " + income);
		LOG.debug("income -= Discount(t) * Alfa(t)");
		LOG.debug("income += Alfa(t) * VedInn(ved).AmountReal(t)");
		LOG.debug("income += Alfa(t) * VedOrd(ved).AmountReal(t)");
		double amountRealInnValue;
		double amountRealOrdValue;
		double alfa;
		for (int t = vrContainer.getStartYear(); t < currentYear; t++) {
			income -= Discount.getDiscountAt(t) * vrContainer.getAlfaAt(t);
			alfa = vrContainer.getAlfaAt(t);
			LOG.debug("income -= " + Discount.getDiscountAt(t) + " * " + alfa + " = " + income);
			for (Integer ved : vedIndexes) {
				amountRealInnValue = model.getVedInn(ved).getAmountReal().getValueAt(t);
				income += alfa * amountRealInnValue;
				LOG.debug("income += " + alfa + " * " + amountRealInnValue + " = " + income);

				amountRealOrdValue = model.getVedOrd(ved).getAmountReal().getValueAt(t);
				income += alfa * amountRealOrdValue;
				LOG.debug("income += " + alfa + " * " + amountRealOrdValue + " = " + income);
			}
			income += vrContainer.getAlfaAt(t) * OutputChange.getOutputChangeAt(t);
		}
		LOG.debug("income = " + income);
		LOG.debug("income -= LumpSumCosts(r) * Alfa(t - StartYear() + r)");
		LOG.debug("income += AssetsOutcome(r) * Alfa(t - StartYear() + r)");
		double anotherAlfa;
		for (int t = vrContainer.getStartYear(); t < tn; t++) {
			for (int r = vrContainer.getStartYear(); r < vrContainer.getL(t); r++) {
				anotherAlfa = vrContainer.getAlfaAt(t - vrContainer.getStartYear() + r);
				income -= LumpSumCosts.getLumpSumCostsAt(r) * anotherAlfa;
				LOG.debug("income -= " + LumpSumCosts.getLumpSumCostsAt(r) + " * " + anotherAlfa + " = "
						+ income);

				income += AssetsOutcome.getAssetsOutcomeAt(r) * anotherAlfa;
				LOG.debug("income += " + AssetsOutcome.getAssetsOutcomeAt(r) + " * " + anotherAlfa + " = "
						+ income);
			}
		}
		LOG.debug("income = " + income);
		LOG.debug("income += Discount(r), (r=startYear, r<=startYear+maxDuration");
		LOG.debug("income *= sum(alfa(k)),(k=r; k<=currentYear)");
		StringBuilder alfaSumBuilder;
		for (int r = vrContainer.getStartYear(); r <= vrContainer.getStartYear()
				+ vrContainer.getMaxDuration(); r++) {// TODO
			alfaSumBuilder = new StringBuilder("sum(alfa(k) = ");
			income += Discount.getDiscountAt(r);
			LOG.debug("income += " + Discount.getDiscountAt(r));
			double val = 0.;
			for (int k = r; k <= currentYear; k++) {// TODO
				val += vrContainer.getAlfaAt(k);
				alfaSumBuilder.append(vrContainer.getAlfaAt(k));
				if (k < currentYear) {
					alfaSumBuilder.append(" + ");
				}
			}
			LOG.debug(alfaSumBuilder.toString());
			income *= val;
			LOG.debug("income *= " + val + " = " + income);
		}
		LOG.debug("Final income = " + income);
		vrContainer.setIncome(income);
		return income;
	}
}
