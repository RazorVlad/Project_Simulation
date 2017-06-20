package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Assets {
	private static Logger LOG = Logger.getLogger(Assets.class);

	static void assets(boolean innovational, Statistics coefOutcome, Statistics coefAmort1,
			Statistics coefAmort2, Statistics qGovInv, Statistics qPrivInv, Statistics partForFunds,
			Statistics partAmort, Statistics assets, Statistics assetsIncome, Statistics coefObsFunds,
			Statistics assetsOutcome, Manufacturing ord) {
		if (innovational) {
			LOG.debug("Calculate innovational assets");
			LOG.debug("assetsIncomeValue = assets(i - 1) * coefAmort1(i) * coefAmort2(i) + (qGovInv(i) + qPrivInv(i)) * partForFunds(i)+ord.Assets(i-1)*ord.CoefAmort1(i)*(1-partAmort(i))*coefAmort2(i)");
		} else {
			LOG.debug("Calculate not innovational assets");
			LOG.debug("assetsIncomeValue = assets(i - 1) * coefAmort1(i) * coefAmort2(i) + (qGovInv(i) + qPrivInv(i)) * partForFunds(i)");

		}
		LOG.debug("assetsOutcomeValue = assets(i - 1) * coefObsFunds(i)");
		LOG.debug("assetsValue=assets(i - 1) + assetsIncome(i) - assetsOutcome(i)");
		double value;
		for (int i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);
			if (innovational) {
				value = assets.getValue(i - 1) * coefAmort1.getValue(i) * coefAmort2.getValue(i)
						+ (qGovInv.getValue(i) + qPrivInv.getValue(i)) * partForFunds.getValue(i)
						+ ord.getAssets(i - 1) * ord.getCoefAmort1(i) * (1 - partAmort.getValue(i))
						* coefAmort2.getValue(i);
				LOG.debug("assetsIncomeValue = " + assets.getValue(i - 1) + " * " + coefAmort1.getValue(i)
						+ " * " + coefAmort2.getValue(i) + " + (" + qGovInv.getValue(i) + " + "
						+ qPrivInv.getValue(i) + ") * " + partForFunds.getValue(i) + " + "
						+ ord.getAssets(i - 1) + " * " + ord.getCoefAmort1(i) + " * (1 - "
						+ partAmort.getValue(i) + ") * " + coefAmort2.getValue(i) + " = " + value);
			} else {
				value = assets.getValue(i - 1) * coefAmort1.getValue(i) * coefAmort2.getValue(i)
						+ (qGovInv.getValue(i) + qPrivInv.getValue(i)) * partForFunds.getValue(i);
				LOG.debug("assetsIncomeValue = " + assets.getValue(i - 1) + " * " + coefAmort1.getValue(i)
						+ " * " + coefAmort2.getValue(i) + " + (" + qGovInv.getValue(i) + " + "
						+ qPrivInv.getValue(i) + ") * " + partForFunds.getValue(i) + " = " + value);
			}
			assetsIncome.add(coefAmort1.getYear(i), value);
			LOG.debug("assetsIncome.add(" + coefAmort1.getYear(i) + "," + value + ")");

			value = assets.getValue(i - 1) * coefObsFunds.getValue(i);
			LOG.debug("assetsOutcomeValue = " + assets.getValue(i - 1) + " * " + coefObsFunds.getValue(i)
					+ " = " + value);
			assetsOutcome.add(coefObsFunds.getYear(i), value);
			LOG.debug("assetsOutcome.add(" + coefObsFunds.getYear(i) + "," + value + ")");

			value = assets.getValue(i - 1) + assetsIncome.getValue(i) - assetsOutcome.getValue(i);
			LOG.debug("assetsValue = " + assets.getValue(i - 1) + " + " + assetsIncome.getValue(i) + " - "
					+ assetsOutcome.getValue(i) + " = " + value);
			assets.add(assetsIncome.getYear(i), value);
			LOG.debug("assets.add(" + assetsIncome.getYear(i) + "," + value + ")");
		}
	}
}
