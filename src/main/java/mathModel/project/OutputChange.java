package mathModel.project;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

public class OutputChange {

	private static Logger LOG = Logger.getLogger(OutputChange.class);

	static void outputChange(int vedOwner, int t3, int deltaT, int duration, Statistics amountOld,
			Statistics staffNew, Statistics staff, Statistics assetsOutcome, Statistics potentialOutcome,
			Statistics amountNaturNew, Statistics outputChange, Statistics laborContent, Statistics amount) {

		ModelObjectsContainer model = ModelObjectsContainer.getInstance();

		StaffNew.staffNew(t3, deltaT, duration, staffNew, laborContent, amount);

		Statistics a = model.getVedInn(vedOwner).getA();
		Statistics alfa = model.getVedInn(vedOwner).getAlfa();
		Statistics beta = model.getVedInn(vedOwner).getBeta();
		Statistics gamma = model.getVedInn(vedOwner).getGamma();
		LOG.debug("amountOldValue(j) = a(j) * (staff(j)-staffNewSum)^alfa(j) * (assets(j)-assetsOutcomeSum)^beta(j) * intangibles(j)^gamma(j)");
		LOG.debug("potentialOutcome(j) = amountOld.getValueAt(j) + amount.getValueAt(j)");

		for (int j = t3 + deltaT + 1; j < duration; j++) {
			double staffNewSum = 0;
			double assetsOutcomeSum = 0;
			double prodDemandJ = model.getVedInn(vedOwner).getProdDemand().getValueAt(j);
			double amountNaturJ = model.getVedInn(vedOwner).getAmountNatur().getValueAt(j);

			StringBuilder staffNewSumBuilder = new StringBuilder("staffNewSum = ");
			StringBuilder assetsOutcomeSumBuilder = new StringBuilder("assetsOutcomeSum = ");

			for (int r = t3 + deltaT + 1; r < j; r++) {
				staffNewSum += staffNew.getValueAt(r);
				staffNewSumBuilder.append(staffNew.getValueAt(r));

				assetsOutcomeSum += assetsOutcome.getValueAt(r);
				assetsOutcomeSumBuilder.append(assetsOutcome.getValueAt(r));

				if (r != (j - 1)) {
					staffNewSumBuilder.append(" + ");
					assetsOutcomeSumBuilder.append(" + ");
				}
			}

			LOG.debug(staffNewSumBuilder);
			LOG.debug(assetsOutcomeSumBuilder);

			double intangibles = model.getVedInn(vedOwner).getIntangiblesAt(j);
			double assets = model.getVedInn(vedOwner).getAssetsAt(j);

			double amountOldValue = a.getValue(j)
					* Math.pow((staff.getValueAt(j) - staffNewSum), alfa.getValue(j))
					* Math.pow((assets - assetsOutcomeSum), beta.getValue(j))
					* Math.pow(intangibles, gamma.getValue(j));

			LOG.debug("amountOld(" + j + ") = " + a.getValue(j) + "* (" + staff.getValueAt(j) + "-"
					+ staffNewSum + ")^" + alfa.getValue(j) + " + (" + assets + "-" + assetsOutcomeSum + ")^"
					+ beta.getValue(j) + " * " + intangibles + "^" + gamma.getValue(j) + " = "
					+ amountOldValue);

			amountOld.add(j, amountOldValue);

			double potentialOutcomeValue = amountOld.getValueAt(j) + amount.getValueAt(j);
			LOG.debug("potentialOutcome(" + j + ")=" + amountOld.getValueAt(j) + " + " + amount.getValueAt(j)
					+ " = " + potentialOutcomeValue);
			potentialOutcome.add(j, potentialOutcomeValue);

			LOG.debug("prodDemandJ = AmountNatur(" + j + ") = " + prodDemandJ);

			if (potentialOutcome.getValueAt(j) > prodDemandJ) {
				LOG.debug("potentialOutcome(" + j + ") > prodDemandJ => amountNaturNew(" + j
						+ ") = prodDemandJ");
				amountNaturNew.add(j, prodDemandJ);
			} else {
				LOG.debug("potentialOutcome(" + j + ") < prodDemandJ => amountNaturNew(" + j
						+ ") = potentialOutcome(" + j + ")");
				amountNaturNew.add(j, potentialOutcome.getValueAt(j));
			}
			outputChange.add(j, amountNaturNew.getValueAt(j) - amountNaturJ);
		}
	}

}
