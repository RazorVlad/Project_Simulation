package mathModel.project;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class LumpSumCosts {

	private static Logger LOG = Logger.getLogger(LumpSumCosts.class);

	static double getLumpSumCosts(int year, int beginYear, int tau1, int tau2, int tau3, double costResearch,
			double costDev, double cost, Statistics output, Statistics totalCost) {
		LOG.debug("count LumpSumCosts for year " + year + ":");
		double LumpSumCosts = 0.;
		if (year == beginYear) {
			LOG.debug(year + " = beginYear => LumpSumCosts = 0");
			return LumpSumCosts;
		} else {
			LOG.debug("tau1 = " + tau1);
			LOG.debug("tau2 = " + tau2);
			LOG.debug("tau3 = " + tau3);
			if (year <= tau1) {
				LOG.debug(year + " <= tau1");
				LumpSumCosts = costResearch / (year - beginYear);
				LOG.debug("LumpSumCosts = costResearch / (" + year + " - beginYear) = " + costResearch
						+ " / (" + year + " - " + beginYear + ") = " + LumpSumCosts);
				return LumpSumCosts;
			} else if (year <= tau2) {
				LOG.debug(year + " <= tau2");
				LumpSumCosts = costDev / (year - tau1);
				LOG.debug("LumpSumCosts =  costDev / (" + year + " - tau1) = " + costDev + " / (" + year
						+ " - " + tau1);
				return LumpSumCosts;
			} else if (year <= tau3) {// TODO стояло tau2, опечатка?
				LOG.debug(year + " <= tau3");
				LumpSumCosts = cost / (year - tau3);// стояло тоже tau2,
													// опечатка?
				LOG.debug("LumpSumCosts = cost / (" + year + " - tau3) = " + cost + " / (" + year + " - "
						+ tau3 + ") = " + LumpSumCosts);

				return LumpSumCosts;
			} else {
				LumpSumCosts = output.getValueAt(year) * totalCost.getValueAt(year);
				LOG.debug("LumpSumCosts = output(" + year + ") * totalCost(" + year + ") = "
						+ output.getValueAt(year) + " * " + totalCost.getValueAt(year) + " = " + LumpSumCosts);
				return LumpSumCosts;
			}
		}
	}
}
