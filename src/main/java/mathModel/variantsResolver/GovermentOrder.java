package mathModel.variantsResolver;

import java.util.List;

import org.jfree.util.Log;

import mathModel.ModelObjectsContainer;

public class GovermentOrder {
	static boolean govermentOrderIsSatisfied(int year, List<Integer> vedIndexes) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double amountRealInnVal;
		double amountRealOrdVal;
		double outputChangeVal;
		double govOrderInnValue;
		double govOrderOrdValue;
		Log.debug("year = " + year);
		for (Integer ved : vedIndexes) {
			Log.debug("check ved with Index = " + ved + " ...");
			amountRealInnVal = model.getVedInn(ved).getAmountReal().getValueAt(year);
			outputChangeVal = OutputChange.getOutputChangeAt(year);
			govOrderInnValue = vrContainer.getGovOrderInn().get(ved).getValueAt(year);
			if (amountRealInnVal + outputChangeVal < govOrderInnValue) {
				Log.debug("amountRealInn() + outputChangeVal < govOrderInnValue ==> return false");
				return false;
			}
			amountRealOrdVal = model.getVedOrd(ved).getAmountReal().getValueAt(year);
			govOrderOrdValue = vrContainer.getGovOrderOrd().get(ved).getValueAt(year);
			if (amountRealOrdVal < govOrderOrdValue) {
				Log.debug("amountRealOrdVal < govOrderOrdValue ==> return false");
				return false;
			}
			Log.debug("return true, check next ved.");
		}
		return true;
	}
}
