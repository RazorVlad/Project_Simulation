package mathModel.variantsResolver;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Leftovers {
	private static Logger LOG = Logger.getLogger(Leftovers.class);

	static boolean leftovers(int year, Statistics leftovers, Statistics F) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		LOG.debug("count leftovers for year = " + year);
		LOG.debug("maxDuration = " + vrContainer.getMaxDuration());
		for (int i = year; i <= vrContainer.getDuration(); i++) {
			if (i <= year + vrContainer.getMaxDuration() - 1) {
				LOG.debug("i <= (year + maxDuaration - 1)");
				double value = leftovers.getValueAt(i - 1) / vrContainer.getAlfaAt(i - 1) + F.getValueAt(i);
				LOG.debug("leftovers(" + i + ") = leftovers(" + (i - 1) + ") / alfa(" + (i - 1) + ") + F("
						+ i + ")");
				LOG.debug("leftovers(" + i + ") = " + leftovers.getValueAt(i - 1) + " / "
						+ vrContainer.getAlfaAt(i - 1) + " + " + F.getValueAt(i) + " = " + value);
				LOG.debug("leftovers(" + i + ") += LumpSumCosts(year + 1 - p) + AssetsOutcome(year + 1 - p)");
				LOG.debug("p=(i + 1 - MaxDuration; p<=i");
				double lumpSumCostval;
				double assetsOutcomeVal;
				for (int p = i + 1 - vrContainer.getMaxDuration(); p <= i; p++) {
					lumpSumCostval = LumpSumCosts.getLumpSumCostsAt(year + 1 - p);
					LOG.debug("LumpSumCosts(" + year + "+1-" + p + ")=" + lumpSumCostval);
					assetsOutcomeVal = AssetsOutcome.getAssetsOutcomeAt(year + 1 - p);
					LOG.debug("AssetsOutcome(" + year + "+1-" + p + ")=" + assetsOutcomeVal);
					value += lumpSumCostval + assetsOutcomeVal;
					LOG.debug("leftovers(" + i + ") += " + lumpSumCostval + " + " + assetsOutcomeVal);
				}
				value *= vrContainer.getAlfaAt(i);
				LOG.debug("leftovers(" + i + ") *= alfa(" + i + ") = " + value);
				leftovers.add(i, value);
			} else {
				LOG.debug("i > (year + maxDuaration - 1)");
				double value = (leftovers.getValueAt(i - 1) / vrContainer.getAlfaAt(i - 1) + F.getValueAt(i))
						* vrContainer.getAlfaAt(i);
				LOG.debug("leftovers(" + i + " = leftovers(" + (i - 1) + " / alfa (" + (i - 1) + ") + F(" + i
						+ ") * alfa(" + i + ")");
				LOG.debug("leftovers(" + i + " = " + value);
				leftovers.add(i, value);
			}
		}
		if (leftovers.getValueAt(year) >= 0) {
			LOG.debug("leftovers (" + year + ") >=0 ==> return true");
		} else {
			LOG.debug("leftovers (" + year + ") <0 ==> return false");
		}
		return leftovers.getValueAt(year) >= 0.;
	}
}
