package mathModel.manufacturing.staff;

import mathModel.Statistics;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class StaffOutcome {
	private static Logger LOG = Logger.getLogger(StaffOutcome.class);

	public static void staffOutcome(Statistics coefOutcome, Statistics staff, Statistics staffOutcome) {
		LOG.debug("Calculate staffOutcome");
		LOG.debug("staffOutcomeValue = staff(i - 1) * coefOutcome(i)");
		double value;
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = staff.getValue(i - 1) * coefOutcome.getValue(i);
			LOG.debug("staffOutcomeValue = " + staff.getValue(i - 1) + " * " + coefOutcome.getValue(i)
					+ " = " + value);
			staffOutcome.add(coefOutcome.getYear(i), value);
			Log.debug("staffOutcome.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
