package mathModel.manufacturing.staff;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class StaffDemand {

	private static Logger LOG = Logger.getLogger(StaffDemand.class);


	public static void staffDemand(Statistics coefOutcome, Statistics prodDemand, Statistics labor,
			Statistics staff, Statistics staffDemand) {
		LOG.debug("Calculate staffDemand");
		LOG.debug("staffDemandValue = prodDemand(i) * labor(i) - staff(i - 1) * (1 - coefOutcome(i))");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = prodDemand.getValue(i) * labor.getValue(i) - staff.getValue(i - 1)
					* (1 - coefOutcome.getValue(i));
			LOG.debug("staffDemandValue = " + prodDemand.getValue(i) + " * " + labor.getValue(i) + " - "
					+ staff.getValue(i - 1) + " * (1 - " + coefOutcome.getValue(i) + ") = " + value);
			staffDemand.add(coefOutcome.getYear(i), value);
			LOG.debug("staffDemand.add(" + coefOutcome.getYear(i) + "," + value + ")");
			if (staffDemand.getValue(i) < 0.0) {
				staffDemand.setValue(i, 0.0);
				LOG.debug("staffDemand(i) < 0.0 => staffDemand(i) = 0");
			}
		}
	}
}
