package tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import mathModel.Population;
import mathModel.Statistics;

public class PopulationTests extends TestCase {

	public PopulationTests(String testName) {
		super(testName);
	}

	public void testValidSolutionForNormalData() {
		Statistics newborns = new Statistics(1996, new double[] { 10000, 10000, 10000, 10000, 10000, 10000,
				10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 9000, 9000, 9000, 9000, 9000, 9000 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 4500, 4500, 4500 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 4500, 4500, 4500 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 2250, 2250, 2250 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 2250, 2250, 2250 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public void testValidSolutionForDifferentNewbornsData() {
		Statistics newborns = new Statistics(1996, new double[] { 1000, 2000, 3000, 4000, 5000, 6000, 7000,
				8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 900, 1800, 2700, 3600, 4500, 5400 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 900, 1125, 1350 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 900, 1125, 1350 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public void testValidSolutionForDifferentSchoolGrad9Data() {
		Statistics newborns = new Statistics(1996, new double[] { 1000, 2000, 3000, 4000, 5000, 6000, 7000,
				8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.9, 0.8, 0.7, 0.6, 0.5, 0.4 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 900, 1800, 2700, 3600, 4500, 5400 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 1440, 2250, 3240 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 2160, 2250, 2160 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 720, 1125, 1620 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 720, 1125, 1620 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public void testValidSolutionForDifferentCollegeApplAmongGrad9Data() {
		Statistics newborns = new Statistics(1996, new double[] { 1000, 2000, 3000, 4000, 5000, 6000, 7000,
				8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.1, 0.1, 0.1 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.9, 0.7, 0.5 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 900, 1800, 2700, 3600, 4500, 5400 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 180, 225, 270 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 1620, 1575, 1350 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public void testValidSolutionForDifferentSchoolGrad12Data() {
		Statistics newborns = new Statistics(1996, new double[] { 1000, 2000, 3000, 4000, 5000, 6000, 7000,
				8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.9, 0.7, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.1, 0.1, 0.1 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 900, 1800, 2700, 3600, 4500, 5400 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 1800, 2250, 2700 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 1620, 1575, 1350 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 180, 225, 270 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public void testValidSolutionForDifferentData() {
		Statistics newborns = new Statistics(1996, new double[] { 1000, 2000, 3000, 4000, 5000, 6000, 7000,
				8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.9, 0.8, 0.7, 0.6, 0.5, 0.4 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.9, 0.7, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.1, 0.3, 0.5 });
		Population population = null;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Statistics schoolGrad9 = new Statistics(2014, new double[] { 900, 1800, 2700, 3600, 4500, 5400 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad9(), schoolGrad9));

		Statistics schoolGrad12 = new Statistics(2014, new double[] { 1440, 2250, 3240 });
		assertTrue(FuncForTest.equals(population.getSchoolGrad12(), schoolGrad12));

		Statistics CollegeApplAmongGrad9 = new Statistics(2014, new double[] { 2160, 2250, 2160 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad9(), CollegeApplAmongGrad9));

		Statistics CollegeApplAmongGrad12 = new Statistics(2014, new double[] { 1296, 1575, 1620 });
		assertTrue(FuncForTest.equals(population.getCollegeApplAmongGrad12(), CollegeApplAmongGrad12));

		Statistics UniverApplAmongGrad12 = new Statistics(2014, new double[] { 144, 675, 1620 });
		assertTrue(FuncForTest.equals(population.getUniverApplAmongGrad12(), UniverApplAmongGrad12));

	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTest(new PopulationTests("testValidSolutionForNormalData"));
		suite.addTest(new PopulationTests("testValidSolutionForDifferentNewbornsData"));
		suite.addTest(new PopulationTests("testValidSolutionForDifferentSchoolGrad9Data"));
		suite.addTest(new PopulationTests("testValidSolutionForDifferentCollegeApplAmongGrad9Data"));
		suite.addTest(new PopulationTests("testValidSolutionForDifferentSchoolGrad12Data"));
		suite.addTest(new PopulationTests("testValidSolutionForDifferentData"));
		runner.doRun(suite);
	}
}
