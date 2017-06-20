package mathModel.variantsResolver;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class Outcome {
	private static Logger LOG = Logger.getLogger(Outcome.class);

	public static double outcome(int currentYear, Integer variant, double costBefore, double amountBefore,
			double varCostBefore) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		LOG.debug("calculate outcome(" + currentYear + ")");
		int lastTime = vrContainer.getLastTime();
		LOG.debug("lastTime = " + lastTime);
		int tn;
		if (currentYear > lastTime) {
			LOG.debug("current year > lastTime ==> tn = lastTime = " + lastTime);
			tn = lastTime;
		} else {
			LOG.debug("current year <= lastTime ==> tn = current year = " + currentYear);
			tn = currentYear;
		}
		double outcome = 0.;
		outcome += costBefore + varCostBefore;
		LOG.debug("outcome = costBefore + varCostBefore" + costBefore + " + " + varCostBefore + " = "
				+ outcome);
		outcome -= amountBefore;
		LOG.debug("outcome -= amountBefore = outcome - " + amountBefore + " = " + outcome);
		double alfa;
		int year;
		Log.debug("outcome += LumpSumCosts(year) * alfa");
		LOG.debug("outcome -= AssetsOutcome(year) * alfa;");
		for (int t = vrContainer.getStartYear(); t < tn; t++) {
			for (int r = 1; r < vrContainer.getL(t); r++) {

				year = vrContainer.getYears().get(r);
				LOG.debug("year = " + year);
				alfa = vrContainer.getAlfaAt(t + r - 1);
				LOG.debug("alfa = " + alfa);
				outcome += LumpSumCosts.getLumpSumCostsAt(year) * alfa;
				LOG.debug("outcome += " + LumpSumCosts.getLumpSumCostsAt(year) + " * " + alfa + " = "
						+ outcome);
				outcome -= AssetsOutcome.getAssetsOutcomeAt(year) * alfa;
				LOG.debug("outcome -= " + AssetsOutcome.getAssetsOutcomeAt(year) + " * " + alfa + " = "
						+ outcome);
			}
		}
		LOG.debug("outcome += Discount(k) * Alfa(k)");
		for (int k = vrContainer.getStartYear(); k <= currentYear; k++) {
			LOG.debug("k = " + k);
			outcome += Discount.getDiscountAt(k) * vrContainer.getAlfaAt(k);
			LOG.debug("outcome += " + Discount.getDiscountAt(k) + " * " + vrContainer.getAlfaAt(k) + " = "
					+ outcome);
		}
		LOG.debug("outcome -= discount(r) * alfaSum(r)");
		double discount;
		double alfaSum;
		StringBuilder alfaSumBuilder;
		for (int r = vrContainer.getStartYear(); r <= vrContainer.getStartYear()
				+ vrContainer.getMaxDuration(); r++) {// TODO
			discount = Discount.getDiscountAt(r);
			LOG.debug("discount(" + r + ") = " + discount);
			alfaSum = 0.;
			alfaSumBuilder = new StringBuilder("alfaSum(").append(r).append(") = ");
			for (int k = r; k <= currentYear; k++) {// TODO
				alfaSum += vrContainer.getAlfaAt(k);
				alfaSumBuilder.append(vrContainer.getAlfaAt(k));
				if (k < currentYear) {
					alfaSumBuilder.append(" + ");
				}
			}
			LOG.debug(alfaSumBuilder.toString());
			outcome -= discount * alfaSum;
			LOG.debug("outcome -= " + discount + " * " + alfaSum + " = " + discount);
		}
		vrContainer.setOutcome(outcome);
		LOG.debug("Final outcome = " + outcome);
		return outcome;
	}
}
