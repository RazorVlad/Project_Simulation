package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class FinSafetyMargin {

	private static Logger LOG = Logger.getLogger(FinSafetyMargin.class);

	static void finSafetyMargin(Statistics coefOutcome, Statistics amountReal,
			Statistics breakEvenPointMoney, Statistics finSafetyMargin) {
		LOG.debug("Calculate finSafetyMargin");
		LOG.debug("finSafetyMarginValue = amountReal(i) - breakEvenPointMoney(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = amountReal.getValue(i) - breakEvenPointMoney.getValue(i);
			LOG.debug("finSafetyMarginValue = " + amountReal.getValue(i) + " - "
					+ breakEvenPointMoney.getValue(i) + " = " + value);
			finSafetyMargin.add(coefOutcome.getYear(i), value);
			LOG.debug("finSafetyMargin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	static void finSafetyMarginRe(Statistics coefOutcome, Statistics finSafetyMargin, Statistics amountReal) {
		LOG.debug("Calculate finSafetyMarginRe");
		LOG.debug("finSafetyMarginValue = finSafetyMargin(i) * 100 / amountReal(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = finSafetyMargin.getValue(i) * 100 / amountReal.getValue(i);
			LOG.debug("finSafetyMarginValue = " + finSafetyMargin.getValue(i) + " * 100 / "
					+ amountReal.getValue(i) + " = value");
			finSafetyMargin.add(coefOutcome.getYear(i), value);
			LOG.debug("finSafetyMargin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
