package mathModel.project;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Effect {
	private static Logger LOG = Logger.getLogger(Effect.class);

	static void effect1(int tau3, int duration, Statistics price, Statistics output, double effect1,
			double costResearch, int vedProducer, Statistics investments, double costDev, double cost,
			Statistics outcomeDeficit, Statistics totalCost) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		LOG.debug("effect1 += price(i) * output(i)");
		LOG.debug("effect1 -= ProdPrice(i) * outcomeDeficit(i)");
		LOG.debug("effect1 -= output(i) * totalCost(i) + investments(i)");
		for (int i = tau3 + 1; i < duration; i++) {
			LOG.debug("i = " + i);

			LOG.debug("effect1 += " + price.getValueAt(i) + " * " + output.getValueAt(i));
			effect1 += price.getValueAt(i) * output.getValueAt(i);

			LOG.debug("effect1 -= " + model.getVedInn(vedProducer).getProdPriceAt(i) + " * "
					+ outcomeDeficit.getValueAt(i));
			effect1 -= model.getVedInn(vedProducer).getProdPriceAt(i) * outcomeDeficit.getValueAt(i);

			LOG.debug("effect1 -= " + output.getValueAt(i) + " * " + totalCost.getValueAt(i) + " + "
					+ investments.getValueAt(i));
			effect1 -= (output.getValueAt(i) * totalCost.getValueAt(i) + investments.getValueAt(i));
			LOG.debug("effect1 = " + effect1);
		}
		LOG.debug("effect1 -= (costResearch + costDev + cost)");
		LOG.debug("effect1 -= (" + costResearch + " + " + costDev + " + " + cost + ")");
		effect1 -= (costResearch + costDev + cost);
		LOG.debug("effect1 = " + effect1);
	}

	static void effect2(double effect2, double discount, double income, double newAssetsCost, int tau3,
			int deltaT, int duration, Statistics assetsOutcome) {
		LOG.debug("effect2 = income + discount - newAssetsCost");
		effect2 = income + discount - newAssetsCost;
		LOG.debug("effect2 = " + income + " + " + discount + " - " + newAssetsCost + " = " + effect2);
		LOG.debug("effect2 += assetsOutcome(j)");
		for (int j = tau3 + deltaT + 1; j < duration; j++) {
			effect2 += assetsOutcome.getValue(j);
			LOG.debug("effect2 += " + assetsOutcome.getValue(j));
		}
		LOG.debug("effect2 = " + effect2);
	}

	static void effectSum(double effectSum, double effect1, double effect2) {
		effectSum = effect1 + effect2;
		LOG.debug("effectSum = effect1 + effect2 = " + effect1 + " + " + effect2 + " = " + effectSum);
	}
}
