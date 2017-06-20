package mathModel.manufacturing.intangibles;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Intangibles {
	private static Logger LOG = Logger.getLogger(Intangibles.class);

	public static void intangibles(Statistics coefOutcome, Statistics intangibles, Statistics coefObsIntangibles,
			Statistics inangiblesIncome, Statistics intangiblesOutcome) {
		LOG.debug("Calculate intangibles");
		LOG.debug("intangiblesOutcomeValue = intangibles(i - 1) * coefObsIntangibles(i)");
		LOG.debug("intangiblesValue = intangibles(i - 1) + inangiblesIncome(i) - intangiblesOutcome(i)");
		double value;
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);

			value = intangibles.getValue(i - 1) * coefObsIntangibles.getValue(i);
			LOG.debug("intangiblesOutcomeValue = " + intangibles.getValue(i - 1) + " * "
					+ coefObsIntangibles.getValue(i) + " = " + value);
			intangiblesOutcome.add(coefObsIntangibles.getYear(i), value);
			LOG.debug("intangiblesOutcome.add(" + coefObsIntangibles.getYear(i) + "," + value + ")");

			value = intangibles.getValue(i - 1) + inangiblesIncome.getValue(i)
					- intangiblesOutcome.getValue(i);
			LOG.debug("intangiblesValue = " + intangibles.getValue(i - 1) + " + "
					+ inangiblesIncome.getValue(i) + " - " + intangiblesOutcome.getValue(i) + " = " + value);
			intangibles.add(inangiblesIncome.getYear(i), value);
			LOG.debug("intangibles.add(" + inangiblesIncome.getYear(i) + "," + value + ")");
		}
	}
}
