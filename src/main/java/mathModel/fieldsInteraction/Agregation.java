package mathModel.fieldsInteraction;

public class Agregation {
	static double[][] agregate(double[][] matrix, double[] lastColumn) {
		System.out.println("agregation input: matrix[0][0] = " + matrix[0][0]);
		double[][] tab = new double[14][14];
		for (int i = 0; i < 38; i++) {
			for (int j = 0; j < 38; j++) {
				if (Math.abs(lastColumn[j]) > 0.001) {
					matrix[i][j] /= lastColumn[j];
				}
			}
		}
		{
			double value = 0.;
			for (int k = 0; k < 6; k++) {
				for (int p = 0; p < 6; p++) {
					value += matrix[k][p];
				}
			}
			for (int k = 0; k < 6; k++) {
				value += matrix[15][k] + matrix[k][15];
			}
			value += matrix[15][15];
			for (int k = 20; k < 38; k++) {
				value += matrix[k][15] + matrix[15][k];
				for (int p = 0; p < 6; p++) {
					value += matrix[k][p] + matrix[p][k];
				}
				for (int m = 20; m < 38; m++) {
					value += matrix[k][m];
				}
			}
			tab[13][13] = value;
		}

		{
			double value = 0.;
			for (int j = 6; j < 15; j++) {
				for (int k = 0; k < 6; k++) {
					value += matrix[j][k];
				}
				value += matrix[j][15];
				for (int m = 20; m < 38; m++) {
					value += matrix[j][m];
				}
				tab[j - 6][13] = value;
				value = 0.;
			}
		}

		{
			double value = 0.;
			for (int j = 16; j < 20; j++) {
				for (int k = 0; k < 6; k++) {
					value += matrix[j][k];
				}
				value += matrix[j][15];
				for (int m = 20; m < 38; m++) {
					value += matrix[j][m];
				}
				tab[j - 7][13] = value;
				value = 0.;
			}
		}

		{
			double value = 0.;
			for (int j = 6; j < 15; j++) {
				for (int k = 0; k < 6; k++) {
					value += matrix[k][j];
				}
				value += matrix[15][j];
				for (int m = 20; m < 38; m++) {
					value += matrix[m][j];
				}
				tab[13][j - 6] = value;
				value = 0.;
			}
		}

		{
			double value = 0.;
			for (int j = 16; j < 20; j++) {
				for (int k = 0; k < 6; k++) {
					value += matrix[k][j];
				}
				value += matrix[15][j];
				for (int m = 20; m < 38; m++) {
					value += matrix[m][j];
				}
				tab[13][j - 7] = value;
				value = 0.;
			}
		}

		for (int j = 6; j < 15; j++) {
			for (int k = 6; k < 15; k++) {
				tab[j - 6][k - 6] = matrix[j][k];
			}
			for (int m = 16; m < 20; m++) {
				tab[j - 6][m - 7] = matrix[j][m];
			}
		}

		for (int j = 16; j < 20; j++) {
			for (int k = 6; k < 15; k++) {
				tab[j - 7][k - 6] = matrix[j][k];
			}
			for (int m = 16; m < 20; m++) {
				tab[j - 7][m - 7] = matrix[j][m];
			}
		}

		return tab;

	}
}
