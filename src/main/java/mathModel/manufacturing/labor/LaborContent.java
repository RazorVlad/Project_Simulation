package mathModel.manufacturing.labor;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class LaborContent {
	private static Logger LOG = Logger.getLogger(LaborContent.class);

	public static void laborContentFin(Statistics coefOutcome, Statistics laborContentFin,
			Statistics workforceProdFin) {
		LOG.debug("Calculate laborContentFin");
		LOG.debug("laborContentFinValue = 1 / workforceProdFin(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = 1 / workforceProdFin.getValue(i);
			LOG.debug("laborContentFinValue = 1 / " + workforceProdFin.getValue(i) + " = " + value);
			laborContentFin.add(coefOutcome.getYear(i), value);
			LOG.debug("laborContentFin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	public static void laborContentNat(Statistics coefOutcome, Statistics laborContentNat,
			Statistics workforceProdNat) {
		LOG.debug("Calculate laborContentNat");
		LOG.debug("laborContentNatValue = 1 / workforceProdNat(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = 1 / workforceProdNat.getValue(i);
			LOG.debug("laborContentNatValue = 1 / " + workforceProdNat.getValue(i) + " = " + value);
			laborContentNat.add(coefOutcome.getYear(i), value);
			LOG.debug("laborContentNat.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
