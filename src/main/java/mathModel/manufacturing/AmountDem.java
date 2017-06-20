package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class AmountDem {
	private static Logger LOG = Logger.getLogger(AmountDem.class);

	static void amountDem(Statistics coefOutcome, Statistics potential, Statistics prodPrice,
			Statistics amountDem, Statistics prodDemand) {
		LOG.debug("Calculate amountDem");
		LOG.debug("div = potential(i) / prodPrice(i)");
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			double div = potential.getValue(i) / prodPrice.getValue(i);
			LOG.debug("div = " + potential.getValue(i) + " / " + prodPrice.getValue(i) + " = " + div);
			if (prodDemand.getValue(i) > div) {
				LOG.debug("prodDemand("+i+") = " + prodDemand.getValue(i) + " > div");
				amountDem.add(coefOutcome.getYear(i), prodDemand.getValue(i));
				LOG.debug("amountDem.add(" + coefOutcome.getYear(i) + "," + prodDemand.getValue(i) + ")");
			} else {
				LOG.debug("prodDemand("+i+") = " + prodDemand.getValue(i) + " < div");
				amountDem.add(coefOutcome.getYear(i), div);
				LOG.debug("amountDem.add(" + coefOutcome.getYear(i) + "," + div + ")");
			}
		}
	}
}
