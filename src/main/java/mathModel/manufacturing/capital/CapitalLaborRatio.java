package mathModel.manufacturing.capital;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class CapitalLaborRatio {
	private static Logger LOG = Logger.getLogger(CapitalLaborRatio.class);

	public static void capitalLaborRatio(Statistics coefOutcome, Statistics assets, Statistics staff,
			Statistics capitalLaborRatio) {
		LOG.debug("Calculate capitalLaborRatio");
		LOG.debug("capitalLaborRatioValue = assets(i) / staff(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			value = assets.getValue(i) / staff.getValue(i);
			LOG.debug("capitalLaborRatioValue("+i+") = " + assets.getValue(i) + " / " + staff.getValue(i) + " = "
					+ value);
			capitalLaborRatio.add(coefOutcome.getYear(i), value);
			LOG.debug("capitalLaborRatio.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
