package mathModel.manFun.gaussSolver;

import org.apache.log4j.Logger;

public class LineExchange {
	private static Logger LOG = Logger.getLogger(LineExchange.class);

	public static int lineExchange(double[][] a, double[] b) {

		int n = a.length;
		LOG.debug("before line exhange");
		for (int ii = 0; ii < n; ii++) {
			for (int j = 0; j < n; j++) {
				LOG.debug("a" + ii + j + " : " + a[ii][j]);
			}
		}
		for (int j = 0; j < n; j++) {
			LOG.debug("b" + j + " : " + b[j]);
		}

		if (!thereAreZeros(a)) {
			// this.trueIndexes = new int[n];
			int count = 0;
			double[][] ac = new double[n][n];
			for (int i = 0; i < n; i++) {
				System.arraycopy(a[i], 0, ac[i], 0, n);
			}
			double[] bc = new double[n];
			System.arraycopy(b, 0, bc, 0, n);
			double max;
			int maxInd;
			for (int j = 0; j < n; j++) {
				max = 0;
				maxInd = j;
				for (int i = 0; i < n; i++) {
					if (ac[i] == null) {
						continue;
					}
					if (Math.abs(ac[i][j]) > max) {
						max = Math.abs(ac[i][j]);
						maxInd = i;
						count++;
					}
				}
				System.arraycopy(ac[maxInd], 0, a[j], 0, n);
				b[j] = bc[maxInd];
				ac[maxInd] = null;
				// trueIndexes[maxInd] = j;
			}
			LOG.debug("after line exhange");
			for (int ii = 0; ii < n; ii++) {
				for (int k = 0; k < n; k++) {
					LOG.debug("a" + ii + k + " : " + a[ii][k]);
				}
			}
			for (int k = 0; k < n; k++) {
				LOG.debug("b" + k + " : " + b[k]);
			}
			return count;
		} else {
			return CarefulLineExchange.carefulLineExchange(a, b);
		}

	}
	
	public static boolean thereAreZeros(double[][] a) {
		int n = a.length;
		for (double[] anA : a) {
			for (int k = 0; k < n; k++)
				if (anA[k] == 0) {
					return true;
				}
		}
		return false;
	}
}
