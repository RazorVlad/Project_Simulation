package mathModel.variantsResolver;

import org.apache.log4j.Logger;

public class AssetsOutcome {
	private static Logger LOG = Logger.getLogger(AssetsOutcome.class);

	static double getAssetsOutcomeAt(int year) {
		LOG.debug("getAssetsOutcomeAt(" + year + ")");
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double result = 0.;
		int start = year - vrContainer.getMaxDuration();
		LOG.debug("start = maxDuration = " + start);
		LOG.debug("startYear = " + vrContainer.getStartYear());
		if (start < vrContainer.getStartYear()) {
			LOG.debug("start<startYear => start = startYear");
			start = vrContainer.getStartYear();
		}
		LOG.debug("result += variant(varIndex).AssetsOutcomeAt(k)");
		LOG.debug("varIndex = variantIndexes(t)");
		int varIndex;
		for (int t = start; t <= year; t++) {
			varIndex = vrContainer.getChosenVariantsIndexes().get(t);
			Variant var = vrContainer.getVariants().get(varIndex);

			LOG.debug("varIndex(" + t + ") = " + varIndex);
			for (int k = t; k <= year; k++) {
				result += var.getAssetsOutcomeAt(k);
				LOG.debug("AssetsOutcome(" + year + ") += " + var.getAssetsOutcomeAt(k) + " = " + result);
			}
		}
		LOG.debug("AssetsOutcome(" + year + " = " + result);
		return result;
	}
}
