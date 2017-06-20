package mathModel.variantsResolver;

import org.apache.log4j.Logger;

public class OutputChange {
	private static Logger LOG = Logger.getLogger(OutputChange.class);

	static double getOutputChangeAt(int year) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double result = 0.;
		int start = year - vrContainer.getMaxDuration();
		if (start < vrContainer.getStartYear()) {
			LOG.debug("start < startYear ==> start=startYear");
			start = vrContainer.getStartYear();
		}
		StringBuilder outputChangeBuilder = new StringBuilder("OutputChange(" + year + ") = ");
		for (int t = start; t <= year; t++) {
			Variant var = vrContainer.getVariants().get(vrContainer.getChosenVariantsIndexes().get(t));
			for (int k = t; k < year; k++) {
				result += var.getOutputChangeAt(k);
				outputChangeBuilder.append(var.getOutputChangeAt(k));
				if (t < year) {
					outputChangeBuilder.append(" + ");
				}
			}
		}
		LOG.debug("OutputChange(" + year + ") = " + result);
		return result;
	}
}
