package mathModel.manufacturing.intangibles;

import mathModel.RnD;
import mathModel.Statistics;

import org.apache.log4j.Logger;

public class IntangiblesIncome {
	private static Logger LOG = Logger.getLogger(IntangiblesIncome.class);

	public static void intangiblesIncome(Statistics coefOutcome, RnD rnd, Statistics workingPart,
			Statistics workingIntangiblesPart, Statistics inangiblesIncome) {
		LOG.debug("Calculate intangiblesIncome");
		LOG.debug("inangiblesIncomeValue = rnd.Real(i) * workingPart(i) * workingIntangiblesPart(i)");
		double value;
		for (byte i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = rnd.getReal(i) * workingPart.getValue(i) * workingIntangiblesPart.getValue(i);
			LOG.debug("inangiblesIncomeValue = " + rnd.getReal(i) + "*" + workingPart.getValue(i) + "*"
					+ workingIntangiblesPart.getValue(i) + " = " + value);
			inangiblesIncome.add(workingPart.getYear(i), value);
			LOG.debug("inangiblesIncome.add(" + workingPart.getYear(i) + "," + value + ")");
		}
	}
}
