package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class BreakEvenPoint {

	private static Logger LOG = Logger.getLogger(BreakEvenPoint.class);

	static void breakEvenPoint(int indexInFieldsInteraction, Statistics coefOutcome, Statistics constCost,
			Statistics breakEvenPoint, Statistics prodPrice, double columnSum) {
		LOG.debug("Calculate breakEvenPoint");
		LOG.debug("indexInFieldsInteraction = " + indexInFieldsInteraction);
		LOG.debug("breakEvenPointValue = constCost(i) / (prodPrice(i) * (1 - fi.getColumnSum(indexInFieldsInteraction)))");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = constCost.getValue(i) / (prodPrice.getValue(i) * (1 - columnSum));
			LOG.debug("breakEvenPointValue = " + constCost.getValue(i) + " / (" + prodPrice.getValue(i)
					+ " * (1 - " + columnSum + ")) = " + value);
			breakEvenPoint.add(coefOutcome.getYear(i), value);
			LOG.debug("breakEvenPoint.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	static void breakEvenPointMoney(Statistics coefOutcome, Statistics breakEvenPoint, Statistics prodPrice,
			Statistics breakEvenPointMoney) {
		LOG.debug("Calculate breakEvenPointMoney");
		LOG.debug("breakEvenPointMoneyValue = breakEvenPoint(i) * prodPrice(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = breakEvenPoint.getValue(i) * prodPrice.getValue(i);
			LOG.debug("breakEvenPointMoneyValue = " + breakEvenPoint.getValue(i) + " * "
					+ prodPrice.getValue(i) + " = " + value);
			breakEvenPointMoney.add(coefOutcome.getYear(i), value);
			LOG.debug("breakEvenPointMoney.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
