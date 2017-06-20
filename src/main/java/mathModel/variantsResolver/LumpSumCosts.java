package mathModel.variantsResolver;


public class LumpSumCosts {
	static double getLumpSumCostsAt(int year) {

		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double result = 0.;
		int start = year - vrContainer.getMaxDuration();
		if (start < vrContainer.getStartYear()) {
			start = vrContainer.getStartYear();
		}
		for (int t = start; t <= year; t++) {
			Variant var = vrContainer.getVariants().get(vrContainer.getChosenVariantsIndexes().get(t));
			for (int k = t; k <= year; k++) {
				result += var.getLumpSumCosts(k);
			}
		}
		return result;
	}
}
