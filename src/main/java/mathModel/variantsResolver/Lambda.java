package mathModel.variantsResolver;

import java.util.List;

import mathModel.ModelObjectsContainer;

public class Lambda {
	public static double maxLambda(List<Integer> vedIndexes, int t, Integer variant,
			ZeroBestAndWorst zeroBestAndWorst, double amountBefore, double costBefore, double varCostBefore) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();
		double border1 = Income.income(vedIndexes, t, amountBefore, costBefore, varCostBefore);
		for (int k = t + 1; k < vrContainer.getDuration(); k++) {
			for (Integer ved : vedIndexes) {
				border1 += vrContainer.getAlfaAt(t) * model.getVedInn(ved).getProdDemand().getValueAt(k);
				border1 += vrContainer.getAlfaAt(t) * model.getVedOrd(ved).getProdDemand().getValueAt(k);
			}
		}

		double border2 = Outcome.outcome(t, variant, costBefore, amountBefore, varCostBefore);

		double border3 = Innov.innov(t, vedIndexes);
		for (int k = t + 1; k < vrContainer.getDuration(); k++) {
			for (Integer ved : vedIndexes) {
				border1 += vrContainer.getAlfaAt(t) * model.getVedInn(ved).getProdDemand().getValueAt(k);
			}
		}

		double lambda1 = (zeroBestAndWorst.getZeroBestFirst() - border1)
				/ (zeroBestAndWorst.getZeroBestFirst() - zeroBestAndWorst.getZeroWorstFirst());
		double lambda2 = (border2 - zeroBestAndWorst.getZeroBestSecond())
				/ (zeroBestAndWorst.getZeroWorstSecond() - zeroBestAndWorst.getZeroBestSecond());
		double lambda3 = (zeroBestAndWorst.getZeroBestThird() - border3)
				/ (zeroBestAndWorst.getZeroBestThird() - zeroBestAndWorst.getZeroWorstThird());

		double maxLambda = lambda1;
		if (lambda2 > maxLambda) {
			maxLambda = lambda2;
		}
		if (lambda3 > maxLambda) {
			maxLambda = lambda3;
		}

		return maxLambda;
	}

}
