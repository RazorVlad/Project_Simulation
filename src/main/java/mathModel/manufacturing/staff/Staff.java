package mathModel.manufacturing.staff;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Staff {

	private static Logger LOG = Logger.getLogger(Staff.class);

	public static void staff(Statistics coefOutcome, Statistics staff, Statistics staffIncome,
			Statistics staffOutcome) {
		LOG.debug("Calculate staff");
		LOG.debug("staffValue = staff(i - 1) + staffIncome(i) - staffOutcome(i)");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = staff.getValue(i - 1) + staffIncome.getValue(i) - staffOutcome.getValue(i);
			LOG.debug("staffValue = " + staff.getValue(i - 1) + " + " + staffIncome.getValue(i) + " - "
					+ staffOutcome.getValue(i) + " = " + value);
			staff.add(coefOutcome.getYear(i), value);
			LOG.debug("staff.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
