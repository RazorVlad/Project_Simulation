package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class WC {
	private static Logger LOG = Logger.getLogger(WC.class);

	static void wc(boolean innovational, Statistics coefOutcome, Statistics assets, Manufacturing ord,
			Statistics coefAmort1, Statistics coefAmort2, Statistics partAmort, Statistics qGovInv,
			Statistics qPrivInv, Statistics partForFunds, Statistics netProfit, Statistics wcIncome,
			Statistics wcOutcome, Statistics loss, Statistics wc) {
		if (innovational) {
			LOG.debug("Calculate innovational wc");
			LOG.debug("wcIncomeValue=(assets(i-1)*coefAmort1(i)+ord.Assets(i-1)*ord.CoefAmort1(i)*(1-partAmort(i)))*(1-coefAmort2(i))+(qGovInv(i)+qPrivInv(i))*(1-partForFunds(i))+netProfit(i-1)");
		} else {
			LOG.debug("Calculate not innovational wc");
			LOG.debug("wcIncomeValue=assets(i-1)*coefAmort1(i)*partAmort(i)*(1-coefAmort2(i))+(qGovInv(i)+qPrivInv(i))*(1-partForFunds(i))+netProfit(i-1)");
		}
		double value;
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			if (innovational) {
				value = (assets.getValue(i - 1) * coefAmort1.getValue(i) + ord.getAssets(i - 1)
						* ord.getCoefAmort1(i) * (1 - partAmort.getValue(i)))
						* (1 - coefAmort2.getValue(i))
						+ (qGovInv.getValue(i) + qPrivInv.getValue(i))
						* (1 - partForFunds.getValue(i)) + netProfit.getValue(i - 1);

				LOG.debug("wcIncomeValue=(" + assets.getValue(i - 1) + " * " + coefAmort1.getValue(i) + " + "
						+ ord.getAssets(i - 1) + " * " + ord.getCoefAmort1(i) + " * (1 - "
						+ partAmort.getValue(i) + ")) * (1 - " + coefAmort2.getValue(i) + ")+("
						+ qGovInv.getValue(i) + " + " + qPrivInv.getValue(i) + ")* (1 - "
						+ partForFunds.getValue(i) + ") + " + netProfit.getValue(i - 1) + " = " + value);
				wcIncome.add(coefAmort1.getYear(i), value);
				LOG.debug("wcIncome.add(" + coefAmort1.getYear(i) + ", " + value + ")");
			} else {
				value = assets.getValue(i - 1) * coefAmort1.getValue(i) * partAmort.getValue(i)
						* (1 - coefAmort2.getValue(i)) + (qGovInv.getValue(i) + qPrivInv.getValue(i))
						* (1 - partForFunds.getValue(i)) + netProfit.getValue(i - 1);

				LOG.debug("wcIncomeValue=" + assets.getValue(i - 1) + " * " + coefAmort1.getValue(i) + " * "
						+ partAmort.getValue(i) + "* (1 - " + coefAmort2.getValue(i) + ") + ("
						+ qGovInv.getValue(i) + " + " + qPrivInv.getValue(i) + ")* (1 - "
						+ partForFunds.getValue(i) + ") + " + netProfit.getValue(i - 1) + " = " + value);
				wcIncome.add(coefAmort1.getYear(i), value);
				LOG.debug("wcIncome.add(" + coefAmort1.getYear(i) + "," + value + ")");
			}
			wcOutcome.add(wcIncome.getYear(i), loss.getValue(i - 1));
			LOG.debug("wcOutcomeValue = loss.getValue(i - 1) = " + loss.getValue(i - 1));
			LOG.debug("wcOutcome.add(" + wcIncome.getYear(i) + ", " + loss.getValue(i - 1) + ")");

			value = wc.getValue(i - 1) + wcIncome.getValue(i) - wcOutcome.getValue(i);
			Log.debug("wcValue = wc(i - 1) + wcIncome(i) - wcOutcome(i) = " + wc.getValue(i - 1) + " + "
					+ wcIncome.getValue(i) + " - " + wcOutcome.getValue(i) + " = " + value);
			wc.add(wcIncome.getYear(i), value);
			LOG.debug("wc.add(" + wcIncome.getYear(i) + "," + value + ")");
		}
	}
}
