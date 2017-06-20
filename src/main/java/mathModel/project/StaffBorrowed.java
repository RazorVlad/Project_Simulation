package mathModel.project;

import mathModel.ModelObjectsContainer;
import mathModel.Statistics;

import org.apache.log4j.Logger;

public class StaffBorrowed {
	private static Logger LOG = Logger.getLogger(StaffBorrowed.class);

	static void staffBorrowed(int tau3, int duration, int vedProducer, Statistics staff,
			Statistics staffBorrowed) {
		ModelObjectsContainer model = ModelObjectsContainer.getInstance();
		for (int i = tau3 + 2; i < duration; i++) {
			double deficit = model.getVedInn(vedProducer).getStaffDeficitAt(i);
			double staffBorrowedValue;

			LOG.debug("deficit(" + i + ") = " + deficit);
			LOG.debug("staff(" + i + ") = " + staff.getValueAt(i));

			if (deficit >= 0) {
				LOG.debug("deficit >= 0");
				LOG.debug("staffBorrowed(" + i + ") = staff(i) = " + staff.getValueAt(i));
				staffBorrowed.add(i, staff.getValueAt(i));
			} else if (staff.getValueAt(i) > -deficit) {
				LOG.debug("staff(" + i + ") > -deficit");
				staffBorrowedValue = staff.getValueAt(i) + deficit;
				LOG.debug("staffBorrowed(" + i + ") = staff(" + i + ") + deficit = " + staff.getValueAt(i)
						+ " + " + deficit + " = " + staffBorrowedValue);
				staffBorrowed.add(i, staffBorrowedValue);
			} else {
				staffBorrowed.add(i, 0);
			}
		}
	}
}
