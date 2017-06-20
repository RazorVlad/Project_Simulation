package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Returns {
	private static Logger LOG = Logger.getLogger(Returns.class);

	static void returnOnSales(Statistics coefOutcome, Statistics netProfit, Statistics profit,
			Statistics loss, Statistics amountReal, Statistics returnOnSales) {
		LOG.debug("Calculate returnOnSales");
		LOG.debug("returnOnSalesValue = upper * 100 / amountReal(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			double upper = netProfit.getValue(i);
			LOG.debug("upper = netProfit.getValue(i) = " + netProfit.getValue(i));
			if (profit.getValue(i) < 0.) {
				upper = loss.getValue(i);
				LOG.debug("profit(i) < 0 => upper = loss(i) = " + loss.getValue(i));
			}
			value = upper * 100 / amountReal.getValue(i);
			LOG.debug("returnOnSalesValue = " + upper + " * 100 / " + amountReal.getValue(i) + " = " + value);
			returnOnSales.add(coefOutcome.getYear(i), value);
			LOG.debug("returnOnSales.add(" + coefOutcome.getYear(i) + "," + value + ");");
		}
	}

	static void returnOnMargin(Statistics coefOutcome, Statistics netProfit, Statistics totalCost,
			Statistics returnOnMargin) {
		LOG.debug("Calculate returnOnMargin");
		LOG.debug("returnOnMarginValue = netProfit(i) * 100 / totalCost(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = netProfit.getValue(i) * 100 / totalCost.getValue(i);
			LOG.debug("returnOnMarginValue = " + netProfit.getValue(i) + " * 100 / " + totalCost.getValue(i)
					+ " = " + value);
			returnOnMargin.add(coefOutcome.getYear(i), value);
			LOG.debug("returnOnMargin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
