package mathModel.manufacturing.costs;

import org.apache.log4j.Logger;

import mathModel.Statistics;
import mathModel.manufacturing.Multiply;

public class ExtraCost {
	private static Logger LOG = Logger.getLogger(ExtraCost.class);

	public static void extraCost(Statistics coefOutcome, Statistics extraCost, Statistics coefExtraCost) {
		LOG.debug("Calculate extraCost");
		double value;
		LOG.debug("extraCostValue = extraCost(0) * multiply(coefExtraCost, i)");
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = extraCost.getValue(0) * Multiply.multiply(coefExtraCost, i);
			LOG.debug("extraCostValue = " + extraCost.getValue(0) + " * " + Multiply.multiply(coefExtraCost, i)
					+ " = " + value);
			extraCost.add(coefExtraCost.getYear(i), value);
			LOG.debug("extraCost.add(" + coefExtraCost.getYear(i) + "," + value + ")");
		}
	}
}
