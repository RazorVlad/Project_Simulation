package mathModel.project;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class StaffNew {

	private static Logger LOG = Logger.getLogger(StaffNew.class);

	static void staffNew(int t3, int deltaT, int duration, Statistics staffNew, Statistics laborContent,
			Statistics amount) {
		LOG.debug("staffNewValue(j) = laborContent(j) * amount(j)");
		for (int j = t3 + deltaT + 1; j < duration; j++) {

			double staffNewValue = laborContent.getValueAt(j) * amount.getValueAt(j);

			LOG.debug("staffNew(" + j + ") = " + laborContent.getValueAt(j) + " * " + amount.getValueAt(j)
					+ " = " + staffNewValue);

			staffNew.add(j, staffNewValue);
		}
	}
}
