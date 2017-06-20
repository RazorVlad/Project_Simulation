package mathModel.manufacturing.staff;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class StaffDeficit {
	private static Logger LOG = Logger.getLogger(StaffDeficit.class);

	public static void staffDeficit(Statistics coefOutcome, Statistics staffDemand, Statistics eduAbility,
			Statistics staffDeficit) {
		LOG.debug("Calculate staffDeficit");
		LOG.debug("staffDeficitValue = staffDemand(i) - eduAbility(i)");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = staffDemand.getValue(i) - eduAbility.getValue(i);
			LOG.debug("staffDeficitValue =" + staffDemand.getValue(i) + " - " + eduAbility.getValue(i)
					+ " = " + value);
			staffDeficit.add(staffDemand.getYear(i), value);
			LOG.debug("staffDeficit.add(" + staffDemand.getYear(i) + "," + value + ")");
		}
	}
}
