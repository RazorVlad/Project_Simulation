package mathModel.manufacturing.costs;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Costs {
	private static Logger LOG = Logger.getLogger(Costs.class);

	public static void costs(Statistics coefOutcome, Statistics totalCost, Statistics amountReal, Statistics costs) {
		LOG.debug("Calculate costs");
		LOG.debug("costsValue = totalCost(i) / amountReal(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = totalCost.getValue(i) / amountReal.getValue(i);
			LOG.debug("costsValue = " + totalCost.getValue(i) + " / " + amountReal.getValue(i) + " = "
					+ value);
			costs.add(coefOutcome.getYear(i), value);
			LOG.debug("costs.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
