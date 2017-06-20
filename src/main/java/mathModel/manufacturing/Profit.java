package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Profit {

	private static Logger LOG = Logger.getLogger(Turnover.class);

	static void profit(Statistics coefOutcome, Statistics amountReal, Statistics totalCost, Statistics profit) {
		LOG.debug("Calculate profit");
		LOG.debug("profitValue(i) = amountReal(i) - totalCost(i)");
		double profitValue;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			profitValue = amountReal.getValue(i) - totalCost.getValue(i);
			LOG.debug("profitValue = " + amountReal.getValue(i) + " - " + totalCost.getValue(i) + " = "
					+ profitValue);
			profit.add(coefOutcome.getYear(i), profitValue);
			LOG.debug("profit.add(" + coefOutcome.getYear(i) + ", " + profitValue + ")");
		}
	}

	static void netProfit(Statistics coefOutcome, Statistics coefTax, Statistics profit, Statistics netProfit) {
		LOG.debug("Calculate netProfit");
		LOG.debug("netProfitValue = profit.getValue(i) * (1 - coefTax.getValue(i))");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = profit.getValue(i) * (1 - coefTax.getValue(i));
			LOG.debug("netProfitValue = " + profit.getValue(i) + " * (1 - " + coefTax.getValue(i) + ") = "
					+ value);
			netProfit.add(coefOutcome.getYear(i), value);
			LOG.debug("netProfit.add(" + coefOutcome.getYear(i) + "," + value + ")");

			if (netProfit.getValue(i) < 0.) {
				LOG.debug("netProfit(i) < 0");
				netProfit.setValue(i, 0.0);
				LOG.debug("netProfit.setValue(i, 0.0)");
			}
		}
	}
}
