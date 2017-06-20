package mathModel.project;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Amount {
	private static Logger LOG = Logger.getLogger(Amount.class);

	static void amount(int tau3, int deltaT, int duration, Statistics loadCoef, Statistics fullProductivity,
			Statistics amount) {
		LOG.debug("amount(j) = loadCoef(r) * fullProductivity(r)");
		StringBuilder amountBuilder;
		double value;
		for (int j = tau3 + deltaT + 1; j < duration; j++) {
			amountBuilder = new StringBuilder("amount(").append(j).append(") = ");
			value = 0;
			for (int r = tau3 + deltaT + 1; r < j; r++) {
				value += loadCoef.getValueAt(r) * fullProductivity.getValueAt(r);
				amountBuilder.append(loadCoef.getValueAt(r)).append(" * ")
						.append(fullProductivity.getValueAt(r));
				if (r != j - 1) {
					amountBuilder.append(" + ");
				}
			}
			amountBuilder.append(" = ").append(value);
			LOG.debug(amountBuilder);
			amount.add(j, value);
		}
	}
}
