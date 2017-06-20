package mathModel.manufacturing.costs;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class ConstCost {
	private static Logger LOG = Logger.getLogger(ConstCost.class);

	public static void constCost(Statistics coefOutcome, Statistics assets, Statistics coefAmort1, Statistics staff,
			Statistics salary, double coefSalaryOut, Statistics extraCost, Statistics constCost) {
		LOG.debug("Calculate constCost");
		LOG.debug("constCostValue = assets(i) * coefAmort1(i) + staff(i) * salary(i) * (1 + coefSalaryOut) * 12 + extraCost(i)");
		double value;
		for (int i = 0; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = assets.getValue(i) * coefAmort1.getValue(i) + staff.getValue(i) * salary.getValue(i)
					* (1 + coefSalaryOut) * 12 + extraCost.getValue(i);
			LOG.debug("constCostValue = " + assets.getValue(i) + " * " + coefAmort1.getValue(i) + " + "
					+ staff.getValue(i) + " * " + salary.getValue(i) + " * (1 + " + coefSalaryOut
					+ ") * 12 + " + extraCost.getValue(i) + " = " + value);
			constCost.add(coefOutcome.getYear(i), value);
			LOG.debug("constCost.add(" + coefOutcome.getYear(i) + "," + value + ")");
		}
	}

}
