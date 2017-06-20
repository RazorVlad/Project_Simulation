package mathModel.manufacturing.costs;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class TotalCost {
	private static Logger LOG = Logger.getLogger(TotalCost.class);

	public static void totalCost(Statistics coefOutcome, Statistics varCost, Statistics constCost,
			Statistics totalCost) {
		LOG.debug("Calculate totalCost");
		LOG.debug("totalCostValue(i) = varCost(i) + constCost(i)");
		double totalCostValue;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			totalCostValue = varCost.getValue(i) + constCost.getValue(i);
			LOG.debug("totalCostValue(i) = " + varCost.getValue(i) + " + " + constCost.getValue(i) + " = "
					+ totalCostValue);
			totalCost.add(coefOutcome.getYear(i), totalCostValue);
			LOG.debug("totalCost.add(" + coefOutcome.getYear(i) + "," + totalCostValue + ")");
		}
	}
}
