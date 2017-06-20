package mathModel.manufacturing;

import mathModel.Statistics;

import org.apache.log4j.Logger;

public class Prices {
	private static Logger LOG = Logger.getLogger(Prices.class);

	static void prices(Statistics coefOutcome, Statistics coefProdPrice, Statistics coefRawPrice,
			Statistics prodPrice, Statistics rawPrice, Statistics wc) {
		LOG.debug("Calculate prices");
		double value;
		LOG.debug("coefProdPrice = " + coefProdPrice);
		LOG.debug("prodPriceValue=prodPrice(0) * multiply(coefProdPrice, i)");
		LOG.debug("coefRawPrice = " + coefRawPrice);
		LOG.debug("rawPriceValue = rawPrice(0) * multiply(coefRawPrice, i)");
		for (byte i = 1; i < coefOutcome.size(); i++) {
			LOG.debug("i = " + i);

			value = prodPrice.getValue(0) * Multiply.multiply(coefProdPrice, i);
			LOG.debug("prodPriceValue = " + prodPrice.getValue(0) + " * "
					+ Multiply.multiply(coefProdPrice, i) + " = " + value);
			prodPrice.add(wc.getYear(i), value);
			LOG.debug("prodPrice.add(" + wc.getYear(i) + "," + value + ")");

			value = rawPrice.getValue(0) * Multiply.multiply(coefRawPrice, i);
			LOG.debug("rawPriceValue = " + rawPrice.getValue(0) + " * " + Multiply.multiply(coefRawPrice, i)
					+ " = " + value);
			rawPrice.add(wc.getYear(i), value);
			LOG.debug("rawPrice.add(" + wc.getYear(i) + "," + value + ")");
		}
	}
}
