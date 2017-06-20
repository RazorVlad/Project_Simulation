package mathModel.project;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

public class AssetsOutcome {
	private static Logger LOG = Logger.getLogger(AssetsOutcome.class);

	static void assetsOutcome(int assetsOutcomeMethod, int t3, int duration, double k, int deltaT,
			int vedOwner, Statistics fixed, Statistics assetsOutcome, Statistics fullProductivity) {
		
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		
		LOG.debug("assetsOutcomeMethod index = " + assetsOutcomeMethod);
		LOG.debug("vedOwner index = " + vedOwner);
		double value;
		switch (assetsOutcomeMethod) {
		case (1): {
			LOG.debug("assetsOutcome(i) = fullProductivity(i) * Assets(i) / PotentialNatur(i)");

			for (int i = t3 + deltaT + 1; i < duration; i++) {

				value = fullProductivity.getValueAt(i) * model.getVedInn(vedOwner).getAssetsAt(i)
						/ model.getVedInn(vedOwner).getPotentialNatur(i);

				LOG.debug("assetsOutcome(" + i + ") = " + fullProductivity.getValueAt(i) + " * "
						+ model.getVedInn(vedOwner).getAssetsAt(i) + " / "
						+ model.getVedInn(vedOwner).getPotentialNatur(i) + " = " + value);

				assetsOutcome.add(i, value);
			}
			break;
		}
		case (2): {
			LOG.debug("create new assetsOutcome");
			assetsOutcome = new Statistics(t3 + deltaT + 1, 0., duration - t3 + deltaT + 1);
			break;
		}
		case (3): {
			LOG.debug("assetsOutcome(i) = fullProductivity(i) * Assets(i) * k / PotentialNatur(i)");

			for (int i = t3 + deltaT + 1; i < duration; i++) {
				value = fullProductivity.getValueAt(i) * model.getVedInn(vedOwner).getAssetsAt(i) * k
						/ model.getVedInn(vedOwner).getPotentialNatur(i);

				LOG.debug("assetsOutcome(" + i + ") = " + fullProductivity.getValueAt(i) + " * "
						+ model.getVedInn(vedOwner).getAssetsAt(i) + " * " + k + " / "
						+ model.getVedInn(vedOwner).getPotentialNatur(i) + " = " + value);

				assetsOutcome.add(i, value);
			}
			break;
		}
		case (4): {
			LOG.debug("assetsOutcome(i) = fixed(i) * Assets(i) / PotentialNatur(i)");
			for (int i = t3 + deltaT + 1; i < duration; i++) {
				value = fixed.getValueAt(i) * model.getVedInn(vedOwner).getAssetsAt(i)
						/ model.getVedInn(vedOwner).getPotentialNatur(i);

				LOG.debug("assetsOutcome(" + i + ") = " + fixed.getValueAt(i) + " * "
						+ model.getVedInn(vedOwner).getAssetsAt(i) + " / "
						+ model.getVedInn(vedOwner).getPotentialNatur(i) + " = " + value);

				assetsOutcome.add(i, value);
			}
			break;
		}
		}
	}
}
