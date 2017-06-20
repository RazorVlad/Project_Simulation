package mathModel.variantsResolver;

import java.util.List;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

public class ZeroBestAndWorst {
	private double zeroBestFirst;

	public double getZeroBestFirst() {
		return zeroBestFirst;
	}

	public double getZeroBestSecond() {
		return zeroBestSecond;
	}

	public double getZeroBestThird() {
		return zeroBestThird;
	}

	public double getZeroWorstFirst() {
		return zeroWorstFirst;
	}

	public double getZeroWorstSecond() {
		return zeroWorstSecond;
	}

	public double getZeroWorstThird() {
		return zeroWorstThird;
	}

	private double zeroBestSecond;
	private double zeroBestThird;

	private double zeroWorstFirst;
	private double zeroWorstSecond;
	private double zeroWorstThird;

	private static Logger LOG = Logger.getLogger(ZeroBestAndWorst.class);

	public void zeroBestAndWorst(double costBefore, double amountBefore, double varCostBefore, double f0,
			List<Integer> vedIndexes, Statistics f) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		VRObjectsContainer vrContainer = VRObjectsContainer.getInstance();

		zeroBestSecond = costBefore - amountBefore + varCostBefore;
		LOG.debug("zeroBestSecond = costBefore - amountBefore + varCostBefore = " + costBefore + " - "
				+ amountBefore + " + " + varCostBefore + " = " + zeroBestSecond);

		zeroWorstSecond = f0 + costBefore - amountBefore + varCostBefore;
		LOG.debug("zeroWorstSecond = F0 + costBefore - amountBefore + varCostBefore = " + f0 + " + "
				+ costBefore + " - " + amountBefore + " + " + varCostBefore + " = " + zeroWorstSecond);

		zeroWorstFirst = -zeroWorstSecond;
		LOG.debug("zeroWorstFirst = -zeroWorstSecond = " + zeroWorstFirst);

		LOG.debug("zeroBestFirst += AlfaAt(t) * (VedOrd(index).ProdDemand(t) + VedInn(index).ProdDemand(t)) - zeroBestSecond");
		LOG.debug("zeroBestThird += AlfaAt(t) * VedInn(index).ProdDemand(t)");
		LOG.debug("zeroWorstThird += AlfaAt(t) * govOrderInn(index).ValueAt(t)");
		LOG.debug("zeroWorstFirst += AlfaAt(t) * (govOrderOrd(index).ValueAt(t) + govOrderInn(index).ValueAt(t))");
		LOG.debug("start calculate:");
		for (int t = vrContainer.getStartYear(); t < vrContainer.getDuration(); t++) {
			zeroWorstSecond += f.getValueAt(t);
			for (Integer index : vedIndexes) {
				zeroBestFirst += vrContainer.getAlfaAt(t)
						* (model.getVedOrd(index).getProdDemand().getValueAt(t) + model.getVedInn(index)
								.getProdDemand().getValueAt(t)) - zeroBestSecond;
				LOG.debug("zeroBestFirst += " + vrContainer.getAlfaAt(t) + " * ("
						+ model.getVedOrd(index).getProdDemand().getValueAt(t) + " + "
						+ model.getVedInn(index).getProdDemand().getValueAt(t) + ") - " + zeroBestSecond
						+ " = " + zeroBestFirst);

				zeroBestThird += vrContainer.getAlfaAt(t)
						* model.getVedInn(index).getProdDemand().getValueAt(t);
				LOG.debug("zeroBestThird += " + vrContainer.getAlfaAt(t) + " * "
						+ model.getVedInn(index).getProdDemand().getValueAt(t) + " = " + zeroBestThird);

				zeroWorstThird += vrContainer.getAlfaAt(t)
						* vrContainer.getGovOrderInn().get(index).getValueAt(t);
				LOG.debug("zeroWorstThird += " + vrContainer.getAlfaAt(t) + " * "
						+ vrContainer.getGovOrderInn().get(index).getValueAt(t) + " = " + zeroWorstThird);

				zeroWorstFirst += vrContainer.getAlfaAt(t)
						* (vrContainer.getGovOrderOrd().get(index).getValueAt(t) + vrContainer
								.getGovOrderInn().get(index).getValueAt(t));
				LOG.debug("zeroWorstFirst += " + vrContainer.getAlfaAt(t) + " * ("
						+ vrContainer.getGovOrderOrd().get(index).getValueAt(t) + " + "
						+ vrContainer.getGovOrderInn().get(index).getValueAt(t) + ") = " + zeroWorstFirst);
			}
		}
		LOG.debug("Result:");
		LOG.debug("zeroBestFirst = " + zeroBestFirst);
		LOG.debug("zeroBestThird = " + zeroBestThird);
		LOG.debug("zeroWorstThird = " + zeroWorstThird);
		LOG.debug("zeroWorstFirst = " + zeroWorstFirst);
	}
}
