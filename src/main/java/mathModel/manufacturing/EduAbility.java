package mathModel.manufacturing;

import mathModel.Education;
import mathModel.Statistics;

import org.apache.log4j.Logger;

public class EduAbility {
	private static Logger LOG = Logger.getLogger(EduAbility.class);

	static void eduAbility(boolean innovational, Statistics coefOutcome, Education edu, Statistics fieldPart,
			Statistics qPart, Statistics eduAbility) {
		double value;
		if (innovational) {
			LOG.debug("calculate eduAbility for innovational");
			LOG.debug("eduAbilityValue = StaffWork(i) * fieldPart(i) * qPart(i)");
			for (byte i = 0; i < coefOutcome.size(); i++) {
				LOG.debug("i = " + i);
				value = edu.getStaffWork().getValue(i) * fieldPart.getValue(i) * qPart.getValue(i);
				LOG.debug("eduAbilityValue = " + edu.getStaffWork().getValue(i) + "*" + fieldPart.getValue(i)
						+ "*" + qPart.getValue(i) + " = " + value);
				eduAbility.add(fieldPart.getYear(i), value);
				LOG.debug("eduAbility.add(" + fieldPart.getYear(i) + "," + value + ");");
			}
		} else {
			LOG.debug("calculate eduAbility for not innovational");
			LOG.debug("eduAbilityValue = StaffWork(i) * fieldPart(i) * (1 - qPart(i))");
			for (byte i = 0; i < coefOutcome.size(); i++) {
				LOG.debug("i = " + i);
				value = edu.getStaffWork().getValue(i) * fieldPart.getValue(i) * (1 - qPart.getValue(i));
				LOG.debug("eduAbilityValue = " + edu.getStaffWork().getValue(i) + " * "
						+ fieldPart.getValue(i) + " * (1 - " + qPart.getValue(i) + ") = " + value);
				eduAbility.add(fieldPart.getYear(i), value);
				LOG.debug("eduAbility.add(" + fieldPart.getYear(i) + "," + value + ")");
			}
		}
	}
}
