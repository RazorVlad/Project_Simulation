package mathModel.fieldsInteraction;

public class Disagregation {
	static double[][] disagregate(double[][] matrix, double[][] coefs) {
		double[][] res = new double[26][26];

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				res[j][k] = matrix[0][0] * coefs[j][k];
			}
			for (int k = 3; k < 6; k++) {
				res[j][k] = matrix[0][1] * coefs[j][k];
				res[k][j] = matrix[1][0] * coefs[k][j];
			}
			for (int k = 6; k < 9; k++) {
				res[j][k] = matrix[0][2] * coefs[j][k];
				res[k][j] = matrix[2][0] * coefs[k][j];
			}
			res[j][9] = matrix[0][3] * coefs[j][9];
			res[9][j] = matrix[3][0] * coefs[9][j];
			res[j][10] = matrix[0][4] * coefs[j][10];
			res[10][j] = matrix[4][0] * coefs[10][j];
			for (int k = 11; k < 14; k++) {
				res[j][k] = matrix[0][5] * coefs[j][k];
				res[k][j] = matrix[5][0] * coefs[k][j];
			}
			res[j][14] = matrix[0][6] * coefs[j][14];
			res[14][j] = matrix[6][0] * coefs[14][j];
			res[j][15] = matrix[0][7] * coefs[j][15];
			res[15][j] = matrix[7][0] * coefs[15][j];
			res[j][16] = matrix[0][7] * coefs[j][16];
			res[16][j] = matrix[7][0] * coefs[16][j];
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[0][8] * coefs[j][k];
				res[k][j] = matrix[8][0] * coefs[k][j];
			}
			res[j][22] = matrix[0][9] * coefs[j][22];
			res[22][j] = matrix[9][0] * coefs[22][j];
			res[j][23] = matrix[0][10] * coefs[j][23];
			res[23][j] = matrix[10][0] * coefs[23][j];
			res[j][24] = (matrix[0][11] + matrix[0][12]) * coefs[j][24];
			res[24][j] = (matrix[11][0] + matrix[12][0]) * coefs[24][j];
			res[j][25] = matrix[0][13] * coefs[j][25];
			res[25][j] = matrix[13][0] * coefs[25][j];
		}

		for (int j = 3; j < 6; j++) {
			for (int k = 3; k < 6; k++) {
				res[j][k] = matrix[1][1] * coefs[j][k];
			}
			for (int k = 6; k < 9; k++) {
				res[j][k] = matrix[1][2] * coefs[j][k];
				res[k][j] = matrix[2][1] * coefs[k][j];
			}
			res[j][9] = matrix[1][3] * coefs[j][9];
			res[9][j] = matrix[3][1] * coefs[9][j];
			res[j][10] = matrix[1][4] * coefs[j][10];
			res[10][j] = matrix[4][1] * coefs[10][j];
			for (int k = 11; k < 14; k++) {
				res[j][k] = matrix[1][5] * coefs[j][k];
				res[k][j] = matrix[5][1] * coefs[k][j];
			}
			res[j][14] = matrix[1][6] * coefs[j][14];
			res[14][j] = matrix[6][1] * coefs[14][j];
			res[j][15] = matrix[1][7] * coefs[j][15];
			res[15][j] = matrix[7][1] * coefs[15][j];
			res[j][16] = matrix[1][7] * coefs[j][16];
			res[16][j] = matrix[7][1] * coefs[16][j];
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[1][8] * coefs[j][k];
				res[k][j] = matrix[8][1] * coefs[k][j];
			}
			res[j][22] = matrix[1][9] * coefs[j][22];
			res[22][j] = matrix[9][1] * coefs[22][j];
			res[j][23] = matrix[1][10] * coefs[j][23];
			res[23][j] = matrix[10][1] * coefs[23][j];
			res[j][24] = (matrix[1][11] + matrix[1][12]) * coefs[j][24];
			res[24][j] = (matrix[11][1] + matrix[12][1]) * coefs[24][j];
			res[j][25] = matrix[1][13] * coefs[j][25];
			res[25][j] = matrix[13][1] * coefs[25][j];
		}

		for (int j = 6; j < 9; j++) {
			for (int k = 6; k < 9; k++) {
				res[j][k] = matrix[2][2] * coefs[j][k];
				res[k][j] = matrix[2][2] * coefs[k][j];
			}
			res[j][9] = matrix[2][3] * coefs[j][9];
			res[9][j] = matrix[3][2] * coefs[9][j];
			res[j][10] = matrix[2][4] * coefs[j][10];
			res[10][j] = matrix[4][2] * coefs[10][j];
			for (int k = 11; k < 14; k++) {
				res[j][k] = matrix[2][5] * coefs[j][k];
				res[k][j] = matrix[5][2] * coefs[k][j];
			}
			res[j][14] = matrix[2][6] * coefs[j][14];
			res[14][j] = matrix[6][2] * coefs[14][j];
			res[j][15] = matrix[2][7] * coefs[j][15];
			res[15][j] = matrix[7][2] * coefs[15][j];
			res[j][16] = matrix[2][7] * coefs[j][16];
			res[16][j] = matrix[7][2] * coefs[16][j];
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[2][8] * coefs[j][k];
				res[k][j] = matrix[8][2] * coefs[k][j];
			}
			res[j][22] = matrix[2][9] * coefs[j][22];
			res[22][j] = matrix[9][2] * coefs[22][j];
			res[j][23] = matrix[2][10] * coefs[j][23];
			res[23][j] = matrix[10][2] * coefs[23][j];
			res[j][24] = (matrix[2][11] + matrix[2][12]) * coefs[j][24];
			res[24][j] = (matrix[11][2] + matrix[12][2]) * coefs[24][j];
			res[j][25] = matrix[2][13] * coefs[j][25];
			res[25][j] = matrix[13][2] * coefs[25][j];
		}
		res[9][9] = matrix[3][3];
		res[9][10] = matrix[3][4];
		res[10][9] = matrix[4][3];
		res[10][10] = matrix[4][4];
		for (int j = 9, p = 3; j < 11; j++, p++) {
			for (int k = 11; k < 14; k++) {
				res[j][k] = matrix[p][5] * coefs[j][k];
				res[k][j] = matrix[5][p] * coefs[k][j];
			}
			res[j][14] = matrix[p][6] * coefs[j][14];
			res[14][j] = matrix[6][p] * coefs[14][j];
			res[j][15] = matrix[p][7] * coefs[j][15];
			res[15][j] = matrix[7][p] * coefs[15][j];
			res[j][16] = matrix[p][7] * coefs[j][16];
			res[16][j] = matrix[7][p] * coefs[16][j];
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[p][8] * coefs[j][k];
				res[k][j] = matrix[8][p] * coefs[k][j];
			}
			res[j][22] = matrix[p][9] * coefs[j][22];
			res[22][j] = matrix[9][p] * coefs[22][j];
			res[j][23] = matrix[p][10] * coefs[j][23];
			res[23][j] = matrix[10][p] * coefs[23][j];
			res[j][24] = (matrix[p][11] + matrix[p][12]) * coefs[j][24];
			res[24][j] = (matrix[11][p] + matrix[12][p]) * coefs[24][j];
			res[j][25] = matrix[p][13] * coefs[j][25];
			res[25][j] = matrix[13][p] * coefs[25][j];
		}

		for (int j = 11; j < 14; j++) {
			for (int k = 11; k < 14; k++) {
				res[j][k] = matrix[5][5] * coefs[j][k];
			}
			res[j][14] = matrix[5][6] * coefs[j][14];
			res[14][j] = matrix[6][5] * coefs[14][j];
			res[j][15] = matrix[5][7] * coefs[j][15];
			res[15][j] = matrix[7][5] * coefs[15][j];
			res[j][16] = matrix[5][7] * coefs[j][16];
			res[16][j] = matrix[7][5] * coefs[16][j];
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[5][8] * coefs[j][k];
				res[k][j] = matrix[8][5] * coefs[k][j];
			}
			res[j][22] = matrix[5][9] * coefs[j][22];
			res[22][j] = matrix[9][5] * coefs[22][j];
			res[j][23] = matrix[5][10] * coefs[j][23];
			res[23][j] = matrix[10][5] * coefs[23][j];
			res[j][24] = (matrix[5][11] + matrix[5][12]) * coefs[j][24];
			res[24][j] = (matrix[11][5] + matrix[12][5]) * coefs[24][j];
			res[j][25] = matrix[5][13] * coefs[j][25];
			res[25][j] = matrix[13][5] * coefs[25][j];
		}
		res[14][14] = matrix[6][6];
		res[14][15] = matrix[6][7] * coefs[14][15];
		res[14][16] = matrix[6][7] * coefs[14][16];
		res[15][14] = matrix[7][6] * coefs[15][14];
		res[16][14] = matrix[7][6] * coefs[16][14];
		for (int k = 17; k < 22; k++) {
			res[14][k] = matrix[6][8] * coefs[14][k];
			res[k][14] = matrix[8][6] * coefs[k][14];
		}
		res[14][22] = matrix[6][9] * coefs[14][22];
		res[22][14] = matrix[9][6] * coefs[22][14];
		res[14][23] = matrix[6][10] * coefs[14][23];
		res[23][14] = matrix[10][6] * coefs[23][14];
		res[14][24] = (matrix[6][11] + matrix[6][12]) * coefs[14][24];
		res[24][14] = (matrix[11][6] + matrix[12][6]) * coefs[24][14];
		res[14][25] = matrix[6][13] * coefs[14][25];
		res[25][14] = matrix[13][6] * coefs[25][14];

		for (int j = 15; j < 17; j++) {
			for (int k = 15; k < 17; k++) {
				res[j][k] = matrix[7][7] * coefs[j][k];
			}
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[7][8] * coefs[j][k];
				res[k][j] = matrix[8][7] * coefs[k][j];
			}
			res[j][22] = matrix[7][9] * coefs[j][22];
			res[22][j] = matrix[9][7] * coefs[22][j];
			res[j][23] = matrix[7][10] * coefs[j][23];
			res[23][j] = matrix[10][7] * coefs[23][j];
			res[j][24] = (matrix[7][11] + matrix[7][12]) * coefs[j][24];
			res[24][j] = (matrix[11][7] + matrix[12][7]) * coefs[24][j];
			res[j][25] = matrix[7][13] * coefs[j][25];
			res[25][j] = matrix[13][7] * coefs[25][j];
		}

		for (int j = 17; j < 22; j++) {
			for (int k = 17; k < 22; k++) {
				res[j][k] = matrix[8][8] * coefs[j][k];
			}
			res[j][22] = matrix[8][9] * coefs[j][22];
			res[22][j] = matrix[9][8] * coefs[22][j];
			res[j][23] = matrix[8][10] * coefs[j][23];
			res[23][j] = matrix[10][8] * coefs[23][j];
			res[j][24] = (matrix[8][11] + matrix[8][12]) * coefs[j][24];
			res[24][j] = (matrix[11][8] + matrix[12][8]) * coefs[24][j];
			res[j][25] = matrix[8][13] * coefs[j][25];
			res[25][j] = matrix[13][8] * coefs[25][j];
		}

		for (int j = 22; j < 26; j++) {
			res[j][22] = matrix[9][9] * coefs[j][22];
			res[j][23] = matrix[9][10] * coefs[j][23];
			res[23][j] = matrix[10][9] * coefs[23][j];
			res[j][24] = (matrix[9][11] + matrix[9][12]) * coefs[j][24];
			res[24][j] = (matrix[11][9] + matrix[12][9]) * coefs[24][j];
			res[j][25] = matrix[9][13] * coefs[j][25];
			res[25][j] = matrix[13][9] * coefs[25][j];
		}
		for (int j = 23; j < 26; j++) {
			res[j][23] = matrix[10][10] * coefs[j][23];
			res[j][24] = (matrix[10][11] + matrix[10][12]) * coefs[j][24];
			res[24][j] = (matrix[11][10] + matrix[12][10]) * coefs[24][j];
			res[j][25] = matrix[10][13] * coefs[j][25];
			res[25][j] = matrix[13][10] * coefs[25][j];
		}
		res[24][24] = (matrix[11][11] + matrix[11][12] + matrix[12][12] + matrix[12][11]) * coefs[24][24];
		res[24][25] = (matrix[11][13] + matrix[12][13]) * coefs[24][25];
		res[25][24] = (matrix[13][11] + matrix[13][12]) * coefs[25][25];
		res[25][25] = matrix[13][13] * coefs[25][25];

		return res;
	}
}
