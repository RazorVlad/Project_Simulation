package mathModel.variantsResolver;

import org.apache.log4j.Logger;

public class VariantOrderCheck {
	private static Logger LOG = Logger.getLogger(VariantOrderCheck.class);

	static boolean isVariantOrderOK(int variantID, int year) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		boolean result;
		LOG.debug("check variant order:");
		for (Integer mustBeBefore : vrContainer.getVariants().get(variantID).getBefore()) {
			result = false;
			for (int k = vrContainer.getStartYear(); k < year; k++) {
				if (vrContainer.getChosenVariantsIndexes().get(k).equals(mustBeBefore)) {

					LOG.debug("var[chosenIndex(" + k + ")] equals mustBeBefore = " + mustBeBefore
							+ " ==> result=true");
					result = true;
					break;
				}
			}
			if (!result) {
				LOG.debug("result = false");
				return false;
			}
		}
		for (Integer mustNotBeBefore : vrContainer.getVariants().get(variantID).getNotAfter()) {
			for (int k = vrContainer.getStartYear(); k < year; k++) {
				if (vrContainer.getChosenVariantsIndexes().get(k).equals(mustNotBeBefore)) {
					LOG.debug("var[chosenIndex(" + k + ")] equals mustNotBeBefore = " + mustNotBeBefore
							+ " ==> result=false");
					return false;
				}
			}
		}
		return true;
	}
}
