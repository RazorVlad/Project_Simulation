package tests;

import exceptions.DataRequiredEx;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import mathModel.Education;
import mathModel.ModelObjectsContainer;
import mathModel.Population;
import mathModel.Statistics;

public class EducationTests extends TestCase {

	public EducationTests(String testName) {
		super(testName);
	}

	public void setPopulation() {
		Statistics newborns = new Statistics(1996, new double[] { 10000, 10000, 10000, 10000, 10000, 10000,
				10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000 });
		double deathCoef = 0.1;
		Statistics collegeAppl9 = new Statistics(2011, new double[] { 0.5, 0.5, 0.5, 0.5, 0.5, 0.5 });
		Statistics collegeApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Statistics univerApplAfter12 = new Statistics(2014, new double[] { 0.5, 0.5, 0.5 });
		Population population;
		try {
			population = new Population(newborns, deathCoef, collegeAppl9, collegeApplAfter12,
					univerApplAfter12);
			population.proceed();
			ModelObjectsContainer.getInstance().setPopulation(population);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testEdu() {
		setPopulation();

		Statistics collegeStud0 = new Statistics(2011, new double[] { 10000, 10000, 10000 });

		Statistics fieldCollegeAppl9 = new Statistics(2011, new double[] { 0.2, 0.4, 0.5 });
		Statistics fieldCollegeAppl12 = new Statistics(2011, new double[] { 0.3, 0.4, 0.1 });
		Statistics succCollegeGrad = new Statistics(2011, new double[] { 0.1, 0.2, 0.3 });
		Statistics statFieldCollegeAppl = new Statistics(2011, new double[] { 1000, 2000, 1500 });

		Statistics univerStud = new Statistics(2011, new double[] { 5000, 5000, 5000 });

		Statistics univerApplAmongCollegeGrad = new Statistics(2011, new double[] { 0.1, 0.2, 0.3 });
		Statistics fieldUniverAppl12 = new Statistics(2011, new double[] { 0.2, 0.3, 0.4 });
		Statistics succUniverGrad = new Statistics(2011, new double[] { 0.1, 0.3, 0.5 });
		Statistics statFieldUniverAppl = new Statistics(2011, new double[] { 500, 1000, 900 });

		Statistics collegeGradWork = new Statistics(2011, new double[] { 0.2, 0.3, 0.4 });
		Statistics univerGradWork = new Statistics(2011, new double[] { 0.1, 0.2, 0.3 });
		Statistics univerGradEdu = new Statistics(2011, new double[] { 0.2, 0.3, 0.4 });
		Statistics univerGradRnD = new Statistics(2011, new double[] { 0.7, 0.5, 0.3 });

		Education edu = new Education(collegeStud0, fieldCollegeAppl9, fieldCollegeAppl12,
				statFieldCollegeAppl, succCollegeGrad, univerApplAmongCollegeGrad, fieldUniverAppl12,
				statFieldUniverAppl, succUniverGrad, collegeGradWork, univerGradWork, univerGradEdu,
				univerGradRnD, univerStud);
		try {
			edu.proceed();
		} catch (DataRequiredEx e) {
			e.printStackTrace();
		}

		Statistics collegeAppl = new Statistics(2014, new double[] { 1575, 2700, 2475 });
		Statistics collegeGrad = new Statistics(2014, new double[] { 100, 400, 450 });

		assertTrue(FuncForTest.equals(collegeAppl, edu.getCollegeAppl()));
		assertTrue(FuncForTest.equals(collegeGrad, edu.getCollegeGrad()));

		Statistics univerAppl = new Statistics(2014, new double[] { 460, 755, 1035 });
		Statistics univerGrad = new Statistics(2014, new double[] { 50, 300, 450 });

		assertTrue(FuncForTest.equals(univerAppl, edu.getuniverAppl()));
		assertTrue(FuncForTest.equals(univerGrad, edu.getuniverGrad()));

		Statistics staffWork = new Statistics(2014, new double[] { 25, 180, 315 });
		Statistics staffEdu = new Statistics(2014, new double[] { 10, 90, 180 });
		Statistics staffRnD = new Statistics(2014, new double[] { 35, 150, 135 });

		assertTrue(FuncForTest.equals(staffWork, edu.getStaffWork()));
		assertTrue(FuncForTest.equals(staffEdu, edu.getStaffEdu()));
		assertTrue(FuncForTest.equals(staffRnD, edu.getStaffRnD()));

	}

	public static void main(String[] args) {
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
		suite.addTest(new EducationTests("testEdu"));
		runner.doRun(suite);
	}

}
