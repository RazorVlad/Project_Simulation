package mathModel.manufacturing;

import org.apache.log4j.Logger;

import mathModel.Statistics;

public class Multiply {
	private static Logger LOG = Logger.getLogger(Multiply.class);

	public static double multiply(Statistics st, int times) {
		LOG.debug("Multiply statistic value " + times + " times");
		double res = 1.;
		LOG.debug("MultiplyValue = 1");
		for (int j = 1; j < times; j++) {
			res *= st.getValue(j);
			LOG.debug("MultiplyValue = MultiplyValue * StatisticValue(" + j + ") = MultiplyValue * "
					+ st.getValue(j) + " = " + res);
		}
		return res;
	}
}
