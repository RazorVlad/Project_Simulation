package mathModel.manufacturing.capital;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class CapitalIntensity {
	private static Logger LOG = Logger.getLogger(CapitalIntensity.class);

	public static void capitalIntensityFin(Statistics coefOutcome, Statistics turnoverFin,
			Statistics capitalIntensityFin) {
		LOG.debug("Calculate capitalIntensityFin");
		LOG.debug("capitalIntensityFinValue = 1 / turnoverFin(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = 1 / turnoverFin.getValue(i);
			LOG.debug("capitalIntensityFinValue = 1 / " + turnoverFin.getValue(i) + " = " + value);
			capitalIntensityFin.add(coefOutcome.getYear(i), value);
			LOG.debug("capitalIntensityFin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	public static void capitalIntensityNat(Statistics coefOutcome, Statistics turnoverNat,
			Statistics capitalIntensityNat) {
		LOG.debug("Calculate capitalIntensityNat");
		LOG.debug("capitalIntensityNatValue = 1 / turnoverNat(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = 1 / turnoverNat.getValue(i);
			LOG.debug("capitalIntensityNatValue = 1 / " + turnoverNat.getValue(i) + " = " + value);
			capitalIntensityNat.add(coefOutcome.getYear(i), value);
			LOG.debug("capitalIntensityNat.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
