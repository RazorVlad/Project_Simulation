package mathModel.manufacturing.staff;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class StaffIncome {

	private static Logger LOG = Logger.getLogger(StaffIncome.class);

	public static void staffIncome(Statistics coefOutcome, Statistics eduAbility, Statistics staffDemand,
			Statistics staffIncome) {
		LOG.debug("Calculate staffIncome");
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			LOG.debug("eduAbility(i) = " + eduAbility.getValue(i));
			LOG.debug("staffDemand(i) = " + staffDemand.getValue(i));
			if (eduAbility.getValue(i) < staffDemand.getValue(i)) {
				LOG.debug("eduAbility(i) < staffDemand(i)");
				staffIncome.add(coefOutcome.getYear(i), eduAbility.getValue(i));
				LOG.debug("staffIncome.add(" + coefOutcome.getYear(i) + "," + eduAbility.getValue(i) + ")");
			} else {
				LOG.debug("staffDemand(i) < eduAbility(i)");
				staffIncome.add(coefOutcome.getYear(i), staffDemand.getValue(i));
				LOG.debug("staffIncome.add(" + coefOutcome.getYear(i) + "," + staffDemand.getValue(i) + ")");
			}
		}
	}

	
}
