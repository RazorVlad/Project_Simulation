package mathModel.manFun.gaussSolver;

public class Determinant {
	public static double det(double[][] a) {
		if (detEqualsZero(a)) {
			return 0.0;
		}
		double det = 1;
		int n = a.length, count = 0;
		// int q = 0;
		for (int i = 0; i < n; i++) {
			if (a[i][i] == 0) {
				count = lineExchange(a);
				// q++;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				a[i][j] /= a[i][i];
			}
			det *= a[i][i];
			a[i][i] = 1;
			for (int k = i + 1; k < n; k++) {
				for (int j = i + 1; j < n; j++) {
					a[k][j] -= a[i][j] * a[k][i];
				}
				a[k][i] = 0;
			}
		}
		if (count % 2 != 0) {
			return 0 - det;
		} else {
			return det;
		}
	}

	public static int lineExchange(double[][] a) {
		int n = a.length;
		int count = 0;
		double[][] ac = new double[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(a[i], 0, ac[i], 0, n);
		}
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
			ac[maxInd] = null;
		}
		return count;
	}

	private static boolean detEqualsZero(double[][] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int k = i + 1; k < a.length; k++) {
				if (a[i][0] == a[k][0]) {
					if (linesAreEqual(a[i], a[k])) {
						return true;
					}
				}
				if (linesAreProportional(a[i], a[k])) {
					return true;
				}
			}
		}
		double[][] trans = transMatrix(a);
		for (int i = 0; i < trans.length - 1; i++) {
			for (int k = i + 1; k < trans.length; k++) {
				if (trans[i][0] == trans[k][0] && linesAreEqual(trans[i], trans[k])) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean linesAreEqual(double[] a, double[] b) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				count++;
			}
		}
		return count == a.length;
	}

	private static boolean linesAreProportional(double[] a, double[] b) {
		double coef = a[0] / b[0];
		for (int j = 1; j < a.length; j++) {
			if (Math.abs(Math.abs(a[j] / b[j]) - Math.abs(coef)) > 0.0000001) {
				return false;
			}
		}
		return true;
	}

	public static double[][] transMatrix(double[][] a) {
		int m = a.length;
		int n = a[0].length;
		double[][] res = new double[n][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				res[j][i] = a[i][j];
			}
		}
		return res;
	}
}
