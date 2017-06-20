package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class WorkforceProd {
	private static Logger LOG = Logger.getLogger(WorkforceProd.class);

	static void workforceProdFin(Statistics coefOutcome, Statistics amountReal, Statistics staff,
			Statistics workforceProdFin) {
		LOG.debug("Calculate workforceProdFin");
		LOG.debug("workforceProdFinValue = amountReal(i) / staff(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = amountReal.getValue(i) / staff.getValue(i);
			LOG.debug("workforceProdFinValue = " + amountReal.getValue(i) + " / " + staff.getValue(i) + " = "
					+ value);
			workforceProdFin.add(coefOutcome.getYear(i), value);
			LOG.debug("workforceProdFin.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

	static void workforceProdNat(Statistics coefOutcome, Statistics amountReal, Statistics prodPrice,
			Statistics workforceProdNat, Statistics staff) {
		LOG.debug("Calculate workforceProdNat");
		LOG.debug("workforceProdNatValue = (amountReal(i) / prodPrice(i))/ staff(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = (amountReal.getValue(i) / prodPrice.getValue(i)) / staff.getValue(i);
			LOG.debug("workforceProdNatValue = (" + amountReal.getValue(i) + " / " + prodPrice.getValue(i)
					+ ") / " + staff.getValue(i) + " = " + value);
			workforceProdNat.add(coefOutcome.getYear(i), value);
			LOG.debug("workforceProdNat.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}
}
