package mathModel.manFun.gaussSolver;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class GaussSolver implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(GaussSolver.class);
	private double[][] a;
	private double[] b, roots;
	int[] trueIndexes;

	public int[] getTrueIndexes() {
		return trueIndexes;
	}

	public double[][] getA() {
		return a;
	}

	public void setA(double[][] a) {
		this.a = a.clone();
	}

	public double[] getB() {
		return b;
	}

	public void setB(double[] b) {
		this.b = b.clone();
	}

	public static double[][] matrixMultiplication(double[][] a, double[][] b) {
		LOG.debug("matrixMultiplication");

		int n = a.length;
		LOG.debug("n = " + n);
		double[][] res = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res[i][j] = 0;
			}
		}
		LOG.debug("result[i][k] += a[i][j] * b[j][k]");
		LOG.debug("i<n; k<n; j<n");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				for (int j = 0; j < n; j++) {
					res[i][k] += a[i][j] * b[j][k];
					LOG.debug("result[" + i + "][" + k + "] += a[" + i + "][" + j + "] * b[" + j + "][" + k
							+ "] += " + a[i][j] + " * " + b[j][k] + " = " + res[i][k]);
				}
			}
		}
		return res;
	}

	public static double[] vectorMatrixMultiplication(double[][] a, double[] b) {
		int n = a.length;
		double[] res = new double[n];
		for (int i = 0; i < n; i++) {
			res[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				res[i] += a[i][k] * b[k];
			}

		}
		return res;
	}

	public static double[] addVectors(double[] a, double[] b) {
		double[] res = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			res[i] = a[i] + b[i];
		}
		return res;
	}

	public static double[][] reverseMatrix(double[][] a) {
		int n = a.length;
		double[] b = new double[n];
		double[][] revA = new double[n][n];
		for (int i = 0; i < n; i++) {
			double[][] copy = new double[n][n];
			for (int t = 0; t < n; t++) {
				System.arraycopy(a[t], 0, copy[t], 0, n);
			}
			double[] xij = new double[n];
			b[i] = 1;
			for (int j = 0; j < n; j++) {
				if (j != i) {
					b[j] = 0;
				}
			}
			xij = solve(copy, b);
			for (int k = 0; k < n; k++) {
				revA[k][i] = xij[k];
			}
			copy = null;
		}
		double[][] check = matrixMultiplication(a, revA);
		return revA;
	}

	public static double[] solve(double[][] a, double[] b) {
		int n = a.length;
		// LineExchange.lineExchange(a, b);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				a[i][j] /= a[i][i];
			}
			b[i] /= a[i][i];
			a[i][i] = 1;
			for (int k = i + 1; k < n; k++) {
				b[k] -= b[i] * a[k][i];
				for (int j = i + 1; j < n; j++) {
					a[k][j] -= a[i][j] * a[k][i];
				}
				a[k][i] = 0;
			}
		}
		double[] x = new double[n];
		for (int i = n - 1; i != -1; i--) {
			for (int j = i - 1; j != -1; j--) {
				b[j] -= b[i] * a[j][i];
				a[j][i] -= a[i][i] * a[j][i];
			}
		}
		System.arraycopy(b, 0, x, 0, n);
		return x;
	}

}
