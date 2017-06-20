package mathModel.variantsResolver;

import java.util.List;

import mathModel.ModelObjectsContainer;

public class Innov {
	static double innov(int currentYear, List<Integer> vedIndexes) {
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		double innov = 0.;
		for (int t = vrContainer.getStartYear(); t < currentYear; t++) {
			for (Integer ved : vedIndexes) {
				innov += vrContainer.getAlfaAt(t) * (model.getVedInn(ved).getAmountReal().getValueAt(t));
			}
			innov += OutputChange.getOutputChangeAt(t);
		}
		vrContainer.setInnov(innov);
		return innov;
	}
}
