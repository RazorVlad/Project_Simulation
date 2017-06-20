package mathModel.variantsResolver;

import org.apache.log4j.Logger;

public class Discount {
	private static Logger LOG = Logger.getLogger(Discount.class);

	static double getDiscountAt(int year) {
		LOG.debug("calculate discount for year = " + year);
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double result = 0.;
		int start = year - vrContainer.getMaxDuration();
		LOG.debug("start = year - maxDuration = " + year + " - " + vrContainer.getMaxDuration() + " = "
				+ start);
		LOG.debug("startYear = vrContainer.getStartYear()");
		if (start < vrContainer.getStartYear()) {
			LOG.debug("start < startYear ==> start=startYear");
			start = vrContainer.getStartYear();
		}
		LOG.debug("discount = sum(var.discount(k)");
		StringBuilder discountBuilder = new StringBuilder("discount(").append("year").append(" = ");
		for (int t = start; t <= year; t++) {
			Variant var = vrContainer.getVariants().get(vrContainer.getChosenVariantsIndexes().get(t));
			for (int k = t; k <= year; k++) {
				result += var.getDiscountAt(k);
				discountBuilder.append(var.getDiscountAt(k));
				if (k < year) {
					discountBuilder.append(" + ");
				}
			}
		}
		LOG.debug(discountBuilder.toString());
		LOG.debug("discount(" + year + ") = " + result);
		return result;
	}
}
