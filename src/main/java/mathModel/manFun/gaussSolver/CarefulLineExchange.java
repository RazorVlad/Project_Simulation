package mathModel.manFun.gaussSolver;

import org.apache.log4j.Logger;

public class CarefulLineExchange {
	private static Logger LOG = Logger.getLogger(CarefulLineExchange.class);

	public static int carefulLineExchange(double[][] a, double[] b) {
		LOG.debug("carefulLineExchange");
		int n = a.length;
		int count = 0;
		double[][] ac = new double[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(a[i], 0, ac[i], 0, n);
		}
		double[] bc = new double[n];
		System.arraycopy(b, 0, bc, 0, n);

		int[][] zInd = new int[n][n]; // ������
										// ��������
										// �������
										// ���������
		int zIndCount[] = new int[n]; // ���������o
										// ����� �
										// �������
		int[] zcDown = new int[n]; // �������
									// �������� ��
									// ��������
									// ����������
		// �����
		// ///////////////////////////////
		// ��������� zInd � zIndCount
		for (int i = 0; i < n; i++) {
			zIndCount[i] = 0;
			for (int j = 0; j < n; j++) {
				if (a[i][j] == 0) {
					zInd[i][zIndCount[i]] = j;
					zIndCount[i]++;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				LOG.debug("zInd " + i + j + " " + zInd[i][j]);
			}
			LOG.debug("zIndCount " + i + " " + zIndCount[i]);
		}
		// ///////////////////////////////
		// ��������� zcDown
		int[] copy = new int[n];
		System.arraycopy(zIndCount, 0, copy, 0, n);
		for (int t = 0; t < n; t++) {
			LOG.debug("copy " + t + " " + copy[t]);
		}
		for (int i = 0; i < n; i++) {
			int mv = -1, ind = 0;
			for (int j = 0; j < n; j++) {
				if (copy[j] > mv) {
					mv = copy[j];
					ind = j;
				}
			}
			copy[ind] = -1;
			zcDown[i] = ind;
			for (int t = 0; t < n; t++) {
				LOG.debug("copy " + t + " " + copy[t]);
			}
		}
		for (int i = 0; i < n; i++) {
			LOG.debug("zcDown " + i + " " + zcDown[i]);
			for (int j = 0; j < n; j++) {
				LOG.debug("a" + zcDown[i] + j + " : " + a[zcDown[i]][j]);
			}
		}
		// ////////////////////////////////
		int[] taken = new int[n];
		int ct = 0;
		for (int iii = 0; iii < n; iii++) {
			for (int j = 0; j < n; j++) {
				a[iii][j] = 0;
			}
			b[iii] = 0;
		}
		for (int ii = 0; ii < n; ii++) {
			int i = zcDown[ii];
			if (zIndCount[i] != 0) { // ���� � ������
										// ���� ����
				for (int j = 0; j < n; j++) { // ����� �� ��
												// ���������
					if (notIn(j, zInd[i], zIndCount[i]) && notIn(j, taken, ct)) { // ����
																					// ��
																					// ����
						if (allZeros(a, j, i)) { // � ���
													// �����
													// ���� �
													// �������
							// ����
							System.arraycopy(ac[i], 0, a[j], 0, n);
							b[j] = bc[i];
							taken[ct] = j;
							ct++;
							ac[i] = null;
							if (i != j) {
								count++;
							}
							LOG.debug("one");
							// if (ct == n)
							// return count;
						}
					}
				}
				if (ac[i] != null) {
					int j = selectMax(ac[i]);
					if (notIn(j, taken, ct)) {
						System.arraycopy(ac[i], 0, a[j], 0, n);
						b[j] = bc[i];
						taken[ct] = j;
						ct++;
						ac[i] = null;
						if (i != j) {
							count++;
						}
						LOG.debug("two");
						// if (ct == n)
						// return count;
					} else {
						for (int k = 0; k < n; k++) {
							if (ac[i] == null && ac[i][k] != 0 && notIn(k, taken, ct)) {
								System.arraycopy(ac[i], 0, a[k], 0, n);
								b[k] = bc[i];
								taken[ct] = k;
								ct++;
								ac[i] = null;
								if (i != j) {
									count++;
								}
								LOG.debug("three");
								// if (ct == n)
								// return count;
							}
						}
					}
				}
			} else {
				if (ct == n) {
					return count;
				} else {
					int j = selectMax(ac[i]);
					if (notIn(j, taken, ct)) {
						System.arraycopy(ac[i], 0, a[j], 0, n);
						b[j] = bc[i];
						taken[ct] = j;
						ct++;
						ac[i] = null;
						if (i != j)
							count++;
						LOG.debug("four");
						// if (ct == n)
						// return count;
					} else {
						for (int k = 0; k < n; k++) {
							if (ac[i] != null && ac[i][k] != 0 && notIn(k, taken, ct)) {
								System.arraycopy(ac[i], 0, a[k], 0, n);
								b[k] = bc[i];
								taken[ct] = k;
								ct++;
								ac[i] = null;
								if (i != k)
									count++;
								LOG.debug("five");
								// if (ct == n)
								// return count;
							}
						}
					}
				}
			}
			for (int j = 0; j < n; j++) {
				LOG.debug("taken" + j + " : " + taken[j]);
			}
			LOG.debug("ct " + ct);
		}

		return count;
	}
	
	public static boolean notIn(int j, int[] zs, int until) {
		for (int i = 0; i < until; i++) {
			if (j == zs[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean allZeros(double[][] a, int j, int except) {
		for (int i = 0; i < a.length; i++) {
			if (j < a[i].length) {
				if (i != except && a[i][j] != 0.0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int selectMax(double[] ai) {
		double max = 0;
		int mi = 0;
		for (int i = 0; i < ai.length; i++) {
			if (Math.abs(ai[i]) > max) {
				max = Math.abs(ai[i]);
				mi = i;
			}
		}
		return mi;
	}
}
