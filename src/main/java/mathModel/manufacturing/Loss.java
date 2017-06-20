package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Loss {
	private static Logger LOG = Logger.getLogger(Loss.class);

	static void loss(Statistics coefOutcome, Statistics loss, Statistics profit) {
		LOG.debug("Calculate loss");
		loss.add(coefOutcome.getYear(0), 0);
		LOG.debug("loss.add(" + coefOutcome.getYear(0) + ", 0)");
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			if (profit.getValue(i) < 0.) {
				LOG.debug("profit < 0");
				loss.add(coefOutcome.getYear(i), profit.getValue(i));
				LOG.debug("loss.add(" + coefOutcome.getYear(i) + ", " + profit.getValue(i) + ")");
			} else {
				LOG.debug("profit >= 0");
				loss.add(coefOutcome.getYear(i), 0.);
				LOG.debug("loss.add(" + coefOutcome.getYear(i) + ",0)");
			}
		}
	}
}
