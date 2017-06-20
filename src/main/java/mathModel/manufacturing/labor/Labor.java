package mathModel.manufacturing.labor;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Labor {
	private static Logger LOG = Logger.getLogger(Labor.class);

	public static void labor(Statistics staff, Statistics potential, Statistics prodPrice, Statistics labor,
			Statistics coefOutcome, Statistics laborContentNat) {
		LOG.debug("Calculate labor");
		double value = staff.getValue(0) / (potential.getValue(0) / prodPrice.getValue(0));
		LOG.debug("laborValue = staff(0) / (potential(0) / prodPrice(0)) = " + staff.getValue(0) + " / ("
				+ potential.getValue(0) + " / " + prodPrice.getValue(0) + ") = " + value);
		labor.add(coefOutcome.getYear(1), value);
		LOG.debug("labor.add(" + coefOutcome.getYear(1) + "," + value + ")");
		for (int i = 2; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = laborContentNat.getValue(i - 1);
			LOG.debug("laborValue = laborContentNat(i - 1) = " + value);
			labor.add(coefOutcome.getYear(i), value);
			LOG.debug("labor.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
