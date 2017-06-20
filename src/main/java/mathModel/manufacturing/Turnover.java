package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Turnover {

	private static Logger LOG = Logger.getLogger(Turnover.class);

	static void turnoverFin(Statistics turnoverFin, Statistics amountReal, Statistics assets,
			Statistics coefOutcome) {
		LOG.debug("Calculate turnoverNat");
		LOG.debug("turnoverFinValue = amountReal(i) / assets(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = amountReal.getValue(i) / assets.getValue(i);
			LOG.debug("turnoverFinValue = " + amountReal.getValue(i) + " / " + assets.getValue(i) + " = "
					+ value);

			turnoverFin.add(coefOutcome.getYear(i), value);
			LOG.debug("turnoverFin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	static void turnoverNat(Statistics turnoverNat, Statistics amountReal, Statistics assets,
			Statistics coefOutcome, Statistics prodPrice) {
		LOG.debug("Calculate turnoverNat");
		LOG.debug("turnoverNatValue = (amountReal(i) / prodPrice(i)) / assets(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = (amountReal.getValue(i) / prodPrice.getValue(i)) / assets.getValue(i);
			LOG.debug("turnoverNatValue = (" + amountReal.getValue(i) + " / " + prodPrice.getValue(i)
					+ ") / " + assets.getValue(i) + " = " + value);
			turnoverNat.add(coefOutcome.getYear(i), value);
			LOG.debug("turnoverNat.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
