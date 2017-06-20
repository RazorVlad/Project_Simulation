package mathModel.manufacturing.costs;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class VarCost {
	private static Logger LOG = Logger.getLogger(VarCost.class);

	public static void varCost(Statistics coefOutcome, Statistics amountReal, double columnSum, Statistics varCost) {
		LOG.debug("Calculate varCost");
		LOG.debug("varCostValue = amountReal(i) * fi.getColumnSum(indexInFieldsInteraction)");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = amountReal.getValue(i) * columnSum;
			LOG.debug("varCostValue = " + amountReal.getValue(i) + " * " + columnSum + " = " + value);
			varCost.add(coefOutcome.getYear(i), value);
			LOG.debug("varCost.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
