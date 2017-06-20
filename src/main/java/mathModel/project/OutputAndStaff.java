package mathModel.project;

import org.apache.log4j.Logger;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

public class OutputAndStaff {

	private static Logger LOG = Logger.getLogger(OutputAndStaff.class);

	static void outputAndStaff(int t3, int duration, Statistics power, Statistics demand, Statistics output,
			Statistics staff, double staff0, int vedProducer) {

		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		LOG.debug("staff(i) = staff0 * output(i)");
		for (int i = t3 + 2; i < duration; i++) {
			LOG.debug("power(" + i + ")=" + power.getValueAt(i));
			LOG.debug("demand(" + i + ")=" + demand.getValueAt(i));

			if (power.getValueAt(i) > demand.getValueAt(i)) {
				LOG.debug("power(" + i + ") > demand(" + i + ") => output(" + i + ") = demand(" + i + ")");
				output.add(i, demand.getValueAt(i));
			} else {
				LOG.debug("power(" + i + ") < demand(" + i + ") => output(" + i + ") = power(" + i + ")");
				output.add(i, power.getValueAt(i));
			}

			double staffValue = staff0 * output.getValueAt(i);
			LOG.debug("staff(" + i + ") = " + staff0 + " * " + output.getValueAt(i) + " = " + staffValue);
			staff.add(i, staffValue);
		}

		for (int i = t3 + 2; i < duration; i++) {
			double deficit = model.getVedInn(vedProducer).getStaffDeficitAt(i);
			LOG.debug("deficit(i) = " + deficit);

			if (deficit < 0 && staff.getValueAt(i) > -deficit) {

				LOG.debug("deficit(" + i + ") < 0 && staff(" + i + ") > -deficit(" + i + ")");
				double outputValue = -deficit / staff0;
				LOG.debug("output(" + i + ") = -deficit / staff0 = -(" + deficit + ") / " + staff0 + " = "
						+ outputValue);
				output.setValueAt(i, outputValue);

			} else if (deficit < 0 && staff.getValueAt(i) <= -deficit) {

				LOG.debug("power(" + i + ")=" + power.getValueAt(i));
				LOG.debug("demand(" + i + ")=" + demand.getValueAt(i));

				if (power.getValueAt(i) > demand.getValueAt(i)) {
					LOG.debug("power(" + i + ") > demand(" + i + ")");
					LOG.debug("output(" + i + ") = demand(" + i + ")");
					output.setValueAt(i, demand.getValueAt(i));
				} else {
					LOG.debug("power(" + i + ") < demand(" + i + ")");
					LOG.debug("output(" + i + ") = power(" + i + ")");
					output.setValueAt(i, power.getValueAt(i));
				}
			} else {
				LOG.debug("deficit(" + i + ") >= 0");
				LOG.debug("output(" + i + ") = 0");
				output.setValueAt(i, 0);
			}
		}
	}
}
