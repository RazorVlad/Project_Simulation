package mathModel.manufacturing.staff;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class StaffEscess {

	private static Logger LOG = Logger.getLogger(StaffEscess.class);

	public static void staffEscess(Statistics coefOutcome, Statistics staff, Statistics prodDemand,
			Statistics staffExcess, Statistics labor) {
		LOG.debug("Calculate staffEscess");
		LOG.debug("staffExcessValue = staff(i - 1) * (1 - coefOutcome(i)) - prodDemand(i) * labor(i)");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = staff.getValue(i - 1) * (1 - coefOutcome.getValue(i)) - prodDemand.getValue(i)
					* labor.getValue(i);
			LOG.debug("staffExcessValue = " + staff.getValue(i - 1) + " * (1 - " + coefOutcome.getValue(i)
					+ ") - " + prodDemand.getValue(i) + " * " + labor.getValue(i) + " = " + value);
			staffExcess.add(coefOutcome.getYear(i), value);
			LOG.debug("staffExcess.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
