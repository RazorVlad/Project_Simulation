package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Salary {
	private static Logger LOG = Logger.getLogger(Salary.class);

	static void salary(Statistics coefSalaryChange,Statistics coefOutcome,Statistics salary ) {
		LOG.debug("Calculate salary");
		double value;
		LOG.debug("coefSalaryChange = " + coefSalaryChange);
		LOG.debug("salaryValue = salary(0) * multiply(coefSalaryChange, i)");
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			value = salary.getValue(0) * Multiply.multiply(coefSalaryChange, i);
			LOG.debug("salaryValue = " + salary.getValue(0) + " * " + Multiply.multiply(coefSalaryChange, i)
					+ " = " + value);
			salary.add(coefSalaryChange.getYear(i), value);
			LOG.debug("salary.add(" + coefSalaryChange.getYear(i) + "," + value + ")");
		}
	}
}
