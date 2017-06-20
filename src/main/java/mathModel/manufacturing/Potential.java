package mathModel.manufacturing;

import mathModel.Statistics;
import mathModel.manFun.ManFun;

import org.apache.log4j.Logger;

public class Potential {
	private static Logger LOG = Logger.getLogger(Potential.class);

	static void potential(boolean innovational, Statistics coefOutcome, ManFun mf, Statistics staff,
			Statistics assets, Statistics intangibles, Statistics potential) {
		double value;
		if (innovational) {
			LOG.debug("Calculate innovational potential");
			LOG.debug("potentialValue = mf.A(i) * (staff(i)^mf.Alfa(i)) * (assets(i)^mf.Beta(i)) * (intangibles(i)^mf.Gamma(i))");
			for (byte i = 1; i < coefOutcome.size(); i++) {
				LOG.debug("i = " + i);
				value = mf.getA().getValue(i) * Math.pow(staff.getValue(i), mf.getAlfa().getValue(i))
						* Math.pow(assets.getValue(i), mf.getBeta().getValue(i))
						* Math.pow(intangibles.getValue(i), mf.getGamma().getValue(i));
				LOG.debug("potentialValue = " + mf.getA().getValue(i) + " * (" + staff.getValue(i) + "^"
						+ mf.getAlfa().getValue(i) + ") * (" + assets.getValue(i) + "^"
						+ mf.getBeta().getValue(i) + ") * (" + intangibles.getValue(i) + "^"
						+ mf.getGamma().getValue(i) + ") = " + value);
				potential.add(staff.getYear(i), value);
				LOG.debug("potential.add(" + staff.getYear(i) + "," + value + ")");
			}
		} else {
			LOG.debug("Calculate not innovational potential");
			LOG.debug("potentialValue = mf.A(i) * (staff(i) ^ mf.Alfa(i)) * (assets(i) ^ mf.Beta(i))");
			for (byte i = 1; i < coefOutcome.size(); i++) {
				LOG.debug("i = " + i);
				value = mf.getA().getValue(i) * Math.pow(staff.getValue(i), mf.getAlfa().getValue(i))
						* Math.pow(assets.getValue(i), mf.getBeta().getValue(i));
				LOG.debug("potentialValue = " + mf.getA().getValue(i) + " * (" + staff.getValue(i) + "^"
						+ mf.getAlfa().getValue(i) + ") * (" + assets.getValue(i) + "^"
						+ mf.getBeta().getValue(i) + ") = " + value);
				potential.add(staff.getYear(i), value);
				LOG.debug("potential.add(" + staff.getYear(i) + "," + value + ")");
			}
		}
	}
}
