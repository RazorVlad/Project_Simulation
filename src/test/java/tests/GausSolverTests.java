package tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import mathModel.manFun.gaussSolver.Determinant;
import mathModel.manFun.gaussSolver.GaussSolver;

public class GausSolverTests extends TestCase {

	public GausSolverTests(String testName) {
		super(testName);
	}

	public void matrixMultiplicationTest() {

		double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] matrixB = new double[][] { { 2, 2, 2 }, { 4, 4, 4 }, { 6, 6, 6 } };
		double[][] result = GaussSolver.matrixMultiplication(matrixA, matrixB);
		double[][] resultMatrix = new double[][] { { 28, 28, 28 }, { 64, 64, 64 }, { 100, 100, 100 } };
		for (int i = 0; i < resultMatrix.length; i++) {
			for (int j = 0; j < resultMatrix[0].length; j++) {
				assertTrue(resultMatrix[i][j] == result[i][j]);
			}
		}
	}

	public void vectorMatrixMultiplicationTest() {
		double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[] matrixB = new double[] { 2, 4, 6 };
		double[] result = GaussSolver.vectorMatrixMultiplication(matrixA, matrixB);
		double[] resultMatrix = new double[] { 28, 64, 100 };
		for (int i = 0; i < resultMatrix.length; i++) {
			assertTrue(resultMatrix[i] == result[i]);
		}
	}

	public void transMatrixTest() {
		double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] result = mathModel.manFun.gaussSolver.Determinant.transMatrix(matrixA);
		double[][] resultMatrix = new double[][] { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
		for (int i = 0; i < resultMatrix.length; i++) {
			for (int j = 0; j < resultMatrix[0].length; j++) {
				assertTrue(resultMatrix[i][j] == result[i][j]);
			}
		}
	}

	public void reverseMatrixTest() {
		double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 10 } };
		double[][] result = GaussSolver.reverseMatrix(matrixA);
		double[][] resultMatrix = new double[][] { { (-0.6666), (-1.3333), 1 }, { (-0.6666), (3.6666), -2 },
				{ 1, -2, 1 } };
		for (int i = 0; i < resultMatrix.length; i++) {
			for (int j = 0; j < resultMatrix[0].length; j++) {
				assertTrue(resultMatrix[i][j] - result[i][j] < 0.0001);
			}
		}
	}

	public void DetTest() {
		double[][] matrixA = new double[][] { { 1, 2, 3 }, { 0, 1, 0 }, { 7, 8, 9 } };
		double result = Determinant.det(matrixA);
		double det = -12;
		assertTrue(det == result);
	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTest(new GausSolverTests("matrixMultiplicationTest"));
		suite.addTest(new GausSolverTests("vectorMatrixMultiplicationTest"));
		suite.addTest(new GausSolverTests("transMatrixTest"));
		suite.addTest(new GausSolverTests("reverseMatrixTest"));
		suite.addTest(new GausSolverTests("DetTest"));
		runner.doRun(suite);
	}

}
