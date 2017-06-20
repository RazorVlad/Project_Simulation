package mathModel.project;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class TotalCosts {
	private static Logger LOG = Logger.getLogger(TotalCosts.class);

	static void totalCosts(double totalCosts, int tau3, int duration, Statistics totalCost,
			Statistics output, Statistics investments, double cost, double costDev, double costResearch) {
		totalCosts = 0.;
		LOG.debug("totalCosts += output(i) * totalCost(i)");
		for (int i = tau3 + 1; i < duration; i++) {
			totalCosts += output.getValueAt(i) * totalCost.getValueAt(i);
			LOG.debug("totalCosts += " + output.getValueAt(i) + " * " + totalCost.getValueAt(i) + " = "
					+ totalCosts);
		}
		LOG.debug("totalCosts = " + totalCosts);
		totalCosts += cost + costDev + costResearch;
		LOG.debug("totalCosts += cost + costDev + costResearch");
		LOG.debug("totalCosts +=" + cost + " + " + costDev + " + " + costResearch + " = " + totalCosts);
		LOG.debug("totalCosts += investments(k)");
		for (int k = tau3 + 1; k < duration - 1; k++) {
			totalCosts += investments.getValueAt(k);
			LOG.debug("totalCosts += " + investments.getValueAt(k) + " = " + totalCosts);
		}
		LOG.debug("totalCosts = " + totalCosts);
	}
}
