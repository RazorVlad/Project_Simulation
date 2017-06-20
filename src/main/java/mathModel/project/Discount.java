package mathModel.project;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Discount {
	private static Logger LOG = Logger.getLogger(Discount.class);

	static void discount(int tau3, int deltaT, int duration, Statistics varCostOld, Statistics varCostNew,
			Statistics amount, double discount) {
		LOG.debug("discount = varCostOld(j) - varCostNew(j)) * amount(j)");
		StringBuilder discountBuilder = new StringBuilder("discount = ");
		for (int j = tau3 + deltaT + 1; j < duration; j++) {
			discount += (varCostOld.getValueAt(j) - varCostNew.getValueAt(j)) * amount.getValueAt(j);
			discountBuilder.append("(").append(varCostOld.getValueAt(j));
			discountBuilder.append(" - ").append(varCostNew.getValueAt(j));
			discountBuilder.append(") * ").append(amount.getValueAt(j));
			if (j != duration - 1) {
				discountBuilder.append(" + ");
			}
		}
		LOG.debug(discountBuilder);
	}
}
