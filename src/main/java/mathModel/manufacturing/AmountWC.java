package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class AmountWC {
	private static Logger LOG = Logger.getLogger(AmountWC.class);

	static void amountWC(Statistics coefOutcome, Statistics wc, Statistics coefWC, Statistics constCost,
			Statistics prodPrice, Statistics rawPrice, Statistics amountWC) {
		LOG.debug("Calculate amountWC");
		double value;
		LOG.debug("amountWCValue = ((wc(i) * coefWC(i) - constCost(i)) * prodPrice(i)) / rawPrice(i)");
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = ((wc.getValue(i) * coefWC.getValue(i) - constCost.getValue(i)) * prodPrice.getValue(i))
					/ rawPrice.getValue(i);
			LOG.debug("amountWCValue = ((" + wc.getValue(i) + " * " + coefWC.getValue(i) + " - "
					+ constCost.getValue(i) + ") * " + prodPrice.getValue(i) + ") / " + rawPrice.getValue(i)
					+ " = " + value);
			amountWC.add(coefOutcome.getYear(i), value);
			Log.debug("amountWC.add(" + coefOutcome.getYear(i) + "," + value + ")");
			if (amountWC.getValue(i) <= 0.0) {
				amountWC.setValue(i, 0.0);
				LOG.debug("amountWC(i) < 0.0 => amountWC(i) = 0");
			}
		}
	}
}
